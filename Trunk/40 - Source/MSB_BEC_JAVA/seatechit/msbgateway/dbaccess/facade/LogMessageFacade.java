package seatechit.msbgateway.dbaccess.facade;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.LogMessage;

public class LogMessageFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public LogMessageFacade() {
		super();
		factory = new DAOFactory();
	}

	public int insertLogMessage(LogMessage logMsg) {
		int objRet = 0;
		try {
			// transaction.begin();
			objRet = factory.getLogMessageDAO().insertLogMessage(logMsg);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			// throw exception;
		}
		return objRet;
	}
}
