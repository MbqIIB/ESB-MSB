package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.AccInfo;
import vn.com.msb.as400.dsp.DSPPackager;

public class RegisterOD_MB28901 extends IMessage {
	public RegisterOD_MB28901() {
		super(DSPPackager.PACKAGER_MBASE_28901I);
		// TODO Auto-generated constructor stub
	}

	public AccInfo acc=null;

	@Override
	public String[] toArray() {
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
		strValue[17] = "BBMBSDDMNTFNC"; // 17 :
		strValue[22] = "1"; // 22 :
		strValue[23] = "10"; // 23 :
		strValue[24] = acc.getTeller(); // 24 :
		strValue[25] = strHostName; // 25 :
		strValue[26] = "28901"; // 26 :
		strValue[27] = "N"; // 27 :
		strValue[31] = acc.getTeller(); // 31 :
		strValue[32] = "1"; // 32 :
		strValue[34] = "*END"; // 34 :
		strValue[36] = "BTS"; // 36 :
		strValue[37] = "RBS"; // 37 :
		strValue[39] = strHostName; // 39 :
		strValue[40] = "27"; // 40 :
		strValue[41] = acc.getBranch(); // 41 :
		strValue[45] = "28901"; // 45 :
		strValue[46] = "C"; // 46 :
		strValue[47] = "R"; // 47 :
		strValue[48] = "1"; // 48 :
		strValue[49] = "N"; // 49 :
		strValue[50] = "F"; // 50 :
		strValue[56] = "C"; // 56 :
		strValue[67] = acc.getAccNo_68(); // 67 :
		strValue[68] = acc.getAcctype(); // 68 :
		strValue[69] = acc.getNewProCode_70();
		strValue[70] = acc.getNewDate_71();
		strValue[71] = acc.getStatus(); // 71 :
		strValue[74] = acc.getPar1_75(); // 74 :
		strValue[75] = acc.getPar2_76(); // 75 :
		strValue[76] = acc.getPar3_77(); // 76 :
		strValue[77] = acc.getServiceCharge_78(); // 77 :
		strValue[78] = acc.getCheque_79(); // 78 : Cheque
		strValue[79] = acc.getHoldMailCode_80();
		strValue[80] = acc.getIntroduceCode_81(); // 80 :
		strValue[81] = acc.getPar4_82(); // 81 :
		strValue[82] = acc.getPar5_83(); // 82 :
		strValue[83] = acc.getPar6_84(); // 83 :
		strValue[84] = acc.getPar7_85(); // 84 :
		strValue[85] = acc.getPar_85();
		strValue[86] = acc.getPar8_87_100(); // 86 :
		strValue[87] = acc.getPar88_rateCode();
		strValue[88] = acc.getPar9_89_100();
		strValue[89] = acc.getPar10_90_100(); // 89 :
		strValue[90] = acc.getPar11_91_100(); // 90 :
		strValue[91] = acc.getPar92_RateFloor(); // 91 :
		strValue[92] = acc.getPar93(); // 92 :
		strValue[93] = acc.getPar94(); // 93 :
		strValue[94] = acc.getPar95(); // 94 :
		strValue[95] = acc.getSalOffice_96(); // 95 :
		strValue[96] = acc.getProductGroup_97(); // 96 :
		strValue[97] = acc.getModeOfoper_98();
		strValue[98] = acc.getGroupCodeUpdate(); // 98 :
		strValue[99] = acc.getPar13_100(); // 99 :
		strValue[103] = acc.getPar_113(); // 103 :
		strValue[104] = "00000000000000";
		strValue[105] = acc.getPar116();
		strValue[107] = acc.getPar14_118_100(); // 107 :
		strValue[108] = acc.getPar15_119_100(); // 108 :
		strValue[109] = acc.getPar16_120_100(); // 109 :
		strValue[110] = acc.getPar17_121(); // 110 :
		strValue[114] = acc.getPar18_118_100(); // 114 :
		strValue[115] = "N"; // 115 :
		strValue[116] = acc.getAccName_129(); // 116 :
		strValue[117] = acc.getPar130();
		strValue[118] = acc.getOverDraffProtect_131();
		return strValue;
	}
	
}
