/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;


public class HNICGameResult
{

    private String away;

    private String home;

    @SerializedName("start-date-time")
    private String start_date_time;


 
    private List<HNICResult> results;


    public static HNICGameResult boxScoreForGameInList(HNICGame game,
            List<HNICGameResult> boxScoreList)
    {
        HNICGameResult match = null;
        if (boxScoreList == null)
            return null;
        for (HNICGameResult boxScore : boxScoreList)
        {
            if (game.getAway().equals(boxScore.getAway())
                    && game.getHome().equals(boxScore.getHome())
                    && game.getStart_date_time().equals(
                            boxScore.getStart_date_time()))
            {
                match = boxScore;
                break;
            }
        }
        return match;
    }



    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "BoxScore [away=" + away + ", home=" + home + ", start_date_time=" + start_date_time + ", status="
                + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HNICGameResult other = (HNICGameResult) obj;
        if (away == null)
        {
            if (other.away != null)
                return false;
        } else if (!away.equals(other.away))
            return false;
        if (home == null)
        {
            if (other.home != null)
                return false;
        } else if (!home.equals(other.home))
            return false;

        return true;
    }

    /**
     * @return the away
     */
    public String getAway()
    {
        return away;
    }

    /**
     * @param away
     *            the away to set
     */
    public void setAway(String away)
    {
        this.away = away;
    }

    /**
     * @return the home
     */
    public String getHome()
    {
        return home;
    }

    /**
     * @param home
     *            the home to set
     */
    public void setHome(String home)
    {
        this.home = home;
    }

    /**
     * @return the start_date_time
     */
    public String getStart_date_time()
    {
        return start_date_time;
    }

    /**
     * @param startDateTime
     *            the start_date_time to set
     */
    public void setStart_date_time(String startDateTime)
    {
        start_date_time = startDateTime;
    }

  
    /**
     * @return the results
     */
    public List<HNICResult> getResults()
    {
        return results;
    }

    /**
     * @param awayBoxScore
     *            the awayBoxScore to set
     */
    public void setAwayBoxScore(List<HNICResult> results)
    {
        this.results = results;
    }


}