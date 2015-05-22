package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.ServiceInfo;

public class ServiceInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public ServiceInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ServiceInfo> getAllServiceInfo() throws Exception {
		Object obj = new ArrayList<ServiceInfo>();
		try {
			// transaction.begin();
			obj = factory.getServiceInfoDAO().getAllServiceInfo();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<ServiceInfo>) ((List) (obj));

	}
}
