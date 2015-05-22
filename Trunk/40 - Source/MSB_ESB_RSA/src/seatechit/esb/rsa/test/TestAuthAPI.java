/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seatechit.esb.rsa.test;

import seatechit.esb.rsa.verify.AuthUser;

/**
 * 
 * @author Administrator
 */
public class TestAuthAPI {
	public static void main(String[] arg) throws Exception {
		AuthUser user = new AuthUser();
		try {
			int i = user.auth("kienvt2", "267582");
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
