package com.example.juli_soep.sekolah.helper;

/**
 * Created by Terminator on 04/06/2017.
 */

public class NewsDataAdmin {

    private String id, nama, jabatan;

    public NewsDataAdmin(){}

    public NewsDataAdmin(String id, String nama, String jabatan){
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {this.jabatan = jabatan;}

}
