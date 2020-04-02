<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Admin Console</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/admin.css"/>

</head>
<body>

	<div class="content">
		<h1 class="heading">Admin Console</h1>
		<p class="description">Hover over a card to flip it.</p>
		<a href="home" class="description rturn"> Return to store</a>

		<a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x402);">
				<p>Welcome {user}</p>
			</div>
			<div class="back">
				<div>
					<img class="profilephoto"
						src="${pageContext.request.contextPath}/res/img/core-img/pp.jpg" />
					<p>Show user info here, similar to the 'profile' page</p>
					<p>Get Creative</p>
					<button class="button">Click Here</button>
				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x403);">
				<p>Store Stats - Book Sales per month</p>
			</div>
			<div class="back">
				<div>
					<p>Consectetur adipisicing elit. Possimus, praesentium?</p>
					<p>Provident consectetur natus voluptatem quis tenetur sed
						beatae eius sint.</p>
					<button class="button">Click Here</button>
				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x403);">
				<p>Site Analytics - UC A2 (Listeners) Most popular Album</p>
			</div>
			<div class="back">
				<div>
					<ol>
						<li>A song : Drake</li>
						<li>Another song : Rihanna</li>
					</ol>
					<button class="button">Click Here</button>
				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x401);">
				<p>Add new Album to store</p>
			</div>
			<div class="back">
				<div>
					<form>
						<input type="text" placeholder="Artist" /> <input type="text"
							placeholder="Title" /> <select id="category" name="category">
							<option value="Hip-hop">Hip-hop</option>
							<option value="Rap">Rap</option>
							<option value="Jazz">Jazz</option>
							<option value="Pop">Pop</option>
							<option value="Folk">Folk</option>
							<option value="Rock">Rock</option>
							<option value="Blues">Blues</option>
							<option value="Heavy metal">Heavy metal</option>
							<option value="RnB">RnB</option>
							<option value="Hard Rock">Hard Rock</option>
							<option value="Punk rock">Punk rock</option>
							<option value="Reggae">Reggae</option>
							<option value="Disco">Disco</option>
							<option value="Gospel">Gospel</option>
							<option value="Indie">Indie</option>
							<option value="Electro">Electro</option>
							<option value="Funk">Funk</option>
							<option value="Synth-pop">Synth-pop</option>
						</select> <input type="number" placeholder="Price (e.g 15.45)" step=".15" />
						<input type="url" placeholder="Album cover Image link" />

					</form>
					<p>Create new Album available in store</p>
					<p>Ensure image url is valid (Copy image Address)</p>
					<p class="error">...</p>
					<button class="button">Update Catelog</button>
				</div>
			</div>
		</a> <a class="card" href="#!">
			<div class="front"
				style="background-image: url(//source.unsplash.com/300x401);">
				<p>Update user role</p>
			</div>
			<div class="back">
				<div>
					<form>
						<input type="text" placeholder="Username" /> <select
							id="privilege" name="privilege">
							<option value="admin">admin</option>
							<option value="partner">partner</option>
							<option value="customer">customer</option>

						</select>
					</form>
					<p>Enter valid username and select user role</p>
					<p class="error">...</p>
					<button class="button">Update</button>
				</div>
			</div>
		</a>

	</div>
	
</body>
</html>
</jsp:root>