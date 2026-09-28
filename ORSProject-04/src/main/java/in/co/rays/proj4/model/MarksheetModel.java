package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel extends BaseModel <MarksheetBean>{

	@Override
	public long add(MarksheetBean b) throws ApplicationException, DuplicateRecordException {
		int pk = 0;
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("insert into"+getTable()
					+ " values(?,?,?,?,?,?,?,?,?,?,?");
			p.setLong(1, pk);
			p.setString(2, b.getRollNo());
			p.setLong(3, b.getStudentId());
			p.setString(4,b.getName());
			p.setInt(5, b.getPhysics());
			p.setInt(6,b.getChemistry());
			p.setInt(7, b.getMaths());
			p.setString(8, b.getCreatedBy());
			p.setString(9, b.getModifiedBy());
			p.setTimestamp(10, b.getCreatedDatetime());
			p.setTimestamp(11, b.getModifiedDatetime());
			
			int i = p.executeUpdate();
			System.out.println(i+" row inserted!");
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
	public void update(MarksheetBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false); // transaction begins.
			PreparedStatement p = c.prepareStatement("update "+getTable()
			+" set roll_no=?, student_id=?, name=?,"
			+ "physics=?, chemistry=?, maths=?, created_by=?,"
			+ "modified_by=?, created_datetime=?, modified_datetime=? "
			+ "where id= ?");
			p.setString(1, b.getRollNo());
			p.setLong(2, b.getStudentId());
			p.setString(3, b.getName());
			p.setInt(4, b.getPhysics());
			p.setInt(5,b.getChemistry());
			p.setInt(6, b.getMaths());
			p.setString(7, b.getCreatedBy());
			p.setString(8, b.getModifiedBy());
			p.setTimestamp(9, b.getCreatedDatetime());
			p.setTimestamp(10, b.getModifiedDatetime());
			p.setLong(11, b.getId());
			
			int i = p.executeUpdate();
			System.out.println(i+" row updated!");
			JDBCDataSource.trnCommit(c);
			
			
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		
	}

	@Override
	public String getWhereClause(MarksheetBean b) {
		StringBuffer s = new StringBuffer("");
		if(b!=null) {
			if(b.getName()!=null && b.getName().length()>0) {
				s.append(" and name like '"+b.getName()+"%'");
			}
			if(b.getRollNo()!= null && b.getRollNo().length()>0) {
				s.append(" and rollNo like '"+b.getRollNo()+"%'");
			}
		}
		return s.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_marksheet";
	}

	@Override
	public MarksheetBean getBean() {
		// TODO Auto-generated method stub
		return new MarksheetBean();
	}

}
