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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/open-iconic-bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/animate.css"/>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/owl.carousel.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/owl.theme.default.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/magnific-popup.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/aos.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/ionicons.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/core-style.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/styles_other.css" />
    <!-- <link rel="stylesheet" href="css/bootstrap-datepicker.css"/>
    <link rel="stylesheet" href="css/jquery.timepicker.css"/> -->

<title>Insert title here</title>
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
						<h2>Cart</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Breadcumb Area End ##### -->
	
			<section class="ftco-section ftco-cart">
			<div class="container">
				<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div class="cart-list">
	    				<table class="table">
						    <thead class="thead-primary">
						      <tr class="text-center">
						        <th>&#160;</th>
						        <th>&#160;</th>
						        <th>Product</th> <th>Price</th> <th>Quantity</th>  <th>Total</th>
						      </tr>
						    </thead>
						    <tbody>
						    <c:forEach var="current" items="${cartItems}">
						        <tr class="text-center">
						        <td class="product-remove"><a href="#" ><span class="fa fa-close" aria-hidden="true"> <!--  Some content -->&#160;</span></a></td>
						          
						         <div class="cart-item-desc">
						           <td class="product-image">
						            <a href="${pageContext.request.contextPath}/ProductPage?aid=${current.item.aid}">
						     		<c:url var="myurl" value="${current.item.picture}" />
						           <img src="${myurl}" class="cart-thumb" alt="" style="width: 50%;"/>
						           	</a>
						           </td>
						        </div>
						        <td class="product-name">
						        	<a href="${pageContext.request.contextPath}/ProductPage?aid=${current.item.aid}">
						        	<h5>${current.item.title}</h5>
						        	<p>${current.item.artist}</p>
						        	</a>
						        </td>
						        <td class="price"><f:setLocale value="en_CA"/><f:formatNumber value="${current.item.price}" type="currency"/></td>
						        <td class="quantity">
							<form action="" class="number-input">
								<button onclick="this.nextElementSibling.stepDown();">-</button>
								<input type="number" class="cartItemQuantity" min="1" step="1"
									value="${current.qty}" />
								<button onclick="this.previousElementSibling.stepUp();"
									class="plus">+</button>
							</form>			
					          </td>
						        
						        <td class="total">$4.90</td>
						      </tr><!-- END TR-->
					</c:forEach>
						     
						    </tbody>
						  </table>
					  </div>
    			</div>
    		</div>
									
    		<div class="row justify-content-end">
    			<div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate">
    				<div class="cart-total mb-3">
    					<h3>Cart Totals</h3>
    					<p class="d-flex">
    						<span>Subtotal</span>
    						<span><f:setLocale value="en_CA"/><f:formatNumber value="${requestScope.cartTotal}" type="currency"/></span>
    					</p>
    					<p class="d-flex">
    						<span>Delivery</span>
    						<span>$0.00</span>
    					</p>
    					<p class="d-flex">
    						<span>Discount</span>
    						<span>-15%</span>
    					</p>
    					<hr/>
    					<p class="d-flex total-price">
    						<span>Total</span>
    						<span>${requestScope.cartTotal-(requestScope.cartTotal*0.15)}</span>
    					</p>
    				</div>
    				<p class="text-center"><a href="checkout" class="btn essence-btn">Proceed to Checkout</a></p>
    			</div>
    		</div>
			</div>
		</section>
		
		
		<!-- ##### START header area  ##### -->
	<jsp:include page="footer.jsp" />
    <!-- ##### END  header area ##### -->
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