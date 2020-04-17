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
<title>Login / Register</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/regLogin.js">;</script>
<!-- leave the ";" from within the script tags or page will not load -->
	<!-- Font Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/fonts/material-icon/css/material-design-iconic-font.min.css"/>
    
<link rel="StyleSheet"
	href="${pageContext.request.contextPath}/res/css/regLogin.css"
	type="text/css" title="cse4413" media="screen, print" />
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/res/fonts/material-icon/css/material-design-iconic-font.min.css"/> -->
</head>
<body>
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content" id="signup">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>

						<form method="POST" class="register-form" id="register-form"
							onsubmit="return validate();">
							<div class="form-group">
								<!-- Your FirstName -->
								<label for="fname"><i class="material-icons">person</i></label>
								<input type="text" name="fname" id="fname"
									placeholder="Your First Name" />
							</div>
							<div class="form-group">
								<!-- Your LastName -->
								<label for="lname"><i class="material-icons">person</i></label>
								<input type="text" name="lname" id="lname"
									placeholder="Your Last Name" />
							</div>
							<div class="form-group">
								<!-- Email -->
								<label for="email"><i class="material-icons">email</i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<!-- Username -->
								<!-- YOU MAY HAVE TO ALTER THIS IN CSS ###################### -->
								<label for="username_reg"><i class="material-icons">alternate_email</i></label>
								<input type="text" name="username" id="username_reg"
									placeholder="Username" />
							</div>
							<div class="form-group">
								<!-- Password -->
								<label for="password_reg"><i class="material-icons">lock</i></label>
								<input type="password" name="password" id="password_reg"
									placeholder="Password" />
							</div>
							<div class="form-group">
								<!-- Re-Enter Password -->
								<label for="re-pass"><i class="material-icons">lock_outline</i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" />
							</div>
							<!-- <div class="form-group"> Faux Terms and Conditions
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div> -->
							<div class="form-group form-button">
								<!-- Submit -->
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img
								src="${pageContext.request.contextPath}/res/img/core-img/signup-image.jpg"
								alt="sing up image" />
						</figure>
						<a href="#signin" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</section>

		<!-- Sign in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content" id="signin">
					<div class="signin-image">
						<figure>
							<img
								src="${pageContext.request.contextPath}/res/img/core-img/signin-image.jpg"
								alt="sing up image" />
						</figure>
						<a href="#signup" class="signup-image-link">Create an account</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Sign in</h2>
						<form method="POST" class="register-form" id="login-form"
							onsubmit="return loginValidate();">
							<div class="form-group">
								<!-- Your UserName -->
								<label for="username"><i class="material-icons">person</i></label>
								<input type="text" name="username" id="username"
									placeholder="Username" />
							</div>
							<div class="form-group">
								<!-- Password -->
								<label for="password"><i class="material-icons">lock</i></label>
								<input type="password" name="password" id="password"
									placeholder="Password" />
							</div>
							<!-- <div class="form-group">
								Remember me?
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span>.content</span></span> Remember me</label>
                            </div> -->
							<div class="form-group form-button">
								<!-- Log-in -->
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Login" />
							</div>
						</form>
						<!-- <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>
                        </div>-->
					</div>
				</div>
			</div>
		</section>

	</div>
</body>
	</html>
</jsp:root>