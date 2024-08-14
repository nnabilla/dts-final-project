package com.nabilla.finalproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_ROTI = "roti";
    public static final String TABLE_TRANSAKSI = "transaksi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMGURL = "imgurl";
    public static final String COLUMN_PEMESAN = "pemesan";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    static final String DB_NAME = "digitalent.db";
    private static final int DB_VERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE_ROTI = "CREATE TABLE " + TABLE_ROTI + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PRICE + " TEXT NOT NULL, " +
                COLUMN_IMGURL + " TEXT NOT NULL " +
                " )";
        final String SQL_CREATE_TABLE_TRANSAKSI = "CREATE TABLE " + TABLE_TRANSAKSI + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PRICE + " TEXT NOT NULL, " +
                COLUMN_IMGURL + " TEXT NOT NULL, " +
                COLUMN_PEMESAN + " TEXT NOT NULL, " +
                COLUMN_LATITUDE + " TEXT NOT NULL, " +
                COLUMN_LONGITUDE + " TEXT NOT NULL " +
                " )";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ROTI);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TRANSAKSI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROTI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAllRotiData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_ROTI;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_PRICE, cursor.getString(2));
                map.put(COLUMN_IMGURL, cursor.getString(3));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite", "" + wordList);

        database.close();
        return wordList;
    }

    public void insertRoti(String name, String price, String imgurl) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_ROTI + " (name, price,imgurl) " +
                "VALUES ('" + name + "', '" + price + "','" + imgurl + "')";
        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void updateRoti(int id, String name, String price, String imgurl) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_ROTI + " SET "
                + COLUMN_NAME + "='" + name + "', "
                + COLUMN_PRICE + "='" + price + "',"
                + COLUMN_IMGURL + "='" + imgurl + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void deleteRoti(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_ROTI + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public ArrayList<HashMap<String, String>> getAllTransaksiData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_TRANSAKSI;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_PRICE, cursor.getString(2));
                map.put(COLUMN_IMGURL, cursor.getString(3));
                map.put(COLUMN_PEMESAN, cursor.getString(4));
                map.put(COLUMN_LATITUDE, cursor.getString(5));
                map.put(COLUMN_LONGITUDE, cursor.getString(6));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite", "" + wordList);

        database.close();
        return wordList;
    }

    public void insertTransaksi(String name, String price, String imgurl, String pemesan, String latitude, String longitude) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_TRANSAKSI + " (name, price,imgurl,pemesan,latitude,longitude) " +
                "VALUES ('" + name + "', '" + price + "','" + imgurl + "','" + pemesan + "','" + latitude + "','" + longitude + "')";
        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void updateTransaksi(int id, String name, String price, String imgurl, String pemesan, String latitude, String longitude) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_TRANSAKSI + " SET "
                + COLUMN_NAME + "='" + name + "', "
                + COLUMN_PRICE + "='" + price + "',"
                + COLUMN_IMGURL + "='" + imgurl + "',"
                + COLUMN_IMGURL + "='" + pemesan + "',"
                + COLUMN_IMGURL + "='" + latitude + "',"
                + COLUMN_IMGURL + "='" + longitude + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void deleteTransaksi(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_TRANSAKSI + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}