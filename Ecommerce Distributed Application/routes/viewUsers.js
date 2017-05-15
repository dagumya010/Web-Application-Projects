var express = require('express');
var router = express.Router();
var pool = require('../database');


/**
 * Handles the /viewUsers business logic.
 */
router.post('/',function (request, response) {
    // check if the administrator is logged in
    if(request.session.key && request.session.key["logged_in"]){
        if(!request.session.key["admin"]){
            response.json({"message":"You must be an admin to perform this action"});
            return;
        }
    }else{
        response.json({"message":"You are not currently logged in"});
        return;
    }

    if(request.body.fname && request.body.lname){
        connectAndRetrieve("both", request, response);
    }else if(request.body.lname){
        console.log("Redirect to last");
        connectAndRetrieve("last", request, response);
    }else if(request.body.fname){
        connectAndRetrieve("first", request, response);
    }
    if (request.body = {}){
        connectAndRetrieve("all",request, response);
    }
});
/**
 * Helper function to connect to the database and retrieve the rows, given a choice of
 * the parameters in our request object.
 */
var connectAndRetrieve = function(choice, request, response){
    pool.getConnection(function (err, connection) {
        if(err){
            console.log(err);
            return;
        }
        switch(choice){
            case "both":
                var fname = request.body.fname;
                var lname = request.body.lname;
                connection.query('SELECT fname , lname, userid ' +
                    'FROM Users ' +
                    'WHERE fname LIKE ? AND lname LIKE ?',[fname,lname],function (err, rows) {
                    if(err){
                        console.log(err);
                    }else
                        if(rows.length !== 0){
                        response.json({"message":"The action was successsful", "user":rows});
                    }else{
                        response.json({"message": "There are no users that match that criteria"});
                    }
                });
                break;
            case "first":
                var fname = request.body.fname;
                connection.query('SELECT fname , lname, userid ' +
                    'FROM Users ' +
                    'WHERE fname LIKE ?',fname,function (err, rows) {
                    if(err){
                        console.log(err);
                    }else
                     if(rows.length !== 0){
                        response.json({"message":"The action was successsful", "user":rows});
                    }else{
                        response.json({"message": "There are no users that match that criteria"});
                    }
                });
                break;
            case "last":
                console.log("In last")
                var lname = request.body.lname;
                connection.query('SELECT fname , lname, userid ' +
                    'FROM Users ' +
                    'WHERE lname LIKE ?',lname,function (err, rows) {
                    if(err){
                        console.log(err);
                    }else if(rows.length !== 0){
                        response.json({"message":"The action was successsful", "user":rows});
                    }else{
                        response.json({"message": "There are no users that match that criteria"});
                    }
                });
                break;
            default:
                connection.query('SELECT fname , lname, userid ' +
                    'FROM Users LIMIT 100 ',function (err, rows) {
                    if(err){
                        console.log(err);
                    }else
                    if(rows.length !== 0){
                        response.json({"message":"The action was successsful", "user":rows});
                    }else{
                        response.json({"message": "There are no users that match that criteria"});
                    }
                })
        }
        connection.release();
    })
};
module.exports = router;
