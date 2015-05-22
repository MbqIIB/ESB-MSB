package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class HoldLockAcc {

	@XmlElement(name = "branch")
	String branch;
	@XmlElement(name = "teller")
	String teller;
	@XmlElement(name = "hostName")
	String hostName;

	String account;
	String journalSeq;
	String accType;

	
	
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getJournalSeq() {
		return journalSeq;
	}

	public void setJournalSeq(String journalSeq) {
		this.journalSeq = journalSeq;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

}
