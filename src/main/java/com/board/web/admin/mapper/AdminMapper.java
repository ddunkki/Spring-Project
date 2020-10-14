package com.board.web.admin.mapper;

import com.board.web.admin.vo.AdminVo;

public interface AdminMapper {
	
	/**
	 * 로그인을 위한 관리자 조회
	 * @param adminId
	 * @return
	 */
	public AdminVo findOneAdminForLogin(String adminId);
}
