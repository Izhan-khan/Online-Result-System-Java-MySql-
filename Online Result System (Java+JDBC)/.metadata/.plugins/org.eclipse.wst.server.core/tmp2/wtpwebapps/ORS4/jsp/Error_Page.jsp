<%@page import="in.co.sunrays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
#btn{
background-color: white;
border :2px solid #21618C ;
color:#21618C;
padding: .8em 1em;
font-size: medium;
border-radius: .5em;
cursor: pointer;
transition-duration:0.5s;
}
#btn:hover {
background-color: #21618C;
border : none;
color : white;
padding: .8em 1em;
font-size: medium;
border-radius: .5em;
}
-->
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
<div style="text-align: center;top: 50%;padding-top: 10%;">
<img src="<%=ORSView.APP_CONTEXT%>/img/error.png" width="100px">
<h1>Error has been Occurred</h1>
<a href="<%=ORSView.WELCOME_CTL%>"><button id="btn" value="oo">Back to Welcome Page</button></a>
</div>
</body>
</html>