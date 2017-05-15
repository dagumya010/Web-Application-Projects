var express = require('express');
var router = express.Router();
var pool = require('../database');

router.post('/',function (request, response) {

    if (!request.body) {
        return response.json({'message': "There are no products that match that criteria"});
    }

    /**
     * Get a connection and execute the query
     */
    pool.getConnection(function (err, connection) { // Get a connection from the pool
        if (err) {
            console.log(err);
            return;
        }


        if(request.body.asin){
            console.log("Asin only clause");
            //parse
            var search_asin = request.body.asin;
            var query = 'SELECT asin, Pname as ProductName from Products where asin like ?';
            // execute query
            connection.query(query,search_asin,function(err, rows){
                connection.release();
                if(err) {
                    console.log(err);
                    return;
                }else
                if(rows.length !== 0){
                    var returnObj = [];
                    for(var i = 0; i < rows.length; i++){
                        if(rows[i].asin === request.body.asin){
                            returnObj.push(rows[i]);
                        }
                    }
                    if(returnObj.length === 0){
                        return response.json({'message': "There are no products that match that criteria"});
                    }else{
                        return response.json({"product": returnObj});
                    }
                }else{
                    response.json({'message':"There are no products that match that criteria"});
                }
            });
        }else
        if(request.body.group && request.body.keyword){  // then check for the keyword and group combination
            console.log("Group and Keyword only clause");
            var search_group = request.body.group;
            var search_key = '%' + request.body.keyword + '%';
            var query = 'SELECT asin, Pname as ProductName '+
            'FROM Products '+
            'where Pname or Pdescription like ? and Pgroup like ? limit 20;';
            connection.query(query,[search_key,search_group],function(err, rows){
                connection.release();
                if(err) {
                    console.log(err);
                }else
                if(rows.length !== 0){
                    response.json({"product":rows});
                }else{
                    response.json({'message':"There are no products that match that criteria"});
                }
            });
        }else
        if (request.body.keyword) { // then check for the keyword only
            console.log("Keyword only clause");

            var search_keyword = "%" + request.body.keyword + "%";
            var keyword_query = 'SELECT asin, Pname as ProductName '+
            'FROM Products '+
            'where Pname or Pdescription like ? limit 20';
            connection.query(keyword_query,search_keyword, function (err, rows) {
                connection.release();
                if (err) {
                    console.log(err);
                } else if (rows.length !== 0) {
                    response.json({"product": rows});
                } else {
                    response.json({'message': "There are no products that match that criteria"});
                }
            });
        }else
        if(request.body.group){ // then check for group only
            console.log("Group only clause");
            var group_toSearch = request.body.group;
            connection.query('SELECT asin, Pname as ProductName '+
            'FROM Products '+
            'WHERE Pgroup '+
            'Like ? LIMIT 20 ',group_toSearch,function(err, rows){
                connection.release();
                if(err){
                    console.log(err);
                }else
                if(rows.length !== 0){
                    response.json({'product':rows});
                }else{
                    response.json({'message':"There are no products that match that criteria"});
                }
            });
        }else {
            connection.query('SELECT asin, ' +
                'Pname AS productName ' +
                'FROM Products LIMIT 20',function(err, rows){
                connection.release();
                if(err){
                    console.log(err);
                }else
                if(rows.length !== 0){
                    response.json({"product":rows});
                }else{
                    response.json({'message':"There are no products that match that criteria"});
                }
            });
        }

    });
});

module.exports = router;

