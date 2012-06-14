/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.HashMap;
import java.util.Map;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Newsbak extends Activity 
{
    public static final String TAG = "news";
    
    
    // declare button for home
    private Button mHomeButton;

    // declare button for news
    private Button mNewsButton;

    // declare button for schedule
    private Button mScheduleButton;

    // declare button for more
    private Button mMoreButton;
    
    
    /* private String _siteId = "1429"; */

    private String _siteId = "4606";

    /* private String _partnerId = "1dd21b33bd603c95"; */

    private String _partnerId = "387131ac6a1aa80d";
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_main);
        
     
        
        // Obtain handles to UI objects

        mHomeButton = (Button) findViewById(R.id.home_button);

        mNewsButton = (Button) findViewById(R.id.news_button);

        mScheduleButton = (Button) findViewById(R.id.schedule_button);

        mMoreButton = (Button) findViewById(R.id.more_button);

        // Register handler for mNewsButton button

        mNewsButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Log.d(TAG, "mNewsButton clicked");
                // launchNewsViewer();

            }
        });

        // Register handler for mNewsButton button

        mHomeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mNewsButton clicked");
                finish();
                
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

        // Register handler for mMoreButton button

        mMoreButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d(TAG, "mMoreButton clicked");
                launchMoreViewer();
            }
        });
        
        
     // Hook up button presses to the appropriate event handler.
        ((Button) findViewById(R.id.home_button)).setOnClickListener(mBackListener); 
        
        final WebView mWebView = (WebView) findViewById(R.id.newsWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                // load the java script file to remove the content from
                // iphone.cbc.ca
                mWebView.loadUrl("javascript:(function() { "
                        +

                        // remove breadcrumb
                        "var breadcrumbs = document.getElementsByClassName('breadcrumb'); \n"
                        + "for (var j=0;j<breadcrumbs.length;j++) { \n"
                        + "breadcrumbs[j].style.display= 'none'; \n"
                        + "} \n"
                        +

                        // remove header
                        "document.getElementById('logo').style.display='none'; \n"
                        +

                        // remove toolbar/menu
                        "var toolbars = document.getElementsByClassName('toolbar'); \n"
                        + "for (var j=0;j< toolbars.length;j++) { \n"
                        + "toolbars[j].style.display= 'none'; \n"
                        + "} \n"
                        +

                        // remove footer
                        "document.getElementById('copy').style.display='none'; \n"
                        +

                        // remove hnic logo
                        "if (document.getElementById('nhl-logo')) document.getElementById('nhl-logo').style.display='none'; \n"
                        +

                        // remove ad
                        "if (document.getElementsByClassName('embeddedAds')[0]) { \n"
                        + "var ads = document.getElementsByClassName('embeddedAds'); \n"
                        + "for (var j=0;j< ads.length;j++) { \n"
                        + "ads[j].style.display= 'none'; \n"
                        + "} \n"
                        + "} \n"
                        + "if (document.getElementById('embeddedAd')) { \n"
                        + "document.getElementById('embeddedAd').style.display= 'none'; \n"
                        + "} \n" + "return 'completed'; \n" + "})()");
            }
        });

        mWebView.loadUrl("http://iphone.cbc.ca/sports.html?hockey");

        ImageButton newsBtBack = (ImageButton) findViewById(R.id.newsBtBack);

        newsBtBack.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {

                mWebView.goBack();

                Toast.makeText(getBaseContext(), "news go back selected",
                        Toast.LENGTH_SHORT).show();

            }
        });

        ImageButton newsBtForward = (ImageButton) findViewById(R.id.newsBtForward);

        newsBtForward.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {

                mWebView.goForward();

                Toast.makeText(getBaseContext(), "news go forward selected",
                        Toast.LENGTH_SHORT).show();

            }
        });

        ImageButton newsBtReload = (ImageButton) findViewById(R.id.newsBtReload);

        newsBtReload.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {

                mWebView.reload();

                Toast.makeText(getBaseContext(), "news reload selected",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
    
    /**
     * Launches the News activity
     */
    protected void launchNewsViewer()
    {
        Context context = getApplicationContext();
        CharSequence text = "...loading news...";
        int duration = Toast.LENGTH_SHORT;

        /*Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

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

       /* Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

        Intent b = new Intent(this, Schedule.class);
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

       /* Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

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