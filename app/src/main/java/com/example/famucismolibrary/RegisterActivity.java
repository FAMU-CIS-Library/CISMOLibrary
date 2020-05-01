package com.example.famucismolibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";

    private EditText etName;
    private EditText etEmail;
    private EditText etStudentNum;
    private EditText etPassword;
    private EditText etConfirm;

    private Button btnRegister;

    private ParseUser student = new ParseUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etStudentName);
        etEmail = findViewById(R.id.etStudentEmail);
        etStudentNum = findViewById(R.id.etStudentNum);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etStudentNum.getText().toString();
                int studentNum = Integer.parseInt(etStudentNum.getText().toString());
                String name = etName.getText().toString();
                String password = etPassword.getText().toString();
                String confirm = etConfirm.getText().toString();
                String email = etEmail.getText().toString();

                // no empty fields
                if(username.isEmpty() || password.isEmpty() || email.isEmpty() || confirm.isEmpty() || name.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please complete all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                // Verify username
                if(studentNum < 300000000 || studentNum > 300999999)
                {
                    Toast.makeText(RegisterActivity.this, "Please, enter you FAMU student number with no hyphens.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valid FAMU Email
                if(!(email.endsWith("@famu.edu")))
                {
                    Toast.makeText(RegisterActivity.this, "Please, use your FAMU email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Confirm Password
                if(!(password.equals(confirm)))
                {
                    Toast.makeText(RegisterActivity.this, "Please, make sure your passwords match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                signUpUser(username, password, email, name);
                goLoginActivity();
            }
        });
    }

    private void signUpUser(String username, String password, String email, String name) {
        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.put("name", name);
        user.put("isRenting", false);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Error Signing Up", e);
                    return;
                }
                else
                    Toast.makeText(RegisterActivity.this, "Signup Successful!", Toast.LENGTH_LONG).show();
                goLoginActivity();
            }
        });
    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
