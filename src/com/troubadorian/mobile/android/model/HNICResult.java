/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;

public class HNICResult
{
    
    private String period;

    private String type;

    private String penaltytype;
    
    private String powerplay;

    private String time;
    
    private String team;
    
    private String player;
    
    private String action;
    
    private String length;
    

   

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HNICGameResult " 
                +"[period=" + period
                + ", type=" + type 
                + ", penaltytype=" + penaltytype
                + ", time=" + time
                + ", team=" + team              
                + ", player="  + player 
                + ", action=" + action 
                + ", length=" + length 
                + "]";
    }

    /**
     * @return the period
     */
    
    public String getPeriod()
    {
        return period;
    }
    
    /**
     * @return the type
     */
    
    public String getType()
    {
        return type;
    }
    
    /**
     * @return the penaltytype
     */
    
    public String getPenaltyType()
    {
        return penaltytype;
    }

    /**
     * @return the powerplay
     */
    
    
    public String getPowerplay()
    {
        return powerplay;
    }

    /**
     * @return the time
     */
    
    public String getTime()
    {
        return time;
    }
    
    /**
     * @return the team
     */
    
    public String getTeam()
    {
        return team;
    }
    
    /**
     * @return the player
     */
    
    public String getPlayer()
    {
        return player;
    }
    
    /**
     * @return the action
     */
    
    public String getAction()
    {
        return action;
    }
    
    /**
     * @return the length
     */
    
    public String getLength()
    {
        return length;
    }
    
    
    
    
    public void setPeriod(String period)
    {
        this.period = period;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setPenaltytype(String penaltytype)
    {
        this.penaltytype = penaltytype;
    }

    public void setPowerplay(String powerplay)
    {
        this.powerplay = powerplay;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setTeam(String team)
    {
        this.team = team;
    }

    public void setPlayer(String player)
    {
        this.player = player;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public void setLength(String length)
    {
        this.length = length;
    }

    public String getFormattedOutput()
    {
        
        String formatted;
        
        return "\n " 
        +"[period=" + period
        + ", type=" + type 
        + ", penaltytype=" + penaltytype
        + ", time=" + time
        + ", team=" + team              
        + ", player="  + player 
        + ", action=" + action 
        + ", length=" + length 
        + "]";
    }
    
}
