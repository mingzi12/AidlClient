package com.mingzi.aidlclient.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/31.
 */
public class MySQLiteDatabase {
    Context mContext;
    MyDBOpenHelper myDBOpenHelper;
    SQLiteDatabase db;
    int i=1;
    public MySQLiteDatabase(Context context,MyDBOpenHelper myDBOpenHelper){
        this.myDBOpenHelper = myDBOpenHelper;
        this.mContext = context;
        db = myDBOpenHelper.getWritableDatabase();
    }
    public void insert(){
      //  db =myDBOpenHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("name","helloKitty"+i);
        values1.put("phone","139777"+i);
        i++;
        db.insert("person",null,values1);
        Toast.makeText(mContext,"insert successfully",Toast.LENGTH_SHORT).show();
    }
    public void query(){
        StringBuilder sb = new StringBuilder();
       // db = myDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("person",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                int personid = cursor.getInt(cursor.getColumnIndex("personId"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone =cursor.getString(cursor.getColumnIndex("phone"));
                sb.append("id: "+personid+";"+"name: "+name+";phone:"+phone+"\n");
            }while (cursor.moveToNext());
            cursor.close();
        }
        Toast.makeText(mContext,sb,Toast.LENGTH_SHORT).show();
    }
    public void update(){
        ContentValues values2 = new ContentValues();
        values2.put("name","hello world!");
       // db = myDBOpenHelper.getWritableDatabase();
        db.update("person",values2,"name= ?",new String[]{"helloKitty4"});
    }
    public void deleteAll(){
        db = myDBOpenHelper.getWritableDatabase();
        db.delete("person",null,null);
        Toast.makeText(mContext, "delete all the record in table person succefully!",
                Toast.LENGTH_SHORT).show();
    }
    public void delete(){
       // db = myDBOpenHelper.getWritableDatabase();
        db.delete("person","personid=?",new String[]{"4"});

    }

}
