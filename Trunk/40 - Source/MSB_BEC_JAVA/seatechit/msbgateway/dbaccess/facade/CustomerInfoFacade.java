package seatechit.msbgateway.dbaccess.facade;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.CustomerInfo;

public class CustomerInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public CustomerInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	public CustomerInfo getCustomerInfoByCIFOrID(String cif_no, String cert_code, String cert_type) throws Exception {
		CustomerInfo obj = new CustomerInfo();
		try {
			// transaction.begin();
			obj = factory.getCustomerInfoDAO().getCustomerInfoByCIFOrID(cif_no, cert_code, cert_type);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return obj;
	}

}
