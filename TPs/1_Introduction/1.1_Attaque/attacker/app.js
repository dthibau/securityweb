var express = require('express');
var app = express();
var stringReplace = require('string-replace-middleware');

var VICTIME_URL = process.env.VICTIME_URL || "http://localhost:8080";

app.use(stringReplace({
   'VICTIME_URL': VICTIME_URL
}));
app.use(express.static('.'))

app.get('/', function(req, res) {
    res.render('index.html');
});

app.get('/client.js', function(req, res) {
    res.render('client.js');
});

app.listen(8000, function () {
  console.log('Started at port 8000');
});
