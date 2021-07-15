package com.example.vigsafeversion01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbManager extends SQLiteOpenHelper {
    private static final String dbname="report.db";

    public DbManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table report (id integer primary key autoincrement, productType text, temperature text, date text)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS report" );
        onCreate(db);

    }
    public String addRecord(String p1, String p2, String p3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("productType", p1);
        cv.put("temperature", p2);
        cv.put("date", p3);

        long res = db.insert("report", null, cv);

        if(res==-1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }
}
