package com.example.juli_soep.sekolah.admin_akademik.DataKaryawan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import volley.AppController;
import volley.Config;
import volley.Session;

public class DetailKaryawan extends AppCompatActivity {
    private ProgressDialog pDialog;
    String foto ,nama ,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat;
    private Session sesion;
    String Nik ;
    int socketTimeout  = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.foto)
    CircleImageView imgFoto ;
    @BindView(R.id.etNama)
    TextView txtNama ;
    @BindView(R.id.etNik)
    TextView txtNik ;
    @BindView(R.id.etNuptk)
    EditText txtNuptk ;
    @BindView(R.id.etTmptLahir)
    EditText txtTmptLahir ;
    @BindView(R.id.etTglLahir)
    EditText txtTglLahir ;
    @BindView(R.id.etKelamin)
    EditText txtKelamin ;
    @BindView(R.id.etPendTerakhir)
    EditText txtPendTerakhir ;
    @BindView(R.id.etTMT)
    EditText txtTmt ;
    @BindView(R.id.etJabatan)
    EditText txtJabatan ;
    @BindView(R.id.etStatus)
    EditText txtStatus ;
    @BindView(R.id.etSertifikasi)
    EditText txtSertifikasi ;
    @BindView(R.id.etAlamat)
    EditText txtAlamat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);
        ButterKnife.bind(this);
        Nik = getIntent().getExtras().getString("nik");
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        KaryawanGetById();

    }

    @OnClick(R.id.btnEdit)
    void btnEdit(){
        Intent i = new Intent(DetailKaryawan.this,EditKaryawan.class);
        i.putExtra("nik",Nik);
        startActivity(i);
        finish();
    }

    private void KaryawanGetById() {
        //String tag_string_req = "req";

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
                            foto = jsonObject.getString("foto");
                            nama = jsonObject.getString("nama");
                            nik = jsonObject.getString("nik");
                            nuptk = jsonObject.getString("nuptk");
                            tmptLahir = jsonObject.getString("tmpt_lahir");
                            tglLahir = jsonObject.getString("tgl_lahir");
                            kelamin = jsonObject.getString("j_kelamin");
                            pendTerakhir = jsonObject.getString("pend_terakhir");
                            tmt = jsonObject.getString("mulai_tugas");
                            jabatan = jsonObject.getString("jabatan");
                            status = jsonObject.getString("status");
                            sertifikasi = jsonObject.getString("sertifikasi");
                            alamat = jsonObject.getString("alamat");

                        }


                        String urlfoto = Config.URL+"foto/";
                        String fotoNya = urlfoto+foto;


                        if(foto.length()>1){
                            Picasso.with(DetailKaryawan.this).load(fotoNya).into(imgFoto);
                        }else{
                            Picasso.with(DetailKaryawan.this).load(R.drawable.ic_user).into(imgFoto);
                        }
                        txtNama.setText(nama);
                        txtNik.setText(nik);
                        txtNuptk.setText(nuptk);
                        txtTmptLahir.setText(tmptLahir);
                        txtTglLahir.setText(tglLahir);
                        txtKelamin.setText(kelamin);
                        txtPendTerakhir.setText(pendTerakhir);
                        txtTmt.setText(tmt);
                        txtJabatan.setText(jabatan);
                        txtStatus.setText(status);
                        txtSertifikasi.setText(sertifikasi);
                        txtAlamat.setText(alamat);
                    }else {
                        String error_msg = jObj.getString("error_msg");
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
                Intent i = new Intent(DetailKaryawan.this,karyawan.class);
                startActivity(i);
                finish();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","KaryawanGetById");
                params.put("nik",Nik);
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
    public void onBackPressed() {
        Intent i = new Intent(DetailKaryawan.this, karyawan.class);
        startActivity(i);
        finish();
    }

}
