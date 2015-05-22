package seatechit.msbgateway.dbaccess.entity;

public class HostTranCodeInfo {
	private String id;
	private String ref_host_tran_code;
	private String real_host_tran_code;
	private String needSynchronized;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRef_host_tran_code() {
		return ref_host_tran_code;
	}

	public void setRef_host_tran_code(String ref_host_tran_code) {
		this.ref_host_tran_code = ref_host_tran_code;
	}

	public String getReal_host_tran_code() {
		return real_host_tran_code;
	}

	public void setReal_host_tran_code(String real_host_tran_code) {
		this.real_host_tran_code = real_host_tran_code;
	}

	public HostTranCodeInfo(String id, String ref_host_tran_code, String real_host_tran_code, String needSynchronized, String description) {
		super();
		this.id = id;
		this.ref_host_tran_code = ref_host_tran_code;
		this.real_host_tran_code = real_host_tran_code;
		this.needSynchronized = needSynchronized;
		this.description = description;
	}

	public void setNeedSynchronized(String needSynchronized) {
		this.needSynchronized = needSynchronized;
	}

	public String getNeedSynchronized() {
		return needSynchronized;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
