/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.os.Environment;
import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;


public class HNICConferenceListReader
{

    private static final String TAG = HNICConferenceListReader.class.getName();

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    /*List<HNICConferenceListResponse> response = null;*/
    
    List<HNICTeam> re = null;
    
    
    
    public List<HNICConferenceListResponse> readConferenceList(String jsonFile)
    {
        String json = null;
        List<HNICConferenceListResponse> response = null;

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

           
            
            /*Type type = new TypeToken<HNICTeam>()
            {
            }.getType();*/
            
            Type type = new TypeToken<HNICConferenceListResponse>()
            {
            }.getType();
            
            try
            {
                response = gson.fromJson(json, type);
                Log.d(TAG, "----------------------does it even get here");
            }
         
            catch (Exception ex)
            {
                Log.e(TAG, "--------------let's try to figure out the exact nature of the exception" + ex.toString());
            }

            Log
                    .d(TAG,
                            "-------------------------reading of conferencelist.json in progress--------");

            if (response != null)
            {
                // Log.d(TAG, leaders.toString());
               /* for (HNICConferenceListResponse res : retrievedresult)
                {
                    Log.d(TAG, "------" + res.toString());
                }*/

            }
        } catch (Exception e)
        {
            Log.e(TAG, "--------------we're talking big exception" + e.toString());
        }
        return response;
    }

}
