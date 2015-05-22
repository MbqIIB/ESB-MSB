package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.msb.as400.dsp.DSPPackager;

public class FDAccountInquiry_MB36501 extends IMessage {

	public String fdAccount;
	public String fdAcountType;
	
	public FDAccountInquiry_MB36501() {
		super(DSPPackager.PACKAGER_MBASE_36501I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {

		// *LINX|10.2.142.164||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSCDINQFNC|||||1|10|DD110015|QuanLD-PC|36501|N||||DD110015|1||*END||BTS|RBS||QuanLD-PC|27|110||||36501|I|R|1|N|F|||||||||||||11020100111293|T|||11020100111293|T||||||||||||||||||||||||||||||||||||||||
		// TODO Auto-generated method stub

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_36501I
				.getFieldDefinitionList().length];
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "localhost";
		}
		strValue[0] = "*LINX"; // Header type
		strValue[1] = strHostName; // Device Name
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSCDINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "36501"; 
		strValue[27] = "N";
		strValue[31] = teller; 
		strValue[32] = "1"; 
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName;
		strValue[40] = "27"; 
		strValue[41] = branch;
		strValue[45] = "36501"; 
		strValue[46] = "I";
		strValue[47] = "R"; 
		strValue[48] = "1"; 
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "";
		strValue[63] = fdAccount; // application Number
		strValue[64] = fdAcountType;
		strValue[67] = fdAccount; // application Number
		strValue[68] = fdAcountType;
		return strValue;

	}

}
