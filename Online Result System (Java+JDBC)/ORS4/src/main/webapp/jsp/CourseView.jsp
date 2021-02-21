<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.controller.CourseCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.controller.ORSView"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>
Add Course Page
</title>
</head>
<body  style="height: 100%; text-align:center; padding-bottom: 50px;">

<form action="<%=ORSView.COURSE_CTL%>" method = "post">
<center>
		<%@ include file ="Header.jsp" %>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CourseBean"
			scope="request"></jsp:useBean>
	
			
	
			<%
				if (bean.getId() != 0) {
			%>

			<h1>Update Course</h1>

			<%
				} else {
			%>

			<h1>Add Course</h1>

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
								<th>Course Name<b style="color: red;">*</b></th>
								<td><input type="text" name="courseName" placeholder="Enter Course Name"
									value="<%=DataUtility.getStringData(bean.getCourseName())%>"></td>
							</tr>
														<tr>
								<th></th>
								<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
							</tr>
							<tr>
								<th>Duration<b style="color: red;">*</b></th>
								<td>
								<%
								HashMap duration = new HashMap();
								duration.put("1","1");
								duration.put("2","2");
								duration.put("3","3");
								duration.put("4","4");
								
								String htmlList = HTMLUtility.getList("Duration",String.valueOf(bean.getDuration()), duration);
								%>
								<%=htmlList %>
								
								</td>
			
							</tr>
							
							<tr>
								<th></th>
								<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("duration", request)%></font></td>
							</tr>
							<tr>
								<th>Description<b style="color: red;">*</b></th>
								<td><textarea  name="description" placeholder="Course Description"  cols="30" rows="6"><%=DataUtility.getStringData(bean.getDescription())%></textarea></td>
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
						type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">Update</button> <%
 	} else {
 %>
						<button type="submit" name="operation"
						value="<%=CourseCtl.OP_SAVE%>">Save</button> <%
 	}
 %> &emsp; <input type="submit"
								name="operation" value="<%=CourseCtl.OP_CANCEL%>"></td>
						</tr>
							
			</table>
	
</form>
</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>