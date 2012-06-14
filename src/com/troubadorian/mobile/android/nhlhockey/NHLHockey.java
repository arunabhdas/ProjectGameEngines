/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Xml.Encoding;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.webkit.CacheManager;
import android.webkit.WebView;
import android.webkit.CacheManager.CacheResult;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery.LayoutParams;



import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;





import com.troubadorian.mobile.android.model.HNICBoxScore;
import com.troubadorian.mobile.android.model.HNICCompletedBoxScoreReader;
import com.troubadorian.mobile.android.model.HNICCompletedGameResultsReader;
import com.troubadorian.mobile.android.model.HNICCompletedGameResultsResponse;
import com.troubadorian.mobile.android.model.HNICGame;
import com.troubadorian.mobile.android.model.HNICLiveBoxScoreReader;
import com.troubadorian.mobile.android.model.HNICPromo;
import com.troubadorian.mobile.android.model.HNICPromosReader;
import com.troubadorian.mobile.android.model.HNICResult;
import com.troubadorian.mobile.android.model.HNICScheduleScoreboardReader;
import com.troubadorian.mobile.android.model.HNICTeam;
import com.troubadorian.mobile.android.nhlhockey.NHLHockey.ImageAdapter;
import com.troubadorian.mobile.android.nhlhockey.Schedule.DataLoadingTask;
import com.troubadorian.mobile.android.nhlhockey.Schedule.MyCustomAdapter;
/**
 * MainEntryPoint
 */
public class NHLHockey extends Activity implements 
        AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory
{
    static final int REWINDSELECTOR = 6;

    ProgressDialog myProgress;

    ProgressDialog myProgressHouseKeeping;

    public static final String TAG = "NHLHockey";

    public static final String C2 = "Box Score";

    List<HNICBoxScore> boxScoreList = null;

    List<HNICBoxScore> liveboxScoreList = null;

    List<HNICCompletedGameResultsResponse> completedGameResultsResponse = null;

    List<HNICCompletedGameResultsResponse> liveGameResultsResponse = null;

    List<HNICPromo> hnicpromos = null;

    // declare button for home
    private Button mHomeButton;

    // declare button for news
    private Button mNewsButton;

    // declare button for schedule
    private Button mScheduleButton;

    // declare button for video
    private Button mVideoButton;

    // declare button for more
    private Button mMoreButton;

    /* private String _siteId = "1429"; */

    /* private String _siteId = "9063"; */

    private String _siteId = "4606";

    /* private String _partnerId = "1dd21b33bd603c95"; */

    private String _partnerId = "387131ac6a1aa80d";

    ImageView promImage;

    Drawable drawablefordisplay;

    TextView feedTitle;

    TextView feedDescribtion;

    TextView feedPubdate;

    TextView feedLink;

    List<HNICGame> gameList = null;

    String start_date;

    String start_time;

    List<Map<String, Object>> completedboxscorelist = new ArrayList<Map<String, Object>>();

    ImageView logoTeam1;

    ImageView logoTeam2;

    TextView textFinal;

    TextView textTeamNotifi;

    TextView textAwayTeamScore;

    TextView textAwayTeamSOG;

    TextView textHomeTeamScore;

    TextView textHomeTeamSOG;

    TextView textAwayTeamName;

    TextView textHomeTeamName;

    TextView textAwayTeam1, textAwayTeam2, textAwayTeam3, textAwayTeamOT,
            textAwayTeamT;

    TextView textHomeTeam1, textHomeTeam2, textHomeTeam3, textHomeTeamOT,
            textHomeTeamT;

    TextView textGameResultBoxP1;

    /* WebView textGameResultBoxP1; */

    TextView textGameResultBoxP2;

    /* WebView textGameResultBoxP2; */

    TextView textGameResultBoxP3;

    TextView textGameResultBoxOT;

    /* WebView textGameResultBoxP3; */

    private ImageSwitcher mSwitcherAway;

    private ImageSwitcher mSwitcherHome;

    private static final int FIRST = 1;

    private static final int CHANGELOG = 2;

    public class DataLoadingTaskHouseKeeping extends
            AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub

            myProgressHouseKeeping.hide();

            myProgressHouseKeeping = null;

            displayItemsHouseKeeping();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub

            myProgressHouseKeeping = new ProgressDialog(NHLHockey.this);

            myProgressHouseKeeping.setMessage("Housekeeping...");

            myProgressHouseKeeping
                    .setProgressStyle(ProgressDialog.STYLE_SPINNER);

            myProgressHouseKeeping.setCancelable(true);

            myProgressHouseKeeping.show();

            /*
             * myProgress = ProgressDialog.show(Schedule.this, "Please wait...",
             * "...loading...", true, false);
             */

            preReadItemsHouseKeeping();

        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            // TODO Auto-generated method stub
            readItemsHouseKeeping();
            return null;
        }

        @Override
        protected void onCancelled()
        {
            myProgressHouseKeeping.cancel();
            this.cancel(true);
        }

    }

    public class DataLoadingTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub

            myProgress.hide();

            myProgress = null;

            displayItems();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub

            myProgress = new ProgressDialog(NHLHockey.this);
            
           

            myProgress.setMessage("Waiting for the puck to drop...");

            myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            
            

           /* myProgress.setCancelable(true);*/

            myProgress.show();

            /*
             * myProgress = ProgressDialog.show(Schedule.this, "Please wait...",
             * "...loading...", true, false);
             */

            preReadItems();

        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            // TODO Auto-generated method stub
            readItemsFromCache();
            return null;
        }

        @Override
        protected void onCancelled()
        {
            myProgress.cancel();
            this.cancel(true);
        }

    }

    public class MyCustomAdapter extends ArrayAdapter<HNICGame>
    {

        public MyCustomAdapter(Context context, int textViewResourceId,
                List<HNICGame> gameList)
        {
            super(context, textViewResourceId, gameList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            // return super.getView(position, convertView, parent);

            View row = convertView;

            if (row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);
            listTitle.setText(gameList.get(position).getAway() + " @ "

            + gameList.get(position).getHome());
            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            listPubdate.setText(gameList.get(position).getStart_date_time());

            if (position % 2 == 0)
            {
                // listTitle.setBackgroundColor(0xff101010);

                // listTitle.setBackgroundColor(R.color.white);
                // listTitle.setTextColor(R.color.black);

                // listPubdate.setBackgroundColor(0xff101010);
                // listPubdate.setBackgroundColor(R.color.white);
                // listPubdate.setTextColor(R.color.black);

            } else
            {
                // listTitle.setBackgroundColor(0xff080808);
                // listTitle.setBackgroundColor(R.color.white);
                // listTitle.setTextColor(R.color.black);

                // listPubdate.setBackgroundColor(0xff080808);
                // listPubdate.setBackgroundColor(R.color.white);
                // listPubdate.setTextColor(R.color.black);

            }

            return row;
        }
    }

    public class MyCustomGameResultAdapter extends ArrayAdapter<HNICResult>
    {

        public MyCustomGameResultAdapter(Context context,
                int textViewResourceId,
                List<HNICResult> completedGameResultsResponse)
        {
            super(context, textViewResourceId, completedGameResultsResponse);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        { // TODO Auto-generated method stub // return
            super.getView(position, convertView, parent);

            View row = convertView;

            if (row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);
            listTitle.setText(completedGameResultsResponse.get(position)
                    .toString()
                    + " @ "

                    + gameList.get(position).getHome());

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            listPubdate
                    .setText(completedGameResultsResponse.get(position).results
                            .toString());

            if (position % 2 == 0)
            { // listTitle.setBackgroundColor(0xff101010);

                // listTitle.setBackgroundColor(R.color.white); //
                listTitle.setTextColor(R.color.black);

                // listPubdate.setBackgroundColor(0xff101010); //
                listPubdate.setBackgroundColor(R.color.white); //
                listPubdate.setTextColor(R.color.black);

            } else
            { // listTitle.setBackgroundColor(0xff080808); //
                listTitle.setBackgroundColor(R.color.white); //
                listTitle.setTextColor(R.color.black);

                // listPubdate.setBackgroundColor(0xff080808); //
                listPubdate.setBackgroundColor(R.color.white); //
                listPubdate.setTextColor(R.color.black);

            }

            return row;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putString("returning", "yesiam");

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        String myString = savedInstanceState.getString("returning");

        /*
         * Toast.makeText(this, "Instance stat was restored :" + myString,
         * Toast.LENGTH_LONG).show();
         */

    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();

    }

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        /* start of code for home and away switchers */

        textFinal = (TextView) findViewById(R.id.textFinal);

        textTeamNotifi = (TextView) findViewById(R.id.textTeamNotifi);

        mSwitcherAway = (ImageSwitcher) findViewById(R.id.switcher_away);

        mSwitcherHome = (ImageSwitcher) findViewById(R.id.switcher_home);

        textAwayTeamScore = (TextView) findViewById(R.id.textAwayTeamScore);

        textAwayTeamSOG = (TextView) findViewById(R.id.textAwayTeamSOG);

        textHomeTeamScore = (TextView) findViewById(R.id.textHomeTeamScore);

        textHomeTeamSOG = (TextView) findViewById(R.id.textHomeTeamSOG);

        textAwayTeamName = (TextView) findViewById(R.id.textAwayTeamName);

        textHomeTeamName = (TextView) findViewById(R.id.textHomeTeamName);

        textAwayTeam1 = (TextView) findViewById(R.id.textAwayTeam1);

        textAwayTeam2 = (TextView) findViewById(R.id.textAwayTeam2);

        textAwayTeam3 = (TextView) findViewById(R.id.textAwayTeam3);

        textAwayTeamOT = (TextView) findViewById(R.id.textAwayTeamOT);

        textAwayTeamT = (TextView) findViewById(R.id.textAwayTeamT);

        textHomeTeam1 = (TextView) findViewById(R.id.textHomeTeam1);

        textHomeTeam2 = (TextView) findViewById(R.id.textHomeTeam2);

        textHomeTeam3 = (TextView) findViewById(R.id.textHomeTeam3);

        textHomeTeamOT = (TextView) findViewById(R.id.textHomeTeamOT);

        textHomeTeamT = (TextView) findViewById(R.id.textHomeTeamT);

        textGameResultBoxP1 = (TextView) findViewById(R.id.textGameResultBoxP1);

        /*
         * textGameResultBoxP1 = (WebView)
         * findViewById(R.id.textGameResultBoxP1);
         */

        textGameResultBoxP2 = (TextView) findViewById(R.id.textGameResultBoxP2);

        /*
         * textGameResultBoxP2 = (WebView)
         * findViewById(R.id.textGameResultBoxP2);
         */

        textGameResultBoxP3 = (TextView) findViewById(R.id.textGameResultBoxP3);

        /*
         * textGameResultBoxP3 = (WebView)
         * findViewById(R.id.textGameResultBoxP3);
         */

        textGameResultBoxOT = (TextView) findViewById(R.id.textGameResultBoxOT);

        mSwitcherAway.setFactory(this);
        mSwitcherAway.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcherAway.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        mSwitcherHome.setFactory(this);
        mSwitcherHome.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcherHome.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        textAwayTeamScore.setAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));

        textHomeTeamScore.setAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));

        Gallery g = (Gallery) findViewById(R.id.galleryTopGames);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemSelectedListener(this);

        Gallery h = (Gallery) findViewById(R.id.promImage);
        h.setAdapter(new PromoImageAdapter(this));
        /* h.setOnItemSelectedListener(this); */
        /* end of code for home and away switchers */

        /* end of data stuff */
        /*AdMarvelView adMarvelView = (AdMarvelView) findViewById(R.id.ad);
        adMarvelView.setEnableClickRedirect(true);
        adMarvelView.setDisableAnimation(false);
        adMarvelView.setListener(this);
        Map<String, String> targetParams = new HashMap<String, String>();
        adMarvelView.requestNewAd(targetParams, _partnerId, _siteId);*/

        // Obtain handles to UI objects

        mHomeButton = (Button) findViewById(R.id.home_button);

        mNewsButton = (Button) findViewById(R.id.news_button);

        mScheduleButton = (Button) findViewById(R.id.schedule_button);

        mVideoButton = (Button) findViewById(R.id.video_button);

        mMoreButton = (Button) findViewById(R.id.more_button);

        // Register handler for mHomeButton button

        mHomeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
                // Log.d(TAG, "mHomeButton clicked");
                launchHomeViewer();

            }
        });

        // Register handler for mNewsButton button

        mNewsButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Log.d(TAG, "mNewsButton clicked");
                launchNewsViewer();
            }
        });

        // Register handler for mScheduleButton button

        mScheduleButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Log.d(TAG, "mScheduleButton clicked");
                launchScheduleViewer();
            }
        });

        // Register handler for mVideoButton button

        mVideoButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Log.d(TAG, "mScheduleButton clicked");
                launchVideoViewer();
            }
        });

        // Register handler for mMoreButton button

        mMoreButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Log.d(TAG, "mMoreButton clicked");
                launchMoreViewer();
            }
        });

     /*   AppMeasurement s;

        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 
         s.account = "cbc-mobile-dev-2"; 

        s.account = "cbc-mobile-prod-9";

         You may add or alter any code config here 

        s.channel = "android-app";

        s.prop1 = "android-app:hockey-app";

        s.prop2 = "android-app:hockey-app" + C2;

        s.prop4 = "page";

        s.pageName = "android-app:hockey-app:" + C2;

        s.currencyCode = "CAD";

         Turn on and configure debugging here 
        s.debugTracking = true;

        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();
*/
        /* functionality for exiting the app */
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }
        /* functionality for exiting the app */

        /* start of displaying app in notification area */
        String ns = Context.NOTIFICATION_SERVICE;

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);

        int icon = R.drawable.notification_icon;

        CharSequence tickerText = "NHL Hockey";

        long when = System.currentTimeMillis();

        Notification notification = new Notification(icon, tickerText, when);

        Context context = getApplicationContext();

        CharSequence contentTitle = "NHL Hockey";

        CharSequence contentText = "NHL Hockey 2011";

        Intent notificationIntent = new Intent(this, NHLHockey.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        notification.setLatestEventInfo(context, contentTitle, contentText,
                contentIntent);

        final int NHLHockey = 1;

        mNotificationManager.notify(NHLHockey, notification);

        /* end of displaying app in notification area */

        // Hook up button presses to the appropriate event handler.
        /*
         * ((Button) findViewById(R.id.home_button))
         * .setOnClickListener(mBackListener);
         */

        /*
         * String path = Environment.getExternalStorageDirectory()
         * .getAbsolutePath() + "/Android/data/hnic/" +
         * "/files/completedboxscore.json";
         */

        /* start of disabling offline browsing */

        /*
         * String path =
         * "/data/data/ca.cbc.mobile.android.hnic/files/completedboxscore.json";
         * 
         * boolean exists = (new File(path)).exists();
         * 
         * if (!exists)
         * 
         * { startReadItemsHouseKeeping();
         * 
         * } else { startReadItems(); }
         */

        /* start of disabling offline browsing */

        startReadItems();

    }

    private void startReadItemsHouseKeeping()
    {
        new DataLoadingTaskHouseKeeping().execute();
    }

    private void startReadItems()
    {
        new DataLoadingTask().execute();
    }

    private void preReadItemsHouseKeeping()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        // setListAdapter(null);

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void preReadItems()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        // setListAdapter(null);

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void readItemsHouseKeeping()
    {
        /* start of copying files from assets to cache */

        AssetManager assetManager = getAssets();

        String[] files = null;
        try
        {
            files = assetManager.list("");

        } catch (IOException e)
        {
            Log.e("tag", e.getMessage());
        }
        for (int i = 0; i < files.length; i++)
        {
            InputStream in = null;
            OutputStream out = null;
            /*
             * String path = Environment.getExternalStorageDirectory()
             * .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
             */

            String path = "/data/data/com.troubadorian.mobile.android.nhlhockey/files";

            try
            {
                in = assetManager.open(files[i]);
                out = new FileOutputStream(path + files[i]);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch (Exception e)
            {
                Log.e(TAG, "------------------------------bad things"
                        + e.getMessage());
            }
        }

        /* end of copying files from assets to cache */

        readItems();
    }

    /* start of readItems() */
    private void readItems()
    {

        String strFileDate = null;

        String startDate = null;

        String startTime = null;

        Map<String, Object> completedboxscoremap = null;

        Log
                .d(
                        TAG,
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

                Log
                        .d(
                                TAG,
                                "---------------------------------------------starting of reading completedboxscore.json-----");
                completedBoxScoresUrl = "http://www.cbc.ca/data/statsfeed/plist/completedboxscore.json";
                HNICCompletedBoxScoreReader hniccompletedboxscorereader = new HNICCompletedBoxScoreReader();

                boxScoreList = hniccompletedboxscorereader
                        .readBoxScores(completedBoxScoresUrl);
                Log
                        .d(
                                TAG,
                                "---------------------------------------------end of reading completedboxscore.json-----");

            } else
            {

                CharSequence text = "...Network Connection Failure...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);

                toast.show();

                /*
                 * completedBoxScoresUrl = "completedboxscore.json";
                 * HNICCompletedBoxScoreReader hniccompletedboxscorereader = new
                 * HNICCompletedBoxScoreReader();
                 * 
                 * boxScoreList = hniccompletedboxscorereader
                 * .readBoxScoresFromCache(completedBoxScoresUrl);
                 * 
                 * Log.d(TAG,
                 * "---------------------------------------------read completedboxscore.json from cache"
                 * );
                 */
            }

            // The data must be available in this format
            /*
             * map = new HashMap<String, Object>(); map.put("gamedate",
             * "SUN  6/6"); map.put("team2", "CHI  7"); map.put("team1",
             * "PHI  4"); map.put("gamestatus", "FINAL"); list.add(map);
             */

            /* completedboxscoremap = new HashMap<String, Object>(); */

            for (final HNICBoxScore boxscore : boxScoreList)
            {
                completedboxscoremap = new HashMap<String, Object>();

                strFileDate = boxscore.getStart_date_time();

                try
                {

                    SimpleDateFormat df1 = new SimpleDateFormat(
                            "yyyyMMdd'T'HH:mm:ssZ");

                    SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                    /* SimpleDateFormat df3 = new SimpleDateFormat("hh:mm a z"); */

                    SimpleDateFormat df3 = new SimpleDateFormat("hh:mm a");

                    startDate = df2.format(df1.parse(strFileDate));

                    startTime = df3.format(df1.parse(strFileDate));

                }

                catch (ParseException e)
                {
                    Log.e(TAG, "Parsing of date exception occured"
                            + e.toString());

                }

                /*
                 * completedboxscoremap.put("gamedate", boxscore
                 * .getStart_date_time());
                 */

                String gamestatus = "";

                gamestatus = boxscore.getStatus().toUpperCase() + "("
                        + boxscore.getPeriod() + ")";

                completedboxscoremap.put("gamedate", startDate);

                completedboxscoremap.put("gametime", " ");

                completedboxscoremap.put("awayteam", boxscore.getAway()
                        .toString()
                        + " " + boxscore.getAwayBoxScore().getScore());

                completedboxscoremap.put("hometeam", boxscore.getHome()
                        .toString()
                        + " " + boxscore.getHomeBoxScore().getScore());

                /*
                 * completedboxscoremap.put("gamestatus",
                 * boxscore.getStatus().toUpperCase());
                 */

                completedboxscoremap.put("gamestatus", gamestatus);

                completedboxscorelist.add(completedboxscoremap);

            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());
        }

        finally
        {

        }

        // -----------------------------2------------read liveboxscore.json

        try
        {
            Log.d(TAG, "reading completed box score url");

            String liveBoxScoresUrl = "http://www.cbc.ca/data/statsfeed/plist/liveboxscore.json";

            HNICLiveBoxScoreReader hnicliveboxscorereader = new HNICLiveBoxScoreReader();

            liveboxScoreList = hnicliveboxscorereader
                    .readBoxScores(liveBoxScoresUrl);

            // The data must be available in this format

            /*
             * map = new HashMap<String, Object>(); map.put("gamedate",
             * "SUN  6/6"); map.put("team2", "CHI  7"); map.put("team1",
             * "PHI  4"); map.put("gamestatus", "FINAL"); list.add(map);
             */

            completedboxscoremap = new HashMap<String, Object>();

            for (final HNICBoxScore liveboxscore : liveboxScoreList)
            {
                completedboxscoremap = new HashMap<String, Object>();

                strFileDate = liveboxscore.getStart_date_time();

                try
                {

                    SimpleDateFormat df1 = new SimpleDateFormat(
                            "yyyyMMdd'T'HH:mm:ssZ");

                    SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                    SimpleDateFormat df3 = new SimpleDateFormat("hh:mm a z");

                    startDate = df2.format(df1.parse(strFileDate));

                    startTime = df3.format(df1.parse(strFileDate));

                }

                catch (ParseException e)
                {
                    Log.e(TAG, "Parsing of date exception occured"
                            + e.toString());

                }

                completedboxscoremap.put("gamedate", liveboxscore
                        .getStart_date_time());

                completedboxscoremap.put("gamedate", startDate);

                completedboxscoremap.put("gametime", " ");

                completedboxscoremap.put("awayteam", liveboxscore.getAway()
                        .toString()
                        + " " + liveboxscore.getAwayBoxScore().getScore());

                completedboxscoremap.put("hometeam", liveboxscore.getHome()
                        .toString()
                        + " " + liveboxscore.getHomeBoxScore().getScore());

                completedboxscoremap
                        .put("gamestatus", liveboxscore.getStatus());

                completedboxscorelist.add(completedboxscoremap);

            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());
        }

        finally
        {

        }

        // -------------------------------------3-------read
        // schedule_scoreboard.json

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
                Log
                        .d(
                                TAG,
                                "---------------------------------------------starting of reading schedule_scoreboard.json-----");

                liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json";

                HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

                gameList = hnicschedulescoreboardreader
                        .readScheduleScoreboard(liveScheduleScoreboardUrl);
                Log
                        .d(
                                TAG,
                                "---------------------------------------------end of reading schedule_scoreboard.json-----");

            } else
            {

                CharSequence text = "...Network Connection Failure...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);

                toast.show();

                /*
                 * liveScheduleScoreboardUrl = "schedule_scoreboard.json";
                 * 
                 * HNICScheduleScoreboardReader hnicschedulescoreboardreader =
                 * new HNICScheduleScoreboardReader();
                 * 
                 * gameList = hnicschedulescoreboardreader
                 * .readScheduleScoreboardFromCache(liveScheduleScoreboardUrl);
                 * 
                 * Log.d(TAG,
                 * "---------------------------------------------read schedule_scoreboard.json from cache"
                 * );
                 */
            }

            Log
                    .d(TAG,
                            "--------------------------------------------reading gameList-----------------");
            int i = 0;
            for (final HNICGame game : gameList)
            {
                /*
                 * Log.d(TAG, "game info " + game.getAway() + "---" +
                 * game.getHome() + "---" + game.getStart_date_time() +
                 * "--available-" + game.getAvailable_on_cbc());
                 */

                /*
                 * we only need to add games that are in the future so we will
                 * add a condition here
                 */

                strFileDate = game.getStart_date_time();

                SimpleDateFormat dfstartDate = new SimpleDateFormat(
                        "yyyyMMdd'T'HH:mm:ssZ");

                Date startDateDate = dfstartDate.parse(strFileDate);

                // get an instance
                Calendar cal = Calendar.getInstance();

                // initialise the start or current date
                Date today = cal.getTime();

                /*
                 * Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                 * today.toString());
                 */

                if (startDateDate.before(today))

                {

                } else
                {
                    completedboxscoremap = new HashMap<String, Object>();

                    try
                    {

                        SimpleDateFormat df1 = new SimpleDateFormat(
                                "yyyyMMdd'T'HH:mm:ssZ");

                        SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                        /*
                         * SimpleDateFormat df3 = new
                         * SimpleDateFormat("h:mm a z");
                         */

                        SimpleDateFormat df3 = new SimpleDateFormat("h:mm a");

                        startDate = df2.format(df1.parse(strFileDate));

                        startTime = df3.format(df1.parse(strFileDate));

                    }

                    catch (ParseException e)
                    {
                        Log.e(TAG, "Parsing of date exception occured"
                                + e.toString());

                    }

                    completedboxscoremap.put("gamedate", startDate);

                    completedboxscoremap.put("gametime", " ");

                    completedboxscoremap.put("awayteam", game.getAway());

                    completedboxscoremap.put("hometeam", game.getHome());

                    completedboxscoremap.put("gamestatus", startTime);

                    completedboxscorelist.add(completedboxscoremap);

                    i++;

                }

            }
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
                Log
                        .d(
                                TAG,
                                "--------------------------------------------start of reading completedgameresults.json");

                HNICCompletedGameResultsReader hhniccompletedgameresultsreader = new HNICCompletedGameResultsReader();

                completedGameResultsResponse = hhniccompletedgameresultsreader
                        .readCompletedGameResults(completedGameResultssUrl);
                Log
                        .d(
                                TAG,
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
                 * HNICCompletedGameResultsReaderf
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

            Log
                    .e(TAG,
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

                promosUrl = "http://www.cbc.ca/m/config/hnic/promos/promoconfig.json";

                HNICPromosReader hnicpromosreader = new HNICPromosReader();

                hnicpromos = hnicpromosreader.readPromos(promosUrl);
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

            drawablefordisplay = loadImageFromWebOperations(hnicpromos.get(1).imageUrl);

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

    /* end of readItems() */

    /* start of readItemsFromCache() */
    private void readItemsFromCache()
    {

        String strFileDate = null;

        String startDate = null;

        String startTime = null;

        Map<String, Object> completedboxscoremap = null;
        
        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());
        

        Log.d(TAG, "----------start of readItemsFromCache----------");

        // -----------------------------1------------read completedboxscore.json
        try
        {

            String completedBoxScoresUrl;

            completedBoxScoresUrl = "completedboxscore.json";
            HNICCompletedBoxScoreReader hniccompletedboxscorereader = new HNICCompletedBoxScoreReader();

            boxScoreList = hniccompletedboxscorereader
                    .readBoxScoresFromCache(completedBoxScoresUrl);

            // The data must be available in this format
            /*
             * map = new HashMap<String, Object>(); map.put("gamedate",
             * "SUN  6/6"); map.put("team2", "CHI  7"); map.put("team1",
             * "PHI  4"); map.put("gamestatus", "FINAL"); list.add(map);
             */

            /* completedboxscoremap = new HashMap<String, Object>(); */

            for (final HNICBoxScore boxscore : boxScoreList)
            {
                completedboxscoremap = new HashMap<String, Object>();

                strFileDate = boxscore.getStart_date_time();
                
                
                /* start of db lookup to get and set the full teamname */ 
                
                HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(boxscore.getAway());
                
                boxscore.setAwayfull(thisTeamAway.getFullname().toUpperCase());
                
                
                HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(boxscore.getHome());
                
                boxscore.setHomefull(thisTeamHome.getFullname().toUpperCase());
                
                /* end of db lookup to get and set the full teamname */

                try
                {

                    SimpleDateFormat df1 = new SimpleDateFormat(
                            "yyyyMMdd'T'HH:mm:ssZ");

                    SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                    /* SimpleDateFormat df3 = new SimpleDateFormat("hh:mm a z"); */

                    SimpleDateFormat df3 = new SimpleDateFormat("hh:mm a");

                    startDate = df2.format(df1.parse(strFileDate));

                    startTime = df3.format(df1.parse(strFileDate));

                }

                catch (ParseException e)
                {
                    Log.e(TAG, "Parsing of date exception occured"
                            + e.toString());

                }

                /*
                 * completedboxscoremap.put("gamedate", boxscore
                 * .getStart_date_time());
                 */

                String gamestatus = "";

                gamestatus = boxscore.getStatus().toUpperCase() + "("
                        + boxscore.getPeriod() + ")";

                completedboxscoremap.put("gamedate", startDate);

                completedboxscoremap.put("gametime", " ");

                completedboxscoremap.put("awayteam", boxscore.getAway()
                        .toString()
                        + " " + boxscore.getAwayBoxScore().getScore());

                completedboxscoremap.put("hometeam", boxscore.getHome()
                        .toString()
                        + " " + boxscore.getHomeBoxScore().getScore());

                /*
                 * completedboxscoremap.put("gamestatus",
                 * boxscore.getStatus().toUpperCase());
                 */

                completedboxscoremap.put("gamestatus", gamestatus);

                completedboxscorelist.add(completedboxscoremap);

            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());
        }

        finally
        {

        }

        // -----------------------------2------------read liveboxscore.json

        try
        {
            Log.d(TAG, "reading completed box score url");

            String liveBoxScoresUrl = "http://www.cbc.ca/data/statsfeed/plist/liveboxscore.json";

            HNICLiveBoxScoreReader hnicliveboxscorereader = new HNICLiveBoxScoreReader();

            liveboxScoreList = hnicliveboxscorereader
                    .readBoxScores(liveBoxScoresUrl);

            // The data must be available in this format

            /*
             * map = new HashMap<String, Object>(); map.put("gamedate",
             * "SUN  6/6"); map.put("team2", "CHI  7"); map.put("team1",
             * "PHI  4"); map.put("gamestatus", "FINAL"); list.add(map);
             */

            completedboxscoremap = new HashMap<String, Object>();

            for (final HNICBoxScore liveboxscore : liveboxScoreList)
            {
                completedboxscoremap = new HashMap<String, Object>();

                strFileDate = liveboxscore.getStart_date_time();
                
                /* start of db lookup to get and set the full teamname */ 
                
                HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(liveboxscore.getAway());
                
                liveboxscore.setAwayfull(thisTeamAway.getFullname().toUpperCase());
                
                
                HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(liveboxscore.getHome());
                
                liveboxscore.setHomefull(thisTeamHome.getFullname().toUpperCase());
                
                /* end of db lookup to get and set the full teamname */

                try
                {

                    SimpleDateFormat df1 = new SimpleDateFormat(
                            "yyyyMMdd'T'HH:mm:ssZ");

                    SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                    SimpleDateFormat df3 = new SimpleDateFormat("hh:mm a z");

                    startDate = df2.format(df1.parse(strFileDate));

                    startTime = df3.format(df1.parse(strFileDate));

                }

                catch (ParseException e)
                {
                    Log.e(TAG, "Parsing of date exception occured"
                            + e.toString());

                }

                completedboxscoremap.put("gamedate", liveboxscore
                        .getStart_date_time());

                completedboxscoremap.put("gamedate", startDate);

                completedboxscoremap.put("gametime", " ");

                completedboxscoremap.put("awayteam", liveboxscore.getAway()
                        .toString()
                        + " " + liveboxscore.getAwayBoxScore().getScore());

                completedboxscoremap.put("hometeam", liveboxscore.getHome()
                        .toString()
                        + " " + liveboxscore.getHomeBoxScore().getScore());

                completedboxscoremap
                        .put("gamestatus", liveboxscore.getStatus());

                completedboxscorelist.add(completedboxscoremap);

            }

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());
        }

        finally
        {

        }

        if (liveboxScoreList != null)
        {

            for (HNICBoxScore oneboxscore : liveboxScoreList)
            {
                boxScoreList.add(oneboxscore);
            }

        }
        // -------------------------------------3-------read
        // schedule_scoreboard.json

        try
        {

            String liveScheduleScoreboardUrl;

            liveScheduleScoreboardUrl = "schedule_scoreboard.json";

            HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

            gameList = hnicschedulescoreboardreader
                    .readScheduleScoreboardFromCache(liveScheduleScoreboardUrl);

            Log
                    .d(
                            TAG,
                            "---------------------------------------------read schedule_scoreboard.json from cache");

            Log
                    .d(TAG,
                            "--------------------------------------------reading gameList-----------------");
            int i = 0;
            
            
            
            
            for (final HNICGame game : gameList)
            {
                
                /* start of db lookup to get and set the full teamname */ 
                
                HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(game.getAway());
                
                game.setAwayfull(thisTeamAway.getFullname().toUpperCase());
                
                
                HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(game.getHome());
                
                game.setHomefull(thisTeamHome.getFullname().toUpperCase());
                
                /* end of db lookup to get and set the full teamname */
                
                /*
                 * Log.d(TAG, "game info " + game.getAway() + "---" +
                 * game.getHome() + "---" + game.getStart_date_time() +
                 * "--available-" + game.getAvailable_on_cbc());
                 */
                
                
                

                /*
                 * we only need to add games that are in the future so we will
                 * add a condition here
                 */

                strFileDate = game.getStart_date_time();

                SimpleDateFormat dfstartDate = new SimpleDateFormat(
                        "yyyyMMdd'T'HH:mm:ssZ");

                Date startDateDate = dfstartDate.parse(strFileDate);

                // get an instance
                Calendar cal = Calendar.getInstance();

                // initialise the start or current date
                Date today = cal.getTime();

                /*
                 * Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                 * today.toString());
                 */

                if (startDateDate.before(today))

                {

                } else
                {
                    completedboxscoremap = new HashMap<String, Object>();

                    try
                    {

                        SimpleDateFormat df1 = new SimpleDateFormat(
                                "yyyyMMdd'T'HH:mm:ssZ");

                        SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                        /*
                         * SimpleDateFormat df3 = new
                         * SimpleDateFormat("h:mm a z");
                         */

                        SimpleDateFormat df3 = new SimpleDateFormat("h:mm a");

                        startDate = df2.format(df1.parse(strFileDate));

                        startTime = df3.format(df1.parse(strFileDate));

                    }

                    catch (ParseException e)
                    {
                        Log.e(TAG, "Parsing of date exception occured"
                                + e.toString());

                    }

                    completedboxscoremap.put("gamedate", startDate);

                    completedboxscoremap.put("gametime", " ");

                    completedboxscoremap.put("awayteam", game.getAway());

                    completedboxscoremap.put("hometeam", game.getHome());

                    completedboxscoremap.put("gamestatus", startTime);

                    completedboxscorelist.add(completedboxscoremap);

                    i++;

                }

            }
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

            String completedGameResultssUrl;

            completedGameResultssUrl = "completedgameresults.json";

            HNICCompletedGameResultsReader hhniccompletedgameresultsreader = new HNICCompletedGameResultsReader();

            completedGameResultsResponse = hhniccompletedgameresultsreader
                    .readCompletedGameResultsFromCache(completedGameResultssUrl);

            Log
                    .d(
                            TAG,
                            "---------------------------------------------read completedgameresults.json from cache");

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());

            Log
                    .e(TAG,
                            "----------there is a pretty good chance that something bad may have happened");

        }

        finally
        {

        }

        // -------------------------------------5------read
        // livegameresults.json

        try
        {

            String liveGameResultssUrl;

            liveGameResultssUrl = "http://www.cbc.ca/data/statsfeed/plist/gameresult.json";

            HNICCompletedGameResultsReader hhniccompletedgameresultsreader = new HNICCompletedGameResultsReader();

            liveGameResultsResponse = hhniccompletedgameresultsreader
                    .readCompletedGameResults(liveGameResultssUrl);

            Log
                    .d(TAG,
                            "---------------------------------------------read gameresults.json");

        }

        catch (Exception ex)
        {
            Log.e(TAG, ex.toString());

            Log
                    .e(TAG,
                            "----------there is a pretty good chance that something bad may have happened");

        }

        finally
        {

        }

        if (liveGameResultsResponse != null)
        {

            for (HNICCompletedGameResultsResponse hniccompletedgameresultsresponse : liveGameResultsResponse)
            {
                completedGameResultsResponse
                        .add(hniccompletedgameresultsresponse);
            }

        }
        // -------------------------------------6---------read promoconfig.json

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

    /* end of readItemsFromCache() */

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

    private void displayItemsHouseKeeping()
    {
        displayItems();

        CharSequence text = "...housekeeping complete...";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getBaseContext(), text, duration);

        toast.show();

        /* startReadItems(); */

    }

    private void displayItems()
    {
        if (gameList != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            /* display the promo */

            /* promImage.setImageDrawable(drawablefordisplay); */

            /* display the promo */
            /*
             * feedTitle.setText(myRssFeed.getTitle() + strCurrentTiime);
             * 
             * 
             * 
             * feedDescribtion.setText(myRssFeed.getDescription());
             * 
             * feedPubdate.setText(myRssFeed.getPubdate());
             * 
             * feedLink.setText(myRssFeed.getLink());
             */

            /*
             * MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.row,
             * gameList);
             */

            /*
             * MyCustomGameResultAdapter adapter = new
             * MyCustomGameResultAdapter(this, R.layout.row, gameResultsList);
             * 
             * setListAdapter(adapter);
             */

            /* start of data stuff */

            /* start of custom SimpleAdapter */

            /* end of custom SimpleAdapter */

            SimpleAdapter adapter2 = new SimpleAdapter(this,
                    completedboxscorelist, R.layout.home_main_topgames,
                    new String[]
                    { "gamedate", "gametime", "awayteam", "hometeam",
                            "gamestatus" }, new int[]
                    { R.id.gamedate, R.id.gametime, R.id.team1, R.id.team2,
                            R.id.gamestatus });

            Gallery gallery2 = (Gallery) findViewById(R.id.galleryTopGames);

            gallery2.setAdapter(adapter2);
            /* gallery2.setSelection(1); */
            gallery2.setSelected(true);
            gallery2.setFocusable(true);
            /* following line focuses selector on last night's game */
            gallery2.setSelection((completedboxscorelist.size() / 2)
                    - REWINDSELECTOR);

            // populate data when game is selected
            gallery2.setOnItemClickListener(new OnItemClickListener()
            {
                public void onItemClick(AdapterView parent, View v,
                        final int position, long id)
                {
                    // populate away data
                    ImageSwitcher switcher_away = (ImageSwitcher) findViewById(R.id.switcher_away);

                    /*
                     * switcher_away.setOnClickListener(new OnClickListener() {
                     * public void onClick(View v) {
                     * 
                     * Intent myIntent1 = new Intent(v.getContext(),
                     * Team.class);
                     * 
                     * myIntent1.putExtra("team", completedboxscorelist
                     * .get(position).get("awayteam").toString());
                     * startActivity(myIntent1);
                     * 
                     * Log .d(TAG,
                     * "you clicked on the awayimage switcher...you did...i saw you"
                     * );
                     * 
                     * } });
                     */

                    // populate away data
                    ImageSwitcher switcher_home = (ImageSwitcher) findViewById(R.id.switcher_home);

                    /*
                     * switcher_home.setOnClickListener(new OnClickListener() {
                     * public void onClick(View v) {
                     * 
                     * Intent myIntent2 = new Intent(v.getContext(),
                     * Team.class); myIntent2.putExtra("team",
                     * completedboxscorelist
                     * .get(position).get("hometeam").toString());
                     * startActivityForResult(myIntent2, 0); Log .d(TAG,
                     * "you clicked on the homeimage switcher...you did...i saw you"
                     * ); startActivity(myIntent2); } });
                     */

                    /*AppMeasurement s;

                    s = new AppMeasurement(getApplication()); // Activity.getApplication

                     Specify the Report Suite ID(s) to track here 
                     s.account = "cbc-mobile-dev-2"; 

                    s.account = "cbc-mobile-prod-9";

                     You may add or alter any code config here 

                    s.channel = "android-app";

                    s.prop1 = "android-app:hockey-app";

                    s.prop2 = "android-app:hockey-app" + C2;

                    s.prop4 = "page";

                    s.pageName = "android-app:hockey-app:" + C2;

                    s.currencyCode = "CAD";

                     Turn on and configure debugging here 
                    s.debugTracking = true;

                    
                     * WARNING: Changing any of the below variables will cause
                     * drastic changes to how your visitor data is collected.
                     * Changes should only be made when instructed to do so by
                     * your account manager.
                     
                    s.trackingServer = "metrics.cbc.ca";

                    s.track();*/

                }
            });

     

        }

        else
        {
            // Log.d(TAG,
            // "-------------------------------------gamelist is null");

            CharSequence text = "...Network Connection Disrupted...Please connect and try again...";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getBaseContext(), text, duration);

            toast.show();
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.home:
            launchHomeViewer();
            break;
        case R.id.news:
            launchNewsViewer();
            break;
        case R.id.schedule:
            launchScheduleViewer();
            break;
        case R.id.video:
            launchVideoViewer();
            break;
        case R.id.about:
            launchAboutViewer();
            break;
        case R.id.stats:
            launchStatsViewer();
            break;
        case R.id.blogs:
            launchBlogsViewer();
            break;
        case R.id.exit:
            finish();
            break;

        }
        return true;
    }

    /**
     * Launches the Home activity
     */
    protected void launchHomeViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading news...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, NHLHockey.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */

        /* b.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY); */

        /* b.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS); */

        startActivity(b);
    }

    /**
     * Launches the News activity
     */
    protected void launchNewsViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading news...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, News.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the Schedule activity
     */
    protected void launchScheduleViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading schedule...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, Schedule.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the Video activity
     */
    protected void launchVideoViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading video...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, VideoNew.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the About activity
     */
    protected void launchAboutViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading video...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, About.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the More activity
     */
    protected void launchMoreViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading more...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, More.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the Stats activity
     */
    protected void launchStatsViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading more...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, Stats.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the Blogs activity
     */
    protected void launchBlogsViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading more...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, Blogs.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * Launches the ContactUs activity
     */
    protected void launchContactUsViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading more...";
        int duration = Toast.LENGTH_SHORT;

        /*
         * Toast toast = Toast.makeText(context, text, duration); toast.show();
         */

        Intent b = new Intent(this, ContactUs.class);
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }

    /**
     * A call-back for when the user presses the back button.
     */
    OnClickListener mBackListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            finish();

        }
    };

   
    // use the xml parse class to parse the data from the xml server. this is an
    // example on
    // how to organize the data

    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gamedate", "date");
        map.put("team2", "team2");
        map.put("team1", "team1");
        map.put("gamestatus", "status");
        list.add(map);

        return list;
    }

    private Integer[] mThumbIds =
    { R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector,
            R.drawable.galleryselector, R.drawable.galleryselector };

    private Integer[] mImageIds =
    { R.drawable.ana, R.drawable.anh, R.drawable.atl, R.drawable.bos,
            R.drawable.buf, R.drawable.cal, R.drawable.car, R.drawable.cbj,
            R.drawable.cgy, R.drawable.chi, R.drawable.cls, R.drawable.col,
            R.drawable.dal, R.drawable.det, R.drawable.edm, R.drawable.fla,
            R.drawable.flo, R.drawable.la, R.drawable.min, R.drawable.mon,
            R.drawable.mtl, R.drawable.nas, R.drawable.nj, R.drawable.njd,
            R.drawable.nsh, R.drawable.nyi, R.drawable.nyr, R.drawable.ott,
            R.drawable.phi, R.drawable.pho, R.drawable.pit, R.drawable.sj,
            R.drawable.stl, R.drawable.tb, R.drawable.tor, R.drawable.van,
            R.drawable.was };

    /*
     * private Integer[] mPromoIds = { R.drawable.promo, R.drawable.promo1,
     * R.drawable.promo2, R.drawable.promo3, R.drawable.promo4,
     * R.drawable.promo5 };
     */

    private Integer[] mPromoIds =
    { R.drawable.promo1, R.drawable.promo2, R.drawable.promo3,
            R.drawable.promo4, R.drawable.promo5 };

    @Override
    public void onItemSelected(AdapterView parent, View v, int position, long id)
    {

        try
        {
            // Log.d(TAG, gameList.get(position).toString());
        } catch (Exception ex)
        {
            // Log.d(TAG, "----------" + ex.toString());

        }

        finally
        {

        }

        // Log.d(TAG, "setting image resource");
        try
        {

            /*
             * Toast.makeText(this, boxScoreList.get(position).getAway() + " " +
             * boxScoreList.get(position).getHome(), Toast.LENGTH_SHORT).show();
             */

            Log
                    .d(TAG,
                            "--------------------------------------we need to find out the position"
                                    + position + " "
                                    + completedboxscorelist.size() + " "
                                    + gameList.size() + " "
                                    + boxScoreList.size());

            int offset = gameList.size() - completedboxscorelist.size();

            // if the selector is past the completed games, then we need to zero
            // out the box score data



            if (position >= boxScoreList.size())
            {

                textAwayTeamName.setTextColor(getResources().getColor(
                        R.color.white));

                textHomeTeamName.setTextColor(getResources().getColor(
                        R.color.white));

                textFinal.setText("   @");

                textTeamNotifi.setText("");

                String awayDrawable = gameList.get(position + offset).getAway()
                        .toLowerCase();

                int resAwayID = getBaseContext().getResources().getIdentifier(
                        awayDrawable, "drawable", "com.troubadorian.mobile.android.nhlhockey");

                mSwitcherAway.setImageResource(resAwayID);

                textAwayTeamScore.setText("");

                textAwayTeamSOG.setText("");

                /*
                 * textAwayTeamName.setText(gameList.get(position + offset)
                 * .getAway());
                 * 
                 * textHomeTeamName.setText(gameList.get(position + offset)
                 * .getHome());
                 */

                /*
                 * start of magic to make team names fullnames instead of
                 * abbreviations
                 */

                textAwayTeamName.setText(gameList.get(position + offset).getAwayfull());

                textHomeTeamName.setText(gameList.get(position + offset).getHomefull());

                /*
                 * end of magic to make team names fullnames instead of
                 * abbreviations
                 */

                String homeDrawable = gameList.get(position + offset).getHome()
                        .toLowerCase();

                int resHomeID = getBaseContext().getResources().getIdentifier(
                        homeDrawable, "drawable", "com.troubadorian.mobile.android.nhlhockey");

                mSwitcherHome.setImageResource(resHomeID);

                textHomeTeamScore.setText("");

                textHomeTeamSOG.setText("");

                textAwayTeam1.setText("");

                textAwayTeam2.setText("");

                textAwayTeam3.setText("");

                textAwayTeamOT.setText("");

                textAwayTeamT.setText("");

                textHomeTeam1.setText("");

                textHomeTeam2.setText("");

                textHomeTeam3.setText("");

                textHomeTeamOT.setText("");

                textHomeTeamT.setText("");

                textGameResultBoxP1.setText("");

                textGameResultBoxP2.setText("");

                textGameResultBoxP3.setText("");

                textGameResultBoxOT.setText("");

                /* start of selector changing code for future games */

                if (gameList.get(position + offset).getAvailable_on_cbc()
                        .compareTo("yes") == 0)
                {
                    ImageView selector = (ImageView) findViewById(R.id.selector);

                    try
                    {
                        selector
                                .setImageResource(R.drawable.scoreboard_frame_watchon);
                    }

                    catch (Exception e)
                    {
                        Log.e(TAG, "---------------bad" + e.toString());
                    }
                } else
                {
                    ImageView selector = (ImageView) findViewById(R.id.selector);

                    try
                    {
                        selector.setImageResource(R.drawable.selector);
                    }

                    catch (Exception e)
                    {
                        Log.e(TAG, "---------------bad" + e.toString());
                    }

                }
                /* end of selector changing code for future games */

            } else
            {

                textFinal.setText(boxScoreList.get(position).getStatus());

                textTeamNotifi.setText(boxScoreList.get(position).getAway()
                        + " @ " + boxScoreList.get(position).getHome());

                String awayDrawable = boxScoreList.get(position).getAway()
                        .toLowerCase();

                int resAwayID = getBaseContext().getResources().getIdentifier(
                        awayDrawable, "drawable", "com.troubadorian.mobile.android.nhlhockey");

                mSwitcherAway.setImageResource(resAwayID);

                textAwayTeamScore.setText(boxScoreList.get(position)
                        .getAwayBoxScore().getScore());

                textAwayTeamSOG.setText(boxScoreList.get(position)
                        .getAwayBoxScore().getScore_attempts()
                        + " SOG");

                /*
                 * textAwayTeamName.setText(boxScoreList.get(position).getAway())
                 * ;
                 * 
                 * 
                 * textHomeTeamName.setText(boxScoreList.get(position).getHome())
                 * ;
                 */

                /*
                 * start of magic to make team names fullnames instead of
                 * abbreviations
                 */

           
                textAwayTeamName.setText(boxScoreList.get(position).getAwayfull());
                
                textHomeTeamName.setText(boxScoreList.get(position).getHomefull());



                /*
                 * end of magic to make team names fullnames instead of
                 * abbreviations
                 */

                String homeDrawable = boxScoreList.get(position).getHome()
                        .toLowerCase();

                int resHomeID = getBaseContext().getResources().getIdentifier(
                        homeDrawable, "drawable", "com.troubadorian.mobile.android.nhlhockey");

                mSwitcherHome.setImageResource(resHomeID);

                textHomeTeamScore.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getScore());

                textHomeTeamSOG.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getScore_attempts()
                        + " SOG");

                textAwayTeam1.setText(boxScoreList.get(position)
                        .getAwayBoxScore().getPeriod1score());

                textAwayTeam2.setText(boxScoreList.get(position)
                        .getAwayBoxScore().getPeriod2score());

                textAwayTeam3.setText(boxScoreList.get(position)
                        .getAwayBoxScore().getPeriod3score());

                textAwayTeamOT.setText(boxScoreList.get(position)
                        .getAwayBoxScore().getOvertimeScore());

                /*
                 * textAwayTeamT.setText(boxScoreList.get(position)
                 * .getAwayBoxScore().getShootOutScore());
                 */

                textAwayTeamT.setText((boxScoreList.get(position)
                        .getAwayBoxScore().getScore()));

                /*
                 * textHomeTeamT.setText(boxScoreList.get(position)
                 * .getHomeBoxScore().getShootOutScore());
                 */

                textHomeTeam1.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getPeriod1score());

                textHomeTeam2.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getPeriod2score());

                textHomeTeam3.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getPeriod3score());

                textHomeTeamOT.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getOvertimeScore());

                /*
                 * textHomeTeamT.setText(boxScoreList.get(position)
                 * .getHomeBoxScore().getShootOutScore());
                 */

                textHomeTeamT.setText(boxScoreList.get(position)
                        .getHomeBoxScore().getScore());

                if (boxScoreList.get(position).getAwayBoxScore().getScore()
                        .compareTo(
                                boxScoreList.get(position).getHomeBoxScore()
                                        .getScore()) > 0)

                {

                    /*
                     * textAwayTeamScore.setTextColor(getResources().getColor(
                     * R.color.yellow));
                     */

                    textAwayTeamName.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textAwayTeam1.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textAwayTeam2.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textAwayTeam3.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textAwayTeamOT.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textAwayTeamT.setTextColor(getResources().getColor(
                            R.color.yellow));

                    textHomeTeamName.setTextColor(getResources().getColor(
                            R.color.white));
                    textHomeTeam1.setTextColor(getResources().getColor(
                            R.color.white));
                    textHomeTeam2.setTextColor(getResources().getColor(
                            R.color.white));
                    textHomeTeam3.setTextColor(getResources().getColor(
                            R.color.white));
                    textHomeTeamOT.setTextColor(getResources().getColor(
                            R.color.white));
                    textHomeTeamT.setTextColor(getResources().getColor(
                            R.color.white));

                    Log.d(TAG, "----------------the away team won");

                } else
                {

                    /*
                     * textHomeTeamScore.setTextColor(getResources().getColor(
                     * R.color.yellow));
                     */

                    textHomeTeamName.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textHomeTeam1.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textHomeTeam2.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textHomeTeam3.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textHomeTeamOT.setTextColor(getResources().getColor(
                            R.color.yellow));
                    textHomeTeamT.setTextColor(getResources().getColor(
                            R.color.yellow));

                    textAwayTeamName.setTextColor(getResources().getColor(
                            R.color.white));
                    textAwayTeam1.setTextColor(getResources().getColor(
                            R.color.white));
                    textAwayTeam2.setTextColor(getResources().getColor(
                            R.color.white));
                    textAwayTeam3.setTextColor(getResources().getColor(
                            R.color.white));
                    textAwayTeamOT.setTextColor(getResources().getColor(
                            R.color.white));
                    textAwayTeamT.setTextColor(getResources().getColor(
                            R.color.white));

                    Log.d(TAG, "---------------the home team won");
                }

            }

            /*
             * MyCustomGameResultAdapter adapter3 = new
             * MyCustomGameResultAdapter( this, R.layout.row,
             * completedGameResultsResponse .get(position).results);
             * 
             * ListView
             * completedGameResultsList=(ListView)findViewById(android.R
             * .id.list);
             * 
             * completedGameResultsList.setAdapter(adapter3);
             */

            /*
             * textGameResultBoxP1.setText(completedGameResultsResponse.get(position
             * ) .getFormattedOutput());
             */

            textGameResultBoxP1.setText(completedGameResultsResponse.get(
                    position).getPeriodOne());

            /*
             * textGameResultBoxP1.loadDataWithBaseURL("",
             * completedGameResultsResponse.get(position ).getPeriodOne(),
             * "text/html", Encoding.UTF_8.toString(),"");
             */

            textGameResultBoxP2.setText(completedGameResultsResponse.get(
                    position).getPeriodTwo());

            textGameResultBoxP3.setText(completedGameResultsResponse.get(
                    position).getPeriodThree());

            textGameResultBoxOT.setText(completedGameResultsResponse.get(
                    position).getPeriodOT());

        } catch (Exception ex)
        {
            // Log.d(TAG, "----------" + ex.toString());

        }

        finally
        {

        }

    }

    public void onNothingSelected(AdapterView parent)
    {
    }

    public View makeView()
    {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        return i;
    }

    public class ImageAdapter extends BaseAdapter
    {
        public ImageAdapter(Context c)
        {
            mContext = c;
        }

        public int getCount()
        {
            return mThumbIds.length;
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.drawable.picture_frame);

            return i;

        }

        private Context mContext;

    }

    public class PromoImageAdapter extends BaseAdapter
    {
        public PromoImageAdapter(Context c)
        {
            mContext = c;
        }

        public int getCount()
        {
            return mPromoIds.length;
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mPromoIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            return i;

        }

        private Context mContext;

    }

}