var express = require('express');
var router = express.Router();
var pool = require('../database');

var add_toPurchases = function(request, response){
    pool.getConnection(function (err, connection) {
        if (err) {
            console.log("Error making connection  ");
            console.log(err);
            return;
        }

        // check that the asin is in the table
        /**
         * TODO Run a match again boolean query to check if asin's are in the db
         * TODO if any of them are not then return to the user that they are not
         * @type {{}}
         */
            // insert into the Purchases Table
        var insert_user = {};
        insert_user.username = request.session.key.user_name;
        connection.query('INSERT INTO Purchase SET ? ', insert_user, function (err, result) {
            if (err) { // duplicate id
                console.log(err);
                return
            }

            var key = result.insertId;
            var to_insert = {};
            var products = request.body.products;

            products.forEach(function (product_purchased) {
                to_insert.purchaseId = key;
                to_insert.asin = product_purchased.asin;
                // insert into the orders tables with corresponding purchase key
                connection.query('INSERT INTO Orders SET ? ', to_insert, function (err, result) {
                    console.log("inserted into orders");
                    if (err) {
                        response.json({"message": "Error on insert into order"});
                    }
                });
            });

            response.json({"message": "Products added to orders"});
        });
        connection.release();
    });
};
var add_toProductRec = function(request){

    var products = request.body.products;
    var productMap = new Map();
    //
    products.forEach(function (product) {
        var map_value = [];
        products.forEach(function (product2) {
            if (product2 !== product){
                map_value.push(product2)
            }
        });
        productMap.set(product,map_value);
    });
    /**
     * Iterate over the map, for each product, insert each product in the map_value list in the db.
     */
    pool.getConnection(function (err, connection) {
    productMap.forEach(function (values, key) {
        if(err){
            console.log("Error in creating connection for insertion into ProductRec" +
                "and Associated Product");
            console.log(err);
            return
        }
        var insert_in_productRec = {};
        insert_in_productRec.asin = key.asin;

        connection.query('INSERT INTO ProductRec SET ? ',insert_in_productRec,function (err, result) {
            if(err){
                console.log("Error in executing insertion into ProductRec");
                console.log(err);
                return;
            }
            var associated_product_key = result.insertId;
            var associated_value = {};

            values.forEach(function (asso_prod) {
                associated_value.productId = associated_product_key;
                associated_value.asin = asso_prod.asin;
                connection.query('INSERT INTO AssociatedProduct SET ? ',associated_value,
                    function (err,assoc_prod_rows) {
                        if(err){
                            console.log("Error in executing insertion into Associated Products");
                            console.log(err);
                            return;
                        }else{
                            console.log("Inserted: key " + key.asin );
                            console.log("Inserted: value " + asso_prod.asin );
                            return;
                        }
                })
            })
        });
    });
    connection.release();
    });
};

router.post('/',function (request, response) {
    /**
     * Check that  the user is logged into the system
     */
    if (request.session.key && request.session.key["logged_in"]) {
        if (!request.session.key["user"]) {
            response.json({"message": "You must be a user to perform this action"});
            return;
        }

    } else {
        response.json({"message": "You are not currently logged in"});
        return;
    }
    /**
     *  Make sure that there products to buy
     */
    if (request.body.products === []) {
        console.log(request.body.products);
        return response.json({"message": "There are no products that match that criteria"});
    }
    /**
     *  Handle the addition of products to Purchases and Orders
     */
    add_toPurchases(request, response);
    /**
     *  Handle the addition of products to ProductRec and Associated Product for Recommendations
     */
    add_toProductRec(request);

});
module.exports = router;