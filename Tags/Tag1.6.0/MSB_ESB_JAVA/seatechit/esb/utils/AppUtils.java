package seatechit.esb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppUtils {
	/**
	 * Convert array of character to string,each item separate by '|' character.
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
	 * Get branch length 3
	 */
	public static String subBranch(String branch) {
		if(branch != null && branch.length() == 5) {
			return branch.substring(3);
		} else {
			return branch;
		}
		 
	}
}
