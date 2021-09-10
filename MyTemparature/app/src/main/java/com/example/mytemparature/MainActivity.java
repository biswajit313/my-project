package com.example.mytemparature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText temp_inp;
    EditText name_inp;
    EditText email_inp;
    EditText time_inp;
    EditText phone_inp;
    Button res, alldata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textView2 = findViewById(R.id.textView2);
        alldata = findViewById(R.id.button2);
        textView2.setText(currentDate);
        name_inp = findViewById(R.id.editTextTextPersonName);
        email_inp = findViewById(R.id.editTextTextEmailAddress);
        time_inp = findViewById(R.id.editTexttime);
        phone_inp = findViewById(R.id.editTextphone);
        temp_inp = findViewById(R.id.editTextNumberDecimal);
        res = findViewById(R.id.button);
        FirebaseFirestore dbroot = FirebaseFirestore.getInstance();

        alldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                finish();
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_inp.getText().toString();
                String email = email_inp.getText().toString();
                Serializable time = time_inp.getText().toString();


                if (TextUtils.isEmpty(temp_inp.getText())) {
                    temp_inp.setError("Empty field");
                    return;


                } else {
                    //check data;
                    Map<String, String> items = new HashMap<>();
                    items.put("name", name_inp.getText().toString().trim());
                    items.put("email", email_inp.getText().toString().trim());
                    items.put("temperature", temp_inp.getText().toString().trim());
                    items.put("phone", phone_inp.getText().toString().trim());
                    items.put("time", time_inp.getText().toString().trim());

                    String datafrominputfield= temp_inp.getText().toString();

                    if(Float.parseFloat(datafrominputfield)<=36){
                        Toast.makeText(MainActivity.this, "Normal body temperature"+datafrominputfield, Toast.LENGTH_SHORT).show();
                    }
                    else if(Float.parseFloat(datafrominputfield)<=32){
                        Toast.makeText(MainActivity.this, "Low body temp"+datafrominputfield, Toast.LENGTH_SHORT).show();
                    }
                    else if(Float.parseFloat(datafrominputfield)>=36){
                        Toast.makeText(MainActivity.this,"High body temperature "+datafrominputfield, Toast.LENGTH_SHORT).show();
                    }

                    dbroot.collection("temperature").add(items)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    name_inp.setText("");
                                    email_inp.setText("");
                                    temp_inp.setText("");
                                    phone_inp.setText("");
                                    time_inp.setText("");
                                    Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_LONG).show();

                                }
                            });
                }
            }
        });


    }
}