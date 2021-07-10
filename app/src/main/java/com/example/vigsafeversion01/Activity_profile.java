package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_profile extends AppCompatActivity {
    public String nameGlobal = "Mac";

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        button1 = (Button) findViewById(R.id.manager);
        button2 = (Button) findViewById(R.id.employee);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1Activity();
            }



            private void button1Activity() {
                startActivity(new Intent(Activity_profile.this, Manager.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2Activity();
            }

            private void button2Activity() {
                startActivity(new Intent(Activity_profile.this, MainActivity.class));
            }
        });

    }
}
