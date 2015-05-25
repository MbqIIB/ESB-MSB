// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPRMHEADERPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPRMHEADERPackager extends DSPPackager
{

    public DSPRMHEADERPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(5, "Description for field 1"), new DSPCharField(15, "Description for field 2"), new DSPNumericField(5, "Description for field 3"), new DSPNumericField(6, "Description for field 4"), new DSPCharField(1, "Description for field 5"), new DSPNumericField(4, "Description for field 6"), new DSPNumericField(6, "Description for field 7"), new DSPNumericField(4, "Description for field 8"), new DSPCharField(4, "Description for field 9"), new DSPCharField(10, "Description for field 10"), 
            new DSPCharField(10, "Description for field 11"), new DSPCharField(10, "Description for field 12"), new DSPCharField(6, "Description for field 13"), new DSPCharField(8, "Description for field 14"), new DSPNumericField(11, "Description for field 15"), new DSPNumericField(2, "Description for field 16"), new DSPCharField(20, "Description for field 17"), new DSPCharField(16, "Description for field 18"), new DSPCharField(4, "Description for field 19"), new DSPCharField(22, "Description for field 20"), 
            new DSPCharField(12, "Description for field 21"), new DSPNumericField(4, "Description for field 22"), new DSPNumericField(4, "Description for field 23"), new DSPNumericField(4, "Description for field 24"), new DSPCharField(10, "Description for field 25"), new DSPCharField(10, "Description for field 26"), new DSPCharField(10, "Description for field 27"), new DSPCharField(1, "Description for field 28"), new DSPCharField(1, "Description for field 29"), new DSPCharField(20, "Description for field 30")
        });
        addHeader("RMHEADER".toUpperCase(), fld);
    }
}
