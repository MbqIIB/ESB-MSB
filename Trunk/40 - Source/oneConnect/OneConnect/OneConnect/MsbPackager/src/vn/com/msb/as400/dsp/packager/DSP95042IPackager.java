// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP99Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP95042IPackager extends DSPPackager {

	public DSP95042IPackager() {
	}

	public void declare() {
		DSPField fld[] = null;
		fld = (new DSPField[] {
				new DSPCharField(100,"Description for field 3"),
				new DSPCharField(20,"Description for field 4"),
				new DSPCharField(1,"Description for field 5"),
				new DSPCharField(1,"Description for field 6")
		});
		addBody("95042I".toUpperCase(), fld);
	}
}
