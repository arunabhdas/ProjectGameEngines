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

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;


public class HNICLiveBoxScoreReader
{

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    public List<HNICBoxScore> readBoxScores(String jsonFile)
    {
        String json = null;
        List<HNICBoxScore> boxScoreList = null;

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

            Type listType = new TypeToken<List<HNICBoxScore>>()
            {
            }.getType();
            boxScoreList = gson.fromJson(json, listType);

            if (boxScoreList != null)
            {
                for (HNICBoxScore boxScore : boxScoreList)
                {
                    // logger.debug(boxScore);
                }
            }
        } catch (Exception e)
        {
            // logger.error(e);
        }
        return boxScoreList;
    }
    
    

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        String boxScoresUrl = "http://www.cbc.ca/data/statsfeed/plist/liveboxscore.json";
        
       
        
        HNICLiveBoxScoreReader reader = new HNICLiveBoxScoreReader();
        try
        {   
            reader.readBoxScores(boxScoresUrl);
        }
        catch (Exception ex)
        {
            
        }
    }

}