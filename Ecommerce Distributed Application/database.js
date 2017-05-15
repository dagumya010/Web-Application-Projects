var express = require('express');
var mysql = require('mysql');
var router = express.Router();

var pool = mysql.createPool({
    connectionLimit: 100,
    host: '', // aws RDS host
    user: '', // username for the aws instance
    password: '', // password for the aws instance
    database: '' // schema
});
module.exports = pool;

