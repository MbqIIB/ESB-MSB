package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP84Packager extends DSPPackager
{

    public DSP84Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPPackedField(2, 0, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4"), new DSPPackedField(10, 2, "Description for field 5"), new DSPCharField(4, "Description for field 6"), new DSPCharField(1, "Description for field 7"), new DSPCharField(1, "Description for field 8")
        });
        addBody("84".toUpperCase(), fld);
    }
}
