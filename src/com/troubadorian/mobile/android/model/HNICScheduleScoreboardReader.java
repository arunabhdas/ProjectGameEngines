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
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Environment;
import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;


public class HNICScheduleScoreboardReader
{

    private static final String TAG = "HNICScheduleScoreboardReader";

    /*
     * String liveScheduleScoreboardUrl =
     * "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json"
     * ;
     */

    List<HNICGame> futuregameList = null;

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    public List<HNICGame> readScheduleScoreboard(String jsonFile)
    {
        String json = null;
        List<HNICGame> gameList = null;

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

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            gameList = gson.fromJson(json, listType);

            /* start of writing file to sdcard */

            // Path to write files to
            /*
             * String path = Environment.getExternalStorageDirectory()
             * .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
             */

            /*
             * String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files/";
             * 
             * String fname = "schedule_scoreboard.json";
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

          /*  if (gameList != null)
            {
                for (HNICGame game : gameList)
                {
                    // logger.debug(boxScore);

                    // Log.d(TAG,"game : " + game.getAway());

                }
            }*/
        } catch (Exception e)
        {
            // logger.error(e);
        }
        return gameList;
    }

    public List<HNICGame> readScheduleScoreboardForDate(String jsonFile,
            Integer mMonth, Integer mDay, Integer mYear)
    {
        String json = null;
        List<HNICGame> gameList = null;

        String selectedDate = null;

        String strFileDate = null;

        try
        {

            strFileDate = mYear.toString() + mMonth.toString()
                    + mDay.toString();

            SimpleDateFormat df1 = new SimpleDateFormat("yyyymdd");

            SimpleDateFormat df2 = new SimpleDateFormat("yyyymmdd");

            strFileDate = mYear.toString() + mMonth.toString()
                    + mDay.toString();

            selectedDate = df2.format(df1.parse(strFileDate));

            String jsonFileWithDate = jsonFile + selectedDate + ".json";

            Log.d(TAG, "------------" + jsonFileWithDate + "-----------");

            URL url = new URL(jsonFileWithDate);
            URLConnection connection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);
            json = buffer.toString();
            Gson gson = new Gson();

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            gameList = gson.fromJson(json, listType);

           /* if (gameList != null)
            {
                for (HNICGame game : gameList)
                {
                    // logger.debug(boxScore);

                    Log.d(TAG, "game : " + game.getAway());

                }
            }*/
        } catch (Exception e)
        {
            // logger.error(e);

            Log.e(TAG, "-------what exactly happened here " + e.toString());
        }
        return gameList;
    }

    public List<HNICGame> readScheduleScoreboardForTeam(String jsonFile)
    {
        String json = null;
        List<HNICGame> gameList = null;

        String selectedDate = null;

        String strFileDate = null;

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

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            gameList = gson.fromJson(json, listType);

            /*if (gameList != null)
            {
                for (HNICGame game : gameList)
                {
                    // logger.debug(boxScore);

                    Log.d(TAG, "game : " + game.getAway() + game.getHome());

                }
            }*/
        } catch (Exception e)
        {
            // logger.error(e);

            Log.e(TAG, "-------what exactly happened here " + e.toString());
        }
        return gameList;
    }

    public String readScheduleScoreboardForTeamAndReturnNextGameDate(
            String jsonFile)
    {
        String json = null;
        List<HNICGame> gameList = null;

        String strFileDate = null;

        String oneGameDate = null;

        String nextGameDate = null;

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

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            gameList = gson.fromJson(json, listType);

            // get an instance
            Calendar cal = Calendar.getInstance();

            // initialise the start or current date
            Date today = cal.getTime();

            if (gameList != null)
            {
                for (HNICGame game : gameList)
                {

                    strFileDate = game.getStart_date_time();

                    SimpleDateFormat dfstartDate = new SimpleDateFormat(
                            "yyyyMMdd'T'HH:mm:ssZ");

                    Date thisGameDate = dfstartDate.parse(strFileDate);

                    SimpleDateFormat df1 = new SimpleDateFormat(
                            "yyyyMMdd'T'HH:mm:ssZ");

                    SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                    oneGameDate = df2.format(df1.parse(strFileDate));

                    Log.d(TAG, "-------------the date that was parsed is "
                            + oneGameDate);

                    if (thisGameDate.after(today))
                    {
                        try
                        {

                            /*
                             * SimpleDateFormat df1 = new SimpleDateFormat(
                             * "yyyyMMdd'T'HH:mm:ssZ");
                             * 
                             * SimpleDateFormat df2 = new
                             * SimpleDateFormat("E M/d");
                             */

                            nextGameDate = df2.format(df1.parse(strFileDate));

                        } catch (ParseException e)
                        {
                            Log.e(TAG,
                                    "Parsing of date exception occured"
                                            + e.toString());

                        }

                        return nextGameDate;

                    } else
                    {
                        Log.d(TAG,
                                "---------there are no games after today for this team");
                    }

                }

            }
        } catch (Exception e)
        {
            // logger.error(e);

            Log.e(TAG, "-------what exactly happened here " + e.toString());
        }
        return nextGameDate;

    }

    public List<HNICGame> readScheduleScoreboardAndWriteToCache(String jsonFile)
    {
        String json = null;
        List<HNICGame> gameList = null;

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

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            gameList = gson.fromJson(json, listType);

            /* start of writing file to sdcard */

            // Path to write files to
            /*
             * String path = Environment.getExternalStorageDirectory()
             * .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
             */

            String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files/";

            String fname = "schedule_scoreboard.json";

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

            if (gameList != null)
            {
                for (HNICGame game : gameList)
                {
                    // logger.debug(boxScore);

                    // Log.d(TAG,"game : " + game.getAway());

                }
            }
        } catch (Exception e)
        {
            // logger.error(e);
        }
        return gameList;
    }

    public List<HNICGame> readScheduleScoreboardFromCache(String jsonFile)
    {
        String json = null;
        List<HNICGame> gameList = null;

        try
        {

            /*
             * File file = new
             * File("/sdcard/Android/data/hnic/files/schedule_scoreboard.json");
             */

            Log.d(TAG,
                    "----------start of reading schedule_scoreboard.json from cache-------");

            File file = new File(
                    "/data/data/com.troubadorian.mobile.android.nhlhockey/files/schedule_scoreboard.json");

            json = this.grabAsSingleString(file);

            Gson gson = new Gson();

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            gameList = gson.fromJson(json, listType);

            if (gameList != null)
            {
                for (HNICGame game : gameList)
                {
                    // logger.debug(boxScore);

                    // Log.d(TAG,"game : " + game.getAway());
                    
                    
                    

                }
            }

            Log.d(TAG,
                    "------------end of reading schedule_scoreboard.json from cache----");

        } catch (Exception e)
        {
            // logger.error(e);
            Log.d(TAG,
                    "!-----------there was an error reading schedule_scoreboard.json from cache----!");

        }
        return gameList;
    }

    
    
    public List<HNICGame> readFutureGames(String liveScheduleScoreboardsUrl)
    {
        // TODO Auto-generated method stub
        String json = null;
        List<HNICGame> listofgames = null;

        try
        {
            URL url = new URL(liveScheduleScoreboardsUrl);
            URLConnection connection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);
            json = buffer.toString();
            Gson gson = new Gson();

            Type listType = new TypeToken<List<HNICGame>>()
            {
            }.getType();
            listofgames = gson.fromJson(json, listType);

            if (listofgames != null)
            {
                for (HNICGame game : listofgames)
                {
                    // logger.debug(boxScore);

                    // Log.d(TAG,"game : " + game.getAway());

                }
            }
        } catch (Exception e)
        {
            // logger.error(e);
        }
        Calendar cal = Calendar.getInstance();

        // initialise the start or current date
        Date today = cal.getTime();

        if (listofgames != null)
        {
            for (HNICGame onegame : listofgames)
            {

                String strFileDate = onegame.getStart_date_time();

                SimpleDateFormat dfstartDate = new SimpleDateFormat(
                        "yyyyMMdd'T'HH:mm:ssZ");

                Date startDateDate = null;
                try
                {
                    startDateDate = dfstartDate.parse(strFileDate);
                } catch (ParseException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (startDateDate.before(today))
                {

                } else
                {
                    futuregameList.add(onegame);
                }
            }
        }

        return futuregameList;

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