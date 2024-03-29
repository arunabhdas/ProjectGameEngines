/*
 * 
 * sax xml parser. 
 * 
 * Created by Arunabh Das on Oct 5, 2010
 * Copyright CBC Radio-Canada 2010. All rights reserved.
 * 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.ArrayList;
import java.util.List;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class XmlSaxParser extends XmlBaseParser {

	static final String RSS = "rss";
	public XmlSaxParser(String feedUrl) {
		super(feedUrl);
	}

	public List<XmlMessage> parse() {
		final XmlMessage currentMessage = new XmlMessage();
		RootElement root = new RootElement(RSS);
		final List<XmlMessage> messages = new ArrayList<XmlMessage>();
		Element channel = root.getChild(CHANNEL);
		Element item = channel.getChild(ITEM);
		item.setEndElementListener(new EndElementListener(){
			public void end() {
				messages.add(currentMessage.copy());
			}
		});
		item.getChild(TITLE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setTitle(body);
			}
		});
		item.getChild(LINK).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setLink(body);
			}
		});
		item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setDescription(body);
			}
		});
		item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setDate(body);
			}
		});
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return messages;
	}

}
