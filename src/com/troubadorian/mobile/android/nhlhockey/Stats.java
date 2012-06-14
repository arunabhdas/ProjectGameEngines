/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.HashMap;
import java.util.Map;




import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Stats extends ListActivity
{
    static final int DATE_DIALOG_ID = 1;

    private int mYear;

    private int mMonth;

    private int mDay;

    public static final String TAG = "Stats";
    
    private static final String C2 = "Stats";

    private static final String C3 = "Index";

    // declare button for home
    private Button mHomeButton;

    // declare button for news
    private Button mNewsButton;

    // declare button for schedule
    private Button mScheduleButton;

    // dclare button for video
    private Button mVideoButton;

    // declare button for more
    private Button mMoreButton;

    /* private String _siteId = "1429"; */

    private String _siteId = "9065";

    /* private String _partnerId = "1dd21b33bd603c95"; */

    private String _partnerId = "387131ac6a1aa80d";

    /*
     * static final String[] MENUOPTIONS = new String[] { "Initialize DB",
     * "Populate DB", "MyHNICStatsReaderActivity",
     * "MyHNICConferenceListReaderActivity", "MyHNICGameResultsReaderActivity",
     * "MyHNICCompletedGameResultsReaderActivity", "Standings", "Leaders",
     * "Players", "Teams", "MyHNICCompletedBoxScoreReaderActivity"
     * 
     * };
     */

    /*
     * static final String[] MENUOPTIONS = new String[] { "Standings",
     * "Leaders", "Players", "Teams", "ReaderActivity"
     * 
     * };
     */

    /*
     * static final String[] MENUOPTIONS = new String[] { "Standings",
     * "Leaders", "Players", "Teams"
     * 
     * };
     */

    /*
     * static final String[] MENUOPTIONS = new String[] { "Standings",
     * "Leaders", "Players", "Teams", "Database"
     * 
     * };
     */

    static final String[] MENUOPTIONS = new String[]
    { "Standings", "Leaders", "Players", "Teams"

    };

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stats_main);

        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

        /* start of listview block */

        // setListAdapter(ListAdapter) automatically adds
        // a ListView to fill the entire screen of the ListActivity

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
                MENUOPTIONS));

        ListView lv = getListView();

        lv.setTextFilterEnabled(true);
        /*
         * final Intent intent0 = new Intent(this,
         * PlayerDatabase.class).putExtra( "feedname", "playerdatabase");
         * 
         * final Intent intent1 = new Intent(this,
         * MyHNICDatabasePopulateActivity.class).putExtra("feedname",
         * "hnicboxscorereader");
         */

        /*
         * final Intent intent3 = new Intent(this,
         * MyHNICConferenceListReaderActivity.class).putExtra("feedname",
         * "schedulescoreboard");
         * 
         * final Intent intent4 = new Intent(this,
         * MyHNICGameResultsReaderActivity.class).putExtra("feedname",
         * "schedulescoreboard");
         * 
         * final Intent intent5 = new Intent(this,
         * MyHNICCompletedGameResultsReaderActivity.class).putExtra( "feedname",
         * "schedulescoreboard");
         */

        final Intent intent6 = new Intent(this, Standings.class).putExtra(
                "feedname", "standings");

        final Intent intent7 = new Intent(this, Leaders.class).putExtra(
                "feedname", "leaders");

        final Intent intent8 = new Intent(this, Players.class).putExtra(
                "feedname", "players");

        final Intent intent9 = new Intent(this, Teams.class).putExtra(
                "feedname", "teams");

        /*
         * final Intent intent10 = new Intent(this, Database.class).putExtra(
         * "feedname", "database");
         */

        /*
         * final Intent intent10 = new Intent(this,
         * MyHNICCompletedBoxScoreReaderActivity.class).putExtra( "feedname",
         * "hnicboxscorereader");
         */

        /*
         * final Intent intent11 = new Intent(this, MyHNICReaderActivity.class)
         * .putExtra("feedname", "schedulescoreboard");
         */

        lv.addStatesFromChildren();

        lv.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                // When clicked, show a toast with the TextView text
                switch (position)
                {
                /*
                 * case 0:
                 * 
                 * startActivity(intent0); break;
                 * 
                 * case 1:
                 * 
                 * startActivity(intent1); break;
                 * 
                 * case 2: startActivity(intent2); break;
                 * 
                 * case 3: startActivity(intent3); break;
                 * 
                 * case 4: startActivity(intent4); break;
                 * 
                 * case 5: startActivity(intent5); break;
                 */
                case 0:
                    startActivity(intent6);
                    break;

                case 1:
                    startActivity(intent7);
                    break;

                case 2:
                    startActivity(intent8);
                    break;

                case 3:
                    startActivity(intent9);
                    break;

                /*
                 * case 4: startActivity(intent10); break;
                 */

                /*
                 * case 4: startActivity(intent11); break;
                 */

                /*
                 * case 10: startActivity(intent10); break;
                 */

                }
            }
        });

        /* end of listview block */

        /*
         * AdMarvelView adMarvelView = (AdMarvelView) findViewById(R.id.ad);
         * adMarvelView.setEnableClickRedirect(true);
         * adMarvelView.setDisableAnimation(false);
         * adMarvelView.setListener(this); Map<String, String> targetParams =
         * new HashMap<String, String>();
         * adMarvelView.requestNewAd(targetParams, _partnerId, _siteId);
         */

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
                // Log.d(TAG, "mNewsButton clicked");
                launchHomeViewer();

            }
        });

        // Register handler for mNewsButton button

        mNewsButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mNewsButton clicked");
                launchNewsViewer();
            }
        });

        // Register handler for mScheduleButton button

        mScheduleButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mScheduleButton clicked");
                launchScheduleViewer();
            }
        });

        // Register handler for mVideoButton button

        mVideoButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mScheduleButton clicked");
                launchVideoViewer();
            }
        });

        // Register handler for mMoreButton button

        mMoreButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mMoreButton clicked");
                launchMoreViewer();
            }
        });

/*        AppMeasurement s;

        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 
        s.account = "cbc-mobile-dev-2";
        
        s.account = "cbc-mobile-prod-9";

         You may add or alter any code config here 

        s.channel = "android-app";

        s.prop1 = "android-app:hockey-app";
        
        s.prop2 = "android-app:hockey-app:" + C2;
        
        
        s.prop4 = "page";

        s.pageName = "android-app:hockey-app:" + C2 + ":" + C3;

        s.currencyCode = "CAD";

         Turn on and configure debugging here 
        s.debugTracking = true;

        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();*/

        // Hook up button presses to the appropriate event handler.
        /*
         * ((Button) findViewById(R.id.home_button))
         * .setOnClickListener(mBackListener);
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.stats_contextmenu, menu);
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

        /*
         * Intent b = new Intent(this, CBCHNIC.class); startActivity(b);
         */

        Intent b = new Intent(this, NHLHockey.class);
        b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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


}
