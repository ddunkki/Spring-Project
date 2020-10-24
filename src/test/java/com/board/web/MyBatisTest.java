package com.board.web;

import java.sql.Connection;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/config/context-root.xml"})
@Log4j
public class MyBatisTest {
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		log.info("==========sqlFactory TEST==========");
		log.info(sqlFactory);
	}
	
	@Test
	public void testSession()throws Exception {
		/* 실제 데이터베이스와의 연결을 담당하는 객체인 SqlSession을 생성 */
		try(SqlSession session = sqlFactory.openSession()){
			log.info("=========testSession Test ======");
			log.info(session);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
