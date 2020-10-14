package com.board.web.common.menu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * 어드민 공통메뉴 정보 얻어옴.
 */
@Data
@XmlRootElement(name="menu")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Menu {

	private String name;
	private String url;
	private String id;
	private SubMenu subMenu;
	private Permission permission;
	
}
