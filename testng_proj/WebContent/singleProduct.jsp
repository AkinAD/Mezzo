<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0" xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:f="http://java.sun.com/jsp/jstl/fmt">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Album</title>
 <!-- Favicon  -->
    <link rel="icon" href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico"/>

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/core-style.css"/>
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
                <img src="${requestScope.albumResult.picture}" alt="Album art" />
                <img src="${requestScope.albumResult.picture}" alt="Album art" />
                <img src="${requestScope.albumResult.picture}" alt="Album art" />
            </div>
        </div>

        <!-- Single Product Description -->
        <div class="single_product_desc clearfix">
            <span>${requestScope.albumResult.artist}</span>
            <a href="cart">
                <h2>${requestScope.albumResult.title}</h2>
            </a>
            <p class="product-price"><!-- <span class="old-price">$65.00</span>--><f:setLocale value="en_CA"/><f:formatNumber value="${requestScope.albumResult.price}" type="currency"/></p>
            <p class="product-desc">Mauris viverra cursus ante laoreet eleifend. Donec vel fringilla ante. Aenean finibus velit id urna vehicula, nec maximus est sollicitudin.</p>

            <!-- Form -->
            <form class="cart-form clearfix" method="post">
                <!-- Select Box -->
                <div class="select-box d-flex mt-50 mb-30">
                    <select name="select" id="productSize" class="mr-5">
                        <option value="value">Size: XL</option>
                        <option value="value">Size: X</option>
                        <option value="value">Size: M</option>
                        <option value="value">Size: S</option>
                    </select>
                    <select name="select" id="productColor">
                        <option value="value">Color: Black</option>
                        <option value="value">Color: White</option>
                        <option value="value">Color: Red</option>
                        <option value="value">Color: Purple</option>
                    </select>
                </div>
                <!-- Cart & Favourite Box -->
                <div class="cart-fav-box d-flex align-items-center">
                    <!-- Cart -->
                    <button type="submit" name="addtocart" value="5" class="btn essence-btn">Add to cart</button>
                    <!-- Favourite -->
                    <div class="product-favourite ml-4">
                        <a href="#" class="favme fa fa-heart">.</a>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <!-- ##### Single Product Details Area End ##### -->
 
 
	<!-- ##### Footer Area Start ##### -->
		<jsp:include page="footer.jsp" />
	<!-- ##### Footer Area End ##### --> 
	
	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script src="${pageContext.request.contextPath}/res/js/jquery/jquery-2.2.4.min.js">;</script>
	<!-- Popper js -->
	<script src="${pageContext.request.contextPath}/res/js/popper.min.js">;</script>
	<!-- Bootstrap js -->
	<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js">;</script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/res/js/plugins.js">;</script>
	<!-- Classy Nav js -->
	<script src="${pageContext.request.contextPath}/res/js/classy-nav.min.js">;</script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/res/js/active.js">;</script>
	
</body>
</html>
</jsp:root>