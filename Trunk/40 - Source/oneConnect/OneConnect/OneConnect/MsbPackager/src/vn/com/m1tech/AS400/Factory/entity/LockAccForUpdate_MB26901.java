package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.LockAcc;
import vn.com.msb.as400.dsp.DSPPackager;

public class LockAccForUpdate_MB26901 extends IMessage {

	public LockAcc acc = null;

	public LockAccForUpdate_MB26901() {
		super(DSPPackager.PACKAGER_MBASE_26901I);
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

}
