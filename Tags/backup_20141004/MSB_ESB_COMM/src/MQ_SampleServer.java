import java.io.IOException;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class MQ_SampleServer extends Thread {

	public synchronized void run() {
		boolean bExit = false;
		MQEnvironment.hostname = "localhost";
		MQEnvironment.channel = "SYSTEM.DEF.SVRCONN";
		MQEnvironment.port = Integer.parseInt("1414");
		String qManager = "ESB_QMGR";
		String ReceiveQ = "ESB.INBOX";
		String SendQ = "ESB.OUTBOX";

		byte[] msgid_byte = null;

		try {
			MQQueueManager qMngr;

			qMngr = new MQQueueManager(qManager);

			// Setup for get message
			int openGetOptions = MQConstants.MQOO_INQUIRE
					| MQConstants.MQOO_FAIL_IF_QUIESCING
					| MQConstants.MQOO_INPUT_SHARED;

			MQQueue myQueue = qMngr.accessQueue(ReceiveQ, openGetOptions, null,
					null, null);

			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.options = MQConstants.MQGMO_FAIL_IF_QUIESCING
					| MQConstants.MQGMO_WAIT;
			gmo.waitInterval = 3000;
			// --end get

			// Setup for put message
			MQQueueManager qMgr = new MQQueueManager(qManager);
			int openPutOptions = MQConstants.MQOO_OUTPUT
					| MQConstants.MQOO_FAIL_IF_QUIESCING;
			MQQueue SDLQ = qMgr.accessQueue(SendQ, openPutOptions, null, null,
					null);
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			// --end put

			do {
				/*
				 * GET MESSAGE FROM INBOX
				 */
				try {

					MQMessage myMessage = new MQMessage();
					myQueue.get(myMessage, gmo);
					msgid_byte = myMessage.messageId;
					String msgRequest = myMessage.readLine();
					System.out.println("Request Message:" + msgRequest);
					// 
					// qMngr.disconnect();

					/*
					 * PUT MESSAGE INTO INBOX
					 */
					if (msgRequest.length() > 0) {
						MQMessage message = new MQMessage();
						message.correlationId = msgid_byte;
						message.writeString("Hello Client" + msgRequest);
						SDLQ.put(message, pmo);
					}
					qMgr.commit();

				} catch (MQException e) {
					if (e.completionCode == 2
							&& e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
					} else {
						e.printStackTrace();
					}
				}
			} while (!bExit);
			myQueue.close();
			SDLQ.close();
			qMgr.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MQException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		MQ_SampleServer obj = new MQ_SampleServer();
		obj.start();
		System.in.read();
	}

}
