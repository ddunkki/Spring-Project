<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

</script>

<body>
<form id="searchForm">
  <h2>LIST  ${prop}</h2>
  <c:forEach var="data" items="${list }">
  	<div>${data.job }</div>
  </c:forEach>
  <div class="paging">
	${list.getPagination()}
  </div>
  <a href="<c:url value='/list/write' />">등록</a>
</form>
</body>
