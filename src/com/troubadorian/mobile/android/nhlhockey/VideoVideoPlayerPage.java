/*
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.nhlhockey;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class VideoVideoPlayerPage extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
       setContentView(R.layout.videovideoplayerpage_main);
       
       final WebView mWebView = (WebView) findViewById(R.id.newsWebView); 
       mWebView.getSettings().setJavaScriptEnabled(true);
       
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
       
   	/* mWebView.loadUrl("http://iphone.cbc.ca/sports.html?hockey");*/
   	  
   	mWebView.loadUrl("http://cds1.yospace.com/access/choice/u/0/1/13162320?rtspdirect=true&amp;f=001110786488&amp;stylesheet=mobile");
   	   
   	 ImageButton newsBtBack = (ImageButton) findViewById(R.id.newsBtBack);  
   	 
   	 newsBtBack.setOnClickListener(new OnClickListener() 
     {
     	public void onClick(View v) 
         {                
     			
     		mWebView.goBack();

     		Toast.makeText(getBaseContext(), 
                     "news go back selected", 
                     Toast.LENGTH_SHORT).show();

         }
     });
   	
  	 ImageButton newsBtForward = (ImageButton) findViewById(R.id.newsBtForward);  
   	 
  	 newsBtForward.setOnClickListener(new OnClickListener() 
      {
      	public void onClick(View v) 
          {                
      			
      		mWebView.goForward();

      		Toast.makeText(getBaseContext(), 
                      "news go forward selected", 
                      Toast.LENGTH_SHORT).show();

          }
      });

 	 ImageButton newsBtReload = (ImageButton) findViewById(R.id.newsBtReload);  
 	 
 	 newsBtReload.setOnClickListener(new OnClickListener() 
     {
    	public void onClick(View v) 
        {                
    			
    		mWebView.reload();

    		Toast.makeText(getBaseContext(), 
                    "news reload selected", 
                    Toast.LENGTH_SHORT).show();

        }
    });
   	
    }
}