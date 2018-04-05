package com.example.juli_soep.sekolah.helper.admin_keuangan;

/**
 * Created by Terminator on 05/04/2018.
 */

public class DataGajih {

    private String nik,nama, gjihBesih,tgl;

    public DataGajih(){}

    public DataGajih(String nik, String nama, String gajihBersih, String tgl){
        this.nama       = nama;
        this.nik        = nik;
        this.gjihBesih  = gajihBersih;
        this.tgl        = tgl;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGjihBesih() {
        return gjihBesih;
    }

    public void setGjihBesih(String gjihBesih) {
        this.gjihBesih = gjihBesih;
    }
}
