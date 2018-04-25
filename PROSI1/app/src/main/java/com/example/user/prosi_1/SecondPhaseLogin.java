package com.example.user.prosi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondPhaseLogin extends AppCompatActivity implements View.OnClickListener{

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_phase_login);
        submit = findViewById(R.id.btn_submit);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == submit.getId()){
            Intent intent = new Intent(this, InputRekening.class);
            startActivity(intent);
        }
    }
}
