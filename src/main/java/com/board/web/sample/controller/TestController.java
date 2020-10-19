package com.board.web.sample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.web.common.exceptions.TestException;
import com.board.web.common.util.paginator.list.PageableList;
import com.board.web.sample.service.TestService;
import com.board.web.sample.vo.TestVo;

@Controller
public class TestController {
	
	@Autowired
	private TestService sevice;
	
	@Value("${sample.test.key}")
	private String prop;
	
	//리스트 화면
	@GetMapping(value="/list")
	public ModelAndView list(@RequestParam(required = false) Map<String, Object> searchData) {
		
		ModelAndView view = new ModelAndView("sample/list");
		
		PageableList<TestVo> list = sevice.list(searchData);
		
		view.addObject("list",list);
		view.addObject("prop",prop);
		return view;
	}
	
	@GetMapping(value="/menu2")
	public ModelAndView menu2() {
		
		ModelAndView view = new ModelAndView("sample/menu2");
		
		return view;
	}
	
	@GetMapping(value="/menu3")
	public ModelAndView menu3() {
		
		ModelAndView view = new ModelAndView("sample/menu3");
		
		return view;
	}
	
	@GetMapping(value="/list/write")
	public ModelAndView writeView() {
		
		ModelAndView view = new ModelAndView("sample/write");
		
		return view;
	}
	
	@GetMapping("/serviceException")
	public String serviceException() {
		return sevice.testException(); //service에서 예외발생
	}
	
	
	@GetMapping("/controllerException")
	public void controllerException() {
		throw new NullPointerException(); //controller에서 예외발생
	}
		
	@GetMapping("/testException")
	public void testException() {
		throw new TestException(); // 만든 TestExceptiond으로 예외발생
	}
}
