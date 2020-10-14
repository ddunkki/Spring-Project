package com.board.web.common.util;

public class SessionUtil {
	
	public static Object getAttribute(String key) {
		return RequestHelper.getSession().getAttribute(key);
	}

	public static void setAttribute(String key, Object object) {
		RequestHelper.getSession().setAttribute(key, object);
	}

	public static void removeAttribute(String key) {
		RequestHelper.getSession().removeAttribute(key);
	}

	public static String getSessionId() {
		return RequestHelper.getSession().getId();
	}
	
	public static boolean isSession(String key) {
		return getAttribute(key) != null;		 
	}
	
	public static void invalidate() {
		RequestHelper.getSession().invalidate();
	}
	
}
