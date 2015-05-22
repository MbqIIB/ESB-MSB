package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AAEntity  {	
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;	
	// cac thong tin doi voi muc dich approve laf thong tin tra ra tu hamf lockAA
	@XmlElement(name="applicationNo")
	String applicationNo; // application Number
	@XmlElement(name="cifNum")
	String cifNum;
	@XmlElement(name="applicationDate")
	String applicationDate;
	@XmlElement(name="workingEx")
	String workingEx;
	@XmlElement(name="clasifielDate")
	String specProvision; // Y/N
	@XmlElement(name="clasifielDate")
	String clasifielDate;
	@XmlElement(name="carCode")
	String carCode;
	@XmlElement(name="reviewDate")
	String reviewDate;
	@XmlElement(name="retention")
	String retention;
	@XmlElement(name="reviewReMark")
	String reviewReMark;
	@XmlElement(name="refinanceFrom")
	String refinanceFrom;
	@XmlElement(name="curencyType")
	String curencyType;
	@XmlElement(name="officeCode")
	String officeCode;
	@XmlElement(name="classification")
	String classification;
	@XmlElement(name="MADate")
	String MARemarck;
	@XmlElement(name="MADate")
	String MADate;
	@XmlElement(name="tucachPn")
	String tucachPn;
	@XmlElement(name="tinhHinhTc")
	String tinhHinhTc;
	@XmlElement(name="khaNangVayVon")
	String khaNangVayVon;
	@XmlElement(name="damBaoTienVay")
	String damBaoTienVay;
	@XmlElement(name="limitAmount")
	String limitAmount;


	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getTucachPn() {
		return tucachPn;
	}

	public void setTucachPn(String tucachPn) {
		this.tucachPn = tucachPn;
	}

	public String getTinhHinhTc() {
		return tinhHinhTc;
	}

	public void setTinhHinhTc(String tinhHinhTc) {
		this.tinhHinhTc = tinhHinhTc;
	}

	public String getKhaNangVayVon() {
		return khaNangVayVon;
	}

	public void setKhaNangVayVon(String khaNangVayVon) {
		this.khaNangVayVon = khaNangVayVon;
	}

	public String getDamBaoTienVay() {
		return damBaoTienVay;
	}

	public void setDamBaoTienVay(String damBaoTienVay) {
		this.damBaoTienVay = damBaoTienVay;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getCifNum() {
		return cifNum;
	}

	public void setCifNum(String cifNum) {
		this.cifNum = cifNum;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getWorkingEx() {
		return workingEx;
	}

	public void setWorkingEx(String workingEx) {
		this.workingEx = workingEx;
	}

	public String getSpecProvision() {
		return specProvision;
	}

	public void setSpecProvision(String specProvision) {
		this.specProvision = specProvision;
	}

	public String getClasifielDate() {
		return clasifielDate;
	}

	public void setClasifielDate(String clasifielDate) {
		this.clasifielDate = clasifielDate;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getRetention() {
		return retention;
	}

	public void setRetention(String retention) {
		this.retention = retention;
	}

	public String getReviewReMark() {
		return reviewReMark;
	}

	public void setReviewReMark(String reviewReMark) {
		this.reviewReMark = reviewReMark;
	}

	public String getRefinanceFrom() {
		return refinanceFrom;
	}

	public void setRefinanceFrom(String refinanceFrom) {
		this.refinanceFrom = refinanceFrom;
	}

	public String getCurencyType() {
		return curencyType;
	}

	public void setCurencyType(String curencyType) {
		this.curencyType = curencyType;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getMARemarck() {
		return MARemarck;
	}

	public void setMARemarck(String mARemarck) {
		MARemarck = mARemarck;
	}

	public String getMADate() {
		return MADate;
	}

	public void setMADate(String mADate) {
		MADate = mADate;
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

	
}
