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
<title>Image</title>
</head>
<body>
	<c:forEach var="current" items="${output}">
						<tr>
							<td><c:out value="${current.key}" /></td>
							<td><c:out value="${current.value[1]}" /></td>
							<td><c:out value="${current.value[2]}" /></td>
							<td><c:out value="${current.value[3]}" /></td>
							<td><c:out value="${current.value[4]}" /></td>
								<c:url var="myurl" value="${current.value[5]}" />
							<td><img  height="100px" width="200px" src="${myurl}"/></td>
							
						</tr>
	</c:forEach>
</body>
</html>
</jsp:root>