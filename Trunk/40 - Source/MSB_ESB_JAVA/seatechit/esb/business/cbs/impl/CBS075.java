/**
 * 
 */
package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.DeleteCollateralLink_MB89305;
import vn.com.m1tech.AS400.Factory.entity.LockCollateralLink_MB86305;
import vn.com.m1tech.AS400.entity.CollateralLink;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author Quanld Xoa link tai san dao bao voi facility CollateralLink
 */
public class CBS075 extends AbTransferMsg implements ITransferMsg {
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollalink/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollalink/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollalink/branch");

		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollalink/hostName");

		String applicationNo = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY
						+ "deleteCollalink/applicationNo"); //
		String facilityCode = ElementUtils
				.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
						+ "deleteCollalink/facilityCode"); // 240
		String facId = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollalink/facId");//
		String collateralId = ElementUtils
				.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
						+ "deleteCollalink/collateralId"); //

		CollateralLink callink = new CollateralLink();

		callink.setApplicationNo(applicationNo);
		callink.setCollateralId(collateralId);
		callink.setBranch(branch);
		callink.setFacId(facId);
		callink.setHostName(hostName);
		callink.setFacilityCode(facilityCode);
		callink.setTeller(teller);

		LockCollateralLink_MB86305 lockColla = new LockCollateralLink_MB86305();

		lockColla.calla = callink;
		Messages retMessage = cbsService.sendMessage(channel,
				lockColla.toArray());
		if ("0".equals(retMessage.getErrCode())
				|| "/0000000".equals(retMessage.getErrCode())) {

			DeleteCollateralLink_MB89305 delCollink = new DeleteCollateralLink_MB89305();
			delCollink.calla = callink;

			retMessage = cbsService.sendMessage(channel, delCollink.toArray());
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "deleteCollalink/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}

}
