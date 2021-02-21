<%@page import="in.co.sunrays.proj4.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.bean.RoleBean"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>RoleList Page</title>
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
<body style="text-align: center;">

    <%@include file="Header.jsp"%>


        <h1>Role List</h1>

        <form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
            <table width="100%">
                <tr>
                    <td align="center"><label>Name :</label> <input type="text"
                        name="name"
                        value="<%=ServletUtility.getParameter("name", request)%>">
                        &nbsp; <input type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH %>">
                        &emsp;
						<input type="reset" value = "Reset">
					
                        <br><br><br>
                    </td>
                </tr>
            </table>
            
                <font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>

            <table border="1" width="100%">
                <tr>
                    <th>S.No.</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Edit</th>
                </tr>
                

                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<RoleBean> it = list.iterator();
                    while (it.hasNext()) {
                        RoleBean bean = it.next();
                %>
                <tr>
                    <td id="tc"><%=index++%></td>
                    <td id="tc"><%=bean.getName()%></td>
                    <td id="tc"><%=bean.getDescription()%></td>
                    <td id="tc"><a href="RoleCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
                    <td></td>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
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

    <%@include file="Footer.jsp"%>
</body>
</html>