package com.example.juli_soep.sekolah.siswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.home.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiswaActivity extends AppCompatActivity {
    String nohp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);
        nohp = getIntent().getExtras().getString("nohp");
        ButterKnife.bind(this);
        Toast.makeText(SiswaActivity.this,nohp,Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnProfilSiswa)
    void klik(){
        Intent i = new Intent(SiswaActivity.this , ProfileSiswa.class);
        i.putExtra("nohp",nohp);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SiswaActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
