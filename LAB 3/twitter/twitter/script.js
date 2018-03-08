var R = require("request");
var x = 'VineethaGummadi'
var url = 'https://api.twitter.com/1.1/friends/list.json?cursor=-1&screen_name='+x;
console.log(url);
R({ url: url,
    method:'GET',
    headers: {
        "Authorization": "Bearer AAAAAAAAAAAAAAAAAAAAACT94gAAAAAA0LKd4PbcKFUcqiCDjpSsrTY%2BrCU%3DkAF9tjOBQmerwgs2umcjWMWPXVZ9fMwlegahRKt548fXjEQqv1"
    }
}, function(err, resp, body) {
    console.log(body); //the bearer token...

});

