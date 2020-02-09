<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
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
	width: 200px;
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
					<h1>Your Cart Contents</h1>
				</div>
				<form:form action="proceedtocheckout.htm" commandName="orderSummary"
					method="POST">
					<c:choose>
						<c:when test="${!empty sessionScope.customerCart}">
							<table class="table">
								<tr>
									<th>Items</th>
								</tr>

								<c:forEach var="book" items="${customerCart}" varStatus="status">
									<tr id="row${status.index}">
										<td>
											<div>
												<div style="float: left; display: inline">
													<img
														src="<c:url value ="/resources/images/${book.photoName}" />"
														height="150px" width="100px" />
												</div>
												<div
													style="float: left; display: inline; padding-left: 20px">
													<div>BookName: ${book.title}</div>
													<br />
													<div>Author: ${book.author}</div>
													<br />
													<div>Price: $${book.price}</div>
													<br />
													<div>
														<a
															href="removeitem.htm?id=${status.index}&action=removeitem">Remove</a>
													</div>
												</div>
											</div>
										</td>

									</tr>
								</c:forEach>
								<tr></tr>
								<tr>
									<td>Total Amount: $${sessionScope.totalAmount}</td>
								</tr>
							</table>
							<br>
							<br>
							<a href="displaybooks.htm" class="btn btn-primary" style="padding-top: 10px; display:inline; float:left; margin-left: 10px"> Continue
								Shopping </a>&nbsp;&nbsp;
                <input class="btn btn-info" style="display:inline" type="submit" value="Proceed to checkout">
						</c:when>

						<c:otherwise>
							<h2><center>Your shopping cart is empty<center></h2>
							<p>
								<center><a href="displaybooks.htm">Return to shopping</a><center>
							</p>
						</c:otherwise>

					</c:choose>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>