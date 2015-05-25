package seatechit.msbgateway.utils;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import seatechit.msbgateway.consumer.ErrorInfo;
import seatechit.msbgateway.dbaccess.dao.impl.AftInfoDAOImpl;
import seatechit.msbgateway.dbaccess.entity.BankStaffInfo;
import seatechit.msbgateway.dbaccess.entity.ChannelInfo;
import seatechit.msbgateway.dbaccess.entity.CurrencyInfo;
import seatechit.msbgateway.dbaccess.entity.HostTranCodeInfo;
import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.dbaccess.entity.SysParam;
import seatechit.msbgateway.dbaccess.facade.BankStaffInfoFacade;
import seatechit.msbgateway.dbaccess.facade.ChannelInfoFacade;
import seatechit.msbgateway.dbaccess.facade.CurrencyInfoFacade;
import seatechit.msbgateway.dbaccess.facade.HostTranCodeInfoFacade;
import seatechit.msbgateway.dbaccess.facade.ServiceInfoFacade;
import seatechit.msbgateway.dbaccess.facade.SysParamFacade;

/**
 * Store application parameter on memory
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6 *
 */
public class CachedParam extends Global {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6664793052670833838L;
	private static CachedParam instance = null;

	public static String getTransactionParam(String consumer_type, String tran_code, String tran_service_code) {
		return null;
	}

	public static ErrorInfo mappingErrorCode(String errorCode, String errorMessage, String errorType) {
		final Logger logger = Logger.getLogger(AftInfoDAOImpl.class);
		logger.info("errorCodeBatDau1111:" + errorCode);
	
		ErrorInfo retErrorInfo = null;
		if (AppUtils.isValidNumber(errorCode)) {
			if ("0".equals(errorCode)){
//					Integer.parseInt(errorCode) == ERR_SYSTEM_OK) {
				retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode), errorMessage, errorType);
			} else {
				if (errorType.equals(MESSAGE_TYPE_ABCS)) {
					retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode) + Global.ERR_ABCS_START, errorMessage, errorType);
				} else if (errorType.equals(MESSAGE_TYPE_MBASE)) {
					retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode) + Global.ERR_MBASE_START, errorMessage, errorType);
				} else if (errorType.equals(MESSAGE_TYPE_BLGW)) {
					if (Integer.parseInt(errorCode) > 1000) {
						if (Integer.parseInt(errorCode) == 1068) {
							retErrorInfo = new ErrorInfo(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT, errorMessage, errorType);
						} else {
							retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode), errorMessage, errorType);
						}
					} else {
						retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode) + Global.ERR_UDF_MP_START, errorMessage, errorType);
					}
				} else {
					retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode)+ Global.ERR_ABCS_START , errorMessage, errorType);
				}
			}
		} else {

			if (errorCode.length() > 0) {
				if (errorCode.indexOf("DSP003") > 0) {
					retErrorInfo = new ErrorInfo(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT, "System is running batch", errorType);
				}else if (errorCode.indexOf("DDM") >= 0) {
					retErrorInfo = new ErrorInfo(Integer.parseInt(errorCode.substring(errorCode.indexOf("DDM") + 3)) + ERR_MBASE_START, errorMessage, errorType);
				} 
				else {
					retErrorInfo = new ErrorInfo(ERR_MBASE_START, "MBASE error code", errorType);
				}
			} else {
				retErrorInfo = new ErrorInfo(ERR_SYSTEM_UNDEFINED_ERROR, "Error code not defined", errorType);
			}
		}
		return retErrorInfo;
	}

	private static Hashtable<String, CurrencyInfo> arrOfCurrency;
	static {
		initCurrencyInfo();
	}

	private static synchronized void initCurrencyInfo() {
		arrOfCurrency = new Hashtable<String, CurrencyInfo>();
		CurrencyInfoFacade currencyFacade = new CurrencyInfoFacade();
		try {
			ArrayList<CurrencyInfo> lstOfCurrency = currencyFacade.getAllCurrency();
			for (int i = 0; i < lstOfCurrency.size(); i++) {
				arrOfCurrency.put(lstOfCurrency.get(i).getCode(), lstOfCurrency.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CurrencyInfo findCurrencyByCode(String currCode) {
		if (arrOfCurrency != null && arrOfCurrency.containsKey(currCode)) {
			return arrOfCurrency.get(currCode);// .getRefCode();
		}
		return null;
	}

	/**
	 * ServiceInfo
	 */
	private static Hashtable<String, ServiceInfo> arrOfService;
	static {
		initService();
	}

	private static synchronized void initService() {
		arrOfService = new Hashtable<String, ServiceInfo>();

		ServiceInfoFacade serviceFacade = new ServiceInfoFacade();
		try {
			ArrayList<ServiceInfo> lstOfService = serviceFacade.getAllServiceInfo();
			for (int i = 0; i < lstOfService.size(); i++) {
				arrOfService.put(lstOfService.get(i).getService_id(), lstOfService.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServiceInfo getServiceInfoByServiceType(String serviceType) {
		if (arrOfService != null && arrOfService.containsKey(serviceType)) {
			return arrOfService.get(serviceType);
		}
		return null;
	}

	private static Hashtable<String, SysParam> arrOfSysParamDef;
	static {
		initSysParam();
	}

	private static synchronized void initSysParam() {
		arrOfSysParamDef = new Hashtable<String, SysParam>();
		SysParamFacade sysFacade = new SysParamFacade();
		try {
			ArrayList<SysParam> lstOfSysParam = sysFacade.getAllSystemParam();
			for (int i = 0; i < lstOfSysParam.size(); i++) {
				arrOfSysParamDef.put(lstOfSysParam.get(i).getParamName(), lstOfSysParam.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getSystemParam(String paramName) {
		if (arrOfSysParamDef != null && arrOfSysParamDef.containsKey(paramName)) {
			SysParam tempParam = arrOfSysParamDef.get(paramName);
			return tempParam.getParamValue().trim();
		}
		return "";
	}

	private static Hashtable<String, HostTranCodeInfo> arrOfHostTranCodeDef;
	static {
		initHostTranCode();
	}

	private static synchronized void initHostTranCode() {
		arrOfHostTranCodeDef = new Hashtable<String, HostTranCodeInfo>();

		HostTranCodeInfoFacade hostTranCodeInfoFacade = new HostTranCodeInfoFacade();
		try {
			ArrayList<HostTranCodeInfo> lstOfHostTranCodeInfo = hostTranCodeInfoFacade.getAllHostTranCodeInfo();
			for (int i = 0; i < lstOfHostTranCodeInfo.size(); i++) {
				arrOfHostTranCodeDef.put(lstOfHostTranCodeInfo.get(i).getRef_host_tran_code(), lstOfHostTranCodeInfo.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HostTranCodeInfo getHostTranCodeByRefTranCode(String ref_tran_code) {
		if (arrOfHostTranCodeDef != null && arrOfHostTranCodeDef.containsKey(ref_tran_code)) {
			return arrOfHostTranCodeDef.get(ref_tran_code);// .getReal_host_tran_code();
		}
		return null;
	}

	private static ArrayList<ChannelInfo> arrOfChannelInfo;
	static {
		initChannel();
	}

	private static synchronized void initChannel() {
		arrOfChannelInfo = new ArrayList<ChannelInfo>();
		ChannelInfoFacade channelFacade = new ChannelInfoFacade();
		try {
			arrOfChannelInfo = channelFacade.getAllChannelInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getRandomChannel() {
		if (arrOfChannelInfo != null) {
			int id = (int) (Math.random() * arrOfChannelInfo.size());
			return arrOfChannelInfo.get(id).getChannelName();
		}
		return null;
	}

	private static Hashtable<String, BankStaffInfo> arrOfBankUserInfo;
	static {
		initBankUser();
	}

	private static synchronized void initBankUser() {
		arrOfBankUserInfo = new Hashtable<String, BankStaffInfo>();
		BankStaffInfoFacade bankStaffInfoFacade = new BankStaffInfoFacade();
		try {
			ArrayList<BankStaffInfo> lstOfBankStaffInfo = bankStaffInfoFacade.getAllBankStaffInfo();
			for (int i = 0; i < lstOfBankStaffInfo.size(); i++) {
				arrOfBankUserInfo.put(lstOfBankStaffInfo.get(i).getUserid(), lstOfBankStaffInfo.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getRandomBankTeller() {
		return getSystemParam("APP_DEF_TELLER_ID");
	}

	public static String getRandomBankApprove() {
		return getSystemParam("APP_DEF_APPROVER_ID");
	}

	protected CachedParam() {
	}

	private static synchronized void initAllParams() {
		initCurrencyInfo();
		initService();
		initSysParam();
		initHostTranCode();
		initChannel();
		initBankUser();
	}

	// have to use synchronized keywork to prevent multithread acccess
	public static synchronized CachedParam getInstance(boolean reload) {
		if (reload == true)
			initAllParams();
		if (instance == null)
			instance = new CachedParam();
		return instance;
	}

	/**
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public static synchronized void setSystemParam(String paramName, String paramValue) {
		if (arrOfSysParamDef.containsKey(paramName)) {
			arrOfSysParamDef.get(paramName).setParamValue(paramValue);
		}
	}

	/**
	 * Prevent another class try to clone Singlenton object
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
