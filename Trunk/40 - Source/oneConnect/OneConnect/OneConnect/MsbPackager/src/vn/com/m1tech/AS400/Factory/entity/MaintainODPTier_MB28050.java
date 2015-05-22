package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.OdpEntity;
import vn.com.msb.as400.dsp.DSPPackager;

public class MaintainODPTier_MB28050 extends IMessage {
	public OdpEntity odp ;

	public MaintainODPTier_MB28050() {
		super(DSPPackager.PACKAGER_MBASE_28050I);
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
		strValue[17] = "BBMBSDDMNTFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = odp.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "28050";
		strValue[27] = "N";
		strValue[31] = odp.getTeller();
		strValue[32] = "1";
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName;
		strValue[40] = "27";
		strValue[41] = odp.getBranch(); // Branch Code
		strValue[45] = "28050"; // Transaction Code
		strValue[46] = "C"; // locl
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = odp.getAccountNumber(); // account Number
		strValue[68] = odp.getAccountType();// account Type
		strValue[69] = odp.getJounalSequence();
		strValue[70] = odp.getOdpType();// tier type
		strValue[71] = odp.getDrawLimit(); // drawLimit
		strValue[72] = odp.getAuthLimit(); // draw
		strValue[73] = "000";
		strValue[74] = "000";
		strValue[75] = odp.getOdRate(); // lãi suất thấu chi "07002"
		strValue[76] = odp.getRateVariance();// rate variance //0.01
		strValue[77] = odp.getRateCode();// rate Code // + or -
		strValue[78] = odp.getRateFloor(); // rateFloor "0.00"
		strValue[79] = odp.getRateCeiling();// rateCeiling "0.15"
		strValue[80] = odp.getCommitFreeRate();
		strValue[81] = odp.getCommitFreeType();
		strValue[82] = odp.getArgDate();// agreementDate "260713"
		strValue[83] = odp.getExpDate();// expirationDate "260713"
		strValue[84] = odp.getProductGroup();
		strValue[85] = odp.getExcessLimitType();
		strValue[86] = "000000";
		strValue[88] = "000";

		return strValue;
	}

}
