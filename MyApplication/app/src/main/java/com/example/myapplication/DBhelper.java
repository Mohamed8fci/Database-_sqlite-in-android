package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    Context c;
    private static final String DATABASE_NAME = "contact";
    private static final int DATABASE_VERSION = 2;

    public DBhelper(Context c){
        super(c,DATABASE_NAME,null,DATABASE_VERSION);
        this.c=c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "Create table contact("
                +"_id Integer primary key autoincrement,"
                +"name Varchar (50),"
                +"phone Integer(20))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("drop table if exists contact");
       onCreate(db);
    }
}
