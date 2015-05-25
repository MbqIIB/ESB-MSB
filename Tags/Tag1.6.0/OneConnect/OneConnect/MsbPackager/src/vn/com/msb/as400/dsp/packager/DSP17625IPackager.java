// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP17625IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP17625IPackager extends DSPPackager
{

    public DSP17625IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPVietField(1, "Description for field 2"), new DSPVietField(1, "Description for field 3"), new DSPVietField(40, "Description for field 4"), new DSPVietField(3, "Description for field 5"), new DSPPackedField(4, 0, "Description for field 6"), new DSPVietField(3, "Description for field 7"), new DSPVietField(3, "Description for field 8"), new DSPVietField(40, "Description for field 9"), new DSPVietField(1, "Description for field 10"), 
            new DSPVietField(10, "Description for field 11"), new DSPVietField(20, "Description for field 12"), new DSPVietField(40, "Description for field 13"), new DSPVietField(40, "Description for field 14"), new DSPVietField(40, "Description for field 15"), new DSPVietField(40, "Description for field 16"), new DSPVietField(40, "Description for field 17"), new DSPVietField(40, "Description for field 18"), new DSPVietField(40, "Description for field 19"), new DSPVietField(20, "Description for field 20"), 
            new DSPVietField(3, "Description for field 21"), new DSPVietField(20, "Description for field 22"), new DSPVietField(9, "Description for field 23"), new DSPVietField(3, "Description for field 24"), new DSPPackedField(4, 0, "Description for field 25"), new DSPVietField(3, "Description for field 26"), new DSPVietField(1, "Description for field 27"), new DSPPackedField(4, 0, "Description for field 28"), new DSPVietField(3, "Description for field 29"), new DSPVietField(1, "Description for field 30"), 
            new DSPVietField(1, "Description for field 31"), new DSPVietField(4, "Description for field 32"), new DSPVietField(1, "Description for field 33"), new DSPVietField(40, "Description for field 34"), new DSPVietField(40, "Description for field 35"), new DSPVietField(2, "Description for field 36"), new DSPVietField(2, "Description for field 37"), new DSPVietField(2, "Description for field 38"), new DSPVietField(30, "Description for field 39"), new DSPVietField(30, "Description for field 40"), 
            new DSPVietField(30, "Description for field 41"), new DSPPackedField(2, 0, "Description for field 42"), new DSPPackedField(3, 0, "Description for field 43"), new DSPVietField(20, "Description for field 44"), new DSPVietField(3, "Description for field 45"), new DSPVietField(40, "Description for field 46"), new DSPVietField(40, "Description for field 47"), new DSPVietField(20, "Description for field 48"), new DSPVietField(20, "Description for field 49"), new DSPVietField(1, "Description for field 50"), 
            new DSPVietField(1, "Description for field 51"), new DSPPackedField(4, 0, "Description for field 52"), new DSPPackedField(5, 0, "Description for field 53"), new DSPPackedField(5, 0, "Description for field 54"), new DSPVietField(2, "Description for field 55"), new DSPVietField(4, "Description for field 56"), new DSPVietField(4, "Description for field 57"), new DSPVietField(4, "Description for field 58"), new DSPVietField(2, "Description for field 59"), new DSPVietField(4, "Description for field 60"), 
            new DSPVietField(4, "Description for field 61"), new DSPVietField(4, "Description for field 62"), new DSPVietField(1, "Description for field 63")
        });
        addBody("17625I".toUpperCase(), fld);
    }
}
