package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel extends BaseModel<UserBean>{
// getting from bean and setting in preparedStatement
	@Override
	public long add(UserBean b) throws ApplicationException, DuplicateRecordException {
//		RoleModel r = new RoleModel();
//		RoleBean rb = r.
		// initialize connection
		Connection c = null;
		UserBean existBean = findByLogin(b.getLogin());

		if (existBean != null) {
			throw new DuplicateRecordException("login already exist");
		}
		// initialize pk
		int pk = 0;
		try {
			// calling and storing nextPk
			pk = nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("insert into "+getTable()+
					" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			p.setLong(1, pk);
			p.setString(2, b.getFirstName());
			p.setString(3, b.getLastName());
			p.setString(4, b.getLogin());
			p.setString(5, b.getPassword());
			p.setDate(6, new java.sql.Date(b.getDob().getTime()));
			p.setString(7, b.getMobileNo());
			p.setLong(8, b.getRoleId());
			p.setInt(9, b.getUnsuccessfulLogin());
			p.setString(10, b.getGender());
//			p.setDate(11, new java.sql.Date(b.getLastLogin().getTime()));
			p.setTimestamp(11, b.getLastLogin());
			p.setString(12, b.getUserLock());
			p.setString(13, b.getRegisteredIp());
			p.setString(14, b.getLastLoginIp());
			p.setString(15, b.getCreatedBy());
			p.setString(16, b.getModifiedBy());
			p.setTimestamp(17, b.getCreatedDatetime());
			p.setTimestamp(18, b.getModifiedDatetime());
			p.setString(19, b.getPhoto());
			int i = p.executeUpdate();
			System.out.println(i+" row inserted");
			JDBCDataSource.trnCommit(c);
			
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		}finally {
			JDBCDataSource.getConnection();
		}
		
		return pk;
	}

	@Override
	public void update(UserBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		UserBean existBean = findByLogin(b.getLogin());
		// it means record exists and record's id is not same as id given by user.
		// if user wants to update his own changes he can do
		if (existBean != null && existBean.getId() != b.getId()) {
			throw new DuplicateRecordException("login already exist");
		}
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("update "+getTable()+" set first_name=?,"
					+ "last_name=?,"
					+ "login=?, password=?, dob=?, mobile_no=?,"
					+ "role_id=?, unsuccessful_login=?, gender=?,"
					+ "last_login=?, user_lock=?, registered_ip=?,"
					+ "last_login_ip=?, created_by=?,"
					+ "modified_by=?, created_datetime=?,"
					+ "modified_datetime=? where id=?");
			
			p.setString(1, b.getFirstName());
			p.setString(2, b.getLastName());
			p.setString(3, b.getLogin());
			p.setString(4, b.getPassword());
			p.setDate(5, new java.sql.Date(b.getDob().getTime()));
			p.setString(6, b.getMobileNo());
			p.setLong(7, b.getRoleId());
			p.setInt(8, b.getUnsuccessfulLogin());
			p.setString(9, b.getGender());
//			p.setDate(10, new java.sql.Date(b.getLastLogin().getTime()));
			p.setTimestamp(10, b.getLastLogin());
			p.setString(11, b.getUserLock());
			p.setString(12, b.getRegisteredIp());
			p.setString(13, b.getLastLoginIp());
			p.setString(14, b.getCreatedBy());
			p.setString(15, b.getModifiedBy());
			p.setTimestamp(16, b.getCreatedDatetime());
			p.setTimestamp(17, b.getModifiedDatetime());
			p.setLong(18, b.getId());
			
			int i = p.executeUpdate();
			System.out.println(i+" row updated!");
			JDBCDataSource.trnCommit(c);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(c);
		}finally {
			JDBCDataSource.closeConnection(c);
		}
	}
	// search filters ; input in test
	@Override
	public String getWhereClause(UserBean b) {
		StringBuilder s = new StringBuilder("");
		
		if(b != null) {
			if(b.getId() > 0) {
				s.append(" and id= "+b.getId());
			}
			if(b.getFirstName() != null && b.getFirstName().length()>0) {
				s.append(" and first_name like '"+b.getFirstName()+"%'");
			}
			if(b.getMobileNo() != null && b.getMobileNo().length()>0) {
				s.append(" and mobile_no like '"+b.getMobileNo()+"%'");
			}
			if(b.getGender()!=null && b.getGender().length()>0) {
				s.append(" and gender like '"+b.getGender()+"%'");
			}
			if(b.getRoleId() > 0) {
				s.append(" and role_id like '"+b.getRoleId()+"%'");
			}
			if(b.getDob() != null && b.getDob().getTime() > 0) {
				s.append(" and dob like '"+DataUtility.getDateString(b.getDob())+"%'");
			}
		}
		return s.toString();
	}
	
	public UserBean findByLogin(String login) {

		UserBean bean = findByUniqueColumn("login", login);

		return bean;

	}
	
	public UserBean authenticate(String login, String password) {

		UserBean bean = findByLogin(login); //select * from st_user where login = "ram@gmail.com"

		if (bean != null && bean.getPassword().equals(password)) {
			return bean;
		}
		return null;

	}
	public long register(UserBean bean) {

		long pk = add(bean);
		// code in testSmtp.java
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		msg.setTo(map.get("login"));
		msg.setSubject("User Rgistration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully");

		return pk;
	}
	public void updatephoto(long id, String photo) throws ApplicationException {
		
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("update "+getTable()+" set photo=? where id=?");
			p.setString(1, photo);
			p.setLong(2, id);
			
			int i = p.executeUpdate();
			System.out.println(i+" row updated!");
			JDBCDataSource.trnCommit(c);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(c);
		}finally {
			JDBCDataSource.closeConnection(c);
		}
	}
	
	public UserBean forgotPassword(String login) {

		UserBean bean = findByLogin(login);

		if (bean != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());
		
			EmailMessage msg = new EmailMessage();

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			return bean;
		}

		return null;

	}
	public UserBean changePassword(String newPassword, String oldPassword, String login) {

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(oldPassword)) {
			bean.setPassword(newPassword);
			update(bean);

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			return bean;
		}

		return null;

	}
	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_user";
	}

	@Override
	public UserBean getBean() {
		// TODO Auto-generated method stub
		return new UserBean();
	}

}
