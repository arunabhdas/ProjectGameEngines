/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

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
import com.troubadorian.mobile.android.model.HNICCompletedGameResultsReader;
import com.troubadorian.mobile.android.model.HNICCompletedGameResultsResponse;
import com.troubadorian.mobile.android.model.HNICResult;



public class MyHNICCompletedGameResultsReaderActivity extends Activity
{
    public static final String TAG = "MyHNICConferenceListReaderActivity";

    String url = "http://www.cbc.ca/data/statsfeed/plist/completedgameresults.json";

    /* String url = "http://search.twitter.com/search.json?q=javacodegeeks"; */

    List<HNICCompletedGameResultsResponse> retrievedresult = null;

    List<HNICResult> re = null;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hnicreaderactivity_main);

        String completedGameResultssUrl = "http://www.cbc.ca/data/statsfeed/plist/completedboxscore.json";

        HNICCompletedGameResultsReader hhniccompletedgameresultsreader = new HNICCompletedGameResultsReader();
/*
        re = hhniccompletedgameresultsreader
                .readCompletedGameResults(completedGameResultssUrl);

        for (HNICResult r : re)
        {

            Log.d(TAG, "------------------" + r.toString());
        }
*/
    }

}
