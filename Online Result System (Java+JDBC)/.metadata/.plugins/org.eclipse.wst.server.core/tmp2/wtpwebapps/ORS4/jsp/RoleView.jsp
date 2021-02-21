<%@page import="in.co.sunrays.proj4.controller.RoleCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Add Role Page</title>

<body style="height: 100%; text-align:center; padding-bottom: 50px;">
    <form action="<%=ORSView.ROLE_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.RoleBean"
            scope="request"></jsp:useBean>

        <center>
            <%
				if (bean.getId() != 0) {
			%>

			<h1>Update Role</h1>

			<%
				} else {
			%>

			<h1>Add Role</h1>

			<%
				}
			%>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table>
                <tr>
                    <th>Name<b style="color: red;">*</b></th>
                    <td><input type="text" name="name" placeholder="Enter your Role"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td>
                </tr>
                <tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("name", request)%>
					</font></td>
				</tr>
                <tr>
                    <th>Description<b style="color: red;">*</b></th>
                    <td><textarea type="text" name="description" placeholder="Description of Role" cols="30" rows="6"><%=DataUtility.getStringData(bean.getDescription())%></textarea></td>
                </tr>
                <tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("description", request)%>
					</font></td>
				</tr>
                <tr>
                    <th></th>
                    <td colspan="2"><%
                    	if (bean.getId() != 0) {
                    %> <button
						type="submit" name="operation" value="<%=RoleCtl.OP_SAVE%>">Update 
						</button>
						<%
 	} else {
 %>
						<button type="submit" name="operation"
						value="<%=RoleCtl.OP_SAVE%>">Save</button> <%
 	}
 %> &emsp; <input type="submit"
                        name="operation" value="<%=RoleCtl.OP_CANCEL%>"></td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>