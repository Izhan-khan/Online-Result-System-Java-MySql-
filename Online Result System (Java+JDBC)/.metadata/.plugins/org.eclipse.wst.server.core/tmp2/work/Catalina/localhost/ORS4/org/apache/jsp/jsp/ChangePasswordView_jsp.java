/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.36
 * Generated at: 2020-08-13 10:54:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import in.co.sunrays.proj4.controller.ChangePasswordCtl;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.ServletUtility;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.controller.LoginCtl;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.controller.ORSView;

public final class ChangePasswordView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/jsp/Footer.jsp", Long.valueOf(1584358349273L));
    _jspx_dependants.put("/jsp/Header.jsp", Long.valueOf(1595889490143L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("in.co.sunrays.proj4.controller.ORSView");
    _jspx_imports_classes.add("in.co.sunrays.proj4.controller.ChangePasswordCtl");
    _jspx_imports_classes.add("in.co.sunrays.proj4.bean.RoleBean");
    _jspx_imports_classes.add("in.co.sunrays.proj4.controller.LoginCtl");
    _jspx_imports_classes.add("in.co.sunrays.proj4.util.ServletUtility");
    _jspx_imports_classes.add("in.co.sunrays.proj4.bean.UserBean");
    _jspx_imports_classes.add("in.co.sunrays.proj4.util.DataUtility");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Change your Password Page</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"height: 100%; text-align:center; padding-bottom: 50px;\">\r\n");
      out.write("\t<form action=\"");
      out.print(ORSView.CHANGE_PASSWORD_CTL);
      out.write("\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

      out.write("\r\n");
      out.write("\r\n");
      out.write("   \r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write(" <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\r\n");
      out.write(" <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\r\n");
      out.write(" <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\r\n");
      out.write(" \r\n");
      out.write("   <script>\r\n");
      out.write(" $(function() {\r\n");
      out.write("\t// minDate: 4,\t \r\n");
      out.write("\t\t$(\"#datepicker\").datepicker({\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tchangeMonth : true,\r\n");
      out.write("\t\t\tdateFormat: \"dd/mm/yy\",\r\n");
      out.write("\t\t\tchangeYear : true,\r\n");
      out.write("\t\t\tyearRange : \"1950:\" + new Date().getFullYear(), \r\n");
      out.write("\t\t\tmaxDate : 0 \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//beforeShowDay: $.datepicker.noWeekends,\r\n");
      out.write("\r\n");
      out.write("\t\t\t//maxDate: -6570,\r\n");
      out.write("\t\t//dateFormat: \"dd/MM/yyyy\",\r\n");
      out.write("\t\t});\r\n");
      out.write(" //$(\"#date\").datepicker(\"setDate\",new Date());\r\n");
      out.write("\t});\r\n");
      out.write(" \r\n");
      out.write(" $(function() {\r\n");
      out.write("\t\t// minDate: 4,\t \r\n");
      out.write("\t\t\t$(\"#datepicker1\").datepicker({\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tchangeMonth : true,\r\n");
      out.write("\t\t\t\tdateFormat: \"dd/mm/yy\",\r\n");
      out.write("\t\t\t\tchangeYear : true,\r\n");
      out.write("\t\t\t\tyearRange : new Date().getFullYear() +\": +1\",\r\n");
      out.write("\t\t\t\tminDate: 0\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//beforeShowDay: $.datepicker.noWeekends,\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t//maxDate: -6570,\r\n");
      out.write("\t\t\t//dateFormat: \"dd/MM/yyyy\",\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t //$(\"#date\").datepicker(\"setDate\",new Date());\r\n");
      out.write("\t\t});\r\n");
      out.write(" \r\n");
      out.write("  </script>\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" border=\"0\">\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td width=\"90%\" ><a href=\"");
      out.print(ORSView.WELCOME_CTL);
      out.write("\">Welcome</b></a> |\r\n");
      out.write("            ");

            if (userLoggedIn) {
        
      out.write(" <a href=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("?operation=");
      out.print(LoginCtl.OP_LOG_OUT);
      out.write("\">Logout</b></a>\r\n");
      out.write("\r\n");
      out.write("            ");

                } else {
            
      out.write(" <a href=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("\">Login</b></a> ");

     }
 
      out.write("</td>\r\n");
      out.write("        <td rowspan=\"2\">\r\n");
      out.write("            <h1 align=\"Right\">\r\n");
      out.write("                <img src=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/Logo.png\" width=150 height=\"60\">\r\n");
      out.write("            </h1>\r\n");
      out.write("        </td>\r\n");
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("    \r\n");
      out.write("    <tr>\r\n");
      out.write("        <td >\r\n");
      out.write("            <h3>\r\n");
      out.write("                ");
      out.print(welcomeMsg);
      out.write("</h3>\r\n");
      out.write("        </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("    ");

        if (userLoggedIn) {
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td colspan=\"2\" >\r\n");
      out.write("         ||<a href=\"");
      out.print(ORSView.MY_PROFILE_CTL);
      out.write("\">MyProfile</b></a>|\r\n");
      out.write("            <a href=\"");
      out.print(ORSView.MARKSHEET_MERIT_LIST_CTL);
      out.write("\">Marksheet Merit\r\n");
      out.write("                List</b>\r\n");
      out.write("        </a> | <a href=\"");
      out.print(ORSView.GET_MARKSHEET_CTL);
      out.write("\">Get Marksheet</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.CHANGE_PASSWORD_CTL);
      out.write("\">Change Password</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.JAVA_DOC_VIEW);
      out.write("\" target=\"_blank\">Java Doc</b></a> | ");

            if (userBean.getRoleId() == RoleBean.ADMIN) {
        
      out.write(" <a href=\"");
      out.print(ORSView.MARKSHEET_CTL);
      out.write("\">Add Marksheet</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.MARKSHEET_LIST_CTL);
      out.write("\">Marksheet List</b></a> |\r\n");
      out.write("            <a href=\"");
      out.print(ORSView.USER_CTL);
      out.write("\">Add User</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.USER_LIST_CTL);
      out.write("\">User List</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.COLLEGE_CTL);
      out.write("\">Add College</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.COLLEGE_LIST_CTL);
      out.write("\">College List</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.STUDENT_CTL);
      out.write("\">Add Student</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.STUDENT_LIST_CTL);
      out.write("\">Student List</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.ROLE_CTL);
      out.write("\">Add Role</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.ROLE_LIST_CTL);
      out.write("\">Role List</b></a> | <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.FACULTY_CTL);
      out.write("\">Add Faculty</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.FACULTY_LIST_CTL);
      out.write("\">Faculty List</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.SUBJECT_CTL);
      out.write("\">Add Subject</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.SUBJECT_LIST_CTL);
      out.write("\">Subject List</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.TIME_TABLE_CTL);
      out.write("\">Add TimeTable</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.TIME_TABLE_LIST_CTL);
      out.write("\">TimeTable List</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.COURSE_CTL);
      out.write("\">Add Course</b></a>| <a\r\n");
      out.write("            href=\"");
      out.print(ORSView.COURSE_LIST_CTL);
      out.write("\">Course List</b></a>  \r\n");
      out.write("            \r\n");
      out.write("          ||\r\n");
      out.write("            \r\n");
      out.write("            ");

            
  		  }
            
 
      out.write("</td>\r\n");
      out.write("    \r\n");
      out.write("    </tr>\r\n");
      out.write("    ");

        }
    
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("<hr>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");
      in.co.sunrays.proj4.bean.UserBean bean = null;
      bean = (in.co.sunrays.proj4.bean.UserBean) _jspx_page_context.getAttribute("bean", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (bean == null){
        bean = new in.co.sunrays.proj4.bean.UserBean();
        _jspx_page_context.setAttribute("bean", bean, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<center>\r\n");
      out.write("\t\t\t<h1>Change Password</h1>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<H3>\r\n");
      out.write("\t\t\t\t<font color=\"red\"> ");
      out.print(ServletUtility.getErrorMessage(request));
      out.write("\r\n");
      out.write("\t\t\t\t</font>\r\n");
      out.write("\t\t\t</H3>\r\n");
      out.write("\t\t\t<H3>\r\n");
      out.write("\t\t\t\t<font color=\"green\"> ");
      out.print(ServletUtility.getSuccessMessage(request));
      out.write("\r\n");
      out.write("\t\t\t\t</font>\r\n");
      out.write("\t\t\t</H3>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"id\" value=\"");
      out.print(bean.getId());
      out.write("\"> <input\r\n");
      out.write("\t\t\t\ttype=\"hidden\" name=\"createdBy\" value=\"");
      out.print(bean.getCreatedBy());
      out.write("\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"modifiedBy\"\r\n");
      out.write("\t\t\t\tvalue=\"");
      out.print(bean.getModifiedBy());
      out.write("\"> <input type=\"hidden\"\r\n");
      out.write("\t\t\t\tname=\"createdDatetime\"\r\n");
      out.write("\t\t\t\tvalue=\"");
      out.print(DataUtility.getTimestamp(bean.getCreatedDatetime()));
      out.write("\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"modifiedDatetime\"\r\n");
      out.write("\t\t\t\tvalue=\"");
      out.print(DataUtility.getTimestamp(bean.getModifiedDatetime()));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>Old Password<b style=\"color: red;\">*</b></th>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"password\" name=\"oldPassword\" placeholder=\"Enter your Password\"\r\n");
      out.write("\t\t\t\t\t\tvalue=");
      out.print(DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword"))));
      out.write(">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th></th>\r\n");
      out.write("\t\t\t\t\t<td><font style=\"color: red;\"> ");
      out.print(ServletUtility.getErrorMessage("oldPassword", request));
      out.write("</font></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>New Password<b style=\"color: red;\">*</b></th>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"password\" name=\"newPassword\" placeholder=\"Enter New Password\"\r\n");
      out.write("\t\t\t\t\t\tvalue=");
      out.print(DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword"))));
      out.write(">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th></th>\r\n");
      out.write("\t\t\t\t\t<td><font style=\"color: red;\"> ");
      out.print(ServletUtility.getErrorMessage("newPassword", request));
      out.write("</font></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>Confirm Password<b style=\"color: red;\">*</b></th>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"password\" name=\"confirmPassword\" placeholder=\"Confirm New Password\"\r\n");
      out.write("\t\t\t\t\t\tvalue=");
      out.print(DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword"))));
      out.write(">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th></th>\r\n");
      out.write("\t\t\t\t\t<td><font style=\"color: red;\"> ");
      out.print(ServletUtility.getErrorMessage("confirmPassword", request));
      out.write("</font></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th></th>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\"><input type=\"submit\" name=\"operation\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print(ChangePasswordCtl.OP_CHANGE_MY_PROFILE);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t&nbsp; <input type=\"submit\" name=\"operation\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print(ChangePasswordCtl.OP_SAVE);
      out.write("\"> &nbsp;</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t</form>\r\n");
      out.write("\t</center>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".foot{  \r\n");
      out.write(" \tleft:0;\r\n");
      out.write("\twidth:100%;\r\n");
      out.write("\tposition: fixed;\r\n");
      out.write("\tbottom: 0;\r\n");
      out.write("\tbackground-color: white;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"foot\">\r\n");
      out.write("\t\t<hr>\r\n");
      out.write("\t\t<CENTER>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<H4 style=\"margin-top: 5px;\">\r\n");
      out.write("\t\t\t\t&copy; <i> Copyrights SUNRAYS Technologies</i>\r\n");
      out.write("\t\t\t</H4>\r\n");
      out.write("\t\t</CENTER>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
