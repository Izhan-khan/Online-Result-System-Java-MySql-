<%@page import="in.co.sunrays.proj4.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Registration Page</title>
</head>
<body>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="./js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>User Registration</h1>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
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
					<th>First Name<b style="color: red;">*</b></th>
					<td><input type="text" name="firstName" placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
						</td>
				</tr>
				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th>Last Name<b style="color: red;">*</b></th>
					<td><input type="text" name="lastName" placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
				</tr>
				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th>LoginId<b style="color: red;">*</b></th>
					<td><input type="text" name="login"
						placeholder="Enter valid Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
				</tr>
				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				
				<tr>
					<th>Password<b style="color: red;">*</b></th>
					<td><input type="password" name="password" placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
				</tr>
				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				
				<tr>
					<th>Confirm Password<b style="color: red;">*</b></th>
					<td><input type="password" name="confirmPassword" placeholder="Enter above Password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"></td>
				</tr>
				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>

					</td>
				</tr>
				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("genderReq", request)%></font></td>
				</tr>
				<tr>
					<th>Date Of Birth</th>
					<td><input id="datepicker" name="dob" readonly="readonly" placeholder="dd/mm/yy"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </td>
				</tr>

				<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<H2>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H2>

				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
					<input type="reset">
					<input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_CANCEL%>">
					</td>
				
				</tr>
		
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>