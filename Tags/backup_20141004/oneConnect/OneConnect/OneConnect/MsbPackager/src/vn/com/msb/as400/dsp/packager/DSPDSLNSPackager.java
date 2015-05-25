// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPDSLNSPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPDSLNSPackager extends DSPPackager
{

    public DSPDSLNSPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(30, "Description for field 1"), new DSPNumericField(19, "Description for field 2"), new DSPNumericField(19, "Description for field 3"), new DSPCharField(3, "Description for field 4"), new DSPNumericField(19, "Description for field 5"), new DSPNumericField(15, "Description for field 6"), new DSPNumericField(15, "Description for field 7"), new DSPCharField(1, "Description for field 8"), new DSPCharField(4, "Description for field 9"), new DSPNumericField(7, "Description for field 10"), 
            new DSPCharField(5, "Description for field 11"), new DSPCharField(6, "Description for field 12"), new DSPNumericField(6, "Description for field 13"), new DSPCharField(20, "Description for field 14"), new DSPNumericField(13, "Description for field 15"), new DSPCharField(3, "Description for field 16"), new DSPCharField(8, "Description for field 17"), new DSPCharField(20, "Description for field 18")
        });
        addBody("DSLNS".toUpperCase(), fld);
    }
}
