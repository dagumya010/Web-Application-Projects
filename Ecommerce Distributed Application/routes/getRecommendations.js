var express = require('express');
var router = express.Router();
var pool = require('../database');

router.post('/',function (request, response) {
    if (request.session.key && !request.session.key["logged_in"]) {
        return response.json({"message": "You are not currently logged in"});
    }
    var search_value = request.body.asin;
    pool.getConnection(function (err,connection) {
        if(err){
            console.log("Error executing the connection in get recommendations");
            console.log(err);
            return
        }
        connection.query('SELECT asin FROM'+
            '(SELECT AssociatedProduct.asin, count(*) As Rec '+
        'FROM ProductRec '+
        'INNER JOIN AssociatedProduct '+
        'on ProductRec.productId = AssociatedProduct.productId '+
        'where ProductRec.asin = ? '+
        'group by AssociatedProduct.asin '+
        'order by Rec desc '+
        'limit 5) As interm',search_value,function (err, rows) {
            if(err){
                console.log("Error executing selection query to get recomendation");
                return
            }
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