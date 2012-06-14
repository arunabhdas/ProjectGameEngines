package com.troubadorian.mobile.android.model;


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


public class HNICTeamsReader
{
    public static final String TAG = "HNICTeamsReader";

    String url = "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";


    List<HNICTeam> retrievedresult = null;
    
 

    public List<HNICTeam> read(String jsonFile)
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

            for (HNICTeam res : retrievedresult)
            {
                Log.d(TAG, "------------------" + res.toString());
                
              
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
}
