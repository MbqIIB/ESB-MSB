//package multi.seatechit.mbconnector.utils;
//
//import java.io.IOException;
//
//import com.ibm.mq.MQEnvironment;
//import com.ibm.mq.MQException;
//import com.ibm.mq.MQGetMessageOptions;
//import com.ibm.mq.MQMessage;
//import com.ibm.mq.MQPutMessageOptions;
//import com.ibm.mq.MQQueue;
//import com.ibm.mq.MQQueueManager;
//import com.ibm.mq.constants.MQConstants;
//
//public class MQ_Client {
//
//	private MQQueueManager qMgr = null;
//
//	/**
//	 * @param args
//	 */
//	public MQ_Client(String hostName, String channel, String port, String qManager) {
//		if (qMgr == null || !qMgr.isConnected()) {
//			try {
//				MQEnvironment.hostname = hostName;
//				MQEnvironment.channel = channel;
//				MQEnvironment.port = Integer.parseInt(port);
//				qMgr = new MQQueueManager(qManager);
//			} catch (MQException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public String putAndGetMessage(String requestMsg, String sendQ, String receiveQ, int waitInterval) {
//
//		byte[] msgid_byte = null;
//		String returnMsg = "";
//		/*
//		 * PUT MESSAGE INTO INBOX
//		 */
//
//		try {
//			int inputOptions = MQConstants.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_OUTPUT;
//			MQQueue putQueue = qMgr.accessQueue(sendQ, inputOptions, null, null, null);
//			MQMessage message = new MQMessage();
//			MQPutMessageOptions pmo = new MQPutMessageOptions();
//
//			message.writeString(requestMsg);
//			putQueue.put(message, pmo);
//
//			// store message_id
//			msgid_byte = message.messageId;
//			// close put queue
//			putQueue.close();
//
//			/*
//			 * GET MESSAGE FROM OUTBOX
//			 */
//			int outputOptions = MQConstants.MQOO_INQUIRE | MQConstants.MQOO_FAIL_IF_QUIESCING | MQConstants.MQOO_INPUT_SHARED;
//			MQQueue getQueue = qMgr.accessQueue(receiveQ, outputOptions, null, null, null);
//
//			MQGetMessageOptions gmo = new MQGetMessageOptions();
//			gmo.options = MQConstants.MQGMO_FAIL_IF_QUIESCING | MQConstants.MQGMO_WAIT;
//			gmo.waitInterval = waitInterval;// doi 30s de lay msg ve 30000
//			MQMessage myMessage = new MQMessage();
//
//			// Get only message has correlationId= messageid above
//			myMessage.correlationId = msgid_byte;
//			getQueue.get(myMessage, gmo);
//			// return message
//			returnMsg = myMessage.readStringOfCharLength(myMessage.getMessageLength());
//			// Close all
//			getQueue.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			return returnMsg;
//		} catch (MQException e) {
//			e.printStackTrace();
//			return returnMsg;
//		} finally {
//			if (qMgr != null && qMgr.isConnected()) {
//				try {
//					qMgr.close();
//					qMgr.disconnect();
//				} catch (MQException e) {
//					e.printStackTrace();
//				}
//
//			}
//		}
//		return returnMsg;
//	}
//
//}