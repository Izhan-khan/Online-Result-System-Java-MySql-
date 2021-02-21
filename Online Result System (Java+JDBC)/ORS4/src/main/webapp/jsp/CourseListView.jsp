<%@page import="in.co.sunrays.proj4.controller.CourseListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>CourseList Page</title>
<style>
td {
		padding: .5%;
}
body {
	height: 100%;
	padding-bottom: 100px;
}
#tc{
text-align: center;
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

		<h1>Course List</h1>

		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="POST">

			<table width="100%">
				<tr>
					<td align="center"><label>Course Name :</label> <input type="text"
						name="courseName"
						value="<%=ServletUtility.getParameter("CourseName", request)%>">
						&emsp; <label>Duration :</label> <input type="text" name="duration"
						value="<%=ServletUtility.getParameter("duration", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=CourseListCtl.OP_SEARCH%>">
						&emsp;
						<input type="reset" value = "Reset">
					</td>
				</tr>
			</table>
			<br>
			
			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
			
			<table border="1" width="100%" ;">
				<tr>
					<th>Select All <br><input type="checkbox" onclick="toggle(this)" name ="selectAll"></th>
					<th>CourseName</th>
					<th>Description</th>
					<th>Duration</th>
					<th>Edit</th>
				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<CourseBean> it = list.iterator();

					while (it.hasNext()) {

						CourseBean bean = it.next();
				%>
				<tr>
					<td id="tc"><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td id="tc"><%=bean.getCourseName()%></td>
					<td id="tc"><%=bean.getDescription()%></td>
					<td id="tc"><%=bean.getDuration()%></td>
					<td id="tc"><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
		<%--    <H1> <%=pageNo%></H1>
        <H1> <%=pageSize%></H1>
        <H1> <%=index%></H1> --%>
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
