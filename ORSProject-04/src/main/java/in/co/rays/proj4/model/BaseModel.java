package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public abstract class BaseModel<T extends BaseBean> {
	/*
	 * Abstract methods add update getWhereClause [Search filter] getTable [returns
	 * table name] getBean [returns bean object]
	 */
	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	public abstract String getWhereClause(T bean);

	public abstract String getTable();

	public abstract T getBean();

	/*
	 * complete classes nextPk delete
	 */
	public Integer nextPk() throws DatabaseException{
		int pk = 0;
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			PreparedStatement p = c.prepareStatement("Select max(id) from " + getTable());
			ResultSet r = p.executeQuery();
			while (r.next()) {
				pk = r.getInt(1);
			}
			r.close();
		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			JDBCDataSource.closeConnection(c);
		}
		return pk + 1;
	}

	public void delete(int id) {
		Connection c = null;
		try {
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			PreparedStatement p = c.prepareStatement("delete from " + getTable() + " where id=?");
			p.setInt(1, id);
			int i = p.executeUpdate();
			System.out.println(i + " rows deleted!");
			JDBCDataSource.trnCommit(c);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(c);
		} finally {
			JDBCDataSource.closeConnection(c);
		}
	}

	public T findByPk(long id) {
		Connection c = null;
		T bean = null;
		try {
			c = JDBCDataSource.getConnection();
			PreparedStatement p = c.prepareStatement("select * from " + getTable() + " where id = ?");
			p.setLong(1, id);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				bean = getBean(); // returns Tbean object
				bean.setResultSet(r); // takes all r from 72 and set in bean; all 5 of super + T's own
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
			
		}
		return bean;

	}
	
	public List<T> search(T bean, int pageNo, int pageSize){
		Connection c = null;
		List<T> list = new ArrayList<T>();
		StringBuffer s = new StringBuffer("select * from "+getTable()+" where 1=1");
		s.append(getWhereClause(bean)); // search filter appended in sql query
		
		if(pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			s.append(" limit "+pageNo+","+pageSize);
		}
		System.out.println("sql -->"+s.toString());
		try {
			c = JDBCDataSource.getConnection();
			PreparedStatement p = c.prepareStatement("select * from "+getTable()+" where 1 = 1");
			ResultSet r = p.executeQuery();
			while (r.next()) {
				bean = getBean(); // new bean
				bean.setResultSet(r);
				list.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(c);
		}
		return list;
	}
}
