package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.SysParamDAO;
import seatechit.msbgateway.dbaccess.entity.SysParam;
import seatechit.msbgateway.utils.Global;

public class SysParamDAOImpl extends SpringFrameworkDAO implements SysParamDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public SysParamDAOImpl() {
	}

	@Override
	public ArrayList<SysParam> getAllSystemParam() {
		StringBuffer buffSQL = new StringBuffer();
		ArrayList<SysParam> arrSysParam = new ArrayList<SysParam>();
		SysParam param = null;
		ResultSet rs = null;
		try {
			buffSQL.append("SELECT grp_name,p_name,p_value,p_desc FROM bec_param");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				param = new SysParam();
				param.setDescription(rs.getString("p_desc"));
				param.setGroupName(rs.getString("grp_name"));
				param.setParamName(rs.getString("p_name"));
				param.setParamValue(rs.getString("p_value"));
				arrSysParam.add(param);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllSystemParam()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAllSystemParam()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return arrSysParam;
	}

	@Override
	public SysParam getParamByName(String paramName) {
		StringBuffer buffSQL = new StringBuffer();
		SysParam param = null;
		ResultSet rs = null;
		try {
			buffSQL.append("SELECT grp_name,p_name,p_value,p_desc FROM bec_param WHERE p_name='" + paramName + "'");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				param = new SysParam();
				param.setDescription(rs.getString("p_desc"));
				param.setGroupName(rs.getString("grp_name"));
				param.setParamName(rs.getString("p_name"));
				param.setParamValue(rs.getString("p_value"));
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getParamByName()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getParamByName()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return param;
	}

	@Override
	public String getTransactionSequence() {
		ResultSet rs = null;
		try {

			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT " + Global.SEQ_TRAN_SN + ".NEXTVAL FROM DUAL");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getTransactionSequence()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getTransactionSequence()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return "";
	}

	@Override
	public String getRMSequence() {
		ResultSet rs = null;
		try {

			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT " + Global.SEQ_RM_NO + ".NEXTVAL FROM DUAL");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getTransactionSequence()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getTransactionSequence()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return "";
	}

	@Override
	public String getSalarySequence() {
		ResultSet rs = null;
		try {

			StringBuffer buffSQL = new StringBuffer();
			buffSQL.append("SELECT " + Global.SEQ_SAL_NO + ".NEXTVAL FROM DUAL");
			rs = executeForQuery(buffSQL.toString());
			while (rs != null && rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getTransactionSequence()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (oraConn != null)
					oraConn.close();
			} catch (SQLException e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getTransactionSequence()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return "";
	}

}
