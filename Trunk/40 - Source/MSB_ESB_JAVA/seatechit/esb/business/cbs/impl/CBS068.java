package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;

import vn.com.m1tech.AS400.Factory.entity.LoanPayoff_ABCS_4088;
import vn.com.m1tech.AS400.Factory.entity.LockFacilityLoan_MB86303;

import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

/*
 * View loanAmount; 
 * loanPayoff_ABCS4088
 * 
 * */

public class CBS068 extends AbTransferMsg implements ITransferMsg {

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub

		try {
			cbsService.setParamList(paramList);

			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "lnInqAmount/channel");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "lnInqAmount/teller");
			String transDate=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "lnInqAmount/transDate");
			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

			String branch = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "lnInqAmount/branch");

			String hostName = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "lnInqAmount/hostName");

			String seqNo = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "lnInqAmount/seqNo");

			String loanAcc = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "lnInqAmount/loanAcc");

			LoanPayoff_ABCS_4088 lnPayOff = new LoanPayoff_ABCS_4088();

			lnPayOff.branch = branch;
			lnPayOff.loanAcc = loanAcc;
			lnPayOff.journalSeq = seqNo;
			lnPayOff.teller = teller;
			lnPayOff.transDate=transDate;
			Messages retMessage = cbsService.sendMessage(channel,
					lnPayOff.toArray());

			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					+ "lnInqAmount/out/", retMessage, msgInf);

			msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
					.getRequestMsg();
			msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
					.getResponseMsg();

			msgInf.teller_id = teller;
			msgInf.approver_id = teller;
			msgInf.ref_channel = channel;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
