package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class PilihRole extends AppCompatActivity implements View.OnClickListener{

    Button traveller, requester;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_role);
        traveller = findViewById(R.id.btn_traveller);
        requester = findViewById(R.id.btn_requester);

        traveller.setOnClickListener(this);
        requester.setOnClickListener(this);

        this.firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == traveller.getId()){
            String userId = firebaseAuth.getCurrentUser().getUid();

            Map<String, Object> userData = new HashMap<>();
            userData.put("role", "traveller");

            mDatabase.child("users").child(userId).updateChildren(userData);

            startActivity(new Intent(this, Home.class));
            finish();
//            final Intent intent = new Intent(this, SecondPhaseLogin.class);
//            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Email berisi kode verifikasi untuk pendaftaran telah dikirim.");
//            builder.setCancelable(true);
//            builder.setPositiveButton("Lanjutkan login", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    startActivity(intent);
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
        }
        else if(view.getId() == requester.getId()){
            String userId = firebaseAuth.getCurrentUser().getUid();

            Map<String, Object> userData = new HashMap<>();
            userData.put("role", "requester");

            mDatabase.child("users").child(userId).updateChildren(userData);

            startActivity(new Intent(this, Home.class));
            finish();
//            final Intent intent = new Intent(this, SecondPhaseRegister.class);
//            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Email berisi kode verifikasi untuk pendaftaran telah dikirim.");
//            builder.setCancelable(true);
//            builder.setPositiveButton("Lanjutkan login", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    startActivity(intent);
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
        }
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda akan membatalkan pendaftaran?");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
