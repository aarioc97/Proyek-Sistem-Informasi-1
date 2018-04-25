package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PilihRole extends AppCompatActivity implements View.OnClickListener{

    ImageButton traveller, requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_role);
        traveller = findViewById(R.id.btn_traveller);
        requester = findViewById(R.id.btn_requester);

        traveller.setOnClickListener(this);
        requester.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == traveller.getId()){
            Intent intent = new Intent(this, SecondPhaseLogin.class);
            startActivity(intent);
        }
        else if(view.getId() == requester.getId()){
            Intent intent = new Intent(this, SecondPhaseLogin.class);
            startActivity(intent);
        }
    }
}
