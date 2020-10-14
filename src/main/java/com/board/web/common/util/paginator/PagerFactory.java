package com.board.web.common.util.paginator;

import java.util.Map;

import com.board.web.common.util.MapUtil;

public class PagerFactory {

	public static Pager getPager() {
		return new OraclePager();
	}
	
	public static Pager getPager(int printArticle, int printPage) {
		return new OraclePager(printArticle, printPage);
	}

	public static Pager getPager(Map<String, Object> searchObject) {
		Pager pager = getPager();
		pager.setPageNumber(MapUtil.getString(searchObject, "pageNo"));
		return pager;
	}

	public static Pager getPager(Map<String, Object> searchObject, int printArticle, int printPage) {
		Pager pager = getPager(printArticle, printPage);
		pager.setPageNumber(MapUtil.getString(searchObject, "pageNo"));
		return pager;
	}
	
}
