/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;





import android.app.Activity;
import android.os.Bundle;
import android.util.Log;



import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;
import com.troubadorian.mobile.android.model.HNICBoxScore;
import com.troubadorian.mobile.android.model.HNICCompletedBoxScoreReader;
import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICPlayersReader;


public class Database extends Activity
{
    
    public static final String TAG = "Database";

    List<HNICBoxScore> gameList = null;
    
    List<HNICPlayer> theList = null;
    
    List<HNICPlayer> theSortedList = null;
    
    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);
    
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.database_main);
        
        
        
        
        
        String theUrl = "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";
        
        HNICPlayersReader hnicplayersreader = new HNICPlayersReader();
        
        /*theList = hnicplayersreader.readPlayers(theUrl);*/
        
        theList = hnicplayersreader.readPlayersFromCache("allplayers2.json");
        
        
        /*theSortedList = this.sortBy(theList, "Name");*/
        
        theSortedList = theList;
        
        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer (getBaseContext());
        
        int numrowsdeleted = databaseDataLayer.DeletePlayers();
        
        Log.d(TAG, "---------the following rows were deleted------------" + numrowsdeleted);
        
        int i = 0;
        for (final HNICPlayer player : theSortedList)
        {
            i++;
            databaseDataLayer.AddPlayer(i, player.getName(), player.getPosition(), player.getTeam());
            
        }
        


        
        Log.d(TAG, "finished writing to db");
        
        
        
        
        
    }
    
    

    

  
   
}