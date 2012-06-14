/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;
import com.troubadorian.mobile.android.model.HNICBoxScore;
import com.troubadorian.mobile.android.model.HNICPromo;
import com.troubadorian.mobile.android.model.HNICResult;


public class HNICPlayersReader
{
    public static final String TAG = "HNICPlayersReader";

    String url = "http://www.cbc.ca/data/statsfeed/plist/players/allplayers2.json";


    List<HNICPlayer> retrievedresult = null;
    
 

    public List<HNICPlayer> readPlayers(String jsonFile)
    {
        

        InputStream source = retrieveStream(url);

        Reader reader = new InputStreamReader(source);

        Gson gson = new Gson();

        Type listType = new TypeToken<List<HNICPlayer>>()
        {
        }.getType();

        retrievedresult = gson.fromJson(reader, listType);

        if (retrievedresult != null)
        {

            for (HNICPlayer res : retrievedresult)
            {
                Log.d(TAG, "------------------" + res.getName());
                
              
            }
            
        }
        /*return re;*/
        
        return retrievedresult;
    }

    private InputStream retrieveStream(String url)
    {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(url);

        try
        {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK)
            {
                Log.w(getClass().getSimpleName(), "Error " + statusCode
                        + " for URL " + url);
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();

        } catch (IOException e)
        {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }

        return null;

    }
    
    public List<HNICPlayer> readPlayersFromCache(String jsonFile)
    {
        String json = null;
        
        List<HNICPlayer> retrievedresult = null;
        
        try
        {
            /*
             * File file = new File(
             * "/sdcard/Android/data/hnic/files/completedboxscore.json");
             */

            Log.d(TAG, "----------start of reading allplayers2.json from cache---------");
            
            File file = new File(
                    "/data/data/com.troubadorian.mobile.android.nhlhockey/files/allplayers2.json");

            json = this.grabAsSingleString(file);

            Gson gson = new Gson();

            Type listType = new TypeToken<List<HNICPlayer>>()
            {
            }.getType();
            retrievedresult = gson.fromJson(json, listType);
            
            int i = 0;
            if (retrievedresult != null)
            {
                for (HNICPlayer player : retrievedresult)
                {
                    /*i++;
                    Log.d(TAG, "---------" + i);*/
                    // logger.debug(boxScore);
                }
            }
            
            Log.d(TAG, "----------end of reading allplayers2.json from cache---------");   
        } catch (Exception e)
        {
            // logger.error(e);
            Log.d(TAG, "!----------------error reading allplayers2.json from cache-----!");
        }
        
        return retrievedresult;
    }
    
    public List<HNICPlayer> readPlayersFromAssetsFolder(String jsonFile)
    {
        String json = null;
        
        List<HNICPlayer> retrievedresult = null;
        
        try
        {
            /*
             * File file = new File(
             * "/sdcard/Android/data/hnic/files/completedboxscore.json");
             */

            Log.d(TAG, "----------start of reading allplayers2.json from assetsfolder---------");
            
            /*File file = new File(
                    "/data/data/ca.cbc.mobile.android.hnic/files/allplayers2.json");*/


   
            File file = new File("file:///android_asset/cachefiles/allplayers2.json");
            
            
            
            json = this.grabAsSingleString(file);

            Gson gson = new Gson();

            Type listType = new TypeToken<List<HNICPlayer>>()
            {
            }.getType();
            retrievedresult = gson.fromJson(json, listType);
            
            int i = 0;
            if (retrievedresult != null)
                
            {
                for (HNICPlayer player : retrievedresult)
                {
                    /*i++;
                    Log.d(TAG, "---------" + i);*/
                    // logger.debug(boxScore);
                }
            }
            
            Log.d(TAG, "----------end of reading allplayers2.json from assetsfolder---------");   
        } catch (Exception e)
        {
            // logger.error(e);
            Log.d(TAG, "!----------------error reading allplayers2.json from assetsfolder-----!");
        }
        
        return retrievedresult;
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
