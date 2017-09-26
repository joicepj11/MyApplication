package com.example.bpn.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 21/9/17.
 */

public class SqlDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "mydb.db";
    public static String TABLE_NAME = "AndroidDeviceDetails";
    public static String COL_NAME1 = "name";
    public static String COL_NAME2 = "version";
    public static String COL_NAME3 = "api";
    String projection[] = new String[]{COL_NAME1, COL_NAME2, COL_NAME3};

    public SqlDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table  "+ TABLE_NAME + " ( " +COL_NAME1 + "  text, " +COL_NAME2 +  "  text, " +COL_NAME3 +   "  text ) " );
    }

    public void insert(String AndroidName, String AndroidVersion, String AndroidApi) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME1, AndroidName);
        values.put(COL_NAME2, AndroidVersion);
        values.put(COL_NAME3, AndroidApi);


        db.insert(TABLE_NAME ,null,values);

    }

    public long insertData(String AndroidName, String AndroidVersion, String AndroidApi) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME1, AndroidName);
        values.put(COL_NAME2, AndroidVersion);
        values.put(COL_NAME3, AndroidApi);


        long data = db.insert(TABLE_NAME, null, values);
        return data;

    }


    public ArrayList<BeanJsonData> read() {
        ArrayList<BeanJsonData> data = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String androidName = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME1));
            String androidVersion = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME2));
            String androidApi = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME3));
            data.add(new BeanJsonData(androidName, androidVersion, androidApi));
//            cursor.moveToNext();
        }


        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("delete table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
