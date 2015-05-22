package vn.com.m1tech.AS400.Factory.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import vn.com.m1tech.AS400.entity.Facility;
import vn.com.msb.as400.dsp.DSPPackager;

public class FacilityMaintain_MB88303 extends IMessage {

	public Facility fac = null;

	public FacilityMaintain_MB88303() {
		super(DSPPackager.PACKAGER_MBASE_88303I);
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
		/*
		 * *LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88303|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||88303|C|R|1|N|F||||   YY       Y|||||||||||||250676000|240|2|0|250676|00110/2014/0000552|UNGVON002|A|260314|VND|1000000000|99||N|O|260314|260314|260314|005|000000|000000|1000000000|1000000000|000|8|D|2|3|M|00101|0.18|0.00||000000|3|M|0.02|0.30|6|3|M|3|M|1000000000|1000000000|500|000|D|260614|000|000|TRAN PHUONG TRA|27|110|CF011004|N|||010|S|||000||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||] End Of Record.
		 * *LINX|192.168.56.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSLNMNTFNC|||||1|10|DD110015|quanld-PC|88303|N||||DD110015|1||*END||BTS|RBS||quanld-PC|27|110||||88303|C|R|1|N|F||||         Y|||||||||||||839830000|240|3|0|839830|00110/2014/0000570|UNGVON002|P|260314|VND|10000000000|01||N|O|260314|260314|000000||000000|000000|10000000000|10000000000|000|8|D|2|3|M||0.00|0.00||000000|3|M|0.00|0.00|6|3|M|3|M|10000000000|10000000000|500|000|D|260614|000|000||27|110|CF011004|N|||110|S|||000||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||] End Of Record.
		 * */

		strValue[0] = "*LINX"; // Header types
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
		strValue[24] = fac.getTeller(); // User ID
		strValue[25] = fac.getHostName(); // Terminal ID
		strValue[26] = "88303"; // Supervisor ID
		strValue[27] = "N";
		strValue[31] = fac.getTeller(); // Message Header
		strValue[32] = "1"; // Message Header
		strValue[34] = "*END";
		strValue[36] = "BTS";
		strValue[37] = "RBS";
		strValue[39] = fac.getHostName(); // Terminal ID
		strValue[40] = "27"; // Journal Seq
		strValue[41] = fac.getBranchCode(); // Branch Code
		strValue[45] = "88303"; // Transaction Code 88303
		strValue[46] = "C"; // C
		strValue[47] = "R"; // response
		strValue[48] = "1"; // Work station
		strValue[49] = "N";
		strValue[50] = "F";
		strValue[54] = fac.getRcdOveride();// overide transaction rcdOveride
		strValue[67] = fac.getApplicationNo();// applicationNo; // application No
		strValue[68] = fac.getFacilityCode();// facilityCode; // Facility code
		strValue[69] = fac.getSeqNo();// seqNo;// seq No
		strValue[70] = fac.getLevelNo();// levelNo; // levelNo
		strValue[71] = fac.getCifNo();// cifNo; // seq No
		strValue[72] = fac.getAFCNo();// AFCNo; // AFCNo
		strValue[73] = fac.getProducType();// producType; // producType
		strValue[74] = fac.getAFCStatus();// AFCStatus; // AFCStatus, 'A','P'
		strValue[75] = fac.getAppliedDate();// appliedDate; // ngay tao
		strValue[76] = fac.getCurrencyType();// currencyType; // currencyType
		strValue[77] = fac.getAppliedAmount();// appliedAmount; // applied Amount
		strValue[78] = fac.getPurposeCode();// purposeCode; // purpose code
		strValue[79] = "";
		strValue[80] = fac.getRevolving();// revolving; // revolving N/R
		strValue[81] = fac.getaBalIndicator();// aBalIndicator;// available Balance Indicator O
		strValue[82] = fac.getSenttoAppDate();// senttoAppDate "260713"
		strValue[83] = fac.getRecFromAppCenter();// recFromAppCenter; // recFromAppCenter
		strValue[84] = fac.getApprovedDate();//approvedDate;// approvedDate
		strValue[85] = fac.getAppBy();// appBy;// appBy
		strValue[86] = fac.getOfferDate();// offerDate; // offerDate
		strValue[87] = fac.getAcceptedDate();// acceptedDate;// acceptedDate
		strValue[88] = fac.getAppAmount();// appAmount
		strValue[89] = fac.getFacilityLimit();// facilityLimit
		strValue[90] = fac.getAvailableBalance();// availableBalance
		strValue[91] = fac.getInterestBase();// interestBase
		strValue[92] = fac.getInterestMode();// interestMode
		strValue[93] = fac.getYearBase();// yearBase
		strValue[94] = fac.getTerm();// term
		strValue[95] = fac.getTermCode();// termCode
		strValue[96] = fac.getRateNumber();// termCode
		
		strValue[97] = fac.getInterestRate();// interestRate
		strValue[98] = fac.getVariance();// variance
		strValue[99] = fac.getVarCode();// varCode
		strValue[100] = fac.getRateReviewDate();// rateReviewDate
		strValue[101] = fac.getRateReviewTerm();// rateReviewTerm
		strValue[102] = fac.getRateReviewCode();// rateReviewCode
		strValue[103] = fac.getPrimeRateFloor();// primeRateFloor
		strValue[104] = fac.getPrimeRateCeiling();// primeRateCeiling
		strValue[105] = fac.getPaymentCode();// paymentCode
		strValue[106] = fac.getPaymentFreq();// paymentFreq
		strValue[107] = fac.getPaymentFreqCode();// paymentFreqCode
		strValue[108] = fac.getInterestPmtFreq();// interestPmtFreq
		strValue[109] = fac.getInterestPmtFreqCode();// interestPmtFreqCode
		strValue[110] = fac.getPaymentAmount();// paymentAmount
		strValue[111] = fac.getFinalPaymAmt();// finalPaymAmt
		strValue[112] = fac.getLateChargeCode();// lateChargeCode
		strValue[113] = fac.getGracePeriod();// gracePeriod
		strValue[114] = fac.getGracePeriodCode();// gracePeriodCode
		strValue[115] = fac.getExpireDate();// expireDate
		strValue[116] = fac.getProcessingFee();// processingFee
		strValue[117] = fac.getLegalFee();// legalFee
		strValue[118] = fac.getCifName();// cifName
		strValue[119] = "27";// fix
		strValue[120] = fac.getBranchTCode();// branchTCode
		strValue[121] = fac.getOfferCode();// offerCode
		strValue[122] = fac.getShareLimitInd();// shareLimitInd
		strValue[125] = fac.getBranchTHCode();// branchTHCode 3 so
		strValue[126] = fac.getSecureCode();// secureCode U, S,P
		strValue[127] = fac.getAvailabelLimit();// availabelLimit
		strValue[129] = fac.getEstimateLose();// estimateLose
		return strValue;
	}

}
