package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Faculty functionality Controller. Performs operation for add, update, delete
 * and get faculty
 *
 * @author SunilOS
 * @version 1.0
 * 
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl{
	
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
			
			request.setAttribute("CollegeList", collegeList);
			request.setAttribute("CourseList", courseList);
			request.setAttribute("SubjectList", subjectList);

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
	
		log.debug("FacultyCtl Method validate Started");
		
		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("firstName"))){
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("lastName"))){
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("qualification"))){
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("emailId"))){
			request.setAttribute("emailId", PropertyReader.getValue("error.require", "Email Id "));
			pass= false;
		}else if (!DataValidator.isEmail(request.getParameter("emailId"))){
			request.setAttribute("emailId", PropertyReader.getValue("error.email", "Email "));
			pass= false;
		}
			
		if(DataValidator.isNull(request.getParameter("dob"))){
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));		
			pass = false;
		}else if(!DataValidator.isDate(request.getParameter("dob"))){
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}else if (!DataValidator.isLong(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.integer", "Mobile No"));
			pass = false;
		}else if ((request.getParameter("mobileNo").length()>10)||(request.getParameter("mobileNo").length()<10)) {
			request.setAttribute("mobileNo", "Mobile Number should be of 10 digits");
			pass = false;
		}
						
		 if(request.getParameter("collegeId").equalsIgnoreCase("0") ){
			request.setAttribute("collegeReq", PropertyReader.getValue("error.select", "College"));
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
				
		
		
		log.debug("FacultyCtl Method validate Ended");
		
		return pass;
	}
	
	
	/**
	 * Set all the user data into Bean
	 * 
	 */
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request){
		
		log.debug("FacultyCtl Method populatebean Started");
		
		FacultyBean bean = new FacultyBean();
		
		CollegeModel collegeModel = new CollegeModel();
		CourseModel courseModel = new CourseModel();
		SubjectModel subjectModel = new SubjectModel();
		
		
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		
		bean.setQualification(DataUtility.getString(request.getParameter("qualification")));
		
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		
	
	/**
	 * Setting College Name by its Primary Key
	 */
			
		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
		
		CollegeBean collegebean = null;
		try {
			collegebean = collegeModel.findByPk(DataUtility.getInt(request.getParameter("collegeId")));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bean.setCollegeName(collegebean.getName());		
		
	/**
	 * Setting Course Name by its Primary Key
	 */
		
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
		

		CourseBean coursebean = null;
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
		try {
			subjectbean = subjectModel.findByPk(DataUtility.getInt(request.getParameter("subjectId")));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bean.setSubjectName(subjectbean.getSubjectName());
		
		
		
		
		log.debug("UserCtl Method populatebean Ended");
		
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
		
		log.debug("FacultyCtl Method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		// get model
		FacultyModel model = new  FacultyModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			FacultyBean bean;
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
		log.debug("FacultyCtl Method doGet Ended");
	}
	
	/**
	 * Contains Submit logics
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	
		log.debug("FacultyCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		//get model
		FacultyModel model = new FacultyModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equals(op)){
			FacultyBean bean = (FacultyBean) populateBean(request);
			
			try {
				System.out.println(bean.getId());
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

			FacultyBean bean = (FacultyBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("FacultyCtl Method doPostEnded");
	
	}
	
	
	
	@Override
	protected String getView() {	
	 return ORSView.FACULTY_VIEW;
	}

}



