package com.example.user.prosi_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button login_to_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_to_app = this.findViewById(R.id.btn_login_to_app);


    }

    @Override
    public void onClick(View view) {
        String restURL = "flynbuy.com";

    }
}
