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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>HomePage</title>
</head>
<body>

	<div class="container theme-showcase" role="main" id="me">
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
				<li><a href="profile?username=<c:out value="${username}"/>"><span
						class="glyphicon glyphicon-user"></span> ${username}</a></li>
				<li><a href="login"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
		</nav>
		<form action="HomePage" method="get">
			<div class="jumbotron">
				<h2>Welcome ${firstname} !</h2>
			</div>
			<input type="hidden" id="uname" name="username" value="${username}">

			<div class="account-wall">
				<h4>
					<span id="successMessage"><b>${messages.success}</b></span>
				</h4>
				</br>
				</br>
				<div class="form-group">
					<label for="filters" class="col-xs-1 control-label">Search :</label>
					<div id="filters" class="col-md-10">
						<span><b>ZipCode: </b></span> 
						<input type="text" name="zipcode" value="${zipcode}" /> 
							
						<span><b>HealthCare Type:  </b></span>
						<select name="filter" id="filter">
							<option value="zipcode"
								<c:if test="${filtersSet.contains('zipcode')}">selected="selected"</c:if>>
								<c:out value="All" /></option>

							<option value="hospital"
								<c:if test="${filtersSet.contains('hospital')}">selected="selected"</c:if>>
								<c:out value="Hospitals only" /></option>
								
							<option value="clinic"
								<c:if test="${filtersSet.contains('clinic')}">selected="selected"</c:if>>
								<c:out value="Clinics only" /></option>
							<option value="pharmacy"
								<c:if test="${filtersSet.contains('pharmacy')}">selected="selected"</c:if>>
								<c:out value="Pharmacy only" /></option>
						</select> 
						<input type="checkbox" name="filter" value="insurance"
							<c:if test="${filtersSet.contains('insurance')}">checked</c:if> />
						<label for="insurance-filter">Show only covered healthcare</label>

						<button type="submit" class="btn btn-success">Filter</button>
					</div>
					<br /> <br />
				</div>
			</div>
		</form>
		<br />
		<div id="mydiv" class="account-wall">
			<h4>Matching HealthCares :</h4>
			</br>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Street</th>
						<th>City</th>
						<th>State</th>
						<th>ZipCode</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${hc}" var="h">
					<tbody>
						<tr>
							<td><c:out value="${h.getName()}" /></td>
							<td><c:out value="${h.getAddress().getStreet()}" /></td>
							<td><c:out value="${h.getAddress().getCity()}" /></td>
							<td><c:out value="${h.getAddress().getState()}" /></td>
							<td><c:out value="${h.getAddress().getZipCode()}" /></td>
							<td><a
								href="hospitaldetail?id=<c:out value="${h.getMediFacilityId()}"/>&username=<c:out value="${username}"/>&zipcode=<c:out value="${zipcode}"/>">Get
									Details</a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>

	<!-- Bootstrap -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
