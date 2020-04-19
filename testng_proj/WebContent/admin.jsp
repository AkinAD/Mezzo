<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0" xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:f="http://java.sun.com/jsp/jstl/fmt">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin Console</title>
<!-- Favicon  -->
<link rel="icon"
	href="${pageContext.request.contextPath}/res/img/core-img/favicon.ico" />
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/admin.css" />
	

</head>
<body>

	<div class="content">
		<h1 class="heading">Admin Console</h1>
		<p class="description">Hover over a card to flip it.</p>
		<a href="home" class="description rturn"> Return to store</a> <a
			class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x402);">
				<p>Welcome ${requestScope.CurProfile.fname}</p>
			</div>
			<div class="back">
				<div>
					<img class="profilephoto"		src="${pageContext.request.contextPath}/res/img/core-img/pp.jpg" />
					<p>${requestScope.CurProfile.lname}, ${requestScope.CurProfile.fname}</p>
					<p>Username: ${requestScope.CurProfile.userName} AccessLevel: ${requestScope.CurProfile.privilege}</p>
					<form action="profile" method="get"><button class="button">Go to Profile</button></form>
				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x403);">
				<p>Store Stats - Album Sales per month</p>
			</div>
			<div class="back">
				<div>
					<p>January: ${requestScope.apm[0]}<br/>
					   February: ${requestScope.apm[1]}<br/>
					   March: ${requestScope.apm[2]}<br/>
					   April: ${requestScope.apm[3]}<br/>
					   May: ${requestScope.apm[4]} <br/>
					   June: ${requestScope.apm[5]}<br/>
					   July: ${requestScope.apm[6]}<br/>
					   August: ${requestScope.apm[7]}<br/>
					   September: ${requestScope.apm[8]}<br/>
					   October: ${requestScope.apm[9]}<br/>
					   November: ${requestScope.apm[10]}<br/>
					   December: ${requestScope.apm[11]}</p>
					<button class="button" onClick="history.go(0)">Refresh Page</button>
				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x403);">
				<p>Site Analytics - A2 (Listeners) Most popular Albums</p>
			</div>
			<div class="back">
				<div>
					<p>Most Popular Album: <br/>${requestScope.mostPopular}</p>
					<span>Top Three: </span>
					<ol>
						<li>${requestScope.topThree[0]}</li>
						<li>${requestScope.topThree[1]}</li>
						<li>${requestScope.topThree[2]}</li>
					</ol>
					<button class="button" onClick="history.go(0)">Refresh Page</button>
				</div>
			</div>
		</a>
		<a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x403);">
				<p>Site Analytics -A3 (Listeners) Annomized Reports</p>
			</div>
			<div class="back">
				<div>
					<ol>
						<li>Something : ahhhh</li>
						<li>Someone : ooouuuu</li>
					</ol>
					<button class="button" onClick="history.go(0)">Refresh Page</button>
				</div>
			</div>
		</a>
		 <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x401);">
				<p>Add new Album to store</p>
			</div>
			<div class="back">
				<div>
					<form>
						<input type="text" placeholder="Artist" name="artist" /> <input
							type="text" placeholder="Title" name="title" /> <select
							id="category" name="category">
							<c:forEach items="${requestScope.albumCats}" var="item">
								<option value="${item}">${item}</option>
							</c:forEach>
						</select> <input type="number" placeholder="Price (e.g 15.45)" step=".01"
							name="price" /> <input type="url" name="url"
							placeholder="Album cover Image link" />
						<button class="button" name="addAlbum" value="true">Update
							Catelog</button>
					</form>
					<p>Create new Album available in store</p>
					<p>Ensure image url is valid (Copy image Address)</p>
					<p class="error">...</p>

				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x401);">
				<p>Update user role</p>
			</div>
			<div class="back">
				<div>
					<form id="userRole">
						<input type="text" placeholder="Username" name="user"/> <select
							id="privilege" name="privilege">
							<option value="admin">admin</option>
							<option value="partner">partner</option>
							<option value="customer">customer</option>

						</select>
					</form>
					<p>Enter valid username and select user role</p>
					<p class="error">...</p>
					<button class="button" name="updateRole" form="userRole">Update</button>
				</div>
			</div>
		</a>

	</div>

</body>
	</html>
</jsp:root>