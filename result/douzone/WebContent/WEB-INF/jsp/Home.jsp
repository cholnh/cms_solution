<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%
	response.setHeader("P3P", "CP='CAO PSA CONi OTR OUR DEM ONL'");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>home</title>
	<!-- css contents -->
	
</head>

<body>
	
	<!-- html contents -->
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- js contents -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/xhr.js"></script>
</body>
</html>