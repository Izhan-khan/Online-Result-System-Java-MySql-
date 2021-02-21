<%@page import="in.co.sunrays.proj4.controller.GetMarksheetCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>
Found Your Marksheet Page 
</title>
</head>
<body style="height: 100%; text-align:center; padding-bottom: 50px;">
    <%@ include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean"
        scope="request"></jsp:useBean>

    <center>
        <h1>Get Marksheet</h1>
		<br><br>
        <H2>
            <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
            </font>
        </H2>

        <form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <table>
                <label>RollNo :</label>&emsp;
                <input type="text" name="rollNo" placeholder="Enter your Roll Number"
                    value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_GO%>">
                <br><br>
                        
       			 <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
        		</font>
                
                <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>

                <%
                    if (bean.getRollNo() != null && bean.getRollNo().trim().length() > 0) {
                %>

                <tr>
                    <td>Rollno :</td>
                    <td><%=DataUtility.getStringData(bean.getRollNo())%></td>
                </tr>
                <tr>
                    <td>Name :</td>
                    <td><%=DataUtility.getStringData(bean.getName())%></td>
                </tr>
                <tr>
                    <td>Physics :</td>
                    <td><%=DataUtility.getStringData(bean.getPhysics())%></td>
                </tr>
                <tr>
                    <td>Chemistry :</td>
                    <td><%=DataUtility.getStringData(bean.getChemistry())%></td>
                </tr>
                <tr>
                    <td>Maths :</td>
                    <td><%=DataUtility.getStringData(bean.getMaths())%></td>

                </tr>
                <tr>
                    </td>
                </tr>
            </table>
            <%
                }
            %>
          
        </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>