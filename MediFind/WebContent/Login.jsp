<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 10%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<title>Login</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><b>MediFind</b></a>
			</div>
			<ul class="nav navbar-nav navbar-right navbar-collapse collapse"
				id="navbar">
				<li><a href="register"><span
						class="glyphicon glyphicon-user"></span>Register</a></li>
			</ul>
		</div>
		</nav>


		<form action="login" method="post">
			<div class="imgcontainer">
				<h1 class="text-center page-header">MediFind Login</h1>
				<img src="css/img_avatar2.png" alt="Avatar" class="avatar">
			</div>

			<div class="container col-md-6 col-md-offset-3">
				<label><b>Username</b></label> 
				<input type="text" name="uname"
					id="uname" placeholder="Enter Username" 
					value="${fn:escapeXml(param.username)}" required> 
				<label><b>Password</b></label>
				<input type="password" id="pass" name="pass"
					placeholder="Enter Password" 
					value="${fn:escapeXml(param.password)}" required>

				<button type="submit">Login</button>

			</div>
		</form>

	</div>

	<br />
	<div class="col-md-5">
		<h4>
			</div><span id="successMessage"><b>${messages.success}</b></span>
		</h4>
	</div>
	<br />


	<!-- Bootstrap -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
