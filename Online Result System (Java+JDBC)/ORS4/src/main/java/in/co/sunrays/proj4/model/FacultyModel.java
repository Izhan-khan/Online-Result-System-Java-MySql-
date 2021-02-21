package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of FacultyModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */


public class FacultyModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws DataBaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_Faculty");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataBaseException("Exception : Exception in getting pk from database");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	// Add --> To Add Faculty into database
	public long add(FacultyBean bean)
			throws ApplicationException, DuplicateRecordException, DataBaseException, RecordNotFoundException {

		FacultyBean existBean = findByEmailID(bean.getEmailId());

		//
		//
		//

		/**
		 * equals is use to compare two Strings whereas == is used to compare other data
		 * Types
		 */

		if (existBean != null) {
			throw new DuplicateRecordException("Exception : Email Id already exist");
		}

		CollegeModel collegemodel = new CollegeModel();

		CollegeBean collegebean = collegemodel.findByPk(bean.getCollegeId());

		if (collegebean.getId() == 0 || collegebean.getId() != bean.getCollegeId()) {
			throw new DuplicateRecordException("Exception : College Id doesnot exist");
		}

		CourseModel coursemodel = new CourseModel();

		CourseBean coursebean = coursemodel.findByPk(bean.getCourseId());

		if (coursebean == null || coursebean.getId() != bean.getCourseId()) {
			throw new DuplicateRecordException("Exception : Course Id doesnot exist");
		}

		Connection conn = null;
		long pk = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("Insert into st_Faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getQualification());
			pstmt.setString(5, bean.getEmailId());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setLong(9, bean.getCollegeId());
			pstmt.setString(10, bean.getSubjectName());
			pstmt.setLong(11, bean.getSubjectId());
			pstmt.setString(12, bean.getCourseName());
			pstmt.setLong(13, bean.getCourseId());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in Adding Faculty");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	// Find By LoginID--> To get Faculty details form database by LoginID
	public FacultyBean findByEmailID(String EmailId) throws ApplicationException {

		FacultyBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_Faculty where EmailId =? ");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, EmailId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setQualification(rs.getString(4));
				bean.setEmailId(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setCollegeName(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

				conn.commit();
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting Faculty by Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	// List --> Gives all Data from the database in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("Select * from st_Faculty");

		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				FacultyBean bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setQualification(rs.getString(4));
				bean.setEmailId(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setCollegeName(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApplicationException("Exception : Exception in getting list of Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	// Find By primaryKey(Id)--> To get Faculty details form database by Id
	public FacultyBean findByPk(long pk) throws ApplicationException {

		FacultyBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_Faculty where id =? ");

		try {
			bean = new FacultyBean();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setQualification(rs.getString(4));
				bean.setEmailId(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setCollegeName(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

				conn.commit();
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting Faculty by Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	// Delete--> To Delete Faculty from database
	public void delete(FacultyBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_Faculty where id=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception  " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in deleting Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
	}

	// Update--> Updates the required data in database
	public void update(FacultyBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {

		FacultyBean existBean = findByEmailID(bean.getEmailId());
		System.out.println(existBean.getId());		System.out.println(bean.getId());
		
		if (existBean.getId() != 0 && (existBean.getId() != (bean.getId()))) {
			throw new DuplicateRecordException("Exception : Login Id already exist");
		}


		CollegeModel cmodel = new CollegeModel();

		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());

		if (cbean.getId() == 0 || cbean.getId() != bean.getCollegeId()) {
			throw new DuplicateRecordException("Exception : College Id doesnot exist");
		}

		CourseModel coursemodel = new CourseModel();

		CourseBean coursebean = coursemodel.findByPk(bean.getCourseId());

		if (coursebean.getId() == 0 || coursebean.getId() != bean.getCourseId()) {
			throw new DuplicateRecordException("Exception : Course Id doesnot exist");
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_Faculty set FirstName=?,LastName=?,Qualification=?,EmailId=?,Dob=?,MobileNo=?,CollegeName=?,CollegeId=?,SubjectName=?,SubjectId=?,CourseName=?,CourseId=?,CreatedBy=?,ModifiedBy=?,CreatedDateTime=?,ModifiedDatetime=? where id =?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getQualification());
			pstmt.setString(4, bean.getEmailId());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getCollegeName());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setLong(10, bean.getSubjectId());
			pstmt.setString(11, bean.getCourseName());
			pstmt.setLong(12, bean.getCourseId());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());

			pstmt.setLong(17, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public List search(FacultyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_Faculty WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FirstName like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LastName like '" + bean.getLastName() + "%'");
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND CourseName like '" + bean.getCourseName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND CourseId like '" + bean.getCourseId() + "%'");
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SubjectName like '" + bean.getSubjectName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" AND SubjectId like '" + bean.getSubjectId() + "%'");
			}
			if (bean.getQualification() != null && bean.getQualification().length() > 0) {
				sql.append(" AND Qualification like '" + bean.getQualification() + "%'");
			}
			if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
				sql.append(" AND EmailId like '" + bean.getEmailId() + "%'");
			}
			if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
				sql.append(" AND EmailId like '" + bean.getEmailId() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGENAME = " + bean.getCollegeName());
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGEId = " + bean.getCollegeName());
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setQualification(rs.getString(4));
				bean.setEmailId(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setCollegeName(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setSubjectName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

}
