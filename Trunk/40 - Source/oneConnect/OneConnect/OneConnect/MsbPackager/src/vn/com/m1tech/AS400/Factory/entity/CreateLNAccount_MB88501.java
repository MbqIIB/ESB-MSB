package vn.com.m1tech.AS400.Factory.entity;

import vn.com.msb.as400.dsp.DSPPackager;
/**
 * 
 * @author QuanLD
 * LN Normal Account - Quick Opening
 */
public class CreateLNAccount_MB88501 extends IMessage {
//	public String facilityCode = "";
//	public String productType = "";
//	public String currencyType = "";
//	public String applicationNo = "";
//	public String cif;	
	
	//*LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88501|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||88501|A|R|1|N|F|||||||||||||||||11082010039319|L|10000000000|10000000000|D|3|M|3|M|000000|000|220414|3|M|0|2|6||000000||000000|000000|000|000|0.00|0.00||0.00|0.00|0.00|0.10|000000|000|00000|D|10000000000|||0.00||0.00|0.00|3|M|000000||10000000000||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||] End Of Record.
	
	public String  lnAcctNo;
	public String  orgLnAmount;
	public String  drawingLimit;
	public String  modeOfInterest; 
	public String  intPayFreq; 
	public String  intPayFreqCode; 
	public String  payFreq; 
	public String  payFreqCode; 
	public String  maturityDate; 
	public String  capitalPrepaym; 
	public String  originationDate; 
	public String  loanTerm; 
	public String  loanTermCode;
	public String  status;  
	public String  yearBase; 
	public String  paymentCode; 
	public String  rateReviewDate; 
	public String  rateUserReviewTerm;  
	public String  rateUserReviewCode;
	public String paymentAmount;
	public String rateReviewTerm;
	public String rateReviewCode;
	public String finalPaymAmt;		
	public String orgLoanAmount;	
	public String trancheMaxTerm;
	public String  trancheMaxCode;	
	public String loanCode;	
	public String interestBase;	
	public String residualValue;
	public String dealerRate;
	public String  maturityRate;
	public String advanceMargin;
	public String rateVariance;
	public String rateVarianceFloor;
	public String rateVarianceCeiling;
	public String  rateFloor;
	public String rateCeiling;
	public String interestRate;
	public String rateReviewDay;
	
	
	/*
	 * *LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88501|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||88501|A|R|1|N|F||||                      Y      Y|||||||||||||11082010038862|L|1000000000|1000000000|D|3|M|3|M|000000|000|260314|3|M|0|2|6||000000||000000|000000|000|000|0.00|0.00|00101|0.00|0.00|0.00|0.01|260314|000|00000|D|1000000000|||0.00||0.00|0.00|3|M|000000||1000000000|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * |||||||||||||||||||||||||||||||||||||||||||||||] End Of Record.
	 * */
	
	
	
	public CreateLNAccount_MB88501(){
		super(DSPPackager.PACKAGER_MBASE_88501I);
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
		strValue[26] = "88501"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = teller; // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = hostname; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = branch; // Branch Code
		strValue[45] = "88501"; // Transaction Code
		strValue[46] = "A"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		String gia_tri_gi_nhi ="";
		strValue[54] = gia_tri_gi_nhi; //             Y                                 Y
		strValue[67] = lnAcctNo; // application Number
		strValue[68] = "L";
		strValue[69] = orgLnAmount;
		strValue[70] = drawingLimit;
		strValue[71] = modeOfInterest; // D
		strValue[72] = intPayFreq; //3
		strValue[73] = intPayFreqCode; //M
		strValue[74] = payFreq; // 3
		strValue[75] = payFreqCode; // M
		strValue[76] = maturityDate; // 241214
		strValue[77] = capitalPrepaym; // 000
		strValue[78] = originationDate; //
		strValue[79] = loanTerm; //
		strValue[80] = loanTermCode;
		strValue[81] = status; // 0 
		strValue[82] = yearBase; //2
		strValue[83] = paymentCode; 
		strValue[85] = "000000"; 
		strValue[87] = "000000"; 
		strValue[88] = "000000"; 
		strValue[89] = "000"; 
		strValue[90] = "000"; 
		strValue[91] = "0.00"; 
		strValue[92] = "0.00"; 
		strValue[94] = "0.00"; 
		strValue[95] = "0.00"; 
		strValue[96] = "0.00"; 
		strValue[97] = interestRate; 
		strValue[98] = rateReviewDate; // 140214
		strValue[99] = "000"; 
		strValue[100] = rateUserReviewTerm; //00000 
		strValue[101] = rateUserReviewCode; // D
		
		strValue[102] =paymentAmount;
		strValue[105] ="0.00";
		strValue[107] ="0.00";
		strValue[108] ="0.00";
		strValue[109] =rateReviewTerm;
		strValue[110] =rateReviewCode;
		strValue[111] = "000000";
		strValue[113] =finalPaymAmt;
		return strValue;
	}
}
