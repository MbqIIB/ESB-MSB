package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import vn.com.m1tech.AS400.entity.AAEntity;
import vn.com.msb.as400.dsp.DSPPackager;

public class AddAA_MB88301 extends IMessage {
	public AAEntity aa;
	public AddAA_MB88301() {
		super(DSPPackager.PACKAGER_MBASE_88301I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
		// TODO Auto-generated method stub
		/* [881|*LINX|10.2.142.30||||213||0200|*DSP|MBSD|*LINX||||01|||
		 * BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88301|N||||DD110015|1||*END||
		 * BTS|RBS||quanld-PC|27|110||||88301|C|R|1|N|F|||||||||||||||||212845000|
		 * 212845|280214||00110|11|N|120314|11|140314|9|
		 * Review Remark|Refinance From|A|C|B|B|000|VND|CF011004|0|MA Remark|
		 * 120214||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		 * ||||||||||||||||||]
		 * 
		 */
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
		strValue[24] = aa.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "88301"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = aa.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = aa.getBranch(); // Branch Code
		strValue[45] = "88301"; // Transaction Code
		strValue[46] = "C"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "";
		strValue[67] = aa.getApplicationNo(); // application Number
		strValue[68] = aa.getCifNum();
		strValue[69] = aa.getApplicationDate();
		strValue[71] = aa.getBranch(); // lpad 5 '0'
		strValue[72] = aa.getWorkingEx();
		strValue[73] = aa.getSpecProvision(); // Y/N
		strValue[74] = aa.getClasifielDate();
		strValue[75] = aa.getCarCode();
		strValue[76] = aa.getReviewDate();
		strValue[77] = aa.getRetention();
		strValue[78] = aa.getReviewReMark();
		strValue[79] = aa.getRefinanceFrom();
		strValue[80] = aa.getTucachPn();
		strValue[81] = aa.getTinhHinhTc();
		strValue[82] = aa.getKhaNangVayVon();
		strValue[83] = aa.getDamBaoTienVay();
		strValue[84] = aa.getLimitAmount();
		strValue[85] = aa.getCurencyType();
		strValue[86] = aa.getOfficeCode();
		strValue[87] = aa.getClassification();
		strValue[88] = aa.getMARemarck();
		strValue[89] = aa.getMADate();
		return strValue;
	}

}
