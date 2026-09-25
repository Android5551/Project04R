package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class RoleModel extends BaseModel<RoleBean> {

	@Override
	public long add(RoleBean bean) {
		Connection c = null;
		int pk = 0;
		try {
			pk = nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false); //begin transaction
			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?)");
			
			p.setLong(1, pk);
			p.setString(2, bean.getName());
			p.setString(3, bean.getDescription());
			p.setString(4, bean.getCreatedBy());
			p.setString(5, bean.getModifiedBy());
			p.setTimestamp(6, bean.getCreatedDatetime());
			p.setTimestamp(7, bean.getModifiedDatetime());
			
			int i = p.executeUpdate();
			JDBCDataSource.trnCommit(c);
			System.out.println(i+" row added!");
		} catch (SQLException e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		return pk;
	}

	@Override
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p=c.prepareStatement("update "+getTable()+" set name= ?, "
					+ "description=?,created_by=?, modified_by=?, created_datetime=?,"
					+ "modified_datetime=? where id=?");
			p.setString(1, bean.getName());
			p.setString(2, bean.getDescription());
			p.setString(3, bean.getCreatedBy());
			p.setString(4, bean.getModifiedBy());
			p.setTimestamp(5, bean.getCreatedDatetime());
			p.setTimestamp(6, bean.getModifiedDatetime());
			p.setLong(7, bean.getId());
			
			int i = p.executeUpdate();
			JDBCDataSource.trnCommit(c);
			System.out.println(i+" row updated!");
			
		}catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		
	}
	// search filter ; should be given in test
	@Override
	public String getWhereClause(RoleBean b) {
		StringBuffer s = new StringBuffer("");
		
		if (b != null) {
			if (b.getId() > 0) {
				s.append(" and id= " + b.getId());
			}
			if (b.getName() != null && b.getName().length() > 0) {
				s.append(" and name like '" + b.getName() + "%'");
			}
			if (b.getDescription() != null && b.getDescription().length() > 0) {
				s.append(" and description like '" + b.getDescription() + "%'");
			}
		}
		return s.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_role";
	}

	@Override
	public RoleBean getBean() {
		// TODO Auto-generated method stub
		return new RoleBean();
	}

}
