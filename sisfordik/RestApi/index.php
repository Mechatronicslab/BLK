<?php

/**
 * File to handle all API requests
 * Accepts GET and POST
 * 
 * Each request will be identified by TAG
 * Response will be JSON data

  /**
 * check for POST request 
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];

    // include db handler
    require_once 'include/DB_Function.php';
	$db = new DB_Functions();
    $AdminAkademik = new AdminAkademik();

    // response Array
    $response = array("tag" => $tag, "error" => FALSE);

    // check for tag type
    if ($tag == 'login') {
        // Request type is check Login
        $username 	= $_POST['username'];
        $password 	= md5($_POST['password']);
		//$imei 		= $_POST['imei'];

		$cek = $db->cekUser($username);
		
		if($cek !=false){
				// check for user
				$user = $db->getUserByUsernameAndPassword($username, $password);
				if ($user != false) {
					// user found
					$response["error"]  = FALSE;
					$response["username"]   = $user["username"];
					$response["lvl"]  		= $user["lvl"];
					$response["id"]     	= $user["id"];
					$response["no_hp"]     	= $user["no_hp"];
					echo json_encode($response);
				} else {
					// user not found
					// echo json with error = 1
					$response["error"]      = TRUE;
					$response["error_msg"]  = "Periksa kembali Password Anda";
					echo json_encode($response);
			}
		}else{
			$response["error"]      = TRUE;
			$response["error_msg"]  = "User ini Tidak Terdaftar";
			echo json_encode($response);
		}

    }else if($tag == 'regis_siswa_baru'){
        $tingkat        =$_POST['tingkat'];
        $program        =$_POST['program'];
        $nama_lengkap   =$_POST['nama_lengkap'];
        $kelamin        =$_POST['kelamin'];
        $nisn           =$_POST['nisn'];
        $nis            =$_POST['nis'];
        $no_ijazah      =$_POST['no_ijazah'];
        $no_skhun       =$_POST['no_ijazah'];
        $no_un          =$_POST['no_un'];
        $nik            =$_POST['nik'];
        $npsn           =$_POST['npsn'];
        $sekolah_asal   =$_POST['sekolah_asal'];
        $tmpt_lahir     =$_POST['tmpt_lahir'];
        $tgl_lahir      =$_POST['tgl_lahir'];
        $agama          =$_POST['agama'];
        $keb_khusus     =$_POST['keb_khusus'];
        $alamat         =$_POST['alamat'];
        $dusun          =$_POST['dusun'];
        $kelurahan      =$_POST['kelurahan'];
        $kecamatan      =$_POST['kecamatan'];
        $kabupaten      =$_POST['kabupaten'];
        $provinsi       =$_POST['provinsi'];
        $alat_transport =$_POST['transportasi'];
        $jns_tinggal    =$_POST['jns_tinggal'];
        $tlp_rmh        =$_POST['tlp_rmh'];
        $email          =$_POST['email'];
        $no_hp          =$_POST['no_hp'];
        $no_kks         =$_POST['no_kks'];
        $no_kps         =$_POST['kps_phk'];
        $usulan_pip     =$_POST['usulan_layak_pip'];
        $no_kip         =$_POST['penerima_kip'];
        $nama_kip       =$_POST['no_kip'];
        $alasan_tolak_kip=$_POST['alasan_tolak_pip'];
        $no_reg_akta    =$_POST['no_reg_akte'];
        $tinggi_bdn     =$_POST['tinggi_bdn'];
        $berat_bdn      =$_POST['berat_bdn'];
        $jarak_sekolah  =$_POST['jarak_sekolah'];
        $waktu_tempuh_sekolah=$_POST['wktu_tempuh_sekolah'];
        $jml_sodara     =$_POST['jml_sodara_kandung'];
        $jns_prestasi   =$_POST['jns_prestasi'];
        $tingkat_prestasi=$_POST['tingkat_prestasi'];
        $nama_prestasi  =$_POST['nama_prestasi'];
        $thn_dapat_prestasi=$_POST['thn_prestasi'];
        $sumber_prestasi=$_POST['sumber_prestasi'];
        $jns_beasiswa=$_POST['jns_beasiswa'];
        $sumber_beasiswa=$_POST['sumber_beasiswa'];
        $thn_mulai_beasiswa=$_POST['thn_mulai_beasiswa'];
        $tahun_selesai_beasiswa=$_POST['thn_selesai_beasiswa'];
        $jns_kesejahteraan=$_POST['jns_kesejahteraan'];
        $no_kesejahteraan=$_POST['no_kesejahteraan'];
        $thn_mulai_kesejahteraan=$_POST['thn_mulai_kesejahteraan'];
        $thn_selesai_kesejahteraan=$_POST['thn_selesai_kesejahteraan'];
        $jurusan_pilihan=$_POST['jurusan_pilihan'];

        $cekNisn = $db->cekNisn($nisn);
        if($cekNisn != false){
            $response["message"]     	= "Siswa Sudah Terdaftar";
            echo json_encode($response);
        }else{
            $insert = $db->RegisSiswaBaru($tingkat,$program,$nama_lengkap,$kelamin,$nisn,$nis,$no_ijazah,$no_skhun,$no_un,$nik,$npsn,$sekolah_asal,
                $tmpt_lahir,$tgl_lahir,$agama,$keb_khusus,$alamat,$dusun,$kelurahan,$kecamatan,$kabupaten,$provinsi,
                $alat_transport,$jns_tinggal,$tlp_rmh,$email,$no_hp,$no_kks,$no_kps,$usulan_pip,$no_kip,$nama_kip,
                $alasan_tolak_kip,$no_reg_akta,$tinggi_bdn,$berat_bdn,$jarak_sekolah,$waktu_tempuh_sekolah,
                $jml_sodara,$jns_prestasi,$tingkat_prestasi,$nama_prestasi,$thn_dapat_prestasi,$sumber_prestasi,
                $jns_beasiswa,$sumber_beasiswa,$thn_mulai_beasiswa,$tahun_selesai_beasiswa,$jns_kesejahteraan,
                $no_kesejahteraan,$thn_mulai_kesejahteraan,$thn_selesai_kesejahteraan,$jurusan_pilihan);
            if($insert != false){
                $response["error"]  = FALSE;
                $response["message"]     	= "Berhasil Registrasi";
                echo json_encode($response);
            }else{
                $response["error"]  = TRUE;
                $response["message"]     	= "Gagal";
                echo json_encode($response);
            }
        }

    }else if($tag == 'view_siswa'){
        $username 	= $_POST['username'];
        $cek = $db->cekUser($username);

        if($cek !=false){
            // check for user
            $user = $db->view_siswa($username);
            if ($user != false) {
                // user found
                $response["error"]  = FALSE;
				$response["nama"]     = $user["nama_lengkap"];
				$response["nisn"]     = $user["nisn"];
                $response["alamat"]     = $user["alamat"];
                $response["jurusan"]    = $user["jurusan"];
                $response["no_hp"]  	= $user["no_hp"];
                $response["email"]  	= $user["email"];
                $response["kelamin"]  	= $user["kelamin"];
                echo json_encode($response);
            } else {
                // user not found
                // echo json with error = 1
                $response["error"]      = TRUE;
                $response["error_msg"]  = "Terjadi Kesalahan";
                echo json_encode($response);
            }
        }else{
            $response["error"]      = TRUE;
            $response["error_msg"]  = "Data Tidak Ada";
            echo json_encode($response);
        }
	}

	else if($tag == 'gaji_staff') {
        $nama = $_POST['nama'];
        $nik = $_POST['nik'];
        $golongan = $_POST['golongan'];
        $pend_terakhir = $_POST['pend_terakhir'];
        $jabatan = $_POST['jabatan'];
        $pengkerja = $_POST['pengkerja'];
        $tunj_awal = $_POST['tunj_awal'];
        $potongan_persenan = $_POST['potongan_persenan'];
        $jml_potongan = $_POST['jml_potongan'];
        $gaji_pokok = $_POST['gaji_pokok'];
        $tunj_jabatan = $_POST['tunj_jabatan'];
        $tunj_lain = $_POST['tunj_lain'];
        $jml_penghasilan = $_POST['jml_penghasilan'];
        $jml_pinjaman= $_POST['jml_pinjaman'];
        $jml_potongan_staff = $_POST['jml_potongan_staff'];
        $sisa_pinjaman = $_POST['sisa_pinjaman'];
        $jml_dibayar = $_POST['jml_dibayar'];

        $cekNik = $db->cekNik($nik);
        if ($cekNik != false) {
            $response["message"] = "Data Ini Sudah Ada";
            echo json_encode($response);
        } else {
            $insert = $db->InputGajiStaff($nama,$nik,$golongan,$pend_terakhir,$jabatan,$pengkerja,$tunj_awal,$potongan_persenan,$jml_potongan,$gaji_pokok
            ,$tunj_jabatan,$tunj_lain,$jml_penghasilan,$jml_pinjaman,$jml_potongan_staff,$sisa_pinjaman,$jml_dibayar);
            if ($insert != false) {
                $response["status"] = FALSE;
                $response["message"] = "Data Berhasil Disimpan";
                echo json_encode($response);
            } else {
                $response["status"] = TRUE;
                $response["message"] = "Gagal";
                echo json_encode($response);
            }
        }
    } else if($tag == 'gaji_satpam') {
        $nama = $_POST['nama'];
        $jabatan = $_POST['jabatan'];
        $gaji_pokok = $_POST['gaji_pokok'];
        $tunj_jabatan = $_POST['tunj_jabatan'];
        $pulsa = $_POST['pulsa'];
        $thr = $_POST['thr'];
        $ttl_gaji = $_POST['ttl_gaji'];
        $p_suyono = $_POST['p_suyono'];
        $koprasi = $_POST['koprasi'];
        $jalur = $_POST['jalur'];
        $dana_sosial = $_POST['dana_sosial'];
        $ttl_potongan = $_POST['ttl_potongan'];
        $gaji_diterima = $_POST['gaji_diterima'];
       

        $cekNik = $db->cekNik($nik);
        if ($cekNik != false) {
            $response["message"] = "Data Ini Sudah Ada";
            echo json_encode($response);
        } else {
            $insert = $db->InputGajiStaff($nama,$jabatan,$gaji_pokok,$tunj_jabatan,$pulsa,$thr,$ttl_gaji,$p_suyono,$koprasi,$jalur
            ,$dana_sosial,$ttl_potongan,$gaji_diterima);
            if ($insert != false) {
                $response["status"] = FALSE;
                $response["message"] = "Data Berhasil Disimpan";
                echo json_encode($response);
            } else {
                $response["status"] = TRUE;
                $response["message"] = "Gagal";
                echo json_encode($response);
            }
        }
    }
	
	else if($tag == 'viewAll_gaji_staff'){
		 $db->viewAll_gaji_staff();
	}
    else if($tag == 'ShowKaryawan'){
        $AdminAkademik->ShowKaryawan();
    }
	




	else {
    // user failed to store
    $response["error"] = TRUE;
    $response["error_msg"] = "Unknow 'tag' value. It should be either 'login' or 'register'";
    echo json_encode($response);
    }
}//end

else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter 'tag' is missing!";
    echo json_encode($response);
}

?>
