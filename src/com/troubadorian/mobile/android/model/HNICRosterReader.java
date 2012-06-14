/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;


public class HNICRosterReader
{

    public static final String TAG = "HNICRosterReader";

    public List<HNICPlayer> readRoster(String jsonFile)
    {
        String json = null;
        HNICTeamPlayer teamPlayer = null;
        
        List<HNICPlayer> teamRoster = null;

        try
        {
            URL url = new URL(jsonFile);

            Log.d(TAG, "--------the url that is going to be read" + jsonFile);
            URLConnection connection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);
            json = buffer.toString();
            Gson gson = new Gson();

            Type listType = new TypeToken<HNICTeamPlayer>()
            {
            }.getType();
            teamPlayer = gson.fromJson(json, listType);

            if (teamPlayer != null)
            {
                teamRoster = teamPlayer.getRoster();

            }
        } catch (Exception e)
        {
            Log.e(TAG, "This is where the exception occured" + e.toString());
        }
        return teamRoster;
    }

}
