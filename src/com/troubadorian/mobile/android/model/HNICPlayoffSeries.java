/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;

public class HNICPlayoffSeries {
	
	@SerializedName("team-1")
	private String team_1;
	
	@SerializedName("team-2")
	private String team_2;
	
	private String status;

	/**
	 * @return the team_1
	 */
	public String getTeam_1() {
		return team_1;
	}

	/**
	 * @param team_1 the team_1 to set
	 */
	public void setTeam_1(String team_1) {
		this.team_1 = team_1;
	}

	/**
	 * @return the team_2
	 */
	public String getTeam_2() {
		return team_2;
	}

	/**
	 * @param team_2 the team_2 to set
	 */
	public void setTeam_2(String team_2) {
		this.team_2 = team_2;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((team_1 == null) ? 0 : team_1.hashCode());
		result = prime * result + ((team_2 == null) ? 0 : team_2.hashCode());
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
		HNICPlayoffSeries other = (HNICPlayoffSeries) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (team_1 == null) {
			if (other.team_1 != null)
				return false;
		} else if (!team_1.equals(other.team_1))
			return false;
		if (team_2 == null) {
			if (other.team_2 != null)
				return false;
		} else if (!team_2.equals(other.team_2))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HNICPlayoffSeries [team_1=" + team_1 + ", team_2=" + team_2
				+ ", status=" + status + "]";
	}

}
