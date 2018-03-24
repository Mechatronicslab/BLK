/**
 * Created by omgimbot on 2/3/2018.
 */
var connection = require('./../config');
module.exports.getAll=function(req,res){
    var key=req.body.keynya;
    if(key == "getAll"){
    connection.query('SELECT * FROM users ', function (error, results, fields) {
        if (error) {
            res.json({
                status:false,
                message:'there are some error with query'
            })
        }else{
            if(results.length >0){

                    res.json({
                        status:true,
                        data : results,
                        message:'successfully retrieve data'
                    })

            }
            else{
                res.json({
                    status:false,
                    message:"failed to retrieve data"
                });
            }
        }
    });
    }else{
        res.json({
            status :false,
            message :"Please check Your Connection"
        })
    }
}