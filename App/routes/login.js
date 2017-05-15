var express = require('express');
var router = express.Router();
var connection_pool = require('../database');


/**
 * This function, will connect to the database, retrieve the neccessary records and then evaluate if the
 * username and password belong to a registered user in our system.
 * @param request The request object from the client.
 * @param response Response to be sent to the client.
 * @param user_name Username, client entered onto the form.
 * @param pass_word Password, client entered into the form, whose request we are processing.
 */
var verifyUser = function (request, response, user_name, pass_word) {
    connection_pool.getConnection(function(err, db_connection)
    {
        if (err) console.log(err);
        db_connection.query('SELECT username, password, fname FROM Users Where username LIKE ?', user_name, function (err, rows) {
            if (err) {
                console.log(err)
            } else {
                if (rows.length === 0) {
                    // check for admin if user not found in user table
                    db_connection.query('SELECT username, password, fname FROM Administrators Where username ' +
                        'Like ?', user_name, function (err, result_rows) {
                        db_connection.release();
                        if (result_rows.length === 0) {
                             return response.json({"message": "There seems to be an issue with the " +
                             "username/password combination that you entered"});
                        } else {
                            if (result_rows[0].username === user_name && result_rows[0].password === pass_word) {
                                var response_msg = "Welcome " + result_rows[0].fname;
                                // set the session management variables
                                var session_admin = {
                                    logged_in: true,
                                    user_name: request.body.username,
                                    user: false,
                                    admin: true,
                                    first_nm: result_rows[0].fname
                                };
                                request.session.key = session_admin;
                                response.json({"message": response_msg});
                                return;
                            }
                        }
                    })
                } else {
                    // found user in the Users table
                    if (rows[0].length !==0 && rows[0].password === pass_word && rows[0].username === user_name) {
                        db_connection.release();
                        // set the session management variables
                        var session_user = {
                            logged_in: true,
                            user_name: request.body.username,
                            user: true,
                            admin: false,
                            first_nm: rows[0].fname
                        };
                        request.session.key = session_user;
                        var message = "Welcome " + rows[0].fname;
                        response.json({"message": message});
                        return;

                    } else {
                        response.json({"message": "There seems to be an issue with the " +
                        "username/password combination that you entered"});
                        return;
                    }
                }
            }
        })

    })
};
/**
 *  Handles the post method in regards to the login business functionality.
 *  Logic : Checks if the user if already logged in.
 *          -> if logged in then we check the session properties. If the current
 *          -> session's user property is equal to the username in the request
 *          -> then we know this is the same user and we keep the session going.
 *          -> otherwise we
 */

router.post('/', function (request, response) {
    //case 1 -> User is already logged in on this session
    if (request.session && request.session.key) {
        if (request.session.key["user"] && request.session.key["user"] === request.body.username) {
            var user_logged_in = "Welcome" + request.session.key["first_nm"];
            response.json({"message": user_logged_in});
            return;
        }
    }
    if (request.body.username && request.body.password) {
        console.log("2nd clause");
        console.log(request.body.username);
        return verifyUser(request, response, request.body.username, request.body.password);

    }
    response.json({"message": "There seems to be an issue with the " +
    "username/password combination that you entered"});

});

module.exports = router;
