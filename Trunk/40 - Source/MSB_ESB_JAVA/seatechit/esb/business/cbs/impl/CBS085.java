package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.CreateAppNo_MB82301;
import vn.com.m1tech.AS400.Factory.entity.FDAccountInquiry_MB36501;
import vn.com.m1tech.AS400.entity.AppNo;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.AppUtils;
import vn.com.msb.cnn.utils.Messages;

/**
 * @author Quanld
 * Inquiry FD Account
 */
public class CBS085 extends AbTransferMsg implements
ITransferMsg {

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		
		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdAccountInq/channel");
		String hostName = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "fdAccountInq/hostName");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdAccountInq/teller");
		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdAccountInq/branch");
	
		String fdAccount = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdAccountInq/fdAccount");
		
		String fdAccountType = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdAccountInq/fdAccountType");

		
		
		FDAccountInquiry_MB36501 fdInquiry= new FDAccountInquiry_MB36501();
		fdInquiry.branch=branch;
		fdInquiry.fdAccount=fdAccount;
		fdInquiry.fdAcountType=fdAccountType;
		fdInquiry.hostname=hostName;
		fdInquiry.teller=teller;
		
		cbsService.setParamList(paramList);
		
		Messages retMessage = cbsService.sendMessage(channel, fdInquiry.toArray());

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "fdAccountInq/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		
	}


}
