package seatechit.msbgateway.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class, contain utility function using in application
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6
 */
public class AppUtils extends Global {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Chuyen tieng viet co dau thanh khong dau
	 */
	public static String convertToVietnamesNoSign(String inputStr) {
		String decomposed = Normalizer.normalize(inputStr, Form.NFD);
		String removed = decomposed.replaceAll("[^\\p{ASCII}]", "");
		return ReplaceCorebankSpecChar(UTF8Tool.coDau2KoDau(removed));
	}

	/*
	 * Replace special character of message before send to host
	 */
	public static String ReplaceCorebankSpecChar(String input) {
		//deny characters ~`#^*_<>;'{}[]|\
		if (input != null) {
			input = input.replace("}", "");
			input = input.replace("{", "");
			input = input.replace(";", "");
			input = input.replace("\\", "");
//			input = input.replace("_", "");
			input = input.replace("#", "");
			input = input.replace("*", "");
			input = input.replace("~", "");
			input = input.replace("`", "");
			input = input.replace("^", "");
			input = input.replace("'", "");
			input = input.replace("\\<", "");
			input = input.replace("<", "");
			input = input.replace("\\>", "");
			input = input.replace(">", "");
			input = input.replace("[", "");
			input = input.replace("]", "");
			input = input.replace("|", "");
			return input;
		} else {
			return "";
		}
	}

	/*
	 * Replace special character in XML message
	 */
	public static String XMLReplaceSpecChar(String input) {
		if (input != null) {
			input = input.replace("\\<", "");
			input = input.replace("<", "");
			input = input.replace("\\>", "");
			input = input.replace(">", "");
			return input;
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @param responseMessage
	 * @param startPos
	 * @param recordLength
	 * @return
	 */
	public static ArrayList<String[]> getListOfResponseRow(final String[] responseMessage, final int startPos, final int recordLength) {
		ArrayList<String[]> retArrString = new ArrayList<String[]>();
		int i = 0;
		while (startPos + recordLength * i < responseMessage.length) {
			int intMissingCharLength = responseMessage.length - (startPos + recordLength * (i + 1));
			String[] currentRecord = new String[recordLength];
			if (intMissingCharLength < 0) {
				System.arraycopy(responseMessage, (startPos + recordLength * i), currentRecord, 0, recordLength + intMissingCharLength);
			} else {
				System.arraycopy(responseMessage, (startPos + recordLength * i), currentRecord, 0, recordLength);

			}
			retArrString.add(currentRecord);
			i++;
		}
		return retArrString;
	}

	/**
	 * Converts java.util.Date to JulianDate format(yyyyD)
	 */
	public static Date getDateFromJulian7(String julianDate) throws ParseException {
		return new SimpleDateFormat("yyyyD").parse(julianDate);
	}

	/**
	 * Converts java.util.Date to JulianDate format(yyyyD)
	 */
	public static String getJulian7FromDate(Date date) {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return sb.append(cal.get(Calendar.YEAR)).append(String.format("%03d", cal.get(Calendar.DAY_OF_YEAR))).toString();
	}

	/**
	 * Get current date time in specified format.
	 */
	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * Check input string is valid number or not
	 */
	public static boolean isValidNumber(String str) {
		if (str == null || str.length() == 0)
			return false;
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Check input string is valid number or not
	 */
	public static boolean isValidNumeric(String str) {
		String s = str;
		if (str == null || str.trim().length() == 0)
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * Check input string is valid date time or not
	 */
	public static boolean isValidDate(String inputDate, String format) {
		if (inputDate == null)
			return false;
		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (inputDate.trim().length() != dateFormat.toPattern().length())
			return false;
		dateFormat.setLenient(false);
		try {
			// parse the inDate parameter
			dateFormat.parse(inputDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/*
	 * Convert date from yyyyMMdd ->ddMMyy format
	 */
	public static String convertYYYYMMDD_DDMMYY(String inputDate) {
		Date outDate = null;
		if (inputDate == null || inputDate.length() <= 0)
			return "";
		// set the format to use as a constructor argument
		SimpleDateFormat sdfEbank = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfCoreBank = new SimpleDateFormat("ddMMyy");

		if (inputDate.trim().length() != sdfEbank.toPattern().length())
			return "";
		sdfEbank.setLenient(false);
		try {
			// parse the inDate parameter
			outDate = sdfEbank.parse(inputDate.trim());
			return sdfCoreBank.format(outDate);
		} catch (ParseException pe) {
			return "";
		}
	}

	/**
	 *Convert array of character to string,each item separate by '|' character.
	 */
	public static String convertArrayToString(String[] listOfField) {
		StringBuffer buffSendMessage = new StringBuffer();
		if (listOfField != null) {
			for (int i = 1; i < listOfField.length; i++) {
				buffSendMessage.append((listOfField[i] == null ? "" : listOfField[i]) + "|");
			}
		} else {
			return "";
		}
		return buffSendMessage.toString();
	}

	/**
	 * Append from the left string n character
	 */
	public static String padLeft(String s, int minLen, char ch) {
		int numPadChars = minLen - s.length();
		if (numPadChars <= 0)
			return s; // nothing to pad
		StringBuffer sb = new StringBuffer(minLen);
		for (int i = 0; i < numPadChars; i++)
			sb.append(ch);
		sb.append(s);
		return sb.toString();
	}

	/**
	 * Append from the Right string n character
	 */
	public static String padRight(String s, int minLen, char ch) {
		int numPadChars = minLen - s.length();
		if (numPadChars <= 0)
			return s; // nothing to pad
		StringBuffer sb = new StringBuffer(minLen);
		sb.append(s);
		for (int i = 0; i < numPadChars; i++)
			sb.append(ch);
		return sb.toString();
	}

	/**
	 * 
	 * @param orgStr
	 * @param specLength
	 * @return
	 */
	public static String subStringBySpecLength(String orgStr, int specLength) {
		int numOfChar = orgStr.length() - specLength;
		if (numOfChar < 0)
			return orgStr;
		else
			return orgStr.substring(0, specLength);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToHexId(String str) {
		byte[] myId = str.getBytes();
		String hexAscii = "";
		for (int i = 0; i < myId.length; i++) {
			char b = (char) (myId[i] & 0xFF);
			if (b < 0x10) {
				hexAscii = hexAscii + "0";
			}
			hexAscii = hexAscii + (String) (Integer.toHexString(b)).toUpperCase();
		}
		return hexAscii;
	}

	/**
	 * 
	 * @param myId
	 * @return
	 */
	public static String dumpHexId(byte[] myId) {
		String retValue = "X'";
		for (int i = 0; i < myId.length; i++) {
			char b = (char) (myId[i] & 0xFF);
			if (b < 0x10) {
				retValue += "0";
			}
			retValue += (String) (Integer.toHexString(b)).toUpperCase();
		}
		retValue += "'";
		return retValue;
	}

	/*
	 * 
	 */
	public static String correctStringValue(String inputValue) {
		if (inputValue == null || inputValue.length() == 0)
			return "";
		return ReplaceCorebankSpecChar(String.valueOf(inputValue).trim());
	}

}
