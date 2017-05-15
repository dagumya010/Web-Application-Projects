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
<title>Profile</title>
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
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${username}</a></li>
            <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
    </div>
</nav>
<br> <br>
<div class="container-fluid text-center">
    <div class="row content">
    <br><br>
        <div class="col-xs-1 col-sm-2 col-md-2 sidebar sidebar-xs">
            <ul class="nav nav-sidebar">
                <li>
                    <a href="HomePage?username=<c:out value="${username}"/>">
                        <span class="visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline glyphicon glyphicon-home"></span>
                        <span class="hidden-xs">Home</span>
                    </a>
                </li>
                <li  class="active">
                    <a href="profile?username=<c:out value="${username}"/>">
                        <span class="visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline glyphicon glyphicon-user"></span>
                        <span class="hidden-xs">Profile</span>
                    </a>
                </li>
                <li>
                    <a href="UserHistory?username=<c:out value="${username}"/>">
                        <span class="visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline glyphicon glyphicon-wrench"></span>
                        <span class="hidden-xs">User History</span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-lg-7 col-lg-offset-3 main">
            <h1 class="text-center page-header">Profile</h1>
            <div class="account-wall">
                <form action="profile" class="form-horizontal" method="post">
        
                    <div class="form-group">
                        <label class="col-sm-4 control-label">First Name :</label>
                        <div class="col-sm-7">
                            <input type="text" name="fname" class="form-control" placeholder="First Name" value="${firstName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Last Name :</label>
                        <div class="col-sm-7">
                            <input type="text" name="lname" class="form-control" placeholder="Last Name" value="${lastName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-4 control-label">Email :</label>
                        <div class="col-sm-7">
                            <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email" value="${email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-4 control-label">Phone :</label>
                        <div class="col-sm-7">
                            <input name="phone" type="text" class="form-control" id="phone" placeholder="Phone Number" value="${phone}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="street" class="col-sm-4 control-label">Street :</label>
                        <div class="col-sm-7">
                            <input name="street" type="text" class="form-control" id="street" placeholder="Address Line 1" value="${street}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="city" class="col-sm-4 control-label">City :</label>
                        <div class="col-sm-7">
                            <input name="city" type="text" class="form-control" id="city" placeholder="City" value="${city}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="state" class="col-sm-4 control-label">State :</label>
                        <div class="col-sm-7">
                            <input name="state" type="text" class="form-control" id="city" placeholder="State" value="${state}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="zipcode" class="col-sm-4 control-label">ZipCode :</label>
                        <div class="col-sm-7">
                            <input name="zipcode" type="number" class="form-control" id="zipcode" placeholder="Zip" value="${zipcode}">
                        </div>
                    </div>
                    <%-- <div class="form-group">
                        <label for="insurance" class="col-sm-4 control-label">Insurance :</label>
                        <div class="col-sm-7">
                            <input name="insuranceName" type="text" class="form-control" id="insuranceName" value="${insuranceName}">
                        </div>
                    </div>  --%>
                    <div class="form-group">
						<label for="insurancename" class="col-sm-4 control-label">Insurance
							Provider :</label>
						<div class="col-sm-8">
							<select name="insuranceId">
								<c:forEach items="${insurances}" var="insurance">
									<option value="${insurance.getInsuranceId()}" <c:if test="${insurance.getName() eq insuranceName}">selected="selected"</c:if>>
									<c:out value="${insurance.getName()}" /></option>
								</c:forEach>
							</select>
						</div>
					</div> 
                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-10">
                            <button type="submit" class="btn btn-primary btn-default" name="update">Update</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

	<br/><br/>
	<p>
		<div class="col-md-5">
		<span  id="successMessage"><b>${messages.success}</b></span>
		</div>
	</p>
	
</div>
	
	<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>