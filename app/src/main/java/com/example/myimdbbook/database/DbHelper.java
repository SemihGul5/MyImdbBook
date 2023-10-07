package com.example.myimdbbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DBNAME="movie.db";
    public static final String TABLE_NAME="movie";
    public static final int VER=1;

    public DbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+TABLE_NAME+"(id integer primary key, movieName text, finishDate text, score text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion<newVersion){
            String sql = "drop table if exists " + TABLE_NAME + " ";
            db.execSQL(sql);
            onCreate(db);
        }

    }
}
