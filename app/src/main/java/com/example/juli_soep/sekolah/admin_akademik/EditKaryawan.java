package com.example.juli_soep.sekolah.admin_akademik;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.juli_soep.sekolah.helper.admin_akademik.AdapterKaryawan;
import com.example.juli_soep.sekolah.home.MainActivity;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.nearby.messages.internal.Update;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import volley.AppController;
import volley.Config;
import volley.Session;
import volley.VolleyMultipartRequest;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static com.example.juli_soep.sekolah.R.id.imageView;
import static com.example.juli_soep.sekolah.R.id.rLaki;

public class EditKaryawan extends AppCompatActivity {
    private ProgressDialog pDialog;
    String id ,foto ,nama ,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat;
    String dataObject ;
    RadioButton ItemKelamin,ItemSertifi ;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Bitmap bitmap;
    String Nik ;
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

    @BindView(R.id.rPerempuan)
    RadioButton perempuan;
    @BindView(R.id.rLaki)
    RadioButton laki;

    @BindView(R.id.rSudah)
    RadioButton Sudah ;
    @BindView(R.id.rBelum)
    RadioButton Belum ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_karyawan);
        ButterKnife.bind(this);
        Nik = getIntent().getExtras().getString("nik");
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        KaryawanGetById();
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
        Intent i = new Intent(EditKaryawan.this , FullScreenFoto.class);
        i.putExtra("nik",Nik);
        i.putExtra("foto",foto);
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
        String gender,sertifi ;
        int pilihan = RadioKelamin.getCheckedRadioButtonId();
        ItemKelamin = (RadioButton)findViewById(pilihan);

        int pilihSertifi = RadioSertifi.getCheckedRadioButtonId();
        ItemSertifi = (RadioButton)findViewById(pilihSertifi);

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
        String sertifikasi = ItemSertifi.getText().toString();
        String alamat = txtAlamat.getText().toString();
        if (kelamin.equals("Laki-Laki")){
            gender = "L";
        }else{
            gender = "P";
        }
        if(sertifikasi.equals("Sudah")){
            sertifi ="Y";
        }else{
            sertifi ="N";
        }
        UpdateKaryawan(nama,nik , nuptk ,tmptLahir,tglLahir,gender,pendTerakhir,tmt,jabatan,status,sertifi,alamat);
        //(bitmap,nama,nik , nuptk ,tmptLahir,tglLahir,kelamin,pendTerakhir,tmt,jabatan,status,sertifikasi,alamat);
    }

    @OnClick(R.id.pickDate)
    void pickDate(){
        showDateDialog();
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
                            id = jsonObject.getString("id");
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
                            Picasso.with(EditKaryawan.this).load(fotoNya).into(imgFoto);
                        }else{
                            Picasso.with(EditKaryawan.this).load(R.drawable.profi).into(imgFoto);
                        }
                        txtNama.setText(nama);
                        txtNik.setText(nik);
                        txtNuptk.setText(nuptk);
                        txtTmptLahir.setText(tmptLahir);
                        txtTglLahir.setText(tglLahir);
                        if(kelamin.equals("L")){
                            laki.setChecked(true);
                        }else{
                            perempuan.setChecked(true);
                        }
                        txtPendTerakhir.setText(pendTerakhir);
                        txtTmt.setText(tmt);
                        txtJabatan.setText(jabatan);
                        txtStatus.setText(status);
                        if(sertifikasi.equals("Y")){
                            Sudah.setChecked(true);
                        }else{
                            Belum.setChecked(true);
                        }
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
                Intent i = new Intent(EditKaryawan.this,karyawan.class);
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


    private void UpdateKaryawan(final String nama, final String nik,final String nuptk,final String tmptLahir
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
                        Intent i = new Intent(EditKaryawan.this,karyawan.class);
                        i.putExtra("nik",nik);
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
                JSONObject data = new JSONObject();
                try {
                    data.put("id",id);
                    data.put("nama", nama);
                    data.put("nik", nik);
                    data.put("nuptk", nuptk);
                    data.put("tmpt_lahir", tmptLahir);
                    data.put("tgl_lahir", tglLahir);
                    data.put("kelamin", kelamin);
                    data.put("pend_terakhir", pendTerakhir);
                    data.put("mulai_tugas", tmt);
                    data.put("jabatan", jabatan);
                    data.put("status", status);
                    data.put("sertifikasi", sertifikasi);
                    data.put("alamat", alamat);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(data);

                try {
                    JSONObject dataObj = new JSONObject();
                    dataObj.put("data", jsonArray);
                    dataObject = dataObj.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Hasil:",dataObject);
                params.put("tag", "UpdateKaryawan");
                params.put("data", dataObject);
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
        Intent i = new Intent(EditKaryawan.this, karyawan.class);
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

