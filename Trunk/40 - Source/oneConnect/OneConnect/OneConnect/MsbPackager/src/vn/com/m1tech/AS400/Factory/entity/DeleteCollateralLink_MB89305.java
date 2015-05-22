package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.CollateralLink;
import vn.com.msb.as400.dsp.DSPPackager;

public class DeleteCollateralLink_MB89305 extends IMessage {

	
	public CollateralLink calla=null;
	public DeleteCollateralLink_MB89305() {
		super(DSPPackager.PACKAGER_MBASE_89305I);
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
		//*LINX|10.2.142.164||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|QuanLD-PC|89305|N||||DD110015|1||*END||BTS|RBS||QuanLD-PC|27|110||||89305|D|R|1|N|F||||||D|||||||||||219424000|240|237|36005||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		//*LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|89305|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||89305|D|R|1|N|F||||||D|||||||||||219424000|240|26|35833||||||||||||||||||||||||||||||||
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
		strValue[69] = calla.getFacId();//
		strValue[70] = calla.getCollateralId(); //

		return strValue;
	}

}
