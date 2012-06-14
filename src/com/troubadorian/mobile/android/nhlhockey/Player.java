/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;




import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICPlayerReader;
import com.troubadorian.mobile.android.model.HNICPlayerSeason;
import com.troubadorian.mobile.android.nhlhockey.Players.MyCustomAdapter;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Player extends ListActivity 
{
    ProgressDialog myProgress;

    public static final String TAG = "Player";
    
    private static final String C2 = "Player";

    private static final String C3 = "Index";

    HNICPlayer player = null;

    String playername;
    
    String playerDisplayName;

    String playerDisplayFirstName;
    
    String playerDisplayLastName;
   
    String firstname;
    
    String lastname;

    List<HNICPlayerSeason> playerSeasonList = null;

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

    public class RssLoadingTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            myProgress.cancel();
            displayPlayer();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(Player.this, "...retrieving...",
                    "...please wait...", true, false);
            preReadPlayer();

        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            // TODO Auto-generated method stub
            // super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            // TODO Auto-generated method stub
            readPlayer();
            return null;
        }

    }

    TextView feedTitle;

    TextView feedDescribtion;

    TextView feedPubdate;

    TextView feedLink;

    public class MyCustomAdapter extends ArrayAdapter<HNICPlayerSeason>
    {

        public MyCustomAdapter(Context context, int textViewResourceId,
                List<HNICPlayerSeason> playerSeasonList)
        {
            super(context, textViewResourceId, playerSeasonList);
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
                row = inflater.inflate(R.layout.row_player, parent, false);
            }

            TextView info = (TextView) row
                    .findViewById(R.id.info);

           

            info.setText("Year : " + playerSeasonList.get(position).getYear()

            + "\n"

            + "Team : " + playerSeasonList.get(position).getTeam()

            + "\n"

            + "Jersey : " + playerSeasonList.get(position).getJersey()
            
            
            + "\n"
            
            + "Position : " + playerSeasonList.get(position).getPosition()
            
            + "\n"
            
            + "Played : " + playerSeasonList.get(position).getPlayed()
            
            + "\n"
            
            + "Goals : " + playerSeasonList.get(position).getGoals()
            
            + "\n"
            
            + "Assists : " + " " 
            
            
            + "\n"
            
            + "Points : " + playerSeasonList.get(position).getPoints()
            
            + "\n"
            
            + "Shots on goal : " + " "
            
            + "\n"
            
            + "Goals for against : " + " "
            
            + "\n"
            
            + "Penalty Minutes : " + playerSeasonList.get(position).getPenaltyminutes()
            
            + "\n"
            
            + "Powerplay Goals " + playerSeasonList.get(position).getPowerplaygoals()
            
            
            );

           

            

            if (position % 2 == 0)
            {
                // listTitle.setBackgroundColor(0xff101010);

                // listTitle.setBackgroundColor(R.color.white);
                // listTitle.setTextColor(R.color.black);

                // listPubdate.setBackgroundColor(0xff101010);
                // listPubdate.setBackgroundColor(R.color.white);
                // / listPubdate.setTextColor(R.color.black);

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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_main);

        Bundle bundle = this.getIntent().getExtras();

        playername = bundle.getString("player").toLowerCase();

        String position = bundle.getString("position");

        String team = bundle.getString("team");

        ImageView teamlogo = (ImageView) findViewById(R.id.teamlogo);

        String myyDrawable = team;

        int resID = getBaseContext().getResources().getIdentifier(myyDrawable,
                "drawable", "com.troubadorian.mobile.android.nhlhockey");

        teamlogo.setImageResource(resID);

        

        /*
         * TextView section_subhead = (TextView)
         * findViewById(R.id.section_subhead); section_subhead.setText(player);
         */

        /*
         * feedTitle = (TextView)findViewById(R.id.feedtitle); feedDescribtion =
         * (TextView)findViewById(R.id.feeddescribtion); feedPubdate =
         * (TextView)findViewById(R.id.feedpubdate); feedLink =
         * (TextView)findViewById(R.id.feedlink);
         */
/*        AdMarvelView adMarvelView = (AdMarvelView) findViewById(R.id.ad);
        adMarvelView.setEnableClickRedirect(true);
        adMarvelView.setDisableAnimation(false);
        adMarvelView.setListener(this);
        Map<String, String> targetParams = new HashMap<String, String>();
        // targetParams.put("KEYWORDS", "election");
        // targetParams.put("GENDER", "female");
         targetParams.put("POSTAL_CODE", "94131"); 
        // targetParams.put("CITY", "New York");
        // targetParams.put("STATE", "NY");

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
                Log.d(TAG, "mHomeButton clicked");
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

       
        
        startReadPlayer();
    }

    private void startReadPlayer()
    {
        new RssLoadingTask().execute();
    }

    private void preReadPlayer()
    {

        setListAdapter(null);

        TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

        newlistTitle.setText("Season List");

    }

    private void readPlayer()
    {

        StringTokenizer st = new StringTokenizer(playername, " ,");

        lastname = st.nextToken().toLowerCase();

        firstname = st.nextToken().toLowerCase();

        String theURL = "http://www.cbc.ca/data/statsfeed/plist/players/player_"
                + lastname + "_" + firstname + ".json";

        HNICPlayerReader hnicplayerreader = new HNICPlayerReader();

        player = hnicplayerreader.readPlayer(theURL);

        playerSeasonList = player.getSeasonList();

    }

    private void displayPlayer()
    {
        
        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        
        String playerDisplayName = player.getName();
        
/*        AppMeasurement s;

        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 
        s.account = "cbc-mobile-dev-2";
        
        s.account = "cbc-mobile-prod-9";

         You may add or alter any code config here 

        s.channel = "android-app";

        s.prop1 = "android-app:hockey-app";
        
        s.prop2 = "android-app:hockey-app:" + C2;
        
        s.prop3 = "android-app:hockey-app:" + C2 + ":" + player.getName();
        
        s.prop4 = "page";

        s.pageName = "android-app:hockey-app:" + C2 + ":" + player.getName();

        s.currencyCode = "CAD";

         Turn on and configure debugging here 
        s.debugTracking = true;

        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();*/
        
        StringTokenizer st = new StringTokenizer(playerDisplayName, " ,");
        
        playerDisplayFirstName = st.nextToken();
        
        playerDisplayLastName = st.nextToken();
                        
        section_header_text.setText( playerDisplayFirstName + " " + playerDisplayLastName);

        TextView height = (TextView) findViewById(R.id.height);
        height.setText("Height : " + player.getHeight());

        TextView weight = (TextView) findViewById(R.id.weight);
        weight.setText("Weight : " + player.getWeight());

        TextView birthdate = (TextView) findViewById(R.id.birthdate);

        birthdate.setText("Birthdate : " + player.getBirthdate());

        TextView birthplace = (TextView) findViewById(R.id.birthplace);

        birthplace.setText("Birthplace : " + player.getBirthplace());

        if (playerSeasonList != null)

        {
            MyCustomAdapter adapter = new MyCustomAdapter(this,
                    R.layout.row_player, playerSeasonList);
            setListAdapter(adapter);
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        menu.add(0, 0, 0, "Reload");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
        case (0):
            startReadPlayer();
            break;
        default:
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

        Intent b = new Intent(this, Player.class);
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

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent b = new Intent(this, VideoNew.class);
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

 

}