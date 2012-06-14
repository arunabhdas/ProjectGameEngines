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
import com.troubadorian.mobile.android.model.HNICGameResults;
import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICPlayersReader;

public class MyHNICReaderActivity extends Activity
{

    public static final String TAG = "MyHNICStatsReaderActivity";

    List<HNICPlayer> theList = null;
    
    List<HNICGameResults> gameResultsList = null;

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hnicreaderactivity_main);
/*
        String liveScheduleScoreboardsUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json";
        
        String completedGameResultsUrl = "http://www.cbc.ca/data/statsfeed/plist/completedgameresults.json";

        HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

        HNICCompletedGameResultsReader hniccompletedgameresultsreader = new HNICCompletedGameResultsReader();*/
        
        
     /*   gameList = hnicschedulescoreboardreader
                .readScheduleScoreboard(liveScheduleScoreboardsUrl);
        
        gameResultsList = hniccompletedgameresultsreader.readCompletedGameResults(completedGameResultsUrl);

        Log.d(TAG, "reading box scores and writing to db");
        int i = 0;
        for (final HNICGameResults gameResults : gameResultsList)
        {
            Log.d(TAG, "game info " + game.getAway() + "---" + game.getHome()
                    + "---" + game.getStart_date_time() + "--available-" + game.getAvailable_on_cbc());


            Log.d(TAG, "gameresult info " 
                   + gameResults.toString()
                    
 
                
            );
            
            i++;

            Log.d(TAG, "finished writing to db");

        }*/
        
        
        String theUrl = "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";
        
        HNICPlayersReader hnicplayersreader = new HNICPlayersReader();
        
        theList = hnicplayersreader.readPlayers(theUrl);
        
        for (final HNICPlayer player : theList)
        {
            Log.d(TAG, "team info " + player.toString());
        }
        
        
        
    }

}