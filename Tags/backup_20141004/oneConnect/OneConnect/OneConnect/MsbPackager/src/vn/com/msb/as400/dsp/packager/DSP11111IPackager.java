package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP11111IPackager extends DSPPackager
{

    public DSP11111IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPPackedField(10, 0, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(2, "Description for field 4")
        });
        addBody("11111I".toUpperCase(), fld);
    }
}
