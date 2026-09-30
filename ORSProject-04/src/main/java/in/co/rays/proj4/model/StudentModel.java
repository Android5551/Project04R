package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel extends BaseModel<StudentBean>{

	@Override
	public long add(StudentBean b) throws ApplicationException, DuplicateRecordException {
		CollegeModel cm = new CollegeModel();
		CollegeBean cb = cm.findByPk(b.getCollegeId());
		int pk = 0;
		Connection c = null;
		try {
			pk=nextPk();
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("insert into "+getTable()+" values("
					+ "?,?,?,?,?,?,?,?,?,?,?,?)");
			p.setLong(1, pk);
			p.setLong(2, b.getCollegeId());
//			p.setString(3, b.getCollegeName());
			p.setString(3, cb.getName()); //college Bean
			p.setString(4, b.getFirstName());
			p.setString(5, b.getLastName());
			p.setDate(6, new java.sql.Date(b.getDateOfBirth().getTime()));
			p.setString(7, b.getMobileNo());
			p.setString(8, b.getEmail());
			p.setString(9, b.getCreatedBy());
			p.setString(10, b.getModifiedBy());
			p.setTimestamp(11, b.getCreatedDatetime());
			p.setTimestamp(12, b.getModifiedDatetime());
			
			int i = p.executeUpdate();
			System.out.println(i+" row inserted!");
			JDBCDataSource.trnCommit(c);
			
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(c);
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		return pk;
	}

	@Override
	public void update(StudentBean b) throws ApplicationException, DuplicateRecordException {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			
			PreparedStatement p=c.prepareStatement("update "+getTable()+" set college_id=?,"
					+ "college_name=?, first_name=?,"
					+ "last_name=?, date_of_birth=?,"
					+ "mobile_no=?, email=? where id=?");
			p.setLong(1, b.getCollegeId());
			p.setString(2, b.getCollegeName());
			p.setString(3, b.getFirstName());
			p.setString(4, b.getLastName());
			p.setDate(5, new java.sql.Date(b.getDateOfBirth().getTime()));
			p.setString(6, b.getMobileNo());
			p.setString(7, b.getEmail());
			p.setString(8, b.getCreatedBy());
			p.setString(9, b.getModifiedBy());
			p.setTimestamp(10, b.getCreatedDatetime());
			p.setTimestamp(11, b.getModifiedDatetime());
			p.setLong(12, b.getId());
			
			int i = p.executeUpdate();
			System.out.println(i+" row inserted!");
			
			JDBCDataSource.trnCommit(c);
			
			
			
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(c);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		
	}

	@Override
	public String getWhereClause(StudentBean b) {
		StringBuffer s = new StringBuffer("");
		if(b!=null) {
			if(b.getCollegeName()!=null && b.getCollegeName().length()>0) {
				s.append(" and collegeName like '"+b.getCollegeName()+"%'");
			}
		}
		return s.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_student";
	}

	@Override
	public StudentBean getBean() {
		// TODO Auto-generated method stub
		return new StudentBean();
	}

}
