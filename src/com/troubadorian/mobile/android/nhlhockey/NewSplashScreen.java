/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





import com.troubadorian.mobile.android.model.HNICBoxScore;
import com.troubadorian.mobile.android.model.HNICCompletedBoxScoreReader;
import com.troubadorian.mobile.android.model.HNICCompletedGameResultsReader;
import com.troubadorian.mobile.android.model.HNICCompletedGameResultsResponse;
import com.troubadorian.mobile.android.model.HNICGame;
import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICPlayersReader;
import com.troubadorian.mobile.android.model.HNICPromo;
import com.troubadorian.mobile.android.model.HNICPromosReader;
import com.troubadorian.mobile.android.model.HNICScheduleScoreboardReader;
import com.troubadorian.mobile.android.model.HNICTeam;
import com.troubadorian.mobile.android.nhlhockey.NHLHockey.DataLoadingTask;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewSplashScreen extends Activity 
{

    protected boolean _active = true;

    /* protected int _splashTime = 7500; */

    /* protected int _splashTime = 10000; */

    List<HNICCompletedGameResultsResponse> completedGameResultsResponse = null;

    List<HNICPromo> hnicpromos = null;

    List<HNICBoxScore> boxScoreList = null;

    List<Map<String, Object>> completedboxscorelist = new ArrayList<Map<String, Object>>();

    List<HNICGame> gameList = null;

    List<HNICPlayer> theList = null;

    List<HNICPlayer> theSortedList = null;

    List<HNICTeam> theSortedTeamList = null;

    protected int _splashTime = 3000;

    protected int _additionalSplashTime = 3000;

    private MediaPlayer mMediaPlayer;

    /* private String _siteId = "10311"; */

    private String _siteId = "9063";

    private String _partnerId = "387131ac6a1aa80d";

    private Thread splashTread;

    Drawable drawablefordisplay;

    public static final String TAG = "NewSplash";

    public static final String C3 = "Launched App";

    ProgressDialog myProgress;

    Button buttonStartProgress;

    public class BackgroundAsyncTask extends AsyncTask<Void, Void, Void>
    {

        int myProgressCounter;

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            /*
             * Toast.makeText(NewSplashScreen.this, "onPostExecute",
             * Toast.LENGTH_LONG).show();
             */

            myProgress.hide();

            myProgress = null;

            /* startActivity(new Intent("ca.cbc.mobile.android.hnic.CBCHNIC")); */

            Intent intenthome = new Intent("com.troubadorian.mobile.android.nhlhockey.NHLHockey");
            intenthome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intenthome);

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            /*
             * Toast.makeText(NewSplashScreen.this, "onPreExecute",
             * Toast.LENGTH_LONG).show();
             */
            myProgress = new ProgressDialog(NewSplashScreen.this);

            myProgress.setMessage("Welcome to NHL Hockey 2011");

            myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            myProgress.setCancelable(true);

            myProgress.show();

            myProgressCounter = 0;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            readTeamsHouseKeeping();

            /* disabling to speed up splash */

            readItemsHouseKeeping();

            readItems();
            /* publishProgress(myProgressCounter); */
            // TODO Auto-generated method stub

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            /* myProgress.setProgress(values[0]); */
            /* myProgress.setProgress(values[0]); */

        }

    }

    public class OfflineBackgroundAsyncTask extends AsyncTask<Void, Void, Void>
    {

        int myProgressCounter;

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            /*
             * Toast.makeText(NewSplashScreen.this, "onPostExecute",
             * Toast.LENGTH_LONG).show();
             */

            myProgress.hide();

            myProgress = null;

            /* startActivity(new Intent("ca.cbc.mobile.android.hnic.NHLHockey")); */

            Intent intenthome = new Intent("com.troubadorian.mobile.android.nhlhockey.NHLHockey");
            
            intenthome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            
            startActivity(intenthome);

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            /*
             * Toast.makeText(NewSplashScreen.this, "onPreExecute",
             * Toast.LENGTH_LONG).show();
             */
            myProgress = new ProgressDialog(NewSplashScreen.this);

            myProgress.setMessage("Welcome to NHL Hockey 2011");

            myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            myProgress.setCancelable(true);

            myProgress.show();

            myProgressCounter = 0;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            readItemsHouseKeeping();

            readTeamsHouseKeepingFromCache();

            /* disabling to speed up splash */

            /*readItems();*/
            /* publishProgress(myProgressCounter); */
            // TODO Auto-generated method stub

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            /* myProgress.setProgress(values[0]); */
            /* myProgress.setProgress(values[0]); */

        }

    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsplash);
      /*   start of Omniture code block 
        AppMeasurement s;
        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 

        s.account = "cbc-mobile-prod-9";
   

        s.channel = "android-app:hockey-app";

        s.prop1 = "android-app:hockey-app";

        s.prop2 = "android-app:hockey-app:"
                + this.getString(R.string.app_version);

        s.prop3 = "android-app:hockey-app:"
                + this.getString(R.string.app_version) + C3;

        s.prop4 = "page";
         s.prop4 = this.getString(R.string.app_version); 

        s.pageName = "android-app:hockey-app:"
                + this.getString(R.string.app_version) + ":launched app";

        s.currencyCode = "CAD";
         Turn on and configure debugging here 
        s.debugTracking = true;
        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();
*/
        /*AdMarvelView adMarvelView = (AdMarvelView) findViewById(R.id.ad);
        adMarvelView.setEnableClickRedirect(true);
        adMarvelView.setDisableAnimation(false);
        adMarvelView.setListener(this);
        Map<String, String> targetParams = new HashMap<String, String>();*/


        // Only retrieve admarvel ad if Wifi or 3G is connected
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // mobile

        State mobile = conMan.getNetworkInfo(0).getState();

        // wifi

        State wifi = conMan.getNetworkInfo(1).getState();

        if (mobile == NetworkInfo.State.CONNECTED
                || mobile == NetworkInfo.State.CONNECTING)
        {
            _active = true;
            /*adMarvelView.requestNewAd(targetParams, _partnerId, _siteId);*/

            CharSequence text = "Standard data charges may apply";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(getBaseContext(), text, duration);

            toast.show();
            new BackgroundAsyncTask().execute();

        } else if (wifi == NetworkInfo.State.CONNECTED
                || wifi == NetworkInfo.State.CONNECTING)
        {

            _active = true;
            /*adMarvelView.requestNewAd(targetParams, _partnerId, _siteId);*/
            new BackgroundAsyncTask().execute();
        } else
        {

            CharSequence text = "...No Internet Connection...Data may be old...";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(getBaseContext(), text, duration);

            toast.show();

            new OfflineBackgroundAsyncTask().execute();

            /* startActivity(new Intent("ca.cbc.mobile.android.hnic.NHLHockey")); */

        }
        /* myProgress.setProgress(0); */
    }

    private void readTeamsHouseKeeping()
    {
        /* start of writing teams to database */

        try
        {
            theSortedTeamList = readConferenceList();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            /* e.printStackTrace(); */
            Log.d(TAG,
                    "--------------------something bad may have happened on the way to reading the conferencelist");
        }

        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        int numrowsdeleted = databaseDataLayer.DeleteTeams();

        Log.d(TAG, "---------the following rows were deleted------------"
                + numrowsdeleted);

        int i = 0;
        for (final HNICTeam team : theSortedTeamList)
        {
            i++;
            databaseDataLayer.AddTeam(i, team.getFullname(),
                    team.getTeamname(), team.getAbbr(), team.getCity(),
                    team.getRank_conference(), team.getRank_division(),
                    team.getPlayed(), team.getWon(), team.getLost(),
                    team.getOT(), team.getPoints(), team.getGoalsfor(),
                    team.getGoalsagainst());

        }

        /* end of writing teams to database */
    }

    private void readTeamsHouseKeepingFromCache()
    {
        /* start of writing teams to database */

        try
        {
            theSortedTeamList = readConferenceListFromCache();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            /* e.printStackTrace(); */
            Log.d(TAG,
                    "--------------------something bad may have happened on the way to reading the conferencelist");
        }

        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        int numrowsdeleted = databaseDataLayer.DeleteTeams();

        Log.d(TAG, "---------the following rows were deleted------------"
                + numrowsdeleted);

        int i = 0;
        for (final HNICTeam team : theSortedTeamList)
        {
            i++;
            databaseDataLayer.AddTeam(i, team.getFullname(),
                    team.getTeamname(), team.getAbbr(), team.getCity(),
                    team.getRank_conference(), team.getRank_division(),
                    team.getPlayed(), team.getWon(), team.getLost(),
                    team.getOT(), team.getPoints(), team.getGoalsfor(),
                    team.getGoalsagainst());

        }

        /* end of writing teams to database */
    }

    private void readItemsHouseKeeping()
    {
        /* start of copying files from assets to cache */

        AssetManager assetManager = getAssets();

        String[] files = null;
        try
        {
            files = assetManager.list("cachefiles");

            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);
            Log.d(TAG, "-----------how many files are in the folder"
                    + files.length);

        } catch (IOException e)
        {
            Log.e(TAG, "---------was there a problem" + e.getMessage());
        }
        for (int i = 0; i < files.length; i++)
        {
            InputStream in = null;
            OutputStream out = null;

            /*
             * String path = Environment.getExternalStorageDirectory()
             * .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
             */

            String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files/";

            /* getFilesDir() will create the /files folder */
            File file = getFilesDir();

            Log.d(TAG, "---------------the path is " + path);
            Log.d(TAG, "---------------the path is " + path);
            Log.d(TAG, "---------------the path is " + path);
            Log.d(TAG, "---------------the path is " + path);

            try
            {
                in = assetManager.open("cachefiles/" + files[i]);
                out = new FileOutputStream(path + files[i]);

                Log.d(TAG,
                        "-------what's really happening here-----"
                                + out.toString());

                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            }

            catch (IOException ex)
            {
                Log.e(TAG,
                        "------------------------------bad things"
                                + ex.getMessage());
            }

        }

        /* end of copying files from assets to cache */

        /* start of writing players to database */

        /*
         * HNICPlayersReader hnicplayersreader = new HNICPlayersReader();
         * 
         * theList = hnicplayersreader.readPlayers(theUrl);
         * 
         * theList = hnicplayersreader.readPlayersFromCache("allplayers2.json");
         * 
         * theSortedList = this.sortBy(theList, "Name");
         * 
         * theSortedList = theList;
         * 
         * DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
         * getBaseContext());
         * 
         * int numrowsdeleted = databaseDataLayer.DeletePlayers();
         * 
         * Log.d(TAG, "---------the following rows were deleted------------" +
         * numrowsdeleted);
         * 
         * int i = 0; for (final HNICPlayer player : theSortedList) { i++;
         * databaseDataLayer.AddPlayer(i, player.getName(),
         * player.getPosition(), player.getTeam());
         * 
         * }
         */

        /* end of writing players to database */

    }

    private void readItems()
    {

        String strFileDate = null;

        String startDate = null;

        String startTime = null;

        Map<String, Object> completedboxscoremap = null;

        Log.d(TAG,
                "--------------------------------------------reading completedboxscore.json----------------");

        // -----------------------------1------------read completedboxscore.json
        try
        {
            /* Log.d(TAG, "reading completed box score url"); */

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            String completedBoxScoresUrl;

            if ((mobile == NetworkInfo.State.CONNECTED)
                    || (wifi == NetworkInfo.State.CONNECTED))
            {

                Log.d(TAG,
                        "---------------------------------------------starting of reading completedboxscore.json-----");
                completedBoxScoresUrl = "http://www.cbc.ca/data/statsfeed/plist/completedboxscore.json";
                HNICCompletedBoxScoreReader hniccompletedboxscorereader = new HNICCompletedBoxScoreReader();

                /*
                 * boxScoreList = hniccompletedboxscorereader
                 * .readBoxScores(completedBoxScoresUrl);
                 */

                boxScoreList = hniccompletedboxscorereader
                        .readBoxScoresAndWriteToCache(completedBoxScoresUrl);
                Log.d(TAG,
                        "---------------------------------------------end of reading completedboxscore.json-----");

            } else
            {

                CharSequence text = "...Network Connection Failure...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);

                toast.show();

            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());
        }

        finally
        {

        }

        try
        {

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            String liveScheduleScoreboardUrl;

            if ((mobile == NetworkInfo.State.CONNECTED)
                    || (wifi == NetworkInfo.State.CONNECTED))
            {
                Log.d(TAG,
                        "---------------------------------------------starting of reading schedule_scoreboard.json-----");

                liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json";

                HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

                gameList = hnicschedulescoreboardreader
                        .readScheduleScoreboardAndWriteToCache(liveScheduleScoreboardUrl);
                Log.d(TAG,
                        "---------------------------------------------end of reading schedule_scoreboard.json-----");

            } else
            {

                CharSequence text = "...Network Connection Failure...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);

                toast.show();

            }

            Log.d(TAG,
                    "--------------------------------------------reading gameList-----------------");

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());
        }

        finally
        {

        }

        // -------------------------------------4------read
        // completedgameresults.json

        try
        {

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            String completedGameResultssUrl;

            if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                    || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
            {

                completedGameResultssUrl = "http://www.cbc.ca/data/statsfeed/plist/completedgameresults.json";
                Log.d(TAG,
                        "--------------------------------------------start of reading completedgameresults.json");

                HNICCompletedGameResultsReader hhniccompletedgameresultsreader = new HNICCompletedGameResultsReader();

                completedGameResultsResponse = hhniccompletedgameresultsreader
                        .readCompletedGameResultsAndWriteToCache(completedGameResultssUrl);
                Log.d(TAG,
                        "--------------------------------------------end of reading completedgameresults.json");
            } else
            {

                CharSequence text = "...Network Connection Failure...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);

                toast.show();

                /*
                 * completedGameResultssUrl = "completedgameresults.json";
                 * 
                 * HNICCompletedGameResultsReader
                 * hhniccompletedgameresultsreader = new
                 * HNICCompletedGameResultsReader();
                 * 
                 * completedGameResultsResponse =
                 * hhniccompletedgameresultsreader
                 * .readCompletedGameResultsFromCache(completedGameResultssUrl);
                 * 
                 * Log.d(TAG,
                 * "---------------------------------------------read completedgameresults.json from cache"
                 * );
                 */
            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());

            Log.e(TAG,
                    "----------there is a pretty good chance that something bad may have happened");

        }

        finally
        {

        }

        // -------------------------------------5---------read promoconfig.json

        try
        {

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            String promosUrl;

            if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                    || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
            {

                /*
                 * promosUrl =
                 * "http://www.cbc.ca/m/config/hnic/promos/promoconfig.json";
                 * 
                 * HNICPromosReader hnicpromosreader = new HNICPromosReader();
                 * 
                 * hnicpromos = hnicpromosreader.readPromos(promosUrl);
                 */
            } else
            {
                /* promosUrl = "file:///android_asset/promoconfig.json"; */

                CharSequence text = "...Network Connection Failure...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);

                toast.show();

                /*
                 * promosUrl = "promoconfig.json";
                 * 
                 * HNICPromosReader hnicpromosreader = new HNICPromosReader();
                 * 
                 * hnicpromos = hnicpromosreader.readPromosFromCache(promosUrl);
                 * 
                 * Log.d(TAG,
                 * "---------------------------------------------read promoconfig.json from cache"
                 * );
                 */
            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());

            Log.e(TAG, "----------Is there something wrong with promos");
        }

        finally
        {

        }

        /* promImage = (ImageView) findViewById(R.id.promImage); */
        try
        {

            /*
             * drawablefordisplay =
             * loadImageFromWebOperations(hnicpromos.get(1).imageUrl);
             */

            /*
             * drawablefordisplay =loadImageFromWebOperations(
             * "http://cbc.ca/m/config/hnic/promos/WM7_BeingErica_480x105.JPG");
             * 
             * 
             * drawablefordisplay = loadImageFromWebOperations(
             * "http://www.cbc.ca/m/config/hnic/promos/fantasypool-320x70.JPG");
             */

        } catch (Exception e)
        {
            Log.e(TAG, "----------something bad just happened");

        }

    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

    private Drawable loadImageFromWebOperations(String url)
    {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e)
        {
            System.out.println("Exc=" + e);
            return null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Log.d(TAG, "BACK KEY PRESSED");
            _active = false;
            finish();

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            /* _active = false; */
            /*
             * added the following line so that user cannot override the splash
             * screen
             */
            _active = true;
        }
        return _active;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }



    private List<HNICTeam> readConferenceList() throws IOException
    {

        List<HNICTeam> theListAll = null;
        try
        {

            Log.d(TAG,
                    "-------------------------reading of conferencelist.json started--------");

            String theUrl = "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                    || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
            {

                /*
                 * theUrl =
                 * "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";
                 * HNICConferenceListReader hnicconferencelistreader = new
                 * HNICConferenceListReader();
                 * 
                 * 
                 * theList =
                 * hnicconferencelistreader.readConferenceList(theUrl);
                 */
                /* start of conferencelist parsing */

                String json = null;

                try
                {

                    URL url = new URL(theUrl);
                    URLConnection connection = url.openConnection();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    StringBuffer buffer = new StringBuffer();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null)
                        buffer.append(inputLine);
                    json = buffer.toString();

                    List<HNICTeam> allTeams = new ArrayList<HNICTeam>();

                    List<HNICTeam> northeastTeams = new ArrayList<HNICTeam>();

                    List<HNICTeam> atlanticTeams = new ArrayList<HNICTeam>();

                    List<HNICTeam> southeastTeams = new ArrayList<HNICTeam>();

                    List<HNICTeam> centralTeams = new ArrayList<HNICTeam>();

                    List<HNICTeam> pacificTeams = new ArrayList<HNICTeam>();

                    List<HNICTeam> northwestTeams = new ArrayList<HNICTeam>();

                    JSONObject jsonObj = new JSONObject(json);

                    /* 1--------------start of All ------------- */

                    JSONObject jsonEastern10 = jsonObj.getJSONObject("Eastern");

                    JSONArray jsonNortheast0 = jsonEastern10
                            .getJSONArray("Northeast");
                    for (int i = 0; i < jsonNortheast0.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonNortheast0
                                .getJSONObject(i);

                        oneTeam.setFullname(jsonOneObject.getString("fullname"));

                        oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                        oneTeam.setCity(jsonOneObject.getString("city"));

                        oneTeam.setRank_conference(jsonOneObject
                                .getString("rank-conference"));

                        oneTeam.setRank_division(jsonOneObject
                                .getString("rank-division"));

                        oneTeam.setPlayed(jsonOneObject.getString("won"));

                        oneTeam.setWon(jsonOneObject.getString("lost"));

                        oneTeam.setLost(jsonOneObject.getString("points"));

                        oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                        oneTeam.setPoints(jsonOneObject
                                .getString("goalsagainst"));
                        try
                        {
                            allTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                    }
                    JSONObject jsonEastern20 = jsonObj.getJSONObject("Eastern");

                    JSONArray jsonAtlantic0 = jsonEastern20
                            .getJSONArray("Atlantic");
                    for (int i = 0; i < jsonAtlantic0.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonAtlantic0
                                .getJSONObject(i);

                        oneTeam.setFullname(jsonOneObject.getString("fullname"));

                        oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                        oneTeam.setCity(jsonOneObject.getString("city"));

                        oneTeam.setRank_conference(jsonOneObject
                                .getString("rank-conference"));

                        oneTeam.setRank_division(jsonOneObject
                                .getString("rank-division"));

                        oneTeam.setPlayed(jsonOneObject.getString("won"));

                        oneTeam.setWon(jsonOneObject.getString("lost"));

                        oneTeam.setLost(jsonOneObject.getString("points"));

                        oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                        oneTeam.setPoints(jsonOneObject
                                .getString("goalsagainst"));
                        try
                        {
                            allTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                    }

                    JSONObject jsonEastern30 = jsonObj.getJSONObject("Eastern");

                    JSONArray jsonSoutheast0 = jsonEastern30
                            .getJSONArray("Southeast");
                    for (int i = 0; i < jsonSoutheast0.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonSoutheast0
                                .getJSONObject(i);

                        oneTeam.setFullname(jsonOneObject.getString("fullname"));

                        oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                        oneTeam.setCity(jsonOneObject.getString("city"));

                        oneTeam.setRank_conference(jsonOneObject
                                .getString("rank-conference"));

                        oneTeam.setRank_division(jsonOneObject
                                .getString("rank-division"));

                        oneTeam.setPlayed(jsonOneObject.getString("won"));

                        oneTeam.setWon(jsonOneObject.getString("lost"));

                        oneTeam.setLost(jsonOneObject.getString("points"));

                        oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                        oneTeam.setPoints(jsonOneObject
                                .getString("goalsagainst"));
                        try
                        {
                            allTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                    }

                    JSONObject jsonWestern10 = jsonObj.getJSONObject("Western");

                    JSONArray jsoncentral0 = jsonWestern10
                            .getJSONArray("Central");
                    for (int i = 0; i < jsoncentral0.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsoncentral0
                                .getJSONObject(i);

                        oneTeam.setFullname(jsonOneObject.getString("fullname"));

                        oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                        oneTeam.setCity(jsonOneObject.getString("city"));

                        oneTeam.setRank_conference(jsonOneObject
                                .getString("rank-conference"));

                        oneTeam.setRank_division(jsonOneObject
                                .getString("rank-division"));

                        oneTeam.setPlayed(jsonOneObject.getString("won"));

                        oneTeam.setWon(jsonOneObject.getString("lost"));

                        oneTeam.setLost(jsonOneObject.getString("points"));

                        oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                        oneTeam.setPoints(jsonOneObject
                                .getString("goalsagainst"));
                        try
                        {
                            allTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                    }
                    JSONObject jsonWestern20 = jsonObj.getJSONObject("Western");

                    JSONArray jsonPacific0 = jsonWestern20
                            .getJSONArray("Pacific");
                    for (int i = 0; i < jsonPacific0.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonPacific0
                                .getJSONObject(i);

                        oneTeam.setFullname(jsonOneObject.getString("fullname"));

                        oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                        oneTeam.setCity(jsonOneObject.getString("city"));

                        oneTeam.setRank_conference(jsonOneObject
                                .getString("rank-conference"));

                        oneTeam.setRank_division(jsonOneObject
                                .getString("rank-division"));

                        oneTeam.setPlayed(jsonOneObject.getString("won"));

                        oneTeam.setWon(jsonOneObject.getString("lost"));

                        oneTeam.setLost(jsonOneObject.getString("points"));

                        oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                        oneTeam.setPoints(jsonOneObject
                                .getString("goalsagainst"));
                        try
                        {
                            allTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                    }

                    JSONObject jsonWestern30 = jsonObj.getJSONObject("Western");

                    JSONArray jsonNorthwest0 = jsonWestern30
                            .getJSONArray("Northwest");
                    for (int i = 0; i < jsonNorthwest0.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonNorthwest0
                                .getJSONObject(i);

                        oneTeam.setFullname(jsonOneObject.getString("fullname"));

                        oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                        oneTeam.setCity(jsonOneObject.getString("city"));

                        oneTeam.setRank_conference(jsonOneObject
                                .getString("rank-conference"));

                        oneTeam.setRank_division(jsonOneObject
                                .getString("rank-division"));

                        oneTeam.setPlayed(jsonOneObject.getString("won"));

                        oneTeam.setWon(jsonOneObject.getString("lost"));

                        oneTeam.setLost(jsonOneObject.getString("points"));

                        oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                        oneTeam.setPoints(jsonOneObject
                                .getString("goalsagainst"));
                        try
                        {
                            allTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                    }

                    theListAll = allTeams;

                    /* --------------end of All ------------- */

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

                /* end of conferencelist parsing */
            } else
            {

                /*
                 * theUrl =
                 * "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";
                 * HNICConferenceListReader hnicconferencelistreader = new
                 * HNICConferenceListReader();
                 * 
                 * 
                 * theList =
                 * hnicconferencelistreader.readConferenceList(theUrl);
                 */

                Log.d(TAG,
                        "---------------------------------------------read conferencelist.json from cache");
            }

            Log.d(TAG,
                    "-------------------------reading of conferencelist.json ended--------");

        }

        finally
        {

        }

        return theListAll;

    }

    private List<HNICTeam> readConferenceListFromCache() throws IOException
    {

        List<HNICTeam> theListAll = null;

        Log.d(TAG,
                "-------------------------reading of conferencelist.json started--------");

        String theUrl = "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";

        /*
         * theUrl =
         * "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json";
         * HNICConferenceListReader hnicconferencelistreader = new
         * HNICConferenceListReader();
         * 
         * 
         * theList = hnicconferencelistreader.readConferenceList(theUrl);
         */
        /* start of conferencelist parsing */

        String json = null;

        try
        {

            File file = new File(
            "/data/data/ca.cbc.mobile.android.hnic/files/conferencelist.json");

            json = this.grabAsSingleString(file);


            List<HNICTeam> allTeams = new ArrayList<HNICTeam>();

            List<HNICTeam> northeastTeams = new ArrayList<HNICTeam>();

            List<HNICTeam> atlanticTeams = new ArrayList<HNICTeam>();

            List<HNICTeam> southeastTeams = new ArrayList<HNICTeam>();

            List<HNICTeam> centralTeams = new ArrayList<HNICTeam>();

            List<HNICTeam> pacificTeams = new ArrayList<HNICTeam>();

            List<HNICTeam> northwestTeams = new ArrayList<HNICTeam>();

            JSONObject jsonObj = new JSONObject(json);

            /* 1--------------start of All ------------- */

            JSONObject jsonEastern10 = jsonObj.getJSONObject("Eastern");

            JSONArray jsonNortheast0 = jsonEastern10.getJSONArray("Northeast");
            for (int i = 0; i < jsonNortheast0.length(); i++)
            {
                HNICTeam oneTeam = new HNICTeam();

                JSONObject jsonOneObject = jsonNortheast0.getJSONObject(i);

                oneTeam.setFullname(jsonOneObject.getString("fullname"));

                oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                oneTeam.setCity(jsonOneObject.getString("city"));

                oneTeam.setRank_conference(jsonOneObject
                        .getString("rank-conference"));

                oneTeam.setRank_division(jsonOneObject
                        .getString("rank-division"));

                oneTeam.setPlayed(jsonOneObject.getString("won"));

                oneTeam.setWon(jsonOneObject.getString("lost"));

                oneTeam.setLost(jsonOneObject.getString("points"));

                oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                oneTeam.setPoints(jsonOneObject.getString("goalsagainst"));
                try
                {
                    allTeams.add(i, oneTeam);
                } catch (Exception e)
                {
                    Log.d(TAG, "--------------------" + e.toString());
                }

            }
            JSONObject jsonEastern20 = jsonObj.getJSONObject("Eastern");

            JSONArray jsonAtlantic0 = jsonEastern20.getJSONArray("Atlantic");
            for (int i = 0; i < jsonAtlantic0.length(); i++)
            {
                HNICTeam oneTeam = new HNICTeam();

                JSONObject jsonOneObject = jsonAtlantic0.getJSONObject(i);

                oneTeam.setFullname(jsonOneObject.getString("fullname"));

                oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                oneTeam.setCity(jsonOneObject.getString("city"));

                oneTeam.setRank_conference(jsonOneObject
                        .getString("rank-conference"));

                oneTeam.setRank_division(jsonOneObject
                        .getString("rank-division"));

                oneTeam.setPlayed(jsonOneObject.getString("won"));

                oneTeam.setWon(jsonOneObject.getString("lost"));

                oneTeam.setLost(jsonOneObject.getString("points"));

                oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                oneTeam.setPoints(jsonOneObject.getString("goalsagainst"));
                try
                {
                    allTeams.add(i, oneTeam);
                } catch (Exception e)
                {
                    Log.d(TAG, "--------------------" + e.toString());
                }

            }

            JSONObject jsonEastern30 = jsonObj.getJSONObject("Eastern");

            JSONArray jsonSoutheast0 = jsonEastern30.getJSONArray("Southeast");
            for (int i = 0; i < jsonSoutheast0.length(); i++)
            {
                HNICTeam oneTeam = new HNICTeam();

                JSONObject jsonOneObject = jsonSoutheast0.getJSONObject(i);

                oneTeam.setFullname(jsonOneObject.getString("fullname"));

                oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                oneTeam.setCity(jsonOneObject.getString("city"));

                oneTeam.setRank_conference(jsonOneObject
                        .getString("rank-conference"));

                oneTeam.setRank_division(jsonOneObject
                        .getString("rank-division"));

                oneTeam.setPlayed(jsonOneObject.getString("won"));

                oneTeam.setWon(jsonOneObject.getString("lost"));

                oneTeam.setLost(jsonOneObject.getString("points"));

                oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                oneTeam.setPoints(jsonOneObject.getString("goalsagainst"));
                try
                {
                    allTeams.add(i, oneTeam);
                } catch (Exception e)
                {
                    Log.d(TAG, "--------------------" + e.toString());
                }

            }

            JSONObject jsonWestern10 = jsonObj.getJSONObject("Western");

            JSONArray jsoncentral0 = jsonWestern10.getJSONArray("Central");
            for (int i = 0; i < jsoncentral0.length(); i++)
            {
                HNICTeam oneTeam = new HNICTeam();

                JSONObject jsonOneObject = jsoncentral0.getJSONObject(i);

                oneTeam.setFullname(jsonOneObject.getString("fullname"));

                oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                oneTeam.setCity(jsonOneObject.getString("city"));

                oneTeam.setRank_conference(jsonOneObject
                        .getString("rank-conference"));

                oneTeam.setRank_division(jsonOneObject
                        .getString("rank-division"));

                oneTeam.setPlayed(jsonOneObject.getString("won"));

                oneTeam.setWon(jsonOneObject.getString("lost"));

                oneTeam.setLost(jsonOneObject.getString("points"));

                oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                oneTeam.setPoints(jsonOneObject.getString("goalsagainst"));
                try
                {
                    allTeams.add(i, oneTeam);
                } catch (Exception e)
                {
                    Log.d(TAG, "--------------------" + e.toString());
                }

            }
            JSONObject jsonWestern20 = jsonObj.getJSONObject("Western");

            JSONArray jsonPacific0 = jsonWestern20.getJSONArray("Pacific");
            for (int i = 0; i < jsonPacific0.length(); i++)
            {
                HNICTeam oneTeam = new HNICTeam();

                JSONObject jsonOneObject = jsonPacific0.getJSONObject(i);

                oneTeam.setFullname(jsonOneObject.getString("fullname"));

                oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                oneTeam.setCity(jsonOneObject.getString("city"));

                oneTeam.setRank_conference(jsonOneObject
                        .getString("rank-conference"));

                oneTeam.setRank_division(jsonOneObject
                        .getString("rank-division"));

                oneTeam.setPlayed(jsonOneObject.getString("won"));

                oneTeam.setWon(jsonOneObject.getString("lost"));

                oneTeam.setLost(jsonOneObject.getString("points"));

                oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                oneTeam.setPoints(jsonOneObject.getString("goalsagainst"));
                try
                {
                    allTeams.add(i, oneTeam);
                } catch (Exception e)
                {
                    Log.d(TAG, "--------------------" + e.toString());
                }

            }

            JSONObject jsonWestern30 = jsonObj.getJSONObject("Western");

            JSONArray jsonNorthwest0 = jsonWestern30.getJSONArray("Northwest");
            for (int i = 0; i < jsonNorthwest0.length(); i++)
            {
                HNICTeam oneTeam = new HNICTeam();

                JSONObject jsonOneObject = jsonNorthwest0.getJSONObject(i);

                oneTeam.setFullname(jsonOneObject.getString("fullname"));

                oneTeam.setAbbr(jsonOneObject.getString("abbr"));

                oneTeam.setCity(jsonOneObject.getString("city"));

                oneTeam.setRank_conference(jsonOneObject
                        .getString("rank-conference"));

                oneTeam.setRank_division(jsonOneObject
                        .getString("rank-division"));

                oneTeam.setPlayed(jsonOneObject.getString("won"));

                oneTeam.setWon(jsonOneObject.getString("lost"));

                oneTeam.setLost(jsonOneObject.getString("points"));

                oneTeam.setPoints(jsonOneObject.getString("goalsfor"));

                oneTeam.setPoints(jsonOneObject.getString("goalsagainst"));
                try
                {
                    allTeams.add(i, oneTeam);
                } catch (Exception e)
                {
                    Log.d(TAG, "--------------------" + e.toString());
                }

            }

            theListAll = allTeams;

            /* --------------end of All ------------- */

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        /* end of conferencelist parsing */

        Log.d(TAG,
                "-------------------------reading of conferencelist.json ended--------");

        return theListAll;

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