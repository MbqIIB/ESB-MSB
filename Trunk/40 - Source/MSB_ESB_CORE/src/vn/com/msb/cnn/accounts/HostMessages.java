package vn.com.msb.cnn.accounts;

import java.util.Date;

public class HostMessages {

	private String messageIn;
	private String messageOut;
	private Date sentTime;
	private String portNumber;
	private String channel;
	private int status;
	private String errCode;
	private String description;

	public String getMessageIn() {
		return messageIn;
	}

	public void setMessageIn(String messageIn) {
		this.messageIn = messageIn;
	}

	public String getMessageOut() {
		return messageOut;
	}

	public void setMessageOut(String messageOut) {
		this.messageOut = messageOut;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
