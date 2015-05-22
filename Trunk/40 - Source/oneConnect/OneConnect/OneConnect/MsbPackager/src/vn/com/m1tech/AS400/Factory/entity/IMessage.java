package vn.com.m1tech.AS400.Factory.entity;

import vn.com.msb.as400.dsp.DSPPackager;

public abstract class IMessage {
	protected String strValue[];
	public String hostname;
	public String teller;
	public String branch;

	public IMessage(DSPPackager packager) {
		try {
			strValue = new String[packager.getFieldDefinitionList().length];
			for (int i = 0; i < strValue.length; ++i) {
				strValue[i] = "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			strValue = new String[900];
			for (int i = 0; i < strValue.length; ++i)
				strValue[i] = "";
		}

	}

	public void setDefault() {
		hostname = "10.0.2.46";
		teller = "DD110083";
		branch = "110";
	}

	public abstract String[] toArray();

	public void printMessage() {
		toArray();
		StringBuffer buff = new StringBuffer();
		for (String str : strValue) {
			buff.append(str).append("|");
		}
		System.out.println(buff);
	}
}
