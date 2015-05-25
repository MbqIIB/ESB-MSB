package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.DSPCharField;
import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.as400.dsp.DSPPackedField;

public class DSP88562RPackager extends DSPPackager{

	 public DSP88562RPackager()
	    {
	    }

	    public void declare()
	    {
	        DSPField fld[] = null;
	        fld = (new DSPField[] {
	            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPCharField(50, "Description for field 5")
	        });
	        addBody("88562R".toUpperCase(), fld);
	    }

}
