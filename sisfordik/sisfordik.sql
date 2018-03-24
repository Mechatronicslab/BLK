-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2018 at 06:49 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sisfordik`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_guru`
--

CREATE TABLE `data_guru` (
  `id` int(20) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `nuptk` varchar(255) NOT NULL,
  `nik` varchar(255) NOT NULL,
  `no_hp` varchar(255) NOT NULL,
  `tmpt_lahir` varchar(255) NOT NULL,
  `tgl_lahir` varchar(255) NOT NULL,
  `kelamin` varchar(255) NOT NULL,
  `pendidikan_terakhir` varchar(255) NOT NULL,
  `mulai_tugas` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `sertifikasi` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_guru`
--

INSERT INTO `data_guru` (`id`, `nama`, `nuptk`, `nik`, `no_hp`, `tmpt_lahir`, `tgl_lahir`, `kelamin`, `pendidikan_terakhir`, `mulai_tugas`, `jabatan`, `status`, `sertifikasi`, `alamat`) VALUES
(15, 'Jalaludin', '365849879856', '011180012133', '0813', 'Bandar Lampung', '1970-02-05', 'Laki-Laki', 'SMA', '020594', 'Guru', 'Sudah Menikah', 'Sertifikasi', 'JLN.Sultan Agung No 12'),
(16, 'Sumiarto', '02366559878', '3022665541883', '', 'Bandar Lampung', '1960-09-09', 'Laki-Laki', 'SMA', '020594', 'Kepala Sekolah', 'Sudah Menikah', 'sertifikasi', 'JLN.Krakatau Raya'),
(17, 'Suminah', '02255445696', '11800201133', '', 'Bandung', '1980-08-08', 'Perempuan', 'SMA', '020594', 'Staff', 'Belum Menikah', 'sertifikasi', 'jln.pulau belitung , sukarame'),
(18, 'Sunaryo', '0336659669', '1166555448799965', '', 'Jakarta', '1960-07-07', 'Laki-Laki', 'SMA', '99388', 'Karyawan', 'Belum Menikah', 'sertifikasi', 'Jln. Za Pagar Alam No.12'),
(19, 'Sumanto', '009002999', '0018881118', '', 'Bandar Lampung', '0000-00-00', 'Pria', 'SMA', '2017', 'kepala sekolah', 'sudah menikah', 'Sudah', 'pagar alaama'),
(20, 'sugiarto', '09990209', '01119288', '', 'bandar lampung', '12-02-2018', 'Pria', 'SMA', '2017', 'kepala sekolah', 'sudah menikah', 'Sudah', 'pagar alam'),
(21, 'adi permana', '1273483', '3247832749832', '', 'Bandar Lampung', '2018-02-12', 'Pria', 'SMA', '2019', 'jabatan', 'status', 'Sudah', 'lampung'),
(22, '11', '111', '111', '', '111', '111', 'Pria', '111', '1111', '111', '111', 'Sudah Sertifikasi', '111'),
(23, 'nama gurunya', 'nuptknya', '08278827762', '', 'tmpt lahirnya', '2018-02-10 ', 'pria', 'pend terakhir', '2018-02-10 ', 'jabatannya', 'statusnya', 'sertifikasinya', 'alamatnya'),
(36, 'nama gurunyas', 'nuptknya', '082788277621', '', 'tmpt lahirnya', '2018-02-10 ', 'pria', 'pend terakhir', '2018-02-10 ', 'jabatannya', 'statusnya', 'sertifikasinya', 'alamatnya'),
(39, 'nama gurunyas', 'nuptknya', '0827882776211', '', 'tmpt lahirnya', '2018-02-10 ', 'pria', 'pend terakhir', '2018-02-10 ', 'jabatannya', 'statusnya', 'sertifikasinya', 'alamatnya');

-- --------------------------------------------------------

--
-- Table structure for table `gaji_satpam`
--

CREATE TABLE `gaji_satpam` (
  `id` int(11) NOT NULL,
  `nama` varchar(11) NOT NULL,
  `gaji_pokok` decimal(11,0) NOT NULL,
  `tunj_jabatan` int(11) NOT NULL,
  `pulsa` int(11) NOT NULL,
  `thr` int(11) NOT NULL,
  `ttl_gaji` int(11) NOT NULL,
  `p_suyono` int(11) NOT NULL,
  `koprasi` int(11) NOT NULL,
  `jalur` int(11) NOT NULL,
  `dana_sosial` int(11) NOT NULL,
  `ttl_potongan` int(11) NOT NULL,
  `gaji_diterima` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaji_satpam`
--

INSERT INTO `gaji_satpam` (`id`, `nama`, `gaji_pokok`, `tunj_jabatan`, `pulsa`, `thr`, `ttl_gaji`, `p_suyono`, `koprasi`, `jalur`, `dana_sosial`, `ttl_potongan`, `gaji_diterima`) VALUES
(1, 'adipermana', '8000', 8000, 9000, 80000, 900, 10000, 12000, 130000, 140000, 150000, 2500000),
(2, 'atox', '3333', 63333, 7777, 8888, 333, 3333, 333, 333, 2255, 5555, 2222),
(3, 'dwii', '987654321', 6666, 8888, 888, 33333, 33333, 665555, 88888, 44444, 8888, 5555),
(4, 'andika', '987654321', 333, 8888, 888, 3333, 333, 444, 4444, 444, 444, 444),
(5, 'ayu', '99999999999', 3333, 777, 888, 3333, 222, 222, 2222, 222, 222, 2222),
(6, 'putri', '9008645', 3333, 8888, 777, 333, 3333, 3333, 666, 555, 5555, 555),
(7, 'toyib', '9876543210', 6666, 777, 777, 666, 5555, 555, 555, 555, 555, 555),
(8, 'joko', '98765432166', 666, 777, 777, 333, 333, 6666, 6666, 6666, 666, 666),
(9, 'duku', '98765432100', 666, 777, 777, 3333, 555, 555, 555, 555, 555, 555);

-- --------------------------------------------------------

--
-- Table structure for table `gaji_staff`
--

CREATE TABLE `gaji_staff` (
  `id` int(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `nik` varchar(255) NOT NULL,
  `golongan` varchar(255) NOT NULL,
  `pend_terakhir` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `pengkerja` varchar(255) NOT NULL,
  `tunj_awal` int(255) NOT NULL,
  `potongan_persenan` varchar(255) NOT NULL,
  `jml_potongan` int(255) NOT NULL,
  `gaji_pokok` int(255) NOT NULL,
  `tunj_jabatan` int(255) NOT NULL,
  `tunj_lain` int(255) NOT NULL,
  `jml_penghasilan` int(255) NOT NULL,
  `jml_pinjaman` int(255) NOT NULL,
  `jml_potongan_staff` int(255) NOT NULL,
  `sisa_pinjaman` int(255) NOT NULL,
  `jml_dibayar` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaji_staff`
--

INSERT INTO `gaji_staff` (`id`, `nama`, `nik`, `golongan`, `pend_terakhir`, `jabatan`, `pengkerja`, `tunj_awal`, `potongan_persenan`, `jml_potongan`, `gaji_pokok`, `tunj_jabatan`, `tunj_lain`, `jml_penghasilan`, `jml_pinjaman`, `jml_potongan_staff`, `sisa_pinjaman`, `jml_dibayar`) VALUES
(1, 'susanto', '90909', 'golongan', 'SMA', 'direktur', '90', 1000, '10', 2000, 3000, 3000, 9000, 100000, 90000, 90000, 20000, 1000000),
(2, 'adi', '235', '12', 'trkhr', 'firkur', '36', 13999, '10 %', 1000, 99000, 90000, 88888, 777777, 77777, 6666666, 77777, 66666),
(3, 'susanto', '90909', 'golongan', 'SMA', 'direktur', '90', 1000, '10', 2000, 3000, 3000, 9000, 100000, 90000, 90000, 20000, 1000000);

-- --------------------------------------------------------

--
-- Table structure for table `identitas_ortu`
--

CREATE TABLE `identitas_ortu` (
  `id` int(100) NOT NULL,
  `nisn_ortu` varchar(100) NOT NULL,
  `nama_ayah` varchar(255) NOT NULL,
  `thn_lahir_ayah` varchar(255) NOT NULL,
  `nik_ayah` varchar(255) NOT NULL,
  `keb_khusus_ayah` varchar(255) NOT NULL,
  `pekerjaan_ayah` varchar(255) NOT NULL,
  `pend_ayah` varchar(255) NOT NULL,
  `penghasilan_ayah` varchar(255) NOT NULL,
  `nama_ibu` varchar(255) NOT NULL,
  `thn_lahir_ibu` varchar(255) NOT NULL,
  `nik_ibu` varchar(255) NOT NULL,
  `keb_khusus_ibu` varchar(255) NOT NULL,
  `pekerjaan` varchar(255) NOT NULL,
  `pend_ibu` varchar(255) NOT NULL,
  `penghasilan_ibu` varchar(255) NOT NULL,
  `nama_wali` varchar(255) NOT NULL,
  `nik_wali` varchar(255) NOT NULL,
  `pekerjaan_wali` varchar(255) NOT NULL,
  `pend_wali` varchar(255) NOT NULL,
  `penghasilan_wali` varchar(255) NOT NULL,
  `thn_lahir_wali` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `identitas_siswa`
--

CREATE TABLE `identitas_siswa` (
  `id` int(22) NOT NULL,
  `tingkat` varchar(255) NOT NULL,
  `program` varchar(255) NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `kelamin` varchar(255) NOT NULL,
  `nisn` varchar(255) NOT NULL,
  `nis` varchar(255) NOT NULL,
  `no_ijazah` varchar(255) NOT NULL,
  `no_skhun` varchar(255) NOT NULL,
  `no_un` varchar(255) NOT NULL,
  `nik` varchar(255) NOT NULL,
  `npsn` varchar(255) NOT NULL,
  `sekolah_asal` varchar(255) NOT NULL,
  `tmpt_lahir` varchar(255) NOT NULL,
  `tgl_lahir` varchar(255) NOT NULL,
  `agama` varchar(255) NOT NULL,
  `berkebutuhan_khusus` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) NOT NULL,
  `dusun` varchar(255) NOT NULL,
  `kelurahan` varchar(255) NOT NULL,
  `kecamatan` varchar(255) NOT NULL,
  `kabupaten` varchar(255) NOT NULL,
  `provinsi` varchar(255) NOT NULL,
  `transportasi` varchar(255) DEFAULT NULL,
  `jns_tinggal` varchar(255) NOT NULL,
  `tlp_rumah` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `no_hp` varchar(255) NOT NULL,
  `no_kks` varchar(255) NOT NULL,
  `kps_phk` varchar(255) NOT NULL,
  `usulan_layak_pip` varchar(255) NOT NULL,
  `penerima_kip` varchar(255) NOT NULL,
  `no_kip` varchar(255) NOT NULL,
  `alasan_tolak_kip` varchar(255) NOT NULL,
  `no_reg_akte` varchar(255) NOT NULL,
  `tinggi_badan` int(10) NOT NULL,
  `berat_badan` int(10) NOT NULL,
  `jarak_kesekolah` int(10) NOT NULL,
  `waktu_tempuh_kesekolah` int(10) NOT NULL,
  `jml_saudara_kandung` int(5) NOT NULL,
  `jns_prestasi` varchar(255) NOT NULL,
  `tingkat_perstasi` varchar(255) NOT NULL,
  `nama_prestasi` varchar(255) NOT NULL,
  `thn_prestasi` varchar(255) NOT NULL,
  `penyelenggara` varchar(255) NOT NULL,
  `jns_beasiswa` varchar(255) NOT NULL,
  `sumber_beasiswa` varchar(255) NOT NULL,
  `thn_mulai_beasiswa` varchar(255) NOT NULL,
  `thn_selesai_beasiswa` varchar(255) NOT NULL,
  `jns_kesejahteraan` varchar(255) NOT NULL,
  `no_kesejahteraan` varchar(255) NOT NULL,
  `thn_mulai_kesejahteraan` varchar(255) NOT NULL,
  `thn_selesai_kesejahteraan` varchar(255) NOT NULL,
  `jurusan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `identitas_siswa`
--

INSERT INTO `identitas_siswa` (`id`, `tingkat`, `program`, `nama_lengkap`, `kelamin`, `nisn`, `nis`, `no_ijazah`, `no_skhun`, `no_un`, `nik`, `npsn`, `sekolah_asal`, `tmpt_lahir`, `tgl_lahir`, `agama`, `berkebutuhan_khusus`, `alamat`, `dusun`, `kelurahan`, `kecamatan`, `kabupaten`, `provinsi`, `transportasi`, `jns_tinggal`, `tlp_rumah`, `email`, `no_hp`, `no_kks`, `kps_phk`, `usulan_layak_pip`, `penerima_kip`, `no_kip`, `alasan_tolak_kip`, `no_reg_akte`, `tinggi_badan`, `berat_badan`, `jarak_kesekolah`, `waktu_tempuh_kesekolah`, `jml_saudara_kandung`, `jns_prestasi`, `tingkat_perstasi`, `nama_prestasi`, `thn_prestasi`, `penyelenggara`, `jns_beasiswa`, `sumber_beasiswa`, `thn_mulai_beasiswa`, `thn_selesai_beasiswa`, `jns_kesejahteraan`, `no_kesejahteraan`, `thn_mulai_kesejahteraan`, `thn_selesai_kesejahteraan`, `jurusan`) VALUES
(1, '0721', 'admin', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1, 1, 1, 1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'),
(2, '11', '111', '44', 'Pria', '44', '44', '4', '44', '44', '44', '4', '44', '44', '4', 'Islam', '444', '44', '444', '444', '444', '44', '44', '44', '44', '444', '444', '444', '444', '444', '4', '44', '4', '44', '4', 4, 44, 444, 444, 444, '4444', '44', '444', '44', '444', '444', '444', '444', '444', '44', '44', '44', '444', 'Teknik Distribusi Tenaga Listrik'),
(7, '0721', 'admin', '1', '1', '12', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1, 1, 1, 1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', ''),
(8, '0721', 'admin', '1', '1', '122', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1, 1, 1, 1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', ''),
(10, '0721', 'admin', '1', '1', '1222', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1, 1, 1, 1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', ''),
(11, 'hha', 'hd hd', 'hak a', 'Pria', '87', '87', '7', '67', '77', '667', '767', 'h SH hp', 'hah s', '2018-02-19', 'Islam', 'hu', 'hh', 'h ah', 'hu', 'jj', 'jj', 'hhh', 'hjj', 'hh', '88', 'hh', '0812', '66', '88', 'hh', 'hhh', '88', 'hh', '88', 88, 88, 88, 88, 88, 'gg', 'hh', 'hh', '08', 'jja', 'hh', 'h SH', 'jj', 'hh', 'hu', '88', '00', 'jj', 'Teknik Otomotif Sepeda Motor');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(20) NOT NULL,
  `no_hp` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `lvl` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `no_hp`, `username`, `password`, `lvl`) VALUES
(1, '0813', 'guru', 'e10adc3949ba59abbe56e057f20f883e', 'guru'),
(2, '0812', 'siswa', 'e10adc3949ba59abbe56e057f20f883e', 'siswa'),
(4, '0721', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin'),
(5, '0811', 'Badan Administrasi Keuangan', 'e10adc3949ba59abbe56e057f20f883e', 'bak');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_guru`
--
ALTER TABLE `data_guru`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nik` (`nik`);

--
-- Indexes for table `gaji_satpam`
--
ALTER TABLE `gaji_satpam`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gaji_staff`
--
ALTER TABLE `gaji_staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `identitas_ortu`
--
ALTER TABLE `identitas_ortu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `identitas_siswa`
--
ALTER TABLE `identitas_siswa`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nisn` (`nisn`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_guru`
--
ALTER TABLE `data_guru`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `gaji_satpam`
--
ALTER TABLE `gaji_satpam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `gaji_staff`
--
ALTER TABLE `gaji_staff`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `identitas_ortu`
--
ALTER TABLE `identitas_ortu`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `identitas_siswa`
--
ALTER TABLE `identitas_siswa`
  MODIFY `id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
