package com.example.juli_soep.sekolah.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juli_soep.sekolah.R;

public class AlamatSekolah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_sekolah);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AlamatSekolah.this, ProfileActivity.class);
        startActivity(i);
        finish();
    }
}
