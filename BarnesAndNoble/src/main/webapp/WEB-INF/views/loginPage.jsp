<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<style>
* {
	border-radius: 0 !important;
	-moz-border-radius: 0 !important;
}

h1 {
	font-family: 'Ubuntu', sans-serif;
}

body {
	background-color: #fff;
	background-size: 100% 1.2em;
}

.container {
	
}

.panel {
	min-height: 560px;
}

.control-label {
	text-align: right;
}

.form-group {
	font-family: 'Dosis', sans-serif;
}

.btn {
	position: relative;
	display: block;
	margin: 30px auto;
	padding: 0;
	overflow: hidden;
	border-width: 0;
	outline: none;
	border-radius: 2px;
	box-shadow: 0 1px 4px rgba(0, 0, 0, .6);
	background-color: #337ab7;;
	color: white;
}

.btn>* {
	position: relative;
}

.btn {
	width: 100px;
	height: 40px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<h1>Sign-up</h1>
				</div>
				<c:set var="contextPath" value="${pageContext.request.contextPath}" />
				<div class="panel-body">
				<br/>
				<br/>
				<br/>
				
					<form:form commandName="user" action="loggedin.htm" method="post">

						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">Username:
							</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="username"
									path="userName" placeholder="Username" />
								<font color="red"><form:errors path="userName" /></font>
							</div>
						</div>
						<br />
						<br />
						<br />
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">Password:</label>
							<div class="col-sm-8">
								<form:input type="password" class="form-control" id="password"
									path="password" placeholder="Password" />
								<font color="red"><form:errors path="password" /></font>
							</div>
						</div>

						<div style = "float: left; margin-left: 300px; padding-right: 20px;">
							<input type="submit" name="submit" class="btn btn-primary"
								value="Confirm" />
						</div>
						
						<div style="float:left">
							<a href="${contextPath}" class="btn btn-primary" style="padding:10px">Cancel</a>
						</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


