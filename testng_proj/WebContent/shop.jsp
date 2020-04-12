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

	<!-- ##### Breadcumb Area Start ##### -->
	<div class="breadcumb_area bg-img"
		style="background-image: url(${pageContext.request.contextPath}/res/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="page-title text-center">
						<h2>ALBUMS</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Breadcumb Area End ##### -->

	<!-- ##### Shop Grid Area Start ##### -->
	<section class="shop_grid_area section-padding-80">
		<div class="container">
			<div class="row">
				<div class="col-12 col-md-4 col-lg-3">
					<div class="shop_sidebar_area">

						<!-- ##### Single Widget ##### -->
						<div class="widget catagory mb-50">
							<!-- Widget Title -->
							<h6 class="widget-title mb-30">Catagories</h6>

							<!--  Catagories  -->
							<div class="catagories-menu">
								<ul id="menu-content2" class="menu-content collapse show">
									<!-- Single Item -->
									<li data-toggle="collapse" data-target="#clothing"><a
										href="#">Popular</a>
										<ul class="sub-menu collapse show" id="clothing">
											<li><a href="shop?category=Hip-hop">Hip-Hop</a></li>
											<li><a href="shop?category=Pop">Pop</a></li>
											<li><a href="shop?category=Rap">Rap</a></li>
											<li><a href="shop?category=RnB">RnB</a></li>
											<li><a href="shop?category=Rock">Rock </a></li>
											<li><a href="shop?category=Jazz">Jazz</a></li>
											<li><a href="shop?category=Country">Country</a></li>
											<li><a href="shop?category=Blues">Blues</a></li>
											<li><a href="shop?category=Folk">Folk</a></li>
											<li><a href="shop?category=Gospel">Gospel</a></li>
										</ul></li>
									<!-- Single Item -->
									<li data-toggle="collapse" data-target="#shoes"
										class="collapsed"><a href="#">More</a>
										<ul class="sub-menu collapse" id="shoes">
											<li><a href="shop">All</a></li>
											<li><a href="shop?category=Funk">Funk</a></li>
											<li><a href="shop?category=Synth-Pop">Synth-Pop</a></li>
											<li><a href="shop?category=Electro">Electro</a></li>
											<li><a href="shop?category=Indie">Indie</a></li>
											<li><a href="shop?category=Disco">Disco</a></li>
											<li><a href="shop?category=Heavy%20metal">Heavy
													metal</a></li>
											<li><a href="shop?category=Hard%20Rock">Hard Rock</a></li>
											<li><a href="shop?category=Punk%20rock">Punk rock</a></li>
											<li><a href="shop?category=Reggae">Reggae</a></li>
											<li><a href="shop?categoryIndie">Indie</a></li>

										</ul></li>
								</ul>
							</div>
						</div>

						<!-- ##### Single Widget ##### -->
						<!--   <div class="widget price mb-50">
                            Widget Title
                            <h6 class="widget-title mb-30">Filter by</h6>
                            Widget Title 2
                            <p class="widget-title2 mb-30">Price</p>

                            <div class="widget-desc">
                                <div class="slider-range">
                                    <div data-min="49" data-max="360" data-unit="$" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="49" data-value-max="360" data-label-result="Range:">
                                        <div class="ui-slider-range ui-widget-header ui-corner-all"> boop.</div>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"> boop.</span>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"> boop.</span>
                                    </div>
                                    <div class="range-price">Range: $49.00 - $360.00</div>
                                </div>
                            </div>
                        </div>

                        ##### Single Widget #####
                        <div class="widget color mb-50">
                            Widget Title 2
                            <p class="widget-title2 mb-30">Color</p>
                            <div class="widget-desc">
                                <ul class="d-flex">
                                    <li><a href="#" class="color1"></a></li>
                                    <li><a href="#" class="color2"></a></li>
                                    <li><a href="#" class="color3"></a></li>
                                    <li><a href="#" class="color4"></a></li>
                                    <li><a href="#" class="color5"></a></li>
                                    <li><a href="#" class="color6"></a></li>
                                    <li><a href="#" class="color7"></a></li>
                                    <li><a href="#" class="color8"></a></li>
                                    <li><a href="#" class="color9"></a></li>
                                    <li><a href="#" class="color10"></a></li>
                                </ul>
                            </div>
                        </div> -->

						<!-- ##### Single Widget ##### -->
						<!--  <div class="widget brands mb-50">
                            Widget Title 2
                            <p class="widget-title2 mb-30">Brands</p>
                            <div class="widget-desc">
                                <ul>
                                    <li><a href="#">Asos</a></li>
                                    <li><a href="#">Mango</a></li>
                                    <li><a href="#">River Island</a></li>
                                    <li><a href="#">Topshop</a></li>
                                    <li><a href="#">Zara</a></li>
                                </ul>
                            </div>
                        </div> -->
					</div>
				</div>

				<div class="col-12 col-md-8 col-lg-9">
					<div class="shop_grid_product_area">
						<div class="row">
							<div class="col-12">
								<div
									class="product-topbar d-flex align-items-center justify-content-between">
									<!-- Total Products -->
									<div class="total-products">
										<p>
											<c:choose>
												<c:when test="${(empty shopDisq)}">
													<span>186</span> products found
											</c:when>
												<c:otherwise>
													<span><c:out value="${fn:length(shopItems)}" /></span> products found
												</c:otherwise>
											</c:choose>
										</p>
									</div>
									<!-- Sorting -->
									<div class="product-sorting d-flex">
										<p>Sort by:</p>
										<form action="#" method="get">
											<select name="select" id="sortByselect">
												<option value="value">Highest Rated</option>
												<option value="value">Newest</option>
												<option value="value">Price: $$ - $</option>
												<option value="value">Price: $ - $$</option>
											</select> <input type="submit" class="d-none" value="" />
										</form>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<c:choose>
								<c:when test="${(empty shopDisq)}">

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-1.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/mj_thriller.jpg"
													alt="" />

												<!-- Product Badge -->
												<div class="product-badge offer-badge">
													<span>-30%</span>
												</div>
												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">
													<span class="old-price">$75.00</span> $55.00
												</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
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
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-3.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/bat_out_of_hell.jpg"
													alt="" />

												<!-- Product Badge -->
												<div class="product-badge new-badge">
													<span>New</span>
												</div>

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-4.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/Roddy-Ricch-Antisocial.jpg"
													alt="" />

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-5.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/dark_side_of_the_moon.jpeg"
													alt="" />

												<!-- Product Badge -->
												<div class="product-badge offer-badge">
													<span>-30%</span>
												</div>

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">
													<span class="old-price">$75.00</span> $55.00
												</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-6.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/beaty_behind_madness.jpg"
													alt="" />

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-7.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/so_much_fun.jpg"
													alt="" />

												<!-- Product Badge -->
												<div class="product-badge new-badge">
													<span>New</span>
												</div>

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-8.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/IGOR.jpg"
													alt="" />

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart">.</a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Single Product -->
									<div class="col-12 col-sm-6 col-lg-4">
										<div class="single-product-wrapper">
											<!-- Product Image -->
											<div class="product-img">
												<img
													src="${pageContext.request.contextPath}/res/img/product-img/product-9.jpg"
													alt="" />
												<!-- Hover Thumb -->
												<img class="hover-img"
													src="${pageContext.request.contextPath}/res/img/product-img/Drake-If-Youre-Reading-This-Its-Too-Late-album-cover-820.jpg"
													alt="" />

												<!-- Favourite -->
												<div class="product-favourite">
													<a href="#" class="favme fa fa-heart"></a>
												</div>
											</div>

											<!-- Product Description -->
											<div class="product-description">
												<span>topshop</span> <a href="single-product-details.html">
													<h6>Knot Front Mini Dress</h6>
												</a>
												<p class="product-price">$80.00</p>

												<!-- Hover Content -->
												<div class="hover-content">
													<!-- Add to Cart -->
													<div class="add-to-cart-btn">
														<a href="#" class="btn essence-btn">Add to Cart</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:when>
								<!-- Display search results/ category results instead of default data -->
								<c:otherwise>
									<c:forEach var="current" items="${shopItems}">
										<c:url var="myurl" value="${current.value[5]}" />
										<div class="col-12 col-sm-6 col-lg-4">
											<div class="single-product-wrapper">
												<!-- Product Image -->
												<div class="product-img">
													<img src="${myurl}" alt="" />
													<!-- Hover Thumb -->
													<img class="hover-img" src="${myurl}" alt="" />
													<!-- Favourite -->
													<div class="product-favourite">
														<a href="#" class="favme fa fa-heart">.</a>
													</div>
												</div>
												<!-- Product Description -->
												<div class="product-description">
													<span>${current.value[1]}</span> <a
														href="single-product-details.html">
														<h6>${current.value[2]}</h6>
													</a>
													<p class="product-price">$${current.value[4]}</p>
													<!-- Hover Content -->
													<div class="hover-content">
														<!-- Add to Cart -->
														<div class="add-to-cart-btn">
															<a href="#" class="btn essence-btn">Add to Cart</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
					<!-- Pagination -->
					<nav aria-label="navigation">
						<ul class="pagination mt-50 mb-70">
							<li class="page-item"><a class="page-link" href="#"><i
									class="fa fa-angle-left">.</i></a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">...</a></li>
							<li class="page-item"><a class="page-link" href="#">21</a></li>
							<li class="page-item"><a class="page-link" href="#"><i
									class="fa fa-angle-right">.</i></a></li>
						</ul>
					</nav>
				</div>

				.
			</div>
		</div>
	</section>
	<!-- ##### Shop Grid Area End ##### -->

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