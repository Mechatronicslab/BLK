package com.example.juli_soep.sekolah.helper.admin_akademik;

import android.provider.ContactsContract;

/**
 * Created by MSI on 4/2/2018.
 */

public class DataMapel {
    private String kdMapel,namaMapel;

    public DataMapel(){}
    public DataMapel(String kdMapel,String namaMapel){
        this.kdMapel=kdMapel;
        this.namaMapel=namaMapel;
    }

    public String getKdMapel() {
        return kdMapel;
    }

    public void setKdMapel(String kdMapel) {
        this.kdMapel = kdMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }
}
