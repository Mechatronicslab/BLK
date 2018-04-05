package com.example.juli_soep.sekolah.helper.admin_keuangan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juli_soep.sekolah.R;

import java.util.List;

/**
 * Created by Terminator on 05/04/2018.
 */

public class AdapterGajih extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataGajih> item;

    public AdapterGajih(Activity activity, List<DataGajih> item) {
        this.activity = activity;
        this.item = item;
    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
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
            convertView = inflater.inflate(R.layout.list_rows_gajih, null);


        TextView nik = (TextView) convertView.findViewById(R.id.nik);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView gajihBersih = (TextView) convertView.findViewById(R.id.gajihbersih);
        TextView tgl = (TextView) convertView.findViewById(R.id.tgl);

        nik.setText("Nik\t\t\t\t\t: "+item.get(position).getNik());
        nama.setText("Nama\t\t\t\t: "+item.get(position).getNama());
        gajihBersih.setText("Gaji Bersih\t: "+"Rp."+item.get(position).getGjihBesih());
        tgl.setText("Tanggal\t\t\t: "+item.get(position).getTgl());

        return convertView;
    }
}
