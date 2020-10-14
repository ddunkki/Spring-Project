package com.board.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.board.web.admin.service.AdminService;
import com.board.web.admin.vo.AdminVo;
import com.board.web.common.ajax.AjaxCode;
import com.board.web.common.ajax.AjaxResult;
import com.board.web.common.util.RequestHelper;
import com.board.web.common.util.SessionUtil;

/**
 * 어드민 세션 체크.
 * 
 * @author Min Chang Jang (mcjang@ex2i.com)
 * @create 2019.10.10
 */
public class AdminLoginCheckInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AdminLoginCheckInterceptor.class);
	
	@Value("${session.login.key}")
	private String loginSessionKey;
	
	@Autowired
	private AdminService adminService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		if(logger.isDebugEnabled()) {
			logger.debug("=======AdminLoginCheckInterceptor===========start====================");
		}
		
		AdminVo admin = (AdminVo) SessionUtil.getAttribute(loginSessionKey);
		
		// FIXME 로그인 된 것 처럼 꾸밈. 삭제
		// 1	15	admin	관리자	webmaster@pulmuoneha.co.kr
//		AdminVo admin = adminService.findOneAdmin(1);
//		SessionUtil.setAttribute(loginSessionKey, admin);
		
		if ( admin == null ) {
			if ( RequestHelper.isAjax() ) {
				// 미로그인 상태
				AjaxResult result = AjaxResult.fail(AjaxCode.ResultCode.SESSION_EXPIRED, "로그인이 필요합니다.");
				PrintWriter writer = response.getWriter();
				writer.write(new Gson().toJson(result));
				writer.flush();
				writer.close();
				return false;
			}
			
			response.sendRedirect(RequestHelper.getContextPath() + "/login?_token_=" + SessionUtil.getAttribute("_token_") );
			return false;
			
		}
		
		return true;
	}
}
