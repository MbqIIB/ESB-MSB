package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.msb.as400.dsp.DSPPackager;

public class LoanPayoff_ABCS_4088  extends IMessage {
	public String journalSeq="";
	public String transDate="";
	public String loanAcc="";
	
	public LoanPayoff_ABCS_4088() {
		super(DSPPackager.PACKAGER_ABCS_REQUEST);
	}

	@Override
	public String[] toArray() {

	String strHostName = null;
		
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		//192.168.133.1| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLNOMONFNC| | | | | | |DD110015|PC|4088| | | |3843|*MOSA| |192.168.133.1| | | | |T9999| ||DD110015|0001|N||||4088|070514| |VD|PC| |110|0|1|N|N|N|N|N|N|N|11082010040089||||070514||||||||||||||||||||||||||VND||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		// Trancode: 4088

		String firstTime = "Y";

		String shortIP = "AP";

		strValue[0] = "*LINX"; // Comment for field 0 :
		strValue[1] = strHostName; // Comment for field 1 :
		strValue[5] = "213"; // Comment for field 5 :
		strValue[6] = "42"; // Comment for field 6 :
		strValue[7] = "0200"; // Comment for field 7 :
		strValue[9] = "ABCS"; // Comment for field 9 :
		strValue[10] = "*LINX"; // Comment for field 10 :
		strValue[17] = "BBHTLNOMONFNC"; // Comment for field 17 :
		strValue[24] = teller; // Comment for field 24 :
		strValue[25] = shortIP; // Comment for field 25 : ap
		strValue[26] = "4088"; // Comment for field 26 : 2260
		strValue[30] = "3843"; // Comment for field 30 :
		strValue[31] = "*MOSA"; // Comment for field 31 :
		strValue[33] = strHostName; // Comment for field 33 :
		strValue[38] = "T9999"; // Comment for field 38 :
		strValue[41] = teller; // Comment for field 41 :
		
		strValue[42] = journalSeq; // Comment for field 42 :JOURNAL
		strValue[43] = "N"; // Comment for field 43 : Correction
		strValue[47] = "4088"; // Comment for field 47 :
		
		strValue[48] = transDate; // Comment for field 48 : Date Of
											// Tranfer
		strValue[50] = "VD"; // Comment for field 50 :
		strValue[51] = shortIP; // Comment for field 51 :
		strValue[53] = branch; // Comment for field 53 :
		strValue[54] = "0";
		strValue[55] = "1";
		strValue[56] = "N";
		strValue[57] = "N";
		strValue[58] = "N";
		strValue[59] = "N";
		strValue[61] = "N";
		strValue[60] = "N"; // Comment for field 61 :
		strValue[62] = "N"; // Comment for field 62 :
		
		strValue[63] = loanAcc; // Comment for field 63 : SA Account
												// Amount
		strValue[67] = transDate;
		//
		strValue[93] = "VND"; // Comment for field 93 :
		
	
		return strValue;
	}

}
