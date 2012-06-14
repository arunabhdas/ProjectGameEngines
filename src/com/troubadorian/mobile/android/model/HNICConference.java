/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;
import com.google.myjson.annotations.SerializedName;

public class HNICConference
{
    private List<HNICDivision> division;
    
    private String conferencename;
    
    public List<HNICDivision> getDivision()
    {
        return division;
    }

    public void setDivision(List<HNICDivision> division)
    {
        this.division = division;
    }

    public String getConferencename()
    {
        return conferencename;
    }

    public void setConferencename(String conferencename)
    {
        this.conferencename = conferencename;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((conferencename == null) ? 0 : conferencename.hashCode());
        result = prime * result
                + ((division == null) ? 0 : division.hashCode());
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
        HNICConference other = (HNICConference) obj;
        if (conferencename == null)
        {
            if (other.conferencename != null)
                return false;
        } else if (!conferencename.equals(other.conferencename))
            return false;
        if (division == null)
        {
            if (other.division != null)
                return false;
        } else if (!division.equals(other.division))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "HNICConference [conferencename=" + conferencename
                + ", division=" + division + "]";
    }

    
}
