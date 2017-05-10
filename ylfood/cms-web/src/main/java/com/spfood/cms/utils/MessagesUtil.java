package com.spfood.cms.utils;

import com.spfood.kernel.utils.ApplicationContextHolder;

public class MessagesUtil {

	public  static String getMessageByKey(String key){
		return ApplicationContextHolder.getApplicationContext().getMessage(key, null, null);
	}
}
