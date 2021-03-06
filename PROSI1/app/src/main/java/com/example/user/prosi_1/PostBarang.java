package com.example.user.prosi_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference();

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    int adminFeeCalculated = 0;
    int kuantitasBarang = 0;

    //Firebase
    StorageReference storageReference;

    public ArrayList<String> arrayIdBarang = new ArrayList<String>();

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

        cb_status = this.findViewById(R.id.check_box);

        calculate.setOnClickListener(this);
        iv_barang.setOnClickListener(this);
        post.setOnClickListener(this);

        storageReference = FirebaseStorage.getInstance().getReference();

        mDatabase.child("fotoBarang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    arrayIdBarang.add(postSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == calculate){
            this.kuantitasBarang = Integer.parseInt(kuantitas.getText().toString());

            int hargaCalculated = Integer.parseInt(harga.getText().toString()) * kuantitasBarang;
            this.adminFeeCalculated = hargaCalculated*25/100;
            int ongkirFeeCalculated = hargaCalculated/10;

            int totalFeeCalculated = hargaCalculated + this.adminFeeCalculated + ongkirFeeCalculated;

            adminFee.setText(""+adminFeeCalculated);
            ongkirFee.setText(""+ongkirFeeCalculated);
            totalFee.setText(""+totalFeeCalculated);
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
            int hargaBarang = Integer.parseInt(harga.getText().toString()) * kuantitasBarang;
            int lebarBarang = Integer.parseInt(lebar.getText().toString());
            String namaBarang = nama.getText().toString();
            int panjangBarang = Integer.parseInt(panjang.getText().toString());
            String statusBarang= "";
            final String idBarang = UUID.randomUUID().toString();

            if(cb_status.isChecked()){
                statusBarang = "Ya";
            }
            else if(!cb_status.isChecked()){
                statusBarang = "Tidak";
            }

            this.kuantitasBarang = Integer.parseInt(kuantitas.getText().toString());

//            String uuid = UUID.randomUUID().toString();

            this.ref = storageReference.child("fotoBarang/"+ idBarang);
            ref.putFile(filePath);

            String gambar = storageReference.child("fotoBarang/"+ idBarang+".jpeg").getPath();

            BarangPostRequest barang = new BarangPostRequest(idBarang, auth.getCurrentUser().getUid(), beratBarang, deskripsiBarang, gambar, hargaBarang, lebarBarang,
                    namaBarang, panjangBarang,statusBarang,kuantitasBarang, adminFeeCalculated);

            mDatabase.child("barang").child(idBarang).setValue(barang);

            Map<String, Object> postReqData = new HashMap<>();
            postReqData.put("statusPost", "belum disetujui");
            postReqData.put("waktuPostDisetujui", "null");

            mDatabase.child("post_rq").child(idBarang).updateChildren(postReqData);

            Toast.makeText(this, "Barang berhasil dipost!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
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
