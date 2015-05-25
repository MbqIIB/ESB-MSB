package seatechit.msbgateway.dbaccess.dao;

import java.util.ArrayList;

import seatechit.msbgateway.dbaccess.entity.AftInfo;

/**
 * 
 * 
 * @version 1.0
 * @author trungkien
 */
public interface AftInfoDAO {

	ArrayList<AftInfo> getAftInfos(String cif_no,String rollout_acct_no,String bnfc_acct_no, String rollout_acct_type,String bnfc_acct_type,String aft_prod);

}