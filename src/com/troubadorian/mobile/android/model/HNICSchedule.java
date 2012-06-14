/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import com.google.myjson.annotations.SerializedName;


public class HNICSchedule {
	private String away;

    private String home;

    @SerializedName("start-date-time")
    private String start_date_time;
    
    @SerializedName("available-on-cbc")
    private String available_on_cbc;
    
    private String season;

	/**
	 * @return the away
	 */
	public String getAway() {
		return away;
	}

	/**
	 * @param away the away to set
	 */
	public void setAway(String away) {
		this.away = away;
	}

	/**
	 * @return the home
	 */
	public String getHome() {
		return home;
	}

	/**
	 * @param home the home to set
	 */
	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * @return the start_date_time
	 */
	public String getStart_date_time() {
		return start_date_time;
	}

	/**
	 * @param start_date_time the start_date_time to set
	 */
	public void setStart_date_time(String start_date_time) {
		this.start_date_time = start_date_time;
	}

	/**
	 * @return the available_on_cbc
	 */
	public String getAvailable_on_cbc() {
		return available_on_cbc;
	}

	/**
	 * @param available_on_cbc the available_on_cbc to set
	 */
	public void setAvailable_on_cbc(String available_on_cbc) {
		this.available_on_cbc = available_on_cbc;
	}

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((available_on_cbc == null) ? 0 : available_on_cbc.hashCode());
		result = prime * result + ((away == null) ? 0 : away.hashCode());
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result
				+ ((start_date_time == null) ? 0 : start_date_time.hashCode());
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
		HNICSchedule other = (HNICSchedule) obj;
		if (available_on_cbc == null) {
			if (other.available_on_cbc != null)
				return false;
		} else if (!available_on_cbc.equals(other.available_on_cbc))
			return false;
		if (away == null) {
			if (other.away != null)
				return false;
		} else if (!away.equals(other.away))
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		if (start_date_time == null) {
			if (other.start_date_time != null)
				return false;
		} else if (!start_date_time.equals(other.start_date_time))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HNICSchedule [away=" + away + ", home=" + home
				+ ", start_date_time=" + start_date_time
				+ ", available_on_cbc=" + available_on_cbc + ", season="
				+ season + "]";
	}
    
    
}
