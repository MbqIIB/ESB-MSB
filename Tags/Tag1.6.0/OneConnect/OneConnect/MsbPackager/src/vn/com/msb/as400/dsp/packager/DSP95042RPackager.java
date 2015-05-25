// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP99Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP95042RPackager extends DSPPackager {

	public DSP95042RPackager() {
	}

	public void declare() {
		DSPField fld[] = null;
		fld = (new DSPField[] {
				new DSPCharField(100,"Description for field 3"),
				new DSPCharField(20,"Description for field 4"),
				new DSPCharField(1,"Description for field 5"),
				new DSPCharField(1,"Description for field 6"),
				new DSPCharField(4,"Description for field 7"),
				new DSPCharField(4,"Description for field 8"),
				new DSPCharField(2,"Description for field 9"),
				new DSPCharField(4,"Description for field 10"),
				new DSPNumericField(8,"Description for field 11"),
				new DSPNumericField(6,"Description for field 12"),
				new DSPCharField(10,"Description for field 13"),
				new DSPCharField(10,"Description for field 14"),
				new DSPCharField(40,"Description for field 15")
		});
		addBody("95042R".toUpperCase(), fld);
	}
}
