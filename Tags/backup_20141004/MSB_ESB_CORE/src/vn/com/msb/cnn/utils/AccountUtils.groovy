package vn.com.msb.cnn.utils

import java.text.DecimalFormat;
import java.text.NumberFormat;

import vn.com.msb.cnn.accounts.AccountSetting;

class AccountUtils {
	
	private static String constructAccountNumber(long sequenceNumber,
            String branchCode, String groupCode, String currencyCode) throws Exception {
        NumberFormat format = new DecimalFormat("000000")
        String strAcountNow = format.format(sequenceNumber).toString()
        // String _currCode = getCurrCode(rinfo.getProperty("currencyCode"))
        // String strAccountNow2 = rinfo.getProperty("client_branch") +
        // rinfo.getProperty("productCode") + _currCode + strAcountNow
        String strAccountNow2 = branchCode + groupCode + currencyCode + strAcountNow
        String checkDigit = calculateCheckDigit(strAccountNow2)
        return strAccountNow2 + checkDigit
    }

	private static String constructFDAccountNumber(long sequenceNumber,
			String branchCode, String groupCode, String currencyCode) throws Exception {
		NumberFormat format = new DecimalFormat("0000000")
		String strAcountNow = format.format(sequenceNumber).toString()
		// String _currCode = getCurrCode(rinfo.getProperty("currencyCode"))
		// String strAccountNow2 = rinfo.getProperty("client_branch") +
		// rinfo.getProperty("productCode") + _currCode + strAcountNow
		String strAccountNow2 = branchCode + groupCode + currencyCode + strAcountNow
		String checkDigit = calculateCheckDigit(strAccountNow2)
		return strAccountNow2 + checkDigit
	}
				
    private static String calculateCheckDigit(String strCheck) throws Exception {
       //
		def w = [1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1]
		int sum = 0
		for (i in 0..12) {
			sum += w[i] * (strCheck.charAt(i) - 48)
		}
		
		//
		int d = sum % 10
		if (d != 0) d = 10 - d
		return d.toString()
    }
	
	public static def getAccountSequence(_group_code, _currency_code, _branch_code){
		AccountSetting accountSetting = AccountSetting.findWhere(branch_code:_branch_code,group_code:_group_code, currency_code:_currency_code)	
		if(accountSetting){
//			if(accountSetting.sequence < accountSetting.sequence_limit - 1)			
				_increaseSeq(accountSetting)
//			else 
//				return null
		}		
		return accountSetting
	}
	
	private static def _increaseSeq(accountSetting){
		long seq = accountSetting.sequence.toLong() + 1
		accountSetting.sequence = seq
		accountSetting.save(flush: true)
	}
	
//	public static def getSocketIPServer(){
//		String ipNumber = ""
//		switch(Environment.current) {
//			case Environment.DEVELOPMENT:
//			   ipNumber = "10.0.1.1"
//			break
//			case Environment.TEST:
//				ipNumber = "10.0.1.1"
//			break
//			case Environment.PRODUCTION:
//			   ipNumber = "10.2.1.1"
//			break
//		}
//		return ipNumber
//	}
	
}
