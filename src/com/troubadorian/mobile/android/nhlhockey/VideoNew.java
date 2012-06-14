package com.troubadorian.mobile.android.nhlhockey;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.troubadorian.mobile.android.nhlhockey.VideoNew.VideoAdapter.VideoHolder;
import com.troubadorian.android.techscape.utils.ImageUtils;
import com.troubadorian.android.techscape.utils.LoadYoutubeThumbTask;
import com.troubadorian.android.techscape.utils.Utils;
import com.troubadorian.android.techscape.utils.LoadYoutubeThumbTask.LoadsThumb;
import com.troubadorian.youtube.Video;
import com.troubadorian.youtube.YouTube;
import com.troubadorian.youtube.YouTubeException;

public class VideoNew extends ListActivity implements LoadsThumb
{
    private static final int MENU_WATCH = 0;

    private static final int MENU_COPY = 1;
    
    private static final String TAG = "Video";

    private String username;

    private Video[] videos;

    private LoadVideosTask loadVideosTask = null;

    private HashMap<Integer, LoadYoutubeThumbTask> loadThumbTasks = new HashMap<Integer, LoadYoutubeThumbTask>();
    
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

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /* setContentView(R.layout.list); */

        setContentView(R.layout.videonew);

        /*username = getIntent().getStringExtra("username");*/
        
        TextView section_header_text = (TextView) findViewById(R.id.section_header_text);
        
        section_header_text.setText(TAG);
        
        username = "NHLArchive";

        LegislatorYouTubeHolder holder = (LegislatorYouTubeHolder) getLastNonConfigurationInstance();
        if (holder != null)
        {
            videos = holder.videos;
            loadVideosTask = holder.loadVideosTask;
            if (loadVideosTask != null)
                loadVideosTask.onScreenLoad(this);

            loadThumbTasks = holder.loadThumbTasks;
            if (loadThumbTasks != null)
            {
                Iterator<LoadYoutubeThumbTask> iterator = loadThumbTasks
                        .values().iterator();
                while (iterator.hasNext())
                    iterator.next().onScreenLoad(this);
            }
        }

        setupControls();
        if (loadVideosTask == null)
        {
            loadVideos();
        }
        
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
                finish();
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
        
    }

    @Override
    public Object onRetainNonConfigurationInstance()
    {
        LegislatorYouTubeHolder holder = new LegislatorYouTubeHolder();
        holder.videos = this.videos;
        holder.loadVideosTask = this.loadVideosTask;
        holder.loadThumbTasks = this.loadThumbTasks;
        return holder;
    }

    private void setupControls()
    {
        Utils.setLoading(this, R.string.youtube_loading);
        ((Button) findViewById(R.id.refresh))
                .setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        videos = null;
                        Utils.showLoading(VideoNew.this);
                        loadVideos();
                    }
                });
        registerForContextMenu(getListView());
    }

    protected void loadVideos()
    {
        if (videos == null)
            loadVideosTask = (LoadVideosTask) new LoadVideosTask(this)
                    .execute(username);
        else
            displayVideos();
    }

    protected void displayVideos()
    {
        if (videos != null && videos.length > 0)
            setListAdapter(new VideoAdapter(VideoNew.this, videos));
        else
            Utils.showRefresh(this, R.string.youtube_empty);
    }

    @Override
    public void onListItemClick(ListView parent, View view, int position,
            long id)
    {
        Video video = (Video) parent.getItemAtPosition(position);
        launchVideo(video);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
            ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(0, MENU_WATCH, 0, "Watch");
        menu.add(0, MENU_COPY, 1, "Copy link");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_contextmenu, menu);
        return true;
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
                .getMenuInfo();
        Video video = (Video) getListView().getItemAtPosition(info.position);

        switch (item.getItemId())
        {
        case MENU_WATCH:
            launchVideo(video);
            return true;
        case MENU_COPY:
            ClipboardManager cm = (ClipboardManager) getSystemService(Activity.CLIPBOARD_SERVICE);
            cm.setText(video.url);
        }

        return super.onContextItemSelected(item);
    }

    private void launchVideo(Video video)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(video.url));
        i.setPackage("com.google.android.youtube");
        startActivity(i);
        /*
         * startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(video.url)));
         * Intent i = new Intent(Intent.ACTION_VIEW,
         * Uri.parse("vnd.youtube:VIDEO_ID")); startActivity(i);
         */
    }

    protected class VideoAdapter extends ArrayAdapter<Video>
    {
        LayoutInflater inflater;

        VideoNew context;

        public VideoAdapter(VideoNew context, Video[] videos)
        {
            super(context, 0, videos);
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }

        @Override
        public int getViewTypeCount()
        {
            return 1;
        }

        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null)
                view = inflater.inflate(R.layout.youtube, null);

            Video video = getItem(position);

            VideoHolder holder = new VideoHolder();
            holder.url = video.thumbnailUrl;
            holder.hash = holder.url == null ? null : holder.url.hashCode();

            TextView title = (TextView) view.findViewById(R.id.video_title);
            title.setText(video.title);
            holder.title = title;

            // make the date stand out in the description using bold text
            StringBuilder full = new StringBuilder("<b>").append(
                    video.timestamp.format("%b %d")).append("</b>");
            String description = video.description != null ? video.description
                    .trim() : "";

            if (!description.equals("")) // check to see if the video has a
                                         // non-empty description first
                full.append(" - ").append(description);

            TextView desc = (TextView) view
                    .findViewById(R.id.video_description);
            desc.setText(Html.fromHtml(Utils.truncate(full.toString(), 150)));
            holder.description = desc;

            ImageView thumb = (ImageView) view.findViewById(R.id.thumbnail);
            holder.thumb = thumb;

            if (holder.hash != null)
            {
                BitmapDrawable pic = ImageUtils.quickGetImage(
                        ImageUtils.YOUTUBE_THUMB, holder.hash, context);
                if (pic != null)
                {
                    holder.thumb.setImageDrawable(pic);
                } else
                {
                    holder.thumb.setImageResource(R.drawable.loading_photo);
                    context.loadThumb(holder);
                }
            } else
                holder.thumb.setImageResource(R.drawable.youtube_thumb);

            view.setTag(holder);
            return view;
        }

        class VideoHolder
        {
            Integer hash;

            String url;

            ImageView thumb;

            TextView title;

            TextView description;

            @Override
            public boolean equals(Object o)
            {
                if (!(o instanceof VideoHolder))
                    return false;
                VideoHolder ov = (VideoHolder) o;
                if (ov == null)
                    return this == null;
                return ov.hash.equals(this.hash);
            }
        }
    }

    private class LoadVideosTask extends AsyncTask<String, Void, Video[]>
    {
        public VideoNew context;

        public LoadVideosTask(VideoNew context)
        {
            super();
            this.context = context;
        }

        public void onScreenLoad(VideoNew context)
        {
            this.context = context;
        }

        @Override
        protected Video[] doInBackground(String... usernames)
        {
            try
            {
                return new YouTube().getVideos(username);
            } catch (YouTubeException e)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Video[] videos)
        {
            context.videos = videos;
            context.displayVideos();
            context.loadVideosTask = null;
        }
    }

    static class LegislatorYouTubeHolder
    {
        Video[] videos;

        LoadVideosTask loadVideosTask;

        HashMap<Integer, LoadYoutubeThumbTask> loadThumbTasks;
    }

    public void loadThumb(VideoAdapter.VideoHolder holder)
    {
        int hash = holder.url.hashCode();
        if (!loadThumbTasks.containsKey(hash))
            loadThumbTasks.put(hash,
                    (LoadYoutubeThumbTask) new LoadYoutubeThumbTask(this,
                            ImageUtils.YOUTUBE_THUMB, holder)
                            .execute(holder.url));
    }

    public void onLoadThumb(Drawable thumb, Object tag)
    {
        VideoAdapter.VideoHolder holder = (VideoHolder) tag;

        loadThumbTasks.remove(holder.hash);

        View result = getListView().findViewWithTag(holder);
        if (result != null)
        {
            if (thumb != null)
                holder.thumb.setImageDrawable(thumb);
            else
                holder.thumb.setImageResource(R.drawable.youtube_thumb);
        }
    }

    public Context getContext()
    {
        return this;
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
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */

        /* b.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY); */

        /* b.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS); */

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
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
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
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
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
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
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
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
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
        /* b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); */
        startActivity(b);
    }
}