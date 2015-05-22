package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.FD_WithDrawGL_ABCS3360;
import vn.com.m1tech.AS400.Factory.entity.PreAddLoanAcc_82501;
import vn.com.m1tech.AS400.entity.WithDrawFD;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author Quanld Tat toan So tich kiem truc tuyen vao tai khoan GL
 */
public class CBS070 extends AbTransferMsg implements ITransferMsg {

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/branchCode");

		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/hostName");
		String fdAcc = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/fdAcc");
		String creditAmount = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "fdWithDrawGL/creditAmount");

		String currency = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/currency");

		String glAcc = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/glAcc");

		String ddAcc = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/ddAcc");

		String debitAmount = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/debitAmount");

		String description = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/description");

		String fdCurrency = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/fdCurrency");

		String jonalSequece = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "fdWithDrawGL/jonalSequece");

		String supervisor = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/supervisor");

		String transCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/transCode");

		String transDate = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "fdWithDrawGL/transDate");

//		String fdSeriaNo = ElementUtils.getXMLElementInStringValue(inMessage,
//				Global.XML_REQ_BODY + "fdWithDrawGL/fdSeriaNo");
		
		WithDrawFD fdAccInfo = new WithDrawFD();

		FD_WithDrawGL_ABCS3360 fdWithDrawGL = new FD_WithDrawGL_ABCS3360();
		fdAccInfo.setFdAcc(fdAcc); // 02010100071190
		fdAccInfo.setBranch(branchCode);
		fdAccInfo.setCreditAmount(creditAmount);
		fdAccInfo.setCurrency(currency);
		fdAccInfo.setGlAcc(glAcc);
		fdAccInfo.setDdAcc(ddAcc);
		fdAccInfo.setDebitAmount(debitAmount);
		fdAccInfo.setDescription(description);
		fdAccInfo.setFdCurrency(fdCurrency);
		fdAccInfo.setJonalSequece(jonalSequece);
		fdAccInfo.setSupervisor(supervisor);
		fdAccInfo.setTeller(teller);
		fdAccInfo.setTransCode(transCode); // 3360
		fdAccInfo.setTransDate(transDate);
		fdAccInfo.setHostName(hostName);
		
		fdWithDrawGL.fd=fdAccInfo;
		Messages retMessage = cbsService.sendMessage(channel,
				fdWithDrawGL.toArray());

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "fdWithDrawGL/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}
}
