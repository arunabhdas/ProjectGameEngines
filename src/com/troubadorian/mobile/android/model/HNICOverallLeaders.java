/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;


public class HNICOverallLeaders {
	List<String> goals;
	List<String> points;
	List<String> goalsforagainst;
	List<String> goaliewins;
	List<String> goalsagainstaverage;
	List<String> savepercentage;
	/**
	 * @return the goals
	 */
	public List<String> getGoals() {
		return goals;
	}
	/**
	 * @param goals the goals to set
	 */
	public void setGoals(List<String> goals) {
		this.goals = goals;
	}
	@Override
    public String toString()
    {
        return "HNICOverallLeaders [goaliewins=" + goaliewins + ", goals="
                + goals + ", goalsagainstaverage=" + goalsagainstaverage
                + ", goalsforagainst=" + goalsforagainst + ", points=" + points
                + ", savepercentage=" + savepercentage + "]";
    }
    /**
	 * @return the points
	 */
	public List<String> getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(List<String> points) {
		this.points = points;
	}
	/**
	 * @return the goalsforagainst
	 */
	public List<String> getGoalsforagainst() {
		return goalsforagainst;
	}
	/**
	 * @param goalsforagainst the goalsforagainst to set
	 */
	public void setGoalsforagainst(List<String> goalsforagainst) {
		this.goalsforagainst = goalsforagainst;
	}
	/**
	 * @return the goaliewins
	 */
	public List<String> getGoaliewins() {
		return goaliewins;
	}
	/**
	 * @param goaliewins the goaliewins to set
	 */
	public void setGoaliewins(List<String> goaliewins) {
		this.goaliewins = goaliewins;
	}
	/**
	 * @return the goalsagainstaverage
	 */
	public List<String> getGoalsagainstaverage() {
		return goalsagainstaverage;
	}
	/**
	 * @param goalsagainstaverage the goalsagainstaverage to set
	 */
	public void setGoalsagainstaverage(List<String> goalsagainstaverage) {
		this.goalsagainstaverage = goalsagainstaverage;
	}
	/**
	 * @return the savepercentage
	 */
	public List<String> getSavepercentage() {
		return savepercentage;
	}
	/**
	 * @param savepercentage the savepercentage to set
	 */
	public void setSavepercentage(List<String> savepercentage) {
		this.savepercentage = savepercentage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goaliewins == null) ? 0 : goaliewins.hashCode());
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime
				* result
				+ ((goalsagainstaverage == null) ? 0 : goalsagainstaverage
						.hashCode());
		result = prime * result
				+ ((goalsforagainst == null) ? 0 : goalsforagainst.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result
				+ ((savepercentage == null) ? 0 : savepercentage.hashCode());
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
		HNICOverallLeaders other = (HNICOverallLeaders) obj;
		if (goaliewins == null) {
			if (other.goaliewins != null)
				return false;
		} else if (!goaliewins.equals(other.goaliewins))
			return false;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (goalsagainstaverage == null) {
			if (other.goalsagainstaverage != null)
				return false;
		} else if (!goalsagainstaverage.equals(other.goalsagainstaverage))
			return false;
		if (goalsforagainst == null) {
			if (other.goalsforagainst != null)
				return false;
		} else if (!goalsforagainst.equals(other.goalsforagainst))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (savepercentage == null) {
			if (other.savepercentage != null)
				return false;
		} else if (!savepercentage.equals(other.savepercentage))
			return false;
		return true;
	}
	
	
}
