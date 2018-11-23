package com.example.user.prosi_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AccountSettings extends AppCompatActivity {

    EditText etUname, etEmail;
    Button signOut;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        this.signOut = this.findViewById(R.id.btn_signout);
        this.etUname = this.findViewById(R.id.et_uname_settings);
        this.etEmail = this.findViewById(R.id.et_email_settings);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.etEmail.setText(this.firebaseAuth.getCurrentUser().getEmail());

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            String userId = firebaseAuth.getCurrentUser().getUid();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                etUname.setText(dataSnapshot.child("users").child(userId).child("userName").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AccountSettings.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });

        this.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AccountSettings.this);
                builder.setMessage("Apakah Anda yakin ingin Sign Out?");
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
                        firebaseAuth.signOut();
                        startActivity(new Intent(AccountSettings.this, SignUpLogin.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    Intent intent = new Intent(AccountSettings.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah data diri sudah benar?");
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
                String userId = firebaseAuth.getCurrentUser().getUid();
                Map<String, Object> userData = new HashMap<>();
                userData.put("userName", etUname.getText().toString());

                mDatabase.child("users").child(userId).updateChildren(userData);

                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
