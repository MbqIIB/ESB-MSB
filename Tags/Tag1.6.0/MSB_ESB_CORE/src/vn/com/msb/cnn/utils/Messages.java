package vn.com.msb.cnn.utils;

public class Messages {

	private String[] arrString;

	private String description;

	private String errCode;

	private String requestMsg;
	private String responseMsg;

	public Messages() {
		arrString = null;
		description = "";
		errCode = "";
		requestMsg = "";
		responseMsg = "";
	}

	public Messages(String errCode, String[] arrString, String description,
			String requestMsg, String responseMsg) {
		super();
		this.errCode = errCode;
		this.arrString = arrString;
		this.description = description;
		this.requestMsg = requestMsg;
		this.responseMsg = responseMsg;
	}

	public String[] getArrString() {
		return arrString;
	}

	public String getDescription() {
		return description;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setArrString(String[] arrString) {
		this.arrString = arrString;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

}
