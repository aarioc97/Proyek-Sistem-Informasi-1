package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        daftar = this.findViewById(R.id.btn_daftar);

        daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == daftar.getId()){
            Intent intent1 = new Intent(this, PilihRole.class);
            startActivity(intent1);
        }
    }
}
