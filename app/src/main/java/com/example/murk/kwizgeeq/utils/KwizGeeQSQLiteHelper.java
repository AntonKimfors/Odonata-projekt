package com.example.murk.kwizgeeq.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by akimfors on 2017-05-16.
 */

public class KwizGeeQSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "_ID";

    //QUIZ Table Functionallity
    public static final String TABLE_QUIZES = "QUIZES";
    public static final String COLUMN_QUIZ_NAME = "QUIZ_NAME";
    private static String DB_CREATE =
                    "CREATE TABLE " + TABLE_QUIZES + " (" + COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUIZ_NAME + " TEXT)";



    public KwizGeeQSQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        //sqLiteDatabase.execSQL(CREATE_ANNOTATIONS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}