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
<meta charset="UTF-8" />
<meta name="description" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Payment - Mezzo</title>

<!-- Favicon  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico" />

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/core-style.css" />
<!--     <link rel="stylesheet" href="style.css"/> -->
</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<jsp:include page="header.jsp" />
	<!-- ##### Header Area End ##### -->


	<!-- ##### Breadcumb Area Start ##### -->
	<div class="breadcumb_area bg-img"
		style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="page-title text-center">
						<h2>Checkout</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Breadcumb Area End ##### -->

	<!-- ##### Checkout Area Start ##### -->
	<div class="checkout_area section-padding-80">
		<div class="container">
			<div class="row">

				<div class="col-12 col-md-6">
					<div class="checkout_details_area mt-50 clearfix">

						<div class="cart-page-heading mb-30">
							<h5>Shipping Address</h5>
						</div>

						<form action="pay" method="post" id="payForm">
				
							<div class="col-12 mb-3">
								<label for="street_address">Street Address + City <span>*</span></label>
								<input name="street" type="text" class="form-control mb-3"
									id="street_address" value=""
									placeholder="4700 Keele Street, Toronto" required="required" />
							</div>


							<div class="col-12 mb-3">
								<label for="country">Country <span>*</span></label> <input
									name="street" type="text" class="form-control mb-3"
									id="street_address" value="" placeholder="Jamaica"
									required="required" />
							</div>

							<div class="col-12 mb-3">
								<label for="state">Province <span>*</span></label> <input
									name="province" type="text" class="form-control" id="state"
									placeholder="ON" required="required" />
							</div>
							<div class="col-12 mb-3">
								<label for="postcode">Postcode <span>*</span></label> <input
									name="postal" type="text" class="form-control" id="postcode"
									placeholder="M3J1P3" required="required" />
							</div>

							<div class="col-12 mb-3">
								<label for="phone_number">Phone No <span>*</span></label> <input
									name="phone" type="number" class="form-control"
									id="phone_number" min="0" value="" placeholder="416 736-2100"
									required="required" />
							</div>

							<div class="col-12 mb-3">
								<label for="street_address">Cardholder Name <span>*</span></label>
								<input name="cardName" type="text" class="form-control mb-3"
									id="street_address" value="" placeholder="John More Doe"
									required="required" />
							</div>

							<div class="col-12 mb-3">
								<label for="street_address">Card Number <span>*</span></label> <input
									name="cardNo" type="number" class="form-control mb-3"
									id="street_address" value="" placeholder="1111-2222-3333-4444"
									required="required" />
							</div>
							<div class="col-12 mb-3">
								<label for="street_address">CVV <span>*</span></label> <input
									name="cardPass" type="number" max="999"
									class="form-control mb-3 small" id="street_address" value=""
									placeholder="259" required="required" />
							</div>
							<div class="col-12 mb-3">
								<label for="street_address">Exp Month <span>*</span></label> <input
									name="cardExpMo" type="number" min="1" max="12"
									class="form-control mb-3 small" id="street_address" value=""
									placeholder="06" required="required" />
							</div>
							<div class="col-12 mb-3">
								<label for="street_address">Exp Year <span>*</span></label> <input
									name="cardExpYe" type="number" class="form-control mb-3 small"
									id="street_address" value="" placeholder="2020"
									required="required" />
							</div>

							<div class="col-12">
								<div class="custom-control custom-checkbox d-block mb-2">
									<input type="checkbox" class="custom-control-input"
										id="customCheck1" required="required" /> <label
										class="custom-control-label" for="customCheck1">Terms
										and conitions</label>
								</div>
							</div>
						</form>

					</div>
				</div>

				<div class="col-12 col-md-6 col-lg-5 ml-lg-auto">
					<div class="order-details-confirmation">

						<div class="cart-page-heading">
							<h5>Your Order</h5>
							<p>Give us your money please</p>
						</div>

						<ul class="order-details-form mb-4">
							<li><span>Product</span> <span>Total</span></li>
							<c:forEach var="current" items="${requestScope.cartItems}">
								
								<li><span>${current.item.title} <span style="color:#ff084e;"> x${current.qty}</span> </span>
									<span><f:setLocale value="en_CA" />
										<f:formatNumber value="${current.effectivePrice}" type="currency" />
									</span>
								</li>
								
							</c:forEach>
							<li><span>Subtotal</span>
								<span><f:setLocale value="en_CA" />
									<f:formatNumber value="${requestScope.cartTotal}" type="currency" />
								</span>
							</li>
							<li><span>Shipping</span> <span>Free</span></li>
							<li><span>Total</span> 
								<span><f:setLocale value="en_CA" />
										<f:formatNumber value="${requestScope.cartTotal}" type="currency" />
								</span>
							</li>
						</ul>
						<c:choose>
							<c:when test="${requestScope.cartTotalQty ge 1}">
								<button form="payForm" class="btn essence-btn">Place Order</button>								
							</c:when>
							<c:otherwise>
								<a href="shop" class="btn essence-btn">Continue shopping</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Checkout Area End ##### -->

	<!-- ##### Footer Area Start ##### -->
	<jsp:include page="footer.jsp" />
	<!-- ##### Footer Area End ##### -->

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script
		src="${pageContext.request.contextPath}/res/js/jquery/jquery-2.2.4.min.js">
		;
	</script>
	<!-- Popper js -->
	<script src="${pageContext.request.contextPath}/res/js/popper.min.js">
		;
	</script>
	<!-- Bootstrap js -->
	<script
		src="${pageContext.request.contextPath}/res/js/bootstrap.min.js">
		;
	</script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/res/js/plugins.js">
		;
	</script>
	<!-- Classy Nav js -->
	<script
		src="${pageContext.request.contextPath}/res/js/classy-nav.min.js">
		;
	</script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/res/js/active.js">
		;
	</script>

</body>
	</html>
</jsp:root>