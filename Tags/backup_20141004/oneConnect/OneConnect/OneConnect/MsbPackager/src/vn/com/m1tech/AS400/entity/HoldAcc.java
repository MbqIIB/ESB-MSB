package vn.com.m1tech.AS400.entity;

public class HoldAcc extends ASEntity {
	String account; 
	String expireDate;
	String description;
	String holdAmount;
	String holdCode;
	String accType;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHoldAmount() {
		return holdAmount;
	}
	public void setHoldAmount(String holdAmount) {
		this.holdAmount = holdAmount;
	}
	public String getHoldCode() {
		return holdCode;
	}
	public void setHoldCode(String holdCode) {
		this.holdCode = holdCode;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	
	
	
	
}
