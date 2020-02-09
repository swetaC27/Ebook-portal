<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script type="text/javascript">

function getXHRObject() {
	var xhrObject = null;
	try {
		xhrObject = new XMLHttpRequest();
	} catch (e) {
		try {
			xhrObject = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xhrObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xhrObject;
}

function fetchBooks() {	
	var xhrObject;
	xhrObject = getXHRObject();

	if(xhrObject == null) {
		alert("Ajax not supported");
		return;
	}

	var searchText = document.getElementById("name").value;
	var searchBy = document.querySelector('input[name = "search"]:checked').value;
	var query = "action=search&key=" + searchText.trim() + "&flag=" + searchBy.trim();
	xhrObject.onreadystatechange = function stateChanged() {
		if (xhrObject.readyState == 4) {
			document.getElementById("results").innerHTML = "";

			var json = JSON.parse(xhrObject.responseText);

			if (json.books.length > 0) {
				document.getElementById("results").classList.remove("setstyle");
				var tbl = document.createElement("TABLE");
				tbl.setAttribute("id", "resultTbl");
				tbl.classList.add("table");
				document.getElementById("results").appendChild(tbl);
				var trow = document.createElement("TR");
				trow.setAttribute("id", "tableRow");
				document.getElementById("resultTbl").appendChild(trow);

				var thead = document.createElement("TH");
				var txt = document.createTextNode("ISBN");
				thead.appendChild(txt);
				document.getElementById("tableRow").appendChild(thead);

				var thead = document.createElement("TH");
				var txt = document.createTextNode("TITLE");
				thead.appendChild(txt);
				document.getElementById("tableRow").appendChild(thead);
				
				var thead = document.createElement("TH");
				var txt = document.createTextNode("AUTHOR");
				thead.appendChild(txt);
				document.getElementById("tableRow").appendChild(thead);

				var thead = document.createElement("TH");
				var txt = document.createTextNode("PRICE");
				thead.appendChild(txt);
				document.getElementById("tableRow").appendChild(thead);

				for (var count = 0; count < json.books.length; count++) {
					var y = document.createElement("TR");
					y.setAttribute("id", "trow" + count);
					document.getElementById("resultTbl").appendChild(y);

					var z = document.createElement("TD");
					var t = document.createTextNode(json.books[count][0]);
					z.appendChild(t);
					document.getElementById("trow" + count).appendChild(z);

					var z = document.createElement("TD");
					var t = document
							.createTextNode(json.books[count][1]);
					z.appendChild(t);
					document.getElementById("trow" + count).appendChild(z);

					var z = document.createElement("TD");
					var t = document
							.createTextNode(json.books[count][2]);
					z.appendChild(t);
					document.getElementById("trow" + count).appendChild(z);

					var z = document.createElement("TD");
					var t = document
							.createTextNode("$"+json.books[count][3]);
					z.appendChild(t);
					document.getElementById("trow" + count).appendChild(z);
					
				}
			} else {
				document.getElementById("results").innerHTML = "Nothing Found";
				document.getElementById("results").classList.add("setstyle");
			}
		}
	};
	xhrObject.open("POST", "search.htm", true);
	xhrObject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhrObject.send(query);
	return false;
}
</script>
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

#resultTbl {
	margin-left: 10px;
}

.table {
	width: 67%;
}

.table, th, td {
	border: 1px solid black;
}

.setstyle {
	color: red;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<a style="float: right; color: white" href="logout.htm">Logout</a>
					<h1>Search Book</h1>
				</div>
				<br />
				<div class="row">
					<div class="form-group">
						<label for="searchBy" class="col-sm-4 control-label"
							style="text-align: right">Search By:</label>
						<div class="col-sm-8">
							<input type="text" id="name" name="book"
								placeholder="Enter search by clause" pattern='[a-zA-Z0-9 ]{0,}'
								title="Please enter valid input." onkeyup="return fetchBooks();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="search" value="title" checked="checked" />&nbsp;&nbsp;<label
								class="control-label">Title</label> &nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="search" value="author" />&nbsp;&nbsp;<label
								class="control-label">Author</label> &nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="search" value="isbn" />&nbsp;&nbsp;<label
								class="control-label">ISBN</label>
						</div>
					</div>
				</div>
				<div style="text-align: center">
					<h4>Search Results:</h4>
				</div>
				<center>
					<div id="results"></div>
				</center>
			</div>
		</div>
	</div>
</body>
</html>