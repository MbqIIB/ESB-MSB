package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.AddODPTier_MB27050;
import vn.com.m1tech.AS400.entity.OdpEntity;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author Quanld
 * Cap han muc thau chi
 */
public class CBS074  extends AbTransferMsg implements ITransferMsg {
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/branch");

		
		String hostName= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/hostName");
		
		String accountNumber= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/accountNumber");
		String accountType= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/accountType");
		String drawLimit= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/drawLimit");
		String authLimit= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/authLimit");
		String odRate= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/odRate");
		String argDate= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/argDate");
		String expDate= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/expDate");
		String rateVariance= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/rateVariance");
		String rateCode= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/rateCode");
		String rateFloor= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/rateFloor");
		String rateCeiling= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/rateCeiling");
		String commitFreeRate= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/commitFreeRate");
		String commitFreeType= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/commitFreeType");
		String productGroup= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/productGroup");
		String excessLimitType = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/excessLimitType");
		String jounalSequence= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/jounalSequence");
		
		String odpType= ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "addOdd/odpType");
		

	
		// lock tai khoan truoc khi update
		AddODPTier_MB27050 addOdt = new AddODPTier_MB27050();
		OdpEntity odp= new OdpEntity();
		
		odp.setAccountNumber(accountNumber);
		odp.setAccountType(accountType);
		odp.setArgDate(argDate);
		odp.setAuthLimit(authLimit);
		odp.setBranch(branch);
		odp.setCommitFreeRate(commitFreeRate);
		odp.setCommitFreeType(commitFreeType);
		odp.setDrawLimit(drawLimit);
		odp.setExcessLimitType(excessLimitType);
		odp.setExpDate(expDate);
		odp.setHostName(hostName);
		odp.setJounalSequence(jounalSequence);
		odp.setOdRate(odRate);
		odp.setProductGroup(productGroup);
		odp.setRateCeiling(rateCeiling);
		odp.setRateCode(rateCode);
		odp.setRateFloor(rateFloor);
		odp.setRateVariance(rateVariance);
		odp.setTeller(teller);	
		odp.setOdpType(odpType);
		addOdt.odp=odp;
		
		Messages retMessage = cbsService.sendMessage(channel,
				addOdt.toArray());

		
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "addOdd/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}
}
