package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.consumer.SIBSConsumer;
import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;

import seatechit.msbgateway.dbaccess.dao.AftInfoDAO;

import seatechit.msbgateway.dbaccess.entity.AftInfo;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.CoreBankUtils;
import seatechit.msbgateway.utils.Global;

/**
 * Inquiry customer's account from AS400 using JDBC.
 *
 */
public class AftInfoDAOImpl extends SpringFrameworkDAO implements AftInfoDAO {
	private String ACCT_INQ_COREBANK_LIB = "SVDATPV51";
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public AftInfoDAOImpl() {
	}

	public ArrayList<AftInfo> getAftInfos(String cif_no,String rollout_acct_no,String bnfc_acct_no, String rollout_acct_type,String bnfc_acct_type,String aft_prod) {
		Connection connToSIBS = null;
		PreparedStatement pst1 = null;
		ArrayList<AftInfo> arrAftInfo = new ArrayList<AftInfo>();
		try {

			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("CBS_HOST_USER");
			String strPassword = CachedParam.getSystemParam("CBS_HOST_PASSWORD");
			ACCT_INQ_COREBANK_LIB = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB");

			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);			
			
			StringBuffer strSQL = new StringBuffer();
			strSQL.append( "select distinct a.*,b.type as CDTYPE from "+ACCT_INQ_COREBANK_LIB+".ddaftm a, ( " );
			strSQL.append( " select acctno,status,cifno,type from "+ACCT_INQ_COREBANK_LIB+".cdmast where status != 2 and cifno =  '" + cif_no +"' " );
			strSQL.append( " union select acctno,status,cifno,type from "+ACCT_INQ_COREBANK_LIB+".cdtnew where status != 2 and cifno = '"+cif_no+"') b ,  ");
			strSQL.append( " (select acctno,status,cifno from "+ACCT_INQ_COREBANK_LIB+".ddmast where status != 2 and cifno = '"+cif_no+"' " );
			strSQL.append( " union select acctno,status,cifno from "+ACCT_INQ_COREBANK_LIB+".ddtnew where status != 2 and cifno = '"+cif_no+"') e ");
			strSQL.append( " where a.crtype = '"+bnfc_acct_type+"' and a.drtype = '"+rollout_acct_type+"' and  aftpcd = '"+aft_prod+"' and  ");
			strSQL.append( " a.cracct = b.acctno and a.dracct = e.acctno ");
			if (!(rollout_acct_no == null || "".equals(rollout_acct_no))) 
				strSQL.append( " and a.dracct = '" + rollout_acct_no + "' ");
			
			if (!(bnfc_acct_no == null || "".equals(bnfc_acct_no)) ) 
				strSQL.append( " and a.cracct = '" + bnfc_acct_no + "' ");
			pst1 = connToSIBS.prepareStatement(strSQL.toString());
			pst1.setQueryTimeout(JDBC_QUERY_TIMEOUT);
			ResultSet rs = pst1.executeQuery();
			while (rs != null && rs.next()) {
				AftInfo temAftInfo = new AftInfo();
				temAftInfo.setStatus(AppUtils.correctStringValue(rs.getString("DRSTAT")));
				temAftInfo.setRollout_Acct_No(AppUtils.correctStringValue(rs.getString("DRACCT")));
				temAftInfo.setRollout_acct_ccy(AppUtils.correctStringValue(rs.getString("DRCTYP")));
				
				temAftInfo.setRollout_acct_type(AppUtils.correctStringValue(rs.getString("DRTYPE")));
				temAftInfo.setBnfc_Acct_No(AppUtils.correctStringValue(rs.getString("CRACCT")));
				temAftInfo.setBnfc_acct_ccy(AppUtils.correctStringValue(rs.getString("CRCTYP")));
				temAftInfo.setBnfc_acct_type(AppUtils.correctStringValue(rs.getString("CRTYPE")));
				temAftInfo.setEntered_date(AppUtils.correctStringValue(rs.getString("AFENT6")));
				temAftInfo.setStarted_Date(AppUtils.correctStringValue(rs.getString("ORGDT6")));
				temAftInfo.setExpired_Date(AppUtils.correctStringValue(rs.getString("EXPDT6")));
				temAftInfo.setNext_Transfer_Date(AppUtils.correctStringValue(rs.getString("NXTDT6")));
				temAftInfo.setLast_Transfer_Date(AppUtils.correctStringValue(rs.getString("LSTDT6")));
				temAftInfo.setAmount(AppUtils.correctStringValue(rs.getString("TRFAMT")));
				temAftInfo.setAft_Fee(AppUtils.correctStringValue(rs.getString("AFTFEE")));
				temAftInfo.setFreq_No(AppUtils.correctStringValue(rs.getString("FREQ")));
				temAftInfo.setFreq_Code(AppUtils.correctStringValue(rs.getString("FREQCD")));
				temAftInfo.setDebit_Seq(AppUtils.correctStringValue(rs.getString("DRSEQ")));
				temAftInfo.setBnfc_acct_prod_type(AppUtils.correctStringValue(rs.getString("CDTYPE")));
				
	
				arrAftInfo.add(temAftInfo);
			}
		} catch (SQLException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAccountInfoByCif()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				try {
					connToSIBS.close();
				} catch (SQLException excep) {
					excep.printStackTrace();
					logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAccountInfoByCif()\n").append("Error message:").append(excep.toString() + "\n"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAccountInfoByCif()\n").append("Error message:").append(ex.toString() + "\n"));
			}
		}
		return arrAftInfo;
	}

	
}
