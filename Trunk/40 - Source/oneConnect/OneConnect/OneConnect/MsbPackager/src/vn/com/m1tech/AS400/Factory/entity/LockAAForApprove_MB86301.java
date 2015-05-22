package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;


import vn.com.m1tech.AS400.entity.AAEntity;
import vn.com.msb.as400.dsp.DSPPackager;

public class LockAAForApprove_MB86301 extends IMessage {

	
	/**
	 * LockAA for approve
	 * 
	 * [679|*LINX|10.2.142.30||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNINQFNC|||||1|
	 * 10|DD110015|quanld-PC|86301|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||
	 * 86301|I|R|1|N|F||||||C|||||||||||212845000|||||||||||||||||||||||||||||||||||
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * */

	public AAEntity aa= null;
	public LockAAForApprove_MB86301( ) {
		super(DSPPackager.PACKAGER_MBASE_86301I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
		// TODO Auto-generated method stub
		
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
		strValue[17] = "BBMBSLNINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = aa.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "86301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = aa.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = aa.getBranch(); // Branch Code
		strValue[45] = "86301"; // Transaction Code
		strValue[46] = "I"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = aa.getApplicationNo(); // application Number

		return strValue;
	}

}
