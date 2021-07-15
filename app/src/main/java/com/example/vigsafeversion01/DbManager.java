package com.example.vigsafeversion01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DbManager extends SQLiteOpenHelper {
    private static final String dbname="report.db";
    private static final String TABLENAME = "report";
    private static final String TAG = "TAG";

    public DbManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table " + TABLENAME +  "(id integer primary key autoincrement, productType, temperature, date)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);

    }
    public String addRecord(String p1, String p2, String p3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("productType", p1);
        cv.put("temperature", p2);
        cv.put("date", p3);

        long res = db.insert(TABLENAME, null, cv);

        if(res==-1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }
    public ArrayList<Measure> getData(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = "select * from " + TABLENAME + " where date LIKE '%" + date +"%'";

        Cursor cursor = db.rawQuery(qry, null, null);
        ArrayList<Measure> result = new ArrayList<Measure>();
        while (cursor.moveToNext()) {
            result.add(new Measure(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return result;
    }


}
