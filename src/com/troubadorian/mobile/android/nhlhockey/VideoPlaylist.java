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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VideoPlaylist extends ListActivity
{
    static final int DATE_DIALOG_ID = 1;

    private int mYear;

    private int mMonth;

    private int mDay;

    public static final String TAG = "Video";

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

    // declare button for stats
    private Button mStatsButton;

    /* private String _siteId = "1429"; */

    private String _siteId = "9065";

    /* private String _partnerId = "1dd21b33bd603c95"; */

    private String _partnerId = "387131ac6a1aa80d";

    static final String[] MENUOPTIONS = new String[]
    { "Highlights", "Segments"
          

    };

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_main);

        /* start of listview block */

        // setListAdapter(ListAdapter) automatically adds
        // a ListView to fill the entire screen of the ListActivity

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
                MENUOPTIONS));

        /*
         * String[] countries =
         * getResources().getStringArray(R.array.countries_array);
         */

        /*
         * setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
         * RSSFEEDS));
         */

        ListView lv = getListView();

        lv.setTextFilterEnabled(true);
        final Intent highlightsintent = new Intent(this, Highlights.class).putExtra(
                "section", "stats");

        final Intent segmentsintent = new Intent(this, Segments.class).putExtra(
                "feedname", "us");


        lv.addStatesFromChildren();

        lv.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                // When clicked, show a toast with the TextView text
                switch (position)
                {
                case 0:

                    startActivity(highlightsintent);
                    break;

                case 1:

                    startActivity(segmentsintent);
                    break;

       
                }
            }
        });

        /* end of listview block */



        // Obtain handles to UI objects

        mHomeButton = (Button) findViewById(R.id.home_button);

        mNewsButton = (Button) findViewById(R.id.news_button);

        mScheduleButton = (Button) findViewById(R.id.schedule_button);

        mVideoButton = (Button) findViewById(R.id.video_button);

        mMoreButton = (Button) findViewById(R.id.more_button);

        /* mStatsButton = (Button) findViewById(R.id.stats_button); */

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

        // Register handler for mStatsButton button

        mMoreButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mStatsButton clicked");
                launchStatsViewer();
            }
        });

      
        // Hook up button presses to the appropriate event handler.
        ((Button) findViewById(R.id.home_button))
                .setOnClickListener(mBackListener);
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

        Intent b = new Intent(this, VideoPlaylist.class);
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

        Intent b = new Intent(this, VideoPlaylist.class);
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
