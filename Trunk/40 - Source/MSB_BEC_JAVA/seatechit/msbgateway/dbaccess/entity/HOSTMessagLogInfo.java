package seatechit.msbgateway.dbaccess.entity;

public class HOSTMessagLogInfo implements Cloneable {
	private String teller_id;
	private String approver_id;

	private String branch_code;
	private String ws_ip;
	private String ws_name;

	private String tran_sn;
	private String host_tran_sn;
	private String host_tran_date;
	private String host_real_date;
	private String host_tran_code;
	private String resp_code;
	private String resp_msg;

	private String ref_tran_code;
	private String ref_channel;
	private String ref_service;

	private String hostRequestMsg;
	private String hostResponseMsg;

	private String send_time;
	private String receive_time;
	private String numOfRetry;
	private String batch_no;
	private String needSynchronized;

	private String ref_cif_acct;
	private String ref_amount;

	public String getRef_tran_code() {
		return ref_tran_code;
	}

	public void setRef_tran_code(String ref_tran_code) {
		this.ref_tran_code = ref_tran_code;
	}

	public String getTeller_id() {
		return teller_id;
	}

	public void setTeller_id(String teller_id) {
		this.teller_id = teller_id;
	}

	public String getApprover_id() {
		return approver_id;
	}

	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}

	public String getWs_ip() {
		return ws_ip;
	}

	public void setWs_ip(String ws_ip) {
		this.ws_ip = ws_ip;
	}

	public String getWs_name() {
		return ws_name;
	}

	public void setWs_name(String ws_name) {
		this.ws_name = ws_name;
	}

	public void setHost_tran_sn(String host_tran_sn) {
		this.host_tran_sn = host_tran_sn;
	}

	public String getHost_tran_sn() {
		return host_tran_sn;
	}

	public void setHost_tran_date(String host_tran_date) {
		this.host_tran_date = host_tran_date;
	}

	public String getHost_tran_date() {
		return host_tran_date;
	}

	public void setRef_channel(String ref_channel) {
		this.ref_channel = ref_channel;
	}

	public String getRef_channel() {
		return ref_channel;
	}

	public void setHostRequestMsg(String hostRequestMsg) {
		this.hostRequestMsg = hostRequestMsg;
	}

	public String getHostRequestMsg() {
		return hostRequestMsg;
	}

	public void setHostResponseMsg(String hostResponseMsg) {
		this.hostResponseMsg = hostResponseMsg;
	}

	public String getHostResponseMsg() {
		return hostResponseMsg;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBranch_code() {
		return branch_code;
	}

	@Override
	public Object clone() {
		HOSTMessagLogInfo obj = new HOSTMessagLogInfo();
		obj.setApprover_id(this.approver_id);
		obj.setBranch_code(this.branch_code);
		obj.setHost_tran_date(this.host_tran_date);
		obj.setHost_tran_sn(this.host_tran_sn);
		obj.setHostRequestMsg(this.hostRequestMsg);
		obj.setHostResponseMsg(this.hostResponseMsg);
		obj.setRef_channel(this.ref_channel);
		obj.setRef_tran_code(this.ref_tran_code);
		obj.setTeller_id(this.teller_id);
		obj.setWs_ip(this.ws_ip);
		obj.setWs_name(this.ws_name);
		obj.setSend_time(this.send_time);
		obj.setReceive_time(this.receive_time);
		obj.setNumOfRetry(this.numOfRetry);
		obj.setTran_sn(this.tran_sn);
		obj.setRef_service(this.ref_service);
		obj.setBatch_no(this.batch_no);
		obj.setResp_code(this.resp_code);
		obj.setResp_msg(this.resp_msg);
		obj.setHost_real_date(this.host_real_date);
		obj.setHost_tran_code(this.host_tran_code);
		obj.setNeedSynchronized(this.needSynchronized);
		obj.setRef_cif_acct(this.ref_cif_acct);
		obj.setRef_amount(this.ref_amount);
		return obj;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setNumOfRetry(String numOfRetry) {
		this.numOfRetry = numOfRetry;
	}

	public String getNumOfRetry() {
		return numOfRetry;
	}

	public void setTran_sn(String tran_sn) {
		this.tran_sn = tran_sn;
	}

	public String getTran_sn() {
		return tran_sn;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setRef_service(String ref_service) {
		this.ref_service = ref_service;
	}

	public String getRef_service() {
		return ref_service;
	}

	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}

	public String getResp_msg() {
		return resp_msg;
	}

	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}

	public String getResp_code() {
		return resp_code;
	}

	public void setHost_real_date(String host_real_date) {
		this.host_real_date = host_real_date;
	}

	public String getHost_real_date() {
		return host_real_date;
	}

	public void setNeedSynchronized(String needSynchronize) {
		this.needSynchronized = needSynchronize;
	}

	public String getNeedSynchronized() {
		return needSynchronized;
	}

	public void setHost_tran_code(String host_tran_code) {
		this.host_tran_code = host_tran_code;
	}

	public String getHost_tran_code() {
		return host_tran_code;
	}

	public void setRef_cif_acct(String ref_cif_acct) {
		this.ref_cif_acct = ref_cif_acct;
	}

	public String getRef_cif_acct() {
		return ref_cif_acct;
	}

	public void setRef_amount(String ref_amount) {
		this.ref_amount = ref_amount;
	}

	public String getRef_amount() {
		return ref_amount;
	}
}
