<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dummy.common.xml.XMLReader"%>
<%@page import="java.util.HashMap"%>
<%
	response.setHeader("P3P", "CP='CAO PSA CONi OTR OUR DEM ONL'");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
	
	String language = (String) session.getAttribute("language");
	String contextPath = getServletContext().getRealPath("/WEB-INF/xml/invalid.xml");
	HashMap<String, String> map = new XMLReader(language==null?"ko":language).read(contextPath);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title></title>

	<meta name="description"	content="">
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" 		content="width=device-width, initial-scale=1.0, maximum-scale=1.0">



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="js/user/custom.js" ></script>


<link href="https://fonts.googleapis.com/css?family=Noto+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/user/reset.css">
<link rel="stylesheet" type="text/css" href="css/user/style.css">
<link rel="stylesheet" type="text/css" href="css/user/index.css">
<link rel="stylesheet" type="text/css" href="css/user/layout.css">


</head>

<body>
	
	<img src="asset/image/invalid.png" >
	<h4><%=map.get("invalidApproach") %></h4>

	<jsp:include page="footer.jsp"></jsp:include>	
	
</body>

</html>