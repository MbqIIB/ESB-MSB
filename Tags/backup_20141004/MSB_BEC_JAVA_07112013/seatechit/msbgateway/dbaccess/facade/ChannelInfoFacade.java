package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.ChannelInfo;

public class ChannelInfoFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public ChannelInfoFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ChannelInfo> getAllChannelInfo() throws Exception {
		Object obj = new ArrayList<ChannelInfo>();
		try {
			// transaction.begin();
			obj = factory.getChannelInfoDAO().getAllChannelInfo();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<ChannelInfo>) ((List) (obj));

	}
}
