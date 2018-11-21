package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//masih gagal verifikasi e-mail.

public class SecondPhaseRegister extends AppCompatActivity implements View.OnClickListener{

    Button submit, sendEmail;
    TextView verification;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_phase_register);
        this.submit = this.findViewById(R.id.btn_submit);
        this.verification = this.findViewById(R.id.verification);
        this.sendEmail = this.findViewById(R.id.btn_send_ver);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.submit.setOnClickListener(this);
        this.sendEmail.setOnClickListener(this);
        verification.setText("verificated: " + String.valueOf(this.firebaseAuth.getCurrentUser().isEmailVerified()));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == submit.getId()){
            if(this.firebaseAuth.getCurrentUser().isEmailVerified() == true){
                verification.setText("verificated: " + String.valueOf(this.firebaseAuth.getCurrentUser().isEmailVerified()));
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            }
            else{
                verification.setText("verificated: " + String.valueOf(this.firebaseAuth.getCurrentUser().isEmailVerified()));
                Toast.makeText(this, "Verificate your e-mail first!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId() == sendEmail.getId()){
            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Verification e-mail has been sent!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Failed to sent verification e-mail!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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