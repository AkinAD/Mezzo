<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:f="http://java.sun.com/jsp/jstl/fmt">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Album</title>
<!-- Favicon  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico" />

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/core-style.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/review.css" />
</head>
<body>
	<!-- 	<div> <%@ include page="shop.jsp" %> </div>
 -->
	<!-- ##### Footer Area Start ##### -->
	<jsp:include page="header.jsp" />
	<!-- ##### Footer Area End ##### -->


	<!-- ##### Single Product Details Area Start ##### -->
	<section class="single_product_details_area d-flex align-items-center">

		<!-- Single Product Thumb -->
		<div class="single_product_thumb clearfix">
			<div class="product_thumbnail_slides owl-carousel">
				<!-- <img src="${pageContext.request.contextPath}/res/img/product-img/product-big-1.jpg" alt=""/>
                <img src="${pageContext.request.contextPath}/res/img/product-img/product-big-2.jpg" alt=""/>
                <img src="${pageContext.request.contextPath}/res/img/product-img/product-big-3.jpg" alt=""/>
                 -->
				<img src="${requestScope.albumResult.picture}" alt="Album art" /> <img
					src="${requestScope.albumResult.picture}" alt="Album art" /> <img
					src="${requestScope.albumResult.picture}" alt="Album art" />
			</div>
		</div>

		<!-- Single Product Description -->
		<div class="single_product_desc clearfix">
			<span>${requestScope.albumResult.artist}</span> <a href="cart">
				<h2>${requestScope.albumResult.title}</h2>
			</a>
			<p class="product-price">
				<!-- <span class="old-price">$65.00</span>-->
				<f:setLocale value="en_CA" />
				<f:formatNumber value="${requestScope.albumResult.price}"
					type="currency" />
			</p>
			<p class="product-desc">Mauris viverra cursus ante laoreet
				eleifend. Donec vel fringilla ante. Aenean finibus velit id urna
				vehicula, nec maximus est sollicitudin.</p>

			<!-- Form -->
			<form class="cart-form clearfix" method="post">
				<!-- Select Box -->
				<div class="select-box d-flex mt-50 mb-30">
					<select name="select" id="productSize" class="mr-5">
						<option value="value">Size: XL</option>
						<option value="value">Size: X</option>
						<option value="value">Size: M</option>
						<option value="value">Size: S</option>
					</select> <select name="select" id="productColor">
						<option value="value">Color: Black</option>
						<option value="value">Color: White</option>
						<option value="value">Color: Red</option>
						<option value="value">Color: Purple</option>
					</select>
				</div>
				<!-- Cart & Favourite Box -->
				<div class="cart-fav-box d-flex align-items-center">
					<!-- Cart -->
					<button type="submit" name="addtocart" value="5"
						class="btn essence-btn">Add to cart</button>
					<!-- Favourite -->
					<div class="product-favourite ml-4">
						<a href="#" class="favme fa fa-heart">.</a>
					</div>
				</div>
			</form>
			<!-- #### START Leave a review Section -->
			<div class="d-flex align-items-center">
				<h6>Rate this Album!</h6>
				<div>
					<form action="" method="get">
						<div class="rating">
							<input type="radio" name="rating" id="r1" class="star" /> <label
								for="r1">
								<!-- Some content here  -->
							</label> <input type="radio" name="rating" id="r2" class="star" /> <label
								for="r2">
								<!-- Some content here  -->
							</label> <input type="radio" name="rating" id="r3" class="star" /> <label
								for="r3">
								<!-- Some content here  -->
							</label> <input type="radio" name="rating" id="r4" class="star" /> <label
								for="r4">
								<!-- Some content here  -->
							</label> <input type="radio" name="rating" id="r5" class="star" /> <label
								for="r5">
								<!-- Some content here  -->
							</label>
							<div class="rev-box">
								<h6 class="review" for="review">Leave a Review!</h6>
								<TEXTAREA class="review" col="60" rows="100" name="review"
									placeholder="Great Tracks!">Great Tracks!</TEXTAREA>
								<button class="btn essence-btn">Submit</button>
							</div>

						</div>
					</form>
				</div>
			</div>
			<!-- #### END Leave a review Section -->
		</div>
	</section>
	<!-- ##### Single Product Details Area End ##### -->
	<br/><br/>
	<!-- Review Section -->
	<section class="reviewBox">
		<div class="otherReviews">
			<span class="heading">User Revies</span>
			 <span class="fa fa-star checked">&#160;</span> 
			 <span class="fa fa-star checked">&#160;</span>
			<span class="fa fa-star checked">&#160;</span>
			<span class="fa fa-star checked">&#160;</span>
			<span class="fa fa-star">&#160;</span>
			<p>4.1 average based on 254 reviews.</p>
			<hr style="border: 3px solid #f1f1f1"/>
			<div class="revRow">
				<div class="side">
					<div>5 star</div>
				</div>
				<div class="middle">
					<div class="bar-container">
						<div class="bar-5">&#160;</div>
					</div>
				</div>
				<div class="side right">
					<div>150</div>
				</div>
				<div class="side">
					<div>4 star</div>
				</div>
				<div class="middle">
					<div class="bar-container">
						<div class="bar-4">&#160;</div>
					</div>
				</div>
				<div class="side right">
					<div>63</div>
				</div>
				<div class="side">
					<div>3 star</div>
				</div>
				<div class="middle">
					<div class="bar-container">
						<div class="bar-3">&#160;</div>
					</div>
				</div>
				<div class="side right">
					<div>15</div>
				</div>
				<div class="side">
					<div>2 star</div>
				</div>
				<div class="middle">
					<div class="bar-container">
						<div class="bar-2">&#160;</div>
					</div>
				</div>
				<div class="side right">
					<div>6</div>
				</div>
				<div class="side">
					<div>1 star</div>
				</div>
				<div class="middle">
					<div class="bar-container">
						<div class="bar-1">&#160;</div>
					</div>
				</div>
				<div class="side right">
					<div>20</div>
				</div>
			</div>
			<br/> <br/>
			<hr style="border: 3px solid #f1f1f1"/>
			<!-- Individual Reviews begin here  -->
			<div class="revRow">
				<div class="side">
					<img alt="" src="https://i.pinimg.com/564x/d8/69/66/d86966ce8d5c7e12cfc40b18da788d1b.jpg" style=" max-width: 50%;"/>
					<div> AlanT says</div>
				</div>
				<div class="middle">
					" All time Classic!"
				</div>
				<div class="side right">
					<div>5<span class="fa fa-star checked">&#160;</span> <span class="fa fa-star checked">&#160;</span> <span class="fa fa-star checked">&#160;</span><span class="fa fa-star checked">&#160;</span> <span class="fa fa-star checked">&#160;</span> </div> 
				</div>
			
			</div>
		</div>
	</section>
<br/>
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
		src="${pageContext.request.contextPath}/res/js/classy-nav.min.js">;</script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/res/js/active.js">;</script>

</body>
	</html>
</jsp:root>