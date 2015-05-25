// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPCD21PPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPCD21PPackager extends DSPPackager
{

    public DSPCD21PPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPCharField(17, "Description for field 5"), new DSPCharField(26, "Description for field 6"), new DSPCharField(1, "Description for field 7"), new DSPCharField(1, "Description for field 8"), new DSPCharField(8, "Description for field 9"), new DSPCharField(1, "Description for field 10"), 
            new DSPNumericField(11, "Description for field 11"), new DSPCharField(18, "Description for field 12"), new DSPCharField(12, "Description for field 13"), new DSPNumericField(11, "Description for field 14"), new DSPCharField(1, "Description for field 15"), new DSPCharField(1, "Description for field 16")
        });
        addBody("CD21P".toUpperCase(), fld);
    }
}
