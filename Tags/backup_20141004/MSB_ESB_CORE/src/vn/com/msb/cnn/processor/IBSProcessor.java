package vn.com.msb.cnn.processor;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import vn.com.msb.as400.dsp.MessageFactory;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.Global;
import vn.com.msb.cnn.utils.Messages;

public class IBSProcessor {
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
	public static Messages getCustomerInfoFullByCertCode(HostParameter in_hostParam,String channel, String teller, String hostName, String bankCode, String cert_code,String hostIP) {
		Messages retMessage = null;
		Messages retEmailMessage;
		try {
			String cif_no = null;

			String[] retArrCustomerInfo = new String[19];
			HostParameter hostParam = in_hostParam;//HostParameter.findByNameAndValue("port_number", channel.toUpperCase());

			// Get Cif number by ID
			Logger.getLogger(Messages.class).info("IBS: Get Cif number information ...");
			Logger.getLogger(Messages.class).info("Params: " + teller + " " + hostName + " " + bankCode + " " + cert_code);
			retMessage = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), MessageFactory.searchCustomerByIDMessage(teller, hostName, bankCode, cert_code),
					channel.toUpperCase(),hostIP);

			if (retMessage != null) {
				if ("0" == retMessage.getErrCode()) {
					int startPos = 68;
					int recordLength = 11;
					ArrayList<String[]> retArrResponseRow = getListOfResponseRow(retMessage.getArrString(), startPos, recordLength);
					if (retArrResponseRow.size() > 0) {
						cif_no = retArrResponseRow.get(0)[5];
					}

					if (cif_no.length() > 0) {
						// Get cif information 16106
						Logger.getLogger(Messages.class).info("IBS: Get Cif information ...");
						Logger.getLogger(Messages.class).info("Params: " + teller + " " + hostName + " " + bankCode + " " + cif_no);
						retMessage = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()),
								MessageFactory.searchCustomerInformationByCifMessage(teller, hostName, bankCode, cif_no), channel.toUpperCase(),hostIP);

						if (retMessage != null) {
							if ("0" == retMessage.getErrCode()) {
								startPos = 67;
								recordLength = retMessage.getArrString().length - startPos;
								retArrResponseRow = getListOfResponseRow(retMessage.getArrString(), startPos, recordLength);
								if (retArrResponseRow.size() > 0) {
									retArrCustomerInfo[0] = retArrResponseRow.get(0)[0];// cif_no
									retArrCustomerInfo[1] = retArrResponseRow.get(0)[9];// cif_name
									retArrCustomerInfo[2] = retArrResponseRow.get(0)[1];// status
									retArrCustomerInfo[3] = retArrResponseRow.get(0)[3];// bank_no
									retArrCustomerInfo[4] = retArrResponseRow.get(0)[40];// country
									retArrCustomerInfo[5] = retArrResponseRow.get(0)[70];// gender
									retArrCustomerInfo[6] = retArrResponseRow.get(0)[62];// cert_code
									retArrCustomerInfo[7] = retArrResponseRow.get(0)[63];// cert_type
									retArrCustomerInfo[8] = retArrResponseRow.get(0)[64];// birth_date
									retArrCustomerInfo[9] = retArrResponseRow.get(0)[65];// birth_place
									retArrCustomerInfo[10] = retArrResponseRow.get(0)[71];// Individual
									retArrCustomerInfo[11] = retArrResponseRow.get(0)[93];// telephone
									retArrCustomerInfo[12] = retArrResponseRow.get(0)[88];// address1
									retArrCustomerInfo[13] = retArrResponseRow.get(0)[89];// address2
									retArrCustomerInfo[14] = retArrResponseRow.get(0)[90];// address3
									retArrCustomerInfo[18] = retArrResponseRow.get(0)[4];// branch_no

									// gan defaul
									retArrCustomerInfo[15] = "";
									retArrCustomerInfo[16] = "";
									retArrCustomerInfo[17] = "";

									// Email,phone
									// Logger.getLogger(this).info("IBS: Get e-contact Information ...");
									// Logger.getLogger(this).info("Params: " +
									// teller + " " + hostName + " " + bankCode
									// + " " + cif_no);
									retEmailMessage = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()),
											MessageFactory.searchMobileByCifMessage("", teller, bankCode, cif_no), channel.toUpperCase(),hostIP);

									if (retEmailMessage != null) {
										if ("0" == retEmailMessage.getErrCode()) {
											startPos = 69;
											recordLength = 12;
											retArrResponseRow = getListOfResponseRow(retEmailMessage.getArrString(), startPos, recordLength);
											int intLastMPRecord = -1;
											int intLastMPSequenceNumber = -1;
											int intLastEMRecord = -1;
											int intLastEMSequenceNumber = -1;
											for (int i = 0; i < retArrResponseRow.size(); i++) {
												if (retArrResponseRow.get(i)[1].equals("MP")) {
													if (Integer.parseInt(retArrResponseRow.get(i)[0]) > intLastMPSequenceNumber) {
														intLastMPSequenceNumber = Integer.parseInt(retArrResponseRow.get(i)[0]);
														intLastMPRecord = i;
														retArrCustomerInfo[16] = retArrResponseRow.get(intLastMPRecord)[2];// phone
													}
												}
												if (retArrResponseRow.get(i)[1].equals("EM")) {
													if (Integer.parseInt(retArrResponseRow.get(i)[0]) > intLastEMSequenceNumber) {
														intLastEMSequenceNumber = Integer.parseInt(retArrResponseRow.get(i)[0]);
														intLastEMRecord = i;
														retArrCustomerInfo[15] = retArrResponseRow.get(intLastEMRecord)[2];// email
													}
												}
											}
										}
										retMessage.setArrString(retArrCustomerInfo);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(Messages.class).info("IBS: Error ");
			ex.printStackTrace();
		}
		return retMessage;
	}

	public static Messages getCustomerInfoFullByCifNo(HostParameter in_hostParam, String channel, String teller, String hostName, String bankCode, String cif_no,String hostIP) {
		Messages retMessage = null;
		Messages retEmailMessage = null;

		String[] retArrCustomerInfo = new String[19];

		try {
			HostParameter hostParam = in_hostParam; // HostParameter.findByNameAndValue("port_number",
													// channel.toUpperCase());
			// Logger.getLogger(this).info ("IBS: Get Cif information ...")
			// Logger.getLogger(this).info ("Params: " + teller + " " + hostName
			// + " " + bankCode +" " +cif_no);
			
			//kienvt_them de test
			//hostParam.setParam1("4105");
			//hostIP="10.2.1.1";
			//
			
			retMessage = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()),
					MessageFactory.searchCustomerInformationByCifMessage(teller, hostName, bankCode, cif_no), channel.toUpperCase(),hostIP);

			if (retMessage != null) {
				if ("0" == retMessage.getErrCode()) {
					int startPos = 67;
					int recordLength = retMessage.getArrString().length - startPos;
					ArrayList<String[]> retArrResponseRow = getListOfResponseRow(retMessage.getArrString(), startPos, recordLength);
					if (retArrResponseRow.size() > 0) {
						retArrCustomerInfo[0] = retArrResponseRow.get(0)[0];// cif_no
						retArrCustomerInfo[1] = retArrResponseRow.get(0)[9];// cif_name
						retArrCustomerInfo[2] = retArrResponseRow.get(0)[1];// status
						retArrCustomerInfo[3] = retArrResponseRow.get(0)[3];// bank_no
						retArrCustomerInfo[4] = retArrResponseRow.get(0)[40];// country
						retArrCustomerInfo[5] = retArrResponseRow.get(0)[70];// gender
						retArrCustomerInfo[6] = retArrResponseRow.get(0)[62];// cert_code
						retArrCustomerInfo[7] = retArrResponseRow.get(0)[63];// cert_type
						retArrCustomerInfo[8] = retArrResponseRow.get(0)[64];// birth_date
						retArrCustomerInfo[9] = retArrResponseRow.get(0)[65];// birth_place
						retArrCustomerInfo[10] = retArrResponseRow.get(0)[71];// Individual
						retArrCustomerInfo[11] = retArrResponseRow.get(0)[93];// telephone
						retArrCustomerInfo[12] = retArrResponseRow.get(0)[88];// address1
						retArrCustomerInfo[13] = retArrResponseRow.get(0)[89];// address2
						retArrCustomerInfo[14] = retArrResponseRow.get(0)[90];// address3
						retArrCustomerInfo[18] = retArrResponseRow.get(0)[4];// branch_no

						// gan defaul
						retArrCustomerInfo[15] = "";
						retArrCustomerInfo[16] = "";
						retArrCustomerInfo[17] = "";
						// Email,phone
						// Logger.getLogger(this).info
						// ("IBS: Get e-contact Information ...");
						// Logger.getLogger(this).info ("Params: " + teller +
						// " " + hostName + " " + bankCode +" " +cif_no);
						retEmailMessage = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), MessageFactory.searchMobileByCifMessage("", teller, bankCode, cif_no),
								channel.toUpperCase(),hostIP);

						if (retEmailMessage != null) {
							if ("0" == retEmailMessage.getErrCode()) {
								startPos = 69;
								recordLength = 12;
								retArrResponseRow = getListOfResponseRow(retEmailMessage.getArrString(), startPos, recordLength);
								int intLastMPRecord = -1;
								int intLastMPSequenceNumber = -1;
								int intLastEMRecord = -1;
								int intLastEMSequenceNumber = -1;
								for (int i = 0; i < retArrResponseRow.size(); i++) {
									if (retArrResponseRow.get(i)[1].equals("MP")) {
										if (Integer.parseInt(retArrResponseRow.get(i)[0]) > intLastMPSequenceNumber) {
											intLastMPSequenceNumber = Integer.parseInt(retArrResponseRow.get(i)[0]);
											intLastMPRecord = i;
											retArrCustomerInfo[16] = retArrResponseRow.get(intLastMPRecord)[2];// phone
										}
									}
									if (retArrResponseRow.get(i)[1].equals("EM")) {
										if (Integer.parseInt(retArrResponseRow.get(i)[0]) > intLastEMSequenceNumber) {
											intLastEMSequenceNumber = Integer.parseInt(retArrResponseRow.get(i)[0]);
											intLastEMRecord = i;
											retArrCustomerInfo[15] = retArrResponseRow.get(intLastEMRecord)[2];// email
										}
									}
								}
							}
						}
						retMessage.setArrString(retArrCustomerInfo);
					}
				}
			}
		} catch (Exception ex) {
			// Logger.getLogger(this).info("IBS: Error ");
			ex.printStackTrace();
		}

		return retMessage;
	}

	private static ArrayList<String[]> getListOfResponseRow(final String[] responseMessage, final int startPos, final int recordLength) {
		ArrayList<String[]> retArrString = new ArrayList<String[]>();
		int i = 0;
		while (startPos + recordLength * i < responseMessage.length) {
			int intMissingCharLength = responseMessage.length - (startPos + recordLength * (i + 1));
			String[] currentRecord = new String[recordLength];
			if (intMissingCharLength < 0) {
				System.arraycopy(responseMessage, (startPos + recordLength * i), currentRecord, 0, recordLength + intMissingCharLength);
			} else {
				System.arraycopy(responseMessage, (startPos + recordLength * i), currentRecord, 0, recordLength);

			}
			retArrString.add(currentRecord);
			i++;
		}
		return retArrString;
	}

}
