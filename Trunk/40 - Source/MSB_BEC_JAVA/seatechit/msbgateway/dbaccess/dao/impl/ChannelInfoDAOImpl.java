package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.ChannelInfoDAO;
import seatechit.msbgateway.dbaccess.entity.ChannelInfo;

public class ChannelInfoDAOImpl extends SpringFrameworkDAO implements ChannelInfoDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public ChannelInfoDAOImpl() {
	}

	@Override
	public ArrayList<ChannelInfo> getAllChannelInfo() {
		ResultSet rs = null;
		ChannelInfo obj = null;
		ArrayList<ChannelInfo> arrChannelInfo = new ArrayList<ChannelInfo>();
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT a.channel_id, a.channel_name, a.status FROM bec_channel a");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				obj = new ChannelInfo(rs.getString("channel_id"), rs.getString("channel_name"), rs.getInt("status"));
				arrChannelInfo.add(obj);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllBankStaffInfo()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllBankStaffInfo()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return arrChannelInfo;
	}
}
