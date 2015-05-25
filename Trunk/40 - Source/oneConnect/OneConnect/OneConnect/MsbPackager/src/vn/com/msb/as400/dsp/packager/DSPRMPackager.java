// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPRMPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPRMPackager extends DSPPackager
{

    public DSPRMPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPPackedField(1, 0, "Description for field 4"), new DSPPackedField(10, 0, "Description for field 5"), new DSPCharField(1, "Description for field 6"), new DSPCharField(20, "Description for field 7"), new DSPCharField(20, "Description for field 8"), new DSPCharField(40, "Description for field 9"), new DSPPackedField(10, 0, "Description for field 10"), 
            new DSPCharField(10, "Description for field 11"), new DSPCharField(2, "Description for field 12"), new DSPPackedField(4, 0, "Description for field 13"), new DSPPackedField(4, 0, "Description for field 14"), new DSPPackedField(2, 0, "Description for field 15"), new DSPPackedField(4, 0, "Description for field 16"), new DSPPackedField(4, 0, "Description for field 17"), new DSPPackedField(10, 0, "Description for field 18"), new DSPCharField(1, "Description for field 19"), new DSPPackedField(10, 0, "Description for field 20"), 
            new DSPCharField(1, "Description for field 21"), new DSPCharField(12, "Description for field 22"), new DSPPackedField(4, 0, "Description for field 23"), new DSPPackedField(4, 0, "Description for field 24"), new DSPPackedField(3, 0, "Description for field 25"), new DSPPackedField(3, 0, "Description for field 26"), new DSPCharField(1, "Description for field 27"), new DSPPackedField(9, 2, "Description for field 28"), new DSPCharField(4, "Description for field 29"), new DSPCharField(1, "Description for field 30"), 
            new DSPCharField(16, "Description for field 31"), new DSPPackedField(2, 0, "Description for field 32"), new DSPPackedField(4, 0, "Description for field 33"), new DSPPackedField(4, 0, "Description for field 34"), new DSPCharField(1, "Description for field 35"), new DSPPackedField(8, 2, "Description for field 36"), new DSPPackedField(8, 2, "Description for field 37"), new DSPPackedField(8, 2, "Description for field 38"), new DSPPackedField(8, 2, "Description for field 39"), new DSPPackedField(8, 2, "Description for field 40"), 
            new DSPPackedField(8, 2, "Description for field 41"), new DSPPackedField(8, 2, "Description for field 42"), new DSPPackedField(8, 2, "Description for field 43"), new DSPPackedField(8, 2, "Description for field 44"), new DSPPackedField(8, 2, "Description for field 45"), new DSPPackedField(8, 2, "Description for field 46"), new DSPPackedField(8, 2, "Description for field 47"), new DSPPackedField(8, 2, "Description for field 48"), new DSPCharField(3, "Description for field 49"), new DSPPackedField(7, 0, "Description for field 50"), 
            new DSPPackedField(10, 0, "Description for field 51"), new DSPCharField(1, "Description for field 52"), new DSPCharField(1, "Description for field 53"), new DSPCharField(1, "Description for field 54"), new DSPCharField(1, "Description for field 55"), new DSPPackedField(10, 0, "Description for field 56"), new DSPCharField(1, "Description for field 57"), new DSPPackedField(10, 0, "Description for field 58"), new DSPCharField(1, "Description for field 59"), new DSPCharField(3, "Description for field 60"), 
            new DSPCharField(20, "Description for field 61"), new DSPCharField(20, "Description for field 62"), new DSPCharField(40, "Description for field 63"), new DSPCharField(12, "Description for field 64"), new DSPCharField(12, "Description for field 65"), new DSPCharField(20, "Description for field 66"), new DSPCharField(1, "Description for field 67"), new DSPCharField(1, "Description for field 68"), new DSPCharField(1, "Description for field 69"), new DSPPackedField(8, 2, "Description for field 70"), 
            new DSPCharField(4, "Description for field 71"), new DSPCharField(40, "Description for field 72"), new DSPCharField(40, "Description for field 73"), new DSPCharField(1, "Description for field 74"), new DSPCharField(10, "Description for field 75"), new DSPPackedField(2, 0, "Description for field 76"), new DSPPackedField(2, 0, "Description for field 77"), new DSPCharField(20, "Description for field 78"), new DSPCharField(5, "Description for field 79"), new DSPCharField(1, "Description for field 80"), 
            new DSPPackedField(3, 0, "Description for field 81"), new DSPPackedField(3, 0, "Description for field 82"), new DSPPackedField(3, 0, "Description for field 83"), new DSPCharField(1, "Description for field 84"), new DSPCharField(3, "Description for field 85"), new DSPPackedField(7, 0, "Description for field 86"), new DSPCharField(20, "Description for field 87"), new DSPPackedField(10, 0, "Description for field 88"), new DSPPackedField(7, 0, "Description for field 89"), new DSPPackedField(9, 2, "Description for field 90"), 
            new DSPCharField(2, "Description for field 91")
        });
        addBody("RM".toUpperCase(), fld);
    }
}
