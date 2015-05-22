package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.CurrencyInfo;

public class CurrencyInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public CurrencyInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CurrencyInfo> getAllCurrency() throws Exception {
		Object obj = new ArrayList<CurrencyInfo>();
		try {
			// transaction.begin();
			obj = factory.getCurrencyInfoFacade().getAllCurrency();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<CurrencyInfo>) ((List) (obj));

	}
}
