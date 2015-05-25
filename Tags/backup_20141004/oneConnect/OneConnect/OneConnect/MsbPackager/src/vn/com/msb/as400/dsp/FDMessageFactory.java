package vn.com.msb.as400.dsp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FDMessageFactory {
	public FDMessageFactory() {
		super();
	}

	// 38902
	public static String[] rateScheduleMainternance(String teller,
			String branchCode, String fdReceipt, String rateChangeDate,
			String newRate, String variantBaseRate) {

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_38902I
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
		strValue[8] = "*DSP"; //
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "1"; //
		strValue[17] = "BBMBSCDMNTFNC"; // Scenario Number
		strValue[22] = "1"; // 
		strValue[23] = "10"; // 
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "38902"; // Supervisor ID
		strValue[27] = "N"; // Supervisor ID
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Device
		strValue[34] = "*END"; //
		strValue[36] = "*BTS";
		strValue[37] = "*RBS";
		strValue[39] = strHostName;
		strValue[40] = "27";
		strValue[41] = branchCode;
		strValue[45] = "38902";
		strValue[46] = "A";
		strValue[47] = "R";
		strValue[48] = "1";
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = fdReceipt;
		strValue[69] = rateChangeDate;
		strValue[70] = newRate;
		strValue[72] = variantBaseRate;
		
		return strValue;
	}
	
	
	

}
