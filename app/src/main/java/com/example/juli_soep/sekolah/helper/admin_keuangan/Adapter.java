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
 * Created by Terminator on 04/04/2018.
 */

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPeminjaman> item;

    public Adapter(Activity activity, List<DataPeminjaman> item) {
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
            convertView = inflater.inflate(R.layout.list_rows, null);


        TextView nik = (TextView) convertView.findViewById(R.id.nik);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView pinjaman = (TextView) convertView.findViewById(R.id.pinjaman);

        nik.setText("Nik\t\t\t\t: "+item.get(position).getNik());
        nama.setText("Nama\t\t\t: "+item.get(position).getNama());
        pinjaman.setText("Pinjaman\t: "+"Rp."+item.get(position).getPinjaman());

        return convertView;
    }
}
