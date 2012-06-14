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


public class HNICTeamLastTenReader
{

    public static final String TAG = "HNICTeamLastTenReader";

    public List<HNICTeamLastTen> readLastTen(String jsonFile)
    {
        String json = null;
        List<HNICTeamLastTen> seriesList = null;

        try
        {
            URL url = new URL(jsonFile);
            URLConnection connection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);
            json = buffer.toString();
            Gson gson = new Gson();

            Type listType = new TypeToken<List<HNICTeamLastTen>>()
            {
            }.getType();
            seriesList = gson.fromJson(json, listType);

            if (seriesList != null)
            {
                for (HNICTeamLastTen series : seriesList)
                {
                    Log.d(TAG, "-------------" + series.toString());
                    // logger.debug(boxScore);
                }
            }
        } catch (Exception e)
        {
            // logger.error(e);
        }
        return seriesList;
    }

}
