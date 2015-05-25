package vn.com.m1tech.AS400.entity;

public class LockODP extends ASEntity {
	String accountNumber; // account Number
	String  accountType; // account Type
	String jounalSequence;
	
	
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getJounalSequence() {
		return jounalSequence;
	}
	public void setJounalSequence(String jounalSequence) {
		this.jounalSequence = jounalSequence;
	}

	
	
}
