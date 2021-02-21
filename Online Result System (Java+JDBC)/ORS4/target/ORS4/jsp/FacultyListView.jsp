<%@page import="in.co.sunrays.proj4.controller.FacultyListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.FacultyBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>FacultyList Page</title>
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

<script type="text/javascript">

function toggle(s){
	var allCheckboxes = document.getElementsByName("ids");
	for (var i = 0; i < allCheckboxes.length; i++) {
		allCheckboxes[i].checked = s.checked;
	}
}

</script>

<body style="text-align: center;">

	<%@include file="Header.jsp"%>
	
		<h1>Faculty List</h1>

		<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="POST">

			<table style="width: 100%;">
				<tr>
					<td align="center">
						
						<label>Faculty First Name :</label> <input type="text"
						name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						&emsp;
						
						<label>Faculty Last Name :</label> <input type="text"
						name="lastName"
						value="<%=ServletUtility.getParameter("lastName", request)%>">
						&emsp;
						
						<label>Subject :</label> <input type="text"
						name="subjectName"
						value="<%=ServletUtility.getParameter("subjectName", request)%>">
						&emsp;
						
						<label>Course :</label> <input type="text" name="courseName"
						value="<%=ServletUtility.getParameter("courseName", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_SEARCH%>">
						
						<input type="reset" value = "Reset">
					
						</td>
				</tr>
			</table>
			<br>
			
		
			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				
			<table border="1" width="100%" ;">
				<tr>
					<th>Select All <br><input type="checkbox" onclick="toggle(this)"> </th>
					<th>Faculty Name</th>
					<th>Qualification</th>
					<th>Email Id</th>
					<th>Date of Birth</th>
					<th>Mobile Number</th>
					<th>College Name</th>
					<th>Course Name</th>
					<th>Subject Name</th>
					<th>Edit</th>
				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<FacultyBean> it = list.iterator();

					while (it.hasNext()) {

						FacultyBean bean = it.next();
				%>
				<tr>
					<td id="tc" ><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td id="tc"><%=bean.getFirstName() +" "+ bean.getLastName()%></td>
					<td id="tc"><%=bean.getQualification()%></td>
					<td id="tc"><%=bean.getEmailId()%></td>
					<td id="tc"><%=bean.getDob()%></td>
					<td id="tc"><%=bean.getMobileNo()%></td>
					<td id="tc"><%=bean.getCollegeName()%></td>
					<td id="tc"><%=bean.getCourseName()%></td>
					<td id="tc"><%=bean.getSubjectName()%></td>
					<td id="tc"><a href="FacultyCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
		  <%--  <H1> <%=pageNo%></H1>
        <H1> <%=pageSize%></H1>
        <H1> <%=index%></H1>  --%>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<%@include file="Footer.jsp"%>
</body>
</html>
