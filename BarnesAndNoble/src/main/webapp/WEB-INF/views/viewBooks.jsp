<%@page import="com.bn.pojo.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.bn.dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	BookDAO bDAO = new BookDAO();
	List<Book> bookList = bDAO.getBooks();
	session.setAttribute("bookLst", bookList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Books</title>
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
<
style>.menu {
	font-size: 18px;
	margin-left: 44%;
	margin-top: 10%;
}

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
					<h1>Barnes & Noble books collection</h1>
				</div>
				<table class="table">
					<tr>
						<th><b>Photo</b></th>
						<th><b>ISBN</b></th>
						<th><b>Title</b></th>
						<th><b>Genre</b></th>
						<th><b>Author</b></th>
						<th><b>Price</b></th>
						<th><b>Description</b></th>
						<th><b>In stock</b></th>
						<th><b>Action</b></th>
					</tr>
					<c:forEach var="book" items="${bookLst}">
						<tr>
							<td><img
								src="<c:url value ="/resources/images/${book.photoName}" />"
								height="150px" width="120px" /></td>
							<td>${book.isbn}</td>
							<td>${book.title}</td>
							<td>${book.genre.genreName}</td>
							<td>${book.author}</td>
							<td>$${book.price}</td>
							<td>${book.description}</td>
							<td>
								<c:if test="${book.quantity <= 0}" >
									<font color="red"><c:out value = "out of stock" /></font>
								</c:if>
								<c:if test="${book.quantity>0}">
									<c:out value = "${book.quantity} copies" />
								</c:if>
							</td>
							<td><a href="editbook.htm?id=${book.bookId}">Edit</a>/ <a
								href="deletebook.htm?id=${book.bookId}">Delete</a></td>
						</tr>
					</c:forEach>

				</table>
				<div style="float: left">
						<a href="managebooks.htm" class="btn btn-primary"
							style="padding: 10px;">Back</a>
					</div>
			</div>
		</div>
	</div>


</body>
</html>