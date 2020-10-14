package com.board.web.common.menu;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * 어드민 공통메뉴 정보 얻어옴.
 * 
 * @author Min Chang Jang (mcjang@ex2i.com)
 * @create 2019.10.10
 */
@Data
@XmlRootElement(name="subMenu")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SubMenu {

	private List<Menu> menu;
	
}
