package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DiseaseDetails extends AppCompatActivity {
    TextInputEditText txtDiseasename, txtSymptoms, txtPrevention, txtAreaofspread, txtFatality;
    Button btnDiseasedb;
    DatabaseReference ref1;
    Disease disease;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);
        getSupportActionBar().setTitle("Disease Details");

        txtDiseasename = (TextInputEditText)findViewById(R.id.txt_diseasename);
        txtSymptoms = (TextInputEditText)findViewById(R.id.txt_symptoms);
        txtPrevention = (TextInputEditText)findViewById(R.id.txt_prevention);
        txtAreaofspread = (TextInputEditText)findViewById(R.id.txt_areaofspread);
        txtFatality = (TextInputEditText)findViewById(R.id.txt_fatality);
        btnDiseasedb = (Button)findViewById(R.id.btn_diseasedb);
        disease = new Disease();
        ref1 = FirebaseDatabase.getInstance().getReference().child("Disease");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnDiseasedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diseasename=txtDiseasename.getText().toString().trim();
                String symptoms=txtSymptoms.getText().toString().trim();
                String prevention=txtPrevention.getText().toString().trim();
                String areaofspread=txtAreaofspread.getText().toString().trim();
                String fatality=txtFatality.getText().toString().trim();
                disease.setDiseasename(diseasename);
                disease.setSymptoms(symptoms);
                disease.setPrevention(prevention);
                disease.setAreaofspread(areaofspread);
                disease.setFatality(fatality);
                ref1.child(String.valueOf(maxid+1)).setValue(disease);
                Toast.makeText(DiseaseDetails.this,"Disease detts entered",Toast.LENGTH_LONG).show();
                txtDiseasename.setText("");
                txtSymptoms.setText("");
                txtPrevention.setText("");
                txtAreaofspread.setText("");
                txtFatality.setText("");
            }
        });

    }
}
