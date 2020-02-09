<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Barnes & Noble</title>
<style>
.backPicture {
	background-image: url('/store/resources/images/Homepage.jpg');
	background-size: 100%;
 } 
 .signin {
    font-size: 27px;
    margin-right: 15px;
    padding-bottom: 5px;
    margin: 0 15px 10px 0;
    display: inline-block;
    border-bottom: 2px solid transparent;
    color: #fff;
    border-color: #1161ee;
    text-transform: uppercase;
    cursor: pointer;
 }
 
 .logo {
    font-size: 35px;
    margin-right: 15px;
    padding-bottom: 5px;
    margin: 0 15px 10px 0;
    display: inline-block;
    border-bottom: 2px solid transparent;
    color: #fff;
    text-transform: uppercase;}
</style>
</head>
<body>
	<div class="backPicture" style="width: 1255px; height: 570px;">
	
		<div class = "logo">
			BARNES & NOBLE ONLINE BOOK STORE
		</div>
		<div style="float: right">
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<a href="${contextPath}/signup.htm"><label class="signin">Sign Up </label> </a> 
			<a href="${contextPath}/login.htm"><label class="signin">Login</label> </a>
		</div>
	</div>


</body>
</html>


