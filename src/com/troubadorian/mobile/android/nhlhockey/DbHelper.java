package com.troubadorian.mobile.android.nhlhockey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper

{
    public DbHelper(Context context, String name, CursorFactory factory,
            int version)
    {
        super(context, "games.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TO-DO generated stub
        String sql = "create table Games " + "(PlayerId integer primar key, "
                + "Result integer, " + "PlayTime integer, "
                + "Difficulty integer); ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)

    {
        if (oldVersion == 1 && newVersion == 2)
        {
            String sql = "drop table Games;";

            db.execSQL(sql);

            sql = "create table Games " + "(Id integer primary key, "
                    + "PlayerId integer, " + "Result integer, "
                    + "PlayTime integer, " + "Difficulty integer);";

            db.execSQL(sql);

            
            String sql2 = "drop table Players;";

            db.execSQL(sql2);

            sql2 = "create table Players " + "(Id integer primary key, "
                    + "PlayerId integer, " + "Result integer, "
                    + "PlayTime integer, " + "Difficulty integer);";

            db.execSQL(sql2);
        }
    }

}
