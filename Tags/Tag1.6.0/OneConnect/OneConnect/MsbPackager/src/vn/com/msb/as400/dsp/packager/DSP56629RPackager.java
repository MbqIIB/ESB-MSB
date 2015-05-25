// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP56629RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP56629RPackager extends DSPPackager
{

    public DSP56629RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPCharField(16, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPCharField(10, "Description for field 4"), new DSPCharField(2, "Description for field 5"), new DSPPackedField(3, 0, "Description for field 6"), new DSPPackedField(3, 0, "Description for field 7"), new DSPCharField(12, "Description for field 8"), new DSPCharField(40, "Description for field 9"), new DSPCharField(12, "Description for field 10"), 
            new DSPCharField(40, "Description for field 11"), new DSPPackedField(4, 0, "Description for field 12"), new DSPPackedField(9, 2, "Description for field 13"), new DSPPackedField(5, 0, "Description for field 14"), new DSPCharField(16, "Description for field 15"), new DSPPackedField(4, 0, "Description for field 16"), new DSPCharField(1, "Description for field 17"), new DSPPackedField(9, 2, "Description for field 18"), new DSPCharField(1, "Description for field 19"), new DSPPackedField(9, 2, "Description for field 20"), 
            new DSPCharField(4, "Description for field 21"), new DSPPackedField(9, 2, "Description for field 22"), new DSPCharField(8, "Description for field 23"), new DSPPackedField(2, 0, "Description for field 24"), new DSPPackedField(9, 2, "Description for field 25"), new DSPPackedField(5, 0, "Description for field 26")
        });
        addBody("56629R".toUpperCase(), fld);
    }
}
