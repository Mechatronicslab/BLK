package com.example.juli_soep.sekolah.helper.admin_akademik;

/**
 * Created by MSI on 3/23/2018.
 */

public class DataKaryawan {
    private String foto ,nama ,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat;

    public DataKaryawan(){}

    public DataKaryawan(String foto ,String nama ,String nik ,
                       String jabatan,String status,String sertifikasi){

        this.foto = foto ;
        this.nama = nama;
        this.nik = nik;
        this.nuptk = nuptk;
        this.tmptLahir = tmptLahir;
        this.tglLahir = tglLahir;
        this.kelamin = kelamin;
        this.pendTerakhir = pendTerakhir;
        this.tmt = tmt;
        this.jabatan = jabatan;
        this.status = status;
        this.sertifikasi = sertifikasi;
        this.alamat = alamat;

    }
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNuptk() {
        return nuptk;
    }

    public void setNuptk(String nuptk) {
        this.nuptk = nuptk;
    }

    public String getTmptLahir() {
        return tmptLahir;
    }

    public void setTmptLahir(String tmptLahir) {
        this.tmptLahir = tmptLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getPendTerakhir() {
        return pendTerakhir;
    }

    public void setPendTerakhir(String pendTerakhir) {
        this.pendTerakhir = pendTerakhir;
    }

    public String getTmt() {
        return tmt;
    }

    public void setTmt(String tmt) {
        this.tmt = tmt;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSertifikasi() {
        return sertifikasi;
    }

    public void setSertifikasi(String sertifikasi) {
        this.sertifikasi = sertifikasi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }




}
