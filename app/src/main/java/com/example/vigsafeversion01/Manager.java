package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Manager extends AppCompatActivity {
    private Button products, temperatures ,schedule, report, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        products = (Button) findViewById(R.id.products);
        temperatures = (Button) findViewById(R.id.temperatures);
        schedule = (Button) findViewById(R.id.schedule);
        report = (Button) findViewById(R.id.reportdb);


        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Manager.this, insertProducts.class));
            }

        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Manager.this, DeleteProducts.class));
            }

        });

        temperatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TemperatureActivity();
            }

            private void TemperatureActivity() {
                startActivity(new Intent(Manager.this, FetchDataFoodList.class));
            }
            });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager.this,
                        ReportResponse.class);
                startActivity(intent);
            }
        });


    }
}