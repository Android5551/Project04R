package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CourseModel extends BaseModel<CourseBean> {

	@Override
	public long add(CourseBean b) throws ApplicationException, DuplicateRecordException {
		int pk = 0;
		Connection c = null;
		try {
			pk = nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);

			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			p.setLong(1, pk);
			p.setString(2, b.getName());
			p.setString(3, b.getDescription());
			p.setString(4, b.getDuration());
			p.setString(5, b.getCreatedBy());
			p.setString(6, b.getModifiedBy());
			p.setTimestamp(7, b.getCreatedDatetime());
			p.setTimestamp(8, b.getModifiedDatetime());

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
	public void update(CourseBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);

			PreparedStatement p = c.prepareStatement("update " + getTable() + " set "
					+ "values(name=?, description=?, duration=?,"
					+ "	+ created_by=?, modified_by=?, created_datetime=?,"
					+ "	+ modified_datetime=? where id=?)");
			
			p.setString(1, b.getName());
			p.setString(2, b.getDescription());
			p.setString(3, b.getDuration());
			p.setString(4, b.getCreatedBy());
			p.setString(5, b.getModifiedBy());
			p.setTimestamp(6, b.getCreatedDatetime());
			p.setTimestamp(7, b.getModifiedDatetime());
			p.setLong(8, b.getId());
			
			int i = p.executeUpdate();
			System.out.println(i+" row updated!");
			
			JDBCDataSource.trnCommit(c);
			
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(c);
		}

	}

	@Override
	public String getWhereClause(CourseBean b) {
		StringBuilder s = new StringBuilder("");
		if(b!=null) {
			if(b.getName() != null && b.getName().length()>0) {
				s.append(" and name like '"+b.getName()+"%'");
			}
			if(b.getDescription() != null && b.getDescription().length()>0) {
				s.append(" and description like '"+b.getDescription()+"%'");
			}
		}
		return s.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_course";
	}

	@Override
	public CourseBean getBean() {
		// TODO Auto-generated method stub
		return new CourseBean();
	}

}
