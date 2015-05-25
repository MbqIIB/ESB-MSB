package seatechit.msbgateway.consumer;

public class ErrorInfo {
	private int errorCode;
	private String errorType;
	private String errorMessage;

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ErrorInfo() {
	}

	public ErrorInfo(int errorCode, String errorDescription, String errorType) {
		super();
		this.errorCode = errorCode;// == null ? "0" : errorCode;
		this.errorMessage = errorDescription;
		this.errorType = errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorType() {
		return errorType;
	}

	@Override
	public String toString() {
		return "[errorCode:" + this.errorCode + ",errorMessage:" + this.errorMessage + ",errorType:" + errorType + "]";
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
