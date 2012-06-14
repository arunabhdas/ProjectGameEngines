/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;

import java.util.List;

import com.google.myjson.annotations.SerializedName;


public class HNICTeamPlayer {
	private List<HNICPlayer> roster;
	
	@SerializedName("injuries-list")
	private List<HNICInjury> injuries_list;

	/**
	 * @return the roster
	 */
	public List<HNICPlayer> getRoster() {
		return roster;
	}

	/**
	 * @param roster the roster to set
	 */
	public void setRoster(List<HNICPlayer> roster) {
		this.roster = roster;
	}

	/**
	 * @return the injuries_list
	 */
	public List<HNICInjury> getInjury_list() {
		return injuries_list;
	}

	/**
	 * @param injuries_list the injuries_list to set
	 */
	public void setInjury_list(List<HNICInjury> injuries_list) {
		this.injuries_list = injuries_list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((injuries_list == null) ? 0 : injuries_list.hashCode());
		result = prime * result + ((roster == null) ? 0 : roster.hashCode());
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
		HNICTeamPlayer other = (HNICTeamPlayer) obj;
		if (injuries_list == null) {
			if (other.injuries_list != null)
				return false;
		} else if (!injuries_list.equals(other.injuries_list))
			return false;
		if (roster == null) {
			if (other.roster != null)
				return false;
		} else if (!roster.equals(other.roster))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HNICTeamPlayer [roster=" + roster + ", injuries_list="
				+ injuries_list + "]";
	}
}
