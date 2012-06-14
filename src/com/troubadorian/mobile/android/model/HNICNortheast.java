package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;

public class HNICNortheast
{

    private String Northeast;
    
    private List<HNICTeam> teams;

    public String getNortheast()
    {
        return Northeast;
    }

    public void setNortheast(String northeast)
    {
        Northeast = northeast;
    }

    public List<HNICTeam> getTeams()
    {
        return teams;
    }

    public void setTeams(List<HNICTeam> teams)
    {
        this.teams = teams;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((Northeast == null) ? 0 : Northeast.hashCode());
        result = prime * result + ((teams == null) ? 0 : teams.hashCode());
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
        HNICNortheast other = (HNICNortheast) obj;
        if (Northeast == null)
        {
            if (other.Northeast != null)
                return false;
        } else if (!Northeast.equals(other.Northeast))
            return false;
        if (teams == null)
        {
            if (other.teams != null)
                return false;
        } else if (!teams.equals(other.teams))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "HNICNortheast [Northeast=" + Northeast + ", teams=" + teams
                + "]";
    }


    
    
}
