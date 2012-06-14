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


public class HNICScheduleReader {
	
	 private static final String TAG = HNICScheduleReader.class.getName();
	    
	    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

	    public List<HNICSchedule> readScheduleScoreboard(String jsonFile)
	    {
	        String json = null;
	        List<HNICSchedule> scheduleList = null;

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

	            Type listType = new TypeToken<List<HNICSchedule>>()
	            {
	            }.getType();
	            scheduleList = gson.fromJson(json, listType);

	            if (scheduleList != null)
	            {
	                for (HNICSchedule schedule : scheduleList)
	                {	                    
	                     Log.d(TAG, schedule.toString());
	                    
	                }
	            }
	        } catch (Exception e)
	        {
	            // logger.error(e);
	        }
	        return scheduleList;
	    }
}
