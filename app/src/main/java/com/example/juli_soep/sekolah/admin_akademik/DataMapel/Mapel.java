package com.example.juli_soep.sekolah.admin_akademik.DataMapel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.admin_akademik.DataKaryawan.AdminAkademikActivity;
import com.example.juli_soep.sekolah.helper.admin_akademik.AdapterMapel;
import com.example.juli_soep.sekolah.helper.admin_akademik.DataKaryawan;
import com.example.juli_soep.sekolah.helper.admin_akademik.DataMapel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import volley.AppController;
import volley.Config;

public class Mapel extends AppCompatActivity implements SearchView.OnQueryTextListener {
    int previousPosition=-1;
    int count=0;
    long previousMil=0;
    ArrayList<DataMapel> MapelList=new ArrayList<DataMapel>();
    String kdMapel ;
    AdapterMapel adapter;
    private ProgressDialog pDialog;
    int socketTimeout = 10000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.list_mapel)
    ListView listMapel;
    @BindView(R.id.searchView)
    SearchView searchnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        adapter = new AdapterMapel(getApplicationContext(), MapelList);
        listMapel.setAdapter(adapter);
        ShowMapel();
        searchnya.setOnQueryTextListener(this);

    }
    @OnItemClick(R.id.list_mapel)
    void onItemClick(AdapterView<?> parent , int position) {
        if(previousPosition==position){
            count++;
            if(count==2)
            {
                DataMapel kdMapel = (DataMapel)adapter.getItem(position);
                Intent intent = new Intent(Mapel.this, EditMapel.class);
                intent.putExtra("kd_mapel", kdMapel.getKdMapel());
                startActivity(intent);

            }
        }else{
            previousPosition=position;
            count = 1;
            previousMil = System.currentTimeMillis();
        }

    }

    @OnItemLongClick(R.id.list_mapel)
    boolean OnItemLongClick(final int position) {
        AlertDialog.Builder adb = new AlertDialog.Builder(Mapel.this);
        adb.setCancelable(false);
        ImageView image = new ImageView(Mapel.this);
        image.setImageResource(R.drawable.quantum_ic_stop_grey600_36);
        adb.setTitle("Apakah Anda Yakin Ingin Menghapus Data ini? \n");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DataMapel kdMapelnya = (DataMapel) adapter.getItem(position);
                DeleteMapel(kdMapelnya.getKdMapel());

            } });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            } });
        adb.setView(image);
        adb.show();

        return false;
    }

    @OnClick(R.id.btnTambah)
    void btnTambah(){
        Intent i = new Intent(Mapel.this, TambahMapel.class);
        startActivity(i);
        finish();
    }
    private void ShowMapel(){

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
                            DataMapel object = new DataMapel();
                            object.setKdMapel(jsonObject.getString("kd_mapel"));
                            object.setNamaMapel(jsonObject.getString("nama_mapel"));

                            MapelList.add(object);
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
                Intent i = new Intent(Mapel.this,AdminAkademikActivity.class);
                startActivity(i);
                finish();

            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","ShowMapel");
                return params;
            }
        };
        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void DeleteMapel(final String kdMapel){

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
                params.put("tag","DeleteMapel");
                params.put("kd_mapel",kdMapel);
                return params;
            }
        };
        strReq.setRetryPolicy(policy);
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

        Intent a = new Intent(Mapel.this, AdminAkademikActivity.class);
        startActivity(a);
        finish();
        super.onBackPressed();
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }
}

