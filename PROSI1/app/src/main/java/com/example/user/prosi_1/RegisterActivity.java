package com.example.user.prosi_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaration EditTexts
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonRegister;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    StorageReference storageReference;

    FirebaseAuth firebaseAuth;
    String userName;
    String email;
    String password;

    //Declaration SqliteHelper
//    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.buttonRegister = this.findViewById(R.id.btn_daftar);
        this.editTextUserName = this.findViewById(R.id.et_username_daftar);
        this.editTextEmail = this.findViewById(R.id.et_email_daftar);
        this.editTextPassword = this.findViewById(R.id.et_password_daftar);
        this.textInputLayoutEmail = this.findViewById(R.id.textInputLayoutHP);
        this.textInputLayoutPassword = this.findViewById(R.id.textInputLayoutPassword);
        this.textInputLayoutUserName = this.findViewById(R.id.textInputLayoutEmail);

        this.firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        this.buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.userName = this.editTextUserName.getText().toString();
        this.email = this.editTextEmail.getText().toString();
        this.password = this.editTextPassword.getText().toString();

        if (userName.isEmpty()) {
            this.textInputLayoutUserName.setError("Please enter valid username!");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.textInputLayoutEmail.setError("Please enter valid email!");
        } else if (password.isEmpty()) {
            this.textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
//                        String userId = firebaseAuth.getCurrentUser().getUid();
//                        User user = new User(userId, userName, email, password);
//                        mDatabase.child("users").child(userId).setValue(user);

                        Toast.makeText(getApplicationContext(),"bisa buat user",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"E-mail or password is wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            startActivity(new Intent(this, SecondPhaseRegister.class));
            finish();
        }

    }
}