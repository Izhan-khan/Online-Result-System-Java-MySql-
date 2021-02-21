package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of SubjectModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */


public class SubjectModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws DataBaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_subject");
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

	// Add --> To Add Subject into database
	public long add(SubjectBean bean)
			throws ApplicationException, DuplicateRecordException, DataBaseException, RecordNotFoundException {

		Connection conn = null;
		long pk = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Insert into st_Subject values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			//pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

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
				throw new ApplicationException("Exception : Exception in Adding Subject");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;

	}

	// List --> Gives all Data from the database in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("Select * from st_Subject");

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
				SubjectBean bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				//bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApplicationException("Exception : Exception in getting Subject list ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	// Find By primaryKey(Id)--> To get Subject details form database by Id
	public SubjectBean findByPk(long pk) throws ApplicationException {

		SubjectBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_Subject where id =? ");

		try {
			bean = new SubjectBean();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				//bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

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

	// Delete--> To Delete Subject from database
	public void delete(SubjectBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_Subject where id=?");
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
			throw new ApplicationException("Exception : Exception in deleting Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
	}

	// Update--> Updates the required data in database
	public void update(SubjectBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_Subject set CourseId=?,CourseName=?,SubjectName=?,Description=?,CreatedBy=?,ModifiedBy=?,CreatedDateTime=?,ModifiedDatetime=? where id =?");

			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			//pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(3, bean.getSubjectName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			pstmt.setLong(9, bean.getId());

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

	public List search(SubjectBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_Subject WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND CourseId like " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND CourseName like '" + bean.getCourseName() + "%'");
			}
			/*if (bean.getSubjectId() > 0) {
				sql.append(" AND SubjectId like " + bean.getSubjectId());
			}*/
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SubjectName like '" + bean.getSubjectName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND Description like '" + bean.getDescription() + "%'");
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
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				//bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

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
