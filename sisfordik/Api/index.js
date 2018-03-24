/**
 * Created by omgimbot on 2/3/2018.
 */
var express=require("express");
var bodyParser=require('body-parser');
var app = express();
var checktag = require('./controllers/checktag');
var authenticateController=require('./controllers/login-controller');
var registerController=require('./controllers/register-controller');
var getallController=require('./controllers/getalldata');
app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());
/* route to handle login and registration */
app.post('/api',checktag.checktag);
app.post('/api/register',registerController.register);
app.post('/api/login',authenticateController.login);
app.post('/api/getAll',getallController.getAll);
app.listen(90);