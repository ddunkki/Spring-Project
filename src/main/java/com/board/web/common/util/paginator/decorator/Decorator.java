package com.board.web.common.util.paginator.decorator;

public class Decorator {

	public StringBuffer makeForm(String formId, String link) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type='hidden' id='"+link+"' name='"+link+"' />");
		return buffer;
	}

	public String makePageNumber(String pageFormat, int i) {
		return pageFormat.replaceAll("@", i + "");
	}

	public String makeHighlightNowPageNumber(String pageNumber) {
		return "<a href='javascript:;' class='active' title='현재페이지'>" + pageNumber + "</a>";
	}

	public String makePrevGroup(int prevGroupPageNumber, String prevButtonName) {
		return "<a href='javascript:movePage("+prevGroupPageNumber+");' class='btn-page-prev'><i class='ico ico-page-prev'></i><span class='offscreen'>"+prevButtonName+"</span></a>";
	}

	public String makeNextGroup(int nextGroupPageNumber, String nextButtonName) {
		return "<a href='javascript:movePage("+nextGroupPageNumber+");' class='btn-page-next'><i class='ico ico-page-next'></i><span class='offscreen'>"+nextButtonName+"</span></a>";
	}

	public String makePageNumbers(int pageIndex, String pageNumber) {
		return "<a href='javascript:movePage("+pageIndex+");'>"+pageNumber+"</a>";
	}
	
	public String makeStart(String startButtonName) {
		return "<a href='javascript:movePage(0);' class='btn-page-first'><i class='ico ico-page-first'></i><span class='offscreen'>"+startButtonName+"</span></a>";
	}
	
	public String makeEnd(int endPageNumber, String endButtonName) {
		return "<a href='javascript:movePage("+endPageNumber+");' class='btn-page-end'><i class='ico ico-page-end'></i><span class='offscreen'>"+endButtonName+"</span></a>";
	}
	
}
