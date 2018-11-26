package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputRekening extends AppCompatActivity implements View.OnClickListener {

    Spinner spNamaBank, spKota;
    EditText etNamaLengkap, etNoRek;
    Button btnSubmitRek;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    String isiNamaBank[] = {"BRI", "BNI", "Mandiri", "BCA", "PermataBank", "OCBC-NISP", "Danamon"};
    String isiKota[] = {"Bandung Kota", "Bandung Kab.", "Jepara", "Jakarta", "Depok", "Balikpapan", "Medan", "Gorontalo", "Denpasar", "Manokwari"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rekening);
        this.spNamaBank = this.findViewById(R.id.spinner_nama_bank);
        this.spKota = this.findViewById(R.id.spinner_kota_bank);
        this.etNamaLengkap = this.findViewById(R.id.et_nama_lengkap);
        this.etNoRek = this.findViewById(R.id.et_no_rek);
        this.btnSubmitRek = this.findViewById(R.id.btn_submit_rek);
        this.firebaseAuth = FirebaseAuth.getInstance();

        ArrayAdapter<String> adapterNamaBank = new ArrayAdapter<String>(this, R.layout.activity_input_rekening, this.isiNamaBank);
//        ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(this, R.layout.activity_input_rekening, isiKota);

        this.spNamaBank.setAdapter(adapterNamaBank);
//
//        adapterKota.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        this.spKota.setAdapter(adapterKota);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.btnSubmitRek.getId()){
            String userId = firebaseAuth.getCurrentUser().getUid();

            Map<String, Object> userData = new HashMap<>();
            userData.put("namaLengkap", this.etNamaLengkap.getText().toString());
            userData.put("noRek", this.etNoRek.getText().toString());

            mDatabase.child("users").child(userId).updateChildren(userData);

            startActivity(new Intent(this, Home.class));
            finish();
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
