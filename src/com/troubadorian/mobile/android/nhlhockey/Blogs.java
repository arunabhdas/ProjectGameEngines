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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;





import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Blogs extends Activity 
{
    ProgressDialog myProgress;

    public static final String TAG = "Blogs";
    
    private static final String C2 = "Blogs";

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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        

        // Let's display the progress in the activity title bar, like the
        // browser app does.
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        
        setContentView(R.layout.blogs_main);
        
        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        section_header_text.setText(TAG);

        final Activity MyActivity = this;
        
        // Makes Progress bar Visible
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        
 
        
        final WebView mWebView = (WebView) findViewById(R.id.newsWebView);
        
    
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            
           ProgressDialog myProgress = ProgressDialog.show(Blogs.this, "...loading...",
                    "...please wait...", true, false);
           
           
           
            
            public void onProgressChanged(WebView view, int progress)
            {
                // Activities and WebViews measure progress with different
                // scales.
                // The progress meter will automatically disappear when we reach
                // 100%
                
                // MyActivity.setTitle("Loading...");
                
                
                
                MyActivity.setProgress(progress * 1000);
                
                /* if(progress == 100)
                    MyActivity.setTitle(R.string.app_name);
                 */
                
                if(progress == 100)
                {
                myProgress.cancel();
                }
             
            }
        });
        
        mWebView.setWebViewClient(new WebViewClient() {  
            @Override  
            public void onPageFinished(WebView view, String url)  
            {  
                //load the java script file to remove the content from iphone.cbc.ca
                mWebView.loadUrl("javascript:(function() { " + 
                        
                        // remove breadcrumb
                        "var breadcrumbs = document.getElementsByClassName('breadcrumb'); \n" +
                        "for (var j=0;j<breadcrumbs.length;j++) { \n"+
                        "breadcrumbs[j].style.display= 'none'; \n"+
                        "} \n" +
                        
                        // remove header
                        "document.getElementById('logo').style.display='none'; \n" +
                        
                        // remove toolbar/menu
                        "var toolbars = document.getElementsByClassName('toolbar'); \n" +
                        "for (var j=0;j< toolbars.length;j++) { \n" +
                        "toolbars[j].style.display= 'none'; \n" +
                        "} \n" +
                        
                        // remove footer
                        "document.getElementById('copy').style.display='none'; \n" +
                        
                        // remove hnic logo
                        "if (document.getElementById('nhl-logo')) document.getElementById('nhl-logo').style.display='none'; \n" +
                        
                        // remove ad
                        "if (document.getElementsByClassName('embeddedAds')[0]) { \n" +
                        "var ads = document.getElementsByClassName('embeddedAds'); \n" +
                        "for (var j=0;j< ads.length;j++) { \n" +
                        "ads[j].style.display= 'none'; \n" +
                        "} \n" +
                        "} \n" +
                        "if (document.getElementById('embeddedAd')) { \n" +
                        "document.getElementById('embeddedAd').style.display= 'none'; \n" +
                        "} \n" +
                        "return 'completed'; \n" +
                        "})()");  
            }  
        });
        
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("http://www.cbc.ca/m/touch/blogs.html?hockey");
        

        /*
         * feedTitle = (TextView)findViewById(R.id.feedtitle); feedDescribtion =
         * (TextView)findViewById(R.id.feeddescribtion); feedPubdate =
         * (TextView)findViewById(R.id.feedpubdate); feedLink =
         * (TextView)findViewById(R.id.feedlink);
         */
    /*    AdMarvelView adMarvelView = (AdMarvelView) findViewById(R.id.ad);
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

      /*  AppMeasurement s;

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

        s.track();
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
            // startReadRss();
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

        /*Intent b = new Intent(this, CBCHNIC.class);
        startActivity(b);*/
        
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

        Intent b = new Intent(this, Blogs.class);
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



}