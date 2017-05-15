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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>DoctorInfo</title>
</head>
<body>

    <div class="container theme-showcase" role="main">
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
	<form action="HomePage" method="post">
	    <div class="jumbotron">
		<h2>Displaying Doctors with HealthCare</h2>
		</div>
		<input type="hidden" id="uname" value="${username}">
	</form>
	<br/>
	<div id="mydiv" class="account-wall">
	</br>
        <table class="table table-striped">
            <thead><tr>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Hours</th>
                <th>Gender</th>
                <th>SpecialityName</th>
                <th></th>
            </tr></thead>
            <c:forEach items="${doctors}" var="doc" > 
                <tbody><tr>
                    <td><c:out value="${doc.getFirstName()}" /></td>
                    <td><c:out value="${doc.getLastName()}" /></td>
                    <td><c:out value="${doc.getHour()}" /></td>
                    <td><c:out value="${doc.getGender()}" /></td>
                    <td><c:out value="${doc.getSpeciality().getSpeciality()}" /></td>
                </tr></tbody>
            </c:forEach>
       </table>    
      </div> 
      <br/><br/>
      <a class="btn btn-primary" href="CreateAppointment?hcid=<c:out value="${hcid}"/>&username=<c:out value="${username}"/>">Make an Appointment</a>
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
	</body>
</html>
