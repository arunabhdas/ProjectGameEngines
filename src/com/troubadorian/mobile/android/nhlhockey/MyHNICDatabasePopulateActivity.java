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


public class MyHNICDatabasePopulateActivity extends Activity
{
    
    public static final String TAG = "HNICBoxScoreReaderActivity";

    List<HNICBoxScore> gameList = null;
    
    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);
    
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hnicreaderactivity_main);
        
        String completedBoxScoresUrl = "http://www.cbc.ca/data/statsfeed/plist/completedboxscore.json";
        
        
        HNICCompletedBoxScoreReader hnicboxscorereader = new HNICCompletedBoxScoreReader();
        
        gameList = hnicboxscorereader.readBoxScores(completedBoxScoresUrl);
        
        
      
        
        MyHNICBoxScoreDataLayer myhnicboxscoredatalayer = new MyHNICBoxScoreDataLayer(getBaseContext());
        
        
        
        Log.d(TAG, "reading box scores and writing to db");
        int i = 0;
        for (final HNICBoxScore game : gameList )
        {
            Log.d(TAG, "game info " + game.getAway() 
             + "---"
             + game.getHome()
             + "---"
             + game.getStart_date_time()
             + "---"
             + game.getPeriod()
             + "---"
             + game.getStatus()
             + "---"
             + game.getAwayBoxScore().getScore()
             
            
            );
            
            i++;
            
            myhnicboxscoredatalayer.AddBoxScore(
                    
                    i, 
                    game.getAway(), 
                    game.getHome(), 
                    game.getStart_date_time(), 
                    game.getPeriod(), 
                    game.getStatus(),
                    game.getAwayBoxScore().getScore(), 
                    game.getAwayBoxScore().getScore_attempts(),
                    game.getAwayBoxScore().getPeriod1score(),
                    game.getAwayBoxScore().getPeriod2score(),
                    game.getAwayBoxScore().getPeriod3score(),
                    game.getHomeBoxScore().getScore(),
                    game.getHomeBoxScore().getScore_attempts(),
                    game.getHomeBoxScore().getPeriod1score(),
                    game.getHomeBoxScore().getPeriod2score(),
                    game.getHomeBoxScore().getPeriod3score()
                    );
                   
        }
        
        Log.d(TAG, "finished writing to db");
        
        
        
        
        
    }
    
    

    

  
   
}