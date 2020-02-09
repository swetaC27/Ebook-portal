<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit/Update a Book</title>
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

.control-label {<!--
	text-align: right;
	-->
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
				<c:set var="contextPath" value="${pageContext.request.contextPath}" />
				<div class="panel-heading text-center">
					<a style="float: right; color: white" href="logout.htm">Logout</a>
					<h1>Edit/Update Book</h1>
				</div>
				<div class="panel-body">
					<form:form commandName="book"
						action="${contextPath}/updatebook.htm" method="post"
						enctype="multipart/form-data">

						<div class="form-group">
							<label for="isbn" class="col-sm-3 control-label">ISBN:</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="isbn"
									path="isbn" placeholder="ISBN" disabled="true" />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="title" class="col-sm-3 control-label">Book
								Title:</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="title"
									path="title" placeholder="Book Title" disabled="true" size="30"
									pattern='[a-zA-Z0-9 ]{3,}'
									title="No special characters allowed. Atleast 3 letters." />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="author" class="col-sm-3 control-label">Author:</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="author"
									path="author" placeholder="Author" size="30"
									pattern='[a-zA-Z ]{3,}'
									title="No special characters allowed. Atleast 3 letters."  disabled="true" />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">Price
								(in USD):</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="price"
									path="price" placeholder="price" size="30" disabled="true" 
									pattern="(?:\d*\.)?\d+"
									title="No special characters allowed. Decimal numbers only." />
								<font color="red"><form:errors path="price" /></font>
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">Description:</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="description"
									path="description" placeholder="Description" size="30" disabled="true" 
									pattern='[a-zA-Z0-9 ]{3,}'
									title="No special characters allowed. Atleast longer than 3 letters." />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="addressLine1" class="col-sm-3 control-label">Genre</label>
							<div class="col-sm-8">
								<form:select path="genre_name" id="genreName" disabled="true" 
									class="form-control">
									<c:forEach var="genre" items="${genreList}">
										<form:option value="${genre.genreName}"></form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="photo" class="col-sm-3 control-label">
								Photo: </label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="photo" disabled="true"
									path="photoName" placeholder="Photo" />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="quantity" class="col-sm-3 control-label">Quantity:</label>
							<div class="col-sm-8">
								<form:input type="text" class="form-control" id="quantity"
									path="quantity" placeholder="Quantity" required="required"
									pattern="(?:\d*\.)?\d+"
									title="No special characters allowed. Decimal numbers only." />
								<font color="red"><form:errors path="quantity" /></font>
							</div>
						</div>
						<br />
						<div class="col-sm-offset-2" id="btnSubmit">
							<input type="submit" name="submit" class="btn btn-primary"
								value="Update Book Details"  style = "width: 200px;"/>
						</div>
						<div style = "float: left">
							<a href="managebooks.htm" class="btn btn-primary" style="padding: 10px;">Back</a>
						</div>
					</form:form>
				</div>
			</div>


		</div>
	</div>
</body>
</html>