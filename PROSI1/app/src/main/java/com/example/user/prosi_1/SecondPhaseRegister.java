package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//masih gagal verifikasi e-mail.

public class SecondPhaseRegister extends AppCompatActivity implements View.OnClickListener{

    Button submit;
    TextView verification;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_phase_register);
        submit = findViewById(R.id.btn_submit);
        this.verification = this.findViewById(R.id.verification);
//        Log.d("onCreate", "oncreate jalan sampe sini");
//        this.user = this.firebaseAuth.getCurrentUser();
//        submit.setOnClickListener(this);
//        verification.setText("verificated: " + String.valueOf(this.user.isEmailVerified()));
    }

    @Override
    public void onClick(View view) {
//        if(view.getId() == submit.getId()){
//            if(this.user.isEmailVerified() == true){
//                Intent intent = new Intent(this, Home.class);
//                startActivity(intent);
//            }
//            else{
//                Toast.makeText(this, "Verificate your e-mail first!", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

//    @Override
//    public void onBackPressed(){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Apakah Anda akan membatalkan pendaftaran?");
//        builder.setCancelable(true);
//        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
//        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}