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


public class HNICGameResultReader
{

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    public static final String TAG = "HNICGameResultReader";
    
    public List<HNICGameResults> readCompletedGameResults(String jsonFile)
    {
        String json = null;
        List<HNICGameResults> gameResultList = null;

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

            Type listType = new TypeToken<List<HNICGameResults>>()
            {
            }.getType();
            gameResultList = gson.fromJson(json, listType);

            if (gameResultList != null)
            {
                for (HNICGameResults gameResult : gameResultList)
                {
                    // logger.debug(boxScore);
                }
            }
        } catch (Exception e)
        {
            Log.e(TAG, "This is where the exception occured");
        }
        return gameResultList;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
        String completedGameResultssUrl = "http://www.cbc.ca/data/statsfeed/plist/completedgameresults.json";

        HNICGameResultReader reader = new HNICGameResultReader();
        reader.readCompletedGameResults(completedGameResultssUrl);
    }

}