package com.example.librarytimeline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etStudentID;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etStudentID = findViewById(R.id.etStudentNumber);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studID = etStudentID.getText().toString();
                String password = etPassword.getText().toString();

                loginStudent(studID, password);
            }
        });
    }

    private void loginStudent(String studID, String password) {
        ParseUser.logInInBackground(studID, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

            }
        });
    }
}
