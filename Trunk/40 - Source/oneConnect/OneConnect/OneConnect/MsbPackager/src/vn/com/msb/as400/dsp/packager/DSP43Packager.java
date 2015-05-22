package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP43Packager extends DSPPackager
{

    public DSP43Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPPackedField(10, 0, "Description for field 4"), new DSPPackedField(8, 2, "Description for field 5"), new DSPPackedField(5, 0, "Description for field 6")
        });
        addBody("43".toUpperCase(), fld);
    }
}
