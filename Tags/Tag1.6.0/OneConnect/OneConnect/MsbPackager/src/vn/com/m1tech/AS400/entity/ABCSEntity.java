package vn.com.m1tech.AS400.entity;

public class ABCSEntity extends ASEntity{
	
	String transCode;
	String transDate;
	String supervisor;
	String jonalSequece;
	String currency;
	
	
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
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
	public void setSuperviser(String superviser) {
		this.supervisor = superviser;
	}
	public String getJonalSequece() {
		return jonalSequece;
	}
	public void setJonalSequece(String jonalSequece) {
		this.jonalSequece = jonalSequece;
	}
	

	
	

}
