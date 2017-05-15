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
<title>Details</title>
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
        	<li><a href="HomePage?username=<c:out value="${username}"/>&zipcode=<c:out value="${zipcode}"/>">
        	<span class="glyphicon glyphicon-home"></span> MediFind Home</a></li>
            <li><a href="profile?username=<c:out value="${username}"/>"><span class="glyphicon glyphicon-user"></span> ${username}</a></li>
            <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
    </div>
</nav>
		<div class="jumbotron">
			<h2>HealthCare Facility Information</h2>
		</div>
	<div class="account-wall">
		<div class="row">
			<div class="form-group">
				<label class="col-sm-2 control-label">HealthCare Name : </label>
				<div class="col-sm-6">
					<label class="form-control">${hc.getName()}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-sm-2 control-label">HealthCare Owner : </label>
				<div class="col-sm-6">
					<label class="form-control">${hc.getOwner().getOwnerName()}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-sm-2 control-label">HealthCare Street : </label>
				<div class="col-sm-6">
					<label class="form-control">${hc.getAddress().getStreet()},&nbsp;
						${hc.getAddress().getCity()},&nbsp;${hc.getAddress().getState()},&nbsp;
						${hc.getAddress().getZipCode()}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-sm-2 control-label">HealthCare Parking : </label>
				<div class="col-sm-6">			
					<c:if test="${hc.isParking()}"><label class="form-control">Available</label></c:if>
					<c:if test="${not hc.isParking()}"><label class="form-control">Unavailable</label></c:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-sm-2 control-label">HealthCare Hours : </label>
				<div class="col-sm-6">
					<label class="form-control">${hc.getHours()}</label>
				</div>
			</div>
		</div>
		</div>
		 </br> </br>
		<div class="row">
		<a class="btn btn-primary"  href="CreateRating?hcid=<c:out value="${hc.getMediFacilityId()}"/>&username=<c:out value="${username}"/>">Write a Review</a>
		<a class="btn btn-primary" href="CreateAppointment?hcid=<c:out value="${hc.getMediFacilityId()}"/>&username=<c:out value="${username}"/>">Make an Appointment</a>
		<a class="btn btn-primary" href="DoctorInfo?hcid=<c:out value="${hc.getMediFacilityId()}"/>&username=<c:out value="${username}"/>">View All Doctors</a>
		</div>
		<br /> <br />
		<h2>Reviews</h2>
		<div class="account-wall">
        <table class="table table-striped">
            <thead><tr>
                <th>Review Comments</th>
                <th>Rating</th>
            </tr></thead>
            <c:forEach items="${rList}" var="r" > 
                <tbody><tr>
                    <td><c:out value="${r.getReviews()}" /></td>
                    <td><c:out value="${r.getRatings()}" /></td>
                </tr></tbody>
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