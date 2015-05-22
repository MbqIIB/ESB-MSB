package vn.com.msb.cnn.utils;

public class StringUtils {

	public static String arrayToString(String[] a) {
		StringBuffer result = new StringBuffer();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i = 1; i < a.length; i++) {
				result.append(HostConstants.localSeparator);
				result.append(a[i]);
			}
		}
		return result.toString();
	}

	public static String formatMessage(String message, int number) {
		if (message == null || message.equals("")) {
			return "";
		}

		String[] strArray = message.split(HostConstants.separator);
		int i = 0;
		StringBuffer buffer = new StringBuffer("");
		for (String str : strArray) {
			i++;
			if (i < number) {
				buffer.append(org.apache.commons.lang.StringUtils.trim(str));
				buffer.append(" ");
				buffer.append(HostConstants.localSeparator);
			}
		}

		return buffer.toString();
	}
}
