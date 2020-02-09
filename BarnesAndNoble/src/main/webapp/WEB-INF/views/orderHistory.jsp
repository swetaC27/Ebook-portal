<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
</head>
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
<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<a style="float: right; color: white" href="logout.htm">Logout</a>
					<h1>View all the orders</h1>
				</div>

				<a style="margin-left: 1050px;font-size: 15px; font-weight: bold;" href="report.pdf">View pdf
					<img src="<c:url value ="/resources/images/PDF_file_icon.png" />" width="25px" height="30px">
					</a><br> <br>

				<table class="table">
					<tr>
						<th>Book Title</th>
						<th>Author</th>
						<th>Price</th>
						<th>Date Ordered</th>
					</tr>

					<c:forEach var="od" items="${od}">
						<c:if test="${od.customerId==sessionScope.userId}">
							<tr id="row">

								<td>${od.bookName}</td>
								<td>${od.bookAuthor}</td>
								<td>$${od.price}</td>
								<td>${od.orderId.orderDate}</td>


							</tr>
						</c:if>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>

</body>
</html>