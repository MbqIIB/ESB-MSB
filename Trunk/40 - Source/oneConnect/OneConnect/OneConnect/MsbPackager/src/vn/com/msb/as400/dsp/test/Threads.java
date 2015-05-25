package vn.com.msb.as400.dsp.test;

import java.net.Socket;

public class Threads extends Thread {

	public void run() {
		// System.out.println(msg);
//		Socket sk = HostUtils.getSocket(6600);//DAI MODE
		Socket sk = HostUtils.getSocket(4115);//NIGHT MODE
		Function247 _247 = new Function247();
		//String receiptNumber;
		try {
			// int i = 1;
//			 for(int i = 1; i<100;i++){//
			// System.out.println("Query:"+ 1);
	/*	 _247.queryAccount(sk, "11001010000105", "280113");
			// System.out.println("Transfer:"+ 4);
		 
			 _247.branchTransfer(sk, "11001010000105", "02001019001103",
					 "999999900",String.valueOf(21),"280113");
					 */
			 
			 // 
			// 02001019001103
			 //11001010000105
			 //03001010237643
		 
			// System.out.println("Transfer:"+ 2);
			// _247.branchTransfer(sk, "11001010019620", "03001013988889",
			// "200000",String.valueOf(2),"270912");
			// System.out.println("Transfer:"+ 3);
			// _247.branchTransfer(sk, "11001010019620", "03001013988889",
			// "300000",String.valueOf(3),"270912");
			//

			// System.out.println("Hold:"+ 1);
			// _247.holdAccount(sk,"11001010019620","20000000");
			// System.out.println("UnHold:"+ 1);
			// _247.unholdMessage(sk, "10.1.99.118", "EBANKING01", "EBANK",
			// "011", "11001010019620", "5000000", "Unhold PROD", "2");
			// System.out.println("FD Account:")
			// get account FD
//			String accountFDNo = AccountSetting.constructFDAccountNumber(sequenceNumber, "110", "01", "05");
			
			// tao tai khoan FD
			//02005010900265
			
//			 _247.createFDMesssage(sk, "02005010900265", "3698",
//			 "CTY CP VAN TAI BIEN", "VND");
			 
			 
			System.out.println("FD receipt:");
//			receiptNumber = Snippet.constructAccountNumber(9990092 + 38, "01",					"110", "1");
//			System.out.println("FD receipt:" + receiptNumber);
		//	receiptNumber = "02020100902565";// "02020100902501";02020100902499;02020100902565
//			_247.createFDReceiptMesssage(sk, "DD110001", "3000", "110",
//					"280113", "1", "02005010900265", "200000000",
//					receiptNumber, "VND", "FC030TOP", "Test IAT");
			//02020100902556
			// //3080 - FD inquiry for settlement
			 
//			 System.out.println("Settlement Inquiry: ");
			
			//. Truyen vao receiptNo = 02020100902499,02020100902501 
			 String[] strList =
			 _247.createFdInquirySettementMessage(sk,"11010100900213","110213","IT110003","200","3080");
////			
			 
			 // 3320 - Settlement FD account
			 String serial = strList[171];
		     System.out.println("Settlement: " + serial);
			 String amt = strList[173];
		     System.out.println("Settlement: " + amt);
			 
			 _247.createFdSettementMessage(sk,"IT110003","11010100900213","110213","2","3320","110",
			
			  "DD110001",amt,"11001010888899","0","0","0",serial,"1.00","1.00","VND","VND","Test IAT");
			 
			 
			 
			// 3120 - Tranfer to FD account
			// System.out.println("Transfer: ");//11010199901060
			// _247.createTransferFromCaToFdMessage(sk,"IT110003",receiptNumber,"200113","6","3120","110",
			// "DD110001","200000000","11001010003384","0","0","1.00","1.00","VND","VND","Test IAT"
			// );
			//
			 
			// 3603 - topup CA to FD - OV
//			 receiptNumber = "11005010100057";
			
//			_247.createTransferFromCaToFdMessage(sk, "IT110003", receiptNumber,
//					"280113", "10", "3120", "110", "DD110001", "200000000",
//					"11001010000105", "0", "0", "10000000", "10000000", "VND", "VND",
//					"Test IAT 3120");
			
//			receiptNumber = "11010100900213";
//			_247.createTransferFromCaToFdMessage(sk, "IT110003", receiptNumber,
//					"110213", "103", "3120", "110", "DD110001", "210000000",
//					"11001010888899", "000", "000", "10000000", "10000000", "VND", "VND",
//					"Test IAT 3120");
//			
//			_247.createTransferFromCaToFdConfirmMessage(sk, "IT110003", receiptNumber,
//			"110213", "104", "3120", "110", "DD110001", "210000000",
//			"11001010888899", "000", "000", "10000000", "10000000", "VND", "VND",
//			"CONFIRM Test IAT 3120");
			
			
//			_247.createTransferFromCaToFdMessage(sk, "IT110003", receiptNumber,
//					"171212", "10", "3603", "110", "DD110001", "200000000",
//					"11001010000105", "000", "000", "10000000", "10000000", "VND", "VND",
//					"Test IAT 3603");
//			_247.createTransferFromCaToFdMessage(sk, "DD110001", receiptNumber,
//					"250113", "101", "3603", "110", "DD110002", "200000000",
//					"11001010172794", "000", "000", "10000000", "10000000", "VND", "VND",
//					"Test IAT 3603");
//			_247.createTransferFromCaToFdConfirmMessage(sk, "DD110001", receiptNumber,
//					"250113", "102", "3603", "110", "DD110002", "200000000",
//					"11001010172794", "000", "000", "10000000", "10000000", "VND", "VND",
//					"Test IAT 3603");
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HostUtils.releaseSocket(sk);
		}
	}

}
