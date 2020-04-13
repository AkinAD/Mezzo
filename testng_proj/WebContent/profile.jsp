<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:f="http://java.sun.com/jsp/jstl/fmt" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Your Account</title>
<!-- Favicon  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico" />


<!-- leave the ";" from within the script tags or page will not load -->
<link rel="StyleSheet"
	href="${pageContext.request.contextPath}/res/css/profile.css"
	type="text/css" title="profileStyle" media="screen, print" />

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/core-style.css" />


</head>
<body>
	<!-- ##### START header area  ##### -->
	<jsp:include page="header.jsp" />

	<div class="shadow overflow">

		<div id="header">
			<a href="home" class="description rturn"> <i
				class="material-icons"
				style="text-align: center; position: relative; top: 0.3rem;">keyboard_backspace</i>To
				Store
			</a> <SPAN></SPAN>
		</div>
		<div id="profile">
			<div class="image">
				<img
					src="https://i.pinimg.com/564x/d8/69/66/d86966ce8d5c7e12cfc40b18da788d1b.jpg"
					alt="" />
				<!-- https://i.pinimg.com/originals/4d/81/0b/4d810b426c6fc385df7cdeb53ae69f93.jpg  -->
			</div>
			<c:forEach var="current" items="${profile}">
				<div class="name">
					<c:out value="${current.key}" />
				</div>
				<div class="nickname">
					Username:
					<c:out value="${current.value[0]}" />
				</div>
				<div class="location"></div>
				<div class="bottom">
					<div class="cust">
						<i class="material-icons">person</i> <span class="user"><c:out
								value="${current.value[4]}" /> </span>
					</div>
					<div class="cust">
						<i class="material-icons">email</i> <span class="email"><c:out
								value="${current.value[3]}" /> </span>
					</div>
				</div>
			</c:forEach>
			<a href="logout" class="btn essence-btn">
				<i class="material-icons"
					style="font-size: 1rem; position: relative; top: 0.2rem;">logout</i>
				Log out
			</a>

		</div>


	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/res/js/profile.js">	;</script>
	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script
		src="${pageContext.request.contextPath}/res/js/jquery/jquery-2.2.4.min.js">
		;
	</script>
	<!-- Popper js -->
	<script src="${pageContext.request.contextPath}/res/js/popper.min.js">;</script>
	<!-- Bootstrap js -->
	<script
		src="${pageContext.request.contextPath}/res/js/bootstrap.min.js">;</script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/res/js/plugins.js">;</script>
	<!-- Classy Nav js -->
	<script
		src="${pageContext.request.contextPath}/res/js/classy-nav.min.js">;</script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/res/js/active.js">;</script>

</body>

	</html>
</jsp:root>