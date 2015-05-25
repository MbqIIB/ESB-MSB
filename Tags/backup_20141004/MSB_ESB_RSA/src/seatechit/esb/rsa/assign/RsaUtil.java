package seatechit.esb.rsa.assign;

import java.rmi.RemoteException;
import java.security.Security;
import java.util.Calendar;
import java.util.HashMap;

import org.omg.CORBA.SystemException;

import seatechit.esb.rsa.utils.StringUtil;

import com.rsa.admin.AddPrincipalsCommand;
import com.rsa.admin.DeletePrincipalsCommand;
import com.rsa.admin.GetIdentitySourcesCommand;
import com.rsa.admin.SearchPrincipalsCommand;
import com.rsa.admin.SearchRealmsCommand;
import com.rsa.admin.SearchSecurityDomainCommand;
import com.rsa.admin.data.IdentitySourceDTO;
import com.rsa.admin.data.PrincipalDTO;
import com.rsa.admin.data.PrincipalDTOConstants;
import com.rsa.admin.data.RealmDTO;
import com.rsa.admin.data.RealmDTOConstants;
import com.rsa.admin.data.SecurityDomainDTO;
import com.rsa.admin.data.SecurityDomainDTOConstants;
import com.rsa.authmgr.admin.principalmgt.AddAMPrincipalCommand;
import com.rsa.authmgr.admin.principalmgt.data.AMPrincipalDTO;
import com.rsa.authmgr.admin.tokenmgt.LinkTokensWithPrincipalCommand;
import com.rsa.authmgr.admin.tokenmgt.LookupTokenCommand;
import com.rsa.authmgr.admin.tokenmgt.SetTokenPinTypeCommand;
import com.rsa.authmgr.admin.tokenmgt.UnlinkTokensFromPrincipalsCommand;
import com.rsa.authmgr.admin.tokenmgt.UpdateTokenCommand;
import com.rsa.authmgr.admin.tokenmgt.data.TokenDTO;
import com.rsa.authmgr.admin.tokenmgt.data.TokenDTOConstants;
import com.rsa.authn.LoginCommand;
import com.rsa.authn.data.AbstractParameterDTO;
import com.rsa.authn.data.FieldParameterDTO;
import com.rsa.command.CommandException;
import com.rsa.command.exception.DataNotFoundException;
import com.rsa.command.exception.DuplicateDataException;
import com.rsa.command.exception.InvalidArgumentException;
import com.rsa.common.AuthenticationConstants;
import com.rsa.common.search.FilterHelper;
import com.rsa.webservice.CommandServerSoapBindingStub;

public class RsaUtil {

	private SecurityDomainDTO domain = null;

	private IdentitySourceDTO idSource = null;

	private static String realm = "";// SysConfigure.getRSAConfigItem("realm");

	private static String am71SOAPendpoint = "";// SysConfigure.getRSAConfigItem("am71SOAPendpoint");

	private static String am71cmdlineUser = "";// SysConfigure.getRSAConfigItem("am71cmdlineUser");

	private static String am71cmdlineUserPassword = "";// SysConfigure.getRSAConfigItem("am71cmdlineUserPassword");

	private static String am71secDomain = "";// SysConfigure.getRSAConfigItem("am71secDomain");

	private static String am71IdentitySource = "";// SysConfigure.getRSAConfigItem("am71IdentitySource");

	private CommandServerSoapBindingStub binding = null;

	private static String session = null;

	private static String trustStore = "";// SysConfigure.getRSAConfigItem("trustStore");

	private static String trustStorePassword = "";// SysConfigure.getRSAConfigItem("trustStorePassword");

	private static String AMuserID = "";// SysConfigure.getRSAConfigItem("AMuserID");

	private static String AMpassword = "";// SysConfigure.getRSAConfigItem("AMpassword");

	private static String socketFactoryProvider = "";// SysConfigure.getRSAConfigItem("socketFactoryProvider");

	private static String serverSocketFactoryProvider = "";// SysConfigure.getRSAConfigItem("serverSocketFactoryProvider");

	private static String trustStoreType = "";// SysConfigure.getRSAConfigItem("trustStoreType");

	public static void main(String[] args) {
		RsaUtil rsa = new RsaUtil();
	}

	/**
	 * i=0 sucess i=1 token not found, i=2 userId is exist , i=3 password format
	 * incorrect , i=4 input data incorrect ,i=5 other error ,i=6 token alerdy
	 * assigned
	 * 
	 * @param hm
	 * @return
	 * @throws
	 */
	public int assignUser(HashMap hm) {

		int i = checkRsaData(hm);
		if (i == 4) {
			return i;
		}

		// log.info("am71SOAPendpoint---------->" + am71SOAPendpoint);
		// log.info("trustStore---------->" + trustStore);
		// log.info("AMuserID---------->" + AMuserID);
		try {
			String tokenNumber = hm.get("tokenNumber").toString();

			Security.setProperty("ssl.SocketFactory.provider",
					socketFactoryProvider);
			Security.setProperty("ssl.ServerSocketFactory.provider",
					serverSocketFactoryProvider);

			System.setProperty("javax.net.ssl.trustStoreType", trustStoreType);
			System.setProperty("javax.net.ssl.trustStore", trustStore);
			System.setProperty("javax.net.ssl.trustStorePassword ",
					trustStorePassword);
			// log.info("begin connect");
			connect(AMuserID, AMpassword);
			// log.info("connect success!");
			TokenDTO token = lookupTokens(tokenNumber);
			String assigonUserId = null;
			if (token != null) {
				assigonUserId = token.getAssignedUserId();
				if (assigonUserId == null) {
					String password = hm.get("password").toString();
					String userId = hm.get("userId").toString();
					String guid = createIMSUser(userId, password,
							hm.get("firstName").toString(), hm.get("lastName")
									.toString());
					if (guid != null) {
						String amGuid = createAMUser(guid);
						// log.info(amGuid);
						assignTokenToUser(userId, tokenNumber, true);
						i = 0;
					}
				} else {
					i = 6;
				}
			} else {
				i = 1;
			}
		} catch (DuplicateDataException ex) {
			i = 2;
			// log.warn("userId is exist");
		} catch (InvalidArgumentException ex) {
			i = 3;
			// log.warn("Password policy not satisfied");
		} catch (DataNotFoundException ex) {
			i = 1;
			// log.warn("tokens  not found ");
			// } catch (ServiceException ex) {
			// i = 5;
			// log.info("ServiceException");
			// ex.printStackTrace();
			// log.error(ex.getMessage());
		} catch (RemoteException ex) {
			i = 5;
			// log.info("RemoteException");
			ex.printStackTrace();
			// log.error(ex.getMessage());
		} catch (Exception ex) {
			i = 5;
			// log.error(ex.getMessage());
		}

		// log.info("i------>" + i);
		return i;
	}

	@SuppressWarnings("unchecked")
	private int checkRsaData(HashMap hm) {
		int i = 5;
		String tokenSn = StringUtil.dealNull(hm.get("tokenNumber"));
		String userId = StringUtil.dealNull(hm.get("userId"));
		String password = StringUtil.dealNull(hm.get("password"));
		String firstName = StringUtil.dealNull(hm.get("firstName"));
		String lastName = StringUtil.dealNull(hm.get("lastName"));
		if ("".equals(tokenSn) || "".equals(userId) || "".equals(password)
				|| "".equals(firstName) || "".equals(lastName)) {
			i = 4;
		}
		return i;
	}

	public void updatePin(String tknSN, String pin) throws SystemException,
			RemoteException {
		TokenDTO token = lookupTokens(tknSN);
		token.setPin(pin); //
		UpdateTokenCommand updateTokenCmd = new UpdateTokenCommand();
		updateTokenCmd.setToken(token);
		updateTokenCmd = (UpdateTokenCommand) binding.executeCommand(session,
				updateTokenCmd);
		System.out.println("PIN updated!");
	}

	private void initSession(String user, String pass) throws RemoteException {

		FieldParameterDTO usernameField = new FieldParameterDTO();
		FieldParameterDTO passwordField = new FieldParameterDTO();

		usernameField.setValue(user);
		usernameField.setPromptKey(AuthenticationConstants.PRINCIPAL_ID);

		passwordField.setValue(pass);
		passwordField.setPromptKey(AuthenticationConstants.PASSWORD);

		LoginCommand cmd = new LoginCommand();
		cmd.setAuthenticationMethodId(AuthenticationConstants.RSA_PASSWORD_METHOD);
		cmd.setParameters(new AbstractParameterDTO[] { usernameField,
				passwordField });

		cmd = (LoginCommand) binding.executeCommand(null, cmd);

		session = cmd.getSessionId();

		SearchRealmsCommand searchRealmCmd = new SearchRealmsCommand();
		searchRealmCmd.setFilter(FilterHelper.equal(
				RealmDTOConstants.NAME_ATTRIBUTE, realm));
		searchRealmCmd = (SearchRealmsCommand) binding.executeCommand(session,
				searchRealmCmd);

		RealmDTO[] realms = searchRealmCmd.getRealms();
		SecurityDomainDTO[] domains;

		// Get the identity source
		GetIdentitySourcesCommand getISCmd = new GetIdentitySourcesCommand();
		getISCmd = (GetIdentitySourcesCommand) binding.executeCommand(session,
				getISCmd);
		IdentitySourceDTO all[] = getISCmd.getIdentitySources();

		if (all == null || all.length == 0) {
			// log.error("Couldn't determine AM 7.1 identity source. 0 identity sources returned");

		} else {

			int countA = 0;

			while (countA < all.length) {
				// System.out.println("Identity source found: " +
				// all[countA].getName());
				if (all[countA].getName().compareTo(this.am71IdentitySource) == 0) {
					idSource = all[countA];
				}
				countA++;
			}
		}

		if (idSource == null) {
			// log.fatal("Could not find identity source " +
			// this.am71IdentitySource);
		}

		// Get the domainDTO object associated with the security domain
		// specified in the config file
		SecurityDomainDTO targetDomain = new SecurityDomainDTO();
		SearchSecurityDomainCommand searchDomainCmd = new SearchSecurityDomainCommand();
		searchDomainCmd.setFilter(FilterHelper.equal(
				SecurityDomainDTOConstants.NAME_ATTRIBUTE, am71secDomain));
		searchDomainCmd = (SearchSecurityDomainCommand) binding.executeCommand(
				session, searchDomainCmd);
		SecurityDomainDTO[] domainDTOs = searchDomainCmd.getSecurityDomains();

		if (domainDTOs == null || domainDTOs.length == 0) {
			// log.error("Couldn't find AM 7.1 security domain: " +
			// am71secDomain + ". Please check SecurID ACSP config file");
		} else {
			domain = domainDTOs[0];
		}
	}

	public void connect(String AMuserID, String AMpassword)
			throws RemoteException {
		// AxisProperties.setProperty("axis.socketSecureFactory",
		// "com.rsa.webservice.net.SSLv3JSSESocketFactory");
		//
		// // AxisProperties.setProperty("axis.socketSecureFactory",
		// // "au.com.rsa.ps.securid.SSLv3Factory3");
		// CommandServerServiceLocator csl = new CommandServerServiceLocator();
		// csl.setCommandServerEndpointAddress(am71SOAPendpoint);
		// binding = (CommandServerSoapBindingStub) csl.getCommandServer();
		//
		// binding.setUsername(am71cmdlineUser);
		// binding.setPassword(am71cmdlineUserPassword);
		// binding.setMaintainSession(true);
		// binding.setTimeout(60000); // 60 seconds, in milliseconds
		//
		// initSession(AMuserID, AMpassword);
	}

	/**
	 * Create an IMS user, needs to exist before an AM user can be created.
	 * 
	 * @param userId
	 *            the user's login UID
	 * @param password
	 *            the user's password
	 * @param first
	 *            the user's first name
	 * @param last
	 *            the user's last name
	 * 
	 * @return the GUID of the user just created
	 * 
	 * @throws CommandException
	 *             if something goes wrong
	 */
	public String createIMSUser(String userId, String password, String first,
			String last) throws SystemException, RemoteException {
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DAY_OF_MONTH, -1);

		PrincipalDTO principal = new PrincipalDTO();
		principal.setUserID(userId);
		principal.setFirstName(first);
		principal.setLastName(last);
		principal.setPassword(password);

		principal.setPasswordAuthenticationDisabled(false);

		principal.setEnabled(true);
		principal.setLockoutStatus(false);
		principal.setAccountStartDate(cal);
		principal.setAdminRole(false);
		principal.setCanBeImpersonated(false);
		principal.setTrustToImpersonate(false);

		principal.setEmail("");
		principal.setEmergencyLockoutStatus(false);
		principal.setLastModifiedBy("Modified by Java API ");
		principal.setLastModifiedOn(cal);
		principal.setSecurityDomainGuid(domain.getGuid());
		principal.setIdentitySourceGuid(idSource.getGuid());
		principal.setDescription("Created by Java API");

		// principal.setPasswordExpired(false); //r
		// principal.setPassword(password);
		// AuthenticatorDTO proxyAuthenticator = new AuthenticatorDTO();
		// proxyAuthenticator.set
		// AuthenticatorDTO[] proxyAuthenticators = {proxyAuthenticator};
		// principal.setProxyAuthenticators(proxyAuthenticators);

		AddPrincipalsCommand cmd = new AddPrincipalsCommand();
		cmd.setPrincipals(new PrincipalDTO[] { principal });

		cmd = (AddPrincipalsCommand) binding.executeCommand(session, cmd);

		// only one user was created, there should be one GUID result
		if (cmd.getGuids() != null) {
			return cmd.getGuids()[0];
		} else {
			return null;
		}
	}

	/**
	 * Delete a user.
	 * 
	 * @param userGuid
	 *            the GUID of the user to delete
	 * 
	 * @throws CommandException
	 *             if something goes wrong
	 */
	public void deleteUser(String userId) throws RemoteException {

		String userGuid = lookupUser(userId).getGuid();

		DeletePrincipalsCommand cmd = new DeletePrincipalsCommand();
		cmd.setGuids(new String[] { userGuid });
		cmd.setIdentitySourceGuid(idSource.getGuid());
		binding.executeCommand(session, cmd);
	}

	/**
	 * Create an Authentication Manager user linked to the IMS user. The user
	 * will have a limit of 3 bad passcodes, default shell will be "/bin/sh",
	 * the static password will be "12345678" and the Windows Password for
	 * offline authentication will be "Password123!".
	 * 
	 * @param guid
	 *            the GUID of the IMS user
	 * 
	 * @throws CommandException
	 *             if something goes wrong
	 */
	public String createAMUser(String guid) throws RemoteException {

		AMPrincipalDTO AMprincipal = new AMPrincipalDTO();

		AMprincipal.setGuid(guid);
		AMprincipal.setClearBadPasscodes(true);
		AMprincipal.setDefaultUserIdShellAllowed(true);
		AMprincipal.setStaticPasswordSet(false);

		// AMprincipal.setDefaultUserIdShellAllowed(false);
		// AMprincipal.setDefaultShell("/non/existing");
		// AMprincipal.setBadPasscodes(3);
		// AMprincipal.setStaticPassword("12345678");

		AddAMPrincipalCommand cmd = new AddAMPrincipalCommand();
		cmd.setAmp(AMprincipal);
		cmd = (AddAMPrincipalCommand) binding.executeCommand(session, cmd);
		AMPrincipalDTO amp = cmd.getAmp();
		if (amp == null) {
			// log.warn("AMPrincipalDTO is null!");
		}
		return amp.getGuid();
	}

	/**
	 * Lookup a user by login UID.
	 * 
	 * @param userId
	 *            the user login UID
	 * 
	 * @return the user record.
	 */
	public PrincipalDTO lookupUser(String userId) throws RemoteException {
		SearchPrincipalsCommand cmd = new SearchPrincipalsCommand();

		// create a filter with the login UID equal condition
		// cmd.setFilter(Filter.equal(PrincipalDTOConstants.LOGINUID, userId));

		cmd.setFilter(FilterHelper
				.equal(PrincipalDTOConstants.LOGINUID, userId));
		cmd.setSystemFilter(FilterHelper.empty());
		cmd.setLimit(1);
		cmd.setIdentitySourceGuid(idSource.getGuid());
		cmd.setSecurityDomainGuid(domain.getGuid());
		cmd.setGroupGuid(null);
		cmd.setOnlyRegistered(true);
		cmd.setSearchSubDomains(false);

		cmd = (SearchPrincipalsCommand) binding.executeCommand(session, cmd);
		if (cmd.getPrincipals().length < 1) {
			// log.warn("ERROR: Unable to find user " + userId + ".");
			return null;
		} else {
			return cmd.getPrincipals()[0];
		}
	}

	/**
	 * Assign next available token to this user.
	 * 
	 * @param userGuid
	 *            the GUID of the user to assign the token to
	 * 
	 * @throws CommandException
	 *             if something goes wrong
	 */
	public void assignTokenToUser(String userId, String tknSN,
			boolean useTknCode) throws RemoteException {

		String userGuid = lookupUser(userId).getGuid();

		if (userGuid != null) {
			// log.debug("Assigntoken: GUID " + userGuid);

			// Lookup token....
			LookupTokenCommand tkn = new LookupTokenCommand();
			tkn.setSerialNumber(tknSN);
			tkn.setBackgroundCommand(false);
			tkn = (LookupTokenCommand) binding.executeCommand(session, tkn);

			if (tkn != null) {
				// log.debug("Assigntoken: tkn " + tkn);
			} else {
				// log.warn("Assigntoken: tknD is null!");
				// TODO: Raise exception

			}

			// Get token DTO
			TokenDTO tknDTO = tkn.getToken();
			if (tknDTO != null) {
				// log.debug("Assigntoken: tknDTO " + tknDTO);
			} else {
				// log.warn("Assigntoken: tknDTO is null!");
				// TODO: Raise exception

			}
			String tknGuid = tknDTO.getId();
			if (tknGuid != null) {
				// log.debug("Assigntoken: TokenGUID " + tknGuid);

				String[] tokens = new String[] { tknGuid };
				SetTokenPinTypeCommand stknPIN = new SetTokenPinTypeCommand();

				// Using tokencode only? (... this implies no PIN)
				if (useTknCode) {
					stknPIN.setPinType(TokenDTOConstants.USE_TOKENCODE);
				}
				stknPIN.setTokenGuids(tokens);
				stknPIN = (SetTokenPinTypeCommand) binding.executeCommand(
						session, stknPIN);

				LinkTokensWithPrincipalCommand cmd2 = new LinkTokensWithPrincipalCommand();
				cmd2.setTokenGuids(tokens);
				cmd2.setPrincipalGuid(userGuid);
				cmd2 = (LinkTokensWithPrincipalCommand) binding.executeCommand(
						session, cmd2);
			} else {
				// log.warn("Assigntoken: tknGuid is null!");

				// TODO: Raise exception
			}
		} else {
			// log.warn("assignTokentToUser: Invalid userID " + userId);
		}
	}

	public TokenDTO lookupTokens(String tknSN) throws RemoteException {

		TokenDTO tknDTO = null;

		if (tknSN != null) {
			LookupTokenCommand tkn = new LookupTokenCommand();
			tkn.setBackgroundCommand(false);
			tkn.setSerialNumber(tknSN);
			tkn = (LookupTokenCommand) binding.executeCommand(session, tkn);
			tknDTO = tkn.getToken();
			return tknDTO;
		}
		return tknDTO;
	}

	public void unassignToken(String tknSN) throws RemoteException {
		LookupTokenCommand tkn = new LookupTokenCommand();
		tkn.setSerialNumber(tknSN);
		tkn.setBackgroundCommand(false);
		tkn = (LookupTokenCommand) binding.executeCommand(session, tkn);
		TokenDTO tknDTO = tkn.getToken();
		String tknGuid = tknDTO.getId();
		String[] tokens = new String[] { tknGuid };

		UnlinkTokensFromPrincipalsCommand unlnk = new UnlinkTokensFromPrincipalsCommand();
		unlnk.setTokenGuids(tokens);
		unlnk = (UnlinkTokensFromPrincipalsCommand) binding.executeCommand(
				session, unlnk);
	}

}
