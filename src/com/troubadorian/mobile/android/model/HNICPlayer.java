/**
 * Author : Arunabh Das
 */
package com.troubadorian.mobile.android.model;

import java.util.List;

public class HNICPlayer
{
    private String name;

    private String height;

    private String weight;

    private String birthdate;

    private String birthplace;

    private String jersey;

    private String position;


    private String team;

    private List<HNICPlayerSeason> seasonlist;

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getTeam()
    {
        return team;
    }

    public void setTeam(String team)
    {
        this.team = team;
    }

    
    
    /**
     * @return the jersey
     */
    public String getJersey()
    {
        return jersey;
    }

    /**
     * @param jersey the jersey to set
     */
    public void setJersey(String jersey)
    {
        this.jersey = jersey;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the height
     */
    public String getHeight()
    {
        return height;
    }

    /**
     * @param height
     *            the height to set
     */
    public void setHeight(String height)
    {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public String getWeight()
    {
        return weight;
    }

    /**
     * @param weight
     *            the weight to set
     */
    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    /**
     * @return the birthdate
     */
    public String getBirthdate()
    {
        return birthdate;
    }

    /**
     * @param birthdate
     *            the birthdate to set
     */
    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    /**
     * @return the birthplace
     */
    public String getBirthplace()
    {
        return birthplace;
    }

    /**
     * @param birthplace
     *            the birthplace to set
     */
    public void setBirthplace(String birthplace)
    {
        this.birthplace = birthplace;
    }

    /**
     * @return the seasonList
     */
    public List<HNICPlayerSeason> getSeasonList()
    {
        return seasonlist;
    }

    /**
     * @param seasonList
     *            the seasonList to set
     */
    public void setSeasonList(List<HNICPlayerSeason> seasonList)
    {
        this.seasonlist = seasonList;
    }

    

    public int compareByTeam(HNICPlayer c)
    {
        int x = team.compareTo(c.getTeam());
        return x;
    }

    public int compareByName(HNICPlayer c)
    {
        int x = name.compareTo(c.getName());
        return x;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HNICPlayer [name=" + name + ", height=" + height + ", weight="
                + weight + ", birthdate=" + birthdate + ", birthplace="
                + birthplace + ", jersey=" + jersey + ", position=" + position
                + ", team=" + team + ", seasonList=" + seasonlist + "]";
    }

}