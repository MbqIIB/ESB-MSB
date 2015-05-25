package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;
import java.util.List;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.SysParam;

public class SysParamFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public SysParamFacade() {
		super();
		factory = new DAOFactory();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysParam> getAllSystemParam() throws Exception {
		Object obj = new ArrayList<SysParam>();
		try {
			// transaction.begin();
			obj = factory.getSysParamDAO().getAllSystemParam();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (ArrayList<SysParam>) ((List) (obj));

	}

	public SysParam getParamByName(String paramName) throws Exception {
		Object obj = new SysParam();
		try {
			// transaction.begin();
			obj = factory.getSysParamDAO().getParamByName(paramName);
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return (SysParam) obj;

	}

	public String getTransactionSequence() throws Exception {
		try {
			// transaction.begin();
			return factory.getSysParamDAO().getTransactionSequence();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
	}

	public String getRMSequence() throws Exception {
		try {
			// transaction.begin();
			return factory.getSysParamDAO().getRMSequence();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
	}

	public String getSalarySequence() throws Exception {
		try {
			// transaction.begin();
			return factory.getSysParamDAO().getSalarySequence();
			// transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
	}
}
