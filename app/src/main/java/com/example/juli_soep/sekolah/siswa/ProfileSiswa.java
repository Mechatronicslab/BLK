package com.example.juli_soep.sekolah.siswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.R;
import com.example.juli_soep.sekolah.home.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.AppController;
import volley.Config_URL;
import volley.Session;

public class ProfileSiswa extends AppCompatActivity {
    private ProgressDialog pDialog;
    private Session sesion;
    String namanya,nisnnya,alamatnya,jurusannya,nohpnya,emailnya,kelaminnya;
    int socketTimeout  = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    String nohp;
    @BindView(R.id.etNama)
    TextView nama ;
    @BindView(R.id.etNisn)
    TextView nisn ;
    @BindView(R.id.etAlamat)
    TextView alamat ;
    @BindView(R.id.etJurusan)
    TextView jurusan ;
    @BindView(R.id.etNohp)
    TextView no_hp ;
    @BindView(R.id.etEmail)
    TextView email ;
    @BindView(R.id.etKelamin)
    TextView kelamin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_siswa);
        nohp = getIntent().getExtras().getString("nohp");
        ButterKnife.bind(this);
        sesion = new Session(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        getDataSiswa();

    }

    private void getDataSiswa() {
        //String tag_string_req = "req";

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

                            namanya = jsonObject.getString("nama_lengkap");
                            nisnnya = jsonObject.getString("nisn");
                            alamatnya = jsonObject.getString("alamat");
                            jurusannya = jsonObject.getString("jurusan");
                            emailnya = jsonObject.getString("email");
                            kelaminnya = jsonObject.getString("kelamin");

                        }
                        nama.setText(namanya);
                        nisn.setText(nisnnya);
                        alamat.setText(alamatnya);
                        jurusan.setText(jurusannya);
                        no_hp.setText(nohp);
                        email.setText(emailnya);
                        kelamin.setText(kelaminnya);
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
                params.put("tag","view_siswa");
                params.put("nohp",nohp);
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
        Intent i = new Intent(ProfileSiswa.this, SiswaActivity.class);
        i.putExtra("nohp",nohp);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnLogout)
    void klik(){
        sesion.setLogin(false);
        sesion.setSessid(0);
        Intent i = new Intent(ProfileSiswa.this , MainActivity.class);
        startActivity(i);
        finish();
    }


}
