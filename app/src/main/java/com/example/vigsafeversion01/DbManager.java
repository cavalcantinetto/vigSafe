package com.example.vigsafeversion01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DbManager extends SQLiteOpenHelper {
    private static final String dbname = "report.db";
    private static final String TABLEFOOD = "menu";
    private static final String TABLENAME = "report";

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
        cursor.close();
        return result;
    }


    public String addRecordFood(byte[] image, String productType, String productDescription) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("imageID", image);
        cv.put("productType", productType.toUpperCase());
        cv.put("productDescription", productDescription.toUpperCase());

        long res = db.insert(TABLEFOOD, null, cv);

        if (res == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }


//    public Boolean removeRecordFood(String productType) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String strSelectProductType = "SELECT * FROM " + TABLEFOOD + "WHERE productType = " + productType;
//        //String strSelectProductDescription = "SELECT * FROM " + TABLEFOOD + " WHERE productDescription =" + productDescription;
//        Cursor cursorProductType = db.rawQuery(strSelectProductType, null);
//        //Cursor cursorProductDescription = db.rawQuery(strSelectProductDescription, null);
//
////        if(cursorProductDescription.getCount() <= 0){
////            cursorProductDescription.close();
//            if (cursorProductType.getCount() <= 0) {
//                cursorProductType.close();
//                return false;
//            } else {
//                String strDelete = "DELETE FROM " + TABLEFOOD + " WHERE productType= " + productType;
//                db.rawQuery(strDelete,null);
//                cursorProductType.close();
//                return true;
//            }
//
//    }
//
//    public Cursor searchProduct (String productType, String productDescription) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String strSelectProductType = "SELECT * FROM " + TABLEFOOD + " WHERE productType=" + '"' + productType + '"';
//        String strSelectProductDescription = "SELECT * FROM " + TABLEFOOD + " WHERE productDescription=" + '"' + productDescription + '"';
//        if ((productType != null) || (productType != "")) {
//            Cursor cursorProductType = db.rawQuery(strSelectProductType, null);
//            return cursorProductType;
//        }
//        else if ((productDescription != null) || (productDescription != "")) {
//            Cursor cursorProductDescription = db.rawQuery(strSelectProductDescription, null);
//            return cursorProductDescription;
//        }
//        else {
//            return null;
//        }
//
//    }


    public Cursor readallDataFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from " + TABLEFOOD + " order by id desc";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;

    }

}