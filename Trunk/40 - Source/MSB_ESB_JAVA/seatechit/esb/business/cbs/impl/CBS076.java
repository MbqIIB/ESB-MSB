package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.DeleteCollateral_MB88222;
import vn.com.m1tech.AS400.Factory.entity.LockCollateral_MB86202;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author Quanld Delete Collateral Xoa tai san dam bao
 */
public class CBS076 extends AbTransferMsg implements ITransferMsg {
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollateral/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollateral/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollateral/branch");

		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "deleteCollateral/hostName");

		String collateralId = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY
						+ "deleteCollateral/collateralId"); //

		DeleteCollateral_MB88222 delCol = new DeleteCollateral_MB88222();
		delCol.branch = branch;
		delCol.collId = collateralId;
		delCol.teller = teller;
		delCol.hostname = hostName;

		LockCollateral_MB86202 lockCola = new LockCollateral_MB86202();

		lockCola.branch = branch;
		lockCola.collId = collateralId;
		lockCola.teller = teller;
		lockCola.hostname = hostName;

		Messages retMessage = cbsService.sendMessage(channel,
				lockCola.toArray());
		if ("0".equals(retMessage.getErrCode())
				|| "/0000000".equals(retMessage.getErrCode())) {
			retMessage = cbsService.sendMessage(channel, delCol.toArray());
		}
		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "deleteCollateral/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}

}
