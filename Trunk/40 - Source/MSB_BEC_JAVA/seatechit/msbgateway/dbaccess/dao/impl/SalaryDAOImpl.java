package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.SalaryDAO;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALW;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALWH;
import seatechit.msbgateway.utils.CachedParam;

public class SalaryDAOImpl extends SpringFrameworkDAO implements SalaryDAO {
	String SALARY_UPDATE_COREBANK_LIB = "SVDATPV51";
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public boolean saveSalary(ArrayList<SalaryBKDDSALW> arrSalaryBKDDSALW,
			ArrayList<SalaryBKDDSALWH> arrSalaryBKDDSALWH) {
		Connection connToSIBS = null;
		boolean result = true;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		try {
			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("SAL_HOST_USER");
			String strPassword = CachedParam
					.getSystemParam("SAL_HOST_PASSWORD");
			SALARY_UPDATE_COREBANK_LIB = CachedParam
					.getSystemParam("SALARY_UPDATE_COREBANK_LIB");

			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);
			// Kienvt: tren UAT bo dong ben duoi,loi do server UAT qua lom
			connToSIBS.setAutoCommit(false);
			if (arrSalaryBKDDSALW != null && arrSalaryBKDDSALWH != null) {
				// DDSALW
				for (int i = 0; i < arrSalaryBKDDSALW.size(); i++) {

					SalaryBKDDSALW objSqlW = arrSalaryBKDDSALW.get(i);
					if (result) {
						if (objSqlW.getSDACCT().length() > 0) {
							// String insBKDDSALW = "INSERT INTO " +
							// SALARY_UPDATE_COREBANK_LIB + ".ddsalw VALUES('" +
							// objSqlW.getSDID() + "'," + objSqlW.getSDBRN() +
							// ",'" + objSqlW.getSDRCID() +
							// "','"
							// + objSqlW.getSDCTYP() + "'," + objSqlW.getSDSEQ()
							// + ",'" + objSqlW.getSDACCT() + "',0,0,'" +
							// objSqlW.getSDATYP() + "',0," + objSqlW.getSDAMT()
							// + ",0,0,'"
							// + objSqlW.getSDMUID() + "','" +
							// objSqlW.getSDMWID() + "'," + objSqlW.getSDMTIM()
							// + "," + objSqlW.getSDDAT6() + "," +
							// objSqlW.getSDDAT7() + ",' ','"
							// + objSqlW.getREMRK1() + "' ,'" +
							// objSqlW.getREMRK2() + " ',' ',' ')";
							String insBKDDSALW = "INSERT INTO "
									+ SALARY_UPDATE_COREBANK_LIB
									+ ".ddsalw(SDID,SDBRN,SDRCID,SDCTYP,SDSEQ,SDACCT,SDCOST,SDPROC,SDATYP,SDSTAT,SDAMT,SDAMT2,SDAVL,SDMUID,SDMWID,SDMTIM,SDDAT6,SDDAT7,SDACUR,REMRK1,REMRK2,SSRSV1,SSRSV2) VALUES('"
									+ objSqlW.getSDID() + "',"
									+ objSqlW.getSDBRN() + ",'"
									+ objSqlW.getSDRCID() + "','"
									+ objSqlW.getSDCTYP() + "',"
									+ objSqlW.getSDSEQ() + ",'"
									+ objSqlW.getSDACCT() + "',0,0,'"
									+ objSqlW.getSDATYP() + "',0,"
									+ objSqlW.getSDAMT() + ",0,0,'"
									+ objSqlW.getSDMUID() + "','"
									+ objSqlW.getSDMWID() + "',"
									+ objSqlW.getSDMTIM() + ","
									+ objSqlW.getSDDAT6() + ","
									+ objSqlW.getSDDAT7() + ",' ','"
									+ objSqlW.getREMRK1() + "' ,'"
									+ objSqlW.getREMRK2() + " ',' ',' ')";

							pst1 = connToSIBS.prepareStatement(insBKDDSALW);
							int ps1Rows = pst1.executeUpdate();
						}
						// if (ps1Rows == 0)
						// result = false;
					}
				}

				// DDSALWH
				if (result) {
					for (int j = 0; j < arrSalaryBKDDSALWH.size(); j++) {

						SalaryBKDDSALWH objSqlW = arrSalaryBKDDSALWH.get(j);
						// String insBKDDSALWH = "INSERT INTO " +
						// SALARY_UPDATE_COREBANK_LIB + ".ddsalwh VALUES('" +
						// objSqlW.getSMID() + "','" + objSqlW.getSMRCID() +
						// "','" + objSqlW.getSMACCT()
						// + "',0,0,'" + objSqlW.getSMATYP() + "'," +
						// objSqlW.getSMBRN() + ",'" + objSqlW.getSMCTYP() +
						// "',0," + objSqlW.getSMAMT() + ",0,0,0,0,'" +
						// objSqlW.getSSMUID() + "','"
						// + objSqlW.getSSMWID() + "'," + objSqlW.getSSMTIM() +
						// "," + objSqlW.getSMDAT6() + "," + objSqlW.getSMDAT7()
						// + "," + objSqlW.getSMTFR6() + "," +
						// objSqlW.getSMTFR7()
						// + ",0,0,' ',' ','" + objSqlW.getSMRMK1() + " ',' " +
						// objSqlW.getSMRMK2() + "')";

						String insBKDDSALWH = "INSERT INTO "
								+ SALARY_UPDATE_COREBANK_LIB
								+ ".ddsalwh(SMID,SMRCID,SMACCT,SMCOST,SMPROC,SMATYP,SMBRN,SMCTYP,SMSTAT,SMAMT,SMAVL,SMCNT,SMAMT2,SMCNT2,SSMUID,SSMWID,SSMTIM,SMDAT6,SMDAT7,SMTFR6,SMTFR7,SMLST6,SMLST7,SSRSV1,SSRSV2,SMRMK1,SMRMK2) VALUES('"
								+ objSqlW.getSMID() + "','"
								+ objSqlW.getSMRCID() + "','"
								+ objSqlW.getSMACCT() + "',0,0,'"
								+ objSqlW.getSMATYP() + "',"
								+ objSqlW.getSMBRN() + ",'"
								+ objSqlW.getSMCTYP() + "',0,"
								+ objSqlW.getSMAMT() + ",0,0,0,0,'"
								+ objSqlW.getSSMUID() + "','"
								+ objSqlW.getSSMWID() + "',"
								+ objSqlW.getSSMTIM() + ","
								+ objSqlW.getSMDAT6() + ","
								+ objSqlW.getSMDAT7() + ","
								+ objSqlW.getSMTFR6() + ","
								+ objSqlW.getSMTFR7() + ",0,0,' ',' ','"
								+ objSqlW.getSMRMK1() + " ',' "
								+ objSqlW.getSMRMK2() + "')";

						pst2 = connToSIBS.prepareStatement(insBKDDSALWH);
						int ps2Rows = pst2.executeUpdate();
						// if (ps2Rows == 0)
						// result = false;
					}
				}
				// Commit
				connToSIBS.commit();
			} else {
				result = false;
			}
		} catch (SQLException e) {
			if (connToSIBS != null) {
				try {
					connToSIBS.rollback();
				} catch (SQLException excep) {
					logger.error(new StringBuffer().append("Error source:")
							.append(CLASS_NAME + ".saveSalary()\n").append(
									"Error message:").append(
									excep.toString() + "\n"));
				}
			}
			result = false;
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				if (pst2 != null) {
					pst2.close();
				}
				connToSIBS.setAutoCommit(true);
				connToSIBS.close();
			} catch (Exception ex) {
				logger.error(new StringBuffer().append("Error source:").append(
						CLASS_NAME + ".saveSalary()\n")
						.append("Error message:").append(ex.toString() + "\n"));
			}
		}
		return result;
	}
}
