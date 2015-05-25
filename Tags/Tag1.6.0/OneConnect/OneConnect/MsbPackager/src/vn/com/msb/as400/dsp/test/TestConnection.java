package vn.com.msb.as400.dsp.test;

import java.math.BigDecimal;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import vn.com.msb.as400.dsp.ATMMessageFactory;
import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPMessageConstant;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.as400.dsp.FDMessageFactory;
import vn.com.msb.as400.dsp.MessageFactory;
import vn.com.msb.as400.dsp.TreasuryMessageFactory;

public class TestConnection {

	
	
	
	public TestConnection() throws Exception {
		
		
		
//		Socket sk, String teller,
//		String manager, String branchCode, String journalSeq,
//		String transDate, String creditAcc, String debitAmount,
//		String debitAcc, String creditRate, String debitRate,
//		String adviceNumber, String creditCurrency, String debitCurrency,
//		String description, String creditAmount
		
		
//		Socket sk = new Socket("10.0.1.14", 9600);
		Socket sk = new Socket("10.2.1.82",9800);
    //  Socket sk = new Socket("10.2.1.1",5105);		
		
/******************thuc hien test****************/	
//		 searchCustomerInformationByID(sk, "DD110001","SMSBK01",
//				 "010","218775");
//		 searchMobileByCifMessage("", "SMSTELLER1", "010", "1007713",sk);
		
		
/******************end test****************/			
		
	//String[] arr11=	
//			transferGl2Sa(sk,"DD110001",
//				"DD110999",
//				"110","002",
//				"220513","220513","201171783", 
//				"11007370000016",
//				"19999",
//				"110101001",
//				"211450000000",
//				"191420000000",
//				"1112","USD","AUD","Description", "");
	
	//String[] vv= 
//			transferSa2GL(sk, "DD110001", "DD110999", "110", "05", "220513","220513", "6666", "110101001", "908367", "11007370000016", "288140000000", "211450000000",
//			"2222", "EUR", "USD", "Chaaaaaaaa", "");
		// searchCustomerInformationByID(sk,"","","","");
		// loanAccountInquiry(sk,"IT020003","10.1.99.140","020","11082010027781");
		// cashDeposit(sk);
//		 createOL2(sk, "10.1.99.4", "HP", "EBANKING03", "EBANKING01", "1234",
//		 "050613", "110", "11001010000105", "100000", "", "280898100",
//		 "0", "0", "ABC", "", "", "", "MSB", "",
//		 "", "", "", "EB8277");
		//buildOL2Message
		
		
//		 createOL2(sk, "10.1.99.36", "HP", "CKVND001", "CKVND002", "100",
//		 "080613", "110", "11001011111129", "100000", "839535", "280898009",
//		 "0", "0", "Do Ngoc Linh/ 091C122258", "CONG TY CHUNG KHOAN MSBS",
//		 "58697874", "445454545454", "FASDFASDF", "",
//		 "92357001","LIENVIETPOSTBANK CAN THO", "92", "", "","CK8277", "110214021880054");
		 
//		String des = "cha biet lam sao";
//	
//		des = des + "Transfer from CI account to other Bank";
//		 createOL2(sk, "10.1.99.36", "HP", "CKVND001", "CKVND002", "114",
//				 "080713", "110", "11001011112210", "16000000", "839740", "280898012",
//				 "0", "0", des , "CTY VNDIRECT",
//				 "58697874", "445454545454", "vu huy tang vu huy tang vu huy tang vu huy tang vu huy tang", "",
//				 "92357001","PHAT TRIEN NONG THON VIET NAM (AGRIBANK)", "92", "", "","CK8277", "110214041882007");
		 
		 
		// testTF(sk);
		// Test test =new Test();
		// test.t.join();
		// transfer(sk, "branchCode", "creditAccount", "creditAmount",
		// "creditCurrency", "creditRate", "debitAccount", "debitAmount",
		// "debitCurrency", "debitRate", "manager", "strDescription",
		// "strJournalSeq", "teller", "transDate");

		// rollback(sk, "0109", "0106");
		// System.out.println("Start." + new java.sql.Timestamp(new
		// Date().getTime()));
		// for (int i = 0; i < 100; i++) {
		// queryAccount(sk, "03101016938888"); //CF011011,271148,031 // OK
		// queryAccount(sk, "11001011111558");

		// 03401013999999
		
		//queryAccount(sk, "11001011112210");//02001010666750
		//amount: 400278747500
				 // 400000000000
		
		//queryAccount(sk, "03101018914783");
		
//		searchAALoanByCifNo(sk,"DT110022","10.1.99.36","110","1234");
//		System.out.println("all facility:");
		
		//searchAllFacilityLoan(sk,"DT110022","10.1.99.36","110","1234000");
		
//		System.out.println("detail facility:");
	    
//		String dennis = "0.30000008880000";
//		double f = Double.parseDouble(dennis);
//		
//		System.out.print(String.format("%.2f", new BigDecimal(f)));
//
//		
		//searchDetailFacilityLoan(sk,"LN020001","10.1.99.36","020","13168000","211","3");
		
		unlockFacilityLoan(sk,"LN020001","10.1.99.36","020","LN020001  " + "\\" + "LNAPPF    " + "\\" + "00085434");
		
		
		
		
		//searchAccountByCifMessage("SMSTELLER1","10.10.10.10","010","160651",sk);
		
		
		// queryAccount(sk, "01101010018337");//01101015669006, 01101010018337
		// queryAccount(sk, "03001013988889"); // MSBDSF0012,271030,030 HIEUTK
		// queryAccount(sk, "11001016915881");
		//queryAccount(sk, "11001010009377");

//		 transfer(sk, "110", "02001010666750", "4000000000", "VND",
//		 "10000000",
//		 "11001011112210","4000000000","VND", "10000000", "DD110001",
//		 "rut tk ck vnd","2","IT110003","090613" );
		 
		
		// System.out.println("End." + new java.sql.Timestamp(new
		// Date().getTime()));

		// String strHoldId = hold(sk);
		// lock(sk,"1");
		// unHold(sk,"1");
		// reversableTranfer(sk, "DD110005", "DD110002", "020",
		// "11001010025201", "11001010000150", "250911", "Test topup", "50000",
		// "1239", "139", "1321");

		// reversableTranferOL2(sk, "10.1.99.4", "HP", "SMSTELLER2",
		// "SMSTELLER1", "1122", "21",
		// "260911", "020", "11001010025201", "5000000","5000000", "213068",
		// "280898100",
		// "0", "0", "Test topup", "VU TRUNG KIEN", "1234567", "", "Smartlink",
		// "",
		// "", "", "", "EB8277", "999110809000517");

		// MSBDSF0012, 271030, 030, 03001013988889
		// createCA(sk,"CF011011",398888,"040412","030","01","VND","271030","CAM1","VIP","1");
		// createCA(sk,"CF011001",199999,"161112","031","01","VND","306448","CAM1","VIP","1");
		// createCA(sk,"CF011001",693686,"281010","110","VND","271052","CAM1","VIP","1");
		// createCA(sk,"CF011001",90000,"021010","032","VND","271057","CAM1","VIP","1");

		// createCA(sk,"DD020001",90031,"110311","041","VND","212755","CAM1","VIP","1");
		// searchCustomerByCif(sk, "tellerId", "hostName", "branchCode",
		// "cifNumber");
		// searchCustomerByCif(sk, "DD020001", "10.1.99.50", "020", "3698");
		// for(int i=0;i<100;i++){

		// searchCustomerByID(sk,"CF110002","10.1.99.32","110","380339685");
		// }
		// searchCustomerByID(sk,"DD020001","10.1.99.32","020","011876841");

		// createCif(sk, "teller", "hostName", "branchCode", "IdNumber",
		// "strIdType", "strIdDateIssued", "strIdCountryIssued",
		// "strIdPlaceIssued", "strSurname", "strAfterSurname",
		// "strAddressLine1", "strAddressLine2", "strAddressLine3",
		// "strAddressLine4_codeCheckCust", "strAddressLine5", "strNationality",
		// "strBirthday", "strRaceCode", "strMaritalStatus", "strGender",
		// "_2strOccupationCode", "strPlaceOfBirth",
		// "strTypeElectronicAddress1", "strTypeElectronicAddress2",
		// "strTypeElectronicAddress3", "strElectronicAddress1",
		// "strElectronicAddress2", "strElectronicAddress3", "hostDate",
		// "strBirthday_Full", "strIdDateIssued_Full", "_1strOccupationCode");

		// createCif(sk, "AT020010", "HP", "020", "EBCORP12345", "BL", "111111",
		// "VN", "HN", "EBANKCORP01", "EBANKCORP01", "HN",
		// "", "", "", "", "VN", "30/11/80", "008", "2", "F",
		// "A004", "Hanoi", "", "", "", "", "", "",
		// "111210", "30111980", "02021982", "A000");

		// createCif(sk,"MG011005","HP","090","EBANKCORP10","BL","111111","VN","HN","EBANKCORP10","EBANKCORP10","","","","","","VN","111111","","","","A001","","","","","","","","051212","11112011","11112011","A000","HN");

		// if(lockCif(sk, "AT020010", "212755", "020", "01001010900288"));{
		// tranferAccountFromCifToAnother(sk, "AT020010", "212755", "020",
		// "01001010900288", "212820", "LE ANH TUAN");
		// }
		// if (lockUpdateIntroducerCodeInCA(sk, "AT020010", "020",
		// "01001010900336")) {
		// updateIntroducerCodeInCA(sk, "AT020010", "DD020202", "020",
		// "01001010900336");
		// }
		// if (lockUpdateIntroducerCodeInNewCA(sk, "AT020010", "020",
		// "01001012900031")) {
		// updateIntroducerCodeInNewCA(sk, "AT020010", "DD020202", "020",
		// "01001012900031");
		// }
		// linkCard(sk, "DD020001", "020", "02001010404831", "", "D", "F",
		// "VND",
		// "00020", "212999", "141020000", "IP", "NGUYEN VAN  C", "jjjjj",
		// "FGDB", "Y", "Ysdd","","","");
		
//		 searchCustomerInformationByID(sk, "SMSTELLER1","SMSBK01",
//		 "020","1007713");
		 
		// searchMobileByCifMessage("strSystemName", "strteller", "strBankcode",
		// "strsearch_key", sk);
		// searchMobileByCifMessage("", "DD020001", "020", "212900",sk);
		// joinCA(sk, "strSystemName", "strteller", "strBankcode", "transDate",
		// "strAccount", "strCifnumber", "strNameAcount", "strcurrCode");
		// joinCA(sk,"10.1.99.32", "DD020001", "020", "110311",
		// "04101010900312", "212900", "", "VND");
		// createGLTransactions_BatchMessage("IT020003", "10.2.1.37", "020",
		// "VND", "301111", sk);
		// createMiniStatementMessage("DD110001", "110", "5", "03101011101539",
		// "D", sk);
		// createDDInquiryMessage(sk, "DD020001", "10.1.99.4", "020",
		// "03101011999996", "D");//83 so du
		
//		createInquiryAccountForOverdraftMessage(sk, "DD020001", "10.2.142.100", "110","11031010090082", "D");
//		
//		createODPTierMaintainMessage(sk,"DD020001", "10.2.142.100", "110","11031010090082", "D","90000000","90000000","07002","260713","261214");
		
		//(teller, hostName, branchCode, accountNumber, accountType, drawLimit, authLimit, odRate, argDate, expDate);
		
		
		// searchAccountMoreByCifMessage("IT020003","localhost","020","212868","D","02001011100149","P",sk);

		// cardInquiry("IT020003","020","1207910000083724",sk);
		// linkMasterCard(sk, "DD020001", "020", "02001010404831", "D", "F",
		// "VND", "020", "212999", "141020000", "IP", "NGUYEN VAN  C",
		// "dia chi nhan pin", "MBASICNOR", "Y",
		// "Ysdd","","","");//9704260000198176

		// activeCard(sk, "DD020001", "020",
		// "9704260000165092","212900","4587887455","IP");
//		 createFDReceipt(sk, "DD110001","110", "1111", "260911",
//		 "11005010136730", "50000000", "260911",
//		 "11010199000002", "0000000000", "11001010024932",
//		 "11001010024932", "VND", "FC030DTOP",
//		 "N", "D", "1",
//		 "kienvtest");
		// hold(sk);
		// holdMessage(sk);
		// holdMessage(sk, "10.1.99.140", "IT110003", "HP", "110",
		// "11001012003098", "123000", "TEST IAT");
		// unholdMessage(sk,"10.1.99.140","IT110003","HP","110","11001012003098","23000","TEST IAT","9");
		// unholdMessage(sk,"10.1.99.140","IT110003","HP","110","11001012003098","1500000","TEST IAT","10");

		// createIO5(sk, "10.10.10.10", "localhost", "DD110001", "IT110003",
		// "10",
		// "210212", "110", "11001375000046", "1234500", "218940", "189802006",
		// "0",
		// "0", "test core", "NGUYEN VAN C", "121212199", "Ha noi", "",
		// "", "8177", "110812020909999", "110812020900001", "USD", "USD");
		// TODO
		// addCifGroup(sk,"IT110003","110","12155","0","212900","1","07","0");
		// rateFD(sk,"CH110003","110","11010100440127","270913","18000000000","");

	}
	
	

	public void rateFD(Socket sk, String teller, String branchCode,
			String fdReceipt, String rateChangeDate, String newRate,
			String variantBaseRate) throws Exception {

		String[] strValue = FDMessageFactory
				.rateScheduleMainternance(teller, branchCode, fdReceipt,
						rateChangeDate, newRate, variantBaseRate);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_38902I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_38902I);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));
	}

	public void addCifGroup(Socket sk, String teller, String branchCode,
			String cifCorp, String seq, String cifEmp, String relationalEmpLev,
			String relationalType, String relationalCorpLev) throws Exception {
		String[] strValue = MessageFactory
				.createCifGroupManagementAdditionMessage(teller, branchCode,
						cifCorp, seq, cifEmp, relationalEmpLev, relationalType,
						relationalCorpLev);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_17321I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_17321I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
		 .unpack(btResponse);
		System.out.println(strMsgStatus);
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();
	}

	public String[] activeCard(Socket sk, String teller, String branchCode,
			String cardNumber, String cifNumber, String idNo, String idType)
			throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = ATMMessageFactory.createCardActivationMessage(
				teller, branchCode, cardNumber, cifNumber, idNo, idType);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_99067I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_99067I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public String[] linkMasterCard(Socket sk, String teller, String branchCode,
			String strAccountTag, String strAccountType, String strUsage,
			String strCurrency, String strBranchTag, String strCIFNo,
			String strIDNumber, String strIDType, String strName,
			String strAddress, String strProduct, String strAnnualFee,
			String strVIP, String strAddressLine2, String strAddressLine3,
			String strAddressLine4) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = ATMMessageFactory.createLinkMasterCardMessage(
				teller, branchCode, strAccountTag, strAccountType, strUsage,
				strCurrency, strBranchTag, strCIFNo, strIDNumber, strIDType,
				strName, strAddress, strProduct, strAnnualFee, strVIP,
				strAddressLine2, strAddressLine3, strAddressLine4);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_95025I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_95025I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public String[] cardInquiry(String strTeller, String strBankcode,
			String cardNumber, Socket sk) throws Exception {
		String[] strReturn = new String[3];

		// String abc =
		// "*LINX|127.0.1.1||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSATMNTFNC|||||10|10|IT110004|Seabird86|95000|||||IT110004|1||*END||BA1|EBS||Seabird86|27|110||||95000|I|R|10||F||||||||||||||||||9704260000198168||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||1|||||||||||||||||||||||||||||||||||||||";
		String[] strValue = ATMMessageFactory.createCardInquiryMessage(
				strTeller, strBankcode, cardNumber);

		byte[] btHold = DSPPackager.PACKAGER_MBASE_95000I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_95000I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public String[] joinCA(Socket sk, String strSystemName, String strteller,
			String strBankcode, String transDate, String strAccount,
			String strCifnumber, String strNameAcount, String strcurrCode)
			throws Exception {
		String[] strReturn = new String[3];
		String[] strValue = MessageFactory.createJoinAccountCAMessage(
				strSystemName, strteller, strBankcode, transDate, strAccount,
				strCifnumber, strNameAcount, strcurrCode);
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}

		// byte[] btTransfer = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		//
		// // Put data to Host
		// sk.getOutputStream().write(btTransfer);
		//
		// // Get response from Host
		// byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
		// .getInputStream());
		//
		// // Check the message status
		// // DSPABCSResponse abcsResponse = new DSPABCSResponse();
		// // DSPPackager mbsdPackager =
		// abcsResponse.getABCSResponsePackager(btResponse);
		// DSPPackager mbsdPackager =
		// DSPPackager.getABCSResponsePackager(btResponse);
		//
		// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
		// .unpack(btResponse);
		//
		// String strResponse = mbsdPackager.unpack(btResponse, "|");
		//
		// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
		// System.out.println(mbsdPackager.unpack(btResponse, "\n"));
		//
		// // Control Error here
		// throw new Exception(strMsgStatus);
		// } else {
		// System.out.println(strResponse);
		// System.out.println("join Success!");
		// }
		return strReturn;
	}

	/**
	 * 
	 * @param sk
	 *            Socket
	 * @throws Exception
	 */
	public void lock(Socket sk, String strHoldId) throws Exception {
		// Create unhold message and send to Host now
		String strValueToHost = "*LINX|10.1.99.4||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDINQFNC|||||1|10|PC011014|HP|26141|N||||PC011014|1||*END||BTS|RBS||HP|27|011||||26141|I|R|1|N|F||||||C|||||||||||01101010021485|D|1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";

		String[] strValue = strValueToHost.split("\\|");
		byte[] btHold = DSPPackager.PACKAGER_MBASE_28141I.pack(strValue);

		// Sending request to bank host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_28141I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Get Error Message
			String strErrorMessage = fld[DSPMessageConstant.ERROR_POSITION]
					.unpack(btResponse);

			System.out.println(" LOCK strErrorMessage= " + strErrorMessage);
			System.out.println("When LOCK, get response from Host= \n "
					+ mbsdPackager.unpack(btResponse, "\n"));

		} else {
			// Log success
			System.out.println(" LOCK completed successfully");
			System.out.println("When LOCK, get response from Host= \n "
					+ mbsdPackager.unpack(btResponse, "\n"));
		}
	}

	public String hold(Socket sk) throws Exception {
		String strHoldId = "";
		// Create holding message to send to bank host
		// String strHold =
		// "*LINX|10.2.1.158||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDMNTFNC|||||1|10|DD020001|tinhlh|27141|N||||DD020001|1||*END||BTS|RBS||tinhlh|27|020||||27141|A|R|1|N|F|||||||||||||||||02001010011828|D||HD||||123400||000|111210||||*TRADE|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		// String strHold =
		// "*LINX|10.1.99.4||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDMNTFNC|||||1|10|IT020003|HP|27141|N||||IT020003|1||*END||BTS|RBS||HP|27|020||||27141|A|R|1|N|F|||||||||||||||||11001010000105|D||HD||||1000000000||000|101012|PHONG TOA THEO QD 235|||*EA||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		// String strHold =
		// "*LINX|10.1.99.66| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLMONEYFNC| | | | | | |IT110003|HP|3080| | | |3843|*MOSA| |10.1.99.66| | | | |T9999| ||IT110003|0018|N||||3080|021112| |VD|HP| |110|0|1|N|N|N|N|N|N|N||||||02020160060142|||||||||||||||||||||||||VND||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		// String[] strValue = strHold.split("\\|");
		// strValue =
		// MessageFactory.createHoldMessage("LINX*","MB010001","12123","010907","fdfd","dfd");
		String[] strValue = TreasuryMessageFactory.chargeCollection("DD110001",
				"011", "291112", "215", "420210001", "110000", "VND", "VND",
				"VND", "11001010000105", "110000", "100000", "10000",
				"TEST COMMENT", "0800");
		byte[] btHold = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// btResponse, DSPPackager.PACKAGER_MBASE_27141I);

		// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
		// .unpack(btResponse);
		// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
		// System.out.println("HOLD Response from Host= \n "
		// + mbsdPackager.unpack(btResponse, "\n"));
		//
		// } else {
		// strHoldId = fld[DSPMessageConstant.UNHOLD_IDX_MESSAGE_ID]
		// .unpack(btResponse);
		// System.out.println("Hold completed successfully with HoldId = "
		// + strHoldId);
		// System.out.println("Hold Response = \n "
		// + mbsdPackager.unpack(btResponse, "\n"));
		// System.out
		// .println("-----------------------------------------------------------------------------");
		//
		// }

		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

		// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		return strHoldId;
	}

	// public String holdMessage(Socket sk, String hostId, String teller,
	// String hostName, String branchCode, String accountNo, String money,
	// String comment) throws Exception {

	public String holdMessage(Socket sk) throws Exception {
		String strHoldId = "";
		// Create holding message to send to bank host
		// String strHold = "*INBK|"
		// + hostId
		// + "||||213||0200|*DSP|MBSD|*INBK||||01|||BBMBSMDMNTFNC|||||1|10|"
		// + teller
		// + "|"
		// + hostName
		// + "|97141|N||||"
		// + teller
		// + "|1||*END||BTS|RBS||"
		// + hostName
		// + "|27|"
		// + branchCode
		// + "||||97141|A|R|1|N|F|||||||||||||||||"
		// + accountNo
		// + "|D||HD||||"
		// + money
		// + "||"
		// + "000|301113|"
		// + comment
		// + "|||*EA|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
		// +
		// "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
		// +
		// "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
		// +
		// "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
		// + "|||||||||||";

		// String strHold =
		// "*LINX|10.1.1.1||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||EBANKING01|al|EB8277||||3843|*MOSA||10.1.1.1|||||T9999|||EBANKING01|70283|N||||EB8277|141111||VD|al|EBANKING02|200|0|1|N|N|N|N|N|N|N|200211111470283||200000000|||||0000000000280898010|20001010903749|||||||||0.000||200000000||||||10000000|10000000||10000000||VND|424744|||||||||||VND|VND|VND|VND|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||VND||||||||||||||20001010903749||0.000|||000|000||||||||200000000|||||||||||||||||||||||||||||||(RmNo: 200211111470283) 122000W(2TR VND) TK VN NGAN HANG NG&PTNN (AGRIBANK)- 7103205207324 - TRAN THI THIEM – CHI NHANH HUYEN MO CAY – TINH KIEN GIANG||||||||||||||||||||||RM        C                            OL2                                                                                                                                                                             SMSBK       VND                                                  CPHAM DINH TUAN                                                                                                                                                                                                                              T00000000002808980107103205207324       TRAN THI THIEM                          83204001                                   AGRIBANK BEN TRE                        HUYEN MO CAY – TINH BEN TRE                                                                                      0000000000000000000      000000000000000000000000000000000000000000000200000000";
		String strHold = "*LINX|10.1.99.141||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSATMNTFNC|||||10|10|IT110003|ngocnv-PC|95013|N||||IT110003|1||*END||BA1|EBS||ngocnv-PC|27|110||||95013|I|R|10|N|F||||||||||||||||||9704260000198192|||1|1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		System.out.println(strHold);

		String[] strValue = strHold.split("\\|");

		// byte[] btHold = DSPPackager.PACKAGER_MBASE_97141I.pack(strValue);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_95013I.pack(strValue);

		// byte[] btHold = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_95013I);
		// DSPPackager mbsdPackager = DSPPackager
		// .getABCSResponsePackager(btResponse);
		DSPField[] fld = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			strHoldId = "0";
		} else {
			strHoldId = fld[DSPMessageConstant.UNHOLD_IDX_MESSAGE_ID]
					.unpack(btResponse);

		}
		return strHoldId;
	}

	public String unholdMessage(Socket sk, String hostId, String teller,
			String hostName, String branchCode, String accountNo, String money,
			String remark, String sequence) throws Exception {

		// Create un-holding message to send to bank host
		String strUnHold = "*INBK|"
				+ hostId
				+ "||||213||0200|*DSP|MBSD|*INBK||||01|||BBMBSMDMNTFNC|||||1|10|"
				+ teller
				+ "|"
				+ hostName
				+ "|98141|N||||"
				+ teller
				+ "|1||*END||BTS|RBS||"
				+ hostName
				+ "|27|"
				+ branchCode
				+ "||||98141|C|R|1|N|F||||||C|||||||||||"
				+ accountNo
				+ "|D|"
				+ sequence
				+ "|HD|"
				+ "0000000||0000000|"
				+ money
				+ "||00|"
				+ "301113"
				+ "|"
				+ remark
				+ "|00000000000000||*EA|.00|"
				+ "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";

		System.out.println(strUnHold);

		String[] strValue = strUnHold.split("\\|");

		byte[] btHold = DSPPackager.PACKAGER_MBASE_98141I.pack(strValue);

		// Sending request to bank host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_98141I);
		DSPField[] fld = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpack(btResponse, "|"));
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			System.out.println("Unhold fails");
		} else {
			System.out.println("Unhold success");
		}

		return null;
	}

	public void unHold(Socket sk, String strHoldId) throws Exception {
		// Create unhold message and send to Host now
		String strValueToHost = "*LINX|10.1.99.4||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDMNTFNC|||||1|10|PC011014|HP|28141|N||||PC011014|1||*END||BTS|RBS||HP|27|011||||28141|D|R|1|N|F||||||C|||||||||||01101010021485|D|1|HD|0000000||0000000|1662708500||00|111111|Comment|00000000000000||*EA|.00|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		String[] strValue = strValueToHost.split("\\|");
		// strValue = MessageFactory.createUnHoldMessage("*LINX", "PC011004",
		// "02001010011828", "AAAAA", "12300", strHoldId);

		byte[] btHold = DSPPackager.PACKAGER_MBASE_28141I.pack(strValue);

		// Sending request to bank host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_28141I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Get Error Message
			String strErrorMessage = fld[DSPMessageConstant.ERROR_POSITION]
					.unpack(btResponse);
			;
			System.out.println("UNHOLD strErrorMessage= " + strErrorMessage);
			System.out.println("When Unhold, get response from Host= \n "
					+ mbsdPackager.unpack(btResponse, "\n"));
			;
		} else {
			// Log success
			System.out.println("UnHold completed successfully with hold id is "
					+ strHoldId);
			System.out.println("When Unhold, get response from Host= \n "
					+ mbsdPackager.unpack(btResponse, "\n"));
		}

	}

	public String[] createGLTransactions_BatchMessage(String teller,
			String hostName, String branchCode, String currency,
			String hostDate, Socket sk) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = TreasuryMessageFactory.createGLTransactionMessage(
				teller, hostName, branchCode, currency, hostDate);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_77113I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_77113I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpack(btResponse, "|"));// sk.close();

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}
		return strReturn;
	}

	public String[] createMiniStatementMessage(String strTeller,
			String strBankcode, String recordNumber, String accountNumber,
			String accountType, Socket sk) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createMiniStatementMessage(
				strTeller, strBankcode, recordNumber, accountNumber,
				accountType);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_25520I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_25520I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public String[] searchAccountByCifMessage(String teller, String hostName,
			String branchCode, String cifNumber, Socket sk) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchAccountByCifMessage(teller,
				hostName, branchCode, cifNumber);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_15999I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_15999I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();
		String resp = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|");
		String[] arrReturn = resp.split("\\|");
		
		String errCode = "";
		String des = "";
		int MBASE_ERROR_DESCRIPTION = 52;
		int MBASE_ERROR_CODE = 51;
		if (!arrReturn[DSPMessageConstant.IDX_MESSAGE_STATUS].equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			errCode = arrReturn[MBASE_ERROR_DESCRIPTION];
			des = arrReturn[MBASE_ERROR_CODE];

		} else {
			
		}
		

		
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public String[] searchAccountMoreByCifMessage(String teller,
			String hostName, String branchCode, String cifNumber,
			String lastAccountType, String lastAccountNumber,
			String relationShip, Socket sk) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchAccountMoreByCifMessage(
				teller, hostName, branchCode, cifNumber, lastAccountType,
				lastAccountNumber, relationShip);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_15999I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_15999I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));// sk.close();

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public String[] createDDInquiryMessage(Socket sk, String teller,
			String hostName, String branchCode, String accountNumber,
			String accountType) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchCustomerByAccountMessage(
				teller, hostName, branchCode, accountNumber, accountType);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_26161I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_26161I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	/**
	 * 
	 * @param strAccount
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public String[] searchMobileByCifMessage(String strSystemName,
			String strteller, String strBankcode, String strsearch_key,
			Socket sk) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchMobileByCifMessage("",
				strteller, strBankcode, strsearch_key);
		for (int i = 0; i < strValue.length; i++) {
			System.out.print((strValue[i] + "|"));
		}

		byte[] btHold = DSPPackager.PACKAGER_MBASE_15242I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_15242I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		String strResponse = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|");

		System.out.println(strResponse);

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			throw new Exception(strMsgStatus);
		}
		// strReturn[0] = fld[DSPMessageConstant.INQUIRY_RESPONSE_IDX_BALANCE]
		// .unpack(btResponse);
		// strReturn[1] = fld[DSPMessageConstant.INQUIRY_RESPONSE_IDX_AVAILABLE]
		// .unpack(btResponse);
		// strReturn[2] =
		// fld[DSPMessageConstant.INQUIRY_RESPONSE_IDX_OWNER_NAME]
		// .unpack(btResponse);
		// System.out.println(strReturn[0]);
		// System.out.println(strReturn[1]);
		// System.out.println(strReturn[2]);
		// System.out.println(mbsdPackager.unpack(btResponse, "\n"));

		return strReturn;
	}

	/**
	 * 
	 * @param strAccount
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public String[] queryAccount(Socket sk, String strAccount) throws Exception {
		String[] strReturn = new String[3];
		String strHold = "*LINX|10.2.2.28| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLNOMONFNC| | | | | | |DD020001|HP|1080| | | |3843|*MOSA| |10.2.2.28| | | | |T9999| ||DD020001|0028|N||||1080|220507| |V1|HP| |020|0|1|N|N|N|N|N|N|N|"
				+ strAccount
				+ "||||||||||||||||||||||||||||||VND||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		String[] strValue = strHold.split("\\|");
		strValue = MessageFactory.createCAInQuiryMessage("*LINX", "DD110001",
				strAccount, "120614");

		byte[] btHold = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
//
//		 for(int i=0; i<strValue.length;i++)
//		 System.out.printf("%02X", strValue[i]);
//		 System.out.println();
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
		// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
		// .unpack(btResponse);
		System.out.println("response:");
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));

		// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
		// // Control Error here
		// throw new Exception(strMsgStatus);
		// }

		return strReturn;
	}

	public String[] createOL2(Socket sk, String hostIP, String hostName,
			String teller, String manager, String sequence, String transDate,
			String branchCode, String fromAccount, String amount,
			String fromCif, String GLAccount, String vatFee, String serviceFee,
			String remarks, String fromName, String fromId, String toAccount,
			String toName, String toId, String toIdBranch, String toBranch, String toAddress, String toIdIssueDate,
			String toIdIssuePlace, String strTransCode, String rm_num) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createOL2MessageInterBank(hostIP, hostName,
				teller, manager, sequence, transDate, branchCode, fromAccount,
				amount, fromCif, GLAccount, vatFee, serviceFee, remarks,
				fromName, fromId, toAccount, toName, toId, toIdBranch, toBranch, toAddress,
				toIdIssueDate, toIdIssuePlace, strTransCode, rm_num);

		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		System.out.println();
		byte[] btHold = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
		// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
		// .unpack(btResponse);

		System.out.print(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));

		// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
		// // Control Error here
		// throw new Exception(strMsgStatus);
		// }

		return strReturn;
	}

	public String[] createIO5(Socket sk, String hostIP, String hostName,
			String teller, String manager, String sequence, String transDate,
			String branchCode, String toAccount, String crAmount,
			String ttAmount, String toCif, String GLAccount, String vatFee,
			String serviceFee, String buyRate, String sendRate,
			String ttSendRate, String remarks, String toName, String toId,
			String toAddress, String toIdIssueDate, String toIdIssuePlace,
			String transCode, String refId, String refWU, String sendCT,
			String receiveCT) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createIO5Message(hostIP, hostName,
				teller, manager, sequence, transDate, branchCode, toAccount,
				toName, toId, toAddress, crAmount, ttAmount, toCif, GLAccount,
				vatFee, serviceFee, buyRate, sendRate, ttSendRate, remarks,
				transCode, refId, refWU, sendCT, receiveCT, toIdIssueDate,
				toIdIssuePlace);

		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		System.out.println();
		byte[] btHold = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

		System.out.print(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));

		return strReturn;
	}

	public String[] testTF(Socket sk) throws Exception {
		String[] strReturn = new String[3];
		String strHold = "*AXIS|10.1.99.133||||||||MBSD|*AXIS||||0|||BBMBSTFMNTFNC||||||||SME-KDBL-YENLH||||||MG110111|507||*END||BTS|RBS|||98|110||||62008|C|R|1|N|F|||||||||||02102011|182004|||||110|11010010000965|1|MG110111|O|||O|Y|11010010000965||2011275|021011|2011275|021011|2011275|21011|0|0|0|0|2011275|021011|||0|0|0|0|0|0|2012284|101012|0|0|2012284|0|0|101012|vn|N|0|FS|||VND|0.00|0.00|12345678.00|0.000000000|0.000000000|0.000000000|12345678.00||0.000000000|12345678.00|2|1.0000000|VND|12345678.00|0.00|0.00||0.00|0|0.0000000|VND|0.00|0.00|0.00|NL|N|N|N|N|N|N||0|0.00|0|0||||||0|||0|||N|||110|||||||||||0.00|CP||S|0||0.000000000|0.00||0||0.000000000|0.00|S|IB|NG|123456||BL|0|0||Y|||21|2011299|261011|0|0||||||NC||||BN|||||0||0||0.00|0.00|||VND|0.00|VND|0.00|0.00||0.00||0.00|0.00|0.00|0.00|0.00||0.00||0.00|0.00|12345678.00|0.00|12345678.00|0.00|||0.00|0|0.0000000|0.00|0.00|0.00|0.00|0.00|0.00||0|0|0|0|0|VND|0|0.000000000|0.000000000||0.000000000|0.00|0|0|0|0|0||0|0.000000000|0.000000000||0.000000000|0.00|0.00|0|0.0000000|N|0||0.00||0.00|0|0.0000000|0.00|0.00|0.00|0||0|0||0|0|0|0.000000000|0.000000000||0.000000000|0||0|0|0|0|0|0|0|0.000000000|0.000000000||0.000000000|0|0|0|0|0.000000000|0.000000000||0.000000000|0|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.00||VND|12345678.00|2011275|21011||||1|||0||0||N||||||||||||0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0||0.00||0.00||0.00||0.00||0.00||0.00||0.00||0.00||0.00|110|0|0.0000000||0.00|0.00|0.00|0.00|0.00|0|0.0000000|0.00|0.00||||0|0.000000000|0.000000000||0.000000000|LC||||0|0|0||0|2|||D|0.000000000|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|0.000000000|0|||12345678.00|0.000000000|0.000000000|0.000000000|0.00|0.00||0.00||0.00||0.00||0.00||0.00|0|0|0|0|0|0|0|0.0000000|0.0000000|0.0000000";
		String[] strValue = strHold.split("\\|");
		byte[] btHold = DSPPackager.PACKAGER_MBASE_62008I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_62008I);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			throw new Exception(strMsgStatus);
		}
		// strReturn[0] = fld[DSPMessageConstant.INQUIRY_RESPONSE_IDX_BALANCE]
		// .unpack(btResponse);
		// strReturn[1] = fld[DSPMessageConstant.INQUIRY_RESPONSE_IDX_AVAILABLE]
		// .unpack(btResponse);
		// strReturn[2] =
		// fld[DSPMessageConstant.INQUIRY_RESPONSE_IDX_OWNER_NAME]
		// .unpack(btResponse);

		return strReturn;
	}

	/**
	 * 
	 * @param strAccount
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public String[] transfer(Socket sk, String branchCode,
			String creditAccount, String creditAmount, String creditCurrency,
			String creditRate, String debitAccount, String debitAmount,
			String debitCurrency, String debitRate, String manager,
			String strDescription, String strJournalSeq, String teller,
			String transDate) throws Exception {
		String[] strReturn = new String[3];
		String[] strValue = MessageFactory.createBranchTranferMessage(
				branchCode, creditAccount, creditAmount, creditCurrency,
				creditRate, debitAccount, debitAmount, debitCurrency,
				debitRate, manager, strDescription, strJournalSeq, teller,
				transDate, "1321", "0", "0");
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		System.out.println("");
		byte[] btTransfer = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btTransfer);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Transfer Success!");
		}
		return strReturn;
	}

	/**
	 * 
	 * @param strAccount
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	// public String[] rollback(Socket sk, String strSequence,
	// String strOriginSequence) throws Exception {
	// String[] strReturn = new String[3];
	// String[] strValue = MessageFactory.createRollbackTranferMessage(
	// "*LINX", "DD020009", "DD020004", "11001010000105",
	// "02001010011828", "220507", "aaa", "100000", strSequence,
	// strOriginSequence, "1322");
	// for (int i = 0; i < strValue.length; i++) {
	// System.out.println(StringUtil.nvl(strValue[i], ""));
	// }
	//
	// byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
	//
	// // Put data to Host
	// sk.getOutputStream().write(btRollback);
	//
	// // Get response from Host
	// byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
	// .getInputStream());
	//
	// // Check the message status
	// DSPPackager mbsdPackager = DSPPackager
	// .getABCSResponsePackager(btResponse);
	// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
	// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
	// .unpack(btResponse);
	// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
	// // Control Error here
	// // Error
	// System.out.println("-------------ERROR-------------------");
	// System.out.println(mbsdPackager.unpack(btResponse, "\n"));
	// throw new Exception(strMsgStatus);
	// } else {
	// System.out.println("Rollback Success!");
	// }
	// return strReturn;
	// }

	public String[] cashDeposit(Socket sk) throws Exception {
		String[] strReturn = new String[3];
		String[] strValue = "*LINX|10.1.99.119| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLMONEYFNC| | | | | | |IT110003|LM|1100| | | |3843|*MOSA| |10.1.99.119| | | | |T9999| ||IT110003|0003|N||||1100|290711| |VD|LM|DD110999|110|0|1|N|N|N|N|N|N|N|11001010000105|||10000000|||||||||||||000|000|000|10000000|||||10000000|10000000|10000000|10000000|||VND||||||||||||VND|VND|VND|VND|||||3|||||||||||||1|4|4|2|||||||||||3|4|4|4|||5||||1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||000||000|000|000|||||||10000000||||5||4|4|4|||||||2||||||||||||||||nt|||||000|000|||||||||1|1|||||||"
				.split("\\|");

		byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "|"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}
		return strReturn;
	}

	private static String constructAccountNumber(int sequenceNumber,
			String branchCode) throws Exception {
		NumberFormat format = new DecimalFormat("000000");
		String strAcountNow = format.format(sequenceNumber).toString();
		// String _currCode = getCurrCode(rinfo.getProperty("currencyCode"));
		// String strAccountNow2 = rinfo.getProperty("client_branch") +
		// rinfo.getProperty("productCode") + _currCode + strAcountNow;
		String strAccountNow2 = branchCode + "01" + "01" + strAcountNow;
		String checkDigit = calculateCheckDigit(strAccountNow2);
		return strAccountNow2 + checkDigit;
	}

	private static String calculateCheckDigit(String strCheck) throws Exception {
		int intWeight[] = { 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1 };
		char[] cArray = strCheck.toCharArray();
		float kq = 0;
		int calsum = 0, divi = 10;
		for (int i = 0; i < cArray.length; i++) {
			calsum = calsum + Integer.valueOf(String.valueOf(cArray[i]))
					* (intWeight[i]);
		}
		kq = calsum % divi;
		kq = (kq == 0) ? kq : divi - kq;
		return String.valueOf((int) kq);
	}

	public String[] createFDReceipt(Socket sk, String teller,
			String branchCode, String journalSeq, String transDate,
			String fdGroupAccount, String depositAmt, String effectiveDate,
			String fdReceipt, String rate, String intPaymentToAcctno,
			String printTranferToAcctno, String currency, String productCode,
			String autoRenew, String intPaymentMode, String fdType,
			String remark) throws Exception {

		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createFDReceiptMessage(teller,
				branchCode, journalSeq, transDate, fdGroupAccount, depositAmt,
				effectiveDate, fdReceipt, rate, intPaymentToAcctno,
				printTranferToAcctno, currency, productCode, autoRenew,
				intPaymentMode, fdType, remark);

		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	

		byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}

		return strReturn;
	}

	public String[] reversableTranferOL2(Socket sk, String hostIP,
			String hostName, String teller, String manager, String sequence,
			String oldSequence, String transDate, String branchCode,
			String fromAccount, String amount, String amountMustPay,
			String fromCif, String GLAccount, String vatFee, String serviceFee,
			String remarks, String fromName, String fromId, String toAccount,
			String toName, String toId, String toAddress, String toIdIssueDate,
			String toIdIssuePlace, String strTransCode, String refId)
			throws Exception {

		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createOL2ReservationMessage(hostIP,
				hostName, teller, manager, sequence, oldSequence, transDate,
				branchCode, fromAccount, amount, amountMustPay, fromCif,
				GLAccount, vatFee, serviceFee, remarks, fromName, fromId,
				toAccount, toName, toId, toAddress, toIdIssueDate,
				toIdIssuePlace, strTransCode, refId);

		byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		// System.out.println(new java.sql.Timestamp(new Date().getTime()));
		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// System.out.println(new java.sql.Timestamp(new Date().getTime()));
		// Check the message status
		// DSPABCSResponse response = new DSPABCSResponse();
		// DSPPackager mbsdPackager =
		// response.getABCSResponsePackager(btResponse);
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}

		return strReturn;
	}

	public String[] reversableTranfer(Socket sk, String teller, String manager,
			String branchCode, String strFromAccount, String strToAccount,
			String transDate, String strDescription, String strTranferAmount,
			String strJournalSeq, String orgJournalSeq, String strTransCode)
			throws Exception {

		String[] strReturn = new String[3];
		// String accountNumber = constructAccountNumber(290002,"060");
		// String[] strValue = MessageFactory.createCAMessage("DD020004",
		// accountNumber, "101210", "020", "VND", "212755", "CAM1", "VIP",
		// "1");

		String[] strValue = MessageFactory
				.createBranchReversableTranferMessage("*LINX", teller, manager,
						branchCode, strFromAccount, strToAccount, transDate,
						strDescription, strTranferAmount, strJournalSeq,
						orgJournalSeq, strTransCode);

		// String[] strValue = MessageFactory.createCAMessage("DD020001",
		// accountNumber, "270211", "060", "VND", "212755", "CAM1", "VIP",
		// "1");

		byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		// System.out.println(new java.sql.Timestamp(new Date().getTime()));
		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// System.out.println(new java.sql.Timestamp(new Date().getTime()));
		// Check the message status
		// DSPABCSResponse response = new DSPABCSResponse();
		// DSPPackager mbsdPackager =
		// response.getABCSResponsePackager(btResponse);
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}

		return strReturn;
	}

	public String[] createCA(Socket sk, String tellerId, int sequenceNumber,
			String hostDate, String branchCode, String currencyCode,
			String currencyType, String cifNumber, String accountType,
			String accountName, String modeOfOperation) throws Exception {

		String[] strReturn = new String[3];
		// String accountNumber = constructAccountNumber(290002,"060");
		// String[] strValue = MessageFactory.createCAMessage("DD020004",
		// accountNumber, "101210", "020", "VND", "212755", "CAM1", "VIP",
		// "1");

		String accountNumber = constructAccountNumber(sequenceNumber,
				branchCode);
		String[] strValue = MessageFactory.createCAMessage(tellerId,
				accountNumber, hostDate, branchCode, currencyType, cifNumber,
				accountType, accountName, modeOfOperation);

		// String[] strValue = MessageFactory.createCAMessage("DD020001",
		// accountNumber, "270211", "060", "VND", "212755", "CAM1", "VIP",
		// "1");

		byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		// System.out.println(new java.sql.Timestamp(new Date().getTime()));
		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// System.out.println(new java.sql.Timestamp(new Date().getTime()));
		// Check the message status
		// DSPABCSResponse response = new DSPABCSResponse();
		// DSPPackager mbsdPackager =
		// response.getABCSResponsePackager(btResponse);
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			// System.out.println(btResponse.length);
			// for (int i = 0; i < fld.length; i++) {
			// System.out.print("i:" + i + " - " + btResponse + " -");
			// System.out.println(fld[i].unpack(btResponse));
			// }

			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}

		return strReturn;
	}

	public String[] searchCustomerByCif(Socket sk, String tellerId,
			String hostName, String branchCode, String cifNumber)
			throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchCustomerByCifMessage(tellerId,
				hostName, branchCode, cifNumber);

		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		byte[] btRollback = DSPPackager.PACKAGER_MBASE_15103I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_15103I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		sk.close();
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "|"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}

		System.out.println(mbsdPackager.unpack(btResponse, "|"));

		// strReturn[0] = fld[67].unpack(btResponse);
		// strReturn[1] = fld[69].unpack(btResponse);
		// strReturn[2] = fld[70].unpack(btResponse);

		// for (int i = 0; i < strReturn.length; i++) {
		// System.out.println(strReturn[i]);
		// }
		return strReturn;
	}

	public String[] searchCustomerByID(Socket sk, String tellerId,
			String hostName, String branchCode, String IdNumber)
			throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchCustomerByIDMessage(tellerId,
				hostName, branchCode, IdNumber);

		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}

		byte[] btRollback = DSPPackager.PACKAGER_MBASE_15104I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_15104I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
		}

		// strReturn[0] = fld[68].unpack(btResponse);
		// strReturn[1] = fld[69].unpack(btResponse);
		// strReturn[2] = fld[70].unpack(btResponse);
		//
		// for (int i = 0; i < strReturn.length; i++) {
		// System.out.println(strReturn[i]);
		// }
		// strReturn[0] = fld[67].unpack(btResponse);

		return strReturn;
	}

	public String[] loanAccountInquiry(Socket sk, String tellerId,
			String hostName, String branchCode, String accountNumber)
			throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchLoanAccountMessage(tellerId,
				hostName, branchCode, accountNumber);

		byte[] btRollback = DSPPackager.PACKAGER_MBASE_85800I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_85800I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
		} else {
			System.out.println("Rollback Success!");
		}

		return strReturn;
	}

	public void searchCustomerInformationByID(Socket sk, String tellerId,
			String hostName, String branchCode, String IdNumber)
			throws Exception {

		 String[] strValue = MessageFactory
		 .searchCustomerInformationByCifMessage(tellerId, hostName,
		 branchCode, IdNumber);

//		String[] strValue = "*LINX|10.1.99.118||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSCFINQFNC|||||1|10|PD110001|HP|16106|N||||PD110001|1||*END||BTS|RBS||HP|27|110||||16106|I|R|1|N|F||||||C|||||||||839327||839327||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
//				.split("\\|");

		byte[] btRollback = DSPPackager.PACKAGER_MBASE_16106I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_16106I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			System.out.println("-------------ERROR-------------------");
			throw new Exception(strMsgStatus);
		}

	}

	public String[] createCif(Socket sk, String teller, String hostName,
			String branchCode, String IdNumber, String strIdType,
			String strIdDateIssued, String strIdCountryIssued,
			String strIdPlaceIssued, String strSurname, String strAfterSurname,
			String strAddressLine1, String strAddressLine2,
			String strAddressLine3, String strAddressLine4_codeCheckCust,
			String strAddressLine5, String strNationality, String strBirthday,
			String strRaceCode, String strMaritalStatus, String strGender,
			String _2strOccupationCode, String strPlaceOfBirth,
			String strTypeElectronicAddress1, String strTypeElectronicAddress2,
			String strTypeElectronicAddress3, String strElectronicAddress1,
			String strElectronicAddress2, String strElectronicAddress3,
			String hostDate, String strBirthday_Full,
			String strIdDateIssued_Full, String _1strOccupationCode,
			String province) throws Exception {

		String[] strReturn = new String[3];

		// String[] strValue = MessageFactory.createCifMessage(teller, hostName,
		// branchCode, IdNumber, strIdType, strIdDateIssued,
		// strIdCountryIssued, strIdPlaceIssued, strSurname,
		// strAfterSurname, strAddressLine1, strAddressLine2,
		// strAddressLine3, strAddressLine4_codeCheckCust,
		// strAddressLine5, strNationality, strBirthday, strRaceCode,
		// strMaritalStatus, strGender, _2strOccupationCode,
		// strPlaceOfBirth, strTypeElectronicAddress1,
		// strTypeElectronicAddress2, strTypeElectronicAddress3,
		// strElectronicAddress1, strElectronicAddress2,
		// strElectronicAddress3, hostDate, strBirthday_Full,
		// strIdDateIssued_Full, _1strOccupationCode, province);

		String[] strValue = MessageFactory.createCifCorpMessage(teller,
				hostName, branchCode, IdNumber, strIdType, strIdDateIssued,
				strIdCountryIssued, strIdPlaceIssued, strSurname,
				strAfterSurname, strAddressLine1, strAddressLine2,
				strAddressLine3, strAddressLine4_codeCheckCust,
				strAddressLine5, strNationality, strBirthday, strRaceCode,
				strMaritalStatus, strGender, _2strOccupationCode,
				strPlaceOfBirth, strTypeElectronicAddress1,
				strTypeElectronicAddress2, strTypeElectronicAddress3,
				strElectronicAddress1, strElectronicAddress2,
				strElectronicAddress3, hostDate, strBirthday_Full,
				strIdDateIssued_Full, _1strOccupationCode, province);

		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();
		byte[] btRollback = DSPPackager.PACKAGER_MBASE_17625I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_17625I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			throw new Exception(strMsgStatus);
		}

		// strReturn[0] = fld[68].unpack(btResponse);
		// strReturn[1] = fld[69].unpack(btResponse);
		// strReturn[2] = fld[70].unpack(btResponse);

		// for (int i = 0; i < fld.length; i++) {
		// System.out.println(fld[i].unpack(btResponse));
		// }
		return strReturn;
	}

	public boolean lockCif(Socket sk, String tellerId, String sourceCif,
			String branchCode, String accountToMove) throws Exception {
		boolean isOK = false;

		// String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createLockTranferCifMessage(
				tellerId, branchCode, sourceCif, accountToMove);

		// for(int i=0;i<strValue.length;i++){
		// System.out.println(strValue[i]);
		// }
		byte[] btRollback = DSPPackager.PACKAGER_MBASE_16122I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_16122I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "\n"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
			isOK = true;
		}

		// System.out.println("Starting ... ");
		// for (int i = 0; i < fld.length; i++) {
		// System.out.print("i:"+i+" - "+btResponse+" -" );
		// System.out.println(fld[i].unpack(btResponse));
		// }
		//
		// System.out.println("End.");
		// strReturn[0] = fld[68].unpack(btResponse);
		// strReturn[1] = fld[69].unpack(btResponse);
		// strReturn[2] = fld[70].unpack(btResponse);
		//
		// for (int i = 0; i < strReturn.length; i++) {
		// System.out.println(strReturn[i]);
		// }
		return isOK;
	}

	public boolean tranferAccountFromCifToAnother(Socket sk, String tellerId,
			String sourceCif, String branchCode, String accountToMove,
			String destinationCif, String destinationName) throws Exception {
		// String[] strReturn = new String[3];
		boolean isOK = false;

		String[] strValue = MessageFactory
				.createTranferFromCifToAnotherMessage(tellerId, branchCode,
						sourceCif, accountToMove, destinationCif,
						destinationName);

		byte[] btRollback = DSPPackager.PACKAGER_MBASE_18122I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		DSPPackager.PACKAGER_MBASE_18102I.pack(strValue);
		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_18122I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "\n"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
			isOK = false;
		}

		// System.out.println("Starting ... ");
		// for (int i = 0; i < fld.length; i++) {
		// System.out.println(fld[i].unpack(btResponse));
		// }
		// System.out.println("End");
		return isOK;
	}

	public boolean lockUpdateIntroducerCodeInCA(Socket sk, String tellerId,
			String branchCode, String accountNumber) throws Exception {
		boolean isOK = false;

		// String[] strReturn = new String[3];

		String[] strValue = MessageFactory
				.createLockUpdatingIntroducerCodeInCAMessage(tellerId,
						branchCode, accountNumber);

		// for(int i=0;i<strValue.length;i++){
		// System.out.println(strValue[i]);
		// }
		byte[] btRollback = DSPPackager.PACKAGER_MBASE_26901I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_26901I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "\n"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
			isOK = true;
		}

		// System.out.println("Starting ... ");
		// for (int i = 0; i < fld.length; i++) {
		// System.out.print("i:"+i+" - "+btResponse+" -" );
		// System.out.println(fld[i].unpack(btResponse));
		// }
		//
		// System.out.println("End.");
		// strReturn[0] = fld[68].unpack(btResponse);
		// strReturn[1] = fld[69].unpack(btResponse);
		// strReturn[2] = fld[70].unpack(btResponse);
		//
		// for (int i = 0; i < strReturn.length; i++) {
		// System.out.println(strReturn[i]);
		// }
		return isOK;
	}

	// public boolean updateIntroducerCodeInCA(Socket sk, String tellerId,
	// String introducerCode, String branchCode, String accountNo, String
	// expense)
	// throws Exception {
	// boolean isOK = false;
	//
	// String[] strValue = MessageFactory
	// .createUpdatingIntroducerCodeInCAMessage(tellerId, branchCode,
	// accountNo, introducerCode,expense);
	//
	// byte[] btRollback = DSPPackager.PACKAGER_MBASE_28901I.pack(strValue);
	//
	// // Put data to Host
	// sk.getOutputStream().write(btRollback);
	//
	// // Get response from Host
	// byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
	// .getInputStream());
	//
	// // Check the message status
	// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
	// btResponse, DSPPackager.PACKAGER_MBASE_28901I);
	//
	// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
	// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
	// .unpack(btResponse);
	// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
	// // Control Error here
	// // Error
	// System.out.println("-------------ERROR-------------------");
	// System.out.println(mbsdPackager.unpack(btResponse, "\n"));
	// throw new Exception(strMsgStatus);
	// } else {
	// System.out.println("Rollback Success!");
	// isOK = true;
	// }
	//
	// System.out.println(mbsdPackager.unpack(btResponse, "\n"));
	//
	// return isOK;
	// }

	public boolean lockUpdateIntroducerCodeInNewCA(Socket sk, String tellerId,
			String branchCode, String accountNumber) throws Exception {
		boolean isOK = false;

		// String[] strReturn = new String[3];

		String[] strValue = MessageFactory
				.createLockUpdatingIntroducerCodeInNewCAMessage(tellerId,
						branchCode, accountNumber);

		// for(int i=0;i<strValue.length;i++){
		// System.out.println(strValue[i]);
		// }
		byte[] btRollback = DSPPackager.PACKAGER_MBASE_26110I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_26110I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "\n"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
			isOK = true;
		}

		// System.out.println("Starting ... ");
		// for (int i = 0; i < fld.length; i++) {
		// System.out.print("i:"+i+" - "+btResponse+" -" );
		// System.out.println(fld[i].unpack(btResponse));
		// }
		//
		// System.out.println("End.");
		// strReturn[0] = fld[68].unpack(btResponse);
		// strReturn[1] = fld[69].unpack(btResponse);
		// strReturn[2] = fld[70].unpack(btResponse);
		//
		// for (int i = 0; i < strReturn.length; i++) {
		// System.out.println(strReturn[i]);
		// }
		return isOK;
	}

	// public boolean updateIntroducerCodeInNewCA(Socket sk, String tellerId,
	// String introducerCode, String branchCode, String accountNo,String
	// expense)
	// throws Exception {
	// boolean isOk = false;
	//
	// // String[] strReturn = new String[3];
	//
	// String[] strValue = MessageFactory
	// .createUpdatingIntroducerCodeInNewCAMessage(tellerId,
	// branchCode, accountNo, introducerCode, expense);
	//
	// byte[] btRollback = DSPPackager.PACKAGER_MBASE_28110I.pack(strValue);
	//
	// // Put data to Host
	// sk.getOutputStream().write(btRollback);
	//
	// // Get response from Host
	// byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
	// .getInputStream());
	//
	// // Check the message status
	// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
	// btResponse, DSPPackager.PACKAGER_MBASE_28110I);
	//
	// DSPField fld[] = mbsdPackager.getFieldDefinitionList();
	// String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
	// .unpack(btResponse);
	// if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
	// // Control Error here
	// // Error
	// System.out.println("-------------ERROR-------------------");
	// System.out.println(mbsdPackager.unpack(btResponse, "\n"));
	// throw new Exception(strMsgStatus);
	// } else {
	// System.out.println("Rollback Success!");
	// isOk = true;
	// }
	//
	// // System.out.println("Starting ... ");
	// // for (int i = 0; i < fld.length; i++) {
	// // System.out.println("i:" + i + " - " + btResponse + " -");
	// // System.out.println(fld[i].unpack(btResponse));
	// // }
	// // System.out.println("End.");
	//
	// return isOk;
	// }

	public boolean linkCard(Socket sk, String tellerId, String branchCode,
			String strAccountTag, String cardNumber, String accountType,
			String usage, String currency, String branchTag, String cifNo,
			String idNumber, String idType, String name, String address,
			String product, String annualFree, String vip, String address2,
			String address3, String address4) throws Exception {

		boolean isOk = false;
		// String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createLinkCardMessage(tellerId,
				branchCode, cardNumber, strAccountTag, accountType, usage,
				currency, branchTag, cifNo, idNumber, idType, name, address,
				product, annualFree, vip, address2, address3, address4);
		// strValue =
		// "*LINX|10.1.99.4||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSATMNTFNC||||||10|IT020003|HP|99000|N||||IT020003|1||*END||BA1|EBS||HP|27|020||||99000|A|R||N|F|||||||||||||||||||1||||||||||||||||||||||02001010404831|D|F|VND|00020|00||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||N||||||||||||||||0000000000000212999|141020000|IP|||||NGUYEN VAN  C|NGUYEN VAN  C|NGUYEN VAN  C||N||AAA|||||||||||||||||||||||00020||||||||||||||||1||||||FGDB|Y||YAAAA||||||||||||||||||||||||||||||".split("\\|");
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}

		byte[] btRollback = DSPPackager.PACKAGER_MBASE_99000I.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btRollback);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_99000I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			// Error
			System.out.println("-------------ERROR-------------------");
			System.out.println(mbsdPackager.unpack(btResponse, "|"));
			throw new Exception(strMsgStatus);
		} else {
			System.out.println("Rollback Success!");
			isOk = true;
		}

		// System.out.println("Starting ... ");
		// for (int i = 0; i < fld.length; i++) {
		// System.out.println("i:" + i + " - " + btResponse + " -");
		// System.out.println(fld[i].unpack(btResponse));
		// }
		// System.out.println("End.");

		return isOk;
	}

	public static void main(String[] args) {
		try {
			new TestConnection();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// private void closeSocket(Socket sk) {
	// try {
	// sk.shutdownOutput();
	// sk.shutdownInput();
	// sk.close();
	// } catch (Exception exs) {
	// exs.printStackTrace();
	// }
	// }

	// private void clearMap() {
	// try {
	// // DSPPackager.mapBody.clear();
	// DSPPackager.mapDSPPackager.clear();
	// // DSPPackager.mapHeader.clear();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }lic

	public static String[] transferGl2Sa(Socket sk, String teller,
			String manager, String branchCode, String journalSeq,
			String transDate, String effectiveDate, String creditAmount, String creditAcc, String debitAmount,
			String debitAcc, String creditRate, String debitRate,
			String adviceNumber, String creditCurrency, String debitCurrency,
			String description, String transCode) {

		String[] hostMsg = MessageFactory.createTranferGL2SaMessage(teller,
				manager, branchCode, journalSeq, transDate,effectiveDate, creditAmount, creditAcc,
				debitAmount, debitAcc, creditRate, debitRate, adviceNumber,
				creditCurrency, debitCurrency, description, transCode);

		String strReturn = "";
		try {

			byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(hostMsg);

			// Put data to Host
			sk.getOutputStream().write(btRollback);

			// Get response from Host
			byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
					.getInputStream());

			// Check the message status
			DSPPackager mbsdPackager = DSPPackager
					.getABCSResponsePackager(btResponse);

			DSPField fld[] = mbsdPackager.getFieldDefinitionList();
			String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
					.unpack(btResponse);
			strReturn = mbsdPackager.unpackMultiRecords(btResponse,
					DSPMessageConstant.ABCS_NAME, "|");
			System.out.println(strMsgStatus);
			System.out.println(strReturn);
			return strReturn.split("\\|");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return strReturn.split("\\|");
			// throw ex;
		} 

	}

	

	public static String[] transferSa2GL(Socket sk, String teller,
			String manager, String branchCode, String journalSeq,
			String transDate, String effectiveDate,String creditAmount, String creditAcc, String debitAmount,
			String debitAcc, String creditRate, String debitRate,
			String adviceNumber, String creditCurrency, String debitCurrency,
			String description, String transCode) {

		String[] hostMsg = MessageFactory.createTranferSA2GLMessage( teller,
																	 manager, 
																	 branchCode,
																	 journalSeq, 
																	 transDate,
																	 effectiveDate,
																	 creditAmount,
																	 creditAcc,
																	 debitAmount, 
																	 debitAcc,
																	 creditRate, 
																	 debitRate,
																	 adviceNumber,
																	 creditCurrency,
																	 debitCurrency, 
																	 description, transCode);
		

		String strReturn = "";
		try {

			byte[] btRollback = DSPPackager.PACKAGER_ABCS_REQUEST.pack(hostMsg);

			// Put data to Host
			sk.getOutputStream().write(btRollback);

			// Get response from Host
			byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
					.getInputStream());

			// Check the message status
			DSPPackager mbsdPackager = DSPPackager
					.getABCSResponsePackager(btResponse);

			DSPField fld[] = mbsdPackager.getFieldDefinitionList();
			String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
					.unpack(btResponse);
			strReturn = mbsdPackager.unpackMultiRecords(btResponse,
					DSPMessageConstant.ABCS_NAME, "|");
			System.out.println(strMsgStatus);
			System.out.println(strReturn);
			return strReturn.split("\\|");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return strReturn.split("\\|");
			// throw ex;
		} 

	}
	
	public String[] createInquiryAccountForOverdraftMessage(Socket sk, String teller,
			String hostName, String branchCode, String accountNumber,
			String accountType) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchAccountInfoForOverdraft(
				teller, hostName, branchCode, accountNumber, accountType);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_26051I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_26051I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}
	
	public String[] createODPTierMaintainMessage(Socket sk, String teller,
			String hostName, String branchCode, String accountNumber,
			String accountType, String drawLimit, String authLimit, 
			String odRate, String argDate, String expDate, String rateVariance, String rateCode, String rateFloor, String rateCeiling) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.createODPTierMaintain(teller, hostName, branchCode, accountNumber, accountType, drawLimit, authLimit,
				odRate, argDate, expDate, rateVariance, rateCode, rateFloor, rateCeiling);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_27050I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_27050I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}
	
	public String[] searchAALoanByCifNo(Socket sk, String teller,
			String hostName, String branchCode, String cifNo) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchAALoanByCifNo(teller, hostName, branchCode, cifNo);
		
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		
		System.out.println("response:");
		
		byte[] btHold = DSPPackager.PACKAGER_MBASE_83301I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_83301I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}
	
	public String[] searchAllFacilityLoan(Socket sk, String teller,
			String hostName, String branchCode, String appNo) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchAllFacilityLoan(teller, hostName, branchCode, appNo);
		
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		
		System.out.println("response:");
		
		byte[] btHold = DSPPackager.PACKAGER_MBASE_85303I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_85303I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}
		
		String[] arrOut = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|").split("\\|");
		return strReturn;
	}
	
	public String[] searchDetailFacilityLoan(Socket sk, String teller,
			String hostName, String branchCode, String appNo, String facilityNo, String seq) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.lockFacilityLoan(teller, hostName, branchCode, appNo, facilityNo, seq);
		
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		
		System.out.println("response:");
		
		byte[] btHold = DSPPackager.PACKAGER_MBASE_86303I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_86303I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}
		
		return strReturn;
	}
	
	
	public String[] unlockFacilityLoan(Socket sk, String teller,
			String hostName, String branchCode, String record) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.unlockFacilityLoan(teller, hostName, branchCode, record);
		
		for (int i = 0; i < strValue.length; i++) {
			System.out.print(strValue[i] + "|");
		}
		
		System.out.println("response:");
		
		byte[] btHold = DSPPackager.PACKAGER_MBASE_88902I.pack(strValue);
		// Date now = new Date();
		// System.out.println("Start" + new java.sql.Timestamp(now.getTime()));
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());
		// Date now1 = new Date();
		// System.out.println("End." + new java.sql.Timestamp(now1.getTime()));

		// System.out.println(StringUtil.byteArrayToHexString(btResponse).substring(72));
		// System.out.println(StringUtil.byteArrayToHexString(btResponse));

		// String hexString =
		// StringUtil.byteArrayToHexString(btResponse).substring(68);

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
				btResponse, DSPPackager.PACKAGER_MBASE_88902I);
		// DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(
		// StringUtil.hexStringToByteArray(hexString),
		// DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// System.out.println(strResponse.split("\\|"));
			// Control Error here
			throw new Exception(strMsgStatus);
		}
		
		return strReturn;
	}
	
	


}
