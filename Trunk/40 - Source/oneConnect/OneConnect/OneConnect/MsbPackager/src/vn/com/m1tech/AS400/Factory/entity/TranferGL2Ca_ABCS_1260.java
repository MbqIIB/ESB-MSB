package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.TransferAcc;
import vn.com.msb.as400.dsp.DSPPackager;

public class TranferGL2Ca_ABCS_1260 extends IMessage {

	public TransferAcc acc = new TransferAcc() ;
	public TranferGL2Ca_ABCS_1260() {
		super(DSPPackager.PACKAGER_ABCS_REQUEST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
		
		String firstTime="Y";
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}

		String shortIP = "AP";

		strValue[0] = acc.getSysName(); // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "0200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = acc.getSysName(); // Comment for field 10 :
		strValue[17] = "BBHTLMONEYFNC"; // Comment for field 17 :
		strValue[24] = acc.getTeller(); // Comment for field 24 :
		strValue[25] = shortIP; // Comment for field 25 : ap
		strValue[26] = acc.getTransCode(); // Comment for field 26 : 2260
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = acc.getTeller(); // Comment for field 41 :
		strValue[42] = acc.getJournalSeq(); // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = acc.getTransCode(); // Comment for field 47 :
		strValue[48] = acc.getTransDate(); // Comment for field 48 : Date Of Tranfer
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = shortIP; // Comment for field 51 :
		strValue[52] = acc.getSupervisor(); // Comment for field 52 :
		strValue[53] = acc.getBranch(); // Comment for field 53 :
		strValue[54] = "0";
		strValue[55] = "1"; 
		strValue[56] = "N"; 
		strValue[57] = "N"; 
		strValue[58] = "N"; 
		strValue[59] = "N"; 
		strValue[61] = "N"; 
		strValue[60] = firstTime; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		strValue[63] = acc.getCreditAcc(); // Comment for field 63 : SA Account
		strValue[66] = acc.getDebitAmount(); // Comment for field 65 : Transfer Amount
		strValue[67] = acc.getEffetiveDate();
		// 
		strValue[71] = acc.getDebitAcc(); // Comment for field 71 :
		strValue[82] = acc.getCreditAmount(); // Comment for field 82 : Send phai tinh
		// 
		strValue[87] = acc.getCreditRate(); // Comment for field 87 : sa rate
		strValue[88] = acc.getDebitRate(); // Comment for field 88 :
		strValue[93] = "VND"; // Comment for field 93 :
		strValue[104] = acc.getAdviceNumber(); // Comment for field 105 :
		strValue[105] = acc.getCreditCurrency(); // Comment for field 106 :
		strValue[106] = acc.getDebitCurrency(); // Comment for field 107 :
		strValue[113] = "3"; // Comment for field 112 :
		strValue[129] = "2"; // Comment for field 126 :
		strValue[140] = "3"; // Comment for field 142 :
		strValue[249] = acc.getCreditAmount();
		strValue[264] = "2"; // Comment for field 264 :
		strValue[280] = acc.getDescription(); // Comment for field 280 : Reason of

		return strValue;
	}

}
