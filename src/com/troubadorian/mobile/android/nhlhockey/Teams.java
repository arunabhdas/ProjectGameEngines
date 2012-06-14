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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.troubadorian.mobile.android.model.HNICTeam;
import com.troubadorian.mobile.android.nhlhockey.Leaders.DataLoadingTask;
import com.troubadorian.mobile.android.nhlhockey.Leaders.MyGoalieWinsCustomAdapter;
import com.troubadorian.mobile.android.nhlhockey.Leaders.MyGoalsAgainstAverageCustomAdapter;
import com.troubadorian.mobile.android.nhlhockey.Leaders.MyGoalsCustomAdapter;
import com.troubadorian.mobile.android.nhlhockey.Leaders.MyGoalsForAgainstCustomAdapter;
import com.troubadorian.mobile.android.nhlhockey.Leaders.MyPointsCustomAdapter;
import com.troubadorian.mobile.android.nhlhockey.Leaders.MySavePercentageCustomAdapter;


import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Teams extends ListActivity 
{
    static final int DATE_DIALOG_ID = 1;

    static final int CONFERENCE = 2;

    static final int DIVISION = 3;

    static final int EXIT = 4;

    private int mYear;

    private int mMonth;

    private int mDay;

    public static final String TAG = "Teams";
    
    private static final String C2 = "Teams";

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

    private TextView section_subhead;

    ProgressDialog myProgress;

    List<HNICTeam> theListAll;

    List<HNICTeam> theListNortheast;

    List<HNICTeam> theListAtlantic;

    List<HNICTeam> theListSoutheast;

    List<HNICTeam> theListCentral;

    List<HNICTeam> theListPacific;

    List<HNICTeam> theListNorthwest;
    
    List<HNICTeam> theListActive;

    public class DataLoadingTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub

            /* myProgress.hide(); */

            myProgress.dismiss();

            /* myProgress = null; */

            display(11);

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub

            myProgress = new ProgressDialog(Teams.this);

            myProgress.setMessage("Please wait..");

            myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            myProgress.setCancelable(true);

            myProgress.show();

            /*
             * myProgress = ProgressDialog.show(Schedule.this, "Please wait...",
             * "...loading...", true, false);
             */

            preRead();

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
            try
            {
                read();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onCancelled()
        {
            myProgress.cancel();
            this.cancel(true);
        }

    }

    public class MyCustomAdapterAll extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterAll(Context context, int textViewResourceId,
                List<HNICTeam> theListAll)
        {
            super(context, textViewResourceId, theListAll);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);
            

            listofstuff.setText(theListAll.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListAll.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListAll.get(position).getRank_division());
            
            ImageView teamlogo = (ImageView) row.findViewById(R.id.teamlogo);

            String myDrawable = theListAll.get(position).getAbbr().toLowerCase();

            int resID = getBaseContext().getResources().getIdentifier(myDrawable,
                    "drawable", "com.troubadorian.mobile.android.nhlhockey");
            
            teamlogo.setImageResource(resID);

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

    public class MyCustomAdapterNortheast extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterNortheast(Context context,
                int textViewResourceId, List<HNICTeam> theListNortheast)
        {
            super(context, textViewResourceId, theListNortheast);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);

            /*
             * listofstuff.setText(theListNortheast.get(position).getFullname()
             * + " - " + theListNortheast.get(position).getRank_division() +
             * " - " + theListNortheast.get(position).getRank_conference() );
             */

            listofstuff.setText(theListNortheast.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListNortheast.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListNortheast.get(position).getRank_division());

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

    public class MyCustomAdapterAtlantic extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterAtlantic(Context context, int textViewResourceId,
                List<HNICTeam> theListAtlantic)
        {
            super(context, textViewResourceId, theListAtlantic);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);

            /*
             * listofstuff.setText(theListNortheast.get(position).getFullname()
             * + " - " + theListNortheast.get(position).getRank_division() +
             * " - " + theListNortheast.get(position).getRank_conference() );
             */

            listofstuff.setText(theListAtlantic.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListAtlantic.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListAtlantic.get(position).getRank_division());

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

    public class MyCustomAdapterSoutheast extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterSoutheast(Context context,
                int textViewResourceId, List<HNICTeam> theListSoutheast)
        {
            super(context, textViewResourceId, theListSoutheast);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);

            listofstuff.setText(theListSoutheast.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListSoutheast.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListSoutheast.get(position).getRank_division());

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

    public class MyCustomAdapterCentral extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterCentral(Context context, int textViewResourceId,
                List<HNICTeam> theListCentral)
        {
            super(context, textViewResourceId, theListCentral);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);

            listofstuff.setText(theListCentral.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListCentral.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListCentral.get(position).getRank_division());

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

    public class MyCustomAdapterPacific extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterPacific(Context context, int textViewResourceId,
                List<HNICTeam> theListPacific)
        {
            super(context, textViewResourceId, theListPacific);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);

            listofstuff.setText(theListPacific.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListPacific.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListPacific.get(position).getRank_division());

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

    public class MyCustomAdapterNorthwest extends ArrayAdapter<HNICTeam>
    {
        public MyCustomAdapterNorthwest(Context context,
                int textViewResourceId, List<HNICTeam> theListNorthwest)
        {
            super(context, textViewResourceId, theListNorthwest);
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
                row = inflater.inflate(R.layout.row_teams, parent, false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);

            TextView listrankconference = (TextView) row
                    .findViewById(R.id.listrankconference);

            TextView listrankdivision = (TextView) row
                    .findViewById(R.id.listrankdivision);

            listofstuff.setText(theListNorthwest.get(position).getFullname());

            listrankconference.setText("Conference Rank : "
                    + theListNorthwest.get(position).getRank_conference());

            listrankdivision.setText("Division Rank : "
                    + theListNorthwest.get(position).getRank_division());

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

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.teams_main);

        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

        /* end of listview block */

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

       /* AppMeasurement s;

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

        startRead();
    }

    private void startRead()
    {
        new DataLoadingTask().execute();
    }

    private void preRead()
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

    private void read() throws IOException
    {

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
                /* start of homemade parsing */

                String json = null;

                try
                {

                    URL url = new URL(
                            "http://www.cbc.ca/data/statsfeed/plist/conferencelist.json");
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

                    /* 1--------------start of Northeast ------------- */

                    JSONObject jsonEastern1 = jsonObj.getJSONObject("Eastern");

                    JSONArray jsonNortheast = jsonEastern1
                            .getJSONArray("Northeast");
                    for (int i = 0; i < jsonNortheast.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonNortheast
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
                            northeastTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                        Log.d(TAG,
                                "Team fullname:"
                                        + jsonOneObject.getString("fullname")
                                        + " teamname: "
                                        + jsonOneObject.getString("teamname"));
                    }

                    theListNortheast = northeastTeams;

                    /* --------------end of Northeast ------------- */
                    /* 2--------------start of Atlantic ------------- */

                    JSONObject jsonEastern2 = jsonObj.getJSONObject("Eastern");

                    JSONArray jsonAtlantic = jsonEastern2
                            .getJSONArray("Atlantic");
                    for (int i = 0; i < jsonAtlantic.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonAtlantic
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
                            atlanticTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                        Log.e(TAG,
                                "Team fullname:"
                                        + jsonOneObject.getString("fullname")
                                        + " teamname: "
                                        + jsonOneObject.getString("teamname"));
                    }

                    theListAtlantic = atlanticTeams;

                    /* --------------end of Atlantic ------------- */

                    /* 3--------------start of Southeast ------------- */

                    JSONObject jsonEastern3 = jsonObj.getJSONObject("Eastern");

                    JSONArray jsonSoutheast = jsonEastern3
                            .getJSONArray("Southeast");
                    for (int i = 0; i < jsonSoutheast.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonSoutheast
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
                            southeastTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                        Log.e(TAG,
                                "Team fullname:"
                                        + jsonOneObject.getString("fullname")
                                        + " teamname: "
                                        + jsonOneObject.getString("teamname"));
                    }

                    theListSoutheast = southeastTeams;

                    /* --------------end of Southeast ------------- */

                    /* 4--------------start of Central ------------- */

                    JSONObject jsonWestern1 = jsonObj.getJSONObject("Western");

                    JSONArray jsoncentral = jsonWestern1
                            .getJSONArray("Central");
                    for (int i = 0; i < jsoncentral.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsoncentral.getJSONObject(i);

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
                            centralTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                        Log.e(TAG,
                                "Team fullname:"
                                        + jsonOneObject.getString("fullname")
                                        + " teamname: "
                                        + jsonOneObject.getString("teamname"));
                    }

                    theListCentral = centralTeams;

                    /* --------------end of Central ------------- */
                    /* 5--------------start of Pacific ------------- */

                    JSONObject jsonWestern2 = jsonObj.getJSONObject("Western");

                    JSONArray jsonPacific = jsonWestern2
                            .getJSONArray("Pacific");
                    for (int i = 0; i < jsonPacific.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonPacific.getJSONObject(i);

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
                            pacificTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                        Log.e(TAG,
                                "Team fullname:"
                                        + jsonOneObject.getString("fullname")
                                        + " teamname: "
                                        + jsonOneObject.getString("teamname"));
                    }

                    theListPacific = pacificTeams;

                    /* --------------end of Pacific ------------- */

                    /* 5--------------start of Northwest ------------- */

                    JSONObject jsonWestern3 = jsonObj.getJSONObject("Western");

                    JSONArray jsonNorthwest = jsonWestern3
                            .getJSONArray("Northwest");
                    for (int i = 0; i < jsonNorthwest.length(); i++)
                    {
                        HNICTeam oneTeam = new HNICTeam();

                        JSONObject jsonOneObject = jsonNorthwest
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
                            northwestTeams.add(i, oneTeam);
                        } catch (Exception e)
                        {
                            Log.d(TAG, "--------------------" + e.toString());
                        }

                        Log.e(TAG,
                                "Team fullname:"
                                        + jsonOneObject.getString("fullname")
                                        + " teamname: "
                                        + jsonOneObject.getString("teamname"));
                    }

                    theListNorthwest = northwestTeams;

                    /* --------------end of Pacific ------------- */

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

                /* end of homemade parsing */
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

    }

    public void display(int thenumber)
    {

        switch (thenumber)
        {

        case (11):
            if (theListAll != null)
            {

                theListActive = theListAll;
                
                MyCustomAdapterAll adapter = new MyCustomAdapterAll(this,
                        R.layout.row_teams, theListActive);
                setListAdapter(adapter);
                

            }
            break;

        case (0):
            if (theListNortheast != null)
            {
                theListActive = theListNortheast;
                
                MyCustomAdapterNortheast adapter = new MyCustomAdapterNortheast(
                        this, R.layout.row_teams, theListNortheast);
                setListAdapter(adapter);

            }
            break;

        case (1):
            if (theListAtlantic != null)
            {
                theListActive = theListAtlantic;
                
                MyCustomAdapterAtlantic adapter = new MyCustomAdapterAtlantic(
                        this, R.layout.row_teams, theListAtlantic);
                setListAdapter(adapter);

            }
            break;

        case (2):
            if (theListSoutheast != null)
            {
                theListActive = theListSoutheast;
                
                MyCustomAdapterSoutheast adapter = new MyCustomAdapterSoutheast(
                        this, R.layout.row_teams, theListSoutheast);
                setListAdapter(adapter);

            }
            break;
        case (3):
            if (theListCentral != null)
            {
                theListActive = theListCentral;
                
                MyCustomAdapterCentral adapter = new MyCustomAdapterCentral(
                        this, R.layout.row_teams, theListCentral);
                setListAdapter(adapter);

            }
            break;
        case (4):
            if (theListPacific != null)
            {
                theListActive = theListPacific;
                
                MyCustomAdapterPacific adapter = new MyCustomAdapterPacific(
                        this, R.layout.row_teams, theListPacific);
                setListAdapter(adapter);

            }
            break;
        case (5):
            if (theListNorthwest != null)
            {
                theListActive = theListNorthwest;
                
                MyCustomAdapterNorthwest adapter = new MyCustomAdapterNorthwest(
                        this, R.layout.row_teams, theListNorthwest);
                setListAdapter(adapter);

            }
            break;

        default:
            if (theListAll != null)
            {

                MyCustomAdapterAll adapter = new MyCustomAdapterAll(this,
                        R.layout.row_teams, theListAll);
                setListAdapter(adapter);

            }
            break;
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        /* commenting line below out temporarily to run newdetails in webview */
        /* Intent intent = new Intent(this, RSSShowDetails.class); */

        Intent intent = new Intent(this, Team.class);

        Bundle bundle = new Bundle();
        bundle.putString("abbr", theListActive.get(position).getAbbr());
        bundle.putString("fullname", theListActive.get(position).getFullname());
        bundle.putString("rank-division", theListActive.get(position)
                .getRank_division());
        bundle.putString("rank-conference", theListActive.get(position)
                .getRank_conference());
        bundle.putString("played", theListActive.get(position).getPlayed());
        bundle.putString("won", theListActive.get(position).getWon());
        bundle.putString("lost", theListActive.get(position).getLost());

        /*
         * bundle.putString("keyDescription", gameList.get(position)
         * .getStart_date_time()); bundle.putString("keyLink",
         * gameList.getItem(position).getLink()); bundle
         * .putString("keyPubdate", myRssFeed.getItem(position) .getPubdate());
         */
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /*
     * if (theList != null) { MyCustomAdapterResults adapterResults = new
     * MyCustomAdapterResults( this, R.layout.row_schedule_results, theList);
     * setListAdapter(adapterResults); }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        /*
         * menu.add(0, 0, 0, "Reload"); return true;
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teams_contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {

        case R.id.allteams:
            Log.d(TAG, "-----------------All teams selected");
            display(11);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("All Teams");

            break;

        case R.id.northeast:
            Log.d(TAG, "-----------------Northeast selected");
            display(0);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Northeast");

            break;

        case R.id.atlantic:
            Log.d(TAG, "------------------Atlantic selected");
            display(1);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Atlantic");

            break;

        case R.id.southeast:
            Log.d(TAG, "-------------------Southeast selected");
            display(2);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Southeast");

            break;

        case R.id.central:
            Log.d(TAG, "----------------------Central selected");
            display(3);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Central");

            break;

        case R.id.pacific:
            Log.d(TAG, "----------------------Pacific selected");
            display(4);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Pacific");

            break;

        case R.id.northwest:
            Log.d(TAG, "----------------------Northwest selected");
            display(5);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Northwest");

            break;

        default:
            display(11);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("All Teams");
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

 
}
