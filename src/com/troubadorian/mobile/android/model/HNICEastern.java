/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;

public class HNICEastern
{

    private List<HNICTeam> Northeast;
    
    private List<HNICTeam> Atlantic;
    
    private List<HNICTeam> Southeast;

    public List<HNICTeam> getNortheast()
    {
        return Northeast;
    }

    
    
    public HNICEastern()
    {
        super();
        // TODO Auto-generated constructor stub
    }



    public void setNortheast(List<HNICTeam> northeast)
    {
        Northeast = northeast;
    }

    public List<HNICTeam> getAtlantic()
    {
        return Atlantic;
    }

    public void setAtlantic(List<HNICTeam> atlantic)
    {
        Atlantic = atlantic;
    }

    public List<HNICTeam> getSoutheast()
    {
        return Southeast;
    }

    public void setSoutheast(List<HNICTeam> southeast)
    {
        Southeast = southeast;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((Atlantic == null) ? 0 : Atlantic.hashCode());
        result = prime * result
                + ((Northeast == null) ? 0 : Northeast.hashCode());
        result = prime * result
                + ((Southeast == null) ? 0 : Southeast.hashCode());
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
        HNICEastern other = (HNICEastern) obj;
        if (Atlantic == null)
        {
            if (other.Atlantic != null)
                return false;
        } else if (!Atlantic.equals(other.Atlantic))
            return false;
        if (Northeast == null)
        {
            if (other.Northeast != null)
                return false;
        } else if (!Northeast.equals(other.Northeast))
            return false;
        if (Southeast == null)
        {
            if (other.Southeast != null)
                return false;
        } else if (!Southeast.equals(other.Southeast))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "HNICEastern [Atlantic=" + Atlantic + ", Northeast=" + Northeast
                + ", Southeast=" + Southeast + "]";
    }
    
    
    
}
