package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.HostTranCodeInfoDAO;
import seatechit.msbgateway.dbaccess.entity.HostTranCodeInfo;

public class HostTranCodeInfoDAOImpl extends SpringFrameworkDAO implements HostTranCodeInfoDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public HostTranCodeInfoDAOImpl() {
	}

	@Override
	public ArrayList<HostTranCodeInfo> getAllHostTranCodeInfo() {
		ResultSet rs = null;
		HostTranCodeInfo obj = null;
		ArrayList<HostTranCodeInfo> arrHostTranCodeInfo = new ArrayList<HostTranCodeInfo>();
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT a.auto_id, a.ref_host_tran_code, a.real_host_tran_code,a.needsynchronized, a.description FROM bec_hostmsg a");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				obj = new HostTranCodeInfo(rs.getString("auto_id"), rs.getString("ref_host_tran_code"), rs.getString("real_host_tran_code"), rs.getString("needsynchronized"), rs
						.getString("description"));
				arrHostTranCodeInfo.add(obj);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllHostTranCodeInfo()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllHostTranCodeInfo()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return arrHostTranCodeInfo;
	}
}
