package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.DSPCharField;
import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.as400.dsp.DSPPackedField;

public class DSP86562IPackager extends DSPPackager {
	public DSP86562IPackager() {
	}

	public void declare() {
		DSPField fld[] = null;
		fld = (new DSPField[] {
				new DSPPackedField(10, 0, "Description for field 1"),
				new DSPCharField(1, "Description for field 2"),
				new DSPPackedField(4, 0, "Description for field 3") });
		addBody("86562I".toUpperCase(), fld);
	}

}
