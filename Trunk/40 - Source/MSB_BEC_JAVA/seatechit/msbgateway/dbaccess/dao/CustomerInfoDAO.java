package seatechit.msbgateway.dbaccess.dao;

import seatechit.msbgateway.dbaccess.entity.CustomerInfo;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface CustomerInfoDAO {

	CustomerInfo getCustomerInfoByCIFOrID(String cif_no, String cert_code, String cert_type);

}