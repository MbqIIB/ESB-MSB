// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP38901RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.DSPCharField;
import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.as400.dsp.DSPPackedField;

public class DSP38902RPackager extends DSPPackager {

	public DSP38902RPackager() {
	}

	public void declare() {
		DSPField fld[] = null;
		fld = (new DSPField[] {
				new DSPPackedField(10, 0, "Description for field 1"),
				new DSPPackedField(4, 0, "Description for field 2"),
				new DSPPackedField(4, 0, "Description for field 3"),
				new DSPPackedField(6, 9, "Description for field 4"),
				new DSPPackedField(3, 0, "Description for field 5"),
				new DSPPackedField(6, 9, "Description for field 6"),
				new DSPCharField(1, "Description for field 7")
				 });
		addBody("38902R".toUpperCase(), fld);
	}
}
