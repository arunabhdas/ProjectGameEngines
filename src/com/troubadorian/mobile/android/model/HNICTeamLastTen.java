/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;


public class HNICTeamLastTen {
	
    private String away;
    
    private String home;
    
    private String awayfull;
    
    private String homefull;
    
	@SerializedName("start-date-time")
	private String start_date_time;
	
	private String result;
	
	private String gamesplayed;
	
	private String winlossot;
	
	private String powerplaygoals;
	
	private String penaltykill;
	
	private String shotsongoal;
	
	private String shotsagainst;
	
	private String fo;
	
	private String goaliesaves;
	
	private String star1;
	
	private String star2;
	
	private String star3;

    /**
     * @return the away
     */
    public String getAway()
    {
        return away;
    }

    /**
     * @param away the away to set
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
     * @param home the home to set
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
     * @param start_date_time the start_date_time to set
     */
    public void setStart_date_time(String start_date_time)
    {
        this.start_date_time = start_date_time;
    }

    /**
     * @return the result
     */
    public String getResult()
    {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result)
    {
        this.result = result;
    }

    /**
     * @return the gamesplayed
     */
    public String getGamesplayed()
    {
        return gamesplayed;
    }

    /**
     * @param gamesplayed the gamesplayed to set
     */
    public void setGamesplayed(String gamesplayed)
    {
        this.gamesplayed = gamesplayed;
    }

    /**
     * @return the winlossot
     */
    public String getWinlossot()
    {
        return winlossot;
    }

    /**
     * @param winlossot the winlossot to set
     */
    public void setWinlossot(String winlossot)
    {
        this.winlossot = winlossot;
    }

    /**
     * @return the powerplaygoals
     */
    public String getPowerplaygoals()
    {
        return powerplaygoals;
    }

    /**
     * @param powerplaygoals the powerplaygoals to set
     */
    public void setPowerplaygoals(String powerplaygoals)
    {
        this.powerplaygoals = powerplaygoals;
    }

    /**
     * @return the penaltykill
     */
    public String getPenaltykill()
    {
        return penaltykill;
    }

    /**
     * @param penaltykill the penaltykill to set
     */
    public void setPenaltykill(String penaltykill)
    {
        this.penaltykill = penaltykill;
    }

    /**
     * @return the shotsongoal
     */
    public String getShotsongoal()
    {
        return shotsongoal;
    }

    /**
     * @param shotsongoal the shotsongoal to set
     */
    public void setShotsongoal(String shotsongoal)
    {
        this.shotsongoal = shotsongoal;
    }

    /**
     * @return the shotsagainst
     */
    public String getShotsagainst()
    {
        return shotsagainst;
    }

    /**
     * @param shotsagainst the shotsagainst to set
     */
    public void setShotsagainst(String shotsagainst)
    {
        this.shotsagainst = shotsagainst;
    }

    /**
     * @return the fo
     */
    public String getFo()
    {
        return fo;
    }

    /**
     * @param fo the fo to set
     */
    public void setFo(String fo)
    {
        this.fo = fo;
    }

    /**
     * @return the goaliesaves
     */
    public String getGoaliesaves()
    {
        return goaliesaves;
    }

    /**
     * @param goaliesaves the goaliesaves to set
     */
    public void setGoaliesaves(String goaliesaves)
    {
        this.goaliesaves = goaliesaves;
    }

    /**
     * @return the star1
     */
    public String getStar1()
    {
        return star1;
    }

    /**
     * @param star1 the star1 to set
     */
    public void setStar1(String star1)
    {
        this.star1 = star1;
    }

    /**
     * @return the star2
     */
    public String getStar2()
    {
        return star2;
    }

    /**
     * @param star2 the star2 to set
     */
    public void setStar2(String star2)
    {
        this.star2 = star2;
    }

    /**
     * @return the star3
     */
    public String getStar3()
    {
        return star3;
    }

    /**
     * @param star3 the star3 to set
     */
    public void setStar3(String star3)
    {
        this.star3 = star3;
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
                + ((awayfull == null) ? 0 : awayfull.hashCode());
        result = prime * result + ((fo == null) ? 0 : fo.hashCode());
        result = prime * result
                + ((gamesplayed == null) ? 0 : gamesplayed.hashCode());
        result = prime * result
                + ((goaliesaves == null) ? 0 : goaliesaves.hashCode());
        result = prime * result + ((home == null) ? 0 : home.hashCode());
        result = prime * result
                + ((homefull == null) ? 0 : homefull.hashCode());
        result = prime * result
                + ((penaltykill == null) ? 0 : penaltykill.hashCode());
        result = prime * result
                + ((powerplaygoals == null) ? 0 : powerplaygoals.hashCode());
        result = prime * result
                + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result
                + ((shotsagainst == null) ? 0 : shotsagainst.hashCode());
        result = prime * result
                + ((shotsongoal == null) ? 0 : shotsongoal.hashCode());
        result = prime * result + ((star1 == null) ? 0 : star1.hashCode());
        result = prime * result + ((star2 == null) ? 0 : star2.hashCode());
        result = prime * result + ((star3 == null) ? 0 : star3.hashCode());
        result = prime * result
                + ((start_date_time == null) ? 0 : start_date_time.hashCode());
        result = prime * result
                + ((winlossot == null) ? 0 : winlossot.hashCode());
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
        HNICTeamLastTen other = (HNICTeamLastTen) obj;
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
        if (fo == null)
        {
            if (other.fo != null)
                return false;
        } else if (!fo.equals(other.fo))
            return false;
        if (gamesplayed == null)
        {
            if (other.gamesplayed != null)
                return false;
        } else if (!gamesplayed.equals(other.gamesplayed))
            return false;
        if (goaliesaves == null)
        {
            if (other.goaliesaves != null)
                return false;
        } else if (!goaliesaves.equals(other.goaliesaves))
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
        if (penaltykill == null)
        {
            if (other.penaltykill != null)
                return false;
        } else if (!penaltykill.equals(other.penaltykill))
            return false;
        if (powerplaygoals == null)
        {
            if (other.powerplaygoals != null)
                return false;
        } else if (!powerplaygoals.equals(other.powerplaygoals))
            return false;
        if (result == null)
        {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        if (shotsagainst == null)
        {
            if (other.shotsagainst != null)
                return false;
        } else if (!shotsagainst.equals(other.shotsagainst))
            return false;
        if (shotsongoal == null)
        {
            if (other.shotsongoal != null)
                return false;
        } else if (!shotsongoal.equals(other.shotsongoal))
            return false;
        if (star1 == null)
        {
            if (other.star1 != null)
                return false;
        } else if (!star1.equals(other.star1))
            return false;
        if (star2 == null)
        {
            if (other.star2 != null)
                return false;
        } else if (!star2.equals(other.star2))
            return false;
        if (star3 == null)
        {
            if (other.star3 != null)
                return false;
        } else if (!star3.equals(other.star3))
            return false;
        if (start_date_time == null)
        {
            if (other.start_date_time != null)
                return false;
        } else if (!start_date_time.equals(other.start_date_time))
            return false;
        if (winlossot == null)
        {
            if (other.winlossot != null)
                return false;
        } else if (!winlossot.equals(other.winlossot))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HNICTeamLastTen [away=" + away + ", home=" + home
                + ", awayfull=" + awayfull + ", homefull=" + homefull
                + ", start_date_time=" + start_date_time + ", result=" + result
                + ", gamesplayed=" + gamesplayed + ", winlossot=" + winlossot
                + ", powerplaygoals=" + powerplaygoals + ", penaltykill="
                + penaltykill + ", shotsongoal=" + shotsongoal
                + ", shotsagainst=" + shotsagainst + ", fo=" + fo
                + ", goaliesaves=" + goaliesaves + ", star1=" + star1
                + ", star2=" + star2 + ", star3=" + star3 + "]";
    }


	

}
