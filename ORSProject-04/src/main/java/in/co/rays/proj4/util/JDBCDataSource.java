package in.co.rays.proj4.util;

import java.sql.Connection;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// SingleTon and Driver Design pattern.
/* provide connection re-usability
 * provide reliable connection with database
 * provide maximum connection  limitation with database
 * 
 * */
public final class JDBCDataSource {
	private static final JDBCDataSource jdbc = null;
	private static ComboPooledDataSource c = null;
	ResourceBundle r = ResourceBundle.getBundle("in.co.rays.proj4.bundle.system");

	private JDBCDataSource() {
		c = new ComboPooledDataSource();
		try {
			c.setDriverClass(r.getString("driver"));
			c.setJdbcUrl(r.getString("url"));
			c.setUser(r.getString("username"));
			c.setPassword(r.getString("password"));

			c.setInitialPoolSize(10); // at first this much connection will be provided
			c.setMinPoolSize(10); // this much need to be maintained.
			c.setMaxPoolSize(30);
			c.setAcquireIncrement(10); // at a time this much will be given at once.

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static JDBCDataSource getInstance() {
		if (jdbc == null) {
			return new JDBCDataSource();
		}
		return jdbc;
	}

	public static Connection getConnection() { // factory design pattern
		try {
			return getInstance().c.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public static void trnCommit(Connection c) {
		if (c != null) {
			try {
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void trnRollBack(Connection c) {
		if (c != null) {
			try {
				c.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
