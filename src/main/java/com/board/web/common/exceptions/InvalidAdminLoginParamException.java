package com.board.web.common.exceptions;

public class InvalidAdminLoginParamException extends Exception {

	private static final long serialVersionUID = 6537067081003280952L;
	
	private String fieldName;
	
	public InvalidAdminLoginParamException(String message) {
		super(message);
	}
	
	public InvalidAdminLoginParamException(String fieldName, String message) {
		super(message);
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}

}
