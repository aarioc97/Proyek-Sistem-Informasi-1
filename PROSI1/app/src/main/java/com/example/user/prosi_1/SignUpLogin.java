package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpLogin extends AppCompatActivity implements View.OnClickListener{

    Button signUp, logins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        signUp = this.findViewById(R.id.btn_signup);
        logins = this.findViewById(R.id.btn_login);

        logins.setOnClickListener(this);
        signUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == logins.getId()){
            Intent intent1 = new Intent(this, LoginPage.class);
            startActivity(intent1);
        }
        if(view.getId() == signUp.getId()){
            Intent intent2 = new Intent(this, SignUp.class);
            startActivity(intent2);
        }
    }
}
