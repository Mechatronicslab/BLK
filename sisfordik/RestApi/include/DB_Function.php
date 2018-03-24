<?php

class DB_Functions {

    private $conn;
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // koneksi ke database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {

    }
   
    public function getUserByUsernameAndPassword($username, $password) {
 
        $stmt = $this->conn->prepare("SELECT * FROM user WHERE no_hp = '$username' and password='$password'");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }

    public function RegisSiswaBaru($tingkat,$program,$nama_lengkap,$kelamin,$nisn,$nis,$no_ijazah,$no_skhun,$no_un,$nik,$npsn,$sekolah_asal,
                                    $tmpt_lahir,$tgl_lahir,$agama,$keb_khusus,$alamat,$dusun,$kelurahan,$kecamatan,$kabupaten,$provinsi,
                                    $alat_transport,$jns_tinggal,$tlp_rmh,$email,$no_hp,$no_kks,$no_kps,$usulan_pip,$no_kip,$nama_kip,
                                    $alasan_tolak_kip,$no_reg_akta,$tinggi_bdn,$berat_bdn,$jarak_sekolah,$waktu_tempuh_sekolah,
                                    $jml_sodara,$jns_prestasi,$tingkat_prestasi,$nama_prestasi,$thn_dapat_prestasi,$sumber_prestasi,
                                    $jns_beasiswa,$sumber_beasiswa,$thn_mulai_beasiswa,$tahun_selesai_beasiswa,$jns_kesejahteraan,
                                    $no_kesejahteraan,$thn_mulai_kesejahteraan,$thn_selesai_kesejahteraan,$jurusan_pilihan) {

 

        $stmt = $this->conn->prepare("INSERT INTO identitas_siswa(tingkat, program, nama_lengkap, kelamin,nisn,nis,no_ijazah,no_skhun,no_un,nik,npsn,sekolah_asal,tmpt_lahir,tgl_lahir,
                agama,berkebutuhan_khusus,alamat,dusun,kelurahan,kecamatan,kabupaten,provinsi,transportasi,jns_tinggal
                ,tlp_rumah,email,no_hp,no_kks,kps_phk,usulan_layak_pip,penerima_kip,no_kip,alasan_tolak_kip,no_reg_akte
                ,tinggi_badan,berat_badan,jarak_kesekolah,waktu_tempuh_kesekolah,jml_saudara_kandung,jns_prestasi
                ,tingkat_perstasi,nama_prestasi,thn_prestasi,penyelenggara,jns_beasiswa,sumber_beasiswa
                ,thn_mulai_beasiswa,thn_selesai_beasiswa,jns_kesejahteraan,no_kesejahteraan
                ,thn_mulai_kesejahteraan,thn_selesai_kesejahteraan,jurusan) 
                VALUES 
                ('$tingkat','$program','$nama_lengkap','$kelamin','$nisn','$nis','$no_ijazah','$no_skhun','$no_un','$nik','$npsn','$sekolah_asal',
                '$tmpt_lahir','$tgl_lahir','$agama','$keb_khusus','$alamat','$dusun','$kelurahan','$kecamatan','$kabupaten','$provinsi',
                '$alat_transport','$jns_tinggal','$tlp_rmh','$email','$no_hp','$no_kks','$no_kps','$usulan_pip','$no_kip','$nama_kip',
                '$alasan_tolak_kip','$no_reg_akta','$tinggi_bdn','$berat_bdn','$jarak_sekolah','$waktu_tempuh_sekolah',
                '$jml_sodara','$jns_prestasi','$tingkat_prestasi','$nama_prestasi','$thn_dapat_prestasi','$sumber_prestasi',
                '$jns_beasiswa','$sumber_beasiswa','$thn_mulai_beasiswa','$tahun_selesai_beasiswa','$jns_kesejahteraan',
                '$no_kesejahteraan','$thn_mulai_kesejahteraan','$thn_selesai_kesejahteraan','$jurusan_pilihan')");
        if ($stmt->execute()) {
            //$user = $stmt->get_result()->fetch_assoc();
            //$stmt->close();
            return $stmt;
        } else {
            return NULL;
        }
    }
	
	public function view_siswa($username) {

        $stmt = $this->conn->prepare("SELECT * FROM identitas_siswa where no_hp= '$username'");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }

    public function InputGajiStaff($nama,$nik,$golongan,$pend_terakhir,$jabatan,$pengkerja,$tunj_awal,$potongan_persenan,$jml_potongan,$gaji_pokok
        ,$tunj_jabatan,$tunj_lain,$jml_penghasilan,$jml_pinjaman,$jml_potongan_staff,$sisa_pinjaman,$jml_dibayar) {
        $stmt = $this->conn->prepare("INSERT INTO gaji_staff(nama,nik,golongan,pend_terakhir,jabatan,pengkerja,tunj_awal,potongan_persenan,jml_potongan,gaji_pokok
            ,tunj_jabatan,tunj_lain,jml_penghasilan,jml_pinjaman,jml_potongan_staff,sisa_pinjaman,jml_dibayar) VALUES ('$nama','$nik','$golongan','$pend_terakhir','$jabatan','$pengkerja','$tunj_awal','$potongan_persenan','$jml_potongan','$gaji_pokok'
            ,'$tunj_jabatan','$tunj_lain','$jml_penghasilan','$jml_pinjaman','$jml_potongan_staff','$sisa_pinjaman','$jml_dibayar')");
        if ($stmt->execute()) {
            //$user = $stmt->get_result()->fetch_assoc();
            //$stmt->close();
            //$stmt->close();
            return $stmt;
        } else {
            return NULL;
        }
    }
	
	public function viewAll_gaji_staff(){
        $mysqli = new mysqli("localhost", "root", "23213343", "sisfordik");

			/* check connection */
			if (mysqli_connect_errno()) {
				printf("Connect failed: %s\n", mysqli_connect_error());
				exit();
			}

			$query = "SELECT nama,nik FROM gaji_staff";
			$result = $mysqli->query($query);
			$myArray   = array();
			$tempArray = array();
			while($row = $result->fetch_object())
			{
				$tempArray = $row;
				array_push($myArray, $tempArray);
			}
			
			echo json_encode(array('result'=>$myArray));
			
			/* free result set */
			$result->close();

			/* close connection */
			$mysqli->close();
    }

	
	public function InputGajiSatpam($nama,$nik,$golongan,$pend_terakhir,$jabatan,$pengkerja,$tunj_awal,$potongan_persenan,$jml_potongan,$gaji_pokok
        ,$tunj_jabatan,$tunj_lain,$jml_penghasilan,$jml_pinjaman,$jml_potongan_staff,$sisa_pinjaman,$jml_dibayar) {
        $stmt = $this->conn->prepare("INSERT INTO gaji_staff(nama,nik,golongan,pend_terakhir,jabatan,pengkerja,tunj_awal,potongan_persenan,jml_potongan,gaji_pokok
            ,tunj_jabatan,tunj_lain,jml_penghasilan,jml_pinjaman,jml_potongan_staff,sisa_pinjaman,jml_dibayar) VALUES ('$nama','$nik','$golongan','$pend_terakhir','$jabatan','$pengkerja','$tunj_awal','$potongan_persenan','$jml_potongan','$gaji_pokok'
            ,'$tunj_jabatan','$tunj_lain','$jml_penghasilan','$jml_pinjaman','$jml_potongan_staff','$sisa_pinjaman','$jml_dibayar')");
        if ($stmt->execute()) {
            //$user = $stmt->get_result()->fetch_assoc();
            //$stmt->close();
            return $stmt;
        } else {
            return NULL;
        }
    }
	
	


    public function cekNik($nik) {
        $stmt = $this->conn->prepare("SELECT * FROM gaji_staff WHERE nik = '$nik'");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }
	
	public function updateImei($imei,$username) {
 
        $stmt = $this->conn->prepare("UPDATE `groupuser` SET `imei_id`='$imei' WHERE username='$username'");
        if ($stmt->execute()) {
            //$user = $stmt->get_result()->fetch_assoc();
            //$stmt->close();
            return $stmt;
        } else {
            return NULL;
        }
    }
	
	public function cekNisn($nisn) {
        $stmt = $this->conn->prepare("SELECT * FROM identitas_siswa WHERE nisn = '$nisn'");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }
	
	public function cekUser($username) {
 
        $stmt = $this->conn->prepare("SELECT * FROM user WHERE no_hp = '$username'");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }
}

class AdminAkademik
{

    private $conn;

    // constructor
    function __construct()
    {
        require_once 'DB_Connect.php';
        // koneksi ke database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct()
    {

    }

    public function ShowKaryawan(){
        $mysqli = new mysqli("localhost", "root", "", "juli_sisfordik");

        /* check connection */
        if (mysqli_connect_errno()) {
            printf("Connect failed: %s\n", mysqli_connect_error());
            exit();
        }

        $query = "SELECT nama,nik FROM m_karyawan";
        $result = $mysqli->query($query);
        $myArray   = array();
        while($row = $result->fetch_object())
        {
            $tempArray = $row;
            array_push($myArray, $tempArray);
        }

        echo json_encode(array('result'=>$myArray));

        /* free result set */
        $result->close();

        /* close connection */
        $mysqli->close();
    }


}
 
?>