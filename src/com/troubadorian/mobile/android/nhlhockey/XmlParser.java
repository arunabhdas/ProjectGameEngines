/*
 * 
 * The interface of the xml parser. 
 * 
 * Created by Arunabh Das on Oct 5, 2010
 * Copyright CBC Radio-Canada 2010. All rights reserved.
 * 
 */

package com.troubadorian.mobile.android.nhlhockey;

import java.util.List;

public interface XmlParser {
	
	List<XmlMessage> parse();

}
