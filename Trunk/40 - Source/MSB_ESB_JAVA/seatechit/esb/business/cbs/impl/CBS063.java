package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;



import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.CreateAppNo_MB82301;
import vn.com.m1tech.AS400.entity.AppNo;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.AppUtils;
import vn.com.msb.cnn.utils.Messages;



public class CBS063 extends AbTransferMsg implements
		ITransferMsg {

	
	/**
	 * Created By : QuanLD Created On : 2015-01-24 Version : v1.0 Description:
	 * Create Loan AppNo
	 * 
	 * Amendment History: Params: MbMessage inMessage, MbMessage outMessage,
	 * MessageInfo msgInf, ArrayList<HostParameter> paramList -- cac tham so
	 * Port, host lay tu bang tham so cua DB Amended By Amended On Amendment
	 * Description ------------ -----------
	 * ---------------------------------------------
	 */
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub		

		
		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "appNo/channel");
		String hostName = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "appNo/hostName");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "appNo/teller");
		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "appNo/branch");
	
		String cifNum = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "appNo/cifNum");

		AppNo appNo= new AppNo();
		appNo.setCifNum(cifNum);
		appNo.setBranch(branch);
		appNo.setHostName(hostName);
		appNo.setTeller(teller);
		appNo.setTransCode("");
		appNo.setChannel(channel);
		
		CreateAppNo_MB82301 appNoMsg= new CreateAppNo_MB82301();
		appNoMsg.appNo=appNo;
		
		cbsService.setParamList(paramList);
		
		Messages retMessage = cbsService.sendMessage(channel, appNoMsg.toArray());

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "appNo/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
		
	}

	

}
