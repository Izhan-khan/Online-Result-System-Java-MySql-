<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.UserListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>
User List Page 
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


<body align="center">

	<%@include file="Header.jsp"%>


	<h1>User List</h1>

	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
	
	<%
		List roleList = (List)request.getAttribute("roleList");	
			
		System.out.println(roleList);
	
	%>

		<table width="100%">
			<tr>
				<td align="center">
					<label>FirstName :</label> <input
					type="text" name="firstName" placeholder="Enter First Name"
					value="<%=ServletUtility.getParameter("firstName", request)%>">
					&emsp;
					<label>Role :</label>
					<%=HTMLUtility.getList("roleId",ServletUtility.getParameter("roleId", request) , roleList) %>
					&emsp; 
					<label>LoginId:</label> <input type="text" name="login" placeholder="Enter Login Id"
					value="<%=ServletUtility.getParameter("login", request)%>">
					&emsp; 
					<input type="submit" name="operation"
					value="<%=UserListCtl.OP_SEARCH%>">
					&emsp;
					<input type="reset" value = "Reset">
			
				</td>
			</tr>
						
		</table>
		<br>

			<table  width="100%" style="text-align: center;">
			<tr>
				<td colspan="8"><font color="red" ><%=ServletUtility.getErrorMessage(request)%></font></td>
			</tr>
			</table>

		<table border="1" width="100%">
			<tr>
				<th>Select All<br><input type="checkbox" onclick="toggle(this)"></th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Login Id</th>
				<th>Role Id</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Edit</th>
			</tr>

			

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<UserBean> it = list.iterator();
				while (it.hasNext()) {
					UserBean bean = it.next();
			%>
			<tr>
				<td align="center"><input type="checkbox" name="ids" value="<%=bean.getId()%>"
								<% if(bean.getRoleId()==1){%>
											disabled
								<%}else{ %>
								
									<%}%>
									></td>
				<td id="tc"><%=bean.getFirstName()%></td>
				<td id="tc"><%=bean.getLastName()%></td>
				<td id="tc"><%=bean.getLogin()%></td>
				<td id="tc"><%=bean.getRoleId()%></td>
				<td id="tc"><%=bean.getGender()%></td>
				<td id="tc"><%=bean.getDob()%></td>
				<td id="tc"> <% if(bean.getRoleId()==1){%>
											......
								<%}else{ %>
								<a href="UserCtl?id=<%=bean.getId()%>">Edit</a>		
									<%}%>
				
				<%-- <a href="UserCtl?id=<%=bean.getId()%>">Edit</a>--%>
				
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					value="<%=UserListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
				<td><input type="submit" name="operation"
					value="<%=UserListCtl.OP_DELETE%>"></td>
				<td><input type="button" name="operation"
					value="<%=UserListCtl.OP_NEW%>" onclick="location.href='/ORS4/ctl/UserCtl';"></td>
				<td align="right"><input type="submit" name="operation"
					value="<%=UserListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
			
			</tr>
		<%--	<%=pageNo%>
			<%=pageSize%>
			<%=index%> --%>
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


	<%@include file="Footer.jsp"%>
</body>
</html>