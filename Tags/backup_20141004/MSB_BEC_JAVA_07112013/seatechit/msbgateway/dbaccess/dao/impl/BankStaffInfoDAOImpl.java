package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.BankStaffInfoDAO;
import seatechit.msbgateway.dbaccess.entity.BankStaffInfo;

public class BankStaffInfoDAOImpl extends SpringFrameworkDAO implements BankStaffInfoDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public BankStaffInfoDAOImpl() {
	}

	@Override
	public ArrayList<BankStaffInfo> getAllBankStaffInfo() {
		ResultSet rs = null;
		BankStaffInfo obj = null;
		ArrayList<BankStaffInfo> arrBankStaffInfo = new ArrayList<BankStaffInfo>();
		try {
			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT a.staff_id, a.staff_name, a.role,a.branch_code,a.status FROM bec_staff a WHERE a.status='1'");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				obj = new BankStaffInfo(rs.getString("staff_id"), rs.getString("branch_code"), rs.getInt("status"), rs.getInt("role"));
				arrBankStaffInfo.add(obj);
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
		return arrBankStaffInfo;
	}
}
