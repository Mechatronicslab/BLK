package com.example.juli_soep.sekolah.admin_akademik;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.example.juli_soep.sekolah.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
}
