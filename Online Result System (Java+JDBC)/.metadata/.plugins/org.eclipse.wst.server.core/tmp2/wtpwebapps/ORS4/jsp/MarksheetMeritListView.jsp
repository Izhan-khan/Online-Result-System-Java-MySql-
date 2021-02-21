<%@page import="in.co.sunrays.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>
Merit-List Page
</title>
</head></head>
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
    
        <h1>Marksheet Merit List</h1>

        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
            <br>
            
                   <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                
            <table border="1" width="100%">
                <tr>

                    <th>Rank</th>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>

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

                    <td id="tc"><%=index++%></td>
                    <td id="tc"><%=bean.getRollNo()%></td>
                    <td id="tc"><%=bean.getName()%></td>
                    <td id="tc"><%=bean.getPhysics()%></td>
                    <td id="tc"><%=bean.getChemistry()%></td>
                    <td id="tc"><%=bean.getMaths()%></td>

                </tr>

                <%
                    }
                %>
            </table>
            <br>
            <table style="text-align: center; width:100% " >
                <tr>
                    <td align="center"><input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
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
    <br>
    
    <%@include file="Footer.jsp"%>
</body>
</html>
