/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICTeam;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
 * This class will be responsible for using DbHelper to perform CRUD operations
 */

public class DatabaseDataLayer
{
    private static final String TAG = "DatabaseDataLayer";
    
    private DatabaseHelper databaseHelper;

    
    
    public DatabaseDataLayer(Context c)
    {
        databaseHelper = new DatabaseHelper(c, "hnic.db", null, 1);

    }

    /**
     * @param args
     */

    public void AddPlayer(int playerid, String name, String position,
            String team)

    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        try
        {
            ContentValues values = new ContentValues();
            values.put("PlayerId", playerid);
            values.put("Name", name);
            values.put("Position", position);
            values.put("Team", team);

            db.insert("Players", "", values);
        } finally
        {
            if (db != null)
            {
                db.close();
            }
        }
    }
    
    public void AddTeam(int teamid, String fullname, String teamname, String abbr, String city, String rank_conference, String rank_division, String played, String won, String lost, String ot, String points, String goalsfor, String goalsagainst)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        
        try
        {
            ContentValues values = new ContentValues();
            values.put("TeamId", teamid);
            values.put("fullname", fullname);
            values.put("teamname", teamname);
            values.put("abbr", abbr);
            values.put("city", city);
            values.put("rank_conference", rank_conference);
            values.put("rank_division", rank_division);
            values.put("played", played);
            values.put("won", won);
            values.put("lost", lost);
            values.put("ot", ot);
            values.put("points", points);
            values.put("goalsfor", goalsfor);
            values.put("goalsagainst", goalsagainst);
            
            db.insert("Teams", "", values);
            
            Log.d(TAG, "--------------------------" + fullname);
            
        }
        catch (Exception e)
        {
            Log.d(TAG, "--------------------adding team failed" + e.toString());
        }
        
        finally
        {
            if (db != null)
            {
                db.close();
            }
        }
    }
    public int UpdatePlayers()
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        try
        {
            Random r = new Random();
            ContentValues values = new ContentValues();
            values.put("Names", r.nextInt());

            int affected = db.update("Players", values, null, null);

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

    public int DeletePlayers()
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        try
        {
            // passing a "1" as the where clause to our delete
            // tells the method to both delete everything
            // and to return the number of rows that were deleted

            int recordsDeleted = db.delete("Players", "1", null);

            return recordsDeleted;

        } finally
        {
            if (db != null)
            {
                db.close();

            }
        }

    }

    public int DeleteTeams()
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        try
        {
            // passing a "1" as the where clause to our delete
            // tells the method to both delete everything
            // and to return the number of rows that were deleted

            int recordsDeleted = db.delete("Teams", "1", null);

            return recordsDeleted;

        } finally
        {
            if (db != null)
            {
                db.close();

            }
        }

    }
    
    public List<HNICPlayer> SelectPlayers()
    {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        
        Cursor c = null;
        try
        {
            /*Log.d("TAG", "Trying to do a select from the Players table");*/
            
            List<HNICPlayer> players = null;

            c = db.rawQuery("select * from Players", null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                do
                {
                    
                    HNICPlayer player = new HNICPlayer();
                    
                    player.setName(c.getString(1));
                    
                    player.setPosition(c.getString(2));
                    
                    player.setTeam(c.getString(3));
                    
                    
                    players.add(player);

                } while (c.moveToNext());
            }

            return players;
        } finally
        {
            if (db != null)
            {
                
                c.close();
                db.close();

            }
        }

    }
    public HNICTeam SelectTeam(String teamabbr)
    {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        HNICTeam team = new HNICTeam();
        
        Cursor c = null;
        
        try
        {
            /*Log.d("TAG", "Trying to do a select from the Teams table");*/
            
            

            c = db.rawQuery("select * from Teams WHERE abbr LIKE '%"   + teamabbr.toUpperCase() + "%'" , null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                
                team.setFullname(c.getString(1));
                team.setTeamname(c.getString(2));
                team.setAbbr(c.getString(3));
                team.setCity(c.getString(4));
                team.setRank_conference(c.getString(5));
                team.setRank_division(c.getString(6));
                team.setPlayed(c.getString(7));
                team.setWon(c.getString(8));
                team.setLost(c.getString(9));
                team.setOT(c.getString(10));
                team.setPoints(c.getString(11));
                team.setGoalsfor(c.getString(12));
                team.setGoalsagainst(c.getString(13));
            }

            return team;
        } finally
        {
            if (db != null)
            {
                c.close();
                db.close();

            }
        }

    }
    

}
