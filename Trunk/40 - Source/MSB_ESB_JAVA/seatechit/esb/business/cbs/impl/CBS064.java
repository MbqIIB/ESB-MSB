/**
 * Created By : QuanLD
 * Created On : Jan 26, 2015
 * Version    : v1.0
 * Description:
 *
 * Amendment History:
 *
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 */
package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.CollateralLink_MB87305;
import vn.com.m1tech.AS400.entity.CollateralLink;

import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

/**
 * @author quanld
 * 
 */
public class CBS064 extends AbTransferMsg implements ITransferMsg {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * seatechit.esb.business.cbs.ITransferMsg#buildMessage(com.ibm.broker.plugin
	 * .MbMessage, com.ibm.broker.plugin.MbMessage,
	 * seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo,
	 * java.util.ArrayList)
	 */
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub

		try {
			cbsService.setParamList(paramList);

			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateralLink/channel");
			String branch = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateralLink/branch");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateralLink/teller");
			String hostName = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/hostName");
			String applicationNo = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/applicationNo");
			String facilityCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/facilityCode");
			String collateralId = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/collateralId");
			String indexDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/indexDate");
			String pledgedAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/pledgedAmount");
			String percentPledged = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/percentPledged");
			String fixedAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/fixedAmount");
			String basicOfadvance = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateralLink/basicOfadvance");
			String facId = ElementUtils.getXMLElementInStringValue(
		inMessage, Global.XML_REQ_BODY + "collateralLink/facId");

			CollateralLink calla = new CollateralLink();
			calla.setFacId(facId);
			calla.setBranch(branch);
			calla.setTeller(teller);
			calla.setHostName(hostName);
			calla.setApplicationNo(applicationNo);  
			calla.setFacilityCode(facilityCode); 
			calla.setCollateralId(collateralId);  
			calla.setIndexDate(indexDate); 
			calla.setPledgedAmount(pledgedAmount);  
			calla.setPercentPledged(percentPledged);  
			calla.setFixedAmount(fixedAmount); 
			calla.setBasicOfadvance(basicOfadvance);
			
			CollateralLink_MB87305 collaLink= new CollateralLink_MB87305();
			collaLink.calla=calla;
			
			Messages retMessage = cbsService.sendMessage(channel, collaLink.toArray());

			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					+ "collateralLink/out/", retMessage, msgInf);

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
