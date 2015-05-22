package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.msb.as400.dsp.DSPPackager;

public class LockFacilityLoan_MB86303 extends IMessage {
	public String teller="";
	public String hostName="";
	public	String branchCode="";
	public String appNo="";
	public String facilityCode="";
	public String seqNo="";
	public LockFacilityLoan_MB86303() {
		super(DSPPackager.PACKAGER_MBASE_86303I);
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
		strValue[24] = teller; // User ID
		strValue[25] = hostName; // Terminal ID
		strValue[26] = "86303"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branchCode; // Branch Code
		strValue[45] = "86303"; // Transaction Code
		strValue[46] = "I"; // Inquirey
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = appNo; // application No
		strValue[68] = facilityCode;
		strValue[69] = seqNo;
		return strValue;
	}

}
