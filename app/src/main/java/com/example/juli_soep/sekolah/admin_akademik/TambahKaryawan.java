package com.example.juli_soep.sekolah.admin_akademik;

import android.Manifest;
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
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import volley.AppController;
import volley.Config;
import volley.VolleyMultipartRequest;

public class TambahKaryawan extends AppCompatActivity {
    private ProgressDialog pDialog;
    String id ,foto ,nama ,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat;
    String dataObject ;
    private Bitmap bitmap;
    String Nik ;
    ImageView btnImg ;
    boolean zoomOut;
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
        setContentView(R.layout.activity_tambah_karyawan);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                //displaying selected image to imageview
                imgFoto.setImageBitmap(bitmap);

                //calling the method uploadBitmap to upload image
                uploadBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.foto)
    void foto(){
        Intent i = new Intent(TambahKaryawan.this , FullScreenFoto.class);
        startActivity(i);
        finish();
    }
    @OnClick(R.id.btnUpload)
    void upload(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.btnSave)
    void BtnSave(){
        String nama = txtNama.getText().toString();
        String nik = txtNik.getText().toString();
        String nuptk = txtNuptk.getText().toString();
        String tmptLahir = txtTmptLahir.getText().toString();
        String tglLahir = txtTglLahir.getText().toString();
        String kelamin = txtKelamin.getText().toString();
        String pendTerakhir = txtPendTerakhir.getText().toString();
        String tmt = txtTmt.getText().toString();
        String jabatan = txtJabatan.getText().toString();
        String status = txtStatus.getText().toString();
        String sertifikasi = txtSertifikasi.getText().toString();
        String alamat = txtAlamat.getText().toString();
        InsertKaryawan(nama,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat);
        //(bitmap,nama,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat);
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
                        Intent i = new Intent(TambahKaryawan.this,karyawan.class);
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
                params.put("id",id);
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



    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(final Bitmap bitmap) {

        //getting the tag from the edittext
        //final String tags = editTextTags.getText().toString().trim();

        //our custom volley request

        pDialog.setMessage("Loading.....");
        showDialog();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Config.URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.e("Response: ", response.toString());
                        hideDialog();
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            boolean error = obj.getBoolean("error");

                            if(!error){
                                String suksess = obj.getString("message");
                                Toast.makeText(getApplicationContext(),suksess,Toast.LENGTH_LONG).show();
                            }else {
                                String error_msg = obj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        error_msg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "errorr", Toast.LENGTH_SHORT).show();
                        hideDialog();
                    }
                }) {

            /*
            * If you want to add more parameters with the image
            * you can do it here
            * here we have only one parameter with the image
            * which is tags
            * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tag", "uploadImg");
                params.put("id", id);
                return params;
            }

            /*
            * Here we are passing image by renaming it with a unique name
            * */
            @Override
            protected Map<String, DataPart> getByteData()throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;

            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
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
}
