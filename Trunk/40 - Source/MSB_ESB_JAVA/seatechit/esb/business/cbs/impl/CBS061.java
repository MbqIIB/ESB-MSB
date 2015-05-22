package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;



import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;

import vn.com.m1tech.AS400.Factory.entity.Fee_ABCS0800;
import vn.com.m1tech.AS400.entity.Fee;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

/**
 * Created By : QuanLD Created On : 2015-01-24 Version : v1.0 Description:
 * Create AA Messages
 * 
 * Amendment History: Amended By Amended On Amendment Description ------------
 * ----------- ---------------------------------------------
 */
public class CBS061 extends AbTransferMsg implements ITransferMsg {

	/**
	 * Created By : QuanLD 
	 * Created On : 2015-01-24 
	 * Version : v1.0 Description:
	 * Buld Message Charge Fee Message 
	 * 
	 * Amendment History: Amended By Amended On Amendment Description
	 * ------------ ----------- ---------------------------------------------
	 */
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub	
		Fee fee= new Fee();
		try {
			cbsService.setParamList(paramList);

			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/channel");
			String hostName = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "fee/hostName");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/teller");
			String branch = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/branch");
			
		
			String transCode= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/transCode");
			String transDate= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/transDate");
			String supervisor= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/supervisor");
			String jonalSequece= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/jonalSequece");
			String currency= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/currency");
			String creditAcc= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/creditAcc");
			String debitAcc= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/debitAcc");
			String totalAmount= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/totalAmount");
			String amount= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/amount");
			String vatAmount= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/vatAmount");
			String description= ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "fee/description");
			
			
			
			
			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

			fee.setAmount(amount);
			fee.setBranch(branch);
			fee.setCreditAcc(creditAcc);
			fee.setCurrency(currency);
			fee.setDebitAcc(debitAcc);
			fee.setDescription(description);
			fee.setHostName(hostName);
			fee.setJonalSequece(jonalSequece);
			fee.setSupervisor(supervisor);
			fee.setTeller(teller);
			fee.setTotalAmount(totalAmount);
			fee.setTransCode(transCode);
			fee.setTransDate(transDate);
			fee.setVatAmount(vatAmount);
			
			Fee_ABCS0800 feeMsg= new Fee_ABCS0800();
			feeMsg.fee= fee;
			String[] feeArr=feeMsg.toArray();
			Messages retMessage = cbsService.sendMessage(channel, feeArr);

			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					+ "fee/out/", retMessage, msgInf);

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
