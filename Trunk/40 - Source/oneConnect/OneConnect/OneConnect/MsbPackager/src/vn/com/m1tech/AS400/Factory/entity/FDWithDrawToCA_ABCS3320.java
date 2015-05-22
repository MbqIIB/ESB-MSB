package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.WithDrawFD;
import vn.com.msb.as400.dsp.DSPPackager;

public class FDWithDrawToCA_ABCS3320 extends IMessage {

	public WithDrawFD ca;

	public FDWithDrawToCA_ABCS3320() {
		super(DSPPackager.PACKAGER_ABCS_REQUEST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
		// transcode: 3320
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX";
		strValue[1] = strHostName;
		strValue[5] = "213"; // 5 :
		strValue[6] = "42"; // 6 :
		strValue[7] = "0200"; // 7 :
		strValue[9] = "ABCS"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[17] = "BBHTLMONEYFNC"; // 17 :
		strValue[24] = ca.getTeller(); // 24 :
		strValue[26] = ca.getTransCode(); // 26 :
		strValue[41] = ca.getTeller(); // 41 :
		strValue[42] = ca.getJonalSequece(); // 42 :JOURNAL
		strValue[47] = ca.getTransCode(); // 47 :
		strValue[48] = ca.getTransDate(); // 48 : Date Of
											// Tranfer
		strValue[52] = ca.getSupervisor(); // 52 :
		strValue[53] = ca.getBranch(); // 53 :
		strValue[65] = ca.getCreditAmount(); // 65 : Send
												// 100000 as
		strValue[68] = ca.getFdAcc();
		// tranfer 1000 VND
		strValue[71] = ca.getDdAcc(); // 72 :
		strValue[72] = ""; // 72 :
		strValue[75] = ""; // 75 :
		strValue[82] = ca.getDebitAmount(); // 82 : Send
											// 100000 as
		// tranfer 1000 VND
		strValue[87] = "10000000"; // 87 :
		strValue[88] = "10000000"; // 88 :
		strValue[89] = "00000000"; // 88 :
		strValue[90] = "10000000"; // 88 :
		strValue[93] = ca.getCurrency(); // 93 :
		strValue[105] = ca.getCurrency(); // 105 :
		strValue[106] = ca.getCurrency(); // 106 :
		strValue[108] = ca.getCurrency(); // 108 :
		strValue[113] = "3";// Amt field curr usage 04
		strValue[119] = "";// Amt field curr usage 10
		strValue[126] = "1"; // 126 :
		strValue[127] = "4"; // 127 :
		strValue[128] = "4"; // 128 :
		strValue[129] = "2"; // 129 :
		// 140 :
		strValue[142] = "4"; // 141:
		strValue[143] = "4"; // 142 :
		strValue[147] = "5"; // 143 :
		strValue[150] = "1"; // 149 :
		strValue[238] = "000"; // 235 :
		strValue[240] = "000";
		strValue[241] = "000"; // 241 :
		strValue[242] = ""; // 237 :
		strValue[248] = ""; // 248 :
		strValue[249] = ca.getDebitAmount();
		strValue[253] = "5"; // 252 :
		strValue[255] = "4"; // 255 :
		strValue[256] = "4"; // 256 :
		strValue[257] = ""; // 257 :
		strValue[264] = "2"; // 264 :
		strValue[280] = ca.getDescription();
		strValue[285] = "000"; // 285 :
		strValue[286] = "000"; // 286 :
		strValue[295] = "1"; // 295 :
		strValue[296] = "1"; // 296 :
		return strValue;
	}

}
