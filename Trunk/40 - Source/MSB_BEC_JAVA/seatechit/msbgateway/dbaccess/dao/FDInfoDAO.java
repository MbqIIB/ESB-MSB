package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.FDInfo;;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface FDInfoDAO {

	ArrayList<FDInfo> getFDInfos(String cif_no,String group_acct,String receipt_no);

}