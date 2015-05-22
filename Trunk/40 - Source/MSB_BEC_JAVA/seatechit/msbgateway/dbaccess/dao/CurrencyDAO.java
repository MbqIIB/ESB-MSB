package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.CurrencyInfo;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface CurrencyDAO {

	ArrayList<CurrencyInfo> getAllCurrency();
}