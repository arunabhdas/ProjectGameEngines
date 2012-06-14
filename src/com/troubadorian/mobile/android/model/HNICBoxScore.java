/**
 * HNICBoxScore.java
 * @author Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;


public class HNICBoxScore
{

    private String away;

    private String home;
    
    private String awayfull;
    
    private String homefull;
    

    @SerializedName("start-date-time")
    private String start_date_time;

    private String period;

    private String status;

    @SerializedName("away-boxscore")
    private HNICBoxScoreDetail awayBoxScore;

    @SerializedName("home-boxscore")
    private HNICBoxScoreDetail homeBoxScore;

    public static HNICBoxScore boxScoreForGameInList(HNICGame game,
            List<HNICBoxScore> boxScoreList)
    {
        HNICBoxScore match = null;
        if (boxScoreList == null)
            return null;
        for (HNICBoxScore boxScore : boxScoreList)
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
     * @return the period
     */
    public String getPeriod()
    {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period)
    {
        this.period = period;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return the awayBoxScore
     */
    public HNICBoxScoreDetail getAwayBoxScore()
    {
        return awayBoxScore;
    }

    /**
     * @param awayBoxScore
     *            the awayBoxScore to set
     */
    public void setAwayBoxScore(HNICBoxScoreDetail awayBoxScore)
    {
        this.awayBoxScore = awayBoxScore;
    }

    /**
     * @return the homeBoxScore
     */
    public HNICBoxScoreDetail getHomeBoxScore()
    {
        return homeBoxScore;
    }

    /**
     * @param homeBoxScore
     *            the homeBoxScore to set
     */
    public void setHomeBoxScore(HNICBoxScoreDetail homeBoxScore)
    {
        this.homeBoxScore = homeBoxScore;
    }



    /**
     * @return the awayfull
     */
    public String getAwayfull()
    {
        return awayfull;
    }



    /**
     * @param awayfull the awayfull to set
     */
    public void setAwayfull(String awayfull)
    {
        this.awayfull = awayfull;
    }



    /**
     * @return the homefull
     */
    public String getHomefull()
    {
        return homefull;
    }



    /**
     * @param homefull the homefull to set
     */
    public void setHomefull(String homefull)
    {
        this.homefull = homefull;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((away == null) ? 0 : away.hashCode());
        result = prime * result
                + ((awayBoxScore == null) ? 0 : awayBoxScore.hashCode());
        result = prime * result
                + ((awayfull == null) ? 0 : awayfull.hashCode());
        result = prime * result + ((home == null) ? 0 : home.hashCode());
        result = prime * result
                + ((homeBoxScore == null) ? 0 : homeBoxScore.hashCode());
        result = prime * result
                + ((homefull == null) ? 0 : homefull.hashCode());
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result
                + ((start_date_time == null) ? 0 : start_date_time.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }



    /* (non-Javadoc)
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
        HNICBoxScore other = (HNICBoxScore) obj;
        if (away == null)
        {
            if (other.away != null)
                return false;
        } else if (!away.equals(other.away))
            return false;
        if (awayBoxScore == null)
        {
            if (other.awayBoxScore != null)
                return false;
        } else if (!awayBoxScore.equals(other.awayBoxScore))
            return false;
        if (awayfull == null)
        {
            if (other.awayfull != null)
                return false;
        } else if (!awayfull.equals(other.awayfull))
            return false;
        if (home == null)
        {
            if (other.home != null)
                return false;
        } else if (!home.equals(other.home))
            return false;
        if (homeBoxScore == null)
        {
            if (other.homeBoxScore != null)
                return false;
        } else if (!homeBoxScore.equals(other.homeBoxScore))
            return false;
        if (homefull == null)
        {
            if (other.homefull != null)
                return false;
        } else if (!homefull.equals(other.homefull))
            return false;
        if (period == null)
        {
            if (other.period != null)
                return false;
        } else if (!period.equals(other.period))
            return false;
        if (start_date_time == null)
        {
            if (other.start_date_time != null)
                return false;
        } else if (!start_date_time.equals(other.start_date_time))
            return false;
        if (status == null)
        {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HNICBoxScore [away=" + away + ", home=" + home + ", awayfull="
                + awayfull + ", homefull=" + homefull + ", start_date_time="
                + start_date_time + ", period=" + period + ", status=" + status
                + ", awayBoxScore=" + awayBoxScore + ", homeBoxScore="
                + homeBoxScore + "]";
    }
    
    
    
}