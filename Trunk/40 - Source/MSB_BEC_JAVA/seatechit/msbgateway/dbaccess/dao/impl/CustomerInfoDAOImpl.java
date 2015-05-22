package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.CustomerInfoDAO;
import seatechit.msbgateway.dbaccess.entity.CustomerInfo;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;

public class CustomerInfoDAOImpl extends SpringFrameworkDAO implements CustomerInfoDAO {
	private String CUST_INQ_COREBANK_LIB = "SVDATPV51";
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public CustomerInfoDAOImpl() {
	}

	@Override
	public CustomerInfo getCustomerInfoByCIFOrID(String cif_no, String cert_code, String cert_type) {
		Connection connToSIBS = null;
		PreparedStatement pst1 = null;
		CustomerInfo temp = null;
		ResultSet rs = null;
		try {
			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("CBS_HOST_USER");
			String strPassword = CachedParam.getSystemParam("CBS_HOST_PASSWORD");
			CUST_INQ_COREBANK_LIB = CachedParam.getSystemParam("CUST_INQ_COREBANK_LIB");

			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);
			String strSQL = "";

			// Kienvt neu truong hop lay theo cert_code thi truoc het truy van
			// ra so cif_no
			// sau do moi truy van tiep,ko dung subquery
			if (cif_no.length() == 0) {
				strSQL += "SELECT a.cfcifn cif_no FROM " + CUST_INQ_COREBANK_LIB + ".cfmast a WHERE a.cfssno = '" + cert_code + "' AND a.cfsscd='" + cert_type + "'";
				pst1 = connToSIBS.prepareStatement(strSQL);
				rs = pst1.executeQuery();
				while (rs != null && rs.next()) {
					cif_no = rs.getString("cif_no");
				}
			}

			if (cif_no.length() > 0) {
				strSQL = "";
				strSQL += " SELECT a.cfcifn cif_no,a.cfsscd cert_type,TRIM (a.cfssno) cert_code,'320' bank_no,TRIM (a.cfna1) cif_acct_name,";
				strSQL += " a.cfbird birth_date,TRIM (a.cfbirp) birth_place,TRIM(a.cfcitz) country,a.cfindi individual,a.cfcsts status,";
				strSQL += " a.cfbrnn branch_no,'' contact_person,a.cfsex gender,(SELECT TRIM (sc.cfeadd) FROM " + CUST_INQ_COREBANK_LIB + ".cfconn sc";
				strSQL += " WHERE sc.cfeadc = 'EM' AND sc.cfatyp = 'C' AND sc.cfaccn = '" + cif_no + "'";
				strSQL += " AND sc.cfzseq = (SELECT MAX (sc2.cfzseq) FROM " + CUST_INQ_COREBANK_LIB + ".cfconn sc2";
				strSQL += " WHERE sc2.cfaccn = sc.cfaccn AND sc2.cfatyp = sc.cfatyp AND sc2.cfeadc = sc.cfeadc)) email,";
				strSQL += " (SELECT TRIM (sc.cfeadd) FROM " + CUST_INQ_COREBANK_LIB + ".cfconn sc WHERE sc.cfeadc = 'MP' AND sc.cfatyp = 'C' AND sc.cfaccn = '" + cif_no + "'";
				strSQL += " AND sc.cfzseq = (SELECT MAX (sc2.cfzseq) FROM " + CUST_INQ_COREBANK_LIB + ".cfconn sc2";
				strSQL += " WHERE sc2.cfaccn = sc.cfaccn AND sc2.cfatyp = sc.cfatyp AND sc2.cfeadc = sc.cfeadc)) mobile,";
				strSQL += " (SELECT TRIM (sc.cfeadd) FROM " + CUST_INQ_COREBANK_LIB + ".cfconn sc";
				strSQL += " WHERE sc.cfeadc = 'HP' AND sc.cfatyp = 'C' AND sc.cfaccn = '" + cif_no + "' AND sc.cfzseq =";
				strSQL += " (SELECT MAX (sc2.cfzseq) FROM " + CUST_INQ_COREBANK_LIB + ".cfconn sc2 WHERE sc2.cfaccn = sc.cfaccn";
				strSQL += " AND sc2.cfatyp = sc.cfatyp AND sc2.cfeadc = sc.cfeadc)) telephone,";
				strSQL += " (SELECT( TRIM (cfna2) || ' ' || TRIM (cfna3) || ' ' || TRIM (cfna3) ) addr";
				strSQL += " FROM " + CUST_INQ_COREBANK_LIB + ".cfaddr d WHERE d.cfcifn = '" + cif_no + "' AND a.cfadsq = d.cfadsq AND a.cfcifn = d.cfcifn) addr";
				strSQL += " FROM " + CUST_INQ_COREBANK_LIB + ".cfmast a WHERE a.cfcifn = '" + cif_no + "'";

				pst1 = connToSIBS.prepareStatement(strSQL);
				pst1.setQueryTimeout(JDBC_QUERY_TIMEOUT);
				rs = pst1.executeQuery();

				while (rs != null && rs.next()) {
					temp = new CustomerInfo();
					temp.setCif_no(AppUtils.correctStringValue(rs.getString("cif_no")));
					temp.setCif_acct_name(AppUtils.correctStringValue(rs.getString("cif_acct_name")));
					temp.setStatus(AppUtils.correctStringValue(rs.getString("status")));
					temp.setBank_no(AppUtils.correctStringValue(rs.getString("bank_no")));
					temp.setGender(AppUtils.correctStringValue(rs.getString("gender")));
					temp.setCert_code(AppUtils.correctStringValue(rs.getString("cert_code")));
					temp.setCert_type(AppUtils.correctStringValue(rs.getString("cert_type")));
					temp.setCountry(AppUtils.correctStringValue(rs.getString("country")));
					temp.setBirth_date(AppUtils.correctStringValue(rs.getString("birth_date")));
					temp.setBirth_place(AppUtils.correctStringValue(rs.getString("birth_place")));
					temp.setIndividual(AppUtils.correctStringValue(rs.getString("individual")));
					temp.setTelephone(AppUtils.correctStringValue(rs.getString("telephone")));
					temp.setAddr(AppUtils.correctStringValue(rs.getString("addr")));
					temp.setEmail(AppUtils.correctStringValue(rs.getString("email")));
					temp.setMobile(AppUtils.correctStringValue(rs.getString("mobile")));
					temp.setContact_person(AppUtils.correctStringValue(rs.getString("contact_person")));
					temp.setBranch_no(AppUtils.correctStringValue(rs.getString("branch_no")));
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getCustomerInfoByCIFOrID()\n").append("Error message:").append(e.toString() + "\n"));
			return null;
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				if (connToSIBS != null) {
					try {
						connToSIBS.close();
					} catch (SQLException e) {
						logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getCustomerInfoByCIFOrID()\n").append("Error message:").append(e.toString() + "\n"));
					}
				}
			} catch (Exception e) {
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getCustomerInfoByCIFOrID()\n").append("Error message:").append(e.toString() + "\n"));
			}
		}
		return temp;
	}
}
