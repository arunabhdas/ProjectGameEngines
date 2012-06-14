/*
 * Author : Arunabh Das 
 */


package com.troubadorian.mobile.android.nhlhockey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;

import com.troubadorian.mobile.android.model.HNICPlayer;
import com.troubadorian.mobile.android.model.HNICPlayersReader;
import com.troubadorian.mobile.android.nhlhockey.Schedule.DataLoadingTask;
import com.troubadorian.mobile.android.nhlhockey.Schedule.MyCustomAdapter;

public class Players extends ListActivity
{

    public static final String TAG = "Players";
    
    private static final String C2 = "Players";

    private static final String C3 = "Index";

    List<HNICPlayer> theList = null;
    
    List<HNICPlayer> theSortedList = null;

    ProgressDialog myProgress;

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

     private String _siteId = "9063"; 

    /*private String _siteId = "4606";*/

    /* private String _partnerId = "1dd21b33bd603c95"; */

    private String _partnerId = "387131ac6a1aa80d";

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

            myProgress = new ProgressDialog(Players.this);

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

    public class MyCustomAdapter extends ArrayAdapter<HNICPlayer>
    {

        public MyCustomAdapter(Context context, int textViewResourceId,
                List<HNICPlayer> theSortedList)
        {
            super(context, textViewResourceId, theSortedList);
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
                row = inflater.inflate(R.layout.row_players, parent,
                        false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);
            listTitle.setText(theSortedList.get(position).getName()  );

            TextView listPubtime = (TextView) row
                    .findViewById(R.id.listpubtime);

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);


      

            listPubdate.setText(theSortedList.get(position).getName());
            
            listTitle.setText(theSortedList.get(position).getTeam().toUpperCase());

            listPubtime.setText(theSortedList.get(position).getPosition());

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

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_main);

        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

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

       /* AppMeasurement s;

        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 
        s.account = "cbc-mobile-dev-2";
        
        s.account = "cbc-mobile-prod-9";

         You may add or alter any code config here 

        s.channel = "android-app";

        s.prop1 = "android-app:hockey-app";
        
        s.prop2 = "android-app:hockey-app:" + C2;
        
        s.prop3 = "android-app:hockey-app:" + C2 + ":" + C3;
        
        s.prop4 = "page";

        s.pageName = "android-app:hockey-app:" + C2 + ":" + C3;

        s.currencyCode = "CAD";

         Turn on and configure debugging here 
        s.debugTracking = true;

        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();
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

        
            HNICPlayersReader hnicplayersreader = new HNICPlayersReader();
            
            /*theList = hnicplayersreader.readPlayers(theUrl);*/
            
            theList = hnicplayersreader.readPlayersFromCache("allplayers2.json");
            

            
            /*theSortedList = this.sortBy(theList, "Name");*/
            
            theSortedList = theList;
            
          /*  Log.d(TAG, "reading box scores and writing to db");
            int i = 0;
            for (final HNICPlayer player : theList)
            {
                

                i++;

            }*/
        }
        catch (Exception ex)
        {
            Log.e(TAG,
                    "-------------------the trouble with paradise is that there is trouble in paradise"
                            + ex.toString());

        }
        
        finally
        {

        }
    }
    
    public List<HNICPlayer> sortBy(List<HNICPlayer> hnicplayerlist, String s) {
        for (int i = 1; i < hnicplayerlist.size(); i++) {
            int j;
            HNICPlayer val = hnicplayerlist.get(i);
            for (j = i-1; j > -1; j--) {
                HNICPlayer temp = hnicplayerlist.get(j);
                if(s.equals("Team")) {
                    if (temp.compareByTeam(val) <= 0) {
                        break;
                    }
                }
                else if(s.equals("Name")) {
                    if (temp.compareByName(val) <= 0) {
                           break;
                    }
                }
                hnicplayerlist.set(j+1, temp);
             }
            hnicplayerlist.set(j+1, val);
        }
        return hnicplayerlist;
      }


    private void display()
    {
        if (theSortedList != null)
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

            MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.row_players,
                    theSortedList);
            setListAdapter(adapter);

        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        /* commenting line below out temporarily to run newdetails in webview */
        /* Intent intent = new Intent(this, RSSShowDetails.class); */

        Intent intent = new Intent(this, Player.class);

        Bundle bundle = new Bundle();
        
        bundle.putString("player", theSortedList.get(position).getName());
        
        bundle.putString("team", theSortedList.get(position).getTeam().toLowerCase());
        
        bundle.putString("position", theSortedList.get(position).getPosition());

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
        inflater.inflate(R.menu.players_contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
        case (0):
            startRead();
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