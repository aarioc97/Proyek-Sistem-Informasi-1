package com.example.user.prosi_1;

<<<<<<< HEAD
import android.app.ProgressDialog;
=======
import android.content.Intent;
>>>>>>> 8cac79a609f9f806e1dda5cbd5098911091c00c2
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

<<<<<<< HEAD
    Button loginToApp;
    Connection con;
    String un, pass, db, ip;
=======
    Button login_to_app;
    EditText username, password;
    RequestQueue requestQueue;
    private static final String URL = "http://127.0.0.1:80/flynbuy/login_control.php";
    private StringRequest request;
>>>>>>> 8cac79a609f9f806e1dda5cbd5098911091c00c2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginToApp = this.findViewById(R.id.btn_login_to_app);
        loginToApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
<<<<<<< HEAD
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
=======
        if(view == login_to_app){
            request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.names().get(0).equals("success")){
                            Toast.makeText(getApplicationContext(),"SUCCESS"+jsonObject.getString("success"),Toast.LENGTH_SHORT);
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"Error"+jsonObject.getString("error"),Toast.LENGTH_SHORT);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
>>>>>>> 8cac79a609f9f806e1dda5cbd5098911091c00c2
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    HashMap<String,String> hashMap = new HashMap<String, String>();
                    hashMap.put("username",username.getText().toString());
                    hashMap.put("password",password.getText().toString());

                    return hashMap;
                }
            };

<<<<<<< HEAD
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
=======
            requestQueue.add(request);
>>>>>>> 8cac79a609f9f806e1dda5cbd5098911091c00c2
        }
    }
}
