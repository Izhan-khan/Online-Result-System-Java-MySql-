<%@page import="in.co.sunrays.proj4.controller.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Change your Password Page</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">
	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>Change Password</h1>


			<H3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H3>
			<H3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H3>
			

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
					<th>Old Password<b style="color: red;">*</b></th>
					<td><input type="password" name="oldPassword" placeholder="Enter your Password"
						value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
				</tr>
				<tr>
					<th>New Password<b style="color: red;">*</b></th>
					<td><input type="password" name="newPassword" placeholder="Enter New Password"
						value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
				</tr>
				<tr>
					<th>Confirm Password<b style="color: red;">*</b></th>
					<td><input type="password" name="confirmPassword" placeholder="Confirm New Password"
						value=<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
						&nbsp; <input type="submit" name="operation"
						value="<%=ChangePasswordCtl.OP_SAVE%>"> &nbsp;</td>
				</tr>

			</table>
			
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>