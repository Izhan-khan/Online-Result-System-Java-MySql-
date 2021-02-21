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
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Course functionality Controller. Performs operation for add, update, delete
 * and get Course
 *
 * @author SunilOS
 * @version 1.0
 * 
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl{
	
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
		CollegeModel collegeModel = new CollegeModel();
		CourseModel courseModel = new CourseModel();
		SubjectModel subjectModel = new SubjectModel();
		
		try {
			List collegeList = collegeModel.list();
			List courseList = courseModel.list();
			List subjectList = subjectModel.list();
			
//			System.out.println(collegeList);
//			System.out.println(courseList);
//			System.out.println(subjectList);
			
			request.setAttribute("CollegeList", collegeList);
			request.setAttribute("CourseList", courseList);
			request.setAttribute("SubjectList", subjectList);
			
//			System.out.println(collegeList+"...................");
//			System.out.println(courseList+"................");
//			System.out.println(subjectList+"...............");
			
//			System.out.println(":::::::::::::::::::"+request.getAttribute("CollegeList")+":::::::::::::::::::");
//			System.out.println(":::::::::::::::::::"+request.getAttribute("CourseList")+":::::::::::::::::::");
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
	
		log.debug("CourseCtl Method validate Started");
		
		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("courseName"))){
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("description"))){
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("Duration"))){
			request.setAttribute("duration", PropertyReader.getValue("error.select", "Duration"));
			pass = false;
		}
		
		
		log.debug("CourseCtl Method validate Ended");
		
		return pass;
	}
	
	
	/**
	 * Set all the user data into Bean
	 * 
	 */
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request){
		
		log.debug("CourseCtl Method populatebean Started");
		
		CourseBean bean = new CourseBean();
		
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		
		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		bean.setDuration(DataUtility.getInt(request.getParameter("Duration")));
		
		log.debug("CourseCtl Method populatebean Ended");
		
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
		
		log.debug("CourseCtl Method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		// get model
		CourseModel model = new  CourseModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			CourseBean bean;
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
		log.debug("CourseCtl Method doGet Ended");
	}
	
	/**
	 * Contains Submit logics
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	
		log.debug("CourseCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		//get model
		CourseModel model = new CourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equals(op)){
			CourseBean bean = (CourseBean) populateBean(request);
			
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

			CourseBean bean = (CourseBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("CourseCtl Method doPostEnded");
	
	}
	
	
	
	@Override
	protected String getView() {	
	 return ORSView.COURSE_VIEW;
	}

}



