package seatechit.msbgateway.utils;

import java.io.Serializable;

/**
 * Contain global constant using in application
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6 *
 * 
 */
public class Global implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3735452520064835448L;
	public static final String APP_COREBANK_IP = "msbgw.corebankIP";
	public static final String APP_COREBANK_PORT = "msbgw.corebankPort";
	public static final String APP_COREBANK_USERNAME = "msbgw.corebankUserName";
	public static final String APP_COREBANK_PASSWORD = "msbgw.corebankPassword";

	public static final String APP_DBCONFIG_FILEPATH = "//opt//IBM//mqsi//8.0.0.1//bin//msbgateway_db.cfg";// Linux-production
	// public static final String APP_DBCONFIG_FILEPATH =
	// "msbgateway_db.cfg";//Moi truong Win-uat

	public static final String APP_SENDER = "IBS";
	public static final String APP_STOP = "stop";
	public static final String APP_START = "start";
	public static final String APP_VERSION = "1.0";

	// BASE VALUE
	public static final String BASED_CCYCD = "VND";
	public static final String BASED_VAT_RATE = "11";
	public static final String BASED_CCYCD_RATE = "10000000";

	// DEFAULT FORMAT
	public static final String DEF_FORMAT_DATE_DDMMYY = "ddMMyy";
	public static final String DEF_FORMAT_DATE_YYYYMMDD = "yyyyMMdd";
	public static final String DEF_FORMAT_LOGMSG_DATE_TIME = "HHmmss";
	public static final String DEF_FORMAT_CREATE_DATE_TIME = "yyyyMMddHHmmss";
	public static final String DEF_FORMAT_DATE_TIME = "yyyyMMddHHmm";
	public static final String DEF_FORMAT_NUMBER = "0.00000";
	public static final String DEF_FORMAT_SIBS_SEQ = "ddMMyyyy0000000";
	public static final String DEF_NULL_DATE = "01/10/1900";

	public static final String DEF_FORMAT_DATE_TIME_ALL = "dd/mm/yyyy";

	// XML HEADER
	public static final String BASE_XML_DOCUMENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><message></message>";
	public static final String XML_HDR_FLD_VERSION = "/message/head/field[@name='version']";
	public static final String XML_HDR_FLD_MESSAGE_TYPE = "/message/head/field[@name='message_type']";
	public static final String XML_HDR_FLD_MESSAGE_SN = "/message/head/field[@name='message_sn']";
	public static final String XML_HDR_FLD_SENDER_ID = "/message/head/field[@name='sender_id']";
	public static final String XML_HDR_FLD_SENDER_TRAN_SN = "/message/head/field[@name='sender_tran_sn']";
	public static final String XML_HDR_FLD_SEND_DATE = "/message/head/field[@name='send_date']";
	public static final String XML_HDR_FLD_SEND_TIME = "/message/head/field[@name='send_time']";
	public static final String XML_HDR_FLD_TRAN_CODE = "/message/head/field[@name='tran_code']";
	public static final String XML_HDR_FLD_RECEIVER_ID = "/message/head/field[@name='receiver_id']";
	public static final String XML_HDR_FLD_RECEIVER_TRAN_SN = "/message/head/field[@name='receiver_tran_sn']";
	public static final String XML_HDR_FLD_RESP_DATE = "/message/head/field[@name='resp_date']";
	public static final String XML_HDR_FLD_RESP_TIME = "/message/head/field[@name='resp_time']";
	public static final String XML_HDR_FLD_RESP_CODE = "/message/head/field[@name='resp_code']";
	public static final String XML_HDR_FLD_RESP_MSG = "/message/head/field[@name='resp_msg']";
	public static final String XML_HDR_FLD_BRANCH_CODE = "/message/head/field[@name='branch_code']";
	public static final String XML_HDR_FLD_TRAN_SERVICE_CODE = "/message/head/field[@name='tran_service_code']";

	// MESSAGES CODE AVAIABLE IN APP
	public static final String MSG_CM_SEND_SMS = "CM004";
	public static final String MSG_CM_SEND_EMAIL = "CM005";
	public static final String MSG_CM_INQUIRY_CIF_INFOR = "CM021";
	public static final String MSG_CM_INQUIRY_CIF_ACCOUNTS = "CM023";
	public static final String MSG_CM_INQUIRY_REL_CIF_ACCOUNTS = "CM024";
	public static final String MSG_CM_INQUIRY_MINISTATEMENT_BY_ACCOUNT = "CM040";

	public static final String MSG_OS_CREATE_FD_ACCOUNT = "OS002";
	public static final String MSG_OS_CREATE_FD_RECEIPT = "OS003";
	public static final String MSG_OS_TRANSFER_FD_RECEIPT = "OS004";
	public static final String MSG_OS_SETTLEMENT_FD_ACCOUNT = "OS006";

	public static final String MSG_MP_CREATE_BILLPAYMENT_TRANSACTION = "MP002";
	public static final String MSG_MP_INQUIRY_BILLPAYMENT_INFOR = "MP003";
	public static final String MSG_MP_INQUIRY_CREDITCARD_BILL_INFOR = "MP004";
	public static final String MSG_MP_PAYMENT_CREDITCARD_BILL = "MP005";

	public static final String MSG_CS_ACTIVATED_ATMCARD = "CS002";
	public static final String MSG_CS_APPLICATION_CREDITCARD = "CS003";

	public static final String MSG_TR_TRANSFER_MONEY = "TR003";
	public static final String MSG_TR_CREATE_CASHFLOW_TRANSACTION = "TR004";
	public static final String MSG_TR_CANCEL_SCHEDULE_TRANSFER = "TR005";
	public static final String MSG_TR_CANCEL_CASHFLOW_TRANSACTION = "TR006";

	public static final String MSG_LN_PAYMENT_LN_TRANSACTION = "LN004";
	public static final String MSG_FX_CREATE_EXCHANGEMONEY_TRANSACTION = "FX002";
	public static final String MSG_TF_SEND_REPORT_TO_PAYMENTCENTER = "TF001";

	// AFT
	public static final String MSG_SETTING_AFT = "AFT001";
	public static final String MSG_DESTROY_AFT = "AFT003";
	public static final String MSG_QUERY_AFT = "AFT002";
	public static final String MSG_QUERY_FD_RECEIPT = "OS005";

	// WU
	public static final String MSG_WU_TRANSACTION_INQUIRY = "WU001";
	public static final String MSG_WU_PICKUP_VALIDATE = "WU002";
	public static final String MSG_WU_PICKUP_RECEIVE = "WU003";

	public static final String MSG_FEE_CAL_FEE_OBJ = "FEE001";
	public static final String MSG_DSC_CAL_PROVIDER_SIMPLE = "DSC001";
	public static final String MSG_DSC_CAL_PROVIDER_OBJECT = "DSC002";

	public static final String MSG_OTP_CHECK_OTP = "OTP001";
	public static final String MSG_OTP_GET_OTP = "OTP002";

	public static final String MSG_RSA_Assign = "RSA001";
	public static final String MSG_RSA_checkOTP = "RSA002";
	public static final String MSG_RSA_CreateUser = "RSA003";
	public static final String MSG_RSA_deleteUser = "RSA004";
	public static final String MSG_RSA_Unassign = "RSA005";

	public static final int ERR_WU_CONNECT_SUCCESS = 0;

	// CONSUMER TYPE
	public static final String CONSUMER_CODE_SIBS = "SIBS";
	public static final String CONSUMER_CODE_SMS = "SMSGW";
	public static final String CONSUMER_CODE_SMLINK = "SMLINK";
	public static final String CONSUMER_CODE_EMAIL = "EMAIL";
	public static final String CONSUMER_CODE_WUABMT = "WUABMT";

	public static final String CONSUMER_CODE_FEE = "FEE";
	public static final String CONSUMER_CODE_OTP = "OTP";
	public static final String CONSUMER_CODE_RSA = "RSA";
	public static final String CONSUMER_CODE_DISCNT = "DISCNT";
	// COMMON ERROR CODE DEFINE
	public static final int ERR_SYSTEM_OK = 0;
	public static final int ERR_SYSTEM_UNKNOWN_ERROR = ERR_SYSTEM_OK - 1;
	public static final int ERR_SYSTEM_UNDEFINED_ERROR = ERR_SYSTEM_OK - 2;

	public static final int ERR_ABCS_START = ERR_SYSTEM_OK + 1000;
	public static final int ERR_MBASE_START = ERR_SYSTEM_OK + 2000;

	public static final int ERR_UDF_START = ERR_SYSTEM_OK;
	public static final int ERR_UDF_CM_START = ERR_UDF_START + 100;
	public static final int ERR_UDF_TR_START = ERR_UDF_START + 200;
	public static final int ERR_UDF_TF_START = ERR_UDF_START + 300;
	public static final int ERR_UDF_LN_START = ERR_UDF_START + 400;
	public static final int ERR_UDF_FX_START = ERR_UDF_START + 500;
	public static final int ERR_UDF_CS_START = ERR_UDF_START + 600;
	public static final int ERR_UDF_MP_START = ERR_UDF_START + 800;
	public static final int ERR_UDF_OT_START = ERR_UDF_START + 900;

	public static final int ERR_CM_START = ERR_SYSTEM_OK;
	public static final int ERR_CM_DOC_FORMAT_INCORRECT = ERR_CM_START + 1;
	public static final int ERR_CM_REQ_DOC_VERSION_MISSING = ERR_CM_START + 2;
	public static final int ERR_CM_REQ_DOC_MESSAGE_TYPE_MISSING = ERR_CM_START + 3;
	public static final int ERR_CM_REQ_DOC_MESSAGE_SN_MISSING = ERR_CM_START + 4;
	public static final int ERR_CM_REQ_DOC_SENDER_ID_MISSING = ERR_CM_START + 5;
	public static final int ERR_CM_REQ_DOC_SENDER_TRAN_SN_MISSING = ERR_CM_START + 6;
	public static final int ERR_CM_REQ_DOC_SEND_TIME_MISSING = ERR_CM_START + 7;
	public static final int ERR_CM_REQ_DOC_TRAN_CODE_MISSING = ERR_CM_START + 8;
	public static final int ERR_CM_REQ_DOC_RECEIVER_ID_MISSING = ERR_CM_START + 9;
	public static final int ERR_CM_APART_OF_TRAN_SUCESSFULLY = ERR_CM_START + 10;
	public static final int ERR_CM_REQ_DOC_TRAN_CODE_NOTFOUND = ERR_CM_START + 11;

	// CONNECT AND TIMEOUT ERROR
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT = ERR_CM_START + 12;
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_PAYMENTGW = ERR_CM_START + 13;
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_SMSGW = ERR_CM_START + 14;
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_EMAILGW = ERR_CM_START + 15;

	public static final int ERR_CM_EXCEPTION_WHEN_CALL_OTP = ERR_CM_START + 16;
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_RSA = ERR_CM_START + 17;
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_FEE = ERR_CM_START + 18;
	public static final int ERR_CM_EXCEPTION_WHEN_CALL_DISCNT = ERR_CM_START + 19;

	// WU ERR
	public static final int ERR_WU_EXCEPTION_WHEN_CALL_GW = ERR_UDF_OT_START + 1;
	public static final int ERR_WU_ONE_OF_RSP_NULL = ERR_UDF_OT_START + 2;

	// OV tu 2000 tro di
	public static final int ERR_OV_MAX_FREQ = ERR_MBASE_START + 459;

	public static final int ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 1;
	public static final int ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 2;
	public static final int ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 3;
	public static final int ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 4;
	public static final int ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 5;
	public static final int ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 6;
	public static final int ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 7;
	public static final int ERR_UDF_CERT_TYPE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 8;
	public static final int ERR_UDF_CEILLING_AMT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 9;
	public static final int ERR_UDF_FLOOR_AMT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 10;
	public static final int ERR_UDF_BALANCE_TYPE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 11;
	public static final int ERR_UDF_FEE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 12;
	public static final int ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 13;
	public static final int ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 14;
	public static final int ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 15;
	public static final int ERR_UDF_CANNOT_CREATE_FDRECEIPT = ERR_UDF_CM_START + 16;
	public static final int ERR_UDF_CANNOT_CREATE_FDACCOUNT = ERR_UDF_CM_START + 17;
	public static final int ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 18;
	public static final int ERR_UDF_FDACCOUNT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 19;
	public static final int ERR_UDF_FDPRODUCT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 20;
	public static final int ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 21;
	public static final int ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 22;
	public static final int ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 23;
	public static final int ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 24;
	public static final int ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 25;
	public static final int ERR_UDF_PAID_BILL_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 26;
	public static final int ERR_UDF_TRANSFER_TYPE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 27;
	public static final int ERR_UDF_IS_BATCH_TRAN_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 28;
	public static final int ERR_UDF_TRAN_SN_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 29;
	public static final int ERR_UDF_CARD_NO_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 30;
	public static final int ERR_UDF_TRANSFER_PRODUCT_CODE_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 31;
	public static final int ERR_UDF_TRANSFER_BANK_ID_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 32;
	public static final int ERR_UDF_TRANSFER_BANK_NAME_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 33;
	public static final int ERR_UDF_TRANSFER_RECEIVE_ACCOUNT_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 34;
	public static final int ERR_UDF_TRANSFER_RECEIVE_NAME_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 35;
	public static final int ERR_UDF_TRANSFER_CIF_NO_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 36;
	public static final int ERR_UDF_TRANSFER_CIF_NAME_NOT_VALID_OR_MISSING = ERR_UDF_CM_START + 37;

	public static final int ERR_UDF_CASHFLOW_RECORD_NOTFOUND = ERR_UDF_CM_START + 40;
	public static final int ERR_UDF_CASHFLOW_ACCOUNT_ALREADY_SECONDARY = ERR_UDF_CM_START + 41;
	public static final int ERR_UDF_CASHFLOW_ACCOUNT_PRIMARY_HIGHERLEVEL = ERR_UDF_CM_START + 42;
	public static final int ERR_UDF_ACTIVE_ATM_CARD_NOTFOUND = ERR_UDF_CM_START + 46;
	public static final int ERR_UDF_ACTIVE_ATM_CARD_NOTREACTIVE = ERR_UDF_CM_START + 47;

	// SOME DEFAULT PARAM
	public static final String APP_DEFAULT_CLIENT_NAME = "kienvt";
	public static final String APP_DEFAULT_CLIENT_ADDR = "10.1.1.1";
	public static final String APP_DEFAULT_BRANCH_CODE = "011";
	public static final String APP_DEFAULT_TELLER_ID = "EBANKING01";
	public static final String APP_DEFAULT_APPROVER_ID = "EBANKING02";

	// PARAM STRI G
	public static final String APP_APPROVER_ID = "APP_APPROVER_ID";
	public static final String APP_TELLER_ID = "APP_TELLER_ID";
	public static final String APP_TRAN_DATE = "APP_TRAN_DATE";
	public static final String APP_TRAN_SN = "APP_TRAN_SN";
	public static final String APP_REF_CHANNEL = "APP_REF_CHANNEL";
	public static final String APP_REF_HOST_TRAN_CODE = "APP_REF_HOST_TRAN_CODE";
	public static final String APP_SERVICE_URL = "APP_SERVICE_URL";

	// HOST MESSAGE TYPE
	public static final String MESSAGE_TYPE_MBASE = "MESSAGE_TYPE_MBASE";
	public static final String MESSAGE_TYPE_ABCS = "MESSAGE_TYPE_ABCS";
	public static final String MESSAGE_TYPE_BLGW = "MESSAGE_TYPE_BLGW";
	public static final String MESSAGE_TYPE_EMGW = "MESSAGE_TYPE_EMGW";
	public static final String MESSAGE_TYPE_SMGW = "MESSAGE_TYPE_SMGW";
	public static final String MESSAGE_TYPE_WUABMT = "MESSAGE_TYPE_WUABMT";
	public static final String MESSAGE_TYPE_OTHER = "MESSAGE_TYPE_OTHER";
	public static final String MESSAGE_TYPE_FEE = "MESSAGE_TYPE_FEE";
	public static final String MESSAGE_TYPE_OTP = "MESSAGE_TYPE_OTP";
	public static final String MESSAGE_TYPE_RSA = "MESSAGE_TYPE_RSA";

	// MESSAGE TYPE FOR BILLING
	public static final String MSG_TYPE_BILLING_INQUIRY = "1102";// OFF
	public static final String MSG_TYPE_BILLING_PAYMENT = "1200";// OFF

	// COREBANK ACCOUNT TYPE
	public static final String COREBBANK_LN_ACCOUNT_TYPE = "L";// loan acct
	public static final String COREBBANK_CA_ACCOUNT_TYPE = "D";// ca acct
	public static final String COREBBANK_FD_GROUP_ACCOUNT_TYPE = "G";// fd group
	public static final String COREBBANK_FD_RECEIPT_ACCOUNT_TYPE = "T";// fdacct
	public static final String COREBBANK_SA_ACCOUNT_TYPE = "S";// sa acct

	// Key for SysParam
	public static final String CBS_HOST_IP = "CBS_HOST_IP";
	public static final String CBS_HOST_USER = "CBS_HOST_USER";
	public static final String CBS_HOST_PASSWORD = "CBS_HOST_PASSWORD";
	public static final String APP_TRAN_STATUS = "APP_TRAN_STATUS";
	public static final String APP_TRAN_NUM = "APP_TRAN_NUM";
	public static final String APP_DEF_BRANCH = "APP_DEF_BRANCH";
	public static final String APP_DEF_TELLER_ID = "APP_DEF_TELLER_ID";
	public static final String APP_DEF_APPROVER_ID = "APP_DEF_APPROVER_ID";
	public static final String APP_WS_NAME = "APP_WS_NAME";
	public static final String APP_WS_IP = "APP_WS_IP";
	public static final String VAT_FEE_RATE = "VAT_FEE_RATE";
	public static final String MAX_RECORD_RETRIEVE = "MAX_RECORD_RETRIEVE";
	public static final String APP_LOGPATH = "APP_LOGPATH";
	public static final String PARAM_GL_INTER_BANK_TRANSFER = "GL_INTER_BANK_TRANSFER";
	public static final String PARAM_GL_SALARY_SRC_BRANCH = "GL_SALARY_SRC_BRANCH";
	public static final String PARAM_GL_HOLD_AMOUNT = "GL_HOLD_AMOUNT";
	public static final String PARAM_GL_HOLD_VAT = "GL_HOLD_VAT";
	public static final String PARAM_GL_HOLD_FEE = "GL_HOLD_FEE";

	// Sender ALLOW
	public static final String APP_SENDER_SMS = "SMS";// SMS_APP
	public static final String APP_SENDER_MOBILE_APP = "MBA";// MOBILE_APP
	public static final String APP_SENDER_MOBILE_WAP = "WAP";// WAP
	public static final String APP_SENDER_IBS = "IBS";// INTERNET

	// Receiver ALLOW
	public static final String APP_RECEIVER_SMS = "SMS";// SMS_SYSTEM
	public static final String APP_RECEIVER_EML = "EML";// EMAIL_SYSTEM
	public static final String APP_RECEIVER_CBS = "CBS";// CORE_BANK
	public static final String APP_RECEIVER_SML = "SML";// SMART_LINK

	// REF TRANCODE
	public static final String SIBS_001 = "SIBS_001";
	public static final String SIBS_002 = "SIBS_002";
	public static final String SIBS_003 = "SIBS_003";
	public static final String SIBS_004 = "SIBS_004";
	public static final String SIBS_005 = "SIBS_005";
	public static final String SIBS_006 = "SIBS_006";
	public static final String SIBS_007 = "SIBS_007";
	public static final String SIBS_008 = "SIBS_008";
	public static final String SIBS_009 = "SIBS_009";
	public static final String SIBS_010 = "SIBS_010";
	public static final String SIBS_011 = "SIBS_011";
	public static final String SIBS_012 = "SIBS_012";
	public static final String SIBS_013 = "SIBS_013";
	public static final String SIBS_014 = "SIBS_014";
	public static final String SIBS_015 = "SIBS_015";
	public static final String SIBS_016 = "SIBS_016";
	public static final String SIBS_017 = "SIBS_017";
	public static final String SIBS_018 = "SIBS_018";
	public static final String SIBS_019 = "SIBS_019";
	public static final String SIBS_020 = "SIBS_020";
	public static final String SIBS_021 = "SIBS_021";
	public static final String SIBS_022 = "SIBS_022";
	public static final String SIBS_023 = "SIBS_023";
	public static final String SIBS_024 = "SIBS_024";
	public static final String SIBS_025 = "SIBS_025";
	public static final String SIBS_026 = "SIBS_026";
	public static final String SIBS_032 = "SIBS_032";
	public static final String SIBS_033 = "SIBS_033";
	public static final String SIBS_034 = "SIBS_034";
	public static final String SIBS_035 = "SIBS_035";
	public static final String SIBS_036 = "SIBS_036";
	public static final String SIBS_037 = "SIBS_037";
	public static final String SIBS_038 = "SIBS_038";
	public static final String SIBS_039 = "SIBS_039";
	public static final String SIBS_040 = "SIBS_040";

	public static final String SIBS_050 = "SIBS_050";
	public static final String SIBS_051 = "SIBS_051";
	public static final String SIBS_052 = "SIBS_052";
	public static final String SIBS_053 = "SIBS_053";

	// REF_TRANCODE FOR SALARY
	public static final String SIBS_081 = "SIBS_081";
	public static final String SIBS_082 = "SIBS_082";
	public static final String SIBS_083 = "SIBS_083";
	public static final String SIBS_084 = "SIBS_084";
	public static final String SIBS_085 = "SIBS_085";

	// tạo lịch AFT. AFT001
	public static final String SIBS_27121 = "SIBS_27121";
	// hủy lịch AFT AFT003
	public static final String SIBS_29121 = "SIBS_29121";

	// lock AFT
	public static final String SIBS_26121 = "SIBS_26121";
	// transferCAtoFD
	public static final String SIBS_3603 = "SIBS_3603";

	// ACCOUNT STATUS
	public static final int STATUS_ACTIVE = 0;
	public static final int STATUS_INACTIVE = 1;

	// ROLE TYPE
	public static final int BANK_USER_ROLE_TELLER = 0;
	public static final int BANK_USER_ROLE_APPROVER = 1;

	// MESSAGE TYPE,REQUEST/RESPONSE
	public static final int EBANK_MESSAGE_TYPE_REQUEST = 0;
	public static final int EBANK_MESSAGE_TYPE_RESPONSE = 1;

	// SEQUENCE ID
	public static final int DEFAULT_RECORD_LENGTH = 10;
	public static final String SEQ_TRAN_SN = "SEQ_TRAN_SN";
	public static final String SEQ_RM_NO = "SEQ_RM_NO";
	public static final String SEQ_SAL_NO = "SEQ_SAL_NO";
	public static final String ERR_SYSTEM_OK_MSG = "System Ok";

	// MESSAGE NEED SYSCHRONIZED OR NOT
	public static final String NEED_SYNCHRONIZED_YES = "Y";
	public static final String NEED_SYNCHRONIZED_NO = "N";

	// ACCOUNT STATUS
	public static final String CIF_ACCT_STATUS_ACTV = "1";// ACTIVE
	public static final String CIF_ACCT_STATUS_CLOS = "2";// CLOSED
	public static final String CIF_ACCT_STATUS_MATU = "3";// MATURED
	public static final String CIF_ACCT_STATUS_NEWR = "3";// NEWRECORD
	public static final String CIF_ACCT_STATUS_ACTZ = "5";// Active zero balance
	public static final String CIF_ACCT_STATUS_REST = "6";// RESTRICTED
	public static final String CIF_ACCT_STATUS_NOPO = "7";// NO POST
	public static final String CIF_ACCT_STATUS_COUN = "8";// Code unavailable
	public static final String CIF_ACCT_STATUS_DORM = "9";// DORMANT
	public static final String FD_ACCOUNT_HAS_PASSBOOK = "1";// DORMANT
	public static final String FD_ACCOUNT_NO_PASSBOOK = "2";// DORMANT

	//
	public static final int DB_MIN_POOL_SIZE = 10;
	public static final int DB_MAX_POOL_SIZE = 100;
}
