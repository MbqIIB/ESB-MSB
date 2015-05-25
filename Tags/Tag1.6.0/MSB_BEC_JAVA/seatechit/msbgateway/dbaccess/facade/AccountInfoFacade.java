package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.AccountInfo;

public class AccountInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public AccountInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<AccountInfo> getAccountInfoByCif(String cif_no, String online) throws Exception {
		Object obj = new ArrayList<AccountInfo>();
		try {
			// transaction.begin();
			obj = factory.getAccountInfoDAO().getAccountInfoByCif(cif_no, online);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<AccountInfo>) ((List) (obj));
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AccountInfo> getRelAccountInfoByCif(String[] cif_no, int numOfCif, String online) throws Exception {
		Object obj = new ArrayList<AccountInfo>();
		try {
			// transaction.begin();
			if (numOfCif == 1) {
				obj = factory.getAccountInfoDAO().getAccountInfoByCif(cif_no[0], online);
			} else if (numOfCif > 1) {
				obj = factory.getAccountInfoDAO().getAccountInfoByListOfCif(cif_no, numOfCif);
			}
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<AccountInfo>) ((List) (obj));
	}
	
	public AccountInfo getAccountInfoByAccountNo(String acct_no, String acct_type, String online) throws Exception {
		Object obj = new AccountInfo();
		try {
			// transaction.begin();
			obj = factory.getAccountInfoDAO().getAccountInfoByAccountNo(acct_no, acct_type, online);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (AccountInfo) obj;

	}
}
