<%@page import="in.co.sunrays.proj4.controller.FrontController"%>
<%@page import="in.co.sunrays.proj4.controller.LoginCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Login Page</title>



<script type="text/javascript">


setTimeout(function hidediv() {
	document.getElementById("22").style.display="none";
}, 6000);
   

</script>


</head>
<body >

		<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.LOGIN_CTL%>" method="post" style="padding-top: 3%">
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>Login</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>



			<div id="22" style="color: green; font-size: x-large; ">
				<%
					String msg = (String) request.getAttribute("message");
				
					//System.out.print(msg);
				
					if (msg!=null) {
				%>
				<b><%=msg%></b>
				<%
					} 
				%>
			</div>
			<div id="22" style="color: red; font-size: x-large; ">
				<%
					String sessExpired = (String) request.getAttribute("sessionExpiredMessage");
				
					//System.out.print(msg);

					
					if (sessExpired !=null) {
				%>
				<%=sessExpired%>
				<%
					} 
				%>
			</div>
			
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th>LoginId<b style="color: red;">*</b></th>
					<td><input type="text" name="login" size=30 placeholder="Enter your LoginId"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password<b style="color: red;">*</b></th>
					<td><input type="password" name="password" size=30 placeholder="Enter your Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("password", request)%>
					</font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
						&nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
								my password</b></a>&nbsp;</td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>