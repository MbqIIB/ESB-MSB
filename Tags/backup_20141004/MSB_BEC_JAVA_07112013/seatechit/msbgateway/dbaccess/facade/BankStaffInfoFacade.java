package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.BankStaffInfo;

public class BankStaffInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public BankStaffInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<BankStaffInfo> getAllBankStaffInfo() throws Exception {
		Object obj = new ArrayList<BankStaffInfo>();
		try {
			// transaction.begin();
			obj = factory.getBankStaffInfoDAO().getAllBankStaffInfo();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<BankStaffInfo>) ((List) (obj));

	}
}
