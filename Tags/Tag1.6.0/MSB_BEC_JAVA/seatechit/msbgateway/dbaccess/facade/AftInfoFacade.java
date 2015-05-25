package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.dao.impl.AftInfoDAOImpl;
import seatechit.msbgateway.dbaccess.entity.AftInfo;

public class AftInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public AftInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
public	ArrayList<AftInfo> getAftInfos(String cif_no,String rollout_acct_no,String bnfc_acct_no, String rollout_acct_type,String bnfc_acct_type,String aft_prod) throws Exception {
		final Logger logger = Logger.getLogger(AftInfoFacade.class);
		
		Object obj = new ArrayList<AftInfo>();
		try {
			// transaction.begin();
			obj = factory.getAftInfoDAO().getAftInfos( cif_no, rollout_acct_no, bnfc_acct_no,  rollout_acct_type, bnfc_acct_type, aft_prod);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<AftInfo>) ((List) (obj));
	}

	
}
