package com.troubadorian.mobile.android.nhlhockey;

public class RSSItem {
	
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubdate = null;
	private String yospaceurl = null;
	
	
	RSSItem(){
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
	String getPubdate()
	{
		return pubdate;
	}
	
	String getUrl()
	{
	    return yospaceurl;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}
	
	

}
