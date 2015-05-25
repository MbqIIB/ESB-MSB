package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP15031RPackager extends DSPPackager
{

    public DSP15031RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(6, "Description for field 1"), new DSPCharField(40, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3")
        });
        addBody("15031R".toUpperCase(), fld);
    }
}
