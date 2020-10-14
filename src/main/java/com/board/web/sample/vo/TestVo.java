package com.board.web.sample.vo;

import com.board.web.common.util.paginator.list.Pageable;

import lombok.Data;

@Data
public class TestVo implements Pageable{
	
	private int totalCount;
	
	private String ename;
	private String job;
}
