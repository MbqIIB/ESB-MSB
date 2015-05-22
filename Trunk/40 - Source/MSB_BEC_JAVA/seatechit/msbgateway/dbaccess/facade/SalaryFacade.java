package seatechit.msbgateway.dbaccess.facade;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.dao.DAOFactory;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALW;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALWH;

public class SalaryFacade extends BusinessBaseFacade {
	DAOFactory factory;

	public SalaryFacade() {
		super();
		factory = new DAOFactory();
	}

	public boolean saveSalary(ArrayList<SalaryBKDDSALW> arrSalaryBKDDSALW, ArrayList<SalaryBKDDSALWH> arrSalaryBKDDSALWH) throws Exception {
		boolean obj = false;
		try {
			// transaction.begin();
			obj = factory.getSalaryDAO().saveSalary(arrSalaryBKDDSALW, arrSalaryBKDDSALWH);
//			 transaction.commit();
		} catch (Exception exception) {
			// transaction.rollback();
			throw exception;
		}
		return obj;
	}
}
