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
    private static final String TABLEUSER = "users";

    public DbManager(Context context) {
        super(context, dbname, null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table " + TABLENAME + "(id integer primary key autoincrement, productType, temperature, date)";
        db.execSQL(qry);
        String qryFood = "create table " + TABLEFOOD + "(id integer primary key autoincrement, imageID, productType, productDescription)";
        db.execSQL(qryFood);
        String qryUser = "create table " + TABLEUSER + "(id integer primary key autoincrement, userName, password, function)";
        db.execSQL(qryUser);

        String qryadm = "SELECT * FROM " + TABLEUSER + " where username=" + "'" + "superadmin" + "'";
        Cursor cursor = db.rawQuery(qryadm, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
        } else {
            ContentValues cv = new ContentValues();
            cv.put("userName", "superadmin");
            cv.put("password", "adminsuper");
            cv.put("function", "SUPERADMIN");

            long res = db.insert(TABLEUSER, null, cv);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLEFOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLEUSER);
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

    public Cursor isUser(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM " + TABLEUSER + " WHERE userName=" + '"' + username + '"' + " AND password=" + '"' + password + '"';
        Cursor cursor = db.rawQuery(qry, null, null);
        if (cursor.getCount() > 0) {
            //cursor.close();
            return cursor;
        } else {
            cursor.close();
            return null;
        }
    }

    public String addUser(String userName, String password, String function) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName", userName.toLowerCase());
        cv.put("password", password);
        cv.put("function", function.toUpperCase());

        long res = db.insert(TABLEUSER, null, cv);

        if (res == -1) {
            return "Failed";
        } else {
            return "Successfully Created";
        }
    }


    public Boolean checkUser(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM " + TABLEUSER + " where userName=" + "'" + username + "'";
        Cursor cursor = db.rawQuery(qry, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public Integer deleteUser(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM " + TABLEUSER + " where userName=" + "'" + username + "'";
        Cursor cursor = db.rawQuery(qry, null, null);
        if (cursor.getCount() > 0) {
            Integer res = db.delete(TABLEUSER, "userName='" + username + "'", null);
            return res;
        } else {
            cursor.close();
            return -1;
        }


    }

    public Cursor getUserToDelete(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM " + TABLEUSER + " WHERE userName=" + '"' + username + '"';
        Cursor cursor = db.rawQuery(qry, null, null);
        if (cursor.getCount() > 0) {
            //cursor.close();
            return cursor;
        } else {
            cursor.close();
            return null;
        }
    }
}