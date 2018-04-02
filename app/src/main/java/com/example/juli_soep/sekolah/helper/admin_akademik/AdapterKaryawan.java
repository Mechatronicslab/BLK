package com.example.juli_soep.sekolah.helper.admin_akademik;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.juli_soep.sekolah.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import volley.Config;

/**
 * Created by MSI on 3/23/2018.
 */

public class AdapterKaryawan extends BaseAdapter implements Filterable{
    private Activity activity;
    Context context;
    private LayoutInflater inflater;
    ArrayList<DataKaryawan> newsItems;
    ArrayList<DataKaryawan> FilterList;
    ValueFilter valueFilter;

    public AdapterKaryawan(Context context, ArrayList<DataKaryawan> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
        FilterList = newsItems;

    }


    @Override
    public int getCount() {
        return newsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }
    static class ViewHolder {
        CircleImageView foto ;
        TextView nmr;
        TextView nama;
        TextView nik;
        TextView jabatan;
        TextView status;
        TextView sertifikasi;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_karyawan, null);
            holder = new ViewHolder();
            holder.foto = (CircleImageView) convertView.findViewById(R.id.foto);
            holder.nmr = (TextView) convertView.findViewById(R.id.nomornya);
            holder.nik = (TextView) convertView.findViewById(R.id.etId);
            holder.nama = (TextView) convertView.findViewById(R.id.etNama);
            holder.jabatan = (TextView) convertView.findViewById(R.id.etJabatan);
            holder.status = (TextView) convertView.findViewById(R.id.etStatus);
            holder.sertifikasi = (TextView) convertView.findViewById(R.id.etSertifikasi);
            convertView.setTag(holder);

            DataKaryawan news = newsItems.get(position);
            String urlfoto = Config.URL + "foto/";
            String urlnya = newsItems.get(position).getFoto();
            String fotoNya = urlfoto + newsItems.get(position).getFoto();
            String a = fotoNya;

            if (urlnya.length() > 1) {
                Picasso.with(this.context).load(a).into(holder.foto);
            } else {
                Picasso.with(this.context).load(R.drawable.profi).into(holder.foto);
            }
            holder.nmr.setText(String.valueOf(position + 1));
            holder.nama.setText(news.getNama());
            holder.nik.setText(news.getNik());
            holder.jabatan.setText(news.getJabatan());
            holder.status.setText(news.getStatus());

            if (news.getSertifikasi().equals("Y")) {
                holder.sertifikasi.setTextColor(Color.BLUE);
                holder.sertifikasi.setText("Sudah Sertifikasi");
            } else {
                holder.sertifikasi.setTextColor(Color.RED);
                holder.sertifikasi.setText("Belum Sertifikasi");
            }

        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new ValueFilter();
            }
            return valueFilter;
        }


    private class ValueFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<DataKaryawan> filterList = new ArrayList<DataKaryawan>();
                for (DataKaryawan user : FilterList) {
                    if (user.getNama().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        //filterList.add(user);
                        /*DataKaryawan Data = new DataKaryawan(FilterList.get(i)
                                .getFoto(),FilterList.get(i)
                                .getNama(),FilterList.get(i)
                                .getNik(),FilterList.get(i)
                                .getJabatan(),FilterList.get(i)
                                .getStatus(),FilterList.get(i)
                                .getSertifikasi());*/
                        filterList.add(user);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = FilterList.size();
                results.values = FilterList;

            }
            return results;

        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub

            //newsItems=(ArrayList<DataKaryawan>) results.values;
            //notifyDataSetChanged();

                newsItems=(ArrayList<DataKaryawan>) results.values;
                notifyDataSetChanged();

        }

    }
}

