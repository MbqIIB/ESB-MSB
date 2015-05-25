package multi.seatechit.mbconnector.processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.Logger;

import multi.seatechit.mbconnector.utils.Global;
import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.as400gw.AS400_PortType;
import seatechit.msbgateway.proxy.as400gw.AS400_ServiceLocator;
import seatechit.msbgateway.proxy.as400gw.Messages;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedDBParam;
import seatechit.msbgateway.utils.CachedParam;

public class ThreadTaskWS extends Thread implements Global {
	final Logger logger = Logger.getLogger(ThreadManager.class);
	private boolean noMore;
	private String MESSAGE_XML;
	private AS400_ServiceLocator as400Service;
	protected AS400_PortType as400Port;
	protected Messages retMessage = null;
	protected Connection oraConn;

	public ThreadTaskWS() {

	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;

		String driverName = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);

		DriverManager.setLoginTimeout(60);
		conn = DriverManager.getConnection("jdbc:oracle:thin:msb_esb/msb_esb@10.0.2.216:1521:ESB");
		if (conn != null) {
			conn.setAutoCommit(false);
		}

		return conn;

	}

	public void run() {

		long start = new Date().getTime();
		int k = 1;
		Statement statement = null;
		do {
			try {
				for (int j = 1; j < 100; j++) {
					if (k <= Integer.parseInt(System.getProperty("TOTAL_TYPE_MESSAGE"))) {
						oraConn = getConnection();
						MESSAGE_XML = "MESSAGE_XML_" + String.valueOf(k);
						if (k == 3)
							k = 0;
						as400Service = new AS400_ServiceLocator();
						//as400Service.setServiceTimeout(60000);
						as400Service.setAS400HttpPortEndpointAddress("http://10.0.2.215:7080/OneConnectServices/services/AS400");
					
						as400Port = as400Service.getAS400HttpPort();
						retMessage = as400Port.hostMessageSending("CC001", System.getProperty(MESSAGE_XML).toString());
						System.out.println("==========================");
						System.out.println("Msg:" + MESSAGE_XML);
						System.out.println("RequestMsg:" + System.getProperty(MESSAGE_XML).toString());
						String returnMsg = AppUtils.convertArrayToString(retMessage.getArrString());
						System.out.println("ResponseMsg:" + returnMsg);
						System.out.println("==========================");
						String insertTableSQL = "insert into temp_tbl(MSG_ID,IN_MSG,OUT_MSG) values ('" + MESSAGE_XML + "','"
								+ System.getProperty(MESSAGE_XML).toString().substring(0, System.getProperty(MESSAGE_XML).toString().length()) + "','"
								+ returnMsg.substring(0, returnMsg.length()) + "')";
						statement = oraConn.createStatement();
						statement.executeUpdate(insertTableSQL);
						statement.close();
						oraConn.close();
						k++;
					}
				}
				noMore = true;
				continue;
			} catch (Exception e) {
				System.out.println(e.toString());

			} finally {

				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (oraConn != null) {
					try {
						oraConn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		} while (!noMore);
		System.out.println(Thread.currentThread().getName() + " Tong thoi gian " + System.getProperty("MESSAGE_SIZE") + " message: " + (new Date().getTime() - start) + "ms");
		logger.info(Thread.currentThread().getName() + " Tong thoi gian " + System.getProperty("MESSAGE_SIZE") + " message: " + (new Date().getTime() - start) + "ms");

	}

}
