package seatechit.msbgateway.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import seatechit.msbgateway.utils.CachedDBParam;

/**
 * Base class for access database
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6
 * 
 */
public class SpringFrameworkDAO {
	protected final int JDBC_QUERY_TIMEOUT = 30;
	protected final int ORA_JDBC_QUERY_TIMEOUT = 10;
	protected final int ORA_JDBC_LOGIN_TIMEOUT = 10;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();
	protected Connection oraConn;

	public SpringFrameworkDAO() {
		oraConn = getConnection();
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);

			DriverManager.setLoginTimeout(ORA_JDBC_LOGIN_TIMEOUT);
			conn = DriverManager.getConnection(CachedDBParam.getOraConnString());
			if (conn != null) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getConnection()\n").append("Error message:").append(e.toString() + "\n"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getConnection()\n").append("Error message:").append(e.toString() + "\n"));
		}
		return conn;

	}

	public Connection getDb2CoreJDBC(String host, String user, String password) {
		Connection conn = null;
		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			DriverManager.setLoginTimeout(JDBC_QUERY_TIMEOUT);
			conn = DriverManager.getConnection("jdbc:as400://" + host, user, password);
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getDb2CoreJDBC()\n").append("Error message:").append(e.toString() + "\n"));
		}
		return conn;

	}

	protected int executeForUpdate(String sql) throws Exception {
		int result = 0;
		if (oraConn != null) {
			try {
				Statement stmt = oraConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setQueryTimeout(ORA_JDBC_QUERY_TIMEOUT);
				result = stmt.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".executeForUpdate()\n").append("Error message:").append(e.toString() + "\n"));
				throw e;
			} finally {
			}
		}
		return result;
	}

	protected ResultSet executeForQuery(String sql) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		if (oraConn != null) {
			try {
				stmt = oraConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setQueryTimeout(ORA_JDBC_QUERY_TIMEOUT);
				rs = stmt.executeQuery(sql);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".executeForQuery()\n").append("Error message:").append(e.toString() + "\n"));
				throw e;
			} finally {
			}
		}
		return rs;
	}

}
