package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.TimeTableModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * TimeTable functionality Controller. Performs operation for add, update, delete
 * and get TimeTable
 *
 * @author SunilOS
 * @version 1.0
 * 
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl" })
public class TimeTableCtl extends BaseCtl{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);

	/**
	 * Loads  List and all the required data  in the HTML form
	 */
	
	
	@Override
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
//			System.out.println(TimeTableList+"................");
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
	
	
	/**
	 * Check all the input data entered by the user 
	 * 
	 */
	
	@Override 
	protected boolean validate(HttpServletRequest request){
	
		log.debug("TimeTableCtl Method validate Started");
		
		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("examDate"))){
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}else if(request.getParameter("examDate").equalsIgnoreCase("0") ){
			request.setAttribute("examDate", PropertyReader.getValue("error.select", "Exam Date"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("examTime"))){
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		} if(request.getParameter("examTime").equalsIgnoreCase("0") ){
			request.setAttribute("examTimeReq", PropertyReader.getValue("error.select", "Exam Time"));
			pass = false;
		}
		
		
		if(DataValidator.isNull(request.getParameter("semester"))){
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		} if(request.getParameter("semester").equalsIgnoreCase("0") ){
			request.setAttribute("semReq", PropertyReader.getValue("error.select", "Semester"));
			pass = false;
		}
		
		 if(request.getParameter("courseId").equalsIgnoreCase("0") ){
				request.setAttribute("courseReq", PropertyReader.getValue("error.select", "Course"));
				pass = false;
			}
		 if(request.getParameter("subjectId").equalsIgnoreCase("0") ){
				request.setAttribute("subjectReq", PropertyReader.getValue("error.select", "Subject"));
				pass = false;
			}
		
		log.debug("TimeTableCtl Method validate Ended");
		
		return pass;
	}
	
	
	/**
	 * Set all the user data into Bean
	 * 
	 */
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request){
		
		log.debug("TimeTableCtl Method populatebean Started");
		
		TimeTableBean bean = new TimeTableBean();
		
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		
		
	/**
	 * Setting Course Name by its Primary Key
	 */
		
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
		
		
		CourseBean coursebean = null;
		CourseModel courseModel = new CourseModel();
		
		try {
			coursebean = courseModel.findByPk(DataUtility.getInt(request.getParameter("courseId")));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bean.setCourseName(coursebean.getCourseName());
		
	/**
	 * Setting Subject Name by its Primary Key
	 */
			
			bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));
			
			SubjectBean subjectbean = null;
			SubjectModel subjectModel = new SubjectModel();
			
			try {
				subjectbean = subjectModel.findByPk(DataUtility.getInt(request.getParameter("subjectId")));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			bean.setSubjectName(subjectbean.getSubjectName());
			

		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		
		bean.setSemester(DataUtility.getInt(request.getParameter("semester")));
		
		log.debug("TimeTableCtl Method populatebean Ended");
		
		return bean;
	}
	
	/**
	 * Contains DIsplay logics
	 * @throws ServletException 
	 * @throws IOException 
	 */
	
	/**
	 * Do View Operations
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		log.debug("TimeTableCtl Method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		// get model
		TimeTableModel model = new  TimeTableModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			TimeTableBean bean;
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("TimeTableCtl Method doGet Ended");
	}
	
	/**
	 * Contains Submit logics
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	
		log.debug("TimeTableCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		//get model
		TimeTableModel model = new TimeTableModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equals(op)){
			TimeTableBean bean = (TimeTableBean) populateBean(request);
			
			try {
				if (id > 0) {
					model.update(bean);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("TimeTableCtl Method doPostEnded");
	
	}
	
	
	
	@Override
	protected String getView() {	
	 return ORSView.TIME_TABLE_VIEW;
	}

}



