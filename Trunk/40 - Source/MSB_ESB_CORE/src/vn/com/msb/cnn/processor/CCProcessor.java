package vn.com.msb.cnn.processor;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import vn.com.msb.as400.dsp.ATMMessageFactory;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.*;

public class CCProcessor {
	static {
		try {
			DOMConfigurator.configure(Global.APP_LOG_FILEPATH);
		} catch (Exception ex) {
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Messages cardChangeServiceName(HostParameter in_hostParam, String channel, String teller, String bankCode, String cardNumber, String serviceName, String cifNumber,String hostIP) {

		Messages cardMainternanceMessage = null;

		try {
			// Search Card info
			String[] arrString = ATMMessageFactory.createCardInquiryMessage(teller, bankCode, cardNumber);
			HostParameter hostParam = in_hostParam;// HostParameter.findByNameAndValue("port_number",
													// channel.toUpperCase());
			Logger.getLogger(CCProcessor.class).info("CC: Search Card ...");
			int port = Integer.parseInt(hostParam.getParam1());

			Messages cardMessage = HostProcessor.sendMessage(port, arrString, channel.toUpperCase(),hostIP);
			if ("0" != cardMessage.getErrCode()) {
				return cardMessage;
			}

			String[] result2 = cardMessage.getArrString();

			String[] addresses = { result2[343], result2[344], result2[345], result2[346] };
			String annualFee = result2[389];
			String vipSecurityQuest = "";

			if (result2.length > 391) {
				vipSecurityQuest = result2[391];
			}

			String accountStr = getAccountStrFromMessage(result2);

			// Change Services
			String[] arrChangeServices = ATMMessageFactory.createCardMaintenanceMessage(teller, bankCode, cardNumber, serviceName, addresses, vipSecurityQuest, annualFee,
					accountStr);

			Logger.getLogger(CCProcessor.class).info("CC: Change Services ...");
			cardMainternanceMessage = HostProcessor.sendMessage(port, arrChangeServices, channel.toUpperCase(),hostIP);

		} catch (Exception ex) {
			Logger.getLogger(CCProcessor.class).info("CC: Change Services error ");
			Logger.getLogger(CCProcessor.class).info(ex.toString());
		}

		return cardMainternanceMessage;
	}

	public static Messages markHotCard(HostParameter in_hostParam, String channel, String teller, String bankCode, String cardNumber, String typeLock, String comment,String hostIP) {

		Messages markHotCardMessage = null;

		try {
			// Search Card info
			String[] arrString = ATMMessageFactory.createSearchCardMessage(teller, bankCode, cardNumber, HostConstants.MARK_HOT);

			HostParameter hostParam = in_hostParam;// HostParameter.findByNameAndValue("port_number",
													// channel.toUpperCase());
			Logger.getLogger(HostParameter.class).info("CC: Search Card mark hot...");
			int port = Integer.parseInt(hostParam.getParam1());

			Messages cardMessage = HostProcessor.sendMessage(port, arrString, channel.toUpperCase(),hostIP);
			if ("0" != cardMessage.getErrCode()) {
				cardMessage.setErrCode("100102");
				cardMessage.setDescription("Invalid card status");
				return cardMessage;
			}

			String[] result2 = cardMessage.getArrString();
			if (result2.length < 99) {
				cardMessage.setErrCode("100102");
				cardMessage.setDescription("Invalid card status");
				return cardMessage;
			}

			String codeCus, CMT, ID;
			codeCus = result2[86];
			CMT = result2[87];
			ID = result2[88];

			//
			String[] arrMarkHotString = ATMMessageFactory.createMarkHotCardMessage(teller, bankCode, cardNumber, typeLock, cardNumber, ID, typeLock, comment);
			Logger.getLogger(CCProcessor.class).info("CC: Mark hot Card ...");
			markHotCardMessage = HostProcessor.sendMessage(port, arrMarkHotString, channel.toUpperCase(),hostIP);

		} catch (Exception ex) {
			Logger.getLogger(CCProcessor.class).info("CC: Mark hot error ");
			Logger.getLogger(CCProcessor.class).info(ex.toString());
		}

		return markHotCardMessage;
	}

	public static Messages reActiveCard(HostParameter in_hostParam, String channel, String teller, String bankCode, String cardNumber,String hostIP) {

		Messages reActiveCardMessage = null;

		try {
			// Search Card info
			String[] arrString = ATMMessageFactory.createCardInquiryMessage(teller, bankCode, cardNumber);

			HostParameter hostParam = in_hostParam;// HostParameter.findByNameAndValue("port_number",
													// channel.toUpperCase());
			Logger.getLogger(CCProcessor.class).info("CC: Search Card reActive...");
			int port = Integer.parseInt(hostParam.getParam1());

			Messages cardMessage = HostProcessor.sendMessage(port, arrString, channel.toUpperCase(),hostIP);
			if ("0" != cardMessage.getErrCode()) {
				cardMessage.setErrCode("100102");
				cardMessage.setDescription("Invalid card status");
				return cardMessage;
			}

			String[] result2 = cardMessage.getArrString();
			if (result2.length < 333) {
				cardMessage.setErrCode("100102");
				cardMessage.setDescription("Invalid card status");
				return cardMessage;
			}
			String oldStatus, typeCard, skillCard, hanmucDV, codeCus, CMT, ID;

			skillCard = result2[78];
			hanmucDV = result2[83];
			oldStatus = result2[71];
			typeCard = result2[77];
			codeCus = result2[330];
			CMT = result2[331];
			ID = result2[332];

			//
			String[] arrReActiveCardString = ATMMessageFactory.createReactiveCardMessage(teller, bankCode, cardNumber, oldStatus, typeCard, skillCard, hanmucDV, codeCus, CMT, ID);
			Logger.getLogger(CCProcessor.class).info("CC: Reactive Card ...");
			reActiveCardMessage = HostProcessor.sendMessage(port, arrReActiveCardString, channel.toUpperCase(),hostIP);

		} catch (Exception ex) {
			Logger.getLogger(CCProcessor.class).info("CC: ReActive error ");
			Logger.getLogger(CCProcessor.class).info(ex.toString());
		}

		return reActiveCardMessage;
	}

	@SuppressWarnings("unused")
	private static String getAccountStrFromAccountArray(String[] accountCurrency, String[] accountNumber, String[] accountType, String[] accountBranch, String[] accountUsage) {
		String accountStr = "";
		for (int i = 0; i < 32; i++) {
			if (i < accountNumber.length) {
				accountStr += accountNumber[i] + "|" + accountType[i] + "|" + accountUsage[i] + "|" + accountCurrency[i] + "|" + accountBranch[i] + "||";
			} else {
				accountStr += "||||||";
			}
		}
		return accountStr;
	}

	private static String getAccountStrFromMessage(String[] result2) {
		StringBuilder accountStr = new StringBuilder("");
		boolean endAcc = false;
		for (int k = 91; k < 283; k++) {
			String value = result2[k];
			if ("0000000000000000000".equals(value)) {
				value = "";
				endAcc = true;
			}
			if (value.length() == 19) {
				value = ('0' == value.charAt(4)) ? value.substring(value.length() - 14) : value.substring(value.length() - 15);
			}
			if ("00".equals(value)) {
				value = "";
			}
			if (endAcc == true && "00000".equals(value)) {
				value = "";
			}
			accountStr.append(value).append('|');
		}
		return accountStr.toString();
	}

}
