package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class OdpEntity {
	
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	String channel;
	String accountNumber;
	String accountType;
	String drawLimit; 
	String authLimit;
	String odRate;
	String argDate; 
	String expDate; 
	String rateVariance;
	String rateCode;
	String rateFloor; 
	String rateCeiling;
	String commitFreeRate;
	String commitFreeType;
	String productGroup;
	String excessLimitType ;
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
	public String getDrawLimit() {
		return drawLimit;
	}
	public void setDrawLimit(String drawLimit) {
		this.drawLimit = drawLimit;
	}
	public String getAuthLimit() {
		return authLimit;
	}
	public void setAuthLimit(String authLimit) {
		this.authLimit = authLimit;
	}
	public String getOdRate() {
		return odRate;
	}
	public void setOdRate(String odRate) {
		this.odRate = odRate;
	}
	public String getArgDate() {
		return argDate;
	}
	public void setArgDate(String argDate) {
		this.argDate = argDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getRateVariance() {
		return rateVariance;
	}
	public void setRateVariance(String rateVariance) {
		this.rateVariance = rateVariance;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public String getRateFloor() {
		return rateFloor;
	}
	public void setRateFloor(String rateFloor) {
		this.rateFloor = rateFloor;
	}
	public String getRateCeiling() {
		return rateCeiling;
	}
	public void setRateCeiling(String rateCeiling) {
		this.rateCeiling = rateCeiling;
	}
	public String getCommitFreeRate() {
		return commitFreeRate;
	}
	public void setCommitFreeRate(String commitFreeRate) {
		this.commitFreeRate = commitFreeRate;
	}
	public String getCommitFreeType() {
		return commitFreeType;
	}
	public void setCommitFreeType(String commitFreeType) {
		this.commitFreeType = commitFreeType;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getExcessLimitType() {
		return excessLimitType;
	}
	public void setExcessLimitType(String excessLimitType) {
		this.excessLimitType = excessLimitType;
	}
	public String getJounalSequence() {
		return jounalSequence;
	}
	public void setJounalSequence(String jounalSequence) {
		this.jounalSequence = jounalSequence;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
	
	
}
