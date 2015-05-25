package seatechit.msbgateway.dbaccess.entity;

import seatechit.msbgateway.utils.CoreBankUtils;

public class AccountInfo {
	private String acct_type = "";
	private String account_no = "";
	private String acct_ccy = "";
	private String bank_no = "";
	private String cif_no = "";
	private String sub_acct_type = "";
	private String acct_name = "";
	private String p_acct_no = "";
	private String sub_branch_no = "";
	private String cb_sign = "";
	private String cx_sign = "";
	private String establish_date = "";
	private String available_date = "";
	private String branch_no = "";
	private String cert_type = "";
	private String cert_code = "";
	private String remark = "";
	private String current_balance = "0";
	private String freezed_balance = "0";
	private String available_balance = "0";
	private String create_by = "";
	private String create_time = "";
	private String update_by = "";
	private String update_time = "";
	private String ledger_balance = "0";
	private String hold_amt = "0";
	private String overdraft_limit = "0";
	private String interest_rate = "0";
	private String date_acct_opened = "";
	private String passbook_no = "";
	private String accured_interest = "0";
	private String term = "";
	private String ori_balance = "0";
	private String principal_balance = "0";
	private String penalty_amt = "0";
	private String current_cash_value = "0";
	private String earmarked_amt = "0";
	private String issued_date = "";
	private String maturity_date = "";
	private String times_nenewed_count = "";
	private String purpose_code = "";
	private String loan_ori_amt = "0";
	private String os_balance = "0";
	private String loan_term = "";
	private String principal_frequent = "";
	private String interest_frequent = "";
	private String full_release_date = "";
	private String overdue_indicator_desc = "";
	private String loan_term_code = "";
	private String acct_status = "";
	private String product_code = "";
	private String billed_late_charge = "0";
	private String billed_principal = "0";
	private String billed_interest = "0";
	private String payment_amt = "0";
	private String final_payment_amt = "0";
	private String os_principal = "0";
	private String accured_interest_due = "";
	private String billed_other_charge = "0";
	private String billed_misc_charge = "0";
	private String billed_total_amt = "0";
	private String term_code = "";

	public AccountInfo() {
	}

	public String getCif_no() {
		return cif_no;
	}

	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}

	public String getSub_acct_type() {
		return sub_acct_type;
	}

	public void setSub_acct_type(String sub_acct_type) {
		this.sub_acct_type = sub_acct_type;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public String getP_acct_no() {
		return p_acct_no;
	}

	public void setP_acct_no(String p_acct_no) {
		this.p_acct_no = p_acct_no;
	}

	public String getSub_branch_no() {
		return sub_branch_no;
	}

	public void setSub_branch_no(String sub_branch_no) {
		this.sub_branch_no = sub_branch_no;
	}

	public String getCb_sign() {
		return cb_sign;
	}

	public void setCb_sign(String cb_sign) {
		this.cb_sign = cb_sign;
	}

	public String getCx_sign() {
		return cx_sign;
	}

	public void setCx_sign(String cx_sign) {
		this.cx_sign = cx_sign;
	}

	public String getEstablish_date() {
		return establish_date;
	}

	public void setEstablish_date(String establish_date) {
		this.establish_date = establish_date;
	}

	public String getAvailable_date() {
		return available_date;
	}

	public void setAvailable_date(String available_date) {
		this.available_date = available_date;
	}

	public String getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_code() {
		return cert_code;
	}

	public void setCert_code(String cert_code) {
		this.cert_code = cert_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(String current_balance) {
		this.current_balance = CoreBankUtils.correctBankNumeric(current_balance);
	}

	public String getFreezed_balance() {
		return freezed_balance;
	}

	public void setFreezed_balance(String freezed_balance) {
		this.freezed_balance = CoreBankUtils.correctBankNumeric(freezed_balance);
	}

	public String getAvailable_balance() {
		return available_balance;
	}

	public void setAvailable_balance(String available_balance) {
		this.available_balance = CoreBankUtils.correctBankNumeric(available_balance);
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getLedger_balance() {
		return ledger_balance;
	}

	public void setLedger_balance(String ledger_balance) {
		this.ledger_balance = CoreBankUtils.correctBankNumeric(ledger_balance);
	}

	public String getHold_amt() {
		return hold_amt;
	}

	public void setHold_amt(String hold_amt) {
		this.hold_amt = CoreBankUtils.correctBankNumeric(hold_amt);
	}

	public String getOverdraft_limit() {
		return overdraft_limit;
	}

	public void setOverdraft_limit(String overdraft_limit) {
		this.overdraft_limit = CoreBankUtils.correctBankNumeric(overdraft_limit);
	}

	public String getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(String interest_rate) {
		this.interest_rate = CoreBankUtils.correctBankNumeric(interest_rate);
	}

	public String getDate_acct_opened() {
		return date_acct_opened;
	}

	public void setDate_acct_opened(String date_acct_opened) {
		this.date_acct_opened = date_acct_opened;
	}

	public String getPassbook_no() {
		return passbook_no;
	}

	public void setPassbook_no(String passbook_no) {
		this.passbook_no = passbook_no;
	}

	public String getAccured_interest() {
		return accured_interest;
	}

	public void setAccured_interest(String accured_interest) {
		this.accured_interest = CoreBankUtils.correctBankNumeric(accured_interest);
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getOri_balance() {
		return ori_balance;
	}

	public void setOri_balance(String ori_balance) {
		this.ori_balance = CoreBankUtils.correctBankNumeric(ori_balance);
	}

	public String getPrincipal_balance() {
		return principal_balance;
	}

	public void setPrincipal_balance(String principal_balance) {
		this.principal_balance = CoreBankUtils.correctBankNumeric(principal_balance);
	}

	public String getPenalty_amt() {
		return penalty_amt;
	}

	public void setPenalty_amt(String penalty_amt) {
		this.penalty_amt = CoreBankUtils.correctBankNumeric(penalty_amt);
	}

	public String getCurrent_cash_value() {
		return current_cash_value;
	}

	public void setCurrent_cash_value(String current_cash_value) {
		this.current_cash_value = CoreBankUtils.correctBankNumeric(current_cash_value);
	}

	public String getEarmarked_amt() {
		return earmarked_amt;
	}

	public void setEarmarked_amt(String earmarked_amt) {
		this.earmarked_amt = CoreBankUtils.correctBankNumeric(earmarked_amt);
	}

	public String getIssued_date() {
		return issued_date;
	}

	public void setIssued_date(String issued_date) {
		this.issued_date = issued_date;
	}

	public String getMaturity_date() {
		return maturity_date;
	}

	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}

	public String getTimes_nenewed_count() {
		return times_nenewed_count;
	}

	public void setTimes_nenewed_count(String times_nenewed_count) {
		this.times_nenewed_count = times_nenewed_count;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public String getLoan_ori_amt() {
		return loan_ori_amt;
	}

	public void setLoan_ori_amt(String loan_ori_amt) {
		this.loan_ori_amt = CoreBankUtils.correctBankNumeric(loan_ori_amt);
	}

	public String getOs_balance() {
		return os_balance;
	}

	public void setOs_balance(String os_balance) {
		this.os_balance = CoreBankUtils.correctBankNumeric(os_balance);
	}

	public String getLoan_term() {
		return loan_term;
	}

	public void setLoan_term(String loan_term) {
		this.loan_term = loan_term;
	}

	public String getPrincipal_frequent() {
		return principal_frequent;
	}

	public void setPrincipal_frequent(String principal_frequent) {
		this.principal_frequent = principal_frequent;
	}

	public String getInterest_frequent() {
		return interest_frequent;
	}

	public void setInterest_frequent(String interest_frequent) {
		this.interest_frequent = interest_frequent;
	}

	public String getFull_release_date() {
		return full_release_date;
	}

	public void setFull_release_date(String full_release_date) {
		this.full_release_date = full_release_date;
	}

	public String getOverdue_indicator_desc() {
		return overdue_indicator_desc;
	}

	public void setOverdue_indicator_desc(String overdue_indicator_desc) {
		this.overdue_indicator_desc = overdue_indicator_desc;
	}

	public String getLoan_term_code() {
		return loan_term_code;
	}

	public void setLoan_term_code(String loan_term_code) {
		this.loan_term_code = loan_term_code;
	}

	public String getAcct_status() {
		return acct_status;
	}

	public void setAcct_status(String acct_status) {
		this.acct_status = acct_status;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getBilled_late_charge() {
		return billed_late_charge;
	}

	public void setBilled_late_charge(String billed_late_charge) {
		this.billed_late_charge = CoreBankUtils.correctBankNumeric(billed_late_charge);
	}

	public String getBilled_principal() {
		return billed_principal;
	}

	public void setBilled_principal(String billed_principal) {
		this.billed_principal = CoreBankUtils.correctBankNumeric(billed_principal);
	}

	public String getBilled_interest() {
		return billed_interest;
	}

	public void setBilled_interest(String billed_interest) {
		this.billed_interest = CoreBankUtils.correctBankNumeric(billed_interest);
	}

	public String getPayment_amt() {
		return payment_amt;
	}

	public void setPayment_amt(String payment_amt) {
		this.payment_amt = CoreBankUtils.correctBankNumeric(payment_amt);
	}

	public String getFinal_payment_amt() {
		return final_payment_amt;
	}

	public void setFinal_payment_amt(String final_payment_amt) {
		this.final_payment_amt = CoreBankUtils.correctBankNumeric(final_payment_amt);
	}

	public String getOs_principal() {
		return os_principal;
	}

	public void setOs_principal(String os_principal) {
		this.os_principal = CoreBankUtils.correctBankNumeric(os_principal);
	}

	public String getAccured_interest_due() {
		return accured_interest_due;
	}

	public void setAccured_interest_due(String accured_interest_due) {
		this.accured_interest_due = CoreBankUtils.correctBankNumeric(accured_interest_due);
	}

	public String getBilled_other_charge() {
		return billed_other_charge;
	}

	public void setBilled_other_charge(String billed_other_charge) {
		this.billed_other_charge = CoreBankUtils.correctBankNumeric(billed_other_charge);
	}

	public String getBilled_misc_charge() {
		return billed_misc_charge;
	}

	public void setBilled_misc_charge(String billed_misc_charge) {
		this.billed_misc_charge = CoreBankUtils.correctBankNumeric(billed_misc_charge);
	}

	public String getBilled_total_amt() {
		return billed_total_amt;
	}

	public void setBilled_total_amt(String billed_total_amt) {
		this.billed_total_amt = CoreBankUtils.correctBankNumeric(billed_total_amt);
	}

	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}

	public String getAcct_type() {
		return acct_type;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}

	public String getAcct_ccy() {
		return acct_ccy;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setTerm_code(String term_code) {
		this.term_code = term_code;
	}

	public String getTerm_code() {
		return term_code;
	}

}
