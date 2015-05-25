package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.ServiceInfoDAO;
import seatechit.msbgateway.dbaccess.entity.ServiceInfo;

public class ServiceInfoDAOImpl extends SpringFrameworkDAO implements ServiceInfoDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public ServiceInfoDAOImpl() {
	}

	@Override
	public ArrayList<ServiceInfo> getAllServiceInfo() {
		ResultSet rs = null;
		ServiceInfo obj = null;
		ArrayList<ServiceInfo> arrServiceInfo = new ArrayList<ServiceInfo>();
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT a.service_id, a.service_name, a.service_url, a.service_timeout FROM bec_service a");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				obj = new ServiceInfo(rs.getString("service_id"), rs.getString("service_name"), rs.getString("service_url"), rs.getInt("service_timeout"));

				arrServiceInfo.add(obj);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllServiceInfo()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllServiceInfo()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return arrServiceInfo;
	}
}
