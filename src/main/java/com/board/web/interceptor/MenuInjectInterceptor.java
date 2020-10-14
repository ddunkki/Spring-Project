package com.board.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.board.web.admin.mapper.AdminMapper;
import com.board.web.admin.vo.AdminMenuVo;
import com.board.web.admin.vo.AdminVo;
import com.board.web.common.menu.AdminMenu;
import com.board.web.common.menu.Menu;
import com.board.web.common.util.CollectionUtil;
import com.board.web.common.util.RequestHelper;
import com.board.web.common.util.SessionUtil;
import com.board.web.common.util.StringUtils;

/**
 * 관리자의 모든 페이지에 메뉴 삽입.
 * 
 * @author Min Chang Jang (mcjang@ex2i.com)
 * @create 2019.10.10
 */
public class MenuInjectInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuInjectInterceptor.class);
	
	private AdminMenu adminMenu;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Value("${session.login.key}")
	private String sessionKey;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("======MenuInjectInterceptor============start====================");
		}
		
		//세션없으면 로그인화면으로
		AdminVo admin = (AdminVo) SessionUtil.getAttribute(sessionKey);
		if ( admin == null ) {
			response.sendRedirect(RequestHelper.getContextPath() + "/login");
			return false;
		}
		
//		List<AdminMenuVo> accessMenuList = null;
		
		//db 접근권한 조회
		//accessMenuList = adminMapper.findOneAdminAccess(admin.getId());
		
		adminMenu = AdminMenu.getAdminMenu();
		
		AdminMenu.removeDeniedPermissionMenu(adminMenu.getMenu(), admin.getPermission());
		
//		if ( CollectionUtil.isEmpty(adminMenu.getMenu()) ) {
//			throw new NoGrantPermissionException("접근 할 수 있는 메뉴가 없습니다.");
//		}
//		if ( CollectionUtil.isEmpty(accessMenuList) ) {
//			throw new NoGrantPermissionException("접근 할 수 있는 메뉴가 없습니다.");
//		}
		
//		String requestUri = request.getRequestURI().replaceFirst(RequestHelper.getContextPath(), "");
//		if ( !requestUri.startsWith("/") ) {
//			requestUri = "/" + requestUri;
//		}
		
		// 1 Depth
//		for ( Menu menu : adminMenu.getMenu() ) {
//			if ( StringUtils.isNotEmpty(menu.getUrl()) && requestUri.startsWith(menu.getUrl()) ) {
//				// 2 Depth
//				if ( menu.getSubMenu() != null && CollectionUtil.isNotEmpty(menu.getSubMenu().getMenu()) ) {
//					for ( Menu depth2 : menu.getSubMenu().getMenu() ) {
//						if ( StringUtils.isNotEmpty(depth2.getUrl()) && requestUri.startsWith(depth2.getUrl()) ) {
//							// 3 Depth
//							if ( depth2.getSubMenu() != null && CollectionUtil.isNotEmpty(depth2.getSubMenu().getMenu()) ) {
//								for ( Menu depth3 : depth2.getSubMenu().getMenu() ) {
//									if ( StringUtils.isNotEmpty(depth3.getUrl()) ) {
//										String[] requestUriArray = requestUri.split("/");
//										String[] depthUriArray = depth3.getUrl().split("/");
//										
//										String depthLastUri = depthUriArray[depthUriArray.length-1];
//										String requestLastUri = requestUriArray[3];
//										
//										if ( depthLastUri.equals(requestLastUri) ) {
//											request.setAttribute("activeUrl", depth3.getUrl());
//											return true;
//										}
//									}
//								}
//							}
//							else {
//								
//								request.setAttribute("activeUrl", depth2.getUrl());
//								return true;
//							}
//						}
//					}
//				}
//			}
//		}
		
//		response.sendRedirect(RequestHelper.getContextPath() + AdminMenu.getFirstMenu(accessMenuList));
		return true;
	}
	
	/**
	 * Controller 의 처리가 종료되면, 실행됨.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if ( modelAndView == null ) {
			return;
		}
		
		modelAndView.addObject("menu", adminMenu.getMenu());
		
		String requestUri = request.getRequestURI().replaceFirst(RequestHelper.getContextPath(), "");
		if ( !requestUri.startsWith("/") ) {
			requestUri = "/" + requestUri;
		}
		
		modelAndView.addObject("requestUri", requestUri);
		modelAndView.addObject("subMenu", adminMenu.getSubMenu(requestUri));
//		
//		List<Menu> path = new ArrayList<>();
//		
//		for ( Menu menu : adminMenu.getMenu() ) {
//			if ( StringUtils.isNotEmpty(menu.getUrl()) && requestUri.startsWith(menu.getUrl()) ) {
//				path.add(menu);
//				
//				if ( menu.getSubMenu() != null && CollectionUtil.isNotEmpty(menu.getSubMenu().getMenu()) ) {
//					for ( Menu depth2 : menu.getSubMenu().getMenu() ) {
//						if ( StringUtils.isNotEmpty(depth2.getUrl()) && requestUri.startsWith(depth2.getUrl()) ) {
//							path.add(depth2);
//							
//							if ( depth2.getSubMenu() != null && CollectionUtil.isNotEmpty(depth2.getSubMenu().getMenu())) {
//								for ( Menu depth3 : depth2.getSubMenu().getMenu() ) {
//									
//									if ( StringUtils.isNotEmpty(depth3.getUrl()) ) {
//										String[] requestUriArray = requestUri.split("/");
//										String[] depthUriArray = depth3.getUrl().split("/");
//										
//										String depthLastUri = depthUriArray[depthUriArray.length-1];
//										String requestLastUri = requestUriArray[3];
//										
//										if ( depthLastUri.equals(requestLastUri) ) {
//											path.add(depth3);
//										}
//										
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		modelAndView.addObject("path", path);
//		
//		adminMenu = AdminMenu.getAdminMenu();
//		modelAndView.addObject("allMenu", adminMenu.getMenu());
		
		
		if(logger.isDebugEnabled()) {
			logger.debug("======MenuInjectInterceptor============end====================");
		}
		
	}
	
}
