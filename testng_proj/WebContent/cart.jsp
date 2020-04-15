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
<title>Insert title here</title>
<meta charset="UTF-8" />
<meta name="description" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/styles_other.css" />
</head>
<body>
	<!-- ##### Right Side Cart Area ##### -->
	<div class="cart-bg-overlay"></div>

	<div class="right-side-cart-area">

		<!-- Cart Button --> <!-- doSimpleAjax('/OsapCalc-v4/Osap/Ajax/');return false; -->
		<div class="cart-button">
			<a href="" id="rightSideCart"><img
				src="${pageContext.request.contextPath}/res/img/core-img/bag.svg"
				alt="" /><span>${cartCount}</span></a>
		</div>
		

		<div class="cart-content d-flex">

			<!-- Cart List Area -->
			<div class="cart-list">
				<c:forEach var="current" items="${cartItems}">
					<!-- Single Cart Item -->
				<div class="single-cart-item">
					<a href="${pageContext.request.contextPath}/ProductPage?aid=${current.value[0]}" class="product-image"> <!-- appende each aid here  -->
					<c:url var="myurl" value="${current.value[5]}" />
					<img src="${myurl}" class="cart-thumb" alt="" /> <!-- Cart Item Desc -->
						<div class="cart-item-desc">
							<span class="product-remove"><i class="fa fa-close"
								aria-hidden="true"> <!--  Some content -->&#160; 
							</i></span><span class="badge">${current.value[1]}</span>
							<a 	href="${pageContext.request.contextPath}/ProductPage?aid=${current.value[0]}">
							<h6>${current.value[2]}</h6>
							</a>
							<!-- <p class="color">Color: Red</p> -->
							<p class="price"><f:setLocale value="en_CA"/><f:formatNumber value="${current.value[4]}" type="currency"/></p>
							<p class="size">Quantity</p>
							<form action="" class="number-input">
								<button onclick="this.nextElementSibling.stepDown();">-</button>
								<input type="number" class="cartItemQuantity" min="1" step="1"
									value="${current.value[6]}" />
								<button onclick="this.previousElementSibling.stepUp();"
									class="plus">+</button>
							</form>
						</div>
					</a>
				</div>

				<!-- Single Cart Item -->	
									</c:forEach>
									
				
				
			</div>

			<!-- Cart Summary -->
			<div class="cart-amount-summary">

				<h2>Summary</h2>
				<ul class="summary-table">
					<li><span>subtotal:</span> <span>$274.00</span></li>
					<li><span>delivery:</span> <span>Free</span></li>
					<li><span>discount:</span> <span>-15%</span></li>
					<li><span>total:</span> <span>$232.00</span></li>
				</ul>
				<div class="checkout-btn mt-100">
					<a href="payment.jsp" class="btn essence-btn">check out</a>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Right Side Cart End ##### -->
		<script src="${pageContext.request.contextPath}/res/js/cart.js">;</script>

</body>
	</html>
</jsp:root>