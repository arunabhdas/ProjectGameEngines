/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

public class HNICCentral
{
    private String Central;

    private List<HNICTeam> teams;

    public String getCentral()
    {
        return Central;
    }

    public void setCentral(String central)
    {
        Central = central;
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
        result = prime * result + ((Central == null) ? 0 : Central.hashCode());
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
        HNICCentral other = (HNICCentral) obj;
        if (Central == null)
        {
            if (other.Central != null)
                return false;
        } else if (!Central.equals(other.Central))
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
        return "HNICCentral [Central=" + Central + ", teams=" + teams + "]";
    }
    

}
