package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALW;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALWH;

public interface SalaryDAO {
	public boolean saveSalary(ArrayList<SalaryBKDDSALW> arrSalaryBKDDSALW, ArrayList<SalaryBKDDSALWH> arrSalaryBKDDSALWH);
}
