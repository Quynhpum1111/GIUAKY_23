package com.example.hoshiko.quanlythuchi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DUNG on 4/2/2018.
 */

public class DatabaseAccess extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ThuchiDb";

    // Table name
    private static final String TABLE = "ThuchiTable";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_CLASSIFY = "classify";

    public DatabaseAccess(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_REMINDERS_TABLE = "CREATE TABLE " + TABLE +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TITLE + " TEXT, "
                + KEY_AMOUNT + " TEXT, "
                + KEY_CLASSIFY + " INTEGER DEFAULT 0);";

        sqLiteDatabase.execSQL(CREATE_REMINDERS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);

    }


    // Adding new MoneyInfo
    public int addMoneyInfo(MoneyInfo moneyInfo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TITLE , moneyInfo.getNoidung());
        values.put(KEY_AMOUNT , moneyInfo.getSotien());
        values.put(KEY_CLASSIFY , moneyInfo.getHinhthuc());

        // Inserting Row
        long ID = db.insert(TABLE, null, values);
        db.close();
        return (int) ID;
    }

    public ArrayList<MoneyInfo> getAllRecords() {

        ArrayList<MoneyInfo> thuchiList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MoneyInfo moneyInfo = new MoneyInfo();
                moneyInfo.setID(cursor.getInt(0));
                moneyInfo.setNoidung(cursor.getString(1));
                moneyInfo.setSotien(cursor.getString(2));
                moneyInfo.setHinhthuc(cursor.getInt(3));


                // Adding thu chi to list
                thuchiList.add(moneyInfo);
            } while (cursor.moveToNext());
        }
        return thuchiList;
    }
}

