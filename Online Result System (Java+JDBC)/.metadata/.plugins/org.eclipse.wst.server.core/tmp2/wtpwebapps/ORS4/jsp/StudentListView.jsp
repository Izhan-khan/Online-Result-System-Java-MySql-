
<%@page import="in.co.sunrays.proj4.controller.StudentListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>StudentList Page</title>
<style>
td {
		padding: .5%;
}
#tc{
text-align: center;
}body {
	height: 100%;
	padding-bottom: 100px;
}
</style>
<body>
	<center>
	<%@include file="Header.jsp"%>

		<h1>Student List</h1>

		<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
		
		<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>

				
			<table width="100%">
				<tr>
					<td align="center"><label> First Name :</label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
						<label>Last Name :</label> <input type="text" name="lastName"
						value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;
						<label>Email Id:</label> <input type="text" name="email"
						value="<%=ServletUtility.getParameter("email", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=StudentListCtl.OP_SEARCH%>">
						&emsp;
						<input type="reset" value = "Reset">
					</td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					<th>S.No.</th>
					<th>College Id</th>
					<th> Name</th>
					<th>Date Of Birth</th>
					<th>Mobil No.</th>
					<th>Email ID.</th>
					<th>Edit</th>
				</tr>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<StudentBean> it = list.iterator();

					while (it.hasNext()) {

						StudentBean bean = it.next();
				%>
				<tr>
					<td id="tc"><%=index++%></td>
					<td id="tc"><%=bean.getCollegeId()%></td>
					<td id="tc"><%=bean.getFirstName()%> <%=bean.getLastName()%></td>
					<td id="tc"><%=bean.getDob()%></td>
					<td id="tc"><%=bean.getMobileNo()%></td>
					<td id="tc"><%=bean.getEmail()%></td>
					<td id="tc"><a href="StudentCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	
	</center>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
		
	<%@ include file="Footer.jsp"%>
</body>