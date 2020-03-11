package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main Activity");
        Toast.makeText(MainActivity.this,"Entered into database",Toast.LENGTH_LONG).show();
        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
        listView.setAdapter(adapter);

        reference = FirebaseDatabase.getInstance().getReference().child("Disease");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Disease dis = snapshot.getValue(Disease.class);
                    String disinfo = "Disease: "+dis.getDiseasename()+"\n"+"Symptoms: "+dis.getSymptoms()+"\n"+"Prevention: "+dis.getPrevention()+"\n"+"Area of Spread: "+dis.getAreaofspread()+"\n"+"Fatality Rate: "+dis.getFatality();
                    list.add(disinfo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void user(View view)
    {
        startActivity(new Intent(getApplicationContext(),UserDetails.class));
    }
    public void disease(View view)
    {
        startActivity(new Intent(getApplicationContext(),DiseaseDetails.class));
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
        finish();
    }
}
