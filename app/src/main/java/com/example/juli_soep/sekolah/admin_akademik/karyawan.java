package com.example.juli_soep.sekolah.admin_akademik;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.juli_soep.sekolah.helper.admin_akademik.AdapterKaryawan;
import com.example.juli_soep.sekolah.helper.admin_akademik.DataKaryawan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;
import volley.AppController;
import volley.Config;

public class karyawan extends AppCompatActivity  {
    int previousPosition=-1;
    int count=0;
    long previousMil=0;
    String nik ;
    List<DataKaryawan> newsList = new ArrayList<DataKaryawan>();

    private static final String TAG = karyawan.class.getSimpleName();
    boolean longclick;
    AdapterKaryawan adapter;
    Handler handler;
    Runnable runnable;
    private ProgressDialog pDialog;

    int socketTimeout = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.list_news)
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan);
        ButterKnife.bind(this);
        newsList.clear();
        adapter = new AdapterKaryawan(karyawan.this, newsList);
        list.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        ShowKaryawan();
    }

    @OnItemClick(R.id.list_news)
    void onItemClick(int position) {
        if(previousPosition==position){
            count++;
            if(count==2 && System.currentTimeMillis()-previousMil<=1000)
            {
                Intent intent = new Intent(karyawan.this, EditKaryawan.class);
                intent.putExtra("nik", newsList.get(position).getNik());
                startActivity(intent);
            }
        }else
        {
            previousPosition = position;
            count = 1;
            previousMil = System.currentTimeMillis();
        }
    }

    @OnItemLongClick(R.id.list_news)
    boolean OnItemLongClick(final int position) {
        AlertDialog.Builder adb = new AlertDialog.Builder(karyawan.this);
        adb.setCancelable(false);
        ImageView image = new ImageView(karyawan.this);
        image.setImageResource(R.drawable.quantum_ic_stop_grey600_36);
        adb.setTitle("Apakah Anda Yakin Ingin Menghapus Data ini? \n");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                nik = newsList.get(position).getNik();
                DeleteKaryawan(nik);

            } });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            } });
        adb.setView(image);
        adb.show();

        return false;
    }

    @OnClick (R.id.btnTambah)
    void btnTambah(){
        Intent i = new Intent(karyawan.this, TambahKaryawan.class);
        startActivity(i);
        finish();
    }
    private void ShowKaryawan(){

        pDialog.setMessage("Loading.....");
        showDialog();

        String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error){
                        String getObject = jObj.getString("result");
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            DataKaryawan object = new DataKaryawan();
                            object.setNama(jsonObject.getString("nama"));
                            object.setNik(jsonObject.getString("nik"));
                            object.setFoto(jsonObject.getString("foto"));
                            object.setJabatan(jsonObject.getString("jabatan"));
                            object.setStatus(jsonObject.getString("status"));
                            object.setSertifikasi(jsonObject.getString("sertifikasi"));
                            /*String sertifi = jsonObject.getString("sertifikasi");
                            if (sertifi.equals("Y")){
                                object.setSertifikasi("Sudah Sertifikasi");
                            }else{
                                object.setSertifikasi("Belum Sertifikasi");

                            }*/

                            newsList.add(object);
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
                Intent i = new Intent(karyawan.this,AdminAkademikActivity.class);
                startActivity(i);
                finish();

            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","ShowKaryawan");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void DeleteKaryawan(final String nik){

        pDialog.setMessage("Loading.....");
        showDialog();

        //String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error){
                        String suksess = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),suksess,Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    }else {
                        String error_msg = jObj.getString("error");
                        Toast.makeText(getApplicationContext(), error_msg,
                                Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), Config.Jaringan_error ,Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","DeleteKaryawan");
                params.put("nik",nik);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq);
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onBackPressed()
    {

        Intent a = new Intent(karyawan.this, AdminAkademikActivity.class);
        startActivity(a);
        finish();
        super.onBackPressed();
    }
}
