package com.example.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "restaurant_db";
    public static final int DATABASE_VERSION = 1;

    public RestaurantHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_BUSINESSES_TABLE = "CREATE TABLE restaurant( name TEXT, quantity TEXT, price TEXT);";
        sqLiteDatabase.execSQL(SQL_CREATE_BUSINESSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(sqLiteDatabase);
    }
}
