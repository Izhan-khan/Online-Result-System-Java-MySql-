
<%@page import="in.co.sunrays.proj4.controller.CollegeCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>
Add College Page 
</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">
    <form action="CollegeCtl" method="POST">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CollegeBean"
            scope="request"></jsp:useBean>

        <center>
            <%
				if (bean.getId() != 0) {
			%>

			<h1>Update College</h1>

			<%
				} else {
			%>

			<h1>Add College</h1>

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
                    <th>Name<b style="color: red;">*</b></th>
                    <td><input type="text" name="name" placeholder="Enter College Name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td>
                </tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
                <tr>
                    <th>Address<b style="color: red;">*</b></th>
                    <td><input type="text" name="address"" placeholder="Enter College Address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
                </tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>
                <tr>
                    <th>State<b style="color: red;">*</b></th>
                    <td><input type="text" name="state"" placeholder="Enter State"
                        value="<%=DataUtility.getStringData(bean.getState())%>"></td>
                </tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
                <tr>
                    <th>City<b style="color: red;">*</b></th>
                    <td><input type="text" name="city"" placeholder="Enter City"
                        value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
                </tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
                <tr>
                    <th>PhoneNo<b style="color: red;">*</b></th>
                    <td><input type="text" name="phoneNo"" placeholder="Enter College Phone No"
                        value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
                </tr>
				<tr>
					<th></th>
					<td><font style="color: red;"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>
                <tr>
                    <th></th>
                    <td colspan="2"><%
                    	if (bean.getId() != 0) {
                    %> <button
						type="submit" name="operation" value="<%=CollegeCtl.OP_SAVE%>">Update</button> <%
 	} else {
 %>
						<button type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>">Save</button> <%
 	}
 %> <%
 	if (bean.getId() > 0) {
 %> &emsp;<input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_DELETE%>"> <%
     }
 %>&emsp; <input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_CANCEL%>"></td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>