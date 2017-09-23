package com.kxcbs.utils;

import java.util.UUID;

public class StringUtils {
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())||"null".equals(str)){
			return true;
		}
		return false;
	}
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return String.valueOf(uuid).replace("-", "");
	}
}
