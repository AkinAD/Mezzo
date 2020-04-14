<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
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

		<!-- Cart Button -->
		<div class="cart-button">
			<a href="#" id="rightSideCart"><img
				src="${pageContext.request.contextPath}/res/img/core-img/bag.svg"
				alt="" /><span>3</span></a>
		</div>

		<div class="cart-content d-flex">

			<!-- Cart List Area -->
			<div class="cart-list">
				<!-- Single Cart Item -->
				<div class="single-cart-item">
					<a href="#" class="product-image"> <img
						src="${pageContext.request.contextPath}/res/img/product-img/product-1.jpg"
						class="cart-thumb" alt="" /> <!-- Cart Item Desc -->
						<div class="cart-item-desc">
							<span class="product-remove"><i class="fa fa-close"
								aria-hidden="true"> <!--  Some content -->&#160; 
							</i></span><span class="badge">Mango</span>
							<h6>Button Through Strap Mini Dress</h6>
							<p class="color">Color: Red</p>
							<p class="price">$45.00</p>
							<p class="size">Quantity</p>
							<form action="" class="number-input">
								<button onclick="this.nextElementSibling.stepDown();">-</button>
								<input type="number" class="cartItemQuantity" min="1" step="1"
									value="1" />
								<button onclick="this.previousElementSibling.stepUp();"
									class="plus">+</button>
							</form>
						</div>
					</a>
				</div>

				<!-- Single Cart Item -->
				<div class="single-cart-item">
					<a href="#" class="product-image"> <img
						src="${pageContext.request.contextPath}/res/img/product-img/product-2.jpg"
						class="cart-thumb" alt="" /> <!-- Cart Item Desc -->
						<div class="cart-item-desc">
							<span class="product-remove"><i class="fa fa-close"
								aria-hidden="true"> <!--  Some content -->&#160; 
							</i></span> <span class="badge">Mango</span>
							<h6>Button Through Strap Mini Dress</h6>
							<!-- <p class="size">Size: S</p> -->
							<p class="color">Color: Red</p>
							<p class="price">$45.00</p>
							<p class="size">Quantity</p>
							<form action="" class="number-input">
								<button onclick="this.nextElementSibling.stepDown();">-</button>
								<input type="number" class="cartItemQuantity" min="1" step="1"
									value="1" />
								<button onclick="this.previousElementSibling.stepUp();"
									class="plus">+</button>
							</form>
						</div>
					</a>
				</div>

				<!-- Single Cart Item -->
				<div class="single-cart-item">
					<a href="#" class="product-image"> <img
						src="${pageContext.request.contextPath}/res/img/product-img/product-3.jpg"
						class="cart-thumb" alt="" /> <!-- Cart Item Desc -->
						<div class="cart-item-desc">
							<span class="product-remove"><i class="fa fa-close"
								aria-hidden="true"> <!--  Some content -->&#160; 
							</i></span> <span class="badge">Mango</span>
							<h6>Button Through Strap Mini Dress</h6>
							<!-- <p class="size">Size: S</p> -->
							<p class="color">Color: Red</p>
							<p class="price">$45.00</p>
							<p class="size">Quantity</p>
							<form action="" class="number-input">
								<button onclick="this.nextElementSibling.stepDown(); doAjaxPriceUpdate('some address');">-</button>
								<input type="number" class="cartItemQuantity" min="1" step="1"
									value="1" />
								<button onclick="this.previousElementSibling.stepUp(); doAjaxPriceUpdate('some address');"
									class="plus">+</button>
							</form>
						</div>
					</a>
				</div>
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