<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<title>Appointment</title>
</head>
<body>
<div class="container theme-showcase">
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><b>MediFind</b></a>
        </div>
        <ul class="nav navbar-nav navbar-right navbar-collapse collapse" id="navbar">
        	<li><a href="HomePage?username=<c:out value="${username}"/>"><span class="glyphicon glyphicon-home"></span> MediFind Home</a></li>
            <li><a href="profile?username=<c:out value="${username}"/>"><span class="glyphicon glyphicon-user"></span> ${username}</a></li>
            <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
    </div>
</nav>
<div class="col-lg-9 col-lg-offset-2 main">
			<h2 class="text-center page-header">Create an Appointment with ${hcName}</h2>
			
			<div class="account-wall">
				<form action="CreateAppointment" class="form-horizontal" method="post">

					<div class="form-group">
						<label for="appointmentDate" class="col-sm-4 control-label">AppointmentDate :</label>
						<div class="col-sm-6">
							<input name="date" id="uname" type="text" class="form-control"
								placeholder="January 20, 2017" value="">
						</div>
					</div>
					<div class="form-group">
						<label for="appointmentTime" class="col-sm-4 control-label">AppointmentTime :</label>
						<div class="col-sm-6">
							<input id="appointmentTime" name="time" type="text" class="form-control"
								placeholder="HH:MM" value="">
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-offset-4 col-lg-10">
						<div class='col-xs-3'>
							<button type="submit" class="btn btn-primary btn-default">Submit</button></div>
							<a class="btn btn-primary btn-default" 
							href="hospitaldetail?id=<c:out value="${hcid}"/>&username=<c:out value="${username}"/>">
							Cancel</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br/>
	<div class="col-md-5">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>    

     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</body>
</html>