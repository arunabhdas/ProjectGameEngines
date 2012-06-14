/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper

{
    public DatabaseHelper(Context context, String name, CursorFactory factory,
            int version)
    {
        super(context, "hnic.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TO-DO generated stub
        
         String sql = "create table Players " +
         "(PlayerId integer primary key, " + "Name varchar, " +
         "Position varchar, " + "Team varchar); ";
         
         db.execSQL(sql);
         
         String sql2 = "create table Teams (TeamId integer primary key, fullname varchar, teamname varchar, abbr varchar, city varchar, rank_conference varchar, rank_division varchar, played varchar, won varchar, lost varchar, OT varchar, points varchar, goalsfor varchar, goalsagainst varchar); ";
         
         db.execSQL(sql2);
  

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)

    {
        if (oldVersion == 1 && newVersion == 2)
        {
            /*
             * String sql = "drop table Games;";
             * 
             * db.execSQL(sql);
             * 
             * sql = "create table Games " + "(Id integer primary key, " +
             * "PlayerId integer, " + "Result integer, " + "PlayTime integer, "
             * + "Difficulty integer);";
             * 
             * db.execSQL(sql);
             */

            /*
             * String sql2 = "drop table HNICBoxScores;";
             * 
             * db.execSQL(sql2);
             */

            String sql1 = "drop table Players;";

            db.execSQL(sql1);

            String sql2 = "create table Players " +
            "(PlayerId integer primary key, " + "Name TEXT, " +
            "Position TEXT, " + "Team TEXT); ";

            db.execSQL(sql2);
            
            String sql3 = "drop table Teams;";
            
            db.execSQL(sql3);
            
            String sql4 = "create table Teams (TeamId integer primary key, fullname varchar, teamname varchar, abbr varchar, city varchar, rank_conference varchar, rank_division varchar, played varchar, won varchar, lost varchar, OT varchar, points varchar, goalsfor varchar, goalsagainst varchar); ";
            
            db.execSQL(sql4);
  

        }
    }

}
