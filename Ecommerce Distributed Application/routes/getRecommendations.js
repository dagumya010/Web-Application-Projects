var express = require('express');
var router = express.Router();
var pool = require('../database');

/**
This method handles the /getRecommendations route. Provided with an http request with a product asin (productId) it
returns the products that were bought the product id provided.
*/
router.post('/',function (request, response) {
    // check that the user is logged into their session id.
    if (request.session.key && !request.session.key["logged_in"]) {
        return response.json({"message": "You are not currently logged in"});
    }
    // extract the search value from the request object
    var search_value = request.body.asin;
    // get a connection from the pool and connect to database
    pool.getConnection(function (err,connection) {
        if(err){
            console.log("Error executing the connection in get recommendations");
            console.log(err);
            return
        }
        // execute connection
        connection.query('SELECT asin FROM'+
            '(SELECT AssociatedProduct.asin, count(*) As Rec '+
        'FROM ProductRec '+
        'INNER JOIN AssociatedProduct '+
        'on ProductRec.productId = AssociatedProduct.productId '+
        'where ProductRec.asin = ? '+
        'group by AssociatedProduct.asin '+
        'order by Rec desc '+
        'limit 5) As interm',search_value,function (err, rows) {
            // handle any error from the sql query execution
            if(err){
                console.log("Error executing selection query to get recomendation");
                return
            }
            // if no error then get the products and send them to the user.
            if(rows && rows.length > 0){
                return response.json({"message": "The action was successful", "products":rows});
            }else{
                return response.json({"message":"There are no recommendations for that product"});
            }
        });
        connection.release();
    });
});
module.exports = router;
