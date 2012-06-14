/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;




import com.troubadorian.mobile.android.model.HNICGame;
import com.troubadorian.mobile.android.model.HNICInjuriesListReader;
import com.troubadorian.mobile.android.model.HNICInjury;
import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICRosterReader;
import com.troubadorian.mobile.android.model.HNICScheduleScoreboardReader;
import com.troubadorian.mobile.android.model.HNICTeam;
import com.troubadorian.mobile.android.model.HNICTeamLastTen;
import com.troubadorian.mobile.android.model.HNICTeamLastTenReader;
import com.troubadorian.mobile.android.model.HNICTeamPlayer;
import com.troubadorian.mobile.android.model.HNICTeamPlayerReader;
import com.troubadorian.mobile.android.model.HNICTeamRanks;
import com.troubadorian.mobile.android.model.HNICTeamRanksReader;

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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Team extends ListActivity 
{
    ProgressDialog myProgress;

    public static final String TAG = "Team";
    
    private static final String C2 = "Team";

    private static final String C3 = "Index";

    String nextgamedate = null;
    
    String nextgamedateformatted = null;
    
    List<HNICTeamLastTen> theList = null;

    List<HNICTeamPlayer> theTeamPlayerList = null;

    List<HNICPlayer> theRosterList = null;

    List<HNICInjury> theInjuriesList = null;

    List<HNICPlayer> theActivePlayerList = null;

    HNICTeamRanks theTeamRanksOffence = null;

    HNICTeamRanks theTeamRanksDefence = null;

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

    private String abbrupper;

    public class DataLoadingTaskTeamLastTen extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            myProgress.cancel();
            displayTeamLastTen();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(Team.this, "...retrieving...",
                    "...please wait...", true, false);
            preReadTeamLastTen();

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
            readTeamLastTen();
            return null;
        }

        public void execute(String teamabbr)
        {
            // TODO Auto-generated method stub

        }

    }

    public class DataLoadingTaskRoster extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            myProgress.cancel();
            displayRoster();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(Team.this, "...retrieving...",
                    "...please wait...", true, false);
            preReadRoster();

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
            readRoster();
            return null;
        }

        public void execute(String teamabbr)
        {
            // TODO Auto-generated method stub

        }

    }

    public class DataLoadingTaskInjuriesList extends
            AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            myProgress.cancel();
            displayInjuriesList();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(Team.this, "...retrieving...",
                    "...please wait...", true, false);
            preReadInjuriesList();

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
            readInjuriesList();
            return null;
        }

        public void execute(String teamabbr)
        {
            // TODO Auto-generated method stub

        }

    }

    public class DataLoadingTaskTeamRanksOffence extends
            AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            myProgress.cancel();
            displayTeamRanksOffence();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(Team.this, "...retrieving...",
                    "...please wait...", true, false);
            preReadTeamRanksOffence();

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
            readTeamRanksOffence();
            return null;
        }

        public void execute(String teamabbr)
        {
            // TODO Auto-generated method stub

        }

    }

    public class DataLoadingTaskTeamRanksDefence extends
            AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            myProgress.cancel();
            displayTeamRanksDefence();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(Team.this, "...retrieving...",
                    "...please wait...", true, false);
            preReadTeamRanksDefence();

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
            readTeamRanksDefence();
            return null;
        }

        public void execute(String teamabbr)
        {
            // TODO Auto-generated method stub

        }

    }

    private RSSFeedVideo myRssFeedVideo = null;

    TextView feedTitle;

    TextView feedDescribtion;

    TextView feedPubdate;

    TextView feedLink;

    public class MyCustomAdapter extends ArrayAdapter<HNICTeamLastTen>
    {

        String strFileDate = null;

        String startDate = null;

        public MyCustomAdapter(Context context, int textViewResourceId,
                List<HNICTeamLastTen> theList)
        {
            super(context, textViewResourceId, theList);
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
                row = inflater.inflate(R.layout.row_team, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            TextView listDetail = (TextView) row.findViewById(R.id.listdetail);

            /*listTitle.setText(theList.get(position).getAway() + "@"
                    + theList.get(position).getHome());*/

            listTitle.setText(theList.get(position).getAwayfull() + "@"
                    + theList.get(position).getHomefull()
                    );
            
            listDetail.setText("Result : " + theList.get(position).getResult()

            + "\n"

            + "Games Played : " + theList.get(position).getGamesplayed()

            + "\n"

            + "Win Loss OT : " + theList.get(position).getWinlossot()

            + "\n"

            + "Power Play Goals : " + theList.get(position).getPowerplaygoals()

            + "\n"

            + "Penalty Kill : " + theList.get(position).getPenaltykill()

            + "\n"

            + "Shots on goal : " + theList.get(position).getShotsongoal()

            + "\n"

            + "Shots against : " + theList.get(position).getShotsagainst()

            + "\n"

            + "fo:" + theList.get(position).getFo()

            + "\n"

            + "Goalie Saves : " + theList.get(position).getGoaliesaves()

            + "\n"

            + "Star1 : " + theList.get(position).getStar1()

            + "\n"

            + "Star2 : " + theList.get(position).getStar2()

            + "\n"

            + "Star3 : " + theList.get(position).getStar3()

            );

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            strFileDate = theList.get(position).getStart_date_time();

            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ssZ");

            SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

            try
            {
                startDate = df2.format(df1.parse(strFileDate));
            } catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            listPubdate.setText(startDate);

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

    public class MyCustomAdapterRoster extends ArrayAdapter<HNICPlayer>
    {

        String strFileDate = null;

        String startDate = null;

        public MyCustomAdapterRoster(Context context, int textViewResourceId,
                List<HNICPlayer> theRosterList)
        {
            super(context, textViewResourceId, theRosterList);
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
                row = inflater.inflate(R.layout.row_team, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            TextView listDetail = (TextView) row.findViewById(R.id.listdetail);

            listTitle.setText(theRosterList.get(position).getName());

            listDetail.setText("Position : "
                    + theRosterList.get(position).getPosition() + "\n"
                    + "Jersey : " + theRosterList.get(position).getJersey()
                    + "\n" + "Height : "
                    + theRosterList.get(position).getHeight() + "\n"
                    + "Weight : " + theRosterList.get(position).getWeight()
                    + "\n"

            );

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            listPubdate.setText("Birthplace : "

            + theRosterList.get(position).getBirthplace() + "\n"

            + "Birthdate : "

            + theRosterList.get(position).getBirthdate()

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

    public class MyCustomAdapterInjuriesList extends ArrayAdapter<HNICInjury>
    {

        String strFileDate = null;

        String startDate = null;

        public MyCustomAdapterInjuriesList(Context context,
                int textViewResourceId, List<HNICInjury> theInjuriesList)
        {
            super(context, textViewResourceId, theInjuriesList);
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
                row = inflater.inflate(R.layout.row_team, parent, false);
            }

            Log.d(TAG, "----------------something needs to be asserted here");

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            TextView listDetail = (TextView) row.findViewById(R.id.listdetail);

            listTitle.setText(theInjuriesList.get(position).getPlayer());

            listDetail.setText(theInjuriesList.get(position).getStatus()
                    + " : " + theInjuriesList.get(position).getDesc());

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            strFileDate = theInjuriesList.get(position).getInjurydate();

            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ssZ");

            SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

            try
            {
                startDate = df2.format(df1.parse(strFileDate));
            } catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            listPubdate.setText("Date of Injury : " + startDate);

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
        setContentView(R.layout.team_main);

        Bundle bundle = this.getIntent().getExtras();

        String abbr = bundle.getString("abbr").toLowerCase();

        abbrupper = bundle.getString("abbr").toUpperCase();

      

        List<HNICTeam> allTeams = new ArrayList<HNICTeam>();
        
        
        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        HNICTeam thisTeam = databaseDataLayer.SelectTeam(abbr);
        
        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        
        section_header_text.setText(thisTeam.getFullname());
        
      
        /*AppMeasurement s;

        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 
        s.account = "cbc-mobile-dev-2";
        
        s.account = "cbc-mobile-prod-9";

         You may add or alter any code config here 

        s.channel = "android-app";

        s.prop1 = "android-app:hockey-app";
        
        s.prop2 = "android-app:hockey-app:" + C2;
        
        s.prop3 = "android-app:hockey-app:" + C2 + ":" + thisTeam.getFullname();
        
        s.prop4 = "page";

        s.pageName = "android-app:hockey-app:" + C2 + ":" + thisTeam.getFullname();

        s.currencyCode = "CAD";

         Turn on and configure debugging here 
        s.debugTracking = true;

        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();*/
        
        
        
        
        
        TextView rank_division = (TextView) findViewById(R.id.rank_division);
        rank_division.setText("Division Rank : " + thisTeam.getRank_division());
        
        
        TextView rank_conference = (TextView) findViewById(R.id.rank_conference);
        rank_conference.setText("Conference Rank : " + thisTeam.getRank_conference());
        
        TextView played = (TextView) findViewById(R.id.played);
        
        played.setText("W-L-OT : " + thisTeam.getWon() + "-" + thisTeam.getLost() + "-" + thisTeam.getOT());
        
        

        /* String fullname = bundle.getString("fullname"); */

        /*
         * TextView section_subheader_text = (TextView)
         * findViewById(R.id.section_subheader_text);
         * section_subheader_text.setText(fullname);
         */

        /*
         * TextView section_header_text = (TextView)
         * findViewById(R.id.section_header_text);
         * section_header_text.setText(fullname);
         */
        ImageView teamlogo = (ImageView) findViewById(R.id.teamlogo);

        String myyDrawable = abbr;

        int resID = getBaseContext().getResources().getIdentifier(myyDrawable,
                "drawable", "com.troubadorian.mobile.android.nhlhockey");

        teamlogo.setImageResource(resID);

        /*
         * TextView section_subhead = (TextView)
         * findViewById(R.id.section_subhead);
         * section_subhead.setText(fullname);
         */

        /*
         * TextView rank_division = (TextView) findViewById(R.id.rank_division);
         * rank_division.setText("Division Rank : " +
         * bundle.getString("rank-division"));
         * 
         * TextView rank_conference = (TextView)
         * findViewById(R.id.rank_conference);
         * rank_conference.setText("Conference Rank : " +
         * bundle.getString("rank-conference"));
         * 
         * TextView played = (TextView) findViewById(R.id.played);
         * played.setText("Played : " + bundle.getString("played"));
         * 
         * TextView won = (TextView) findViewById(R.id.won);
         * won.setText("Won : " + bundle.getString("won"));
         * 
         * TextView lost = (TextView) findViewById(R.id.lost);
         * lost.setText("Lost : " + bundle.getString("lost"));
         */

        /*
         * feedTitle = (TextView)findViewById(R.id.feedtitle); feedDescribtion =
         * (TextView)findViewById(R.id.feeddescribtion); feedPubdate =
         * (TextView)findViewById(R.id.feedpubdate); feedLink =
         * (TextView)findViewById(R.id.feedlink);
         */
        /*AdMarvelView adMarvelView = (AdMarvelView) findViewById(R.id.ad);
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



        startReadTeamLastTen();
    }

    private void startReadTeamLastTen()
    {
        new DataLoadingTaskTeamLastTen().execute();
    }

    private void startReadRoster()
    {
        new DataLoadingTaskRoster().execute();
    }

    private void startReadInjuriesList()
    {
        new DataLoadingTaskInjuriesList().execute();
    }

    private void startReadTeamRanksOffence()
    {
        new DataLoadingTaskTeamRanksOffence().execute();
    }

    private void startReadTeamRanksDefence()
    {
        new DataLoadingTaskTeamRanksDefence().execute();
    }

    private void preReadTeamLastTen()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        setListAdapter(null);

        TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

        TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

        newlistTitle.setText("");

        newlistDetail.setText("");

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void preReadRoster()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        setListAdapter(null);

        TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

        TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

        newlistTitle.setText("");

        newlistDetail.setText("");

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void preReadInjuriesList()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        setListAdapter(null);

        TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

        TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

        newlistTitle.setText("");

        newlistDetail.setText("");

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void preReadTeamRanksOffence()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        setListAdapter(null);

        TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

        TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

        newlistTitle.setText("");

        newlistDetail.setText("");

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void preReadTeamRanksDefence()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        setListAdapter(null);

        TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

        TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

        newlistTitle.setText("");

        newlistDetail.setText("");

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void readTeamLastTen()
    {
        /*
         * String theUrl =
         * "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_"
         * 
         * + abbrupper + ".json";
         * 
         * HNICTeamPlayerReader hnicteamplayerreader = new
         * HNICTeamPlayerReader();
         * 
         * theList = hnicteamplayerreader.readTeamPlayer(theUrl);
         */

        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());
        
        String theUrl = "http://www.cbc.ca/data/statsfeed/plist/teams/teamlast10_iPad_"
                + abbrupper + ".json";

        HNICTeamLastTenReader hnicteamlasttenreader = new HNICTeamLastTenReader();

        theList = hnicteamlasttenreader.readLastTen(theUrl);
        
        if (theList != null)
        {
            for (final HNICTeamLastTen tlt : theList)
            {
                /* start of db lookup to get and set the full teamname */ 
                
                HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(tlt.getAway());
                
                tlt.setAwayfull(thisTeamAway.getFullname());
                
                
                HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(tlt.getHome());
                
                tlt.setHomefull(thisTeamHome.getFullname());
                
                
                /* end of db lookup to get and set the full teamname */
            }
        }
        
        
        
        
        String liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_" + abbrupper + ".json";

        HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

        nextgamedate = hnicschedulescoreboardreader
                .readScheduleScoreboardForTeamAndReturnNextGameDate(liveScheduleScoreboardUrl);
        
       

    }

    private void readRoster()
    {
        /*
         * String theUrl =
         * "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_"
         * 
         * + abbrupper + ".json";
         * 
         * HNICTeamPlayerReader hnicteamplayerreader = new
         * HNICTeamPlayerReader();
         * 
         * theList = hnicteamplayerreader.readTeamPlayer(theUrl);
         */
        /*
         * String theTeamPlayerUrl =
         * "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_" +
         * abbrupper + ".json";
         * 
         * HNICTeamPlayerReader hnicteamplayerreader = new
         * HNICTeamPlayerReader();
         * 
         * theTeamPlayerList = hnicteamplayerreader
         * .readTeamPlayer(theTeamPlayerUrl);
         */

        String theRosterUrl = "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_"
                + abbrupper + ".json";

        HNICRosterReader hnicrosterreader = new HNICRosterReader();

        theRosterList = hnicrosterreader.readRoster(theRosterUrl);
    }

    private void readInjuriesList()
    {
        /*
         * String theUrl =
         * "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_"
         * 
         * + abbrupper + ".json";
         * 
         * HNICTeamPlayerReader hnicteamplayerreader = new
         * HNICTeamPlayerReader();
         * 
         * theList = hnicteamplayerreader.readTeamPlayer(theUrl);
         */
        /*
         * String theTeamPlayerUrl =
         * "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_" +
         * abbrupper + ".json";
         * 
         * HNICTeamPlayerReader hnicteamplayerreader = new
         * HNICTeamPlayerReader();
         * 
         * theTeamPlayerList = hnicteamplayerreader
         * .readTeamPlayer(theTeamPlayerUrl);
         */

        Log.d(TAG, "-------------start of readInjuriesList");

        String theInjuriesListUrl = "http://www.cbc.ca/data/statsfeed/plist/teams/teamplayer_"
                + abbrupper + ".json";

        HNICInjuriesListReader hnicinjurieslistreader = new HNICInjuriesListReader();

        theInjuriesList = hnicinjurieslistreader
                .readInjuries(theInjuriesListUrl);

        if (theInjuriesList != null)
        {
            Log.d(TAG, "----------theInjuriesList is there");
        } else
        {
            Log.d(TAG,
                    "----------theInjuriesList was read and no injuries could be found");

        }
    }

    private void readTeamRanksOffence()
    {
        String theTeamRanksListOffenceUrl = "http://www.cbc.ca/data/statsfeed/plist/teams/teamranks_"
                + abbrupper + ".json";

        HNICTeamRanksReader hnicteamranksreader = new HNICTeamRanksReader();

        theTeamRanksOffence = hnicteamranksreader
                .readTeamRanks(theTeamRanksListOffenceUrl);

    }

    private void readTeamRanksDefence()
    {
        String theTeamRanksListOffenceUrl = "http://www.cbc.ca/data/statsfeed/plist/teams/teamranks_"
                + abbrupper + ".json";

        HNICTeamRanksReader hnicteamranksreader = new HNICTeamRanksReader();

        theTeamRanksDefence = hnicteamranksreader
                .readTeamRanks(theTeamRanksListOffenceUrl);

    }

    private void displayTeamLastTen()
    {

        if (theList != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            /* List<HNICPlayer> theRoster = theList.getRoster(); */

            TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

            TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

            newlistTitle.setText("Latest");

            TextView nextgame = (TextView) findViewById(R.id.nextgame);
            
           /* try
            {

                SimpleDateFormat df1 = new SimpleDateFormat(
                        "yyyyMMdd'T'HH:mm:ssZ");

                SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

             

                nextgamedateformatted = df2.format(df1.parse(nextgamedate));

                

            }

            catch (java.text.ParseException e)
            {
                Log.e(TAG, "Parsing of date exception occured" + e.toString());
            }
            
            finally
            {
                nextgame.setText("Next Game : " + nextgamedateformatted);
            }*/
            
            nextgame.setText("Next Game : " + nextgamedate);
            
            MyCustomAdapter adapter = new MyCustomAdapter(this,
                    R.layout.row_team, theList);

            setListAdapter(adapter);

        }

    }

    private void displayRoster()
    {

        if (theRosterList != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            /* List<HNICPlayer> theRoster = theList.getRoster(); */

            TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

            TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

            newlistTitle.setText("Roster");

            MyCustomAdapterRoster adapter2 = new MyCustomAdapterRoster(this,
                    R.layout.row_team, theRosterList);

            setListAdapter(adapter2);

        }
    }

    private void displayInjuriesList()
    {

        Log.d(TAG, "------------------start of displayInjuriesList");

        if (theInjuriesList != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            /* List<HNICPlayer> theRoster = theList.getRoster(); */

            Log.d(TAG,
                    "----------------------start of retrieving injuries list");

            TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

            TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

            newlistTitle.setText("Injuries List");

            MyCustomAdapterInjuriesList adapter3 = new MyCustomAdapterInjuriesList(
                    this, R.layout.row_team, theInjuriesList);

            setListAdapter(adapter3);

            Log.d(TAG, "----------------------end of displaying injuries list");

        } else
        {
            Log.d(TAG, "------------------theInjuriesList is null");
        }
    }

    private void displayTeamRanksOffence()
    {

        Log.d(TAG, "---------------start of displayTeamRanksOffence");

        if (theTeamRanksOffence != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            /* List<HNICPlayer> theRoster = theList.getRoster(); */

            Log.d(TAG,
                    "----------------------start of retrieving team ranks offence");

            /*
             * MyCustomAdapterTeamRanksOffence adapter4 = new
             * MyCustomAdapterTeamRanksOffence(this, R.layout.row_team,
             * theTeamRanksOffence);
             * 
             * setListAdapter(adapter4);
             */

            /*
             * RelativeLayout item = (RelativeLayout)
             * findViewById(R.id.widget25);
             * 
             * View child = getLayoutInflater().inflate(R.layout.row_team, item,
             * true);
             * 
             * item.addView(child);
             */

            TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

            TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

            newlistTitle.setText("Offence");

            newlistDetail.setText("Goals : " + theTeamRanksOffence.getGoals()

            + "\n"

            + "Shots : " + theTeamRanksOffence.getShots()

            + "\n"

            + "PP% : " + theTeamRanksOffence.getPowerplay_success()

            + "\n"

            + "Penalty Minutes : "
                    + theTeamRanksOffence.getPenalty_minutes_offense()

                    + "\n"

                    + "Assists : " + theTeamRanksOffence.getAssits()

                    + "\n"

                    + "SH Goals : " + theTeamRanksOffence.getShorthanded()

            );

            Log.d(TAG, "----------------------end of displayTeamRanksOffence");

        }
    }

    private void displayTeamRanksDefence()
    {

        Log.d(TAG, "------------------start of displayTeamRanksDefence");

        if (theTeamRanksDefence != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            /* List<HNICPlayer> theRoster = theList.getRoster(); */

            Log.d(TAG,
                    "----------------------start of retrieving team ranks defence");

            /*
             * RelativeLayout item = (RelativeLayout)
             * findViewById(R.id.widget25);
             * 
             * View child = getLayoutInflater().inflate(R.layout.row_team, item,
             * true);
             * 
             * item.addView(child);
             */

            TextView newlistTitle = (TextView) findViewById(R.id.newlisttitle);

            TextView newlistDetail = (TextView) findViewById(R.id.newlistdetail);

            newlistTitle.setText("Defence");

            newlistDetail.setText("Goals Against : "
                    + theTeamRanksDefence.getGoals_against()

                    + "\n"

                    + "Shots Against : "
                    + theTeamRanksDefence.getShots_against()

                    + "\n"

                    + "PK% : " + theTeamRanksDefence.getPenalty_killing()

                    + "\n"

                    + "Penalty Minutes : "
                    + theTeamRanksDefence.getPenalty_minutes_defense()

                    + "\n"

                    + "Shutouts : " + theTeamRanksDefence.getShutouts()

                    + "\n"

                    + "Sv% : " + theTeamRanksDefence.getSave_percentage()

            );

            Log.d(TAG, "----------------------end of displayTeamRanksDefence");

        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        /* commenting line below out temporarily to run newdetails in webview */

        /* String guid = myRssFeedVideo.getItem(position).getGuid(); */

        /*
         * Intent intent = new Intent(this, VideoViewActivity.class);
         * 
         * 
         * Bundle bundle = new Bundle(); bundle.putString("title",
         * myRssFeedVideo.getItem(position).getTitle());
         * bundle.putString("description", myRssFeedVideo.getItem(position)
         * .getDescription()); bundle.putString("link",
         * myRssFeedVideo.getItem(position).getLink()); bundle
         * .putString("pubDate", myRssFeedVideo.getItem(position)
         * .getPubdate());
         * 
         * 
         * 
         * bundle.putString("guid", myRssFeedVideo.getItem(position).getGuid());
         * 
         * 
         * intent.putExtras(bundle);
         * 
         * startActivity(intent);
         */

        /*
         * playVideo(
         * "http://cds1.yospace.com/access/choice/u/0/1/15749276?rtspdirect=true&f=001110786488&stylesheet=mobile"
         * , true);
         */

        /*
         * String url =
         * "http://cds1.yospace.com/access/pass/CA/advert/11029005/cbc01/-/d/400/u/0/1/15749276/-/http/mobilevideo.cbc.ca/u/pass~drmnone/1/t/~s~3gp~h263~176x144~15~387~0~amr~1~8000~12.2/1/s/~s/1/(f/~video_mpeg~5002-1/1/m/z/5/q/c/2j2c/cbc01)(f/~video_mpeg~5002-1/1/m/v/o/q/4/j24v/cbc01)/15749276?f=001110786488"
         * ;
         */

        /*
         * String uriString = "http://cds1.yospace.com/access/choice/u/0/1/" +
         * guid + "?rtspdirect=true&f=001111582643&stylesheet=mobile";
         * 
         * Intent intent = new Intent("android.intent.action.VIEW",
         * Uri.parse(uriString));
         * 
         * startActivity(intent);
         */
        /*
         * i.setClass(CBCNewsAndroidWebView.this, VideoViewActivity.class);
         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        /* menu.add(0, 0, 0, "Reload"); */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.team_contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        CharSequence text;

        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
        case (R.id.latest):

            /*
             * text = "...latest...";
             * 
             * toast = Toast.makeText(context, text, duration); toast.show();
             */

            startReadTeamLastTen();

            break;

        case (R.id.teaminjuries):

            /*
             * text = "...team injuries...";
             * 
             * toast = Toast.makeText(context, text, duration); toast.show();
             */

            startReadInjuriesList();

            break;
        case (R.id.roster):

            /*
             * text = "...roster...";
             * 
             * toast = Toast.makeText(context, text, duration); toast.show();
             */

            theActivePlayerList = theRosterList;

            startReadRoster();

            break;

        case (R.id.offence):

            /*
             * text = "...offence...";
             * 
             * toast = Toast.makeText(context, text, duration); toast.show();
             */

            startReadTeamRanksOffence();

            break;

        case (R.id.defence):

            /*
             * text = "...defence...";
             * 
             * toast = Toast.makeText(context, text, duration); toast.show();
             */

            startReadTeamRanksDefence();

            break;

        default:
            startReadTeamLastTen();
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

        Intent b = new Intent(this, Team.class);
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