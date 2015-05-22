package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.WithDrawFD;
import vn.com.msb.as400.dsp.DSPPackager;

public class FD_WithDrawGL_ABCS3360 extends IMessage {

	public FD_WithDrawGL_ABCS3360() {
		super(DSPPackager.PACKAGER_ABCS_REQUEST);
		// TODO Auto-generated constructor stub
	}
	
	public WithDrawFD fd;

	@Override
	public String[] toArray() {
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		// Trancode: 3360
		strValue[0] = "*LINX";
		strValue[1] = strHostName;
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = fd.getTeller(); // 24 :
		strValue[25] = "PC"; // 25 :
		strValue[26] = fd.getTransCode(); // 26 :
		strValue[30] = "3843"; // 30 :
		strValue[31] = "*MOSA"; // 31 :
		strValue[33] = strHostName; // 33 :
		strValue[38] = "T9999"; // 38 :
		strValue[41] = fd.getTeller(); // 41 :
		strValue[42] = fd.getJonalSequece(); // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[44] = ""; // So sequence cua giao dich can dao
		strValue[47] = fd.getTransCode(); // 47 :
		strValue[48] = fd.getTransDate(); // 48 : Date Of											// Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = "PC"; // 51 :
		strValue[52] = fd.getSupervisor(); // 52 :
		strValue[53] = fd.getBranch(); // 53 :
		strValue[54] = "0"; // 54 :
		strValue[55] = "1"; // 55 :
		strValue[56] = "N"; // 56 :
		strValue[57] = "N"; // 57 :
		strValue[58] = "N"; // 58 :
		strValue[59] = "N"; // 59 :
		strValue[60] = "N"; // 60 :
		strValue[61] = "N"; // 61 :
		strValue[62] = "N"; // 62 :
		strValue[63] = ""; // 63 :
		strValue[66] = fd.getCreditAmount(); // 65 : Send												// 100000 as
		strValue[68] = fd.getFdAcc();
		strValue[71] = fd.getGlAcc(); // 72 :
		strValue[79] = "000"; // 79 :
		strValue[80] = "000"; // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = fd.getDebitAmount(); // 82 : Send
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[89] = "10000000"; // 88 :
		strValue[90] = "10000000"; // 88 :
		strValue[93] = "VND"; // 93 :
		strValue[105] = "VND"; // 105 :
		strValue[106] = "VND"; // 106 :
		strValue[107] = ""; // 107 :
		strValue[108] = "VND"; // 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "4"; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		strValue[140] = "3"; // 140 :
		strValue[141] = "4"; // 141:
		strValue[142] = "4"; // 142 :
		strValue[146] = "5"; // 143 :
		strValue[149] = "1"; // 149 :
		strValue[238] = "000"; // 235 :
		strValue[240] = "000";		// 1000 VND
		strValue[241] = "000"; // 241 :
		strValue[249] = fd.getDebitAmount();
		strValue[253] = "5"; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[264] = "2"; // 264 :
		strValue[280] = fd.getDescription(); //
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

}
