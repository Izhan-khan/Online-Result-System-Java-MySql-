<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.TimeTableListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.TimeTableBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>TimeTableList Page</title>
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
	
		<h1>TimeTable List</h1>

	<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.TimeTableBean"
			scope="request"></jsp:useBean>


		<form action="<%=ORSView.TIME_TABLE_LIST_CTL%>" method="POST">

<%
			List courseList = (List) request.getAttribute("CourseList");
			List subjectList = (List) request.getAttribute("SubjectList");
			
	
			
			/* System.out.println("********************"+request.getAttribute("CollegeList")+":::::::::::::::::::");
			System.out.println("***************"+request.getAttribute("CourseList")+":::::::::::::::::::");
			System.out.println("**********"+request.getAttribute("SubjectList")+":::::::::::::::::::"); */
			
					//System.out.println("*"+	courseList);	
					//System.out.println("*"+subjectList);
		%>



			<table width="100%" style="text-align: center;" >
				<tr>	
							
							<td><label>Course Id :</label>
							 <%=HTMLUtility.getList("courseId", ServletUtility.getParameter("courseId", request), courseList)%>	
								&emsp;&emsp;
							
								<label>Subject Id :</label>
							  <%=HTMLUtility.getList("subjectId", ServletUtility.getParameter("subjectId", request), subjectList)%>	
								&emsp;
							
							<%-- 
							</th>
							<td><b>Subject Id :</b>
							 <%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%>	
							&nbsp;
							--%>
							
							<input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_SEARCH%>">
							&emsp;
							<input type="reset" value = "Reset">
							
							</td>
						</tr>	
			</table>
			<br>
			
			
		<font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				
			
			<table border="1" width="100%" ;">
				<tr>
					<th>Select All<br><input type="checkbox" onclick="toggle(this)"> </th>
					<th>Course Name</th>
					<th>Subject Name</th>
					<th>Semester</th>
					<th>Exam Date</th>
					<th>Exam Time</th>
					<th>Edit</th>
				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<TimeTableBean> it = list.iterator();

					while (it.hasNext()) {

						TimeTableBean bean1 = it.next();
				%>
				<tr>
					<td id="tc"><input type="checkbox" name="ids"
						value="<%=bean1.getId()%>"></td>
					<td id="tc"><%=bean1.getCourseName()%></td>
					<td id="tc"><%=bean1.getSubjectName()%></td>
					<td id="tc"><%=bean1.getSemester()%></td>
					<td id="tc"><%=bean1.getExamDate()%></td>
					<td id="tc"><%=bean1.getExamTime()%></td>
					<td><a href="TimeTableCtl?id=<%=bean1.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>
			<br>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
					<td><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
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
	<br>
	
	<%@include file="Footer.jsp"%>
</body>
</html>
