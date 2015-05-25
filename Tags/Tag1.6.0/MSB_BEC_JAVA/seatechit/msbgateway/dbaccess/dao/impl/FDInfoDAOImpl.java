package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;


import seatechit.msbgateway.dbaccess.dao.FDInfoDAO;

import seatechit.msbgateway.dbaccess.entity.FDInfo;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.CoreBankUtils;
import seatechit.msbgateway.utils.Global;

/**
 * Inquiry customer's account from AS400 using JDBC.
 *
 */
public class FDInfoDAOImpl extends SpringFrameworkDAO implements FDInfoDAO {
	private String ACCT_INQ_COREBANK_LIB = "SVDATPV51";
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public FDInfoDAOImpl() {
	}

	public ArrayList<FDInfo> getFDInfos(String cif_no,String group_acct,String receipt_no) {
		Connection connToSIBS = null;
		PreparedStatement pst1 = null;
		ArrayList<FDInfo> arrAftInfo = new ArrayList<FDInfo>();
		try {
			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("CBS_HOST_USER");
			String strPassword = CachedParam.getSystemParam("CBS_HOST_PASSWORD");
			ACCT_INQ_COREBANK_LIB = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB");

			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);			
			
			StringBuffer strSQL = new StringBuffer();
			strSQL.append( "select a.acctno,a.acname,a.type,a.cdnum,a.orgbal,a.issdt6,a.matdt6,a.accint,a.curtyp,a.cdterm,a.rate, ");
			strSQL.append( "case when (b.cbal is NULL) then a.cbal else b.cbal end as cbal, ");
			strSQL.append( "case when (b.hold is NULL) then a.hold else b.hold end as hold, ");
			strSQL.append( "case when (b.status is NULL) then a.status else b.status end as status ");
			strSQL.append( "from  ");
			strSQL.append( "(select acctno,acname,type,status,cdnum,orgbal,cbal,hold,issdt6,matdt6,accint,curtyp,cdterm,rate from "+ACCT_INQ_COREBANK_LIB+".cdmast where actype = 'T' and cifno = '"+cif_no+"' and status != 2 ");
			if (!(group_acct == null || "".equals(group_acct))) 
				strSQL.append( " and cdnum =  '"+group_acct+"'  ");
			strSQL.append( "union ");
			strSQL.append( "select acctno,acname,type,status,cdnum,orgbal,cbal,hold,issdt6,matdt6,accint,curtyp,cdterm,rate from "+ACCT_INQ_COREBANK_LIB+".cdtnew where actype = 'T' and cifno = '"+cif_no+"' and status != 2 ");
			if (!(group_acct == null || "".equals(group_acct))) 
				strSQL.append( " and cdnum =  '"+group_acct+"'  ");
			strSQL.append( ")  ");

			strSQL.append( "a left join "+ACCT_INQ_COREBANK_LIB+".cdmemo b on a.acctno = b.acctno ");	
						
			
			
			pst1 = connToSIBS.prepareStatement(strSQL.toString());
			pst1.setQueryTimeout(JDBC_QUERY_TIMEOUT);
			ResultSet rs = pst1.executeQuery();
			while (rs != null && rs.next()) {
				FDInfo temFDInfo = new FDInfo();
				temFDInfo.setGroup_Acct(AppUtils.correctStringValue(rs.getString("cdnum")));
				temFDInfo.setReceipt_No(AppUtils.correctStringValue(rs.getString("acctno")));
				temFDInfo.setReceipt_Product_Code(AppUtils.correctStringValue(rs.getString("type")));
				temFDInfo.setInterest_Rate(AppUtils.correctStringValue(rs.getString("rate")));
				temFDInfo.setOriginal_Balance(AppUtils.correctStringValue(rs.getString("orgbal")));
				temFDInfo.setIssue_Date(AppUtils.correctStringValue(rs.getString("issdt6")));
				temFDInfo.setMaturity_Date(AppUtils.correctStringValue(rs.getString("matdt6")));
				temFDInfo.setAccurated_Interest(AppUtils.correctStringValue(rs.getString("accint")));
				temFDInfo.setCur_Type(AppUtils.correctStringValue(rs.getString("curtyp")));
				temFDInfo.setCd_Term(AppUtils.correctStringValue(rs.getString("cdterm")));
				temFDInfo.setAvailable_Balance(AppUtils.correctStringValue(rs.getString("cbal")));
				temFDInfo.setHold(AppUtils.correctStringValue(rs.getString("hold")));
				temFDInfo.setStatus(AppUtils.correctStringValue(rs.getString("status")));
				arrAftInfo.add(temFDInfo);
			}
		} catch (SQLException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getFDInfos()\n").append("Error message:").append(e.toString() + "\n"));
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
