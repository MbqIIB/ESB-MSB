package vn.com.msb.cnn.processor;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import vn.com.msb.as400.dsp.TreasuryMessageFactory;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.*;

public class MtradingProcessor {
	static {
		try {
			DOMConfigurator.configure(Global.APP_LOG_FILEPATH);
		} catch (Exception ex) {
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static int tranferGLtoGL(HostParameter in_hostParam, String channel, String refID, String debitGLBranch, String debitGLAccount, String creditGLBranch,
			String creditGLAccount, String amount, String currency, String comments, String teller, String manager, String hostDate, String reconcileGL, String transactionOffice,
			String customerTypeCode, String account, String businessDeptCode, String branchCode5, String hostIP) {
		int result = 0;
		Messages retMessage;
		Messages retDebitMessage;
		Messages retCreditMessage;
		Messages retApproveMessage;
		try {
			String cif_no = null;

			String[] retArrCustomerInfo = new String[19];
			HostParameter hostParam = in_hostParam;// HostParameter.findByNameAndValue("port_number",
													// channel.toUpperCase());

			// Create Batch
			// Logger.getLogger(MtradingProcessor.class).info("MTD: Create Batch GL ...");
			// Logger.getLogger(MtradingProcessor.class).info(
			// "Params: " + refID + " " + debitGLBranch + " " + debitGLAccount +
			// " " + creditGLBranch + " " + creditGLAccount + " " + amount + " "
			// + currency + " " + comments
			// + " " );
			int port = Integer.parseInt(hostParam.getParam1());
			String[] createBatch = TreasuryMessageFactory.createGLTransactionMessage(teller, "10.1.17.45", debitGLBranch, currency, hostDate);
			retMessage = HostProcessor.sendMessage(port, createBatch, channel.toUpperCase(), hostIP);

			if (retMessage != null) {
				if (retMessage.getErrCode() == "0") {
					String[] tmpArr = retMessage.getArrString();
					String batchNo = tmpArr[68];

					String[] debitArr = TreasuryMessageFactory.GLTransaction(teller, debitGLBranch, batchNo, "D", amount, debitGLAccount, refID, comments, reconcileGL,
							transactionOffice, customerTypeCode, account, businessDeptCode, branchCode5);
					String[] creditArr = TreasuryMessageFactory.GLTransaction(teller, creditGLBranch, batchNo, "C", amount, creditGLAccount, refID, comments, reconcileGL,
							transactionOffice, customerTypeCode, account, businessDeptCode, branchCode5);
					
					// GLTransaction(
					// teller, creditGLBranch, batchNo, "C", amount,
					// creditGLAccount, refID, comments);
					String[] approveBatch = TreasuryMessageFactory.ApproveGLTransaction(manager, debitGLBranch, batchNo);

					// Debit
					retDebitMessage = HostProcessor.sendMessage(port, debitArr, channel.toUpperCase(), hostIP);
					// Credit
					retCreditMessage = HostProcessor.sendMessage(port, creditArr, channel.toUpperCase(), hostIP);
					// Approve
					retApproveMessage = HostProcessor.sendMessage(port, approveBatch, channel.toUpperCase(), hostIP);

					if (!(retDebitMessage != null && retDebitMessage.getErrCode() == "0")) {
						result += 1;
						Logger.getLogger(MtradingProcessor.class).info("MTD: Error Debit message " + retApproveMessage.getErrCode());
					}
					if (!(retCreditMessage != null && retCreditMessage.getErrCode() == "0")) {
						result += 2;
						Logger.getLogger(MtradingProcessor.class).info("MTD: Error Credit message " + retApproveMessage.getErrCode());
					}
					if (!(retApproveMessage != null && retApproveMessage.getErrCode() == "0")) {

						result += 4;
						Logger.getLogger(MtradingProcessor.class).info("MTD: Error Approved message " + retApproveMessage.getErrCode());
					}
				} else {
					result += 8;
					Logger.getLogger(MtradingProcessor.class).info("MTD: Error Create message ");
				}
			} else {
				result += 8;
				Logger.getLogger(MtradingProcessor.class).info("MTD: Error Create message ");
			}
		} catch (Exception ex) {
			result += 16;
			Logger.getLogger(MtradingProcessor.class).info("MTD: Error ");
			Logger.getLogger(MtradingProcessor.class).info(ex.toString());
		}
		return result;
	}
}
