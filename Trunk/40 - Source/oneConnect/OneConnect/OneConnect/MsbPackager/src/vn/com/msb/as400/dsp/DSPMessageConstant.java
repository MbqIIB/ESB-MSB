package vn.com.msb.as400.dsp;

public class DSPMessageConstant
{
	// Common values
	public static int MESSAGE_LENGTH;
	public static byte[] MESSAGE_LENGTH_FIELD = new byte[4];
	public static int IDX_MESSAGE_STATUS = 13;
	public static String STATUS_SUCCESS = "/0000000";

	// CA Inquiry Response
	public static int INQUIRY_RESPONSE_IDX_OWNER_NAME = 46;
	public static int INQUIRY_RESPONSE_IDX_BALANCE = 96;
	public static int INQUIRY_RESPONSE_IDX_AVAILABLE = 97;

	//ABCS Response Code
	public static int ABCS_RESPONSE_FORMAT_CODE = 43;
	
	//MBASE Response Code
	public static int MBASE_RESPONSE_FORMAT_CODE = 69;//

	//ABCS Name
	public static String ABCS_NAME = "ABCS";

	//MBSD Name
	public static String MBSD_NAME = "MBSD";

	//DSP Data Format Id
	public static int DSP_DATA_FORMAT_ID = 9;

	// MBSD Request Package
	public static int UNHOLD_IDX_MESSAGE_ID = 69;

	// MBSD Response Package
	public static int ERROR_POSITION = 52;
	public static String ERROR_RECORD__NOT_FOUND = "MB28141 : Delete Operation Fail. Record Not Found."; // Delete Operation Fail. Record Not Found.
	public static String ERROR_LOCK_RECORD__NOT_FOUND = "MB26141 : No records found."; // MB26141 : No records found.

	// Response Format Position
	public static int RESPONSE_POSITION = 45;//45
	
	// Hold Response Format Position
	public static int HOLD_RESPONSE_POSITION = 45;

	// Unhold Response Format Position
	public static int UNHOLD_RESPONSE_POSITION = 45;

}
