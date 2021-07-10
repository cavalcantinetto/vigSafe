package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class manager_report_layout extends MainActivity implements Serializable {

    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report_layout);
        Report report = (Report) getIntent().getSerializableExtra("REPORT");

        btnTest = findViewById(R.id.buttonTest);

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
//        for (int i = 0; i < report.getMeasurements().size(); i++) {
//            String pname = report.getMeasurements().get(i).getProductType();
//            String temp = report.getMeasurements().get(i).getTemperature();
//            String date = report.getMeasurements().get(i).getDate();
//
//            TextView t1 = new TextView(this);
//            TextView t2 = new TextView(this);
//            TextView t3 = new TextView(this);
//
//            t1.setText(pname);
//            t2.setText(temp);
//            t3.setText(date);
//
//            TableRow row = new TableRow(this);
//            row.addView(t1);
//            row.addView(t2);
//            row.addView(t3);
//
//            table.addView(row);
//        }

//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}

