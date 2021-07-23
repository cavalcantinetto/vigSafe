package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class FetchDataFoodList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FoodList> dataholder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data_food_list);


        recyclerView=(RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DbManager(this).readallDataFood();
        while (cursor.moveToNext()) {
            if (cursor.getBlob(1) != null) {
                byte[] image = cursor.getBlob(1);
                String productType = cursor.getString(2);
                String productDescription = cursor.getString(3);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                FoodList obj = new FoodList(bitmap, productType, productDescription);
                dataholder.add(obj);

            } else {
                Resources res = this.getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.fooddefault);
                String productType = cursor.getString(2);
                String productDescription = cursor.getString(3);
                FoodList obj = new FoodList(bitmap, productType, productDescription);
                dataholder.add(obj);
            }

            MyAdapter myadapter = new MyAdapter(dataholder);
            recyclerView.setAdapter(myadapter);


        }

    }
}