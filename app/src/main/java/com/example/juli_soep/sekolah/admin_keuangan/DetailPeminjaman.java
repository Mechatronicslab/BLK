package com.example.juli_soep.sekolah.admin_keuangan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.AppController;
import volley.Config;

public class DetailPeminjaman extends AppCompatActivity {

    private Toolbar mTopToolbar;
    @BindView(R.id.ed_nik)
    EditText edNik;
    @BindView(R.id.ed_nama)
    EditText edNama;
    @BindView(R.id.ed_jumlah_pinjaman)
    EditText edPinjaman;

    ProgressDialog pDialog;

    int socketTimeout = 30000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peminjaman);
        ButterKnife.bind(this);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Ubah Data");
        Intent a        = getIntent();
        String nik      = a.getStringExtra("nik");
        String nama     = a.getStringExtra("nama");
        String pinjaman = a.getStringExtra("pinjaman");

        edNik.setText(nik);
        edNama.setText(nama);
        edPinjaman.setText(pinjaman);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }

    @OnClick(R.id.buttonUpdatePinjaman)
    void update(){

        final String niknya       = edNik.getText().toString();
        final String pinjamannya  = edPinjaman.getText().toString();


        if(!pinjamannya.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailPeminjaman.this);
            builder.setTitle(Html.fromHtml("<font color='#2980B9'><b>Peringatan !</b></font>"));

            builder.setMessage(Html.fromHtml("<font color='#2980B9'><b>Yakin Ingin Merubah Data ?</b></font>"))
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            updatePinjaman(niknya, pinjamannya);
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                }).show();
        }else{
            Toast.makeText(getApplicationContext(),"Silahkan Input Data Peminjaman",Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.buttonHapusPinjaman)
    void hapus(){

        final String niknya       = edNik.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPeminjaman.this);
        builder.setTitle(Html.fromHtml("<font color='#2980B9'><b>Peringatan !</b></font>"));

        builder.setMessage(Html.fromHtml("<font color='#2980B9'><b>Yakin Ingin Merubah Data ?</b></font>"))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePinjaman(niknya);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(DetailPeminjaman.this, ListDataPinjam.class);
        startActivity(a);
        finish();
    }

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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void updatePinjaman(final String nik, final String pinjaman){

        //Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Please Wait.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: " , response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error){
                        String suksess = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),suksess,Toast.LENGTH_LONG).show();
                        Intent a = new Intent(DetailPeminjaman.this, ListDataPinjam.class);
                        startActivity(a);
                        finish();
                    }else {
                        String error_msg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                error_msg, Toast.LENGTH_LONG).show();
                    }

                }catch (JSONException e){
                    //JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("Update Error : " , error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "updatePinjamanKaryawan");
                params.put("pinjaman", pinjaman);
                params.put("nik", nik);
                return params;
            }
        };

        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

    }

    private void deletePinjaman(final String nik){

        //Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Please Wait.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: " , response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error){
                        String suksess = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),suksess,Toast.LENGTH_LONG).show();
                        Intent a = new Intent(DetailPeminjaman.this, ListDataPinjam.class);
                        startActivity(a);
                        finish();
                    }else {
                        String error_msg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                error_msg, Toast.LENGTH_LONG).show();
                    }

                }catch (JSONException e){
                    //JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("Update Error : " , error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "deletePinjamanKaryawan");
                params.put("nik", nik);
                return params;
            }
        };

        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

    }
}
