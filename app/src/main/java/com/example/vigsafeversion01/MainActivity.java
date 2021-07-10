package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Report report = new Report();
    TextView text1, text2, text3, text4;
    EditText editText1, editText2, editText3, editText4;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.productType);
        editText1 = findViewById(R.id.measurement);
        text2 = findViewById(R.id.productType2);
        editText2 = findViewById(R.id.measurement2);
        text3 = findViewById(R.id.productType3);
        editText3 = findViewById(R.id.measurement3);
        text4 = findViewById(R.id.productType4);
        editText4 = findViewById(R.id.measurement4);

        btn1 = findViewById(R.id.buttonSubmit);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement();
                Intent intent = new Intent(MainActivity.this,
                        manager_report_layout.class);
                intent.putExtra("REPORT", report);
                startActivity(intent);
            }
        });

    }

    public void measurement() {
        Date currentTime = Calendar.getInstance().getTime();

        Measure m1 = new Measure(text1.getText().toString(), editText1.getText().toString(), currentTime.toString());
        Measure m2 = new Measure(text2.getText().toString(), editText2.getText().toString(), currentTime.toString());
        Measure m3 = new Measure(text3.getText().toString(), editText3.getText().toString(), currentTime.toString());
        Measure m4 = new Measure(text4.getText().toString(), editText4.getText().toString(), currentTime.toString());

        report.addMeasure(m1);
        report.addMeasure(m2);
        report.addMeasure(m3);
        report.addMeasure(m4);

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");

        editText1.requestFocus();
    }
}





