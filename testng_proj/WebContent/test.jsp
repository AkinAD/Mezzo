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
<title>Test Page</title>
</head>
<body>
	<p>There <c:if test="${requestScope.cartTotalQty eq 0}">are no</c:if><c:if test="${requestScope.cartTotalQty gt 0}">are ${requestScope.cartTotalQty} items</c:if> items in the cart.</p>
	<ul>
		<c:forEach items="${requestScope.cartItems}" var="deadmau5">
			<li>${deadmau5.item.title} x${deadmau5.qty} - ${deadmau5.item.price} per unit - ${deadmau5.effectivePrice} total</li>
		</c:forEach>
	</ul>
	<p>Total: ${requestScope.cartTotal}</p>
</body>
</html>
</jsp:root>