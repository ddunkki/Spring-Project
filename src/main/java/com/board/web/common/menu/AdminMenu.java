package com.board.web.common.menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.web.admin.vo.AdminMenuVo;
import com.board.web.common.util.CollectionUtil;
import com.board.web.common.util.SessionUtil;
import com.board.web.common.util.StringUtils;

import lombok.Data;

/**
 * 어드민 공통메뉴 정보 얻어옴.
 * 
 */
@Data 
@XmlRootElement(name="adminMenu")  
@XmlAccessorType(XmlAccessType.PROPERTY) 
public class AdminMenu { 

	private static Logger logger = LoggerFactory.getLogger(AdminMenu.class);
	
	private List<Menu> menu;
	
	public SubMenu getSubMenu(String requestUri) {
		
		for ( Menu menu : this.menu ) {
			if ( StringUtils.isNotEmpty(menu.getUrl()) && requestUri.startsWith(menu.getUrl()) ) {
				return menu.getSubMenu(); 
			}
		}
		
		return null; 
	}
	
	public static AdminMenu getAdminMenu() {
		
		InputStream in = AdminMenu.class.getResourceAsStream("/props/menu.xml");
		InputStreamReader reader = new InputStreamReader(in);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AdminMenu.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (AdminMenu) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			return null;
		}finally {
			if ( reader != null ) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if ( in != null ) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	public static String getFirstMenu(List<AdminMenuVo> accessMenuList) {
		
		AdminMenu adminMenu = AdminMenu.getAdminMenu();
		
		//removeDeniedPermissionMenu(adminMenu.getMenu(), accessMenuList, true);
		
		String url = "";
		
		if ( CollectionUtil.isNotEmpty(adminMenu.getMenu()) ) {
			Menu menu = adminMenu.getMenu().get(0);
			if ( menu.getSubMenu() != null && CollectionUtil.isNotEmpty(menu.getSubMenu().getMenu()) ) {
				if( menu.getSubMenu().getMenu().get(0).getSubMenu() != null ) {
					url = menu.getSubMenu().getMenu().get(0).getSubMenu().getMenu().get(0).getUrl();
				} else {
					url = menu.getSubMenu().getMenu().get(0).getUrl();
				}
			}
		} 
		
		return url + "?_token_=" + SessionUtil.getAttribute("_token_");
	}
	
	
	/**
	 * 권한이 없는 메뉴는 제거함.
	 * @param menuList 메뉴 목록
	 * @param accessMenuList 세션아이디의 메뉴접근권한 목록
	 * @param 하위의 하위메뉴 권한 체크
	 */

	public static void removeDeniedPermissionMenu(List<Menu> menuList, String permission) {
		boolean isGrantedPermission = false;
		Menu menu = null;
		for ( int i = menuList.size()-1; i >=0; i-- ) {
				
			isGrantedPermission = false;
				
			menu = menuList.get(i);
			
			
			for ( String permissions : menu.getPermission().getPermission() ) {
				if ( permission.equalsIgnoreCase(permissions) ) {
					isGrantedPermission = true;
					break;
				}
			}
			// 권한 없는 메뉴 삭제
			if ( !isGrantedPermission ) {
				menuList.remove(i);
			}
			// 하위 메뉴가 있을 경우 검사.
			else if ( menu.getSubMenu() != null && CollectionUtil.isNotEmpty(menu.getSubMenu().getMenu())) {
				removeDeniedPermissionMenu(menu.getSubMenu().getMenu(), permission); 
			}
		}
	}
}
