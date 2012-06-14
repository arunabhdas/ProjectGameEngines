/*
 * 
 * base xml parser class. 
 * 
 * Created by Arunabh Das on Oct 4, 2010
 * Copyright CBC Radio-Canada 2010. All rights reserved.
 * 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class XmlBaseParser implements XmlParser {

	
	// names of the XML tags
	static final String CHANNEL = "channel";
	static final String PUB_DATE = "pubDate";
	static final  String DESCRIPTION = "description";
	static final  String LINK = "link";
	static final  String TITLE = "title";
	static final  String ITEM = "item";
	
	private final URL feedUrl;
	
	protected XmlBaseParser(String feedUrl){
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
