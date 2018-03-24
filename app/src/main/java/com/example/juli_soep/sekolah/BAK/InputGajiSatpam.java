package com.example.juli_soep.sekolah.BAK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;
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
import volley.Config_URL;

public class InputGajiSatpam extends AppCompatActivity {
    private ProgressDialog pDialog;
    String TAG = InputGajiSatpam.class.getSimpleName();
    int socketTimeout = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @BindView(R.id.etName)
    TextView getNamanya;
    @BindView(R.id.etGajiPokok)
    TextView getGajiPokok;
    @BindView(R.id.etTunjanganJabatan)
    TextView getTunjanganJabatan;
    @BindView(R.id.etPulsa)
    TextView getPulsa;
    @BindView(R.id.etThr)
    TextView getThr;
    @BindView(R.id.etTtlGaji)
    TextView getTtlGaji;
    @BindView(R.id.etPsuyono)
    TextView getSuyono;
    @BindView(R.id.etKoperasi)
    TextView getKoprasi;
    @BindView(R.id.etJalur)
    TextView getJalur;
    @BindView(R.id.etDanaSosial)
    TextView getDanaSosial;
    @BindView(R.id.etTtlPotongan)
    TextView getTtlPotongan;
    @BindView(R.id.etGajiTerima)
    TextView getGajiTerima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_gaji_satpam);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }

    @OnClick(R.id.zubmit)
    void submit() {
        String nama = getNamanya.getText().toString();
        String jabatan = "Satpam";
        String gaji_pokok = getGajiPokok.getText().toString();
        String tunj_jabatan = getTunjanganJabatan.getText().toString();
        String pulsa = getPulsa.getText().toString();
        String thr = getThr.getText().toString();
        String ttl_gaji = getTtlGaji.getText().toString();
        String p_suyono = getSuyono.getText().toString();
        String koprasi = getKoprasi.getText().toString();
        String jalur = getJalur.getText().toString();
        String dana_sosial = getDanaSosial.getText().toString();
        String ttl_potongan = getTtlPotongan.getText().toString();
        String gaji_terima = getGajiTerima.getText().toString();
        gaji_satpam(nama,jabatan,gaji_pokok,tunj_jabatan,pulsa,thr,ttl_gaji,p_suyono,koprasi,jalur,dana_sosial,ttl_potongan,gaji_terima);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(InputGajiSatpam.this, Bak.class);
        startActivity(i);
        finish();
    }

    private void gaji_satpam(final String nama,final String jabatan, final String gaji_pokok,final String tunj_jabatan
            ,final String pulsa,final String thr,final String ttl_gaji,final String suyono,final String koprasi
            ,final String jalur,final String dana_sosial,final String ttl_potongan,final String gaji_terima){

        //Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Please Wait.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config_URL.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("status");

                    if(!error){
                        String result = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(InputGajiSatpam.this,Bak.class);
                        startActivity(i);
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
                Log.e(TAG, "Login Error : " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config_URL.TAG, "gaji_satpam");
                params.put("nama", nama);
                params.put("jabatan", jabatan);
                params.put("gaji_pokok",gaji_pokok );
                params.put("tunj_jabatan", tunj_jabatan);
                params.put("pulsa", pulsa);
                params.put("thr", thr);
                params.put("ttl_gaji", ttl_gaji);
                params.put("p_suyono", suyono);
                params.put("koprasi", koprasi);
                params.put("jalur", jalur);
                params.put("dana_sosial", dana_sosial);
                params.put("ttl_potongan", ttl_potongan);
                params.put("gaji_diterima", gaji_terima);


                return params;
            }
        };

        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

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
