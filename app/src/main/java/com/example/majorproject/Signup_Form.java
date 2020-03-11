package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Form extends AppCompatActivity {
    TextInputEditText txtUserName, txtEmail, txtPassword, txtConfirmPassword, txtArea;
    Button btnSignup;
    TextView txtText;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("SignUp Form");

        txtUserName = (TextInputEditText) findViewById(R.id.txt_username);
        txtEmail = (TextInputEditText)findViewById(R.id.txt_email);
        txtPassword = (TextInputEditText)findViewById(R.id.txt_password);
        txtConfirmPassword = (TextInputEditText)findViewById(R.id.txt_confirmpassword);
        txtArea = (TextInputEditText)findViewById(R.id.txt_area);
        btnSignup = (Button)findViewById(R.id.btn_signup);
        txtText = (TextView)findViewById(R.id.txt_text);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String username = txtUserName.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();
                String area = txtArea.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    txtUserName.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    txtEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    txtPassword.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword)){
                    txtConfirmPassword.setError("Confirm Password is required");
                    return;
                }
                if(TextUtils.isEmpty(area)){
                    txtArea.setError("Area is Required");
                    return;
                }
                if(password.length()<6){
                    txtPassword.setError("Password must be >=6 chars");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                if(password.equals(confirmpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Signup_Form.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    txtConfirmPassword.setError("Passwords doesn't match");
                    return;
                }
            }
        });
        txtText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login_Form.class));
            }
        });
    }
}
