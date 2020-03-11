package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Form extends AppCompatActivity {

    TextInputEditText txtEmail, txtPassword;
    Button btnLogin;
    TextView txtText;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");

        txtEmail = (TextInputEditText) findViewById(R.id.txt_email);
        txtPassword = (TextInputEditText) findViewById(R.id.txt_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
        txtText = (TextView)findViewById(R.id.txt_text);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    txtEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    txtPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    txtPassword.setError("Password must be >=6 chars");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Toast.makeText(Login_Form.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(Login_Form.this, "Login Failed / User not available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        txtText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup_Form.class));
            }
        });
    }
}
