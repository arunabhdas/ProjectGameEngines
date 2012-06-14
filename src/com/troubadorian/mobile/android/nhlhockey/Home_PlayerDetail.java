/*
 * Author : Arunabh Das 
 */
package com.troubadorian.mobile.android.nhlhockey;



import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class Home_PlayerDetail extends Activity
{
    // @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // back to home data page
        Button homeBtBack = (Button) findViewById(R.id.homeBtBack);
        homeBtBack.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //
        Button homeBtLatest = (Button) findViewById(R.id.homeBtLatest);
        homeBtLatest.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                // populate latest data
            }
        });

        //
        Button homeBtRoster = (Button) findViewById(R.id.homeBtRoster);
        homeBtRoster.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //
            }
        });

        //
        Button homeBtOffence = (Button) findViewById(R.id.homeBtOffence);
        homeBtBack.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //
            }
        });

        //
        Button homeBtDefence = (Button) findViewById(R.id.homeBtDefence);
        homeBtDefence.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //
            }
        });

    }
}
