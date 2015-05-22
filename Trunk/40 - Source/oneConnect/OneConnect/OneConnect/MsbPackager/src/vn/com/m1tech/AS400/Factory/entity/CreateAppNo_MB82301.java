package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;


import vn.com.m1tech.AS400.entity.AppNo;
import vn.com.msb.as400.dsp.DSPPackager;

public class CreateAppNo_MB82301 extends IMessage {

	public AppNo appNo;

	public CreateAppNo_MB82301() {
		super(DSPPackager.PACKAGER_MBASE_82301I);

	}

	@Override
	public String[] toArray() {
		// TODO Auto-generated method stub
		// Message:
		// 682|*LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|82301|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||82301|A|R|1|N|F||||||C|||||||||||212789|110||||||||

		String strValue[] = new String[DSPPackager.PACKAGER_MBASE_88301I
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
		strValue[17] = "BBMBSLNMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = appNo.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "82301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = appNo.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = appNo.getBranch(); // Branch Code
		strValue[45] = "82301"; // Transaction Code
		strValue[46] = "A"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = appNo.getCifNum(); // application Number
		strValue[68] = appNo.getBranch();
		
		
		return strValue;
	}

}
