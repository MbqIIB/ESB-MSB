// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPICFD2Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPICFD2Packager extends DSPPackager
{

    public DSPICFD2Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(4, 0, "Description for field 1"), new DSPCharField(5, "Description for field 2"), new DSPCharField(10, "Description for field 3"), new DSPCharField(15, "Description for field 4"), new DSPNumericField(5, "Description for field 5"), new DSPCharField(10, "Description for field 6"), new DSPPackedField(5, 0, "Description for field 7"), new DSPNumericField(6, "Description for field 8"), new DSPCharField(5, "Description for field 9"), new DSPCharField(5, "Description for field 10"), 
            new DSPCharField(4, "Description for field 11"), new DSPCharField(4022, "Description for field 12")
        });
        addBody("ICFD2".toUpperCase(), fld);
    }
}
