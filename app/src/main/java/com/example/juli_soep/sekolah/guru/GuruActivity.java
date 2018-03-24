package com.example.juli_soep.sekolah.guru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.home.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuruActivity extends AppCompatActivity {
    String nohp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);
        ButterKnife.bind(this);
        nohp = getIntent().getExtras().getString("nohp");


    }
    @OnClick(R.id.btnProfilGuru)
    void klik(){
        Intent i = new Intent(GuruActivity.this , ProfileGuru.class);
        i.putExtra("nohp",nohp);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(GuruActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
