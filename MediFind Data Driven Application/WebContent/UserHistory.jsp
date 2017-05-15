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
<title>UserHistory</title>
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
				<li><a href="profile?username=<c:out value="${username}"/>"><span
						class="glyphicon glyphicon-user"></span> ${username}</a></li>
				<li><a href="login"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
		</nav>
		<br> <br>
		<div class="container-fluid text-center">
			<div class="row content">
				<br>
				<br>
				<div class="col-xs-1 col-sm-2 col-md-2 sidebar sidebar-xs">
					<ul class="nav nav-sidebar">
						<li><a href="HomePage?username=<c:out value="${username}"/>">
								<span
								class="visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline glyphicon glyphicon-home"></span>
								<span class="hidden-xs">Home</span>
						</a></li>
						<li><a href="profile?username=<c:out value="${username}"/>">
								<span
								class="visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline glyphicon glyphicon-user"></span>
								<span class="hidden-xs">Profile</span>
						</a></li>
						<li class="active"><a href="admin.html"> <span
								class="visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline glyphicon glyphicon-wrench"></span>
								<span class="hidden-xs">User History</span>
						</a></li>
					</ul>
				</div>
				<div class="col-lg-9 col-lg-offset-3 main">
					<h1 class="text-center page-header">User History</h1>
					<div class="account-wall">
						<form class="form-horizontal" method="post">
							</br>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>AppointmentId</th>
										<th>HealthCare Facility</th>
										<th>Date</th>
										<th>Time</th>
										<th>Prescription</th>
										<th></th>
									</tr>
								</thead>
								<c:forEach items="${appointments}" var="a">
									<tbody>
										<tr>
											<td align="left"><c:out value="${a.getAppointmentId()}" /></td>
											<td align="left"><c:out
													value="${a.getMediFacility().getName()}" /></td>
											<td align="left"><c:out
													value="${a.getAppointmentDate()}" /></td>
											<td align="left"><c:out
													value="${a.getAppointmentTime()}" /></td>
											<td align="left"><c:out value="${a.getPrescription()}" /></td>
											<td align="left" name="appointId" value="hello_rucha">
												<button type="button" class="btn btn-link"
													class="btn btn-link" data-toggle="modal"
													data-target="#myModal">Cancel Appointment</button>
											</td>
											<!-- Modal -->
											<div class="modal fade" id="myModal" role="dialog">
												<div class="modal-dialog">

													<!-- Modal content-->
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal">&times;</button>
															<h4 class="modal-title">Alert : Canceling Appointment</h4>
														</div>
														<div class="modal-body">
															<p>Are you sure you want to cancel your appointment?</p>
														</div>
														<form id="myForm" method="post">
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Close</button>
																<input type="hidden" name="bId"
																	value="${a.getAppointmentId()}" id="myField">
																<button id="myFormSubmit" class="btn btn-default"
																	type="submit" name="delete">Proceed</button>
															</div>
														</form>
													</div>

												</div>
											</div>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>

		<br /> <br />
		<p>
		<div class="col-md-5">
			<span id="successMessage"><b>${messages.success}</b></span>
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