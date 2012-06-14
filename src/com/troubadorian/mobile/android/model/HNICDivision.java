/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.util.List;
import com.google.myjson.annotations.SerializedName;

public class HNICDivision
{
    
    private List<HNICTeam> teams;
    
    private String divisionname;

    public List<HNICTeam> getTeams()
    {
        return teams;
    }

    public void setTeams(List<HNICTeam> teams)
    {
        this.teams = teams;
    }

    public String getDivisionname()
    {
        return divisionname;
    }

    public void setDivisionname(String divisionname)
    {
        this.divisionname = divisionname;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((divisionname == null) ? 0 : divisionname.hashCode());
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
        HNICDivision other = (HNICDivision) obj;
        if (divisionname == null)
        {
            if (other.divisionname != null)
                return false;
        } else if (!divisionname.equals(other.divisionname))
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
        return "HNICDivision [divisionname=" + divisionname + ", teams="
                + teams + "]";
    }
    
    
    
    

}
