package com.example.juli_soep.sekolah;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.admin_akademik.AdminAkademikActivity;
import com.example.juli_soep.sekolah.guru.GuruActivity;
import com.example.juli_soep.sekolah.home.MainActivity;
import com.example.juli_soep.sekolah.siswa.SiswaActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import volley.AppController;
import volley.Config;
import volley.Session;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = LoginActivity.class.getSimpleName();
    EditText usernames, password;
    private ProgressDialog pDialog;
    private Session session;
    SharedPreferences prefs;
    Context context;
    String id, level, user,nama;
    int socketTimeout = 15000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    Button btn_login;
    String username ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernames = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        prefs = LoginActivity.this.getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        session = new Session(getApplicationContext());

        nama = prefs.getString("nama", "");
        level = prefs.getString("lvl", "");
        username = prefs.getString("username", "");
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            if (level.contains("1")) {
                Intent intent = new Intent(LoginActivity.this, AdminAkademikActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("username",username);
                startActivity(intent);
            }
            /*else if (level.contains("2")) {
                Intent intent = new Intent(LoginActivity.this, Bak.class);
                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                startActivity(intent);
            }*/
            else if (level.contains("3")) {
                Intent intent = new Intent(LoginActivity.this, GuruActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                startActivity(intent);

            }else if (level.contains("4")) {
                Intent intent = new Intent(LoginActivity.this, SiswaActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                startActivity(intent);
            }


        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (v == btn_login){
            String usernamenya = usernames.getText().toString();
            String passwordnya = password.getText().toString();

            //Check for empty data in the form
            if(usernamenya.trim().length() > 0 && passwordnya.trim().length() > 0){
                checkLogin(usernamenya, passwordnya);
            }else{
                //Prompt user to enter credential
                Toast.makeText(getApplicationContext(),
                        "Masukan Email atau Password Anda !!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkLogin(final String username, final String password){

        //Tag used to cancel the request
        String tag_string_req = "tag";

        pDialog.setMessage("Login, Please Wait.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error) {
                            user = jObj.getString("username");
                            nama = jObj.getString("nama");
                            level = jObj.getString("lvl");


                        //user successsfully
                        //Create login Session
                        session.setLogin(true);

                        storeRegIdinSharedPref(LoginActivity.this, nama, user, level);
                        String theRole = level;
                        if (theRole.equals("1")) {
                            //Lauch to main activity
                            Intent i = new Intent(LoginActivity.this,
                                    AdminAkademikActivity.class);
                            i.putExtra("nama",nama);
                            i.putExtra("username", user);
                            i.putExtra("lvl", level);
                            startActivity(i);
                        /*} else if (theRole.equals("2")) {
                            //Lauch to main activity
                            Intent i = new Intent(LoginActivity.this,
                                    Bak.class);
                            i.putExtra("nama",nama);
                            i.putExtra("username", user);
                            i.putExtra("lvl", level);
                            startActivity(i);*/
                        } else if (theRole.equals("3")) {
                            //Lauch to main activity
                            Intent i = new Intent(LoginActivity.this,
                                    GuruActivity.class);
                            i.putExtra("nama",nama);
                            i.putExtra("username", user);
                            i.putExtra("lvl", level);
                            startActivity(i);
                        } else if (theRole.equals("4")) {
                            //Lauch to main activity
                            Intent i = new Intent(LoginActivity.this,
                                    SiswaActivity.class);
                            i.putExtra("nama",nama);
                            i.putExtra("username", user);
                            i.putExtra("lvl", level);
                            startActivity(i);
                        }
                    }else{
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
                params.put(Config.TAG, Config.TAG_LOGIN);
                params.put(Config.username, username);
                params.put(Config.password, password);

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

    private void storeRegIdinSharedPref(Context context,String nama,String username, String level) {

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nama", nama);
        editor.putString("username", username);
        editor.putString("lvl", level);
        editor.commit();
    }

}
