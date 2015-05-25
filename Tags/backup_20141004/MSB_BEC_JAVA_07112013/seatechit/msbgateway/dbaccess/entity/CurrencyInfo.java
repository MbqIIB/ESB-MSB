package seatechit.msbgateway.dbaccess.entity;

public class CurrencyInfo {
	private String id;
	private String code;
	private String refCode;
	private int status;
	private String fd_groupcode;
	private String fd_type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CurrencyInfo(String id, String code, String refCode, int status, String fd_type, String fd_groupcode) {
		super();
		this.id = id;
		this.code = code;
		this.refCode = refCode;
		this.status = status;
		this.fd_type = fd_type;
		this.fd_groupcode = fd_groupcode;
	}

	public void setFd_groupcode(String fd_groupcode) {
		this.fd_groupcode = fd_groupcode;
	}

	public String getFd_groupcode() {
		return fd_groupcode;
	}

	public void setFd_type(String fd_type) {
		this.fd_type = fd_type;
	}

	public String getFd_type() {
		return fd_type;
	}
}
