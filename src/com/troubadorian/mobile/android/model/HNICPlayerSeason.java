/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;


public class HNICPlayerSeason {
	String year;
	String team;
	String jersey;
	String position;
	
	String played;
	String goals;
	String points;
	String goalsforagainst;
	String penaltyminutes;
	String powerplaygoals;
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
	 * @return the jersey
	 */
	public String getJersey() {
		return jersey;
	}
	/**
	 * @param jersey the jersey to set
	 */
	public void setJersey(String jersey) {
		this.jersey = jersey;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the played
	 */
	public String getPlayed() {
		return played;
	}
	/**
	 * @param played the played to set
	 */
	public void setPlayed(String played) {
		this.played = played;
	}
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
	 * @return the points
	 */
	public String getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(String points) {
		this.points = points;
	}
	/**
	 * @return the goalsforagainst
	 */
	public String getGoalsforagainst() {
		return goalsforagainst;
	}
	/**
	 * @param goalsforagainst the goalsforagainst to set
	 */
	public void setGoalsforagainst(String goalsforagainst) {
		this.goalsforagainst = goalsforagainst;
	}
	/**
	 * @return the penaltyminutes
	 */
	public String getPenaltyminutes() {
		return penaltyminutes;
	}
	/**
	 * @param penaltyminutes the penaltyminutes to set
	 */
	public void setPenaltyminutes(String penaltyminutes) {
		this.penaltyminutes = penaltyminutes;
	}
	/**
	 * @return the powerplaygoals
	 */
	public String getPowerplaygoals() {
		return powerplaygoals;
	}
	/**
	 * @param powerplaygoals the powerplaygoals to set
	 */
	public void setPowerplaygoals(String powerplaygoals) {
		this.powerplaygoals = powerplaygoals;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result
				+ ((goalsforagainst == null) ? 0 : goalsforagainst.hashCode());
		result = prime * result + ((jersey == null) ? 0 : jersey.hashCode());
		result = prime * result
				+ ((penaltyminutes == null) ? 0 : penaltyminutes.hashCode());
		result = prime * result + ((played == null) ? 0 : played.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result
				+ ((powerplaygoals == null) ? 0 : powerplaygoals.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		HNICPlayerSeason other = (HNICPlayerSeason) obj;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (goalsforagainst == null) {
			if (other.goalsforagainst != null)
				return false;
		} else if (!goalsforagainst.equals(other.goalsforagainst))
			return false;
		if (jersey == null) {
			if (other.jersey != null)
				return false;
		} else if (!jersey.equals(other.jersey))
			return false;
		if (penaltyminutes == null) {
			if (other.penaltyminutes != null)
				return false;
		} else if (!penaltyminutes.equals(other.penaltyminutes))
			return false;
		if (played == null) {
			if (other.played != null)
				return false;
		} else if (!played.equals(other.played))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (powerplaygoals == null) {
			if (other.powerplaygoals != null)
				return false;
		} else if (!powerplaygoals.equals(other.powerplaygoals))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HNICPlayerSeason [year=" + year + ", team=" + team
				+ ", jersey=" + jersey + ", position=" + position + ", played="
				+ played + ", goals=" + goals + ", points=" + points
				+ ", goalsforagainst=" + goalsforagainst + ", penaltyminutes="
				+ penaltyminutes + ", powerplaygoals=" + powerplaygoals + "]";
	}
	
	
}
