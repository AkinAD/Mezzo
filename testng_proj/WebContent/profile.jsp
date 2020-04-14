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
			<!-- <a href="home" class="description rturn"> <i
				class="material-icons"
				style="text-align: center; position: relative; top: 0.3rem;">keyboard_backspace</i>To
				Store
			</a> <SPAN></SPAN> -->
		</div>
		<div id="profile">
			<div class="image">
				<img
					src="https://i.pinimg.com/564x/d8/69/66/d86966ce8d5c7e12cfc40b18da788d1b.jpg"
					alt="" />
				<!-- https://i.pinimg.com/originals/4d/81/0b/4d810b426c6fc385df7cdeb53ae69f93.jpg  -->
			</div>
			<!-- Below is for testing  -->
			<!-- <div class="name">Daft Punk</div>
			<div class="nickname">@daftpunk</div>
			<div class="location">
				<i class="materizal-icons">place</i>Europe
			</div> -->
			<!-- Above is for testing  -->

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

			<h5>
				Billing Details 
				<a href="billing" class="btn essence-btn small-btn"> Update Address</a>
			</h5>
			
			<c:forEach var="current" items="${Billing}">
				<FORM action="">
					<div>
						<div>	
							<label for="street"><h6>Street</h6></label> 
							<input type="text" name="street" value="7538 Old Maple Drive" /> 
						</div>	
						<div>
							<label for="city"><h6>City</h6></label> 
							<input type="text" name="city" value="Bloomfield" /> 
						</div>	
						<div>
							<label for="state-province"><h6>Province</h6></label> 
							<input type="text" name="state-province" value="NJ " /> 
						</div>	
						<div>
							<label for="postal"><h6>Postal Code</h6></label>
							<input type="text" name="postal" value="07003" />
						</div>	
						<div>
							<label for="phone"><h6>Phone Number</h6></label>
							<input type="text" name="phone" value="08076234173" />
						</div>	
			
					</div>
				</FORM>
			<!--  use case -->
					<c:out value="${current.key}" />
					<c:out value="${current.value[0]}" />
					<c:out value="${current.value[1]}" />
					<c:out value="${current.value[2]}" />
					<c:out value="${current.value[3]}" />
					<c:out value="${current.value[4]}" />
			</c:forEach>



			<a href="logout" class="btn essence-btn"> <i
				class="material-icons"
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