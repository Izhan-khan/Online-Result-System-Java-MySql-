<%@page import="in.co.sunrays.proj4.controller.MyProfileCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>My Profile Page</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">
	<div>
	<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

				
			<h1>My Profile</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			<!--	<p><%= bean.getId() %> </p>-->
			</H2>
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table align="center">
				<tr>
					<th>LoginId<b style="color: red;">*</b></th>
					<td><input type="text" name="login" placeholder="Enter your Login Id"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						readonly="readonly">
				</tr>
				<tr>
					<th><td><font style="color: red;"> <%=ServletUtility.getErrorMessage("login", request)%></font></td></th>
				</tr>
	
				<tr>
					<th>First Name<b style="color: red;">*</b></th>
					<td><input type="text" name="firstName" placeholder="Enter your First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th>Last Name<b style="color: red;">*</b></th>
					<td><input type="text" name="lastName" placeholder="Enter your Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>">
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
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
					<th>Mobile No<b style="color: red;">*</b></th>
					<td><input type="text" name="mobileNo" placeholder="Enter your Mobile Number"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>
				<tr>
					<th>Date Of Birth </th>
					<td><input id="datepicker" name="dob" readonly="readonly" placeholder="(dd/mm/yyyy)"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </a><font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
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

					<td colspan="2"><br>
					<input type="submit" name="operation"
						value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=MyProfileCtl.OP_SAVE%>"> &nbsp;</td>
				</tr>
			</table>
	</form>
	</div>
	
	<%@ include file="Footer.jsp"%>
</body>
</html>
