package com.troubadorian.mobile.android.nhlhockey;

public class RSSItemVideo {
	
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubdate = null;
	private String guid = null;
	
	
	RSSItemVideo(){
	}
	
	void setTitle(String value)
	{
		title = value;
	}
	void setDescription(String value)
	{
		description = value;
	}
	void setLink(String value)
	{
		link = value;
	}
	void setPubdate(String value)
	{
		pubdate = value;
	}
	
	String getTitle()
	{
		return title;
	}
	String getDescription()
	{
		return description;
	}
	String getLink()
	{
		return link;
	}
	public String getGuid()
    {
        return guid;
    }

    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    String getPubdate()
	{
		return pubdate;
	}
	


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}
	
	

}
