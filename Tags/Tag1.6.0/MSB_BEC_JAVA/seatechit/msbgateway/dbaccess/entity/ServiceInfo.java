package seatechit.msbgateway.dbaccess.entity;

public class ServiceInfo {
	private String service_id;
	private String service_url;
	private int service_timeout;
	private String service_type;

	public String getService_id() {
		return service_id;
	}

	public void setServicee_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_url() {
		return service_url;
	}

	public void setService_url(String service_url) {
		this.service_url = service_url;
	}

	public int getService_timeout() {
		return service_timeout;
	}

	public void setService_timeout(int service_timeout) {
		this.service_timeout = service_timeout;
	}

	@Override
	public String toString() {
		return "service_id:" + this.service_id + ",service_url:" + this.service_url + ",service_timeout:" + service_timeout;
	}

	public ServiceInfo(String service_id, String service_type, String service_url, int service_timeout) {
		super();
		this.service_id = service_id;
		this.setService_type(service_type);
		this.service_url = service_url;
		this.service_timeout = service_timeout;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getService_type() {
		return service_type;
	}

}
