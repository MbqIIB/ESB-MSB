//import java.rmi.RemoteException;
//
//import javax.xml.rpc.ServiceException;
//
//import vn.com.msb.cnn.accounts.AS400_PortType;
//import vn.com.msb.cnn.accounts.AS400_ServiceLocator;
//import vn.com.msb.cnn.utils.Messages;
//
//public class testOneConnect {
//
//	/**
//	 * @param args
//	 * @throws RemoteException
//	 */
//	public static void main(String[] args) throws RemoteException {
//		// TODO Auto-generated method stub
//		AS400_ServiceLocator as400Service;
//		AS400_PortType as400Port;
//		as400Service = new AS400_ServiceLocator();
//		try {
//			as400Port = as400Service.getAS400HttpPort();
//			for (int i = 0; i < 2000; i++) {
//
//				Messages msg = as400Port.accountInquiry("SMSBK01", "TT110001",
//						"11001010000105", "270712");
//				Thread.sleep(1000);
//				if (msg != null) {
//					System.out.println(msg.getErrCode());
//				}
//			}
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
