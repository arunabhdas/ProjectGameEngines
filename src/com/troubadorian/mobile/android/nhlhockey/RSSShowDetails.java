/*
 * Author : Arunabh Das 
 */

package com.troubadorian.mobile.android.nhlhockey;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RSSShowDetails extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rssshow_details);
        TextView headerTitleText = (TextView) findViewById(R.id.headertitletext);
        
        
        TextView detailsTitle = (TextView) findViewById(R.id.detailstitle);
        TextView detailsDescription = (TextView) findViewById(R.id.detailsdescription);
        TextView detailsPubdate = (TextView) findViewById(R.id.detailspubdate);
        TextView detailsLink = (TextView) findViewById(R.id.detailslink);

        Bundle bundle = this.getIntent().getExtras();

        headerTitleText.setText(bundle.getString("keyTitle"));
        
        
        detailsTitle.setText(bundle.getString("keyTitle"));
        detailsDescription.setText(bundle.getString("keyDescription"));
        detailsPubdate.setText(bundle.getString("keyPubdate"));
        detailsLink.setText(bundle.getString("keyLink"));
        
        
        
        

    }

}
