package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface ServiceInfoDAO {
	ArrayList<ServiceInfo> getAllServiceInfo();
}