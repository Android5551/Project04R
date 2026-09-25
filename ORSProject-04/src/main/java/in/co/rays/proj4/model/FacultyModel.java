package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean>{

	@Override
	public long add(FacultyBean b) throws ApplicationException, DuplicateRecordException {
		int pk = 0;
		Connection c = null;
		try {
			pk = nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			
			PreparedStatement p = c.prepareStatement("insert into "+getTable()+
					" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?");
			p.setLong(1, pk);
			p.setLong(2, b.getCollegeId());
			p.setString(3, b.getCollegeName());
			p.setString(4, b.getFirstName());
			p.setString(5, b.getLastName());
			p.setString(6, b.getEmail());
			p.setString(7, b.getMobileNo());
			p.setString(8, b.getAddress());
			p.setString(9, b.getGender());
			p.setDate(10, new java.sql.Date(b.getDateOfBirth().getTime()));
			p.setString(11, b.getCreatedBy());
			p.setString(12, b.getModifiedBy());
			p.setTimestamp(13, b.getCreatedDatetime());
			p.setTimestamp(14, b.getModifiedDatetime());
			
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
	public void update(FacultyBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("update "+getTable()+
					"set college_id=?, college_name=?,"
					+ "first_name=?, last_name=?, email=?,"
					+ "mobile_no=?, address=?, gender=?,"
					+ "date_of_birth=?, created_by=?,"
					+ "modified_by=?, created_datetime=?,"
					+ "modified_datetime=? where id=?");
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
	public String getWhereClause(FacultyBean b) {
		StringBuilder s = new StringBuilder("");
		if(b!=null) {
			if(b.getCollegeName() != null && b.getCollegeName().length()>0) {
				s.append(" and collegeName like '"+b.getCollegeName()+"%'");
			}
			if(b.getFirstName() != null && b.getFirstName().length()>0) {
				s.append(" and firstName like '"+b.getFirstName()+"%'");
			}
			if(b.getGender() != null && b.getGender().length()>0) {
				s.append(" and gender like '"+b.getGender()+"%'");
			}
		}
		return s.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_faculty";
	}

	@Override
	public FacultyBean getBean() {
		// TODO Auto-generated method stub
		return new FacultyBean();
	}

}
