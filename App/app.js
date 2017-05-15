var express = require('express');
var bodyParser = require('body-parser');
var redis = require('redis');
var expressSession = require('express-session');
var redisStore = require('connect-redis')(expressSession);
var redis_client = redis.createClient(6379,"appsession-001.p52hnv.0001.usw2.cache.amazonaws.com",
 {no_ready_check:true});
// var redis_client = redis.createClient();
var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

/*
* Import the route paths from the other directories.
 */
var login = require('./routes/login');
var logout = require('./routes/logout');
var addProducts = require('./routes/addProducts');
var modifyProducts = require('./routes/modifyProduct');
var registerUsers = require('./routes/registerUsers');
var updateInfo = require('./routes/updateInfo');
var viewProducts = require('./routes/viewProducts');
var viewUsers = require('./routes/viewUsers');
/**
 * TODO: Need to make the actual routes and files in the routes folder
 */
var productsPurchased = require('./routes/productsPurchased');
var buyProducts = require('./routes/buyProducts');
var getRecommendations = require('./routes/getRecommendations');


/**
 *  Establish the session that all the routes will use.
 */
/**
 * TODO : When you deploy to the server, change this to AWS redis storage.
 */
var redis_store = new redisStore({
    // host:'localhost',
    port: 6379,
    client: redis_client,
    ttl: 260
});
var sessionOptions = {
    secret:"jadbjabjidsbiavdg727301023nkonhko1b3opsnfkw4-82384-2840-23-4-8213kfldmsl",
    store: redis_store,
    saveUninitialized: false,
    resave: true
};
app.use(expressSession(sessionOptions));
/**
 *  Routes used to handle business logic.
 */
app.use('/login', login);
app.use('/logout', logout);
app.use('/addProducts', addProducts);
app.use('/modifyProduct', modifyProducts);
app.use('/registerUser', registerUsers);
app.use('/updateInfo', updateInfo);
app.use('/viewProducts', viewProducts);
app.use('/viewUsers', viewUsers);

app.use('/productsPurchased', productsPurchased);
app.use('/buyProducts', buyProducts);
app.use('/getRecommendations', getRecommendations);
/**
 * Establish a listening server.
 */
app.get('/', function (req, res) {
    res.status(200).send();
});

app.listen(4040,function(err){
    console.log("Server is listening on port 4040")
});

module.exports = app;
