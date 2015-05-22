package vn.com.m1tech.AS400.Factory.entity;

import vn.com.msb.as400.dsp.DSPPackager;
/**
 * 
 * @author anhnt6
 * LN Facility Maintenance - Pre Add 
 */
public class PreAddFac_82303 extends IMessage {
	public String facilityCode = "";
	public String productType = "";
	public String currencyType = "";
	public String applicationNo = "";
	public String cif;	
	
	public PreAddFac_82303(){
		super(DSPPackager.PACKAGER_MBASE_82303I);
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
		strValue[26] = "82303"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostname; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branch; // Branch Code
		strValue[45] = "82303"; // Transaction Code
		strValue[46] = "A"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = applicationNo; // application Number
		strValue[68] = cif;
		strValue[69] = facilityCode;
		strValue[74] = productType;
		strValue[75] = currencyType;
		strValue[76] = "27";
		strValue[77] = branch;
		return strValue;
	}
}
