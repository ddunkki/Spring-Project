package com.board.web.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServletRequest 관련 유틸
 *
 */
public class RequestHelper {
	
	/**
	 * 현재 App의 Context Path를 리턴.
	 * Context Path가  "/" 라면 "" 리턴
	 * @return
	 */
	public static String getContextPath() {
		String contextPath = RequestHelper.getServletRequest().getContextPath();
		if (contextPath.length() <= 1) {
			return "";
		}
		return contextPath;
	}
	
	/**
	 * 현재 요청이 Ajax인지 판단. Ajax라면 true
	 * @return
	 */
	public static boolean isAjax() {
		
		String ajaxCall = (String) getServletRequest().getHeader("X-Requested-With");
		if ( "XMLHttpRequest".equalsIgnoreCase(ajaxCall) ) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * HttpServletRequest 리턴
	 * @return
	 */
	public static HttpServletRequest getServletRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpSession getNewSession() {
		HttpServletRequest request = getServletRequest();
		return request.getSession(true);
	}
	
	public static HttpSession getSession() {
		return getServletRequest().getSession();
	}

}
