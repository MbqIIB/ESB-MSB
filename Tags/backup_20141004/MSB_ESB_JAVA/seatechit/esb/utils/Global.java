package seatechit.esb.utils;

import java.io.Serializable;

public class Global implements Serializable {
	private static final long serialVersionUID = 1L;
	protected static final String DB2_JDBC_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
	protected static final String ORA_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

	public static final String DEF_FORMAT_LOGMSG_DATE = "dd/MM/yyyy";
	public static final String DEF_FORMAT_LOGMSG_TIME = "HH:mm:ss";

	public static final String DEF_FORMAT_DATE_TIME = "yyyyMMddHHmm";
	public static final String DEF_FORMAT_NUMBER = "0.00000";
	public static final String DEF_NULL_DATE = "01/10/1900";

	public static final String XML_REQ_BODY = "/Data/Body/";
	public static final String XML_RES_BODY = "/Data/Body/";

}
