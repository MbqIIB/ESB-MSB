package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.LockAccForUpdate_MB26901;
import vn.com.m1tech.AS400.Factory.entity.RegisterOD_MB28901;
import vn.com.m1tech.AS400.entity.AccInfo;
import vn.com.m1tech.AS400.entity.LockAcc;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

//Cap han muc thau chi
/**
 * @author Quanld Cap han muc thau chi
 */
public class CBS073 extends AbTransferMsg implements ITransferMsg {
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branch = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/branch");

		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/hostName");
		String accNo = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/accNo");
		String accType = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/accType");
		String groupCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "registerOd/groupCode");
		String overdraffCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "registerOd/overdraffCode");

		LockAcc lockAcc = new LockAcc();
		lockAcc.setAccNo(accNo);
		lockAcc.setAccType(accType);
		lockAcc.setBranch(branch);
		lockAcc.setHostName(hostName);
		lockAcc.setTeller(teller);
		// lock tai khoan truoc khi update
		LockAccForUpdate_MB26901 lockAccSvr = new LockAccForUpdate_MB26901();
		lockAccSvr.acc = lockAcc;
		Messages retMessage = cbsService.sendMessage(channel,
				lockAccSvr.toArray());

		if ("0".equals(retMessage.getErrCode())
				|| "/0000000".equals(retMessage.getErrCode())) {

			String[] accReturn = retMessage.getArrString();

			RegisterOD_MB28901 regisOd = new RegisterOD_MB28901();
			AccInfo accinfo = new AccInfo();

			String par130 = "";
			String par131 = "";
			if (accReturn.length >= 130) {
				par130 = accReturn[129];
			}
			if (accReturn.length >= 131) {
				par131 = accReturn[130];
			}
			String arr_125;
			String arr_128;
			if (accReturn.length < 126) {
				arr_125 = "";
			} else {
				arr_125 = accReturn[125];
			}
			if (accReturn.length < 129) {
				arr_128 = "";
			} else {
				arr_128 = accReturn[128];
			}

			accinfo.setBranch(branch);
			accinfo.setAccNo_68(accNo);
			accinfo.setNewProCode_70(accReturn[69] + "");
			accinfo.setNewDate_71(accReturn[70] + "");
			accinfo.setPar1_75(accReturn[74] + "");
			accinfo.setPar2_76(accReturn[75] + "");
			accinfo.setPar3_77(accReturn[76] + "");
			accinfo.setServiceCharge_78(accReturn[77] + "");
			accinfo.setCheque_79(accReturn[78] + "");
			accinfo.setHoldMailCode_80(accReturn[79] + "");
			accinfo.setIntroduceCode_81(accReturn[80] + "");
			accinfo.setPar4_82(accReturn[81] + "");
			accinfo.setPar5_83(accReturn[82] + "");
			accinfo.setPar6_84(accReturn[83] + "");
			accinfo.setPar7_85(accReturn[84] + "");
			accinfo.setPar8_87_100(AppUtils.convertHostDouble( accReturn[86]
					+ ""));
			accinfo.setPar9_89_100(AppUtils.convertHostDouble(accReturn[88]
					+ ""));
			accinfo.setPar10_90_100(AppUtils.convertHostDouble(accReturn[89]
					+ ""));
			accinfo.setPar11_91_100(AppUtils.convertHostDouble(accReturn[90]
					+ ""));
			accinfo.setSalOffice_96(accReturn[95] + "");
			accinfo.setProductGroup_97(accReturn[96] + "");
			accinfo.setModeOfoper_98(accReturn[97] + "");
			accinfo.setPar12_99(accReturn[98] + "");
			accinfo.setPar13_100(accReturn[99] + "");
			accinfo.setPar14_118_100(AppUtils.convertHostDouble(accReturn[118]
					+ ""));
			accinfo.setPar15_119_100(accReturn[119] + "");
			accinfo.setPar16_120_100(AppUtils.convertHostDouble(accReturn[120]
					+ ""));
			accinfo.setPar17_121(accReturn[121] + "");
			accinfo.setPar18_118_100(AppUtils.convertHostDouble(accReturn[117]
					+ ""));
			accinfo.setPar19_126(arr_125 + "");
			accinfo.setAccName_129(arr_128 + "");
			accinfo.setStatus(accReturn[71] + "");
			accinfo.setPar88_rateCode(accReturn[87] + "");
			accinfo.setPar_85(accReturn[85] );
			accinfo.setPar92_RateFloor(accReturn[91] + "");
			accinfo.setPar93(accReturn[92] + "");
			accinfo.setPar94(accReturn[93] + "");
			accinfo.setPar95(accReturn[94] + "");
			accinfo.setPar116(accReturn[115] + "");
			accinfo.setPar130(par130);
			accinfo.setPar131(par131);
			accinfo.setAcctype(accType);
			accinfo.setGroupCodeUpdate(groupCode);
			accinfo.setOverDraffProtect_131(overdraffCode);
			accinfo.setHostName(hostName);
			accinfo.setTeller(teller);
			accinfo.setPar_113(accReturn[113] + "");
			regisOd.acc = accinfo;
			retMessage = cbsService.sendMessage(channel, regisOd.toArray());
		}

		msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "registerOd/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;
	}

}
