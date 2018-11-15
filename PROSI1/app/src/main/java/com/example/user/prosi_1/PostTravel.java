package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import basepackage.AksiTraveller;

public class PostTravel extends AppCompatActivity implements View.OnClickListener{

    EditText negara, tanggal1, tanggal2;
    Button btn_post;

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

    }

    @Override
    public void onClick(View v) {
        if(v == btn_post){
            AksiTraveller aksi = new AksiTraveller(negara.getText().toString(), tanggal1.getText().toString());

            mDatabase.child("act_traveller/" + UUID.randomUUID().toString()).setValue(aksi);

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);

        }
    }
}
