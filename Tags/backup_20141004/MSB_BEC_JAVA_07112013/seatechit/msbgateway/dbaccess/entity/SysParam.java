package seatechit.msbgateway.dbaccess.entity;

public class SysParam {
	private String groupName;
	private String paramName;
	private String paramValue;
	private String description;

	public SysParam() {
	}

	public SysParam(String groupName, String paramName, String paramValue, String description) {
		super();
		this.groupName = groupName;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.description = description;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
