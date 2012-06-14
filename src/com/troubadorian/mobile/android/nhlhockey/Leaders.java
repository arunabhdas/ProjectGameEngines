/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.troubadorian.mobile.android.model.HNICOverallLeaders;
import com.troubadorian.mobile.android.model.HNICOverallLeadersReader;
import com.troubadorian.mobile.android.nhlhockey.Schedule.DataLoadingTask;
import com.troubadorian.mobile.android.nhlhockey.Schedule.MyCustomAdapter;



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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Leaders extends ListActivity 
{

    public static final String TAG = "Leaders";
    
    private static final String C2 = "Leaders";

    private static final String C3 = "Goals";

    HNICOverallLeaders theList;

    List<String> goals;

    List<String> points;

    List<String> goalsforagainst;

    List<String> goaliewins;

    List<String> goalsagainstaverage;

    List<String> savepercentage;

    ProgressDialog myProgress;

    static final int DATE_DIALOG_ID = 1;

    private int mYear;

    private int mMonth;

    private int mDay;

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

    static final String[] MENUOPTIONS = new String[]
    { "Standings", "Leaders", "Players", "Teams"

    };

    public class DataLoadingTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub

            /* myProgress.hide(); */

            myProgress.dismiss();

            /* myProgress = null; */

            display(0);

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub

            myProgress = new ProgressDialog(Leaders.this);

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

    TextView feedTitle;

    TextView feedDescribtion;

    TextView feedPubdate;

    TextView feedLink;

    String start_date;

    String start_time;

    public class MyGoalsCustomAdapter extends ArrayAdapter<String>
    {
        public MyGoalsCustomAdapter(Context context, int textViewResourceId,
                List<String> theList)
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
                row = inflater.inflate(R.layout.row_overallleaders, parent,
                        false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);
            listofstuff.setText(theList.getGoals().get(position).toString());

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

    public class MyPointsCustomAdapter extends ArrayAdapter<String>
    {
        public MyPointsCustomAdapter(Context context, int textViewResourceId,
                List<String> theList)
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
                row = inflater.inflate(R.layout.row_overallleaders, parent,
                        false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);
            listofstuff.setText(theList.getPoints().get(position).toString());

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

    public class MyGoalsForAgainstCustomAdapter extends ArrayAdapter<String>
    {
        public MyGoalsForAgainstCustomAdapter(Context context,
                int textViewResourceId, List<String> theList)
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
                row = inflater.inflate(R.layout.row_overallleaders, parent,
                        false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);
            listofstuff.setText(theList.getGoalsforagainst().get(position)
                    .toString());

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

    public class MyGoalieWinsCustomAdapter extends ArrayAdapter<String>
    {
        public MyGoalieWinsCustomAdapter(Context context,
                int textViewResourceId, List<String> theList)
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
                row = inflater.inflate(R.layout.row_overallleaders, parent,
                        false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);
            listofstuff.setText(theList.getGoaliewins().get(position).toString());

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

    public class MyGoalsAgainstAverageCustomAdapter extends
            ArrayAdapter<String>
    {
        public MyGoalsAgainstAverageCustomAdapter(Context context,
                int textViewResourceId, List<String> theList)
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
                row = inflater.inflate(R.layout.row_overallleaders, parent,
                        false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);
            listofstuff.setText(theList.getGoalsagainstaverage().get(position)
                    .toString());

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

    public class MySavePercentageCustomAdapter extends ArrayAdapter<String>
    {
        public MySavePercentageCustomAdapter(Context context,
                int textViewResourceId, List<String> theList)
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
                row = inflater.inflate(R.layout.row_overallleaders, parent,
                        false);
            }

            TextView listofstuff = (TextView) row
                    .findViewById(R.id.listofstuff);
            listofstuff
                    .setText(theList.getSavepercentage().get(position).toString());

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

        setContentView(R.layout.leaders_main);

        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

        /* end of listview block */

       

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

            String theUrl = "http://www.cbc.ca/data/statsfeed/plist/overallleaders.json";

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                    || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
            {

                theUrl = "http://www.cbc.ca/data/statsfeed/plist/overallleaders.json";
                HNICOverallLeadersReader hnicoverallleadersreader = new HNICOverallLeadersReader();

                theList = hnicoverallleadersreader.readOverallLeaders(theUrl);

                goals = theList.getGoals();

                points = theList.getPoints();

                goalsforagainst = theList.getGoalsforagainst();

                goaliewins = theList.getGoaliewins();

                goalsagainstaverage = theList.getGoalsagainstaverage();

                savepercentage = theList.getSavepercentage();

            } else
            {

                theUrl = "overallleaders.json";
                HNICOverallLeadersReader hnicoverallleadersreader = new HNICOverallLeadersReader();

                try
                {
                    theList = hnicoverallleadersreader
                            .readOverallLeadersFromCache(theUrl);
                }

                catch (Exception ex)
                {
                    Log.e(TAG,
                            "-------------------the trouble with paradise is that there is trouble in paradise"
                                    + ex.toString());

                }

                Log.d(TAG,
                        "---------------------------------------------read overallleaders.json from cache");
            }

        } catch (Exception e)
        {
            Log.d(TAG,
                    "------Whoa!! There was an exception--------------"
                            + e.toString());
        }

        finally
        {

        }

    }

    public void display(int itemtype)
    {
        if (theList != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            Log.d(TAG, "----------" + theList.getGoaliewins());

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
            MyGoalsCustomAdapter adapter0;
            MyPointsCustomAdapter adapter1;
            MyGoalsForAgainstCustomAdapter adapter2;
            MyGoalieWinsCustomAdapter adapter3;
            MyGoalsAgainstAverageCustomAdapter adapter4;
            MySavePercentageCustomAdapter adapter5;
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_SHORT;
            Toast toast;
            
          
            switch (itemtype)
            {
            case (0):
                adapter0 = new MyGoalsCustomAdapter(this,
                        R.layout.row_overallleaders, theList.getGoals());
                setListAdapter(adapter0);
                
                


                break;

            case (1):
                adapter1 = new MyPointsCustomAdapter(this,
                        R.layout.row_overallleaders, theList.getPoints());
                setListAdapter(adapter1);
                
               


                break;

            case (2):
                adapter2 = new MyGoalsForAgainstCustomAdapter(this,
                        R.layout.row_overallleaders, theList.getGoalsforagainst());
                setListAdapter(adapter2);
                
      

                break;

            case (3):
                adapter3 = new MyGoalieWinsCustomAdapter(this,
                        R.layout.row_overallleaders, theList.getGoaliewins());
                setListAdapter(adapter3);
                
              
                

                break;

            case (4):
                adapter4 = new MyGoalsAgainstAverageCustomAdapter(this,
                        R.layout.row_overallleaders,
                        theList.getGoalsagainstaverage());
                setListAdapter(adapter4);
                
             

                break;

            case (5):

                adapter5 = new MySavePercentageCustomAdapter(this,
                        R.layout.row_overallleaders, theList.getSavepercentage());
                setListAdapter(adapter5);
                
               

                break;

            default:

                adapter0 = new MyGoalsCustomAdapter(this,
                        R.layout.row_overallleaders, theList.getGoals());
                
              


                break;

            }

        }
        /*
         * if (theList != null) { MyCustomAdapterResults adapterResults = new
         * MyCustomAdapterResults( this, R.layout.row_schedule_results,
         * theList); setListAdapter(adapterResults); }
         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        /*
         * menu.add(0, 0, 0, "Reload"); return true;
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.leaders_contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
        case R.id.goals:
            display(0);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Goals");
            break;

        case R.id.points:
            display(1);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Points");
            break;

        case R.id.goalsforagainst:
            display(2);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Goals For Against");
            break;

        case R.id.goaliewins:
            display(3);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Goalie Wins");
            break;

        case R.id.goalsagainstaverage:
            display(4);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Goals Against Average");
            break;

        case R.id.savepercentage:
            display(5);
            section_subhead = (TextView) findViewById(R.id.section_subhead);
            section_subhead.setText("Save Percentage");
            break;

        default:
            display(0);
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
