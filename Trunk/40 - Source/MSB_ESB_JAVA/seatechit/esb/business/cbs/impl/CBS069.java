package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;

import vn.com.m1tech.AS400.Factory.entity.FDWithDrawToCA_ABCS3320;
import vn.com.m1tech.AS400.Factory.entity.UnlockFacilityLoan_MB88902;
import vn.com.m1tech.AS400.entity.WithDrawFD;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

/**
 * Created By : Quanld
 * 
 * Version    : v1.0
 * Description: Chuyen tien tu FD sang CA
 *
 * Amendment History:
 *
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 * 
*/

public class CBS069  extends AbTransferMsg implements ITransferMsg{

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		
		try {
			cbsService.setParamList(paramList);
			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/channel");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/teller");

			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

			String branchCode = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/branchCode");

			String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/hostName");
			String fdAcc = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/fdAcc");
			String creditAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "fdWithDrawCA/creditAmount");

			String currency = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/currency");

			String glAcc = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/glAcc");

			String ddAcc = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/ddAcc");

			String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/debitAmount");

			String description = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/description");

			String fdCurrency = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/fdCurrency");

			String jonalSequece = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "fdWithDrawCA/jonalSequece");

			String supervisor = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/supervisor");

			String transCode = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/transCode");

			String transDate = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/transDate");

			String fdSeriaNo = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fdWithDrawCA/transDate");
			
			WithDrawFD ca= new WithDrawFD();
			FDWithDrawToCA_ABCS3320 fdCa= new FDWithDrawToCA_ABCS3320();
			
			
			ca.setBranch(branchCode);
			ca.setCreditAmount(creditAmount);
			ca.setCurrency(fdCurrency);
			ca.setDdAcc(ddAcc);
			ca.setDebitAmount(debitAmount);
			ca.setDescription(description);
			ca.setFdAcc(fdAcc);
			ca.setFdCurrency(fdCurrency);
			ca.setHostName(hostName);
			ca.setJonalSequece(jonalSequece);
			ca.setSupervisor(supervisor);
			ca.setTeller(teller);
			ca.setTransCode(transCode);
			ca.setTransDate(transDate);
			
			fdCa.ca=ca;
			
			Messages retMessage = cbsService.sendMessage(channel,fdCa.toArray());

			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					+ "fdWithDrawCA/out/", retMessage, msgInf);

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
