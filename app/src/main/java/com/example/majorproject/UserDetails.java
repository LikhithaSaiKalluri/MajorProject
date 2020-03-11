package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetails extends AppCompatActivity {
    TextInputEditText txtName, txtAge, txtPhoneno, txtArea1;
    Button btnUserdb;
    DatabaseReference ref;
    User user;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        getSupportActionBar().setTitle("User Details");

        txtName = (TextInputEditText)findViewById(R.id.txt_name);
        txtAge = (TextInputEditText)findViewById(R.id.txt_age);
        txtPhoneno = (TextInputEditText)findViewById(R.id.txt_phoneno);
        txtArea1 = (TextInputEditText)findViewById(R.id.txt_area1);
        btnUserdb = (Button)findViewById(R.id.btn_userdb);
        user = new User();
        ref = FirebaseDatabase.getInstance().getReference().child("User");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnUserdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age=0;
                String name=txtName.getText().toString().trim();
                age=Integer.parseInt(txtAge.getText().toString().trim());
                String phoneno=txtPhoneno.getText().toString().trim();
                String area1=txtArea1.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    txtName.setError("Name is required");
                    return;
                }
                if(age<1 || age>120){
                    txtAge.setError("Enter Valid Age");
                    return;
                }
                if(TextUtils.isEmpty(area1)){
                    txtName.setError("Area is required");
                    return;
                }
                user.setName(name);
                user.setAge(age);
                if((phoneno.length()==10) && phoneno.matches("[0-9]+")) {
                    user.setPhoneno(phoneno);
                }
                else{
                    txtPhoneno.setError("Enter 10-digit valid phone no");
                    return;
                }
                user.setArea1(area1);
                ref.child(String.valueOf(maxid+1)).setValue(user);
                Toast.makeText(UserDetails.this,"Data inserted successfully",Toast.LENGTH_LONG).show();
                txtName.setText("");
                txtAge.setText("");
                txtPhoneno.setText("");
                txtArea1.setText("");
            }
        });

    }
}
