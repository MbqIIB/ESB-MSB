package vn.com.m1tech.AS400.entity;

import javax.xml.bind.annotation.XmlElement;

public class AppNo {
	
	@XmlElement(name="branch")
	String branch;
	@XmlElement(name="channel")
	String channel;
	@XmlElement(name="teller")
	String teller;
	@XmlElement(name="hostName")
	String hostName;
	@XmlElement(name="transCode")
	String transCode;
	@XmlElement(name="cifNum")
	String cifNum;
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
	public String getCifNum() {
		return cifNum;
	}
	public void setCifNum(String cifNum) {
		this.cifNum = cifNum;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
}
