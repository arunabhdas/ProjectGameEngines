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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Environment;
import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;
import com.troubadorian.mobile.android.model.HNICBoxScore;


public class HNICCompletedBoxScoreReader
{

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);
    private static final String TAG = "HNICCompletedBoxScoreReader";

    /*
     * String boxScoresUrl =
     * "http://www.cbc.ca/data/statsfeed/plist/completedboxscore.json";
     */

    List<HNICBoxScore> boxScoreList = null;

    public List<HNICBoxScore> readBoxScores(String jsonFile)
    {
        String json = null;

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

            /* start of writing file to sdcard */

            // Path to write files to
            /*
             * String path = Environment.getExternalStorageDirectory()
             * .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
             */

            /*
             * String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files/";
             * 
             * String fname = "completedboxscore.json";
             * 
             * // Current state of the external media String extState =
             * Environment.getExternalStorageState();
             * 
             * // External media can be written onto if
             * (extState.equals(Environment.MEDIA_MOUNTED)) { try { // Make sure
             * the path exists boolean exists = (new File(path)).exists(); if
             * (!exists) { new File(path).mkdirs(); }
             * 
             * // Open output stream FileOutputStream fOut = new
             * FileOutputStream(path + fname);
             * 
             * fOut.write(json.getBytes());
             * 
             * // Close output stream fOut.flush(); fOut.close();
             * 
             * } catch (IOException ioe) { ioe.printStackTrace();
             * 
             * Log.e(TAG, "-------------------" + ioe.toString()); } }
             */
            /* end of writing file to sdcard */
            if (boxScoreList != null)
            {
                for (HNICBoxScore boxScore : boxScoreList)
                {
                    // logger.debug(boxScore);
                    Log.d(TAG, "----------------------" + boxScore.getAway());
                }
            } else
            {
                Log
                        .d(TAG,
                                "--------------------------------problems reading completed box score");
            }

        } catch (Exception e)
        {
            // logger.error(e);

            Log.e(TAG,
                    "---------------------problems reading completed box score"
                            + e.toString());
        }
        return boxScoreList;
    }

    public List<HNICBoxScore> readBoxScoresAndWriteToCache(String jsonFile)
    {
        String json = null;

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

            /* start of writing file to sdcard */

            // Path to write files to
            /*
             * String path = Environment.getExternalStorageDirectory()
             * .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
             */

            String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files/";

            String fname = "completedboxscore.json";

            // Current state of the external media
            String extState = Environment.getExternalStorageState();

            // External media can be written onto

            try
            {
                // Make sure the path exists
                boolean exists = (new File(path)).exists();
                if (!exists)
                {
                    new File(path).mkdirs();
                }

                // Open output stream
                FileOutputStream fOut = new FileOutputStream(path + fname);

                fOut.write(json.getBytes());

                // Close output stream
                fOut.flush();
                fOut.close();

            } catch (IOException ioe)
            {
                ioe.printStackTrace();

                Log.e(TAG, "-------------------" + ioe.toString());
            }

            /* end of writing file to sdcard */
            if (boxScoreList != null)
            {
                for (HNICBoxScore boxScore : boxScoreList)
                {
                    // logger.debug(boxScore);
                    Log.d(TAG, "----------------------" + boxScore.getAway());
                }
            } else
            {
                Log
                        .d(TAG,
                                "--------------------------------problems reading completed box score");
            }

        } catch (Exception e)
        {
            // logger.error(e);

            Log.e(TAG,
                    "---------------------problems reading completed box score"
                            + e.toString());
        }
        return boxScoreList;
    }

    public List<HNICBoxScore> readBoxScoresFromCache(String jsonFile)
    {
        String json = null;
        List<HNICBoxScore> boxScoreList = null;

        try
        {
            /*
             * File file = new File(
             * "/sdcard/Android/data/hnic/files/completedboxscore.json");
             */

            Log.d(TAG, "----------start of reading completedboxscore.json from cache---------");
            
            File file = new File(
                    "/data/data/com.troubadorian.mobile.android.nhlhockey/files/completedboxscore.json");

            json = this.grabAsSingleString(file);

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
            
            Log.d(TAG, "----------end of reading completedboxscore.json from cache---------");   
        } catch (Exception e)
        {
            // logger.error(e);
            Log.d(TAG, "!----------------error reading completedboxscore.json from cache-----!");
        }
        return boxScoreList;
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