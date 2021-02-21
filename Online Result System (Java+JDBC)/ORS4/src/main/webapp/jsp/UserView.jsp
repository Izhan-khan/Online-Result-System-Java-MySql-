<%@page import="in.co.sunrays.proj4.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>
Add User Page 
</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">
	<form action="<%=ORSView.USER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>


		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
			<%
				if (bean.getId() != 0) {
			%>

			<h1>Update User</h1>

			<%
				} else {
			%>

			<h1>Add User</h1>

			<%
				}
			%>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>

			<%-- <p><%=bean.getId()%></p>--%>
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
					<td><input type="text" name="firstName" placeholder="Enter your First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th>Last Name<b style="color: red;">*</b></th>
					<td><input type="text" name="lastName" placeholder="Enter your Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th>LoginId<b style="color: red;">*</b></th>
					<td><input type="text" name="login" placeholder="Enter your Login Id"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password<b style="color: red;">*</b></th>
					<td><input type="password" name="password" placeholder="Enter your Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th>Confirm Password<b style="color: red;">*</b></th>
					<td><input type="password" name="confirmPassword" placeholder="Confirm your Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
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
					<th>Role</th>
					<td><%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"><%=ServletUtility.getErrorMessage("roleReq", request)%></font></td>				
				</tr>
				<tr>
					<th>Date Of Birth </th>
					<td><input id="datepicker" name="dob" readonly="readonly"  placeholder="(dd/mm/yyyy)"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2">
						<%
							if (bean.getId() != 0) {
						%> <button type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>">Update</button> <%
 	} else {
 %> 					<button
						type="submit" name="operation" value="<%=UserCtl.OP_SAVE%>">Save</button>

						<%
							}
						%><input type="submit" name="operation"
						value="<%=UserCtl.OP_CANCEL%>">
					</td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>