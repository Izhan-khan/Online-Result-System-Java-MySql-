<%@page import="in.co.sunrays.proj4.controller.SubjectCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.controller.ORSView"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>
Add Subject Page
</title>
</head>
<body  style="height: 100%; text-align:center; padding-bottom: 50px;">

<form action="<%=ORSView.SUBJECT_CTL%>" method = "post">
<center>
		<%@ include file ="Header.jsp" %>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.SubjectBean"
			scope="request"></jsp:useBean>
	
			<%
			List courseList = (List) request.getAttribute("CourseList");
			
			//System.out.println("***************"+request.getAttribute("CourseList")+":::::::::::::::::::");
			
			
				//	System.out.println("*"+	courseList);	
		%>
	
			<%
				if (bean.getId() != 0) {
			%>

			<h1>Update Subject</h1>

			<%
				} else {
			%>

			<h1>Add Subject</h1>

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
							<th>Course Id :</th>
							<td>
							<% System.out.println(courseList); %>
							 <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%>	
							</td>
							</tr>
							<tr>
							<th></th>
								<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("courseReq", request)%></font></td>
							</tr>
							<tr>
								<th>Subject Name<b style="color: red;">*</b></th>
								<td><input type="text" name="subjectName" placeholder="Enter Subject Name"
									value="<%=DataUtility.getStringData(bean.getSubjectName())%>"></td>
				
							</tr>
							<tr>
								<th></th>
								<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("subjectName", request)%></font></td>
							</tr>
							<tr>
							<th>Description<b style="color: red;">*</b></th>
							<td><textarea name = "description" placeholder="Description" cols="30" rows="6"><%=DataUtility.getStringData(bean.getDescription())%></textarea></td>
							</tr>
							<tr>
								<th></th>
								<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
							</tr>
							<tr>
							<th></th>
							<td colspan="2"><%
                    	if (bean.getId() != 0) {
                    %> <button
						type="submit" name="operation" value="<%=SubjectCtl.OP_SAVE%>">Update</button> <%
 	} else {
 %>
						<button type="submit" name="operation"
						value="<%=SubjectCtl.OP_SAVE%>">Save</button> <%
 	}
 %>&emsp;
								 <input type="submit"
								name="operation" value="<%=SubjectCtl.OP_CANCEL%>"></td>
						</tr>
			</table>
	
</form>
</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>