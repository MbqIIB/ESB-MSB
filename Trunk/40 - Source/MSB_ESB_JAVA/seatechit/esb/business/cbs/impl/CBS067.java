package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.LockODPTier_MB26050;
import vn.com.m1tech.AS400.Factory.entity.MaintainODPTier_MB28050;
import vn.com.m1tech.AS400.Factory.entity.PreAddFac_82303;
import vn.com.m1tech.AS400.entity.LockODP;
import vn.com.m1tech.AS400.entity.OdpEntity;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

/**
 * @author Quanld ODP Maintaince
 * 
 */
public class CBS067 extends AbTransferMsg implements ITransferMsg {

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub

		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/branchCode");

		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/hostName");

		LockODPTier_MB26050 lockOdpSrv = new LockODPTier_MB26050();
		LockODP lockOdp = new LockODP();

		String accountNumber = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "maintainOdp/accountNumber");
		String accountType = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/accountType");
		String jounalSequence = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "maintainOdp/jounalSequence");
		String odpType = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/odpType");

		String amountLimit = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "maintainOdp/amountLimit");

		// lock Odp truoc khi maintaince
		lockOdp.setAccountNumber(accountNumber);
		lockOdp.setAccountType(accountType);
		lockOdp.setBranch(branch);
		lockOdp.setHostName(hostName);

		lockOdp.setJounalSequence(jounalSequence);

		lockOdp.setOdpType(odpType);
		lockOdp.setTeller(teller);
		lockOdpSrv.lock=lockOdp;
		Messages retMessage = new Messages();
		try {
			retMessage = cbsService.sendMessage(channel, lockOdpSrv.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		if ("0".equals(retMessage.getErrCode())
				|| "/0000000".equals(retMessage.getErrCode())) {
			OdpEntity odp = new OdpEntity();

			String[] arrReturn = retMessage.getArrString();
			odp.setAccountNumber(accountNumber);
			odp.setAccountType(accountType);
			String argDate = arrReturn[81] + "";
			odp.setArgDate(argDate);
			String authLimit = arrReturn[72] + "";
			odp.setAuthLimit(authLimit);
			odp.setBranch(branch);
			odp.setChannel(channel);
			String commitFreeRate = arrReturn[79] + "";
			odp.setCommitFreeRate(commitFreeRate);
			
			String commitFreeType = arrReturn[80] + "";
			odp.setCommitFreeType(commitFreeType);
			
			//String drawLimit = arrReturn[71] + "";
			odp.setDrawLimit(amountLimit);
			
			String excessLimitType = arrReturn[84] + "";
			odp.setExcessLimitType(excessLimitType);
			String expDate = arrReturn[82] + "";
			odp.setExpDate(expDate);
			odp.setHostName(hostName);
			odp.setJounalSequence(jounalSequence);
			odp.setOdpType(odpType);
			String odRate = arrReturn[73] + "";
			odp.setOdRate(odRate);
			String productGroup = arrReturn[83] + "";
			odp.setProductGroup(productGroup);
			String rateCeiling = arrReturn[78] + "";
			odp.setRateCeiling(rateCeiling);
			String rateCode = arrReturn[76] + "";
			odp.setRateCode(rateCode);
			String rateFloor = arrReturn[77] + "";
			odp.setRateFloor(rateFloor);
			String rateVariance = arrReturn[75] + "";
			odp.setRateVariance(rateVariance);
			odp.setTeller(teller);
			
			MaintainODPTier_MB28050 mn11= new MaintainODPTier_MB28050();
			
			mn11.odp=odp;
			
			try {
				retMessage = cbsService.sendMessage(channel,mn11.toArray());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "maintainOdp/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;

	}

}
