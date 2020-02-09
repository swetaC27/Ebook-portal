<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Displaying Cart Items</title>
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
					<a style="float: right; color: white" href="logout.htm">Logout</a>
					<h1>Your Order Summary</h1>
				</div>
				<div>
					<form method="post" action="confirmorder.htm">
						<div style="text-align: center; font-size: 20px;">
							<b><c:out value="${customer.firstName}" /> <c:out
									value="${customer.lastName}" /></b>
						</div>
						<div style="text-align: center">
							<b><c:out value="Shipping Address:" /></b><br/>
							<c:out value="${customer.address.addressLine1}," /><br/>
							<c:out value="${customer.address.city}," /><br/>
							<c:out value="${customer.address.zipCode}," /><br/>
							<c:out value="${customer.address.state}," /><br/>
							<c:out value="${customer.address.country}" />
						</div>

						<table class="table" align="center" style="width: 50%">
							<tr>
								<th>Items</th>
							</tr>

							<c:forEach var="book" items="${customerCart}" varStatus="status">
								<tr id="row${status.index}">
									<td>
										<div>
											<div style="float: left; display: inline; padding-left: 20px">
												<div>BookName: ${book.title}</div>
												<br />
												<div>Author: ${book.author}</div>
												<br />
												<div>Price: $${book.price}</div>
												<br />
											</div>
											<div style="float: left; display: inline; padding-left: 20px">
												<img
													src="<c:url value ="/resources/images/${book.photoName}" />"
													height="120px" width="90px" />
											</div>
										</div>
									</td>

								</tr>
							</c:forEach>
							<tr></tr>
							<tr>
								<td>Total Amount: $${totalAmount}</td>
							</tr>
						</table>
						<input class="btn btn-success" style = "display: inline; float: left; margin-left: 500px" type="submit" value="Place Order">
						<a style="padding-top: 10px; display: inline; float: left; margin-left: 10px"
							class="btn btn-danger" href="cancelorder.htm">Cancel Order</a>
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>