/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.ArrayList;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PlayerDatabase extends Activity implements OnClickListener
{
    public static final String TAG = "PlayerDatabase";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerdatabase_main);

        Button b1 = (Button) findViewById(R.id.AddRecord);

        b1.setOnClickListener(this);
        
        Button b2 = (Button) findViewById(R.id.UpdateRecords);
        
        b2.setOnClickListener(this);
        
        Button b3 = (Button) findViewById(R.id.SelectRecords);
        
        b3.setOnClickListener(this);
        
        Button b4 = (Button) findViewById(R.id.DeleteGames);
        
        b4.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        Toast t = null;
        
        Button b = (Button) v;

        DataLayer d = new DataLayer(getBaseContext());

        switch (b.getId())
        {
        
        
        case R.id.AddRecord:
        
            Log.d(TAG, "AddRecords button was clicked.");
            d.AddGame(1, 2, 3, 4);
            break;

        case R.id.UpdateRecords : 
            
            Log.d(TAG, "UpdateRecords button was clicked.");
            
            int affected = d.UpdateGames();
            
            t = Toast.makeText(getBaseContext(), "records affected" + affected, 1000);
            
            t.show();
            
            
            break;

   /*     case R.id.SelectRecords:
        
            Log.d(TAG, "SelectRecords button was clicked.");
            ArrayList<HNICGameResult> results = d.SelectGames();

            t = Toast.makeText(getBaseContext(),
                    "# records: " + results.size(), 1000);

            t.show();

            break;*/

            
        case R.id.DeleteGames :
            
            Log.d(TAG, "DeleteGames button was clicked.");
            
            int deleted = d.DeleteGames();
           
            t = Toast.makeText(getBaseContext(), "records deleted" + deleted, 1000);
            
            t.show();
            
            break;

        }

    }
    
 
 

}
