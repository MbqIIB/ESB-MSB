package vn.com.msb.as400.dsp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ftl.util.StringUtil;

public class BDS24Factory {
	public BDS24Factory() {
		super();
	}

	public static String[] createCAInQuiryMessage(String teller,
			String strAccount, String transDate, String transCode,
			String strJournalSeq) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*INBK"; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "0200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = "*INBK"; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = transCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Correction
		strValue[47] = transCode; // Transaction Code
		strValue[48] = transDate; // StringUtil.format(new
		// java.util.Date(),"ddMMyy"); //
		// Transaction Date
		strValue[50] = "V1"; // Control unit
		strValue[51] = strHostName; // Work station
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

		strValue[0] = "*INBK"; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "0200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = "*INBK"; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = transCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = transCode; // Comment for field 47 :
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "V1"; // Comment for field 50 :
		strValue[51] = strHostName; // Comment for field 51 :
		strValue[52] = manager; // Comment for field 52 :
		strValue[53] = branchCode; // Comment for field 53 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[63] = debitAccount; // Comment for field 63 :
		strValue[65] = debitAmount + "00"; // Comment for field 65 : Send
		// 100000 as tranfer 1000 VND
		strValue[71] = creditAccount; // Comment for field 71 :
		strValue[79] = "000"; // Comment for field 79 :
		strValue[80] = totalFee == 0 ? "" : totalFee + "00";// vatFee +
															// serviceFee
		strValue[81] = "000"; // Comment for field 81 :
		strValue[82] = creditAmount + "00"; // Comment for field 82 : Send
		// 100000 as tranfer 1000 VND
		strValue[87] = debitRate; // Comment for field 87 :
		strValue[88] = creditRate; // Comment for field 88 :
		strValue[93] = "VND"; // Comment for field 93 :
		strValue[105] = debitCurrency; // Comment for field 105 :
		strValue[106] = creditCurrency; // Comment for field 106 :
		strValue[107] = creditCurrency; // Comment for field 107 :
		strValue[108] = creditCurrency; // Comment for field 108 :
		strValue[112] = "3"; // Comment for field 112 :
		strValue[126] = "1"; // Comment for field 126 :
		strValue[128] = "4"; // Comment for field 128 :
		strValue[129] = "2"; // Comment for field 129 :
		strValue[142] = "4"; // Comment for field 142 :
		strValue[143] = "4"; // Comment for field 143 :
		strValue[150] = "1"; // Comment for field 150 :
		strValue[235] = debitAccount;
		strValue[237] = totalFee == 0 ? "" : totalFee + "00"; // vatFree +
																// serviceFree
		strValue[239] = "000";
		strValue[240] = serviceFee + "00";
		strValue[241] = vatFee + "00";
		strValue[249] = debitAmount + "00"; // Comment for field 249 : Send
		// 100000 as tranfer 1000 VND
		strValue[252] = ""; // Comment for field 252 :
		strValue[255] = "4"; // Comment for field 255 :
		strValue[256] = "4"; // Comment for field 256 :
		strValue[264] = "1"; // Comment for field 264 :
		strValue[280] = strDescription; // Comment for field 280 : Reason of
		// transfer
		strValue[284] = creditAmount + "00"; // Comment for field 284 : Send
		// 100000 as tranfer 1000 VND
		strValue[285] = "000"; // Comment for field 285 :
		strValue[286] = "000"; // Comment for field 286 :
		strValue[294] = "3"; // Comment for field 294 :
		strValue[295] = "1"; // Comment for field 295 :
		strValue[296] = "1"; // Comment for field 296 :
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
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = "us"; // Comment for field 25 :
		strValue[26] = strTransCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "Y"; // Comment for field 43 : Correction
		strValue[44] = originseq; // So sequence cua giao dich can dao
		strValue[47] = strTransCode; // Comment for field 47 :
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "V1"; // Comment for field 50 :
		strValue[51] = "us"; // Comment for field 51 :
		strValue[52] = attestor; // Comment for field 52 :
		strValue[53] = "20"; // Comment for field 53 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[63] = strFromAccount; // Comment for field 63 :
		strValue[66] = strAmount; // Comment for field 65 : Send 100000 as
		// tranfer 1000 VND
		strValue[72] = "0"; // Comment for field 72 :
		strValue[75] = "999"; // Comment for field 75 :
		strValue[79] = "0"; // Comment for field 79 :
		strValue[80] = ""; // Comment for field 80 :
		strValue[81] = "0"; // Comment for field 81 :
		strValue[82] = strAmount; // Comment for field 82 : Send 100000 as
		// tranfer 1000 VND
		strValue[87] = "10000000"; // Comment for field 87 :
		strValue[88] = "10000000"; // Comment for field 88 :
		strValue[89] = "10000000"; // Comment for field 88 :
		strValue[90] = "10000000"; // Comment for field 88 :
		strValue[93] = "VND"; // Comment for field 93 :
		strValue[105] = "VND"; // Comment for field 105 :
		strValue[106] = "VND"; // Comment for field 106 :
		strValue[107] = "VND"; // Comment for field 107 :
		strValue[108] = "VND"; // Comment for field 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "2";// Amt field curr usage 10
		strValue[126] = "1"; // Comment for field 126 :
		strValue[127] = ""; // Comment for field 127 :
		strValue[128] = "4"; // Comment for field 128 :
		strValue[129] = "2"; // Comment for field 129 :
		strValue[140] = "3"; // Comment for field 140 :
		strValue[141] = "4"; // Comment for field 141:
		strValue[142] = "4"; // Comment for field 142 :
		strValue[143] = "4"; // Comment for field 143 :
		strValue[149] = "1"; // Comment for field 149 :
		strValue[235] = ""; // Comment for field 235 :
		strValue[237] = ""; // Comment for field 237 :
		strValue[240] = "0"; // Comment for field 240 : Send 100000 as charge
		// 1000 VND
		strValue[241] = "0"; // Comment for field 241 :
		strValue[242] = "0"; // Comment for field 237 :
		strValue[248] = "0"; // Comment for field 248 :
		strValue[249] = strAmount; // Comment for field 249 : Send 100000 as
		// tranfer 1000 VND
		strValue[252] = ""; // Comment for field 252 :
		strValue[255] = "4"; // Comment for field 255 :
		strValue[256] = "4"; // Comment for field 256 :
		strValue[257] = "4"; // Comment for field 257 :
		strValue[263] = "1"; // Comment for field 263 :
		strValue[264] = "2"; // Comment for field 264 :
		strValue[280] = strDescription; // Comment for field 280 : Reason of
		// transfer
		strValue[285] = "0"; // Comment for field 285 :
		strValue[286] = "0"; // Comment for field 286 :
		strValue[287] = strAmount; // Comment for field 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[288] = "0"; // Comment for field 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[295] = "1"; // Comment for field 295 :
		strValue[296] = "1"; // Comment for field 296 :
		strValue[297] = "1"; // Comment for field 297 :
		strValue[298] = "1"; // Comment for field 298 :
		return strValue;
	}

	public static String[] createHoldMessage(String strSystemName,
			String teller, String strAccount, String transHoldToDate,
			String strDescription, String strHoldAmount) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_97141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[8] = "*DSP"; // Comment for field 8 :
		strValue[9] = "MBSD"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[14] = "1"; // Comment for field 14 :
		// strValue[17] = "BBMBSDDMNTFNC"; // Comment for field 17 :
		strValue[17] = "BBMBSMDMNTFNC"; // Comment for field 17 :
		strValue[22] = "1"; // Comment for field 22 :
		strValue[23] = "10"; // Comment for field 23 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = "97141"; // Comment for field 26 :
		strValue[27] = "N"; // Comment for field 27 :
		strValue[31] = teller; // Comment for field 31 :
		strValue[32] = "1"; // Comment for field 32 :
		strValue[34] = "*END"; // Comment for field 34 :
		strValue[36] = "BTS"; // Comment for field 36 :
		strValue[37] = "RBS"; // Comment for field 37 :
		strValue[39] = strHostName; // Comment for field 39 :
		strValue[40] = "27"; // Comment for field 40 :
		strValue[41] = "20"; // Comment for field 41 :
		strValue[45] = "97141"; // Comment for field 45 :
		strValue[46] = "A"; // Comment for field 46 :
		strValue[47] = "R"; // Comment for field 47 :
		strValue[48] = "1"; // Comment for field 48 :
		strValue[49] = "N"; // Comment for field 49 :
		strValue[50] = "F"; // Comment for field 50 :
		strValue[67] = strAccount; // Comment for field 67 :
		strValue[68] = "D"; // Comment for field 68 :
		strValue[70] = "HD"; // Comment for field 70 :
		strValue[74] = strHoldAmount; // Comment for field 74 :
		strValue[76] = "000"; // Comment for field 76 :
		strValue[77] = transHoldToDate; // Comment for field 77 :
		strValue[78] = strDescription; // Comment for field 78 :
		strValue[81] = "*EA"; // Comment for field 81 :

		return strValue;
	}

	public static String[] createUnHoldMessage(String strSystemName,
			String teller, String strAccount, String strDescription,
			String strHoldAmount, String strJournalSeq) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_28141I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[2] = ""; // Comment for field 2 :
		strValue[3] = ""; // Comment for field 3 :
		strValue[4] = ""; // Comment for field 4 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = ""; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[8] = "*DSP"; // Comment for field 8 :
		strValue[9] = "MBSD"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[11] = ""; // Comment for field 11 :
		strValue[12] = ""; // Comment for field 12 :
		strValue[13] = ""; // Comment for field 13 :
		strValue[14] = "1"; // Comment for field 14 :
		strValue[15] = ""; // Comment for field 15 :
		strValue[16] = ""; // Comment for field 16 :
		strValue[17] = "BBMBSDDMNTFNC"; // Comment for field 17 :
		strValue[18] = ""; // Comment for field 18 :
		strValue[19] = ""; // Comment for field 19 :
		strValue[20] = ""; // Comment for field 20 :
		strValue[21] = ""; // Comment for field 21 :
		strValue[22] = "1"; // Comment for field 22 :
		strValue[23] = "10"; // Comment for field 23 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = "28141"; // Comment for field 26 :
		strValue[27] = "N"; // Comment for field 27 :
		strValue[28] = ""; // Comment for field 28 :
		strValue[29] = ""; // Comment for field 29 :
		strValue[30] = ""; // Comment for field 30 : Response Result Code
		strValue[31] = teller; // Comment for field 31 : User ID
		strValue[32] = "1"; // Comment for field 32 : Reference Number
		strValue[33] = ""; // Comment for field 33 : Rebid Number
		strValue[34] = "*END"; // Comment for field 34 : End of Group Indicator
		strValue[35] = ""; // Comment for field 35 : Block Message Number
		strValue[36] = "BTS"; // Comment for field 36 : Source ID
		strValue[37] = "RBS"; // Comment for field 37 : Destination ID
		strValue[38] = ""; // Comment for field 38 : Return Data Queue Name
		strValue[39] = strHostName; // Comment for field 39 : Terminal ID
		strValue[40] = "27"; // Comment for field 40 : Bank Number
		strValue[41] = "20"; // Comment for field 41 : Branch Number
		strValue[42] = ""; // Comment for field 42 : Review Supervisor ID
		// (Local)
		strValue[43] = ""; // Comment for field 43 : Transmit Supervisor ID
		// (Local)
		strValue[44] = ""; // Comment for field 44 : Host Supervisor ID
		strValue[45] = "28141"; // Transaction Code
		strValue[46] = "D"; // Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N"; // More Records Indicator
		strValue[50] = "F"; // Search Method
		strValue[51] = ""; // Response Error Code 1
		strValue[52] = ""; // Comment for field 52 : Response Reason For Code 1
		strValue[53] = ""; // Comment for field 53 : Response Error Code 2
		strValue[54] = ""; // Comment for field 54 : Response Reason For Code 2
		strValue[55] = ""; // Comment for field 55 : Response Error Code 3
		strValue[56] = ""; // Comment for field 56 : Response Reason For Code 3
		strValue[57] = ""; // Comment for field 57 : Response Error Code 4
		strValue[58] = ""; // Comment for field 58 : Response Reason For Code 4
		strValue[59] = ""; // Comment for field 59 : Response Error Code 5
		strValue[60] = ""; // Comment for field 60 : Response Reason For Code 5
		strValue[61] = ""; // Comment for field 61 : Date In From Client
		// DDMMYYYY
		strValue[62] = ""; // Comment for field 62 : Time In From Client HHMMSS
		strValue[63] = strAccount; // Comment for field 63 : Account No
		strValue[64] = "D"; // Comment for field 64 : Account Type
		strValue[65] = ""; // Comment for field 65 : CIF No
		strValue[66] = ""; // Comment for field 66 : Filler
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
		strValue[77] = "280907"; // Expiration date
		strValue[78] = strDescription; // Stop/Hold remarks:
		strValue[79] = "0"; // Block for Account number
		strValue[80] = "0"; // Block for account type
		strValue[81] = "*TRADE"; // Stop/Hold code
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
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[2] = ""; // Comment for field 2 :
		strValue[3] = ""; // Comment for field 3 :
		strValue[4] = ""; // Comment for field 4 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = ""; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[8] = "*DSP"; // Comment for field 8 :
		strValue[9] = "MBSD"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[11] = ""; // Comment for field 11 :
		strValue[12] = ""; // Comment for field 12 :
		strValue[13] = ""; // Comment for field 13 :
		strValue[14] = "1"; // Comment for field 14 :
		strValue[15] = ""; // Comment for field 15 :
		strValue[16] = ""; // Comment for field 16 :
		strValue[17] = "BBMBSDDINQFNC"; // Comment for field 17 :
		strValue[18] = ""; // Comment for field 18 :
		strValue[19] = ""; // Comment for field 19 :
		strValue[20] = ""; // Comment for field 20 :
		strValue[21] = ""; // Comment for field 21 :
		strValue[22] = "1"; // Comment for field 22 :
		strValue[23] = "10"; // Comment for field 23 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = "26141"; // Comment for field 26 :
		strValue[27] = "N"; // Comment for field 27 :
		strValue[28] = ""; // Comment for field 28 :
		strValue[29] = ""; // Comment for field 29 :
		strValue[30] = ""; // Comment for field 30 :
		strValue[31] = teller; // Comment for field 31 :
		strValue[32] = "1"; // Comment for field 32 :
		strValue[33] = ""; // Comment for field 33 :
		strValue[34] = "*END"; // Comment for field 34 :
		strValue[35] = ""; // Comment for field 35 :
		strValue[36] = "BTS"; // Comment for field 36 :
		strValue[37] = "RBS"; // Comment for field 37 :
		strValue[38] = ""; // Comment for field 38 :
		strValue[39] = strHostName; // Comment for field 39 :
		strValue[40] = "27"; // Comment for field 40 :
		strValue[41] = "20"; // Comment for field 41 :
		strValue[42] = ""; // Comment for field 42 :
		strValue[43] = ""; // Comment for field 43 :
		strValue[44] = ""; // Comment for field 44 :
		strValue[45] = "26141"; // Comment for field 45 :
		strValue[46] = "I"; // Comment for field 46 :
		strValue[47] = "R"; // Comment for field 47 :
		strValue[48] = "1"; // Comment for field 48 :
		strValue[49] = "N"; // Comment for field 49 :
		strValue[50] = "F"; // Comment for field 50 :
		strValue[51] = ""; // Comment for field 51 :
		strValue[52] = ""; // Comment for field 52 :
		strValue[53] = ""; // Comment for field 53 :
		strValue[54] = ""; // Comment for field 54 :
		strValue[55] = ""; // Comment for field 55 :
		strValue[56] = "C"; // Comment for field 56 :
		strValue[57] = ""; // Comment for field 57 :
		strValue[58] = ""; // Comment for field 58 :
		strValue[59] = ""; // Comment for field 59 :
		strValue[60] = ""; // Comment for field 60 :
		strValue[61] = ""; // Comment for field 61 :
		strValue[62] = ""; // Comment for field 62 :
		strValue[63] = ""; // Comment for field 63 :
		strValue[64] = ""; // Comment for field 64 :
		strValue[65] = ""; // Comment for field 65 :
		strValue[66] = ""; // Comment for field 66 :
		strValue[67] = strAccount; // Comment for field 67 :
		strValue[68] = "D"; // Comment for field 68 :
		strValue[69] = strJournalSeq; // Comment for field 69 :

		return strValue;
	}

	public static String[] createFDMessage(String strteller,
			String strBankcode, String transDate, String strAccount,
			String strCifnumber, String strNameAcount, String strCurrencyType,
			String strmodeOfOperation) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		strValue[0] = "*INBK"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*INBK"; // Source ID
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

	public static String[] createFDReceiptMessage(String strteller,
			String transCode, String strBankcode, String transDate,
			String sequence, String fdAccount, String amount, String receipt,
			String receipt_currency, String product_code, String remark) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		strValue[0] = "*INBK"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[6] = "42"; // Message Length
		strValue[7] = "200"; // Version
		strValue[9] = "ABCS"; // Data format
		strValue[10] = "*INBK"; // Source ID
		strValue[17] = "BBHTLMONEYFNC"; // Scenario Number
		strValue[24] = strteller; // User ID
		strValue[25] = "10.10.10.10"; // Terminal ID
		strValue[26] = transCode; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = strteller; // TELLER ID
		strValue[42] = sequence; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = transCode; // Transaction Code
		strValue[48] = transDate; //
		strValue[50] = "VD"; // Control unit
		strValue[51] = "OK"; // Work station
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
		strValue[63] = fdAccount; // Single Fields 01, Account here
		strValue[66] = amount; // Single Fields 01, Account here
		strValue[67] = transDate; // Currency code
		strValue[68] = receipt;
		strValue[78] = "0000000000";
		strValue[93] = receipt_currency;
		strValue[95] = product_code;
		strValue[99] = "Y";
		strValue[100] = "T";
		strValue[113] = "1";
		strValue[129] = "1";
		strValue[130] = "1";
		strValue[280] = remark;

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
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "1100"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = "us"; // Comment for field 25 :
		strValue[26] = strTransCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = strTransCode; // Comment for field 47 : 1360
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "V1"; // Comment for field 50 :
		strValue[51] = strHostName; // Comment for field 51 :
		// strValue[52] = attestor; // Comment for field 52 :
		strValue[53] = "110"; // Comment for field 53 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[63] = strFromAccount; // Comment for field 63 :
		strValue[66] = strAmount; // Comment for field 65 : Send 100000 as
		// tranfer 1000 VND
		strValue[79] = "000"; // Comment for field 79 :
		strValue[80] = ""; // Comment for field 80 :
		strValue[81] = "000"; // Comment for field 81 :
		strValue[82] = strAmount; // Comment for field 82 : Send 100000 as
		// tranfer 1000 VND
		strValue[87] = "10000000"; // Comment for field 87 :
		strValue[88] = "10000000"; // Comment for field 88 :
		strValue[93] = "VND"; // Comment for field 93 :
		strValue[105] = "VND"; // Comment for field 105 :
		strValue[106] = "VND"; // Comment for field 106 :
		strValue[107] = "VND"; // Comment for field 107 :
		strValue[108] = "VND"; // Comment for field 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "2";// Amt field curr usage 10
		strValue[126] = "1"; // Comment for field 126 :
		strValue[127] = ""; // Comment for field 127 :
		strValue[128] = "4"; // Comment for field 128 :
		strValue[129] = "2"; // Comment for field 129 :
		strValue[140] = "3"; // Comment for field 140 :
		strValue[141] = "4"; // Comment for field 141:
		strValue[142] = "4"; // Comment for field 142 :
		strValue[143] = "4"; // Comment for field 143 :
		strValue[149] = "1"; // Comment for field 149 :
		strValue[235] = ""; // Comment for field 235 :
		strValue[237] = ""; // Comment for field 237 :
		strValue[240] = "0"; // Comment for field 240 : Send 100000 as charge
		// 1000 VND
		strValue[241] = "0"; // Comment for field 241 :
		strValue[242] = "0"; // Comment for field 237 :
		strValue[248] = "0"; // Comment for field 248 :
		strValue[249] = strAmount; // Comment for field 249 : Send 100000 as
		// tranfer 1000 VND
		strValue[252] = ""; // Comment for field 252 :
		strValue[255] = "4"; // Comment for field 255 :
		strValue[256] = "4"; // Comment for field 256 :
		strValue[257] = "4"; // Comment for field 257 :
		strValue[263] = "1"; // Comment for field 263 :
		strValue[264] = "2"; // Comment for field 264 :
		strValue[280] = strDescription; // Comment for field 280 : Reason of
		// transfer
		strValue[285] = "0"; // Comment for field 285 :
		strValue[286] = "0"; // Comment for field 286 :
		strValue[287] = strAmount; // Comment for field 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[288] = "0"; // Comment for field 284 : Send 100000 as
		// tranfer 1000 VND
		strValue[295] = "1"; // Comment for field 295 :
		strValue[296] = "1"; // Comment for field 296 :
		strValue[297] = "1"; // Comment for field 297 :
		strValue[298] = "1"; // Comment for field 298 :
		return strValue;
	}

	public static String[] createFdInquirySettementMessage(
			String strSystemName, String teller, String fdAccount,
			String transDate, String strJournalSeq, String strTransCode) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = strTransCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = strTransCode; // Comment for field 47 : 1360
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = strHostName; // Comment for field 51 :
		strValue[52] = teller; // Comment for field 52 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[68] = fdAccount; // Comment for field 63 :

		return strValue;
	}

	public static String[] createFdSettementMessage(String strSystemName,
			String teller, String fdAccount, String transDate,
			String strJournalSeq, String strTransCode, String branchCode,
			String manager, String amount, String account, String fee,
			String vatFee, String totalFee, String serial, String buy_rate,
			String receive_sell_rate, String receipt_ccy,
			String rollin_acc_ccy, String remark) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = strTransCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = strTransCode; // Comment for field 47 : 1360
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = strHostName; // Comment for field 51 :
		strValue[52] = manager; // Comment for field 52 :
		strValue[53] = branchCode; // Comment for field 52 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[65] = amount; // Comment for field 65 :
		strValue[68] = fdAccount; // Comment for field 68 :
		strValue[71] = account; // Comment for field 71 :
		strValue[80] = totalFee; // Comment for field 80 :
		strValue[82] = amount; // Comment for field 82 :
		strValue[83] = serial; // Comment for field 83 :
		strValue[87] = buy_rate; // Comment for field 87 :
		strValue[88] = buy_rate; // Comment for field 88 :
		strValue[89] = receive_sell_rate; // Comment for field 89 :
		strValue[90] = receive_sell_rate; // Comment for field 90 :
		strValue[93] = "VND"; // Comment for field 93 :
		strValue[106] = receipt_ccy; // Comment for field 106 :
		strValue[107] = receipt_ccy; // Comment for field 107 :
		strValue[108] = rollin_acc_ccy; // Comment for field 108 :
		strValue[109] = rollin_acc_ccy; // Comment for field 109 :

		strValue[235] = account; // Comment for field 235 :
		strValue[237] = totalFee; // Comment for field 237 :
		strValue[240] = fee; // Comment for field 240 :
		strValue[241] = vatFee; // Comment for field 241 :
		strValue[249] = amount; // Comment for field 249 :
		strValue[280] = remark; // Comment for field 280 :
		return strValue;
	}

	public static String[] createTransferFromCaToFdMessage(
			String strSystemName, String teller, String fdAccount,
			String transDate, String strJournalSeq, String strTransCode, String branchCode,String manager, String amount, String account, String fee,
			String vatFee, String buy_rate,
			String sell_rate, String receipt_ccy,
			String rollin_acc_ccy, String remark ) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = strTransCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = strTransCode; // Comment for field 47 : 1360
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = strHostName; // Comment for field 51 :
		strValue[52] = manager; // Comment for field 52 :
		strValue[53] = branchCode; // Comment for field 53 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[65] = amount; // Comment for field 65 :
		strValue[68] = fdAccount; // Comment for field 68 :		
		strValue[71] = account; // Comment for field 71 :
		strValue[79] = "000";
		strValue[80] = "000";
		strValue[81] = "000";
		strValue[82] = amount;
		strValue[87] = buy_rate;
		strValue[88] = sell_rate;
		strValue[89] = buy_rate;
		strValue[90] = buy_rate;
		strValue[93] = "VND";
		strValue[105] = receipt_ccy;
		strValue[106] = receipt_ccy;
		strValue[107] = rollin_acc_ccy;
		strValue[108] = rollin_acc_ccy;
		strValue[112] = "3";
		strValue[126] = "1";
		strValue[127] = "4";
		strValue[128] = "4";
		strValue[129] = "2";
		strValue[142] = "4";
		strValue[143] = "4";
		strValue[144] = "4";
		strValue[147] = "5";
		strValue[150] = "1";
		strValue[238] = "000";
		strValue[240] = fee;
		strValue[241] = vatFee;
		strValue[242] = "000";
		strValue[249] = amount;
		strValue[253] = "5";
		strValue[255] = "4";
		strValue[256] = "4";
		strValue[257] = "4";
		strValue[264] = "2";
		strValue[280] = remark;
		strValue[285] = "000";
		strValue[286] = "000";
		strValue[295] = "1";
		strValue[296] = "1";
		
		return strValue;
	}
	
	public static String[] createTransferFromCaToFdMConfirmMessage(
			String strSystemName, String teller, String fdAccount,
			String transDate, String strJournalSeq, String strTransCode, String branchCode,String manager, String amount, String account, String fee,
			String vatFee, String buy_rate,
			String sell_rate, String receipt_ccy,
			String rollin_acc_ccy, String remark ) {
		String strValue[] = new String[DSPPackager.PACKAGER_ABCS_REQUEST
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = strSystemName; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = strSystemName; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = strHostName; // Comment for field 25 :
		strValue[26] = strTransCode; // Comment for field 26 :
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		strValue[42] = strJournalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = strTransCode; // Comment for field 47 : 1360
		strValue[48] = transDate; // Comment for field 48 : Date Of Tranfer
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = strHostName; // Comment for field 51 :
		strValue[52] = manager; // Comment for field 52 :
		strValue[53] = branchCode; // Comment for field 53 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "Y"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[65] = amount; // Comment for field 65 :
		strValue[68] = fdAccount; // Comment for field 68 :		
		strValue[71] = account; // Comment for field 71 :
		strValue[79] = "000";
		strValue[80] = "000";
		strValue[81] = "000";
		strValue[82] = amount;
		strValue[87] = buy_rate;
		strValue[88] = sell_rate;
		strValue[89] = buy_rate;
		strValue[90] = buy_rate;
		strValue[93] = "VND";
		strValue[105] = receipt_ccy;
		strValue[106] = receipt_ccy;
		strValue[107] = rollin_acc_ccy;
		strValue[108] = rollin_acc_ccy;
		strValue[112] = "3";
		strValue[126] = "1";
		strValue[127] = "4";
		strValue[128] = "4";
		strValue[129] = "2";
		strValue[142] = "4";
		strValue[143] = "4";
		strValue[144] = "4";
		strValue[147] = "5";
		strValue[150] = "1";
		strValue[238] = "000";
		strValue[240] = fee;
		strValue[241] = vatFee;
		strValue[242] = "000";
		strValue[249] = amount;
		strValue[253] = "5";
		strValue[255] = "4";
		strValue[256] = "4";
		strValue[257] = "4";
		strValue[264] = "2";
		strValue[280] = remark;
		strValue[285] = "000";
		strValue[286] = "000";
		strValue[295] = "1";
		strValue[296] = "1";
		
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
		strValue[302] = "RM        C                            OL2                                                                                                                                                                             SMSBK       VND                                                  "				+ StringUtil.rpad(fromName, 41, ' ')
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
}
