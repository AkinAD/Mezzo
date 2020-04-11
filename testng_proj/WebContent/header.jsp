<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
<!-- <title>Insert title here</title>  -->
</head>
<body>
<!-- ##### Header Area Start ##### -->
	<header class="header_area">
		<div
			class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
			<!-- Classy Menu -->
			<nav class="classy-navbar" id="essenceNav">
				<!-- Logo -->
				<a class="nav-brand" href="Home.jsp"><img
					src="${pageContext.request.contextPath}/res/img/core-img/logo.png" alt=""/></a>
				<!-- Navbar Toggler -->
				<div class="classy-navbar-toggler">
					<span class="navbarToggler"><span><!-- content --></span><span><!-- content --></span><span><!-- content --></span></span>
				</div>
				<!-- Menu -->
				<div class="classy-menu">
					<!-- close btn -->
					<div class="classycloseIcon">
						<div class="cross-wrap">
							<span class="top"><!-- content --></span><span class="bottom"><!-- content --></span>
						</div>
					</div>
					<!-- Nav Start -->
					<div class="classynav">
						<ul>
							<li><a href="#">Shop</a>
								<div class="megamenu">
									<ul class="single-mega cn-col-4">
										<li class="title">Women's Collection</li>
										<li><a href="shop.html">Dresses</a></li>
										<li><a href="shop.html">Blouses &amp; Shirts</a></li>
										<li><a href="shop.html">T-shirts</a></li>
										<li><a href="shop.html">Rompers</a></li>
										<li><a href="shop.html">Bras &amp; Panties</a></li>
									</ul>
									<ul class="single-mega cn-col-4">
										<li class="title">Men's Collection</li>
										<li><a href="shop.html">T-Shirts</a></li>
										<li><a href="shop.html">Polo</a></li>
										<li><a href="shop.html">Shirts</a></li>
										<li><a href="shop.html">Jackets</a></li>
										<li><a href="shop.html">Trench</a></li>
									</ul>
									<ul class="single-mega cn-col-4">
										<li class="title">Kid's Collection</li>
										<li><a href="shop.html">Dresses</a></li>
										<li><a href="shop.html">Shirts</a></li>
										<li><a href="shop.html">T-shirts</a></li>
										<li><a href="shop.html">Jackets</a></li>
										<li><a href="shop.html">Trench</a></li>
									</ul>
									<div class="single-mega cn-col-4">
										<img src="${pageContext.request.contextPath}/res/img/bg-img/bg-6.jpg" alt=""/>
									</div>
								</div></li>
							<li><a href="#">Pages</a>
								<ul class="dropdown">
									<li><a href="index.html">Home</a></li>
									<li><a href="shop.html">Shop</a></li>
									<li><a href="single-product-details.html">Product
											Details</a></li>
									<li><a href="payment.jsp">Checkout</a></li>
									<li><a href="blog.html">Blog</a></li>
									<li><a href="single-blog.html">Single Blog</a></li>
									<li><a href="regular-page.html">Regular Page</a></li>
									<li><a href="contact.html">Contact</a></li>
								</ul></li>
							<li><a href="blog.html">Blog</a></li>
							<li><a href="contact.html">Contact</a></li>
						</ul>
					</div>
					<!-- Nav End -->
				</div>
			</nav>

			<!-- Header Meta Data -->
			<div class="header-meta d-flex clearfix justify-content-end">
				<!-- Search Area -->
				<div class="search-area">
					<form action="#" method="post">
						<input type="search" name="search" id="headerSearch"
							placeholder="Type for search"/>
						<button type="submit">
							<i class="fa fa-search" aria-hidden="true"><!--  Some content -->.</i>
						</button>
					</form>
				</div>
				<!-- Favourite Area -->
				<div class="favourite-area">
					<a href="#"><img src="${pageContext.request.contextPath}/res/img/core-img/heart.svg" alt=""/><!-- content --></a>
				</div>
				<!-- User Login Info -->
				<!--                <div class="classynav">-->
				<div class="user-login-info ">
					<!--       choose block right here to either display current user login or redirect to acoount page                 -->
					<a href="login"><img src="${pageContext.request.contextPath}/res/img/core-img/user.svg" alt=""/><!-- content --></a>
				</div>
				<!--                </div>-->
				<!-- Cart Area -->
				<div class="cart-area">
					<a href="#" id="essenceCartBtn"><img src="${pageContext.request.contextPath}/res/img/core-img/bag.svg"
						alt=""/><span>3</span><!-- content --></a>
				</div>
			</div>

		</div>
	</header>
	<!-- ##### Header Area End ##### -->
	
	
	<!-- ##### START Cart area  ##### -->
	<jsp:include page="cart.jsp" />
    <!-- ##### END  Cart area ##### -->

</body>
</html>
</jsp:root>