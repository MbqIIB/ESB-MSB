// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPABCSHEADERPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPABCSHEADERPackager extends DSPPackager {

	public DSPABCSHEADERPackager() {
	}

	public void declare() {
		DSPField fld[] = null;
		fld = (new DSPField[] {
				new DSPCharField(5, "Header Type"),
				new DSPCharField(15, "Device Name"),
				new DSPNumericField(5, "Socket Number"),
				new DSPNumericField(6, "Port Number"),
				new DSPCharField(1, "Filler"),
				new DSPNumericField(4, "Header Length"),
				new DSPNumericField(6, "Message Length"),
				new DSPNumericField(4, "Version Number"),
				new DSPCharField(4, "Header Format ID"),
				new DSPCharField(10, "Data Format ID"),
				new DSPCharField(10, "Source ID"),
				new DSPCharField(10, "Destination ID"),
				new DSPCharField(6, "Routing Number"),
				new DSPCharField(8, "Message Status"),
				new DSPNumericField(11, "Bank ID Number"),
				new DSPNumericField(2, "Node"),
				new DSPCharField(20, "Exchange ID"),
				new DSPCharField(16, "Scenario Number"),
				new DSPCharField(4, "Txn Code/Sub SSNO"),
				new DSPCharField(22, "Retrieval Reference No."),
				new DSPCharField(12, "Acquirer Reference No."),
				new DSPNumericField(4, "Transmission Number"),
				new DSPNumericField(4, "Records to be loaded"),
				new DSPNumericField(4, "Errors to be loaded"),
				new DSPCharField(10, "User ID"),
				new DSPCharField(10, "Terminal ID"),
				new DSPCharField(10, "Supervisor ID"),
				new DSPCharField(1, "More record indicator"),
				new DSPCharField(1, "Cut-off indicator"),
				new DSPCharField(20, "User Data"),
				new DSPBinaryField(4, "Message Length"),
				new DSPCharField(5, "Message Header"),
				new DSPCharField(10, "Router Queue"),
				new DSPCharField(15, "Device"),
				new DSPNumericField(5, "Message number"),
				new DSPCharField(10, "Communications Queue"),
				new DSPPackedField(5, 0, "Final socket addr"),
				new DSPNumericField(6, "Entry time"),
				new DSPCharField(5, "Transaction type"),
				new DSPCharField(5, "RESERVED"),
				new DSPCharField(4, "EOM group indicator") 
				});
		addHeader("ABCS".toUpperCase(), fld);
	}
}
