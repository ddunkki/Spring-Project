<script type="text/javascript">
	/* $("#login").on("click",function(){
		alert("login on");
		return;
	}); */
	
	$().ready(function() {
		$("#login").click(function(){
			
			/* $.ajax({
				url: "/login",
				type: "post",
				data : $("#loginForm").serialize(),
				dataType : 'json',
				success:function(data){
					console.log(data);
					alert("성공");
				},
				error:function(data){
					console.log(data);
					alert("실패");
				}
				
			}); */
			
			post({
				url: "/login",
				param: {
					id: $("#id").val()
				}
			}, function(response) { 
				
			}/* , function(failResponse) {
				console.log(failResponse);
				var message = failResponse.RESULT_MSG;
				if ( message == "아이디를 확인해주세요" ) {
					message = "아이디 또는 비밀번호를 확인해주세요";
				}
				else if ( message == "비밀번호를 확인해주세요" ) {
					message = "아이디 또는 비밀번호를 확인해주세요";
				}
				
				if ( failResponse.RESULT_CODE == "2001" ) {
					showError("id");
					showError("password", message);
				}
				else {
					showError("id");
					showError("password", message);
				} 
			} */);
			
		});
		
		$("#id").keyup(function(e) {
			if ( e.keyCode === 13 ) {
				$("#login").click();
			}
		});
		
	});
</script>

<body>
<form id="loginForm" onSubmit="return false;">
	<div>
		<input type="text" id="id" name="id" placeholder="아이디"/>
	</div>
	<div>
		<button type="button" id="login">로그인</button>
	</div>
</form>
	
</body>
