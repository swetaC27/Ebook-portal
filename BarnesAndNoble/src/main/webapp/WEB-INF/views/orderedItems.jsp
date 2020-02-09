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
					<h1>Order placed successfully</h1>

				</div>

				<div class="menu form-group">
					You paid a total amount of $${sessionScope.totalAmount} <br> <br>
					Your Order id generated is ${sessionScope.orderId} <br> <br>
					<img src="<c:url value ="/resources/images/ordersuccess.png" />"
						height="120px" width="100px" /> <a href="orderhistory.htm">View
						Order History</a>
				</div>
				<center><a href="returnfromorder.htm">Return to shopping</a><center>
			</div>
		</div>
	</div>
</body>
</html>