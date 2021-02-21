package in.co.sunrays.proj4.controller;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimeTableModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * TimeTable List functionality Controller. Performs operation for list, search
 * and delete operations of TimeTable
 *
 * @author SunilOS
 * @version 1.0
 * 
 */

/**
 * Servlet implementation class TimeTablelistCtl
 */
@ WebServlet(name="TimeTableListCtl",urlPatterns={"/ctl/TimeTableListCtl"})
public class TimeTableListCtl extends BaseCtl {

    private static Logger log = Logger.getLogger(TimeTableListCtl.class);

    
    protected void preload(HttpServletRequest request) {
		CourseModel courseModel = new CourseModel();
		SubjectModel subjectModel = new SubjectModel();
		
		try {
			List courseList = courseModel.list();
			List subjectList = subjectModel.list();
			
//			System.out.println(collegeList);
//			System.out.println(TimeTableList);
//			System.out.println(subjectList);
			
			request.setAttribute("CourseList", courseList);
			request.setAttribute("SubjectList", subjectList);
			
//			System.out.println(collegeList+"...................");
//			System.out.println(courseList+"................");
//			System.out.println(subjectList+"...............");
			
//			System.out.println(":::::::::::::::::::"+request.getAttribute("CollegeList")+":::::::::::::::::::");
//			System.out.println(":::::::::::::::::::"+request.getAttribute("TimeTableList")+":::::::::::::::::::");
//			System.out.println(":::::::::::::::::::"+request.getAttribute("SubjectList")+":::::::::::::::::::");
//			
		} catch (ApplicationException e) {
			// TODO: handle exception
			log.error(e);
		}
		
	}
	
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        TimeTableBean bean = new TimeTableBean();

        bean.setSemester(DataUtility.getInt(request.getParameter("semester")));
        
        bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));

        bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));

        return bean;
    }

    /**
     * ContainsDisplaylogics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;

        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;

        TimeTableBean bean = (TimeTableBean) populateBean(request);

        List list = null;
        TimeTableModel model = new TimeTableModel();
        try {
            list = model.search(bean, pageNo, pageSize);
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }

        if (list == null || list.size() == 0) {
            ServletUtility.setErrorMessage("No record found ", request);
        }
        ServletUtility.setList(list, request);
        ServletUtility.setPageNo(pageNo, request);
        ServletUtility.setPageSize(pageSize, request);
        ServletUtility.forward(getView(), request, response);
        log.debug("TimeTableListCtl doGet End");

    }

    /**
     * Contains Submit logics
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("TimeTableListCtl doPost Start");

        List list = null;

        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;

        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;

        TimeTableBean bean = (TimeTableBean) populateBean(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        // get the selected checkbox ids array for delete list
        String[] ids = request.getParameterValues("ids");

        TimeTableModel model = new TimeTableModel();

        try {

            if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op)
                    || OP_PREVIOUS.equalsIgnoreCase(op)) {

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }

            } else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.TIME_TABLE_CTL, request,
                        response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    TimeTableBean deletebean = new TimeTableBean();
                    for (String id : ids) {
                        deletebean.setId(DataUtility.getInt(id));
                        model.delete(deletebean);
                    }
                } else {
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
                }
            }
            list = model.search(bean, pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }

        log.debug("TimeTableListCtl doPost End");
    }

    @Override
    protected String getView() {
        return ORSView.TIMETABLE_LIST_VIEW;
    }

}

