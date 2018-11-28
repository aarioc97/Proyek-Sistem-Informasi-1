package com.example.user.prosi_1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.prosi_1.home_tabs.TabHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ImageDetails extends AppCompatActivity implements View.OnClickListener {

    TextView tvImageName, tvQuantity, tvBerat, tvPanjang, tvLebar, tvItemStats, tvDesc, tvFrom, tvTo, tvPrice;
    ImageView ivUpload;
    Button btnAcc;
    public int pos;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    String idBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        this.tvImageName = this.findViewById(R.id.text_view_name);
        this.ivUpload = this.findViewById(R.id.image_view_upload);
        this.tvQuantity = this.findViewById(R.id.tv_quantity);
        this.tvBerat = this.findViewById(R.id.tv_berat);
        this.tvPanjang = this.findViewById(R.id.tv_panjang);
        this.tvLebar = this.findViewById(R.id.tv_lebar);
        this.tvItemStats = this.findViewById(R.id.tv_item_stats);
        this.tvDesc = this.findViewById(R.id.tv_desc);
        this.tvFrom = this.findViewById(R.id.tv_from);
        this.tvTo = this.findViewById(R.id.tv_to);
        this.tvPrice = this.findViewById(R.id.tv_price);
        this.btnAcc = this.findViewById(R.id.btn_acc_req);
        this.idBarang = "";
        this.pos = this.getIntent().getIntExtra("pos", 0);

        if(this.pos == 0){
            this.tvImageName.setText("Sepatu");
            this.ivUpload.setImageResource(R.drawable.prosi1);
            this.tvQuantity.setText("1");
            this.tvBerat.setText("0,5 Kg");
            this.tvPanjang.setText("40 cm");
            this.tvLebar.setText("20 cm");
            this.idBarang = "07b073c8-319b-4adb-8c9d-546e09ae4be1";
            this.tvItemStats.setVisibility(View.GONE);
            this.tvDesc.setText("Tolong belikan sepasang sepatu berwarna merah ukuran 40 ya.");
            this.tvFrom.setText("Jepang");
            this.tvTo.setText("Pekanbaru");
            this.tvPrice.setText("Rp 1500000");
        } else if(this.pos == 1){
            this.tvImageName.setText("Mouse");
            this.ivUpload.setImageResource(R.drawable.prosi2);
            this.tvQuantity.setText("1");
            this.tvBerat.setText("0,1 Kg");
            this.tvPanjang.setText("8 cm");
            this.tvLebar.setText("5 cm");
            this.idBarang = "485d4c84-fd1f-4015-a981-eaab6f3b1402";
            this.tvItemStats.setVisibility(View.VISIBLE);
            this.tvDesc.setText("Tolong belikan sebuah mouse berwarna putih.");
            this.tvFrom.setText("Australia");
            this.tvTo.setText("Surabaya");
            this.tvPrice.setText("Rp 43000");
        } else if(this.pos == 2) {
            this.tvImageName.setText("Premium Pencil Case");
            this.ivUpload.setImageResource(R.drawable.prosi3);
            this.tvQuantity.setText("2");
            this.tvBerat.setText("0,05 Kg");
            this.tvPanjang.setText("10 cm");
            this.tvLebar.setText("2 cm");
            this.idBarang = "807cd952-7256-4417-8ead-303033171285";
            this.tvItemStats.setVisibility(View.VISIBLE);
            this.tvDesc.setText("Tolong belikan tempat pensil premium ini dari Seoul, Korea Selatan.");
            this.tvFrom.setText("Korea Selatan");
            this.tvTo.setText("Bandung");
            this.tvPrice.setText("Rp 775000");
        } else if(this.pos == 3) {
            this.tvImageName.setText("Tas Mewah");
            this.ivUpload.setImageResource(R.drawable.prosi4);
            this.tvQuantity.setText("3");
            this.tvBerat.setText("0,7 Kg");
            this.tvPanjang.setText("30 cm");
            this.tvLebar.setText("15 cm");
            this.idBarang = "32a205ae-e635-48d5-a17b-774c5c0a1b2f";
            this.tvItemStats.setVisibility(View.GONE);
            this.tvDesc.setText("Titip beli tas mewah ini dari Jepang ya.");
            this.tvFrom.setText("Jepang");
            this.tvTo.setText("Jepara");
            this.tvPrice.setText("Rp 3750000");
        } else if(this.pos == 4) {
            this.tvImageName.setText("Kaos Batman");
            this.ivUpload.setImageResource(R.drawable.prosi5);
            this.tvQuantity.setText("5");
            this.tvBerat.setText("0,02Kg");
            this.tvPanjang.setText("55 cm");
            this.tvLebar.setText("37 cm");
            this.idBarang = "cc036ba0-f119-477e-ad34-88d791177630";
            this.tvItemStats.setVisibility(View.GONE);
            this.tvDesc.setText("Titip beli kaos Batman keren ini, 5 aja.");
            this.tvFrom.setText("Singapura");
            this.tvTo.setText("Jakarta");
            this.tvPrice.setText("Rp 5340000");
        } else if(this.pos == 5) {
            this.tvImageName.setText("Tas Laptop Besar");
            this.ivUpload.setImageResource(R.drawable.prosi6);
            this.tvQuantity.setText("1");
            this.tvBerat.setText("0,6 Kg");
            this.tvPanjang.setText("50 cm");
            this.tvLebar.setText("40 cm");
            this.idBarang = "a41b38f7-463f-499f-ac4a-25541749f4b6";
            this.tvItemStats.setVisibility(View.GONE);
            this.tvDesc.setText("Titip tas laptop besar dari Thailand ya.");
            this.tvFrom.setText("Thailand");
            this.tvTo.setText("Subang");
            this.tvPrice.setText("Rp 420000");
        } else if(this.pos == 6) {
            this.tvImageName.setText("Parfum Calvin Klein");
            this.ivUpload.setImageResource(R.drawable.prosi7);
            this.tvQuantity.setText("1");
            this.tvBerat.setText("0,6 Kg");
            this.tvPanjang.setText("15 cm");
            this.tvLebar.setText("8 cm");
            this.idBarang = "4bbd6399-31a8-43bc-99e5-3f3ee2adde20";
            this.tvItemStats.setVisibility(View.VISIBLE);
            this.tvDesc.setText("Tolong beli parfum CK ini, yang tipe X.");
            this.tvFrom.setText("Jepang");
            this.tvTo.setText("Karawang");
            this.tvPrice.setText("Rp 8950000");
        }
        this.btnAcc.setOnClickListener(this);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.btnAcc.getId()){
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                String userId = firebaseAuth.getCurrentUser().getUid();
                String trip = "no trip";
                String idActTraveller = "";
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                        trip = dataSnapshot.child("users").child(userId).child("trip").getValue().toString();
                        if(!trip.equalsIgnoreCase(tvFrom.getText().toString())){
                            Toast.makeText(ImageDetails.this, "Harus menerima Request dalam satu negara!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ImageDetails.this, "Request diterima!", Toast.LENGTH_SHORT).show();
                            idActTraveller = dataSnapshot.child("users").child(userId).child("id_act_traveller").getValue().toString();
                            Map<String, Object> actTravellerData = new HashMap<>();
                            actTravellerData.put("request_accepted", idBarang);
                            mDatabase.child("act_traveller").child(idActTraveller).updateChildren(actTravellerData);
                            startActivity(new Intent(ImageDetails.this, Home.class));
                            finish();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ImageDetails.this, "Anda harus menjadi Traveller (Post Trip) untuk menerima Request!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
}
