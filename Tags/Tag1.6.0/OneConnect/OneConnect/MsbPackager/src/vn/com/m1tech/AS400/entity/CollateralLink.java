package vn.com.m1tech.AS400.entity;

public class CollateralLink extends ASEntity {

	String applicationNo; // Account number
	String facilityCode; // 240
	String collateralId; // 
	String indexDate; // 
	String PledgedAmount; // 
	String percentPledged; // Check Amount
	String fixedAmount; // Payee name
	String basicOfadvance;
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
		return PledgedAmount;
	}
	public void setPledgedAmount(String pledgedAmount) {
		PledgedAmount = pledgedAmount;
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
	
	
	
	
}
