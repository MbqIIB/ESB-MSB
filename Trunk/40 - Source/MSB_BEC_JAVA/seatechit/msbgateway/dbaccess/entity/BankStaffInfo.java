package seatechit.msbgateway.dbaccess.entity;

public class BankStaffInfo {
	private String userid;
	private String branchcode;
	private int status;
	private int role;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public BankStaffInfo(String userid, String branchcode, int status, int role) {
		super();
		this.userid = userid;
		this.branchcode = branchcode;
		this.status = status;
		this.role = role;
	}

}
