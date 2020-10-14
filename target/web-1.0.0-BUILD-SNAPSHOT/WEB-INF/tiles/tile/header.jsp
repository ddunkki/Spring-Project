
<body>
	<a href="/logout">로그아웃</a>
	<h2>Tiles:header</h2>
	
	<ul class="gnb-wrap">
		<c:if test="${not empty menu}">
			<c:forEach items="${menu}" var="headerMenu">
				<c:if test="${not empty headerMenu.url and requestUri.startsWith(headerMenu.url)}">
					<c:set var="headerTitle" value="${headerMenu.name}"/>
				</c:if>
				<li ${not empty headerMenu.url and requestUri.startsWith(headerMenu.url) ? "class='is-active'" : "" }>
					<a href="<c:url value='${headerMenu.url}' />">${headerMenu.name}</a>
					<%-- <c:if test="${empty headerMenu.subMenu.menu[0].subMenu}">
						<a href="<c:url value='${headerMenu.subMenu.menu[0].url}' />">${headerMenu.name}</a>
					</c:if>
					<c:if test="${not empty headerMenu.subMenu.menu[0].subMenu}">
						<a href="<c:url value='${headerMenu.subMenu.menu[0].subMenu.menu[0].url}' />">${headerMenu.name}</a>
					</c:if> --%>
				</li>
			</c:forEach>
		</c:if>
	</ul>
</body>
