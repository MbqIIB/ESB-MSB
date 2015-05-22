package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class LockODP  {
	
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	String accountNumber; // account Number
	String accountType; // account Type
	String jounalSequence;
	String odpType;
	
	
	
	public String getOdpType() {
		return odpType;
	}
	public void setOdpType(String odpType) {
		this.odpType = odpType;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTeller() {
		return teller;
	}
	public void setTeller(String teller) {
		this.teller = teller;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
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
