package com.board.web.common.util;

import java.util.Map;

public class MapUtil {

	public static boolean isEmpty(Map<?, ?> map, String key) {
		return map.get(key) == null || map.get(key).toString().trim().length() == 0;
	}
	
	public static boolean isNotEmpty(Map<?, ?> map, String key) {
		return map.get(key) != null && map.get(key).toString().trim().length() > 0;
	}
	
	public static Object get(Map<?, ?> map, String key) {
		if ( isEmpty(map, key) ) {
			return null;
		}
		
		return map.get(key);
	}
	
	public static String getString(Map<?, ?> map, String key) {
		return getString(map, key, "");
	}
	
	public static String getString(Map<?, ?> map, String key, String defaultString) {
		if ( isEmpty(map, key) ) {
			return defaultString;
		}
		
		return map.get(key).toString().trim();
	}
	
}
