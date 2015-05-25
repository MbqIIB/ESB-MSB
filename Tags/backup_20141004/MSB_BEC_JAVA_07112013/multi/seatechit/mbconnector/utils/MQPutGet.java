//package multi.seatechit.mbconnector.utils;
//
//import com.ibm.mq.MQC;
//import com.ibm.mq.MQEnvironment;
//import com.ibm.mq.MQException;
//import com.ibm.mq.MQGetMessageOptions;
//import com.ibm.mq.MQMessage;
//import com.ibm.mq.MQPutMessageOptions;
//import com.ibm.mq.MQQueue;
//import com.ibm.mq.MQQueueManager;
//
//public class MQPutGet {
//
//	public MQPutGet() {
//	}
//
//	public MQMessage  MQGetMessage(MQQueue queue) throws MQException {
//		MQMessage retrievedMessage = null;
//		retrievedMessage = new MQMessage();
//		MQGetMessageOptions gmo = new MQGetMessageOptions();
//		queue.get(retrievedMessage, gmo);
//
//		return retrievedMessage;
//	}
//
//	public MQQueueManager connectionQMGR(String qManager, String hostName,String channel, int port)  {
//		MQEnvironment.hostname = hostName;
//		MQEnvironment.channel = channel;
//		MQEnvironment.port = port;
//		MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);
//		MQQueueManager qMgr=null;
//		try {
//			qMgr = new MQQueueManager(qManager);
//		} catch (MQException e) {
//			e.printStackTrace();
//		}
//		return qMgr;
//	}
//	public MQQueue accessQueue(MQQueueManager qMgr, String qName) {
//		int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_INQUIRE| MQC.MQOO_OUTPUT;
//		MQQueue queue=null;
//		try {
//			queue = qMgr.accessQueue(qName, openOptions, null, null,null);
//		} catch (MQException e) {
//			e.printStackTrace();
//		}
//		return queue;
//	}
//
//	public void MQPutMessage(MQQueue queue, MQMessage mqPutMsg) throws MQException {
//			// the options on the queue
//			MQPutMessageOptions pmo = new MQPutMessageOptions();
//			queue.put(mqPutMsg, pmo);	
//
//	}
//
//	public static void main(String[] agrs) {
//		String qManagerFrom = "KBNNQMR";
//		String qManagerTo = "KBNNMQMGR";
//		String qNameFrom = "HTTH.INBOX.QUEUE";
//		String qNameTo = "HTTH.INBOX.QUEUE";
//		MQQueueManager qMgrFrom=null;
//		MQQueueManager qMgrTo=null;
//		MQQueue qFrom=null;
//		MQQueue qTo=null;
//		MQPutGet i = new MQPutGet();
//		qMgrFrom=i.connectionQMGR(qManagerFrom, "192.168.1.43", "JAVA_CHANNEL", 1414);
//		qMgrTo=i.connectionQMGR(qManagerTo, "192.168.1.154", "JAVA_CHANNEL", 1414);
//		qFrom=i.accessQueue(qMgrFrom, qNameFrom);
//		qTo=i.accessQueue(qMgrTo, qNameTo);
//		boolean isStopLoop=true;
//		while (isStopLoop) {
//			try{
//				MQMessage  mqPutMsg=i.MQGetMessage(qFrom);
//				i.MQPutMessage(qTo, mqPutMsg);
//			}catch(MQException e){
//				if(e.getMessage().indexOf("2033")>0)
//					isStopLoop=false;					
//				try {
//					qFrom.close();
//					qMgrFrom.close();
//					qTo.close();
//					qMgrTo.close();
//				} catch (MQException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//		
//		
//	}
//}
