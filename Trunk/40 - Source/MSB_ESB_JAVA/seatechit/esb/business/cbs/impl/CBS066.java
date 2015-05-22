/**
 * Created By : QuanLD
 * Created On : Jan 25, 2015
 * Version    : v1.0
 * Description:
 *
 * Amendment History:
 *
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 */
package seatechit.esb.business.cbs.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import com.ibm.broker.plugin.MbMessage;

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.FacilityMaintain_MB88303;
import vn.com.m1tech.AS400.Factory.entity.LockFacilityLoan_MB86303;
import vn.com.m1tech.AS400.Factory.entity.PreAddFac_82303;
import vn.com.m1tech.AS400.entity.Facility;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

/**
 * @author quanld Tao Facility
 */
public class CBS066 extends AbTransferMsg implements ITransferMsg {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * seatechit.esb.business.cbs.ITransferMsg#buildMessage(com.ibm.broker.plugin
	 * .MbMessage, com.ibm.broker.plugin.MbMessage,
	 * seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo,
	 * java.util.ArrayList) Method: Buid message Maintain Facility
	 */

	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub
		try {
			cbsService.setParamList(paramList);

			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/channel");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/teller");

			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

			String branchCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/branchCode");
			String rcdOveride = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/rcdOveride");
			String applicationNo = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/applicationNo");
			String facilityCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/facilityCode");
			String seqNo = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/seqNo");
			String levelNo = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/levelNo");
			String cifNo = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/cifNo");
			String aFCNo = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/AFCNo");
			String producType = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/producType");
			String aFCStatus = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/AFCStatus");
			String appliedDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/appliedDate");
			String currencyType = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/currencyType");
			String appliedAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/appliedAmount");
			String purposeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/purposeCode");
			String revolving = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/revolving");
			String aBalIndicator = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/aBalIndicator");
			String senttoAppDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/senttoAppDate");
			String recFromAppCenter = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "facility/recFromAppCenter");
			String approvedDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/approvedDate");
			String appDate = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/appDate");
			String appBy = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/appBy");
			String offerDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/offerDate");
			String acceptedDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/acceptedDate");
			String appAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/appAmount");
			String availabelLimit = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/availabelLimit");
			String facilityLimit = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/facilityLimit");
			String availableBalance = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "facility/availableBalance");
			String interestBase = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/interestBase");
			String interestMode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/interestMode");
			String yearBase = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/yearBase");
			String term = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/term");
			String termCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/termCode");
			String interestRate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/interestRate");
			String variance = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/variance");
			String varCode = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/varCode");
			String rateReviewDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/rateReviewDate");
			String rateReviewTerm = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/rateReviewTerm");
			String rateReviewCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/rateReviewCode");
			String primeRateFloor = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/primeRateFloor");
			String primeRateCeiling = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "facility/primeRateCeiling");

			String paymentCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/paymentCode");
			String paymentFreq = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/paymentFreq");
			String paymentFreqCode = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "facility/paymentFreqCode");
			String interestPmtFreq = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "facility/interestPmtFreq");

			String interestPmtFreqCode = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "facility/interestPmtFreqCode");
			String paymentAmount = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/paymentAmount");
			String finalPaymAmt = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/finalPaymAmt");
			String lateChargeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/lateChargeCode");
			String gracePeriod = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/gracePeriod");
			String gracePeriodCode = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "facility/gracePeriodCode");
			String expireDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/expireDate");
			String processingFee = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/processingFee");
			String legalFee = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/legalFee");
			String cifName = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "facility/cifName");
			String branchTCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/branchTCode");
			String offerCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/offerCode");
			String shareLimitInd = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/shareLimitInd");
			String branchTHCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/branchTHCode");
			String secureCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/secureCode");
			String estimateLose = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/estimateLose");

			String rateNumber = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "facility/rateNumber");

			Facility fac = new Facility();

			fac.setaBalIndicator(aBalIndicator);
			fac.setAcceptedDate(acceptedDate);
			fac.setAFCNo(aFCNo);
			fac.setAFCStatus(aFCStatus);
			fac.setAppAmount(appAmount);
			fac.setAppBy("");
			fac.setAppDate(appDate);
			fac.setApplicationNo(applicationNo);
			fac.setAppliedAmount(appliedAmount);
			fac.setAppliedDate(appliedDate);
			fac.setApprovedDate("");
			fac.setAvailabelLimit(availabelLimit);
			fac.setAvailableBalance(availableBalance);
			fac.setBranchCode(branchCode);
			fac.setBranchTCode(branchTCode);
			fac.setBranchTHCode(branchTHCode);
			fac.setCifName(cifName);
			fac.setCifNo(cifNo);
			fac.setCurrencyType(currencyType);
			fac.setEstimateLose(estimateLose);
			fac.setExpireDate(expireDate);
			fac.setFacilityCode(facilityCode);
			fac.setFacilityLimit(facilityLimit);
			fac.setFinalPaymAmt(finalPaymAmt);
			fac.setGracePeriod(gracePeriodCode);
			fac.setGracePeriodCode(gracePeriodCode);
			fac.setHostName("IB");
			fac.setInterestBase(interestBase);
			fac.setInterestMode(interestMode);
			fac.setInterestPmtFreq(interestPmtFreq);
			fac.setInterestPmtFreqCode(interestPmtFreqCode);
			fac.setInterestRate(interestRate);
			fac.setLateChargeCode(lateChargeCode);
			fac.setLegalFee(legalFee);
			fac.setLevelNo(levelNo);
			fac.setOfferCode(offerCode);
			fac.setOfferDate(offerDate);
			fac.setPaymentAmount(paymentAmount);
			fac.setPaymentCode(paymentCode);
			fac.setPaymentFreq(paymentFreq);
			fac.setPaymentFreqCode(paymentFreqCode);
			fac.setPrimeRateCeiling(primeRateCeiling);
			fac.setPrimeRateFloor(primeRateFloor);
			fac.setProcessingFee(processingFee);
			fac.setProducType(producType);
			fac.setPurposeCode(purposeCode);
			fac.setRateReviewCode(rateReviewCode);
			fac.setRateReviewDate(rateReviewDate);
			fac.setRateReviewTerm(rateReviewTerm);
			fac.setRcdOveride(rcdOveride);
			fac.setRecFromAppCenter(recFromAppCenter);
			fac.setRevolving(revolving);
			fac.setSecureCode(secureCode);
			fac.setSenttoAppDate(senttoAppDate);
			fac.setSeqNo(seqNo);
			fac.setShareLimitInd(shareLimitInd);
			fac.setTeller(teller);
			fac.setTerm(term);
			fac.setTermCode(termCode);
			fac.setVarCode(varCode);
			fac.setVariance(variance);
			fac.setYearBase(yearBase);
			fac.setRateNumber(rateNumber);
			Messages retMessage = null;
			// Generate so facility truoc khi tao

			try {
				PreAddFac_82303 preAddFac = new PreAddFac_82303();
				preAddFac.applicationNo = applicationNo;
				preAddFac.branch = branchCode;
				preAddFac.cif = cifNo;
				preAddFac.currencyType = currencyType;
				preAddFac.facilityCode = facilityCode;
				preAddFac.hostname = "ESBHost";
				preAddFac.productType = producType;
				preAddFac.teller = teller;

				retMessage = cbsService.sendMessage(channel,
						preAddFac.toArray());

				// msgInf.rsp_date =
				// AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
				// msgInf.rsp_time =
				// AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
				// buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
				// + "preFacility/out/", retMessage, msgInf);
				//
				// msgInf.hostRequestMsg = retMessage == null ? "" : retMessage
				// .getRequestMsg();
				// msgInf.hostResponseMsg = retMessage == null ? "" : retMessage
				// .getResponseMsg();
				// neu generate thanh cong thi thuc hien tao Facility

				if ("0".equals(retMessage.getErrCode())
						|| "/0000000".equals(retMessage.getErrCode())) {
					FacilityMaintain_MB88303 facSvr = new FacilityMaintain_MB88303();

					fac.setAFCNo(retMessage.getArrString()[72]);
					fac.setSeqNo(retMessage.getArrString()[69]);

					facSvr.fac = fac;

					retMessage = cbsService.sendMessage(channel,
							facSvr.toArray());

					//

					// msgInf.rsp_date = AppUtils
					// .now(Global.DEF_FORMAT_LOGMSG_DATE);
					// msgInf.rsp_time = AppUtils
					// .now(Global.DEF_FORMAT_LOGMSG_TIME);
					//
					// buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					// + "facility/out/", retMessage, msgInf);
					//
					// msgInf.hostRequestMsg = retMessage == null ? ""
					// : retMessage.getRequestMsg();
					// msgInf.hostResponseMsg = retMessage == null ? ""
					// : retMessage.getResponseMsg();

					if ("0".equals(retMessage.getErrCode())
							|| "/0000000".equals(retMessage.getErrCode())) {

						seqNo = retMessage.getArrString()[69];
						// tao thanh cong thi lock facility lai de duyet
						LockFacilityLoan_MB86303 lockFac = new LockFacilityLoan_MB86303();
						lockFac.appNo = applicationNo;
						lockFac.branch = branchCode;
						lockFac.branchCode = branchCode;
						lockFac.facilityCode = facilityCode;
						lockFac.hostName = "127.0.0.1";
						lockFac.seqNo = seqNo;
						lockFac.teller = teller;

						retMessage = cbsService.sendMessage(channel,
								lockFac.toArray());

						// msgInf.rsp_date = AppUtils
						// .now(Global.DEF_FORMAT_LOGMSG_DATE);
						// msgInf.rsp_time = AppUtils
						// .now(Global.DEF_FORMAT_LOGMSG_TIME);
						// buildMsgFromReturnObject(outMessage,
						// Global.XML_RES_BODY + "lockFacility/out/",
						// retMessage, msgInf);
						//
						// msgInf.hostRequestMsg = retMessage == null ? ""
						// : retMessage.getRequestMsg();
						// msgInf.hostResponseMsg = retMessage == null ? ""
						// : retMessage.getResponseMsg();

						if ("0".equals(retMessage.getErrCode())
								|| "/0000000".equals(retMessage.getErrCode())) {
							// Duyet facility
							facSvr = new FacilityMaintain_MB88303();
							rcdOveride = "YYYYYYYYYYYYYYYYYYYYYY";
							facSvr.fac = fac;
							fac.setAFCStatus("A");
							fac.setAppBy(appBy);
							fac.setApprovedDate(approvedDate);

							String[] inputArr = retMessage.getArrString();

							fac.setTeller(teller);

							fac.setBranchCode(branchCode);

							fac.setApplicationNo(inputArr[67]);
							fac.setFacilityCode(inputArr[68]);
							fac.setSeqNo(inputArr[69]);
							fac.setLevelNo(inputArr[70]);
							fac.setCifNo(inputArr[71]);
							fac.setAFCNo(inputArr[72]);
							fac.setProducType(inputArr[73]);
							fac.setAFCStatus("A");
							fac.setAppliedDate(inputArr[75]);
							fac.setCurrencyType(inputArr[76]);
							fac.setAppliedAmount(convertAmountData(inputArr[77]));
							fac.setPurposeCode(inputArr[78]);
							// fac.purposeCode = "51"
							fac.setRevolving(inputArr[80]);
							fac.setaBalIndicator(inputArr[81]);
							fac.setSenttoAppDate(inputArr[82]);
							fac.setRecFromAppCenter(inputArr[83]);
							fac.setAppDate(inputArr[84]);
							fac.setAppBy(appBy);
							fac.setOfferDate(inputArr[86]);
							fac.setAcceptedDate(inputArr[87]);
							fac.setAppAmount(convertAmountData(inputArr[88]));
							fac.setAvailabelLimit(convertAmountData(inputArr[127]));// chua
																					// ro
							fac.setFacilityLimit(convertAmountData(inputArr[89]));
							fac.setAvailableBalance(convertAmountData(inputArr[90]));
							fac.setInterestBase(inputArr[91]);
							fac.setInterestMode(inputArr[92]);
							fac.setYearBase(inputArr[93]);
							fac.setTerm(inputArr[94]);
							fac.setTermCode(inputArr[95]);
							fac.setInterestRate("0" + inputArr[97]); // convertPercentData(inputArr[97])

							fac.setRateReviewDate(inputArr[100]);
							fac.setRateReviewTerm(inputArr[101]);
							fac.setRateReviewCode(inputArr[102]);
							fac.setPrimeRateFloor("0" + inputArr[103]);// convertPercentData(inputArr[103])
							fac.setPrimeRateCeiling("0" + inputArr[104]); // convertPercentData(inputArr[104])
							fac.setPaymentCode(inputArr[105]);
							fac.setPaymentFreq(inputArr[106]);
							fac.setPaymentFreqCode(inputArr[107]);
							fac.setInterestPmtFreq(inputArr[108]);
							fac.setInterestPmtFreqCode(inputArr[109]);
							fac.setPaymentAmount(convertAmountData(inputArr[110]));
							fac.setFinalPaymAmt(convertAmountData(inputArr[111]));
							fac.setLateChargeCode(inputArr[112]);
							fac.setGracePeriod(inputArr[113]);
							fac.setGracePeriodCode(inputArr[114]);
							fac.setExpireDate(inputArr[115]);
							fac.setProcessingFee(convertAmountData(inputArr[116]));
							fac.setLegalFee(convertAmountData(inputArr[117]));
							fac.setCifName(inputArr[118]);
							fac.setBranchTCode(inputArr[120]);
							fac.setOfferCode(inputArr[121]);
							fac.setShareLimitInd(inputArr[122]);
							fac.setBranchTHCode(inputArr[125]); // decision
																// center
							fac.setSecureCode(inputArr[126]);
							fac.setEstimateLose(convertAmountData(inputArr[129]));
							fac.setApprovedDate(approvedDate);
							fac.setRcdOveride(rcdOveride);

							retMessage = cbsService.sendMessage(channel,
									facSvr.toArray());

							//

						}

					}
					msgInf.rsp_date = AppUtils
							.now(Global.DEF_FORMAT_LOGMSG_DATE);
					msgInf.rsp_time = AppUtils
							.now(Global.DEF_FORMAT_LOGMSG_TIME);

					buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
							+ "facility/out/", retMessage, msgInf);

					msgInf.hostRequestMsg = retMessage == null ? ""
							: retMessage.getRequestMsg();
					msgInf.hostResponseMsg = retMessage == null ? ""
							: retMessage.getResponseMsg();

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			msgInf.teller_id = teller;
			msgInf.approver_id = teller;
			msgInf.ref_channel = channel;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private String convertAmountData(String input) {

		if (!"".equals(input)) {

			DecimalFormat df = new DecimalFormat("#");
			input = df.format(BigDecimal.valueOf(Double.valueOf(input) * 100));
			// if(input[0] == '.') {
			// input = 0 + input;
			// }
			return input.replace(".", "");
		} else {
			return "";
		}
	}
}
