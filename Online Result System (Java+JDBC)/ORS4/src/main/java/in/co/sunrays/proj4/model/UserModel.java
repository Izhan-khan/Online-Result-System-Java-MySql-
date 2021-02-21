package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.util.EmailBuilder;
import in.co.sunrays.proj4.util.EmailMessage;
import in.co.sunrays.proj4.util.EmailUtility;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DataBaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of UserModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */


public class UserModel {

	// nextPk method --> To generate id automatically
	public long nextPk() throws Exception {
		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_user");
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
			throw new DataBaseException("Exception : Exception in getting PK ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	// Add --> To Add user into database
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {

		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean.getLogin() != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("Insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setString(6, bean.getConfirmPassword());
			pstmt.setDate(7, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(8, bean.getMobileNo());
			pstmt.setLong(9, bean.getRoleId());
			pstmt.setLong(10, bean.getUnSuccessfulLogin());
			pstmt.setString(11, bean.getGender());
			pstmt.setTimestamp(12, bean.getLastLogin());
			pstmt.setString(13, bean.getLock());
			pstmt.setString(14, bean.getRegisteredIP());
			pstmt.setString(15, bean.getLastLoginIP());
			pstmt.setString(16, bean.getCreatedBy());
			pstmt.setString(17, bean.getModifiedBy());
			pstmt.setTimestamp(18, bean.getCreatedDatetime());
			pstmt.setTimestamp(19, bean.getModifiedDatetime());

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

	// Delete--> To Delete user from database
	public void delete(UserBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_user where id=?");
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

	// Find By primaryKey(Id)--> To get User details form database by Id*/
	public UserBean findByPk(long pk) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_user where id =?");
		UserBean bean = new UserBean();
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
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setMobileNo(rs.getString(8));
				bean.setRoleId(rs.getLong(9));
				bean.setUnSuccessfulLogin(rs.getInt(10));
				bean.setGender(rs.getString(11));
				bean.setLastLogin(rs.getTimestamp(12));
				bean.setLock(rs.getString(13));
				bean.setRegisteredIP(rs.getString(14));
				bean.setLastLoginIP(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by Pk");
		}
		return bean;
	}

	// Find By LoginId--> To get User details from database by loginId
	public UserBean findByLogin(String login) throws ApplicationException {

		StringBuffer sql = new StringBuffer("Select * from st_user where login =?");
		UserBean bean = new UserBean();

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

				bean.setId(rs.getInt(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setMobileNo(rs.getString(8));
				bean.setRoleId(rs.getLong(9));
				bean.setUnSuccessfulLogin(rs.getInt(10));
				bean.setGender(rs.getString(11));
				bean.setLastLogin(rs.getTimestamp(12));
				bean.setLock(rs.getString(13));
				bean.setRegisteredIP(rs.getString(14));
				bean.setLastLoginIP(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApplicationException("" + "Exception : Exception in getting User by login");
		}

		return bean;
	}

	// Update--> Updates the required data in database
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		UserBean beanExist = findByLogin(bean.getLogin());

		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == (bean.getId()))) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set firstName=?,lastName=?,login=?,password=?,confirmpassword=?,dob=?,MobileNo=?,roleId=?,UnsuccessfullLogin=?,gender=?,Lastlogin=?,UserLock=?,RegisteredIp=?,LastLoginIp=?,createdBy=?,modifiedBy=?,CreatedDatetime=?,modifiedDatetime=? where id =?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setString(5, bean.getConfirmPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnSuccessfulLogin());
			pstmt.setString(10, bean.getGender());
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getLock());
			pstmt.setString(13, bean.getRegisteredIP());
			pstmt.setString(14, bean.getLastLoginIP());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setLong(19, bean.getId());

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
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	// List--> Gives all the database elements in the form of List
	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_user");

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
				UserBean bean = new UserBean();

				bean = new UserBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setMobileNo(rs.getString(8));
				bean.setRoleId(rs.getLong(9));
				bean.setUnSuccessfulLogin(rs.getInt(10));
				bean.setGender(rs.getString(11));
				bean.setLastLogin(rs.getTimestamp(12));
				bean.setLock(rs.getString(13));
				bean.setRegisteredIP(rs.getString(14));
				bean.setLastLoginIP(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

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
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getGender());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO = '" + bean.getMobileNo() + "%'");
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLEID = " + bean.getRoleId());
			}
			if (bean.getUnSuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFULLOGIN = " + bean.getUnSuccessfulLogin());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
			}
			if (bean.getLastLogin() != null && bean.getLastLogin().getTime() > 0) {
				sql.append(" AND LASTLOGIN = " + bean.getLastLogin());
			}
			if (bean.getRegisteredIP() != null && bean.getRegisteredIP().length() > 0) {
				sql.append(" AND REGISTEREDIP like '" + bean.getRegisteredIP() + "%'");
			}
			if (bean.getLastLoginIP() != null && bean.getLastLoginIP().length() > 0) {
				sql.append(" AND LASTLOGINIP like '" + bean.getLastLoginIP() + "%'");
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
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setMobileNo(rs.getString(8));
				bean.setRoleId(rs.getLong(9));
				bean.setUnSuccessfulLogin(rs.getInt(10));
				bean.setGender(rs.getString(11));
				bean.setLastLogin(rs.getTimestamp(12));
				bean.setLock(rs.getString(13));
				bean.setRegisteredIP(rs.getString(14));
				bean.setLastLoginIP(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));

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

	/**
	 * Authenticates the user 
	 * 
	 * @param login : Login id of user
	 * @param password : Password of user
	 * 
	 * @return bean : existing bean
	 * 
	 * @throws ApplicationException :  if application error occurs
	 * 
	 */
	public UserBean authenticate(String login, String password) throws ApplicationException {
		// log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setDob(rs.getDate(7));
				bean.setMobileNo(rs.getString(8));
				bean.setRoleId(rs.getLong(9));
				bean.setUnSuccessfulLogin(rs.getInt(10));
				bean.setGender(rs.getString(11));
				bean.setLastLogin(rs.getTimestamp(12));
				bean.setLock(rs.getString(13));
				bean.setRegisteredIP(rs.getString(14));
				bean.setLastLoginIP(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));

			}
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model authenticate End");
		return bean;
	}

	public long registerUser(UserBean bean) throws Exception {

		// log.debug("Model add Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}

	/**
	 * Send the password of User to his Email
	 *
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException : if application error occurs
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData.getLogin() == null) {
			throw new RecordNotFoundException("Email ID does not exist !");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SUNARYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;

		return flag;
	}

	/**
	 * Updates the old password to new password
	 * 
	 * @param id : non-business primary key
	 * @param oldPassword : Previous password 
	 * @param newPassword : New Password
	 * 
	 * @throws RecordNotFoundException : if no record is found
	 * 
	 * @throws ApplicationException : if Application exception occurs
	 * 
	 * @return boolean : true if success otherwise false
	 * 
	 */

	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		// log.debug("model changePassword Started");
		boolean flag = false;
		UserBean beanExist = null;

		beanExist = findByPk(id);
		System.out.println(beanExist.getPassword());
		System.out.println(oldPassword);
		System.out.println(beanExist);
		
		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);
			beanExist.setConfirmPassword(newPassword);
			try {
				System.out.println(1);
				update(beanExist);

			} catch (DuplicateRecordException e) {
				// log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("confirmPassword", beanExist.getConfirmPassword());
		map.put("firstName", beanExist.getFirstName());
		map.put("lastName", beanExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(beanExist.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		// log.debug("Model changePassword End");
		return flag;

	}

}