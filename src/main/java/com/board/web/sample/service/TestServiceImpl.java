package com.board.web.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.web.common.exceptions.TestException;
import com.board.web.common.util.paginator.Pager;
import com.board.web.common.util.paginator.PagerFactory;
import com.board.web.common.util.paginator.explorer.PageExplorer;
import com.board.web.common.util.paginator.list.PageableList;
import com.board.web.sample.mapper.TestMapper;
import com.board.web.sample.vo.TestVo;

@Service
@Transactional(value = "transactionManager", rollbackFor = RuntimeException.class)
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestMapper mapper;
	
	@Override
	public PageableList<TestVo> list(Map<String, Object> searchData) {
		
		Pager pager = PagerFactory.getPager(searchData);
		PageExplorer result = pager.makePageExplorer(searchData);
		result.setList(mapper.list(searchData));
		
		return new PageableList<TestVo>(result);
	}

	@Override
	public String testException() {
		throw new TestException();
	}

}
