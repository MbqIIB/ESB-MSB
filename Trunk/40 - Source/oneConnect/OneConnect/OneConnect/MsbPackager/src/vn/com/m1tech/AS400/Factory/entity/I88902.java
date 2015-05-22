package vn.com.m1tech.AS400.Factory.entity;

import vn.com.msb.as400.dsp.DSPPackager;
/**
 * 
 * @author anhnt6
 * LN Facility Maintenance - Pre Add 
 */
public class I88902 extends IMessage {
	public String key = "DD110083  \\LNAPPF    \\00085473";
	public I88902(){
		super(DSPPackager.PACKAGER_MBASE_88902I);
	}
	public String[] toArray(){
		strValue[0] = "*LINX"; // Header type
		strValue[1] = hostname; // Device Name		
		strValue[5] = "213"; // Header Length
		strValue[7] = "0200"; // Version
		strValue[8] = "*DSP"; // Version
		strValue[9] = "MBSD"; // Data format
		strValue[10] = "*LINX"; // Source ID
		strValue[14] = "01"; // Source ID
		strValue[17] = "BBMBSLNMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = teller; // User ID
		strValue[25] = hostname; // Terminal ID
		strValue[26] = "88902"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostname; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branch; // Branch Code
		strValue[45] = "88902"; // Transaction Code
		strValue[46] = "C"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[67] = key;
		return strValue;
	}
}
