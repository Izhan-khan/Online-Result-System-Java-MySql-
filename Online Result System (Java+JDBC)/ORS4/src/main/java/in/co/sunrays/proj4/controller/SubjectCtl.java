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
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Subject
 *
 * @author SunilOS
 * @version 1.0
 * 
 */ 
	@WebServlet(name = "SubjectCtl", urlPatterns = { "/ctl/SubjectCtl" })
	public class SubjectCtl extends BaseCtl {
		
		
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
			
			try {
				List courseList = courseModel.list();
				
//				System.out.println(collegeList);
				
				request.setAttribute("CourseList", courseList);
				
				
//				System.out.println(collegeList+"...................");
			
//				System.out.println(":::::::::::::::::::"+request.getAttribute("CollegeList")+":::::::::::::::::::");
				
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
		
			log.debug("SubjectCtl Method validate Started");
			
			boolean pass = true;
			
			if(request.getParameter("courseId").equalsIgnoreCase("0") ){
					request.setAttribute("courseReq", PropertyReader.getValue("error.select", "Course"));
					pass = false;
			}
			
			if(DataValidator.isNull(request.getParameter("subjectName"))){
				request.setAttribute("subjectName", PropertyReader.getValue("error.require", "Subject Name"));
				pass = false;
			}
			
			if(DataValidator.isNull(request.getParameter("description"))){
				request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
				pass = false;
			}

			log.debug("SubjectCtl Method validate Ended");
			
			return pass;
		}
		
		
		/**
		 * Set all the user data into Bean
		 * 
		 */
		
		@Override
		protected BaseBean populateBean(HttpServletRequest request){
			
			log.debug("SubjectCtl Method populatebean Started");
			
			SubjectBean bean = new SubjectBean();
			
			bean.setId(DataUtility.getInt(request.getParameter("id")));
			
			bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
			
			/**
			 * Setting Course Name with the help of primary key
			 */
			
			CourseBean coursebean = new CourseBean();
			CourseModel coursemodel = new CourseModel();
			
			try {
				coursebean = coursemodel.findByPk(DataUtility.getInt(request.getParameter("courseId")));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bean.setCourseName(coursebean.getCourseName());
			
			//bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));
		
			bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
			
			bean.setDescription(DataUtility.getString(request.getParameter("description")));
			
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
			
			log.debug("SubjectCtl Method doGet Started");
			
			String op = DataUtility.getString(request.getParameter("operation"));
			
			// get model
			SubjectModel model = new  SubjectModel();
			
			long id = DataUtility.getLong(request.getParameter("id"));
			if (id > 0 || op != null) {
				SubjectBean bean;
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
			log.debug("SubjectCtl Method doGet Ended");
		}
		/**
		 * Contains Submit logics
		 * @throws ServletException 
		 * @throws IOException 
		 */
		protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
			log.debug("SubjectCtl Method doPost Started");
			String op = DataUtility.getString(request.getParameter("operation"));
			//get model
			SubjectModel model = new SubjectModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			
			if(OP_SAVE.equals(op)){
				SubjectBean bean = (SubjectBean) populateBean(request);
				
				try {
					if (id > 0) {
						model.update(bean);
						System.out.println(bean.getCourseId()+"  "+bean.getSubjectName()+"  "+ bean.getDescription()); 
					} else {
						long pk = model.add(bean);

						System.out.println(pk+ "******** *"+bean.getCourseId()+bean.getSubjectName()+bean.getDescription());
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

				SubjectBean bean = (SubjectBean) populateBean(request);
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

				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
				return;
			}
			ServletUtility.forward(getView(), request, response);

			log.debug("SubjectCtl Method doPostEnded");
		
		}
		
		
		
		
		@Override
		protected String getView() {
			// TODO Auto-generated method stub
			return ORSView.SUBJECT_VIEW;
		}

		
		
}

 