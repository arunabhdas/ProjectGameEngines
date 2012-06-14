package com.troubadorian.mobile.android.nhlhockey;

import java.util.ArrayList;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * This class will be responsible for using DbHelper to perform CRUD operations
 */

public class MyHNICBoxScoreDataLayer
{

    private MyHNICDbHelper _myhnicdbHelper;
    
    public MyHNICBoxScoreDataLayer(Context c)
    {
        _myhnicdbHelper = new MyHNICDbHelper(c, "hnic.db", null, 1);
        
    }
    
    /**
     * @param args
     */
    
    public void AddBoxScore(
            int hnicboxscoreId, 
            String away, 
            String home, 
            String start_date_time,
            String period,
            String status,
            String away_boxscore_score,
            String away_boxscore_score_attempts,
            String away_boxscore_period1score,
            String away_boxscore_period2score,
            String away_boxscore_period3score,
            String home_boxscore_score,
            String home_boxscore_score_attempts,
            String home_boxscore_period1score,
            String home_boxscore_period2score,
            String home_boxscore_period3score
            
            )
    {
        SQLiteDatabase db = _myhnicdbHelper.getWritableDatabase();
        
        try
        {
            ContentValues values = new ContentValues();
            
            values.put("HNICBoxScoreId", hnicboxscoreId);
            values.put("away", away);
            values.put("home", home);
            values.put("start_date_time", start_date_time);    
            values.put("period", period);
            values.put("status", status);
            values.put("away_boxscore_score", away_boxscore_score);
            values.put("away_boxscore_score_attempts", away_boxscore_score_attempts);
            values.put("away_boxscore_period1score", away_boxscore_period1score);
            values.put("away_boxscore_period2score", away_boxscore_period2score);
            values.put("away_boxscore_period3score", away_boxscore_period3score);
            values.put("home_boxscore_score", home_boxscore_score);
            values.put("home_boxscore_score_attempts", home_boxscore_score_attempts);
            values.put("home_boxscore_period1score", home_boxscore_period1score);
            values.put("home_boxscore_period2score", home_boxscore_period2score);
            values.put("home_boxscore_period3score", home_boxscore_period3score);
            
            
            db.insert("HNICBoxScores", "", values);
            
           
            
        }
        finally
        {
            if (db!=null)
            {
                db.close();
                
            }
        }
    }
    
    public int UpdateGames()
    {
        SQLiteDatabase db = _myhnicdbHelper.getWritableDatabase();
        
        try
        {
            Random r = new Random();
            ContentValues values = new ContentValues();
            values.put("PlayTime", r.nextInt());
            
            int affected = db.update("Games", values, null, null);
            
            return affected;
            
        }
        
        finally
        {
            if (db != null)
            {
                db.close();
                
            }
        }
        
    }
    
    public int DeleteGames()
    {
        SQLiteDatabase db = _myhnicdbHelper.getWritableDatabase();
        
        try
        {
            // passing a "1" as the where clause to our delete
            // tells the method to both delete everything
            // and to return the number of rows that were deleted
            
            int recordsDeleted = db.delete("Games", "1", null);
            
            return recordsDeleted;
            
        }
        finally
        {
            if (db != null)
            {
                db.close();
                
                
            }
        }
        
    }
    
/*    public ArrayList<HNICGameResult> SelectGames()
    {
        SQLiteDatabase db = _myhnicdbHelper.getReadableDatabase();
        try
        {
            ArrayList<HNICGameResult> results = new ArrayList<HNICGameResult>();
            
            Cursor c = db.rawQuery("select * from Games", null);
            
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                do
                {
                    results.add(new HNICGameResult(
                    c.getInt(c.getColumnIndex("PlayerId")),
                    c.getInt(c.getColumnIndex("Result")),
                    c.getInt(c.getColumnIndex("PlayTime")),
                    c.getInt(c.getColumnIndex("Difficulty"))));
                    
                    
                }
                while (c.moveToNext());        
                }
             
            return results;
        }
        finally 
        {
            if (db != null)
            {
                db.close();
                
            }
        }
        
    }*/

}
