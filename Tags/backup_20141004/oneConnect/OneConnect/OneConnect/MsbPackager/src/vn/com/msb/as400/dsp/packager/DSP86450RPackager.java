// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP86450RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP86450RPackager extends DSPPackager
{

    public DSP86450RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPPackedField(2, 0, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPCharField(1, "Description for field 5"), new DSPPackedField(8, 2, "Description for field 6"), new DSPPackedField(7, 2, "Description for field 7"), new DSPPackedField(7, 2, "Description for field 8"), new DSPPackedField(7, 2, "Description for field 9"), new DSPPackedField(7, 2, "Description for field 10"), 
            new DSPPackedField(7, 2, "Description for field 11"), new DSPPackedField(7, 2, "Description for field 12"), new DSPPackedField(7, 2, "Description for field 13"), new DSPPackedField(7, 2, "Description for field 14"), new DSPPackedField(4, 0, "Description for field 15"), new DSPPackedField(4, 0, "Description for field 16"), new DSPPackedField(4, 0, "Description for field 17"), new DSPPackedField(4, 0, "Description for field 18"), new DSPPackedField(2, 0, "Description for field 19"), new DSPPackedField(2, 0, "Description for field 20"), 
            new DSPPackedField(6, 9, "Description for field 21"), new DSPPackedField(8, 2, "Description for field 22"), new DSPCharField(1, "Description for field 23"), new DSPPackedField(6, 9, "Description for field 24"), new DSPCharField(1, "Description for field 25"), new DSPCharField(1, "Description for field 26"), new DSPPackedField(7, 2, "Description for field 27"), new DSPPackedField(7, 2, "Description for field 28"), new DSPPackedField(7, 2, "Description for field 29"), new DSPPackedField(7, 2, "Description for field 30"), 
            new DSPPackedField(7, 2, "Description for field 31"), new DSPPackedField(7, 2, "Description for field 32"), new DSPCharField(1, "Description for field 33"), new DSPPackedField(4, 0, "Description for field 34"), new DSPPackedField(4, 0, "Description for field 35"), new DSPCharField(1, "Description for field 36"), new DSPCharField(1, "Description for field 37"), new DSPPackedField(7, 2, "Description for field 38"), new DSPPackedField(7, 2, "Description for field 39"), new DSPPackedField(7, 2, "Description for field 40"), 
            new DSPPackedField(7, 2, "Description for field 41"), new DSPPackedField(7, 2, "Description for field 42"), new DSPPackedField(7, 2, "Description for field 43"), new DSPPackedField(2, 0, "Description for field 44"), new DSPPackedField(4, 0, "Description for field 45"), new DSPPackedField(4, 0, "Description for field 46"), new DSPCharField(1, "Description for field 47"), new DSPCharField(1, "Description for field 48"), new DSPPackedField(7, 2, "Description for field 49"), new DSPPackedField(6, 9, "Description for field 50")
        });
        addBody("86450R".toUpperCase(), fld);
    }
}
