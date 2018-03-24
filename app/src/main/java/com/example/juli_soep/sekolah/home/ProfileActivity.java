package com.example.juli_soep.sekolah.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.juli_soep.sekolah.R;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout sejarah, prestasi, alamat, visi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sejarah = (LinearLayout)findViewById(R.id.btnSejarahSekolah);
        prestasi = (LinearLayout)findViewById(R.id.btnPrestasiSekolah);
        alamat = (LinearLayout)findViewById(R.id.btnAlamatSekolah);
        visi = (LinearLayout)findViewById(R.id.btnVisiMisi);

        sejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, SejarahSekolah.class);
                startActivity(i);
                finish();
            }
        });

        prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, PrestasiSekolah.class);
                startActivity(i);
                finish();
            }
        });

        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, AlamatSekolah.class);
                startActivity(i);
                finish();
            }
        });

        visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, VisiSekolah.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
