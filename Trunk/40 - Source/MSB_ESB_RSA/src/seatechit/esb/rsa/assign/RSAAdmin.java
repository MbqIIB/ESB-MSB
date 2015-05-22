package seatechit.esb.rsa.assign;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisProperties;
import org.apache.log4j.Logger;

import seatechit.esb.rsa.utils.SysConfigure;

import com.rsa.admin.AddPrincipalsCommand;
import com.rsa.admin.DeletePrincipalsCommand;
import com.rsa.admin.GetIdentitySourcesCommand;
import com.rsa.admin.SearchPrincipalsCommand;
import com.rsa.admin.SearchRealmsCommand;
import com.rsa.admin.SearchSecurityDomainCommand;
import com.rsa.admin.data.IdentitySourceDTO;
import com.rsa.admin.data.PrincipalDTO;
import com.rsa.admin.data.PrincipalDTOConstants;
import com.rsa.admin.data.RealmDTOConstants;
import com.rsa.admin.data.SecurityDomainDTO;
import com.rsa.admin.data.SecurityDomainDTOConstants;
import com.rsa.authmgr.admin.principalmgt.AddAMPrincipalCommand;
import com.rsa.authmgr.admin.principalmgt.data.AMPrincipalDTO;
import com.rsa.authmgr.admin.tokenmgt.LinkTokensWithPrincipalCommand;
import com.rsa.authmgr.admin.tokenmgt.LookupTokenCommand;
import com.rsa.authmgr.admin.tokenmgt.SetTokenPinTypeCommand;
import com.rsa.authmgr.admin.tokenmgt.UnlinkTokensFromPrincipalsCommand;
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
import com.rsa.webservice.CommandServerServiceLocator;
import com.rsa.webservice.CommandServerSoapBindingStub;

public class RSAAdmin {
	private Logger log = Logger.getLogger(getClass());
	private SecurityDomainDTO domain = null;
	private IdentitySourceDTO idSource = null;
	private String realm = "SystemDomain";
	private String am71SOAPendpoint;
	private String am71cmdlineUser;
	private String am71cmdlineUserPassword;
	private String am71secDomain = "SystemDomain";
	private String am71IdentitySource = "Internal Database";
	private CommandServerSoapBindingStub binding = null;
	private String session = null;
	private final String AM71_CMD_LINE_USER = SysConfigure.getRSAConfigItem("AM71_CMD_LINE_USER");
	private final String AM71_CMD_LINE_USERPASSWORD = SysConfigure.getRSAConfigItem("AM71_CMD_LINE_USERPASSWORD");
	private final String AM71_SECDOMAIN = SysConfigure.getRSAConfigItem("AM71_SECDOMAIN");
	private final String AM71_IDENTIFY_SOURCE = "Internal Database";
	private final String URL_TRUSTORE = "javax.net.ssl.trustStore";
	private final String URL_TRUSTORE_PASS = "javax.net.ssl.trustStorePassword";

	private final String TRUSTORE_PASS = SysConfigure.getRSAConfigItem("TRUSTORE_PASS");
	private final String URL_SERVER = SysConfigure.getRSAConfigItem("URL_SERVER");
	private final String URL_JKS_FILE = SysConfigure.getRSAConfigItem("URL_JKS_FILE");
	private String AMuserID = SysConfigure.getRSAConfigItem("AM_USER_ID");
	private String AMpassword = SysConfigure.getRSAConfigItem("AM_PASSWORD");

	public RSAAdmin() {
		this.am71SOAPendpoint = URL_SERVER;
		this.am71cmdlineUser = AM71_CMD_LINE_USER;
		this.am71cmdlineUserPassword = AM71_CMD_LINE_USERPASSWORD;
		this.am71secDomain = AM71_SECDOMAIN;
		this.am71IdentitySource = AM71_IDENTIFY_SOURCE;
		this.setPropertySystem();

	}

	private void setPropertySystem() {
		System.setProperty(URL_TRUSTORE, URL_JKS_FILE);
		System.setProperty(URL_TRUSTORE_PASS, TRUSTORE_PASS);
	}

	// Begin Private Function-------------
	private void initSession(String user, String pass) {

		FieldParameterDTO usernameField = new FieldParameterDTO();
		FieldParameterDTO passwordField = new FieldParameterDTO();

		usernameField.setValue(user);
		usernameField.setPromptKey(AuthenticationConstants.PRINCIPAL_ID);

		passwordField.setValue(pass);
		passwordField.setPromptKey(AuthenticationConstants.PASSWORD);

		LoginCommand cmd = new LoginCommand();
		cmd.setAuthenticationMethodId(AuthenticationConstants.RSA_PASSWORD_METHOD);
		cmd.setParameters(new AbstractParameterDTO[] { usernameField, passwordField });

		try {
			cmd = (LoginCommand) binding.executeCommand(null, cmd);

			session = cmd.getSessionId();

			SearchRealmsCommand searchRealmCmd = new SearchRealmsCommand();
			searchRealmCmd.setFilter(FilterHelper.equal(RealmDTOConstants.NAME_ATTRIBUTE, realm));
			searchRealmCmd = (SearchRealmsCommand) binding.executeCommand(session, searchRealmCmd);

			// Get the identity source
			GetIdentitySourcesCommand getISCmd = new GetIdentitySourcesCommand();
			getISCmd = (GetIdentitySourcesCommand) binding.executeCommand(session, getISCmd);
			IdentitySourceDTO all[] = getISCmd.getIdentitySources();

			if (all == null || all.length == 0) {
				log.error("Couldn't determine AM 7.1 identity source. 0 identity sources returned");
			} else {
				int countA = 0;
				while (countA < all.length) {
					if (all[countA].getName().compareTo(this.am71IdentitySource) == 0) {
						idSource = all[countA];
					}
					countA++;
				}

			}

			if (idSource == null) {
				log.fatal("Could not find identity source " + this.am71IdentitySource);
			}

			SearchSecurityDomainCommand searchDomainCmd = new SearchSecurityDomainCommand();
			searchDomainCmd.setFilter(FilterHelper.equal(SecurityDomainDTOConstants.NAME_ATTRIBUTE, am71secDomain));
			searchDomainCmd = (SearchSecurityDomainCommand) binding.executeCommand(session, searchDomainCmd);
			SecurityDomainDTO[] domainDTOs = searchDomainCmd.getSecurityDomains();

			if (domainDTOs == null || domainDTOs.length == 0) {
				log.error("Couldn't find AM 7.1 security domain: " + am71secDomain + ". Please check SecurID ACSP config file");
			} else {
				domain = domainDTOs[0];
			}
		} catch (CommandException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private TokenDTO lookupTokens(String tknSN) throws CommandException, RemoteException {
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

	private PrincipalDTO lookupUser(String userId) throws CommandException, RemoteException {
		SearchPrincipalsCommand cmd = new SearchPrincipalsCommand();

		cmd.setFilter(FilterHelper.equal(PrincipalDTOConstants.LOGINUID, userId));
		cmd.setSystemFilter(FilterHelper.empty());
		cmd.setLimit(1);
		cmd.setIdentitySourceGuid(idSource.getGuid());
		cmd.setSecurityDomainGuid(domain.getGuid());
		cmd.setGroupGuid(null);
		cmd.setOnlyRegistered(true);
		cmd.setSearchSubDomains(false);

		cmd = (SearchPrincipalsCommand) binding.executeCommand(session, cmd);

		if (cmd.getPrincipals().length < 1) {
			log.warn("ERROR: Unable to find user " + userId + ".");
			return null;
		} else {
			return cmd.getPrincipals()[0];
		}
	}

	public boolean connect(String AMuserID, String AMpassword) {
		boolean bReturn = true;
		AxisProperties.setProperty("axis.socketSecureFactory", "com.rsa.webservice.net.SSLv3JSSESocketFactory");
		CommandServerServiceLocator csl = new CommandServerServiceLocator();
		csl.setCommandServerEndpointAddress(am71SOAPendpoint);
		try {
			binding = (CommandServerSoapBindingStub) csl.getCommandServer();
			binding.setUsername(am71cmdlineUser);
			binding.setPassword(am71cmdlineUserPassword);
			binding.setMaintainSession(true);
			binding.setTimeout(60000); // 60 seconds, in milliseconds
			initSession(AMuserID, AMpassword);
		} catch (ServiceException e) {
			bReturn = false;
			e.printStackTrace();
		}
		return bReturn;
	}

	public String createIMSUser(String userId, String password, String first, String last) throws CommandException, RemoteException {
		Calendar cal = Calendar.getInstance();

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

		AddPrincipalsCommand cmd = new AddPrincipalsCommand();
		cmd.setPrincipals(new PrincipalDTO[] { principal });

		cmd = (AddPrincipalsCommand) binding.executeCommand(session, cmd);
		if (cmd.getGuids() != null) {
			return cmd.getGuids()[0];
		} else {
			return null;
		}
	}

	/**
	 * 
	 */
	public String createAMUser(String guid) throws CommandException, RemoteException {
		AMPrincipalDTO AMprincipal = new AMPrincipalDTO();
		AMprincipal.setGuid(guid);
		AMprincipal.setClearBadPasscodes(true);
		AMprincipal.setDefaultUserIdShellAllowed(true);
		AMprincipal.setStaticPasswordSet(false);
		AddAMPrincipalCommand cmd = new AddAMPrincipalCommand();
		cmd.setAmp(AMprincipal);
		cmd = (AddAMPrincipalCommand) binding.executeCommand(session, cmd);
		AMPrincipalDTO amp = cmd.getAmp();
		if (amp == null) {
			log.warn("AMPrincipalDTO is null!");
		}
		return amp.getGuid();
	}

	// End Private Function--------------

	/**
	 * i=0 sucess i=1 token not found, i=2 userId is exist , i=3 password format
	 * incorrect , i=4 input data incorrect ,i=5 other error ,i=6 token alerdy
	 * assigned
	 * 
	 * @param tknSN
	 * @param userId
	 * @param pwd
	 * @param fname
	 * @param lname
	 * @return
	 */
	public int createUser(String tknSN, String userId, String pwd, String fname, String lname) {
		int i = 0;
		try {
			if (connect(AMuserID, AMpassword)) {
				TokenDTO token = lookupTokens(tknSN);
				String assigonUserId = null;
				if (token != null) {
					assigonUserId = token.getAssignedUserId();
					if (assigonUserId == null) {
						String guid = createIMSUser(userId, pwd, fname, lname).toString();
						if (guid != null) {
							String amGuid = createAMUser(guid);
							log.info(amGuid);
							i = 0;
						}
					} else {
						i = 6;
						log.warn("token already assign");
					}
				} else {
					i = 1;// token not found
					log.warn(" token not found");
				}
			} else {
				i = -1;// cannot connect to server
			}
		} catch (DuplicateDataException ex) {
			i = 2;
			log.warn("userId is exist");
		} catch (InvalidArgumentException ex) {
			i = 3;
			log.warn("Password policy not satisfied");
		} catch (DataNotFoundException ex) {
			i = 1;
			log.warn("tokens  not found ");
		} catch (RemoteException ex) {
			i = 5;
			log.info("RemoteException");
			ex.printStackTrace();
			log.error(ex.getMessage());
		} catch (Exception ex) {
			i = 5;
			log.error(ex.getMessage());
		}

		log.info("i------>" + i);
		return i;

	}

	/**
	 * i=4 invalid user
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteUser(String userId) {
		int iReturn = 0;
		String userGuid;
		try {
			log.info("begin connect");
			if (connect(AMuserID, AMpassword)) {
				log.info("connect success!");
				userGuid = lookupUser(userId) == null ? null : lookupUser(userId).getGuid();
				if (userGuid != null) {
					DeletePrincipalsCommand cmd = new DeletePrincipalsCommand();
					cmd.setGuids(new String[] { userGuid });
					cmd.setIdentitySourceGuid(idSource.getGuid());
					binding.executeCommand(session, cmd);
				} else {
					iReturn = 4;
					log.warn("user not found!");
				}
			} else {
				iReturn = -1;// Cannot connect server
			}
		} catch (CommandException e) {
			iReturn = 5;
			e.printStackTrace();
		} catch (RemoteException e) {
			iReturn = 5;
			e.printStackTrace();
		}
		return iReturn;
	}

	/**
	 * i=0 sucess i=1 token not found, i=2 userId is exist , i=3 password format
	 * incorrect , i=4 invalid user ,i=5 other error ,i=6 token alerdy assigned
	 * 
	 * @param userId
	 * @param tknSN
	 * @param useTknCode
	 * @return
	 */
	public int assignTokenToUser(String userId, String tknSN, boolean useTknCode) {
		int iReturn = 0;

		String userGuid;
		try {
			if (connect(AMuserID, AMpassword)) {
				userGuid = lookupUser(userId) == null ? null : lookupUser(userId).getGuid();
				if (userGuid != null) {
					log.debug("Assigntoken: GUID " + userGuid);

					// Lookup token....
					LookupTokenCommand tkn = new LookupTokenCommand();
					tkn.setSerialNumber(tknSN);
					tkn.setBackgroundCommand(false);
					tkn = (LookupTokenCommand) binding.executeCommand(session, tkn);

					if (tkn != null) {
						log.debug("Assigntoken: tkn " + tkn);
						// Get token DTO
						TokenDTO tknDTO = tkn.getToken();
						if (tknDTO != null) {
							log.debug("Assigntoken: tknDTO " + tknDTO);
							String tknGuid = tknDTO.getId();

							if (tknGuid != null) {
								log.debug("Assigntoken: TokenGUID " + tknGuid);

								String[] tokens = new String[] { tknGuid };
								SetTokenPinTypeCommand stknPIN = new SetTokenPinTypeCommand();

								if (useTknCode) {
									stknPIN.setPinType(TokenDTOConstants.USE_TOKENCODE);
								}
								stknPIN.setTokenGuids(tokens);
								stknPIN = (SetTokenPinTypeCommand) binding.executeCommand(session, stknPIN);

								LinkTokensWithPrincipalCommand cmd2 = new LinkTokensWithPrincipalCommand();
								cmd2.setTokenGuids(tokens);
								cmd2.setPrincipalGuid(userGuid);
								cmd2 = (LinkTokensWithPrincipalCommand) binding.executeCommand(session, cmd2);
								iReturn = 0;// success-OK
							} else {
								iReturn = 3;// -OK
								log.warn("Assigntoken: tknGuid is null!");
							}
						} else {
							iReturn = 1;// token not found-OK
							log.warn("Assigntoken: tknDTO is null!");
						}
					} else {
						iReturn = 1;// token not found-OK
						log.warn("Assigntoken: tknD is null!");
					}
				} else {
					iReturn = 4;// --OK
					log.warn("assignTokentToUser: Invalid userID " + userId);
				}
			} else {
				iReturn = -1;// --OK
				log.warn("Cannot connect RSA server");
			}
		} catch (InvalidArgumentException ex) {
			iReturn = 6;
			log.warn("token already assign");
		} catch (CommandException e) {
			iReturn = 5;
			log.warn("CommandException");
		} catch (RemoteException e) {
			iReturn = 5;
			log.warn("RemoteException");
		}
		return iReturn;
	}

	/**
	 * 
	 * @param tknSN
	 * @return
	 */

	/**
	 * i=0 sucess i=4 token not found
	 * 
	 */
	public int unassignToken(String tknSN) {
		int iReturn = 0;

		try {
			if (connect(AMuserID, AMpassword)) {
				LookupTokenCommand tkn = new LookupTokenCommand();
				tkn.setSerialNumber(tknSN);
				tkn.setBackgroundCommand(false);

				tkn = (LookupTokenCommand) binding.executeCommand(session, tkn);
				TokenDTO tknDTO = tkn.getToken();
				String tknGuid = tknDTO.getId();
				String[] tokens = new String[] { tknGuid };

				UnlinkTokensFromPrincipalsCommand unlnk = new UnlinkTokensFromPrincipalsCommand();
				unlnk.setTokenGuids(tokens);
				unlnk = (UnlinkTokensFromPrincipalsCommand) binding.executeCommand(session, unlnk);
			} else {
				iReturn = -1;// cannot connect to server
			}
		} catch (DataNotFoundException ex) {
			iReturn = 4;
			log.warn("tokens  not found ");
		} catch (CommandException e) {
			iReturn = 5;// token already unassign
		} catch (RemoteException e) {
			iReturn = 5;
		}
		return iReturn;
	}

}
