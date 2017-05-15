var express = require('express');
var mysql = require('mysql');
var router = express.Router();

// var pool = mysql.createPool({
//     connectionLimit: 100,
//     host: 'myinstance.cnkvfbeumu7f.us-west-2.rds.amazonaws.com',
//     user: 'davidagumya',
//     password: 'joseph1234',
//     database: 'ecommerceapplication'
// });

// Test Database
// var pool = mysql.createPool({
//     connectionLimit: 100,
//     host: 'secondarydb.cnkvfbeumu7f.us-west-2.rds.amazonaws.com',
//     user: 'davidagumya',
//     password: 'joseph1234',
//     database: 'ecommerceapplication'
// });
// Bigger DataBase
var pool = mysql.createPool({
    connectionLimit: 100,
    host: 'thirddatabase.cnkvfbeumu7f.us-west-2.rds.amazonaws.com',
    user: 'davidagumya',
    password: 'joseph1234',
    database: 'ecommerceapplication'
});
module.exports = pool;

