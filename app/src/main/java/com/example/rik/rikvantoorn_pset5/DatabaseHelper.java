package com.example.rik.rikvantoorn_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rik on 1-12-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static DatabaseHelper instance;

    static final String DATABASE_NAME = "myDB.db";
    static final int DATABASE_VERSION = 1;

    public static String TABLE_LIST = "TABLE_LIST";
    public static String TABLE_TODO = "TABLE_TODO";

    public static String _ID = "_id";
    public static String LIST_NAME = "LIST_NAME";
    public static String TODO_NAME = "TODO_NAME";
    public static String TODO_DESC = "TODO_DESC";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_LIST = "CREATE TABLE " + TABLE_LIST + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LIST_NAME + " TEXT);";
        String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_NAME + " TEXT " + LIST_NAME + " TEXT " + TODO_DESC + " TEXT);";
        db.execSQL(CREATE_TABLE_LIST);
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
       onCreate(db);
    }

    public void insertList(String list) {
        db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(LIST_NAME, list);
        db.insert(TABLE_LIST, null, contentValue);
    }

    public void insertTodo (String todo, String desc, String listName) {
        db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(TODO_NAME, todo);
        contentValue.put(LIST_NAME, listName);
        contentValue.put(TODO_DESC, desc);
        db.insert(TABLE_TODO, null, contentValue);
    }
}
