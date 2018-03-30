package com.example.juli_soep.sekolah.admin_akademik;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juli_soep.sekolah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.AppController;
import volley.Config;
import volley.VolleyMultipartRequest;

public class TambahKaryawan extends AppCompatActivity {
    private ProgressDialog pDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    RadioButton ItemKelamin , itemSertifi ;
    int socketTimeout  = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    @BindView(R.id.foto)
    ImageView imgFoto ;
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
    @BindView(R.id.radiogrupKelamin)
    RadioGroup RadioKelamin ;
    @BindView(R.id.etPendTerakhir)
    EditText txtPendTerakhir ;
    @BindView(R.id.etTMT)
    EditText txtTmt ;
    @BindView(R.id.etJabatan)
    EditText txtJabatan ;
    @BindView(R.id.etStatus)
    EditText txtStatus ;
    @BindView(R.id.radiogrupsertifikasi)
    RadioGroup RadioSertifi ;
    @BindView(R.id.etAlamat)
    EditText txtAlamat ;

    @BindView(R.id.rLaki)
    RadioButton Laki ;
    @BindView(R.id.rSudah)
    RadioButton Sudah ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_karyawan);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Laki.setChecked(true);
        Sudah.setChecked(true);



    }


    @OnClick(R.id.foto)
    void foto(){
        Intent i = new Intent(TambahKaryawan.this , FullScreenFoto.class);
        startActivity(i);
        finish();
    }
    @OnClick(R.id.btnUpload)
    void upload(){
        Toast.makeText(getApplicationContext(),"Anda Harus Menambahkan Data Terlebih Dahulu",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnSave)
    void BtnSave(){
        String gender ,sertifi;
        int pilihKelamin = RadioKelamin.getCheckedRadioButtonId();
        ItemKelamin = (RadioButton)findViewById(pilihKelamin);

        int pilihSertifi = RadioSertifi.getCheckedRadioButtonId();
        itemSertifi = (RadioButton)findViewById(pilihSertifi);

        String nama = txtNama.getText().toString();
        String nik = txtNik.getText().toString();
        String nuptk = txtNuptk.getText().toString();
        String tmptLahir = txtTmptLahir.getText().toString();
        String tglLahir = txtTglLahir.getText().toString();
        String kelamin = ItemKelamin.getText().toString();
        String pendTerakhir = txtPendTerakhir.getText().toString();
        String tmt = txtTmt.getText().toString();
        String jabatan = txtJabatan.getText().toString();
        String status = txtStatus.getText().toString();
        String sertifikasi = itemSertifi.getText().toString();
        String alamat = txtAlamat.getText().toString();
        if(kelamin.equals("Laki-Laki")){
            gender = "L";
        }else{
            gender = "P";
        }
        if(sertifikasi.equals("Sudah")){
            sertifi ="Y";
        }else{
            sertifi ="N";
        }
        InsertKaryawan(nama,nik , nuptk ,tmptLahir,tglLahir,gender,pendTerakhir,tmt,jabatan,status,sertifi,alamat);
    }
    @OnClick(R.id.pickDate)
    void pickDate(){
        showDateDialog();
    }


    private void InsertKaryawan(final String nama, final String nik,final String nuptk,final String tmptLahir
            ,final String tglLahir,final String kelamin,final String pendTerakhir,final String tmt,final String jabatan
            ,final String status,final String sertifikasi,final String alamat){

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
                        Intent i = new Intent(TambahKaryawan.this,EditKaryawan.class);
                        i.putExtra("nik", nik);
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
                Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "InsertKaryawan");
                params.put("nama", nama);
                params.put("nik", nik);
                params.put("nuptk", nuptk);
                params.put("tmpt_lahir", tmptLahir);
                params.put("tgl_lahir", tglLahir);
                params.put("kelamin", kelamin);
                params.put("pend_terakhir", pendTerakhir);
                params.put("mulai_tugas", tmt);
                params.put("jabatan", jabatan);
                params.put("status", status);
                params.put("sertifikasi", sertifikasi);
                params.put("alamat", alamat);
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
        Intent i = new Intent(TambahKaryawan.this, karyawan.class);
        startActivity(i);
        finish();
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                txtTglLahir.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
