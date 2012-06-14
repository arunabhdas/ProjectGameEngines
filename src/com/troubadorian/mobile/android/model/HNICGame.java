/**
 * @author Arunabh Das
 * 
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;


public class HNICGame
{
    private String away;

    private String home;
    
    private String awayfull;
    
    private String homefull;

    @SerializedName("start-date-time")
    private String start_date_time;

    @SerializedName("available-on-cbc")
    private String available_on_cbc;
    
    private String season;
    
    // private GameResult[] results;

    private List<HNICGameResults> results;

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
     * @return the available_on_cbc
     */
    public String getAvailable_on_cbc()
    {
        return available_on_cbc;
    }

    /**
     * @param availableObCbc
     */
    public void setAvailable_on_cbc(String availableOnCbc)
    {
        available_on_cbc = availableOnCbc;
    }

    
    
    /**
     * @return the results
     */
    public List<HNICGameResults> getResults()
    {
        return results;
    }

    /**
     * @param results
     *            the results to set
     */
    /*
     * public void setResults(GameResult[] results) { this.results = results; }
     */
    public void setResults(List<HNICGameResults> results)
    {
        this.results = results;
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

    /**
     * @return the season
     */
    public String getSeason()
    {
        return season;
    }

    /**
     * @param season the season to set
     */
    public void setSeason(String season)
    {
        this.season = season;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((available_on_cbc == null) ? 0 : available_on_cbc.hashCode());
        result = prime * result + ((away == null) ? 0 : away.hashCode());
        result = prime * result
                + ((awayfull == null) ? 0 : awayfull.hashCode());
        result = prime * result + ((home == null) ? 0 : home.hashCode());
        result = prime * result
                + ((homefull == null) ? 0 : homefull.hashCode());
        result = prime * result + ((results == null) ? 0 : results.hashCode());
        result = prime * result + ((season == null) ? 0 : season.hashCode());
        result = prime * result
                + ((start_date_time == null) ? 0 : start_date_time.hashCode());
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
        HNICGame other = (HNICGame) obj;
        if (available_on_cbc == null)
        {
            if (other.available_on_cbc != null)
                return false;
        } else if (!available_on_cbc.equals(other.available_on_cbc))
            return false;
        if (away == null)
        {
            if (other.away != null)
                return false;
        } else if (!away.equals(other.away))
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
        if (homefull == null)
        {
            if (other.homefull != null)
                return false;
        } else if (!homefull.equals(other.homefull))
            return false;
        if (results == null)
        {
            if (other.results != null)
                return false;
        } else if (!results.equals(other.results))
            return false;
        if (season == null)
        {
            if (other.season != null)
                return false;
        } else if (!season.equals(other.season))
            return false;
        if (start_date_time == null)
        {
            if (other.start_date_time != null)
                return false;
        } else if (!start_date_time.equals(other.start_date_time))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HNICGame [away=" + away + ", home=" + home + ", awayfull="
                + awayfull + ", homefull=" + homefull + ", start_date_time="
                + start_date_time + ", available_on_cbc=" + available_on_cbc
                + ", season=" + season + ", results=" + results + "]";
    }

    
    
    
    
    
    

}