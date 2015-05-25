package vn.com.msb.as400.dsp.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Test247Connection {

	public static void main(String[] args) throws Exception {

		Thread t1 = new Threads();		
//		Thread t2 = new Threads();
//		Thread t3 = new Threads();		
//		Thread t4 = new Threads();
//		Thread t5 = new Threads();		
		
		t1.run();			
//		t2.run();
//		t3.run();			
//		t4.run();
//		t5.run();			
		Socket sk;
		try {
			sk = new Socket("10.2.1.81",4115);
			
			System.out.println(sk.isConnected());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(Snippet.constructAccountNumber(90327,"01","110","05"));
	}
	
	
	public Test247Connection() throws Exception {
				
//		Socket sk = new Socket("10.2.1.81", 4105);// IAT params	online
//		Socket sk = new Socket("10.2.1.81", 4105);// IAT params offline
//		System.out.println(isConnected(sk));
		//Query
//		queryAccount(sk,"02001010005993");
//		queryAccount(sk,"11001010000105");
		//Hold
//		holdAccount(sk,"02001010005993","10000000");		
		//transfer
//		branchTransfer(sk, "11001010003384","02001019001103","150000");
		//createFDGroup
//		createFDMesssage(sk, "11005010903268", "3698", "CTY CP VAN TAI BIEN", "VND");
		//createFDReceipt 11010199900885
//		createFDReceiptMesssage(sk, "DD110001", "3000", "110", "300911", "15", "11005010903268", "200000000", "11010199900885", "VND", "CD010I", "Test IAT");
		//width draw
//		withDrawMessage(sk, "11001010000105", "6000000", "060113", "DD110001", "1213", "", "1360");
		//transfer GL - OL2 -- dont support
//		createOL2(sk, "10.1.99.4", "HP", "IT110003", "DD110001", "54", "070113", "020", "11001010003384", "100000", "120787", "280898009", "0", "0", "ABC", "CNGUYEN TUAN NGOC", "012945065", "", "VTC", "", "", "", "", "EB8277","999111223345");
		
		
		
		
	}

	
	
}
