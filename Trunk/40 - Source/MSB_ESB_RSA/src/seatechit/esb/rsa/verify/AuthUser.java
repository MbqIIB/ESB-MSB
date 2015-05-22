package seatechit.esb.rsa.verify;

/*
 * AuthUser.java
 * Copyright (C) 2003 - 2005 by RSA Security Inc. All rights reserved.
 */

import seatechit.esb.rsa.utils.SysConfigure;

import com.rsa.authagent.authapi.AuthAgentException;
import com.rsa.authagent.authapi.AuthSession;
import com.rsa.authagent.authapi.AuthSessionFactory;

/**
 * This sample performs a SecurID authentication.
 */
public class AuthUser {
	private static final String ACCESS_DENIED = "Access Denied";
	private static final String PASSCODE_ACCEPTED = "Passcode Accepted";
	private static final String PIN_REJECTED = "New Pin rejected";
	private static final String PIN_ACCEPTED = "New Pin accepted; please login again with the new Pin.";
	private static final String GOOD_AUTH = "Authentication successful";
	private static final String BAD_AUTH = "Authentication incomplete";

	private static final String PATH = SysConfigure.getRSAConfigItem("AUTH_FILE_PATH");

	private AuthSessionFactory api = null;
	private ConsoleIO io;

	public AuthUser() {
		io = new ConsoleIO();
		try {
			api = AuthSessionFactory.getInstance(PATH);
		} catch (AuthAgentException e) {
			io.outputLn("Can't create api: " + e.getMessage());
			// throw e;
		}
	}

	/*
	 * 
	 * // Field descriptor #20 I public static final int ACCESS_OK = 0;
	 * 
	 * // Field descriptor #20 I public static final int ACCESS_DENIED = 1;
	 * 
	 * // Field descriptor #20 I public static final int NEXT_CODE_REQUIRED = 2;
	 * 
	 * // Field descriptor #20 I public static final int NEXT_CODE_BAD = 4;
	 * 
	 * // Field descriptor #20 I public static final int NEW_PIN_REQUIRED = 5;
	 * 
	 * // Field descriptor #20 I public static final int PIN_ACCEPTED = 6;
	 * 
	 * // Field descriptor #20 I public static final int PIN_REJECTED = 7;
	 */
	public int auth(String userName, String passCode) {
		AuthSession session;
		int iReturn = 1;
		try {
			session = api.createUserSession();
			int authStatus = AuthSession.ACCESS_DENIED;
			for (int i = 0; i < 3 && authStatus != AuthSession.ACCESS_OK; i++) {
				authStatus = session.lock(userName);
				authStatus = session.check(userName, passCode);
				authStatus = finalizeAuth(authStatus, session);
				switch (authStatus) {
				case AuthSession.ACCESS_OK:
					io.outputLn(PASSCODE_ACCEPTED);
					break;

				case AuthSession.PIN_ACCEPTED:
					io.outputLn(PIN_ACCEPTED);
					i--;
					continue;

					// case AuthSession.PIN_REJECTED :
					// io.outputLn(PIN_REJECTED);
					// // break;
					// continue;
					// case AuthSession.ACCESS_DENIED :
				default:
					io.outputLn(ACCESS_DENIED);
					continue;
				}
			}
			session.close();
			if (authStatus == AuthSession.ACCESS_OK) {
				io.outputLn(GOOD_AUTH);
				iReturn = 0;
			} else {
				io.outputLn(BAD_AUTH);
				iReturn = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (api != null) {
				try {
					api.shutdown();
				} catch (AuthAgentException e) {
					e.printStackTrace();
				}
			}
		}
		return iReturn;
	}

	/**
	 * @param authStatus
	 *            the initial status
	 * @return the final status
	 */
	private int finalizeAuth(int authStatus, AuthSession session) throws Exception {
		int finalStatus;
		switch (authStatus) {
		case AuthSession.NEW_PIN_REQUIRED:
			NewPinSession newPin = new NewPinSession(io, session);
			// finalStatus = newPin.process();
			// break;
			finalStatus = 0;
			break;
		case AuthSession.NEXT_CODE_REQUIRED:
			//NextCodeSession nextCode = new NextCodeSession(io, session);
			//finalStatus = nextCode.process();
			//break;

		case AuthSession.ACCESS_OK:
			finalStatus = authStatus;
			break;

		default:
			finalStatus = AuthSession.ACCESS_DENIED;
			break;
		}
		return finalStatus;
	}

}
