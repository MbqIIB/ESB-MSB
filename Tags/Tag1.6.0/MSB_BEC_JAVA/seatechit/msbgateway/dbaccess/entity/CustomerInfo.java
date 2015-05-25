package seatechit.msbgateway.dbaccess.entity;

import seatechit.msbgateway.utils.CoreBankUtils;

public class CustomerInfo {
	private String cif_no;
	private String cif_acct_name;
	private String status;
	private String bank_no;
	private String country;
	private String gender;
	private String cert_code;
	private String cert_type;
	private String birth_date;
	private String birth_place;
	private String individual;
	private String telephone;
	private String addr;
	private String mobile;
	private String email;
	private String contact_person;
	private String branch_no;

	public CustomerInfo() {
	}

	public CustomerInfo(String cif_no, String cif_acct_name, String status, String bank_no, String country, String gender, String cert_code, String cert_type, String birth_date, String birth_place,
			String individual, String telephone, String addr, String mobile, String email, String contact_person, String branch_no) {
		super();
		this.cif_no = cif_no;
		this.cif_acct_name = cif_acct_name;
		this.status = status;
		this.bank_no = bank_no;
		this.country = country;
		this.gender = gender;
		this.cert_code = cert_code;
		this.cert_type = cert_type;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.individual = individual;
		this.telephone = telephone;
		this.addr = addr;
		this.mobile = mobile;
		this.email = email;
		this.contact_person = contact_person;
		this.branch_no = branch_no;
	}

	public String toString() {
		return "cif_no:" + this.cif_no + ",cif_acct_name:" + this.cif_acct_name + ",status:" + this.status + ",bank_no:" + this.bank_no + ",country:" + this.country + ",gender:" + this.gender
				+ ",cert_code:" + this.cert_code + ",cert_type:" + this.cert_type + ",birth_date:" + CoreBankUtils.convertCB2EBDateYYYYMMdd(this.birth_date) + ",birth_place:" + this.birth_place
				+ ",individual:" + this.individual + ",telephone:" + this.telephone + ",addr:" + this.addr + ",mobile:" + this.mobile + ",email:" + this.email + ",contact_person:"
				+ this.contact_person + ",branch_no:" + this.branch_no;
	}

	public String getCif_no() {
		return cif_no;
	}

	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}

	public String getCif_acct_name() {
		return cif_acct_name;
	}

	public void setCif_acct_name(String cif_acct_name) {
		this.cif_acct_name = cif_acct_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCert_code() {
		return cert_code;
	}

	public void setCert_code(String cert_code) {
		this.cert_code = cert_code;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getIndividual() {
		return individual;
	}

	public void setIndividual(String individual) {
		this.individual = individual;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}

}
