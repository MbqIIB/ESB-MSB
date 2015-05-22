package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.Collateral;
import vn.com.msb.as400.dsp.DSPPackager;

public class Collateral_MB87202 extends IMessage {
	public Collateral calla=null;
	public Collateral_MB87202() {
		super(DSPPackager.PACKAGER_MBASE_87202I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
		
		// *LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|87202|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||87202|A|R|1|N|F||||Y|||||||||||||DP1|COLATERALNAME|110||1|DP1||COLAT DESCRIPTION|O|CF011004|11020100901702|VN|100000000|VND|3|M|212845|0.21|CA|260314|Y|270314||270314|Y|CDMK370001|FD|ISSUING INSTITUTION|NAME1|NAME2|NAME3|DUE DATE INSTRUCTION|REMARRK||0.80||010114||020114||REGNO|AG|030114||040114||REG OWNER NUM|BD|AA|040114||050114||060114||1000|1200||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		
		String strHostName = null;
		try {
			strHostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			strHostName = "127.0.0.1";
		}
		strValue[0] = "*LINX"; // 0 :
		strValue[1] = strHostName; // 1 :
		strValue[5] = "213"; // 5 :
		strValue[7] = "0200"; // 7 :
		strValue[8] = "*DSP"; // 8 :
		strValue[9] = "MBSD"; // 9 :
		strValue[10] = "*LINX"; // 10 :
		strValue[14] = "01"; // 14 :
		strValue[17] = "BBMBSLNMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = calla.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "87202"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = calla.getTeller();
		strValue[32] = "1"; 
		strValue[34] = "*END"; 
		strValue[35] = "";
		strValue[36] = "BTS"; 
		strValue[37] = "RBS"; 
		strValue[38] = "";
		strValue[39] = strHostName;
		strValue[40] = "27"; // 40 : Bank Number
		strValue[41] = calla.getBranch(); // 41 : Branch
											// Number
		strValue[45] = "87202"; // Transaction Code
		strValue[46] = "A"; 
		strValue[47] = "R"; 
		strValue[48] = "1"; 
		strValue[49] = "N"; 
		strValue[50] = "F";
		strValue[54] = "YYY";
		// DDMMYYYY
		strValue[67] = calla.getCalaCode();
		strValue[68] = calla.getCollateralName(); 
		strValue[69] = calla.getBranch(); 
		strValue[70] = calla.getCifName(); 
		strValue[71] = "1"; // Check date
		strValue[72] =calla.getCalaCode(); // Low check number
		strValue[73] = "";
		strValue[74] = calla.getCollateralDesc();
		strValue[75] = calla.getDeposit();
		strValue[76] = calla.getOfficeCode();
		strValue[77] = calla.getFdAcc();
		strValue[78] = calla.getCountryCode();
		strValue[79] = calla.getDepositAmount();
		strValue[80] = calla.getCurency();
		strValue[81] = calla.getDepositTenureNum();
		strValue[82] = calla.getDepositTenureType();
		strValue[83] = calla.getCifNum();
		strValue[84] = calla.getInterestRate();
		strValue[85] = calla.getRelationShip();
		strValue[86] = calla.getIssueDate();
		strValue[87] = calla.getCashValueSale();
		strValue[88] = calla.getMaturityDate();
		strValue[90] = calla.getReviewDate();
		strValue[91] = calla.getMarkedValue();
		strValue[92] = calla.getLienOfficeCode();
		strValue[93] = "FD";
		strValue[94] = calla.getIssuingInstitution();
		strValue[95] = calla.getDepositName1();
		strValue[96] = calla.getDepositName2();
		strValue[97] = calla.getDepositName3();
		strValue[98] = calla.getDudateInstruction();
		strValue[99] = calla.getDescription();
		strValue[101] = calla.getCollateralRatio();
		strValue[103] = calla.getSecuregDate();
		strValue[105] = calla.getSecuregExp();
		strValue[107] = calla.getSecureReqNo();
		strValue[108] = calla.getRegPlace();
		strValue[109] =  calla.getOwnerRightDate();
		strValue[111] =  calla.getOwnerExtDate();
		strValue[113] = calla.getOwnerNum();
		strValue[114] = calla.getOwnerPlace();
		strValue[115] = calla.getNotaryPublic();
		strValue[116] = "000000";
		strValue[118] = "000000";
		strValue[120] = "000000";
		strValue[122] = calla.getInvestment();
		strValue[123] = calla.getBankUsage();
		return strValue;
	}

}
