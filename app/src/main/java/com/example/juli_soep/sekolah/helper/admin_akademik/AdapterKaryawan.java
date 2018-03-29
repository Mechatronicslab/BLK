package com.example.juli_soep.sekolah.helper.admin_akademik;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juli_soep.sekolah.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import volley.Config;

/**
 * Created by MSI on 3/23/2018.
 */

public class AdapterKaryawan extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataKaryawan> newsItems;

    public AdapterKaryawan(Activity activity, List<DataKaryawan> newsItems) {
        this.activity = activity;
        this.newsItems = newsItems;
    }

    @Override
    public int getCount() {
        return newsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return newsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_admin_akademik, null);
        CircleImageView foto = (CircleImageView) convertView.findViewById(R.id.foto);
        TextView nmr = (TextView)convertView.findViewById(R.id.nomornya);
        TextView nik = (TextView) convertView.findViewById(R.id.etId);
        TextView nama = (TextView) convertView.findViewById(R.id.etNama);
        TextView jabatan = (TextView) convertView.findViewById(R.id.etJabatan);
        TextView status = (TextView) convertView.findViewById(R.id.etStatus);
        TextView sertifikasi = (TextView) convertView.findViewById(R.id.etSertifikasi);

        DataKaryawan news = newsItems.get(position);
        String urlfoto = Config.URL+"foto/";
        String urlnya =newsItems.get(position).getFoto();
        String fotoNya = urlfoto+newsItems.get(position).getFoto();
        String a = fotoNya;

        if(urlnya.length()>1){
            Picasso.with(this.activity).load(a).into(foto);
        }else{
            Picasso.with(this.activity).load(R.drawable.profi).into(foto);
        }

        nmr.setText(String.valueOf(position+1));
        nama.setText(news.getNama());
        nik.setText(news.getNik());
        jabatan.setText(news.getJabatan());
        status.setText(news.getStatus());

        if(news.getSertifikasi().equals("Y")){
            sertifikasi.setText("Sudah Sertifikasi");
        }else{
            sertifikasi.setTextColor(Color.RED);
            sertifikasi.setText("Belum Sertifikasi");
        }
        return convertView;
    }
}
