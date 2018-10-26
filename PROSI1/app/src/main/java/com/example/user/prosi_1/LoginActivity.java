package com.example.user.prosi_1;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.prosi_1.R;
import com.example.user.prosi_1.InputValidation;
import com.example.user.prosi_1.DatabaseHelper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity{

    EditText editTextEmail;
    EditText editTextPassword;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;
    SignInButton signInButton;

    private static final int RC_SIGN_IN = 9001;

    FirebaseAuth firebaseAuth;
    GoogleApiClient mGoogleApiClient;

    //Declaration SqliteHelper
//    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        this.editTextEmail = this.findViewById(R.id.et_email_login);
        this.editTextPassword = this.findViewById(R.id.et_password_login);
        this.textInputLayoutEmail = this.findViewById(R.id.textInputLayoutEmail);
        this.textInputLayoutPassword = this.findViewById(R.id.textInputLayoutPassword);
        this.buttonLogin = this.findViewById(R.id.btn_login_to_app);
        this.signInButton = this.findViewById(R.id.btn_signup_google);

        this.firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(LoginActivity.this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    textInputLayoutEmail.setError("Please enter valid email!");
                } else {
                    textInputLayoutEmail.setError(null);
                }

                if (password.isEmpty()) {
                    textInputLayoutPassword.setError("Please enter valid password!");
                } else if (password.length() > 5) {
                    textInputLayoutPassword.setError(null);
                } else {
                    textInputLayoutPassword.setError("Password is to short!");
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

//        if(firebaseAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),Home.class));
//        }

//        databaseHelper = new DatabaseHelper(this);
//        initViews();
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //Check user input is correct or not
//                if (validate()) {
//
//                    //Get values from EditText fields
//                    String Email = editTextEmail.getText().toString();
//                    String Password = editTextPassword.getText().toString();
//
//                    //Authenticate user
//                    User currentUser = databaseHelper.Authenticate(new User(null, null, Email, Password));
//
//                    //Check Authentication is successful or not
//                    if (currentUser != null) {
//                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
//
//                        //User Logged in Successfully Launch You home screen activity
//                        Intent intent=new Intent(LoginActivity.this, Home.class);
//                        startActivity(intent);
//                        finish();
//
//                    } else {
//
//                        //User Logged in Failed
//                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();
//
//                    }
//                }
//            }
//        });
//
//
//    }
//
//    private void signIn() {
//        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signIntent,RC_SIGN_IN);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==RC_SIGN_IN){
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if(result.isSuccess()){
//                GoogleSignInAccount account = result.getSignInAccount();
//                authWithGoogle(account);
//            }
//        }
//    }
//
//    private void authWithGoogle(GoogleSignInAccount account) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
//        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    startActivity(new Intent(getApplicationContext(),Home.class));
//                    finish();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Auth Error",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }

//    //this method is used to connect XML views to its Objects
//    private void initViews() {
//        editTextEmail = (EditText) findViewById(R.id.et_username_login);
//        editTextPassword = (EditText) findViewById(R.id.et_password_login);
//        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
//        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
//        buttonLogin = (Button) findViewById(R.id.btn_login_to_app);
//
//    }
//
//    //This method is for handling fromHtml method deprecation
//    @SuppressWarnings("deprecation")
//    public static Spanned fromHtml(String html) {
//        Spanned result;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
//        } else {
//            result = Html.fromHtml(html);
//        }
//        return result;
//    }
//
//    //This method is used to validate input given by user
//    public boolean validate() {
//        boolean valid = false;
//
//        //Get values from EditText fields
//        String Email = editTextEmail.getText().toString();
//        String Password = editTextPassword.getText().toString();
//
//        //Handling validation for Email field
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//            valid = false;
//            textInputLayoutEmail.setError("Please enter valid email!");
//        } else {
//            valid = true;
//            textInputLayoutEmail.setError(null);
//        }
//
//        //Handling validation for Password field
//        if (Password.isEmpty()) {
//            valid = false;
//            textInputLayoutPassword.setError("Please enter valid password!");
//        } else {
//            if (Password.length() > 5) {
//                valid = true;
//                textInputLayoutPassword.setError(null);
//            } else {
//                valid = false;
//                textInputLayoutPassword.setError("Password is to short!");
//            }
//        }
//
//        return valid;
//    }
}