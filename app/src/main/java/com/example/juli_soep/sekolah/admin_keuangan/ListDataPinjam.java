package com.example.juli_soep.sekolah.admin_keuangan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.admin_akademik.DataKaryawan.AdminAkademikActivity;
import com.example.juli_soep.sekolah.admin_akademik.DataKaryawan.karyawan;
import com.example.juli_soep.sekolah.helper.admin_keuangan.Adapter;
import com.example.juli_soep.sekolah.helper.admin_keuangan.DataPeminjaman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import volley.AppController;
import volley.Config;

public class ListDataPinjam extends AppCompatActivity {

    private Toolbar mTopToolbar;
    Adapter adapter;
    ListView list;
    //SwipeRefreshLayout swipe;
    ArrayList<DataPeminjaman> newsList = new ArrayList<DataPeminjaman>();

    int socketTimeout = 30000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_pinjam);
        ButterKnife.bind(this);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("List Peminjaman");
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.list_news);
        newsList.clear();

        adapter = new Adapter(ListDataPinjam.this, newsList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ListDataPinjam.this, DetailPeminjaman.class);
                intent.putExtra("nik", newsList.get(position).getNik());
                intent.putExtra("nama", newsList.get(position).getNama());
                intent.putExtra("pinjaman", newsList.get(position).getPinjaman());
                startActivity(intent);
            }
        });

        getData();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(ListDataPinjam.this, SetPinjaman.class);
        startActivity(a);
        finish();
    }


    // fungsi kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Fungsi get JSON Mahasiswa
    private void getData() {

        String tag_string_req = "req";

        pDialog.setMessage("Loading.....");
        showDialog();

        String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.POST, Config.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    boolean error = jObj.getBoolean("error");

                    if(error == false){
                        String getObject = jObj.getString("result");
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            DataPeminjaman news = new DataPeminjaman();

                            news.setNik(jsonObject.getString("nik"));
                            news.setNama(jsonObject.getString("nama"));
                            news.setPinjaman(jsonObject.getString("jumlah_pinjam"));

                            newsList.add(news);
                        }
                    }else {
                        String error_msg = jObj.getString("error");
                        Toast.makeText(getApplicationContext(), error_msg,
                                Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), Config.Jaringan_error ,Toast.LENGTH_LONG).show();
                hideDialog();
                Intent i = new Intent(ListDataPinjam.this,AppsKeuangan.class);
                startActivity(i);
                finish();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","viewPeminjaman");
                return params;
            }
        };

        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
