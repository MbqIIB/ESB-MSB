package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.LnPaidOffGL_ABCS4960;

import vn.com.m1tech.AS400.entity.TransferAcc;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author Quanld
 * tat toan khoan vay tai khoan GL
 */
public class CBS079  extends AbTransferMsg implements ITransferMsg {
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/branch");
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/hostName");
		String sysName = "";
		try {
			sysName = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "transferAcc/sysName");
		} catch (Exception ex) {
			sysName = "*LINX";
		}
		String transCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/transCode");
		String journalSeq = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/journalSeq");
		String transDate = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/transDate");
		String supervisor = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/supervisor");

		String creditAcc = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/creditAcc");
		String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/debitAmount");
		String effetiveDate = ElementUtils
				.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
						+ "transferAcc/effetiveDate");
		String debitAcc = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/debitAcc");
		String creditAmount = ElementUtils
				.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
						+ "transferAcc/creditAmount");
		String creditRate = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/creditRate");
		String debitRate = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/debitRate");
		
		
		String adviceNumber = "";
		try {
			adviceNumber = ElementUtils
			.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
					+ "transferAcc/adviceNumber");;
		} catch (Exception ex) {
			adviceNumber = "1";
		}
		String creditCurrency = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY
						+ "transferAcc/creditCurrency");
		String debitCurrency = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY
						+ "transferAcc/debitCurrency");
		String description = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "transferAcc/description");

		TransferAcc transferAcc= new TransferAcc();
		
		transferAcc.setAdviceNumber(adviceNumber);
		transferAcc.setBranch(branch);
		transferAcc.setCreditAcc(creditAcc);
		transferAcc.setCreditAmount(creditAmount);
		transferAcc.setCreditCurrency(creditCurrency);
		transferAcc.setCreditRate(creditRate);
		transferAcc.setDebitAcc(debitAcc);
		transferAcc.setDebitAmount(debitAmount);
		transferAcc.setDebitCurrency(debitCurrency);
		transferAcc.setDebitRate(debitRate);
		transferAcc.setDescription(description);
		transferAcc.setEffetiveDate(effetiveDate);
		transferAcc.setHostName(hostName);
		transferAcc.setJournalSeq(journalSeq);
		transferAcc.setSupervisor(supervisor);
		transferAcc.setSysName(sysName);
		transferAcc.setTeller(teller);
		transferAcc.setTransCode(transCode);
		transferAcc.setTransDate(transDate);
		
		
		LnPaidOffGL_ABCS4960 paidOffCa = new LnPaidOffGL_ABCS4960();
		paidOffCa.acc=transferAcc;
		Messages retMessage = cbsService.sendMessage(channel,
				paidOffCa.toArray());

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "transferAcc/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}

}
