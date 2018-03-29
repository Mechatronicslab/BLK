package com.example.juli_soep.sekolah.admin_akademik;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.home.MainActivity;
import com.example.juli_soep.sekolah.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import volley.AppController;
import volley.Config;

public class DaftarGuru extends AppCompatActivity implements View.OnClickListener {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView dateShow;
    private Button datePick;
    private RadioGroup radioGroupGender, radioGroupSertifikasi;
    private RadioButton male, female, sudah, belum;
    private RadioButton gendernya , sertifinya ;

    private EditText nama , nuptk,nik,tmpt_lahir,tgl_lahir,kelamin,pend_terakhir,
            mulai_tugas,jabatan,status,sertifikasi,alamat;
    private Button save,batal;
    private ProgressDialog pDialog;
    String TAG = DaftarGuru.class.getSimpleName();
    int socketTimeout = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_guru);
        radioGroupGender = (RadioGroup) findViewById(R.id.radiogrupsex);
        radioGroupSertifikasi = (RadioGroup) findViewById(R.id.radiogrupsertifikasi);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        dateShow = (TextView) findViewById(R.id.etTglLahir);
        datePick = (Button) findViewById(R.id.pickDate);
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        nama = (EditText)findViewById(R.id.etNama);
        nuptk = (EditText)findViewById(R.id.etNuptk);
        nik = (EditText)findViewById(R.id.etNik);
        tmpt_lahir = (EditText)findViewById(R.id.etTmptLahir);
        tgl_lahir = (EditText)findViewById(R.id.etTglLahir);
        tgl_lahir.setEnabled(false);
        pend_terakhir = (EditText)findViewById(R.id.etPendTerakhir);
        mulai_tugas = (EditText)findViewById(R.id.etTMT);
        jabatan = (EditText)findViewById(R.id.etJabatan);
        status = (EditText)findViewById(R.id.etStatus);
        alamat = (EditText)findViewById(R.id.etAlamat);
        save = (Button)findViewById(R.id.submit);
        batal = (Button)findViewById(R.id.batal);
        save.setOnClickListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DaftarGuru.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        int gender = radioGroupGender.getCheckedRadioButtonId();
        gendernya = (RadioButton)findViewById(gender);
        int sertifikasi=radioGroupSertifikasi.getCheckedRadioButtonId();
        sertifinya= (RadioButton)findViewById(sertifikasi);
        String namanya = nama.getText().toString();
        String nuptknya = nuptk.getText().toString();
        String niknya = nik.getText().toString();
        String tmpt_lahirnya = tmpt_lahir.getText().toString();
        String tgl_lahirnya = tgl_lahir.getText().toString();
        String kelaminnya = gendernya.getText().toString();
        String pendidikannya = pend_terakhir.getText().toString();
        String mulai_tugasnya = mulai_tugas.getText().toString();
        String jabatannya = jabatan.getText().toString();
        String statusnya = status.getText().toString();
        String sertifikasinya = sertifinya.getText().toString();
        String alamatnya = alamat.getText().toString();
        if(v == save){
            if(namanya.trim().length() > 0 && nuptknya.trim().length() > 0&& niknya.trim().length() > 0
                    && tmpt_lahirnya.trim().length() > 0&& tgl_lahirnya.trim().length() > 0&& kelaminnya.trim().length() > 0
                    && pendidikannya.trim().length() > 0&& mulai_tugasnya.trim().length() > 0&& jabatannya.trim().length() > 0
                    && statusnya.trim().length() > 0&& sertifikasinya.trim().length() > 0&& alamatnya.trim().length() > 0){
                input_guru(namanya, nuptknya,niknya,tmpt_lahirnya,tgl_lahirnya,kelaminnya,pendidikannya,mulai_tugasnya
                        ,jabatannya,statusnya,sertifikasinya,alamatnya);
            }else{
                //Prompt user to enter credential
                Toast.makeText(getApplicationContext(),
                        "Pastikan Semua Sudah Terisi",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void input_guru(final String nama, final String nuptk,final String nik,final String tmpt_lahir
            ,final String tgl_lahir,final String kelamin,final String pend_terakhir,final String mulai_tugas,final String jabatan
            ,final String status,final String sertifikasi,final String alamat){

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
                        Intent i = new Intent(DaftarGuru.this,MainActivity.class);
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
                params.put(Config.TAG, "input_guru");
                params.put("nama", nama);
                params.put("nuptk", nuptk);
                params.put("nik", nik);
                params.put("tmpt_lahir", tmpt_lahir);
                params.put("tgl_lahir", tgl_lahir);
                params.put("kelamin", kelamin);
                params.put("pendidikan_terakhir", pend_terakhir);
                params.put("mulai_tugas", mulai_tugas);
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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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
                dateShow.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

}
