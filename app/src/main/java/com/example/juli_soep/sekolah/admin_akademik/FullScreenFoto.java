package com.example.juli_soep.sekolah.admin_akademik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.juli_soep.sekolah.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import volley.Config;

public class FullScreenFoto extends AppCompatActivity {
    String foto ,Nik;
    @BindView(R.id.foto)
    ImageView imgFoto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_foto);
        ButterKnife.bind(this);
        foto = getIntent().getExtras().getString("foto");
        Nik = getIntent().getExtras().getString("nik");
        String urlfoto = Config.URL+"foto/";
        String fotoNya = urlfoto+foto;

        if(foto.length()>1){
            Picasso.with(FullScreenFoto.this).load(fotoNya).into(imgFoto);
        }else{
            Picasso.with(FullScreenFoto.this).load(R.drawable.profi).into(imgFoto);
        }
    }
    public void onBackPressed() {
        Intent i = new Intent(FullScreenFoto.this, EditKaryawan.class);
        i.putExtra("nik",Nik);
        startActivity(i);
        finish();
    }
}
