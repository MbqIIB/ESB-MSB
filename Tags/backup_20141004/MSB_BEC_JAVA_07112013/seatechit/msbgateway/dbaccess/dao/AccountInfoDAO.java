package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.AccountInfo;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface AccountInfoDAO {

	ArrayList<AccountInfo> getAccountInfoByCif(String cif_no, String online);
	
	ArrayList<AccountInfo> getAccountInfoByListOfCif(String[] cif_no, int numOfCif);
	
	AccountInfo getAccountInfoByAccountNo(String acct_no, String acct_type, String online);
}