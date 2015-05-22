package seatechit.esb.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpringFrameworkDAO {
	final String CLASS_NAME = this.getClass().getName();
	protected Connection oraConn;

	public SpringFrameworkDAO() {
	}

	public Connection getConnection(String driverName, String url, int timeout) {
		Connection conn = null;
		try {
			Class.forName(driverName);
			DriverManager.setLoginTimeout(timeout);
			conn = DriverManager.getConnection(url);
			if (conn != null) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException ex) {
		} catch (ClassNotFoundException ex) {
		}
		return conn;
	}

	protected int executeForUpdate(String sql, int timeout) throws Exception {
		int result = 0;
		if (oraConn != null) {
			try {
				Statement stmt = oraConn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				stmt.setQueryTimeout(timeout);
				result = stmt.executeUpdate(sql);
			} catch (Exception e) {
				throw e;
			} finally {
			}
		}
		return result;
	}

	protected ResultSet executeForQuery(String sql, int timeout)
			throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		if (oraConn != null) {
			try {
				stmt = oraConn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				stmt.setQueryTimeout(timeout);
				rs = stmt.executeQuery(sql);
			} catch (Exception e) {
				throw e;
			} finally {
			}
		}
		return rs;
	}

}
