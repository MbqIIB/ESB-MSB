package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class Collateral  {

	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	
	String collateralName; 
	String cifName; 
	String collateralDesc; 
	String deposit; 
	String officeCode; 
	String fdAcc; 
	String countryCode; 
	String depositAmount; 
	String curency;
	String depositTenureNum;
	String depositTenureType; 
	String cifNum;
	String interestRate;
	String relationShip;
	String issueDate;
	String cashValueSale;
	String maturityDate;
	String reviewDate;
	String markedValue;
	String lienOfficeCode;
	String issuingInstitution;
	String depositName1;
	String depositName2;
	String depositName3;
	String dudateInstruction;
	String description;
	String collateralRatio;
	String investment;
	String bankUsage;
	String regPlace;
	String ownerPlace;
	String notaryPublic;
	
	String calaCode;
	String securegDate;
	String securegExp ;
	String secureReqNo;
	String ownerRightDate;
	
	String ownerNum;
	String ownerExtDate;
	
	
	
	public String getCalaCode() {
		return calaCode;
	}
	public void setCalaCode(String calaCode) {
		this.calaCode = calaCode;
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
	public String getCollateralName() {
		return collateralName;
	}
	public void setCollateralName(String collateralName) {
		this.collateralName = collateralName;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getCollateralDesc() {
		return collateralDesc;
	}
	public void setCollateralDesc(String collateralDesc) {
		this.collateralDesc = collateralDesc;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getFdAcc() {
		return fdAcc;
	}
	public void setFdAcc(String fdAcc) {
		this.fdAcc = fdAcc;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getCurency() {
		return curency;
	}
	public void setCurency(String curency) {
		this.curency = curency;
	}
	public String getDepositTenureNum() {
		return depositTenureNum;
	}
	public void setDepositTenureNum(String depositTenureNum) {
		this.depositTenureNum = depositTenureNum;
	}
	public String getDepositTenureType() {
		return depositTenureType;
	}
	public void setDepositTenureType(String depositTenureType) {
		this.depositTenureType = depositTenureType;
	}
	public String getCifNum() {
		return cifNum;
	}
	public void setCifNum(String cifNum) {
		this.cifNum = cifNum;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getRelationShip() {
		return relationShip;
	}
	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getCashValueSale() {
		return cashValueSale;
	}
	public void setCashValueSale(String cashValueSale) {
		this.cashValueSale = cashValueSale;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getMarkedValue() {
		return markedValue;
	}
	public void setMarkedValue(String markedValue) {
		this.markedValue = markedValue;
	}
	public String getLienOfficeCode() {
		return lienOfficeCode;
	}
	public void setLienOfficeCode(String lienOfficeCode) {
		this.lienOfficeCode = lienOfficeCode;
	}
	public String getIssuingInstitution() {
		return issuingInstitution;
	}
	public void setIssuingInstitution(String issuingInstitution) {
		this.issuingInstitution = issuingInstitution;
	}
	public String getDepositName1() {
		return depositName1;
	}
	public void setDepositName1(String depositName1) {
		this.depositName1 = depositName1;
	}
	public String getDepositName2() {
		return depositName2;
	}
	public void setDepositName2(String depositName2) {
		this.depositName2 = depositName2;
	}
	public String getDepositName3() {
		return depositName3;
	}
	public void setDepositName3(String depositName3) {
		this.depositName3 = depositName3;
	}
	public String getDudateInstruction() {
		return dudateInstruction;
	}
	public void setDudateInstruction(String dudateInstruction) {
		this.dudateInstruction = dudateInstruction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCollateralRatio() {
		return collateralRatio;
	}
	public void setCollateralRatio(String collateralRatio) {
		this.collateralRatio = collateralRatio;
	}
	public String getInvestment() {
		return investment;
	}
	public void setInvestment(String investment) {
		this.investment = investment;
	}
	public String getBankUsage() {
		return bankUsage;
	}
	public void setBankUsage(String bankUsage) {
		this.bankUsage = bankUsage;
	}
	public String getRegPlace() {
		return regPlace;
	}
	public void setRegPlace(String regPlace) {
		this.regPlace = regPlace;
	}
	public String getOwnerPlace() {
		return ownerPlace;
	}
	public void setOwnerPlace(String ownerPlace) {
		this.ownerPlace = ownerPlace;
	}
	public String getNotaryPublic() {
		return notaryPublic;
	}
	public void setNotaryPublic(String notaryPublic) {
		this.notaryPublic = notaryPublic;
	}
	/**
	 * Created By : QuanLD
	 * Created On : Feb 26, 2015
	 * Version    : v1.0
	 * @return
	 * Description:
	 * Params:
	 * Amendment History:
	 *
	 * Amended By       Amended On      Amendment Description
	 * ------------     -----------     ---------------------------------------------
	 * 
	*/
	
	




	public String getSecuregDate() {
		return securegDate;
	}
	public String getOwnerExtDate() {
		return ownerExtDate;
	}
	public void setOwnerExtDate(String ownerExtDate) {
		this.ownerExtDate = ownerExtDate;
	}
	public void setSecuregDate(String securegDate) {
		this.securegDate = securegDate;
	}
	public String getSecuregExp() {
		return securegExp;
	}
	public void setSecuregExp(String securegExp) {
		this.securegExp = securegExp;
	}
	public String getSecureReqNo() {
		return secureReqNo;
	}
	public void setSecureReqNo(String secureReqNo) {
		this.secureReqNo = secureReqNo;
	}
	public String getOwnerRightDate() {
		return ownerRightDate;
	}
	public void setOwnerRightDate(String ownerRightDate) {
		this.ownerRightDate = ownerRightDate;
	}

	public String getOwnerNum() {
		return ownerNum;
	}
	public void setOwnerNum(String ownerNum) {
		this.ownerNum = ownerNum;
	}
	
	
	
	

}
