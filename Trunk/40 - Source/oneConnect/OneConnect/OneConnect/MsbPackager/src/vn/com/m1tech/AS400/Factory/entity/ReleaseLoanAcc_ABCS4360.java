package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.msb.as400.dsp.DSPPackager;

public class ReleaseLoanAcc_ABCS4360  extends IMessage {


	public ReleaseLoanAcc_ABCS4360() {
		super(DSPPackager.PACKAGER_ABCS_REQUEST);
	}

	public ReleaseLnAcc relewaseAcc= new ReleaseLnAcc();
	
	@Override
	public String[] toArray() {
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		//*LINX|192.168.56.1| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLMONEYFNC| | | | | | |DD110015|PC|4360| | | |3843|*MOSA| |192.168.56.1| | | | |T9999| ||DD110015|0005              |N||||4360|220414| |VD|PC|MG110099|110|0|1|N|N|N|N|N|N|N|11082010039319|||347054|220414||||     280701003||||||||000|000|000|10000000000|||||10000000|288140000000|10000000|10000000|||VND||||||||||||VND|EUR|VND|VND|VND||||3|||||||||||||1|4|4|2|||||||||||3|4|5||||6||||1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||000||000|000||||||||10000000000||||6||4|5||||||||2||||||||||||||||Giai Ngan Ung von|||   |   |000|000|||||||||1|1|||||||
		//*LINX|10.0.2.214||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||DD110015|PC|4360||||3843|*MOSA||10.0.2.214||||                            |T9999|| |DD110015|219424250414180530|N||||4360|250414| |VD|PC|DD110015|110|0|1|N|N|N|N|N|N|N|11082010039560|||      |250414||||11001018902166||||||||000|000|000|108000000  |||||10000000|10000000    |10000000|10000000|||VND||||||||||||VND|VND|VND|VND|   ||||3|||||||||||||1|4|4|2|||||||||||3|4|5||||6||||1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||000||000|000||||||||108000000||||6||4|5||||||||2|||||||||||||||||||||000|000|||||||||1|1|||||||
		//*LINX|192.168.56.1| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLMONEYFNC| | | | | | |DD110015|PC|4320| | | |3843|*MOSA| |192.168.56.1| | | | |T9999| ||DD110015|0003|N||||4320|290414| |VD|PC|MG110099|110|0|1|N|N|N|N|N|N|N|11082010039560||10000000||290414||||11001010000105||||||||000|000|000|10000000|||||10000000|10000000|10000000|10000000|||VND||||||||||||VND|VND|VND|VND|VND|||3||||||||||||||1|4|4|2|||||||||||3|4|5||||6||||1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||000||000|000||||||||10000000||||6||4|5||||||||2||||||||||||||||Giai ngan phat nao|||||000|000|||||||||1|1|||||||
		// Trancode: 3360
		 
		
		strValue[0] = "*LINX";
		strValue[1] = strHostName;
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = relewaseAcc.getTeller(); // 24 :
		strValue[25] = "PC"; // 25 :
		strValue[26] = "4320"; // :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = relewaseAcc.getTeller(); // 41 :
		strValue[42] = relewaseAcc.getJonalSequece(); // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[44] = ""; // So sequence cua giao dich can dao
		strValue[47] = "4320"; // 47 :
		strValue[48] = relewaseAcc.getTransDate(); // 48 : Date Of											// Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = "PC"; // 51 :
		strValue[52] = relewaseAcc.getSupervisor(); // 52 :
		strValue[53] = relewaseAcc.getBranch(); // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = relewaseAcc.getLoanAcc(); // 63 :
		strValue[65] =  relewaseAcc.getReleaseAmount();//"10000000"; // 65 : Send	xem lai											// 100000 as
		strValue[67] = relewaseAcc.getTransDate();
		strValue[71] = relewaseAcc.getCaAcc(); // 72 :
		strValue[79] = "000"; // 79 :
		strValue[80] = "000"; // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = relewaseAcc.getReleaseAmount(); // 82 : Send
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88  xem lai
		strValue[89] = "10000000"; // 88 :
		strValue[90] = "10000000"; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = "VND"; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[108] = "VND"; // 109 :
		strValue[112] = "3";// Amt field curr usage 04
		strValue[119] = "";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "4"; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[140] = "3"; // 140 :
		strValue[141] = "4"; // 141:
		strValue[142] = "5"; // 142 :
		strValue[146] = "6"; // 143 :
		strValue[150] = "1"; // 149 :
		strValue[238] = "000"; // 235 :
		strValue[240] = "000";		// 1000 VND
		strValue[241] = "000"; // 241 :
		strValue[249] = relewaseAcc.getReleaseAmount();
		strValue[253] = "6"; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "5"; // 256 :
		strValue[264] = "2"; // 264 :
		strValue[280] = relewaseAcc.getDescription(); //
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

}
