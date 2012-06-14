package com.troubadorian.mobile.android.nhlhockey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewPlayer extends Activity
{
    private static final String TAG = "VideoViewPlayer";

    public SurfaceHolder holder;

    public VideoView mVideoView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoviewplayer_main);
        mVideoView = (VideoView) findViewById(R.id.surfaceview);

        /*
         * String path =
         * "android.resource://ca.cbc.mobile.android.hnic/R.raw.hnic_short";
         */
        /*Uri uri = Uri.parse("android.resource://com.troubadorian.mobile.android.nhlhockey/"
                + R.raw.hnic_short);
        MediaController mc = new MediaController(this);
        mVideoView.setMediaController(mc);
        mVideoView.setVideoURI(uri);
        // video.requestFocus();
        mVideoView.start();*/

    }

}