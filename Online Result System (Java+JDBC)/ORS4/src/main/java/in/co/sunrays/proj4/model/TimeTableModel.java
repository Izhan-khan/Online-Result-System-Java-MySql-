package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of TimetableModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */

public class TimeTableModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws DataBaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_TimeTable");
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

	// Add --> To Add TimeTable into database
	public long add(TimeTableBean bean)
			throws ApplicationException, DuplicateRecordException, DataBaseException, RecordNotFoundException {

		Connection conn = null;
		long pk = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Insert into st_TimeTable values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCourseName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setLong(5, bean.getSubjectId());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getExamTime());
			pstmt.setInt(8, bean.getSemester());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

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
				throw new ApplicationException("Exception : Exception in Adding TimeTable");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;

	}

	// Delete--> To Delete TimeTable from database
	public void delete(TimeTableBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_TimeTable where id=?");
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
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in deleting User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	// Find By primaryKey(Id)--> To get TimeTable details form database by Id
	public TimeTableBean findByPk(long pk) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_TimeTable where id =?");
		TimeTableBean bean = new TimeTableBean();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getInt(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting TimeTable by Pk");
		}
		return bean;
	}

	// Update--> Updates the required data in database
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_TimeTable set CourseName=?,CourseId=?,SubjectName=?,SubjectId=?,ExamDate=?,ExamTime=?,Semester=?,CreatedBy=?,ModifiedBy=?,CreatedDateTime=?,ModifiedDatetime=? where id =?");
			pstmt.setString(1, bean.getCourseName());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getSubjectName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setDate(5, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(6, bean.getExamTime());
			pstmt.setLong(7, bean.getSemester());
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
			throw new ApplicationException("Exception in updating TimeTable ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	// List --> Gives all Data from the database in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_TimeTable");

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
				TimeTableBean bean = new TimeTableBean();

				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getInt(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting list of TimeTable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List search(TimeTableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TimeTable WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
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
			if (bean.getExamDate() != null && bean.getExamDate().getDate() > 0) {
				sql.append(" AND ExamDate = " + bean.getExamDate());
			}
			if (bean.getExamTime() != null && bean.getExamTime().length() > 0) {
				sql.append(" AND ExamTime= " + bean.getExamTime());
			}
			if (bean.getSemester() == 0 && bean.getSemester() > 0) {
				sql.append(" AND Semester = " + bean.getSemester());
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
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getInt(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

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
