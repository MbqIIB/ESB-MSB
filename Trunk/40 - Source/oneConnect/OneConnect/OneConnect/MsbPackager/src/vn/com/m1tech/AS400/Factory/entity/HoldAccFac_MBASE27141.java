package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.HoldAcc;
import vn.com.msb.as400.dsp.DSPPackager;

public class HoldAccFac_MBASE27141 extends IMessage  {
	public HoldAcc holdAcc;

	public HoldAccFac_MBASE27141() {
		super(DSPPackager.PACKAGER_MBASE_27141I);
	}

	@Override
	public String[] toArray() {
		String accType = "";
		accType=holdAcc.getAccType();
		String branch = "20";
		//*LINX|10.2.142.26||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDMNTFNC|||||1|10|DD110015|quanld-PC|27141|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||27141|A|R|1|N|F|||||||||||||||||02010100051316|T||HD||||89898900||000|231214|SFXDVXFXCX|||*EA||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		if ("".equals(holdAcc.getAccType())) {
			accType = "D";
		}
		if ("".equals(holdAcc.getBranch())) {
			branch = "20";
		}
	
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

}
