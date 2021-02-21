<%@page import="in.co.sunrays.proj4.controller.FacultyCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.controller.ORSView"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>
Add Faculty Page
</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">

<form action="<%=ORSView.FACULTY_CTL%>" method = "post">
<center>
		<%@ include file ="Header.jsp" %>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.FacultyBean"
			scope="request"></jsp:useBean>
	
			<%
			List collegeList = (List) request.getAttribute("CollegeList");
			List courseList = (List) request.getAttribute("CourseList");
			List subjectList = (List) request.getAttribute("SubjectList");
			
		%>
	
			<%
				if (bean.getId() != 0) {
			%>

			<h1>Update Faculty</h1>

			<%
				} else {
			%>

			<h1>Add Faculty</h1>

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
								<th>First Name<b style="color: red;">*</b></th>
								<td><input type="text" name="firstName" placeholder="Enter First Name"
									value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
							</tr>
							<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
							</tr>
							<tr>
								<th>Last Name<b style="color: red;">*</b></th>
								<td><input type="text" name="lastName" placeholder="Enter Last Name"
									value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
							</tr>
							<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
							</tr>
							<tr>
								<th>Qualification<b style="color: red;">*</b></th>
								<td><input type = "text" name ="qualification" placeholder="Enter Qualification"
									value ="<%=DataUtility.getStringData(bean.getQualification()) %>"></td>
							</tr>
							<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("qualification", request)%></font></td>
							</tr>
							<tr>
								<th>Email Id<b style="color: red;">*</b></th>
								<td><input type="text" name="emailId" placeholder="Enter Email Id"
									value="<%=DataUtility.getStringData(bean.getEmailId())%>"
									<%=(bean.getId() > 0) ? "readonly" : ""%>></td>
							</tr>
							<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("emailId", request)%></font></td>
							</tr>	
							<tr>
								<th>Date Of Birth</th>
								<td><input id ="datepicker" name="dob" readonly="readonly" placeholder="(dd/mm/yyyy)"
									value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
							</tr>
							<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
							</tr>	
							<tr>
								<th>Mobile Number<b style="color: red;">*</b></th>
								<td><input type="text" name="mobileNo" maxlength="10"  placeholder="Enter Mobile Number"
									value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
							</tr>
							<tr>
							<th></th>
							<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
							</tr>
							<tr>	
							<th>College Name :</th>
							<td>
							 <%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), collegeList)%>	
							</td>
							</tr>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("collegeReq", request)%></font></td>
							</tr>		
							<tr>	
							<th>Course Name :</th>
							<td>
							 <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%>	
							</td>
							</tr>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("courseReq", request)%></font></td>
							</tr>
							<tr>	
							<th>Subject Name :</th>
							<td>
							 <%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%>	
							</td>
							</tr>
							<tr>
								<th></th>
									<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("subjectReq", request)%></font></td>
							</tr>
							<tr>
							<th></th>
							<td colspan="2"><%
                    	if (bean.getId() != 0) {
                    %> <button
						type="submit" name="operation" value="<%=FacultyCtl.OP_SAVE%>">Update</button> <%
 	} else {
 %>
						<button type="submit" name="operation"
						value="<%=FacultyCtl.OP_SAVE%>">Save</button> <%
 	}
 %>&emsp; <input type="submit"
								name="operation" value="<%=FacultyCtl.OP_CANCEL%>"></td>
						</tr>
			</table>
	
</form>
</center>
<%@ include file="Footer.jsp"%>
</body>
</html>