package seatechit.msbgateway.dbaccess.entity;

public class ChannelInfo {
	private String channelId;
	private String channelName;
	private int status;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public ChannelInfo(String channelId, String channelName, int status) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.status = status;
	}

	@Override
	public String toString() {
		return "[channelId:" + this.channelId + ",channelName:" + this.channelName + "]";
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
