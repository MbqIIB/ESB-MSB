// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP76100RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP76100RPackager extends DSPPackager
{

    public DSP76100RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPPackedField(10, 0, "Description for field 2"), new DSPPackedField(3, 0, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4"), new DSPCharField(4, "Description for field 5"), new DSPCharField(4, "Description for field 6"), new DSPPackedField(8, 0, "Description for field 7"), new DSPPackedField(8, 0, "Description for field 8"), new DSPCharField(2, "Description for field 9"), new DSPPackedField(8, 0, "Description for field 10"), 
            new DSPCharField(2, "Description for field 11"), new DSPPackedField(8, 0, "Description for field 12"), new DSPCharField(2, "Description for field 13"), new DSPPackedField(8, 0, "Description for field 14"), new DSPCharField(2, "Description for field 15"), new DSPPackedField(8, 0, "Description for field 16"), new DSPCharField(2, "Description for field 17"), new DSPPackedField(8, 0, "Description for field 18"), new DSPCharField(2, "Description for field 19"), new DSPPackedField(8, 0, "Description for field 20"), 
            new DSPCharField(2, "Description for field 21"), new DSPPackedField(8, 0, "Description for field 22"), new DSPCharField(2, "Description for field 23"), new DSPPackedField(8, 0, "Description for field 24"), new DSPCharField(2, "Description for field 25"), new DSPPackedField(8, 0, "Description for field 26"), new DSPCharField(2, "Description for field 27"), new DSPPackedField(8, 0, "Description for field 28"), new DSPCharField(2, "Description for field 29"), new DSPPackedField(8, 0, "Description for field 30"), 
            new DSPCharField(2, "Description for field 31"), new DSPPackedField(8, 0, "Description for field 32"), new DSPCharField(2, "Description for field 33"), new DSPPackedField(8, 0, "Description for field 34"), new DSPCharField(2, "Description for field 35"), new DSPPackedField(8, 0, "Description for field 36"), new DSPCharField(2, "Description for field 37"), new DSPPackedField(8, 0, "Description for field 38"), new DSPCharField(2, "Description for field 39"), new DSPPackedField(8, 0, "Description for field 40"), 
            new DSPCharField(2, "Description for field 41"), new DSPPackedField(8, 0, "Description for field 42"), new DSPCharField(2, "Description for field 43"), new DSPPackedField(8, 0, "Description for field 44"), new DSPCharField(2, "Description for field 45"), new DSPPackedField(8, 0, "Description for field 46"), new DSPCharField(2, "Description for field 47"), new DSPPackedField(8, 0, "Description for field 48"), new DSPCharField(2, "Description for field 49"), new DSPPackedField(8, 0, "Description for field 50"), 
            new DSPCharField(2, "Description for field 51"), new DSPPackedField(8, 0, "Description for field 52"), new DSPCharField(2, "Description for field 53"), new DSPPackedField(8, 0, "Description for field 54"), new DSPCharField(2, "Description for field 55"), new DSPPackedField(8, 0, "Description for field 56"), new DSPCharField(2, "Description for field 57"), new DSPPackedField(8, 0, "Description for field 58"), new DSPCharField(2, "Description for field 59"), new DSPPackedField(8, 0, "Description for field 60"), 
            new DSPCharField(2, "Description for field 61"), new DSPPackedField(8, 0, "Description for field 62"), new DSPCharField(2, "Description for field 63"), new DSPPackedField(8, 0, "Description for field 64"), new DSPCharField(2, "Description for field 65"), new DSPPackedField(8, 0, "Description for field 66"), new DSPCharField(2, "Description for field 67"), new DSPPackedField(8, 0, "Description for field 68"), new DSPCharField(2, "Description for field 69"), new DSPPackedField(8, 0, "Description for field 70"), 
            new DSPCharField(2, "Description for field 71"), new DSPPackedField(8, 0, "Description for field 72"), new DSPCharField(2, "Description for field 73"), new DSPPackedField(8, 0, "Description for field 74"), new DSPCharField(2, "Description for field 75"), new DSPPackedField(8, 0, "Description for field 76"), new DSPCharField(2, "Description for field 77"), new DSPPackedField(8, 0, "Description for field 78"), new DSPCharField(2, "Description for field 79"), new DSPPackedField(8, 0, "Description for field 80"), 
            new DSPCharField(2, "Description for field 81"), new DSPPackedField(8, 0, "Description for field 82"), new DSPCharField(2, "Description for field 83"), new DSPPackedField(8, 0, "Description for field 84"), new DSPCharField(2, "Description for field 85"), new DSPCharField(40, "Description for field 86"), new DSPCharField(40, "Description for field 87")
        });
        addBody("76100R".toUpperCase(), fld);
    }
}
