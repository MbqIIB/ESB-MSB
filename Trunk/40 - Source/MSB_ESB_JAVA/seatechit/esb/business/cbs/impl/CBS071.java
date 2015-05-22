package seatechit.esb.business.cbs.impl;

import java.util.ArrayList;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.ApproLoanAcc_MB88562;
import vn.com.m1tech.AS400.Factory.entity.CreateLNAccount_MB88501;
import vn.com.m1tech.AS400.Factory.entity.LockAccLoanForApprove83503;
import vn.com.m1tech.AS400.Factory.entity.PreAddLoanAcc_82501;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

public class CBS071 extends AbTransferMsg implements ITransferMsg {

	// CreateLNAccount_MB88501

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub

		cbsService.setParamList(paramList);

		String channel = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/channel");
		String teller = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/teller");

		msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
		msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

		String branchCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/branchCode");

		String hostName = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/hostName");

		String lnAcctNo = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/lnAcctNo");

		String orgLnAmount = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/orgLnAmount");

		String drawingLimit = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/drawingLimit");

		String modeOfInterest = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/modeOfInterest");

		String intPayFreq = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/intPayFreq");

		String intPayFreqCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/intPayFreqCode");

		String payFreq = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/payFreq");

		String payFreqCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/payFreqCode");

		String maturityDate = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/maturityDate");

		String capitalPrepaym = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/capitalPrepaym");

		String originationDate = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/originationDate");

		String loanTerm = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/loanTerm");

		String loanTermCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/loanTermCode");

		String status = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/status");

		String yearBase = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/yearBase");

		String paymentCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/paymentCode");

		String rateReviewDate = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateReviewDate");

		String rateUserReviewTerm = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateUserReviewTerm");

		String rateUserReviewCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateUserReviewCode");

		String paymentAmount = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/paymentAmount");

		String rateReviewTerm = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateReviewTerm");

		String rateReviewCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateReviewCode");

		String finalPaymAmt = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/finalPaymAmt");

		String orgLoanAmount = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/orgLoanAmount");

		String trancheMaxTerm = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/trancheMaxTerm");

		String trancheMaxCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/trancheMaxCode");

		String loanCode = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/loanCode");

		String interestBase = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/interestBase");

		String residualValue = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/residualValue");

		String dealerRate = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/dealerRate");

		String maturityRate = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/maturityRate");

		String advanceMargin = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/advanceMargin");

		String rateVariance = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateVariance");

		String rateVarianceFloor = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateVarianceFloor");

		String rateVarianceCeiling = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateVarianceCeiling");

		String rateFloor = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/rateFloor");

		String rateCeiling = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/rateCeiling");

		String interestRate = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/interestRate");

		String rateReviewDay = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/rateReviewDay");

		String facilityCode = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/facilityCode");
		String productType = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/productType");
		String currencyType = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/currencyType");
		String applicationNo = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_REQ_BODY + "lnAccount/applicationNo");
		String cif = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/cif");
		String seqNo = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_REQ_BODY + "lnAccount/seqNo");

		CreateLNAccount_MB88501 lnAcc = new CreateLNAccount_MB88501();

		lnAcc.branch = branchCode;
		lnAcc.hostname = hostName;
		lnAcc.teller = teller;

		lnAcc.lnAcctNo = lnAcctNo;
		lnAcc.orgLnAmount = orgLnAmount;
		lnAcc.drawingLimit = drawingLimit;
		lnAcc.modeOfInterest = modeOfInterest;
		lnAcc.intPayFreq = intPayFreq;
		lnAcc.intPayFreqCode = intPayFreqCode;
		lnAcc.payFreq = payFreq;
		lnAcc.payFreqCode = payFreqCode;
		lnAcc.maturityDate = maturityDate;
		lnAcc.capitalPrepaym = capitalPrepaym;
		lnAcc.originationDate = originationDate;
		lnAcc.loanTerm = loanTerm;
		lnAcc.loanTermCode = loanTermCode;
		lnAcc.status = status;
		lnAcc.yearBase = yearBase;
		lnAcc.paymentCode = paymentCode;
		lnAcc.rateReviewDate = rateReviewDate;
		lnAcc.rateUserReviewTerm = rateUserReviewTerm;
		lnAcc.rateUserReviewCode = rateUserReviewCode;
		lnAcc.paymentAmount = paymentAmount;
		lnAcc.rateReviewTerm = rateReviewTerm;
		lnAcc.rateReviewCode = rateReviewCode;
		lnAcc.finalPaymAmt = finalPaymAmt;
		lnAcc.orgLoanAmount = orgLoanAmount;
		lnAcc.trancheMaxTerm = trancheMaxTerm;
		lnAcc.trancheMaxCode = trancheMaxCode;
		lnAcc.loanCode = loanCode;
		lnAcc.interestBase = interestBase;
		lnAcc.residualValue = residualValue;
		lnAcc.dealerRate = dealerRate;
		lnAcc.maturityRate = maturityRate;
		lnAcc.advanceMargin = advanceMargin;
		lnAcc.rateVariance = rateVariance;
		lnAcc.rateVarianceFloor = rateVarianceFloor;
		lnAcc.rateVarianceCeiling = rateVarianceCeiling;
		lnAcc.rateFloor = rateFloor;
		lnAcc.rateCeiling = rateCeiling;
		lnAcc.interestRate = interestRate;
		lnAcc.rateReviewDay = rateReviewDay;
		// Generate AccNo

		PreAddLoanAcc_82501 preLnAcc = new PreAddLoanAcc_82501();

		preLnAcc.branch = branchCode;
		preLnAcc.hostname = hostName;
		preLnAcc.teller = teller;
		preLnAcc.applicationNo = applicationNo;
		preLnAcc.cif = cif;
		preLnAcc.currencyType = currencyType;
		preLnAcc.facilityCode = facilityCode;
		preLnAcc.hostname = hostName;
		preLnAcc.productType = productType;
		preLnAcc.seqNo = seqNo;
		preLnAcc.teller = teller;

		Messages retMessage = cbsService.sendMessage(channel,
				preLnAcc.toArray());

		

		if ("0".equals(retMessage.getErrCode())
				|| "/0000000".equals(retMessage.getErrCode())) {
			lnAcctNo = retMessage.getArrString()[70];
			// Tao loan Acc

			lnAcc.lnAcctNo = lnAcctNo;

			// Tao so tai khoan truoc khi tao tai khoan Loan:

			// tao tai khoan vay
			retMessage = cbsService.sendMessage(channel, lnAcc.toArray());

		
			 
			// / neu tao tai khoan vay thanh cong thi duyet tai khoan vay
			if ("0".equals(retMessage.getErrCode())
					|| "/0000000".equals(retMessage.getErrCode())) {
			 
				LockAccLoanForApprove83503 lockAcc = new LockAccLoanForApprove83503();
				
				lockAcc.branch = branchCode;
				lockAcc.hostname = hostName;
				lockAcc.teller = teller;
				lockAcc.loanAcc=lnAcctNo;

				retMessage = cbsService.sendMessage(channel, lockAcc.toArray());

				if ("0".equals(retMessage.getErrCode())
						|| "/0000000".equals(retMessage.getErrCode())) {
					
					ApproLoanAcc_MB88562 app = new ApproLoanAcc_MB88562();
					
					app.branch=branchCode;
					app.hostname=hostName;
					app.loanAcc=lnAcctNo;
					app.teller=teller;
				
					retMessage = cbsService.sendMessage(channel, app.toArray());

					msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
					
				}

			}

		}
		msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
		buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				+ "lnAccount/out/", retMessage, msgInf);

		msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				.getRequestMsg();
		msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				.getResponseMsg();

		msgInf.teller_id = teller;
		msgInf.approver_id = teller;
		msgInf.ref_channel = channel;

	}
}
