package com.example.user.prosi_1;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button login_to_app;
    Connection con;
    String un, pass, db, ip;
    EditText username, password;

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
        String restURL = "flynbuy.com";

    }

    public class checkLogin extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected String doInBackground(String... strings) {
            String usernam = username.getText().toString();
            String passwordd = password.getText().toString();

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
