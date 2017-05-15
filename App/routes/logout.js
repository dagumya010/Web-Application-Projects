var express = require('express');
var router = express.Router();

router.post('/',function(request, response){
    if(request.session.key){
        response.json({"message":"You have been successfully logged out"});
        request.session.destroy();
    }else{
        response.json({"message":"You are not currently logged in"})
    }
})

module.exports = router;