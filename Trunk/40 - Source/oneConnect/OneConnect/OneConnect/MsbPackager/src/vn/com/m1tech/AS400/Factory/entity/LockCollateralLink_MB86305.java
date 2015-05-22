package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.CollateralLink;
import vn.com.msb.as400.dsp.DSPPackager;

public class LockCollateralLink_MB86305 extends IMessage {

	public CollateralLink calla=null;
	public LockCollateralLink_MB86305() {
		super(DSPPackager.PACKAGER_MBASE_86305I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
		// TODO Auto-generated method stub
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
		strValue[17] = "BBMBSLNINQFNC"; // 17 :
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
		strValue[69] = calla.getFacId();//
		strValue[70] = calla.getCollateralId(); //

		return strValue;
	}

}
