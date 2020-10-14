package com.board.web.common.util.paginator.list;

import java.util.ArrayList;

import com.board.web.common.util.paginator.explorer.PageExplorer;

public class PageableList<E> extends ArrayList<Pageable> {

	private static final long serialVersionUID = -5479233752581038033L;

	private int totalCount;

	private String pagination;
	
	public PageableList(PageExplorer p) {
		super(p.getList());
		this.totalCount = p.getTotalCount();
		this.pagination = p.make();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public String getPagination() {
		return pagination;
	}

}
