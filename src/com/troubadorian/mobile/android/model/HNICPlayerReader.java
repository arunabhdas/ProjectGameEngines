/**
 * HNICPlayerReader.java
 * ca.cbc.mobile.android.hnic
 * CBCHNIC
 * 
 * Created by warmannj on Feb 2, 2011
 * Copyright 2011 CBC/Radio-Canada. All rights reserved.
 */

package com.troubadorian.mobile.android.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;

/**
 * @author warmannj
 * 
 */
public class HNICPlayerReader
{

    private static final String TAG = "HNICPlayerReader";

    public HNICPlayer readPlayer(String jsonFile)
    {
        String json = null;

        HNICPlayer player = null;
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

            Type type = new TypeToken<HNICPlayer>()
            {
            }.getType();
            player = gson.fromJson(json, type);

            if (player != null)
            {
                Log.d(TAG, "---------------------------------" + player.toString());
            }
        } catch (Exception e)
        {
            // logger.error(e);
        }
        return player;
    }



    public final String grabAsSingleString(File fileToUse)

    {

        BufferedReader theReader = null;
        String returnString = null;

        try
        {
            theReader = new BufferedReader(new FileReader(fileToUse));
            char[] charArray = null;

            if (fileToUse.length() > Integer.MAX_VALUE)
            {
                // TODO implement handling of large files.
                System.out.println("The file is larger than int max = "
                        + Integer.MAX_VALUE);
            } else
            {
                charArray = new char[(int) fileToUse.length()];

                // Read the information into the buffer.
                theReader.read(charArray, 0, (int) fileToUse.length());
                returnString = new String(charArray);

            }
        } catch (FileNotFoundException ex)
        {
            Log.e(TAG, "------trouble with file" + ex.toString());
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } finally
        {
            try
            {
                theReader.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return returnString;
    }

}
