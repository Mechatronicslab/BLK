package com.example.juli_soep.sekolah.BAK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.AppController;
import volley.Config;

public class InputGajiPegawai extends AppCompatActivity {
    private ProgressDialog pDialog;
    String TAG = InputGajiPegawai.class.getSimpleName();
    int socketTimeout = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.etNama)
    TextView getNama;
    @BindView(R.id.etNik)
    TextView getNik;
    @BindView(R.id.etGol)
    TextView getGol;
    @BindView(R.id.etPendTerakhir)
    TextView getPendTerakhir;
    @BindView(R.id.etJabatan)
    TextView getJabatan;
    @BindView(R.id.etPengkerja)
    TextView getPengkerja;
    @BindView(R.id.etTunjanganAwal)
    TextView getTunjanganawal;
    @BindView(R.id.spinnerPersenan)
    Spinner spis;
    @BindView(R.id.etJmlPotongan)
    TextView getJmlPotongan;
    @BindView(R.id.etGajiPokok)
    TextView getGajiPokok;
    @BindView(R.id.etTunjanganJabatan)
    TextView getTunjanganJabatan;
    @BindView(R.id.etTunjanganLain)
    TextView getTunjanganLain;
    @BindView(R.id.etJmlPenghasilan)
    TextView getJmlPenghasilan;
    @BindView(R.id.etJmlPinjaman)
    TextView getJmlPinjaman;
    @BindView(R.id.etJmlPotonganKaryawan)
    TextView getjmlPotonganKaryawan;
    @BindView(R.id.etSisaPinjaman)
    TextView getSisaPinjaman;
    @BindView(R.id.etJmlDibayar)
    TextView getJmlDibayar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_gaji_pegawai);
        ButterKnife.bind(this);
        List<String> persenan = new ArrayList<>();
        persenan.add("-10 %");
        persenan.add("10 %");
        ArrayAdapter<String> dataAdapterpersenan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, persenan);
        dataAdapterpersenan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spis.setAdapter(dataAdapterpersenan);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

    }
    @OnClick(R.id.submit)
    void klik(){
        String nama = getNama.getText().toString();
        String nik = getNik.getText().toString();
        String gol = getGol.getText().toString();
        String pend_terakhir = getPendTerakhir.getText().toString();
        String jabatan = getJabatan.getText().toString();
        String pengkerja = getPengkerja.getText().toString();
        String tunj_awal = getTunjanganawal.getText().toString();
        String potongan_persenan = spis.getSelectedItem().toString();
        String jml_potongan = getJmlPotongan.getText().toString();
        String gaji_pokok = getGajiPokok.getText().toString();
        String tunj_jabatan = getTunjanganJabatan.getText().toString();
        String tunj_lain = getTunjanganLain.getText().toString();
        String jml_penghasilan = getJmlPenghasilan.getText().toString();
        String jml_pinjaman = getJmlPinjaman.getText().toString();
        String jml_potongan_staff = getjmlPotonganKaryawan.getText().toString();
        String sisa_pinjaman = getSisaPinjaman.getText().toString();
        String jml_dibayar = getJmlDibayar.getText().toString();

        gaji_staff(nama,nik,gol,pend_terakhir,jabatan,pengkerja,tunj_awal,potongan_persenan,jml_potongan,gaji_pokok,tunj_jabatan
        ,tunj_lain,jml_penghasilan,jml_pinjaman,jml_potongan_staff,sisa_pinjaman,jml_dibayar);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(InputGajiPegawai.this, Bak.class);
        startActivity(i);
        finish();
    }


    private void gaji_staff(final String nama, final String nik,final String golongan
            ,final String pend_terakhir,final String jabatan,final String pengkerja,final String tunj_awal,final String potongan_persenan
            ,final String jml_potongan,final String gaji_pokok,final String tunj_jabatan,final String tunj_lain,final String jml_penghasilan
            ,final String jml_pinjaman,final String jml_potongan_staff,final String sisa_pinjaman,final String jml_dibayar){

        //Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Please Wait.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {
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
                        Intent i = new Intent(InputGajiPegawai.this,Bak.class);
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
                params.put(Config.TAG, "gaji_staff");
                params.put("nama", nama);
                params.put("nik", nik);
                params.put("golongan", golongan);
                params.put("pend_terakhir", pend_terakhir);
                params.put("jabatan", jabatan);
                params.put("pengkerja", pengkerja);
                params.put("tunj_awal", tunj_awal);
                params.put("potongan_persenan", potongan_persenan);
                params.put("jml_potongan", jml_potongan);
                params.put("gaji_pokok", gaji_pokok);
                params.put("tunj_jabatan", tunj_jabatan);
                params.put("tunj_lain", tunj_lain);
                params.put("jml_penghasilan", jml_penghasilan);
                params.put("jml_pinjaman", jml_pinjaman);
                params.put("jml_potongan_staff", jml_potongan_staff);
                params.put("sisa_pinjaman", sisa_pinjaman);
                params.put("jml_dibayar", jml_dibayar);

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
