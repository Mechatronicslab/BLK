/**
 * Created by omgimbot on 2/10/2018.
 */
var connection = require('./../config');
var md5 = require('md5');
module.exports.checktag=function(req,res){
    var tag = req.body.tag;
    var today = new Date();
	var nohp=req.body.nohp;
	var id = req.body.id ;
    if (tag == "login"){
        var username=req.body.username;
        var password= md5(req.body.password);
        connection.query('SELECT * FROM user WHERE no_hp = ?',[username],
            function (error, results, fields){
                if (error) {
                    res.json({
                        status:true,
                        message:'there are some error with query'
                    })
                }else{
                    if(results.length >0){
                        if(password==results[0].password){
                            res.json({
                                status:false,
                                tag : results
                            })
                        }else{
                            res.json({
                                status:true,
                                message:"Email and password does not match"
                            });
                        }

                    }
                    else{
                        res.json({
                            status:true,
                            message:"Email does not exits"
                        });
                    }
                }

        })
    }

    else if (tag == "input_guru"){
            //siswa
            var data={
                "nama"                  :req.body.namas,
                "nuptk"                 :req.body.nuptk,
                "nik"                   :req.body.nik,
                "tmpt_lahir"            :req.body.tmpt_lahir,
                "tgl_lahir"             :req.body.tgl_lahir,
                "kelamin"               :req.body.kelamin,
                "pendidikan_terakhir"   :req.body.pendidikan_terakhir,
                "mulai_tugas"           :req.body.mulai_tugas,
                "jabatan"               :req.body.jabatan,
                "status"                :req.body.status,
                "sertifikasi"           :req.body.sertifikasi,
                "alamat"                :req.body.alamat
            }
            connection.query('INSERT INTO data_guru SET ?',data, function (error, results, fields) {
                if (error) {
                    res.json({
                        status:true,
                        message:'Pastikan Semua Data Sudah Terisi'
                    })
                }else{
                    res.json({
                        status:false,
                        message:'Input Data Berhasil'
                    })
                }
            });

        }

    else if(tag == "view_guru"){
        connection.query('SELECT * FROM data_guru where no_hp = ? ',[nohp],
		function (error, results, fields) {
            if (error) {
                res.json({
                    status:true,
                    message:'there are some error with query'
                })
            }else{
                if(results.length >0){

                    res.json({
                        status:false,
                        tag:results,
                        message:'successfully retrieve data'
                    })

                }
                else{
                    res.json({
                        status:true,
                        message:"failed to retrieve data"
                    });
                }
            }
        });
    }
	
	else if (tag == "regis_siswa_baru"){
            //siswa
            var data_siswa={
                "tingkat"                  		 :req.body.tingkat,
                "program"                 		 :req.body.program,
                "nama_lengkap"                   :req.body.nama_lengkap,
                "kelamin"            			 :req.body.kelamin,
                "nisn"             				 :req.body.nisn,
                "nis"               			 :req.body.nis,
                "no_ijazah"   					 :req.body.no_ijazah,
                "no_skhun"           			 :req.body.no_skhun,
                "no_un"               			 :req.body.no_un,
                "nik"                			 :req.body.nik,
                "npsn"           				 :req.body.npsn,
                "sekolah_asal"                	 :req.body.sekolah_asal,
                "tmpt_lahir"                	 :req.body.tmpt_lahir,
                "tgl_lahir"                		 :req.body.tgl_lahir,
                "agama"                			 :req.body.agama,
                "berkebutuhan_khusus"            :req.body.keb_khusus,
                "alamat"                		 :req.body.alamat,
                "dusun"                			 :req.body.dusun,
                "kelurahan"                	 	 :req.body.kelurahan,
                "kecamatan"                		 :req.body.kecamatan,
                "kabupaten"                		 :req.body.kabupaten,
                "provinsi"                		 :req.body.provinsi,
                "transportasi"                	 :req.body.transportasi,
                "jns_tinggal"                	 :req.body.jns_tinggal,
                "tlp_rumah"                		 :req.body.tlp_rmh,
                "email"                			 :req.body.email,
                "no_hp"                			 :req.body.no_hp,
                "no_kks"                		 :req.body.no_kks,
                "kps_phk"                		 :req.body.kps_phk,
                "usulan_layak_pip"               :req.body.usulan_layak_pip,
                "penerima_kip"                	 :req.body.penerima_kip,
                "no_kip"                	 	 :req.body.no_kip,
                "alasan_tolak_kip"               :req.body.alasan_tolak_pip,
                "no_reg_akte"               	 :req.body.no_reg_akte,
                "tinggi_badan"             	     :req.body.tinggi_bdn,
                "berat_badan"             	     :req.body.berat_bdn,
                "jarak_kesekolah"                :req.body.jarak_sekolah,
                "waktu_tempuh_kesekolah"         :req.body.wktu_tempuh_sekolah,
                "jml_saudara_kandung"            :req.body.jml_sodara_kandung,
                "jns_prestasi"                	 :req.body.jns_prestasi,
                "tingkat_perstasi"               :req.body.tingkat_prestasi,
                "nama_prestasi"               	 :req.body.nama_prestasi,
                "thn_prestasi"                	 :req.body.thn_prestasi,
                "penyelenggara"                	 :req.body.sumber_prestasi,
                "jns_beasiswa"                	 :req.body.jns_beasiswa,
                "sumber_beasiswa"                :req.body.sumber_beasiswa,
                "thn_mulai_beasiswa"             :req.body.thn_mulai_beasiswa,
                "thn_selesai_beasiswa"           :req.body.thn_selesai_beasiswa,
                "jns_kesejahteraan"              :req.body.jns_kesejahteraan,
                "no_kesejahteraan"               :req.body.no_kesejahteraan,
                "thn_mulai_kesejahteraan"        :req.body.thn_mulai_kesejahteraan,
                "thn_selesai_kesejahteraan"      :req.body.thn_selesai_kesejahteraan,
                "jurusan"                		 :req.body.jurusan_pilihan
            }
            connection.query('INSERT INTO identitas_siswa SET ?',data_siswa, function (error, results, fields) {
                if (error) {
                    res.json({
                        status:true,
						errors:error,
						
                        message:'Pastikan Semua Data Sudah Terisi'
                    })
                }else{
                    res.json({
                        status:false,
						result:results,
                        message:'Input Data Berhasil'
                    })
                }
            });

        }
		
		else if(tag == "view_siswa"){
        connection.query('SELECT * FROM identitas_siswa where no_hp = ? ',[nohp],
		function (error, results, fields) {
            if (error) {
                res.json({
                    status:true,
                    message:'there are some error with query'
                })
            }else{
                if(results.length >0){

                    res.json({
                        status:false,
                        tag:results,
                        message:'successfully retrieve data'
                    })

                }
                else{
                    res.json({
                        status:true,
                        message:"failed to retrieve data"
                    });
                }
            }
        });
    }
	
	else if (tag == "gaji_staff"){
            //siswa
            var gaji_staff={
                "nama"                  		 	:req.body.nama,
                "nik"                 		 		:req.body.nik,
                "golongan"                   		:req.body.golongan,
                "pend_terakhir"            			:req.body.pend_terakhir,
                "jabatan"             				:req.body.jabatan,
                "pengkerja"               			:req.body.pengkerja,
                "tunj_awal"   					 	:req.body.tunj_awal,
                "potongan_persenan"           		:req.body.potongan_persenan,
                "jml_potongan"               		:req.body.jml_potongan,
                "gaji_pokok"                		:req.body.gaji_pokok,
                "tunj_jabatan"           			:req.body.tunj_jabatan,
                "tunj_lain"                	 		:req.body.tunj_lain,
                "jml_penghasilan"                	:req.body.jml_penghasilan,
                "jml_pinjaman"                		:req.body.jml_pinjaman,
                "jml_potongan_staff"                :req.body.jml_potongan_staff,
                "sisa_pinjaman"            			:req.body.sisa_pinjaman,
                "jml_dibayar"                		:req.body.jml_dibayar,
                
            }
            connection.query('INSERT INTO gaji_staff SET ?',gaji_staff, function (error, results, fields) {
                if (error) {
                    res.json({
                        status:true,
						errors:error,
						
                        message:'Pastikan Semua Data Sudah Terisi'
                    })
                }else{
                    res.json({
                        status:false,
						result:results,
                        message:'Input Data Berhasil'
                    })
                }
            });

        }
		
		else if (tag == "gaji_satpam"){
            //siswa
            var gaji_satpam={
                "nama"                  		 	:req.body.nama,
                "gaji_pokok"                 		:req.body.gaji_pokok,
                "tunj_jabatan"                   	:req.body.tunj_jabatan,
                "pulsa"            					:req.body.pulsa,
                "thr"             					:req.body.thr,
                "ttl_gaji"               			:req.body.ttl_gaji,
                "p_suyono"   					 	:req.body.p_suyono,
                "koprasi"           				:req.body.koprasi,
                "jalur"               				:req.body.jalur,
                "dana_sosial"                		:req.body.dana_sosial,
                "ttl_potongan"           			:req.body.ttl_potongan,
                "gaji_diterima"                		:req.body.gaji_diterima,
                
                
            }
            connection.query('INSERT INTO gaji_satpam SET ?',gaji_satpam, function (error, results, fields) {
                if (error) {
                    res.json({
                        status:true,
						errors:error,
						
                        message:'Pastikan Semua Data Sudah Terisi'
                    })
                }else{
                    res.json({
                        status:false,
						result:results,
                        message:'Input Data Berhasil'
                    })
                }
            });

        }
		
		else if(tag == "view_gaji_satpam"){
        connection.query('SELECT * FROM gaji_satpam',
		function (error, results, fields) {
            if (error) {
                res.json({
                    status:true,
                    message:'there are some error with query'
                })
            }else{
                if(results.length >0){

                    res.json({
                        status:false,
                        tag:results,
                        message:'successfully retrieve data'
                    })

                }
                else{
                    res.json({
                        status:true,
						errors:error,
                        message:"failed to retrieve data"
                    });
                }
            }
        });
    }
    }


