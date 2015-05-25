// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP15104IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP15104IPackager extends DSPPackager {

	public DSP15104IPackager() {
	}

	public void declare() {
		DSPField fld[] = null;
		fld = (new DSPField[] {
				new DSPCharField(40, "Description for field 1"),
				new DSPCharField(3, "Description for field 2") });
		addBody("15104I".toUpperCase(), fld);
	}
}
