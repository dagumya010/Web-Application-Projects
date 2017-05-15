var express = require('express');
var router = express.Router();
var pool = require('../database');


router.post('/',function (request, response) {
    // check if the administrator is logged in
    if(!request.session.key || !request.session.key["logged_in"] || !request.session.key["user"]){
        response.json({"message":"You are not currently logged in"});
        return;
    }
    var currentLoggedInUser = request.session.key["user_name"];

    var update_parameters = request.body;
    var toBeUpdated = new Object();
    if(update_parameters.length === 0){
        response.json({"message":"The input you provided is not valid"});
    }else{
        if(update_parameters.hasOwnProperty('fname')){
            toBeUpdated.fname = update_parameters.fname;
        }
        if(update_parameters.hasOwnProperty('lname')){
            toBeUpdated.lname = update_parameters.lname;
        }
        if(update_parameters.hasOwnProperty('address')){
            toBeUpdated.address = update_parameters.address;
        }
        if(update_parameters.hasOwnProperty('city')){
            toBeUpdated.city = update_parameters.city;
        }
        if(update_parameters.hasOwnProperty('state')){
            toBeUpdated.state = update_parameters.state;
        }
        if(update_parameters.hasOwnProperty('zip')){
            toBeUpdated.Zip = update_parameters.zip;
        }
        if(update_parameters.hasOwnProperty('email')){
            toBeUpdated.email = update_parameters.email;
        }
        if(update_parameters.hasOwnProperty('username')){
            toBeUpdated.username = update_parameters.username;
        }
        if(update_parameters.hasOwnProperty('password')){
            toBeUpdated.password = update_parameters.password;
        }
    }


    pool.getConnection(function (err, connection) {
        if(err) {
            console.log(err);
            return;
        }
        connection.query('UPDATE Users SET ? WHERE username = ?',[toBeUpdated, currentLoggedInUser],
            function (err, results) {
             connection.release();
            if(err){
                console.log(err)
            }else if(results.changedRows ==0){
                response.json({"message":"The input you provided is not valid"});
            }else{
                var successMsg = request.session.key["first_nm"] + " your information was successfully updated";
                response.json({"message":successMsg});
            }
        })
    })


});



module.exports = router;