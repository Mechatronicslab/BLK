package com.example.juli_soep.sekolah.admin_keuangan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.admin_akademik.DataKaryawan.AdminAkademikActivity;
import com.example.juli_soep.sekolah.home.MainActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.Session;

public class AppsKeuangan extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    private Session sesion;
    @BindView(R.id.slider)
    SliderLayout sliderLayout;

    HashMap<String,Integer> Hash_file_maps ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_keuangan);
        ButterKnife.bind(this);
        sesion = new Session(getApplicationContext());
        imageSlider();
    }


    @OnClick(R.id.btnInputdataGaji)
    void klikInputGaji(){

        Intent a = new Intent(AppsKeuangan.this, InputGajih.class);
        startActivity(a);
        finish();

    }

    @OnClick(R.id.btnSttingPinjaman)
    void klikSetPinjaman(){
        Intent a = new Intent(AppsKeuangan.this, SetPinjaman.class);
        startActivity(a);
        finish();
    }

    @OnClick(R.id.btnDataGajih)
    void klikListGajih(){
        Intent a = new Intent(AppsKeuangan.this, ListDataGajih.class);
        startActivity(a);
        finish();
    }

    public void imageSlider(){
        Hash_file_maps = new HashMap<String, Integer>();

        Hash_file_maps.put("",R.drawable.slide1);
        Hash_file_maps.put(" ",R.drawable.slide2);
        Hash_file_maps.put("  ",R.drawable.slide3);
        Hash_file_maps.put("   ",R.drawable.slide4);

        for(String name : Hash_file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(AppsKeuangan.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(AppsKeuangan.this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(6000);
        sliderLayout.addOnPageChangeListener(AppsKeuangan.this);
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(AppsKeuangan.this, MainActivity.class);
        startActivity(a);
        finish();
    }

    @OnClick(R.id.btnLogout)
    void logout(){
        sesion.setLogin(false);
        sesion.setSessid(0);
        Intent i = new Intent(AppsKeuangan.this , MainActivity.class);
        startActivity(i);
        finish();
    }
}
