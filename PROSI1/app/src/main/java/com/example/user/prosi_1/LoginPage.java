package com.example.user.prosi_1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button loginToApp;
    Connection con;
    String un, pass, db, ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginToApp = this.findViewById(R.id.btn_login_to_app);
        loginToApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String restURL = "http://www.flynbuy.com";
        new CheckLogin().execute(restURL);
    }

    private class CheckLogin extends AsyncTask<String, String, String> {

        final HttpClient httpClient = new DefaultHttpClient();
        String content, error, data = "";
        ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
        EditText username = findViewById(R.id.et_username_login);
        EditText password = findViewById(R.id.et_password_login);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.setTitle("Please wait...");
            progressDialog.show();
            try {
                data += "&" + URLEncoder.encode("data", "UTF-8") + "-" + username.getText();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            BufferedReader br = null;
            try{
                URL url = new URL(params[0]);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

            if (usernam.trim().equals("") || passwordd.trim().equals("")) {
                z = "Please enter username and password";
            } else {
                try {
                    con = connectionclass(un, pass, db, ip);
                    if (con == null) {
                        z = "Check your internet access";
                    } else {
                        String query = "select * from login where username = '" + usernam.toString() + "' and password = '" + passwordd.toString() + "'";
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
    }

    @Override
    protected void onPostExecute(Void res){

    }

    //--------------------------------------------//

    public Connection connectionclass(String user, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "flynbuy.com";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e) {
            Log.e("error here 1 : ", e.getMessage());
        } catch (SQLException se) {
            Log.e("error here 2 : ", se.getMessage());
        } catch (Exception e) {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
