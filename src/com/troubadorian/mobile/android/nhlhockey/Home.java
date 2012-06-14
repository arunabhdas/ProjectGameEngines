/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;

public class Home extends Activity
{

    // @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.home_main);
        
        setContentView(R.layout.main);

//        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
//                R.layout.home_main_topgames, new String[]
//                { "gamedate", "player1", "player2", "gamestatus" }, new int[]
//                { R.id.gamedate, R.id.player1, R.id.player2, R.id.gamestatus });
//       
//        Gallery gallery = (Gallery) findViewById(R.id.galleryTopGames);
//
//        gallery.setAdapter(adapter);
//        gallery.setSelection(1);
//        gallery.setSelected(true);
//        gallery.setFocusable(true);

//        // populate data when top game is selected
//        gallery.setOnItemClickListener(new OnItemClickListener()
//        {
//            public void onItemClick(AdapterView parent, View v, int position,
//                    long id)
//            {
//                //
//
//            }
//        });

//        // populate player 1 data
//        ImageView imageViewPlayer1 = (ImageView) findViewById(R.id.logoPlayer1);
//        // TabHost tabHost = (TabHost)findViewById(R.id.ta);
//        imageViewPlayer1.setOnClickListener(new OnClickListener()
//        {
//            public void onClick(View v)
//            {
//
//                // Intent myIntent = new Intent(Home.this,
//                // HomePlayerDetail.class);
//                // Intent intent = new Intent().setClass(Home.this,
//                // HomePlayerDetail.class);
//                setContentView(R.layout.home_playerdetail);
//                // Home.this.startActivity(myIntent);
//            }
//        });

        // populate player 2 data
//        ImageView imageViewPlayer2 = (ImageView) findViewById(R.id.logoPlayer2);
//        imageViewPlayer2.setOnClickListener(new OnClickListener()
//        {
//            public void onClick(View v)
//            {
//
//                Intent myIntent = new Intent(v.getContext(),
//                        HomePlayerDetail.class);
//                startActivityForResult(myIntent, 0);
//            }
//        });
    }

    // use the xml parse class to parse the data from the xml server. this is an
    // example on
    // how to organize the data
    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("gamedate", "FRI  6/4");
        map.put("player2", "PHI  5");
        map.put("player1", "CHI  3");
        map.put("gamestatus", "FINAL");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("gamedate", "SUN  6/6");
        map.put("player2", "CHI  7");
        map.put("player1", "PHI  4");
        map.put("gamestatus", "FINAL");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("gamedate", "WED  6/9");
        map.put("player2", "PHI  3");
        map.put("player1", "CHI  4");
        map.put("gamestatus", "FINAL(OT)");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("gamedate", "THU  6/9");
        map.put("player2", "PIT  3");
        map.put("player1", "BOS  4");
        map.put("gamestatus", "FINAL(OT)");
        list.add(map);
        return list;
    }

}
