package com.board.web.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.admin.service.AdminService;
import com.board.web.admin.vo.AdminVo;
import com.board.web.common.ajax.AjaxResult;
import com.board.web.common.exceptions.InvalidAdminLoginParamException;
import com.board.web.common.ajax.AjaxCode;
import com.board.web.common.util.RequestHelper;
import com.board.web.common.util.SessionUtil;
import com.board.web.common.util.StringUtils;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminSevice;
	
	@Value("${session.login.key}")
	private String sessionKey;
	
	//로그인 화면
	@GetMapping("/login")
	public String viewLoginPage() {
		
		AdminVo adminVo = (AdminVo) SessionUtil.getAttribute(sessionKey);
		
		if ( adminVo != null ) {
			return "redirect:/list";
		}
		
		return "login/login";
	}
	
	//로그인 처리
	@PostMapping("/login")
	@ResponseBody
	public AjaxResult login(@ModelAttribute AdminVo adminVo) {
		
		if ( StringUtils.isEmpty(adminVo.getId()) ) {
			return AjaxResult.validationFail(AjaxCode.ResultCode.INVALID_PARAM, "id", "아이디를 입력해주세요.");
		}
		
		AdminVo loginAdmin = null;
		try {
			loginAdmin = adminSevice.findOneAdminForLogin(adminVo);
			
			SessionUtil.invalidate();
			HttpSession session = RequestHelper.getNewSession();
			session.setAttribute(sessionKey, loginAdmin);
			
		} catch (InvalidAdminLoginParamException e) { //없는 아이디일때
			return AjaxResult.validationFail(AjaxCode.ResultCode.INVALID_PARAM, e.getFieldName(), e.getMessage());
		}
		return AjaxResult.redirect("/list");
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout() {
		SessionUtil.invalidate();
		return "redirect:/login";
	}
	
	
	
}
