package com.example.juli_soep.sekolah.helper.admin_akademik;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.juli_soep.sekolah.R;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by MSI on 4/2/2018.
 */

public class AdapterMapel extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<DataMapel> MapelList;
    ArrayList<DataMapel> FilterList;
    ValueFilter valueFilter;

    public AdapterMapel(Context context, ArrayList<DataMapel> MapelList) {
        this.context = context;
        this.MapelList = MapelList;
        FilterList = MapelList;

    }


    @Override
    public int getCount() {
        return MapelList.size();
    }

    @Override
    public Object getItem(int position) {
        return MapelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }
    static class ViewHolder {
        TextView nmr;
        TextView kdMapel;
        TextView namaMapel;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AdapterMapel.ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_mapel, null);
            holder = new AdapterMapel.ViewHolder();
            holder.nmr = (TextView) convertView.findViewById(R.id.txtNmr);
            holder.kdMapel = (TextView) convertView.findViewById(R.id.txtKdMapel);
            holder.namaMapel = (TextView) convertView.findViewById(R.id.txtNamaMapel);
            convertView.setTag(holder);

            DataMapel news = MapelList.get(position);

            holder.nmr.setText(String.valueOf(position + 1));
            holder.kdMapel.setText(news.getKdMapel());
            holder.namaMapel.setText(news.getNamaMapel());


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


    class ValueFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<DataMapel> filterList = new ArrayList<DataMapel>();
                for (DataMapel user : FilterList) {
                    if (user.getNamaMapel().toLowerCase().contains(constraint.toString().toLowerCase())) {
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

            MapelList=(ArrayList<DataMapel>) results.values;
            notifyDataSetChanged();

        }

    }
}

