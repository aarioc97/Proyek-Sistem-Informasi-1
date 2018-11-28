package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import basepackage.AksiTraveller;

public class PostTravel extends AppCompatActivity implements View.OnClickListener{

    EditText negara, tanggal1, tanggal2;
    Button btn_post;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_travel);

        negara = this.findViewById(R.id.et_negara);
        tanggal1 = this.findViewById(R.id.et_tanggal1);
        tanggal2 = this.findViewById(R.id.et_tanggal2);

        btn_post = this.findViewById(R.id.btn_post);
        btn_post.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v == btn_post){
            AksiTraveller aksi = new AksiTraveller(negara.getText().toString(), tanggal1.getText().toString());

            String idActTraveller = UUID.randomUUID().toString();

            mDatabase.child("act_traveller/" + idActTraveller).setValue(aksi);

            String userId = firebaseAuth.getCurrentUser().getUid();
            Map<String, Object> userData = new HashMap<>();
            userData.put("trip", negara.getText().toString());
            userData.put("id_act_traveller", idActTraveller);

            mDatabase.child("users").child(userId).updateChildren(userData);

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);

        }
    }
}
