package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.LockODP;
import vn.com.msb.as400.dsp.DSPPackager;

public class LockODPTier_MB26050 extends IMessage {

	public LockODP lock = new LockODP();

	public LockODPTier_MB26050() {
		super(DSPPackager.PACKAGER_MBASE_26050I);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] toArray() {
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
		strValue[17] = "BBMBSDDINQFNC"; // Scenario Number
		strValue[22] = "1";
		strValue[23] = "10";
		strValue[24] = lock.getTeller(); // User ID
		strValue[25] = strHostName; // Terminal ID
		strValue[26] = "26050"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = lock.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = strHostName; // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = lock.getBranch(); // Branch Code
		strValue[45] = "26050"; // Transaction Code
		strValue[46] = "I"; // add
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[56] = "C";
		strValue[67] = lock.getAccountNumber(); // account Number
		strValue[68] = lock.getAccountType();// account Type
		strValue[69] = lock.getJounalSequence();

		return strValue;
	}

}
