package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.Collateral;
import vn.com.msb.as400.dsp.DSPPackager;

public class DeleteCollateral_MB88222 extends IMessage {

	public String collId;
	public DeleteCollateral_MB88222() {
		super(DSPPackager.PACKAGER_MBASE_88222I);
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
		
		//*LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88222|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||88222|C|R|1|N|F|||||||||||||||||35846|1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
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
		strValue[24] = teller; // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "88222"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = teller; // 31 : User ID
		strValue[32] = "1";
		strValue[33] = "";
		strValue[34] = "*END";
		strValue[36] = "BTS"; // 36 : Source ID
		strValue[37] = "RBS"; // 37 : Destination ID
		strValue[38] = ""; // 38 : Return Data Queue Name
		strValue[39] = strHostName; // 39 : Terminal ID
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = branch; // 41 : Branch
		strValue[45] = "88222"; // Transaction Code
		strValue[46] = "C"; // "D","C" Action Code
		strValue[47] = "R"; // Transaction Mode
		strValue[48] = "1"; // No. Of Records To Retrieve
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = collId; //
		strValue[68] = "1"; // C

		return strValue;
	}

}
