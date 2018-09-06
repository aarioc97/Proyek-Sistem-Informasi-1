package com.example.user.prosi_1;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;

public class SignUpLogin extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

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
            Intent intent1 = new Intent(this, LoginActivity.class);
            startActivity(intent1);
        }
        if(view.getId() == signUp.getId()){
            Intent intent2 = new Intent(this, SignUp.class);
            startActivity(intent2);
        }
    }

    boolean backTwice;
    @Override
    public void onBackPressed(){
        if(backTwice==true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(this, "Tekan 'kembali' sekali lagi untuk keluar.", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backTwice = false;
            }
        }, 3000);
        backTwice = true;
    }
}
