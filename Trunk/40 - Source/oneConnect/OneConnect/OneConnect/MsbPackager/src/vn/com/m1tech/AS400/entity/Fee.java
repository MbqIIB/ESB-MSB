package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fee  {
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	@XmlElement(name="transCode")
	String transCode;
	@XmlElement(name="transDate")
	String transDate;
	@XmlElement(name="supervisor")
	String supervisor;
	@XmlElement(name="jonalSequece")
	String jonalSequece;
	@XmlElement(name="currency")
	String currency;
	@XmlElement(name="creditAcc")	
	String creditAcc;
	@XmlElement(name="debitAcc")
	String debitAcc;
	@XmlElement(name="totalAmount")
	String totalAmount;
	@XmlElement(name="amount")
	String amount;
	@XmlElement(name="vatAmount")
	String vatAmount;
	@XmlElement(name="description")
	String description;
	
	
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
	public String getCreditAcc() {
		return creditAcc;
	}
	public void setCreditAcc(String creditAcc) {
		this.creditAcc = creditAcc;
	}
	public String getDebitAcc() {
		return debitAcc;
	}
	public void setDebitAcc(String debitAcc) {
		this.debitAcc = debitAcc;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
