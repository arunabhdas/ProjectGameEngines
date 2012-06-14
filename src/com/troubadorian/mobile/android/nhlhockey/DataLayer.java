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

public class DataLayer
{

    private DbHelper _dbHelper;
    
    public DataLayer(Context c)
    {
        _dbHelper = new DbHelper(c, "games.db", null, 1);
        
    }
    
    /**
     * @param args
     */
    
    public void AddGame(int playerId, int result, int playTime, int difficulty)
    {
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        
        try
        {
            ContentValues values = new ContentValues();
            values.put("PlayerId", playerId);
            values.put("Result", result);
            values.put("PlayTime", playTime);
            values.put("Difficulty", difficulty);          
            db.insert("Games", "", values);
            
           
            
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
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        
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
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        
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
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
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
                    c.getInt(c.getColumnIndex("period")),
                    c.getInt(c.getColumnIndex("type")),
                    c.getInt(c.getColumnIndex("penaltytype")),
                    c.getInt(c.getColumnIndex("time"))));
                    
                    
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
