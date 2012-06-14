/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.util.List;

public class HNICPacific
{

    private String Pacific;

    private List<HNICTeam> teams;

    public String getPacific()
    {
        return Pacific;
    }

    public void setPacific(String pacific)
    {
        Pacific = pacific;
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
        result = prime * result + ((Pacific == null) ? 0 : Pacific.hashCode());
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
        HNICPacific other = (HNICPacific) obj;
        if (Pacific == null)
        {
            if (other.Pacific != null)
                return false;
        } else if (!Pacific.equals(other.Pacific))
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
        return "HNICPacific [Pacific=" + Pacific + ", teams=" + teams + "]";
    }
    
    
    
}
