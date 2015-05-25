package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP13999RPackager extends DSPPackager
{

    public DSP13999RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(8, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(20, "Description for field 4")
        });
        addBody("13999R".toUpperCase(), fld);
    }
}
