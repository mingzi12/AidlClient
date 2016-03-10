package com.mingzi.aidlclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/31.
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME="my.db";
    public MyDBOpenHelper(Context context,String name,
                          SQLiteDatabase.CursorFactory factory,int version) {
        super(context,FILE_NAME,null,1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(personId integer primary key autoincrement,name varchar(20),phone varchar(12))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
