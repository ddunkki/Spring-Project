package com.board.web.common.menu; 

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="permission")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Permission {

	private List<String> permission;
	
}
