var express = require('express');
var router = express.Router();
var pool = require('../database');


router.post('/',function (request, response) {
    // does not need to be authorized
    var record;
    var username = request.body.username;
    var password = request.body.password;
    var firstName = request.body.fname;
    var lastName = request.body.lname;
    var email = request.body.email;
    var address = request.body.address;
    var state = request.body.state;
    var city = request.body.city;
    var zip = request.body.zip;

    record = {
        username: username,
        password: password,
        fname: firstName,
        lname: lastName,
        email: email,
        address: address,
        state: state,
        city: city,
        zip: zip
    };
    pool.getConnection(function (err, connection) {
        connection.query('INSERT INTO Users SET ?',record, function(err, result){
            connection.release();
            if(err){
                console.log(err);
                response.json({"message":"The input you provided is not valid"});
                return;
            }else{
                var reg_usersuccessMsg = firstName+ " was registered successfully";
                response.json({"message":reg_usersuccessMsg});
                return;
            }
        })
    })
});



module.exports = router;
