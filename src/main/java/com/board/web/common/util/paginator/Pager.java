package com.board.web.common.util.paginator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.board.web.common.util.paginator.explorer.ClassicPageExplorer;
import com.board.web.common.util.paginator.explorer.PageExplorer;

public abstract class Pager {
	
	private int totalArticleCount;

	protected int printArticle;
	public int printPage;

	protected int startArticleNumber;
	protected int endArticleNumber;

	public int totalPage;
	public int totalGroup;

	public int nowGroupNumber;

	public int groupStartPage;

	public int nextGroupPageNumber;
	public int prevGroupPageNumber;

	public int pageNo;
	
	/**
	 * Paging 객체를 얻어온다.
	 * 한 페이지당 보여지는 게시글 수 10개
	 * 한 페이지당 보여지는 페이지 수 10개
	 * 로 기본 설정됨.
	 */
	public Pager() {
		this.printArticle = 10;
		this.printPage = 10;
	}
	
	public Pager(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}
	
	public void setPageNumber(int pageNumber) {
		setPageNumber(pageNumber + "");
	}
	
	/**
	 * 요청된 페이지의 번호를 얻어온다.
	 * 1 페이지의 경우 0이 입력된다.
	 * 아무것도 입력하지 않았다면 0으로 기본 설정된다.
	 * @param pageNumber: 요청 페이지 번호
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		computeArticleNumbers();
		
		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}
	
	protected abstract void computeArticleNumbers();
	
	/**
	 * 조회하려는 조건(Query)의 총 게시물 수를 정의한다.
	 * @param count: 총 게시물의 수
	 */
	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}
	
	/**
	 * 조회하려는 조건(Query)의 총 게시물 수를 가져온다.
	 * @return 총 게시물의 수
	 */
	public int getTotalArticleCount() {
		return this.totalArticleCount;
	}

	/**
	 * Query에서 사용될 탐색 시작 번호 
	 * Oracle의 경우 rownum의 시작 번호
	 * @return startArticleNumber: 시작 번호
	 */
	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}
	
	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}
	
	/**
	 * Query에서 사용될 탐색 끝 번호를 정의한다.
	 * @param endArticleNumber: 끝 번호 셋팅
	 */
	public abstract void setEndArticleNumber(int endArticleNumber);

	/**
	 * Query에서 사용될 탐색 마지막 번호
	 * Oracle의 경우 rownum의 마지막 번호
	 * @return 끝 번호
	 */
	public abstract int getEndArticleNumber();
	
	/**
	 * PageExplorer 만들기
	 * @param cls
	 * 		<ul>
	 * 			<li>ClassicPageExplorer.class</li>
	 * 			<li>ListPageExplorer.class</li>
	 * 		</ul>
	 * @return ClassicPageExplorer, ListPageExplorer
	 */
	public PageExplorer makePageExplorer(Class<? extends PageExplorer> cls) {
		try {
			PageExplorer pageExplorer = cls.getDeclaredConstructor(Pager.class).newInstance(this);

			return pageExplorer;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			return null;
		}
	}

	public PageExplorer makePageExplorer(Map<String, Object> searchObject) {
		return makePageExplorer(ClassicPageExplorer.class, searchObject);
	}
	
	/**
	 * PageExplorer 만들기
	 * @param cls
	 * 		<ul>
	 * 			<li>ClassicPageExplorer.class</li>
	 * 			<li>ListPageExplorer.class</li>
	 * 		</ul>
	 * @param searchObject: 검색 VO
	 * @return ClassicPageExplorer, ListPageExplorer
	 */
	public PageExplorer makePageExplorer(Class<? extends PageExplorer> cls, Map<String, Object> searchObject) {
		PageExplorer pageExplorer = makePageExplorer(cls);
		setSearchRow(searchObject.getClass(), searchObject);
		return pageExplorer;
	}
	
	private boolean setSearchRow(Class<? extends Object> cls, Map<String, Object> searchObject) {
		
		boolean check = false;
		
		if ( cls == null ) return true;
		
		searchObject.put("endNum", this.getEndArticleNumber());
		searchObject.put("startNum", this.getStartArticleNumber());
		
		return check;
	}
	
}
