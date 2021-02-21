<%@page import="in.co.sunrays.proj4.controller.ORSView"%>

<html>
<head>
<title>Welcome Page</title>
</head>
<body>
	<form action="<%=ORSView.WELCOME_CTL%>">
		<%@ include file="Header.jsp"%>
		<h1 align="Center"  style="padding-top: 100px;">
			<font size="10px" color="red">Welcome to ORS </font>
		</h1>

		<%
			UserBean beanUserBean = (UserBean) session.getAttribute("user");
			if (beanUserBean != null) {
				if (beanUserBean.getRoleId() == RoleBean.STUDENT) {
		%>

		<h2 align="Center" style="padding-top: 50px;">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
				Marksheet </a>
		</h2>

		<%
			}
			}
		%>

	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>