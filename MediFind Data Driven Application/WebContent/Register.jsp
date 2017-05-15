<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<title>RegisterUser</title>
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
				<li><a href="login"><span
						class="glyphicon glyphicon-user"></span>Login</a></li>
			</ul>
		</div>
		</nav>

	
	 <h1 class="page-header">MediFind User Registration</h1>
	 
	 <div class="account-wall">
		<form action="register" method="post" class="form-horizontal">
		
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">UserName
					:</label>
				<div class="col-sm-6">
					<input name="username" type="text" class="form-control"
						id="username" placeholder="Username" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class=" col-sm-2 control-label">Password
					:</label>
				<div class="col-sm-6">
					<input name="password" type="password" class="form-control"
						id="password" placeholder="Password" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="verifypassword" class="col-sm-2 control-label">Verify
					Password :</label>
				<div class="col-sm-6">
					<input name="verifypassword" type="password" class="form-control"
						id="verifypassword" placeholder="Verify Password" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">FirstName
					:</label>
				<div class="col-sm-6">
					<input name="firstname" type="text" class="form-control"
						id="firstname" placeholder="First Name" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">LastName
					:</label>
				<div class="col-sm-6">
					<input name="lastname" type="text" class="form-control"
						id="lastname" placeholder="Last Name" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email :</label>
				<div class="col-sm-6">
					<input name="email" type="email" class="form-control" id="email"
						placeholder="Email" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-2 control-label">Phone :</label>
				<div class="col-sm-6">
					<input name="phone" type="text" class="form-control" id="phone"
						placeholder="Phone Number" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="street" class="col-sm-2 control-label">Street :</label>
				<div class="col-sm-6">
					<input name="street" type="text" class="form-control" id="street"
						placeholder="Address Line 1" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-2 control-label">City :</label>
				<div class="col-sm-6">
					<input name="city" type="text" class="form-control" id="city"
						placeholder="City" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State :</label>
				<div class="col-sm-6">
					<input name="state" type="text" class="form-control" id="city"
						placeholder="State" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="zipcode" class="col-sm-2 control-label">ZipCode
					:</label>
				<div class="col-sm-6">
					<input name="zipcode" type="number" class="form-control"
						id="zipcode" placeholder="Zip" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="insurancename" class="col-sm-2 control-label">Insurance
					Provider :</label>
				<div class="col-sm-6">
					<select name="insuranceId">
						<c:forEach items="${insurances}" var="insurance">
							<option value="${insurance.getInsuranceId()}"><c:out
									value="${insurance.getName()}" /></option>

						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-4 col-sm-offset-4 col-sm-10">
					<button type="submit" class="btn btn-primary btn-default">Register</button>
				</div>
			</div>
		</form>
	</div>

		</br> </br>
		<p>
		<div class="alert alert-success " role="alert">
			<span id="successMessage"><b>${messages.success}</b></span>
		</div>
		<div class="alert alert-failure " role="alert">
			<span id="failureMessage"><b>${messages.failure}</b></span>
		</div>
		</p>

	</div>

	<!-- Bootstrap -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>