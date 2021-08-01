package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vigsafeversion01.ui.login.LoginActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.vigsafeversion01.R.layout.activity_fetch_data_food_list;

public class FetchDataFoodList extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<FoodList> dataholder = new ArrayList<>();
    public Report report = new Report();
    public Button buttonSubmit, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_fetch_data_food_list);


        recyclerView=(RecyclerView) findViewById(R.id.recview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        Cursor cursor = new DbManager(this).readallDataFood();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (cursor.getBlob(1) != null) {
                    byte[] image = cursor.getBlob(1);
                    String productType = cursor.getString(2);
                    String productDescription = cursor.getString(3);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    String temperature = null;
                    FoodList obj = new FoodList(bitmap, productType, productDescription, temperature);
                    dataholder.add(obj);

                } else {
                    Resources res = this.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.fooddefault2);
                    String productType = cursor.getString(2);
                    String productDescription = cursor.getString(3);
                    String temperature = null;
                    FoodList obj = new FoodList(bitmap, productType, productDescription, temperature);
                    dataholder.add(obj);
                }

                MyAdapter myadapter = new MyAdapter(dataholder);
                recyclerView.setAdapter(myadapter);
                recyclerView.scrollToPosition(0);


            }

        } else {
            startActivity(new Intent(FetchDataFoodList.this, insertProducts.class));
            Toast.makeText(getApplicationContext(), "You should input at least one product to be measured!", Toast.LENGTH_LONG).show();
        }


        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement();
                Intent intent = new Intent(FetchDataFoodList.this, LoginActivity.class);

                intent.putExtra("REPORT", report);
                //resetvalues(); It'' better not reset considering it could be a lot of work to correct just one measurement
                startActivity(intent);

            }
        });

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement();
                Intent intent = new Intent(FetchDataFoodList.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }

    public void measurement() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        Date todaysDate = new Date();
        String testDateString = df.format(todaysDate);

        for (int i = 0; i < dataholder.size(); i++) {
            Measure measure = new Measure(dataholder.get(i).getProductType(), dataholder.get(i).getTemperature(), testDateString);
            report.addMeasure(measure);
        }
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG);
    }

    public void resetvalues() {
            for (int i = dataholder.size() - 1; i >= 0; i--) {

                dataholder.get(i).setTemperature(null);
            }
        }

    }