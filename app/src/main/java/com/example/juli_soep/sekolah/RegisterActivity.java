package com.example.juli_soep.sekolah;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juli_soep.sekolah.admin_akademik.DaftarGuru;
import com.example.juli_soep.sekolah.home.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import volley.AppController;
import volley.Config_URL;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText tingkat,program,nama,nisn,nis,no_ijazah,no_skhun,no_un,nik,npsn,sekolah_asal,
    tmpt_lahir,tgl_lahir,keb_khusus,alamat,dusun,kelurahan,kecamatan,kabupaten,provinsi,
    alat_transport,jns_tinggal,tlp_rmh,email,no_hp,no_kks,no_kps,usulan_pip,no_kip,nama_kip,
    alasan_tolak_kip,no_reg_akta,tinggi_bdn,berat_bdn,jarak_sekolah,waktu_tempuh_sekolah,
    jml_sodara,jns_prestasi,tingkat_prestasi,nama_prestasi,thn_dapat_prestasi,sumber_prestasi,
    jns_beasiswa,sumber_beasiswa,thn_mulai_beasiswa,tahun_selesai_beasiswa,jns_kesejahteraan,
    no_kesejahteraan,thn_mulai_kesejahteraan,thn_selesai_kesejahteraan,jurusan_pilihan;
    Spinner spinnerAgama,spinnerjurusan;
    private RadioGroup kelamin;
    private RadioButton gendernya;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView dateShow;
    private Button datePick;


    private Button save,batal;
    private ProgressDialog pDialog;
    String TAG = DaftarGuru.class.getSimpleName();
    int socketTimeout = 30000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    String item ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tingkat = (EditText)findViewById(R.id.etTingkat);
        program = (EditText)findViewById(R.id.etProgram);
        nama = (EditText)findViewById(R.id.etNama);
        kelamin = (RadioGroup) findViewById(R.id.radiogrupsex);
        nisn = (EditText)findViewById(R.id.etNisn);
        nis = (EditText)findViewById(R.id.etNis);
        no_ijazah = (EditText)findViewById(R.id.etNoijazah);
        no_skhun = (EditText)findViewById(R.id.etNo_skhun);
        no_un = (EditText)findViewById(R.id.etNo_un);
        nik = (EditText)findViewById(R.id.etNik);
        npsn = (EditText)findViewById(R.id.etNpsn);
        sekolah_asal = (EditText)findViewById(R.id.etSekolah_asal);
        tmpt_lahir = (EditText)findViewById(R.id.etTmptLahir);
        tgl_lahir = (EditText)findViewById(R.id.etTglLahir);
        spinnerAgama = (Spinner) findViewById(R.id.spinner);
        keb_khusus = (EditText)findViewById(R.id.etKeb_khusus);
        alamat = (EditText)findViewById(R.id.etAlamat);
        dusun = (EditText)findViewById(R.id.etDusun);
        kelurahan = (EditText)findViewById(R.id.etKelurahan);
        kecamatan = (EditText)findViewById(R.id.etKecamatan);
        kabupaten = (EditText)findViewById(R.id.etKabupaten);
        provinsi = (EditText)findViewById(R.id.etProvinsi);
        alat_transport = (EditText)findViewById(R.id.etAlat_transportasi);
        jns_tinggal = (EditText)findViewById(R.id.etJns_tinggal);
        tlp_rmh = (EditText)findViewById(R.id.etTlp_rmh);
        email = (EditText)findViewById(R.id.etEmail);
        no_hp = (EditText)findViewById(R.id.etNo_hp);
        no_kks = (EditText)findViewById(R.id.etNo_kks);
        no_kps = (EditText)findViewById(R.id.etNo_kps);
        usulan_pip = (EditText)findViewById(R.id.etUsulanPip);
        no_kip = (EditText)findViewById(R.id.etNo_kip);
        nama_kip = (EditText)findViewById(R.id.etNama_pip);
        alasan_tolak_kip = (EditText)findViewById(R.id.etTolak_pip);
        no_reg_akta = (EditText)findViewById(R.id.etNo_reg_akta);
        tinggi_bdn = (EditText)findViewById(R.id.etTinggi_bdn);
        berat_bdn = (EditText)findViewById(R.id.etBerat_bdn);
        jarak_sekolah = (EditText)findViewById(R.id.etJarak_skolah);
        waktu_tempuh_sekolah = (EditText)findViewById(R.id.etWaktu_tempuh_skolah);
        jml_sodara = (EditText)findViewById(R.id.etJml_sodara);
        jns_prestasi = (EditText)findViewById(R.id.etJns_prestasi);
        tingkat_prestasi = (EditText)findViewById(R.id.etTingkat_prestasi);
        nama_prestasi = (EditText)findViewById(R.id.etNama_prestasi);
        thn_dapat_prestasi = (EditText)findViewById(R.id.etThun_dapat_prestasi);
        sumber_prestasi = (EditText)findViewById(R.id.etPenyelenggara_prestasi);
        jns_beasiswa = (EditText)findViewById(R.id.etJns_beasiswa);
        sumber_beasiswa = (EditText)findViewById(R.id.etPenyelenggara_beasiswa);
        thn_mulai_beasiswa = (EditText)findViewById(R.id.etThun_mulai_beasiswa);
        tahun_selesai_beasiswa = (EditText)findViewById(R.id.etThun_selesai_beasiswa);
        jns_kesejahteraan = (EditText)findViewById(R.id.etJns_kesejahteraan);
        no_kesejahteraan = (EditText)findViewById(R.id.etNo_kesejahteraan);
        thn_mulai_kesejahteraan = (EditText)findViewById(R.id.etThun_mulai_kesejahteraan);
        thn_selesai_kesejahteraan = (EditText)findViewById(R.id.etThun_selesai_kesejahteraan);
        spinnerjurusan = (Spinner) findViewById(R.id.spinnerJurusan);

        save = (Button)findViewById(R.id.btn_save);
        save.setOnClickListener(this);


        List<String> categoriesAgama = new ArrayList<>();
        categoriesAgama.add("Islam");
        categoriesAgama.add("Kristen");
        categoriesAgama.add("Hindu");
        categoriesAgama.add("Budha");
        categoriesAgama.add("Khatolik");
        categoriesAgama.add("Protestan");
        List<String> categoriesJurusan = new ArrayList<>();
        categoriesJurusan.add("Teknik Distribusi Tenaga Listrik");
        categoriesJurusan.add("Teknik Instalasi Pemanfaatan Tenaga Listrik");
        categoriesJurusan.add("Teknik Otomotif Kendaraan Ringan");
        categoriesJurusan.add("Teknik Komputer Jaringan");
        categoriesJurusan.add("Teknik Otomotif Sepeda Motor");
        categoriesJurusan.add("multimedia");
        ArrayAdapter<String> dataAdapterjurusan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesJurusan);
        dataAdapterjurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesAgama);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgama.setAdapter(dataAdapter);
        spinnerjurusan.setAdapter(dataAdapterjurusan);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        //item = spinnerAgama.getSelectedItem().toString();

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateShow = (TextView) findViewById(R.id.etTglLahir);
        datePick = (Button) findViewById(R.id.pickDate);
        datePick.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onClick(View v) {
        if(v==datePick){
            showDateDialog();
        }
        int gender = kelamin.getCheckedRadioButtonId();
        gendernya = (RadioButton)findViewById(gender);
        String tingkatnya = tingkat.getText().toString();
        String programnya = program.getText().toString();
        String namanya = nama.getText().toString();
        String kelaminnya = gendernya.getText().toString();
        String nisnnya = nisn.getText().toString();
        String nisnya = nis.getText().toString();
        String no_ijazahnya = no_ijazah.getText().toString();
        String skhunnya = no_skhun.getText().toString();
        String nounnya = no_un.getText().toString();
        String niknya = nik.getText().toString();
        String npsnnya = npsn.getText().toString();
        String sekolahasalnya = sekolah_asal.getText().toString();
        String tmpt_lahirnya = tmpt_lahir.getText().toString();
        String tgl_lahirnya = tgl_lahir.getText().toString();
        String agamanya = spinnerAgama.getSelectedItem().toString();
        String kebkhususnya = keb_khusus.getText().toString();
        String alamatnya = alamat.getText().toString();
        String dusunnya = dusun.getText().toString();
        String kelurahannya = kelurahan.getText().toString();
        String kecamatannya = kecamatan.getText().toString();
        String kabupatennya = kabupaten.getText().toString();
        String provinsinya = provinsi.getText().toString();
        String transportnya = alat_transport.getText().toString();
        String jnstinggalnya = jns_tinggal.getText().toString();
        String tlprumahnya = tlp_rmh.getText().toString();
        String emailnya = email.getText().toString();
        String nohpnya = no_hp.getText().toString();
        String nokksnya = no_kks.getText().toString();
        String nokpsnya = no_kps.getText().toString();
        String usulanpipnya = usulan_pip.getText().toString();
        String nokipnya = no_kip.getText().toString();
        String namakipnya = nama_kip.getText().toString();
        String alasantolakkipnya = alasan_tolak_kip.getText().toString();
        String noregaktanya = no_reg_akta.getText().toString();
        String tinggibdnnya = tinggi_bdn.getText().toString();
        String beratbdnnya = berat_bdn.getText().toString();
        String jaraksekolahnya = jarak_sekolah.getText().toString();
        String wktutempuhnya = waktu_tempuh_sekolah.getText().toString();
        String jmlsodaranya = jml_sodara.getText().toString();
        String jnsprestasinya = jns_prestasi.getText().toString();
        String tingkatprestasinya = tingkat_prestasi.getText().toString();
        String namaprestasinya = nama_prestasi.getText().toString();
        String thundptprestasinya = thn_dapat_prestasi.getText().toString();
        String sumberprestasinya = sumber_prestasi.getText().toString();
        String jnsbeasiswanya = jns_beasiswa.getText().toString();
        String sumberbeasiswanya = sumber_beasiswa.getText().toString();
        String thnmulaibeasiswanya = thn_mulai_beasiswa.getText().toString();
        String thnselesaibeasiswanya = tahun_selesai_beasiswa.getText().toString();
        String jnskesejahteraannya = jns_kesejahteraan.getText().toString();
        String nokesejahteraannya = no_kesejahteraan.getText().toString();
        String thnmulaikesejahteraannya = thn_mulai_kesejahteraan.getText().toString();
        String thnselesaikesejahteraannya = thn_selesai_kesejahteraan.getText().toString();
        String jurusannya = spinnerjurusan.getSelectedItem().toString();
        if(v == save){
                Regis_siswa_baru(tingkatnya, programnya,namanya,kelaminnya,nisnnya,nisnya,no_ijazahnya,skhunnya
                        ,nounnya,niknya,npsnnya,sekolahasalnya,tmpt_lahirnya,tgl_lahirnya,agamanya,kebkhususnya,alamatnya,dusunnya
                ,kelurahannya,kecamatannya,kabupatennya,provinsinya,transportnya,jnstinggalnya,tlprumahnya,emailnya,nohpnya
                ,nokksnya,nokpsnya,usulanpipnya,nokipnya,namakipnya,alasantolakkipnya
                ,noregaktanya,tinggibdnnya,beratbdnnya,jaraksekolahnya,wktutempuhnya
                ,jmlsodaranya,jnsprestasinya,tingkatprestasinya,namaprestasinya,thundptprestasinya
                ,sumberprestasinya,jnsbeasiswanya,sumberbeasiswanya,thnmulaibeasiswanya,thnselesaibeasiswanya
                ,jnskesejahteraannya,nokesejahteraannya,thnmulaikesejahteraannya,thnselesaikesejahteraannya,jurusannya);
        }
    }

    private void Regis_siswa_baru(final String tingkat,final String program, final String nama,final String kelamin,final String nisn
            ,final String nis,final String no_ijazah,final String no_skhun,final String no_un,final String nik
            ,final String npsn,final String sekolah_asal,final String tmpt_lahir,final String tgl_lahir,final String agama
            ,final String keb_khusus,final String alamat,final String dusun,final String kelurahan,final String kecamatan,final String kabupaten
            ,final String provinsi,final String alat_transport,final String jns_tinggal,final String tlp_rumah,final String email,final String no_hp
            ,final String no_kks,final String no_kps,final String usulan_pip,final String no_kip,final String nama_kip,final String alasan_tolak_kip
            ,final String no_rek_akta,final String tinggi_bdn,final String berat_bdn,final String jarak_sekolah,final String waktu_tempuh_sekolah
            ,final String jml_sodara,final String jns_prestasi,final String tingkat_prestasi,final String nama_prestasi,final String thn_dpt_prestasi
            ,final String sumber_prestasi,final String jns_beasiswa,final String sumber_beasiswa,final String thn_mulai_beasiswa,final String thn_selesai_beasiswa
            ,final String jns_kesejahteraan,final String no_kesejahteraan,final String thn_mulai_kesejahteraan
            ,final String thn_selesai_kesejahteraan,final String jurusan_pilihan){

        //Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Please Wait.....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config_URL.URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if(!error){
                        String result = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
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
                params.put(Config_URL.TAG, "regis_siswa_baru");
                params.put("tingkat", tingkat);
                params.put("program", program);
                params.put("nama_lengkap", nama);
                params.put("kelamin", kelamin);
                params.put("nisn", nisn);
                params.put("nis", nis);
                params.put("no_ijazah", no_ijazah);
                params.put("no_skhun", no_skhun);
                params.put("no_un", no_un);
                params.put("nik", nik);
                params.put("npsn", npsn);
                params.put("sekolah_asal", sekolah_asal);
                params.put("tmpt_lahir", tmpt_lahir);
                params.put("tgl_lahir", tgl_lahir);
                params.put("agama", agama);
                params.put("keb_khusus", keb_khusus);
                params.put("alamat", alamat);
                params.put("dusun", dusun);
                params.put("kelurahan", kelurahan);
                params.put("kecamatan", kecamatan);
                params.put("kabupaten", kabupaten);
                params.put("provinsi", provinsi);
                params.put("transportasi", alat_transport);
                params.put("jns_tinggal", jns_tinggal);
                params.put("tlp_rmh", tlp_rumah);
                params.put("email", email);
                params.put("no_hp", no_hp);
                params.put("no_kks", no_kks);
                params.put("kps_phk", no_kps);
                params.put("usulan_layak_pip", usulan_pip);
                params.put("penerima_kip", nama_kip);
                params.put("no_kip", no_kip);
                params.put("alasan_tolak_pip", alasan_tolak_kip);
                params.put("no_reg_akte", no_rek_akta);
                params.put("tinggi_bdn", tinggi_bdn);
                params.put("berat_bdn", berat_bdn);
                params.put("jarak_sekolah", jarak_sekolah);
                params.put("wktu_tempuh_sekolah", waktu_tempuh_sekolah);
                params.put("jml_sodara_kandung", jml_sodara);
                params.put("jns_prestasi", jns_prestasi);
                params.put("tingkat_prestasi", tingkat_prestasi);
                params.put("nama_prestasi", nama_prestasi);
                params.put("thn_prestasi", thn_dpt_prestasi);
                params.put("sumber_prestasi", sumber_prestasi);
                params.put("jns_beasiswa", jns_beasiswa);
                params.put("sumber_beasiswa", sumber_beasiswa);
                params.put("thn_mulai_beasiswa", thn_mulai_beasiswa);
                params.put("thn_selesai_beasiswa", thn_selesai_beasiswa);
                params.put("jns_kesejahteraan", jns_kesejahteraan);
                params.put("no_kesejahteraan", no_kesejahteraan);
                params.put("thn_mulai_kesejahteraan", thn_mulai_kesejahteraan);
                params.put("thn_selesai_kesejahteraan", thn_selesai_kesejahteraan);
                params.put("jurusan_pilihan", jurusan_pilihan);

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
