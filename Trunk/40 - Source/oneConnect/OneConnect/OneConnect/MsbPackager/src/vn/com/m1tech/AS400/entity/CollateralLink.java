package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class CollateralLink  {
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;

	String applicationNo; 
	String facilityCode; // 240
	String collateralId; // 
	String indexDate; // 
	String pledgedAmount; // 
	String percentPledged; 
	String fixedAmount; 
	String basicOfadvance;
	String facId;
	
	
	
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
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	public String getCollateralId() {
		return collateralId;
	}
	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
	}
	public String getIndexDate() {
		return indexDate;
	}
	public void setIndexDate(String indexDate) {
		this.indexDate = indexDate;
	}
	public String getPledgedAmount() {
		return pledgedAmount;
	}
	public void setPledgedAmount(String pledgedAmounts) {
		this.pledgedAmount = pledgedAmounts;
	}
	public String getPercentPledged() {
		return percentPledged;
	}
	public void setPercentPledged(String percentPledged) {
		this.percentPledged = percentPledged;
	}
	public String getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(String fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public String getBasicOfadvance() {
		return basicOfadvance;
	}
	public void setBasicOfadvance(String basicOfadvance) {
		this.basicOfadvance = basicOfadvance;
	}
	public String getFacId() {
		return facId;
	}
	public void setFacId(String facId) {
		this.facId = facId;
	}

	
	
	
}
