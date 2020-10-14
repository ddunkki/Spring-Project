package com.board.web.common.ajax;

import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.board.web.common.ajax.AjaxCode.ResultCode;

public class AjaxResult {
	
	@JsonProperty("RESULT_ST")
	@SerializedName("RESULT_ST")
	private String resultStatus;

	@JsonProperty("RESULT_CODE")
	@SerializedName("RESULT_CODE")
	private String resultCode;

	@JsonProperty("RESULT_CNT")
	@SerializedName("RESULT_CNT")
	private int count;

	@JsonProperty("RESULT_MSG")
	@SerializedName("RESULT_MSG")
	private Object message;
	
	@JsonProperty("FIELD_NAME")
	@SerializedName("FIELD_NAME")
	private String fieldName;
	
	@JsonProperty("REDIRECT_URL")
	@SerializedName("REDIRECT_URL")
	private String redirectUrl;
	
	@JsonProperty("POPUP_URL")
	@SerializedName("POPUP_URL")
	private String popupUrl;
	
	@JsonProperty("POPUP_NAME")
	@SerializedName("POPUP_NAME")
	private String popupName;

	public AjaxResult() {}
	
	public boolean isOk() {
		return resultCode.equals(AjaxCode.ResultCode.OK.value);
	}
	
	/**
	 * Ajax 성공  결과 전달
	 * @param message 전달 메시지
	 * @return
	 */
	public static AjaxResult pass(Object message) {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.PASS.value;
		result.resultCode = AjaxCode.ResultCode.OK.value;
		result.setMessage(message);
		
		return result;
	}
	
	/**
	 * Ajax 실패  결과 전달
	 * @param resultCode null 일 경우 200으로 전달됨.<br/>
	 * AjaxCode.ResultCode.SESSION_EXPIRED, AjaxCode.ResultCode.INVALID_AUTHORITY, AjaxCode.ResultCode.INVALID_PARAM
	 * @param message 전달 메시지
	 * @return
	 */
	public static AjaxResult fail(AjaxCode.ResultCode resultCode, Object message) {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.FAIL.value;
		result.resultCode = resultCode == null ? ResultCode.OK.value : resultCode.value;
		result.setMessage(message);
		
		return result;
	}
	
	/**
	 * Ajax 성공 팝업 결과 전달
	 * @param popupUrl open 하려는 popupUrl
	 * @param popupName open 하려는 popup의 이름
	 * @param message 전달 메시지
	 * @return
	 */
	public static AjaxResult popup(String popupUrl, String popupName, Object message) {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.POPUP.value;
		result.resultCode = ResultCode.OK.value;
		result.popupUrl = popupUrl;
		result.popupName = popupName;
		result.setMessage(message);
		
		return result;
	}
	
	/**
	 * Ajax 성공. 화면 새로고침
	 * @return
	 */
	public static AjaxResult refresh() {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.REFRESH.value;
		result.resultCode = ResultCode.OK.value;
		result.setMessage("");
		
		return result;
	}
	
	/**
	 * Ajax 성공. 화면 새로고침
	 * @param message
	 * @return
	 */
	public static AjaxResult refresh(String message) {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.REFRESH.value;
		result.resultCode = ResultCode.OK.value;
		result.setMessage(message);
		
		return result;
	}
	
	/**
	 * Ajax 성공, Ajax 결과 전달 이후 Redirect 필요 할 경우 사용.
	 * @param redirectUrl 이동할 URL
	 * @return
	 */
	public static AjaxResult redirect(String redirectUrl) {
		return AjaxResult.redirect(null, redirectUrl);
	}
	
	/**
	 * Ajax 성공, Ajax 결과 전달 이후 Redirect 필요 할 경우 사용.
	 * @param message 전달 메시지
	 * @param redirectUrl 이동할 URL
	 * @return
	 */
	public static AjaxResult redirect(Object message, String redirectUrl) {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.REDIRECT.value;
		result.resultCode = AjaxCode.ResultCode.OK.value;
		result.redirectUrl = redirectUrl;
		result.setMessage(message);
		
		return result;
	}
	
	/**
	 * Ajax 실패. Validation 검증 실패시 사용.
	 * @param resultCode AjaxCode.ResultCode.SESSION_EXPIRED, AjaxCode.ResultCode.INVALID_AUTHORITY, AjaxCode.ResultCode.INVALID_PARAM
	 * @param fieldName
	 * @param message
	 * @return
	 */
	public static AjaxResult validationFail(AjaxCode.ResultCode resultCode, String fieldName, Object message) {
		AjaxResult result = new AjaxResult();
		result.resultStatus = AjaxCode.StatusCode.FAIL.value;
		result.resultCode = resultCode.value;
		result.fieldName = fieldName;
		result.setMessage(message);
		
		return result;
	}
	
	private void setMessage(Object message) {
		
		if ( message == null ) {
			this.count = 0;
			this.message = "";
			return;
		}
		
		if ( message instanceof Collection ) {
			this.count = ((Collection<?>) message).size();
		}
		else if ( message instanceof Map ) {
			this.count = ((Map<?, ?>) message).size();
		}
		else {
			this.count = 1;
		}
		
		this.message = message;
	}
	
	public String getResultStatus() {
		return resultStatus;
	}

	public String getResultCode() {
		return resultCode;
	}

	public int getCount() {
		return count;
	}

	public Object getMessage() {
		return message;
	}
	
	public String getFieldName() {
		return fieldName;
	}

}
