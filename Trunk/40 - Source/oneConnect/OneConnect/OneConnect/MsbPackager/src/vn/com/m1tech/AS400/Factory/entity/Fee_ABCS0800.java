package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.Fee;
import vn.com.msb.as400.dsp.DSPPackager;

public class Fee_ABCS0800 extends IMessage {
	public Fee fee;
	public Fee_ABCS0800() {
		super(DSPPackager.PACKAGER_ABCS_REQUEST);
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
		// transcode: 0800
		strValue[0] = "*LINX"; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "0200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = "*LINX"; // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = fee.getTeller(); // 24 :
		strValue[26] = fee.getTransCode(); // 26 :
		strValue[30] = "3843"; // 24 :
		strValue[31] = "*MOSA"; // 24 :
		strValue[33] = strHostName; // 24 :
		strValue[38] = "T9999"; // 24 :
		
		strValue[41] = fee.getTeller(); // 41 :
		strValue[42] = fee.getJonalSequece(); // 42 :JOURNAL
		strValue[43] = "N"; // 43 : Correction
		strValue[44] = ""; // So sequence cua giao dich can dao
		strValue[47] = fee.getTransCode(); // 47 :
		strValue[48] = fee.getTransDate(); // 48 : Date Of
											// Tranfer
		strValue[50] = "VD"; // 50 :
		strValue[51] = "PC"; // 51 :
		strValue[53] = fee.getBranch(); // 53 :
		// tranfer 1000 VND
		strValue[71] = fee.getCreditAcc(); // 72 :
		strValue[79] = "000"; // 79 :
		strValue[80] = fee.getTotalAmount(); // 80 :
		strValue[81] = "000"; // 81 :
		strValue[82] = fee.getTotalAmount(); // 82 : Send
												// 100000 as
		strValue[93] = fee.getCurrency(); // 93 :
		strValue[105] = fee.getCurrency(); // 105 :
		strValue[106] = fee.getCurrency(); // 106 :
		strValue[107] = ""; // 107 :
		strValue[118] = "1";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "2"; // 127 :
		strValue[128] = "2"; // 128 :
		strValue[129] = "2"; // 129 :
		// 140 :
		strValue[140] = "2"; // 141:
		strValue[141] = "2"; // 142 :
		strValue[235] = fee.getDebitAcc();
		strValue[237] = fee.getTotalAmount(); // 235 :
		strValue[240] = fee.getAmount();
		strValue[241] = fee.getVatAmount();

		strValue[252] = "3"; // 252 :
		strValue[255] = "2"; // 255 :
		strValue[256] = "2"; // 256 :
		strValue[257] = ""; // 257 :
		strValue[264] = ""; // 264 :
		strValue[280] = fee.getDescription();
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		strValue[297] = ""; // 297 :
		return strValue;
	}

}
