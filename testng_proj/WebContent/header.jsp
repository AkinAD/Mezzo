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
<!-- ##### Header Area Start ##### -->
	<header class="header_area">
		<div
			class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
			<!-- Classy Menu -->
			<nav class="classy-navbar" id="essenceNav">
				<!-- Logo -->
				<a class="nav-brand" href="home"><img
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
							<span class="top">&#160; </span><span class="bottom"><!-- content --></span>
						</div>
					</div>
					<!-- Nav Start -->
					<div class="classynav">
						<ul>
							<li><a href="shop">Shop</a>
								<div class="megamenu">
									<ul class="single-mega cn-col-4">
										<li class="title">Top Genres</li>
										<li><a href="shop?category=Hip-hop">Hip-hop</a></li>
										<li><a href="shop?category=Pop">Pop</a></li>
										<li><a href="shop?category=Rap">Rap</a></li>
										<li><a href="shop?category=RnB">RnB</a></li>
										<li><a href="shop?category=Rock">Rock </a></li>
									</ul>
									<ul class="single-mega cn-col-4">
										<li class="title">Favorites</li>
										<li><a href="shop?category=Jazz">Jazz</a></li>
										<li><a href="shop?category=Country">Country</a></li>
										<li><a href="shop?category=Blues">Blues</a></li>
										<li><a href="shop?category=Folk">Folk</a></li>
										<li><a href="shop?category=Gospel" >Gospel</a></li>
									</ul>
									<ul class="single-mega cn-col-4">
										<li class="title">Others</li>
										<li><a href="shop?category=Funk">Funk</a></li>
										<li><a href="shop?category=Synth-Pop">Synth-Pop</a></li>
										<li><a href="shop?category=Electro">Electro</a></li>
										<li><a href="shop?category=Indie">Indie</a></li>
										<li><a href="shop?category=Disco">Disco</a></li>
										<li><a href="shop" style="color:blue;">View All</a></li>
									</ul>
									<div class="single-mega cn-col-4">
										<img src="${pageContext.request.contextPath}/res/img/bg-img/bg-6.jpg" alt=""/>
									</div>
								</div></li>
							<li><a href="#">Pages</a>
								<ul class="dropdown">
									<li><a href="home">Home</a></li>
									<li><a href="shop">Shop</a></li>
									<li><a href="checkout">Checkout</a></li>
									<li><a href="mailto:mrspence004@gmail.com?Subject=Hello%20From%20The%20Mezzo%20Store" target="_top">Contact</a></li>
								</ul></li>
							<li><a href="admin">Admin</a></li>
						</ul>
					</div>
					<!-- Nav End -->
				</div>
			</nav>

			<!-- Header Meta Data -->
			<div class="header-meta d-flex clearfix justify-content-end">
				<!-- Search Area -->
				<div class="search-area">
					<form action="shop" method="GET">
						<input type="search" name="search" id="headerSearch"
							placeholder="Type for search"/>
						<button type="submit">
							<i class="fa fa-search" aria-hidden="true"><!--  Some content -->&#160;</i>
						</button>
					</form>
				</div>
				<!-- Favourite Area -->
				<div class="favourite-area">
					<!-- <a href="#"><img src="${pageContext.request.contextPath}/res/img/core-img/heart.svg" alt=""/>&#160;</a>-->
				</div>
				<!-- User Login Info -->
				<!--                <div class="classynav">-->
				<div class="user-login-info ">
					<!--       choose block right here to either display current user login or redirect to acoount page                 -->
					<a href="profile"><img src="${pageContext.request.contextPath}/res/img/core-img/user.svg" alt=""/><!-- content --></a>
				</div>
				<!--                </div>-->
				<!-- Cart Area -->
				<div class="cart-area"><!--ajaxCart('/testng_proj/cart/Ajax');return false;  -->
					<a href="cart" id="essenceCartBtn"><img src="${pageContext.request.contextPath}/res/img/core-img/bag.svg"
						alt=""/><span>${requestScope.cartTotalQty}</span><!-- content --></a>
				</div>
			</div>

		</div>
	</header>
	<!-- ##### Header Area End ##### -->
	
	
	<!-- ##### START Cart area  ##### -->
	<!--<jsp:include page="cart.jsp" />-->
    <!-- ##### END  Cart area ##### -->
</jsp:root>