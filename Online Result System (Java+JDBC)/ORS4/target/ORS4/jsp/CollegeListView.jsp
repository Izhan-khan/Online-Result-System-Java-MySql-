
<%@page import="in.co.sunrays.proj4.controller.CollegeListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>CollegeList Page</title>
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
</head>
<body style="text-align: center;">
	<%@include file="Header.jsp"%>

	<h1>College List</h1>

	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">

		<table width="100%">
			<tr>
				<td align="center">
				<label> Name :</label> 
				<input type="text"	name="name" value="<%=ServletUtility.getParameter("name", request)%>"> &nbsp;&nbsp;&nbsp;
				<label>City :</label> 
				<input type="text" name="city"	value="<%=ServletUtility.getParameter("city", request)%>">
				<input type="submit" name="operation"	value="<%=CollegeListCtl.OP_SEARCH%>">
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
				<th>Name.</th>
				<th>Address.</th>
				<th>State.</th>
				<th>City.</th>
				<th>PhoneNo.</th>
				<th>Edit</th>
			</tr>
			
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<CollegeBean> it = list.iterator();

				while (it.hasNext()) {

					CollegeBean bean = it.next();
			%>
			<tr>
				<td id="tc"><%=index++%></td>
				<td id="tc"><%=bean.getName()%></td>
				<td id="tc"><%=bean.getAddress()%></td>
				<td id="tc"><%=bean.getState()%></td>
				<td id="tc"><%=bean.getCity()%></td>
				<td id="tc"><%=bean.getPhoneNo()%></td>
				<td id="tc"><a href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
				<td></td>
				<td align="right"><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
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
	
<%@ include file="Footer.jsp"%>
</body>
</html>