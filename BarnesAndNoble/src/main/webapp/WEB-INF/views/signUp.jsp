<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Sign Up</title>
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
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<h1>Sign-up</h1>
				</div>
				<div class="panel-body">
					<form:form commandName="customer"
						action="${contextPath}/signupsuccess.htm" method="post"
						class="form-horizontal">

						<div class="form-group">
							<label for="roleName" class="col-sm-3 control-label">Role</label>
							<div class="col-sm-8">
								<form:select path="roleName" id="roleName" class="form-control">
									<form:option value="customer">Customer</form:option>
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label for="first" class="col-sm-3 control-label">First
								Name</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="first"
									path="firstName" placeholder="First Name" size="30"
									pattern='[a-zA-Z]{2,}'
									title="No special characters allowed. Atleast longer than 2 letters." />
								<font color="red"><form:errors path="firstName" /></font>
							</div>
						</div>
						<div class="form-group">
							<label for="last" class="col-sm-3 control-label">Last
								Name</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="last"
									path="lastName" placeholder="Last Name" size="30"
									pattern='[a-zA-Z ]{2,}'
									title="No special characters allowed. Atleast longer than 2 letters." />
								<font color="red"><form:errors path="lastName" /></font>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">Email</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="email"
									path="emailId" size="30" pattern="^[^@]+@[^@]+\.[^@]+$" title="Please enter a valid email address." placeholder="Email ID" />
								<font color="red"><form:errors path="emailId" /></font>
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">Username</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="username"
									path="userName" placeholder="Username" size="30" pattern='[a-zA-Z0-9 ]{2,}' title="No special characters allowed. Atleast longer than 2 letters."/>
								<font color="red"><form:errors path="userName" /></font>
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">Password</label>
							<div class="col-sm-8">
								<form:input type="password" class="form-control" id="password"
									path="password" placeholder="Password" size="30" pattern='[a-zA-Z0-9 ]{2,}' title="No special characters allowed. Atleast longer than 2 letters."  />
								<font color="red"><form:errors path="password" /></font>
							</div>
						</div>
						<div class="form-group">
							<label for="addressLine1" class="col-sm-3 control-label">Address
								Line 1 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="addressLine1"
									name="addressLine1" placeholder="Address Line 1"  required="required"/>
								
							</div>
						</div>
						<div class="form-group">
							<label for="addressLine2" class="col-sm-3 control-label">Address
								Line 2: </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="addressLine2"
									name="addressLine2" placeholder="Address Line 2"
									size="30" pattern='[a-zA-Z0-9,. ]{2,}' title="No special characters allowed. Atleast longer than 2 letters."
									/>
							</div>
						</div>
						<div class="form-group">
							<label for="city" class="col-sm-3 control-label">City: </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="city" name="city" required="required"
								size="30" pattern='[a-zA-Z0-9,. ]{2,}' title="No special characters allowed. Atleast longer than 2 letters."
									placeholder="City" />
							</div>
						</div>
						<div class="form-group">
							<label for="zip" class="col-sm-3 control-label">Zip: </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="zip" name="zip" required="required"
									size="30" pattern="[0-9]{3,6}" title="Enter a valid zip code."
									placeholder="Zip" />
							</div>
						</div>
						<div class="form-group">
							<label for="state" class="col-sm-3 control-label">State:
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="state" name="state" required="required"
									placeholder="State" pattern="[a-zA-Z ]{2,}" title="No special characters allowed. Atleast longer than 2 letters." />
							</div>
						</div>

						<div class="form-group">
							<label for="country" class="col-sm-3 control-label">Country:
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="country" required="required"
								size="30" pattern="[a-zA-Z ]{2,}" title="No special characters allowed. Atleast longer than 2 letters."
									name="country" placeholder="Country" />
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-sm-3 control-label">Phone:
							</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="phone"
									path="phone" placeholder="Phone"
									size="30" pattern="\b\d{3}[-.]?\d{3}[-.]?\d{4}\b" title="Please enter a valid phone number"
									 />
								<font color="red"><form:errors path="phone" /></font>
							</div>
						</div>
						<div class="form-group">
							<label for="gender" class="col-sm-3 control-label">Gender:</label>
							<div class="col-sm-8">
								<form:radiobutton path="gender" value="Male" checked="true" />
								Male
								<form:radiobutton path="gender" value="Female" />
								Female
							</div>
						</div>


							<div style="margin-left:500px; float:left; display:inline;">
								<input type="submit" class="btn btn-primary"
									value="Sign-up" />
							</div>
							
							<div style="padding-left:10px; float:left; display:inline;">
								<a href="${contextPath}" class="btn btn-primary"
									style="padding: 10px">Cancel</a>
							</div>
							
						


					</form:form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>