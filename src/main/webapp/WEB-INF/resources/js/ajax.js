var post = function(option, callback, failCallback) {
	
	if ( option.duplicate == undefined ) {
		// false : 중복 방지해야 함.
		// true : 중복 허용 해야 함.
		option.duplicate = false;
	}
	
	if ( !option.param ) {
		option.param = {};
	}
	
	$.post(option.url, option.param, function ( response ) {
		
		try {
			response = JSON.parse(response);
		}
		catch(e) {}
		
		if ( response.RESULT_ST == "PASS" ) {
			callback(response);
		}
		else if ( response.RESULT_ST == "REDIRECT" ) {
			callback(response);
			location.href = response.REDIRECT_URL;
		}
		else if ( response.RESULT_ST == "REFRESH" ) {
			callback(response);
			location.reload();
		}
		else if ( response.RESULT_ST == "DUPLICATE" ) {
			callback = function() {};
			callback();
		}
		else if ( response.RESULT_ST == "POPUP" ) {
			callback(response);
			
			$("#popupDialogContent").load(response.POPUP_URL, function() {
				$(".ui-dialog-title").text(response.POPUP_NAME)
				$('.popup').dialog('open');
			});
		}
		else if ( response.RESULT_ST == "FAIL" ) {
			failProcess(response, failCallback);
		}
	});
	
};


function failProcess(response, failCallback) {
	// 세션 만료
	if ( response.RESULT_CODE == "9999" ) {
		location.href = "/login";
	}
	// 인증정보 틀림
	else if ( response.RESULT_CODE == "9998" ) {
		if ( failCallback ) {
			failCallback(response);
		}
		else {
			alert(response.RESULT_MSG);
		}
	}
	// 접근 권한 없음
	else if ( response.RESULT_CODE == "9997" ) {
		if ( failCallback ) {
			failCallback(response);
		}
		else {
			alert(response.RESULT_MSG);
		}
	}
	// 필수 입력 파라미터 미전달
	else if ( response.RESULT_CODE == "1000" ) {
		if ( failCallback ) {
			failCallback(response);
		}
		else {
			alert("RESULT_CODE = " + response.RESULT_CODE);
			alert(response.RESULT_MSG);
		}
		$("#" + response.FIELD_NAME).focus();
	}
	// 패스워드 만료됨
	else if ( response.RESULT_CODE == "2000" ) {
		if ( failCallback ) {
			failCallback(response);
		}
		else {
			alert(response.RESULT_MSG);
		}
	}
	// 계정 잠김
	else if ( response.RESULT_CODE == "2001" ) {
		if ( failCallback ) {
			failCallback(response);
		}
		else {
			alert(response.RESULT_MSG);
		}
	}
	// 시스템 에러
	else if ( response.RESULT_CODE == "-9999" ) {
		alert(response.RESULT_MSG);
	}
	// 그 외
	else {
		if ( failCallback ) {
			failCallback(response);
		}
		else {
			alert(response.RESULT_MSG);
		}
	}
}