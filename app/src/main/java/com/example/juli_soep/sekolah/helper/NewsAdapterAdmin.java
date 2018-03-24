package com.example.juli_soep.sekolah.helper;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.juli_soep.sekolah.R;

import java.util.List;

/**
 * Created by Terminator on 04/06/2017.
 */

public class NewsAdapterAdmin extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsDataAdmin> newsItems;

    public NewsAdapterAdmin(Activity activity, List<NewsDataAdmin> newsItems) {
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
            convertView = inflater.inflate(R.layout.list_row_news, null);

        TextView nmr = (TextView)convertView.findViewById(R.id.nomornya);
        TextView id = (TextView) convertView.findViewById(R.id.idnya);
        TextView nama = (TextView) convertView.findViewById(R.id.etNama);
        TextView jabatan = (TextView) convertView.findViewById(R.id.etJabatan) ;

        NewsDataAdmin news = newsItems.get(position);
        nmr.setText(String.valueOf(position+1));
        id.setText(news.getId());
        nama.setText("Nama     :   "+news.getNama());
        jabatan.setText(news.getJabatan());

        return convertView;
    }
}