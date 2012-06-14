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

public class HNICTeamRanksReader
{

    private static final String TAG = HNICTeamRanksReader.class.getName();

    public HNICTeamRanks readTeamRanks(String jsonFile)
    {
        String json = null;
        HNICTeamRanks ranks = null;

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

            Type type = new TypeToken<HNICTeamRanks>()
            {
            }.getType();
            ranks = gson.fromJson(json, type);

            if (ranks != null)
            {
                Log.d(TAG, ranks.toString());
            }
        } catch (Exception e)
        {
            // logger.error(e);
        }
        return ranks;
    }

}
