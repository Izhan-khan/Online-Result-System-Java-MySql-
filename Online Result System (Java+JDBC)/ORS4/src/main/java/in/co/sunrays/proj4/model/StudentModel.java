
package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of StudentModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */


public class StudentModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws Exception {
		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_student");
			ResultSet rs = pstmt.executeQuery();
			conn.setAutoCommit(false);
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			conn.commit();
			rs.close();
			pstmt.close();
		} catch (DataBaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataBaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	// Add --> To Add Student into database
	public long add(StudentBean bean) throws Exception {

		StudentBean existbean = findByEmail(bean.getEmail());

		if (existbean.getEmail() != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		CollegeModel collegemodel = new CollegeModel();

		CollegeBean collegebean = collegemodel.findByPk(bean.getCollegeId());

		if (collegebean.getId() == 0 || collegebean.getId() != bean.getCollegeId()) {
			throw new DuplicateRecordException("Exception : College Id doesnot exist");
		}

		Connection conn = null;
		long pk = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Insert into st_student values(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setLong(4, collegebean.getId());
			pstmt.setString(5, collegebean.getName());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				// TODO: handle exception
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	// Delete--> To Delete Student from database
	public void delete(StudentBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_student where id=?");
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
			throw new ApplicationException("Exception : Exception in deleting Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	// Find By primaryKey(Id)--> To get Student details form database by Id*/
	public StudentBean findByPk(long pk) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_student where id =?");
		StudentBean bean = new StudentBean();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setCollegeId(rs.getLong(4));
				bean.setCollegeName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

				conn.commit();
			}
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Student by Pk");
		}
		return bean;
	}

	// Find By LoginId--> To get Student details from database by Email Id
	public StudentBean findByEmail(String Email) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_student where email =?");

		StudentBean bean = new StudentBean();

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setCollegeId(rs.getLong(4));
				bean.setCollegeName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

				conn.commit();
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting Student by Email");
		}

		return bean;
	}

	// Update--> Updates the required data in database
	public void update(StudentBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {

		Connection conn = null;

		StudentBean beanExist = findByEmail(bean.getEmail());

		// Check if updated LoginId already exist
		if (beanExist.getId() != 0 && (beanExist.getId() != (bean.getId()))) {
			throw new DuplicateRecordException("Exception : Login Id already exist");
		}

		CollegeModel collegemodel = new CollegeModel();

		CollegeBean collegebean = collegemodel.findByPk(bean.getCollegeId());

		if (collegebean == null || (collegebean.getId() != bean.getCollegeId())) {
			throw new RecordNotFoundException("College Id does not exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_student set FirstName=?,LastName=?,CollegeId=?,CollegeName=?,Dob=?,MobileNo=?,Email=?,CreatedBy=?,ModifiedBy=?,CreatedDateTime=?,ModifiedDatetime=? where id =?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setLong(3, bean.getCollegeId());
			pstmt.setString(4, bean.getCollegeName());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			pstmt.setLong(12, bean.getId());

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
			throw new ApplicationException("Exception in updating Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	// List --> Gives all Data from the database in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("Select * from st_student");

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
				StudentBean bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setCollegeId(rs.getLong(4));
				bean.setCollegeName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApplicationException("Exception : Exception in getting list of Colleges");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List search(StudentBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Student with pagination
	 *
	 * @return list : List of Students
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 *
	 */

	public List search(StudentBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRSTNAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LASTNAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getCollegeId() != null && bean.getCollegeId() > 0) {
				sql.append(" AND CollegeId like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGENAME = " + bean.getCollegeName());
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
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
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setCollegeId(rs.getLong(4));
				bean.setCollegeName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

}
