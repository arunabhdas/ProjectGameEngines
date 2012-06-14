/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.myjson.Gson;
import com.troubadorian.mobile.android.nhlhockey.RetrievedResult;
import com.troubadorian.mobile.android.nhlhockey.SearchResponse;




public class MyHNICGameResultsReaderActivity extends Activity
{

    public static final String TAG = "MyHNICConferenceListReaderActivity";

   /* String url = "http://www.cbc.ca/data/statsfeed/plist/completedgameresults.json";*/
    
    String url = "http://search.twitter.com/search.json?q=javacodegeeks";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hnicreaderactivity_main);

        InputStream source = retrieveStream(url);

        Gson gson = new Gson();

        Reader reader = new InputStreamReader(source);

        SearchResponse response

        = gson.fromJson(reader, SearchResponse.class);

        List<RetrievedResult> retrievedresult = response.results;
        
        for (RetrievedResult r : retrievedresult)
        {
            Log.d(TAG, r.fromUser);
        }

      
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