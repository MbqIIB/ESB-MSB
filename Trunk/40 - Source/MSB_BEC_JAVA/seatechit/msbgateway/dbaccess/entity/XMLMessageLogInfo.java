package seatechit.msbgateway.dbaccess.entity;

public class XMLMessageLogInfo {
	// Message info
	private String message_sn;
	private String tran_code;
	private String tran_service_code;

	// Sender + responser info
	private String sender_id;
	private String sender_tran_sn;
	private String send_date;
	private String send_time;
	private String receiver_id;
	private String receiver_tran_sn;
	private String service_code;

	private String receive_date;
	private String receive_time;

	private String resp_date;
	private String resp_time;

	private String execution_date;
	private String ref_tran_no;

	private String resp_code;
	private String resp_msg;

	private String services;
	private String needSynchronized;

	public XMLMessageLogInfo() {
	}

	public String getMessage_sn() {
		return message_sn;
	}

	public void setMessage_sn(String tran_sn) {
		this.message_sn = tran_sn;
	}

	public String getTran_code() {
		return tran_code;
	}

	public void setTran_code(String tran_code) {
		this.tran_code = tran_code;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getSender_tran_sn() {
		return sender_tran_sn;
	}

	public void setSender_tran_sn(String sender_tran_sn) {
		this.sender_tran_sn = sender_tran_sn;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getReceiver_tran_sn() {
		return receiver_tran_sn;
	}

	public void setReceiver_tran_sn(String receiver_tran_sn) {
		this.receiver_tran_sn = receiver_tran_sn;
	}

	public String getResp_date() {
		return resp_date;
	}

	public void setResp_date(String resp_date) {
		this.resp_date = resp_date;
	}

	public String getResp_time() {
		return resp_time;
	}

	public void setResp_time(String resp_time) {
		this.resp_time = resp_time;
	}

	public String getResp_code() {
		return resp_code;
	}

	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}

	public String getResp_msg() {
		return resp_msg;
	}

	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public void setExecution_date(String execution_date) {
		this.execution_date = execution_date;
	}

	public String getExecution_date() {
		return execution_date;
	}

	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}

	public String getReceive_date() {
		return receive_date;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setRef_tran_no(String ref_tran_no) {
		this.ref_tran_no = ref_tran_no;
	}

	public String getRef_tran_no() {
		return ref_tran_no;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getService_code() {
		return service_code;
	}

	public void setTran_service_code(String tran_service_code) {
		this.tran_service_code = tran_service_code;
	}

	public String getTran_service_code() {
		return tran_service_code;
	}

	public void setNeedSynchronized(String needSynchronize) {
		this.needSynchronized = needSynchronize;
	}

	public String getNeedSynchronized() {
		return needSynchronized;
	}

}
