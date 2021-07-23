package com.example.vigsafeversion01;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class manager_report_layout extends MainActivity implements Serializable {

    Button btnSend, btnCorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report_layout);
        Report report = (Report) getIntent().getSerializableExtra("REPORT");
        ArrayList<Measure> measurements = report.getMeasurements();

        btnSend = findViewById(R.id.buttonSend);
        btnCorrect = findViewById(R.id.buttonCorrect);

        TableLayout table = (TableLayout)findViewById(R.id.table_report);

        for(Measure measure : report.getMeasurements()) {
            TextView t1 = new TextView(this);
            TextView t2 = new TextView(this);
            TextView t3 = new TextView(this);

            t1.setText(measure.getDate());
            t2.setText(measure.getProductType());
            t3.setText(measure.getTemperature());

            TableRow row = new TableRow(this);
            row.addView(t1);
            row.addView(t2);
            row.addView(t3);

            table.addView(row);
        }


        btnSend.setOnClickListener(v -> {
            String res = "";
            for(Measure measure : report.getMeasurements()) {
                DbManager db = new DbManager(this);
                res = db.addRecord(measure.productType, measure.temperature, measure.date);
            }

            Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG );
            toast.setGravity(Gravity.CENTER |Gravity.START, 500, 0);
            toast.show();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(manager_report_layout.this, Activity_profile.class);
                    startActivity(intent);
                    finish();
                }
            }, 5000);


            });


        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manager_report_layout.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
