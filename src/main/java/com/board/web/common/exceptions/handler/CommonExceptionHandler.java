package com.board.web.common.exceptions.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.board.web.common.exceptions.TestException;

@ControllerAdvice
public class CommonExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleRuntimeException() {
		
		logger.info("======RuntimeException=====");
				
		ModelAndView view = new ModelAndView("error/500");
		view.addObject("exception" , "RuntimeException");
		view.setStatus(HttpStatus.NOT_FOUND);
		return view;
	}
	
	@ExceptionHandler(TestException.class)
	public ModelAndView handleTestException() {
		
		logger.info("======TestException=====");
		
		ModelAndView view = new ModelAndView("error/500");
		view.addObject("exception" , "TestException");
		return view;
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException() {
		
		logger.info("======NullPointerException=====");
				
		ModelAndView view = new ModelAndView("error/500");
		view.addObject("exception" , "NullPointerException");
		return view;
	}
	
//	@ExceptionHandler(IOException.class)
//	public ModelAndView handleExceptionException() {
//		
//		logger.info("======IOException=====");
//				
//		ModelAndView view = new ModelAndView("error/500");
//		view.setStatus(HttpStatus.NOT_FOUND);
//		return view;
//	}
	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public ModelAndView handleNoHandlerFoundException() {
//		
//		logger.info("======NoHandlerFoundException=====");
//		
//		ModelAndView view = new ModelAndView("error/404");
//		view.setStatus(HttpStatus.NOT_FOUND);
//		return view;
//	}
	
}
