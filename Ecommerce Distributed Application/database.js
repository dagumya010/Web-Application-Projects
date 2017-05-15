var express = require('express');
var mysql = require('mysql');
var router = express.Router();
var pool = mysql.createPool({
    connectionLimit: 100,
    host: '',
    user: '',
    password: '',
    database: 'ecommerceapplication'
});
module.exports = pool;

