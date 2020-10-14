package com.board.web.common.ajax;

public interface AjaxCode {

	public static enum StatusCode {
		
		/**
		 * 성공 상태
		 */
		PASS("PASS"),
		/**
		 * 실패 상태
		 */
		FAIL("FAIL"),
		
		/**
		 * Redirect 상태
		 */
		REDIRECT("REDIRECT"),
		
		/**
		 * 화면 새로고침 상태
		 */
		REFRESH("REFRESH"),
		
		/**
		 * POPUP 상태
		 */
		POPUP("POPUP");
		
		String value;
		
		StatusCode(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
		
	}
	
	
	public static enum ResultCode  {
		
		/**
		 * 세션 만료 됨
		 */
		SESSION_EXPIRED("9999"),
		/**
		 * 인증 잘못 됨
		 */
		INVALID_AUTHORITY("9998"),
		/**
		 * 접근 권한 없음
		 */
		NO_GRANT("9997"),
		/**
		 * 필수 입력 파라미터 미전달/불일치 
		 */
		INVALID_PARAM("1000"),
		/**
		 * 패스워드 만료됨.
		 */
		EXPIRED_PASSWORD("2000"),
		/**
		 * 계정 잠김
		 */
		LOCK_ACCOUNT("2001"),
		/**
		 * 시스템에러
		 */
		SYSTEM_ERROR("-9999"),
		/**
		 * 성공 코드
		 */
		OK("200");
		
		String value;
		
		ResultCode(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
		
	}
	
}
