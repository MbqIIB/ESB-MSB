package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class TransferAcc  {
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	String supervisor;
	String currency;	
	String journalSeq;
	String transDate;
	String creditAmount; 
	String creditAcc;
	String debitAmount;
	String debitAcc; 
	String creditRate;
	String debitRate;
	String adviceNumber; 
	String creditCurrency;
	String debitCurrency; 
	String description;
	String transCode;
	String effetiveDate;
	String sysName;
	
	
	
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
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
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getJournalSeq() {
		return journalSeq;
	}
	public void setJournalSeq(String journalSeq) {
		this.journalSeq = journalSeq;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getCreditAcc() {
		return creditAcc;
	}
	public void setCreditAcc(String creditAcc) {
		this.creditAcc = creditAcc;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getDebitAcc() {
		return debitAcc;
	}
	public void setDebitAcc(String debitAcc) {
		this.debitAcc = debitAcc;
	}
	public String getCreditRate() {
		return creditRate;
	}
	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}
	public String getDebitRate() {
		return debitRate;
	}
	public void setDebitRate(String debitRate) {
		this.debitRate = debitRate;
	}
	public String getAdviceNumber() {
		return adviceNumber;
	}
	public void setAdviceNumber(String adviceNumber) {
		this.adviceNumber = adviceNumber;
	}
	public String getCreditCurrency() {
		return creditCurrency;
	}
	public void setCreditCurrency(String creditCurrency) {
		this.creditCurrency = creditCurrency;
	}
	public String getDebitCurrency() {
		return debitCurrency;
	}
	public void setDebitCurrency(String debitCurrency) {
		this.debitCurrency = debitCurrency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getEffetiveDate() {
		return effetiveDate;
	}
	public void setEffetiveDate(String effetiveDate) {
		this.effetiveDate = effetiveDate;
	}
	
	
}
