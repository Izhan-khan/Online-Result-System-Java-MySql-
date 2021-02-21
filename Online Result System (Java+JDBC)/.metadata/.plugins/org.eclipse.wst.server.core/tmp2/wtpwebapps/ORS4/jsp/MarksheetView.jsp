<%@page import="in.co.sunrays.proj4.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>
Add Marksheet Page 
</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">

	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("studentList");
		%>

		<center>
			<%
				if (bean.getId() != 0) {
			%>

			<h1>Update Marksheet</h1>

			<%
				} else {
			%>

			<h1>Add Marksheet</h1>

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


			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table>
				<tr>
					<th>Rollno<b style="color: red;">*</b></th>
					<td><input type="text" name="rollNo" placeholder="Enter Roll Number"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>> 
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
				</tr>
				<tr>
					<th>Name<b style="color: red;">*</b></th>
					<td><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), l)%></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("studentIdReq", request)%></font></td>
				</tr>
				<tr>
					<th>Physics</th>
					<td><input type="text" name="physics" placeholder="Enter Physics Marks"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>"></td>
				</tr>		
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>
				<tr>
					<th>Chemistry</th>
					<td><input type="text" name="chemistry" placeholder="Enter Chemisrty Marks"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>
				<tr>
					<th>Maths</th>
					<td><input type="text" name="maths" placeholder="Enter Maths Marks"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2">
					  <%if(bean.getId()==0){ %>
        
            <button type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>">Save</button>
            
            <% }else{ %>
            
           <button type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>">Update</button>
            
            <% }%>
					
					
					 <%
 	if (bean.getId() > 0) {
 %><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_DELETE%>"> <%
 	}
 %> <input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>