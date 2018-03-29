package com.example.juli_soep.sekolah.helper.admin_akademik;

import android.app.ProgressDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import volley.AppController;
import volley.Config;

import static volley.AppController.TAG;

/**
 * Created by MSI on 3/25/2018.
 */

public class Proses_function {
    private ProgressDialog pDialog;

    /*private void UpdateKaryawan(final String nama,final String jabatan, final String gaji_pokok,final String tunj_jabatan
            ,final String pulsa,final String thr,final String ttl_gaji,final String suyono,final String koprasi
            ,final String jalur,final String dana_sosial,final String ttl_potongan,final String gaji_terima){

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
                    boolean error = jObj.getBoolean("status");

                    if(!error){
                        String result = jObj.getString("message");
                        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(InputGajiSatpam.class,Bak.class);
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
                params.put(Config.TAG, "gaji_satpam");
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

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }*/
}
