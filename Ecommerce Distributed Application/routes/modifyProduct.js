var express = require('express');
var router = express.Router();
var pool = require('../database');


router.post('/', function (request, response) {
    if (request.session.key && request.session.key["logged_in"]) {
        if (!request.session.key["admin"]) {
            response.json({"message": "You must be an admin to perform this action"});
            return;
        }
    } else {
        response.json({"message": "You are not currently logged in"});
        return;
    }
    if (!request.body.asin || !request.body.productName || !request.body.productDescription || !request.body.group ) {
        response.json({"message": "The input you provided is not valid"})
        return;
    }
    var product_name = request.body.productName;
    var product_description = request.body.productDescription;
    var group = request.body.group;

    /**
     * Insert product
     */
    var toAddtoDB = {
        Pname: product_name,
        PDescription: product_description,
        PGroup: group
    };
    /**
     * Check if the productId is valid
     */
    pool.getConnection(function (err, connection) {
        if(err){
            response.json({"message":"Error accessing database"});
            return;
        }
        connection.query('UPDATE Products SET ? Where asin = ?', [toAddtoDB, request.body.asin], function (err, result) {
            connection.release();
            if (err) {
                console.log(err);
                response.json({"message": "The input you provided is not valid"});
                return;
            }

            if(result.changedRows > 0){
                var successMsg = request.body.productName + " was successfully updated";
                response.json({"message": successMsg});
            }else {
                response.json({"message": "The input you provided is not valid"});
            }

        });

    })


})
module.exports = router;
