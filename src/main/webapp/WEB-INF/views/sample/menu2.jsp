<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$().ready(function() {
	
		$("#callback").click(function(){
		   callbackTest(function(req){
			   alert(req);
		   });
		});
	
	});
	var callbackTest = function(callback){
	   callback("콜백");
	}
</script>

<body>
  <h2>MENU2 페이지</h2>
</body>

<button type="button" id="callback">콜백</button>
