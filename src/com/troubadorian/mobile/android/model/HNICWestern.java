package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;

public class HNICWestern
{
    private List<HNICTeam> Central;

    private List<HNICTeam> Pacific;

    private List<HNICTeam> Northwest;

    public HNICWestern()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<HNICTeam> getCentral()
    {
        return Central;
    }

    public void setCentral(List<HNICTeam> central)
    {
        Central = central;
    }

    public List<HNICTeam> getPacific()
    {
        return Pacific;
    }

    public void setPacific(List<HNICTeam> pacific)
    {
        Pacific = pacific;
    }

    public List<HNICTeam> getNorthwest()
    {
        return Northwest;
    }

    public void setNorthwest(List<HNICTeam> northwest)
    {
        Northwest = northwest;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Central == null) ? 0 : Central.hashCode());
        result = prime * result
                + ((Northwest == null) ? 0 : Northwest.hashCode());
        result = prime * result + ((Pacific == null) ? 0 : Pacific.hashCode());
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
        HNICWestern other = (HNICWestern) obj;
        if (Central == null)
        {
            if (other.Central != null)
                return false;
        } else if (!Central.equals(other.Central))
            return false;
        if (Northwest == null)
        {
            if (other.Northwest != null)
                return false;
        } else if (!Northwest.equals(other.Northwest))
            return false;
        if (Pacific == null)
        {
            if (other.Pacific != null)
                return false;
        } else if (!Pacific.equals(other.Pacific))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "HNICWestern [Central=" + Central + ", Northwest=" + Northwest
                + ", Pacific=" + Pacific + "]";
    }
    
    

}
