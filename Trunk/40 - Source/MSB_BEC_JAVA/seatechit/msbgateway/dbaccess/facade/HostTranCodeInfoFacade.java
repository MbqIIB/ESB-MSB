package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.HostTranCodeInfo;

public class HostTranCodeInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public HostTranCodeInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<HostTranCodeInfo> getAllHostTranCodeInfo() throws Exception {
		Object obj = new ArrayList<HostTranCodeInfo>();
		try {
			// transaction.begin();
			obj = factory.getHostTranCodeInfoDAO().getAllHostTranCodeInfo();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<HostTranCodeInfo>) ((List) (obj));

	}
}
