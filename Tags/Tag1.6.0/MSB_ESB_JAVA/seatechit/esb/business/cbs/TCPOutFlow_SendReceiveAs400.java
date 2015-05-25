package seatechit.esb.business.cbs;

import java.util.ArrayList;
import java.util.List;

import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import seatechit.esb.utils.StringUtils;
import vn.com.msb.cnn.accounts.AS400Service;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbXMLNSC;
import org.apache.commons.codec.binary.Base64;

public class TCPOutFlow_SendReceiveAs400 extends MbJavaComputeNode {
	private AS400Service cbsService = new AS400Service();

	private class MessageInfo {
		public String rcv_date = "";
		public String rcv_time = "";
		public String req_date = "";
		public String req_time = "";
		public String rsp_date = "";
		public String rsp_time = "";
		public String rsp_code = "";
		public String rsp_msg = "";
		public String tran_sn = "";
		public String tran_date = "";
		public String teller_id = "";
		public String approver_id = "";
		public String ref_app_id = "";
		public String ref_service = "";
		public String ref_channel = "";
		public String ref_port = "";
		public String ref_tran_code = "";
		public String ref_cif_acct = "";
		public String ref_amt = "";
		public String hostRequestMsg = "";
		public String hostResponseMsg = "";

		public MessageInfo() {
		}

	}

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MessageInfo msgInf = new MessageInfo();
		// Ghi nhan thoi gian bat dau xy ly message
		msgInf.rcv_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rcv_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

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
		String rm_seq = "";
		String act_seq = "";
		String rm_interbank_seq = "";
		try {
			MbMessage localEnv = new MbMessage(inAssembly.getLocalEnvironment());

			String tranCode = ElementUtils.getXMLElementInStringValue(inMessage, "/Data/Header/srv");

			// Lay so rm_num,act_num tren bien moi truong
			if (tranCode.equals("CBS033") || tranCode.equals("CBS028")) {
				// gl transfer
				rm_seq = localEnv.getRootElement().getFirstElementByPath("/XMLNSC/rm_seq_list/RM_SEQ").getValueAsString();
				
			} else if (tranCode.equals("CBS039") || tranCode.equals("CBS049")) {
				// get fd acct
				act_seq = localEnv.getRootElement().getFirstElementByPath("/XMLNSC/act_seq_list/ACT_SEQ").getValueAsString();
			} else if(tranCode.equals("CBS058")) {
				rm_interbank_seq = localEnv.getRootElement().getFirstElementByPath("/XMLNSC/rm_seq_interbank_list/RM_SEQ_INTERBANK").getValueAsString();
			}

			// Get Paramlist from cache
			MbElement[] arrElm = localEnv.getRootElement().getAllElementsByPath("./XMLNSC/param_list");
			ArrayList<HostParameter> paramList = new ArrayList<HostParameter>();
			for (int i = 0; i < arrElm.length; i++) {
				String name = arrElm[i].getFirstElementByPath("./paramName") == null ? "" : arrElm[i].getFirstElementByPath("./paramName").getValueAsString();
				String value = arrElm[i].getFirstElementByPath("./paramValue") == null ? "" : arrElm[i].getFirstElementByPath("./paramValue").getValueAsString();
				String param1 = arrElm[i].getFirstElementByPath("./param1") == null ? "" : arrElm[i].getFirstElementByPath("./param1").getValueAsString();
				String param2 = arrElm[i].getFirstElementByPath("./param2") == null ? "" : arrElm[i].getFirstElementByPath("./param2").getValueAsString();
				paramList.add(new HostParameter(name, value, param1, param2));
			}
			cbsService.setParamList(paramList);
			// --end setting param

			if (tranCode.equalsIgnoreCase("CBS001")) {
				createHoldMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS002")) {
				createFD(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS003")) {
				lockUpdateMainternanceCA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS004")) {
				debitAdvice(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS005")) {
				linkMasterCard(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS006")) {
				HostMessageSending(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS007")) {
				lockUpdateE_Contact(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS008")) {
				changeCardService(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS009")) {
				createFDReceipt(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS010")) {
				transferFromCASAToCASA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS011")) {
				lockTransferAccountFromCifToAnother(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS012")) {
				createSA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS013")) {
				TFMessageSending(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS014")) {
				joinSA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS015")) {
				joinCA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS016")) {
				accountInquiry(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS017")) {
				markHotCard(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS018")) {
				getInformationByIDForIBSRegistration(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS019")) {
				searchCustomerById(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS020")) {
				creditAdvice(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS021")) {
				linkCard(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS022")) {
				reActiveCard(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS023")) {
				reversableTransaction(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS024")) {
				ddMasterInquiry(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS025")) {
				searchAccountMoreByCif(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS026")) {
				loanAccountInquiry(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS027")) {
				transferGLtoGL(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS028")) {
				transferFromCASAToGL(inMessage, tempMessage, msgInf, rm_seq);
			} else if (tranCode.equalsIgnoreCase("CBS029")) {
				updateE_Contact(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS030")) {
				updateMainternanceCA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS031")) {
				getInformationByCIFForIBSRegistration(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS032")) {
				createUnHoldMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS033")) {
				transferFromWUToCA(inMessage, tempMessage, msgInf, rm_seq);
			} else if (tranCode.equalsIgnoreCase("CBS034")) {
				miniStatement(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS035")) {
				searchCustomerByCif(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS036")) {
				addE_Contact(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS037")) {
				createCA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS038")) {
				reversableOL2Transaction(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS039")) {
				getAccount(inMessage, tempMessage, msgInf, act_seq);
			} else if (tranCode.equalsIgnoreCase("CBS040")) {
				updateMainternanceNewCA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS041")) {
				transferAccountFromCifToAnother(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS042")) {
				cardActivation(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS043")) {
				searchAccountByCif(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS044")) {
				createCif(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS045")) {
				lockUpdateMainternanceNewCA(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS046")) {
				createLockForUnHoldMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS047")) {
				searchE_ContactByCif(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS048")) {
				searchCustomerInformationByID(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS049")) {
				getFDReceiptAccount(inMessage, tempMessage, msgInf, act_seq);
			} else if (tranCode.equalsIgnoreCase("CBS050")) {
				createAddCifGroupMainternanceMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS051")) {
				createCifMainternanceMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS052")) {
				chargeCollection(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS053")) {
				rateScheduleMainternance(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS054")) {
				transferFromCASAToCASA247(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS055")) {
				createTranferSA2GLMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS056")) {
				createTranferGL2SaMessage(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS057")) {
				accountInquiry247(inMessage, tempMessage, msgInf);
			} else if (tranCode.equalsIgnoreCase("CBS058")) {
				//creditAdvice1(inMessage, tempMessage, msgInf);
				transferInterbank(inMessage, tempMessage, msgInf, rm_interbank_seq);
			} else if(tranCode.equalsIgnoreCase("CBS059")) {
				// tao thau chi tai khoan
				createODPTierMessage(inMessage, tempMessage, msgInf);				
			}
			
			buildResponseMessage(inMessage, tempMessage, outMessage, msgInf);
			out.propagate(outAssembly);
		} catch (Exception ex) {
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Build outMessage based on inputMessage and tempMessage
	 * 
	 * @param inMessage
	 * @param outMessage
	 * @throws MbException
	 */
	private void buildResponseMessage(final MbMessage inMessage, final MbMessage tempMessage, MbMessage outMessage, final MessageInfo msgInf) throws MbException {

		// 1.Copy tu input Message
		ElementUtils.setXMLElementValue(outMessage, "/?Messages/?Request", "");
		copyMessageContent(inMessage, outMessage, "/Data", "/Messages/Request");
		// 2.Copy tu output Message
		ElementUtils.setXMLElementValue(outMessage, "/?Messages/?Response", "");
		copyMessageContent(tempMessage, outMessage, "/Data", "/Messages/Response");

		// 3.Insert cac thanh phan khac
		ElementUtils.setXMLElementValue(outMessage, "/?Messages/?Control", "");
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rcv_date[set-value('%s')]", msgInf.rcv_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rcv_time[set-value('%s')]", msgInf.rcv_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rsp_code[set-value('%s')]", msgInf.rsp_code);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?tllog/?$rsp_msg[set-value('%s')]", msgInf.rsp_msg);
		// 4. Cho message log
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rcv_date[set-value('%s')]", msgInf.rcv_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rcv_time[set-value('%s')]", msgInf.rcv_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$req_date[set-value('%s')]", msgInf.req_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$req_time[set-value('%s')]", msgInf.req_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_date[set-value('%s')]", msgInf.rsp_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_time[set-value('%s')]", msgInf.rsp_time);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_code[set-value('%s')]", msgInf.rsp_code);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$rsp_msg[set-value('%s')]", msgInf.rsp_msg);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$in_message[set-value('%s')]", ElementUtils.ReplaceCorebankSpecChar(msgInf.hostRequestMsg));
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$out_message[set-value('%s')]", ElementUtils.ReplaceCorebankSpecChar(msgInf.hostResponseMsg));
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$tran_sn[set-value('%s')]", msgInf.tran_sn);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$tran_date[set-value('%s')]", msgInf.tran_date);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$teller_id[set-value('%s')]", msgInf.teller_id);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$approver_id[set-value('%s')]", msgInf.approver_id);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_app_id[set-value('%s')]", msgInf.ref_app_id);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_service[set-value('%s')]", msgInf.ref_service);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_channel[set-value('%s')]", msgInf.ref_channel);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_port[set-value('%s')]", msgInf.ref_port);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_tran_code[set-value('%s')]", msgInf.ref_tran_code);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_cif_acct[set-value('%s')]", msgInf.ref_cif_acct);
		ElementUtils.setXMLElementValue(outMessage, "/Messages/Control/?msglog/?$ref_amt[set-value('%s')]", msgInf.ref_amt);

	}

	/**
	 * Build response message from return object of core (Message)
	 * 
	 * @param outMessage
	 * @param xmlPath
	 * @param retMessage
	 */
	private void buildMsgFromReturnObject(MbMessage outMessage, String xmlPath, final Messages retMessage, final MessageInfo msgInf) {
		xmlPath = xmlPath.replace("/", "/?");
		if (retMessage != null) {
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$description[set-value('%s')]", retMessage.getDescription());
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$errCode[set-value('%s')]", retMessage.getErrCode());
			setArrStringUsingCDATA(outMessage, "arrString", StringUtils.arrayToString(retMessage.getArrString()));
			msgInf.rsp_code = retMessage.getErrCode();
			msgInf.rsp_msg = retMessage.getDescription();
		} else {
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$description[set-value('%s')]", "System Error");
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$errCode[set-value('%s')]", "-1");
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$arrString[set-value('%s')]", "");
			msgInf.rsp_code = "-1";
			msgInf.rsp_msg = "System Error";

		}
	}

	private void setArrStringUsingCDATA(MbMessage outMessage, String tagName, String arrStringWithCDATA) {
		MbElement outBody;
		try {
			outBody = outMessage.getRootElement().getLastChild();
			outBody.getLastChild().getLastChild().getLastChild().getLastChild().getLastChild().createElementAsLastChild(MbXMLNSC.CDATA_FIELD, "arrString", arrStringWithCDATA);
		} catch (MbException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Copy xml content from inputMessage to outputMessage
	 * 
	 * @param inMessage
	 * @param outMessage
	 * @throws MbException
	 */
	private void copyMessageContent(final MbMessage inMessage, MbMessage outMessage, final String fromXPath, final String toXPath) throws MbException {
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
	 * Remove <Element> section from message
	 * 
	 * @param outMessage
	 *            the ouput message
	 * @throws MbException
	 */
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
	 * 
	 * @param inMessage
	 * @param tempMessage
	 */
	private void transferFromCASAToGL(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf, String rm_seq) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/channel");
		String hostIP = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/hostIP");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/hostName");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/manager");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/sequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/transDate");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/branchCode");
		String fromAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/fromAccount");
		String amount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/amount");
		String fromCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/fromCif");
		String GLAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/GLAccount");
		String vatFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/vatFee");
		String serviceFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/serviceFee");
		String remarks = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/remarks");
		String fromName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/fromName");
		String fromId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/fromId");
		String toAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/toAccount");
		String toName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/toName");
		String toId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/toId");
		String toAddress = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/toAddress");
		String toIdIssueDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/toIdIssueDate");
		String toIdIssuePlace = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/toIdIssuePlace");
		String transCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToGL/transCode");
				
		
//		if(rm_interbank_seq != null) {			
//			rm_seq = AppUtils.subBranch(branchCode) + "2" + AppUtils.now("yyMMdd") + "8" + AppUtils.padLeft(rm_interbank_seq, 4, '0');
//		}
		
		
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = null;
		if (rm_seq != null) {			
			retMessage = cbsService.transferFromCASAToGL(channel, hostIP, hostName, teller, manager, sequence, transDate, branchCode, fromAccount, amount, fromCif, GLAccount,
					vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, transCode, rm_seq);
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "transferFromCASAToGL/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = transCode;
		msgInf.ref_cif_acct = fromAccount;
		msgInf.ref_amt = amount;
	}
	
	/**
	 * 
	 * @param inMessage
	 * @param tempMessage
	 */
	private void transferInterbank(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf, String rm_seq) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/channel");
		String hostIP = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/hostIP");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/hostName");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/manager");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/sequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/transDate");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/branchCode");
		String fromAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/fromAccount");
		String amount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/amount");
		String fromCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/fromCif");
		String GLAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/GLAccount");
		String vatFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/vatFee");
		String serviceFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/serviceFee");
		String remarks = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/remarks");
		String fromName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/fromName");
		String fromId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/fromId");
		String toAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toAccount");
		String toName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toName");
//		String toId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toId");
		String toBranchId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toBranchId");
		String toBranchName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toBranchName");		
		String toAddress = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toAddress");
		String toIdIssueDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toIdIssueDate");
		String toIdIssuePlace = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/toIdIssuePlace");
		String transCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferInterBank/transCode");
		String toId = "";
		
		// kiem tra transcode la chung khoan lien ngan hang thi thay doi lai so RM
		
		rm_seq = AppUtils.subBranch(branchCode) + "2" + AppUtils.now("yyMMdd") + "8" + AppUtils.padLeft(rm_seq, 4, '0');				
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = null;
		if (rm_seq != null &&!"".equals(rm_seq)) {			
			retMessage = cbsService.transferInterBank(channel, hostIP, hostName, teller, manager, sequence, transDate, branchCode, fromAccount, amount, fromCif, GLAccount, 
					vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toBranchId, toBranchName, toAddress, toIdIssueDate, toIdIssuePlace, transCode, rm_seq);
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "transferInterBank/out/", retMessage, msgInf);
//		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?transferInterBank/?out/?$resp[set-value('%s')]", "nothing");
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = transCode;
		msgInf.ref_cif_acct = fromAccount;
		msgInf.ref_amt = amount;
	}
	
	private void createODPTierMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/hostName");		
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/branchCode");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/accountNumber");
		String accountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/accountType");
		String drawLimit = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/drawLimit");
		String authLimit = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/authLimit");
		String odRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/odRate");
		String argDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/argDate");
		String expDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/expDate");
		
		String rateVariance = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/rateVariance");
		String rateCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/rateCode");
		String rateFloor = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/rateFloor");
		String rateCeiling = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "maintainODPTier/rateCeiling");
		
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createODPTierMessage(channel, teller, hostName, branchCode, accountNumber, accountType, drawLimit, 
				authLimit, odRate, argDate, expDate, rateVariance, rateCode, rateFloor, rateCeiling);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "maintainODPTier/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		
		
	}

	private void getAccount(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf, String act_seq) {
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getAccount/branchCode");
		String currencyCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getAccount/currencyCode");
		String groupCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getAccount/groupCode");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		String retAccount = null;
		if (act_seq != null) {
			retAccount = cbsService.getAccount(act_seq, branchCode, currencyCode, groupCode);
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?getAccount/?out/?$accountNumber[set-value('%s')]", retAccount == null ? "" : retAccount);
		msgInf.rsp_code = "0";// retMessage.getErrCode();
		msgInf.rsp_msg = "SystemOK";// retMessage.getDescription();
	}

	private void getFDReceiptAccount(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf, String act_seq) {
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getFDReceiptAccount/branchCode");
		String currencyCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getFDReceiptAccount/currencyCode");
		String groupCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getFDReceiptAccount/groupCode");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		String retAccount = null;
		if (act_seq != null) {
			retAccount = cbsService.getFDReceiptAccount(act_seq, branchCode, currencyCode, groupCode);
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?getFDReceiptAccount/?out/?$accountNumber[set-value('%s')]", retAccount == null ? "" : retAccount);
		msgInf.rsp_code = "0";// retMessage.getErrCode();
		msgInf.rsp_msg = "SystemOK";// retMessage.getDescription();
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		try {
			String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/channel");
			String hostDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/hostDate");
			String hostId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/hostId");
			String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/branchCode");
			String currencyType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/currencyType");
			String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/cifNumber");
			String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/accountNumber");
			String accountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/accountType");
			String accountName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/accountName");
			String modeOfOperation = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCA/modeOfOperation");
			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			Messages retMessage = cbsService.createCA(channel, hostDate, hostId, branchCode, currencyType, cifNumber, accountNumber, accountType, accountName, modeOfOperation);
			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createCA/out/", retMessage, msgInf);
			msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
			msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
			msgInf.tran_sn = "";
			msgInf.tran_date = hostDate;
			msgInf.teller_id = "";
			msgInf.approver_id = "";
			msgInf.ref_app_id = "";
			msgInf.ref_service = "";
			msgInf.ref_channel = channel;
			msgInf.ref_port = "";
			msgInf.ref_tran_code = "";
			msgInf.ref_cif_acct = accountNumber;
			msgInf.ref_amt = "";
		} catch (Throwable ex) {
		}

	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createSA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/channel");
		String hostDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/hostDate");
		String hostId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/hostId");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/branchCode");
		String currencyType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/currencyType");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/cifNumber");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/accountNumber");
		String accountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/accountType");
		String accountName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/accountName");
		String modeOfOperation = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createSA/modeOfOperation");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createSA(channel, hostDate, hostId, branchCode, currencyType, cifNumber, accountNumber, accountType, accountName, modeOfOperation);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createSA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = hostDate;
		msgInf.ref_cif_acct = accountNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createFD(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/channel");
		String hostDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/hostDate");
		String hostId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/hostId");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/branchCode");
		String currencyType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/currencyType");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/cifNumber");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/accountNumber");
		String accountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/accountType");
		String accountName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/accountName");
		String modeOfOperation = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFD/modeOfOperation");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createFD(channel, hostDate, hostId, branchCode, currencyType, cifNumber, accountNumber, accountType, accountName, modeOfOperation);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createFD/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = hostDate;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void transferFromCASAToCASA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/channel");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/branchCode");
		String creditAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditAccount");
		String creditAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditAmount");
		String creditCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditCurrency");
		String creditRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditRate");
		String debitAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitAccount");
		String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitAmount");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitCurrency");
		String debitRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitRate");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/manager");
		String description = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/description");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/sequence");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/teller");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/transDate");
		String transCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/transCode");
		String vatFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/vatFee");
		String serviceFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/serviceFee");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.transferFromCASAToCASA(channel, branchCode, creditAccount, creditAmount, creditCurrency, creditRate, debitAccount, debitAmount,
				debitCurrency, debitRate, manager, description, sequence, teller, transDate, transCode, vatFee, serviceFee);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "transferFromCASAToCASA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = transCode;
		msgInf.ref_cif_acct = debitAccount;
		msgInf.ref_amt = debitAmount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void transferFromCASAToCASA247(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/channel");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/branchCode");
		String creditAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditAccount");
		String creditAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditAmount");
		String creditCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditCurrency");
		String creditRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/creditRate");
		String debitAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitAccount");
		String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitAmount");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitCurrency");
		String debitRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/debitRate");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/manager");
		String description = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/description");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/sequence");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/teller");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/transDate");
		String transCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/transCode");
		String vatFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/vatFee");
		String serviceFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromCASAToCASA/serviceFee");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.transferFromCASAToCASA247(channel, branchCode, creditAccount, creditAmount, creditCurrency, creditRate, debitAccount, debitAmount,
				debitCurrency, debitRate, manager, description, sequence, teller, transDate, transCode, vatFee, serviceFee);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		// endcode 64 tangvh
		if(retMessage != null) {	
			String[] newArr = new String[retMessage.getArrString().length];
			int index = 0;
			byte[] encodedBytes;
			String resp = "";
			for(String item : retMessage.getArrString() ) {
				
				try {
					if(item == null) {
						newArr[index] = "";
						break;
					}
					resp = resp + item + "|";
					newArr[index] = item;
//					encodedBytes = Base64.encodeBase64(item.getBytes());
//					newArr[index] = encodedBytes.toString();
				} catch(Exception ex) {
					newArr[index] = "index:" + String.valueOf(index);
				}
				
				index ++;
			}	
			retMessage.setResponseMsg(resp);
			retMessage.setArrString(newArr);
		}
		
		
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "transferFromCASAToCASA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		
		
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = transCode;
		msgInf.ref_cif_acct = debitAccount;
		msgInf.ref_amt = debitAmount;
	}
	
	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void joinCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/channel");
		String strteller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/strteller");
		String strBankcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/strBankcode");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/transDate");
		String strAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/strAccount");
		String strCifnumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/strCifnumber");
		String strNameAcount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/strNameAcount");
		String strcurrCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinCA/strcurrCode");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.joinCA(channel, strteller, strBankcode, transDate, strAccount, strCifnumber, strNameAcount, strcurrCode);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "joinCA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = transDate;
		msgInf.teller_id = strteller;
		msgInf.approver_id = strteller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strAccount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void joinSA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/channel");
		String strteller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/strteller");
		String strBankcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/strBankcode");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/transDate");
		String strAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/strAccount");
		String strCifnumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/strCifnumber");
		String strNameAcount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/strNameAcount");
		String strcurrCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "joinSA/strcurrCode");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.joinSA(channel, strteller, strBankcode, transDate, strAccount, strCifnumber, strNameAcount, strcurrCode);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "joinSA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = transDate;
		msgInf.teller_id = strteller;
		msgInf.approver_id = strteller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strAccount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void searchCustomerByCif(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerByCif/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerByCif/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerByCif/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerByCif/branchCode");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerByCif/cifNumber");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.searchCustomerByCif(channel, teller, hostName, branchCode, cifNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "searchCustomerByCif/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void searchCustomerById(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerById/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerById/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerById/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerById/branchCode");
		String idNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerById/idNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.searchCustomerById(channel, teller, hostName, branchCode, idNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "searchCustomerById/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = idNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void searchE_ContactByCif(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchE_ContactByCif/channel");
		String strteller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchE_ContactByCif/teller");
		String strBankcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchE_ContactByCif/bankCode");
		String strsearch_key = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchE_ContactByCif/cifNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.searchE_ContactByCif(channel, strteller, strBankcode, strsearch_key);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "searchE_ContactByCif/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = strteller;
		msgInf.approver_id = strteller;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void searchAccountByCif(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountByCif/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountByCif/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountByCif/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountByCif/branchCode");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountByCif/cifNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.searchAccountByCif(channel, teller, hostName, branchCode, cifNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "searchAccountByCif/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void searchAccountMoreByCif(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/branchCode");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/cifNumber");
		String lastAccountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/lastAccountType");
		String lastAccountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/lastAccountNumber");
		String relationShip = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchAccountMoreByCif/relationShip");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.searchAccountMoreByCif(channel, teller, hostName, branchCode, cifNumber, lastAccountType, lastAccountNumber, relationShip);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "searchAccountMoreByCif/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void searchCustomerInformationByID(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerInformationByID/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerInformationByID/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerInformationByID/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerInformationByID/branchCode");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "searchCustomerInformationByID/cifNumber");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.searchCustomerInformationByID(channel, teller, hostName, branchCode, cifNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "searchCustomerInformationByID/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void lockTransferAccountFromCifToAnother(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockTransferAccountFromCifToAnother/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockTransferAccountFromCifToAnother/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockTransferAccountFromCifToAnother/branchCode");
		String sourceCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockTransferAccountFromCifToAnother/sourceCif");
		String accountToMove = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockTransferAccountFromCifToAnother/accountToMove");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.lockTransferAccountFromCifToAnother(channel, teller, branchCode, sourceCif, accountToMove);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "lockTransferAccountFromCifToAnother/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = sourceCif;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void transferAccountFromCifToAnother(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/branchCode");
		String sourceCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/sourceCif");
		String accountToMove = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/accountToMove");
		String destinationCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/destinationCif");
		String destinationName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferAccountFromCifToAnother/destinationName");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.transferAccountFromCifToAnother(channel, teller, branchCode, sourceCif, accountToMove, destinationCif, destinationName);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "transferAccountFromCifToAnother/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = sourceCif;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void lockUpdateE_Contact(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateE_Contact/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateE_Contact/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateE_Contact/bankCode");
		String strCifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateE_Contact/cifNumber");
		String sequenceNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateE_Contact/sequenceNo");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.lockUpdateE_Contact(channel, teller, branchCode, strCifNumber, sequenceNo);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "lockUpdateE_Contact/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequenceNo;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strCifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void updateE_Contact(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/bankCode");
		String strCifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/cifNumber");
		String sequenceNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/sequenceNo");
		String strEcontactType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/eContactType");
		String strNewEcontact = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateE_Contact/newEcontact");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.updateE_Contact(channel, teller, branchCode, strCifNumber, sequenceNo, strEcontactType, strNewEcontact);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "updateE_Contact/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequenceNo;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strCifNumber;

	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void addE_Contact(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "addE_Contact/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "addE_Contact/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "addE_Contact/bankCode");
		String strCifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "addE_Contact/cifNumber");
		String strEcontactType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "addE_Contact/eContactType");
		String strEcontact = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "addE_Contact/newEcontact");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.addE_Contact(channel, teller, branchCode, strCifNumber, strEcontactType, strEcontact);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "addE_Contact/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strCifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createCif(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/bankCode");
		String strIdNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/IdNumber");
		String strIdType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/IdType");
		String strIdDateIssued = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/IdDateIssued");
		String strIdCountryIssued = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/IdCountryIssued");
		String strIdPlaceIssued = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/IdPlaceIssued");
		String strSurname = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/strSurname");
		String strAfterSurname = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/afterSurname");
		String strAddressLine1 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/addressLine1");
		String strAddressLine2 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/addressLine2");
		String strAddressLine3 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/addressLine3");
		String strAddressLine4_codeCheckCust = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/addressLine4");
		String strAddressLine5 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/addressLine5");
		String strNationality = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/nationality");
		String strBirthday = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/birthday");
		String strRaceCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/raceCode");
		String strMaritalStatus = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/maritalStatus");
		String strGender = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/gender");
		String _2strOccupationCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/occupationCode_2");
		String strPlaceOfBirth = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/placeOfBirth");
		String strTypeElectronicAddress1 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/typeElectronicAddress1");
		String strTypeElectronicAddress2 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/typeElectronicAddress2");
		String strTypeElectronicAddress3 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/typeElectronicAddress3");
		String strElectronicAddress1 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/electronicAddress1");
		String strElectronicAddress2 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/electronicAddress2");
		String strElectronicAddress3 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/electronicAddress3");
		String hostDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/hostDate");
		String strBirthday_Full = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/birthday_Full");
		String strIdDateIssued_Full = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/idDateIssued_Full");
		String _1strOccupationCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/strOccupationCode_1");
		String strProvince = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCif/strProvince");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createCif(channel, teller, hostName, branchCode, strIdNo, strIdType, strIdDateIssued, strIdCountryIssued, strIdPlaceIssued, strSurname,
				strAfterSurname, strAddressLine1, strAddressLine2, strAddressLine3, strAddressLine4_codeCheckCust, strAddressLine5, strNationality, strBirthday, strRaceCode,
				strMaritalStatus, strGender, _2strOccupationCode, strPlaceOfBirth, strTypeElectronicAddress1, strTypeElectronicAddress2, strTypeElectronicAddress3,
				strElectronicAddress1, strElectronicAddress2, strElectronicAddress3, hostDate, strBirthday_Full, strIdDateIssued_Full, _1strOccupationCode, strProvince);

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createCif/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = hostDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strIdNo;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void miniStatement(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "miniStatement/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "miniStatement/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "miniStatement/branchCode");
		String recordNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "miniStatement/recordNumber");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "miniStatement/accountNumber");
		String accountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "miniStatement/accountType");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.miniStatement(channel, teller, branchCode, recordNumber, accountNumber, accountType);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "miniStatement/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void ddMasterInquiry(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "ddMasterInquiry/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "ddMasterInquiry/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "ddMasterInquiry/hostName");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "ddMasterInquiry/branchCode");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "ddMasterInquiry/accountNumber");
		String accountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "ddMasterInquiry/accountType");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.ddMasterInquiry(channel, teller, hostName, branchCode, accountNumber, accountType);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "ddMasterInquiry/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void linkCard(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/bankCode");
		String strCardNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/cardNumber");
		String strAccountTag = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/accountTag");
		String strAccountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/accountType");
		String strUsage = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/usage");
		String strCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/currency");
		String strBranchTag = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/branchTag");
		String strCIFNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/cifNumber");
		String strIDNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/idNumber");
		String strIDType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/idType");
		String strName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/name");
		String strAddress = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/address");
		String strProduct = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/product");
		String strAnnualFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/annualFee");
		String strVIP = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/vip");
		String strAddressLine2 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/addressLine2");
		String strAddressLine3 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/addressLine3");
		String strAddressLine4 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkCard/addressLine4");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.linkCard(channel, teller, branchCode, strCardNumber, strAccountTag, strAccountType, strUsage, strCurrency, strBranchTag, strCIFNo,
				strIDNumber, strIDType, strName, strAddress, strProduct, strAnnualFee, strVIP, strAddressLine2, strAddressLine3, strAddressLine4);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "linkCard/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strCIFNo;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void linkMasterCard(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/bankCode");
		String strAccountTag = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/accountTag");
		String strAccountType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/accountType");
		String strUsage = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/usage");
		String strCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/currency");
		String strBranchTag = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/branchTag");
		String strCIFNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/cifNumber");
		String strIDNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/idNumber");
		String strIDType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/idType");
		String strName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/name");
		String strAddress = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/address");
		String strProduct = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/product");
		String strAnnualFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/annualFee");
		String strVIP = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/vip");
		String strAddressLine2 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/addressLine2");
		String strAddressLine3 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/addressLine3");
		String strAddressLine4 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "linkMasterCard/addressLine4");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.linkMasterCard(channel, teller, branchCode, strAccountTag, strAccountType, strUsage, strCurrency, strBranchTag, strCIFNo, strIDNumber,
				strIDType, strName, strAddress, strProduct, strAnnualFee, strVIP, strAddressLine2, strAddressLine3, strAddressLine4);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "linkMasterCard/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = strCIFNo;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void cardActivation(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/branchCode");
		String cardNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/cardNumber");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/cifNumber");
		String idNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/idNo");
		String idType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "cardActivation/idType");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.cardActivation(channel, teller, branchCode, cardNumber, cifNumber, idNo, idType);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "cardActivation/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void lockUpdateMainternanceNewCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceNewCA/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceNewCA/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceNewCA/bankCode");
		String accountNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceNewCA/accountNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.lockUpdateMainternanceNewCA(channel, teller, branchCode, accountNo);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "lockUpdateMainternanceNewCA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNo;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void updateMainternanceNewCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/bankCode");
		String accountNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/accountNumber");
		String introducerCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/introducerCode");
		String expense = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/expense");
		String accountName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceNewCA/accountName");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.updateMainternanceNewCA(channel, teller, branchCode, accountNo, introducerCode, expense, accountName);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "updateMainternanceNewCA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNo;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void lockUpdateMainternanceCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceCA/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceCA/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceCA/bankCode");
		String accountNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "lockUpdateMainternanceCA/accountNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.lockUpdateMainternanceCA(channel, teller, branchCode, accountNo);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "lockUpdateMainternanceCA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNo;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void updateMainternanceCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/bankCode");
		String accountNo = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/accountNumber");
		String introducerCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/introducerCode");
		String expense = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/expense");
		String accountName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "updateMainternanceCA/accountName");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.updateMainternanceCA(channel, teller, branchCode, accountNo, introducerCode, expense, accountName);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "updateMainternanceCA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountName;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void HostMessageSending(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "HostMessageSending/channel");
		String message = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "HostMessageSending/message");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.HostMessageSending(channel, message);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "HostMessageSending/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void TFMessageSending(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "TFMessageSending/channel");
		String message = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "TFMessageSending/message");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.TFMessageSending(channel, message);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "TFMessageSending/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void getInformationByIDForIBSRegistration(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByIDForIBSRegistration/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByIDForIBSRegistration/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByIDForIBSRegistration/hostName");
		String bankCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByIDForIBSRegistration/bankCode");
		String cert_code = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByIDForIBSRegistration/cert_code");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.getInformationByIDForIBSRegistration(channel, teller, hostName, bankCode, cert_code);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "getInformationByIDForIBSRegistration/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cert_code;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void getInformationByCIFForIBSRegistration(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByCIFForIBSRegistration/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByCIFForIBSRegistration/teller");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByCIFForIBSRegistration/hostName");
		String bankCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByCIFForIBSRegistration/bankCode");
		String cif_no = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "getInformationByCIFForIBSRegistration/cif_no");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.getInformationByCIFForIBSRegistration(channel, teller, hostName, bankCode, cif_no);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "getInformationByCIFForIBSRegistration/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cif_no;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void reversableTransaction(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/manager");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/branchCode");
		String strFromAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/strFromAccount");
		String strToAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/strToAccount");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/transDate");
		String strDescription = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/strDescription");
		String strTranferAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/strTranferAmount");
		String strJournalSeq = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/strJournalSeq");
		String orgJournalSeq = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/orgJournalSeq");
		String strTransCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableTransaction/strTransCode");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.reversableTransaction(channel, teller, manager, branchCode, strFromAccount, strToAccount, transDate, strDescription, strTranferAmount,
				strJournalSeq, orgJournalSeq, strTransCode);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "reversableTransaction/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = strTransCode;
		msgInf.ref_cif_acct = strFromAccount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void reversableOL2Transaction(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/channel");
		String hostIP = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/hostIP");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/hostName");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/manager");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/sequence");
		String oldSequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/oldSequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/transDate");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/branchCode");
		String fromAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/fromAccount");
		String amount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/amount");
		String amountMustPay = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/amountMustPay");
		String fromCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/fromCif");
		String GLAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/GLAccount");
		String vatFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/vatFee");
		String serviceFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/serviceFee");
		String remarks = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/remarks");
		String fromName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/fromName");
		String fromId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/fromId");
		String toAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/toAccount");
		String toName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/toName");
		String toId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/toId");
		String toAddress = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/toAddress");
		String toIdIssueDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/toIdIssueDate");
		String toIdIssuePlace = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/toIdIssuePlace");
		String strTransCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/strTransCode");
		String refId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reversableOL2Transaction/refId");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.reversableOL2Transaction(channel, hostIP, hostName, teller, manager, sequence, oldSequence, transDate, branchCode, fromAccount, amount,
				amountMustPay, fromCif, GLAccount, vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, strTransCode,
				refId);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "reversableOL2Transaction/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = strTransCode;
		msgInf.ref_cif_acct = fromAccount;
		msgInf.ref_amt = amount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createFDReceipt(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/branchCode");
		String journalSeq = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/journalSeq");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/transDate");
		String fdGroupAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/fdGroupAccount");
		String depositAmt = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/depositAmt");
		String effectiveDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/effectiveDate");
		String fdReceipt = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/fdReceipt");
		String rate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/rate");
		String intPaymentToAcctno = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/intPaymentToAcctno");
		String printTranferToAcctno = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/printTranferToAcctno");
		String currency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/currency");
		String productCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/productCode");
		String autoRenew = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/autoRenew");
		String intPaymentMode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/intPaymentMode");
		String fdType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/fdType");
		String remark = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createFDReceipt/remark");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createFDReceipt(channel, teller, branchCode, journalSeq, transDate, fdGroupAccount, depositAmt, effectiveDate, fdReceipt, rate,
				intPaymentToAcctno, printTranferToAcctno, currency, productCode, autoRenew, intPaymentMode, fdType, remark);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createFDReceipt/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = journalSeq;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = fdReceipt;
		msgInf.ref_amt = depositAmt;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void loanAccountInquiry(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "loanAccountInquiry/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "loanAccountInquiry/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "loanAccountInquiry/branchCode");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "loanAccountInquiry/hostName");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "loanAccountInquiry/accountNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.loanAccountInquiry(channel, teller, branchCode, hostName, accountNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "loanAccountInquiry/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void accountInquiry(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/teller");
		String account = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/account");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/transDate");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.accountInquiry(channel, teller, account, transDate);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "accountInquiry/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = account;
	}
	
	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void accountInquiry247(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/teller");
		String account = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/account");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "accountInquiry/transDate");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		String transCode = "1080";// fix cung
		String strJournalSeq = "1"; // fix
		Messages retMessage = cbsService.accountInquiry247(channel, teller, account, transDate, transCode, strJournalSeq);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "accountInquiry/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = account;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void debitAdvice(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/branchCode");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/sequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/transDate");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/manager");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/accountNumber");
		String effectiveDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/effectiveDate");
		String crAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/crAmount");
		String glAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/glAccount");
		String drAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/drAmount");
		String buyRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/buyRate");
		String sellRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/sellRate");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/debitCurrency");
		String glCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/glCurrency");
		String remark = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/remark");
		String transcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "debitAdvice/transcode");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.debitAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount,
				buyRate, sellRate, debitCurrency, glCurrency, remark, transcode);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "debitAdvice/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
		msgInf.ref_amt = crAmount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void creditAdvice(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/branchCode");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/sequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/transDate");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/manager");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/accountNumber");
		String effectiveDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/effectiveDate");
		String crAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/crAmount");
		String glAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/glAccount");
		String drAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/drAmount");
		String buyRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/buyRate");
		String sellRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/sellRate");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/debitCurrency");
		String glCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/glCurrency");
		String remark = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/remark");
		String transcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/transcode");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		String override = "N";
		Messages retMessage = cbsService.creditAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount,
				buyRate, sellRate, debitCurrency, glCurrency, remark, transcode, override);
		
		if("106".equals(retMessage.getErrCode())) {
			override = "Y";
			 retMessage = cbsService.creditAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount,
						buyRate, sellRate, debitCurrency, glCurrency, remark, transcode, override);
			
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "creditAdvice/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
		msgInf.ref_amt = crAmount;
	}
	
	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void creditAdvice1(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/branchCode");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/sequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/transDate");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/manager");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/accountNumber");
		String effectiveDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/effectiveDate");
		String crAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/crAmount");
		String glAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/glAccount");
		String drAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/drAmount");
		String buyRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/buyRate");
		String sellRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/sellRate");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/debitCurrency");
		String glCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/glCurrency");
		String remark = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/remark");
		String transcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "creditAdvice/transcode");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		String override = "N";
		Messages retMessage = cbsService.creditAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount,
				buyRate, sellRate, debitCurrency, glCurrency, remark, transcode, override);
		
		if("106".equals(retMessage.getErrCode())) {
			override = "Y";
			 retMessage = cbsService.creditAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount,
						buyRate, sellRate, debitCurrency, glCurrency, remark, transcode, override);
			
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "creditAdvice/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = accountNumber;
		msgInf.ref_amt = crAmount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void transferGLtoGL(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/channel");
		String refID = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/refID");
		String debitGLBranch = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/debitGLBranch");
		String debitGLAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/debitGLAccount");
		String creditGLBranch = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/creditGLBranch");
		String creditGLAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/creditGLAccount");
		String amount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/amount");
		String currency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/currency");
		String comments = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/comments");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/manager");
		String hostDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/hostDate");
		String reconcileGL = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/reconcileGL");
		String transactionOffice = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/transactionOffice");
		String customerTypeCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/customerTypeCode");
		String account = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/account");
		String businessDeptCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/businessDeptCode");
		String branchCode5 = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferGLtoGL/branchCode5");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		int retValue = cbsService.transferGLtoGL(channel, refID, debitGLBranch, debitGLAccount, creditGLBranch, creditGLAccount, amount, currency, comments, teller, manager,
				hostDate, reconcileGL, transactionOffice, customerTypeCode, account, businessDeptCode, branchCode5);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		ElementUtils.setXMLElementValue(outMessage, "/?Data/?Body/?transferGLtoGL/?out/?$retValue[set-value('%s')]", retValue);
		msgInf.rsp_code = "0";// retMessage.getErrCode();
		msgInf.rsp_msg = "SystemOK";// retMessage.getDescription();
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void changeCardService(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "changeCardService/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "changeCardService/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "changeCardService/branchCode");
		String cardNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "changeCardService/cardNumber");
		String serviceName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "changeCardService/serviceName");
		String cifNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "changeCardService/cifNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.changeCardService(channel, teller, branchCode, cardNumber, serviceName, cifNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "changeCardService/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void markHotCard(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "markHotCard/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "markHotCard/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "markHotCard/branchCode");
		String cardNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "markHotCard/cardNumber");
		String typeLock = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "markHotCard/typeLock");
		String comment = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "markHotCard/comment");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.markHotCard(channel, teller, branchCode, cardNumber, typeLock, comment);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "markHotCard/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cardNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void reActiveCard(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reActiveCard/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reActiveCard/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reActiveCard/branchCode");
		String cardNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "reActiveCard/cardNumber");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.reActiveCard(channel, teller, branchCode, cardNumber);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "reActiveCard/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cardNumber;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createHoldMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/teller");
		String account = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/account");
		String expiryDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/expiryDate");
		String description = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/description");
		String amount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/amount");
		String code = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createHoldMessage/code");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createHoldMessage(channel, teller, account, expiryDate, description, amount, code);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createHoldMessage/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = account;
		msgInf.ref_amt = amount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createLockForUnHoldMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createLockForUnHoldMessage/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createLockForUnHoldMessage/teller");
		String account = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createLockForUnHoldMessage/account");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createLockForUnHoldMessage/sequence");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createLockForUnHoldMessage(channel, teller, account, sequence);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createLockForUnHoldMessage/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = account;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void chargeCollection(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {

		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/branchCode");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/transDate");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/sequence");
		String glAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/glAccount");
		String totalNetCharges = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/totalNetCharges");
		String baseCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/baseCurrency");
		String glCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/glCurrency");
		String customerCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/customerCurrency");
		String accountNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/accountNumber");
		String drTotalNetCharge = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/drTotalNetCharge");
		String totalCharge = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/totalCharge");
		String vat = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/vat");
		String remark = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/remark");
		String transcode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "chargeCollection/transcode");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.chargeCollection(channel, teller, branchCode, transDate, sequence, glAccount, totalNetCharges, baseCurrency, glCurrency, customerCurrency,
				accountNumber, drTotalNetCharge, totalCharge, vat, remark, transcode);

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "chargeCollection/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = transcode;
		msgInf.ref_cif_acct = accountNumber;
		msgInf.ref_amt = totalNetCharges;

	}

	private void createCifMainternanceMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String cifObj = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createCifMainternanceMessage/cifObj");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.changeCIFInformationMessage(cifObj);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createCifMainternanceMessage/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createUnHoldMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/teller");
		String account = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/account");
		String description = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/description");
		String amount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/amount");
		String code = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/code");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/sequence");
		String actionCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/actionCode");
		String expireDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createUnHoldMessage/expireDate");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createUnHoldMessage(channel, teller, account, description, amount, code, sequence, actionCode, expireDate);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createUnHoldMessage/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = account;
		msgInf.ref_amt = amount;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void createAddCifGroupMainternanceMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/branchCode");
		String cifCorp = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/cifCorp");
		String seq = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/seq");
		String cifEmp = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/cifEmp");
		String relationalEmpLev = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/relationalEmpLev");
		String relationalType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/relationalType");
		String relationalCorpLev = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createAddCifGroupMainternance/relationalCorpLev");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createAddCifGroupMainternanceMessage(channel, teller, branchCode, cifCorp, seq, cifEmp, relationalEmpLev, relationalType,
				relationalCorpLev);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createAddCifGroupMainternance/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = seq;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_cif_acct = cifEmp;
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void transferFromWUToCA(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf, String rm_seq) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/channel");
		String hostIP = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/hostIP");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/hostName");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/manager");
		String sequence = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/sequence");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/transDate");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/branchCode");
		String toAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toAccount");
		String crAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/crAmount");
		String ttAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/ttAmount");
		String toCif = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toCif");
		String GLAccount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/WUAccount");
		String vatFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/vatFee");
		String serviceFee = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/serviceFee");
		String buyRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/buyRate");
		String sendRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/sendRate");
		String ttSendRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/ttSendRate");
		String remarks = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/remarks");
		String toName = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toName");
		String toId = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toId");
		String toAddress = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toAddress");
		String toIdIssueDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toIdIssueDate");
		String toIdIssuePlace = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/toIdIssuePlace");
		String transCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/transCode");
		String refWU = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/refWU");
		String sendingCurrencyType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/sendingCurrencyType");
		String receivingCurrencyType = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "transferFromWUToCA/receivingCurrencyType");
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.transferFromWUToCA(channel, hostIP, hostName, teller, manager, sequence, transDate, branchCode, toAccount, crAmount, ttAmount, toCif,
				GLAccount, vatFee, serviceFee, buyRate, sendRate, ttSendRate, remarks, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, transCode, refWU,
				sendingCurrencyType, receivingCurrencyType, rm_seq);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "transferFromWUToCA/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.tran_sn = sequence;
		msgInf.tran_date = transDate;
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		msgInf.ref_tran_code = transCode;
		msgInf.ref_cif_acct = GLAccount;
		msgInf.ref_amt = crAmount;
	}
	
	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void rateScheduleMainternance(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "rateScheduleMainternance/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "rateScheduleMainternance/teller");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "rateScheduleMainternance/branchCode");
		String fdReceipt = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "rateScheduleMainternance/fdReceipt");
		String rateChangeDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "rateScheduleMainternance/rateChangeDate");
		String newRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "rateScheduleMainternance/newRate");
		
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.rateScheduleMainternance( channel,teller, branchCode, fdReceipt, rateChangeDate, newRate);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "rateScheduleMainternance/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}
	
	private void createTranferSA2GLMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/manager");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/branchCode");
		String journalSeq = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/journalSeq");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/transDate");
		String effectiveDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/effectiveDate");
		String creditAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/creditAmount");
		String creditAcc = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/creditAcc");
		String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/debitAmount");
		String debitAcc = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/debitAcc");
		String creditRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/creditRate");
		String debitRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/debitRate");
		String adviceNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/adviceNumber");
		String creditCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/creditCurrency");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/debitCurrency");
		String description = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/description");
		String tranCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferSAToGLMessage/tranCode");
		
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createTranferSA2GLMessage(channel, teller, manager, branchCode, journalSeq
				, transDate, effectiveDate, creditAmount, creditAcc, debitAmount, debitAcc, creditRate, debitRate, adviceNumber, creditCurrency, debitCurrency, description, tranCode);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createTranferSAToGLMessage/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}
	
	private void createTranferGL2SaMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf) {
		
		String channel = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/teller");
		String manager = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/manager");
		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/branchCode");
		String journalSeq = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/journalSeq");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/transDate");
		String effectiveDate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/effectiveDate");
		String creditAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/creditAmount");
		String creditAcc = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/creditAcc");
		String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/debitAmount");
		String debitAcc = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/debitAcc");
		String creditRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/creditRate");
		String debitRate = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/debitRate");
		String adviceNumber = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/adviceNumber");
		String creditCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/creditCurrency");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/debitCurrency");
		String description = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/description");
		String tranCode = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY + "createTranferGLToSaMessage/tranCode");
		
		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		Messages retMessage = cbsService.createTranferGL2SaMessage(channel, teller, manager, branchCode, journalSeq
				, transDate, effectiveDate, creditAmount, creditAcc, debitAmount, debitAcc, creditRate, debitRate, adviceNumber, creditCurrency, debitCurrency, description, tranCode);
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY + "createTranferGLToSaMessage/out/", retMessage, msgInf);
		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage.getResponseMsg();
		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		
		
	}

}
