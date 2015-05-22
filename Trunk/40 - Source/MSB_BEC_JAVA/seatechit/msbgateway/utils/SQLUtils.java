package seatechit.msbgateway.utils;

import java.io.Serializable;

/**
 * Contain sql query for online and offline
 * 
 * 
 */
public class SQLUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619422746572048765L;
		
	public static StringBuffer buildSQLGetAccountInfoByAccountNoOnline(StringBuffer strSQL, String ACCT_INQ_COREBANK_LIB, String acct_no){
		
		strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
		strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
		strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
		strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
		strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
		strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
		strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
		strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal,(SELECT TRIM(tmbmrser) FROM " + ACCT_INQ_COREBANK_LIB + ".tmpbmast WHERE tmbmacct = a.acctno) passbook_no, ");
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
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
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddmemo sub WHERE sub.acctno = '" + acct_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno AND a.status <> '2') memo_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".ddtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfaccn = '" + acct_no + "'");
		strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
		
		return strSQL;		
	}
	
	public static StringBuffer buildSQLGetAccountInfoByAccountNoOffline(StringBuffer strSQL, String ACCT_INQ_COREBANK_LIB,String ACCT_INQ_COREBANK_LIB_OFFLINE, String acct_no){
		
		strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
		strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
		strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
		strSQL.append( "  mst.passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
		strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
		strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
		strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
		strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal,(SELECT TRIM(tmbmrser) FROM " + ACCT_INQ_COREBANK_LIB + ".tmpbmast WHERE tmbmacct = a.acctno) passbook_no, ");
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
		strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB_OFFLINE + ".DDME24 sub WHERE  sub.acctno = '" + acct_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno ");
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
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB_OFFLINE + ".DDME24 sub WHERE sub.acctno = '" + acct_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno AND a.status <> '2') memo_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB_OFFLINE + ".DDTNW24 a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfaccn = '" + acct_no + "'");
		strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
		
		return strSQL;		
	}
	
	public static StringBuffer buildSQLGetAccountInfoByCifOnline(StringBuffer strSQL, String ACCT_INQ_COREBANK_LIB, String cif_no){
		
		strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
		strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
		strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
		strSQL.append( "  '' passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
		strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
		strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
		strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
		strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal, ");
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
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
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
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
		strSQL.append( "  (SELECT TRIM(a.orgbal || ':' || a.orgbal || ':' || sub.accint || ':' || sub.penamt || ':' || sub.hold || ':' || ");
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
		strSQL.append( "  TRIM (a.TYPE) product_code,(SELECT (a.orgbal || ':' || a.orgbal || ':' || sub.accint || ':' || sub.penamt || ':'");
		strSQL.append( "  || sub.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) inner_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
		strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
		
		return strSQL;		
	}
	
	public static StringBuffer buildSQLGetAccountInfoByCifOffline(StringBuffer strSQL, String ACCT_INQ_COREBANK_LIB, String ACCT_INQ_COREBANK_LIB_OFFLINE, String cif_no){
		
		strSQL.append( " SELECT mst.bank_no,mst.branch_no,mst.acct_no,'' p_acct_no,mst.acct_type,mst.acct_name,mst.acct_ccy, ");
		strSQL.append( "  mst.cif_no,mst.acct_status,mst.issued_date,mst.date_acct_opened,'' maturity_date,'' establish_date, ");
		strSQL.append( "  '' full_release_date,COALESCE (mst.memo_bal, mst.all_bal) real_bal,'' product_code,'' term,'' term_type, ");
		strSQL.append( "  '' passbook_no,mst.interest_rate,mst.accured_interest,mst.accured_interest_due,'' loan_no ");
		strSQL.append( "  FROM (SELECT '032' bank_no,TRIM (a.branch) branch_no,TRIM (a.acctno) acct_no,TRIM (a.actype) acct_type, ");
		strSQL.append( "  TRIM (a.acname) acct_name,TRIM (a.ddctyp) acct_ccy,TRIM (a.cifno) cif_no,TRIM (a.status) acct_status, ");
		strSQL.append( "  TRIM (a.datop6) date_acct_opened,TRIM (a.datop7) issued_date,TRIM (a.rate) interest_rate,TRIM(a.accrue) accured_interest,TRIM('') accured_interest_due,TRIM (a.odlimt) overdraft_limit, ");
		strSQL.append( "  TRIM (a.hold || ':' || a.cbal || ':' || (a.cbal + a.odlimt - a.hold) || ':' || a.odlimt || ':' || a.whhirt) all_bal, ");
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal ");
		strSQL.append( "  FROM  " + ACCT_INQ_COREBANK_LIB_OFFLINE + ".DDME24 sub WHERE sub.cifno = '" + cif_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno ");
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
		strSQL.append( "  (SELECT TRIM(sub.hold || ':' || sub.cbal || ':' || (sub.cbal - sub.hold) || ':' || sub.odlimt || ':' || a.whhirt) inner_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB_OFFLINE + ".DDME24 sub WHERE sub.cifno = '" + cif_no + "' AND a.acctno = sub.acctno AND a.cifno = sub.cifno AND a.status <> '2') memo_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB_OFFLINE + ".DDTNW24 a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
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
		strSQL.append( "  (SELECT TRIM(a.orgbal || ':' || a.orgbal || ':' || sub.accint || ':' || sub.penamt || ':' || sub.hold || ':' || ");
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
		strSQL.append( "  TRIM (a.TYPE) product_code,(SELECT (a.orgbal || ':' || a.orgbal || ':' || sub.accint || ':' || sub.penamt || ':'");
		strSQL.append( "  || sub.hold || ':' || (TRIM (cbal + accint - penamt - hold - wdrwh))) inner_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdmemo sub WHERE a.acctno = sub.acctno AND a.status <> '2') memo_bal");
		strSQL.append( "  FROM " + ACCT_INQ_COREBANK_LIB + ".cdtnew a," + ACCT_INQ_COREBANK_LIB + ".cfacct b WHERE b.cfcifn = '" + cif_no + "'");
		strSQL.append( "  AND a.acctno = b.cfaccn AND a.cifno = b.cfcifn AND a.status <> '2') mst");
		
		return strSQL;		
	}
}
