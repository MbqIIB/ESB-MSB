package vn.com.m1tech.AS400;

import java.net.Socket;

import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPMessageConstant;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.as400.dsp.TreasuryMessageFactory;

public class SOS {

	public boolean revertChargeCollection(Socket sk, String teller,
			String branchCode, String transDate, String sequence,
			String glAccount, String totalNetCharges, String baseCurrency,
			String glCurrency, String customerCurrency, String accountNumber,
			String drTotalNetCharge, String totalCharge, String vat,
			String remark, String transcode, String newSequence, String manager)
			throws Exception {
		
		boolean result = false;
		String[] strValue = TreasuryMessageFactory.revertChargeCollection(
				teller, branchCode, transDate, sequence, glAccount,
				totalNetCharges, baseCurrency, glCurrency, customerCurrency,
				accountNumber, drTotalNetCharge, totalCharge, vat, remark,
				transcode, newSequence, manager);

//		System.out.println("Message Input: ");
//		for (int i = 0; i < strValue.length; i++) {
//			System.out.print(strValue[i] + "|");
//		}

		byte[] btHold = DSPPackager.PACKAGER_ABCS_REQUEST.pack(strValue);

		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk
				.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager
				.getABCSResponsePackager(btResponse);

//		System.out.println("Message Output: ");
//		System.out.print(mbsdPackager.unpackMultiRecords(btResponse,
//				DSPMessageConstant.ABCS_NAME, "|"));

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS]
				.unpack(btResponse);
		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
}
