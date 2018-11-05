package com.example.a59070035.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context){
        super(context, "DB.SLEEPY", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SLEEP_TABLE = "CREATE TABLE SLEEP ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "uid TEXT, date TEXT, timetosleep TEXT , timetowake TEXT )";
        db.execSQL(CREATE_SLEEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTime(Sleep sleep){
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("uid", sleep.getUid());
        values.put("date", sleep.getDate());
        values.put("timetosleep", sleep.getTimeToSleep());
        values.put("timetowake", sleep.getTimeWakeUp());
//        sqLiteDatabase.update("SLEEP", values, "_id=1" , null);

        sqLiteDatabase.insert("SLEEP", null, values);
        sqLiteDatabase.close();
    }

    public void updateTime(Sleep sleep, int id){
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("uid", sleep.getUid());
        values.put("date", sleep.getDate());
        values.put("timetosleep", sleep.getTimeToSleep());
        values.put("timetowake", sleep.getTimeWakeUp());


        int row = sqLiteDatabase.update("SLEEP", values, "_id=?" , new String[]{String.valueOf(id)});
        Log.d("System", "DBHelper say " + id + " >> " + row);
        sqLiteDatabase.close();
    }





    public ArrayList<Sleep> getAllTimeSleep(ArrayList<Sleep> _sleeps){
        sqLiteDatabase = this.getReadableDatabase();

        Cursor myCursor = sqLiteDatabase.rawQuery("Select * from SLEEP", null);
        while (myCursor.moveToNext()){
            String top = myCursor.getString(2);
            String bot = myCursor.getString(3);
            String right = myCursor.getString(4);
            String uid = myCursor.getString(1);
            int id = myCursor.getInt(0);

            _sleeps.add(new Sleep(top, bot, right, uid));
        }
        return _sleeps;
    }
}
