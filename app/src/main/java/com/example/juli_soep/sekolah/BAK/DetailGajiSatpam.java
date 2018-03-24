package com.example.juli_soep.sekolah.BAK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.helper.NewsDataAdmin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import volley.AppController;
import volley.Config_URL;

public class DetailGajiSatpam extends AppCompatActivity {
    private ProgressDialog pDialog;
    String id ,nama,gaji_pokok,tunj_jabatan,pulsa,thr,ttl_gaji,p_suyono,koprasi
            ,jalur,dana_sosial,ttl_potongan,gaji_diterima;
    int socketTimeout  = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.etNama)
    TextView getNama;
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
        setContentView(R.layout.activity_detail_gaji_satpam);
        ButterKnife.bind(this);
        id = getIntent().getExtras().getString("id");
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        detailGaji();
    }

    private void detailGaji(){

        pDialog.setMessage("Loading.....");
        showDialog();

        String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config_URL.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("status");

                    if(!error){
                        String getObject = jObj.getString("tag");
                        JSONArray jsonArray = new JSONArray(getObject);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            nama = jsonObject.getString("nama");
                            gaji_pokok = jsonObject.getString("gaji_pokok");
                            tunj_jabatan = jsonObject.getString("tunj_jabatan");
                            pulsa = jsonObject.getString("pulsa");
                            thr = jsonObject.getString("thr");
                            ttl_gaji = jsonObject.getString("ttl_gaji");
                            p_suyono = jsonObject.getString("p_suyono");
                            koprasi = jsonObject.getString("koprasi");
                            jalur = jsonObject.getString("jalur");
                            dana_sosial = jsonObject.getString("dana_sosial");
                            ttl_potongan = jsonObject.getString("ttl_potongan");
                            gaji_diterima = jsonObject.getString("gaji_diterima");
                        }
                        getNama.setText(nama);
                        getGajiPokok.setText(gaji_pokok);
                        getTunjanganJabatan.setText(tunj_jabatan);
                        getPulsa.setText(pulsa);
                        getThr.setText(thr);
                        getTtlGaji.setText(ttl_gaji);
                        getSuyono.setText(p_suyono);
                        getKoprasi.setText(koprasi);
                        getJalur.setText(jalur);
                        getDanaSosial.setText(dana_sosial);
                        getTtlPotongan.setText(ttl_potongan);
                        getGajiTerima.setText(gaji_diterima);
                    }else {
                        String error_msg = jObj.getString("message");
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
                Toast.makeText(getApplicationContext(), error.getMessage() ,Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","view_gaji_satpam_byId");
                params.put("id",id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
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
        Intent a = new Intent(DetailGajiSatpam.this, Bak.class);
        startActivity(a);
        finish();
        super.onBackPressed();
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

}
