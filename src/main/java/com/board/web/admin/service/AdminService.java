package com.board.web.admin.service;

import com.board.web.admin.vo.AdminVo;
import com.board.web.common.exceptions.InvalidAdminLoginParamException;

public interface AdminService {
	
	/**
	 * 관리자 한명 조회. 로그인 할 때 사용.
	 * @param adminVo
	 * @return
	 * @throws InvalidAdminLoginParamException 아이디 또는 비밀번호 틀렸을 때 발생.
	 */
	public AdminVo findOneAdminForLogin(AdminVo adminVo) throws InvalidAdminLoginParamException;

}
