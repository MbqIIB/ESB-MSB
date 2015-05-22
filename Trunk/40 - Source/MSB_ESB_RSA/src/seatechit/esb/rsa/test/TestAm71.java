package seatechit.esb.rsa.test;

import com.rsa.command.CommandException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import seatechit.esb.rsa.assign.RSAAdmin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Administrator
 */
public class TestAm71 {
	public static void main(String[] args) throws ServiceException, RemoteException {
		String passcode;
		InputStreamReader converter;
		BufferedReader in;
		// System.setProperty("javax.net.ssl.trustStore",
		// "D:\\RSA\\rsa_pro.jks");
		// System.setProperty("javax.net.ssl.trustStorePassword ",
		// "Admin01");

		// Sample1 am71 = new
		// Sample1("https://10.1.17.90:7002/ims-ws/services/CommandServer",
		// "CmdClient_rb5wpdj1", "GB8Bi2cJqv", "Customers",
		// "Internal Database");
		RSAAdmin am71 = new RSAAdmin();
		int i = 0;
		//String userId = "h2_maker";
		 String userId = "kienvt2";

		String tknSN = "000210296466";
		// String tknSN = "000210296481";

		// am71.connect("apiadmin", "apiadmin_123");

		// 1) Create user
		//i = am71.createUser(tknSN, userId, "abcdef.124", "Lname", "Fname");
		//System.out.println("create User finish...." + i);
		
		
		// String amGuid = am71.createAMUser(guid);

		// am71.createUser(tknSN, userId, "abccddd.se333124", "fname", "lname");
		// 2)Assign token
		//i = am71.assignTokenToUser(userId, tknSN, false);
		// System.out.println("assign finish...." + i);
		// true: no PIN required

		// update PIN
		// am71.updatePin("000207588054", "admin123");

		// 3)Unassign token
		//i= am71.unassignToken(tknSN);
		// System.out.println("unassign finish" + i);

		// 4) Delete user
		// i = am71.deleteUser(userId);
		// System.out.println("delete finish...." + i);

	}
}
