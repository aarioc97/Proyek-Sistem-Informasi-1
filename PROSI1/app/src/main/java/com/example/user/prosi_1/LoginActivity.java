package com.example.user.prosi_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    EditText editTextEmail;
    EditText editTextPassword;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonLogin;
    SignInButton signInButton;
    ProgressBar pbLogin;

    FirebaseAuth firebaseAuth;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, Home.class));
            finish();
        }

        setContentView(R.layout.activity_login_page);

        this.editTextEmail = this.findViewById(R.id.et_email_login);
        this.editTextPassword = this.findViewById(R.id.et_password_login);
        this.textInputLayoutEmail = this.findViewById(R.id.textInputLayoutEmail);
        this.textInputLayoutPassword = this.findViewById(R.id.textInputLayoutPassword);
        this.buttonLogin = this.findViewById(R.id.btn_login_to_app);
//        this.signInButton = this.findViewById(R.id.btn_signup_google);
        this.pbLogin = this.findViewById(R.id.pb_login);

        this.firebaseAuth = FirebaseAuth.getInstance();

        this.pbLogin.setVisibility(View.GONE);

        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    textInputLayoutEmail.setError("Please enter valid email!");
                } else if (password.isEmpty()) {
                    textInputLayoutPassword.setError("Please enter valid password!");
                } else if (password.length() < 5) {
                    textInputLayoutPassword.setError("Password is to short!");
                } else {
                    textInputLayoutPassword.setError(null);
                    pbLogin.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    pbLogin.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            editTextPassword.setError("Password is too short!");
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Intent intent = new Intent(LoginActivity.this, Home.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}