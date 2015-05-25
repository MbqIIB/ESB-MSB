package seatechit.msbgateway.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * Store application parameter on memory
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6 *
 */
public class CachedDBParam extends Global {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6664793052670833838L;
	private static CachedDBParam instance = null;
	protected static final String sFileSeparator;
	static {
		sFileSeparator = File.separator;
	}

	private static Properties loadProperty(String fileName) {
		// try {
		// FileInputStream propFile = new
		// FileInputStream(String.valueOf(String.valueOf((new
		// StringBuffer(String.valueOf(String.valueOf((new
		// File("")).getCanonicalPath())))).append(sFileSeparator)
		// .append(fileName))));
		// Properties p = new Properties(System.getProperties());
		// p.load(propFile);
		// return p;
		// } catch (IOException ioe) {
		// ioe.printStackTrace();
		// }
		// return null;
		try {

			// 1.Tren windows
			// FileInputStream propFile = new FileInputStream(fileName);
			// String.valueOf(String.valueOf((new
			// StringBuffer(String.valueOf(String.valueOf((new
			// File("")).getCanonicalPath())))).append(sFileSeparator).append(fileName)));

			// 2.Tren linux
			FileInputStream propFile = new FileInputStream(fileName);

			Properties p = new Properties(System.getProperties());
			p.load(propFile);
			return p;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Cannot Load loadProperty file." + ioe.getMessage());
		}
		return null;

	}

	// Khong dung dbtype4 cua Mb vi khong ho tro AS400
	private static String oraConnString = "";
	private static String MSBGW_ORADB_CONN_STR = "";// "jdbc:oracle:thin:BEC/bec@10.1.17.89:1521:ibdev";
	// private static String MSBGW_ORADB_CONN_STR =
	// "jdbc:oracle:thin:BEC/bec@10.1.17.43:1521:ibprod";
	static {
		try {
			Properties p1 = loadProperty(Global.APP_DBCONFIG_FILEPATH);
			String key = "", value = "";
			for (Entry<Object, Object> propItem : p1.entrySet()) {
				key = (String) propItem.getKey();
				value = (String) propItem.getValue();
				if (key.equalsIgnoreCase("MSBGW_ORADB_CONN_STR")) {
					oraConnString = value;
				}

			}
			// oraConnString = MSBGW_ORADB_CONN_STR;
		} catch (Exception ex) {
			System.out.println("Cannot Load config file.");
			ex.printStackTrace(System.out);
		}

	}

	public static String getOraConnString() {
		return oraConnString;
	}

	protected CachedDBParam() {
	}

	// have to use synchronized keywork to prevent multithread acccess
	public static synchronized CachedDBParam getInstance() {
		if (instance == null)
			instance = new CachedDBParam();
		return instance;
	}

	/**
	 * Prevent another class try to clone Singlenton object
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
