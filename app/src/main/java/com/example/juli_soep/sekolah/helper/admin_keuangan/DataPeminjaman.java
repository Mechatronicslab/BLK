package com.example.juli_soep.sekolah.helper.admin_keuangan;

/**
 * Created by Terminator on 04/04/2018.
 */

public class DataPeminjaman {

    private String nik,nama,pinjaman;

    public DataPeminjaman(){}

    public DataPeminjaman(String nik, String nama, String pinjaman){
        this.nama       = nama;
        this.nik        = nik;
        this.pinjaman   = pinjaman;
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

    public String getPinjaman() {
        return pinjaman;
    }

    public void setPinjaman(String pinjaman) {
        this.pinjaman = pinjaman;
    }
}
