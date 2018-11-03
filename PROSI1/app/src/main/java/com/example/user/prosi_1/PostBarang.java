package com.example.user.prosi_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.FirebaseAppHelper;

import basepackage.BarangPostRequest;

public class PostBarang extends AppCompatActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE = 1;

    EditText nama, kuantitas, deskripsi, kotaAsal, kotaTujuan, harga, berat, panjang, lebar;
    TextView adminFee, ongkirFee, totalFee;
    Button calculate, post;
    ImageView iv_barang;
    CheckBox cb_status;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_barang);

        nama = this.findViewById(R.id.et_nama_barang);
        kuantitas = this.findViewById(R.id.et_kuantitas);
        deskripsi = this.findViewById(R.id.et_deskripsi);
        kotaAsal = this.findViewById(R.id.kota_asal);
        kotaTujuan = this.findViewById(R.id.kota_tujuan);
        harga = this.findViewById(R.id.et_harga);
        berat = this.findViewById(R.id.et_berat);
        panjang = this.findViewById(R.id.et_panjang);
        lebar = this.findViewById(R.id.et_lebar);

        adminFee = this.findViewById(R.id.tv_admin_fee);
        ongkirFee = this.findViewById(R.id.tv_ongkir_fee);
        totalFee = this.findViewById(R.id.tv_total_fee);

        calculate = this.findViewById(R.id.btn_calculate);
        post = this.findViewById(R.id.btn_post);

        iv_barang = this.findViewById(R.id.iv_canvas_req);

        cb_status = this.findViewById(R.id.checkBox2);

        calculate.setOnClickListener(this);
        iv_barang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == calculate){
            int hargaCalculated = Integer.parseInt(harga.getText().toString());
            int adminFeeCalculated = hargaCalculated*25/100;
            int ongkirFeeCalculated = hargaCalculated/10;

            int totalFeeCalculated = hargaCalculated + adminFeeCalculated + ongkirFeeCalculated;

            adminFee.setText(adminFeeCalculated);
            ongkirFee.setText(ongkirFeeCalculated);
            totalFee.setText(totalFeeCalculated);
        }
        if (v == iv_barang){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
        }
        if(v == post){
            int beratBarang = Integer.parseInt(berat.getText().toString());
            String deskripsiBarang = deskripsi.getText().toString();
            Image gambarBarang = null;
            int hargaBarang = Integer.parseInt(harga.getText().toString());
            int lebarBarang = Integer.parseInt(lebar.getText().toString());
            String namaBarang = nama.getText().toString();
            int panjangBarang = Integer.parseInt(panjang.getText().toString());
            String statusBarang="";

            if(cb_status.isChecked()){
                statusBarang = "Ya";
            }
            else if(!cb_status.isChecked()){
                statusBarang = "Tidak";
            }

            int kuantitasBarang = Integer.parseInt(kuantitas.getText().toString());


            BarangPostRequest barang = new BarangPostRequest(beratBarang,deskripsiBarang,gambarBarang,hargaBarang,lebarBarang,namaBarang,
                    panjangBarang,statusBarang,kuantitasBarang);

            mDatabase.child("barang").setValue(barang);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            iv_barang.setImageURI(selectedImage);
        }
    }
}
