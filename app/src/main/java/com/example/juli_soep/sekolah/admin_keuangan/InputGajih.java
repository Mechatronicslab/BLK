package com.example.juli_soep.sekolah.admin_keuangan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.AppController;
import volley.Config;

public class InputGajih extends AppCompatActivity implements Spinner.OnItemSelectedListener,  View.OnClickListener{

    private Toolbar mTopToolbar;
    private ArrayList<String> spnProfile;
    ProgressDialog pDialog;

    int socketTimeout = 30000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    //JSON Array
    private JSONArray result;

    private TextWatcher text = null;

    String strNama;
    String strPendidikan;
    String strJabatam;
    int strMasaKerja;
    double strGapok;
    double strTunJabatan;
    double strTunLainnya;
    double strJumlanPinjam;
    double strJumlahPotongan;
    double strSisaPnjam;
    double strGajihBersih;

    double strTotalGajih;


    @BindView(R.id.spnProfile) Spinner profileSpn;
    @BindView(R.id.ed_nama) EditText edtNama;
    @BindView(R.id.ed_pendidikan) EditText edtPendidikan;
    @BindView(R.id.ed_jabatan) EditText edtJabatan;
    @BindView(R.id.ed_masa_kerja) EditText edMasaKerja;
    @BindView(R.id.ed_gapok) EditText edGapok;
    @BindView(R.id.ed_tun_jabatan) EditText edTunJabatan;
    @BindView(R.id.ed_tun_lainnya) EditText edTunjanganLainnya;
    @BindView(R.id.txttotalGajih) TextView txtTotGajih;
    @BindView(R.id.ed_jmlh_pinjam) EditText edJumPinjam;
    @BindView(R.id.ed_jmlh_potongan) EditText edJumPotonga;
    @BindView(R.id.txt_sisa_Pinjam) TextView txtSisaPinjam;
    @BindView(R.id.txtgajihBersih) TextView txtGajihBersih;

    String[] dataSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_gajih);
        ButterKnife.bind(this);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Input Gajih");
        edMasaKerja.requestFocus();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        spnProfile = new ArrayList<String>();
        profileSpn.setOnItemSelectedListener(this);

        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double kampret;
                try{
                    strGapok         = Double.parseDouble(edGapok.getText().toString());
                    strTunJabatan    = Double.parseDouble(edTunJabatan.getText().toString());
                    strTunLainnya    = Double.parseDouble(edTunjanganLainnya.getText().toString());

                    kampret = strGapok + strTunLainnya + strTunJabatan;
                    txtTotGajih.setText(String.valueOf(kampret));
                }catch (NumberFormatException e){
                    strGapok         = 0.0;
                    strTunJabatan    = 0.0;
                    strTunLainnya    = 0.0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        edTunjanganLainnya.addTextChangedListener(text);
        getDataProfile();
        select();

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(InputGajih.this, AppsKeuangan.class);
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getDataProfile(){

        String tag_string_req = "req_";
        pDialog.setMessage("Loading.....");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray("result");

                            //Calling method getStudents to get the students from the JSON Array
                            getDataProfile(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(String.valueOf(getApplicationContext()), "Login Error : " + error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "viewNikKaryawan");
                return params;
            }
        };

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }


    private void getDataProfile(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                String nama = json.getString("nama");
                String nik = json.getString("nik");
                spnProfile.add(nik+" - "+ nama);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        profileSpn.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spnProfile));
        ArrayAdapter<String> mAdapter;
        mAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spnProfile);
        profileSpn.setAdapter(mAdapter);
    }

    public void getDataFromNik(final String niknya){
        //Tag used to cancel the request
        String tag_string_req = "req";

        pDialog.setMessage("Loading.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(String.valueOf(getApplication()), "Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error){
                        String nama       = jObj.getString("nama");edtNama.setText(nama);
                        String jabatan    = jObj.getString("jabatan");edtJabatan.setText(jabatan);
                        String pendidikan = jObj.getString("pendidikan");
                        //String[] dataSplit = profile.split(" - ");
                        //Toast.makeText(getApplicationContext(), dataSplit[0], Toast.LENGTH_LONG).show();
                        //getDataFromNik(dataSplit[0]);
                        String[] splitPendidikan = pendidikan.split("\\s");
                        if(splitPendidikan[0].equals(null)){
                            edtPendidikan.setText("-");
                        }else{
                            edtPendidikan.setText(splitPendidikan[0]);
                        }

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
                Log.e(String.valueOf(getApplication()), "Login Error : " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag","getNikKeuangan");
                params.put("nik", niknya);
                return params;
            }
        };


        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);
    }


    private void select(){

        profileSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                // TODO Auto-generated method stub
                String profile=profileSpn.getSelectedItem().toString();
                Log.e("Selected item : ",profile);
                dataSplit = profile.split(" - ");
                //Toast.makeText(getApplicationContext(), dataSplit[0], Toast.LENGTH_LONG).show();
                getDataFromNik(dataSplit[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    @OnClick(R.id.buttonSimpan)
    void simpadgajih(){

        dataSplit[0]     = profileSpn.getSelectedItem().toString();
        strNama          = edtNama.getText().toString();
        strPendidikan    = edtPendidikan.getText().toString();
        strJabatam       = edtJabatan.getText().toString();
        strMasaKerja     = Integer.parseInt(edMasaKerja.getText().toString());
        strGapok         = Double.parseDouble(edGapok.getText().toString());
        strTunJabatan    = Double.parseDouble(edTunJabatan.getText().toString());
        strTunLainnya    = Double.parseDouble(edTunjanganLainnya.getText().toString());
        strJumlanPinjam  = Double.parseDouble(edJumPinjam.getText().toString());
        strJumlahPotongan= Double.parseDouble(edJumPotonga.getText().toString());
        strSisaPnjam     = Double.parseDouble(txtSisaPinjam.getText().toString());
        strGajihBersih   = Double.parseDouble(txtGajihBersih.getText().toString());

    }

}
