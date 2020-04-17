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
<title>Mezzo - #1 Album Store</title>

<!-- Favicon  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico" />

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/core-style.css" />
<!--     <link rel="stylesheet" href="style.css"/> -->
</head>
<body>

	<!-- ##### START header area  ##### -->
	<jsp:include page="header.jsp" />
    <!-- ##### END  header area ##### -->



	<!-- ##### Welcome Area Start ##### -->
	<section class="welcome_area bg-img background-overlay"
		style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/bg-1.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="hero-content">
						<h6>Chart Toppers</h6>
						<h2>Latest Releases</h2>
						<a href="shop" class="btn essence-btn">view collection</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ##### Welcome Area End ##### -->

	<!-- ##### Top Catagory Area Start ##### -->
	<div class="top_catagory_area section-padding-80 clearfix">
		<div class="container">
			<div class="row justify-content-center">
				<!-- Single Catagory -->
				<div class="col-12 col-sm-6 col-md-4">
					<div
						class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
						style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/bg-2.jpg);">
						<div class="catagory-content">
							<a href="shop?category=Hip-hop">Hip Hop</a> <!-- redirect to Search by category -->
						</div>
					</div>
				</div>
				<!-- Single Catagory -->
				<div class="col-12 col-sm-6 col-md-4">
					<div
						class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
						style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/bg-3.jpg);">
						<div class="catagory-content">
							<a href="shop?category=RnB">RnB</a> <!-- redirect to Search by category -->
						</div>
					</div>
				</div>
				<!-- Single Catagory -->
				<div class="col-12 col-sm-6 col-md-4">
					<div class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
						style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/bg-4.jpg);">
						<div class="catagory-content">
							<a href="shop?category=Pop">Pop</a> <!-- redirect to Search by category -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Top Catagory Area End ##### -->

	<!-- ##### CTA Area Start ##### -->
	<div class="cta-area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="cta-content bg-img background-overlay"
						style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/bg-5.jpg);">
						<div class="h-100 d-flex align-items-center justify-content-end">
							<div class="cta--text">
								<h6>This Weeks</h6>
								<h2>Chart Toppers</h2>
								<a href="shop" class="btn essence-btn">Buy Now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### CTA Area End ##### -->

	<!-- ##### New Arrivals Area Start ##### -->
	<section class="new_arrivals_area section-padding-80 clearfix">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-heading text-center">
						<h2>Popular Releases</h2>
					</div>

				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="popular-products-slides owl-carousel">
						<!-- Single Product -->
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<a href="${pageContext.request.contextPath}/ProductPage?aid=1">
							<div class="product-img">
								<img
									src="${pageContext.request.contextPath}/res/img/product-img/product-1.jpg"
									alt="" />
								<!-- Hover Thumb -->
								<img class="hover-img"
									src="${pageContext.request.contextPath}/res/img/product-img/mj_thriller.jpg"
									alt="" />
								<!-- Favourite -->
								<div class="product-favourite">
									<a href="#" class="favme fa fa-heart">
										<!--  Some content -->&#160;
									</a>
								</div>
							</div>
							</a>
							<!-- Product Description -->
							<div class="product-description">
								<span>Micheal Jackson</span> <a href="${pageContext.request.contextPath}/ProductPage?aid=1">
									<h6>Thriller</h6>
								</a>
								<p class="product-price">$47.30</p>

								<!-- Hover Content -->
								<div class="hover-content">
									<!-- Add to Cart -->
									<div class="add-to-cart-btn">
										<a href="?addToCart=1" class="btn essence-btn">Add to Cart</a> <!-- Come back to fix this later -->
									</div>
								</div>
							</div>
						</div>

						<!-- Single Product -->
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<a href="${pageContext.request.contextPath}/ProductPage?aid=2">
							<div class="product-img">
								<img
									src="${pageContext.request.contextPath}/res/img/product-img/product-2.jpg"
									alt="" />
								<!-- Hover Thumb -->
								<img class="hover-img"
									src="${pageContext.request.contextPath}/res/img/product-img/ACDC_BackInBlack.jpg"
									alt="" />
								<!-- Favourite -->
								<div class="product-favourite">
									<a href="#" class="favme fa fa-heart">
										<!--  Some content -->&#160;
									</a>
								</div>
							</div>
							</a>
							<!-- Product Description -->
							<div class="product-description">
								<span>ACDC</span> <a href="${pageContext.request.contextPath}/ProductPage?aid=2">
									<h6>Back In Black</h6>
								</a>
								<p class="product-price">$29.40</p>

								<!-- Hover Content -->
								<div class="hover-content">
									<!-- Add to Cart -->
									<div class="add-to-cart-btn">
										<a href="?addToCart=2" class="btn essence-btn">Add to Cart</a> <!-- Come back to fix this later -->
									</div>
								</div>
							</div>
						</div>

						<!-- Single Product -->
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<a href="${pageContext.request.contextPath}/ProductPage?aid=3">
								<div class="product-img">
								<img
									src="${pageContext.request.contextPath}/res/img/product-img/product-3.jpg"
									alt="" />
								<!-- Hover Thumb -->
								<img class="hover-img"
									src="${pageContext.request.contextPath}/res/img/product-img/bat_out_of_hell.jpg"
									alt="" />
								<!-- Product Badge -->
								<div class="product-badge offer-badge">
									<span>-30%</span>
								</div>

								<!-- Favourite -->
								<div class="product-favourite">
									<a href="#" class="favme fa fa-heart">
										<!--  Some content -->&#160;
									</a>
								</div>
							</div>
							</a>
							<!-- Product Description -->
							<div class="product-description">
								<span>Meat Loaf</span> <a href="${pageContext.request.contextPath}/ProductPage?aid=3">
									<h6>Bat Out Of Hell</h6>
								</a>
								<p class="product-price">
									<span class="old-price">$30.00</span> $21.70
								</p>

								<!-- Hover Content -->
								<div class="hover-content">
									<!-- Add to Cart -->
									<div class="add-to-cart-btn">
										<a href="?addToCart=3" class="btn essence-btn">Add to Cart</a> <!-- Come back to fix this later -->
									</div>
								</div>
							</div>
						</div>

						<!-- Single Product -->
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<a href="${pageContext.request.contextPath}/ProductPage?aid=4">
								<div class="product-img">
								<img
									src="${pageContext.request.contextPath}/res/img/product-img/product-4.jpg"
									alt="" />
								<!-- Hover Thumb -->
								<img class="hover-img"
									src="${pageContext.request.contextPath}/res/img/product-img/Roddy-Ricch-Antisocial.jpg"
									alt="" />

								<!-- Product Badge -->
								<div class="product-badge new-badge">
									<span>New</span>
								</div>

								<!-- Favourite -->
								<div class="product-favourite">
									<a href="#" class="favme fa fa-heart">
										<!--  Some content -->&#160;
									</a>
								</div>
							</div>
							</a>
							<!-- Product Description -->
							<div class="product-description">
								<span>Roddy Ricch</span> <a href="${pageContext.request.contextPath}/ProductPage?aid=4">
									<h6>Antisocial </h6>
								</a>
								<p class="product-price">$15.00</p>

								<!-- Hover Content -->
								<div class="hover-content">
									<!-- Add to Cart -->
									<div class="add-to-cart-btn">
										<a href="?addToCart=4" class="btn essence-btn">Add to Cart</a> <!-- Come back to fix this later -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ##### New Arrivals Area End ##### -->

	<!-- ##### Brands Area Start ##### -->
	<div
		class="brands-area d-flex align-items-center justify-content-between">
		<!-- Brand Logo -->
		<div class="single-brands-logo">
			<img
				src="${pageContext.request.contextPath}/res/img/core-img/brand1.png"
				alt="" />
		</div>
		<!-- Brand Logo -->
		<div class="single-brands-logo">
			<img
				src="${pageContext.request.contextPath}/res/img/core-img/brand2.png"
				alt="" />
		</div>
		<!-- Brand Logo -->
		<div class="single-brands-logo">
			<img
				src="${pageContext.request.contextPath}/res/img/core-img/brand3.png"
				alt="" />
		</div>
		<!-- Brand Logo -->
		<div class="single-brands-logo">
			<img
				src="${pageContext.request.contextPath}/res/img/core-img/brand4.png"
				alt="" />
		</div>
		<!-- Brand Logo -->
		<div class="single-brands-logo">
			<img
				src="${pageContext.request.contextPath}/res/img/core-img/brand5.png"
				alt="" />
		</div>
		<!-- Brand Logo -->
		<div class="single-brands-logo">
			<img
				src="${pageContext.request.contextPath}/res/img/core-img/brand6.png"
				alt="" />
		</div>
	</div>
	<!-- ##### Brands Area End ##### -->

	<!-- ##### Footer Area Start ##### -->
		<jsp:include page="footer.jsp" />
	<!-- ##### Footer Area End ##### -->
	

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
			<script src="${pageContext.request.contextPath}/res/js/cart.js">;</script>
	
</body>
	</html>
</jsp:root>