package com.example.vigsafeversion01;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Blob;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DbManager extends SQLiteOpenHelper {
    private static final String dbname = "report.db";
    private static final String TABLEFOOD = "menu";
    private static final String TABLENAME = "report";
    private static final String TAG = "TAG";

    public DbManager(Context context) {
        super(context, dbname, null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table " + TABLENAME + "(id integer primary key autoincrement, productType, temperature, date)";
        db.execSQL(qry);
        String qryFood = "create table " + TABLEFOOD + "(id integer primary key autoincrement, imageID, productType, productDescription)";
        db.execSQL(qryFood);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLEFOOD);
        onCreate(db);

    }

    public String addRecord(String p1, String p2, String p3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("productType", p1);
        cv.put("temperature", p2);
        cv.put("date", p3);

        long res = db.insert(TABLENAME, null, cv);

        if (res == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }

    public ArrayList<Measure> getData(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = "select * from " + TABLENAME + " where date LIKE '%" + date + "%'";

        Cursor cursor = db.rawQuery(qry, null, null);
        ArrayList<Measure> result = new ArrayList<Measure>();
        while (cursor.moveToNext()) {
            result.add(new Measure(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return result;
    }

    public String addRecordFood(byte[] image, String productType, String productDescription) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("imageID", image);
        cv.put("productType", productType);
        cv.put("productDescription", productDescription);

        long res = db.insert(TABLEFOOD, null, cv);

        if (res == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }

    public Cursor readallDataFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from " + TABLEFOOD + " order by id desc";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;


    }
}