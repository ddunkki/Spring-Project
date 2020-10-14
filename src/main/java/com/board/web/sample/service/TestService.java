package com.board.web.sample.service;

import java.util.List;
import java.util.Map;

import com.board.web.common.util.paginator.list.PageableList;
import com.board.web.sample.vo.TestVo;

public interface TestService {
	
	public PageableList<TestVo> list(Map<String, Object> searchData);
}
