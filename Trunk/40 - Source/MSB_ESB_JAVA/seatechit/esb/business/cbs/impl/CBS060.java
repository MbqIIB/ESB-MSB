package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;


import com.ibm.broker.plugin.MbMessage;
import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;

import vn.com.m1tech.AS400.Factory.entity.AddAA_MB88301;
import vn.com.m1tech.AS400.entity.AAEntity;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

public class CBS060 extends AbTransferMsg implements
		ITransferMsg {

	/**
	 * Created By : QuanLD Created On : 2015-01-24 Version : v1.0 Description:
	 * Create AA Messages
	 * 
	 * Amendment History: Params: MbMessage inMessage, MbMessage outMessage,
	 * MessageInfo msgInf, ArrayList<HostParameter> paramList -- cac tham so
	 * Port, host lay tu bang tham so cua DB Amended By Amended On Amendment
	 * Description ------------ -----------
	 * ---------------------------------------------
	 */
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {

		// Get Paramlist from cache

		try {
			cbsService.setParamList(paramList);

			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "aAEntity/channel");
			String hostName = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/hostName");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "aAEntity/teller");
			String branch = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "aAEntity/branch");

			String applicationNo = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/applicationNo");

			String cifNum = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "aAEntity/cifNum");

			String applicationDate = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "aAEntity/applicationDate");

			String workingEx = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/workingEx");

			String specProvision = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/specProvision"); // Y/N

			String clasifielDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/clasifielDate");

			String carCode = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "aAEntity/carCode");

			String reviewDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/reviewDate");

			String retention = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/retention");

			String reviewReMark = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/applicationNo");

			String refinanceFrom = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/refinanceFrom");

			String curencyType = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/curencyType");

			String officeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/officeCode");

			String classification = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/classification");

			String MARemarck = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/MARemarck");

			String mADate = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "aAEntity/mADate");

			String tucachPn = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/tucachPn");

			String tinhHinhTc = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/tinhHinhTc");

			String khaNangVayVon = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/khaNangVayVon");

			String damBaoTienVay = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/damBaoTienVay");
			
			String limitAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "aAEntity/limitAmount");

			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

			AAEntity aa = new AAEntity();

			aa.setApplicationDate(applicationDate);
			aa.setApplicationNo(applicationNo);
			aa.setBranch(branch);
			aa.setCarCode(carCode);
			aa.setCifNum(cifNum);
			aa.setClasifielDate(clasifielDate);
			aa.setClassification(classification);
			aa.setCurencyType(curencyType);
			aa.setDamBaoTienVay(damBaoTienVay);
			aa.setHostName(hostName);
			aa.setKhaNangVayVon(khaNangVayVon);
			aa.setLimitAmount(limitAmount);
			aa.setMADate(mADate);
			aa.setMARemarck(MARemarck);
			aa.setOfficeCode(officeCode);
			aa.setRefinanceFrom(refinanceFrom);
			aa.setRetention(retention);
			aa.setReviewDate(reviewDate);
			aa.setReviewReMark(reviewReMark);
			aa.setSpecProvision(specProvision);
			aa.setTeller(teller);
			aa.setTinhHinhTc(tinhHinhTc);
			aa.setTucachPn(tucachPn);
			aa.setWorkingEx(workingEx);

			//build ra mang
			AddAA_MB88301 aaMsg = new AddAA_MB88301();
			aaMsg.aa = aa;
			String[] messageArray = aaMsg.toArray();
			
			Messages retMessage = cbsService.sendMessage(channel, messageArray);

			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					+ "aAEntity/out/", retMessage, msgInf);

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
