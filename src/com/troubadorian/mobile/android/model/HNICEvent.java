/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;


public class HNICEvent {
    
    public enum EventType {GOAL, PENALTY, MISC};
    
    
    private String period;
    private String type;
    private String time;
    private String team;
    private String player;
    private String assit1;
    private String assist2;
    private String penaltyType;
    private String length;
    private String action;
    private String description;
    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }
    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }
    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }
    /**
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }
    /**
     * @return the player
     */
    public String getPlayer() {
        return player;
    }
    /**
     * @param player the player to set
     */
    public void setPlayer(String player) {
        this.player = player;
    }
    /**
     * @return the assit1
     */
    public String getAssit1() {
        return assit1;
    }
    /**
     * @param assit1 the assit1 to set
     */
    public void setAssit1(String assit1) {
        this.assit1 = assit1;
    }
    /**
     * @return the assist2
     */
    public String getAssist2() {
        return assist2;
    }
    /**
     * @param assist2 the assist2 to set
     */
    public void setAssist2(String assist2) {
        this.assist2 = assist2;
    }
    /**
     * @return the penaltyType
     */
    public String getPenaltyType() {
        return penaltyType;
    }
    /**
     * @param penaltyType the penaltyType to set
     */
    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }
    /**
     * @return the length
     */
    public String getLength() {
        return length;
    }
    /**
     * @param length the length to set
     */
    public void setLength(String length) {
        this.length = length;
    }
    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HNICEvent other = (HNICEvent) obj;
        if (period == null) {
            if (other.period != null)
                return false;
        } else if (!period.equals(other.period))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Event [period=" + period + ", player=" + player + ", team="
                + team + ", time=" + time + ", type=" + type + "]";
    }
}