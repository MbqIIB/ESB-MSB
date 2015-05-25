package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.SysParam;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface SysParamDAO {
	abstract ArrayList<SysParam> getAllSystemParam();

	abstract String getTransactionSequence();

	abstract String getRMSequence();

	abstract String getSalarySequence();

	abstract SysParam getParamByName(String paramName);
}