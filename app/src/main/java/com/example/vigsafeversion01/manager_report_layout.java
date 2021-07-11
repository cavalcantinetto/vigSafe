package com.example.vigsafeversion01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class manager_report_layout extends MainActivity implements Serializable {

    Button btnSend, btnCorrect;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

            t1.setText(measure.getProductType());
            t2.setText(measure.getTemperature());
            t3.setText(measure.getDate());

            TableRow row = new TableRow(this);
            row.addView(t1);
            row.addView(t2);
            row.addView(t3);

            table.addView(row);
        }
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();

                for (int i=0; i<measurements.size(); i++) {
                    Measure m1Test = measurements.get(i);
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference(String.valueOf(i));
                    reference.setValue(m1Test);
                }

                }
        });
    }
}

