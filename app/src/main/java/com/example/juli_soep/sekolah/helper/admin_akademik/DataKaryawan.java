package com.example.juli_soep.sekolah.helper.admin_akademik;

/**
 * Created by MSI on 3/23/2018.
 */

public class DataKaryawan {
    private String foto, nama, nik;

    public DataKaryawan(){}

    public DataKaryawan(String nama, String nik, String foto){

        this.nama = nama;
        this.nik = nik;
        this.foto = foto ;
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
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }


}
