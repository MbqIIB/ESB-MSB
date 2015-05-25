package vn.com.msb.cnn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import vn.com.msb.cnn.accounts.HostMessages;
import vn.com.msb.cnn.accounts.HostParameter;

public class DBUtils {
	protected final int JDBC_DBACCESS_TIMEOUT = 30;// 30s

	public static int MESSAGE_OK = 0;

	public static int MESSAGE_FAILS = 1;

	public static int MESSAGE_NULL = 2;

	protected Connection oraConn;

	public DBUtils() {

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

	public static boolean _setHostMessagesValue(String portNumber,
			String messageIn, String messageOut, int status, String errorCode,
			String description, String channel) {
		HostMessages message = new HostMessages();
		message.setMessageIn(messageIn);
		message.setMessageOut(messageOut);
		message.setStatus(status);
		message.setErrCode(errorCode);
		message.setDescription(description);
		message.setPortNumber(portNumber);
		message.setSentTime(new Date());
		message.setChannel(channel);
		// Kienvt: save hostmessage into db
		boolean result = saveHostMessages(message);
		return result;
	}

	public HostParameter findByNameAndValue(String port_number,
			String refChannel) {
		ResultSet rs = null;
		HostParameter obj = null;
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT name,value,param1,param2 FROM host_parameter WHERE name='port_number' AND value='"
					+ refChannel + "'");
			rs = executeForQuery(buffSQL.toString(), JDBC_DBACCESS_TIMEOUT);
			while (rs != null && rs.next()) {
				obj = new HostParameter(rs.getString("name"),
						rs.getString("value"), rs.getString("param1"),
						rs.getString("param2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public HostParameter findByName(String findByName) {
		ResultSet rs = null;
		HostParameter obj = null;
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT name,value,param1,param2 FROM host_parameter WHERE name='"
					+ findByName + "'");
			rs = executeForQuery(buffSQL.toString(), JDBC_DBACCESS_TIMEOUT);
			while (rs != null && rs.next()) {
				obj = new HostParameter(rs.getString("name"),
						rs.getString("value"), rs.getString("param1"),
						rs.getString("param2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static boolean saveHostParameter(HostParameter inputHostParameter) {
		return false;
	}

	public static boolean saveHostMessages(HostMessages inputHostMessages) {
		return false;
	}
}
