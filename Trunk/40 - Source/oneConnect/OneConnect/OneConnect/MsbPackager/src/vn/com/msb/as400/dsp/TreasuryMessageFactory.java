package vn.com.msb.as400.dsp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TreasuryMessageFactory {
	public TreasuryMessageFactory() {
		super();
	}

	// 1351
	public static String[] debitAdvice(String teller, String branchCode,
			String transDate, String sequence, String manager,
			String accountNumber, String crAmount, String effectiveDate,
			String glAccount, String drAmount, String buyRate, String sellRate,
			String debitCurrency, String glCurrency, String remark,
			String transcode) {

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
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = transcode; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = sequence; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = transcode; // Transaction Code
		strValue[48] = transDate;
		strValue[50] = "V1"; // Control unit
		strValue[51] = strHostName; // Work station
		strValue[52] = manager; // Work station
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
		strValue[63] = accountNumber;
		strValue[66] = crAmount;// format xxxx.xx
		strValue[67] = effectiveDate;
		strValue[71] = glAccount;
		strValue[82] = drAmount;// format xxxx.xx
		strValue[87] = buyRate;// xxxxxx.0000000
		strValue[88] = sellRate;// xxxxxx.0000000
		strValue[93] = "VND";
		strValue[105] = debitCurrency;
		strValue[106] = glCurrency;
		strValue[113] = "3";
		strValue[129] = "2";
		strValue[140] = "3";
		strValue[249] = drAmount;// format xxxx.xx
		strValue[264] = "2";
		strValue[280] = remark;

		return strValue;
	}

	// 1350
	public static String[] creditAdvice(String teller, String branchCode,
			String transDate, String sequence, String manager,
			String accountNumber, String crAmount, String effectiveDate,
			String glAccount, String drAmount, String buyRate, String sellRate,
			String creditCurrency, String glCurrency, String remark,
			String transcode, String override) {

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
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = transcode; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = sequence; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = transcode; // Transaction Code
		strValue[48] = transDate;
		strValue[50] = "V1"; // Control unit
		strValue[51] = strHostName; // Work station
		strValue[52] = manager; // Work station
		strValue[53] = branchCode; // TELLER BRANCH
		strValue[54] = "0"; // REBID REQUEST NO
		strValue[55] = "1"; // AM/PM MODE

		strValue[56] = "N"; // FORWARDED ITEM
		strValue[57] = "N"; // TRAINING MODE
		strValue[58] = "N"; // Re-bid Request
		strValue[59] = "N"; // Teller Override
		strValue[60] = override; // Supervsr Override
		strValue[61] = "N"; // Frwd Edit Override
		strValue[62] = "N"; // Release A/c Request
		strValue[63] = accountNumber;
		strValue[66] = drAmount;// format xxxx.xx
		strValue[67] = effectiveDate;
		strValue[71] = glAccount;
		strValue[82] = crAmount;// format xxxx.xx
		strValue[87] = sellRate;// xxxxxx.0000000
		strValue[88] = buyRate;// xxxxxx.0000000
		strValue[93] = "VND";
		strValue[105] = creditCurrency;
		strValue[106] = glCurrency;
		strValue[113] = "3";
		strValue[129] = "2";
		strValue[140] = "3";
		strValue[249] = crAmount;// format xxxx.xx
		strValue[264] = "2";
		strValue[280] = remark;

		return strValue;
	}

	// 70000
	public static String[] createGLTransactionMessage(String teller,
			String hostName, String branchCode, String currency, String hostDate) {
		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_77113I
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
		strValue[17] = "BBMBSGLMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "77113"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // User ID
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "77113"; // Transaction Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = branchCode; // Branch Code
		strValue[69] = currency; // Currency Code
		strValue[74] = hostDate; // Host date
		return strValue;
	}

	// 70000
	public static String[] GLTransaction(String teller, String branchCode,
			String batchNo, String debit_or_credit, String amount,
			String glAccount, String tran_ref, String comments,
			String reconcileGL, String transactionOffice,
			String customerTypeCode, String account, String businessDeptCode, String branchCode5) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_77150I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "10.1.17.45";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; //
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSGLMNTFNC"; // Scenario Number
		strValue[22] = "1"; // User ID
		strValue[23] = "10"; // User ID
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "77150"; // Supervisor ID
		strValue[27] = "N"; //
		strValue[31] = teller; // TELLER ID
		strValue[32] = "1"; //
		strValue[34] = "*END"; //
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "77150"; // Trans Code
		strValue[46] = "A"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = branchCode;
		strValue[68] = batchNo;
		strValue[71] = glAccount;
		strValue[72] = "00000";
		strValue[73] = "00000";
		strValue[74] = debit_or_credit;
		strValue[75] = amount;
		strValue[76] = comments;
		strValue[77] = tran_ref;

		// add 5 fields cause's upgrade core bank
		strValue[87] = reconcileGL;
		strValue[88] = transactionOffice;
		strValue[89] = customerTypeCode;
		strValue[90] = account;
		strValue[91] = businessDeptCode;

		strValue[93] = "N";

		strValue[94] = branchCode5; // branch to dr/cr 5digits

		return strValue;
	}

	// 70000
	public static String[] ApproveGLTransaction(String manager,
			String branchCode, String batchNo) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_78155I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "10.1.17.45";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; //
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSGLMNTFNC"; // Scenario Number
		strValue[22] = "1"; // User ID
		strValue[23] = "10"; // User ID
		strValue[24] = manager; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "78155"; // Supervisor ID
		strValue[27] = "N"; //
		strValue[31] = manager; // TELLER ID
		strValue[32] = "1"; //
		strValue[34] = "*END"; //
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "78155"; // Trans Code
		strValue[46] = "C"; // Insert
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = branchCode;
		strValue[68] = batchNo;
		strValue[69] = "A";

		return strValue;
	}

	// 8300
	public static String[] chargeCollection(String teller, String branchCode,
			String transDate, String sequence, String glAccount, String totalNetCharges, String baseCurrency,
			String glCurrency, String customerCurrency, String accountNumber, String drTotalNetCharge,String totalCharge,
			String vat, String remark, String transcode) {

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
		strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = transcode; // Supervisor ID
		strValue[30] = "3843"; // Message Length
		strValue[31] = "*MOSA"; // Message Header
		strValue[33] = strHostName; // Device
		strValue[38] = "T9999"; // Transaction type
		strValue[41] = teller; // TELLER ID
		strValue[42] = sequence; // Journal Seq
		strValue[43] = "N"; // Correction
		strValue[47] = transcode; // Transaction Code
		strValue[48] = transDate;
		strValue[50] = "V1"; // Control unit
		strValue[51] = strHostName; // Work station
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
		strValue[71] = glAccount;
		strValue[79] = "0";
		strValue[80] = totalNetCharges;
		strValue[81] = "0";
		strValue[82] = totalNetCharges;		
		strValue[93] = baseCurrency;
		strValue[105] = glCurrency;
		strValue[106] = customerCurrency;
		strValue[118] = "1";
		strValue[126] = "1";
		strValue[127] = "2";
		strValue[128] = "2";
		strValue[129] = "2";
		strValue[140] = "2";
		strValue[141] = "2";
		strValue[235] = accountNumber;
		strValue[237] = drTotalNetCharge;
		strValue[240] = totalCharge;
		strValue[241] = vat;
		strValue[252] = "3";
		strValue[255] = "2";
		strValue[256] = "2";
		strValue[280] = remark;
		strValue[285] = "0";
		strValue[286] = "0";
		strValue[295] = "1";
		strValue[296] = "1";
		return strValue;
	}
	
	// 8300 revertChargeCollection
		public static String[] revertChargeCollection(String teller, String branchCode,
				String transDate, String sequence, String glAccount, String totalNetCharges, String baseCurrency,
				String glCurrency, String customerCurrency, String accountNumber, String drTotalNetCharge,String totalCharge,
				String vat, String remark, String transcode, String newSequence, String manager ) {

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
			strValue[17] = "BBHTLNOMONFNC"; // Scenario Number
			strValue[24] = teller; // User ID
			strValue[25] = strHostName; // Terminal ID
			strValue[26] = transcode; // Supervisor ID
			strValue[30] = "3843"; // Message Length
			strValue[31] = "*MOSA"; // Message Header
			strValue[33] = strHostName; // Device
			strValue[38] = "T9999"; // Transaction type
			strValue[41] = teller; // TELLER ID
			strValue[42] = newSequence; // Journal Seq
			strValue[43] = "Y"; // Correction
			strValue[44] = sequence; // Correction
			strValue[47] = transcode; // Transaction Code
			strValue[48] = transDate;
			strValue[50] = "V1"; // Control unit
			strValue[51] = strHostName; // Work station
			strValue[52] = manager; // 
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
			strValue[71] = glAccount;
			strValue[79] = "0";
			strValue[80] = totalNetCharges;
			strValue[81] = "0";
			strValue[82] = totalNetCharges;		
			strValue[93] = baseCurrency;
			strValue[105] = glCurrency;
			strValue[106] = customerCurrency;
			strValue[118] = "1";
			strValue[126] = "1";
			strValue[127] = "2";
			strValue[128] = "2";
			strValue[129] = "2";
			strValue[140] = "2";
			strValue[141] = "2";
			strValue[235] = accountNumber;
			strValue[237] = drTotalNetCharge;
			strValue[240] = totalCharge;
			strValue[241] = vat;
			strValue[252] = "3";
			strValue[255] = "2";
			strValue[256] = "2";
			strValue[280] = remark;
			strValue[285] = "0";
			strValue[286] = "0";
			strValue[295] = "1";
			strValue[296] = "1";
			return strValue;
		}
}
