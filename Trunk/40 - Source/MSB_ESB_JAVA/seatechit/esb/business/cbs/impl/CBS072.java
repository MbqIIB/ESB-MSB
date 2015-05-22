package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.ReleaseLnAcc;
import vn.com.m1tech.AS400.Factory.entity.ReleaseLoanAcc_ABCS4360;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author Quanld
 * Giai ngan tai khoan vay
 */
public class CBS072 extends AbTransferMsg implements
ITransferMsg {

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "releaseLnAcc/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "releaseLnAcc/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

	
		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "releaseLnAcc/hostName");

		
		ReleaseLoanAcc_ABCS4360 releaseLoanAcc = new ReleaseLoanAcc_ABCS4360();

		
		
		 String jonalSequece=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/jonalSequece");
		 String transDate =ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/transDate");
		 String supervisor=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/supervisor");
		 String branch=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/branch");
		 String loanAcc=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/loanAcc");
		 String releaseAmount=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/releaseAmount");
		 String description=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/description");
		 String glAcc=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/glAcc");
		 
		 String caAcc=ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "releaseLnAcc/caAcc");
		 
		 ReleaseLnAcc releaseAcc= new ReleaseLnAcc();
		//quan-dh
		 releaseAcc.setBranch(branch);
		 releaseAcc.setDescription(description);
		 releaseAcc.setJonalSequece(jonalSequece);
		 releaseAcc.setLoanAcc(loanAcc);
		 releaseAcc.setReleaseAmount(releaseAmount);
		 releaseAcc.setSupervisor(supervisor);
		 releaseAcc.setTeller(teller);
		 releaseAcc.setTransDate(transDate);
		 releaseAcc.setGlAcc(glAcc);
		 releaseAcc.setCaAcc(caAcc);
		 releaseAcc.setChannel(channel);
		 releaseAcc.setHostName(hostName);
		 
		 releaseLoanAcc.relewaseAcc=releaseAcc;
		 
		Messages retMessage = cbsService.sendMessage(channel, releaseLoanAcc.toArray());

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "releaseLnAcc/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}

	
	
	//ReleaseLoanAcc_ABCS4360
}
