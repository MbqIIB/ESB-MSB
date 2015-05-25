package seatechit.esb.business.rsa;

import java.util.List;

import seatechit.esb.rsa.assign.RSAAdmin;
import seatechit.esb.rsa.verify.AuthUser;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;

public class RSAOutFlow_CallRSAService extends MbJavaComputeNode {

	private String rcv_date = "";
	private String rcv_time = "";
	private String req_date = "";
	private String req_time = "";
	private String rsp_date = "";
	private String rsp_time = "";
	private String rsp_code = "";
	private String rsp_msg = "";

	private RSAAdmin rsaAdminSrv = null;
	private AuthUser rsaAuthenSrv = null;

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		// Ghi nhan thoi gian bat dau xy ly message
		rcv_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		rcv_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		MbOutputTerminal out = getOutputTerminal("out");

		MbMessage inMessage = inAssembly.getMessage();
		// copy message tu in sang out
		MbMessage tempMessage = new MbMessage(inMessage);
		MbMessage outMessage = new MbMessage(inMessage);

		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly, outMessage);

		// Bo phan body o temp message
		removeElement(tempMessage, "/Data/Body");

		// Bo phan du lieu xml o out message,se xay lai sau
		removeElement(outMessage, "/Data");
		try {

			String tranCode = ElementUtils.getXMLElementInStringValue(inMessage, "/Data/Header/srv");

			// Lay so rm_num,act_num tren bien moi truong
			if (tranCode.equals("RSA001")) {
				Assign(inMessage, tempMessage);
			} else if (tranCode.equals("RSA005")) {
				Unassign(inMessage, tempMessage);
			} else if (tranCode.equals("RSA003")) {
				CreateUser(inMessage, tempMessage);
			} else if (tranCode.equals("RSA004")) {
				deleteUser(inMessage, tempMessage);
			} else if (tranCode.equals("RSA002")) {
				checkOTP(inMessage, tempMessage);
			}
			//ElementUtils.getXMLContent(outMessage);
			//ElementUtils.getXMLContent(inMessage);
			buildResponseMessage(inMessage, tempMessage, outMessage);
			//ElementUtils.getXMLContent(outMessage);
			//ElementUtils.getXMLContent(inMessage);
			out.propagate(outAssembly);

		} catch (Exception ex) {
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void removeElement(MbMessage outMessage, String xPath) throws MbException {
		try {
			List<MbElement> lstOfElement = ((List<MbElement>) outMessage.evaluateXPath(xPath));
			if (lstOfElement.size() > 0) {
				lstOfElement.get(0).detach();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Build outMessage based on inputMessage and tempMessage
	 * 
	 * @param inMessage
	 * @param outMessage
	 * @throws MbException
	 */
	private void buildResponseMessage(final MbMessage inMessage, final MbMessage tempMessage, MbMessage outMessage) throws MbException {

		// 1.Copy tu input Message
		ElementUtils.setXMLElementValue(outMessage, "/?Messages/?Request", "");
		copyMessageContent(inMessage, outMessage, "/Data", "/Messages/Request");
		// 2.Copy tu output Message
		ElementUtils.setXMLElementValue(outMessage, "/?Messages/?Response", "");
		copyMessageContent(tempMessage, outMessage, "/Data", "/Messages/Response");

		// 3.Insert cac thanh phan khac
		ElementUtils.setXMLElementValue(outMessage, "/?Messages/?Control", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rcv_date[set-value('%s')]", this.rcv_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rcv_time[set-value('%s')]", this.rcv_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rsp_code[set-value('%s')]", this.rsp_code);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rsp_msg[set-value('%s')]", this.rsp_msg);

		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rcv_date[set-value('%s')]", this.rcv_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rcv_time[set-value('%s')]", this.rcv_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$req_date[set-value('%s')]", this.req_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$req_time[set-value('%s')]", this.req_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_date[set-value('%s')]", this.rsp_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_time[set-value('%s')]", this.rsp_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_code[set-value('%s')]", this.rsp_code);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_msg[set-value('%s')]", this.rsp_msg);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$in_message[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$out_message[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$tran_sn[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$tran_date[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$teller_id[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$approver_id[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_app_id[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_service[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_channel[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_port[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_tran_code[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_cif_acct[set-value('%s')]", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_amt[set-value('%s')]", "");

	}

	/**
	 * 
	 * Copy xml content from inputMessage to outputMessage
	 * 
	 * @param inMessage
	 * @param outMessage
	 * @throws MbException
	 */
	private void copyMessageContent(MbMessage inMessage, MbMessage outMessage, String fromXPath, String toXPath) throws MbException {
		MbElement inputElement = (MbElement) inMessage.getRootElement().getLastChild().getFirstChild();
		MbElement outputElement = (MbElement) ((List) outMessage.evaluateXPath(toXPath)).get(0);

		while ((inputElement != null)) {
			String name = inputElement.getName();
			String value = inputElement.getValueAsString();
			outputElement.addAsLastChild(inputElement.copy());
			inputElement = inputElement.getNextSibling();
		}
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void Assign(final MbMessage inMessage, MbMessage outMessage) {
		String userId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "Assign/userId");
		String sn = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "Assign/sn");
		req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		int iReturn = 0;
		iReturn = rsaAdminSrv.assignTokenToUser(userId, sn, true);
		this.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		this.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?Assign/?$returnCode[set-value('%s')]", iReturn);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?Assign/?$desc[set-value('%s')]", "");
		this.rsp_code = String.valueOf(iReturn);
		this.rsp_msg = "";
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void Unassign(final MbMessage inMessage, MbMessage outMessage) {
		String sn = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "Unassign/sn");
		req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		int iReturn = 0;
		iReturn = rsaAdminSrv.unassignToken(sn);
		this.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		this.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?Unassign/?$returnCode[set-value('%s')]", iReturn);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?Unassign/?$desc[set-value('%s')]", "");
		this.rsp_code = String.valueOf(iReturn);
		this.rsp_msg = "";
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void CreateUser(final MbMessage inMessage, MbMessage outMessage) {
		String tknSN = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "CreateUser/tknSN");
		String userId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "CreateUser/userId");
		String pwd = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "CreateUser/pwd");
		String fname = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "CreateUser/fname");
		String lname = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "CreateUser/lname");
		req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		int iReturn = 0;
		rsaAdminSrv = new RSAAdmin();
		iReturn = rsaAdminSrv.createUser(tknSN, userId, pwd, fname, lname);
		this.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		this.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?CreateUser/?$returnCode[set-value('%s')]", iReturn);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?CreateUser/?$desc[set-value('%s')]", "");
		this.rsp_code = String.valueOf(iReturn);
		this.rsp_msg = "";
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void deleteUser(final MbMessage inMessage, MbMessage outMessage) {
		int iReturn = 0;
		String userId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "deleteUser/userId");
		req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		iReturn = rsaAdminSrv.deleteUser(userId);
		this.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		this.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?deleteUser/?$returnCode[set-value('%s')]", iReturn);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?deleteUser/?$desc[set-value('%s')]", "");
		this.rsp_code = String.valueOf(iReturn);
		this.rsp_msg = "";
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void checkOTP(final MbMessage inMessage, MbMessage outMessage) {
		int iReturn = 0;
		String userId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "checkOTP/userId");
		String otp = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "checkOTP/otp");
		req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		rsaAuthenSrv = new AuthUser();
		iReturn = rsaAuthenSrv.auth(userId, otp);
		this.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		this.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?checkOTP/?$returnCode[set-value('%s')]", iReturn);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?checkOTP/?$desc[set-value('%s')]", "");
		this.rsp_code = String.valueOf(iReturn);
		this.rsp_msg = "";
	}
}
