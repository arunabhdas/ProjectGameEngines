/**
 * HNICBoxScoreReaderActivity.java
 * Copyright 2010 CBC/Radio-Canada. All rights reserved.
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

public class MyHNICConferenceListReaderActivity extends Activity
{

/*    public static final String TAG = "MyHNICConferenceListReaderActivity";

     List<HNICGame> gameList = null; 

    List<HNICConference> conferenceList = null;

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hnicreaderactivity_main);

        // String liveScheduleScoreboardsUrl =
        // "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json";

        String conferenceListUrl = "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";

        
         * HNICScheduleScoreboardReader hnicschedulescoreboardreader = new
         * HNICScheduleScoreboardReader();
         

        HNICConferenceListReader hnicconferencelistreader = new HNICConferenceListReader();

        
         * gameList = hnicschedulescoreboardreader
         * .readScheduleScoreboard(liveScheduleScoreboardsUrl);
         

        conferenceList = hnicconferencelistreader
                .readConferenceList(conferenceListUrl);

        Log.d(TAG, "reading box scores and writing to db");
        int i = 0;
        for (final HNICConference conference : conferenceList)
        {
            
             * Log.d(TAG, "game info " + game.getAway() + "---" + game.getHome()
             * + "---" + game.getStart_date_time() + "--available-" +
             * game.getAvailable_on_cbc());
             

            Log.d(TAG, "gameresult info " + conference.toString()

            );

            i++;

            Log.d(TAG, "finished writing to db");

        }
    }*/

}