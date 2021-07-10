package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager extends AppCompatActivity {
    private Button products, temperatures ,schedule, report, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        products = (Button) findViewById(R.id.manager);
        temperatures = (Button) findViewById(R.id.temperatures);
        schedule = findViewById(R.id.schedule);
        report = findViewById(R.id.report);


//        products.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                productsActivity();
//            }
//
//            private void productsActivity() {
//                startActivity(new Intent(Manager.this, Products.class));
//            }
//
//        });

        temperatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TemperatureActivity();
            }

            private void TemperatureActivity() {
                startActivity(new Intent(Manager.this, MainActivity.class));
            }
        });

//        schedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scheduleActivity();
//            }
//
//            private void scheduleActivity() {
//                startActivity(new Intent(Manager.this, Schedule.class));
//            }

//        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportActivity();
            }

            private void reportActivity() {
                startActivity(new Intent(Manager.this, manager_report_layout.class));
            }

        });



    }
}