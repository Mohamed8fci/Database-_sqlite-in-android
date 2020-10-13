package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBhelper helper;
    EditText editTextname,editTextphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper= new DBhelper(getApplicationContext());
    }
    public void insert(View v){

        editTextname = (EditText) findViewById(R.id.editTextName);
        editTextphone = (EditText) findViewById(R.id.editTextPhone);

        String nameValue = editTextname.getText().toString();
        String phoneValue = editTextphone.getText().toString();

        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("name",nameValue);
        values.put("phone",phoneValue);

        long RowNum = db.insert("contact",null,values);
        Toast.makeText(this,RowNum+"",Toast.LENGTH_LONG).show();
    }

    public void select(View v){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("contact",null,null,null,null,null,null);
         String data = "";
         while (c.moveToNext()){
             int _id= c.getInt(c.getColumnIndex("_id"));
             String name = c.getString(c.getColumnIndex("name"));
             String phone = c.getString(c.getColumnIndex("phone"));
             String row = _id +", "+name+", " +phone +"/n";
             data= data+row;
             Toast.makeText(this,data,Toast.LENGTH_LONG).show();
         }
    }

    public void delete(View v){
        editTextname = (EditText) findViewById(R.id.editTextName);
        String nameValue = editTextname.getText().toString();

        SQLiteDatabase db=  helper.getWritableDatabase();

        String selection=  "name=?";
        String[] selectionArgs = {nameValue};
        db.delete("contact",selection,selectionArgs);
    }

    public void update(View v){

        editTextname = (EditText) findViewById(R.id.editTextName);
        editTextphone = (EditText) findViewById(R.id.editTextPhone);

        String nameValue = editTextname.getText().toString();
        String phoneValue = editTextphone.getText().toString();

        SQLiteDatabase dp = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone",phoneValue);
        String selection ="name=?";
        String[] selectionArgs = {nameValue};
        dp.update("contact",values,selection,selectionArgs);
    }


}