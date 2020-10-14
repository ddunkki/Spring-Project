package com.board.web.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.web.admin.mapper.AdminMapper;
import com.board.web.admin.vo.AdminVo;
import com.board.web.common.exceptions.InvalidAdminLoginParamException;

@Service
@Transactional(value = "transactionManager", rollbackFor = RuntimeException.class)
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public AdminVo findOneAdminForLogin(AdminVo adminVo) throws InvalidAdminLoginParamException{
		
		AdminVo oneAdmin = adminMapper.findOneAdminForLogin(adminVo.getId());
		
		if ( oneAdmin == null ) {
			throw new InvalidAdminLoginParamException("id", "아이디를 확인해주세요");
		}
		
		//비밀번호 불일치
//		if( !oneAdmin.getPassword().equalsIgnoreCase(adminVo.getPassword())) {
//			
//		}
		
		return oneAdmin;
	}

}
