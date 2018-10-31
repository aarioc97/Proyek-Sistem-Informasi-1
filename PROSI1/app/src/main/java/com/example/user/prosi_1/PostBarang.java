package com.example.user.prosi_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PostBarang extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    EditText namaBarang, kuantitas, deskripsi, kotaAsal, kotaTujuan, harga;
    TextView adminFee, ongkirFee, totalFee;
    Button calculate, post;
    ImageView iv_barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_barang);

        namaBarang = this.findViewById(R.id.et_nama_barang);
        kuantitas = this.findViewById(R.id.et_kuantitas);
        deskripsi = this.findViewById(R.id.et_deskripsi);
        kotaAsal = this.findViewById(R.id.kota_asal);
        kotaTujuan = this.findViewById(R.id.kota_tujuan);
        harga = this.findViewById(R.id.et_harga);

        adminFee = this.findViewById(R.id.tv_admin_fee);
        ongkirFee = this.findViewById(R.id.tv_ongkir_fee);
        totalFee = this.findViewById(R.id.tv_total_fee);

        calculate = this.findViewById(R.id.btn_calculate);
        post = this.findViewById(R.id.btn_post);

        iv_barang = this.findViewById(R.id.iv_canvas_req);

        calculate.setOnClickListener(this);
        iv_barang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == calculate){
            adminFee.setText(harga.getText());
            ongkirFee.setText("10000");
            totalFee.setText("");
        }
        if (v == iv_barang){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
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
