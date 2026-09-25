package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel extends BaseModel<UserBean>{
// getting from bean and setting in preparedStatement
	@Override
	public long add(UserBean b) throws ApplicationException, DuplicateRecordException {
		// initialize connection
		Connection c = null;
		// initialize pk
		int pk = 0;
		try {
			// calling and storing nextPk
			pk = nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("insert into "+getTable()+
					" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			p.setDate(11, new java.sql.Date(b.getLastLogin().getTime()));
			p.setString(12, b.getUserLock());
			p.setString(13, b.getRegisteredIp());
			p.setString(14, b.getLastLoginIp());
			p.setString(15, b.getCreatedBy());
			p.setString(16, b.getModifiedBy());
			p.setTimestamp(17, b.getCreatedDatetime());
			p.setTimestamp(18, b.getModifiedDatetime());
			
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
					+ "modified_datetime=? where id=?)");
			
			p.setString(1, b.getFirstName());
			p.setString(2, b.getLastName());
			p.setString(3, b.getLogin());
			p.setString(4, b.getPassword());
			p.setDate(5, new java.sql.Date(b.getDob().getTime()));
			p.setString(6, b.getMobileNo());
			p.setLong(7, b.getRoleId());
			p.setInt(8, b.getUnsuccessfulLogin());
			p.setString(9, b.getGender());
			p.setDate(10, new java.sql.Date(b.getLastLogin().getTime()));
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
				s.append(" and firstName like '"+b.getFirstName()+"'%");
			}
			if(b.getMobileNo() != null && b.getMobileNo().length()>0) {
				s.append(" and mobileNo like '"+b.getMobileNo()+"'%");
			}
		}
		return s.toString();
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
