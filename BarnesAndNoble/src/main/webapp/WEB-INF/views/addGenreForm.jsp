<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Genre</title>
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

.control-label {
	text-align: right;
	padding-top: 7px;
}

.panel {
	min-height: 560px;
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

.menu {
	font-size: 18px;
	margin-left: 44%;
	margin-top: 10%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<a style="float: right; color: white" href="logout.htm">Logout</a>
					<h1>Add Genre</h1>
				</div>
				<c:set var="contextPath" value="${pageContext.request.contextPath}" />
				<form:form commandName="genre"
					action="${contextPath}/genreadded.htm" method="post">
					<center></center>
					<div class="form-group">
						<label for="genreName" class="col-sm-4 control-label">Genre
							Name:</label>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="genreName"
								path="genreName" placeholder="Genre Name" ize="30"
								pattern='[a-zA-Z ]{2,}'
								title="No special characters allowed. Atleast longer than 2 letters." />
							<font color="red"><form:errors path="genreName" /></font>
						</div>
					</div>
					<br />
					<br />
					<div class="col-sm-offset-4 col-sm-2" id="btnSubmit">
						<input type="submit" name="submit" class="btn btn-primary"
							value="Add Genre" />
					</div>
					<div style="float: left">
						<a href="managebooks.htm" class="btn btn-primary"
							style="padding: 10px;">Back</a>
					</div>
				</form:form>

			</div>
		</div>
	</div>



</body>
</html>