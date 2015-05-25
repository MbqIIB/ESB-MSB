package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.FDInfo;

public class FDInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public FDInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
public	ArrayList<FDInfo> getFDInfos(String cif_no,String group_acct,String receipt_no) throws Exception {
		Object obj = new ArrayList<FDInfo>();
		try {
			// transaction.begin();
			obj = factory.getFDInfoDAO().getFDInfos( cif_no, group_acct, receipt_no);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<FDInfo>) ((List) (obj));
	}

	
}
