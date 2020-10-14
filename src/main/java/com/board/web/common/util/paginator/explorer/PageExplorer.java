package com.board.web.common.util.paginator.explorer;

import java.util.ArrayList;
import java.util.List;

import com.board.web.common.util.CollectionUtil;
import com.board.web.common.util.paginator.Pager;
import com.board.web.common.util.paginator.decorator.Decorator;
import com.board.web.common.util.paginator.list.Pageable;

public abstract class PageExplorer {

	protected boolean isSetData;
	protected Pager pager;
	protected Decorator decorator;

	protected String formId;
	protected String link;
	protected String pageFormat;
	protected String prev;
	protected String next;
	protected String start;
	protected String end;

	public int getTotalCount() {
		return pager.getTotalArticleCount();
	}

	public PageExplorer setData(PageOption pageOption) {
		return setData(pageOption.link(), pageOption.pageFormat(), pageOption.prev(), pageOption.next(),
				pageOption.formId(), pageOption.start(), pageOption.end());
	}

	/**
	 * JSP에서 Paging 결과를 보여준다.
	 * 
	 * @param link       Page 번호를 전달할 Parameter Name
	 * @param pageFormat Page 번호를 보여줄 패턴 @(at)가 페이지 번호로 치환된다. [@]로 작성할 경우 [1] [2] [3] 처럼 보여진다.
	 * @param prev       이전 페이지 그룹으로 가는 버튼의 이름을 작성한다.
	 * @param next       다음 페이지 그룹으로 가는 버튼의 이름을 작성한다.
	 * @param formId     서버에게 전달될 Form 의 아이디를 작성한다.
	 * @return PageExplorer
	 */
	public PageExplorer setData(String link, String pageFormat, String prev, String next, String formId, String start, String end) {
		this.isSetData = true;
		this.formId = formId;
		this.link = link;
		this.pageFormat = pageFormat;
		this.prev = prev;
		this.next = next;
		this.start = start;
		this.end = end;
		return this;
	}

	public String make() {
		if (!isSetData) {
			setData(new PageOption());
		}

		StringBuffer buffer = decorator.makeForm(formId, link);

		return generate(buffer);
	}

	protected abstract String generate(StringBuffer pagenation);

	protected String makeStart() {
		return decorator.makeStart(start);
	}
	
	protected String makeEnd(int endPageNumber) {
		return decorator.makeEnd(endPageNumber, end);
	}
	
	protected String makePrevGroup(int prevGroupPageNumber) {
		return decorator.makePrevGroup(prevGroupPageNumber, prev);
	}

	protected String makeHighlightNowPageNumber(String pageNumber) {
		return decorator.makeHighlightNowPageNumber(pageNumber);
	}

	protected String makePageNumbers(int i, String pageNumber) {
		return decorator.makePageNumbers(i, pageNumber);
	}

	protected String makeNextGroup(int nextGroupPageNumber) {
		return decorator.makeNextGroup(nextGroupPageNumber, next);
	}

	public List<? extends Pageable> list;

	public PageExplorer setList(List<? extends Pageable> list) {
		this.list = list;
		if ( CollectionUtil.isNotEmpty(list) ) {
			this.pager.setTotalArticleCount(list.get(0).getTotalCount());
		}
		return this;
	}

	public List<? extends Pageable> getList() {
		if ( list == null ) {
			return new ArrayList<>();
		}
		return list;
	}

}
