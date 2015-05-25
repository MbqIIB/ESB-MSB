package vn.com.msb.cnn.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;

public class SysParam {

	private String paramFileName;

	private Properties parameterProps;

	public SysParam() {
	}

	public void init() {
		parameterProps = new Properties();
		FileInputStream in;

		try {
			// now load properties from last invocation
			in = new FileInputStream(paramFileName);
			parameterProps.load(in);
			in.close();
		} catch (IOException ex) {

		}

	}

	private boolean containsParam(String param_name) {
		return parameterProps.containsKey(param_name);
	}

	public boolean setParameter(String param_name, String param_value) {
		if (containsParam(param_name)) {
			// save into db
			parameterProps.setProperty(param_name, param_value);
			return true;
		}
		return false;
	}

	public String getParameter(String param_name) {
		if (containsParam(param_name)) {
			return parameterProps.getProperty(param_name);
		}

		return null;
	}

	public void store() {
		FileOutputStream out;
		try {
			out = new FileOutputStream(paramFileName);
			parameterProps.store(out, null);
			out.close();
		} catch (IOException ex) {
		}
	}

	public void setParamFileName(String paramFileName) {
		this.paramFileName = paramFileName;
	}

	public Properties getParameterProps() {
		return parameterProps;
	}
}
