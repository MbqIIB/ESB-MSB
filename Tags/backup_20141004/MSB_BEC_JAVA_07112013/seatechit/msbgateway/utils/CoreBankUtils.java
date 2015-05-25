package seatechit.msbgateway.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreBankUtils {

	/**
	 * 
	 * @param accountType
	 * @param orgStatus
	 * @return // '1', 'ACTV',--ACTIVE // '2', 'CLOS',--CLOSED // '3', 'MATU',--MATURED // '4', 'NEWR',--New Record // '5', 'ACTZ',--Active zero balance // '6', 'REST',--RESTRICTED // '7', 'NOPO',--NO
	 *         POST // '8', 'COUN',--Code unavailable // '9', 'DORM',--DORMANT
	 */
	public static String mappingAccountStatus(String accountType, String orgStatus) {
		orgStatus = orgStatus.toUpperCase();

		String retStatus = "";
		if (orgStatus.equalsIgnoreCase("Active".toUpperCase())) {
			retStatus = "1";
		} else if (orgStatus.equalsIgnoreCase("Closed".toUpperCase()) || orgStatus.equalsIgnoreCase("Paid-off".toUpperCase())) {
			retStatus = "2";
		} else if (orgStatus.equalsIgnoreCase("Matured".toUpperCase())) {
			retStatus = "3";
		} else if (orgStatus.equalsIgnoreCase("Newtoday".toUpperCase()) || orgStatus.equalsIgnoreCase("New today".toUpperCase())) {
			retStatus = "4";
		} else if (orgStatus.equalsIgnoreCase("Donotclose".toUpperCase()) || orgStatus.equalsIgnoreCase("Donot close".toUpperCase()) || orgStatus.equalsIgnoreCase("Do not close".toUpperCase())) {
			retStatus = "5";
		} else if (orgStatus.equalsIgnoreCase("Restricted".toUpperCase())) {
			retStatus = "6";
		} else if (orgStatus.equalsIgnoreCase("Frozen".toUpperCase())) {
			retStatus = "7";
		} else if (orgStatus.equalsIgnoreCase("Chargedoff".toUpperCase()) || orgStatus.equalsIgnoreCase("Charged off".toUpperCase())) {
			retStatus = "8";
		} else if (orgStatus.equalsIgnoreCase("Dormant".toUpperCase())) {
			retStatus = "9";
		} else {
			return "2";
		}
		return retStatus;
	}

	/**
	 * Append '0' character to the left of corebank account.
	 * 
	 * @param orgAccountNo
	 * @return
	 */
	public static String correctAccountNumber(String orgAccountNo) {
		if (orgAccountNo == null || orgAccountNo.length() == 0)
			return orgAccountNo;
		return AppUtils.padLeft(orgAccountNo, 14, '0');
	}

	/**
	 * Append '0' character to the left of corebank branch code.
	 * 
	 * @param orgBranchCode
	 * @return
	 */
	public static String correctBranchCode(String orgBranchCode) {
		if (orgBranchCode == null || orgBranchCode.length() == 0)
			return orgBranchCode;
		return AppUtils.padLeft(orgBranchCode, 3, '0');
	}

	public static String correctBankNumeric(String orgNum) {
		if (orgNum == null || orgNum.length() == 0)
			return "0";
		else {
			try {
				if (Double.parseDouble(orgNum) >= 0 && Double.parseDouble(orgNum) < 1) // loai bo truong hop .00102
					return String.valueOf(Double.parseDouble(orgNum));
				else
					return orgNum;
			} catch (NumberFormatException ne) {
				return "0";
			}
		}
	}

	/**
	 * Convert from corebank date format to ebank date format.
	 * 
	 * @param corebankDate
	 * @return
	 */
	public static String convertCB2EBDate(String corebankDate) {
		if (corebankDate == null || corebankDate.length() <= 0)
			return "";
		String tempStr = "";
		if (corebankDate.length() < 6) {
			tempStr = AppUtils.padLeft(corebankDate, 6, '0');
		} else {
			tempStr = corebankDate;

		}
		return (Integer.parseInt(tempStr.substring(4, 6)) > 20 ? "19" : "20") + tempStr.substring(4, 6) + tempStr.substring(2, 4) + tempStr.substring(0, 2);
	}
	
	

	public static String convertCB2EBDateYYYYMMdd(String corebankDate) {
		if (corebankDate == null)// || corebankDate.length() < 7)
			return "";
		String tempStr = corebankDate;
		if (tempStr.length() == 7) {
			return tempStr.substring(0, 4) + "0" + tempStr.substring(6, 7) + tempStr.substring(4, 6);
		} else if (tempStr.length() == 5 || tempStr.length() == 6) {
			return convertCB2EBDate(corebankDate);
		} else if (tempStr.length() == 8) {
			return tempStr.substring(0, 4) + tempStr.substring(6, 8) + tempStr.substring(4, 6);
		} else if (tempStr.length() < 5) {
			return "";
		} else {
			return corebankDate;
		}

	}

	/**
	 * Append two '0' to right of number.
	 * 
	 * @return
	 */
	public static String convertEB2CBNumeric(String inNumber) {
		if (AppUtils.isValidNumber(inNumber)) {
			return AppUtils.padRight(inNumber, inNumber.length() + 2, '0');
		}
		return "0";
	}

	/**
	 * 
	 * @param inNumberNumber
	 * @return
	 */
	public static String roundCBNumberic(String inNumber) {
		if (AppUtils.isValidNumber(inNumber)) {
			// inNumber =
			// String.valueOf(java.lang.Math.round(Double.valueOf(inNumber)));
			return inNumber;
		}
		return "0";
	}

	/**
	 * Check input account is valid coreabank account type or not.
	 * 
	 * @param accountType
	 * @return
	 */
	public static boolean isValidAccountType(String accountType) {
		String strValidAcctType = "DSTGL";
		if (accountType == null || accountType.length() == 0)
			return false;
		return strValidAcctType.contains(accountType);
	}

	/**
	 * Check input transaction amount is numeric and greater than '0' or not.
	 * 
	 * @param strAmount
	 * @return
	 */
	public static boolean isValidTransactionAmount(String strAmount) {
		if (AppUtils.isValidNumeric(strAmount)) {
			if (Double.parseDouble(strAmount) <= 0)
				return false;
			else
				return true;
		} else {
			return false;
		}
	}

	/**
	 * Reversal date from format ddMMyy to format yyMMdd
	 * 
	 * @param corebankDate
	 * @return
	 */
	public static String convertCBDateReversal(String corebankDate) {
		Date outDate = null;
		String eBankDate = convertCB2EBDate(corebankDate);
		if (eBankDate == null || eBankDate.length() <= 0)
			return "";
		// set the format to use as a constructor argument
		SimpleDateFormat sdfEbank = new SimpleDateFormat(Global.DEF_FORMAT_DATE_YYYYMMDD);
		SimpleDateFormat sdfCoreBank = new SimpleDateFormat("yyMMdd");

		if (eBankDate.trim().length() != sdfEbank.toPattern().length())
			return "";
		sdfEbank.setLenient(false);
		try {
			// parse the inDate parameter
			outDate = sdfEbank.parse(eBankDate.trim());
			return sdfCoreBank.format(outDate);
		} catch (ParseException pe) {
			return "";
		}
	}
	
	public static String convertDdMMyyToYyyyMMyy(String str){
		if (str == null || "".equals(str)) return str;
		if (str.length() < 6) {
			str = AppUtils.padLeft(str, 6, '0');
		}
		SimpleDateFormat sdfEbank = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfCoreBank = new SimpleDateFormat("ddMMyy");
		Date date = null;
		try {
			date = (Date)sdfCoreBank.parse(str);
		}catch(Exception ex){			
			ex.printStackTrace();
			return "";
		}
		
		return sdfEbank.format(date);
	}
	
	public static void main(String[] args){
		System.out.println(convertDdMMyyToYyyyMMyy("30312"));
	}
}
