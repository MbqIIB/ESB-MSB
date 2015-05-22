package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class WithDrawFD{
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;	
	String transCode;
	String transDate;
	String supervisor;
	String jonalSequece;
	String currency;	
	String creditAmount;
	String debitAmount;
	String fdAcc;
	String glAcc;
	String ddAcc;
	String description;
	String fdCurrency;
	
	
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
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getJonalSequece() {
		return jonalSequece;
	}
	public void setJonalSequece(String jonalSequece) {
		this.jonalSequece = jonalSequece;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getFdAcc() {
		return fdAcc;
	}
	public void setFdAcc(String fdAcc) {
		this.fdAcc = fdAcc;
	}
	public String getGlAcc() {
		return glAcc;
	}
	public void setGlAcc(String glAcc) {
		this.glAcc = glAcc;
	}
	public String getDdAcc() {
		return ddAcc;
	}
	public void setDdAcc(String ddAcc) {
		this.ddAcc = ddAcc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFdCurrency() {
		return fdCurrency;
	}
	public void setFdCurrency(String fdCurrency) {
		this.fdCurrency = fdCurrency;
	}
	
	
	

}
