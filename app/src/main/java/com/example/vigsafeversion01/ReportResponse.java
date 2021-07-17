package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReportResponse extends AppCompatActivity {

    TextView textResponse;
    EditText textSearch;
    DbManager obj = null;
    Button btnSearch, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_response);

        //textResponse = findViewById(R.id.textResponse);
        textSearch = findViewById(R.id.textSearch);
        btnSearch = findViewById(R.id.search_button);
        btnReturn = findViewById(R.id.return_button);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportResponse.this, Activity_profile.class);
                startActivity(intent);
            }
        });

    }

    public void searchData() {

        obj = new DbManager(this);
        ArrayList<Measure> measures= obj.getData(textSearch.getText().toString());

        TableLayout table = (TableLayout)findViewById(R.id.table_report);
        table.removeAllViews();

        for(Measure measure : measures) {
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


    }



}
