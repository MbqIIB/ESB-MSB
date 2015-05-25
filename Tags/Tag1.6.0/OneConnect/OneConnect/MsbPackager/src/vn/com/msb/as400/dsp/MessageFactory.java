package vn.com.msb.as400.dsp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.AAEntity;
import vn.com.m1tech.AS400.entity.AccInfo;
import vn.com.m1tech.AS400.entity.Collateral;
import vn.com.m1tech.AS400.entity.CollateralLink;
import vn.com.m1tech.AS400.entity.DefaultMsg;
import vn.com.m1tech.AS400.entity.Fee;
import vn.com.m1tech.AS400.entity.HoldAcc;
import vn.com.m1tech.AS400.entity.HoldLockAcc;
import vn.com.m1tech.AS400.entity.LockAcc;
import vn.com.m1tech.AS400.entity.LockODP;
import vn.com.m1tech.AS400.entity.OdpEntity;
import vn.com.m1tech.AS400.entity.UnHoldAcc;
import vn.com.m1tech.AS400.entity.WithDrawFD;

import com.ftl.util.StringUtil;

public class MessageFactory {
	public MessageFactory() {
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

		strValue[330] = "0000000000000" + strCIFNo;
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

	public static String[] createUpdatingIntroducerCodeInCAMessage(
			String teller, String branchCode, String accountNo,
			String introducerCode, String expense, String accountName) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28901I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "28901"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "28901"; // 45 :
		strValue[46] = "C"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "10"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[67] = accountNo; // 67 :
		strValue[68] = "D"; // 68 :
		strValue[71] = "1"; // 71 :
		strValue[74] = "01"; // 74 :
		strValue[75] = "01"; // 75 :
		strValue[76] = "01"; // 76 :
		strValue[77] = "C"; // 77 :
		strValue[78] = "Y"; // 78 : Cheque allowed
		strValue[80] = introducerCode; // 80 :
		strValue[81] = "N"; // 81 :
		strValue[82] = "N"; // 82 :
		strValue[83] = "N"; // 83 :
		strValue[84] = "N"; // 84 :
		strValue[86] = "0.00"; // 86 :
		strValue[88] = "0.00"; // 88 :
		strValue[89] = "0.00"; // 89 :
		strValue[90] = "0.00"; // 90 :
		strValue[91] = ".00"; // 91 :
		strValue[92] = "000000000000000"; // 92 :
		strValue[93] = "0000000"; // 93 :
		strValue[94] = "0000000"; // 94 :
		strValue[95] = "0000000"; // 95 :
		strValue[96] = "0000000"; // 96 :
		strValue[98] = "C"; // 98 :
		strValue[99] = "1"; // 99 :
		strValue[103] = "0"; // 103 :
		strValue[104] = "00000000000000"; // 104 :
		strValue[107] = "0.00"; // 107 :
		strValue[108] = "+"; // 108 :
		strValue[109] = "0.00"; // 109 :
		strValue[110] = "0.00"; // 110 :
		strValue[114] = expense; // 114 :
		strValue[115] = "N"; // 115 :
		strValue[116] = accountName; // 116 :

		return strValue;
	}

	public static String[] createUpdatingIntroducerCodeInNewCAMessage(
			String teller, String branchCode, String accountNo,
			String introducerCode, String expense, String accountName) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28110I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "28110"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "28110"; // 45 :
		strValue[46] = "C"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[67] = accountNo; // 67 :
		strValue[68] = "D"; // 68 :
		strValue[69] = introducerCode; // 69 :
		strValue[71] = "01"; // 71 :
		strValue[72] = "01"; // 72 :
		strValue[73] = "01"; // 73 :
		strValue[75] = "1"; // 75 :
		strValue[77] = "0.00"; // 77 :
		strValue[78] = "0.00"; // 78 :
		strValue[79] = ".00"; // 79 :
		strValue[80] = "000000000000000"; // 79 :
		strValue[81] = "000000000000000"; // 81 :
		strValue[87] = "0000000000000000000"; // 87 :
		strValue[88] = "0"; // 88 :
		strValue[89] = "N"; // 89 :
		strValue[90] = "N"; // 90 :
		strValue[91] = "N"; // 91 :
		strValue[92] = "N"; // 92 :
		strValue[93] = "0.00"; // 93 :
		strValue[95] = "00000000000000"; // 95 :
		strValue[98] = "0.00"; // 98 :
		strValue[99] = "+"; // 99 :
		strValue[100] = "0.00"; // 100 :
		strValue[101] = "0.00"; // 101 :
		strValue[105] = expense; // 105 :
		strValue[106] = "N"; // 106 :
		strValue[107] = "0.00"; // 107 :
		strValue[108] = accountName; // 108 :

		return strValue;
	}

	public static String[] createLockUpdatingIntroducerCodeInNewCAMessage(
			String teller, String branchCode, String accountNo) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26110I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDINQFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "10"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "26110"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "26110"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "10"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = accountNo; // 63 :
		strValue[64] = "D"; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = accountNo; // 67 :
		strValue[68] = "D"; // 68 :

		return strValue;
	}

	public static String[] createLockUpdatingIntroducerCodeInCAMessage(
			String teller, String branchCode, String accountNo) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26901I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDINQFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "10"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "26901"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "26901"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "10"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = accountNo; // 63 :
		strValue[64] = "D"; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = accountNo; // 67 :
		strValue[68] = "D"; // 68 :

		return strValue;
	}

	public static String[] createLockTranferCifMessage(String teller,
			String branchCode, String sourceCif, String accountToMove) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_16122I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSCFINQFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "16122"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "16122"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = sourceCif; // 67 :
		strValue[68] = "D"; // 68 :
		strValue[69] = accountToMove; // 69 :
		strValue[70] = "P"; // 70 :

		return strValue;
	}

	public static String[] createTranferFromCifToAnotherMessage(String teller,
			String branchCode, String sourceCif, String accountToMove,
			String destinationCif, String destinationName) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_18122I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSCFMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "18122"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "18122"; // 45 :
		strValue[46] = "C"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = sourceCif; // 67 :
		strValue[68] = "D"; // 68 :
		strValue[69] = accountToMove; // 69 :
		strValue[70] = "P"; // 70 :
		strValue[71] = destinationCif; // 71 :
		strValue[72] = destinationName; // 72 :
		strValue[73] = destinationName; // 73 :
		strValue[74] = destinationName; // 74 :
		strValue[75] = "0"; // 75 :
		return strValue;
	}

	public static String[] createCifMessage(String teller, String hostName,
			String branchCode, String strIdNo, String strIdType,
			String strIdDateIssued, String strIdCountryIssued,
			String strIdPlaceIssued, String strSurname, String strAfterSurname,
			String strAddressLine1, String strAddressLine2,
			String strAddressLine3, String strAddressLine4_codeCheckCust,
			String strAddressLine5, String strNationality, String strBirthday,
			String strRaceCode, String strMaritalStatus, String strGender,
			String _2strOccupationCode, String strPlaceOfBirth,
			String strTypeElectronicAddress1, String strTypeElectronicAddress2,
			String strTypeElectronicAddress3, String strElectronicAddress1,
			String strElectronicAddress2, String strElectronicAddress3,
			String hostDate, String strBirthday_Full,
			String strIdDateIssued_Full, String _1strOccupationCode,
			String strProvince) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_17625I
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
		strValue[17] = "BBMBSCFMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "17625"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "17625"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = "Y";
		strValue[69] = "I";
		strValue[70] = strIdNo;
		strValue[71] = strIdType;
		strValue[72] = strIdDateIssued;
		strValue[73] = strIdCountryIssued;
		strValue[75] = strIdPlaceIssued;
		strValue[76] = "N";
		strValue[78] = strSurname;
		strValue[79] = strAfterSurname;
		strValue[80] = strAfterSurname;
		strValue[81] = strAddressLine1;
		strValue[82] = strAddressLine2;
		strValue[83] = strAddressLine3;
		strValue[84] = strAddressLine4_codeCheckCust;
		strValue[85] = strAddressLine5;
		strValue[87] = strProvince;
		strValue[89] = "000000000";
		strValue[90] = strNationality;
		strValue[91] = strBirthday;
		strValue[92] = strRaceCode;
		strValue[93] = strMaritalStatus;
		strValue[94] = "000000";
		strValue[95] = strNationality;
		strValue[96] = strGender;
		strValue[98] = _2strOccupationCode;
		strValue[100] = strPlaceOfBirth;
		strValue[102] = strTypeElectronicAddress1;
		strValue[103] = strTypeElectronicAddress2;
		strValue[104] = strTypeElectronicAddress3;
		strValue[105] = strElectronicAddress1;
		strValue[106] = strElectronicAddress2;
		strValue[107] = strElectronicAddress3;
		strValue[117] = "Y";
		strValue[118] = hostDate;
		strValue[119] = strBirthday_Full;
		strValue[120] = strIdDateIssued_Full;
		strValue[121] = "C";
		strValue[122] = "TN";
		strValue[123] = "I";
		strValue[124] = "I1";
		strValue[125] = "I";
		strValue[126] = _1strOccupationCode;
		strValue[127] = _2strOccupationCode;
		strValue[129] = "I";

		return strValue;
	}

	public static String[] createCifCorpMessage(String teller, String hostName,
			String branchCode, String strIdNo, String strIdType,
			String strIdDateIssued, String strIdCountryIssued,
			String strIdPlaceIssued, String strSurname, String strAfterSurname,
			String strAddressLine1, String strAddressLine2,
			String strAddressLine3, String strAddressLine4_codeCheckCust,
			String strAddressLine5, String strNationality, String strBirthday,
			String strRaceCode, String strMaritalStatus, String strGender,
			String _2strOccupationCode, String strPlaceOfBirth,
			String strTypeElectronicAddress1, String strTypeElectronicAddress2,
			String strTypeElectronicAddress3, String strElectronicAddress1,
			String strElectronicAddress2, String strElectronicAddress3,
			String hostDate, String strBirthday_Full,
			String strIdDateIssued_Full, String _1strOccupationCode,
			String strProvince) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_17625I
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
		strValue[17] = "BBMBSCFMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "17625"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "17625"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[68] = "N";
		strValue[69] = "";
		strValue[70] = strIdNo;
		strValue[71] = strIdType;
		strValue[72] = strIdDateIssued;
		strValue[73] = strIdCountryIssued;
		strValue[75] = strIdPlaceIssued;
		strValue[76] = "N";
		strValue[78] = strSurname;
		strValue[79] = strAfterSurname;
		strValue[80] = "";
		strValue[81] = strAddressLine1;
		strValue[82] = strAddressLine2;
		strValue[83] = strAddressLine3;
		strValue[84] = strAddressLine4_codeCheckCust;
		strValue[85] = strAddressLine5;
		strValue[87] = strProvince;
		strValue[89] = "000000000";
		strValue[90] = strNationality;
		strValue[91] = strBirthday;
		strValue[92] = strRaceCode;
		strValue[93] = strMaritalStatus;
		// strValue[94] = "000000";
		strValue[95] = strNationality;
		strValue[96] = strGender;
		strValue[98] = _2strOccupationCode;
		strValue[100] = strPlaceOfBirth;
		strValue[102] = strTypeElectronicAddress1;
		strValue[103] = strTypeElectronicAddress2;
		strValue[104] = strTypeElectronicAddress3;
		strValue[105] = strElectronicAddress1;
		strValue[106] = strElectronicAddress2;
		strValue[107] = strElectronicAddress3;
		strValue[116] = "Y";
		strValue[117] = "Y";
		strValue[118] = hostDate;
		strValue[119] = strBirthday_Full;
		strValue[120] = strIdDateIssued_Full;
		strValue[121] = "C";
		strValue[122] = "CN";
		strValue[123] = "CN";
		strValue[124] = "";
		strValue[125] = "I";
		strValue[126] = _1strOccupationCode;
		strValue[127] = _2strOccupationCode;
		strValue[129] = "C";

		return strValue;
	}

	public static String[] searchCustomerByIDMessage(String teller,
			String hostName, String branchCode, String idNumber) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_15104I
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
		strValue[17] = "BBMBSCFINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "15104"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[38] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "15104"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = idNumber; // cif Number

		return strValue;
	}

	public static String[] searchCustomerByAccountMessage(String teller,
			String hostName, String branchCode, String accountNumber,
			String accountType) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26161I
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
		strValue[17] = "BBMBSDDINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "26161"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[38] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "26161"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = accountNumber; // account Number
		strValue[68] = accountType; // account Type
		return strValue;
	}

	public static String[] searchCustomerByCifMessage(String teller,
			String hostName, String branchCode, String cifNumber) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_15103I
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
		strValue[17] = "BBMBSCFINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "15103"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[38] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "15103"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = cifNumber; // cif Number

		return strValue;
	}

	public static String[] searchLoanAccountMessage(String teller,
			String hostName, String branchCode, String accountNumber) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_85800I
				.getFieldDefinitionList().length];

		strValue[0] = "*LINX"; // Header type
		strValue[1] = hostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "85800"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[38] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "85800"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = accountNumber; // account Number

		return strValue;
	}

	public static String[] searchAccountByCifMessage(String teller,
			String hostName, String branchCode, String cifNumber) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_15999I
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
		strValue[17] = "BBMBSCFINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "15999"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[38] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "15999"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[65] = cifNumber;
		strValue[67] = cifNumber; // cif Number

		return strValue;
	}

	public static String[] searchAccountMoreByCifMessage(String teller,
			String hostName, String branchCode, String cifNumber,
			String lastAccountType, String lastAccountNumber,
			String relationShip) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_15999I
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
		strValue[17] = "BBMBSCFINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "15999"; // Supervisor ID
		strValue[27] = "Y";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[38] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "15999"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "Y";
		strValue[50] = "F";
		strValue[65] = cifNumber; // cif Number
		strValue[67] = cifNumber; // cif Number
		strValue[68] = lastAccountType;
		strValue[69] = lastAccountNumber;
		strValue[70] = relationShip;

		return strValue;
	}

	public static String[] searchCustomerInformationByCifMessage(String teller,
			String hostName, String branchCode, String cifNumber) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_16106I
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
		strValue[17] = "BBMBSCFINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "16106"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // User ID
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "16106"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[65] = cifNumber; // cif Number
		strValue[67] = cifNumber; // cif Number

		return strValue;
	}

	public static String[] createCAMessage(String teller, String strAccount,
			String transDate, String branchCode, String currencyType,
			String numCif, String accountType, String accountName,
			String modeOfOperation) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
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
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*LINX"; // Source ID
		// strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[17] = "BBHTLMONEYFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = "VC"; // Terminal ID
		strValue[26] = "1000"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = "0005"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "1000"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "VD"; // Control unit
		strValue[51] = "VC"; // Work station
		strValue[53] = branchCode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = currencyType; // Currency Type
		strValue[94] = numCif; // Cif number
		strValue[95] = accountType; // Account type
		strValue[173] = accountName; // Account name
		strValue[219] = modeOfOperation
				+ "0                                 00001"; // mode of
		// orperation

		return strValue;
	}

	public static String[] createCAInQuiryMessage(String strSystemName,
			String teller, String strAccount, String transDate) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = strSystemName; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = strSystemName; // Source ID
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = "AC"; // Terminal ID
		strValue[26] = "1080"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = "10"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "1080"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "V1"; // Control unit
		strValue[51] = "AC"; // Work station
		strValue[53] = "20"; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = "VND"; // Currency code
		return strValue;
	}

	public static String[] createBranchTranferMessage(String branchCode,
			String creditAccount, String creditAmount, String creditCurrency,
			String creditRate, String debitAccount, String debitAmount,
			String debitCurrency, String debitRate, String manager,
			String strDescription, String strJournalSeq, String teller,
			String transDate, String transCode, String vatFee, String serviceFee) {

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		int vFee = vatFee == "0" ? 0 : Integer.parseInt(vatFee);
		int sFee = serviceFee == "0" ? 0 : Integer.parseInt(serviceFee);
		int totalFee = vFee + sFee;

		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = transCode; // 26 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = teller; // 41 :
		strValue[42] = strJournalSeq; // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[47] = transCode; // 47 :
		strValue[48] = transDate; // 48 : Date Of Tranfer
		strValue[50] = "V1"; // 50 :
		strValue[51] = strHostName; // 51 :
		strValue[52] = manager; // 52 :
		strValue[53] = branchCode; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = debitAccount; // 63 :
		strValue[65] = debitAmount + "00"; // 65 : Send
		// 100000 as tranfer 1000 VND
		strValue[71] = creditAccount; // 71 :
		strValue[79] = "000"; // 79 :
		strValue[80] = totalFee == 0 ? "" : totalFee + "00";// vatFee +
															// serviceFee
		strValue[81] = "000"; // 81 :
		strValue[82] = creditAmount + "00"; // 82 : Send
		// 100000 as tranfer 1000 VND
		strValue[87] = debitRate; // 87 :
		strValue[88] = creditRate; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = debitCurrency; // 105 :
		strValue[106] = creditCurrency; // 106 :
		strValue[107] = creditCurrency; // 107 :
		strValue[108] = creditCurrency; // 108 :
		strValue[112] = "3"; // 112 :
		strValue[126] = "1"; // 126 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[142] = "4"; // 142 :
		strValue[143] = "4"; // 143 :
		strValue[150] = "1"; // 150 :
		strValue[235] = debitAccount;
		strValue[237] = totalFee == 0 ? "" : totalFee + "00"; // vatFree +
																// serviceFree
		strValue[239] = "000";
		strValue[240] = serviceFee + "00";
		strValue[241] = vatFee + "00";
		strValue[249] = debitAmount + "00"; // 249 : Send
		// 100000 as tranfer 1000 VND
		strValue[252] = ""; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[264] = "1"; // 264 :
		strValue[280] = strDescription; // 280 : Reason of
		// transfer
		strValue[284] = creditAmount + "00"; // 284 : Send
		// 100000 as tranfer 1000 VND
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[294] = "3"; // 294 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

	public static String[] createBranchReversableTranferMessage(
			String strSystemName, String teller, String manager,
			String branchCode, String strFromAccount, String strToAccount,
			String transDate, String strDescription, String strTranferAmount,
			String strJournalSeq, String orgJournalSeq, String strTransCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		strTranferAmount = strTranferAmount.concat("00");
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "1100"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = teller; // 24 :
		strValue[25] = "lh"; // 25 :
		strValue[26] = "1321"; // 26 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = teller; // 41 :
		strValue[42] = strJournalSeq; // 42 :JOURNAL
		strValue[43] = "Y"; // 43 : Correction
		strValue[44] = orgJournalSeq;
		strValue[47] = strTransCode; // 47 :
		strValue[48] = transDate; // 48 : Date Of Tranfer
		strValue[50] = "V1"; // 50 :
		strValue[51] = strHostName; // 51 :
		strValue[52] = manager; // 52 :
		strValue[53] = branchCode; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = strFromAccount; // 63 :
		strValue[65] = strTranferAmount; // 65 : Send
		// 100000 as tranfer 1000 VND
		strValue[71] = strToAccount; // 71 :
		strValue[79] = "000"; // 79 :
		strValue[80] = ""; // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = strTranferAmount; // 82 : Send
		// 100000 as tranfer 1000 VND
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = "VND"; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[112] = "3"; // 112 :
		strValue[126] = "1"; // 126 :
		strValue[127] = ""; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[142] = "4"; // 142 :
		strValue[143] = "4"; // 143 :
		strValue[150] = "1"; // 150 :
		strValue[235] = ""; // 235 :
		strValue[237] = ""; // 237 :
		strValue[240] = "000"; // 240 : Send 100000 as charge
		// 1000 VND
		strValue[241] = "000"; // 241 :
		strValue[249] = strTranferAmount; // 249 : Send
		// 100000 as tranfer 1000 VND
		strValue[252] = ""; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[264] = "1"; // 264 :
		strValue[280] = strDescription; // 280 : Reason of
		// transfer
		strValue[284] = strTranferAmount; // 284 : Send
		// 100000 as tranfer 1000 VND
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[294] = "3"; // 294 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

	public static String[] createBranchWithdrawMessage(String strSystemName,
			String teller, String strFromAccount, String transDate,
			String strDescription, String strAmount, String strJournalSeq,
			String strTransCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "1100"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = teller; // 24 :
		strValue[25] = "us"; // 25 :
		strValue[26] = strTransCode; // 26 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = teller; // 41 :
		strValue[42] = strJournalSeq; // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[47] = strTransCode; // 47 : 1360
		strValue[48] = transDate; // 48 : Date Of Tranfer
		strValue[50] = "V1"; // 50 :
		strValue[51] = strHostName; // 51 :
		// strValue[52] = attestor; // 52 :
		strValue[53] = "110"; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = strFromAccount; // 63 :
		strValue[66] = strAmount; // 65 : Send 100000 as
		// tranfer 1000 VND
		strValue[79] = "000"; // 79 :
		strValue[80] = ""; // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = strAmount; // 82 : Send 100000 as
		// tranfer 1000 VND
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = "VND"; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "2";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = ""; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[140] = "3"; // 140 :
		strValue[141] = "4"; // 141:
		strValue[142] = "4"; // 142 :
		strValue[143] = "4"; // 143 :
		strValue[149] = "1"; // 149 :
		strValue[235] = ""; // 235 :
		strValue[237] = ""; // 237 :
		strValue[240] = "0"; // 240 : Send 100000 as charge
		// 1000 VND
		strValue[241] = "0"; // 241 :
		strValue[242] = "0"; // 237 :
		strValue[248] = "0"; // 248 :
		strValue[249] = strAmount; // 249 : Send 100000 as
		// tranfer 1000 VND
		strValue[252] = ""; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[257] = "4"; // 257 :
		strValue[263] = "1"; // 263 :
		strValue[264] = "2"; // 264 :
		strValue[280] = strDescription; // 280 : Reason of
		// transfer
		strValue[285] = "0"; // 285 :
		strValue[286] = "0"; // 286 :
		strValue[287] = strAmount; // 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[288] = "0"; // 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		strValue[297] = "1"; // 297 :
		strValue[298] = "1"; // 298 :
		return strValue;
	}

	public static String[] createBranchReversableFDMessage(
			String strSystemName, String teller, String attestor,
			String strFromAccount, String transDate, String strDescription,
			String strAmount, String strJournalSeq, String originseq,
			String strTransCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = teller; // 24 :
		strValue[25] = "us"; // 25 :
		strValue[26] = strTransCode; // 26 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = teller; // 41 :
		strValue[42] = strJournalSeq; // 42 :JOURNAL
		strValue[43] = "Y"; // 43 : Correction
		strValue[44] = originseq; // So sequence cua giao dich can dao
		strValue[47] = strTransCode; // 47 :
		strValue[48] = transDate; // 48 : Date Of Tranfer
		strValue[50] = "V1"; // 50 :
		strValue[51] = "us"; // 51 :
		strValue[52] = attestor; // 52 :
		strValue[53] = "20"; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = strFromAccount; // 63 :
		strValue[66] = strAmount; // 65 : Send 100000 as
		// tranfer 1000 VND
		strValue[72] = "0"; // 72 :
		strValue[75] = "999"; // 75 :
		strValue[79] = "0"; // 79 :
		strValue[80] = ""; // 80 :
		strValue[81] = "0"; // 81 :
		strValue[82] = strAmount; // 82 : Send 100000 as
		// tranfer 1000 VND
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[89] = "10000000"; // 88 :
		strValue[90] = "10000000"; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = "VND"; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "2";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = ""; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[140] = "3"; // 140 :
		strValue[141] = "4"; // 141:
		strValue[142] = "4"; // 142 :
		strValue[143] = "4"; // 143 :
		strValue[149] = "1"; // 149 :
		strValue[235] = ""; // 235 :
		strValue[237] = ""; // 237 :
		strValue[240] = "0"; // 240 : Send 100000 as charge
		// 1000 VND
		strValue[241] = "0"; // 241 :
		strValue[242] = "0"; // 237 :
		strValue[248] = "0"; // 248 :
		strValue[249] = strAmount; // 249 : Send 100000 as
		// tranfer 1000 VND
		strValue[252] = ""; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[257] = "4"; // 257 :
		strValue[263] = "1"; // 263 :
		strValue[264] = "2"; // 264 :
		strValue[280] = strDescription; // 280 : Reason of
		// transfer
		strValue[285] = "0"; // 285 :
		strValue[286] = "0"; // 286 :
		strValue[287] = strAmount; // 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[288] = "0"; // 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		strValue[297] = "1"; // 297 :
		strValue[298] = "1"; // 298 :
		return strValue;
	}

	public static String[] createOL2Message(String hostIP, String hostName,
			String teller, String manager, String sequence, String transDate,
			String branchCode, String fromAccount, String amount,
			String fromCif, String GLAccount, String vatFee, String serviceFee,
			String remarks, String fromName, String fromId, String toAccount,
			String toName, String toId, String toAddress, String toIdIssueDate,
			String toIdIssuePlace, String strTransCode, String refId) {

		int vFee = vatFee == "0" ? 0 : Integer.parseInt(vatFee);
		int sFee = serviceFee == "0" ? 0 : Integer.parseInt(serviceFee);
		int totalFee = vFee + sFee;

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];

		strValue[0] = "*LINX";
		strValue[1] = hostIP;
		strValue[5] = "213";
		strValue[6] = "42";
		strValue[7] = "0200";
		strValue[9] = "ABCS";
		strValue[10] = "*LINX";
		strValue[17] = "BBHTLMONEYFNC";
		strValue[24] = teller;
		strValue[25] = hostName;
		strValue[26] = strTransCode;
		strValue[30] = "3843";
		strValue[31] = "*MOSA";
		strValue[33] = hostIP;
		strValue[38] = "T9999";
		strValue[41] = teller;
		strValue[42] = StringUtil.lpad(sequence, 4, '0');
		strValue[43] = "N";
		strValue[47] = strTransCode;
		strValue[48] = transDate;
		strValue[50] = "VD";
		strValue[51] = hostName;
		strValue[52] = manager;
		strValue[53] = branchCode;
		strValue[54] = "0";
		strValue[55] = "1";
		strValue[56] = "N";
		strValue[57] = "N";
		strValue[58] = "N";
		strValue[59] = "N";
		strValue[60] = "N";
		strValue[61] = "N";
		strValue[62] = "N";
		strValue[63] = refId; // 15 number
		strValue[65] = amount;
		strValue[70] = StringUtil.lpad(GLAccount, 19, '0');
		strValue[71] = fromAccount;
		strValue[78] = "000";
		strValue[79] = "000";
		strValue[80] = totalFee == 0 ? "" : totalFee + "00";// vatFee +
															// serviceFee
		strValue[82] = amount;
		strValue[86] = "00000000";
		strValue[88] = "10000000";
		strValue[89] = "10000000";
		strValue[91] = "10000000";
		strValue[93] = "VND";
		strValue[94] = fromCif;
		strValue[105] = "VND";
		strValue[106] = "VND";
		strValue[107] = "VND";
		strValue[108] = "VND";
		strValue[112] = "3";
		strValue[125] = "5";
		strValue[126] = "1";
		strValue[127] = "5";
		strValue[129] = "2";
		strValue[143] = "5";
		strValue[144] = "5";
		strValue[145] = "5";
		strValue[221] = "VND";
		strValue[235] = fromAccount;
		strValue[237] = totalFee == 0 ? "" : totalFee + "00"; // vatFree +
																// serviceFree
		strValue[239] = "000";
		strValue[240] = serviceFee + "00";
		strValue[241] = vatFee + "00";
		strValue[249] = amount;
		strValue[252] = "4";
		strValue[254] = "5";
		strValue[255] = "5";
		strValue[256] = "5";
		strValue[264] = "2";
		strValue[280] = remarks;
		strValue[285] = "000";
		strValue[286] = "000";
		strValue[295] = "1";
		strValue[296] = "1";
		strValue[302] = "RM        C                            OL2                                                                                                                                                                             SMSBK       VND                                                  "
				+ StringUtil.rpad(fromName, 41, ' ')
				+ StringUtil.rpad(fromId, 196, ' ')
				+ "T"
				+ StringUtil.lpad(toAccount, 30, '0')
				+ "         "
				+ StringUtil.rpad(toName, 40, ' ')
				+ StringUtil.rpad(toId, 43, ' ')
				+ StringUtil.rpad(toAddress, 153, ' ')
				+ "0000000000000000000      "
				+ StringUtil.lpad(amount, 54, '0')
				+ "            "
				+ toIdIssueDate + StringUtil.rpad(toIdIssuePlace, 144, ' ');

		return strValue;
	}

	public static String[] createOL2MessageInterBank(String hostIP,
			String hostName, String teller, String manager, String sequence,
			String transDate, String branchCode, String fromAccount,
			String amount, String fromCif, String GLAccount, String vatFee,
			String serviceFee, String remarks, String fromName, String fromId,
			String toAccount, String toName, String toId, String toIdBranch,
			String toBranch, String toAddress, String toIdIssueDate,
			String toIdIssuePlace, String strTransCode, String refId) {

		int vFee = vatFee == "0" ? 0 : Integer.parseInt(vatFee);
		int sFee = serviceFee == "0" ? 0 : Integer.parseInt(serviceFee);
		int totalFee = vFee + sFee;

		String toName1 = "";
		String toName2 = "";
		String[] arrName = toName.split(" ");
		for (int i = 0; i < arrName.length; i++) {
			if (toName1.length() + (arrName[i].trim() + " ").length() < 40) {
				toName1 += arrName[i].trim() + " ";
			} else
				break;
		}

		if (toName1.length() > 40) {
			toName1 = toName1.substring(0, 40);
		}

		int remainLength = toName.length() - toName1.length();
		if (remainLength > 0) {
			// Neu so ky tu con lai > 30 ky tu
			// thi chap nhan truong hop ghep ten bi lech
			if (remainLength > 30) {
				toName1 = toName.substring(0, 40);
				toName2 = toName.substring(toName1.length(), toName.length());
			} else {
				toName2 = toName.substring(toName1.length(), toName.length());
			}

		}
		if (toName2.length() > 30) {
			toName2 = toName2.substring(0, 30);
		}

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];

		strValue[0] = "*LINX";
		strValue[1] = hostIP;
		strValue[5] = "213";
		strValue[6] = "42";
		strValue[7] = "0200";
		strValue[9] = "ABCS";
		strValue[10] = "*LINX";
		strValue[17] = "BBHTLMONEYFNC";
		strValue[24] = teller;
		strValue[25] = hostName;
		strValue[26] = strTransCode;
		strValue[30] = "3843";
		strValue[31] = "*MOSA";
		strValue[33] = hostIP;
		strValue[38] = "T9999";
		strValue[41] = teller;
		strValue[42] = StringUtil.lpad(sequence, 4, '0');
		strValue[43] = "N";
		strValue[47] = strTransCode;
		strValue[48] = transDate;
		strValue[50] = "VD";
		strValue[51] = hostName;
		strValue[52] = manager;
		strValue[53] = branchCode;
		strValue[54] = "0";
		strValue[55] = "1";
		strValue[56] = "N";
		strValue[57] = "N";
		strValue[58] = "N";
		strValue[59] = "N";
		strValue[60] = "N";
		strValue[61] = "N";
		strValue[62] = "N";
		strValue[63] = refId; // 15 number
		strValue[65] = amount;
		strValue[70] = StringUtil.lpad(GLAccount, 19, '0');
		strValue[71] = fromAccount;
		strValue[78] = "000";
		strValue[79] = "000";
		strValue[80] = totalFee == 0 ? "" : totalFee + "00";// vatFee +
															// serviceFee
		strValue[82] = amount;
		strValue[86] = "00000000";
		strValue[88] = "10000000";
		strValue[89] = "10000000";
		strValue[91] = "10000000";
		strValue[93] = "VND";
		strValue[94] = fromCif;
		strValue[105] = "VND";
		strValue[106] = "VND";
		strValue[107] = "VND";
		strValue[108] = "VND";
		strValue[112] = "3";
		strValue[125] = "5";
		strValue[126] = "1";
		strValue[127] = "5";
		strValue[129] = "2";
		strValue[143] = "5";
		strValue[144] = "5";
		strValue[145] = "5";
		strValue[221] = "VND";
		strValue[235] = fromAccount;
		strValue[237] = totalFee == 0 ? "" : totalFee + "00"; // vatFree +
																// serviceFree
		strValue[239] = "000";
		strValue[240] = serviceFee + "00";
		strValue[241] = vatFee + "00";
		strValue[249] = amount;
		strValue[252] = "4";
		strValue[254] = "5";
		strValue[255] = "5";
		strValue[256] = "5";
		strValue[264] = "2";
		strValue[280] = remarks;
		strValue[285] = "000";
		strValue[286] = "000";
		strValue[295] = "1";
		strValue[296] = "1";
		strValue[302] = "RM        C                            OL2                                                                                                                                                                             SMSBK       VND                                                  "
				+ StringUtil.rpad(fromName.toUpperCase(), 41, ' ')
				+ StringUtil.rpad(toId, 196, ' ')
				+ "T"
				+ StringUtil.lpad(GLAccount, 19, '0')
				+ StringUtil.lpad(toAccount.toUpperCase(), 20, ' ')
				+ StringUtil.rpad(toName1.toUpperCase(), 40, ' ')
				+ StringUtil.rpad(toIdBranch, 43, ' ')// bnfc_bank_id
				+ StringUtil.rpad(toBranch, 40, ' ')
				+ StringUtil.rpad(toAddress.toUpperCase(), 113, ' ')
				+ "0000000000000000000      "
				+ StringUtil.lpad(amount, 54, '0')
				+ "            "
				+ toIdIssueDate
				+ StringUtil.rpad("", 87, ' ')
				+ StringUtil.rpad(toName2.toUpperCase(), 30, ' ')
				+ StringUtil.rpad("", 27, ' ') + "|";

		return strValue;
	}

	public static String[] createOL2ReservationMessage(String hostIP,
			String hostName, String teller, String manager, String sequence,
			String oldSequence, String transDate, String branchCode,
			String fromAccount, String amount, String amountMustPay,
			String fromCif, String GLAccount, String vatFee, String serviceFee,
			String remarks, String fromName, String fromId, String toAccount,
			String toName, String toId, String toAddress, String toIdIssueDate,
			String toIdIssuePlace, String strTransCode, String refId) {

		int vFee = vatFee == "0" ? 0 : Integer.parseInt(vatFee);
		int sFee = serviceFee == "0" ? 0 : Integer.parseInt(serviceFee);
		int totalFee = vFee + sFee;

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];

		strValue[0] = "*LINX";
		strValue[1] = hostIP;
		strValue[5] = "213";
		strValue[6] = "42";
		strValue[7] = "0200";
		strValue[9] = "ABCS";
		strValue[10] = "*LINX";
		strValue[17] = "BBHTLMONEYFNC";
		strValue[24] = teller;
		strValue[25] = hostName;
		strValue[26] = strTransCode;
		strValue[30] = "3843";
		strValue[31] = "*MOSA";
		strValue[33] = hostIP;
		strValue[38] = "T9999";
		strValue[41] = teller;
		strValue[42] = StringUtil.lpad(sequence, 4, '0');
		strValue[43] = "Y";
		strValue[44] = StringUtil.lpad(oldSequence, 4, '0');
		;
		strValue[47] = strTransCode;
		strValue[48] = transDate;
		strValue[50] = "V1";
		strValue[51] = hostName;
		strValue[52] = manager;
		strValue[53] = branchCode;
		strValue[54] = "0";
		strValue[55] = "1";
		strValue[56] = "N";
		strValue[57] = "N";
		strValue[58] = "N";
		strValue[59] = "N";
		strValue[60] = "N";
		strValue[61] = "N";
		strValue[62] = "N";
		strValue[63] = refId; // 15 number
		strValue[65] = amountMustPay;
		strValue[70] = StringUtil.lpad(GLAccount, 19, '0');
		strValue[71] = fromAccount;
		strValue[78] = "000";
		strValue[79] = "000";
		strValue[80] = totalFee == 0 ? "" : totalFee + "00";// vatFee +
															// serviceFee
		strValue[82] = amount;
		strValue[86] = "00000000";
		strValue[88] = "10000000";
		strValue[89] = "10000000";
		strValue[91] = "10000000";
		strValue[93] = "VND";
		strValue[94] = fromCif;
		strValue[105] = "VND";
		strValue[106] = "VND";
		strValue[107] = "VND";
		strValue[108] = "VND";
		strValue[112] = "3";
		strValue[125] = "5";
		strValue[126] = "1";
		strValue[129] = "2";
		strValue[143] = "5";
		strValue[144] = "5";
		strValue[145] = "5";
		strValue[221] = "VND";
		strValue[235] = fromAccount;
		strValue[237] = totalFee == 0 ? "" : totalFee + "00"; // vatFree +
																// serviceFree
		strValue[239] = "000";
		strValue[240] = serviceFee + "00";
		strValue[241] = vatFee + "00";
		strValue[249] = amountMustPay;
		strValue[254] = "5";
		strValue[255] = "5";
		strValue[256] = "5";
		strValue[264] = "2";
		strValue[280] = remarks;
		strValue[285] = "000";
		strValue[286] = "000";
		strValue[295] = "1";
		strValue[296] = "1";
		strValue[302] = "RM        C                            OL2                                                                                                                                                                             SMSBK       VND                                                  "
				+ StringUtil.rpad(fromName, 41, ' ')
				+ StringUtil.rpad(fromId, 196, ' ')
				+ "T"
				+ StringUtil.lpad(toAccount, 30, '0')
				+ "         "
				+ StringUtil.rpad(toName, 40, ' ')
				+ StringUtil.rpad(toId, 43, ' ')
				+ StringUtil.rpad(toAddress, 153, ' ')
				+ "0000000000000000000      "
				+ StringUtil.lpad(amount, 54, '0')
				+ "            "
				+ toIdIssueDate + StringUtil.rpad(toIdIssuePlace, 144, ' ');

		return strValue;
	}

	public static String[] createRollbackTranferMessage(String strSystemName,
			String teller, String attestor, String strFromAccount,
			String strToAccount, String transDate, String strDescription,
			String strTranferAmount, String strJournalSeq,
			String strORIGJournalSeq, String strTransCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = " "; // 2 :
		strValue[3] = " "; // 3 :
		strValue[4] = " "; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = " "; // 8 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[11] = " "; // 11 :
		strValue[12] = " "; // 12 :
		strValue[13] = " "; // 13 :
		strValue[14] = " "; // 14 :
		strValue[15] = " "; // 15 :
		strValue[16] = " "; // 16 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[18] = " "; // 18 :
		strValue[19] = " "; // 19 :
		strValue[20] = " "; // 20 :
		strValue[21] = " "; // 21 :
		strValue[22] = " "; // 22 :
		strValue[23] = " "; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = "lh"; // 25 :
		strValue[26] = strTransCode; // 26 :
		strValue[27] = " "; // 27 :
		strValue[28] = " "; // 28 :
		strValue[29] = " "; // 29 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[32] = " "; // 32 :
		strValue[33] = strHostName; // 33 :
		strValue[34] = " "; // 34 :
		strValue[35] = " "; // 35 :
		strValue[36] = " "; // 36 :
		strValue[37] = " "; // 37 :
		strValue[38] = "T9999"; // 38 :
		strValue[39] = " "; // 39 :
		strValue[40] = ""; // 40 :
		strValue[41] = teller; // 41 :
		strValue[42] = strJournalSeq; // 42 :
		strValue[43] = "Y"; // 43 :
		strValue[44] = strORIGJournalSeq; // 44 :
		strValue[45] = ""; // 45 :
		strValue[46] = ""; // 46 :
		strValue[47] = strTransCode; // 47 :
		strValue[48] = transDate; // 48 :
		strValue[49] = " "; // 49 :
		strValue[50] = "V1"; // 50 :
		strValue[51] = "lh"; // 51 :
		strValue[52] = attestor; // 52 :
		strValue[53] = "020"; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = strFromAccount; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = strTranferAmount; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = ""; // 67 :
		strValue[68] = ""; // 68 :
		strValue[69] = ""; // 69 :
		strValue[70] = ""; // 70 :
		strValue[71] = strToAccount; // 71 :
		strValue[72] = ""; // 72 :
		strValue[73] = ""; // 73 :
		strValue[74] = ""; // 74 :
		strValue[75] = ""; // 75 :
		strValue[76] = ""; // 76 :
		strValue[77] = ""; // 77 :
		strValue[78] = ""; // 78 :
		strValue[79] = "000"; // 79 :
		strValue[80] = "000"; // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = strTranferAmount; // 82 :
		strValue[83] = ""; // 83 :
		strValue[84] = ""; // 84 :
		strValue[85] = ""; // 85 :
		strValue[86] = ""; // 86 :
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[89] = ""; // 89 :
		strValue[90] = ""; // 90 :
		strValue[91] = ""; // 91 :
		strValue[92] = ""; // 92 :
		strValue[93] = "VND"; // 93 :
		strValue[94] = ""; // 94 :
		strValue[95] = ""; // 95 :
		strValue[96] = ""; // 96 :
		strValue[97] = ""; // 97 :
		strValue[98] = ""; // 98 :
		strValue[99] = ""; // 99 :
		strValue[100] = ""; // 100 :
		strValue[101] = ""; // 101 :
		strValue[102] = ""; // 102 :
		strValue[103] = ""; // 103 :
		strValue[104] = ""; // 104 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = "VND"; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[109] = ""; // 109 :
		strValue[110] = ""; // 110 :
		strValue[111] = ""; // 111 :
		strValue[112] = "3"; // 112 :
		strValue[113] = ""; // 113 :
		strValue[114] = ""; // 114 :
		strValue[115] = ""; // 115 :
		strValue[116] = ""; // 116 :
		strValue[117] = ""; // 117 :
		strValue[118] = ""; // 118 :
		strValue[119] = ""; // 119 :
		strValue[120] = ""; // 120 :
		strValue[121] = ""; // 121 :
		strValue[122] = ""; // 122 :
		strValue[123] = ""; // 123 :
		strValue[124] = ""; // 124 :
		strValue[125] = ""; // 125 :
		strValue[126] = "1"; // 126 :
		strValue[127] = "4"; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[130] = ""; // 130 :
		strValue[131] = ""; // 131 :
		strValue[132] = ""; // 132 :
		strValue[133] = ""; // 133 :
		strValue[134] = ""; // 134 :
		strValue[135] = ""; // 135 :
		strValue[136] = ""; // 136 :
		strValue[137] = ""; // 137 :
		strValue[138] = ""; // 138 :
		strValue[139] = ""; // 139 :
		strValue[140] = ""; // 140 :
		strValue[141] = ""; // 141 :
		strValue[142] = "4"; // 142 :
		strValue[143] = "4"; // 143 :
		strValue[144] = ""; // 144 :
		strValue[145] = ""; // 145 :
		strValue[146] = ""; // 146 :
		strValue[147] = "5"; // 147 :
		strValue[148] = ""; // 148 :
		strValue[149] = ""; // 149 :
		strValue[150] = "1"; // 150 :
		strValue[151] = ""; // 151 :
		strValue[152] = ""; // 152 :
		strValue[153] = ""; // 153 :
		strValue[154] = ""; // 154 :
		strValue[155] = ""; // 155 :
		strValue[156] = ""; // 156 :
		strValue[157] = ""; // 157 :
		strValue[158] = ""; // 158 :
		strValue[159] = ""; // 159 :
		strValue[160] = ""; // 160 :
		strValue[161] = ""; // 161 :
		strValue[162] = ""; // 162 :
		strValue[163] = ""; // 163 :
		strValue[164] = ""; // 164 :
		strValue[165] = ""; // 165 :
		strValue[166] = ""; // 166 :
		strValue[167] = ""; // 167 :
		strValue[168] = ""; // 168 :
		strValue[169] = ""; // 169 :
		strValue[170] = ""; // 170 :
		strValue[171] = ""; // 171 :
		strValue[172] = ""; // 172 :
		strValue[173] = ""; // 173 :
		strValue[174] = ""; // 174 :
		strValue[175] = ""; // 175 :
		strValue[176] = ""; // 176 :
		strValue[177] = ""; // 177 :
		strValue[178] = ""; // 178 :
		strValue[179] = ""; // 179 :
		strValue[180] = ""; // 180 :
		strValue[181] = ""; // 181 :
		strValue[182] = ""; // 182 :
		strValue[183] = ""; // 183 :
		strValue[184] = ""; // 184 :
		strValue[185] = ""; // 185 :
		strValue[186] = ""; // 186 :
		strValue[187] = ""; // 187 :
		strValue[188] = ""; // 188 :
		strValue[189] = ""; // 189 :
		strValue[190] = ""; // 190 :
		strValue[191] = ""; // 191 :
		strValue[192] = ""; // 192 :
		strValue[193] = ""; // 193 :
		strValue[194] = ""; // 194 :
		strValue[195] = ""; // 195 :
		strValue[196] = ""; // 196 :
		strValue[197] = ""; // 197 :
		strValue[198] = ""; // 198 :
		strValue[199] = ""; // 199 :
		strValue[200] = ""; // 200 :
		strValue[201] = ""; // 201 :
		strValue[202] = ""; // 202 :
		strValue[203] = ""; // 203 :
		strValue[204] = ""; // 204 :
		strValue[205] = ""; // 205 :
		strValue[206] = ""; // 206 :
		strValue[207] = ""; // 207 :
		strValue[208] = ""; // 208 :
		strValue[209] = ""; // 209 :
		strValue[210] = ""; // 210 :
		strValue[211] = ""; // 211 :
		strValue[212] = ""; // 212 :
		strValue[213] = ""; // 213 :
		strValue[214] = ""; // 214 :
		strValue[215] = ""; // 215 :
		strValue[216] = ""; // 216 :
		strValue[217] = ""; // 217 :
		strValue[218] = ""; // 218 :
		strValue[219] = ""; // 219 :
		strValue[220] = ""; // 220 :
		strValue[221] = ""; // 221 :
		strValue[222] = ""; // 222 :
		strValue[223] = ""; // 223 :
		strValue[224] = ""; // 224 :
		strValue[225] = ""; // 225 :
		strValue[226] = ""; // 226 :
		strValue[227] = ""; // 227 :
		strValue[228] = ""; // 228 :
		strValue[229] = ""; // 229 :
		strValue[230] = ""; // 230 :
		strValue[231] = ""; // 231 :
		strValue[232] = ""; // 232 :
		strValue[233] = ""; // 233 :
		strValue[234] = ""; // 234 :
		strValue[235] = ""; // 235 :
		strValue[236] = ""; // 236 :
		strValue[237] = ""; // 237 :
		strValue[238] = "000"; // 238 :
		strValue[239] = ""; // 239 :
		strValue[240] = "000"; // 240 :
		strValue[241] = "000"; // 241 :
		strValue[242] = ""; // 242 :
		strValue[243] = ""; // 243 :
		strValue[244] = ""; // 244 :
		strValue[245] = ""; // 245 :
		strValue[246] = ""; // 246 :
		strValue[247] = ""; // 247 :
		strValue[248] = ""; // 248 :
		strValue[249] = strTranferAmount; // 249 :
		strValue[250] = ""; // 250 :
		strValue[251] = ""; // 251 :
		strValue[252] = ""; // 252 :
		strValue[253] = "5"; // 253 :
		strValue[254] = ""; // 254 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[257] = ""; // 257 :
		strValue[258] = ""; // 258 :
		strValue[259] = ""; // 259 :
		strValue[260] = ""; // 260 :
		strValue[261] = ""; // 261 :
		strValue[262] = ""; // 262 :
		strValue[263] = ""; // 263 :
		strValue[264] = "1"; // 264 :
		strValue[265] = ""; // 265 :
		strValue[266] = ""; // 266 :
		strValue[267] = ""; // 267 :
		strValue[268] = ""; // 268 :
		strValue[269] = ""; // 269 :
		strValue[270] = ""; // 270 :
		strValue[271] = ""; // 271 :
		strValue[272] = ""; // 272 :
		strValue[273] = ""; // 273 :
		strValue[274] = ""; // 274 :
		strValue[275] = ""; // 275 :
		strValue[276] = ""; // 276 :
		strValue[277] = ""; // 277 :
		strValue[278] = ""; // 278 :
		strValue[279] = ""; // 279 :
		strValue[280] = strDescription; // 280 :
		strValue[281] = ""; // 281 :
		strValue[282] = ""; // 282 :
		strValue[283] = ""; // 283 :
		strValue[284] = strTranferAmount; // 284 :
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[287] = ""; // 287 :
		strValue[288] = ""; // 288 :
		strValue[289] = ""; // 289 :
		strValue[290] = ""; // 290 :
		strValue[291] = ""; // 291 :
		strValue[292] = ""; // 292 :
		strValue[293] = ""; // 293 :
		strValue[294] = "3"; // 294 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

	public static String[] createMessage(String strSystemName, String teller,
			String strAccount, String transDate) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = strSystemName; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = strSystemName; // Source ID
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = "AC"; // Terminal ID
		strValue[26] = "1080"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = "10"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "1080"; // Transaction Code
		strValue[48] = transDate;// StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "V1"; // Control unit
		strValue[51] = "AC"; // Work station
		strValue[53] = "20"; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = "VND"; // Currency code
		return strValue;
	}

	public static String[] createHoldMessage(String strSystemName,
			String teller, String strAccount, String transHoldToDate,
			String strDescription, String strHoldAmount, String holdCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_27141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "1"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "27141"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = "20"; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "27141"; // 45 :
		strValue[46] = "A"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = ""; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = strAccount; // 67 :
		strValue[68] = "D"; // 68 :
		strValue[69] = ""; // 69 :
		strValue[70] = "HD"; // 70 :
		strValue[71] = ""; // 71 :
		strValue[72] = ""; // 72 :
		strValue[73] = ""; // 73 :
		strValue[74] = strHoldAmount; // 74 :
		strValue[75] = ""; // 75 :
		strValue[76] = "0"; // 76 :
		strValue[77] = transHoldToDate; // 77 :
		strValue[78] = strDescription; // 78 :
		strValue[79] = ""; // 79 :
		strValue[80] = ""; // 80 :
		strValue[81] = holdCode; // hold code *TRADE/*EA/*RETENTION

		return strValue;
	}

	public static String[] createUnHoldMessage(String strSystemName,
			String teller, String strAccount, String strDescription,
			String strHoldAmount, String holdCode, String strJournalSeq,
			String actionCode, String expireDate) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "1"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "28141"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 : Response Result Code
		strValue[31] = teller; // 31 : User ID
		strValue[32] = "1"; // 32 : Reference Number
		strValue[33] = ""; // 33 : Rebid Number
		strValue[34] = "*END"; // 34 : End of Group Indicator
		strValue[35] = ""; // 35 : Block Message Number
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[38] = ""; // 38 : Return Data Queue Name
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = "20"; // 41 : Branch Number
		strValue[42] = ""; // 42 : Review Supervisor ID
		// (Local)
		strValue[43] = ""; // 43 : Transmit Supervisor ID
		// (Local)
		strValue[44] = ""; // 44 : Host Supervisor ID
		strValue[45] = "28141"; // Transaction Code
		strValue[46] = actionCode; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		strValue[51] = ""; // Response Error Code 1
		strValue[52] = ""; // 52 : Response Reason For Code 1
		strValue[53] = ""; // 53 : Response Error Code 2
		strValue[54] = ""; // 54 : Response Reason For Code 2
		strValue[55] = ""; // 55 : Response Error Code 3
		strValue[56] = ""; // 56 : Response Reason For Code 3
		strValue[57] = ""; // 57 : Response Error Code 4
		strValue[58] = ""; // 58 : Response Reason For Code 4
		strValue[59] = ""; // 59 : Response Error Code 5
		strValue[60] = ""; // 60 : Response Reason For Code 5
		strValue[61] = ""; // 61 : Date In From Client
		// DDMMYYYY
		strValue[62] = ""; // 62 : Time In From Client HHMMSS
		strValue[63] = strAccount; // 63 : Account No
		strValue[64] = "D"; // 64 : Account Type
		strValue[65] = ""; // 65 : CIF No
		strValue[66] = ""; // 66 : Filler
		strValue[67] = strAccount; // Account number
		strValue[68] = "D"; // Account type
		strValue[69] = strJournalSeq; // Sequence
		strValue[70] = "HD"; // Type of entry
		strValue[71] = "0"; // Check date
		strValue[72] = "0"; // Low check number
		strValue[73] = "0"; // High check number
		strValue[74] = strHoldAmount; // Check Amount
		strValue[75] = ""; // Payee name
		strValue[76] = "0"; // Stop charge
		strValue[77] = expireDate; // Expiration date
		strValue[78] = strDescription; // Stop/Hold remarks:
		strValue[79] = "0"; // Block for Account number
		strValue[80] = "0"; // Block for account type
		strValue[81] = holdCode;// "*TRADE", "*EA" // Stop/Hold code
		strValue[82] = "0"; // Penalty amount

		return strValue;
	}

	public static String[] createLockMessage(String strSystemName,
			String teller, String strAccount, String strJournalSeq) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = strSystemName; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "1"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSDDINQFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "26141"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = "20"; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "26141"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = strAccount; // 67 :
		strValue[68] = "D"; // 68 :
		strValue[69] = strJournalSeq; // 69 :

		return strValue;
	}

	public static String[] createAddCifEContactMessage(String strSystemName,
			String strteller, String strBankcode, String strCIF_num,
			String strTypeElectronic, String strElectronic) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_17240I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strteller; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSCFMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = strteller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "17240"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = strteller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = strBankcode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "17240"; // 45 :
		strValue[46] = "A"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = ""; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = strCIF_num; // 67 :
		strValue[68] = "C"; // 68 :
		strValue[70] = strTypeElectronic; // 70 :
		strValue[71] = strElectronic;
		strValue[78] = "0";

		return strValue;
	}

	public static String[] createSAMessage(String strSystemName,
			String strteller, String strBankcode, String transDate,
			String strAccount, String strCifnumber, String strtypeAcount,
			String strNameAcount, String strCurrencyType,
			String strmodeOfOperation) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "0200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = strteller; // User ID
		strValue[25] = "VC"; // Terminal ID
		strValue[26] = "2000"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = strteller; // TELLER ID
		strValue[42] = "0001"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "2000"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "VD"; // Control unit
		strValue[51] = "VC"; // Work station
		strValue[53] = strBankcode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = strCurrencyType; // Currency code
		strValue[94] = strCifnumber; // Cif code
		strValue[95] = strtypeAcount; // Account type
		strValue[173] = strNameAcount; // Account type
		strValue[219] = strmodeOfOperation + "00001"; // Account type
		return strValue;
	}

	public static String[] createFDMessage(String strSystemName,
			String strteller, String strBankcode, String transDate,
			String strAccount, String strCifnumber, String strNameAcount,
			String strCurrencyType, String strmodeOfOperation) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[17] = "BBHTLMONEYFNC"; // Scenario Number
		strValue[24] = strteller; // User ID
		strValue[25] = "VC"; // Terminal ID
		strValue[26] = "3090"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = strteller; // TELLER ID
		strValue[42] = "0003"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "3090"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "VD"; // Control unit
		strValue[51] = "VC"; // Work station
		strValue[53] = strBankcode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = strCurrencyType; // Currency code
		strValue[94] = strCifnumber; // Cif number
		// strValue[95] = accountType; // Account type
		strValue[173] = strNameAcount; // Account name
		strValue[219] = strmodeOfOperation
				+ "0                                 00001"; // mode of
		strValue[224] = "Y";

		return strValue;
	}

	public static String[] createJoinAccountCAMessage(String strSystemName,
			String strteller, String strBankcode, String transDate,
			String strAccount, String strCifnumber, String strNameAcount,
			String strcurrCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "0200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[17] = "BBHTLMONEYFNC"; // Scenario Number
		strValue[24] = strteller; // User ID
		strValue[25] = "AP"; // Terminal ID
		strValue[26] = "1001"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = strteller; // TELLER ID
		strValue[42] = "0005"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "1001"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "VD"; // Control unit
		strValue[51] = "AP"; // Work station
		strValue[53] = strBankcode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = strcurrCode; // Currency code
		strValue[94] = strCifnumber; // Cif code
		strValue[95] = "JA"; // Account type
		// strValue[173] = strNameAcount; // Account type
		// strValue[219] = strmodeOfOperation; // Account type
		// strValue[224] = "Y" ;// Account type
		return strValue;
	}

	public static String[] createJoinAccountSAMessage(String strSystemName,
			String strteller, String strBankcode, String transDate,
			String strAccount, String strCifnumber, String strNameAcount,
			String strcurrCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "0200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = strSystemName; // Source ID
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = strteller; // User ID
		strValue[25] = "AP"; // Terminal ID
		strValue[26] = "2001"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = strteller; // TELLER ID
		strValue[42] = "0004"; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "2001"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "VD"; // Control unit
		strValue[51] = "AP"; // Work station
		strValue[53] = strBankcode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = strAccount; // Single Fields 01, Account here
		strValue[93] = strcurrCode; // Currency code
		strValue[94] = strCifnumber; // Cif code
		strValue[95] = "JA"; // Account type
		strValue[110] = "1"; // Account type
		// strValue[219] = strmodeOfOperation; // Account type
		// strValue[224] = "Y" ;// Account type
		return strValue;
	}

	public static String[] searchMobileByCifMessage(String strSystemName,
			String strteller, String strBankcode, String strsearch_key) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_15242I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSCFINQFNC"; // BBMBSCFINQFNC 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "8"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = strteller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "15242"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = strteller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = strBankcode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "15242"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "10"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = ""; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = strsearch_key; // 67 :
		strValue[68] = "C"; // 68 :
		strValue[69] = ""; // 70 :

		return strValue;
	}

	public static String[] createLockPhoneMessage(String strSystemName,
			String strteller, String strBankcode, String strCifNumber,
			String strNewPhoneSeqno) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_16240I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSCFINQFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = strteller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "16240"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = strteller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = strBankcode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "16240"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = strCifNumber; // 67 :
		strValue[68] = "C"; // 68 :
		strValue[69] = strNewPhoneSeqno; // 70 :

		return strValue;
	}

	public static String[] createUpdateEcontactMessage(String strSystemName,
			String strteller, String strBankcode, String strCifNumber,
			String strNewPhoneSeqno, String strEcontactType,
			String strnewPhoneNumber) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_18240I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[15] = ""; // 15 :
		strValue[16] = ""; // 16 :
		strValue[17] = "BBMBSCFMNTFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = strteller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "18240"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[28] = ""; // 28 :
		strValue[29] = ""; // 29 :
		strValue[30] = ""; // 30 :
		strValue[31] = strteller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = strBankcode; // 41 :
		strValue[42] = ""; // 42 :
		strValue[43] = ""; // 43 :
		strValue[44] = ""; // 44 :
		strValue[45] = "18240"; // 45 :
		strValue[46] = "C"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[51] = ""; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = ""; // 53 :
		strValue[54] = ""; // 54 :
		strValue[55] = ""; // 55 :
		strValue[56] = "C"; // 56 :
		strValue[57] = ""; // 57 :
		strValue[58] = ""; // 58 :
		strValue[59] = ""; // 59 :
		strValue[60] = ""; // 60 :
		strValue[61] = ""; // 61 :
		strValue[62] = ""; // 62 :
		strValue[63] = ""; // 63 :
		strValue[64] = ""; // 64 :
		strValue[65] = ""; // 65 :
		strValue[66] = ""; // 66 :
		strValue[67] = strCifNumber; // 67 :
		strValue[68] = "C"; // 68 :
		strValue[69] = strNewPhoneSeqno; // 70 :
		strValue[70] = strEcontactType; // 70 :
		strValue[71] = strnewPhoneNumber; // 70 :
		strValue[72] = "N";
		strValue[78] = "0";
		return strValue;
	}

	public static String[] createFDReceiptMessage(String teller,
			String branchCode, String journalSeq, String transDate,
			String fdGroupAccount, String depositAmt, String effectiveDate,
			String fdReceipt, String rate, String intPaymentToAcctno,
			String printTranferToAcctno, String currency, String productCode,
			String autoRenew, String intPaymentMode, String fdType,
			String remark) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			hostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = hostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[17] = "BBHTLMONEYFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "3000"; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = hostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = journalSeq; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = "3000"; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "VD"; // Control unit
		strValue[51] = hostName; // Work station
		strValue[53] = branchCode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE
		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = "N"; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = fdGroupAccount; // Single Fields 01, Account here
		strValue[66] = depositAmt; //
		strValue[67] = effectiveDate; //
		strValue[68] = fdReceipt; //
		strValue[78] = rate; //
		strValue[82] = intPaymentToAcctno; //
		strValue[83] = printTranferToAcctno; //
		strValue[93] = currency; //
		strValue[95] = productCode; //
		strValue[99] = autoRenew; //
		strValue[100] = intPaymentMode; //
		strValue[101] = "D"; //
		strValue[102] = "D"; //
		strValue[103] = "D"; //
		strValue[113] = "1"; //
		strValue[129] = "1"; //
		strValue[130] = "1"; //
		strValue[280] = remark; //

		return strValue;
	}

	public static String[] createCifGroupManagementAdditionMessage(
			String teller, String branchCode, String cifCorp, String seq,
			String cifEmp, String relationalEmpLev, String relationalType,
			String relationalCorpLev) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_17321I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSCFMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "17321"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = teller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[34] = "*END"; // 34 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branchCode; // 41 :
		strValue[45] = "17321"; // 45 :
		strValue[46] = "A"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[67] = cifCorp; // 67 :
		strValue[68] = seq; // 68 : default 0
		strValue[69] = cifEmp; // 69 :
		strValue[70] = cifCorp; // 70 :
		strValue[71] = relationalEmpLev; // 71 :
		strValue[72] = relationalType; // 72 :
		strValue[73] = relationalCorpLev; // 71 :

		return strValue;
	}

	public static String[] createIO5Message(String hostIP, String hostName,
			String teller, String manager, String sequence, String transDate,
			String branchCode, String toAccount, String toName, String toId,
			String toAddress, String crAmount, String ttAmount, String toCif,
			String GLAccount, String vatFee, String serviceFee, String buyRate,
			String sendRate, String ttSendRate, String remarks,
			String strTransCode, String refId, String refWU,
			String sendingCurrencyType, String receivingCurrencyType,
			String toIdIssueDate, String toIdIssuePlace) {

		int vFee = vatFee == "0" ? 0 : Integer.parseInt(vatFee);
		int sFee = serviceFee == "0" ? 0 : Integer.parseInt(serviceFee);
		int totalFee = vFee + sFee;

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];

		strValue[0] = "*LINX";
		strValue[1] = hostIP;
		strValue[5] = "213";
		strValue[6] = "42";
		strValue[7] = "0200";
		strValue[9] = "ABCS";
		strValue[10] = "*LINX";
		strValue[17] = "BBHTLMONEYFNC";
		strValue[24] = teller;
		strValue[25] = hostName;
		strValue[26] = strTransCode;
		strValue[30] = "3843";
		strValue[31] = "*MOSA";
		strValue[33] = hostIP;
		strValue[38] = "T9999";
		strValue[41] = teller;
		strValue[42] = StringUtil.lpad(sequence, 4, '0');
		strValue[43] = "N";
		strValue[47] = strTransCode;
		strValue[48] = transDate;
		strValue[50] = "VD";
		strValue[51] = hostName;
		strValue[52] = manager;
		strValue[53] = branchCode;
		strValue[54] = "0";
		strValue[55] = "1";
		strValue[56] = "N";
		strValue[57] = "N";
		strValue[58] = "N";
		strValue[59] = "N";
		strValue[60] = "N";
		strValue[61] = "N";
		strValue[62] = "N";
		strValue[63] = refId; // 15 number
		strValue[65] = crAmount;
		strValue[70] = StringUtil.lpad(GLAccount, 19, '0');
		strValue[71] = toAccount;
		strValue[78] = "000";
		strValue[79] = "000";
		strValue[80] = totalFee == 0 ? "" : totalFee + "00";// vatFee +
															// serviceFee
		strValue[82] = ttAmount;
		// strValue[86] = "00000000";
		strValue[88] = buyRate;// format 10000000 same as 1
		strValue[89] = sendRate;// format 10000000 same as 1
		strValue[91] = ttSendRate;// format 10000000 same as 1
		strValue[93] = "VND";
		strValue[94] = toCif;
		strValue[104] = refWU;
		strValue[105] = sendingCurrencyType;
		strValue[106] = receivingCurrencyType;
		strValue[107] = receivingCurrencyType;
		strValue[108] = sendingCurrencyType;
		strValue[112] = "3";
		strValue[125] = "5";
		strValue[126] = "1";
		// strValue[127] = "5";
		strValue[129] = "2";
		strValue[143] = "5";
		strValue[144] = "5";
		// strValue[145] = "5";
		// strValue[221] = "VND";
		// strValue[235] = fromAccount;
		// strValue[237] = totalFee == 0 ? "" : totalFee + "00"; // vatFree +
		// serviceFree
		// strValue[239] = "000";
		strValue[240] = serviceFee + "00";
		strValue[241] = vatFee + "00";
		strValue[249] = ttAmount;
		// strValue[252] = "4";
		// strValue[254] = "5";
		strValue[255] = "5";
		strValue[256] = "5";
		strValue[264] = "2";
		strValue[280] = remarks;
		strValue[285] = "000";
		strValue[286] = "000";
		strValue[295] = "1";
		strValue[296] = "1";
		strValue[302] = "RM        CZ                           IO5                                                                                                                                                                             MONEYGRAM   "
				+ sendingCurrencyType
				+ "                                                  "
				+ StringUtil.rpad(toName, 41, ' ')
				+ StringUtil.rpad(toId, 196, ' ')
				+ "T"
				+ StringUtil.lpad(toAccount, 30, '0')
				+ "         "
				+ StringUtil.rpad(toName, 40, ' ')
				+ StringUtil.rpad(toId, 43, ' ')
				+ StringUtil.rpad(toAddress, 153, ' ')
				+ "0000000000000000000      "
				+ StringUtil.lpad(crAmount, 54, '0')
				+ "            "
				+ toIdIssueDate + StringUtil.rpad(toIdIssuePlace, 144, ' ');

		return strValue;
	}

	public static String[] createMiniStatementMessage(String strteller,
			String strBankcode, String recordNumber, String accountNumber,
			String accountType) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_25520I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSDDINQFNC"; // 17 :
		strValue[22] = "10"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = strteller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "25520"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = strteller; // 31 :
		strValue[32] = "1"; // 32 :
		strValue[34] = "*END"; // 34 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = strBankcode; // 41 :
		strValue[45] = "25520"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = recordNumber; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[67] = accountNumber; // 67 :
		strValue[68] = accountType; // 68 :
		return strValue;
	}

	public static String[] changeCIFInformationMessage(String teller,
			String hostName, String branchCode, String cifNumber,
			String salution, String shortName, String fullName1,
			String fullName2, String motherName, String inquiryIdCode,
			String insiderCode, String vipCustomerCode, String codonglonMSB,
			String thamsochuadung1, String doanhnghiepvuavanho,
			String tochuctaichinhvanganhang, String cacbenlienquan,
			String doanhnghieplon, String khachhangmuabaohiem,
			String thamsochuadung2, String baohiem, String buuchinhvienthong,
			String hangkhong, String nuoitrongthuysan,
			String congnghiepchebien, String nganhdichvu, String nganhhanghai,
			String nganhxaydung, String addressSequence, String residentCode,
			String nationality, String raceCode, String religionCode,
			String languageIdentifier, String nameFormat, String param1,
			String param2, String customerFirstNameStart,
			String customerFirstNameLength, String param3, String param4,
			String param5, String param6, String param7, String param8,
			String param9, String param10, String dateCIFCreated,
			String dateCIFReview, String id, String idType,
			String dateOfBirthShort, String placeOfBirth, String maritalStatus,
			String maritalDateShorted, String decreaseCustomerFlag,
			String decreaseCustomerFlagDate, String gender, String cfClass,
			String individual, String occupationCode, String subClass,
			String customerRatingCode, String combineCycle,
			String federalWithholdingDate, String tellerCreated,
			String hostNameCreated, String timeCreated, String customerStatus,
			String customerStatusDate, String foreignAddress,
			String addressLine1, String addressLine2, String addressLine3,
			String provincePostalCode, String homePhoneNo,
			String officePhoneNo, String handPhoneNo, String issuesDateShort,
			String issuesCountry, String issuesPlace, String tax,
			String addressLine4, String addressLine5, String dateOfBirthFull,
			String issuesDateFull, String businessType1, String businessType2,
			String businessType3, String cfsubClass, String businessCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_18102I
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
		strValue[17] = "BBMBSCFMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "18102"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // User ID
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "18102"; // Transaction Code
		strValue[46] = "C"; // Create
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = cifNumber; // cif Number
		strValue[68] = "A";
		strValue[69] = "0";
		strValue[70] = branchCode;
		strValue[73] = "0";
		strValue[74] = "C";
		strValue[75] = salution;
		strValue[76] = shortName;
		strValue[77] = shortName;
		strValue[78] = fullName1;
		strValue[79] = fullName2;
		strValue[80] = motherName;
		strValue[81] = inquiryIdCode;
		strValue[83] = insiderCode;
		strValue[84] = vipCustomerCode;
		strValue[88] = codonglonMSB;
		strValue[89] = thamsochuadung1;
		strValue[90] = doanhnghiepvuavanho;
		strValue[91] = tochuctaichinhvanganhang;
		strValue[92] = cacbenlienquan;
		strValue[93] = doanhnghieplon;
		strValue[94] = khachhangmuabaohiem;
		strValue[95] = thamsochuadung2;
		strValue[96] = baohiem;
		strValue[97] = buuchinhvienthong;
		strValue[98] = hangkhong;
		strValue[99] = nuoitrongthuysan;
		strValue[100] = congnghiepchebien;
		strValue[101] = nganhdichvu;
		strValue[102] = nganhhanghai;
		strValue[103] = nganhxaydung;
		strValue[104] = addressSequence;
		strValue[105] = residentCode;
		strValue[107] = nationality;
		strValue[109] = raceCode;
		strValue[110] = religionCode;
		strValue[111] = languageIdentifier;
		strValue[112] = nameFormat;
		strValue[113] = param1;
		strValue[114] = param2;
		strValue[115] = customerFirstNameStart;
		strValue[116] = customerFirstNameLength;
		strValue[117] = param3;
		strValue[118] = param4;
		strValue[119] = param5;
		strValue[120] = param6;
		strValue[121] = param7;
		strValue[122] = param8;
		strValue[123] = param9;
		strValue[124] = param10;
		strValue[126] = dateCIFCreated;
		strValue[127] = dateCIFCreated;
		strValue[128] = dateCIFReview;
		strValue[129] = id;
		strValue[130] = idType;
		strValue[131] = dateOfBirthShort;
		strValue[132] = placeOfBirth;
		strValue[133] = maritalStatus;
		strValue[134] = maritalDateShorted;
		strValue[135] = decreaseCustomerFlag;
		strValue[136] = decreaseCustomerFlagDate;
		strValue[137] = gender;
		strValue[138] = cfClass;
		strValue[139] = individual;
		strValue[141] = occupationCode;
		strValue[142] = subClass;
		strValue[143] = customerRatingCode;
		strValue[145] = combineCycle;
		strValue[148] = federalWithholdingDate;
		strValue[149] = tellerCreated;
		strValue[150] = hostNameCreated;
		strValue[151] = timeCreated;
		strValue[152] = customerStatus;
		strValue[153] = customerStatusDate;
		strValue[154] = foreignAddress;
		strValue[155] = addressLine1;
		strValue[156] = addressLine2;
		strValue[157] = addressLine3;
		strValue[158] = provincePostalCode;
		strValue[159] = homePhoneNo;
		strValue[160] = officePhoneNo;
		strValue[161] = handPhoneNo;
		strValue[162] = issuesDateShort;
		strValue[163] = issuesCountry;
		strValue[164] = issuesPlace;
		strValue[165] = tax;
		strValue[166] = addressLine4;
		strValue[167] = addressLine5;
		strValue[168] = id;
		strValue[169] = idType;
		strValue[170] = dateOfBirthFull;
		strValue[171] = issuesDateFull;
		strValue[172] = cfClass;
		strValue[173] = businessType1;
		strValue[174] = businessType2;
		strValue[175] = businessType3;
		strValue[176] = cfsubClass;
		strValue[177] = businessCode;
		strValue[178] = occupationCode;

		return strValue;
	}

	/**
	 * DateCreate: 31/12/2013; Create message for transfer from GL to SA
	 * 
	 * @param teller
	 * @param manager
	 * @param branchCode
	 * @param journalSeq
	 * @param transDate
	 * @param creditAcc
	 * @param debitAmount
	 * @param debitAcc
	 * @param creditRate
	 * @param debitRate
	 * @param adviceNumber
	 * @param creditCurrency
	 * @param debitCurrency
	 * @param description
	 * @param creditAmount
	 * @return
	 */

	public static String[] createTranferGL2SaMessage(String teller,
			String manager, String branchCode, String journalSeq,
			String transDate, String effectiveDate, String creditAmount,
			String creditAcc, String debitAmount, String debitAcc,
			String creditRate, String debitRate, String adviceNumber,
			String creditCurrency, String debitCurrency, String description,
			String transCode) {

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		String shortIP = "ap";
		// String transCode="2260";
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = teller; // 24 :
		strValue[25] = shortIP; // 25 : ap
		strValue[26] = transCode; // 26 : 2260
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = teller; // 41 :
		strValue[42] = journalSeq; // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[47] = transCode; // 47 :
		strValue[48] = transDate; // 48 : Date Of Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = shortIP; // 51 :
		strValue[52] = manager; // 52 :
		strValue[53] = branchCode; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = creditAcc; // 63 : SA Account
		strValue[66] = debitAmount; // 65 : Transfer Amount
		strValue[67] = effectiveDate; // Efective date
		// 100000 as tranfer 1000 VND
		strValue[71] = debitAcc; // 71 :
		strValue[82] = creditAmount; // 82 : Send phai tinh
		// 100000 as tranfer 1000 VND
		strValue[87] = creditRate; // 87 : sa rate
		strValue[88] = debitRate; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[104] = adviceNumber; // 105 :
		strValue[105] = creditCurrency; // 106 :
		strValue[106] = debitCurrency; // 107 :
		strValue[113] = "3"; // 112 :
		strValue[129] = "2"; // 126 :
		strValue[140] = "3"; // 142 :
		strValue[249] = creditAmount;
		strValue[264] = "2"; // 264 :
		strValue[280] = description; // 280 : Reason of

		return strValue;
	}

	/**
	 * Method: createTranferSA2GLMessage Description: Create mesage for transfer
	 * from SA to GL
	 * 
	 * @param teller
	 * @param manager
	 * @param branchCode
	 * @param journalSeq
	 * @param transDate
	 * @param creditAmount
	 * @param creditAcc
	 * @param debitAmount
	 * @param debitAcc
	 * @param creditRate
	 * @param debitRate
	 * @param adviceNumber
	 * @param creditCurrency
	 * @param debitCurrency
	 * @param description
	 * @return
	 */

	public static String[] createTranferSA2GLMessage(String teller,
			String manager, String branchCode, String journalSeq,
			String transDate, String effectiveDate, String creditAmount,
			String creditAcc, String debitAmount, String debitAcc,
			String creditRate, String debitRate, String adviceNumber,
			String creditCurrency, String debitCurrency, String description,
			String transCode) {

		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		String shortIP = "ap";
		// String transCode="2460";

		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = teller; // 24 :
		strValue[25] = shortIP; // 25 : ap
		strValue[26] = transCode; // 26 : 2260
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = teller; // 41 :
		strValue[42] = journalSeq; // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[47] = transCode; // 47 :
		strValue[48] = transDate; // 48 : Date Of Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = shortIP; // 51 :
		strValue[52] = manager; // 52 :
		strValue[53] = branchCode; // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = debitAcc; // 63 : SA Account
		strValue[66] = creditAmount; // 65 : Transfer Amount
		strValue[67] = effectiveDate;
		// 100000 as tranfer 1000 VND
		strValue[71] = creditAcc; // 71 :
		strValue[82] = debitAmount; // 82 : Send phai tinh
		// 100000 as tranfer 1000 VND
		strValue[87] = debitRate; // 87 : sa rate
		strValue[88] = creditRate; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[104] = adviceNumber; // 105 :
		strValue[105] = debitCurrency; // 106 :
		strValue[106] = creditCurrency; // 107 :
		strValue[113] = "3"; // 112 :
		strValue[129] = "2"; // 126 :
		strValue[140] = "3"; // 142 :
		strValue[249] = debitAmount;
		strValue[264] = "2"; // 264 :
		strValue[280] = description; // 280 : Reason of

		return strValue;
	}

	public static String[] searchAccountInfoForOverdraft(String teller,
			String hostName, String branchCode, String accountNumber,
			String accountType) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26051I
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
		strValue[17] = "BBMBSDDINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "26051"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "26051"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = accountNumber; // account Number
		strValue[68] = accountType; // account Type
		return strValue;
	}

	// thau chi tai khoan
	public static String[] createODPTierMaintain(String teller,
			String hostName, String branchCode, String accountNumber,
			String accountType, String drawLimit, String authLimit,
			String odRate, String argDate, String expDate, String rateVariance,
			String rateCode, String rateFloor, String rateCeiling) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_27050I
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
		strValue[17] = "BBMBSDDMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "27050"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "27050"; // Transaction Code
		strValue[46] = "A"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = accountNumber; // account Number
		strValue[68] = accountType; // account Type
		strValue[70] = "ODP";// tier type
		strValue[71] = drawLimit + "00"; // drawLimit
		strValue[72] = authLimit + "00"; // draw
		strValue[73] = "000";
		strValue[74] = "000";
		strValue[75] = odRate; // lãi suất thấu chi "07002"
		strValue[76] = rateVariance;// rate variance //0.01
		strValue[77] = rateCode;// rate Code // + or -
		strValue[78] = rateFloor; // rateFloor "0.00"
		strValue[79] = rateCeiling;// rateCeiling "0.15"
		strValue[81] = "A";
		strValue[82] = argDate;// agreementDate "260713"
		strValue[83] = expDate;// expirationDate "260713"
		strValue[84] = "R";
		strValue[85] = "A";
		strValue[86] = "000000";
		strValue[88] = "000";
		return strValue;
	}

	public static String[] searchAALoanByCifNo(String teller, String hostName,
			String branchCode, String cifNo) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_83301I
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
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "83301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "83301"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = cifNo; // cifNo
		return strValue;
	}

	public static String[] searchAllFacilityLoan(String teller,
			String hostName, String branchCode, String appNo) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_85303I
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
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "85303"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "85303"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "M";
		strValue[67] = appNo; // application No
		return strValue;
	}

	public static String[] lockFacilityLoan(String teller, String hostName,
			String branchCode, String appNo, String facilityCode, String seqNo) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_86303I
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
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "86303"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "86303"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = appNo; // application No
		strValue[68] = facilityCode;
		strValue[69] = seqNo;
		return strValue;
	}

	// sua facility
	public static String[] loanFacilityMaintain(String teller, String hostName,
			String branchCode, String rcdOveride, String applicationNo,
			String facilityCode, String seqNo, String levelNo, String cifNo,
			String AFCNo, String producType, String AFCStatus,
			String appliedDate, String currencyType, String appliedAmount,
			String purposeCode, String revolving, String aBalIndicator,
			String senttoAppDate, String recFromAppCenter, String approvedDate,
			String appDate, String appBy, String offerDate,
			String acceptedDate, String appAmount, String availabelLimit,
			String facilityLimit, String availableBalance, String interestBase,
			String interestMode, String yearBase, String term, String termCode,
			String interestRate, String variance, String varCode,
			String rateReviewDate, String rateReviewTerm,
			String rateReviewCode, String primeRateFloor,
			String primeRateCeiling, String paymentCode, String paymentFreq,
			String paymentFreqCode, String interestPmtFreq,
			String interestPmtFreqCode, String paymentAmount,
			String finalPaymAmt, String lateChargeCode, String gracePeriod,
			String gracePeriodCode, String expireDate, String processingFee,
			String legalFee, String cifName, String branchTCode,
			String offerCode, String shareLimitInd, String branchTHCode,
			String secureCode, String estimateLose) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_88303I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}

		strValue[0] = "*LINX"; // Header types
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSLNMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "88303"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "88303"; // Transaction Code 88303
		strValue[46] = "C"; // C
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[54] = rcdOveride;// overide transaction rcdOveride
		strValue[67] = applicationNo; // application No
		strValue[68] = facilityCode; // Facility code
		strValue[69] = seqNo;// seq No
		strValue[70] = levelNo; // levelNo
		strValue[71] = cifNo; // seq No
		strValue[72] = AFCNo; // AFCNo
		strValue[73] = producType; // producType
		strValue[74] = AFCStatus; // AFCStatus, 'A','P'
		strValue[75] = appliedDate; // ngay tao
		strValue[76] = currencyType; // currencyType
		strValue[77] = appliedAmount; // applied Amount
		strValue[78] = purposeCode; // purpose code
		strValue[79] = "";
		strValue[80] = revolving; // revolving N/R
		strValue[81] = aBalIndicator;// available Balance Indicator O
		strValue[82] = senttoAppDate;// senttoAppDate "260713"
		strValue[83] = recFromAppCenter; // recFromAppCenter
		strValue[84] = approvedDate;// approvedDate
		strValue[85] = appBy;// appBy
		strValue[86] = offerDate; // offerDate
		strValue[87] = acceptedDate;// acceptedDate
		strValue[88] = appAmount;// appAmount
		strValue[89] = facilityLimit;// facilityLimit
		strValue[90] = availableBalance;// availableBalance
		strValue[91] = interestBase;// interestBase
		strValue[92] = interestMode;// interestMode
		strValue[93] = yearBase;// yearBase
		strValue[94] = term;// term
		strValue[95] = termCode;// termCode
		strValue[97] = interestRate;// interestRate
		strValue[98] = variance;// variance
		strValue[99] = varCode;// varCode
		strValue[100] = rateReviewDate;// rateReviewDate
		strValue[101] = rateReviewTerm;// rateReviewTerm
		strValue[102] = rateReviewCode;// rateReviewCode
		strValue[103] = primeRateFloor;// primeRateFloor
		strValue[104] = primeRateCeiling;// primeRateCeiling
		strValue[105] = paymentCode;// paymentCode
		strValue[106] = paymentFreq;// paymentFreq
		strValue[107] = paymentFreqCode;// paymentFreqCode
		strValue[108] = interestPmtFreq;// interestPmtFreq
		strValue[109] = interestPmtFreqCode;// interestPmtFreqCode
		strValue[110] = paymentAmount;// paymentAmount
		strValue[111] = finalPaymAmt;// finalPaymAmt
		strValue[112] = lateChargeCode;// lateChargeCode
		strValue[113] = gracePeriod;// gracePeriod
		strValue[114] = gracePeriodCode;// gracePeriodCode
		strValue[115] = expireDate;// expireDate
		strValue[116] = processingFee;// processingFee
		strValue[117] = legalFee;// legalFee
		strValue[118] = cifName;// cifName
		strValue[119] = "27";// fix
		strValue[120] = branchTCode;// branchTCode
		strValue[121] = offerCode;// offerCode
		strValue[122] = shareLimitInd;// shareLimitInd
		strValue[125] = branchTHCode;// branchTHCode 3 so
		strValue[126] = secureCode;// secureCode U, S,P
		strValue[127] = availabelLimit;// availabelLimit
		strValue[129] = estimateLose;// estimateLose
		return strValue;
	}

	// unlock facility

	public static String[] unlockFacilityLoan(String teller, String hostName,
			String branchCode, String record) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_88902I
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
		strValue[17] = "BBMBSLNMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "88902"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "88902"; // Transaction Code
		strValue[46] = "C"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[54] = "";
		strValue[67] = record; // user/LNAPPF /aapNo
		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message hold tai khoan

	public static String[] accHold_MBASE27141(HoldAcc holdAcc) {
		String accType = "";
		String branch = "20";

		if ("".equals(holdAcc.getAccType())) {
			accType = "D";
		}
		if ("".equals(holdAcc.getBranch())) {
			branch = "20";

		}
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_27141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX";
		strValue[1] = strHostName;
		strValue[2] = "";
		strValue[3] = "";
		strValue[4] = "";
		strValue[5] = "213";
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "1"; // 14 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = holdAcc.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "27141"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = holdAcc.getTeller(); // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[38] = ""; // 38 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = branch; // 41 :
		strValue[45] = "27141"; // 45 :
		strValue[46] = "A"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[67] = holdAcc.getAccount(); // 67 :
		strValue[68] = accType; // 68 :
		strValue[69] = ""; // 69 :
		strValue[70] = "HD"; // 70 :
		strValue[74] = holdAcc.getHoldAmount(); // 74 :
		strValue[75] = ""; // 75 :
		strValue[76] = "000"; // 76 :
		strValue[77] = holdAcc.getExpireDate(); // 77 :
		strValue[78] = holdAcc.getDescription(); // 78 :
		strValue[79] = ""; // 79 :
		strValue[80] = ""; // 80 :
		strValue[81] = holdAcc.getHoldCode(); // hold code *TRADE/*EA/*RETENTION

		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message Lock tai khoan truoc khi unhold tai
	// khoan

	public static String[] accLock_MBASE26141(HoldLockAcc lockAcc) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "1"; // 14 :
		strValue[17] = "BBMBSDDINQFNC"; // 17 :
		strValue[18] = ""; // 18 :
		strValue[19] = ""; // 19 :
		strValue[20] = ""; // 20 :
		strValue[21] = ""; // 21 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = lockAcc.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "26141"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = lockAcc.getTeller(); // 31 :
		strValue[32] = "1"; // 32 :
		strValue[34] = "*END"; // 34 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = lockAcc.getBranch(); // 41 :
		strValue[45] = "26141"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[56] = "C"; // 56 :
		strValue[67] = lockAcc.getAccount(); // 67 :
		strValue[68] = lockAcc.getAccType(); // 68 :
		strValue[69] = lockAcc.getJournalSeq(); // 69 :

		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message unhold tai khoan

	public static String[] accUnhold_MBASE28141(UnHoldAcc unHold) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX";
		strValue[1] = strHostName;
		strValue[5] = "213";
		strValue[7] = "0200";
		strValue[8] = "*DSP";
		strValue[9] = "MBSD";
		strValue[10] = "*LINX";
		strValue[14] = "01";
		strValue[17] = "BBMBSDDMNTFNC";
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = unHold.getTeller();
		strValue[25] = strHostName;
		strValue[26] = "28141";
		strValue[27] = "N";
		strValue[31] = unHold.getTeller();
		strValue[32] = "1";
		strValue[34] = "*END";
		strValue[35] = ""; //
		strValue[36] = "BTS"; //
		strValue[37] = "RBS"; //
		strValue[39] = strHostName;
		strValue[40] = "27";
		strValue[41] = unHold.getBranch();
		strValue[45] = "28141"; // Transaction Code
		strValue[46] = unHold.getActionCode(); // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		strValue[63] = unHold.getAccount();
		strValue[64] = unHold.getAccType();
		strValue[67] = unHold.getAccount();
		strValue[68] = unHold.getAccType();
		strValue[69] = unHold.getJournalSeq();
		strValue[70] = "HD";
		strValue[71] = "0000000"; // Check date
		strValue[73] = "0000000"; // High check number
		strValue[74] = unHold.getHoldAmount(); // Check Amount
		strValue[75] = ""; // Payee name
		strValue[76] = "0"; // Stop charge
		strValue[77] = unHold.getExpireDate(); // Expiration date
		strValue[78] = unHold.getDescription(); // Stop/Hold remarks:
		strValue[79] = "0"; //
		strValue[80] = "0"; //
		strValue[81] = unHold.getHoldCode();// "*TRADE", "*EA" // Stop/Hold code
		strValue[82] = ".00"; // Penalty amount

		return strValue;
	}

	// Ngay 29/09/2014 Message tat toan the tich kiem

	public static String[] fdWithDrawToGL_ABCS_3360(WithDrawFD fd) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		// Trancode: 3360
		strValue[0] = "*LINX";
		strValue[1] = strHostName;
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = fd.getTeller(); // 24 :
		strValue[25] = "PC"; // 25 :
		strValue[26] = fd.getTransCode(); // 26 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = fd.getTeller(); // 41 :
		strValue[42] = fd.getJonalSequece(); // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[44] = ""; // So sequence cua giao dich can dao
		strValue[47] = fd.getTransCode(); // 47 :
		strValue[48] = fd.getTransDate(); // 48 : Date Of
											// Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = "PC"; // 51 :
		strValue[52] = fd.getSupervisor(); // 52 :
		strValue[53] = fd.getBranch(); // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = ""; // 63 :
		strValue[66] = fd.getCreditAmount(); // 65 : Send
												// 100000 as
		strValue[68] = fd.getFdAcc();
		// tranfer 1000 VND
		strValue[71] = fd.getGlAcc(); // 72 :
		strValue[72] = ""; // 72 :
		strValue[75] = ""; // 75 :
		strValue[79] = "000"; // 79 :
		strValue[80] = "000"; // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = fd.getDebitAmount(); // 82 : Send

		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[89] = "10000000"; // 88 :
		strValue[90] = "10000000"; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = ""; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "4"; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[140] = "3"; // 140 :
		strValue[141] = "4"; // 141:
		strValue[142] = "4"; // 142 :
		strValue[146] = "5"; // 143 :
		strValue[149] = "1"; // 149 :
		strValue[238] = "000"; // 235 :
		strValue[240] = "000";
		// 1000 VND
		strValue[241] = "000"; // 241 :
		strValue[249] = fd.getDebitAmount();
		strValue[253] = "5"; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :

		strValue[264] = "2"; // 264 :
		strValue[280] = fd.getDescription(); //
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

	// Ngay 29/09/2014 Message tat toan the tich kiem chuyen khoan tu FD sang CA

	public static String[] fdWithDrawToCA_ABCS3320(WithDrawFD fd) {

		String strValue[] = DefaultMsg
				.buidABCSMsg(DSPPackager.PACKAGER_ABCS_REQUEST
						.getFieldDefinitionList().length);

		// transcode: 3320

		strValue[24] = fd.getTeller(); // 24 :
		strValue[26] = fd.getTransCode(); // 26 :
		strValue[41] = fd.getTeller(); // 41 :
		strValue[42] = fd.getJonalSequece(); // 42 :JOURNAL
		strValue[47] = fd.getTransCode(); // 47 :
		strValue[48] = fd.getTransDate(); // 48 : Date Of
											// Tranfer
		strValue[52] = fd.getSupervisor(); // 52 :
		strValue[53] = fd.getBranch(); // 53 :
		strValue[65] = fd.getCreditAmount(); // 65 : Send
												// 100000 as
		strValue[68] = fd.getFdAcc();
		// tranfer 1000 VND
		strValue[71] = fd.getDdAcc(); // 72 :
		strValue[72] = ""; // 72 :
		strValue[75] = ""; // 75 :
		strValue[82] = fd.getDebitAmount(); // 82 : Send
											// 100000 as
		// tranfer 1000 VND
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[89] = "00000000"; // 88 :
		strValue[90] = "10000000"; // 88 :
		strValue[93] = fd.getCurrency(); // 93 :
		strValue[105] = fd.getCurrency(); // 105 :
		strValue[106] = fd.getCurrency(); // 106 :
		strValue[108] = fd.getCurrency(); // 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "4"; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		// 140 :
		strValue[142] = "4"; // 141:
		strValue[143] = "4"; // 142 :
		strValue[147] = "5"; // 143 :
		strValue[150] = "1"; // 149 :
		strValue[238] = "000"; // 235 :
		strValue[240] = "000";
		strValue[241] = "000"; // 241 :
		strValue[242] = ""; // 237 :
		strValue[248] = ""; // 248 :
		strValue[249] = fd.getDebitAmount();
		strValue[253] = "5"; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[257] = ""; // 257 :
		strValue[264] = "2"; // 264 :
		strValue[280] = fd.getDescription();
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

	// Ngay 29/09/2014 Message thu phi bang chuyen khoan tu CASA vao GL

	public static String[] chargeFee_ABCS0800(Fee fee) {

		String strValue[] = DefaultMsg
				.buidABCSMsg(DSPPackager.PACKAGER_ABCS_REQUEST
						.getFieldDefinitionList().length);

		// transcode: 0800
		strValue[24] = fee.getTeller(); // 24 :
		strValue[26] = fee.getTransCode(); // 26 :
		strValue[41] = fee.getTeller(); // 41 :
		strValue[42] = fee.getJonalSequece(); // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[44] = ""; // So sequence cua giao dich can dao
		strValue[47] = fee.getTransCode(); // 47 :
		strValue[48] = fee.getTransDate(); // 48 : Date Of
											// Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = "PC"; // 51 :
		strValue[52] = ""; // 52 :
		strValue[53] = fee.getBranch(); // 53 :
		// tranfer 1000 VND
		strValue[71] = fee.getCreditAcc(); // 72 :
		strValue[79] = "000"; // 79 :
		strValue[80] = fee.getTotalAmount(); // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = fee.getTotalAmount(); // 82 : Send
												// 100000 as
		strValue[93] = fee.getCurrency(); // 93 :
		strValue[105] = fee.getCurrency(); // 105 :
		strValue[106] = fee.getCurrency(); // 106 :
		strValue[107] = ""; // 107 :
		strValue[118] = "1";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "2"; // 127 :
		strValue[128] = "2"; // 128 :
		strValue[129] = "2"; // 129 :
		// 140 :
		strValue[140] = "2"; // 141:
		strValue[141] = "2"; // 142 :
		strValue[235] = fee.getDebitAcc();
		strValue[237] = fee.getTotalAmount(); // 235 :
		strValue[240] = fee.getAmount();
		strValue[241] = fee.getVatAmount();

		strValue[253] = "3"; // 252 :
		strValue[255] = "2"; // 255 :
		strValue[256] = "2"; // 256 :
		strValue[257] = ""; // 257 :
		strValue[264] = ""; // 264 :
		strValue[280] = fee.getDescription();
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		strValue[297] = ""; // 297 :
		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message tao tai san the chap

	public static String[] collateral_MBASE87202(Collateral calla) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_87202I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "87202"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller();
		strValue[32] = "1"; // 32 : Reference Number
		strValue[34] = "*END"; // 34 : End of Group Indicator
		strValue[35] = ""; // 35 : Block Message Number
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[38] = ""; // 38 : Return Data Queue Name
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
											// Number
		strValue[45] = "87202"; // Transaction Code
		strValue[46] = "A"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		// DDMMYYYY
		strValue[67] = "DP1"; // Account number
		strValue[68] = calla.getCollateralName(); // Account type
		strValue[69] = calla.getBranch(); // Sequence
		strValue[70] = calla.getCifName(); // Type of entry
		strValue[71] = "1"; // Check date
		strValue[72] = "DP1"; // Low check number
		strValue[73] = "";
		strValue[74] = calla.getCollateralDesc();
		strValue[75] = calla.getDeposit();
		strValue[76] = calla.getOfficeCode();
		strValue[77] = calla.getFdAcc();
		strValue[78] = calla.getCountryCode();
		strValue[79] = calla.getDepositAmount();
		strValue[80] = calla.getCurency();
		strValue[81] = calla.getDepositTenureNum();
		strValue[82] = calla.getDepositTenureType();
		strValue[83] = calla.getCifNum();
		strValue[84] = calla.getInterestRate();
		strValue[85] = calla.getRelationShip();
		strValue[86] = calla.getIssueDate();
		strValue[87] = calla.getCashValueSale();
		strValue[88] = calla.getMaturityDate();
		strValue[90] = calla.getReviewDate();
		strValue[91] = calla.getMarkedValue();
		strValue[92] = calla.getLienOfficeCode();
		strValue[93] = "FD";
		strValue[94] = calla.getIssuingInstitution();
		strValue[95] = calla.getDepositName1();
		strValue[96] = calla.getDepositName2();
		strValue[97] = calla.getDepositName3();
		strValue[98] = calla.getDudateInstruction();
		strValue[99] = calla.getDescription();
		strValue[101] = calla.getCollateralRatio();
		strValue[103] = calla.getInvestment();
		strValue[105] = calla.getBankUsage();
		strValue[108] = calla.getRegPlace();
		strValue[109] = "000000";
		strValue[111] = "000000";
		strValue[114] = calla.getOwnerPlace();
		strValue[115] = calla.getNotaryPublic();
		strValue[116] = "000000";
		strValue[118] = "000000";
		strValue[120] = "000000";
		strValue[122] = "000";
		strValue[123] = "000";

		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message gan tai san db voi HD vay

	public static String[] collateralLink_MBASE87305(CollateralLink calla) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_87305I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "87305"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller(); // 31 : User ID
		strValue[32] = "1"; // 32 : Reference Number
		strValue[33] = ""; // 33 : Rebid Number
		strValue[34] = "*END"; // 34 : End of Group Indicator
		strValue[35] = ""; // 35 : Block Message Number
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[38] = ""; // 38 : Return Data Queue Name
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
											// Number
		strValue[45] = "87305"; // Transaction Code
		strValue[46] = "A"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		// DDMMYYYY
		strValue[67] = calla.getApplicationNo(); //
		strValue[68] = calla.getFacilityCode(); // 240
		strValue[69] = "1";//
		strValue[70] = calla.getCollateralId(); //
		strValue[71] = "1"; // C
		strValue[72] = calla.getIndexDate(); //
		strValue[73] = calla.getPledgedAmount(); //
		strValue[74] = calla.getPercentPledged(); // Check Amount
		strValue[75] = calla.getFixedAmount(); // Payee name
		strValue[76] = calla.getBasicOfadvance(); // Stop charge

		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message lock gan tai san db voi HD vay

	public static String[] lockCollateralLink_MBASE86305(CollateralLink calla) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_86305I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSLNMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "86305"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller(); // 31 : User ID
		strValue[32] = "1"; // 32 : Reference Number
		strValue[34] = "*END"; // 34 : End of Group Indicator
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
											// Number
		strValue[45] = "86305"; // Transaction Code
		strValue[46] = "I"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		strValue[56] = "D"; // 56 : Response Reason For Code 3
		strValue[67] = calla.getApplicationNo(); // Account number
		strValue[68] = calla.getFacilityCode(); // 240
		strValue[69] = "1";//
		strValue[70] = calla.getCollateralId(); //

		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message xoa gan tai san db voi HD vay

	public static String[] deleteCollateralLink_MBASE89305(CollateralLink calla) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_89305I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSLNMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "89305"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller(); // 31 : User ID
		strValue[32] = "1"; // 32 : Reference Number
		strValue[34] = "*END"; // 34 : End of Group Indicator
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
		strValue[45] = "89305"; // Transaction Code
		strValue[46] = "D"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		strValue[56] = "D"; // 56 : Response Reason For Code 3
		// DDMMYYYY
		strValue[67] = calla.getApplicationNo(); // Account number
		strValue[68] = calla.getFacilityCode(); // 240
		strValue[69] = "1";//
		strValue[70] = calla.getCollateralId(); //

		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message xoa gan tai san db voi HD vay

	public static String[] lockCollateral_MBASE86202(CollateralLink calla) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_86202I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSLNINQFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "86202"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller(); // 31 : User ID
		strValue[32] = "1"; // 32 : Reference Number
		strValue[34] = "*END"; // 34 : End of Group Indicator
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
											// Number
		strValue[45] = "86202"; // Transaction Code
		strValue[46] = "I"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		strValue[56] = "C"; // 56 : Response Reason For Code 3
		strValue[67] = calla.getCollateralId(); //
		return strValue;
	}

	// Ngay 26/09/2014 QuanLD them message xoa gan tai san db voi HD vay

	public static String[] deleteCollateral_MBASE88222(CollateralLink calla) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_88222I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[2] = ""; // 2 :
		strValue[3] = ""; // 3 :
		strValue[4] = ""; // 4 :
		strValue[5] = "213"; // 5 :
		strValue[6] = ""; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[11] = ""; // 11 :
		strValue[12] = ""; // 12 :
		strValue[13] = ""; // 13 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSLNMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "88222"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller(); // 31 : User ID
		strValue[32] = "1";
		strValue[33] = "";
		strValue[34] = "*END";
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[38] = ""; // 38 : Return Data Queue Name
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
		strValue[45] = "88222"; // Transaction Code
		strValue[46] = "C"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = calla.getCollateralId(); //
		strValue[67] = "1";
		strValue[71] = ""; // C

		return strValue;
	}

	// Lock tai khoan truoc khi maintain

	public static String[] lockAccForUpdate_MB26901(LockAcc acc) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26901I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSDDINQFNC"; // 17 :
		strValue[22] = "10"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = acc.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "26901"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = acc.getTeller(); // 31 :
		strValue[32] = "1"; // 32 :
		strValue[33] = ""; // 33 :
		strValue[34] = "*END"; // 34 :
		strValue[35] = ""; // 35 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = acc.getBranch(); // 41 :
		strValue[45] = "26901"; // 45 :
		strValue[46] = "I"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "10"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[56] = "C"; // 56 :
		strValue[63] = acc.getAccNo(); // 63 :
		strValue[64] = "D"; // 64 :
		strValue[67] = acc.getAccNo(); // 67 :
		strValue[68] = acc.getAccType(); // 68 :

		return strValue;
	}

	// Dang ky tai khoan thấu chi
	public static String[] RegisterOD_MB28901(AccInfo acc) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28901I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = acc.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "28901"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = acc.getTeller(); // 31 :
		strValue[32] = "1"; // 32 :
		strValue[34] = "*END"; // 34 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = acc.getBranch(); // 41 :
		strValue[45] = "28901"; // 45 :
		strValue[46] = "C"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[56] = "C"; // 56 :
		strValue[67] = acc.getAccountNo(); // 67 :
		strValue[68] = acc.getAccType(); // 68 :
		strValue[69] = acc.getNewProduct();
		strValue[70] = acc.getNewDateProduct();
		strValue[71] = acc.getAccStatus(); // 71 :
		strValue[74] = acc.getStmt(); // 74 :
		strValue[75] = acc.getServiceChg(); // 75 :
		strValue[76] = acc.getIntCycle(); // 76 :
		strValue[77] = acc.getServiceChange(); // 77 :
		strValue[78] = acc.getChequeAllowed(); // 78 : Cheque
		strValue[79] = acc.getHoldMainCode();
		strValue[80] = acc.getIntroducerCode(); // 80 :
		strValue[81] = acc.getPar_81(); // 81 :
		strValue[82] = acc.getPar_82(); // 82 :
		strValue[83] = acc.getPar_83(); // 83 :
		strValue[84] = acc.getPar_84(); // 84 :
		strValue[85] = acc.getPar_85();
		strValue[86] = acc.getPar_86_100(); // 86 :
		strValue[87] = acc.getPar_87();
		strValue[88] = acc.getPar_88_100();
		strValue[89] = acc.getPar_89_100(); // 89 :
		strValue[90] = acc.getPar_90_100(); // 90 :
		strValue[91] = acc.getPar_91_100(); // 91 :
		strValue[92] = acc.getPar_92(); // 92 :
		strValue[93] = acc.getPar_93(); // 93 :
		strValue[94] = acc.getPar_94(); // 94 :
		strValue[95] = acc.getPar_95(); // 95 :
		strValue[96] = acc.getPar_96(); // 96 :
		strValue[96] = acc.getSaleOfficer_97();
		strValue[98] = acc.getProductGroup_98(); // 98 :
		strValue[99] = acc.getPar_99(); // 99 :
		strValue[103] = acc.getPar_113(); // 103 :
		strValue[104] = acc.getPar_114();
		strValue[105] = acc.getPar_115();
		strValue[107] = acc.getPar_118_100(); // 107 :
		strValue[108] = acc.getPar_119(); // 108 :
		strValue[109] = acc.getPar_120_100(); // 109 :
		strValue[110] = acc.getPar_121_100(); // 110 :
		strValue[114] = acc.getExpense117_100(); // 114 :
		strValue[115] = "N"; // 115 :
		strValue[116] = acc.getAccountName(); // 116 :
		strValue[117] = acc.getStaffAcc_129();
		strValue[118] = acc.getOverDraffProtect_130();
		return strValue;
	}

	// thau chi tai khoan Lock tai khoan thâu chi trước khi maintain
	public static String[] lockODPTier_26050(LockODP lock) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26050I
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
		strValue[17] = "BBMBSDDINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = lock.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "26050"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = lock.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = lock.getBranch(); // Branch Code
		strValue[45] = "26050"; // Transaction Code
		strValue[46] = "I"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = lock.getAccountNumber(); // account Number
		strValue[68] = lock.getAccountType();// account Type
		strValue[69] = lock.getJounalSequence();

		return strValue;
	}

	// thau chi tai khoan
	public static String[] addODPTier_MB27050(OdpEntity odp) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_27050I
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
		strValue[17] = "BBMBSDDMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = odp.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "27050"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = odp.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = odp.getBranch(); // Branch Code
		strValue[45] = "27050"; // Transaction Code
		strValue[46] = "A"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = odp.getAccountNumber(); // account Number
		strValue[68] = odp.getAccountType(); // account Type
		strValue[70] = "ODP";// tier type
		strValue[71] = odp.getDrawLimit(); // drawLimit
		strValue[72] = odp.getAuthLimit(); // draw
		strValue[73] = "000";
		strValue[74] = "000";
		strValue[75] = odp.getOdRate(); // lãi suất thấu chi "07002"
		strValue[76] = odp.getRateVariance();// rate variance //0.01
		strValue[77] = odp.getRateCode();// rate Code // + or -
		strValue[78] = odp.getRateFloor(); // rateFloor "0.00"
		strValue[79] = odp.getRateCeiling();// rateCeiling "0.15"
		strValue[80] = odp.getCommitFreeRate();
		strValue[81] = odp.getCommitFreeType();
		strValue[82] = odp.getArgDate();// agreementDate "260713"
		strValue[83] = odp.getExpDate();// expirationDate "260713"
		strValue[84] = odp.getProductGroup();
		strValue[85] = odp.getExcessLimitType();
		strValue[86] = "000000";
		strValue[88] = "000";
		return strValue;
	}

	// thau chi tai khoan Lock tai khoan thâu chi trước khi maintain
	public static String[] lockODPTier_MB26051(LockODP lock) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_26051I
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
		strValue[17] = "BBMBSDDINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = lock.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "26051"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = lock.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = lock.getBranch(); // Branch Code
		strValue[45] = "26051"; // Transaction Code
		strValue[46] = "I"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "";
		strValue[67] = lock.getAccountNumber(); // account Number
		strValue[68] = lock.getAccountType();// account Type
		// strValue[69] = lock.getJounalSequence();

		return strValue;
	}

	// thau chi tai khoan Lock tai khoan thâu chi trước khi maintain
	public static String[] maintainODPTier_MB28050(OdpEntity odp) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28050I
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
		strValue[17] = "BBMBSDDMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = odp.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "28050";
		strValue[27] = "N";
		strValue[31] = odp.getTeller();
		strValue[32] = "1";
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName;
		strValue[40] = "27";
		strValue[41] = odp.getBranch(); // Branch Code
		strValue[45] = "28050"; // Transaction Code
		strValue[46] = "C"; // locl
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = odp.getAccountNumber(); // account Number
		strValue[68] = odp.getAccountType();// account Type
		strValue[69] = odp.getJounalSequence();
		strValue[70] = "ODP";// tier type
		strValue[71] = odp.getDrawLimit(); // drawLimit
		strValue[72] = odp.getAuthLimit(); // draw
		strValue[73] = "000";
		strValue[74] = "000";
		strValue[75] = odp.getOdRate(); // lãi suất thấu chi "07002"
		strValue[76] = odp.getRateVariance();// rate variance //0.01
		strValue[77] = odp.getRateCode();// rate Code // + or -
		strValue[78] = odp.getRateFloor(); // rateFloor "0.00"
		strValue[79] = odp.getRateCeiling();// rateCeiling "0.15"
		strValue[80] = odp.getCommitFreeRate();
		strValue[81] = odp.getCommitFreeType();
		strValue[82] = odp.getArgDate();// agreementDate "260713"
		strValue[83] = odp.getExpDate();// expirationDate "260713"
		strValue[84] = odp.getProductGroup();
		strValue[85] = odp.getExcessLimitType();
		strValue[86] = "000000";
		strValue[88] = "000";

		return strValue;
	}

	// Search AA by cif
	public static String[] searchAAByCif_MB85301(String cifNum, String teller,
			String branch) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_85301I
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
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "10";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "85301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branch; // Branch Code
		strValue[45] = "85301"; // Transaction Code
		strValue[46] = "I"; // add
		strValue[47] = "R"; // response
		strValue[48] = "10"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = cifNum; // account Number

		// strValue[69] = lock.getJounalSequence();

		return strValue;
	}

	// tao AA
	public static String[] addAA_MB88301(AAEntity aa) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_88301I
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
		strValue[17] = "BBMBSLNMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = aa.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "88301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = aa.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = aa.getBranch(); // Branch Code
		strValue[45] = "88301"; // Transaction Code
		strValue[46] = "C"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "";
		strValue[67] = aa.getApplicationNo(); // application Number
		strValue[68] = aa.getCifNum();
		strValue[69] = aa.getApplicationDate();
		strValue[71] = aa.getBranch(); // lpad 5 '0'
		strValue[72] = aa.getWorkingEx();
		strValue[73] = aa.getSpecProvision(); // Y/N
		strValue[74] = aa.getClasifielDate();
		strValue[75] = aa.getCarCode();
		strValue[76] = aa.getReviewDate();
		strValue[77] = aa.getRetention();
		strValue[78] = aa.getReviewReMark();
		strValue[79] = aa.getRefinanceFrom();
		strValue[80] = aa.getTucachPn();
		strValue[81] = aa.getTinhHinhTc();
		strValue[82] = aa.getKhaNangVayVon();
		strValue[83] = aa.getDamBaoTienVay();
		strValue[84] = aa.getLimitAmount();
		strValue[85] = aa.getCurencyType();
		strValue[86] = aa.getOfficeCode();
		strValue[87] = aa.getClassification();
		strValue[88] = aa.getMARemarck();
		strValue[89] = aa.getMADate();
		return strValue;
	}

	// tao AA
	public static String[] lockAAForApprove_MB86301(AAEntity aa) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_88301I
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
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = aa.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "86301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = aa.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = aa.getBranch(); // Branch Code
		strValue[45] = "86301"; // Transaction Code
		strValue[46] = "I"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = aa.getApplicationNo(); // application Number

		return strValue;
	}

}
