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
<title>Hello Partner</title>
<!-- Favicon  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico" />

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/core-style.css" />
</head>
<body>
	<!-- ##### Footer Area Start ##### -->
	<jsp:include page="header.jsp" />
	<!-- ##### Footer Area End ##### -->

<div class="single-blog-wrapper">

        <!-- Single Blog Post Thumb -->
        <div class="single-blog-post-thumb">
            <img src="${pageContext.request.contextPath}/res/img/bg-img/blog.jpg" alt=""/>
        </div>
	        <!-- Single Blog Content Wrap -->
        <div class="single-blog-content-wrapper d-flex">

            <!-- Blog Content -->
            <div class="single-blog--text">
                <h2>How to use API methods</h2>
                <blockquote>
                <span style="margin-left: 0px;">OrdersEndpoint</span>
                <br/><br/>
                The orders endpoint serves /api/orders and produces a JSON document with the following entries: 
                <pre class="prettyprint">
               	<ul>
	 				<li>totalOrders - Quantity of orders for this item</li>
	 				<li>orders - Array of PO IDs corresponding to orders of this item</li>
	  			</ul>
	  			</pre>	  			
                It must be called with the parameter aid which is the album ID for which the orders are retrieved.
                <br/><br/>
                <pre class="prettyprint">void ordersEndpoint<br/>@param request<br/>@param response<br/>@throws IOException</pre>
                
                <P> Sample Link: <a href="https://mezzo-4413.mybluemix.net/api/orders/?aid=1 ">https://mezzo-4413.mybluemix.net/api/orders/?aid=1</a></P>
                <img src="${pageContext.request.contextPath}/res/img/other/rest_orders.png" />
            	</blockquote>   
            	            	
            	
            	<blockquote>
                <span style="margin-left: 0px;">productEndpoint</span>
                <br/><br/>
                The product endpoint itself has nothing. Its first-level subpaths (album IDs) correspond to the respective album. The JSON document produced by these endpoints contain the following entries:
                <pre class="prettyprint">
               		<ul>
						<li>aid - Album ID</li>
	 					<li>artist - Album artist name</li>
						<li>title - Album title</li>
	 					<li>category - Album genre</li>
	 					<li>price - Album price</li>
						<li>picture - Album cover art</li>
	 				</ul>
	  			</pre>	  			
                <pre class="prettyprint">void productEndpoint<br/>@param request<br/>@param response<br/>@throws IOException</pre>
                 <P> Sample Link: <a href="https://mezzo-4413.mybluemix.net/api/products/1 ">https://mezzo-4413.mybluemix.net/api/products/1</a></P>
                  <img src="${pageContext.request.contextPath}/res/img/other/rest_products.png" />
                
            	</blockquote>   
            	
            	
                <blockquote>
                    <h6><i class="fa fa-quote-left" aria-hidden="true">&#160;</i> If you have any issues using the Api methods, Contact an administrator </h6>
                    <span>The Mezzo Team</span>
                </blockquote>
            </div>

            <!-- Related Blog Post -->
            <div class="related-blog-post">
                <!-- Single Related Blog Post -->
                <div class="single-related-blog-post">
                    <img src="${pageContext.request.contextPath}/res/img/bg-img/rp1.jpg" alt=""/>
                    <a href="#">
                        <h5>Other Instruction Doc #1</h5>
                    </a>
                </div>
                <!-- Single Related Blog Post -->
                <div class="single-related-blog-post">
                    <img src="${pageContext.request.contextPath}/res/img/bg-img/rp2.jpg" alt=""/>
                    <a href="#">
                        <h5>Other Instruction Doc #2</h5>
                    </a>
                </div>
                <!-- Single Related Blog Post -->
                <div class="single-related-blog-post">
                    <img src="${pageContext.request.contextPath}/res/img/bg-img/rp3.jpg" alt=""/>
                    <a href="#">
                        <h5>Other Instruction Doc #3</h5>
                    </a>
                </div>
                <!-- Single Related Blog Post -->
                <div class="single-related-blog-post">
                    <img src="${pageContext.request.contextPath}/res/img/bg-img/rp4.jpg" alt=""/>
                    <a href="#">
                        <h5>Other Instruction Doc #4</h5>
                    </a>
                </div>
            </div>
        </div>

        </div>
  
    <!-- ##### Blog Wrapper Area End ##### -->

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
	<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js">;</script>
	
	
</body>
</html>
</jsp:root>