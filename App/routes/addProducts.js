var express = require('express');
var router = express.Router();
var pool = require('../database');


router.post('/',function (request, response) {
    // check if user is logged in, then check if the user is an admin based on the session.
    if(request.session.key && request.session.key["logged_in"]){
        if(!request.session.key["admin"]){
            response.json({"message":"You must be an admin to perform this action"});
            return;
        }
    }else{
        response.json({"message":"You are not currently logged in"});
        return;
    }

    // make sure that the body atleast has a productId.
    if(!request.body.asin){
        response.json({"message":"The input you provided is not valid"});
        return;
    }

    // parse the Json body object
    var product_id = request.body.asin;
    var product_name = request.body.productName;
    var group = request.body.group;

    /**
     * Make a product object
     */
    var toBeAdded = {
        asin: product_id,
        Pname: product_name,
        PDescription: request.body.productDescription,
        PGroup: group
    };
    /**
     *  Connect to database and insert the Product.
     */
    pool.getConnection(function (err, connection) {
        if(err){
            console.log(err)
            return;
        }

        connection.query('INSERT INTO Products SET ?',toBeAdded, function(err, result){
            connection.release();

            if(err){ // duplicate id
                response.json({"message":"The input you provided is not valid"});
            }else{

                var successMsg = product_name + " was successfully added to the system";
                response.json({"message":successMsg});
            }
        })

    })
});

module.exports = router;
