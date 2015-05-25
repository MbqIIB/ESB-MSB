package seatechit.esb.utils;

public class StringUtils {

	public static String arrayToString(String[] a) {
		StringBuffer result = new StringBuffer();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i = 1; i < a.length; i++) {
				result.append("|");
				result.append(a[i]);
			}
		}
		return result.toString();
	}

}
