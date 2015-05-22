package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Facility {
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	@XmlElement(name="branchCode")
	String branchCode;
	@XmlElement(name="rcdOveride")
	String rcdOveride;
	@XmlElement(name="applicationNo")
	String applicationNo;
	@XmlElement(name="facilityCode")
	String facilityCode;
	@XmlElement(name="seqNo")
	String seqNo;
	@XmlElement(name="levelNo")
	String levelNo;
	@XmlElement(name="cifNo")
	String cifNo;
	@XmlElement(name="AFCNo")
	String AFCNo;
	@XmlElement(name="producType")
	String producType;
	@XmlElement(name="AFCStatus")
	String AFCStatus;
	@XmlElement(name="appliedDate")
	String appliedDate;
	@XmlElement(name="currencyType")
	String currencyType;
	@XmlElement(name="appliedAmount")
	String appliedAmount;
	@XmlElement(name="purposeCode")
	String purposeCode;
	@XmlElement(name="revolving")
	String revolving;
	@XmlElement(name="aBalIndicator")
	String aBalIndicator;
	@XmlElement(name="senttoAppDate")
	String senttoAppDate;
	@XmlElement(name="recFromAppCenter")
	String recFromAppCenter;
	@XmlElement(name="approvedDate")
	String approvedDate;
	@XmlElement(name="appDate")
	String appDate;
	@XmlElement(name="appBy")
	String appBy;
	@XmlElement(name="offerDate")
	String offerDate;
	@XmlElement(name="acceptedDate")
	String acceptedDate;
	@XmlElement(name="appAmount")
	String appAmount;
	@XmlElement(name="availabelLimit")
	String availabelLimit;
	@XmlElement(name="facilityLimit")
	String facilityLimit;
	@XmlElement(name="availableBalance")
	String availableBalance;
	@XmlElement(name="interestBase")
	String interestBase;
	@XmlElement(name="interestMode")
	String interestMode;
	@XmlElement(name="yearBase")	
	String yearBase;
	@XmlElement(name="term")
	String term;
	@XmlElement(name="termCode")
	String termCode;
	@XmlElement(name="interestRate")
	String interestRate;
	@XmlElement(name="variance")
	String variance;
	@XmlElement(name="varCode")
	String varCode;
	@XmlElement(name="rateReviewDate")
	String rateReviewDate;
	@XmlElement(name="rateReviewTerm")
	String rateReviewTerm;
	@XmlElement(name="rateReviewCode")
	String rateReviewCode;
	@XmlElement(name="primeRateFloor")
	String primeRateFloor;
	@XmlElement(name="primeRateCeiling")
	String primeRateCeiling;
	@XmlElement(name="paymentCode")
	String paymentCode;
	@XmlElement(name="paymentFreq")
	String paymentFreq;
	@XmlElement(name="paymentFreqCode")
	String paymentFreqCode;
	@XmlElement(name="interestPmtFreq")
	String interestPmtFreq;
	@XmlElement(name="interestPmtFreqCode")
	String interestPmtFreqCode;
	@XmlElement(name="paymentAmount")
	String paymentAmount;
	@XmlElement(name="finalPaymAmt")
	String finalPaymAmt;
	@XmlElement(name="lateChargeCode")
	String lateChargeCode;
	@XmlElement(name="gracePeriod")
	String gracePeriod;
	@XmlElement(name="gracePeriodCode")
	String gracePeriodCode;
	@XmlElement(name="expireDate")
	String expireDate;
	@XmlElement(name="processingFee")
	String processingFee;
	@XmlElement(name="legalFee")
	String legalFee;
	@XmlElement(name="cifName")
	String cifName;
	@XmlElement(name="branchTCode")
	String branchTCode;
	@XmlElement(name="offerCode")
	String offerCode;
	@XmlElement(name="shareLimitInd")
	String shareLimitInd;
	@XmlElement(name="branchTHCode")
	String branchTHCode;
	@XmlElement(name="secureCode")
	String secureCode;
	@XmlElement(name="estimateLose")
	String estimateLose;
	@XmlElement(name="rateNumber")
	String rateNumber;
	
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
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getRcdOveride() {
		return rcdOveride;
	}
	public void setRcdOveride(String rcdOveride) {
		this.rcdOveride = rcdOveride;
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
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public String getAFCNo() {
		return AFCNo;
	}
	public void setAFCNo(String aFCNo) {
		AFCNo = aFCNo;
	}
	public String getProducType() {
		return producType;
	}
	public void setProducType(String producType) {
		this.producType = producType;
	}
	public String getAFCStatus() {
		return AFCStatus;
	}
	public void setAFCStatus(String aFCStatus) {
		AFCStatus = aFCStatus;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getAppliedAmount() {
		return appliedAmount;
	}
	public void setAppliedAmount(String appliedAmount) {
		this.appliedAmount = appliedAmount;
	}
	public String getPurposeCode() {
		return purposeCode;
	}
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	public String getRevolving() {
		return revolving;
	}
	public void setRevolving(String revolving) {
		this.revolving = revolving;
	}
	public String getaBalIndicator() {
		return aBalIndicator;
	}
	public void setaBalIndicator(String aBalIndicator) {
		this.aBalIndicator = aBalIndicator;
	}
	public String getSenttoAppDate() {
		return senttoAppDate;
	}
	public void setSenttoAppDate(String senttoAppDate) {
		this.senttoAppDate = senttoAppDate;
	}
	public String getRecFromAppCenter() {
		return recFromAppCenter;
	}
	public void setRecFromAppCenter(String recFromAppCenter) {
		this.recFromAppCenter = recFromAppCenter;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getAppBy() {
		return appBy;
	}
	public void setAppBy(String appBy) {
		this.appBy = appBy;
	}
	public String getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	public String getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public String getAppAmount() {
		return appAmount;
	}
	public void setAppAmount(String appAmount) {
		this.appAmount = appAmount;
	}
	public String getAvailabelLimit() {
		return availabelLimit;
	}
	public void setAvailabelLimit(String availabelLimit) {
		this.availabelLimit = availabelLimit;
	}
	public String getFacilityLimit() {
		return facilityLimit;
	}
	public void setFacilityLimit(String facilityLimit) {
		this.facilityLimit = facilityLimit;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getInterestBase() {
		return interestBase;
	}
	public void setInterestBase(String interestBase) {
		this.interestBase = interestBase;
	}
	public String getInterestMode() {
		return interestMode;
	}
	public void setInterestMode(String interestMode) {
		this.interestMode = interestMode;
	}
	public String getYearBase() {
		return yearBase;
	}
	public void setYearBase(String yearBase) {
		this.yearBase = yearBase;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getVariance() {
		return variance;
	}
	public void setVariance(String variance) {
		this.variance = variance;
	}
	public String getVarCode() {
		return varCode;
	}
	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}
	public String getRateReviewDate() {
		return rateReviewDate;
	}
	public void setRateReviewDate(String rateReviewDate) {
		this.rateReviewDate = rateReviewDate;
	}
	public String getRateReviewTerm() {
		return rateReviewTerm;
	}
	public void setRateReviewTerm(String rateReviewTerm) {
		this.rateReviewTerm = rateReviewTerm;
	}
	public String getRateReviewCode() {
		return rateReviewCode;
	}
	public void setRateReviewCode(String rateReviewCode) {
		this.rateReviewCode = rateReviewCode;
	}
	public String getPrimeRateFloor() {
		return primeRateFloor;
	}
	public void setPrimeRateFloor(String primeRateFloor) {
		this.primeRateFloor = primeRateFloor;
	}
	public String getPrimeRateCeiling() {
		return primeRateCeiling;
	}
	public void setPrimeRateCeiling(String primeRateCeiling) {
		this.primeRateCeiling = primeRateCeiling;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPaymentFreq() {
		return paymentFreq;
	}
	public void setPaymentFreq(String paymentFreq) {
		this.paymentFreq = paymentFreq;
	}
	public String getPaymentFreqCode() {
		return paymentFreqCode;
	}
	public void setPaymentFreqCode(String paymentFreqCode) {
		this.paymentFreqCode = paymentFreqCode;
	}
	public String getInterestPmtFreq() {
		return interestPmtFreq;
	}
	public void setInterestPmtFreq(String interestPmtFreq) {
		this.interestPmtFreq = interestPmtFreq;
	}
	public String getInterestPmtFreqCode() {
		return interestPmtFreqCode;
	}
	public void setInterestPmtFreqCode(String interestPmtFreqCode) {
		this.interestPmtFreqCode = interestPmtFreqCode;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getFinalPaymAmt() {
		return finalPaymAmt;
	}
	public void setFinalPaymAmt(String finalPaymAmt) {
		this.finalPaymAmt = finalPaymAmt;
	}
	public String getLateChargeCode() {
		return lateChargeCode;
	}
	public void setLateChargeCode(String lateChargeCode) {
		this.lateChargeCode = lateChargeCode;
	}
	public String getGracePeriod() {
		return gracePeriod;
	}
	public void setGracePeriod(String gracePeriod) {
		this.gracePeriod = gracePeriod;
	}
	public String getGracePeriodCode() {
		return gracePeriodCode;
	}
	public void setGracePeriodCode(String gracePeriodCode) {
		this.gracePeriodCode = gracePeriodCode;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}
	public String getLegalFee() {
		return legalFee;
	}
	public void setLegalFee(String legalFee) {
		this.legalFee = legalFee;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getBranchTCode() {
		return branchTCode;
	}
	public void setBranchTCode(String branchTCode) {
		this.branchTCode = branchTCode;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getShareLimitInd() {
		return shareLimitInd;
	}
	public void setShareLimitInd(String shareLimitInd) {
		this.shareLimitInd = shareLimitInd;
	}
	public String getBranchTHCode() {
		return branchTHCode;
	}
	public void setBranchTHCode(String branchTHCode) {
		this.branchTHCode = branchTHCode;
	}
	public String getSecureCode() {
		return secureCode;
	}
	public void setSecureCode(String secureCode) {
		this.secureCode = secureCode;
	}
	public String getEstimateLose() {
		return estimateLose;
	}
	public void setEstimateLose(String estimateLose) {
		this.estimateLose = estimateLose;
	}

	public String getRateNumber() {
		return rateNumber;
	}
	public void setRateNumber(String rateNumber) {
		this.rateNumber = rateNumber;
	}

}
