package com.example.user.prosi_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity{

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

    FirebaseAuth firebaseAuth;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_UP = 9001;

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

        this.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = editTextUserName.getText().toString();
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();

                if (userName.isEmpty()) {
                    textInputLayoutUserName.setError("Please enter valid username!");
                } else {
                    if (userName.length() > 5) {
                        textInputLayoutUserName.setError(null);
                    } else {
                        textInputLayoutUserName.setError("Username is to short!");
                    }
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    textInputLayoutEmail.setError("Please enter valid email!");
                } else {
                    textInputLayoutEmail.setError(null);
                }

                if (password.isEmpty()) {
                    textInputLayoutPassword.setError("Please enter valid password!");
                } else {
                    if (password.length() > 5) {
                        textInputLayoutPassword.setError(null);
                    } else {
                        textInputLayoutPassword.setError("Password is to short!");
                    }
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(),Home.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"E-mail or password is wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}