/**
 * Created by omgimbot on 2/3/2018.
 */
var connection = require('./../config');
var md5 = require('md5');
module.exports.register=function(req,res){
	var tag = req.body.tag;
    var today = new Date();
	//siswa
	var users={
		"uuid":req.body.uuid,
		"nisn":req.body.nisn,
        "nama":req.body.nama,
        "email":req.body.email,
        "password":md5(req.body.password),
        "lvl":req.body.lvl,
        "alamat":req.body.alamat,
        "created_at":today,
        "updated_at":today
    }
    connection.query('INSERT INTO user_siswa SET ?',users, function (error, results, fields) {
        if (error) {
            res.json({
                status:false,
                message:'there are some error with query'
            })
        }else{
            res.json({
                status:true,
                data:results,
                message:'user registered sucessfully'
            })
        }
    });

}