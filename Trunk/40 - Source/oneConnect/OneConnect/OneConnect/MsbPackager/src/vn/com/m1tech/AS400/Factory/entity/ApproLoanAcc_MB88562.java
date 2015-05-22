package vn.com.m1tech.AS400.Factory.entity;

import vn.com.msb.as400.dsp.DSPPackager;

public class ApproLoanAcc_MB88562  extends IMessage{
	public String loanAcc;
public ApproLoanAcc_MB88562() {
	
	super(DSPPackager.PACKAGER_MBASE_88562I);
		// TODO Auto-generated constructor stub
	}

///*LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88562|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||88562|C|R|1|N|F|||||||||||||||||11082010039319|L|Y||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

	
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
		strValue[26] = "88562"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostname; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branch; // Branch Code
		strValue[45] = "88562"; // Transaction Code
		strValue[46] = "C"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";		
		strValue[67] = loanAcc; // LoanAcc
		strValue[68] = "L";
		strValue[69] = "Y";
		
		return strValue;
	}
}
