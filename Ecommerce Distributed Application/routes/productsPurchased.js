var express = require('express');
var router = express.Router();
var pool = require('../database');
/**
This method connect is reponsible for connecting to the database and extracting all the products 
bought by a user.
*/
var connect = function(req, res){
    var user = req.body.username;
    pool.getConnection(function(err, connection){
        if(err){
            console.log(err);
            return
        }
        connection.query('SELECT Products.Pname AS ProductName, intermediate.Quantity FROM' +
            '(SELECT Orders.asin, count(*) as Quantity FROM Purchase ' +
            'INNER JOIN Orders on Purchase.purchaseId = Orders.purchaseId '+
            'WHERE username like ? '+
            'GROUP BY asin)AS intermediate '+
            'inner join Products on Products.asin = intermediate.asin',user,function(err, result){
            if(err){
                console.log(err);
                return
            }else{
                return handle_row(res,result);
            }
        });
    });
};

/**
This method handles the sending of the products that are extracted through the connect method
the products are sent back to the user. 
*/

var handle_row = function(res, rows){
    if(rows.length === 0){
        res.json({"message":"There are no users that match that criteria"})
    }
    res.json({"message":"The action was successful", "products":rows});
};

router.post('/',function (request, response) {
    if(request.session.key && request.session.key["logged_in"]){
        if(!request.session.key["admin"]){
            response.json({"message":"You must be an admin to perform this action"});
            return;
        }
    }else{
        response.json({"message":"You are not currently logged in"});
        return;
    }

    connect(request,response)

});


module.exports = router;
