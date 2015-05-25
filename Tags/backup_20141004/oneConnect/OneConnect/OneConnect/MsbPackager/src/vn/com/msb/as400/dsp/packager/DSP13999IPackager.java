package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP13999IPackager extends DSPPackager
{

    public DSP13999IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(8, "Description for field 1"), new DSPCharField(1, "Description for field 2")
        });
        addBody("13999I".toUpperCase(), fld);
    }
}
