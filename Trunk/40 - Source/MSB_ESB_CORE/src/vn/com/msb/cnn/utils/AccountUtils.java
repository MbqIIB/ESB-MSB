package vn.com.msb.cnn.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import vn.com.msb.cnn.accounts.AccountSetting;

public class AccountUtils {

	public static String constructAccountNumber(int sequenceNumber,
			String branch, String currencyCode, String productCode) {
		//
		NumberFormat format = new DecimalFormat("000000");
		String strAcountNow = format.format(sequenceNumber).toString();
		String _currCode = currencyCode;
		String strAccountNow2 = branch + productCode + _currCode + strAcountNow;
		String checkDigit = null;
		try {
			checkDigit = calculateCheckDigit(strAccountNow2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strAccountNow2 + checkDigit;
	}

	public static String constructFDAccountNumber(int sequenceNumber,
			String branch, String currencyCode, String productCode)
			 {
		//
		NumberFormat format = new DecimalFormat("0000000");
		String strAcountNow = format.format(sequenceNumber).toString();
		String _currCode = currencyCode;
		String strAccountNow2 = branch + productCode + _currCode + strAcountNow;
		String checkDigit = null;
		try {
			checkDigit = calculateCheckDigit(strAccountNow2);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public static AccountSetting getAccountSequence(String _group_code,
			String _currency_code, String _branch_code) {
		AccountSetting accountSetting = null;// AccountSetting.findWhere(branch_code:_branch_code,group_code:_group_code,
		// currency_code:_currency_code);
		if (accountSetting != null) {
			// if(accountSetting.sequence < accountSetting.sequence_limit - 1)
			_increaseSeq(accountSetting);
			// else
			// return null
		}
		return accountSetting;
	}

	private static boolean _increaseSeq(AccountSetting accountSetting) {
		long seq = accountSetting.getSequence() + 1;
		accountSetting.setSequence(seq);
		// accountSetting.save(flush: true);
		return false;
	}

}
