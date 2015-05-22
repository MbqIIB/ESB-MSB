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

import seatechit.esb.business.cbs.AbTransferMsg;
import seatechit.esb.business.cbs.ITransferMsg;
import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.AppUtils;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.Global;
import vn.com.m1tech.AS400.Factory.entity.Collateral_MB87202;
import vn.com.m1tech.AS400.entity.Collateral;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbMessage;

/**
 * @author quanld
 * 
 */
public class CBS065 extends AbTransferMsg implements ITransferMsg {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Create collateral 
	 */
	@Override
	public void buildMessage(MbMessage inMessage, MbMessage outMessage,
			MessageInfo msgInf, ArrayList<HostParameter> paramList) {
		// TODO Auto-generated method stub

		try {
			cbsService.setParamList(paramList);

			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/channel");
			String teller = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/teller");

			msgInf.req_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.req_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);

			String branch = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/branch");

			String hostName = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/hostName");
			String collateralName = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/collateralName"); // Account type
			String cifName = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/cifName"); // Type of
																	// entry
			String collateralDesc = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/collateralDesc"); // Check Amount

			String deposit = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/deposit"); // Payee name
			String officeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/officeCode"); // Stop
																				// charge
			String fdAcc = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/fdAcc"); // Expiration
																// date

			String countryCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/countryCode"); // Stop/Hold
																				// remarks:

			String depositAmount = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "collateral/depositAmount"); // Block for Account
															// number
			String curency = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/curency"); // Block for
																	// account
																	// type
			String depositTenureNum = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/depositTenureNum");// "*TRADE", "*EA"
																// // Stop/Hold
																// code
			String depositTenureType = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/depositTenureType"); // Penalty amount
			String cifNum = ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_REQ_BODY + "collateral/cifNum");
			String interestRate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/interestRate");
			String relationShip = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/relationShip");
			String issueDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/issueDate");
			String cashValueSale = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "collateral/cashValueSale");
			String maturityDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/maturityDate");
			String reviewDate = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/reviewDate");
			String markedValue = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/markedValue");
			String lienOfficeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/lienOfficeCode");
			String issuingInstitution = ElementUtils
					.getXMLElementInStringValue(inMessage, Global.XML_REQ_BODY
							+ "collateral/issuingInstitution");
			String depositName1 = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/depositName1");
			String depositName2 = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/depositName2");
			String depositName3 = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/depositName3");
			String dudateInstruction = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/dudateInstruction");
			String description = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/description");
			String collateralRatio = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY
							+ "collateral/collateralRatio");
			String investment = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/investment");
			String bankUsage = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/bankUsage");
			String regPlace = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/regPlace");
			String ownerPlace = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/ownerPlace");
			String notaryPublic = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/notaryPublic");
			
			String calaCode= ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/calaCode");
			String securegDate= ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/securegDate");
			String securegExp = ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/securegExp");
			String secureReqNo= ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/secureReqNo");
			String ownerRightDate= ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/ownerRightDate");
		
			String ownerNum= ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/ownerNum");
			String ownerExtDate= ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_REQ_BODY + "collateral/ownerExtDate");

			Collateral calla = new Collateral();

			calla.setBranch(branch);
			calla.setTeller(teller);

			calla.setHostName(hostName);
			calla.setCollateralName(collateralName); // Account type
			calla.setCifName(cifName); // Type of entry
			calla.setCollateralDesc(collateralDesc); // Check Amount
			calla.setDeposit(deposit); // Payee name
			calla.setOfficeCode(officeCode); // Stop charge
			calla.setFdAcc(fdAcc); // Expiration date
			calla.setCountryCode(countryCode); // Stop/Hold remarks:
			calla.setDepositAmount(depositAmount); // Block for Account number
			calla.setCurency(curency); // Block for account type
			calla.setDepositTenureNum(depositTenureNum);// "*TRADE", "*EA" //
														// Stop/Hold code
			calla.setDepositTenureType(depositTenureType); // Penalty amount
			calla.setCifNum(cifNum);
			calla.setInterestRate(interestRate);
			calla.setRelationShip(relationShip);
			calla.setIssueDate(issueDate);
			calla.setCashValueSale(cashValueSale);
			calla.setMaturityDate(maturityDate);
			calla.setReviewDate(reviewDate);
			calla.setMarkedValue(markedValue);
			calla.setLienOfficeCode(lienOfficeCode);
			calla.setIssuingInstitution(issuingInstitution);
			calla.setDepositName1(depositName1);
			calla.setDepositName2(depositName2);
			calla.setDepositName3(depositName3);
			calla.setDudateInstruction(dudateInstruction);
			calla.setDescription(description);
			calla.setCollateralRatio(collateralRatio);
			calla.setInvestment(investment);
			calla.setBankUsage(bankUsage);
			calla.setRegPlace(regPlace);
			calla.setOwnerPlace(ownerPlace);
			calla.setNotaryPublic(notaryPublic);
			
			calla.setCalaCode(calaCode);
			calla.setSecuregDate(securegDate);
			calla.setSecuregExp(securegExp) ;
			calla.setSecureReqNo(secureReqNo);
			calla.setOwnerRightDate(ownerRightDate);
			calla.setOwnerNum(ownerNum);
			calla.setOwnerExtDate(ownerExtDate);

			
			Collateral_MB87202 colaMsg=new Collateral_MB87202();
			colaMsg.calla= calla;
			Messages retMessage = cbsService.sendMessage(channel, colaMsg.toArray());

			msgInf.rsp_date = AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE);
			msgInf.rsp_time = AppUtils.now(Global.DEF_FORMAT_LOGMSG_TIME);
			buildMsgFromReturnObject(outMessage, Global.XML_RES_BODY
					+ "collateral/out/", retMessage, msgInf);

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
