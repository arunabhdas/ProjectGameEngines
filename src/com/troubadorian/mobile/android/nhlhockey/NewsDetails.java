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
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;






import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewsDetails extends Activity 
{
    private static final String TAG = "News";
    
    private static final String C2 = "News";

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

    private String _siteId = "4606";

    /* private String _partnerId = "1dd21b33bd603c95"; */

    private String _partnerId = "387131ac6a1aa80d";

    WebView mWebView;

    final Activity MyActivity = this;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.newsdetails_main);
        
        /* start of framework */
        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

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
                finish();
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

        
        
        /* end of framework */


        mWebView = (WebView) findViewById(R.id.webview);

        mWebView.getSettings().setJavaScriptEnabled(true);

        // Makes Progress bar Visible
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
                Window.PROGRESS_VISIBILITY_ON);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setAllowFileAccess(true);

        /* String newUA = "CBCAndroidNewsAppUserAgent"; */

        String newUA = "Mozilla/5.0 (Linux; U; Android 2.1-update1; en-ca; GT-I9000M Build/ECLAIR) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.1;CBCAndroidNewsAppUserAgent";

        /* Log.d(TAG, "User agent being sent out " + newUA); */

        mWebView.getSettings().setUserAgentString(newUA);

        Bundle bundle = this.getIntent().getExtras();

        String uriString = bundle.getString("uriString");
        
        
       /* AppMeasurement s;

        s = new AppMeasurement(getApplication()); // Activity.getApplication

         Specify the Report Suite ID(s) to track here 
        s.account = "cbc-mobile-dev-2";
        
        s.account = "cbc-mobile-prod-9";

         You may add or alter any code config here 

        s.channel = "android-app";

        s.prop1 = "android-app:hockey-app";
        
        s.prop2 = "android-app:hockey-app:" + C2;
        
        s.prop3 = "android-app:hockey-app:" + C2 + ":" + uriString;
        
        s.prop4 = "page";

        s.pageName = "android-app:hockey-app:" + C2 + ":" + uriString;

        s.currencyCode = "CAD";

         Turn on and configure debugging here 
        s.debugTracking = true;

        
         * WARNING: Changing any of the below variables will cause drastic
         * changes to how your visitor data is collected. Changes should only be
         * made when instructed to do so by your account manager.
         
        s.trackingServer = "metrics.cbc.ca";

        s.track();*/

        /* mWebView.setWebViewClient(new MyWebViewClient()); */

        mWebView.setWebChromeClient(new WebChromeClient()
        {

            ProgressDialog myProgress = ProgressDialog.show(NewsDetails.this,
                    "...loading...", "...please wait...", true, true);

            public void onProgressChanged(WebView view, int progress)
            {
                // Activities and WebViews measure progress with different
                // scales.
                // The progress meter will automatically disappear when we reach
                // 100%

                // MyActivity.setTitle("Loading...");

                MyActivity.setProgress(progress * 1000);

                if (progress == 100)
                    MyActivity.setTitle(R.string.app_name);

                if (progress == 100)
                {
                    myProgress.cancel();
                }

            }
        });

        mWebView.setWebViewClient(new MyWebViewClient());

        mWebView.loadUrl(uriString);

    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            /*
             * if (url.startsWith("http://mobilevideo")) {
             */
            // videoPlayer(url, true);

            /*
             * Intent i = new Intent();
             * 
             * i.setClass(CBCNewsAndroidWebView.this, VideoViewActivity.class);
             * 
             * i.putExtra("uriString", url);
             * 
             * 
             * startActivity(i);
             * 
             * return false;
             */
            /*
             * } else {
             * 
             * 
             * Log.i(TAG, "Loading url " + url);
             * 
             * 
             * view.loadUrl(url); return true; }
             */

            // view.loadUrl(url);
            // view.loadUrl(url);

            /* Log.d(TAG, "----------------logging url" + url); */

            String firstload = getIntent().getExtras().getString("firstload");

            /*
             * Log.d(TAG, "--------------------------" + TAG +
             * "-----------------------------" + firstload);
             */

            /* if (firstload.compareTo("true") == 0) */

            /*
             * Log.d(TAG,
             * "-----------------------------------------This is NOT first load-----------------------------------------"
             * );
             */

            if (url.contains("ys_"))
            {
                /* Log.d(TAG, "logging url" + url); */

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                /*
                 * i.setClass(CBCNewsAndroidWebView.this,
                 * VideoViewActivity.class);
                 */

                startActivity(i);
                return super.shouldOverrideUrlLoading(view, url);
            }

            else if ((url.endsWith("mp3")))
            {
                /*
                 * Log.d(TAG,
                 * "---------------this is either an audio file or a video file"
                 * + url);
                 */

                /*
                 * Intent intent = new Intent("android.intent.action.VIEW",
                 * Uri.parse(url));
                 * 
                 * 
                 * 
                 * 
                 * 
                 * view.getContext().startActivity(intent);
                 */

                Intent i = new Intent();

                i.setClass(NewsDetails.this, VideoViewPlayer.class);

                i.putExtra("uriString", url);

                startActivity(i);

                return super.shouldOverrideUrlLoading(view, url);

            }

            /*
             * else if ((url.startsWith("http://cds1.yospace.com"))) {
             * Log.d(TAG, "---------------this is a video file" + url);
             * 
             * 
             * Intent intent = new Intent("android.intent.action.VIEW",
             * Uri.parse(url));
             * 
             * view.getContext().startActivity(intent);
             * 
             * 
             * Intent i = new Intent();
             * 
             * i.setClass(CBCNewsAndroidWebViewNext.this,
             * VideoViewPlayer.class);
             * 
             * i.putExtra("uriString", url);
             * 
             * i.putExtra("firstload", "false");
             * 
             * startActivity(i);
             * 
             * Intent i = new Intent();
             * 
             * i.setClass(CBCNewsAndroidWebViewNext.this,
             * VideoViewActivity.class);
             * 
             * i.putExtra("uriString", url);
             * 
             * startActivity(i);
             * 
             * return false;
             * 
             * }
             */
            else
            {
                /* Log.d(TAG, "not a video" + url); */

                Intent i = new Intent();

                i.putExtra("uriString", url);

                i.setClass(getBaseContext(), NewsDetails.class);

                /*
                 * Log.d(TAG,
                 * "about to start an activity----------------------------------CBCNewsAndroidWebViewNextNext-----"
                 * + url);
                 */

                startActivity(i);

                return super.shouldOverrideUrlLoading(view, url);
                /* view.loadUrl(url); */
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.news_contextmenu, menu);
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

        Intent b = new Intent(this, VideoOld.class);
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

  

    private InputStream retrieveStream(String url)
    {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(url);

        try
        {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK)
            {
                Log.w(getClass().getSimpleName(), "Error " + statusCode
                        + " for URL " + url);
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();

        } catch (IOException e)
        {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }

        return null;

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