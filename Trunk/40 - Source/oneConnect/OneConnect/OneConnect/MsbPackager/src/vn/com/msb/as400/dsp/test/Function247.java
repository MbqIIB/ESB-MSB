package vn.com.msb.as400.dsp.test;

import java.net.Socket;

import vn.com.msb.as400.dsp.BDS24Factory;
import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPMessageConstant;
import vn.com.msb.as400.dsp.DSPPackager;

public class Function247 {
	
	public void queryAccount(Socket sk, String strAccount,String hostDate) throws Exception {
		String[] strValue = BDS24Factory.createCAInQuiryMessage("IT110003",strAccount, hostDate,"1080","1");
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));
		
//		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
//			// Control Error here
//			throw new Exception(strMsgStatus);
//		}
		
	}
	
	public void holdAccount(Socket sk, String strAccount, String amount) throws Exception {
		String[] strValue = BDS24Factory.createHoldMessage("*INBK","EBANKING01", strAccount, "311212","Hold PROD 311212", amount);
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_MBASE_97141I.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(btResponse, DSPPackager.PACKAGER_MBASE_27141I);
		
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.MBSD_NAME, "|"));
		
//		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
//			// Control Error here
//			throw new Exception(strMsgStatus);
//		}
		
	}
	
	public void branchTransfer(Socket sk, String toAccount, String fromAccount, String amount, String sequence, String hostDate) throws Exception{
		String[] strValue = BDS24Factory.createBranchTranferMessage("011", fromAccount, amount, "VND", "10000000", toAccount,amount,"VND", "10000000", "EBANKING01", "Chuyen tien qua PROD",sequence,"EBANKING02",hostDate,"EB1321", "0", "0");
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,DSPMessageConstant.ABCS_NAME,
				 "|"));
		
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			// Control Error here
			throw new Exception(strMsgStatus);
		}
	}
	
	public void createFDMesssage(Socket sk, String account, String cifNumber, String cifName, String currencyType) throws Exception{
		String[] strValue = BDS24Factory.createFDMessage("DD110001", "110", "080213", account, cifNumber, cifName, currencyType, "1");
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));
		
//		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
//			// Control Error here
//			throw new Exception(strMsgStatus);
//		}
	}
	
	public void createFDReceiptMesssage(Socket sk, String strteller, String transCode, String strBankcode, String transDate,
			String sequence, String fdAccount, String amount, String receipt, String receipt_currency, String product_code, String remark) throws Exception{
		
		String[] strValue = BDS24Factory.createFDReceiptMessage(strteller, transCode, strBankcode, transDate, sequence, fdAccount, amount, receipt, receipt_currency, product_code, remark);
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));
		
//		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
//			// Control Error here
//			throw new Exception(strMsgStatus);
//		}
	}
	
	public boolean isConnected(Socket sk) throws Exception {
		return sk.isConnected();
	}
	
	public void withDrawMessage(Socket sk, String strAccount, String amount, String transDate, String teller, String seq, String desciption, String transCode) throws Exception {
		String[] strValue = BDS24Factory.createBranchWithdrawMessage("*INBK", teller, strAccount, transDate, desciption, amount, seq, transCode);
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|"));
		
//		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
//			// Control Error here
//			throw new Exception(strMsgStatus);
//		}
		
	}

	public String[] createFdSettementMessage (Socket sk, String teller, String fdAccount, String transDate,
			String strJournalSeq, String strTransCode, String branchCode,
			String manager, String amount, String account, String fee,
			String vatFee, String totalFee, String serial, String buy_rate,
			String receive_sell_rate, String receipt_ccy,
			String rollin_acc_ccy, String remark) throws Exception {
		String[] strValue = BDS24Factory.createFdSettementMessage("*INBK", teller, fdAccount, transDate, strJournalSeq, strTransCode, branchCode,
				manager, amount, account, fee,
				vatFee, totalFee, serial, buy_rate,
				receive_sell_rate, receipt_ccy,
				rollin_acc_ccy, remark);
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);
		String strBack = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|");
		System.out.println(strBack);
		return strBack.split("\\|");
		
	}
	
	public String[] createFdInquirySettementMessage (Socket sk, String fdAccount, String transDate, String teller, String seq,String transCode) throws Exception {
		String[] strValue = BDS24Factory.createFdInquirySettementMessage("*INBK", teller, fdAccount, transDate, seq, transCode);
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);
		String strBack = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|");
		System.out.println(strBack);
		return strBack.split("\\|");
		
	}
	
	public String[] createTransferFromCaToFdMessage (Socket sk, String teller, String fdAccount,
			String transDate, String strJournalSeq, String strTransCode, String branchCode,String manager, String amount, String account, String fee,
			String vatFee, String buy_rate,
			String sell_rate, String receipt_ccy,
			String rollin_acc_ccy, String remark) throws Exception {
		String[] strValue = BDS24Factory.createTransferFromCaToFdMessage("*INBK", teller, fdAccount,
				transDate, strJournalSeq, strTransCode, branchCode,manager, amount, account, fee,
				vatFee, buy_rate,
				sell_rate, receipt_ccy,
				rollin_acc_ccy, remark);
		//a
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);
		String strBack = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|");
		System.out.println(strBack);
		return strBack.split("\\|");
		
	}
	
	public String[] createTransferFromCaToFdConfirmMessage (Socket sk, String teller, String fdAccount,
			String transDate, String strJournalSeq, String strTransCode, String branchCode,String manager, String amount, String account, String fee,
			String vatFee, String buy_rate,
			String sell_rate, String receipt_ccy,
			String rollin_acc_ccy, String remark) throws Exception {
		String[] strValue = BDS24Factory.createTransferFromCaToFdMConfirmMessage("*INBK", teller, fdAccount,
				transDate, strJournalSeq, strTransCode, branchCode,manager, amount, account, fee,
				vatFee, buy_rate,
				sell_rate, receipt_ccy,
				rollin_acc_ccy, remark);
		
		for(int i = 0; i< strValue.length;i++){
			System.out.print(strValue[i]);
			System.out.print("|");
		}
		System.out.println();	
		
		byte[] byteRequest = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);
		
		sk.getOutputStream().write(byteRequest);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);
//		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
//		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
//				.unpack(btResponse);
		String strBack = mbsdPackager.unpackMultiRecords(btResponse,
				DSPMessageConstant.ABCS_NAME, "|");
		System.out.println(strBack);
		return strBack.split("\\|");
		
	}
	
	public void createOL2(Socket sk, String hostIP, String hostName,
			String teller, String manager, String sequence, String transDate,
			String branchCode, String fromAccount, String amount,
			String fromCif, String GLAccount, String vatFee, String serviceFee,
			String remarks, String fromName, String fromId, String toAccount,
			String toName, String toId, String toAddress, String toIdIssueDate,
			String toIdIssuePlace, String strTransCode, String refId) throws Exception {

		String[] strValue = BDS24Factory.createOL2Message(hostIP, hostName,
				teller, manager, sequence, transDate, branchCode, fromAccount,
				amount, fromCif, GLAccount, vatFee, serviceFee, remarks,
				fromName, fromId, toAccount, toName, toId, toAddress,
				toIdIssueDate, toIdIssuePlace, strTransCode, refId);
		strValue = "*LINX|10.1.1.1||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||EBANKING01|al|EB8277||||3843|*MOSA||10.1.1.1|||||T9999|||EBANKING01|84449|N||||EB8277|070113||VD|al|EBANKING02|110|0|1|N|N|N|N|N|N|N|11021301070723||1205110300|||||0000000000280898009|11001010003384|||||||   |   |770000||1205110300||||        ||10000000|10000000||10000000||VND|120787|||||||||||VND|VND|VND|VND|||||||||||||||||| | | || |||||||||||||| | | |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||VND||||||||||||||11001010003384||770000|||700000|70000||||||||1205110300|||||||||||||||||||||||||||||||(RmNo: 11021301070723) TEST C247||||||||||||||||||||||RM        C                            OL2                                                                                                                                                                             SMSBK       VND                                                  CNGUYEN TUAN NGOC                                                                                                                                                                                                                            T00000000002808980090123456789          NGUYEN TUAN NGOC                        01617001                                   HSBC HA NOI (HN)                                                                                                                                         0000000000000000000      000000000000000000000000000000000000000000001205110300".split("\\|");                                                                                                                                                            

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
}
