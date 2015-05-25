// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPHLHISPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPHLHISPackager extends DSPPackager
{

    public DSPHLHISPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPNumericField(6, "Description for field 5"), new DSPNumericField(4, "Description for field 6"), new DSPCharField(7, "Description for field 7"), new DSPCharField(1, "Description for field 8"), new DSPNumericField(11, "Description for field 9"), new DSPCharField(40, "Description for field 10"), 
            new DSPNumericField(6, "Description for field 11"), new DSPNumericField(4, "Description for field 12"), new DSPCharField(7, "Description for field 13"), new DSPCharField(1, "Description for field 14"), new DSPNumericField(11, "Description for field 15"), new DSPCharField(40, "Description for field 16"), new DSPNumericField(6, "Description for field 17"), new DSPNumericField(4, "Description for field 18"), new DSPCharField(7, "Description for field 19"), new DSPCharField(1, "Description for field 20"), 
            new DSPNumericField(11, "Description for field 21"), new DSPCharField(40, "Description for field 22"), new DSPNumericField(6, "Description for field 23"), new DSPNumericField(4, "Description for field 24"), new DSPCharField(7, "Description for field 25"), new DSPCharField(1, "Description for field 26"), new DSPNumericField(11, "Description for field 27"), new DSPCharField(40, "Description for field 28"), new DSPNumericField(6, "Description for field 29"), new DSPNumericField(4, "Description for field 30"), 
            new DSPCharField(7, "Description for field 31"), new DSPCharField(1, "Description for field 32"), new DSPNumericField(11, "Description for field 33"), new DSPCharField(40, "Description for field 34"), new DSPNumericField(6, "Description for field 35"), new DSPNumericField(4, "Description for field 36"), new DSPCharField(7, "Description for field 37"), new DSPCharField(1, "Description for field 38"), new DSPNumericField(11, "Description for field 39"), new DSPCharField(40, "Description for field 40")
        });
        addBody("HLHIS".toUpperCase(), fld);
    }
}
