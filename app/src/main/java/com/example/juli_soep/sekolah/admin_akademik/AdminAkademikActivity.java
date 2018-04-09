package com.example.juli_soep.sekolah.admin_akademik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.juli_soep.sekolah.admin_akademik.DataKaryawan.karyawan;
import com.example.juli_soep.sekolah.admin_akademik.DataMapel.Mapel;
import com.example.juli_soep.sekolah.home.MainActivity;
import com.example.juli_soep.sekolah.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.Session;

public class AdminAkademikActivity extends AppCompatActivity {
    private Session sesion;
    LinearLayout daftarguru, daftarstaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_akademik);
        ButterKnife.bind(this);
        sesion = new Session(getApplicationContext());
        daftarguru = (LinearLayout)findViewById(R.id.btnDaftarGuru);
        daftarguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminAkademikActivity.this, karyawan.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AdminAkademikActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnMapel)
    void btnMapel(){
        Intent i = new Intent(AdminAkademikActivity.this, Mapel.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnLogout)
    void logout(){
        sesion.setLogin(false);
        sesion.setSessid(0);
        Intent i = new Intent(AdminAkademikActivity.this , MainActivity.class);
        startActivity(i);
        finish();
    }
}
