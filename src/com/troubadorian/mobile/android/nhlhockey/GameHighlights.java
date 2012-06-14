/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;





import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class GameHighlights extends ListActivity 
{
    ProgressDialog myProgress;

    public static final String TAG = "Game Highlights";

    private static final String C2 = "Game Highlights";

    private static final String C3 = "Index";

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
            displayRss();

        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            myProgress = ProgressDialog.show(GameHighlights.this,
                    "...retrieving...", "...please wait...", true, false);
            preReadRss();

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
            readRss();
            return null;
        }

    }

    private RSSFeedVideo myRssFeedVideo = null;

    TextView feedTitle;

    TextView feedDescribtion;

    TextView feedPubdate;

    TextView feedLink;

    public class MyCustomAdapter extends ArrayAdapter<RSSItemVideo>
    {

        public MyCustomAdapter(Context context, int textViewResourceId,
                List<RSSItemVideo> list)
        {
            super(context, textViewResourceId, list);
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
                row = inflater.inflate(R.layout.row_video, parent, false);
            }

            TextView listTitle = (TextView) row.findViewById(R.id.listtitle);

            TextView listDetail = (TextView) row.findViewById(R.id.listdetail);

            listTitle
                    .setText(myRssFeedVideo.getList().get(position).getTitle());

            listDetail.setText(myRssFeedVideo.getList().get(position)
                    .getDescription());

            TextView listPubdate = (TextView) row
                    .findViewById(R.id.listpubdate);
            listPubdate.setText(myRssFeedVideo.getList().get(position)
                    .getPubdate());

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
        setContentView(R.layout.video_inside_main);

        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

        /*
         * feedTitle = (TextView)findViewById(R.id.feedtitle); feedDescribtion =
         * (TextView)findViewById(R.id.feeddescribtion); feedPubdate =
         * (TextView)findViewById(R.id.feedpubdate); feedLink =
         * (TextView)findViewById(R.id.feedlink);
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

        startReadRss();
    }

    private void startReadRss()
    {
        new RssLoadingTask().execute();
    }

    private void preReadRss()
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

    private void readRss()
    {

        try
        {

            String json = null;

            ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // mobile

            State mobile = conMan.getNetworkInfo(0).getState();

            // wifi

            State wifi = conMan.getNetworkInfo(1).getState();

            String completedBoxScoresUrl;

            if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                    || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
            {

                URL rssUrl = new URL(
                        "http://cds1-feed.yospace.com/001111582643");

                SAXParserFactory mySAXParserFactory = SAXParserFactory
                        .newInstance();
                SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
                XMLReader myXMLReader = mySAXParser.getXMLReader();
                RSSHandlerVideo myRSSHandlerVideo = new RSSHandlerVideo();
                myXMLReader.setContentHandler(myRSSHandlerVideo);
                InputSource myInputSource = new InputSource(rssUrl.openStream());
                myXMLReader.parse(myInputSource);

                myRssFeedVideo = myRSSHandlerVideo.getFeed();

                /* start of writing file to sdcard */

                URLConnection connection = rssUrl.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    buffer.append(inputLine);
                json = buffer.toString();

                // Path to write files to
                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/Android/data/hnic/" + "/files/";
                String fname = "gamehighlights.xml";

                // Current state of the external media
                String extState = Environment.getExternalStorageState();

                // External media can be written onto
                if (extState.equals(Environment.MEDIA_MOUNTED))
                {
                    try
                    {
                        // Make sure the path exists
                        boolean exists = (new File(path)).exists();
                        if (!exists)
                        {
                            new File(path).mkdirs();
                        }

                        // Open output stream
                        FileOutputStream fOut = new FileOutputStream(path
                                + fname);

                        fOut.write(json.getBytes());

                        // Close output stream
                        fOut.flush();
                        fOut.close();

                    } catch (IOException ioe)
                    {
                        ioe.printStackTrace();

                        Log.e(TAG, "-------------------" + ioe.toString());
                    }
                }

                /* end of writing file to sdcard */
            } else
            {
                File file = new File(
                        "/sdcard/Android/data/hnic/files/gamehighlights.xml");

                String fileString = this.grabAsSingleString(file);

                SAXParserFactory mySAXParserFactory = SAXParserFactory
                        .newInstance();

                SAXParser mySAXParser = mySAXParserFactory.newSAXParser();

                XMLReader myXMLReader = mySAXParser.getXMLReader();

                RSSHandlerVideo myRSSHandlerVideo = new RSSHandlerVideo();

                myXMLReader.setContentHandler(myRSSHandlerVideo);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                        fileString.getBytes());

                InputSource myInputSource = new InputSource(
                        byteArrayInputStream);

                myXMLReader.parse(myInputSource);

                myRssFeedVideo = myRSSHandlerVideo.getFeed();
            }

        } catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void displayRss()
    {
        if (myRssFeedVideo != null)
        {
            Calendar c = Calendar.getInstance();
            String strCurrentTiime = "\n(Time of Reading - "
                    + c.get(Calendar.HOUR_OF_DAY) + " : "
                    + c.get(Calendar.MINUTE) + ")\n";

            MyCustomAdapter adapter = new MyCustomAdapter(this,
                    R.layout.row_video, myRssFeedVideo.getList());

            setListAdapter(adapter);

        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        /* commenting line below out temporarily to run newdetails in webview */

        String guid = myRssFeedVideo.getItem(position).getGuid();

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

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // mobile

        State mobile = conMan.getNetworkInfo(0).getState();

        // wifi

        State wifi = conMan.getNetworkInfo(1).getState();

        String completedBoxScoresUrl;
        if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                || (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING))
        {
            String uriString = "http://cds1.yospace.com/access/choice/u/0/1/"
                    + guid
                    + "?rtspdirect=true&f=001111582643&stylesheet=mobile";

            Intent intent = new Intent("android.intent.action.VIEW",
                    Uri.parse(uriString));
            
        /*    AppMeasurement s;

            s = new AppMeasurement(getApplication()); // Activity.getApplication

             Specify the Report Suite ID(s) to track here 
            s.account = "cbc-mobile-dev-2";
            
            s.account = "cbc-mobile-prod-9";

             You may add or alter any code config here 

            s.channel = "android-app";

            s.prop1 = "android-app:hockey-app";
            
            s.prop2 = "android-app:hockey-app:" + C2;
            
            s.prop3 = "android-app:hockey-app:" + C2 + ":" + myRssFeedVideo.getItem(position).getTitle();
            
            s.prop4 = "page";

            s.pageName = "android-app:hockey-app:" + C2 + ":" + myRssFeedVideo.getItem(position).getTitle();

            s.currencyCode = "CAD";

             Turn on and configure debugging here 
            s.debugTracking = true;

            
             * WARNING: Changing any of the below variables will cause drastic
             * changes to how your visitor data is collected. Changes should only be
             * made when instructed to do so by your account manager.
             
            s.trackingServer = "metrics.cbc.ca";

            s.track();
*/
            startActivity(intent);

        } else
        {
            CharSequence text = "...no network connection found...";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getBaseContext(), text, duration);
            toast.show();

        }

        /*
         * i.setClass(CBCNewsAndroidWebView.this, VideoViewActivity.class);
         */

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
            startReadRss();
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

        Intent b = new Intent(this, GameHighlights.class);
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

        Intent b = new Intent(this, VideoOld.class);
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