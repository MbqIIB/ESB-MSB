/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multi.seatechit.mbconnector.processor;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import multi.seatechit.mbconnector.utils.General;
import multi.seatechit.mbconnector.utils.Utils;

/**
 * 
 * @author
 */
public class ThreadManager {
	final Logger logger = Logger.getLogger(ThreadManager.class);
	private static General settings;

	public ThreadManager() {
		Utils.loadHostCommSetting();
		Utils.setDebugMode();
		settings = General.getInstance();
		DOMConfigurator.configure(System.getProperty("LOG4J_PROPFILE_PATH"));
		// fmtFile = new MessageFormatFile(Utils.loadFormatSetting());
	}

	public static ThreadManager newInstance() {
		return new ThreadManager();
	}

	public static void main(String args[]) {
		ThreadManager manager = ThreadManager.newInstance();
		manager.startThreads();
		System.out.println("end time..");

	}

	public void startThreads() {
		// try {
		for (int ii = 0; ii < Integer.parseInt(System.getProperty("THREAD_SIZE")); ii++) {
			String MESSAGE_XML = "MESSAGE_XML_" + String.valueOf(ii);
			ThreadTaskWS task = new ThreadTaskWS();
			task.start();
			// task.sleep(10000);
			// task.join();
		}
		// } catch (InterruptedException ire) {
		// }
	}

}
