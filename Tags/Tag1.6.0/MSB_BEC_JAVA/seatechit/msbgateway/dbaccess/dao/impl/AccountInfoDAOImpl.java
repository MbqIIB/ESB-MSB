package seatechit.msbgateway.dbaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.SpringFrameworkDAO;
import seatechit.msbgateway.dbaccess.dao.AccountInfoDAO;
import seatechit.msbgateway.dbaccess.entity.AccountInfo;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.CoreBankUtils;
import seatechit.msbgateway.utils.Global;
import seatechit.msbgateway.utils.SQLUtils;

/**
 * Inquiry customer's account from AS400 using JDBC.
 *
 */
public class AccountInfoDAOImpl extends SpringFrameworkDAO implements AccountInfoDAO {
	private String ACCT_INQ_COREBANK_LIB = "SVDATPV51";
	private String ACCT_INQ_COREBANK_LIB_OFFLINE = "SVDATP24H";
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	public AccountInfoDAOImpl() {
	}

	@Override
	public AccountInfo getAccountInfoByAccountNo(String acct_no, String acct_type, String online) {
		Connection connToSIBS = null;
		PreparedStatement pst1 = null;
		AccountInfo temAccountInfo = null;
		try {
			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("CBS_HOST_USER");
			String strPassword = CachedParam.getSystemParam("CBS_HOST_PASSWORD");
			ACCT_INQ_COREBANK_LIB = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB");
			//TODO 26/02/201212
//			ACCT_INQ_COREBANK_LIB_OFFLINE = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB_OFFLINE");
						
			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);
			StringBuffer strSQL = new StringBuffer();
			if (acct_type.equals(Global.COREBBANK_CA_ACCOUNT_TYPE) || acct_type.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
				//
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
				strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
				strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
				strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
				strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
				strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal,(SELECT TRIM(tmbmrser) FROM " + ACCT_INQ_COREBANK_LIB + ".tmpbmast WHERE tmbmacct = a.acctno) passbook_no, ");
				strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal + sub.odlimt - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
				strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE  sub.acctno = '" + acct_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno ");
				strSQL.append( "  AND a.status <> '2') memo_bal FROM " + ACCT_INQ_COREBANK_LIB + ".ddmast a," + ACCT_INQ_COREBANK_LIB + ".cfacct b ");
				strSQL.append( "  WHERE b.cfaccn = '" + acct_no + "' AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
				strSQL.append( "  UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,");
				strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date,");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type,");
				strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type,");
				strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status,");
				strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due, TRIM (a.odlimt) overdraft_limit,");
				strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal,(SELECT TRIM(tmbmrser) FROM " + ACCT_INQ_COREBANK_LIB + ".tmpbmast WHERE tmbmacct = a.acctno) passbook_no,");
				strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal + sub.odlimt - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE sub.acctno = '" + acct_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfaccn = '" + acct_no + "'");
				strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
				
				//TODO 247
//				if("true".equals(online)){
//					strSQL = SQLUtils.buildSQLGetAccountInfoByAccountNoOnline(strSQL, ACCT_INQ_COREBANK_LIB, acct_no);
//				}else{
//					strSQL = SQLUtils.buildSQLGetAccountInfoByAccountNoOffline(strSQL, ACCT_INQ_COREBANK_LIB,ACCT_INQ_COREBANK_LIB_OFFLINE, acct_no);
//				}
				
				
			} else if (acct_type.equals(Global.COREBBANK_LN_ACCOUNT_TYPE)) {
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
				strSQL.append( "  mst.acct_status,mst.issued_date,mst.date_acct_opened,mst.maturity_date,'' establish_date,mst.full_release_date,");
				strSQL.append( "  COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,'' passbook_no,");
				strSQL.append( "  '0' interest_rate,mst.accured_interest,'' accured_interest_due,mst.loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.brn) branch_no,TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,");
				strSQL.append( "  TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type,TRIM (a.curtyp) acct_ccy,TRIM (a.status) acct_status,");
				strSQL.append( "  TRIM (a.lnnum) loan_no,TRIM (a.datopn) date_acct_opened,TRIM (a.datopn) issued_date,TRIM (a.purcod) purpose_code,");
				strSQL.append( "  TRIM (a.fulldt) full_release_date,TRIM (a.matdt) maturity_date,TRIM (a.odind) overdue_indicator_description,");
				strSQL.append( "  TRIM (a.TYPE) product_code,TRIM (a.freq) principal_frequent,TRIM(prterm) term,TRIM(prcode) term_type,TRIM (a.ipfreq) interest_frequent,");
				strSQL.append( "  TRIM (a.orgamt) original_balance,TRIM (a.cbal) os_principal,TRIM (a.orgamt - a.amtrel) available_limit,");
				strSQL.append( "  TRIM (a.ysobal + a.billco) os_balance,TRIM(  a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc)billed_total_amount,");
				strSQL.append( "  TRIM (a.bilprn) billed_principal,TRIM (a.bilint) billed_interest,TRIM (a.billc) billed_late_charge,");
				strSQL.append( "  TRIM (a.othchg) other_charges,TRIM (a.drlimt) overdraft_limit,TRIM (a.hold) hold_amount,TRIM (a.ysacci + a.accint) accured_interest,");
				strSQL.append( "  TRIM (a.comacc) accrued_common_fee,TRIM (a.accmlc) accrued_late_charge,TRIM (a.orgamt - a.amtrel) available_limit,");
				strSQL.append( "  TRIM (a.hold) earmarked_amt,TRIM (a.pmtamt) payment_amount,TRIM (a.fnlpmt) final_payment_amt,");
				strSQL.append( "  (TRIM (a.orgamt) || ':' || TRIM (a.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.ysobal + a.billco)");
				strSQL.append( "  || ':' || TRIM(a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc) || ':' || TRIM (a.bilprn) || ':' || ");
				strSQL.append( "  TRIM (a.bilint) || ':' || TRIM (a.billc) || ':' || TRIM (a.othchg) || ':' || TRIM (a.drlimt) || ':' || ");
				strSQL.append( "  TRIM (a.hold) || ':' || TRIM (a.accint) || ':' || TRIM (a.comacc) || ':' ||  TRIM (a.accmlc) || ':' || ");
				strSQL.append( "  TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt)) all_bal,");
				strSQL.append( "  (SELECT (TRIM (a.orgamt) || ':' || TRIM (sub.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || ");
				strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM(sub.bilprn + sub.bilint + sub.billc + sub.bilesc + sub.biloc + sub.bilmc)");
				strSQL.append( "  || ':' || TRIM (sub.bilprn) || ':' || TRIM (sub.bilint) || ':' || TRIM (sub.billc) || ':' || TRIM (sub.othchg) || ':' ||");
				strSQL.append( "  TRIM (sub.drlimt) || ':' || TRIM (sub.hold) || ':' || TRIM (sub.accint) || ':' || TRIM (sub.comacc) || ':' || TRIM (a.accmlc) || ':' ||");
				strSQL.append( "  TRIM (a.orgamt - sub.amtrel) || ':' || TRIM (sub.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt))");
				strSQL.append( "  inner_bal FROM  " + ACCT_INQ_COREBANK_LIB + ".lnmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM   " + ACCT_INQ_COREBANK_LIB + ".lnmast a, " + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfaccn = '" + acct_no + "'");
				strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
				strSQL.append( " UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,");
				strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,mst.maturity_date,'' establish_date,mst.full_release_date,");
				strSQL.append( "  COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,'' passbook_no,'0' interest_rate,");
				strSQL.append( "  mst.accured_interest,'' accured_interest_due,mst.loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.brn) branch_no,TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.acctno) acct_no,");
				strSQL.append( "  TRIM (a.actype) acct_type,TRIM (a.curtyp) acct_ccy,TRIM (a.status) acct_status,TRIM (a.lnnum) loan_no,");
				strSQL.append( "  TRIM (a.datopn) date_acct_opened,TRIM (a.datopn) issued_date,TRIM (a.purcod) purpose_code,TRIM (a.fulldt) full_release_date,");
				strSQL.append( "  TRIM (a.matdt) maturity_date,TRIM (a.odind) overdue_indicator_description, TRIM (a.TYPE) product_code, TRIM (a.freq) principal_frequent,");
				strSQL.append( "  TRIM(prterm) term,TRIM(prcode) term_type,TRIM (a.ipfreq) interest_frequent,TRIM (a.orgamt) original_balance,TRIM (a.cbal) os_principal,TRIM (a.orgamt - a.amtrel) available_limit,");
				strSQL.append( "  TRIM (a.ysobal + a.billco) os_balance,TRIM (a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc) billed_total_amount,");
				strSQL.append( "  TRIM (a.bilprn) billed_principal,TRIM (a.bilint) billed_interest,TRIM (a.billc) billed_late_charge,TRIM (a.othchg) other_charges,");
				strSQL.append( "  TRIM (a.drlimt) overdraft_limit,TRIM (a.hold) hold_amount,TRIM (a.ysacci + a.accint) accured_interest,TRIM (a.comacc) accrued_common_fee,");
				strSQL.append( "  TRIM (a.accmlc) accrued_late_charge,TRIM (a.orgamt - a.amtrel) available_limit,TRIM (a.hold) earmarked_amt,TRIM (a.pmtamt) payment_amount,");
				strSQL.append( "  TRIM (a.fnlpmt) final_payment_amt,(TRIM (a.orgamt) || ':' || TRIM (a.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':'||");
				strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM (a.bilprn+ a.bilint+ a.billc+ a.bilesc+ a.biloc+ a.bilmc) || ':' ||");
				strSQL.append( "  TRIM (a.bilprn) || ':' || TRIM (a.bilint) || ':' || TRIM (a.billc) || ':' || TRIM (a.othchg) || ':' ||");
				strSQL.append( "  TRIM (a.drlimt) || ':' || TRIM (a.hold) || ':' || TRIM (a.accint) || ':' || TRIM (a.comacc) || ':' ||");
				strSQL.append( "  TRIM (a.accmlc) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.hold) || ':' || TRIM (a.pmtamt) || ':' ||");
				strSQL.append( "  TRIM (a.fnlpmt)) all_bal, (SELECT (TRIM (a.orgamt) || ':' || TRIM (sub.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || ");
				strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM(sub.bilprn + sub.bilint + sub.billc + sub.bilesc + sub.biloc + sub.bilmc)");
				strSQL.append( "  || ':' || TRIM (sub.bilprn) || ':' || TRIM (sub.bilint) || ':' || TRIM (sub.billc) || ':' || TRIM (sub.othchg) || ':' ||");
				strSQL.append( "  TRIM (sub.drlimt) || ':' || TRIM (sub.hold) || ':' || TRIM (sub.accint) || ':' || TRIM (sub.comacc) || ':' || TRIM (a.accmlc) || ':' ||");
				strSQL.append( "  TRIM (a.orgamt - sub.amtrel) || ':' || TRIM (sub.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt))");
				strSQL.append( "  inner_bal FROM  " + ACCT_INQ_COREBANK_LIB + ".lnmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB + ".lntnew a, " + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfaccn = '" + acct_no + "' AND a.acctno = b.cfaccn");
				strSQL.append( "  AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
			} else if (acct_type.equals(Global.COREBBANK_FD_RECEIPT_ACCOUNT_TYPE)) {
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,mst.p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
				strSQL.append( "  mst.acct_status,mst.issued_date,mst.issued_date date_acct_opened,mst.maturity_date,mst.establish_date,");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,product_code,term,term_type,");
				strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,'' accured_interest_due,'' loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.actype) acct_type,TRIM (a.acctno) acct_no,TRIM (a.brn) branch_no,");
				strSQL.append( "  TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.cdterm) term,TRIM (a.cdtcod) term_type,");
				strSQL.append( "  TRIM (a.curtyp) acct_ccy,TRIM (a.orgbal) ori_balance,TRIM (a.cbal) principal_balance,TRIM (a.accint) accured_interest,");
				strSQL.append( "  TRIM (a.penamt) penalty_amt,TRIM (a.hold) hold_amt,TRIM(a.orgbal || ':' || a.cbal || ':' || a.accint");
				strSQL.append( "  || ':' || a.penamt || ':' || a.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) all_bal,TRIM(a.cdrsrn) passbook_no,");
				strSQL.append( "  (a.cbal + a.accint - a.penamt - a.hold - a.wdrwh) current_cash_value, TRIM (a.issdt6) issued_date,");
				strSQL.append( "  TRIM (a.matdt6) maturity_date,TRIM (a.statd6) establish_date,TRIM (a.status) acct_status,TRIM (a.rate) interest_rate,");
				strSQL.append( "  TRIM (a.wdrwh),TRIM (a.cdnum) p_acct_no,TRIM (a.rnwctr),TRIM (a.renew),TRIM (a.dactn),TRIM (a.TYPE) product_code,");
				strSQL.append( "  (SELECT TRIM(a.orgbal || ':' || sub.cbal || ':' || sub.accint || ':' || sub.penamt || ':' || sub.hold || ':' || ");
				strSQL.append( "  (TRIM(cbal + accint - penamt - hold - wdrwh)))inner_bal FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub ");
				strSQL.append( "  WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal FROM " + ACCT_INQ_COREBANK_LIB + ".cdmast a," + ACCT_INQ_COREBANK_LIB + ".cfacct b");
				strSQL.append( "  WHERE a.acctno = '" + acct_no + "' AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
				strSQL.append( " UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,mst.p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
				strSQL.append( "  mst.acct_status,mst.issued_date,mst.issued_date date_acct_opened,mst.maturity_date,mst.establish_date,");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,");
				strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,'' accured_interest_due,'' loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.actype) acct_type,TRIM (a.acctno) acct_no,TRIM (a.brn) branch_no,");
				strSQL.append( "  TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.cdterm) term,TRIM (a.cdtcod) term_type,");
				strSQL.append( "  TRIM (a.curtyp) acct_ccy,TRIM (a.orgbal) ori_balance,TRIM (a.cbal) principal_balance,TRIM (a.accint) accured_interest,");
				strSQL.append( "  TRIM (a.penamt) penalty_amt,TRIM (a.hold) hold_amt,TRIM (a.orgbal || ':' || a.cbal || ':' || a.accint || ':' || a.penamt || ':' || a.hold || ':'");
				strSQL.append( "  || (TRIM (cbal + accint - penamt - hold - wdrwh))) all_bal,TRIM(a.cdrsrn) passbook_no, (a.cbal + a.accint - a.penamt - a.hold - a.wdrwh) current_cash_value,");
				strSQL.append( "  TRIM (a.issdt6) issued_date,TRIM (a.matdt6) maturity_date,TRIM (a.statd6) establish_date,TRIM (a.status) acct_status,");
				strSQL.append( "  TRIM (a.rate) interest_rate,TRIM (a.wdrwh),TRIM (a.cdnum) p_acct_no,TRIM (a.rnwctr),TRIM (a.renew),TRIM (a.dactn),");
				strSQL.append( "  TRIM (a.TYPE) product_code,(SELECT (a.orgbal || ':' || sub.cbal || ':' || sub.accint || ':' || sub.penamt || ':'");
				strSQL.append( "  || sub.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) inner_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE a.acctno = '" + acct_no + "'");
				strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
			} else if (acct_type.equals(Global.COREBBANK_FD_GROUP_ACCOUNT_TYPE)) {
				// FD group
				strSQL.append( " SELECT '032' bank_no,'' branch_no,TRIM (a.cfagno) acct_no,'' p_acct_no,'G' acct_type,TRIM (a.cfgnam) acct_name,");
				strSQL.append( " TRIM (a.cfgcur) acct_ccy,TRIM (a.cfcifn) cif_no,'' acct_status,'' issued_date,TRIM (a.cfagd7) date_acct_opened,");
				strSQL.append( " TRIM (a.cfcmd7) maturity_date,'' establish_date,'' full_release_date,");
				strSQL.append( " (a.cfgrat || ':' || a.cfggbl || ':' || a.cfgpbl || ':' || a.cfgint || ':' || a.cfgpen || ':' ||");
				strSQL.append( " a.cfghld || ':' || a.cfgflt || ':' || a.cfgcft) real_bal,'' product_code,'' term,'' term_type,'' passbook_no,");
				strSQL.append( " TRIM(a.cfgrat) interest_rate,TRIM (a.cfgint) accured_interest,'' accured_interest_due,'' loan_no FROM " + ACCT_INQ_COREBANK_LIB + ".cfagrp a");
				strSQL.append( " WHERE a.cfagno = '" + acct_no + "'");
			}
			
			pst1 = connToSIBS.prepareStatement(strSQL.toString());
			pst1.setQueryTimeout(JDBC_QUERY_TIMEOUT);
			ResultSet rs = pst1.executeQuery();
			while (rs != null && rs.next()) {
				temAccountInfo = new AccountInfo();
				temAccountInfo.setBank_no(AppUtils.correctStringValue(rs.getString("bank_no")));
				temAccountInfo.setBranch_no(AppUtils.correctStringValue(rs.getString("branch_no")));
				temAccountInfo.setAccount_no(AppUtils.correctStringValue(rs.getString("acct_no")));
				temAccountInfo.setP_acct_no(AppUtils.correctStringValue(rs.getString("p_acct_no")));
				temAccountInfo.setAcct_type(AppUtils.correctStringValue(rs.getString("acct_type")));
				temAccountInfo.setAcct_name(AppUtils.correctStringValue(rs.getString("acct_name")));
				temAccountInfo.setAcct_ccy(AppUtils.correctStringValue(rs.getString("acct_ccy")));
				temAccountInfo.setCif_no(AppUtils.correctStringValue(rs.getString("cif_no")));
				temAccountInfo.setAcct_status(AppUtils.correctStringValue(rs.getString("acct_status")));
				temAccountInfo.setIssued_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("issued_date"))));
				temAccountInfo.setDate_acct_opened(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("date_acct_opened"))));
				temAccountInfo.setMaturity_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("maturity_date"))));
				temAccountInfo.setEstablish_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("establish_date"))));
				temAccountInfo.setFull_release_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("full_release_date"))));
				temAccountInfo.setProduct_code(AppUtils.correctStringValue(rs.getString("product_code")));
				temAccountInfo.setTerm(AppUtils.correctStringValue(rs.getString("term")));
				temAccountInfo.setTerm_code(AppUtils.correctStringValue(rs.getString("term_type")).toLowerCase());
				temAccountInfo.setAccured_interest_due(AppUtils.correctStringValue(rs.getString("accured_interest_due")));
				processBalanceForDetail(temAccountInfo, temAccountInfo.getAcct_type(), rs.getString("real_bal"));
				temAccountInfo.setPassbook_no(AppUtils.correctStringValue(rs.getString("passbook_no")));
				temAccountInfo.setInterest_rate(CoreBankUtils.correctBankNumeric(AppUtils.correctStringValue(rs.getString("interest_rate"))));
				temAccountInfo.setAccured_interest(CoreBankUtils.correctBankNumeric(AppUtils.correctStringValue(rs.getString("accured_interest"))));
			}
		} catch (SQLException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAccountInfoByAccountNo()\n").append("Error message:").append(e.toString() + "\n"));
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				if (connToSIBS != null) {
					try {
						connToSIBS.close();
					} catch (SQLException excep) {
						logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAccountInfoByAccountNo()\n").append("Error message:").append(excep.toString() + "\n"));
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getAccountInfoByAccountNo()\n").append("Error message:").append(ex.toString() + "\n"));
			}
		}
		return temAccountInfo;
	}

	public ArrayList<AccountInfo> getAccountInfoByCif(String cif_no,  String online) {
		Connection connToSIBS = null;
		PreparedStatement pst1 = null;
		ArrayList<AccountInfo> arrAccountInfo = new ArrayList<AccountInfo>();
		try {

			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("CBS_HOST_USER");
			String strPassword = CachedParam.getSystemParam("CBS_HOST_PASSWORD");
			ACCT_INQ_COREBANK_LIB = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB");
			//TODO 26/02/201212
//			ACCT_INQ_COREBANK_LIB_OFFLINE = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB_OFFLINE");
			
			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);			
			
			StringBuffer strSQL = new StringBuffer();
			strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
			strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
			strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
			strSQL.append( "  '' passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
			strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
			strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
			strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
			strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal, ");
			strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal + sub.odlimt - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
			strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE sub.cifno = '" + cif_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno ");
			strSQL.append( "  AND a.status <> '2') memo_bal FROM " + ACCT_INQ_COREBANK_LIB + ".ddmast a," + ACCT_INQ_COREBANK_LIB + ".cfacct b ");
			strSQL.append( "  WHERE b.cfcifn = '" + cif_no + "' AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2' AND a.sccode <> 'CA12OPI') mst ");
			strSQL.append( "  UNION ALL ");
			strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,");
			strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date,");
			strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type,");
			strSQL.append( "  '' passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no");
			strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type,");
			strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status,");
			strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due, TRIM (a.odlimt) overdraft_limit,");
			strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal,");
			strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal + sub.odlimt - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
			strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE sub.cifno = '" + cif_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno AND a.status <> '2') memo_bal");
			strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
			strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2' AND TRIM(a.sccode) <> 'CA12OPI') mst");
			strSQL.append( " UNION ALL ");
			strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
			strSQL.append( "  mst.acct_status,mst.issued_date,mst.date_acct_opened,mst.maturity_date,'' establish_date,mst.full_release_date,");
			strSQL.append( "  COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,'' passbook_no,");
			strSQL.append( "  '0' interest_rate,mst.accured_interest,'' accured_interest_due,mst.loan_no");
			strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.brn) branch_no,TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,");
			strSQL.append( "  TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type,TRIM (a.curtyp) acct_ccy,TRIM (a.status) acct_status,");
			strSQL.append( "  TRIM (a.lnnum) loan_no,TRIM (a.datopn) date_acct_opened,TRIM (a.datopn) issued_date,TRIM (a.purcod) purpose_code,");
			strSQL.append( "  TRIM (a.fulldt) full_release_date,TRIM (a.matdt) maturity_date,TRIM (a.odind) overdue_indicator_description,");
			strSQL.append( "  TRIM (a.TYPE) product_code,TRIM (a.freq) principal_frequent,TRIM(prterm) term,TRIM(prcode) term_type,TRIM (a.ipfreq) interest_frequent,");
			strSQL.append( "  TRIM (a.orgamt) original_balance,TRIM (a.cbal) os_principal,TRIM (a.orgamt - a.amtrel) available_limit,");
			strSQL.append( "  TRIM (a.ysobal + a.billco) os_balance,TRIM(  a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc)billed_total_amount,");
			strSQL.append( "  TRIM (a.bilprn) billed_principal,TRIM (a.bilint) billed_interest,TRIM (a.billc) billed_late_charge,");
			strSQL.append( "  TRIM (a.othchg) other_charges,TRIM (a.drlimt) overdraft_limit,TRIM (a.hold) hold_amount,TRIM (a.ysacci + a.accint) accured_interest,");
			strSQL.append( "  TRIM (a.comacc) accrued_common_fee,TRIM (a.accmlc) accrued_late_charge,TRIM (a.orgamt - a.amtrel) available_limit,");
			strSQL.append( "  TRIM (a.hold) earmarked_amt,TRIM (a.pmtamt) payment_amount,TRIM (a.fnlpmt) final_payment_amt,");
			strSQL.append( "  (TRIM (a.orgamt) || ':' || TRIM (a.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.ysobal + a.billco)");
			strSQL.append( "  || ':' || TRIM(a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc) || ':' || TRIM (a.bilprn) || ':' || ");
			strSQL.append( "  TRIM (a.bilint) || ':' || TRIM (a.billc) || ':' || TRIM (a.othchg) || ':' || TRIM (a.drlimt) || ':' || ");
			strSQL.append( "  TRIM (a.hold) || ':' || TRIM (a.accint) || ':' || TRIM (a.comacc) || ':' ||  TRIM (a.accmlc) || ':' || ");
			strSQL.append( "  TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt)) all_bal,");
			strSQL.append( "  (SELECT (TRIM (a.orgamt) || ':' || TRIM (sub.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || ");
			strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM(sub.bilprn + sub.bilint + sub.billc + sub.bilesc + sub.biloc + sub.bilmc)");
			strSQL.append( "  || ':' || TRIM (sub.bilprn) || ':' || TRIM (sub.bilint) || ':' || TRIM (sub.billc) || ':' || TRIM (sub.othchg) || ':' ||");
			strSQL.append( "  TRIM (sub.drlimt) || ':' || TRIM (sub.hold) || ':' || TRIM (sub.accint) || ':' || TRIM (sub.comacc) || ':' || TRIM (a.accmlc) || ':' ||");
			strSQL.append( "  TRIM (a.orgamt - sub.amtrel) || ':' || TRIM (sub.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt))");
			strSQL.append( "  inner_bal FROM  " + ACCT_INQ_COREBANK_LIB + ".lnmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
			strSQL.append( "  FROM   " + ACCT_INQ_COREBANK_LIB + ".lnmast a, " + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
			strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
			strSQL.append( " UNION ALL ");
			strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,");
			strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,mst.maturity_date,'' establish_date,mst.full_release_date,");
			strSQL.append( "  COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,'' passbook_no,'0' interest_rate,");
			strSQL.append( "  mst.accured_interest,'' accured_interest_due,mst.loan_no");
			strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.brn) branch_no,TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.acctno) acct_no,");
			strSQL.append( "  TRIM (a.actype) acct_type,TRIM (a.curtyp) acct_ccy,TRIM (a.status) acct_status,TRIM (a.lnnum) loan_no,");
			strSQL.append( "  TRIM (a.datopn) date_acct_opened,TRIM (a.datopn) issued_date,TRIM (a.purcod) purpose_code,TRIM (a.fulldt) full_release_date,");
			strSQL.append( "  TRIM (a.matdt) maturity_date,TRIM (a.odind) overdue_indicator_description, TRIM (a.TYPE) product_code, TRIM (a.freq) principal_frequent,");
			strSQL.append( "  TRIM(prterm) term,TRIM(prcode) term_type,TRIM (a.ipfreq) interest_frequent,TRIM (a.orgamt) original_balance,TRIM (a.cbal) os_principal,TRIM (a.orgamt - a.amtrel) available_limit,");
			strSQL.append( "  TRIM (a.ysobal + a.billco) os_balance,TRIM (a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc) billed_total_amount,");
			strSQL.append( "  TRIM (a.bilprn) billed_principal,TRIM (a.bilint) billed_interest,TRIM (a.billc) billed_late_charge,TRIM (a.othchg) other_charges,");
			strSQL.append( "  TRIM (a.drlimt) overdraft_limit,TRIM (a.hold) hold_amount,TRIM (a.ysacci + a.accint) accured_interest,TRIM (a.comacc) accrued_common_fee,");
			strSQL.append( "  TRIM (a.accmlc) accrued_late_charge,TRIM (a.orgamt - a.amtrel) available_limit,TRIM (a.hold) earmarked_amt,TRIM (a.pmtamt) payment_amount,");
			strSQL.append( "  TRIM (a.fnlpmt) final_payment_amt,(TRIM (a.orgamt) || ':' || TRIM (a.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':'||");
			strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM (a.bilprn+ a.bilint+ a.billc+ a.bilesc+ a.biloc+ a.bilmc) || ':' ||");
			strSQL.append( "  TRIM (a.bilprn) || ':' || TRIM (a.bilint) || ':' || TRIM (a.billc) || ':' || TRIM (a.othchg) || ':' ||");
			strSQL.append( "  TRIM (a.drlimt) || ':' || TRIM (a.hold) || ':' || TRIM (a.accint) || ':' || TRIM (a.comacc) || ':' ||");
			strSQL.append( "  TRIM (a.accmlc) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.hold) || ':' || TRIM (a.pmtamt) || ':' ||");
			strSQL.append( "  TRIM (a.fnlpmt)) all_bal, (SELECT (TRIM (a.orgamt) || ':' || TRIM (sub.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || ");
			strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM(sub.bilprn + sub.bilint + sub.billc + sub.bilesc + sub.biloc + sub.bilmc)");
			strSQL.append( "  || ':' || TRIM (sub.bilprn) || ':' || TRIM (sub.bilint) || ':' || TRIM (sub.billc) || ':' || TRIM (sub.othchg) || ':' ||");
			strSQL.append( "  TRIM (sub.drlimt) || ':' || TRIM (sub.hold) || ':' || TRIM (sub.accint) || ':' || TRIM (sub.comacc) || ':' || TRIM (a.accmlc) || ':' ||");
			strSQL.append( "  TRIM (a.orgamt - sub.amtrel) || ':' || TRIM (sub.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt))");
			strSQL.append( "  inner_bal FROM  " + ACCT_INQ_COREBANK_LIB + ".lnmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
			strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB + ".lntnew a, " + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "' AND a.acctno = b.cfaccn");
			strSQL.append( "  AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
			strSQL.append( " UNION ALL ");
			strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,mst.p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
			strSQL.append( "  mst.acct_status,mst.issued_date,mst.issued_date date_acct_opened,mst.maturity_date,mst.establish_date,");
			strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,product_code,term,term_type,");
			strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,'' accured_interest_due,'' loan_no");
			strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.actype) acct_type,TRIM (a.acctno) acct_no,TRIM (a.brn) branch_no,");
			strSQL.append( "  TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.cdterm) term,TRIM (a.cdtcod) term_type,");
			strSQL.append( "  TRIM (a.curtyp) acct_ccy,TRIM (a.orgbal) ori_balance,TRIM (a.cbal) principal_balance,TRIM (a.accint) accured_interest,");
			strSQL.append( "  TRIM (a.penamt) penalty_amt,TRIM (a.hold) hold_amt,TRIM(a.orgbal || ':' || a.cbal || ':' || a.accint");
			strSQL.append( "  || ':' || a.penamt || ':' || a.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) all_bal,TRIM(a.cdrsrn) passbook_no,");
			strSQL.append( "  (a.cbal + a.accint - a.penamt - a.hold - a.wdrwh) current_cash_value, TRIM (a.issdt6) issued_date,");
			strSQL.append( "  TRIM (a.matdt6) maturity_date,TRIM (a.statd6) establish_date,TRIM (a.status) acct_status,TRIM (a.rate) interest_rate,");
			strSQL.append( "  TRIM (a.wdrwh),TRIM (a.cdnum) p_acct_no,TRIM (a.rnwctr),TRIM (a.renew),TRIM (a.dactn),TRIM (a.TYPE) product_code,");
			strSQL.append( "  (SELECT TRIM(a.orgbal || ':' || sub.cbal || ':' || sub.accint || ':' || sub.penamt || ':' || sub.hold || ':' || ");
			strSQL.append( "  (TRIM(cbal + accint - penamt - hold - wdrwh)))inner_bal FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub ");
			strSQL.append( "  WHERE  a.acctno = sub.acctno AND a.status <> '2') memo_bal FROM " + ACCT_INQ_COREBANK_LIB + ".cdmast a," + ACCT_INQ_COREBANK_LIB + ".cfacct b");
			strSQL.append( "  WHERE b.cfcifn = '" + cif_no + "' AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
			strSQL.append( " UNION ALL ");
			strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,mst.p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
			strSQL.append( "  mst.acct_status,mst.issued_date,mst.issued_date date_acct_opened,mst.maturity_date,mst.establish_date,");
			strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,");
			strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,'' accured_interest_due,'' loan_no");
			strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.actype) acct_type,TRIM (a.acctno) acct_no,TRIM (a.brn) branch_no,");
			strSQL.append( "  TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.cdterm) term,TRIM (a.cdtcod) term_type,");
			strSQL.append( "  TRIM (a.curtyp) acct_ccy,TRIM (a.orgbal) ori_balance,TRIM (a.cbal) principal_balance,TRIM (a.accint) accured_interest,");
			strSQL.append( "  TRIM (a.penamt) penalty_amt,TRIM (a.hold) hold_amt,TRIM (a.orgbal || ':' || a.cbal || ':' || a.accint || ':' || a.penamt || ':' || a.hold || ':'");
			strSQL.append( "  || (TRIM (cbal + accint - penamt - hold - wdrwh))) all_bal,TRIM(a.cdrsrn) passbook_no, (a.cbal + a.accint - a.penamt - a.hold - a.wdrwh) current_cash_value,");
			strSQL.append( "  TRIM (a.issdt6) issued_date,TRIM (a.matdt6) maturity_date,TRIM (a.statd6) establish_date,TRIM (a.status) acct_status,");
			strSQL.append( "  TRIM (a.rate) interest_rate,TRIM (a.wdrwh),TRIM (a.cdnum) p_acct_no,TRIM (a.rnwctr),TRIM (a.renew),TRIM (a.dactn),");
			strSQL.append( "  TRIM (a.TYPE) product_code,(SELECT (a.orgbal || ':' || sub.cbal || ':' || sub.accint || ':' || sub.penamt || ':'");
			strSQL.append( "  || sub.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) inner_bal");
			strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
			strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
			strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
			
			//TODO 247
//			if("true".equals(online)){
//				strSQL = SQLUtils.buildSQLGetAccountInfoByCifOnline(strSQL, ACCT_INQ_COREBANK_LIB, cif_no);
//			}else{
//				strSQL = SQLUtils.buildSQLGetAccountInfoByCifOffline(strSQL, ACCT_INQ_COREBANK_LIB,ACCT_INQ_COREBANK_LIB_OFFLINE, cif_no);
//			}
			
			pst1 = connToSIBS.prepareStatement(strSQL.toString());
			String sql = strSQL.toString();
			pst1.setQueryTimeout(JDBC_QUERY_TIMEOUT);
			ResultSet rs = pst1.executeQuery();
			
			while (rs != null && rs.next()) {
				AccountInfo temAccountInfo = new AccountInfo();
				temAccountInfo.setBank_no(AppUtils.correctStringValue(rs.getString("bank_no")));
				temAccountInfo.setBranch_no(AppUtils.correctStringValue(rs.getString("branch_no")));
				temAccountInfo.setAccount_no(AppUtils.correctStringValue(rs.getString("acct_no")));
				temAccountInfo.setP_acct_no(AppUtils.correctStringValue(rs.getString("p_acct_no")));
				temAccountInfo.setAcct_type(AppUtils.correctStringValue(rs.getString("acct_type")));
				temAccountInfo.setAcct_name(AppUtils.correctStringValue(rs.getString("acct_name")));
				temAccountInfo.setAcct_ccy(AppUtils.correctStringValue(rs.getString("acct_ccy")));
				temAccountInfo.setCif_no(AppUtils.correctStringValue(rs.getString("cif_no")));
				temAccountInfo.setAcct_status(AppUtils.correctStringValue(rs.getString("acct_status")));
				temAccountInfo.setIssued_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("issued_date"))));
				temAccountInfo.setDate_acct_opened(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("date_acct_opened"))));
				temAccountInfo.setMaturity_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("maturity_date"))));
				temAccountInfo.setEstablish_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("establish_date"))));
				temAccountInfo.setFull_release_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("full_release_date"))));
				temAccountInfo.setProduct_code(AppUtils.correctStringValue(rs.getString("product_code")));
				temAccountInfo.setTerm(AppUtils.correctStringValue(rs.getString("term")));
				temAccountInfo.setTerm_code(AppUtils.correctStringValue(rs.getString("term_type")).toLowerCase());
				temAccountInfo.setAccured_interest_due(AppUtils.correctStringValue(rs.getString("accured_interest_due")));
				processBalance(temAccountInfo, temAccountInfo.getAcct_type(), rs.getString("real_bal"));
				temAccountInfo.setPassbook_no(AppUtils.correctStringValue(rs.getString("passbook_no")));
				temAccountInfo.setInterest_rate(CoreBankUtils.correctBankNumeric(AppUtils.correctStringValue(rs.getString("interest_rate"))));
				temAccountInfo.setAccured_interest(CoreBankUtils.correctBankNumeric(AppUtils.correctStringValue(rs.getString("accured_interest"))));
				arrAccountInfo.add(temAccountInfo);
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
		return arrAccountInfo;
	}

	public ArrayList<AccountInfo> getAccountInfoByListOfCif(String[] lstCifNo, int numOfCif) {
		Connection connToSIBS = null;
		PreparedStatement pst1 = null;
		ArrayList<AccountInfo> arrAccountInfo = new ArrayList<AccountInfo>();
		String cif_no = "";
		try {
			String strHostName = CachedParam.getSystemParam("CBS_HOST_IP");
			String strUserName = CachedParam.getSystemParam("CBS_HOST_USER");
			String strPassword = CachedParam.getSystemParam("CBS_HOST_PASSWORD");
			ACCT_INQ_COREBANK_LIB = CachedParam.getSystemParam("ACCT_INQ_COREBANK_LIB");
			connToSIBS = getDb2CoreJDBC(strHostName, strUserName, strPassword);			
			String sub_cifno = "";
			String b_cfcifn = "";
			for (int i = 0 ; i < numOfCif; i++) {
				cif_no = lstCifNo[i];
				StringBuffer strSQL = new StringBuffer();
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
				strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
				strSQL.append( "  '' passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
				strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
				strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
				strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal, ");
				strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal + sub.odlimt - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
				strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE sub.cifno = '" + cif_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno ");
				strSQL.append( "  AND a.status <> '2') memo_bal FROM " + ACCT_INQ_COREBANK_LIB + ".ddmast a," + ACCT_INQ_COREBANK_LIB + ".cfacct b ");
				strSQL.append( "  WHERE b.cfcifn = '" + cif_no + "' AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2' AND a.sccode <> 'CA12OPI') mst ");
				strSQL.append( "  UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,");
				strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date,");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type,");
				strSQL.append( "  '' passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type,");
				strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status,");
				strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due, TRIM (a.odlimt) overdraft_limit,");
				strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal,");
				strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal + sub.odlimt - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE sub.cifno = '" + cif_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
				strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2' AND TRIM(a.sccode) <> 'CA12OPI') mst");
				strSQL.append( " UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
				strSQL.append( "  mst.acct_status,mst.issued_date,mst.date_acct_opened,mst.maturity_date,'' establish_date,mst.full_release_date,");
				strSQL.append( "  COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,'' passbook_no,");
				strSQL.append( "  '0' interest_rate,mst.accured_interest,'' accured_interest_due,mst.loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.brn) branch_no,TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,");
				strSQL.append( "  TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type,TRIM (a.curtyp) acct_ccy,TRIM (a.status) acct_status,");
				strSQL.append( "  TRIM (a.lnnum) loan_no,TRIM (a.datopn) date_acct_opened,TRIM (a.datopn) issued_date,TRIM (a.purcod) purpose_code,");
				strSQL.append( "  TRIM (a.fulldt) full_release_date,TRIM (a.matdt) maturity_date,TRIM (a.odind) overdue_indicator_description,");
				strSQL.append( "  TRIM (a.TYPE) product_code,TRIM (a.freq) principal_frequent,TRIM(prterm) term,TRIM(prcode) term_type,TRIM (a.ipfreq) interest_frequent,");
				strSQL.append( "  TRIM (a.orgamt) original_balance,TRIM (a.cbal) os_principal,TRIM (a.orgamt - a.amtrel) available_limit,");
				strSQL.append( "  TRIM (a.ysobal + a.billco) os_balance,TRIM(  a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc)billed_total_amount,");
				strSQL.append( "  TRIM (a.bilprn) billed_principal,TRIM (a.bilint) billed_interest,TRIM (a.billc) billed_late_charge,");
				strSQL.append( "  TRIM (a.othchg) other_charges,TRIM (a.drlimt) overdraft_limit,TRIM (a.hold) hold_amount,TRIM (a.ysacci + a.accint) accured_interest,");
				strSQL.append( "  TRIM (a.comacc) accrued_common_fee,TRIM (a.accmlc) accrued_late_charge,TRIM (a.orgamt - a.amtrel) available_limit,");
				strSQL.append( "  TRIM (a.hold) earmarked_amt,TRIM (a.pmtamt) payment_amount,TRIM (a.fnlpmt) final_payment_amt,");
				strSQL.append( "  (TRIM (a.orgamt) || ':' || TRIM (a.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.ysobal + a.billco)");
				strSQL.append( "  || ':' || TRIM(a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc) || ':' || TRIM (a.bilprn) || ':' || ");
				strSQL.append( "  TRIM (a.bilint) || ':' || TRIM (a.billc) || ':' || TRIM (a.othchg) || ':' || TRIM (a.drlimt) || ':' || ");
				strSQL.append( "  TRIM (a.hold) || ':' || TRIM (a.accint) || ':' || TRIM (a.comacc) || ':' ||  TRIM (a.accmlc) || ':' || ");
				strSQL.append( "  TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt)) all_bal,");
				strSQL.append( "  (SELECT (TRIM (a.orgamt) || ':' || TRIM (sub.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || ");
				strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM(sub.bilprn + sub.bilint + sub.billc + sub.bilesc + sub.biloc + sub.bilmc)");
				strSQL.append( "  || ':' || TRIM (sub.bilprn) || ':' || TRIM (sub.bilint) || ':' || TRIM (sub.billc) || ':' || TRIM (sub.othchg) || ':' ||");
				strSQL.append( "  TRIM (sub.drlimt) || ':' || TRIM (sub.hold) || ':' || TRIM (sub.accint) || ':' || TRIM (sub.comacc) || ':' || TRIM (a.accmlc) || ':' ||");
				strSQL.append( "  TRIM (a.orgamt - sub.amtrel) || ':' || TRIM (sub.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt))");
				strSQL.append( "  inner_bal FROM  " + ACCT_INQ_COREBANK_LIB + ".lnmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM   " + ACCT_INQ_COREBANK_LIB + ".lnmast a, " + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
				strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
				strSQL.append( " UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,");
				strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,mst.maturity_date,'' establish_date,mst.full_release_date,");
				strSQL.append( "  COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,'' passbook_no,'0' interest_rate,");
				strSQL.append( "  mst.accured_interest,'' accured_interest_due,mst.loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.brn) branch_no,TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.acctno) acct_no,");
				strSQL.append( "  TRIM (a.actype) acct_type,TRIM (a.curtyp) acct_ccy,TRIM (a.status) acct_status,TRIM (a.lnnum) loan_no,");
				strSQL.append( "  TRIM (a.datopn) date_acct_opened,TRIM (a.datopn) issued_date,TRIM (a.purcod) purpose_code,TRIM (a.fulldt) full_release_date,");
				strSQL.append( "  TRIM (a.matdt) maturity_date,TRIM (a.odind) overdue_indicator_description, TRIM (a.TYPE) product_code, TRIM (a.freq) principal_frequent,");
				strSQL.append( "  TRIM(prterm) term,TRIM(prcode) term_type,TRIM (a.ipfreq) interest_frequent,TRIM (a.orgamt) original_balance,TRIM (a.cbal) os_principal,TRIM (a.orgamt - a.amtrel) available_limit,");
				strSQL.append( "  TRIM (a.ysobal + a.billco) os_balance,TRIM (a.bilprn + a.bilint + a.billc + a.bilesc + a.biloc + a.bilmc) billed_total_amount,");
				strSQL.append( "  TRIM (a.bilprn) billed_principal,TRIM (a.bilint) billed_interest,TRIM (a.billc) billed_late_charge,TRIM (a.othchg) other_charges,");
				strSQL.append( "  TRIM (a.drlimt) overdraft_limit,TRIM (a.hold) hold_amount,TRIM (a.ysacci + a.accint) accured_interest,TRIM (a.comacc) accrued_common_fee,");
				strSQL.append( "  TRIM (a.accmlc) accrued_late_charge,TRIM (a.orgamt - a.amtrel) available_limit,TRIM (a.hold) earmarked_amt,TRIM (a.pmtamt) payment_amount,");
				strSQL.append( "  TRIM (a.fnlpmt) final_payment_amt,(TRIM (a.orgamt) || ':' || TRIM (a.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':'||");
				strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM (a.bilprn+ a.bilint+ a.billc+ a.bilesc+ a.biloc+ a.bilmc) || ':' ||");
				strSQL.append( "  TRIM (a.bilprn) || ':' || TRIM (a.bilint) || ':' || TRIM (a.billc) || ':' || TRIM (a.othchg) || ':' ||");
				strSQL.append( "  TRIM (a.drlimt) || ':' || TRIM (a.hold) || ':' || TRIM (a.accint) || ':' || TRIM (a.comacc) || ':' ||");
				strSQL.append( "  TRIM (a.accmlc) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || TRIM (a.hold) || ':' || TRIM (a.pmtamt) || ':' ||");
				strSQL.append( "  TRIM (a.fnlpmt)) all_bal, (SELECT (TRIM (a.orgamt) || ':' || TRIM (sub.cbal) || ':' || TRIM (a.orgamt - a.amtrel) || ':' || ");
				strSQL.append( "  TRIM (a.ysobal + a.billco) || ':' || TRIM(sub.bilprn + sub.bilint + sub.billc + sub.bilesc + sub.biloc + sub.bilmc)");
				strSQL.append( "  || ':' || TRIM (sub.bilprn) || ':' || TRIM (sub.bilint) || ':' || TRIM (sub.billc) || ':' || TRIM (sub.othchg) || ':' ||");
				strSQL.append( "  TRIM (sub.drlimt) || ':' || TRIM (sub.hold) || ':' || TRIM (sub.accint) || ':' || TRIM (sub.comacc) || ':' || TRIM (a.accmlc) || ':' ||");
				strSQL.append( "  TRIM (a.orgamt - sub.amtrel) || ':' || TRIM (sub.hold) || ':' || TRIM (a.pmtamt) || ':' || TRIM (a.fnlpmt))");
				strSQL.append( "  inner_bal FROM  " + ACCT_INQ_COREBANK_LIB + ".lnmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB + ".lntnew a, " + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "' AND a.acctno = b.cfaccn");
				strSQL.append( "  AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
				strSQL.append( " UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,mst.p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
				strSQL.append( "  mst.acct_status,mst.issued_date,mst.issued_date date_acct_opened,mst.maturity_date,mst.establish_date,");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,product_code,term,term_type,");
				strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,'' accured_interest_due,'' loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.actype) acct_type,TRIM (a.acctno) acct_no,TRIM (a.brn) branch_no,");
				strSQL.append( "  TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.cdterm) term,TRIM (a.cdtcod) term_type,");
				strSQL.append( "  TRIM (a.curtyp) acct_ccy,TRIM (a.orgbal) ori_balance,TRIM (a.cbal) principal_balance,TRIM (a.accint) accured_interest,");
				strSQL.append( "  TRIM (a.penamt) penalty_amt,TRIM (a.hold) hold_amt,TRIM(a.orgbal || ':' || a.cbal || ':' || a.accint");
				strSQL.append( "  || ':' || a.penamt || ':' || a.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) all_bal,TRIM(a.cdrsrn) passbook_no,");
				strSQL.append( "  (a.cbal + a.accint - a.penamt - a.hold - a.wdrwh) current_cash_value, TRIM (a.issdt6) issued_date,");
				strSQL.append( "  TRIM (a.matdt6) maturity_date,TRIM (a.statd6) establish_date,TRIM (a.status) acct_status,TRIM (a.rate) interest_rate,");
				strSQL.append( "  TRIM (a.wdrwh),TRIM (a.cdnum) p_acct_no,TRIM (a.rnwctr),TRIM (a.renew),TRIM (a.dactn),TRIM (a.TYPE) product_code,");
				strSQL.append( "  (SELECT TRIM(a.orgbal || ':' || sub.cbal || ':' || sub.accint || ':' || sub.penamt || ':' || sub.hold || ':' || ");
				strSQL.append( "  (TRIM(cbal + accint - penamt - hold - wdrwh)))inner_bal FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub ");
				strSQL.append( "  WHERE  a.acctno = sub.acctno AND a.status <> '2') memo_bal FROM " + ACCT_INQ_COREBANK_LIB + ".cdmast a," + ACCT_INQ_COREBANK_LIB + ".cfacct b");
				strSQL.append( "  WHERE b.cfcifn = '" + cif_no + "' AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst ");
				strSQL.append( " UNION ALL ");
				strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,mst.p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy,mst.cif_no,");
				strSQL.append( "  mst.acct_status,mst.issued_date,mst.issued_date date_acct_opened,mst.maturity_date,mst.establish_date,");
				strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,mst.product_code,mst.term,mst.term_type,");
				strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,'' accured_interest_due,'' loan_no");
				strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.actype) acct_type,TRIM (a.acctno) acct_no,TRIM (a.brn) branch_no,");
				strSQL.append( "  TRIM (a.cifno) cif_no,TRIM (a.acname) acct_name,TRIM (a.cdterm) term,TRIM (a.cdtcod) term_type,");
				strSQL.append( "  TRIM (a.curtyp) acct_ccy,TRIM (a.orgbal) ori_balance,TRIM (a.cbal) principal_balance,TRIM (a.accint) accured_interest,");
				strSQL.append( "  TRIM (a.penamt) penalty_amt,TRIM (a.hold) hold_amt,TRIM (a.orgbal || ':' || a.cbal || ':' || a.accint || ':' || a.penamt || ':' || a.hold || ':'");
				strSQL.append( "  || (TRIM (cbal + accint - penamt - hold - wdrwh))) all_bal,TRIM(a.cdrsrn) passbook_no, (a.cbal + a.accint - a.penamt - a.hold - a.wdrwh) current_cash_value,");
				strSQL.append( "  TRIM (a.issdt6) issued_date,TRIM (a.matdt6) maturity_date,TRIM (a.statd6) establish_date,TRIM (a.status) acct_status,");
				strSQL.append( "  TRIM (a.rate) interest_rate,TRIM (a.wdrwh),TRIM (a.cdnum) p_acct_no,TRIM (a.rnwctr),TRIM (a.renew),TRIM (a.dactn),");
				strSQL.append( "  TRIM (a.TYPE) product_code,(SELECT (a.orgbal || ':' || sub.cbal || ':' || sub.accint || ':' || sub.penamt || ':'");
				strSQL.append( "  || sub.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) inner_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
				strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
				strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
				
				pst1 = connToSIBS.prepareStatement(strSQL.toString());
				pst1.setQueryTimeout(JDBC_QUERY_TIMEOUT);
				ResultSet rs = pst1.executeQuery();
				while (rs != null && rs.next()) {
					AccountInfo temAccountInfo = new AccountInfo();
					temAccountInfo.setBank_no(AppUtils.correctStringValue(rs.getString("bank_no")));
					temAccountInfo.setBranch_no(AppUtils.correctStringValue(rs.getString("branch_no")));
					temAccountInfo.setAccount_no(AppUtils.correctStringValue(rs.getString("acct_no")));
					temAccountInfo.setP_acct_no(AppUtils.correctStringValue(rs.getString("p_acct_no")));
					temAccountInfo.setAcct_type(AppUtils.correctStringValue(rs.getString("acct_type")));
					temAccountInfo.setAcct_name(AppUtils.correctStringValue(rs.getString("acct_name")));
					temAccountInfo.setAcct_ccy(AppUtils.correctStringValue(rs.getString("acct_ccy")));
					temAccountInfo.setCif_no(AppUtils.correctStringValue(rs.getString("cif_no")));
					temAccountInfo.setAcct_status(AppUtils.correctStringValue(rs.getString("acct_status")));
					temAccountInfo.setIssued_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("issued_date"))));
					temAccountInfo.setDate_acct_opened(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("date_acct_opened"))));
					temAccountInfo.setMaturity_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("maturity_date"))));
					temAccountInfo.setEstablish_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("establish_date"))));
					temAccountInfo.setFull_release_date(CoreBankUtils.convertCB2EBDateYYYYMMdd(AppUtils.correctStringValue(rs.getString("full_release_date"))));
					temAccountInfo.setProduct_code(AppUtils.correctStringValue(rs.getString("product_code")));
					temAccountInfo.setTerm(AppUtils.correctStringValue(rs.getString("term")));
					temAccountInfo.setTerm_code(AppUtils.correctStringValue(rs.getString("term_type")).toLowerCase());
					temAccountInfo.setAccured_interest_due(AppUtils.correctStringValue(rs.getString("accured_interest_due")));
					processBalance(temAccountInfo, temAccountInfo.getAcct_type(), rs.getString("real_bal"));
					temAccountInfo.setPassbook_no(AppUtils.correctStringValue(rs.getString("passbook_no")));
					temAccountInfo.setInterest_rate(CoreBankUtils.correctBankNumeric(AppUtils.correctStringValue(rs.getString("interest_rate"))));
					temAccountInfo.setAccured_interest(CoreBankUtils.correctBankNumeric(AppUtils.correctStringValue(rs.getString("accured_interest"))));
					arrAccountInfo.add(temAccountInfo);
				}
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
		return arrAccountInfo;
	}
	
	//Do version Db2 cua AS400 rat cu nen khong xu ly truc tiep duoc trong sql ma dung java de xu ly
	private void processBalance(AccountInfo tempAccount, String account_type, String real_bal) {
		if (account_type.equals(Global.COREBBANK_CA_ACCOUNT_TYPE) || account_type.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setHold_amt(arrAccount[0].trim());// hold
			tempAccount.setLedger_balance(arrAccount[1].trim());// ledger
			tempAccount.setAvailable_balance(arrAccount[2].trim());// avail
			tempAccount.setOverdraft_limit(arrAccount[3].trim());// overdraft_limit
			tempAccount.setInterest_rate(arrAccount[4].trim());// interest_rate
			tempAccount.setEarmarked_amt(arrAccount[0].trim());// earm
			// =hold
		} else if (account_type.equals(Global.COREBBANK_FD_RECEIPT_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setOri_balance(arrAccount[0].trim());// ori_balance
			tempAccount.setPrincipal_balance(arrAccount[1].trim());// principal_balance
			tempAccount.setAccured_interest(arrAccount[2].trim());// accured_interest
			tempAccount.setPenalty_amt(arrAccount[3].trim());// penalty_amt
			tempAccount.setHold_amt(arrAccount[4].trim());// hold_amt
			tempAccount.setEarmarked_amt(arrAccount[4].trim());// hold_amt
			tempAccount.setCurrent_cash_value(arrAccount[5].trim());// current_cash_value
		} else if (account_type.equals(Global.COREBBANK_FD_GROUP_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setPenalty_amt(arrAccount[4].trim());// penalty_amt
			tempAccount.setHold_amt(arrAccount[5].trim());// hold_amt
			tempAccount.setEarmarked_amt(arrAccount[5].trim());// hold_amt
		} else if (account_type.equals(Global.COREBBANK_LN_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setOri_balance(arrAccount[0].trim());// original_balance
			tempAccount.setOs_principal(arrAccount[1].trim());// os_principal
			tempAccount.setOs_balance(arrAccount[3].trim());// os_balance
			tempAccount.setBilled_total_amt(arrAccount[4].trim());// billed_total_amount
			tempAccount.setBilled_principal(arrAccount[5].trim());// billed_principal
			tempAccount.setBilled_interest(arrAccount[6].trim());// billed_interest
			tempAccount.setBilled_late_charge(arrAccount[7].trim());// billed_late_charge
			tempAccount.setBilled_other_charge(arrAccount[8].trim());// other_charges
			tempAccount.setOverdraft_limit(arrAccount[9].trim());// overdraft_limit
			tempAccount.setHold_amt(arrAccount[10].trim());// hold_amount
			tempAccount.setAccured_interest(arrAccount[11].trim());// accured_interest
			tempAccount.setEarmarked_amt(arrAccount[15].trim());// earmarked_amt
			tempAccount.setPayment_amt(arrAccount[16].trim());// payment_amount
			tempAccount.setFinal_payment_amt(arrAccount[17].trim());// final_payment_amt
		} else {
		}
	}
	//Do version Db2 cua AS400 rat cu nen khong xu ly truc tiep duoc trong sql ma dung java de xu ly
	private void processBalanceForDetail(AccountInfo tempAccount, String account_type, String real_bal) {
		if (account_type.equals(Global.COREBBANK_CA_ACCOUNT_TYPE) || account_type.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setHold_amt(arrAccount[0].trim());// hold
			tempAccount.setLedger_balance(arrAccount[1].trim());// ledger
			tempAccount.setAvailable_balance(arrAccount[2].trim());// avail
			tempAccount.setOverdraft_limit(arrAccount[3].trim());// overdraft_limit
			tempAccount.setInterest_rate(arrAccount[4].trim());// interest_rate
			tempAccount.setEarmarked_amt(arrAccount[0].trim());// earm
			// =hold
		} else if (account_type.equals(Global.COREBBANK_FD_RECEIPT_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setOri_balance(arrAccount[0].trim());// ori_balance
			tempAccount.setPrincipal_balance(arrAccount[1].trim());// principal_balance
			tempAccount.setAccured_interest(arrAccount[2].trim());// accured_interest
			tempAccount.setPenalty_amt(arrAccount[3].trim());// penalty_amt
			tempAccount.setHold_amt(arrAccount[4].trim());// hold_amt
			tempAccount.setEarmarked_amt(arrAccount[4].trim());// hold_amt
			tempAccount.setCurrent_cash_value(arrAccount[5].trim());// current_cash_value
		} else if (account_type.equals(Global.COREBBANK_FD_GROUP_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setPenalty_amt(arrAccount[4].trim());// penalty_amt
			tempAccount.setHold_amt(arrAccount[5].trim());// hold_amt
			tempAccount.setEarmarked_amt(arrAccount[5].trim());// hold_amt
		} else if (account_type.equals(Global.COREBBANK_LN_ACCOUNT_TYPE)) {
			String[] arrAccount = real_bal.split(":");
			tempAccount.setOri_balance(arrAccount[0].trim());// original_balance
			tempAccount.setOs_principal(arrAccount[1].trim());// os_principal
			tempAccount.setOs_balance(arrAccount[3].trim());// os_balance
			tempAccount.setBilled_total_amt(arrAccount[4].trim());// billed_total_amount
			tempAccount.setBilled_principal(arrAccount[5].trim());// billed_principal
			tempAccount.setBilled_interest(arrAccount[6].trim());// billed_interest
			tempAccount.setBilled_late_charge(arrAccount[7].trim());// billed_late_charge
			tempAccount.setBilled_other_charge(arrAccount[8].trim());// other_charges
			tempAccount.setOverdraft_limit(arrAccount[9].trim());// overdraft_limit
			tempAccount.setHold_amt(arrAccount[10].trim());// hold_amount
			tempAccount.setAccured_interest(arrAccount[11].trim());// accured_interest
			tempAccount.setEarmarked_amt(arrAccount[15].trim());// earmarked_amt
			tempAccount.setPayment_amt(arrAccount[16].trim());// payment_amount
			tempAccount.setFinal_payment_amt(arrAccount[17].trim());// final_payment_amt
		} else {
		}
	}
}
