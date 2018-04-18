package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    Button logins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        logins = this.findViewById(R.id.btn_login);

        logins.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == logins){
            Intent intent= new Intent(this, pilihRole.class);
            startActivity(intent);
        }
    }
}
