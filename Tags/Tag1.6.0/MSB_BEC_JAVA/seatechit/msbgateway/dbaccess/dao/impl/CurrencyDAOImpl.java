package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.CurrencyDAO;
import seatechit.msbgateway.dbaccess.entity.CurrencyInfo;

public class CurrencyDAOImpl extends SpringFrameworkDAO implements CurrencyDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public CurrencyDAOImpl() {
	}

	@Override
	public ArrayList<CurrencyInfo> getAllCurrency() {
		ResultSet rs = null;
		CurrencyInfo obj = null;
		ArrayList<CurrencyInfo> arrCurrencyInfo = new ArrayList<CurrencyInfo>();
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT a.auto_id, a.ccy_code, a.ccy_name, a.ref_code, a.status ,a.fd_type, a.fd_grpcode FROM bec_currency a");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				obj = new CurrencyInfo(rs.getString("auto_id"), rs.getString("ccy_code"), rs.getString("ref_code"), rs.getInt("status"), rs.getString("fd_type"), rs.getString("fd_grpcode"));
				arrCurrencyInfo.add(obj);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllCurrency()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllCurrency()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return arrCurrencyInfo;
	}
}
