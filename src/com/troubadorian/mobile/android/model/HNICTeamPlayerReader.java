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


public class HNICTeamPlayerReader
{

    public static final String TAG = "HNICTeamPlayerReader";

    public List<HNICTeamPlayer> readTeamPlayer(String jsonFile)
    {
        String json = null;
        List<HNICTeamPlayer> teamPlayerList = null;

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
            teamPlayerList = gson.fromJson(json, listType);

            if (teamPlayerList != null)
            {
                /*
                 * Log.d(TAG, "----------------------" +
                 * teamPlayerList.toString());
                 */

            }
        } catch (Exception e)
        {
            Log.e(TAG, "This is where the exception occured" + e.toString());
        }
        return teamPlayerList;
    }

}
