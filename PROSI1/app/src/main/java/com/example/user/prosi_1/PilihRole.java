package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PilihRole extends AppCompatActivity implements View.OnClickListener{

    Button traveller, requester;

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
            final Intent intent = new Intent(this, SecondPhaseLogin.class);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Email berisi kode verifikasi untuk pendaftaran telah dikirim.");
            builder.setCancelable(true);
            builder.setPositiveButton("Lanjutkan login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if(view.getId() == requester.getId()){
            final Intent intent = new Intent(this, SecondPhaseLogin.class);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Email berisi kode verifikasi untuk pendaftaran telah dikirim.");
            builder.setCancelable(true);
            builder.setPositiveButton("Lanjutkan login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda akan membatalkan pendaftaran?");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
