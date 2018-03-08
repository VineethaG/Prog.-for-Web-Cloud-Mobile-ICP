var R = require("request");
var key = "Ked0sPpkLwr4OUg2vJpxDEVWy";
var secret = "syLUqtssCZ8cAJxBRYmKLFyXXbCCoJ7ieamqevfRr49xPmsj5u";
var cat = key +":"+secret;
var credentials = new Buffer(cat).toString('base64');

var url = 'https://api.twitter.com/oauth2/token';
R({ url: url,
    method:'POST',
    headers: {
        "Authorization": "Basic "+ credentials,
        "Content-Type":"application/x-www-form-urlencoded;charset=UTF-8"
    },
    body: "grant_type=client_credentials"

}, function(err, resp, body) {
    console.dir(body); //the bearer token...
});

