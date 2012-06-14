/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;


public class HNICTeam
{
    private String fullname;
    
    private String teamname;

    private String abbr;

    private String city;

    @SerializedName("rank-conference")
    private String rank_conference;
    
    @SerializedName("rank-division")
    private String rank_division;
    
    private String played;
    
    private String won;
    
    private String lost;
    
    private String OT;
    
    private String points;
    
    private String goalsfor;
    
    private String goalsagainst;

    
    
    public HNICTeam()
    {
       //
        // TODO Auto-generated constructor stub
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public String getTeamname()
    {
        return teamname;
    }

    public void setTeamname(String teamname)
    {
        this.teamname = teamname;
    }

    public String getAbbr()
    {
        return abbr;
    }

    public void setAbbr(String abbr)
    {
        this.abbr = abbr;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getRank_conference()
    {
        return rank_conference;
    }

    public void setRank_conference(String rankConference)
    {
        rank_conference = rankConference;
    }

    public String getRank_division()
    {
        return rank_division;
    }

    public void setRank_division(String rankDivision)
    {
        rank_division = rankDivision;
    }

    public String getPlayed()
    {
        return played;
    }

    public void setPlayed(String played)
    {
        this.played = played;
    }

    public String getWon()
    {
        return won;
    }

    public void setWon(String won)
    {
        this.won = won;
    }

    public String getLost()
    {
        return lost;
    }

    public void setLost(String lost)
    {
        this.lost = lost;
    }

    public String getOT()
    {
        return OT;
    }

    public void setOT(String oT)
    {
        OT = oT;
    }

    public String getPoints()
    {
        return points;
    }

    public void setPoints(String points)
    {
        this.points = points;
    }

    public String getGoalsfor()
    {
        return goalsfor;
    }

    public void setGoalsfor(String goalsfor)
    {
        this.goalsfor = goalsfor;
    }

    public String getGoalsagainst()
    {
        return goalsagainst;
    }

    public void setGoalsagainst(String goalsagainst)
    {
        this.goalsagainst = goalsagainst;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((OT == null) ? 0 : OT.hashCode());
        result = prime * result + ((abbr == null) ? 0 : abbr.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result
                + ((fullname == null) ? 0 : fullname.hashCode());
        result = prime * result
                + ((goalsagainst == null) ? 0 : goalsagainst.hashCode());
        result = prime * result
                + ((goalsfor == null) ? 0 : goalsfor.hashCode());
        result = prime * result + ((lost == null) ? 0 : lost.hashCode());
        result = prime * result + ((played == null) ? 0 : played.hashCode());
        result = prime * result + ((points == null) ? 0 : points.hashCode());
        result = prime * result
                + ((rank_conference == null) ? 0 : rank_conference.hashCode());
        result = prime * result
                + ((rank_division == null) ? 0 : rank_division.hashCode());
        result = prime * result
                + ((teamname == null) ? 0 : teamname.hashCode());
        result = prime * result + ((won == null) ? 0 : won.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HNICTeam other = (HNICTeam) obj;
        if (OT == null)
        {
            if (other.OT != null)
                return false;
        } else if (!OT.equals(other.OT))
            return false;
        if (abbr == null)
        {
            if (other.abbr != null)
                return false;
        } else if (!abbr.equals(other.abbr))
            return false;
        if (city == null)
        {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (fullname == null)
        {
            if (other.fullname != null)
                return false;
        } else if (!fullname.equals(other.fullname))
            return false;
        if (goalsagainst == null)
        {
            if (other.goalsagainst != null)
                return false;
        } else if (!goalsagainst.equals(other.goalsagainst))
            return false;
        if (goalsfor == null)
        {
            if (other.goalsfor != null)
                return false;
        } else if (!goalsfor.equals(other.goalsfor))
            return false;
        if (lost == null)
        {
            if (other.lost != null)
                return false;
        } else if (!lost.equals(other.lost))
            return false;
        if (played == null)
        {
            if (other.played != null)
                return false;
        } else if (!played.equals(other.played))
            return false;
        if (points == null)
        {
            if (other.points != null)
                return false;
        } else if (!points.equals(other.points))
            return false;
        if (rank_conference == null)
        {
            if (other.rank_conference != null)
                return false;
        } else if (!rank_conference.equals(other.rank_conference))
            return false;
        if (rank_division == null)
        {
            if (other.rank_division != null)
                return false;
        } else if (!rank_division.equals(other.rank_division))
            return false;
        if (teamname == null)
        {
            if (other.teamname != null)
                return false;
        } else if (!teamname.equals(other.teamname))
            return false;
        if (won == null)
        {
            if (other.won != null)
                return false;
        } else if (!won.equals(other.won))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "HNICTeam [OT=" + OT + ", abbr=" + abbr + ", city=" + city
                + ", fullname=" + fullname + ", goalsagainst=" + goalsagainst
                + ", goalsfor=" + goalsfor + ", lost=" + lost + ", played="
                + played + ", points=" + points + ", rank_conference="
                + rank_conference + ", rank_division=" + rank_division
                + ", teamname=" + teamname + ", won=" + won + "]";
    }
    
   


}

