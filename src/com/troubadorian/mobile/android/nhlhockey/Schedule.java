/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import com.troubadorian.mobile.android.model.HNICBoxScore;
import com.troubadorian.mobile.android.model.HNICGame;
import com.troubadorian.mobile.android.model.HNICScheduleScoreboardReader;
import com.troubadorian.mobile.android.model.HNICTeam;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Schedule extends ListActivity 
{
    ProgressDialog myProgress;

    public static final String TAG = "Schedule";

    private static final String C2 = "Schedule";

    private static final String C3 = "All Teams";

    List<HNICBoxScore> theList = null;

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

    TextView section_team;

    TextView section_date;

    private int mYear;

    private int mMonth;

    private int mDay;

    static final int DATE_DIALOG_ID = 0;

    static final int TEAM_DIALOG_ID = 0;

    TextView feedTitle;

    TextView feedDescribtion;

    TextView feedPubdate;

    TextView feedLink;

    List<HNICGame> gameList = null;

    List<HNICGame> datemodifiedGameList = null;

    List<HNICGame> teammodifiedGameList = null;

    List<HNICGame> theGameList = null;

    String start_date;

    String start_time;

    public class DataLoadingTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub

            /* myProgress.hide(); */

            myProgress.dismiss();

            /* myProgress = null; */

            display();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub

            myProgress = new ProgressDialog(Schedule.this);

            myProgress.setMessage("Please wait..");

            myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            myProgress.setCancelable(true);

            myProgress.show();

            /*
             * myProgress = ProgressDialog.show(Schedule.this, "Please wait...",
             * "...loading...", true, false);
             */

            preReadScheduleScoreboard();

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
            read();
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
                List<HNICGame> theGameList)
        {
            super(context, textViewResourceId, theGameList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            // return super.getView(position, convertView, parent);

            View row = convertView;

            String strFileDate = null;

            String startDate = null;

            String startTime = null;

            if (row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_schedule, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            /*
             * HNICTeam thisTeamAway =
             * databaseDataLayer.SelectTeam(theGameList.get(position)
             * .getAway().toLowerCase());
             * 
             * HNICTeam thisTeamHome =
             * databaseDataLayer.SelectTeam(theGameList.get(position)
             * .getHome().toLowerCase());
             */

            listTitle.setText(theGameList.get(position).getAwayfull()

            + " @ "

            + theGameList.get(position).getHomefull());

            /*
             * listTitle.setText(thisTeamAway.getFullname()
             * 
             * + " @ "
             * 
             * + thisTeamHome.getFullname());
             */

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            TextView listPubtime = (TextView) row
                    .findViewById(R.id.listpubtime);

            strFileDate = theGameList.get(position).getStart_date_time();

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

            catch (java.text.ParseException e)
            {
                Log.e(TAG, "Parsing of date exception occured" + e.toString());
            }

            listPubdate.setText(startDate);

            listPubtime.setText(startTime);

            /* TextView listoncbc = (TextView) findViewById(R.id.listoncbc); */

            /*
             * if
             * (theGameList.get(position).getAvailable_on_cbc().compareTo("yes")
             * == 0) {
             * 
             * try {
             * 
             * }
             * 
             * catch (Exception e) { Log.e(TAG, "---------------bad" +
             * e.toString()); } } else {
             * 
             * try {
             * 
             * }
             * 
             * catch (Exception e) { Log.e(TAG, "---------------bad" +
             * e.toString()); }
             * 
             * }
             */

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

    public class MyCustomAdapterDateModified extends ArrayAdapter<HNICGame>
    {

        public MyCustomAdapterDateModified(Context context,
                int textViewResourceId, List<HNICGame> theGameList)
        {
            super(context, textViewResourceId, theGameList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            // return super.getView(position, convertView, parent);

            View row = convertView;

            String strFileDate = null;

            String startDate = null;

            String startTime = null;

            if (row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_schedule, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            /*
             * HNICTeam thisTeamAway =
             * databaseDataLayer.SelectTeam(theGameList.get(position)
             * .getAway().toLowerCase());
             * 
             * HNICTeam thisTeamHome =
             * databaseDataLayer.SelectTeam(theGameList.get(position)
             * .getHome().toLowerCase());
             */

            listTitle.setText(theGameList.get(position).getAwayfull()

            + " @ "

            + theGameList.get(position).getHomefull());

            /*
             * listTitle.setText(thisTeamAway.getFullname()
             * 
             * + " @ "
             * 
             * + thisTeamHome.getFullname());
             */

            /*
             * listTitle.setText(theGameList.get(position).getAway() + " @ "
             * 
             * + theGameList.get(position).getHome());
             */

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            TextView listPubtime = (TextView) row
                    .findViewById(R.id.listpubtime);

            strFileDate = theGameList.get(position).getStart_date_time();

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

            catch (java.text.ParseException e)
            {
                Log.e(TAG, "Parsing of date exception occured" + e.toString());
            }

            listPubdate.setText(startDate);

            listPubtime.setText(startTime);

            /* TextView listoncbc = (TextView) findViewById(R.id.listoncbc); */

            /*
             * if
             * (theGameList.get(position).getAvailable_on_cbc().compareTo("yes")
             * == 0) {
             * 
             * try {
             * 
             * }
             * 
             * catch (Exception e) { Log.e(TAG, "---------------bad" +
             * e.toString()); } } else {
             * 
             * try {
             * 
             * }
             * 
             * catch (Exception e) { Log.e(TAG, "---------------bad" +
             * e.toString()); }
             * 
             * }
             */

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

    public class MyCustomAdapterTeamModified extends ArrayAdapter<HNICGame>
    {
        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        public MyCustomAdapterTeamModified(Context context,
                int textViewResourceId, List<HNICGame> theGameList)
        {
            super(context, textViewResourceId, theGameList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            // return super.getView(position, convertView, parent);

            View row = convertView;

            String strFileDate = null;

            String startDate = null;

            String startTime = null;

            if (row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_schedule, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            /*
             * HNICTeam thisTeamAway =
             * databaseDataLayer.SelectTeam(theGameList.get(position)
             * .getAway().toLowerCase());
             * 
             * HNICTeam thisTeamHome =
             * databaseDataLayer.SelectTeam(theGameList.get(position)
             * .getHome().toLowerCase());
             */

            listTitle.setText(theGameList.get(position).getAwayfull()

            + " @ "

            + theGameList.get(position).getHomefull());

            /*
             * listTitle.setText(thisTeamAway.getFullname()
             * 
             * + " @ "
             * 
             * + thisTeamHome.getFullname());
             */

            /*
             * listTitle.setText(theGameList.get(position).getAway() + " @ "
             * 
             * + theGameList.get(position).getHome());
             */

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            TextView listPubtime = (TextView) row
                    .findViewById(R.id.listpubtime);

            strFileDate = theGameList.get(position).getStart_date_time();

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

            catch (java.text.ParseException e)
            {
                Log.e(TAG, "Parsing of date exception occured" + e.toString());
            }

            listPubdate.setText(startDate);

            listPubtime.setText(startTime);

            /* TextView listoncbc = (TextView) findViewById(R.id.listoncbc); */

            if (theGameList.get(position).getAvailable_on_cbc()
                    .compareTo("yes") == 0)
            {

                try
                {

                }

                catch (Exception e)
                {
                    Log.e(TAG, "---------------bad" + e.toString());
                }
            } else
            {

                try
                {

                }

                catch (Exception e)
                {
                    Log.e(TAG, "---------------bad" + e.toString());
                }

            }

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

    public class MyOnItemSelectedListener implements OnItemSelectedListener
    {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                long id)
        {
            Log.d(TAG, parent.getItemAtPosition(pos).toString());

            TextView section_team = (TextView) findViewById(R.id.section_team);
            section_team
                    .setText(parent.getItemAtPosition(pos).toString() + " ");

           /*updateTeam(parent.getItemAtPosition(pos).toString());*/
            
            if (parent.getItemAtPosition(pos).toString().equals("Boston Bruins"))
            {
                updateTeam("BOS");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Montreal Canadiens"))
            {
                updateTeam("MON");
            }

            else if (parent.getItemAtPosition(pos).toString().equals("Buffalo Sabres"))
            {
                updateTeam("BUF");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Toronto Maple Leafs"))
            {
                updateTeam("TOR");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Ottawa Senators"))
            {
                updateTeam("OTT");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Philadelphia Flyers"))
            {
                updateTeam("PHI");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Pittsburgh Penguins"))
            {
                updateTeam("PIT");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("New York Rangers"))
            {
                updateTeam("NYR");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("New Jersey Devils"))
            {
                updateTeam("NJ");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("New York Islanders"))
            {
                updateTeam("NYI");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Washington Capitals"))
            {
                updateTeam("WAS");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Tampa Bay Lightning"))
            {
                updateTeam("TB");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Carolina Hurricanes"))
            {
                updateTeam("CAR");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Atlanta Thrashers"))
            {
                updateTeam("ATL");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Florida Panthers"))
            {
                updateTeam("FLA");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Detroit Red Wings"))
            {
                updateTeam("DET");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Nashville Predators"))
            {
                updateTeam("NSH");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Chicago Blackhawks"))
            {
                updateTeam("CHI");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Columbus Blue Jackets"))
            {
                updateTeam("CLS");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("St. Louis Blues"))
            {
                updateTeam("STL");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("San Jose Sharks"))
            {
                updateTeam("SJ");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Phoenix Coyotes"))
            {
                updateTeam("PHO");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Los Angeles Kings"))
            {
                updateTeam("LA");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Anaheim Ducks"))
            {
                updateTeam("ANH");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Dallas Stars"))
            {
                updateTeam("DAL");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Vancouver Canucks"))
            {
                updateTeam("VAN");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Calgary Flames"))
            {
                updateTeam("CGY");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Minnesota Wild"))
            {
                updateTeam("MIN");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Colorado Avalanche"))
            {
                updateTeam("COL");
            }
            else 
            {
                /* don't do anything */
            }

            section_date = (TextView) findViewById(R.id.section_date);

            section_date.setText("");
        }

        public void onNothingSelected(AdapterView parent)
        {
            // Do nothing.
        }
    }

    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                int dayOfMonth)
        {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            TextView section_team = (TextView) findViewById(R.id.section_team);
            section_team.setText("");
            updateDate();
        }
    };

    public class MyCustomAdapterResults extends ArrayAdapter<HNICBoxScore>
    {

        public MyCustomAdapterResults(Context context, int textViewResourceId,
                List<HNICBoxScore> theList)
        {
            super(context, textViewResourceId, theList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            // return super.getView(position, convertView, parent);

            View row = convertView;

            String strFileDate = null;

            String startDate = null;

            String startTime = null;

            if (row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_schedule, parent, false);
            }

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            TextView listPubtime = (TextView) row
                    .findViewById(R.id.listpubtime);

            listTitle.setText(theList.get(position).getAway() + " @ "

            + theList.get(position).getHome()

            );

            strFileDate = theList.get(position).getStart_date_time();

            try
            {

                SimpleDateFormat df1 = new SimpleDateFormat(
                        "yyyyMMdd'T'HH:mm:ssZ");

                SimpleDateFormat df2 = new SimpleDateFormat("E M/d");

                SimpleDateFormat df3 = new SimpleDateFormat("h:m a z");

                startDate = df2.format(df1.parse(strFileDate));

                startTime = df3.format(df1.parse(strFileDate));

            }

            catch (java.text.ParseException e)
            {
                Log.e(TAG, "Parsing of date exception occured" + e.toString());
            }

            listPubdate.setText(startDate);

            listPubtime.setText(theList.get(position).getAwayBoxScore()
                    .getScore()
                    + " - "

                    + theList.get(position).getHomeBoxScore().getScore());

            if (position % 2 == 0)
            {
                // listTitle.setBackgroundColor(0xff101010);

                /* listTitle.setBackgroundColor(R.color.white); */
                // listTitle.setTextColor(R.color.black);

                // listPubdate.setBackgroundColor(0xff101010);
                // listPubdate.setBackgroundColor(R.color.white);
                // listPubdate.setTextColor(R.color.black);

            } else
            {
                // listTitle.setBackgroundColor(0xff080808);
                /* listTitle.setBackgroundColor(R.color.white); */
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
        setContentView(R.layout.schedule_main);

        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

        // get an instance
        Calendar cal = Calendar.getInstance();

        // initialise the start or current date
        Date today = cal.getTime();

        SimpleDateFormat df2 = new SimpleDateFormat("M-d-yyyy");

        TextView section_team = (TextView) findViewById(R.id.section_team);
        TextView section_date = (TextView) findViewById(R.id.section_date);
        section_team.setText("All Teams ");
        section_date.setText(df2.format(today));

        /*
         * feedTitle = (TextView)findViewById(R.id.feedtitle); feedDescribtion =
         * (TextView)findViewById(R.id.feeddescribtion); feedPubdate =
         * (TextView)findViewById(R.id.feedpubdate); feedLink =
         * (TextView)findViewById(R.id.feedlink);
         */
       
        
        /* what if i don't ask for admarvel */
        /*adMarvelView.requestNewAd(targetParams, _partnerId, _siteId);*/

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
            { // Log.d(TAG, "mHomeButton clicked"); //
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
                finish();
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



        // Hook up button presses to the appropriate event handler.
        /*
         * ((Button) findViewById(R.id.home_button))
         * .setOnClickListener(mBackListener);
         */

        startReadScheduleScoreboard();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        /* start of magical way to create key-value-pair spinner */

        /*
         * final TeamData items[] = new TeamData[30];
         * 
         * items[0] = new TeamData("BOS", "Boston Bruins"); items[1] = new
         * TeamData("MON", "Montreal Canadiens"); items[2] = new TeamData("BUF",
         * "Buffalo Sabres"); items[3] = new TeamData("TOR",
         * "Toronto Maple Leafs"); items[4] = new TeamData("OTT",
         * "Ottawa Senators"); items[5] = new TeamData("PHI",
         * "Philadelphia Penguins"); items[6] = new TeamData("PIT",
         * "Pittsburgh Penguins"); items[7] = new TeamData("NYR",
         * "New York Rangers"); items[8] = new TeamData("NJ",
         * "New Jersey Devils"); items[9] = new TeamData("NYI",
         * "New York Islanders"); items[10] = new TeamData("WAS",
         * "Washington Capitals"); items[11] = new TeamData("TB",
         * "Tampa Bay Lightning"); items[12] = new TeamData("CAR",
         * "Carolina Hurricanes"); items[13] = new TeamData("ATL",
         * "Atlanta Thrashers"); items[14] = new TeamData("FLA",
         * "Florida Panthers"); items[15] = new TeamData("DET",
         * "Detroit Red Wings"); items[16] = new TeamData("CHI",
         * "Chicago Blackhawks"); items[17] = new TeamData("NSH",
         * "Nashville Predators"); items[18] = new TeamData("CLS",
         * "Columbus Blue Jackets"); items[19] = new TeamData("STL",
         * "St. Louis Blues"); items[20] = new TeamData("SJ",
         * "San Jose Sharks"); items[21] = new TeamData("PHO",
         * "Phoenix Coyotes"); items[22] = new TeamData("LA",
         * "Los Angeles Kings"); items[23] = new TeamData("ANH",
         * "Anaheim Ducks"); items[24] = new TeamData("DAL", "Dallas Stars");
         * items[25] = new TeamData("VAN", "Vancouver Canucks"); items[26] = new
         * TeamData("CGY", "Calgary Flames"); items[27] = new TeamData("MIN",
         * "Minnesota Wild"); items[28] = new TeamData("COL",
         * "Colorado Avalanche"); items[29] = new TeamData("EDM",
         * "Edmonton Oilers");
         * 
         * ArrayAdapter<TeamData> adapter = new ArrayAdapter<TeamData>(this,
         * android.R.layout.simple_spinner_item, items);
         * 
         * adapter.setDropDownViewResource(android.R.layout.
         * simple_spinner_dropdown_item);
         * 
         * spinner.setAdapter(adapter);
         * 
         * spinner.setOnItemSelectedListener(new MyNewOnItemSelectedListener());
         */
        /* end of magical way to create key-value-pair spinner */

        /* start of old way to create a spinner */

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.teams_array_full,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        /* end of old way to create a spinner */

    }

    public class MyNewOnItemSelectedListener implements OnItemSelectedListener
    {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                long id)
        {

            Log.d(TAG, "---------------what was selected"
                    + parent.getItemAtPosition(pos).toString());

            TextView section_team = (TextView) findViewById(R.id.section_team);
            section_team
                    .setText(parent.getItemAtPosition(pos).toString() + " ");

            /*updateTeam(parent.getItemAtPosition(pos).toString());*/
            
            if (parent.getItemAtPosition(pos).toString().equals("Boston Bruins"))
            {
                updateTeam("BOS");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Montreal Canadiens"))
            {
                updateTeam("MON");
            }

            else if (parent.getItemAtPosition(pos).toString().equals("Buffalo Sabres"))
            {
                updateTeam("BUF");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Toronto Maple Leafs"))
            {
                updateTeam("TOR");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Ottawa Senators"))
            {
                updateTeam("OTT");
            }
            
            else if (parent.getItemAtPosition(pos).toString().equals("Philadelphia Flyers"))
            {
                updateTeam("PHI");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Pittsburgh Penguins"))
            {
                updateTeam("PIT");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("New York Rangers"))
            {
                updateTeam("NYR");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("New Jersey Devils"))
            {
                updateTeam("NJ");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("New York Islanders"))
            {
                updateTeam("NYI");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Washington Capitals"))
            {
                updateTeam("WAS");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Tampa Bay Lightning"))
            {
                updateTeam("TB");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Carolina Hurricanes"))
            {
                updateTeam("CAR");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Atlanta Thrashers"))
            {
                updateTeam("ATL");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Florida Panthers"))
            {
                updateTeam("FLA");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Detroit Red Wings"))
            {
                updateTeam("DET");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Nashville Predators"))
            {
                updateTeam("NSH");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Chicago Blackhawks"))
            {
                updateTeam("CHI");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Columbus Blue Jackets"))
            {
                updateTeam("CLS");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("St. Louis Blues"))
            {
                updateTeam("STL");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("San Jose Sharks"))
            {
                updateTeam("SJ");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Phoenix Coyotes"))
            {
                updateTeam("PHO");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Los Angeles Kings"))
            {
                updateTeam("LA");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Anaheim Ducks"))
            {
                updateTeam("ANH");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Dallas Stars"))
            {
                updateTeam("DAL");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Vancouver Canucks"))
            {
                updateTeam("VAN");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Calgary Flames"))
            {
                updateTeam("CGY");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Minnesota Wild"))
            {
                updateTeam("MIN");
            }
            else if (parent.getItemAtPosition(pos).toString().equals("Colorado Avalanche"))
            {
                updateTeam("COL");
            }
            else 
            {
                // do nothing
            }
            
           

            section_date = (TextView) findViewById(R.id.section_date);

            section_date.setText("");
        }

        public void onNothingSelected(AdapterView parent)
        {
            // Do nothing.
        }
    }

    private void startReadScheduleScoreboard()
    {
        new DataLoadingTask().execute();
    }

    private void preReadScheduleScoreboard()
    {
        /*
         * feedTitle.setText("--- wait ---"); feedDescribtion.setText("");
         * feedPubdate.setText(""); feedLink.setText("");
         */
        setListAdapter(null);

        /*
         * Toast.makeText(this, "Reading RSS, Please wait.",
         * Toast.LENGTH_LONG).show();
         */

    }

    private void read()
    {

        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        try
        {

            String liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json";

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                    || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
            {

                liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_scoreboard.json";

                HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

                theGameList = hnicschedulescoreboardreader
                        .readScheduleScoreboard(liveScheduleScoreboardUrl);
                
             
                
                

            } else
            {

                liveScheduleScoreboardUrl = "schedule_scoreboard.json";

                HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

                theGameList = hnicschedulescoreboardreader
                        .readScheduleScoreboardFromCache(liveScheduleScoreboardUrl);

                Log.d(TAG,
                        "---------------------------------------------read schedule_scoreboard.json from cache");
            }
        }

        finally
        {
            if (theGameList != null)
            {
                for (final HNICGame game : theGameList)
                {
                    /* start of db lookup to get and set the full teamname */

                    HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(game
                            .getAway());

                    game.setAwayfull(thisTeamAway.getFullname());

                    HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(game
                            .getHome());

                    game.setHomefull(thisTeamHome.getFullname());

                    /* end of db lookup to get and set the full teamname */
                }
            }

        }

    }

    private void display()
    {
        if (theGameList != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

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

            MyCustomAdapter adapter = new MyCustomAdapter(this,
                    R.layout.row_schedule, theGameList);
            setListAdapter(adapter);

        }
        /*
         * if (theList != null) { MyCustomAdapterResults adapterResults = new
         * MyCustomAdapterResults( this, R.layout.row_schedule_results,
         * theList); setListAdapter(adapterResults); }
         */

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        /* commenting line below out temporarily to run newdetails in webview */
        /* Intent intent = new Intent(this, RSSShowDetails.class); */

        /*
         * Intent intent = new Intent(this, Team.class);
         * 
         * Bundle bundle = new Bundle(); bundle.putString("abbr",
         * theGameList.get(position).getHome());
         * 
         * intent.putExtras(bundle); startActivity(intent);
         */
        Intent intent = new Intent(this, Team.class);

        Bundle bundle = new Bundle();

        bundle.putString("abbr", theGameList.get(position).getHome());
        /*
         * bundle.putString("fullname",
         * theGameList.get(position).getFullname());
         * bundle.putString("rank-division",
         * theGameList.get(position).getRank_division());
         * bundle.putString("rank-conference",
         * theGameList.get(position).getRank_conference());
         * bundle.putString("played", theGameList.get(position).getPlayed());
         * bundle.putString("won", theGameList.get(position).getWon());
         * bundle.putString("lost", theGameList.get(position).getLost());
         */
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        /*
         * menu.add(0, 0, 0, "Reload"); return true;
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.schedule_contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
        /*
         * case (R.id.teamselector): Spinner spinner2 = (Spinner)
         * findViewById(R.id.spinner); ArrayAdapter<CharSequence> adapter =
         * ArrayAdapter .createFromResource(this, R.array.teams_array,
         * android.R.layout.simple_spinner_item); adapter
         * .setDropDownViewResource
         * (android.R.layout.simple_spinner_dropdown_item);
         * spinner2.setAdapter(adapter); spinner2.setOnItemSelectedListener(new
         * MyOnItemSelectedListener());
         * 
         * 
         * break;
         */
        case (R.id.dateselector):
            // get the current date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            showDialog(DATE_DIALOG_ID);
            break;
        default:
            break;
        }

        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                    mDay);
        }
        return null;
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
     * A call-back for when the user presses the back button.
     */
    OnClickListener mBackListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            finish();
        }
    };

    private void updateTeam(String abbr)
    {
        /*
         * section_date.setText(new StringBuilder() // Month is 0 based so add 1
         * .append(mMonth + 1).append("-").append(mDay).append("-")
         * .append(mYear).append(" "));
         */
        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        try
        {

            String liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_"
                    + abbr.toUpperCase() + ".json";

            HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

            theGameList = hnicschedulescoreboardreader
                    .readScheduleScoreboardForTeam(liveScheduleScoreboardUrl);

            if (theGameList != null)
            {

                Log.d(TAG, "---------the new gameslist was found ---");
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

                MyCustomAdapterTeamModified adapter = new MyCustomAdapterTeamModified(
                        this, R.layout.row_schedule, theGameList);
                setListAdapter(adapter);

            }

        } catch (Exception ex)
        {
            Log.e(TAG,
                    "-------dude there was an exception which was not handled very well----"
                            + ex.toString());
        }

        finally
        {
            if (theGameList != null)
            {
                for (final HNICGame game : theGameList)
                {
                    /* start of db lookup to get and set the full teamname */

                    HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(game
                            .getAway());

                    game.setAwayfull(thisTeamAway.getFullname());

                    HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(game
                            .getHome());

                    game.setHomefull(thisTeamHome.getFullname());

                    /* end of db lookup to get and set the full teamname */
                }
            }
        }

    }

    private void updateDate()
    {
        /*
         * section_date.setText(new StringBuilder() // Month is 0 based so add 1
         * .append(mMonth + 1).append("-").append(mDay).append("-")
         * .append(mYear).append(" "));
         */

        DatabaseDataLayer databaseDataLayer = new DatabaseDataLayer(
                getBaseContext());

        section_date = (TextView) findViewById(R.id.section_date);
        try
        {
            section_date.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-").append(mDay).append("-")
                    .append(mYear).append(" "));

            String liveScheduleScoreboardUrl = "http://www.cbc.ca/data/statsfeed/plist/schedules/schedule_";

            HNICScheduleScoreboardReader hnicschedulescoreboardreader = new HNICScheduleScoreboardReader();

            theGameList = hnicschedulescoreboardreader
                    .readScheduleScoreboardForDate(liveScheduleScoreboardUrl,
                            mMonth + 1, mDay, mYear);

            if (theGameList != null)
            {

                Log.d(TAG, "---------the new gameslist was found ---");
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

                MyCustomAdapterDateModified adapter = new MyCustomAdapterDateModified(
                        this, R.layout.row_schedule, theGameList);
                setListAdapter(adapter);

            }

        } catch (Exception ex)
        {
            Log.e(TAG,
                    "-------dude there was an exception which was not handled very well----"
                            + ex.toString());
        }

        finally
        {
            if (theGameList != null)
            {
                for (final HNICGame game : theGameList)
                {
                    /* start of db lookup to get and set the full teamname */

                    HNICTeam thisTeamAway = databaseDataLayer.SelectTeam(game
                            .getAway());

                    game.setAwayfull(thisTeamAway.getFullname());

                    HNICTeam thisTeamHome = databaseDataLayer.SelectTeam(game
                            .getHome());

                    game.setHomefull(thisTeamHome.getFullname());

                    /* end of db lookup to get and set the full teamname */
                }
            }
        }

    }



    class TeamData
    {
        String spinnerText;

        String value;

        public TeamData(String spinnerText, String value)
        {
            this.spinnerText = spinnerText;
            this.value = value;
        }

        public String getSpinnerText()
        {
            return spinnerText;
        }

        public String getValue()
        {
            return value;
        }

        public String toString()
        {
            return spinnerText;
        }
    }
}