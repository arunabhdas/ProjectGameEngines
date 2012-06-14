/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;


public class HNICTeamRanks {
	
	String goals;
	
	String shots;
	
	@SerializedName("powerplay-success")
	String powerplay_success;
	
	@SerializedName("penalty-minutes-offense")
	String penalty_minutes_offense;
	
	@SerializedName("penalty-minutes-defense")
	String penalty_minutes_defense;
	
	String assits;
	
	String shorthanded;
	
	@SerializedName("goals-against")
	String goals_against;
	
	@SerializedName("shots-against")
	String shots_against;
	
	@SerializedName("penalty-killing")
	String penalty_killing;
	
	String shutouts;
	
	@SerializedName("save-percentage")
	String save_percentage;

	/**
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}

	/**
	 * @return the shots
	 */
	public String getShots() {
		return shots;
	}

	/**
	 * @param shots the shots to set
	 */
	public void setShots(String shots) {
		this.shots = shots;
	}
	
	/**
     * @return the powerplay_success
     */
    public String getPowerplay_success()
    {
        return powerplay_success;
    }

    /**
     * @param powerplay_success the powerplay_success to set
     */
    public void setPowerplay_success(String powerplay_success)
    {
        this.powerplay_success = powerplay_success;
    }

    /**
	 * @return the penalty_minutes_offense
	 */
	public String getPenalty_minutes_offense() {
		return penalty_minutes_offense;
	}

	/**
	 * @param penalty_minutes_offense the penalty_minutes_offense to set
	 */
	public void setPenalty_minutes_offense(String penalty_minutes_offense) {
		this.penalty_minutes_offense = penalty_minutes_offense;
	}

	/**
	 * @return the penalty_minutes_defense
	 */
	public String getPenalty_minutes_defense() {
		return penalty_minutes_defense;
	}

	/**
	 * @param penalty_minutes_defense the penalty_minutes_defense to set
	 */
	public void setPenalty_minutes_defense(String penalty_minutes_defense) {
		this.penalty_minutes_defense = penalty_minutes_defense;
	}

	/**
	 * @return the assits
	 */
	public String getAssits() {
		return assits;
	}

	/**
	 * @param assits the assits to set
	 */
	public void setAssits(String assits) {
		this.assits = assits;
	}

	/**
	 * @return the shorthanded
	 */
	public String getShorthanded() {
		return shorthanded;
	}

	/**
	 * @param shorthanded the shorthanded to set
	 */
	public void setShorthanded(String shorthanded) {
		this.shorthanded = shorthanded;
	}

	/**
	 * @return the goals_against
	 */
	public String getGoals_against() {
		return goals_against;
	}

	/**
	 * @param goals_against the goals_against to set
	 */
	public void setGoals_against(String goals_against) {
		this.goals_against = goals_against;
	}

	/**
	 * @return the shots_against
	 */
	public String getShots_against() {
		return shots_against;
	}

	/**
	 * @param shots_against the shots_against to set
	 */
	public void setShots_against(String shots_against) {
		this.shots_against = shots_against;
	}

	/**
	 * @return the penalty_killing
	 */
	public String getPenalty_killing() {
		return penalty_killing;
	}

	/**
	 * @param penalty_killing the penalty_killing to set
	 */
	public void setPenalty_killing(String penalty_killing) {
		this.penalty_killing = penalty_killing;
	}

	/**
	 * @return the shutouts
	 */
	public String getShutouts() {
		return shutouts;
	}

	/**
	 * @param shutouts the shutouts to set
	 */
	public void setShutouts(String shutouts) {
		this.shutouts = shutouts;
	}

	/**
	 * @return the save_percentage
	 */
	public String getSave_percentage() {
		return save_percentage;
	}

	/**
	 * @param save_percentage the save_percentage to set
	 */
	public void setSave_percentage(String save_percentage) {
		this.save_percentage = save_percentage;
	}
	

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((assits == null) ? 0 : assits.hashCode());
        result = prime * result + ((goals == null) ? 0 : goals.hashCode());
        result = prime * result
                + ((goals_against == null) ? 0 : goals_against.hashCode());
        result = prime * result
                + ((penalty_killing == null) ? 0 : penalty_killing.hashCode());
        result = prime
                * result
                + ((penalty_minutes_defense == null) ? 0
                        : penalty_minutes_defense.hashCode());
        result = prime
                * result
                + ((penalty_minutes_offense == null) ? 0
                        : penalty_minutes_offense.hashCode());
        result = prime
                * result
                + ((powerplay_success == null) ? 0 : powerplay_success
                        .hashCode());
        result = prime * result
                + ((save_percentage == null) ? 0 : save_percentage.hashCode());
        result = prime * result
                + ((shorthanded == null) ? 0 : shorthanded.hashCode());
        result = prime * result + ((shots == null) ? 0 : shots.hashCode());
        result = prime * result
                + ((shots_against == null) ? 0 : shots_against.hashCode());
        result = prime * result
                + ((shutouts == null) ? 0 : shutouts.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HNICTeamRanks other = (HNICTeamRanks) obj;
        if (assits == null)
        {
            if (other.assits != null)
                return false;
        } else if (!assits.equals(other.assits))
            return false;
        if (goals == null)
        {
            if (other.goals != null)
                return false;
        } else if (!goals.equals(other.goals))
            return false;
        if (goals_against == null)
        {
            if (other.goals_against != null)
                return false;
        } else if (!goals_against.equals(other.goals_against))
            return false;
        if (penalty_killing == null)
        {
            if (other.penalty_killing != null)
                return false;
        } else if (!penalty_killing.equals(other.penalty_killing))
            return false;
        if (penalty_minutes_defense == null)
        {
            if (other.penalty_minutes_defense != null)
                return false;
        } else if (!penalty_minutes_defense
                .equals(other.penalty_minutes_defense))
            return false;
        if (penalty_minutes_offense == null)
        {
            if (other.penalty_minutes_offense != null)
                return false;
        } else if (!penalty_minutes_offense
                .equals(other.penalty_minutes_offense))
            return false;
        if (powerplay_success == null)
        {
            if (other.powerplay_success != null)
                return false;
        } else if (!powerplay_success.equals(other.powerplay_success))
            return false;
        if (save_percentage == null)
        {
            if (other.save_percentage != null)
                return false;
        } else if (!save_percentage.equals(other.save_percentage))
            return false;
        if (shorthanded == null)
        {
            if (other.shorthanded != null)
                return false;
        } else if (!shorthanded.equals(other.shorthanded))
            return false;
        if (shots == null)
        {
            if (other.shots != null)
                return false;
        } else if (!shots.equals(other.shots))
            return false;
        if (shots_against == null)
        {
            if (other.shots_against != null)
                return false;
        } else if (!shots_against.equals(other.shots_against))
            return false;
        if (shutouts == null)
        {
            if (other.shutouts != null)
                return false;
        } else if (!shutouts.equals(other.shutouts))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HNICTeamRanks [goals=" + goals + ", shots=" + shots
                + ", powerplay_success=" + powerplay_success
                + ", penalty_minutes_offense=" + penalty_minutes_offense
                + ", penalty_minutes_defense=" + penalty_minutes_defense
                + ", assits=" + assits + ", shorthanded=" + shorthanded
                + ", goals_against=" + goals_against + ", shots_against="
                + shots_against + ", penalty_killing=" + penalty_killing
                + ", shutouts=" + shutouts + ", save_percentage="
                + save_percentage + "]";
    }


	
	
}
