package com.troubadorian.mobile.android.nhlhockey;

import java.util.List;
import java.util.Vector;

public class RSSFeedVideo {
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubdate = null;
	
	private List<RSSItemVideo> itemList;
	
	RSSFeedVideo(){
		itemList = new Vector<RSSItemVideo>(0);
	}
	
	void addItem(RSSItemVideo item){
		itemList.add(item);
	}
	
	RSSItemVideo getItem(int location){
		return itemList.get(location);
	}
	
	List<RSSItemVideo> getList(){
		return itemList;
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

}
