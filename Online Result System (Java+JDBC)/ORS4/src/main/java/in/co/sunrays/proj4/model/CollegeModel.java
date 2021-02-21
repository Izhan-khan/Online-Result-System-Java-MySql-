package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of CollegeModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */

public class CollegeModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws Exception {
		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_college");
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

	// Add --> To Add College into database
	public long add(CollegeBean bean) throws Exception {

		CollegeBean existbean = findByName(bean.getName());

		if (existbean != null) {
			throw new DuplicateRecordException("College Name already exists");
		}

		Connection conn = null;
		long pk = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Insert into st_college values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getState());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

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

	// Delete--> To Delete College from database
	public void delete(CollegeBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_college where id=?");
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
			throw new ApplicationException("Exception : Exception in deleting college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	// Find By primaryKey(Id)--> To get College details form database by Id*/
	public CollegeBean findByPk(long pk) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_college where id =?");
		CollegeBean bean = new CollegeBean();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setCity(rs.getString(4));
				bean.setState(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting College by Pk");
		}
		return bean;
	}

	// Find By LoginId--> To get College details from database by loginId
	public CollegeBean findByName(String login) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_college where name =?");
		CollegeBean bean = null;

		/**
		 * duplicate execption when instantiating bean here
		 */
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setCity(rs.getString(4));
				bean.setState(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting College by login");
		}

		return bean;
	}

	// Update--> Updates the required data in database
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		CollegeBean beanExist = findByName(bean.getName());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getName().equals(bean.getName()))) {
			throw new DuplicateRecordException("CollegeName already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_college set Name=?,Address=?,City=?,State=?,PhoneNo=?,CreatedBy=?,ModifiedBy=?,CreatedDateTime=?,ModifiedDatetime=? where id =?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getCity());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.setLong(10, bean.getId());
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
			throw new ApplicationException("Exception in updating College ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	// List --> Gives all Data from the database in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("Select * from st_college");

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

				CollegeBean bean = new CollegeBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setCity(rs.getString(4));
				bean.setState(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

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

	public List search(CollegeBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" AND STATE like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND CITY like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" AND PHONE_NO = " + bean.getPhoneNo());
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
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

	/**
	 * Search College
	 *
	 * @param bean
	 *            : Search Parameters
	 */
	public List search(CollegeBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

}
