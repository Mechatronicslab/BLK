package com.example.juli_soep.sekolah.admin_akademik.DataMapel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class TambahMapel extends AppCompatActivity {
    private ProgressDialog pDialog;
    int socketTimeout  = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.etKdMapel)
    EditText etKdMapel ;
    @BindView(R.id.etNamaMapel)
    EditText etNamaMapel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mapel);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }
    @OnClick(R.id.btnSave)
    void btnSave(){
        String KdMapel = etKdMapel.getText().toString();
        String NamaMapel = etNamaMapel.getText().toString();
        if(!KdMapel.isEmpty()&&!NamaMapel.isEmpty()){
            InsertMapel(KdMapel,NamaMapel);
        }else{
            Toast.makeText(getApplicationContext(),"Pastikan Semua Data Sudah Terisi",Toast.LENGTH_LONG).show();
        }
    }
    private void InsertMapel(final String kdMapel, final String namaMapel){

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
                        Intent i = new Intent(TambahMapel.this,Mapel.class);
                        startActivity(i);
                        finish();
                    }else {
                        String error_msg = jObj.getString("error_msg");
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
                Toast.makeText(getApplicationContext(), Config.Jaringan_error, Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "InsertMapel");
                params.put("kd_mapel", kdMapel);
                params.put("nama_mapel", namaMapel);

                return params;
            }
        };

        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

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
    public void onBackPressed() {
        Intent i = new Intent(TambahMapel.this, Mapel.class);
        startActivity(i);
        finish();
    }
}
