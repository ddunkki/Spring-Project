package com.board.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.web.sample.Restaurant;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/resources/config/action-servlet.xml")// context-root.xml과 상관이 없으므로 파일 정보를 넣어준다.
@RunWith(SpringJUnit4ClassRunner.class) //테스트 실행할때 사용하는 프로그램, spring-test안에 있는 클래스 사용
@Log4j //log라는 변수를 사용할 수 있게 한다. - lombok설정이 제대로 되있어야함,아니면 log4j.xml로 사용할 수 있다.
public class SampleTest {
	
	//자동 DI 선언
	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant;
	
	
	//자동 DI 테스트 메서드
	@Test //jUnit을 이용한 테스트
	public void testExist() {
		log.info("=======자동DI 테스트========");
		
		//null check
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("===========================================================");
		log.info(restaurant.getChef());
		
	}
}
