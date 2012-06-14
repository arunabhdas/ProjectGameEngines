package com.troubadorian.mobile.android.model;

import java.util.List;

public class HNICNorthwest
{
    private String Northwest;

    private List<HNICTeam> teams;

    public String getNorthwest()
    {
        return Northwest;
    }

    public void setNorthwest(String northwest)
    {
        Northwest = northwest;
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
                + ((Northwest == null) ? 0 : Northwest.hashCode());
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
        HNICNorthwest other = (HNICNorthwest) obj;
        if (Northwest == null)
        {
            if (other.Northwest != null)
                return false;
        } else if (!Northwest.equals(other.Northwest))
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
        return "HNICNorthwest [Northwest=" + Northwest + ", teams=" + teams
                + "]";
    }
    
    
}
