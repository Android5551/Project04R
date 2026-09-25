package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean> {

	@Override
	public long add(CollegeBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		int pk = 0;
		try {

			pk = nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			/*
			 * private String name; private String address; private String state; private
			 * String city; private String phoneNo;
			 */
			PreparedStatement p = c.prepareStatement("insert into " + getTable() +
					"values(?,?,?,?,?,?,?,?,?,?)");
			// getting from bean , setting in p
			p.setLong(1, pk);
			p.setString(2, b.getName());
			p.setString(3, b.getAddress());
			p.setString(4, b.getState());
			p.setString(5, b.getCity());
			p.setString(6, b.getPhoneNo());

			p.setString(7, b.getCreatedBy());
			p.setString(8, b.getModifiedBy());
			p.setTimestamp(9, b.getCreatedDatetime());
			p.setTimestamp(10, b.getModifiedDatetime());
			int i = p.executeUpdate();
			System.out.println(i + " row inserted!");
			JDBCDataSource.trnCommit(c);

		} catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		return pk;
	}

	@Override
	public void update(CollegeBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			/*
			 * private String name; 
			 * private String address; 
			 * private String state; private
			 * String city; private String phoneNo; 
			 * id, name, address, state, city,
			 * phone_no, created_by, modified_by, created_datetime, modified_datetime
			 */
			PreparedStatement p = c.prepareStatement("update " + getTable() + "set " 
			 + "name=?, address=?, state=?, city=?,"
			 + "phone_no=?, created_by=?, modified_by=?,"
			 + "created_datetime=?, modified_datetime=? "
			 + "where id=?");
			// getting from b and setting to p
			p.setString(1, b.getName());
			p.setString(2, b.getAddress());
			p.setString(3, b.getState());
			p.setString(4, b.getCity());
			p.setString(5, b.getPhoneNo());
			p.setString(6, b.getCreatedBy());
			p.setString(7, b.getModifiedBy());
			p.setTimestamp(8, b.getCreatedDatetime());
			p.setTimestamp(9, b.getModifiedDatetime());
			p.setLong(10, b.getId());
			
			JDBCDataSource.trnCommit(c);
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(c);
		}

	}

	@Override
	public String getWhereClause(CollegeBean bean) {
		StringBuilder s = new StringBuilder("");
		
		if(bean != null) {
			if(bean.getName() != null && bean.getName().length()>0) {
				s.append(" and name like '"+bean.getName()+"'%");
			}
			if(bean.getPhoneNo()!= null && bean.getPhoneNo().length()>0) {
				s.append(" and phoneNo like '"+bean.getPhoneNo()+"'%");
			}
		}
		return s.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_college";
	}

	@Override
	public CollegeBean getBean() {
		// TODO Auto-generated method stub
		return new CollegeBean();
		}
}
	