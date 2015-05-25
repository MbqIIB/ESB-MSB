package vn.com.msb.cnn.accounts;

//Table : settings
public class AccountSetting {
	private long sequence;
	private String branch_code;
	private String group_code;
	private String currency_code;
	private long sequence_limit;
	private String param1 = "";
	private String param2 = "";

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public long getSequence_limit() {
		return sequence_limit;
	}

	public void setSequence_limit(long sequence_limit) {
		this.sequence_limit = sequence_limit;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

}
