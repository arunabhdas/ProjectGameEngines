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

import android.os.Environment;
import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;


public class HNICOverallLeadersReader
{

    private static final String TAG = HNICOverallLeadersReader.class.getName();

    // static Logger logger = Logger.getLogger(HNICBoxScoreReader.class);

    public HNICOverallLeaders readOverallLeaders(String jsonFile)
    {
        String json = null;
        HNICOverallLeaders leaders = null;

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

            Type type = new TypeToken<HNICOverallLeaders>()
            {
            }.getType();
            leaders = gson.fromJson(json, type);
            
            /* start of writing file to sdcard */

            // Path to write files to
           /* String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/Android/data/hnic/" + "/files/";*/
            
            String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files";
            
            
            String fname = "overallleaders.json";

            // Current state of the external media
            String extState = Environment.getExternalStorageState();

            // External media can be written onto
  /*          if (extState.equals(Environment.MEDIA_MOUNTED))
            {
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
            }*/

            /* end of writing file to sdcard */
            
            
            

            if (leaders != null)
            {
                // Log.d(TAG, leaders.toString());
            }
        } catch (Exception e)
        {
            // logger.error(e);
            Log.d(TAG, "------------" + e.toString() );
        }
        return leaders;
    }

    public HNICOverallLeaders readOverallLeadersFromCache(String jsonFile)
    {
        String json = null;
        HNICOverallLeaders leaders = null;

        try
        {
            File file = new File(
                    "/sdcard/Android/data/hnic/files/overallleaders.json");

            json = this.grabAsSingleString(file);
            Gson gson = new Gson();

            Type type = new TypeToken<HNICOverallLeaders>()
            {
            }.getType();
            leaders = gson.fromJson(json, type);

            if (leaders != null)
            {
                Log.d(TAG, leaders.toString());
            }
        } catch (Exception e)
        {
            Log.e(TAG, "------xxx------" + e.toString());
        }
        return leaders;
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
