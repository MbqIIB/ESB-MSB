package vn.com.m1tech.AS400.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DefaultMsg {
	
	
	public static String[]  buidABCSMsg(int length){
		String strValue[] = new String[length];
		
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		
		// transcode: 3320
		strValue[0] = "*LINX"; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "0200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = "*LINX"; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		
		strValue[25] = "PC"; // Comment for field 25 :
		
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[44] = ""; // So sequence cua giao dich can dao
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = "PC"; // Comment for field 51 :
		strValue[54] = "0"; // Comment for field 54 :
		strValue[55] = "1"; // Comment for field 55 :
		strValue[56] = "N"; // Comment for field 56 :
		strValue[57] = "N"; // Comment for field 57 :
		strValue[58] = "N"; // Comment for field 58 :
		strValue[59] = "N"; // Comment for field 59 :
		strValue[60] = "N"; // Comment for field 60 :
		strValue[61] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[63] = ""; // Comment for field 63 :
		
		strValue[72] = ""; // Comment for field 72 :
		strValue[75] = ""; // Comment for field 75 :
		strValue[79] = "000"; // Comment for field 79 :
		strValue[80] = "000"; // Comment for field 80 :
		strValue[81] = "000"; // Comment for field 81 :
		
		// tranfer 1000 VND
		strValue[87] = "10000000"; // Comment for field 87 :
		strValue[88] = "10000000"; // Comment for field 88 :
		strValue[89] = "00000000"; // Comment for field 88 :
		strValue[90] = "10000000"; // Comment for field 88 :
		
		strValue[107] = ""; // Comment for field 107 :
		
		// Comment for field 140 :
		strValue[142] = "4"; // Comment for field 141:
		strValue[143] = "4"; // Comment for field 142 :
		strValue[147] = "5"; // Comment for field 143 :
		strValue[150] = "1"; // Comment for field 149 :
		strValue[238] = "000"; // Comment for field 235 :
		strValue[240] = "000"; // Comment for field 240 : Send 100000 as charge
		// 1000 VND
		strValue[241] = "000"; // Comment for field 241 :
		strValue[242] = ""; // Comment for field 237 :
		strValue[248] = ""; // Comment for field 248 :
		// transfer
		strValue[285] = "000"; // Comment for field 285 :
		strValue[286] = "000"; // Comment for field 286 :
		strValue[287] = ""; // Comment for field 284 : Send 100000 as
		// tranfer 1000 VND
		// tranfer 1000 VND
		strValue[295] = "1"; // Comment for field 295 :
		strValue[296] = "1"; // Comment for field 296 :
		strValue[297] = ""; // Comment for field 297 :
		strValue[298] = ""; // Comment for field 298 :
		
		return strValue;
	}

}
