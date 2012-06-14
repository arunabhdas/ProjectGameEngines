/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

public class HNICSoutheast
{
    private String Southeast;

    private List<HNICTeam> teams;

    public String getSoutheast()
    {
        return Southeast;
    }

    public void setSoutheast(String southeast)
    {
        Southeast = southeast;
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
                + ((Southeast == null) ? 0 : Southeast.hashCode());
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
        HNICSoutheast other = (HNICSoutheast) obj;
        if (Southeast == null)
        {
            if (other.Southeast != null)
                return false;
        } else if (!Southeast.equals(other.Southeast))
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
        return "HNICSoutheast [Southeast=" + Southeast + ", teams=" + teams
                + "]";
    }
    
    
}
