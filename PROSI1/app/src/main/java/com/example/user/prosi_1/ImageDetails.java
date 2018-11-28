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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ImageDetails extends AppCompatActivity implements View.OnClickListener {

    TextView tvImageName, tvQuantity, tvBerat, tvPanjang, tvLebar, tvItemStats, tvDesc, tvFrom, tvTo, tvPrice;
    ImageView ivUpload;
    Button btnAcc;
    public int pos;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

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
        this.pos = this.getIntent().getIntExtra("pos", 0);

        if(this.pos == 0){
            this.tvImageName.setText("Sepatu");
            this.ivUpload.setImageResource(R.drawable.prosi1);
            this.tvQuantity.setText("1");
            this.tvBerat.setText("0,5 Kg");
            this.tvPanjang.setText("40 cm");
            this.tvLebar.setText("20 cm");
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
            this.tvItemStats.setVisibility(View.VISIBLE);
            this.tvDesc.setText("Tolong beli parfum CK ini, yang tipe X.");
            this.tvFrom.setText("Jepang");
            this.tvTo.setText("Karawang");
            this.tvPrice.setText("Rp 8950000");
        }
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
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    trip = dataSnapshot.child("users").child(userId).child("trip").getValue().toString();
                    Toast.makeText(ImageDetails.this, trip, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ImageDetails.this, "Anda harus menjadi Traveller (Post Trip) untuk menerima Request!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
