/**
 * HNICAtlantic.java
 * @author Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.util.List;

public class HNICAtlantic
{
    private String Atlantic;

    private List<HNICTeam> teams;

    public String getAtlantic()
    {
        return Atlantic;
    }

    public void setAtlantic(String atlantic)
    {
        Atlantic = atlantic;
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
                + ((Atlantic == null) ? 0 : Atlantic.hashCode());
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
        HNICAtlantic other = (HNICAtlantic) obj;
        if (Atlantic == null)
        {
            if (other.Atlantic != null)
                return false;
        } else if (!Atlantic.equals(other.Atlantic))
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
        return "HNICAtlantic [Atlantic=" + Atlantic + ", teams=" + teams + "]";
    }
    
    
}
