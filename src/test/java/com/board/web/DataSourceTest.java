package com.board.web;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/config/context-root.xml"})
@Log4j
public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	
	@Test
	public void testConection() throws Exception{
		try(Connection con = ds.getConnection()){
			log.info("=========Connection Test ======");
			log.info(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
