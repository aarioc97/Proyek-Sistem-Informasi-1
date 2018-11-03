package com.example.user.prosi_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import basepackage.BarangPostRequest;

public class PostBarang extends AppCompatActivity implements View.OnClickListener{

    EditText nama, kuantitas, deskripsi, kotaAsal, kotaTujuan, harga, berat, panjang, lebar;
    TextView adminFee, ongkirFee, totalFee;
    Button calculate, post;
    ImageView iv_barang;
    CheckBox cb_status;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    StorageReference ref;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    //Firebase
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();;

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
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        if(v == post){
            int beratBarang = Integer.parseInt(berat.getText().toString());
            String deskripsiBarang = deskripsi.getText().toString();
            StorageReference gambarBarang = ref;
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

            if(filePath != null)
            {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                this.ref = storageReference.child("barang/"+ UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(PostBarang.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(PostBarang.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+(int)progress+"%");
                            }
                        });
            }


            BarangPostRequest barang = new BarangPostRequest(beratBarang,deskripsiBarang,gambarBarang,hargaBarang,lebarBarang,namaBarang,
                    panjangBarang,statusBarang,kuantitasBarang);

            mDatabase.child("barang").setValue(barang);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv_barang.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
