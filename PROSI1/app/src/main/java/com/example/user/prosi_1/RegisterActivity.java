package com.example.user.prosi_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    //Declaration SqliteHelper
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        databaseHelper = new DatabaseHelper(this);
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!databaseHelper.isEmailExists(Email)) {

                        //Email does not exist now add new user to database
                        databaseHelper.addUser(new User(null, UserName, Email, Password));
                        Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        //new Handler().postDelayed(new Runnable() {
                        //    @Override
                        //    public void run() {
                        //        finish();
                        //    }
                        //}, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(buttonRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.et_hp_daftar);
        editTextPassword = (EditText) findViewById(R.id.et_password_daftar);
        editTextUserName = (EditText) findViewById(R.id.et_username_daftar);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutHP);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        buttonRegister = (Button) findViewById(R.id.btn_daftar);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }


        return valid;
    }
}