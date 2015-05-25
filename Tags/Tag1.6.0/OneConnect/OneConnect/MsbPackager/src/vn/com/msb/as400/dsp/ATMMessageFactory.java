package vn.com.msb.as400.dsp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ftl.util.StringUtil;

public class ATMMessageFactory {
	public ATMMessageFactory() {
		super();
	}

	public static String[] createLinkCardMessage(String teller,
			String branchCode, String strCardNumber, String strAccountTag,
			String strAccountType, String strUsage, String strCurrency,
			String strBranchTag, String strCIFNo, String strIDNumber,
			String strIDType, String strName, String strAddress,
			String strProduct, String strAnnualFee, String strVIP,
			String strAddressLine2, String strAddressLine3,
			String strAddressLine4) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_95025I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "99000"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "99000"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = ""; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = strCardNumber; // card Number
		strValue[69] = "1";
		strValue[91] = strAccountTag;
		strValue[92] = strAccountType;
		strValue[93] = strUsage;
		strValue[94] = strCurrency;
		strValue[95] = strBranchTag;
		strValue[96] = "00";

		// strValue[314] = "N"; just only use for master card

		strValue[330] = StringUtil.lpad(strCIFNo, 19, '0');
		strValue[331] = strIDNumber;
		strValue[332] = strIDType;
		strValue[337] = strName;
		// strValue[338] = strName; just only use for master card
		strValue[339] = strName;

		strValue[343] = strAddress;
		strValue[344] = strAddressLine2;
		strValue[345] = strAddressLine3;
		strValue[346] = strAddressLine4;
		strValue[366] = strBranchTag;
		strValue[382] = "1";
		strValue[388] = strProduct;
		strValue[389] = strAnnualFee;
		strValue[391] = strVIP;

		return strValue;
	}

	public static String[] createLinkMasterCardMessage(String teller,
			String branchCode, String strAccountTag, String strAccountType,
			String strUsage, String strCurrency, String strBranchTag,
			String strCIFNo, String strIDNumber, String strIDType,
			String strName, String strAddress, String strProduct,
			String strAnnualFee, String strVIP, String strAddressLine2,
			String strAddressLine3, String strAddressLine4) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_95025I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "99000"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "99000"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = ""; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[69] = "1";
		strValue[91] = strAccountTag;
		strValue[92] = strAccountType;
		strValue[93] = strUsage;
		strValue[94] = strCurrency;
		strValue[95] = strBranchTag;
		strValue[96] = "00";

		strValue[314] = "N"; // just only use for master card

		strValue[330] = StringUtil.lpad(strCIFNo, 19, '0');
		strValue[331] = strIDNumber;
		strValue[332] = strIDType;
		strValue[337] = strName;
		strValue[338] = strName; // just only use for master card
		strValue[339] = strName;

		strValue[343] = strAddress;
		strValue[344] = strAddressLine2;
		strValue[345] = strAddressLine3;
		strValue[346] = strAddressLine4;
		strValue[366] = strBranchTag;
		strValue[382] = "1";
		strValue[388] = strProduct;
		strValue[389] = strAnnualFee;
		strValue[391] = strVIP;

		return strValue;
	}

	public static String[] createCardInquiryMessage(String teller,
			String branchCode, String cardNumber) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_95000I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "95000"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "95000"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = cardNumber;
		strValue[382] = "1"; // 95000
		// strValue[72] = "1"; //95013
		// strValue[73] = "1"; //95013
		return strValue;
	}

	public static String[] createCardActivationMessage(String teller,
			String branchCode, String cardNumber, String cifNumber,
			String idNo, String idType) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_99067I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "99067"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "99067"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = cardNumber;
		strValue[69] = "1";
		strValue[330] = StringUtil.lpad(cifNumber, 19);
		strValue[331] = idNo;
		strValue[332] = idType;
		strValue[382] = "1";

		return strValue;
	}

	public static String[] createCardMaintenanceMessage(String tellerId,
			String branchCode, String cardNumber, String serviceName,
			String[] addresses, String vipSecurityQuest, String annualFee,
			String accountStr) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_99002I
				.getFieldDefinitionList().length];
		String strHostName = "";		
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[24] = tellerId; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "99002"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = tellerId; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "99002"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = cardNumber;
		strValue[69] = "1";
		strValue[83] = serviceName;
//		strValue[91] = accountStr;
		// from 91 -283
		String[] arr = accountStr.split("\\|");		
		for(int i = 0; i<arr.length; i++){
			strValue[91+i] = arr[i]; 
		}
		
		strValue[343] = addresses[0];
		strValue[344] = addresses[1];
		strValue[345] = addresses[2];
		strValue[346] = addresses[3];
		strValue[382] = "1";
		strValue[389] = annualFee;
		strValue[391] = vipSecurityQuest;

		return strValue;
	}

	public static String[] createSearchCardMessage(String teller,
			String branchCode, String cardNumber, String status) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_95013I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "95013"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "95013"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[66] = status;
		strValue[68] = cardNumber;
		strValue[72] = "1"; // 95013
		strValue[73] = "1"; // 95013
		return strValue;
	}

	public static String[] createMarkHotCardMessage(String teller,
			String branchCode, String card, String typeLock, String cifNumber,
			String customerID, String idType, String comment) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_99010I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "99010"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "99010"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = card;
		strValue[69] = "1"; //
		strValue[82] = typeLock; //
		strValue[330] = cifNumber; //
		strValue[331] = customerID; //
		strValue[332] = idType; //
		strValue[351] = comment; //
		strValue[382] = "1"; //
		return strValue;
	}

	public static String[] createReactiveCardMessage(String teller,
			String branchCode, String card, String oldStatus, String typeCard,
			String skillCard, String hanmucDV, String cifNumber,
			String customerID, String idType) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_99011I
				.getFieldDefinitionList().length];
		String strHostName = null;

		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSATMNTFNC"; // Scenario Number
		strValue[23] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "99011"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BA1";
		strValue[37] = "EBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "99011"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = card;
		strValue[69] = "1"; //
		strValue[71] = oldStatus; //
		strValue[77] = typeCard; //
		strValue[78] = skillCard; //
		strValue[80] = "1"; //
		strValue[83] = hanmucDV; //
		strValue[330] = cifNumber; //
		strValue[331] = customerID; //
		strValue[332] = idType; //
		strValue[382] = "1"; //
		return strValue;
	}

}
