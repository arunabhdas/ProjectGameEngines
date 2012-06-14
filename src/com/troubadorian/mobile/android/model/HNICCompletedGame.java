/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.util.List;


import com.google.myjson.annotations.SerializedName;
import com.troubadorian.mobile.android.model.HNICResult;

public class HNICCompletedGame
{
    public String away;

    public String home;

    @SerializedName("start-date-time")
    public String start_date_time;

    public List<HNICResult> results;

    @Override
    public String toString()
    {
        return "HNICResult [away=" + away + ", home=" + home
                + ", start_date_time=" + start_date_time + ", status=" + "]";
    }

}
