package com.example.user.prosi_1;

import android.content.Intent;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button login_to_app;
    EditText username, password;
    RequestQueue requestQueue;
    private static final String URL = "http://127.0.0.1:80/flynbuy/login_control.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_to_app = this.findViewById(R.id.btn_login_to_app);
        login_to_app.setOnClickListener(this);

        username = this.findViewById(R.id.et_username_login);
        password = this.findViewById(R.id.et_password_login);


    }

    @Override
    public void onClick(View view) {
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

            requestQueue.add(request);
        }
    }
}
