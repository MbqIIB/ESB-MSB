package vn.com.m1tech.AS400.entity;

public class HoldLockAcc  extends ASEntity{
	
	String account; 
	String journalSeq;
	String accType;
	
	
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
