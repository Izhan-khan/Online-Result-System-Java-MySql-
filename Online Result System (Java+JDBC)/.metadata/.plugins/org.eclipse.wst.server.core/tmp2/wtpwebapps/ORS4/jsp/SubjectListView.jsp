
<%@page import="in.co.sunrays.proj4.controller.SubjectListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.SubjectBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>SubjectList Page</title>
<style>
td {
		padding: .5%;
}
#tc{
text-align: center;
}
body {
	height: 100%;
	padding-bottom: 100px;
}
</style>
<body style="text-align: center;">
	<%@include file="Header.jsp"%>

	<h1>Subject List</h1>

	<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="POST">

		<table  width="100%">
			<tr>
				<td align="center">
				<label> Course Name :</label> 
				<input type="text"	name="courseName" value="<%=ServletUtility.getParameter("courseName", request)%>">&emsp;
				<label>Subject Name :</label> 
				<input type="text" name="subjectName"	value="<%=ServletUtility.getParameter("subjectName", request)%>">
				<input type="submit" name="operation"	value="<%=SubjectListCtl.OP_SEARCH%>">
				&emsp;
				<input type="reset" value = "Reset">
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>	
			<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
			</tr>
			</table>
			
		<table border="1" width="100%">
			<tr>
				<th>S.No.</th>
				<th>Course Name.</th>
				<th>Subject Name</th>
				<th>Description</th>
				<th>Edit</th>
			</tr>
			
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<SubjectBean> it = list.iterator();

				while (it.hasNext()) {

					SubjectBean bean = it.next();
			%>
			<tr>
				<td id="tc"><%=index++%></td>
				<td id="tc"><%=bean.getCourseName()%></td>
				<td id="tc"><%=bean.getSubjectName()%></td>
				<td id="tc"><%=bean.getDescription()%></td>
				<td id="tc"><a href="SubjectCtl?id=<%=bean.getId()%>">Edit</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					value="<%=SubjectListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
				<td></td>
				<td align="right"><input type="submit" name="operation"
					value="<%=SubjectListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
			</tr>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<%@ include file="Footer.jsp" %>
</body>
</html>