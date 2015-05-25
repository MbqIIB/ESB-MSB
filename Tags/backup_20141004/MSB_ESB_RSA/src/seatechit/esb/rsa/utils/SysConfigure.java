package seatechit.esb.rsa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

public class SysConfigure {

	//private static final String RSA_CONFIG_FILE_PATH = "C:\\RSA\\production2\\rsa.properties";
	//private static final String RSA_CONFIG_FILE_PATH = "//home//rsa_esb//rsa.properties";
	private static final String RSA_CONFIG_FILE_PATH = "//opt//IBM//mqsi//8.0.0.1//bin//esb_rsa//rsa.properties";
	public static String getRSAConfigItem(String keyName) {
		String key = "";
		String value = "";
		try {
			Properties p1 = loadProperty(RSA_CONFIG_FILE_PATH);

			for (Entry<Object, Object> propItem : p1.entrySet()) {
				key = (String) propItem.getKey();
				if (key.equals(keyName)) {
					value = (String) propItem.getValue();
				}
			}
		} catch (Exception ex) {
			System.out.println("Cannot Load config file.");
			ex.printStackTrace(System.out);
		}
		return value;
	}

	private static Properties loadProperty(String fileName) {
		try {
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

}
