import java.io.IOException;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class MQ_Sample {

	/**
	 * @param args
	 */
	public MQ_Sample() {
	}

	public void putAndGetMessage() {

		MQEnvironment.hostname = "localhost";
		MQEnvironment.channel = "SYSTEM.DEF.SVRCONN";
		MQEnvironment.port = Integer.parseInt("1414");
		String qManager = "ESB_QMGR";
		String SendQ = "ESB.INBOX";
		String ReceiveQ = "ESB.OUTBOX";
		byte[] msgid_byte = null;
		String requestMsg = "HELLO MSB-ESB SYSTEM1111111111111";

		/*
		 * PUT MESSAGE INTO INBOX
		 */
		try {
			MQQueueManager qMgr = new MQQueueManager(qManager);
			int openOptions = MQConstants.MQOO_OUTPUT
					| MQConstants.MQOO_FAIL_IF_QUIESCING;
			MQQueue putQueue = qMgr.accessQueue(SendQ, openOptions, null, null,
					null);
			MQMessage message = new MQMessage();
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			System.out.println("Request Message:" + requestMsg);
			message.writeString(requestMsg);
			putQueue.put(message, pmo);

			// store message_id
			msgid_byte = message.messageId;
			// close all
			putQueue.close();
			qMgr.disconnect();
		} catch (MQException ex) {
			System.err.println("An MQ error occurred : Completion code "
					+ ex.completionCode + " Reason code " + ex.reasonCode);
		} catch (java.io.IOException ex) {
			System.err.println("An error occurred writing to message buffer: "
					+ ex);
		}
		/*
		 * GET MESSAGE FROM OUTBOX
		 */
		try {
			MQQueueManager qMngr = new MQQueueManager(qManager);
			int openOptions = MQConstants.MQOO_INQUIRE
					| MQConstants.MQOO_FAIL_IF_QUIESCING
					| MQConstants.MQOO_INPUT_SHARED;
			MQQueue getQueue = qMngr.accessQueue(ReceiveQ, openOptions, null,
					null, null);

			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.options = MQConstants.MQGMO_SYNCPOINT
					| MQConstants.MQGMO_FAIL_IF_QUIESCING
					| MQConstants.MQGMO_WAIT;
			gmo.waitInterval = 30000;// doi 30s de lay msg ve
			MQMessage myMessage = new MQMessage();

			// Get only message has correlationId= messageid above
			myMessage.correlationId = msgid_byte;
			getQueue.get(myMessage, gmo);
			System.out.println("Response Message:" + myMessage.readLine());

			// Close all
			getQueue.close();
			qMngr.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MQException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		MQ_Sample obj = new MQ_Sample();
		obj.putAndGetMessage();
	}

}