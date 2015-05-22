package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.UnHoldAcc;
import vn.com.msb.as400.dsp.DSPPackager;

public class UnholdAcc_MB28141 extends IMessage{

	public UnHoldAcc unHold;
	public UnholdAcc_MB28141() {
		super(DSPPackager.PACKAGER_MBASE_28141I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
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

}
