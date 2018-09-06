package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class InputRekening extends AppCompatActivity implements View.OnClickListener{

    protected Spinner namaBank, kotaBank;
    protected List<String> listNama, listKota;
    protected Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rekening);
        this.namaBank = this.findViewById(R.id.spinner_nama_bank);
        this.kotaBank = this.findViewById(R.id.spinner_kota_bank);
        this.submit = this.findViewById(R.id.btn_submit_rek);

        this.listNama = new ArrayList<String>();
        this.listKota = new ArrayList<String>();
        this.submit.setOnClickListener(this);

        listNama.add("BRI");
        listNama.add("BNI");
        listNama.add("Mandiri");
        listNama.add("Permata Bank");
        listNama.add("OCBC NISP");

        listKota.add("Bandung");
        listKota.add("Jakarta");
        listKota.add("Pontianak");
        listKota.add("Medan");
        listKota.add("Gorontalo");

        ArrayAdapter<String> adapterNama = new ArrayAdapter<String>(this, R.layout.activity_input_rekening, listNama);
        ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(this, R.layout.activity_input_rekening, listKota);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.submit.getId()){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }
}
