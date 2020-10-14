package com.board.web.sample.mapper;

import java.util.List;
import java.util.Map;

import com.board.web.sample.vo.TestVo;

public interface TestMapper {
	
	public List<TestVo> list(Map<String, Object> searchData);
}
