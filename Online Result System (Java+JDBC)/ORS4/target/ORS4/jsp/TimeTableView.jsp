<%@page import="in.co.sunrays.proj4.controller.TimeTableCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<title>
Add TimeTable Page  
</title>
</head>
<body  style="height: 100%; text-align:center; padding-bottom: 50px;">

<form action="<%=ORSView.TIME_TABLE_CTL%>" method = "post">
<center>
		<%@ include file ="Header.jsp" %>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.TimeTableBean"
			scope="request"></jsp:useBean>
	
			<%
			List courseList = (List) request.getAttribute("CourseList");
			List subjectList = (List) request.getAttribute("SubjectList");
			
			/* System.out.println("********************"+request.getAttribute("CollegeList")+":::::::::::::::::::");
			System.out.println("***************"+request.getAttribute("CourseList")+":::::::::::::::::::");
			System.out.println("**********"+request.getAttribute("SubjectList")+":::::::::::::::::::"); */
			
					//System.out.println("*"+	courseList);	
						//	System.out.println("*"+subjectList);
		%>
	
			<%
				if (bean.getId() != 0) {
			%>

			<h1>Update Time-Table</h1>

			<%
				} else {
			%>

			<h1>Add Time-Table</h1>

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
		
			<input type="hidden" name="id" value="<%=bean.getId()%>"> 
			<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
			<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">



			<table>
									
							<tr>	
							<th>Course Name :</th>
							<td>
							 <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%>	
							</td>
							<tr>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("courseReq", request)%></font></td>
							</tr>
							<th>Subject Name :</th>
							<td>
							 <%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%>	
							</td>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("subjectReq", request)%></font></td>
							</tr>
							<tr>
								<th>Date Of Exam </th>
								<td><input id ="datepicker1" name="examDate" readonly="readonly" placeholder="(dd/mm/yyyy)"
									value="<%=DataUtility.getDateString(bean.getExamDate())%>"></td> 
							</tr>	
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
							</tr>
							<tr>
								<th>Exam Time</th>
								<td>
									<%
										HashMap map = new HashMap();
										map.put("8:00 to 10:00", "8:00 to 10:00 ");
										map.put("10:30 to 12:30", "10:30 to 12:30 ");
										map.put("13:00 to 15:00", "13:00 to 15:00 ");
										
										String htmlList = HTMLUtility.getList("examTime", bean.getExamTime(), map);
									%> <%=htmlList%> 
						
							</td>
							</tr>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("examTimeReq", request)%></font></td>
							</tr>
								<tr>
								<th>Semester</th>
								<td>
									<%
										HashMap sem = new HashMap();
										sem.put(1, 1);
										sem.put(2, 2);
										sem.put(3, 3);
										sem.put(4, 4);
										sem.put(5, 5);
										sem.put(6, 6);
										sem.put(7, 7);
										sem.put(8, 8);
										
								
										
										String htmlListsem = HTMLUtility.getList("semester", bean.getSemester(), sem);
									%> <%=htmlListsem%> 
						
							</td>
							</tr>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("semReq", request)%></font></td>
							</tr>
							<tr>
							<th></th>
							<td colspan="2"><%
                    	if (bean.getId() != 0) {
                    %> <button
						type="submit" name="operation" value="<%=TimeTableCtl.OP_SAVE%>">Update</button> <%
 	} else {
 %>
						<button type="submit" name="operation"
						value="<%=TimeTableCtl.OP_SAVE%>">Save</button> <%
 	}
 %> &emsp; <input type="submit"
								name="operation" value="<%=TimeTableCtl.OP_CANCEL%>"></td>
						</tr>
			</table>
	
</form>
</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>