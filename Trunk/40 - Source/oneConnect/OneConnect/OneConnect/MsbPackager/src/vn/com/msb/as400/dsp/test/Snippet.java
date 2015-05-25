package vn.com.msb.as400.dsp.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Snippet {
	
	public static String constructAccountNumber(int sequenceNumber,
				String currencyCode, String branch, String productCode)
				throws Exception {
			//
			NumberFormat format = new DecimalFormat("0000000");
			String strAcountNow = format.format(sequenceNumber).toString();
			String _currCode = currencyCode;
			String strAccountNow2 = branch + productCode + _currCode + strAcountNow;
			String checkDigit = calculateCheckDigit(strAccountNow2);
			return strAccountNow2 + checkDigit;
		}
	
		private static String calculateCheckDigit(String strCheck) throws Exception {
			int intWeight[] = { 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1 };
			char[] cArray = strCheck.toCharArray();
			float kq = 0;
			int calsum = 0, divi = 10;
			for (int i = 0; i < cArray.length; i++) {
				calsum = calsum + Integer.valueOf(String.valueOf(cArray[i]))
						* (intWeight[i]);
			}
			kq = calsum % divi;
			kq = (kq == 0) ? kq : divi - kq;
			return String.valueOf((int) kq);
		}
	
}

