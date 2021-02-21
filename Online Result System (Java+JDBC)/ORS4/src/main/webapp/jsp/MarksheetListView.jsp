<%@page import="in.co.sunrays.proj4.controller.MarksheetListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>
Marksheet List Page 
</title>
</head>
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
	
		<h1>Marksheet List</h1>

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">

			<table style="width: 100%">
				<tr>
					<td align="center"><label> Name :</label> <input type="text"
						name="name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						&emsp; <label>RollNo :</label> <input type="text" name="rollNo"
						value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_SEARCH%>">
						&emsp;
						<input type="reset" value = "Reset">
					</td>
				</tr>
			</table>
			<br>
			
			<table style="width: 100%">
			<tr>	
			<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
			</tr>
			</table>
			
			
			<table border="1" width="100%" ;>
				<tr>
					<th>Select All<br><input type="checkbox" onclick="toggle(this)"></th>
					<th>Roll No.</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Edit</th>
				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<MarksheetBean> it = list.iterator();

					while (it.hasNext()) {

						MarksheetBean bean = it.next();
				%>
				<tr>
					<td id="tc"><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td id="tc"><%=bean.getRollNo()%></td>
					<td id="tc"><%=bean.getName()%></td>
					<td id="tc"><%=bean.getPhysics()%></td>
					<td id="tc"><%=bean.getChemistry()%></td>
					<td id="tc"><%=bean.getMaths()%></td>
					<td id="tc"><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
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
