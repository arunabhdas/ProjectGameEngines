/*
 * 
 * Splash Screen of the CBC Hockey app. 
 * 
 * Created by Arunabh Das on Oct 5, 2010
 * Copyright CBC Radio-Canada 2010. All rights reserved.
 * 
 */
package com.troubadorian.mobile.android.nhlhockey;

import java.util.HashMap;
import java.util.Map;





import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends Activity 
{
    protected boolean _active = true;

    /* protected int _splashTime = 7500; */

    /* protected int _splashTime = 10000; */

    protected int _splashTime = 3000;

    protected int _additionalSplashTime = 3000;

    private MediaPlayer mMediaPlayer;

    /*private String _siteId = "10311";*/
    
    private String _siteId = "9063"; 

    private String _partnerId = "387131ac6a1aa80d";

    private Thread splashTread;

    public static final String TAG = "Splash";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        /* start of Omniture code block */

     

      
        // Only retrieve admarvel ad if Wifi or 3G is connected

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // mobile

        State mobile = conMan.getNetworkInfo(0).getState();

        // wifi

        State wifi = conMan.getNetworkInfo(1).getState();

        if (mobile == NetworkInfo.State.CONNECTED
                || mobile == NetworkInfo.State.CONNECTING)
        {
            // mobile
           

            Log
                    .d(
                            TAG,
                            "--------------------Data connection was found and is active--------------------");

            /* start of display warning dialog */
            _active = true;
            // set up dialog
            final Dialog dialog = new Dialog(SplashScreen.this);
            dialog.setContentView(R.layout.datawarningdialog);
            dialog.setTitle("CBC Hockey");
            dialog.setCancelable(true);
            // there are a lot of settings, for dialog, check them all out!

            // set up text
            TextView text = (TextView) dialog.findViewById(R.id.TextView01);
            text.setText(R.string.datawarningtext);

            // set up image view
            ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
            img.setImageResource(R.drawable.logo);

            // set up buttons
            Button buttonYes = (Button) dialog.findViewById(R.id.ButtonYes);
            Button buttonNo = (Button) dialog.findViewById(R.id.ButtonNo);

            buttonYes.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // finish();
                    _active = false;
                    dialog.cancel();

                    /*
                     * if the continue button was clicked, then definitely force
                     * start
                     */
                    Thread newnewsplashTread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                int waited = 0;
                                while (_active
                                        && (waited < _additionalSplashTime))
                                {
                                    sleep(100);
                                    if (_active)
                                    {
                                        waited += 100;
                                    }
                                }
                            } catch (InterruptedException e)
                            {
                                // do nothing
                            } finally
                            {
                                finish();
                                startActivity(new Intent(
                                        "ca.cbc.mobile.android.hnic.CBCHNIC"));

                                stop();
                            }
                        }
                    };

                    newnewsplashTread.start();

                    /*
                     * if the continue button was clicked, then definitely force
                     * start
                     */

                }
            });

            buttonNo.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // finish();
                    _active = false;
                    dialog.cancel();
                    finish();

                }
            });

            // now that the dialog is set up, it's time to show it
            dialog.show();

            /* end of display warning dialog */

        }

        else if (wifi == NetworkInfo.State.CONNECTED
                || wifi == NetworkInfo.State.CONNECTING)
        {

            // wifi
           

            Log
                    .d(
                            TAG,
                            "--------------------Wifi connection was found and is active--------------------");
            Thread newnewsplashTread = new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        int waited = 0;
                        while (_active
                                && (waited < _additionalSplashTime))
                        {
                            sleep(100);
                            if (_active)
                            {
                                waited += 100;
                            }
                        }
                    } catch (InterruptedException e)
                    {
                        // do nothing
                    } finally
                    {
                        finish();
                        startActivity(new Intent(
                                "ca.cbc.mobile.android.hnic.CBCHNIC"));

                        stop();
                    }
                }
            };

            newnewsplashTread.start();
        }

        else
        {
            /*
             * Context context = getApplicationContext();
             * 
             * CharSequence text =
             * "No connection found. Please check your network connection.";
             * 
             * int duration = Toast.LENGTH_SHORT;
             * 
             * Toast toast = Toast.makeText(context, text, duration);
             * 
             * toast.show();
             */

            /* start of display warning dialog */
            _active = true;
            // set up dialog
            final Dialog dialog = new Dialog(SplashScreen.this);
            dialog.setContentView(R.layout.datawarningdialog);
            dialog.setTitle("CBC Hockey");
            dialog.setCancelable(true);
            // there are a lot of settings, for dialog, check them all out!

            // set up text
            TextView text2 = (TextView) dialog.findViewById(R.id.TextView01);
            text2.setText(R.string.offlinewarningtext);

            // set up image view
            ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
            img.setImageResource(R.drawable.logo);

            // set up buttons
            Button buttonYes = (Button) dialog.findViewById(R.id.ButtonYes);
            Button buttonNo = (Button) dialog.findViewById(R.id.ButtonNo);

            buttonYes.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // finish();
                    _active = false;
                    dialog.cancel();

                    /*
                     * if the continue button was clicked, then definitely force
                     * start
                     */
                    Thread newnewsplashTread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                int waited = 0;
                                while (_active
                                        && (waited < _additionalSplashTime))
                                {
                                    sleep(100);
                                    if (_active)
                                    {
                                        waited += 100;
                                    }
                                }
                            } catch (InterruptedException e)
                            {
                                // do nothing
                            } finally
                            {
                                finish();
                                startActivity(new Intent(
                                        "ca.cbc.mobile.android.hnic.CBCHNIC"));

                                stop();
                            }
                        }
                    };

                    newnewsplashTread.start();

                    /*
                     * if the continue button was clicked, then definitely force
                     * start
                     */

                }
            });

            buttonNo.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // finish();
                    _active = false;
                    dialog.cancel();
                    finish();

                }
            });

            // now that the dialog is set up, it's time to show it
            dialog.show();

            /* end of display warning dialog */

            /* adMarvelView.requestNewAd(targetParams, _partnerId, _siteId); */
        }
        //

        /* end of admarvel code */

        // thread for displaying the SplashScreen
        splashTread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    int waited = 0;
                    while (_active || (waited < _splashTime))
                    {
                        sleep(100);
                        if (_active)
                        {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e)
                {
                    // do nothing
                } finally
                {
                    finish();
                    /*
                     * startActivity(new Intent(
                     * "ca.cbc.mobile.android.hnic.CBCHNIC"));
                     */
                    stop();
                }
            }
        };
        // splashTread.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Log.d(TAG, "BACK KEY PRESSED");
            _active = false;
            finish();
  
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            /* _active = false; */
            /*
             * added the following line so that user cannot override the splash
             * screen
             */
            _active = true;
        }
        return _active;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }



}