package com.board.web.common.util.paginator.explorer;

import com.board.web.common.util.paginator.Pager;
import com.board.web.common.util.paginator.decorator.Decorator;

public class ClassicPageExplorer extends PageExplorer {

	public ClassicPageExplorer(Pager pager) {
		this.pager = pager;
		this.decorator = new Decorator();
	}
	
	protected String generate(StringBuffer pagenation) {
		
		String toStart = makeStart();
		if (pager.pageNo == 0) {
			toStart = toStart.replace("btn-page-first", "btn-page-first is-disabled")
							.replace("javascript:movePage(0);", "#");
		}
		pagenation.append(toStart);
		
		String toPrevGroup = makePrevGroup(pager.prevGroupPageNumber);
        if (pager.nowGroupNumber == 0) {
        	toPrevGroup = makePrevGroup(0);
        	toPrevGroup = toPrevGroup.replace("btn-page-prev", "btn-page-prev is-disabled")
							.replace("javascript:movePage("+pager.prevGroupPageNumber+");", "#");
        }
        //pagenation.append(toPrevGroup);
        
        //pagenation.append("<div class='page-num'>");
        
        int nextPrintPage = pager.groupStartPage + pager.printPage;
        if (nextPrintPage > pager.totalPage) {
            nextPrintPage = pager.totalPage + 1;
        }

        String pageNumber = "";
        
        if ( nextPrintPage > 1 ) {
        	for (int i = pager.groupStartPage; i < nextPrintPage; i++) {
        		
            	pageNumber = decorator.makePageNumber(pageFormat, i);
                
                if ((i-1) == pager.pageNo) {
                	pageNumber = makeHighlightNowPageNumber(pageNumber);
                	pagenation.append(pageNumber);
                	continue;
                }
                
                pagenation.append(makePageNumbers(i-1, pageNumber));
            }
        }
        else {
        	 pagenation.append(makePageNumbers(0, "1"));
        }
        
        //pagenation.append("</div>");
        
        
        String toNextGroup = makeNextGroup(pager.nextGroupPageNumber);
        String toEnd = makeEnd(pager.totalPage-1);
        if (pager.totalGroup == 0 || pager.nowGroupNumber == (pager.totalGroup - 1)) {
        	toNextGroup = makeNextGroup((pager.totalGroup - 1));
        	toNextGroup = toNextGroup.replace("btn-page-next", "btn-page-next is-disabled")
							.replace("javascript:movePage("+pager.nextGroupPageNumber+");", "#");
        }
        if (pager.totalPage == 0 || pager.pageNo == pager.totalPage-1) {
        	toEnd = toEnd.replace("btn-page-end", "btn-page-end is-disabled")
							.replace("javascript:movePage("+(pager.totalPage-1)+");", "#");
        }
        //pagenation.append(toNextGroup);
        pagenation.append(toEnd);

        return pagenation.toString();
    }
	
}
