// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP26161RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP26161RPackager extends DSPPackager
{

    public DSP26161RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(40, "Description for field 3"), new DSPCharField(40, "Description for field 4"), new DSPCharField(40, "Description for field 5"), new DSPCharField(40, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPCharField(40, "Description for field 8"), new DSPPackedField(10, 0, "Description for field 9"), new DSPPackedField(3, 0, "Description for field 10"), 
            new DSPCharField(4, "Description for field 11"), new DSPCharField(20, "Description for field 12"), new DSPPackedField(4, 0, "Description for field 13"), new DSPCharField(30, "Description for field 14"), new DSPCharField(30, "Description for field 15"), new DSPCharField(78, "Description for field 16"), new DSPPackedField(8, 2, "Description for field 17"), new DSPPackedField(8, 2, "Description for field 18"), new DSPPackedField(8, 2, "Description for field 19"), new DSPPackedField(8, 2, "Description for field 20"), 
            new DSPPackedField(8, 2, "Description for field 21"), new DSPCharField(10, "Description for field 22"), new DSPPackedField(8, 2, "Description for field 23"), new DSPPackedField(8, 2, "Description for field 24"), new DSPPackedField(8, 2, "Description for field 25"), new DSPPackedField(8, 2, "Description for field 26"), new DSPPackedField(8, 2, "Description for field 27"), new DSPPackedField(8, 2, "Description for field 28"), new DSPPackedField(8, 2, "Description for field 29"), new DSPPackedField(8, 2, "Description for field 30"), 
            new DSPPackedField(8, 2, "Description for field 31"), new DSPPackedField(8, 2, "Description for field 32"), new DSPPackedField(8, 2, "Description for field 33"), new DSPPackedField(8, 2, "Description for field 34"), new DSPPackedField(8, 2, "Description for field 35"), new DSPPackedField(8, 2, "Description for field 36"), new DSPPackedField(8, 2, "Description for field 37"), new DSPPackedField(8, 2, "Description for field 38"), new DSPPackedField(8, 2, "Description for field 39"), new DSPPackedField(8, 2, "Description for field 40"), 
            new DSPPackedField(8, 2, "Description for field 41"), new DSPPackedField(8, 2, "Description for field 42"), new DSPPackedField(8, 2, "Description for field 43"), new DSPPackedField(8, 2, "Description for field 44"), new DSPPackedField(8, 2, "Description for field 45"), new DSPPackedField(8, 2, "Description for field 46"), new DSPPackedField(8, 2, "Description for field 47"), new DSPPackedField(6, 9, "Description for field 48"), new DSPPackedField(6, 9, "Description for field 49"), new DSPPackedField(7, 2, "Description for field 50"), 
            new DSPPackedField(1, 0, "Description for field 51"), new DSPPackedField(8, 5, "Description for field 52"), new DSPPackedField(8, 5, "Description for field 53"), new DSPCharField(10, "Description for field 54"), new DSPPackedField(1, 0, "Description for field 55"), new DSPCharField(20, "Description for field 56"), new DSPPackedField(2, 0, "Description for field 57"), new DSPPackedField(2, 0, "Description for field 58"), new DSPPackedField(2, 0, "Description for field 59"), new DSPCharField(1, "Description for field 60"), 
            new DSPCharField(1, "Description for field 61"), new DSPCharField(1, "Description for field 62"), new DSPCharField(15, "Description for field 63"), new DSPPackedField(8, 5, "Description for field 64"), new DSPPackedField(7, 2, "Description for field 65"), new DSPPackedField(7, 2, "Description for field 66"), new DSPPackedField(8, 5, "Description for field 67"), new DSPPackedField(8, 5, "Description for field 68"), new DSPCharField(1, "Description for field 69"), new DSPCharField(1, "Description for field 70"), 
            new DSPCharField(1, "Description for field 71"), new DSPCharField(1, "Description for field 72"), new DSPCharField(2, "Description for field 73"), new DSPCharField(15, "Description for field 74"), new DSPPackedField(8, 2, "Description for field 75"), new DSPPackedField(8, 0, "Description for field 76"), new DSPPackedField(4, 0, "Description for field 77"), new DSPPackedField(4, 0, "Description for field 78"), new DSPPackedField(8, 0, "Description for field 79"), new DSPPackedField(4, 0, "Description for field 80"), 
            new DSPPackedField(4, 0, "Description for field 81"), new DSPCharField(10, "Description for field 82"), new DSPCharField(1, "Description for field 83"), new DSPCharField(1, "Description for field 84"), new DSPPackedField(4, 0, "Description for field 85"), new DSPPackedField(4, 0, "Description for field 86"), new DSPPackedField(4, 0, "Description for field 87"), new DSPPackedField(4, 0, "Description for field 88"), new DSPPackedField(4, 0, "Description for field 89"), new DSPPackedField(4, 0, "Description for field 90"), 
            new DSPCharField(32, "Description for field 91"), new DSPCharField(32, "Description for field 92"), new DSPCharField(32, "Description for field 93"), new DSPCharField(32, "Description for field 94"), new DSPCharField(1, "Description for field 95"), new DSPCharField(1, "Description for field 96"), new DSPCharField(1, "Description for field 97"), new DSPCharField(1, "Description for field 98"), new DSPPackedField(6, 0, "Description for field 99"), new DSPPackedField(4, 0, "Description for field 100"), 
            new DSPPackedField(4, 0, "Description for field 101"), new DSPCharField(3, "Description for field 102"), new DSPCharField(3, "Description for field 103"), new DSPCharField(20, "Description for field 104"), new DSPPackedField(10, 0, "Description for field 105"), new DSPCharField(1, "Description for field 106"), new DSPPackedField(8, 2, "Description for field 107"), new DSPPackedField(6, 9, "Description for field 108"), new DSPCharField(1, "Description for field 109"), new DSPPackedField(6, 9, "Description for field 110"), 
            new DSPPackedField(6, 9, "Description for field 111"), new DSPPackedField(4, 0, "Description for field 112"), new DSPPackedField(3, 0, "Description for field 113"), new DSPCharField(1, "Description for field 114"), new DSPCharField(1, "Description for field 115"), new DSPCharField(20, "Description for field 116"), new DSPCharField(40, "Description for field 117"), new DSPCharField(40, "Description for field 118"), new DSPCharField(3, "Description for field 119"), new DSPCharField(1, "Description for field 120"), 
            new DSPCharField(1, "Description for field 121")
        });
        addBody("26161R".toUpperCase(), fld);
    }
}
