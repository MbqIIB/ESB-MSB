package seatechit.msbgateway.dbaccess.dao.impl;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.LogMessageDAO;
import seatechit.msbgateway.dbaccess.entity.HOSTMessagLogInfo;
import seatechit.msbgateway.dbaccess.entity.LogMessage;
import seatechit.msbgateway.dbaccess.entity.XMLMessageLogInfo;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.Global;

public class LogMessageDAOImpl extends SpringFrameworkDAO implements LogMessageDAO {
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	private java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	@Override
	public int insertLogMessage(LogMessage logMsg) {
		try {
			PreparedStatement prpStmtInsertBEC_tllog = null;
			PreparedStatement prpStmtInsertBEC_msglog = null;

			XMLMessageLogInfo messageInfo = logMsg.getXMLMessageLogInfo();

			String sqlInsertBEC_tllog = "INSERT INTO bec_tllog(message_sn, message_date,tran_code,tran_service_code," + "sender_id,sender_tran_sn,send_date,send_time,"
					+ "receive_date,receive_time,execution_date,receiver_id,receiver_tran_sn,resp_date,resp_time,resp_code,resp_msg,"
					+ "ref_service,in_xml_msg,out_xml_msg,history_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			String sqlInsertBEC_msglog = "INSERT INTO bec_msglog(message_sn,message_date,branch_code,host_tran_sn,host_tran_date,host_real_date,"
					+ "teller_id, approver_id,ref_channel, send_time,receive_time,num_of_retry, ws_ip, ws_name,in_host_msg,out_host_msg, ref_tran_code,"
					+ "tran_sn, batch_no,ref_service, resp_code, resp_msg,sender_id,sorn,ref_cif_acct,ref_amt,history_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				String sender_id = messageInfo.getSender_id();
				oraConn.setAutoCommit(false);
				prpStmtInsertBEC_tllog = oraConn.prepareStatement(sqlInsertBEC_tllog);
				prpStmtInsertBEC_msglog = oraConn.prepareStatement(sqlInsertBEC_msglog);

				// Insert tllog
				prpStmtInsertBEC_tllog.setString(1, messageInfo.getMessage_sn());
				prpStmtInsertBEC_tllog.setString(2, AppUtils.now(Global.DEF_FORMAT_DATE_TIME));
				prpStmtInsertBEC_tllog.setString(3, messageInfo.getTran_code());
				prpStmtInsertBEC_tllog.setString(4, messageInfo.getTran_service_code());
				prpStmtInsertBEC_tllog.setString(5, messageInfo.getSender_id());
				prpStmtInsertBEC_tllog.setString(6, messageInfo.getSender_tran_sn());
				prpStmtInsertBEC_tllog.setString(7, messageInfo.getSend_date());
				prpStmtInsertBEC_tllog.setString(8, messageInfo.getSend_time());
				prpStmtInsertBEC_tllog.setString(9, messageInfo.getReceive_date());
				prpStmtInsertBEC_tllog.setString(10, messageInfo.getReceive_time());
				prpStmtInsertBEC_tllog.setString(11, messageInfo.getExecution_date());
				prpStmtInsertBEC_tllog.setString(12, messageInfo.getReceiver_id());
				prpStmtInsertBEC_tllog.setString(13, messageInfo.getReceiver_tran_sn());
				prpStmtInsertBEC_tllog.setString(14, messageInfo.getResp_date());
				prpStmtInsertBEC_tllog.setString(15, messageInfo.getResp_time());
				prpStmtInsertBEC_tllog.setString(16, messageInfo.getResp_code());
				prpStmtInsertBEC_tllog.setString(17, messageInfo.getResp_msg());
				prpStmtInsertBEC_tllog.setString(18, messageInfo.getServices());
				prpStmtInsertBEC_tllog.setCharacterStream(19, new StringReader(logMsg.getTradeMessageRequest()), logMsg.getTradeMessageRequest().length());
				prpStmtInsertBEC_tllog.setCharacterStream(20, new StringReader(logMsg.getTradeMessageResponse()), logMsg.getTradeMessageResponse().length());
				prpStmtInsertBEC_tllog.setTimestamp(21, getCurrentTimeStamp());
				prpStmtInsertBEC_tllog.executeUpdate();

				// Insert msglog
				ArrayList<HOSTMessagLogInfo> arrHOSTMessagLogInfo = logMsg.getArrHOSTMessagLogInfo();
				for (int i = 0; i < arrHOSTMessagLogInfo.size(); i++) {
					HOSTMessagLogInfo tranInfo = arrHOSTMessagLogInfo.get(i);
					prpStmtInsertBEC_msglog.setString(1, messageInfo.getMessage_sn());
					prpStmtInsertBEC_msglog.setString(2, AppUtils.now(Global.DEF_FORMAT_DATE_TIME));
					prpStmtInsertBEC_msglog.setString(3, tranInfo.getBranch_code());
					prpStmtInsertBEC_msglog.setString(4, tranInfo.getHost_tran_sn());
					prpStmtInsertBEC_msglog.setString(5, tranInfo.getHost_tran_date());
					prpStmtInsertBEC_msglog.setString(6, tranInfo.getHost_real_date());
					prpStmtInsertBEC_msglog.setString(7, tranInfo.getTeller_id());
					prpStmtInsertBEC_msglog.setString(8, tranInfo.getApprover_id());
					prpStmtInsertBEC_msglog.setString(9, tranInfo.getRef_channel());
					prpStmtInsertBEC_msglog.setString(10, tranInfo.getSend_time());
					prpStmtInsertBEC_msglog.setString(11, tranInfo.getReceive_time());
					prpStmtInsertBEC_msglog.setString(12, tranInfo.getNumOfRetry());
					prpStmtInsertBEC_msglog.setString(13, tranInfo.getWs_ip());
					prpStmtInsertBEC_msglog.setString(14, tranInfo.getWs_name());
					prpStmtInsertBEC_msglog.setCharacterStream(15, new StringReader(tranInfo.getHostRequestMsg()), tranInfo.getHostRequestMsg().length());
					prpStmtInsertBEC_msglog.setCharacterStream(16, new StringReader(tranInfo.getHostResponseMsg()), tranInfo.getHostResponseMsg().length());
					prpStmtInsertBEC_msglog.setString(17, tranInfo.getRef_tran_code());
					prpStmtInsertBEC_msglog.setString(18, tranInfo.getTran_sn());
					prpStmtInsertBEC_msglog.setString(19, tranInfo.getBatch_no());
					prpStmtInsertBEC_msglog.setString(20, tranInfo.getRef_service());
					prpStmtInsertBEC_msglog.setString(21, tranInfo.getResp_code());
					prpStmtInsertBEC_msglog.setString(22, tranInfo.getResp_msg());
					prpStmtInsertBEC_msglog.setString(23, sender_id);
					prpStmtInsertBEC_msglog.setString(24, tranInfo.getNeedSynchronized());
					prpStmtInsertBEC_msglog.setString(25, tranInfo.getRef_cif_acct());
					prpStmtInsertBEC_msglog.setString(26, tranInfo.getRef_amount());
					prpStmtInsertBEC_msglog.setTimestamp(27, getCurrentTimeStamp());
					prpStmtInsertBEC_msglog.executeUpdate();
				}

				// Commit
				oraConn.commit();
			} catch (SQLException e) {
				if (oraConn != null) {
					try {
						oraConn.rollback();
					} catch (SQLException excep) {
						logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".insertLogMessage()\n").append("Error message:")
								.append(excep.toString() + "\n"));
					}
				}
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".insertLogMessage()\n").append("Error message:").append(e.toString() + "\n"));
			} finally {
				if (prpStmtInsertBEC_tllog != null) {
					prpStmtInsertBEC_tllog.close();
				}
				if (prpStmtInsertBEC_msglog != null) {
					prpStmtInsertBEC_msglog.close();
				}
				if (oraConn != null) {
					try {
						oraConn.setAutoCommit(true);
						oraConn.close();
					} catch (SQLException e) {
						logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".insertLogMessage()\n").append("Error message:").append(e.toString() + "\n"));
					}
				}

			}

		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".insertLogMessage()\n").append("Error message:").append(e.toString() + "\n"));
		}
		return 0;
	}
}
