package com.board.web.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * 문자열과 관련된 유틸
 * @author mcjan
 *
 */
public class StringUtils {

	/**
	 * 공백 제거
	 * NullPointerException 위험으로 따로 분리함.
	 * @param string
	 * @return
	 */
	public static String trim(String string) {
		if ( isEmpty(string) ) {
			return "";
		}
		
		return string.trim();
	}
	
	/**
	 * 문자열이 비어있는지 체크
	 * @param str
	 * @return 비어있을 경우 true
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * 전달된 파라미터 중 하나의 문자열이라도 비어있는지 체크
	 * @param str
	 * @return
	 */
	public static boolean isAnyEmpty(String ... strings) {
		for ( String str : strings ) {
			if ( isEmpty(str) ) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 문자열이 비어있지 않은지 체크
	 * @param str
	 * @return 비어있지 않을 경우 true
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}
	
	/**
	 * null인 문자열을 defaultString으로 변경.
	 * @param str
	 * @param defaultString
	 * @return
	 */
	public static String nullToString(String str, String defaultString) {
		if ( isEmpty(str) ) { 
			return defaultString;
		}
		
		return str;
	}
	
	/**
	 * String을 HTTP URL Encoding
	 * @param str
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getUrlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return URLEncoder.encode(str);
		}
	}
	
	/**
	 * String을 HTTP URL Decoding
	 * @param str
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getUrlDecode(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return URLDecoder.decode(str);
		}
	}
	
	/**
	 * 휴대폰번호 포멧팅
	 * @param phoneNumber 구분자가 없는 휴대폰 번호
	 * @param separate 구분자
	 * @return
	 */
	public static String convertPhoneFormat(String phoneNumber, String separate) {
		if ( StringUtils.isEmpty(phoneNumber) ) {
			return "";
		}
		
		final String format1 = "01012341234";
		final String format2 = "0111231234";
		
		if ( !phoneNumber.contains(separate) ) {
			
			if ( phoneNumber.length() == format1.length() ) {
				return String.format("%s%s%s%s%s", 
							phoneNumber.substring(0, 3)
							, separate
							, phoneNumber.substring(3, 7)
							, separate
							, phoneNumber.substring(7)
						);
			}
			else if ( phoneNumber.length() == format2.length() ) {
				return String.format("%s%s%s%s%s", 
						phoneNumber.substring(0, 3)
						, separate
						, phoneNumber.substring(3, 6)
						, separate
						, phoneNumber.substring(6)
					);
			}
			
		}
		
		return phoneNumber;
	}
	
	/**
	 * 글자의 개수에 맞체 fillString을 채운다.
	 * @param string
	 * @param length
	 * @param fillString
	 * @return
	 */
	public static String fillLeft(String string, int length, String fillString) {
		String result = "";
		
		if ( StringUtils.isEmpty(string) ) {
			string = " ";
		}
		
		if ( string.length() < length ) {
			int fillLength = length - string.length();
			for ( int i = 0; i < fillLength; i++ ) {
				result += fillString;
			}
		}
		
		result += string;
		
		return result;
		
	}
	
	/**
	 * 글자의 개수에 맞체 fillString을 채운다.
	 * @param string
	 * @param length
	 * @param fillString
	 * @return
	 */
	public static String fillRight(String string, int length, String fillString) {
		if ( StringUtils.isEmpty(string) ) {
			string = " ";
		}
		
		String result = string;
		
		if ( result.length() < length ) {
			int fillLength = length - result.length();
			for ( int i = 0; i < fillLength; i++ ) {
				result += fillString;
			}
		}
		
		return result;
	}
	
	/**
	 * 문자열이 동일한지 비교
	 * @param str
	 * @param strings
	 * @return
	 */
	public static boolean equals(String str, String ... strings) {
		for ( String string : strings ) {
			if ( str.trim().equals(string.trim()) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 바이트로 텍스트 잘라냄.
	 * @param string
	 * @param startByte
	 * @param endByte
	 * @return
	 */
	public static String cutString(String string, Integer maxByte, String appendStr) {
		
		try {
			Charset charset = Charset.forName("UTF-8");
			
			if (string == null) return null;
		  	if (string.length() == 0) return string;
		    
			string = string.replace("&lt;", "<").replace("&gt;", ">");
			string = string.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
			
			byte[] byteArray = string.getBytes(charset);
			
			if ( byteArray.length < maxByte ) {
				return string;
			}
			
			// 마지막 줄임말
	        int trailByte = 0;

	        // 줄임말의 바이트 수 계산
	        if ( appendStr != null ) {
	        	trailByte = appendStr.getBytes(charset).length;
	        }
			
	        // 실질적으로 포함되는 최대 바이트 수는 trailByte를 뺀 것이다.
	        maxByte = maxByte - trailByte;
	        
	        int endPos = 0;     // 마지막 바이트 위치
	        int currByte = 0;   // 현재까지 조사한 바이트 수
	        
	        for (int i = 0; i < string.length(); i++) {
	            // 순차적으로 문자들을 가져옴.
	            char ch = string.charAt(i);

	            // 이 문자가 몇 바이트로 구성된 UTF-8 코드인지를 검사하여 currByte에 누적 시킨다.
	            currByte = currByte + availibleByteNum(ch);

	            // 현재까지 조사된 바이트가 maxSize를 넘는다면 이전 단계 까지 누적된 바이트 까지를 유효한 바이트로 간주한다.
	            if (currByte > maxByte) {
	                endPos = currByte - availibleByteNum(ch);
	                break;
	            }
	        }

	        // 원래 문자열을 바이트로 가져와서 유효한 바이트 까지 배열 복사를 한다.
	        byte newStrByte[] = new byte[endPos];

	        System.arraycopy(byteArray, 0, newStrByte, 0, endPos);
	        
	        String newStr = new String(newStrByte, "UTF-8");
	        newStr += appendStr;

	        return newStr;
	        
		} catch (Exception e) {
			return "";
		}
        
	}
	
	 public static int availibleByteNum(char c) {

        int ONE_BYTE_MIN = 0x0000;
        int ONE_BYTE_MAX = 0x007F;

        int TWO_BYTE_MIN = 0x0800;
        int TWO_BYTE_MAX = 0x07FF;

        int THREE_BYTE_MIN = 0x0800;
        int THREE_BYTE_MAX = 0xFFFF;

        int SURROGATE_MIN = 0x10000;
        int SURROGATE_MAX = 0x10FFFF;

        int digit = (int) c;

        if(ONE_BYTE_MIN <= digit && digit <= ONE_BYTE_MAX)          return 1;
        else if(TWO_BYTE_MIN <= digit && digit <= TWO_BYTE_MAX)     return 2;
        else if(THREE_BYTE_MIN <= digit && digit <= THREE_BYTE_MAX) return 3;
        else if(SURROGATE_MIN <= digit && digit <= SURROGATE_MAX)   return 4;

        return -1;
    }
	
}
