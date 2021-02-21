package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of MarksheetModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */


public class MarksheetModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws DataBaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_marksheet");
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

	// Add --> To Add Marksheet into database
	public long add(MarksheetBean bean)
			throws ApplicationException, DuplicateRecordException, DataBaseException, RecordNotFoundException {

		MarksheetBean existbean = findByRollNo(bean.getRollNo());

		if (existbean.getRollNo() != null) {
			throw new DuplicateRecordException("RollNo already Exist");
		}

		StudentModel Studentmodel = new StudentModel();

		StudentBean Studentbean = Studentmodel.findByPk(bean.getStudentId());

		if (Studentbean.getId() == 0) {
			throw new RecordNotFoundException("Student Id does not exists");
		}

		Connection conn = null;
		long pk = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setString(3, bean.getName());
			pstmt.setLong(4, bean.getPhysics());
			pstmt.setLong(5, bean.getChemistry());
			pstmt.setLong(6, bean.getMaths());
			pstmt.setLong(7, bean.getStudentId());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
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
				throw new ApplicationException("Exception : Exception in Adding marksheet");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	// Delete--> To Delete Marksheet from database
	public void delete(MarksheetBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_marksheet where id=?");
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
			throw new ApplicationException("Exception : Exception in deleting Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
	}

	// List --> Gives all Data from the database in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("Select * from st_Marksheet");

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
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhysics(rs.getInt(4));
				bean.setChemistry(rs.getInt(5));
				bean.setMaths(rs.getInt(6));
				bean.setStudentId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApplicationException("Exception : Exception in getting list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	// Find By Roll Number--> To get Marksheet details form database by Roll
	// Number
	public MarksheetBean findByRollNo(String rollno) throws ApplicationException {

		MarksheetBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_marksheet where rollno =? ");

		try {
			bean = new MarksheetBean();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rollno);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhysics(rs.getInt(4));
				bean.setChemistry(rs.getInt(5));
				bean.setMaths(rs.getInt(6));
				bean.setStudentId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				conn.commit();
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting Marksheet by Roll No");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	// Find By primaryKey(Id)--> To get Marksheet details form database by Id
	public MarksheetBean findByPk(long pk) throws ApplicationException {

		MarksheetBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_marksheet where id =? ");

		try {
			bean = new MarksheetBean();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhysics(rs.getInt(4));
				bean.setChemistry(rs.getInt(5));
				bean.setMaths(rs.getInt(6));
				bean.setStudentId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

				conn.commit();
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting Marksheet by Roll No");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	// Update--> Updates the required data in database
	public void update(MarksheetBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {

		Connection conn = null;

		MarksheetBean beanExist = findByRollNo(bean.getRollNo());

		// Check if updated LoginId already exist
		if (beanExist.getId() != 0 && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("RollNo already exist");
		}

		StudentModel Studentmodel = new StudentModel();

		StudentBean Studentbean = Studentmodel.findByPk(bean.getStudentId());

		if (Studentbean == null || (Studentbean.getId() != bean.getStudentId())) {
			throw new RecordNotFoundException("Student Id does not exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_marksheet set RollNo=?, Name=?,Physics=?,Chemistry=?,Maths=?,StudentId=?,CreatedBy=?,ModifiedBy=?,CreatedDateTime=?,ModifiedDatetime=? where id =?");
			pstmt.setString(1, bean.getRollNo());
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getPhysics());
			pstmt.setLong(4, bean.getChemistry());
			pstmt.setLong(5, bean.getMaths());
			pstmt.setLong(6, bean.getStudentId());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

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
			throw new ApplicationException("Exception in updating Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public List search(MarksheetBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Searches Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 *
	 */

	public List search(MarksheetBean bean, int pageNo, int pageSize) throws ApplicationException {

		// log.debug("Model search Started");

		StringBuffer sql = new StringBuffer("select * from ST_MARKSHEET where true");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" AND RollNo like '" + bean.getRollNo() + "%'");
				System.out.println(bean.getRollNo());		
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND name like '" + bean.getName() + "%'");
			}
			if (bean.getPhysics() == 0 && bean.getPhysics() > 0) {
				sql.append(" AND physics = " + bean.getPhysics());
			}
			if (bean.getChemistry() == 0 && bean.getChemistry() > 0) {
				sql.append(" AND chemistry = " + bean.getChemistry());
			}
			if (bean.getMaths() == 0 && bean.getMaths() > 0) {
				sql.append(" AND maths = '" + bean.getMaths());
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
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhysics(rs.getInt(4));
				bean.setChemistry(rs.getInt(5));
				bean.setMaths(rs.getInt(6));
				bean.setStudentId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error(e);
			throw new ApplicationException("Update rollback exception " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

	public List getMeritList(int pageNo, int pageSize)
            throws ApplicationException {
        //log.debug("Model  MeritList Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer(
                "SELECT `ID`,`ROLLNO`, `NAME`, `PHYSICS`, `CHEMISTRY`, `MATHS` , (PHYSICS + CHEMISTRY + MATHS) as total from `ST_MARKSHEET` WHERE physics>33 AND chemistry>33 AND maths>33 order by total desc");
        // if page size is greater than zero then apply pagination
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
                MarksheetBean bean = new MarksheetBean();
                bean.setId(rs.getLong(1));
                bean.setRollNo(rs.getString(2));
                bean.setName(rs.getString(3));
                bean.setPhysics(rs.getInt(4));
                bean.setChemistry(rs.getInt(5));
                bean.setMaths(rs.getInt(6));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           // log.error(e);
            throw new ApplicationException(
                    "Exception in getting merit list of Marksheet");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model  MeritList End");
        return list;
    }

}
	
